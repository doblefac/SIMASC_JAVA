package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

// Escriba en esta seccion sus modificaciones

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.ResultadoCasoDTO;
import com.ccb.simasc.transversal.dto.ResultadoMinisterioDTO;
import com.ccb.simasc.transversal.entidades.ResultadoAudiencia;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad ResultadoAudiencia.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorResultadoAudiencia extends ManejadorCrud<ResultadoAudiencia, Long> {

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end

	public ManejadorResultadoAudiencia() {
		super(ResultadoAudiencia.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	public List<ResultadoAudiencia> consultarResultadoAudiencias(Long idAudiencia) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT a ");
		jpqlQuery.append(" FROM ResultadoAudiencia a ");
		jpqlQuery.append(" where a.idAudiencia = ?1 ");
		jpqlQuery.append(" and a.estadoRegistro = ?2 ");

		Query query = getEntityManager().createQuery(jpqlQuery.toString());
		query.setParameter(1, idAudiencia);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();
	}

	/**
	 * Metodo que permite consultar los resultados de audiencia de un caso.
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 * @param tiposResultados:
	 *            Filtro de resultados de audiencia.
	 * @return List<ResultadoCasoDTO>
	 */
	public List<ResultadoCasoDTO> consultarResultadosAudienciasCaso(Long idCaso, List<String> tiposResultados) {

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT r.id_resultado_audiencia AS idResultadoAudiencia, ");
		nativeQuery.append("r.tipo_resultado_audiencia AS resultadoAudiencia, ");
		nativeQuery.append("r.estado AS estadoResultado, ");
		nativeQuery.append("r.id_documento AS idDocumento, ");
		nativeQuery.append("dd.observaciones_respuesta AS respuestaConciliador, ");
		nativeQuery.append("a.consecutivo AS consecutivo ");

		nativeQuery.append("FROM RESULTADO_AUDIENCIA r ");

		nativeQuery.append("INNER JOIN AUDIENCIA a ");
		nativeQuery.append("ON a.id_audiencia = r.id_audiencia ");
		nativeQuery.append("AND a.estado_registro = ?1 ");

		nativeQuery.append("LEFT JOIN DEVOLUCION_DOCUMENTO_RESULTADO dd ");
		nativeQuery.append("ON dd.id_documento = r.id_documento ");
		nativeQuery.append("AND dd.estado_registro = ?1 ");

		nativeQuery.append("WHERE a.id_caso = ?2 ");
		nativeQuery.append("AND r.estado_registro = ?1 ");
		nativeQuery.append("AND r.tipo_resultado_audiencia ")
				.append(UtilConsultasSQL.clausulaInSQLStrings(tiposResultados));
		;

		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), ResultadoCasoDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idCaso);

		return query.getResultList();
	}

	/**
	 * Metodo que permite consultar los resultados por parte del ministerio.
	 * 
	 * @param idServicio:
	 *            Identificador del servicio.
	 * @param idCaso:
	 *            Identificador del caso.
	 * @param tiposResultados:
	 *            Lista de resultados de audiencia (Acuerdo, Imposibilidad o
	 *            Inasistencia).
	 * @return List<ResultadoMinisterioDTO>.
	 */
	public List<ResultadoMinisterioDTO> consultarResultadosPorMinisterio(Long idServicio, Long idCaso,
			List<String> tiposResultados) {

		StringBuilder nativeQuery = new StringBuilder();

		nativeQuery.append("SELECT a.hora_inicio AS horaInicio, ");
		if (idServicio.equals(UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA)) {

			nativeQuery.append("a.resultado AS tipoResultado, ");
			nativeQuery.append("d.id_documento AS idDocumento ");

			nativeQuery.append("FROM AUDIENCIA a ");

			nativeQuery.append("INNER JOIN DOCUMENTO d ");
			nativeQuery.append("ON d.id_audiencia = a.id_audiencia ");
			nativeQuery.append("AND d.estado_registro = ?1 ");

			nativeQuery.append("WHERE a.id_caso = ?4 ");
			nativeQuery.append("AND a.estado = ?3 ");
			nativeQuery.append("AND a.estado_registro = ?1 ");

		} else {
			nativeQuery.append("r.tipo_resultado_audiencia AS tipoResultado, ");
			nativeQuery.append("r.id_documento AS idDocumento ");

			nativeQuery.append("FROM RESULTADO_AUDIENCIA r ");

			nativeQuery.append("INNER JOIN AUDIENCIA a ");
			nativeQuery.append("ON a.id_audiencia = r.id_audiencia ");
			nativeQuery.append("AND a.estado_registro = ?1 ");

			nativeQuery.append("WHERE a.id_caso = ?4 ");
			nativeQuery.append("AND a.estado = ?3 ");
			nativeQuery.append("AND r.estado = ?2 ");
			nativeQuery.append("AND r.estado_registro = ?1 ");
			if (tiposResultados != null)
				nativeQuery.append("AND r.tipo_resultado_audiencia ")
						.append(UtilConsultasSQL.clausulaInSQLStrings(tiposResultados));
		}

		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), ResultadoMinisterioDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_RESULTADO_VALIDADO);
		query.setParameter(3, UtilDominios.ESTADO_AUDIENCIA_REALIZADA);
		query.setParameter(4, idCaso);

		return query.getResultList();
	}
	/**
	 * Método encargado de eliminar un ObligacionParte dado su idResultadoAudiencia
	 * 
	 */
	public void eliminarObligacionParte(Long idResultadoAudiencia){
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" delete from ObligacionParte op where op.obligacionPartePK.idResultadoAudiencia =:idResultadoAudiencia ");
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter("idResultadoAudiencia", idResultadoAudiencia);
		query.executeUpdate();
	}
	
	/**
	 * Método encargado de eliminar un registro de la tabla Obligacion dada su idResultadoAudiencia
	 * 
	 */
	public void eliminarObligacion(Long idResultadoAudiencia){
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" delete from Obligacion op where op.obligacionPK.idResultadoAudiencia =:idResultadoAudiencia ");
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter("idResultadoAudiencia", idResultadoAudiencia);
		query.executeUpdate();
	}
	
	/**
	 * Método encargado de eliminar un registro de la tabla Cuota dada su idResultadoAudiencia
	 * 
	 */
	public void eliminarCuota(Long idResultadoAudiencia){
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" delete from Cuota c where c.cuotaPK.idResultadoAudiencia =:idResultadoAudiencia ");
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter("idResultadoAudiencia", idResultadoAudiencia);
		query.executeUpdate();
	}

	/**
	 * Método encargado de eliminar un resultadoAudiencia dado su id
	 * 
	 */
	public void eliminarResultadoAudiencia(Long idResultadoAudiencia){
		this.eliminarCuota(idResultadoAudiencia);
		this.eliminarObligacionParte(idResultadoAudiencia);
		this.eliminarObligacion(idResultadoAudiencia);
		this.eliminarPorId(idResultadoAudiencia);
	}
	// protected region metodos adicionales end

}
