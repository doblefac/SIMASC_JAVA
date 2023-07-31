package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.ParametroDeServicio;
import com.ccb.simasc.transversal.entidades.ParametroDeServicioPK;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad ParametroDeServicio.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorParametroDeServicio extends ManejadorCrud<ParametroDeServicio,ParametroDeServicioPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorParametroDeServicio() {
        super(ParametroDeServicio.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

	/**
	 * Consulta los Parametros de Servicio. Opcional aplicar filtros de:
	 * tipoParametro
	 * 
	 * @param tipoParametro
	 * @return List<ParametroDeServicio>
	 */
    @SuppressWarnings("unchecked")
    public List<ParametroDeServicio> consultarParametrosDeServicio(String tipoParametro, Long idServicio){
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT ps FROM ParametroDeServicio ps ");
		jpqlQuery.append(" WHERE ps.estadoRegistro =: ")
				.append(ParametroDeServicio.ENTIDAD_PARAMETRO_DE_SERVICIO_ESTADO_REGISTRO);
		
		if(tipoParametro != null){
			jpqlQuery.append(" AND ps.parametroDeServicioPK.tipoParametro =: ");
			jpqlQuery.append(ParametroDeServicio.ENTIDAD_PARAMETRO_DE_SERVICIO_TIPO_PARAMETRO);
		}
		
		if(idServicio != null) {
			jpqlQuery.append(" AND ps.parametroDeServicioPK.idServicio =: ");
			jpqlQuery.append(ParametroDeServicio.ENTIDAD_PARAMETRO_DE_SERVICIO_PK_ID_SERVICIO.replace(".", "_"));
		}
		
		Query query = getEntityManager().createQuery(jpqlQuery.toString());
		query.setParameter(ParametroDeServicio.ENTIDAD_PARAMETRO_DE_SERVICIO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		if(tipoParametro != null){
			query.setParameter(ParametroDeServicio.ENTIDAD_PARAMETRO_DE_SERVICIO_TIPO_PARAMETRO, tipoParametro);
		}
		
		if(idServicio != null)
			query.setParameter(ParametroDeServicio.ENTIDAD_PARAMETRO_DE_SERVICIO_PK_ID_SERVICIO.replace(".", "_"), idServicio);
    	
    	return query.getResultList();
    }

    /**
     * Consulta de parametros de servicio
     * @param nombres
     * @param idServicio
     * @param tipoParametro
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<ParametroDeServicio> consultarParametrosDeServicio(List<String> nombres, Long idServicio,
			String tipoParametro) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT p FROM ParametroDeServicio p");
		jpqlQuery.append(" WHERE p.parametroDeServicioPK.nombre in :").append(
				UtilConsultasSQL.obtenerNombreParametro(ParametroDeServicio.ENTIDAD_PARAMETRO_DE_SERVICIO_PK_NOMBRE));
		if (idServicio != null)
			jpqlQuery.append(" AND p.servicio.idServicio = :").append(Servicio.ENTIDAD_SERVICIO_PK);
		jpqlQuery.append(" AND p.parametroDeServicioPK.tipoParametro = :").append(UtilConsultasSQL
				.obtenerNombreParametro(ParametroDeServicio.ENTIDAD_PARAMETRO_DE_SERVICIO_PK_TIPO_PARAMETRO));
		jpqlQuery.append(" AND p.estadoRegistro =:")
				.append(ParametroDeServicio.ENTIDAD_PARAMETRO_DE_SERVICIO_ESTADO_REGISTRO);			
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(ParametroDeServicio.ENTIDAD_PARAMETRO_DE_SERVICIO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(
				UtilConsultasSQL.obtenerNombreParametro(ParametroDeServicio.ENTIDAD_PARAMETRO_DE_SERVICIO_PK_NOMBRE),
				nombres);
		query.setParameter(Servicio.ENTIDAD_SERVICIO_PK, idServicio);
		query.setParameter(UtilConsultasSQL.obtenerNombreParametro(
				ParametroDeServicio.ENTIDAD_PARAMETRO_DE_SERVICIO_PK_TIPO_PARAMETRO), tipoParametro);
    	
    	return query.getResultList();
    }

    // protected region metodos adicionales end
	
	public ParametroDeServicio consultarParametroDeServicioPorNombre(String nombre) {
	 	StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append("SELECT ps FROM ParametroDeServicio ps ");
			jpqlQuery.append(" WHERE ps.estadoRegistro =: ")
					.append(ParametroDeServicio.ENTIDAD_PARAMETRO_DE_SERVICIO_ESTADO_REGISTRO);
			
			if(nombre != null){
				jpqlQuery.append(" AND ps.parametroDeServicioPK.nombre =: ");
				jpqlQuery.append(ParametroDeServicio.ENTIDAD_PARAMETRO_DE_SERVICIO_PK_NOMBRE.replace(".", "_"));
			}
			
			Query query = getEntityManager().createQuery(jpqlQuery.toString());
			query.setParameter(ParametroDeServicio.ENTIDAD_PARAMETRO_DE_SERVICIO_ESTADO_REGISTRO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
			
			if(nombre != null){
				query.setParameter(ParametroDeServicio.ENTIDAD_PARAMETRO_DE_SERVICIO_PK_NOMBRE.replace(".", "_"), nombre);
			}
			

	    	return (ParametroDeServicio) query.getResultList().get(0);
		
	}
        
}

