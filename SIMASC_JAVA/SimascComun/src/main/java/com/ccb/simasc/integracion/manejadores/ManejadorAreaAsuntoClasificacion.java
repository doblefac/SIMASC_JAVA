package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

// Escriba en esta sección sus modificaciones

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.AreaAsuntoClasificacion;
import com.ccb.simasc.transversal.entidades.Caso;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad AreaAsuntoClasificacion.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorAreaAsuntoClasificacion extends ManejadorCrud<AreaAsuntoClasificacion, Long> {

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end

	public ManejadorAreaAsuntoClasificacion() {
		super(AreaAsuntoClasificacion.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	public Long obtenerIdPorLlaves(Long asunto, Long clasificacion) {
		Long resultado;
		try {

			StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append(" SELECT a.idAreaAsuntoClasificacion FROM AreaAsuntoClasificacion a ");
			jpqlQuery.append(" WHERE a.asunto.idAsunto =:  ");
			jpqlQuery.append(AreaAsuntoClasificacion.ENTIDAD_AREA_ASUNTO_CLASIFICACION_ID_ASUNTO);

			if (clasificacion != 0) {
				jpqlQuery.append(" 	 AND a.idClasificacion =:");
				jpqlQuery.append(AreaAsuntoClasificacion.ENTIDAD_AREA_ASUNTO_CLASIFICACION_ID_CLASIFICACION);
			} else {
				jpqlQuery.append(" 	 AND a.idClasificacion IS NULL ");
			}

			Query query = mp.createQuery(jpqlQuery.toString());
			// query.setParameter(Area.ENTIDAD_AREA_PK, area);
			query.setParameter(AreaAsuntoClasificacion.ENTIDAD_AREA_ASUNTO_CLASIFICACION_ID_ASUNTO, asunto);
			if (clasificacion != 0)
				query.setParameter(AreaAsuntoClasificacion.ENTIDAD_AREA_ASUNTO_CLASIFICACION_ID_CLASIFICACION,
						clasificacion);

			resultado = (Long) query.getSingleResult();
		} catch (NoResultException e) {
			resultado = null;
		}
		return resultado;
	}

	/**
	 * Metodo que permite consultar un areaAsuntoClasificacion de un caso.
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 * @return AreaAsuntoCLasificacion.
	 */
	@SuppressWarnings("unchecked")
	public AreaAsuntoClasificacion obtenerAreAsuntoClasificacionPorCaso(Long idCaso) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT a FROM Caso c JOIN c.areaAsuntoClasificacion a ");
		jpqlQuery.append(" WHERE c.idCaso =:  ");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);

		List<AreaAsuntoClasificacion> resultado = query.getResultList();
		return !resultado.isEmpty() ? resultado.get(0) : new AreaAsuntoClasificacion();
	}

	// protected region metodos adicionales end

}
