package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Funcionalidad;
import com.ccb.simasc.transversal.entidades.FuncionalidadRol;
import com.ccb.simasc.transversal.entidades.FuncionalidadRolPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad FuncionalidadRol.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorFuncionalidadRol extends ManejadorCrud<FuncionalidadRol,FuncionalidadRolPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
	private transient EntityManager em;

	// protected region atributos end
    
    public ManejadorFuncionalidadRol() {
        super(FuncionalidadRol.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    /**
     * consulta las funcionalidades activas para el rol enviado
     * @param idRoles
     * @return
     */
    public List<Funcionalidad> obtenerFuncionalidadesActivasPorRoles(List<Long> idRoles) {
   	 List<Funcionalidad> funcionalidades = new ArrayList<Funcionalidad>();
   	 if (idRoles != null && !idRoles.isEmpty()) {   
   		 
   		StringBuilder stringQuery = new StringBuilder(); 
   		stringQuery.append("SELECT funr FROM FuncionalidadRol funr WHERE funr.rol.idRol in :idRoles "
   				+ "AND funr.estadoRegistro='" 
   				+ UtilDominios.ESTADO_REGISTRO_ACTIVO + "' AND funr.funcionalidad.estadoRegistro='" 
   				+ UtilDominios.ESTADO_REGISTRO_ACTIVO + "' ");
   		    		 
   		 List<FuncionalidadRol> funcionalidadesRol = em.createQuery(stringQuery.toString(), 
   				 FuncionalidadRol.class)
   	    			.setParameter("idRoles", idRoles)
   	    			.getResultList();
   		 
   		 if (funcionalidadesRol != null) {
   			for (FuncionalidadRol funRol : funcionalidadesRol) {
   	   			funcionalidades.add(funRol.getFuncionalidad());
   	   		 }
   		 }
   	 }
   	 return funcionalidades;
   }
    
    /**
     * Obtiene las funcionalidades asignadas a un rol especifico
     * 
     * @param idRol
     * @return
     */
	@SuppressWarnings("unchecked")
	public List<FuncionalidadRol> obtenerFuncionalidadesRol(Long idRol) {

		StringBuilder nativeQuery = new StringBuilder();

		nativeQuery.append(
				"WITH FUNCIONALIDADES (nombre, titulo, url, nombre_tipo_funcionalidad, aplicacion_tipo_funcionalidad, nombre_funcionalidad_padre, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro, nivel) ");
		nativeQuery.append("AS ( ");
		nativeQuery.append("SELECT f.*, ");
		nativeQuery.append("1 nivel ");
		nativeQuery.append("FROM FUNCIONALIDAD f (nolock) ");
		nativeQuery.append("WHERE nombre_funcionalidad_padre IS NULL ");
		nativeQuery.append("UNION ALL ");
		nativeQuery.append("SELECT fh.*, ");
		nativeQuery.append("fp.nivel + 1 ");
		nativeQuery.append("FROM FUNCIONALIDADES fp ");
		nativeQuery.append("INNER JOIN FUNCIONALIDAD fh (nolock) ");
		nativeQuery.append("ON fh.nombre_funcionalidad_padre = fp.nombre ");
		nativeQuery.append(") ");
		nativeQuery.append("SELECT fr.* ");
		nativeQuery.append("FROM FUNCIONALIDADES f (nolock) ");
		nativeQuery.append("INNER JOIN FUNCIONALIDAD_ROL fr (nolock) ");
		nativeQuery.append("ON f.nombre = fr.nombre_funcionalidad ");
		nativeQuery.append("AND fr.id_rol = ?1 ");
		nativeQuery.append("ORDER BY f.nivel ");

		Query query = em.createNativeQuery(nativeQuery.toString(), FuncionalidadRol.class);
		query.setParameter(1, idRol);

		return query.getResultList();
	}

    // protected region metodos adicionales end
        
}

