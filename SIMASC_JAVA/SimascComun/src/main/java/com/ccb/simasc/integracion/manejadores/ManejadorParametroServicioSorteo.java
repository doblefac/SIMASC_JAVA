package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.ParametroServicioSorteo;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.entidades.Rol;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad ParametroServicioSorteo.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorParametroServicioSorteo extends ManejadorCrud<ParametroServicioSorteo,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorParametroServicioSorteo() {
        super(ParametroServicioSorteo.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    public boolean rolTieneLista(String nombreRol) {
    	StringBuilder string = new StringBuilder();     	
    	string.append("SELECT COUNT(DISTINCT ID_ROL) FROM PARAMETRO_SERVICIO_SORTEO PS ");
    	string.append(" WHERE PS.ID_ROL = (SELECT ID_ROL FROM ROL WHERE NOMBRE  = ?");
    	string.append(" AND ESTADO_REGISTRO = ?2 ");
    	string.append(" ) AND PS.ESTADO_REGISTRO = ?2 ");
    	string.append(" AND PS.SORTEO_CON_LISTA = 1");
    	
    	Query query = getEntityManager().createNativeQuery(string.toString());
    	query.setParameter(1, nombreRol);
    	query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	
    	try { 
    		return ((int) query.getSingleResult()) > 0;			
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }
    
    /**
	 * Obtiene los nombres de los roles parametrizados como árbitros
	 * 
	 * @return
	 */
	public List<String> obtenerRolesParametroServicioSorteo() {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT DISTINCT r.nombre FROM ParametroServicioSorteo pss ");
		jpqlQuery.append("INNER JOIN pss.rol r ");
		jpqlQuery.append("WHERE pss.estadoRegistro = :")
				.append(ParametroServicioSorteo.ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(ParametroServicioSorteo.ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();
	}

	/**
     * query que consulta los Roles de los Arbitros que aparecen en la tabla parametro_servicio_sorteo
     * @return List<Rol>
     */
    public List<Rol> consultarRolesPrestadores(){
    	StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append("SELECT DISTINCT(p.rol) FROM ParametroServicioSorteo p ");
    	jpqlQuery.append(" WHERE p.estadoRegistro = : ").append(ParametroServicioSorteo.ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ESTADO_REGISTRO);
    	jpqlQuery.append(" AND p.rol.estadoRegistro = : ").append(Rol.ENTIDAD_ROL_ESTADO_REGISTRO);
    	
    	Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(ParametroServicioSorteo.ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Rol.ENTIDAD_ROL_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return (List<Rol>) query.getResultList();
    }
	
	public String obtenerRol(Long idCaso) {
    	StringBuilder string = new StringBuilder();     	
    	string.append("select d.nombre from PARAMETRO_SERVICIO_SORTEO pss ");
    	string.append("inner join caso c on pss.id_servicio = c.id_servicio and c.id_caso = ?2 and c.estado_registro = ?1 ");
    	string.append("inner join rol r on r.id_rol = pss.id_rol  and r.estado_registro = ?1 ");
    	string.append("inner join DOMINIO d on d.codigo = r.nombre and d.dominio = ?3 ");
    	string.append("where pss.estado_registro = ?1 ");
    	
    	
    	Query query = getEntityManager().createNativeQuery(string.toString());
    	query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	query.setParameter(2, idCaso);
    	query.setParameter(3, UtilDominios.DOMINIO_ROL_PERSONA);
    	
    	try { 
    		return (String) query.getSingleResult();			
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }
    
    public boolean sorteoConMateria(Long idServicio) {
    	return sqlSorteo(false, idServicio);
    }
    
    public boolean sqlSorteo(boolean isLista,Long idServicio) {
    	StringBuilder string = new StringBuilder();
    	if(isLista) {
    		string.append("SELECT SORTEO_CON_LISTA " );
    	}else {
    		string.append("SELECT SORTEO_CON_MATERIA ");
    	}
    	     	
    	string.append(" FROM PARAMETRO_SERVICIO_SORTEO PS ");
    	string.append(" WHERE PS.ID_SERVICIO = ?1 ");
    	string.append(" AND ESTADO_REGISTRO = ?2 ");
    	
    	Query query = getEntityManager().createNativeQuery(string.toString());
    	query.setParameter(1, idServicio);
    	query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	try { 
    		return (boolean) query.getSingleResult();			
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }
    
    public boolean sorteoConLista(Long idServicio) {
    	return sqlSorteo(true, idServicio);
    }

    public boolean bloqueaSuplente(Long idCaso) {
    	StringBuilder string = new StringBuilder();     	
    	string.append("select top 1 bloquea_suplente from PARAMETRO_SERVICIO_SORTEO pss ");
    	string.append("inner join caso c on c.id_servicio = pss.id_servicio ");
    	string.append("where id_caso = ?1 ");
    	
    	Query query = getEntityManager().createNativeQuery(string.toString());
    	query.setParameter(1, idCaso);
    	try { 
    		return (boolean) query.getSingleResult();			
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }
    // protected region metodos adicionales end
 
    public boolean suspendeNoPronunciamiento(Long idCaso) {
    	StringBuilder string = new StringBuilder();     	
    	string.append("select top 1 suspende_no_pronunciamiento ");
		string.append("from PARAMETRO_SERVICIO_SORTEO pss ");
    	string.append("inner join caso c on c.id_servicio = pss.id_servicio ");
    	string.append("where id_caso = ?1 ");
    	
    	Query query = getEntityManager().createNativeQuery(string.toString());
    	query.setParameter(1, idCaso);
    	try { 
    		return (boolean) query.getSingleResult();				
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }
    
    public boolean suspendeExtemporaneo(Long idCaso) {
    	StringBuilder string = new StringBuilder();     	
    	string.append("select top 1 suspende_extemporaneo ");
		string.append("from PARAMETRO_SERVICIO_SORTEO pss ");
    	string.append("inner join caso c on c.id_servicio = pss.id_servicio ");
    	string.append("where id_caso = ?1 ");
    	
    	Query query = getEntityManager().createNativeQuery(string.toString());
    	query.setParameter(1, idCaso);
    	try { 
    		return (boolean) query.getSingleResult();				
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }
    
    public boolean suspendeRechazo(Long idCaso) {
    	StringBuilder string = new StringBuilder();     	
    	string.append("select top 1 suspende_rechazo ");
		string.append("from PARAMETRO_SERVICIO_SORTEO pss ");
    	string.append("inner join caso c on c.id_servicio = pss.id_servicio ");
    	string.append("where id_caso = ?1 ");
    	
    	Query query = getEntityManager().createNativeQuery(string.toString());
    	query.setParameter(1, idCaso);
    	try { 
    		return (boolean) query.getSingleResult();				
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }
    
    public String audenciaLiberaSuplente(Long idCaso) {
    	StringBuilder string = new StringBuilder();  
    	string.append("select top 1 audiencia_libera_suplente from PARAMETRO_SERVICIO_SORTEO pss ");
    	string.append("inner join caso c on c.id_servicio = pss.id_servicio ");
    	string.append("where id_caso = ?1 ");
    	
    	Query query = getEntityManager().createNativeQuery(string.toString());
    	query.setParameter(1, idCaso);
    	try { 

    		return (String) query.getSingleResult();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }
    
    public HashMap<String,Object> audienciaTramitaSuplente(Long idCaso) {
    	HashMap<String,Object> Result = new HashMap<>();
    	StringBuilder string = new StringBuilder();  
    	string.append("select bloquea_suplente, audiencia_libera_suplente from PARAMETRO_SERVICIO_SORTEO pss ");
    	string.append("inner join caso c on c.id_servicio = pss.id_servicio ");
    	string.append("where id_caso = ?1  and bloquea_suplente = 1 and audiencia_libera_suplente is not null ");
    	
    	Query query = getEntityManager().createNativeQuery(string.toString());
    	query.setParameter(1, idCaso);
    	try { 
    		List<Object[]> listResult = query.getResultList();
    		if(listResult.size() == 1) {
    			Boolean bloquea = null;
    			String libera = "";
    			for(Object[] res : listResult) {
    				if(res[0] != null) 
    					bloquea = (Boolean)res[0]; // confirmar
    				if(res[1] != null)
    					libera = (String)res[1];    					
    			}
    			Result.put("bloqueaSuplentes", bloquea);
    			Result.put("liberarSuplentes", libera);
    		}
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}    	
    	return Result;
    }

    public boolean liberaArbitrosCierre(Long idCaso) {
    	StringBuilder string = new StringBuilder();     	
    	string.append("select top 1 libera_arbitros_cierre from PARAMETRO_SERVICIO_SORTEO pss ");
    	string.append("inner join caso c on c.id_servicio = pss.id_servicio ");
    	string.append("where id_caso = ?1 ");
    	
    	Query query = getEntityManager().createNativeQuery(string.toString());
    	query.setParameter(1, idCaso);
    	try { 
    		return (boolean) query.getSingleResult();			
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }

}

