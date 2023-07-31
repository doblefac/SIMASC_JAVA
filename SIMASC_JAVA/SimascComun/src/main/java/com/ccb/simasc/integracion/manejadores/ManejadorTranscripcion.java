package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

// Escriba en esta sección sus modificaciones

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.AudienciaPendienteTranscripcionDTO;
import com.ccb.simasc.transversal.dto.TranscripcionPendienteDTO;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.entidades.Transcripcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad Transcripcion.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorTranscripcion extends ManejadorCrud<Transcripcion, Long> {

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	
	@PersistenceContext
	private transient EntityManager em;

	// protected region atributos end
	
    public ManejadorTranscripcion() {
        super(Transcripcion.class);
    }

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	/**
	 * 
	 * 
	 * @param idCaso
	 * @param idAudiencia
	 */
	@SuppressWarnings("unchecked")
	public List<TranscripcionPendienteDTO> consultarTranscripcionesPendientes(Long idCaso, Long idAudiencia) {
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append("SELECT t.id_transcripcion, ");
		nativeQuery.append("	   c.id_caso, ");
		nativeQuery.append("       c.nombre, ");
		nativeQuery.append("       a.hora_inicio as fecha_audiencia, ");
		nativeQuery.append("       t.fecha_prevista_de_entrega, ");
		nativeQuery.append("       CONCAT ( ");
		nativeQuery.append("          p.primer_nombre_o_razon_social, ");
		nativeQuery.append("          ' ', ");
		nativeQuery.append("          p.segundo_nombre, ");
		nativeQuery.append("          ' ', ");
		nativeQuery.append("          p.primer_apellido, ");
		nativeQuery.append("          ' ', ");
		nativeQuery.append("          p.segundo_apellido ");
		nativeQuery.append("       ) as nombre_auxiliar_administrativo_asignado, ");
		nativeQuery.append("	   t.id_doc_audio_fuente as numero_audio, "); 
		nativeQuery.append("       d.descripcion, "); 
		nativeQuery.append("       (t.tiempo_final - t.tiempo_inicial) minutos_a_transcribir, "); 
		nativeQuery.append("       t.tiempo_transcrito minutos_transcritos, "); 
		nativeQuery.append("       ((t.tiempo_final - t.tiempo_inicial) - t.tiempo_transcrito) as minutos_pendientes_transcripcion ");
		nativeQuery.append("  FROM TRANSCRIPCION t, "); 
		nativeQuery.append("       DOCUMENTO d, ");
		nativeQuery.append("       PERSONA p, ");
		nativeQuery.append("       ROL_PERSONA rp, ");
		nativeQuery.append("       ROL r, ");
		nativeQuery.append("       AUDIENCIA a, ");
		nativeQuery.append("       CASO c ");
		nativeQuery.append(" WHERE t.id_doc_audio_fuente = d.id_documento ");
		nativeQuery.append("   AND d.estado_digitalizacion = ?1 ");
		
		nativeQuery.append("   AND t.id_persona = p.id_persona ");
		nativeQuery.append("   AND p.id_persona = rp.id_persona ");
		nativeQuery.append("   AND rp.id_rol = r.id_rol ");
		nativeQuery.append("   AND r.nombre = ?2 ");
		nativeQuery.append("   AND d.id_audiencia = a.id_audiencia ");
		nativeQuery.append("   AND a.id_caso = c.id_caso ");
		nativeQuery.append("   AND c.id_caso = ?3 ");
		nativeQuery.append("   AND a.id_audiencia = ?4 ");
		
		nativeQuery.append("   AND d.tipo_documento = ?5 ");
		nativeQuery.append("   AND t.estado = ?6 ");
		
		nativeQuery.append("   AND t.estado_registro = ?7 ");
		nativeQuery.append("   AND d.estado_registro = ?8 ");
		nativeQuery.append("   AND p.estado_registro = ?9 ");
		nativeQuery.append("   AND rp.estado_registro = ?10 ");
		nativeQuery.append("   AND r.estado_registro = ?11 ");
		nativeQuery.append("   AND a.estado_registro = ?12 ");
		nativeQuery.append("   AND c.estado_registro = ?13 ");
		
		nativeQuery.append("ORDER BY t.fecha_prevista_de_entrega ");
		
		Query query = em.createNativeQuery(nativeQuery.toString());
		// query.setParameter(1, UtilDominios.ESTADO_TRANSCRIPCION_POR_TRANSCRIBIR);
		query.setParameter(1, UtilDominios.ESTADO_DIGITALIZACION_DIGITALIZADO);
		query.setParameter(2, UtilDominios.ROL_PERSONA_AUXILIAR_ADM);
		query.setParameter(3, idCaso);
		query.setParameter(4, idAudiencia);
		
		query.setParameter(5, UtilDominios.TIPO_DOCUMENTO_DIG_AUDIO);
		query.setParameter(6, UtilDominios.ESTADO_TRANSCRIPCION_PENDIENTE);
		query.setParameter(7, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(8, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(9, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(10, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(11, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(12, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(13, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		List<Object[]> resultados = query.getResultList();
		
		return convertirResultadoATranscripcionPendienteDTO(resultados);
	}
	
	private List<TranscripcionPendienteDTO> convertirResultadoATranscripcionPendienteDTO(List<Object[]> registros) {
		List<TranscripcionPendienteDTO> transcripciones = new ArrayList<>();
		for (Object[] registro : registros) {
			transcripciones.add(convertirRegistroATranscripcionPendienteDTO(registro));
		}

		return transcripciones;
	}
	
	private TranscripcionPendienteDTO convertirRegistroATranscripcionPendienteDTO(Object[] registro) {
		TranscripcionPendienteDTO transcripcion = new TranscripcionPendienteDTO();
		
		transcripcion.setIdTranscripcion(registro[0] != null ? ((BigDecimal) registro[0]).longValue() : null);
		transcripcion.setCodigoCaso(registro[1] != null ? ((BigDecimal) registro[1]).longValue() : null);
		transcripcion.setNombreCaso(registro[2] != null ? (String) registro[2] : null);
		transcripcion.setFechaAudiencia(registro[3] != null ? (Date) registro[3] : null);
		transcripcion.setFechaPrevistaEntrega(registro[4] != null ? (Date) registro[4] : null);
		transcripcion.setAuxiliarAdministrativoAsignado(registro[5] != null ? (String) registro[5] : null);
		transcripcion.setNumeroAudio(registro[6] != null ? ((BigDecimal) registro[6]).longValue() : null);
		transcripcion.setDescripcionDocumento(registro[7] != null ? (String) registro[7] : null);
		
		Calendar calendar = Calendar.getInstance();
		
		if (registro[8] != null) {
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, ((BigDecimal) registro[8]).intValue());
			transcripcion.setMinutosPorTranscribir(calendar.getTime());
		} else {
			transcripcion.setMinutosPorTranscribir(null);
		}
		
		if (registro[9] != null) {
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, ((BigDecimal) registro[9]).intValue());
			transcripcion.setMinutosTranscritos(calendar.getTime());
		} else {
			transcripcion.setMinutosTranscritos(null);
		}
		
		if (registro[10] != null) {
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, ((BigDecimal) registro[10]).intValue());
			transcripcion.setMinutosPendientesTranscripcion(calendar.getTime());
		} else {
			transcripcion.setMinutosPorTranscribir(null);
		}
		
		return transcripcion;
	}
	
	@SuppressWarnings("unchecked")
	public List<AudienciaPendienteTranscripcionDTO> casosAudienciasPendientesTranscripciones(String idUsuario) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT c.id_caso AS idCaso, c.nombre AS nombreCaso ,a.hora_inicio AS fechaAudiencia, t.fecha_prevista_de_entrega AS fechaPrevistaEntrega, ");
		nativeQuery.append("d.id_documento AS numeroAudio, d.descripcion AS descripcion, (t.tiempo_final - t.tiempo_inicial) as minutosTranscribir, ");
		nativeQuery.append("t.tiempo_transcrito AS minutosTranscritos, ((datediff(mi,a.hora_inicio,a.hora_fin)) -t.tiempo_transcrito) AS minutosPendientesTranscripcion, ");
		nativeQuery.append("t.id_transcripcion AS idTranscricpion, a.id_audiencia AS idAudiencia, ");
		nativeQuery.append("d.id_documento AS idDocumento, t.fecha_entrega_transcripcion AS fechaEntregaTranscripcion, t.fecha_inicio_transcripcion AS fechaInicioTranscripcion ");
		nativeQuery.append("FROM caso c ");
		nativeQuery.append("LEFT JOIN audiencia a ON c.id_caso=a.id_caso AND a.estado_registro = '"
				+ UtilDominios.ESTADO_REGISTRO_ACTIVO + "' ");
		nativeQuery.append("LEFT JOIN documento d ON d.id_audiencia=a.id_audiencia AND d.estado_registro = '"
				+ UtilDominios.ESTADO_REGISTRO_ACTIVO + "' ");
		nativeQuery
				.append("LEFT JOIN transcripcion t ON t.id_doc_audio_fuente= d.id_documento AND t.estado_registro = '"
						+ UtilDominios.ESTADO_REGISTRO_ACTIVO + "' ");
		nativeQuery.append("WHERE d.tipo_documento='" + UtilDominios.TIPO_DOCUMENTO_DIG_AUDIO + "' ");
		// nativeQuery.append("AND d.estado_digitalizacion='" + UtilDominios.ESTADO_TRANSCRIPCION_POR_TRANSCRIBIR + "' ");
		
		nativeQuery.append("AND d.estado_digitalizacion='" + UtilDominios.ESTADO_DIGITALIZACION_DIGITALIZADO + "' ");
		nativeQuery.append("AND t.estado='" + UtilDominios.ESTADO_TRANSCRIPCION_PENDIENTE + "' ");
		
		nativeQuery.append("AND t.id_persona = " + idUsuario + " ");
		// nativeQuery.append("AND t.estado_registro='" + UtilDominios.ESTADO_REGISTRO_ACTIVO + "' ");
		nativeQuery.append("ORDER BY t.fecha_prevista_de_entrega, a.hora_inicio ");

		Query q = em.createNativeQuery(nativeQuery.toString(), AudienciaPendienteTranscripcionDTO.class);
		
		return q.getResultList();
	}
	
	/**
	 *  Devuleve las transcripciones de un documento
	 * @param idDocumento
	 */
	@SuppressWarnings("unchecked")
	public List<Transcripcion> consultarTranscripcionesPorDocumento(Long idDocumento) {
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append("SELECT t "
				+ "		   FROM Transcripcion t"
				+ "		  WHERE t.idDocAudioFuente = :idDocumento "
				+ "		    AND t.estadoRegistro = :estadoRegistro ");
		
		Query query = em.createQuery(queryStr.toString());
		query.setParameter("idDocumento", idDocumento);
		query.setParameter("estadoRegistro", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		List<Transcripcion> resultados = (List<Transcripcion>)query.getResultList();
		
		return resultados;
	}
	
	
	/**
	 * Consulta los auxiliares que no realizaron registro de minutos diarios el día anterior
	 * @param 
	 */
	@SuppressWarnings("unchecked")
	public List<InfoBasicaAlertasDTO> alertaTranscripcionesRegistrarMinutosDiarios() {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select rpc.id_persona as idPersona ");
		nativeQuery.append(" from documento D ");
		nativeQuery.append(" INNER JOIN TRANSCRIPCION T ON T.id_doc_audio_fuente=D.id_documento ");
		nativeQuery.append(" INNER JOIN AUDIENCIA AUD ON AUD.id_audiencia=D.id_audiencia ");
		nativeQuery.append(" INNER JOIN CASO C ON C.id_caso=AUD.id_caso ");
		nativeQuery.append(" inner join ROL_PERSONA_CASO rpc on rpc.id_caso=c.id_caso ");
		nativeQuery.append(" inner join SERVICIO s on s.id_servicio = c.id_servicio ");
		nativeQuery.append(" INNER JOIN PERSONA P ON P.id_persona=T.id_persona ");
		nativeQuery.append(" where D.tipo_documento=?1 and D.estado_digitalizacion=?2 AND D.estado_registro=?3  ");
		nativeQuery.append(" and  Cast(t.fecha_ultima_modificacion as DATE) != Cast(DATEADD(dd,-1,GETDATE()) as DATE) ");
		nativeQuery.append(" and s.tipo = ?4 ");
		nativeQuery.append(" AND d.id_audiencia=aud.id_audiencia ");
		nativeQuery.append(" AND AUD.estado_registro=?3 ");
		nativeQuery.append(" AND T.estado_registro=?3 ");
		nativeQuery.append(" AND T.estado=?5 ");
		nativeQuery.append(" AND rpc.id_rol = (select r.id_rol from rol r where nombre=?6) ");
		nativeQuery.append(" AND C.estado_registro=?3 ");
		nativeQuery.append(" AND P.estado_registro=?3 ");
		nativeQuery.append(" AND RPC.estado_registro=?3 ");
		nativeQuery.append(" group by  rpc.id_persona ");
		
		Query q = em.createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		
		q.setParameter(1, UtilDominios.TIPO_DOCUMENTO_DIG_AUDIO);
		q.setParameter(2, UtilDominios.ESTADO_DIGITALIZACION_DIGITALIZADO);
		q.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(4, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		q.setParameter(5, UtilDominios.ESTADO_TRANSCRIPCION_PENDIENTE);
		q.setParameter(6, UtilDominios.ROL_PERSONA_AUXILIAR_ADM);
		
		return q.getResultList();
	}
	
	
	/**
	 * Consulta los auxiliares que tienen mas de 500 minutos de asignación de transcripciones
	 * @param 
	 */
	@SuppressWarnings("unchecked")
	public List<InfoBasicaAlertasDTO> alertaTranscripcionesSobreAsignacion(Long idPersona) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select P.ID_PERSONA AS idPersona, ");
		nativeQuery.append(" concat(rtrim(p.primer_nombre_o_razon_social), rtrim(' '+p.segundo_nombre), rtrim(' '+p.primer_apellido), rtrim(' '+p.segundo_apellido)) AS nombrePersona ");
		nativeQuery.append(" from documento D ");
		nativeQuery.append(" INNER JOIN TRANSCRIPCION T ON T.id_doc_audio_fuente=D.id_documento ");
		nativeQuery.append(" INNER JOIN AUDIENCIA AUD ON AUD.id_audiencia=D.id_audiencia ");
		nativeQuery.append(" INNER JOIN CASO C ON C.id_caso=AUD.id_caso ");
		nativeQuery.append(" inner join ROL_PERSONA_CASO rpc on rpc.id_caso=c.id_caso  ");
		nativeQuery.append(" inner join SERVICIO s on s.id_servicio = c.id_servicio ");
		nativeQuery.append(" INNER JOIN PERSONA P ON P.id_persona=T.id_persona ");
		nativeQuery.append(" where D.tipo_documento=?1 and D.estado_digitalizacion=?2 AND D.estado_registro=?3  ");
		nativeQuery.append(" AND T.id_persona=?4 ");
		nativeQuery.append(" and s.tipo = ?5 ");
		nativeQuery.append(" AND d.id_audiencia=aud.id_audiencia ");
		nativeQuery.append(" AND AUD.estado_registro=?3 ");
		nativeQuery.append(" AND T.estado_registro=?3 ");
		nativeQuery.append(" AND T.estado=?6 ");
		nativeQuery.append(" AND rpc.id_rol = (select r.id_rol from rol r where nombre=?7) ");
		nativeQuery.append(" AND C.estado_registro=?3 ");
		nativeQuery.append(" AND P.estado_registro=?3 ");
		nativeQuery.append(" AND RPC.estado_registro=?3 ");
		nativeQuery.append(" GROUP BY p.id_persona, concat(rtrim(p.primer_nombre_o_razon_social), rtrim(' '+p.segundo_nombre), rtrim(' '+p.primer_apellido), rtrim(' '+p.segundo_apellido))  ");
		nativeQuery.append(" HAVING (sum(ISNULL(d.duracion,0)) - sum (ISNULL(t.tiempo_transcrito,0)))> (select valor_numerico*60 from PARAMETROS_GENERALES  ");
		nativeQuery.append(" where tipo=?8 and codigo=?9) ");
				
		Query q = em.createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		
		q.setParameter(1, UtilDominios.TIPO_DOCUMENTO_DIG_AUDIO);
		q.setParameter(2, UtilDominios.ESTADO_DIGITALIZACION_DIGITALIZADO);
		q.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(4, idPersona);
		q.setParameter(5, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		q.setParameter(6, UtilDominios.ESTADO_TRANSCRIPCION_PENDIENTE);
		q.setParameter(7, UtilDominios.ROL_PERSONA_AUXILIAR_ADM);
		q.setParameter(8, UtilConstantes.TIPO_MINUTOS_MAXIMOS_TRANSCRIPCION);
		q.setParameter(9, UtilDominios.CODIGO_MINUTOS_MAXIMO_TRANSCRIPCION);
				
		return q.getResultList();
	}
	

    // protected region metodos adicionales end

}
