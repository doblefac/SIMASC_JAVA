package com.ccb.simasc.integracion.manejadores.utilidades;

import static com.ccb.simasc.transversal.utilidades.UtilReflection.consultarAtributoId;
import static com.ccb.simasc.transversal.utilidades.UtilReflection.consultarAtributoPKCompleja;
import static com.ccb.simasc.transversal.utilidades.UtilReflection.consultarAtributosClase;
import static com.ccb.simasc.transversal.utilidades.UtilReflection.consultarMetodoGetAtributo;
import static com.ccb.simasc.transversal.utilidades.UtilReflection.esPkCompleja;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Administrador de persistencia para todas las tareas que incluyan acceso a la
 * base de datos.
 *
 * @param <T> Clase de la entidad asociada al manejador
 */
@Stateless
public class ManejadorPersistencia<T> implements Serializable {

    // Atributos
    @PersistenceContext
    private transient EntityManager em;
    
    @Inject
    private ApplicationRequestContext appContext;

    /**
    * Variable estatica para imprimir logs...
    */
    private static final Logger logger = LogManager.getLogger(ManejadorPersistencia.class.getName());

    /**
     * Persiste un objeto en la base de datos.
     *
     * @param obj Instancia de la entidad T a crear en la base de datos
     */
    public void create(Object obj) {
    	asignarAtributosAuditoria(obj);
        em.persist(obj);
        em.flush();
    }
    
    /**
     * Asigna de forma general los atribnutos de auditoría definidos para las entidades
     * @param obj
     */
    private void asignarAtributosAuditoria(Object obj) {
    	for(String campoAuditable : UtilConstantes.CAMPOS_AUDITABLES)
    		asignarValorDeCampo(obj,campoAuditable,appContext.getContextoSesion());
    }	
    /**
     * Asigna por reflección los campos de auditoría para una entidad en general
     * @param obj
     * @param campoAuditable
     */
	private void asignarValorDeCampo(Object obj, String campoAuditable, ContextoDeSesion cs) {
		Class<?> claseDeAsignacion = obj.getClass();
		try {
			Field campo = claseDeAsignacion.getDeclaredField(campoAuditable);
			campo.setAccessible(true);
			asignarValorPorTipoCampo(campo,claseDeAsignacion.cast(obj), campoAuditable,cs);
		} catch (NoSuchFieldException e) {
			//La entidad no posee un campo auditable basado en la definición por tanto se omite la asignación
			LogManager.getLogger(ManejadorPersistencia.class.getName()).log(Level.WARN, "", e);
		} 
	}
	
	/**
     * Asigna por reflección los valores de los campos de auditoría para una entidad en general
	 * @param campo Representación del campo
	 * @param obj Objeto a asignar el valor
	 * @param nombreCampo El nombre del campo
	 */
	private void asignarValorPorTipoCampo(Field campo, Object obj,String nombreCampo, ContextoDeSesion cs) {
		try{
			Class<?> claseDeAsignacion = obj.getClass();
			switch(nombreCampo){
			    case UtilConstantes.AUDITABLE_IDUSUARIOMODIFICACION:
			    	if(cs!=null && cs.getIdUsuario()!=null)campo.set(claseDeAsignacion.cast(obj), cs.getNombreUsuario());
			    	else
			    		if(campo.get(claseDeAsignacion.cast(obj)) == null)
			    			campo.set(claseDeAsignacion.cast(obj), UtilConstantes.USUARIO_DEFECTO_SIMASC);
			    	break;
			    case UtilConstantes.AUDITABLE_FECHAULTIMAMODIFICACION:
			    	campo.set(claseDeAsignacion.cast(obj), new Date());
			    	break;
			}
		}catch(Exception e){
			LogManager.getLogger(ManejadorPersistencia.class.getName()).log(Level.FATAL, "", e);
		}
	}

	/**
     * Persiste un objeto en la base de datos.
     *
     * @param obj Instancia de la entidad T a crear en la base de datos
     */
    public void create(Object obj, EntityManager em) {
        if(em!=null){
        	asignarAtributosAuditoria(obj);
        	em.persist(obj);
        }else{
        	asignarAtributosAuditoria(obj);
        	this.create(obj);
        }
    	
        //em.flush();
    }
    
    /**
     * Persiste un objeto en la base de datos sin setear los atributos de auditoria.
     *
     * @param obj Instancia de la entidad T a crear en la base de datos
     */
    public void createSinAtributosAuditoria(Object obj) {
        em.persist(obj);
        em.flush();
    }

    /**
     * Elimina un objeto de la obase de datos identificado por idObj.
     *
     * @param claseEntidad Clase de la entidad T
     * @param idObj  Puede ser una instancia de una pk compuesto de una entidad o un id simple (Ej. Integer, String, Long, etc)
     */
    public <R> void delete(Class<R> claseEntidad, Object idObj) {
                             
        Query q = construirQueryEliminacion(claseEntidad, idObj);    
        q.executeUpdate();
        
    }
    
    /**
     * Devuelve un query delete del objeto identificado por idObj
     * @param claseEntidad Clase de la entidad T
     * @param idObj : Puede ser una instancia de una pk compuesto de una entidad o un id simple (Ej. Integer, String, Long, etc)
     * @return Query
     */
    public <R> Query construirQueryEliminacion(Class<R> claseEntidad, Object idObj){
        
        //Construcción de la condición
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM " + claseEntidad.getSimpleName());
        sql.append(" p WHERE");
        if(esPkCompleja(idObj)){
            List<String> atributosId = consultarAtributosClase(idObj.getClass());
            String atributoPKEntity = consultarAtributoPKCompleja(claseEntidad);
            for(int i = 0; i<atributosId.size(); i++){
                String referenciaAtributo = atributoPKEntity + "." + atributosId.get(i);
                if(i>0){
                    sql.append(" AND");
                }
                sql.append(" p." + referenciaAtributo + " = :" + atributosId.get(i));
            }
        }else{
            String atributo = consultarAtributoId(claseEntidad);
            sql.append(" p." + atributo + " = :" + atributo);
        }                        
                       
        Query q = em.createQuery(sql.toString());
        if(esPkCompleja(idObj)){
            List<String> atributosId = consultarAtributosClase(idObj.getClass());
            for(String atributo : atributosId){
                try {
                    q.setParameter(atributo, consultarMetodoGetAtributo(idObj, atributo).invoke(idObj));
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    LogManager.getLogger(ManejadorPersistencia.class.getName()).log(Level.FATAL, "", ex);
                }
            }
        }else{
            q.setParameter(consultarAtributoId(claseEntidad), idObj);
        }
        
        return q;
    }    

    /**
     * Elimina los objetos en cascada deacuerdo a su mapeo
     *
     * @param o Objeto a eliminar
     */
    public void deleteCascade(Object o) {
        em.remove(em.merge(o));
    }

    /**
     * Limpia toda la informaci√≥n en cache para la sesi√≥n actual
     */
    public void clear() {
        em.clear();
    }

    /**
     * Elimina un objeto de la base de datos.
     *
     * @param obj Instancia de la entidad T a eliminar de la base de datos
     */
    public void delete(Object obj) {
        em.remove(em.merge(obj));
        em.flush();
    }


    /**
     * Actualiza un objeto en la base de datos.
     *
     * @param obj Instancia de la entidad T a actualizar en la base de datos
     */
    public void update(Object obj) {
    	asignarAtributosAuditoria(obj);
    	em.merge(obj);
    	em.flush();
    }
    
    /**
     * Actualiza un objeto en la base de datos.
     *
     * @param obj Instancia de la entidad T a actualizar en la base de datos
     */
    public void updateWithoutFlush(Object obj) {
    	asignarAtributosAuditoria(obj);
    	em.merge(obj);
    }
    
    /**
     * Actualiza un objeto en la base de datos.
     *
     * @param obj Instancia de la entidad T a actualizar en la base de datos
     */
    public void update(Object obj, EntityManager em) {
    	asignarAtributosAuditoria(obj);
    	em.merge(obj);
    	//em.flush();
    }
    
    /**
     * Actualiza un objeto en la base de datos.
     *
     * @param obj Instancia de la entidad T a actualizar en la base de datos
     */
    public Object updateObject(Object obj) {   	
    	asignarAtributosAuditoria(obj);
        return em.merge(obj);
    }

    /**
     * Actualiza un objeto desde la base de datos.
     *
     * @param obj Instancia de la entidad T a actualizar desde la base de datos
     */
    public void refresh(Object obj) {
    	asignarAtributosAuditoria(obj);
        em.refresh(obj);
    }

    /**
     * Busca un objeto en la base de datos.
     * @param <T> Identificador de la clase de una entidad
     * @param cl Clase de la entidad T
     * @param pk Llave primaria de la instancia de la entidad T a buscar
     * @return El objeto de la clase T identificado con el pk especificado 
     */
    public T find(Class<T> cl, Object pk) {
        return em.find(cl, pk);
    }
    
    /**
     * Busca un objeto en la base de datos.
     * @param <T> Identificador de la clase de una entidad
     * @param cl Clase de la entidad T
     * @param pk Llave primaria de la instancia de la entidad T a buscar
     * @param lockMode Indica el tipo de bloqueo que se va a manejar en la consulta
     * @return El objeto de la clase T identificado con el pk especificado 
     */
    public T find(Class<T> cl, Object pk, LockModeType lockMode) {
        return em.find(cl, pk, lockMode);
    }    
    
    /**
     * Busca un objeto en la base de datos.
     * @param <T> Identificador de la clase de una entidad
     * @param cl Clase de la entidad T
     * @param pk Llave primaria de la instancia de la entidad T a buscar
     * @return El objeto de la clase T identificado con el pk especificado 
     */
    public T find(Class<T> cl, Object pk, EntityManager em) {
    	if(em!=null){
    		return em.find(cl, pk);
    	}else{
    		return this.find(cl, pk);
    	}
        
    }

    /**
     * Obtiene una lista con todos los resultados de una entidad.
     *
     * @param obj Instancia de una entidad (No importan los valores)
     * @return List Lista de todas las instancias encontradas para la entidad del objeto obj
     */
    public List<?> listData(Object obj) {
        return em.createNamedQuery(obj.getClass().getSimpleName() + ".findAll").getResultList();
    }

    /**
     * Obtiene una lista con todos los resultados de una entidad.
     *
     * @param clazz Clase de la entidad a consultar
     * @return List Lista de todas las instancias encontradas para la entidad de la clase clazz
     */
    public List<?> listData(Class<T> clazz) {
        return em.createNamedQuery(clazz.getSimpleName() + ".findAll").getResultList();
    }

    /**
     * Obtiene una lista con todos los resultados de un query.
     *
     * @param q Query jpql a ejecutar
     * @return List Lista con los resultados de la ejecución del query
     */
    public List<?> listData(Query q) {    	
        return q.getResultList();
    }

    /**
     * Crea un nuevo query JPQL
     *
     * @param sql El query en formato JPQL
     * @return El query JPQL
     */
    public Query createQuery(String sql) {
        return em.createQuery(sql);
    }
    
    /**
     * Crea un nuevo query JPQL
     *
     * @param sql El query en formato JPQL
     * @return El query JPQL
     */
    public Query createQuery(String sql, EntityManager em) {
    	if(em!=null){
    		return em.createQuery(sql);
    	}else{
    		return this.createQuery(sql);
    	}
        
    }

    /**
     * Crea un nuevo query a partir del nombre dado
     *
     * @param sql El query nombrado en formato jpql
     * @return El query JPQL
     */
    public Query createNamedQuery(String sql) {
        return em.createNamedQuery(sql);
    }

    /**
     * Obtiene un referencia a una instancia de la clase dada
     * @param <R> Identificador de una clase
     * @param clazz Clase de la cual se quiere obtener la referencia
     * @param id Identificar del objeto del cual se quiere obtener la referencia
     * @return El objeto identificado con el id especificado
     */
    public <R> R getReference(Class<R> clazz, Object id) {
        return em.getReference(clazz, id);
    }

    /**
     * Ejecuta el conteo de todos los registros de la tabla corrspondiente a la clase c
     * 
     * @param c La clase de la entidad sobre la cual se va a realizar la consulta
     * @return El número de registro en la tabla de la entidad c
     */
    public <R> int getCount(Class<R> c) {
        return ((Long) em.createQuery("select count(c) from " + c.getSimpleName() + " c").getSingleResult()).intValue();
    }

    /**
     * Ejecuta el "Callable" que llega como parametro en el contexto de
     * persistencia
     *
     * @param <V> El tipo de resultado generado por el "Callable"
     * @param c El "Callable"
     * @return El resultado de la ejecución
     */
    public <V> V executeInPersistenceContext(Callable<V> c) {
        V resultado = null;
        try {
            resultado = c.call();
        } catch (Exception ex) {
            logger.error(ex);
        }
        return resultado;
    }   

    /**
     * Ejecuta el query nativo sel que se pasa como parámetro
     * @param querystr Query nativo en formato sql
     * @return La lista con los resultados de la ejecución del query
     */
    public List<Object[]> doNativeQuery(String querystr) {

        logger.debug("NATIVE QRY> " + querystr);
        
        return em.createNativeQuery(querystr).getResultList();

    }
    
    /**
     * Obtiene un único objeto resultado de la ejecución del query
     * 
     * @param q Query jpql a ejecutar
     * @return El resultado de la consulta 
     */
    public Object getData(Query q) {
    	Object objeto;
    	try{
            objeto = q.getSingleResult();
    	}catch(NoResultException e){
            logger.error(e);
            objeto = null;
    	}
    	
        return objeto;
    }
    
    public EntityManager getEntityManager(){
    	//em.setFlushMode(FlushModeType.COMMIT);
    	return em;
    }
    
    /**
     * Persiste un objeto en la base de datos sin setear los atributos de auditoria.
     *
     * @param obj Instancia de la entidad T a crear en la base de datos
     */
    public void actualizarSinAtributosAuditoria(Object obj) {
        em.merge(obj);
        em.flush();
    }
    
    public Query createNativeQuery(String strSql) {
    	return em.createNativeQuery(strSql);
    }
    
    public Query createNativeQuery(String strSql, Class<T> c) {
    	return em.createNativeQuery(strSql, c);
    }

}
