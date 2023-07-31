package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Centro;
import com.ccb.simasc.transversal.entidades.Materia;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Centro.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorCentro extends ManejadorCrud<Centro,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorCentro() {
        super(Centro.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
	/**
	 * Consulta los Centros activos. Opcional enviar el idServicio y el
	 * idMateria para obtener los Cnetros especializados en dichos parametros.
	 * 
	 * @param idServicio
	 * @param idMateria
	 * @return List<Centro>
	 */
    @SuppressWarnings("unchecked")
	public List<Centro> obtenerCentros(Long idServicio, Long idMateria){
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT c FROM Centro c ");

		if (idServicio != null && idMateria != null) {
			jpqlQuery.append(" INNER JOIN c.servicioMateriaCentroList sm ");
			jpqlQuery.append(" WHERE sm.servicioMateria.materia.idMateria =: ");
			jpqlQuery.append(Materia.ENTIDAD_MATERIA_PK);
			jpqlQuery.append(" AND sm.servicioMateria.servicio.idServicio =: ");
			jpqlQuery.append(Servicio.ENTIDAD_SERVICIO_PK);
			jpqlQuery.append(" AND c.estadoRegistro =: ");
			jpqlQuery.append(Centro.ENTIDAD_CENTRO_ESTADO_REGISTRO);
		} else {
			jpqlQuery.append(" WHERE c.estadoRegistro =: ");
			jpqlQuery.append(Centro.ENTIDAD_CENTRO_ESTADO_REGISTRO);
		}
		jpqlQuery.append(" ORDER BY c.nombre");

		Query query = mp.createQuery(jpqlQuery.toString());

		if (idServicio != null && idMateria != null) {
			query.setParameter(Materia.ENTIDAD_MATERIA_PK, idMateria);
			query.setParameter(Servicio.ENTIDAD_SERVICIO_PK, idServicio);
		}

		query.setParameter(Centro.ENTIDAD_CENTRO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();    	
    }
    // protected region metodos adicionales end
        
}

