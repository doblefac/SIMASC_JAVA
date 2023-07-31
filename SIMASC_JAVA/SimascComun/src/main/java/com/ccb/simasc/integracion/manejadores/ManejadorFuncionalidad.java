package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Funcionalidad;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Funcionalidad.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorFuncionalidad extends ManejadorCrud<Funcionalidad,String>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorFuncionalidad() {
        super(Funcionalidad.class);
    } 
    
  
    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    /**
     * ADM-C-012, ADM-C-014
     * Método para obtener las funcionalidades padre dependiendo del tipo de funcionalidad
     * @param tipoFuncionalidad
     * @return
     */
	public List<Funcionalidad> consultarFuncionalidadesPadre(String tipoFuncionalidad) {
		List<Funcionalidad> funcionalidadesList = new ArrayList<>();
		funcionalidadesList.addAll(obtenerFuncionalidadesPadre(tipoFuncionalidad));
		if (tipoFuncionalidad != null)
			funcionalidadesList.addAll(obtenerOtrasFuncionalidades(tipoFuncionalidad));
		
		return funcionalidadesList;
	}
	
	/**
	 * Método para obtener todas aquellas funcionalidades que no tienen un padre asociado
	 * @param tipoFuncionalidad parametro opcional para obtener las funcionalidades por tipo de funcionalidad
	 * @return
	 */
	private List<Funcionalidad> obtenerFuncionalidadesPadre(String tipoFuncionalidad) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT f FROM Funcionalidad f");
		jpqlQuery.append(" WHERE f.estadoRegistro =:");
		jpqlQuery.append(Funcionalidad.ENTIDAD_FUNCIONALIDAD_ESTADO_REGISTRO);
		jpqlQuery.append(" AND f.aplicacionTipoFuncionalidad = :");
		jpqlQuery.append(Funcionalidad.ENTIDAD_FUNCIONALIDAD_APLICACION_TIPO_FUNCIONALIDAD);
		jpqlQuery.append(" AND f.nombreFuncionalidadPadre IS NULL ");
		if (tipoFuncionalidad != null) {
			jpqlQuery.append(" AND f.nombreTipoFuncionalidad = :");
			jpqlQuery.append(Funcionalidad.ENTIDAD_FUNCIONALIDAD_NOMBRE_TIPO_FUNCIONALIDAD);			
		}
		jpqlQuery.append(" ORDER BY f.titulo ASC");
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Funcionalidad.ENTIDAD_FUNCIONALIDAD_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if (tipoFuncionalidad != null)
			query.setParameter(Funcionalidad.ENTIDAD_FUNCIONALIDAD_NOMBRE_TIPO_FUNCIONALIDAD, tipoFuncionalidad);
		query.setParameter(Funcionalidad.ENTIDAD_FUNCIONALIDAD_APLICACION_TIPO_FUNCIONALIDAD,
				UtilDominios.APLICACION_TIPO_FUNCIONALIDAD_ANGULAR);
		
		return query.getResultList();
	}
	
	/**
	 * Método para obtener las funcionalidades cuyo padre es de otro tipo de funcionalidad
	 * @param tipoFuncionalidad
	 * @return
	 */
	private List<Funcionalidad> obtenerOtrasFuncionalidades(String tipoFuncionalidad) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT f FROM Funcionalidad f");
		jpqlQuery.append(" WHERE f.nombreTipoFuncionalidad = :");
		jpqlQuery.append(Funcionalidad.ENTIDAD_FUNCIONALIDAD_NOMBRE_TIPO_FUNCIONALIDAD);
		jpqlQuery.append(" AND f.nombreFuncionalidadPadre in (SELECT fu.nombre from Funcionalidad fu");
		jpqlQuery.append(" WHERE fu.nombreTipoFuncionalidad NOT LIKE :");
		jpqlQuery.append(Funcionalidad.ENTIDAD_FUNCIONALIDAD_NOMBRE_TIPO_FUNCIONALIDAD);
		jpqlQuery.append(" AND fu.aplicacionTipoFuncionalidad = :");
		jpqlQuery.append(Funcionalidad.ENTIDAD_FUNCIONALIDAD_APLICACION_TIPO_FUNCIONALIDAD);
		jpqlQuery.append(" AND fu.estadoRegistro = :");
		jpqlQuery.append(Funcionalidad.ENTIDAD_FUNCIONALIDAD_ESTADO_REGISTRO);
		jpqlQuery.append(" ) AND f.aplicacionTipoFuncionalidad = :");
		jpqlQuery.append(Funcionalidad.ENTIDAD_FUNCIONALIDAD_APLICACION_TIPO_FUNCIONALIDAD);
		jpqlQuery.append(" AND f.estadoRegistro = :");
		jpqlQuery.append(Funcionalidad.ENTIDAD_FUNCIONALIDAD_ESTADO_REGISTRO);
		jpqlQuery.append(" ORDER BY f.titulo ASC");
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Funcionalidad.ENTIDAD_FUNCIONALIDAD_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Funcionalidad.ENTIDAD_FUNCIONALIDAD_NOMBRE_TIPO_FUNCIONALIDAD, tipoFuncionalidad);
		query.setParameter(Funcionalidad.ENTIDAD_FUNCIONALIDAD_APLICACION_TIPO_FUNCIONALIDAD,
				UtilDominios.APLICACION_TIPO_FUNCIONALIDAD_ANGULAR);
		
		return query.getResultList();
	}
    // protected region metodos adicionales end
        
}

