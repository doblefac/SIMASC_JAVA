package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.AudienciaDTO;
import com.ccb.simasc.transversal.dto.AudienciaSorteoDTO;
import com.ccb.simasc.transversal.dto.AudienciaTranscripcionesPendientesDTO;
import com.ccb.simasc.transversal.dto.CitacionDTO;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.dto.formularios.AudienciasProgramadasDTO;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Audiencia.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorAudiencia extends ManejadorCrud<Audiencia,Long>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	private static final String AUDIENCIA_NULL_EXCEPTION = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR090.toString());
	
	ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	// protected region atributos end
    
    public ManejadorAudiencia() {
        super(Audiencia.class);
    }    
    
    // protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/**
	 * Coloca la audiencia que se pasa como parámetro en estado cancelada
	 * 
	 * @param audiencia
	 * @throws SimascException
	 *             Si la audiencia es nula
	 */
	public void cancelarAudiencia(Audiencia audiencia) {
		if (audiencia != null) {
			audiencia.setEstado(UtilDominios.ESTADO_AUDIENCIA_CANCELADA);
			audiencia.setFechaUltimaModificacion(new Date());
			this.actualizar(audiencia);
		} else {
			throw new SimascException(AUDIENCIA_NULL_EXCEPTION);
		}
	}

	public List<AudienciaDTO> obtenerAudienciasFiltros(AudienciaDTO filtroAudiencias) {
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append("select C.id_caso");
		nativeQuery.append(", A.tipo_audiencia");
		nativeQuery.append(", C.nombre");
		nativeQuery.append(", A.hora_inicio");
		nativeQuery.append(", A.hora_fin");
		nativeQuery.append(", (SED.nombre + ' ' + SED.direccion +' NUMERO SALA:'+ SAL.numero_sala) AS SEDE");
		nativeQuery.append(", A.estado");
		nativeQuery.append(", A.observaciones ");
		nativeQuery.append(", A.id_audiencia ");
		nativeQuery.append(", C.etapa ");
		nativeQuery.append(", A.cantidad_principales ");
		nativeQuery.append(", A.cantidad_suplentes ");
		nativeQuery.append("FROM AUDIENCIA A ");
		if (filtroAudiencias.getEtapa() != null) {
			nativeQuery.append(" inner join dominio d on d.codigo = a.tipo_audiencia and codigo_dom_padre = ?7 ");
		}
		nativeQuery.append("INNER JOIN  CASO C ");
		nativeQuery.append(" ON C.id_caso = A.id_caso ");
		if (filtroAudiencias.getIdUsuario() != null) {
			nativeQuery.append(" INNER JOIN  rol_persona_caso RPC ");
			nativeQuery.append(" ON RPC.id_caso = C.id_caso ");
		}
		nativeQuery.append("LEFT JOIN AGENDAMIENTO AG ");
		nativeQuery.append("ON A.id_audiencia = AG.id_audiencia");
		nativeQuery.append(" LEFT JOIN SALA SAL ");
		nativeQuery.append(" ON SAL.id_sala = AG.id_sala ");
		nativeQuery.append(" LEFT JOIN SEDE SED ");
		nativeQuery.append(" ON SAL.id_sede = SED.id_sede ");
		nativeQuery.append(" WHERE ");
		nativeQuery.append(" C.estado_registro =").append("'ACT'");
		nativeQuery.append(" AND A.estado_registro = ").append("'ACT'");
		
		if (filtroAudiencias.getIdCaso() != null) {
			nativeQuery.append(" AND A.id_caso =?1 ");
		}
		if (filtroAudiencias.getNombreCaso() != null) {
			nativeQuery.append(" AND C.nombre like '%'+?2+'%'");
		}
		if (filtroAudiencias.getHoraInicio() != null && filtroAudiencias.getHoraFin() != null) {
			nativeQuery.append(" AND A.hora_inicio >=?3 ");
			nativeQuery.append(" AND A.hora_inicio <=?4");
		}
		if (filtroAudiencias.getHoraInicio() == null && filtroAudiencias.getHoraFin() == null
				& filtroAudiencias.getIdCaso() == null) {
			nativeQuery.append(" AND A.hora_inicio >= DATEADD(month, -1, GETDATE())");
		}
		if (filtroAudiencias.getHoraInicio() != null && filtroAudiencias.getHoraFin() == null) {
			nativeQuery.append(" AND A.hora_inicio >=?5");
		}
		if (filtroAudiencias.getHoraInicio() == null && filtroAudiencias.getHoraFin() != null) {
			nativeQuery.append(" AND A.hora_inicio >= DATEADD(month, -1, GETDATE())");
			nativeQuery.append(" AND A.hora_inicio <=?5");
		}
		if (filtroAudiencias.getIdUsuario() != null) {
			nativeQuery.append(" AND RPC.id_persona =?6 ");
			nativeQuery.append(" AND RPC.id_rol = ").append(filtroAudiencias.getIdRolSecretario());
		}		
		nativeQuery.append(" ORDER BY A.hora_inicio, C.id_caso");
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString());
		agregarParametrosConsulta(query, filtroAudiencias);
		return convertirResultadoAudienciasDTO(query.getResultList());			
	}
	
	private void agregarParametrosConsulta(Query query, AudienciaDTO filtroAudiencias) {
		if (filtroAudiencias.getIdCaso() != null)	{
			query.setParameter(1, filtroAudiencias.getIdCaso());
		}
		if (filtroAudiencias.getNombreCaso() != null)	{
			query.setParameter(2, filtroAudiencias.getNombreCaso());
		}
		if (filtroAudiencias.getHoraFin() != null && filtroAudiencias.getHoraInicio() != null) {
			Date horaInicio = UtilOperaciones.obtenerFechaComienzoDelDia(filtroAudiencias.getHoraInicio());
			Date horaFin = UtilOperaciones.obtenerFechaFinDelDia(filtroAudiencias.getHoraFin());
			query.setParameter(4, horaFin);
			query.setParameter(3, horaInicio);
		}
		if (filtroAudiencias.getHoraInicio() != null && filtroAudiencias.getHoraFin() == null)	{		
			query.setParameter(5, filtroAudiencias.getHoraInicio());
		}
		if (filtroAudiencias.getHoraInicio() == null && filtroAudiencias.getHoraFin() != null)	{
			query.setParameter(5, filtroAudiencias.getHoraFin());
		}		
		if(filtroAudiencias.getIdUsuario()!=null)	{
			query.setParameter(6, filtroAudiencias.getIdUsuario());
		}
		if(filtroAudiencias.getEtapa()!=null){
			query.setParameter(7,
					UtilDominios.ETAPA_CASO_PREARBITRAL.equals(filtroAudiencias.getEtapa())
							? UtilDominios.AGRUPADOR_TIPO_AUDIENCIA_PREARBITRAL
							: UtilDominios.AGRUPADOR_TIPO_AUDIENCIA_ARBITRAL);
		}		
	}
	
	private List<AudienciaDTO> convertirResultadoAudienciasDTO(List<Object[]> registros){
		List<AudienciaDTO> audiencias = new ArrayList<>();
		for(Object[] registro : registros){
			audiencias.add(convertirRegistroAudienciaDTO(registro));
		}
		
		return audiencias;
	}
	
	private AudienciaDTO convertirRegistroAudienciaDTO(Object[] registro){
		AudienciaDTO audiencia = new AudienciaDTO();
		Object reg;

		audiencia.setIdCaso( ( ( reg = registro[0]) ==null ? null : ( (BigDecimal) reg).longValue() ) );
		audiencia.setTipoAudiencia( ( ( reg = registro[1]) ==null ? null : (String) reg) );
		audiencia.setNombreCaso( ( ( reg = registro[2]) ==null ? null : (String) reg) );
		audiencia.setHoraInicio( ( ( reg = registro[3]) ==null ? null : (Date) reg) );
		audiencia.setHoraFin( ( ( reg = registro[4]) ==null ? null : (Date) reg) );
		audiencia.setNombreSala( ( ( reg = registro[5]) ==null ? "Sin Sala" : (String) reg) );
		audiencia.setEstado( ( ( reg = registro[6]) ==null ? null : (String) reg) );
		audiencia.setObservaciones( ( ( reg = registro[7]) ==null ? null : (String) reg) );	
		audiencia.setIdAudiencia( ( ( reg = registro[8]) ==null ? null : ( (BigDecimal) reg).longValue() ) );
		audiencia.setEtapa( ( ( reg = registro[9]) ==null ? null : (String) reg) );	
		audiencia.setCantidadPrincipales( ( ( reg = registro[10]) ==null ? null : ( (BigDecimal) reg).intValue()) );	
		audiencia.setCantidadSuplentes( ( ( reg = registro[11]) ==null ? null : ( (BigDecimal) reg).intValue() ));	
		return audiencia;
	}
	
	public AudienciaDTO obtenerAudienciaFiltros(Long idCaso) {
		AudienciaDTO audiencia = new AudienciaDTO();

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select id_audiencia ");
		nativeQuery.append("from audiencia ");
		nativeQuery.append("where ");
		nativeQuery.append("id_caso =").append(idCaso);
		nativeQuery.append(" AND estado ='").append(UtilDominios.ESTADO_AUDIENCIA_PENDIENTE).append("'");
		nativeQuery.append(" and hora_inicio=(select max(hora_inicio) from audiencia where id_caso=").append(idCaso);
		nativeQuery.append(" and estado = '").append(UtilDominios.ESTADO_AUDIENCIA_PENDIENTE).append("' )");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString());
		try{
			BigDecimal idAudiencia=(BigDecimal)query.getSingleResult();
			audiencia.setIdAudiencia(idAudiencia.longValue());
		} catch (NonUniqueResultException | NoResultException e) {
			audiencia = null;
		}	
		
		return audiencia;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AudienciaTranscripcionesPendientesDTO> consultarAudienciasTranscripcionesPendientes(Long idPersona, String nombreRol) {
		StringBuilder nativeQuery = new StringBuilder();

		nativeQuery.append("SELECT a.id_caso, ");
		nativeQuery.append("       a.id_audiencia, ");
		nativeQuery.append("       c.nombre, ");
		nativeQuery.append("       MAX (t.fecha_prevista_de_entrega) as fecha_prevista_entrega, ");
		nativeQuery.append("       a.hora_inicio as fecha_audiencia, ");
		nativeQuery.append("       SUM (t.tiempo_final - t.tiempo_inicial) as minutos_a_transcribir, ");
		nativeQuery.append("       SUM (t.tiempo_transcrito) as minutos_transcritos ");
		nativeQuery.append("  FROM AUDIENCIA a,  ");
		nativeQuery.append("	   DOCUMENTO d, ");
		nativeQuery.append("	   CASO c, ");
		nativeQuery.append("	   TRANSCRIPCION t, ");
		nativeQuery.append("	   DOMINIO dom, ");
		nativeQuery.append("	   PERSONA p, ");
		nativeQuery.append("	   ROL_PERSONA rp, ");
		nativeQuery.append("	   ROL r ");
		nativeQuery.append(" WHERE d.id_audiencia = a.id_audiencia ");
		nativeQuery.append("   AND a.id_caso = c.id_caso ");
		nativeQuery.append("   AND t.id_doc_audio_fuente = d.id_documento ");
		nativeQuery.append("   AND d.estado_digitalizacion = ?1 ");
		nativeQuery.append("   AND d.tipo_archivo = dom.codigo ");
		nativeQuery.append("   AND dom.codigo_dom_padre = ?2 ");
		nativeQuery.append("   AND dom.dominio_padre = ?3 ");
		nativeQuery.append("   AND t.id_persona = p.id_persona ");
		nativeQuery.append("   AND rp.id_persona = p.id_persona ");
		nativeQuery.append("   AND rp.id_rol = r.id_rol ");
		nativeQuery.append("   AND r.nombre = ?4 ");
		
		if (UtilDominios.ROL_PERSONA_AUXILIAR_ADM.equals(nombreRol)) {
			nativeQuery.append("   AND t.id_persona = ?5 ");
		}
		
		nativeQuery.append("   AND d.tipo_documento = ?6 ");
		nativeQuery.append("   AND t.estado = ?7 ");
		
		nativeQuery.append("   AND a.estado_registro = ?8 ");
		nativeQuery.append("   AND d.estado_registro = ?9 ");
		nativeQuery.append("   AND c.estado_registro = ?10 ");
		nativeQuery.append("   AND t.estado_registro = ?11 ");
		nativeQuery.append("   AND p.estado_registro = ?12 ");
		nativeQuery.append("   AND rp.estado_registro = ?13 ");
		nativeQuery.append("   AND r.estado_registro = ?14 ");
		
		nativeQuery.append("GROUP BY a.id_audiencia, a.id_caso, c.nombre, a.hora_inicio ");
		nativeQuery.append("ORDER BY fecha_prevista_entrega, a.hora_inicio ");

		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString());
		query.setParameter(1, UtilDominios.ESTADO_DIGITALIZACION_DIGITALIZADO);
		query.setParameter(2, UtilDominios.AGRUPADOR_TIPO_ARCHIVO_AUDIO);
		query.setParameter(3, UtilDominios.DOMINIO_AGRUPADOR_TIPO_ARCHIVO);
		query.setParameter(4, UtilDominios.ROL_PERSONA_AUXILIAR_ADM);
		
		if (UtilDominios.ROL_PERSONA_AUXILIAR_ADM.equals(nombreRol)) {
			query.setParameter(5, idPersona);
		}
		
		query.setParameter(6, UtilDominios.TIPO_DOCUMENTO_DIG_AUDIO);
		query.setParameter(7, UtilDominios.ESTADO_TRANSCRIPCION_PENDIENTE);
		query.setParameter(8, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(9, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(10, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(11, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(12, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(13, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(14, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		List<Object[]> resultados = query.getResultList();

		return convertirResultadoAAudienciaTranscripcionesPendientesDTO(resultados);
	}
	
	private List<AudienciaTranscripcionesPendientesDTO> convertirResultadoAAudienciaTranscripcionesPendientesDTO(
			List<Object[]> registros) {
		List<AudienciaTranscripcionesPendientesDTO> audiencias = new ArrayList<>();
		for (Object[] registro : registros) {
			audiencias.add(convertirRegistroAAudienciaTranscripcionesPendientesDTO(registro));
		}

		return audiencias;
	}

	private AudienciaTranscripcionesPendientesDTO convertirRegistroAAudienciaTranscripcionesPendientesDTO(
			Object[] registro) {
		AudienciaTranscripcionesPendientesDTO audiencia = new AudienciaTranscripcionesPendientesDTO();

		audiencia.setIdCaso(registro[0] == null ? null : ((BigDecimal) registro[0]).longValue());
		audiencia.setIdAudiencia(registro[1] == null ? null : ((BigDecimal) registro[1]).longValue());
		audiencia.setNombreCaso(registro[2] == null ? null : (String) registro[2]);
		audiencia.setFechaPrevistaEntrega(registro[3] == null ? null : (Date) registro[3]);
		audiencia.setFechaAudiencia(registro[4] == null ? null : (Date) registro[4]);
		
		Calendar calendar = Calendar.getInstance();
		
		if (registro[5] != null) {
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, ((BigDecimal) registro[5]).intValue());
			audiencia.setMinutosPorTranscribir(calendar.getTime());
		} else {
			audiencia.setMinutosPorTranscribir(null);
		}

		if (registro[6] != null) {
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, ((BigDecimal) registro[6]).intValue());
			audiencia.setMinutosTranscritos(calendar.getTime());
		} else {
			audiencia.setMinutosTranscritos(null);
		}

		return audiencia;
	}
	
	/**
	 * obtiene la ultima audiencia de un caso de determinado tipo
	 * 
	 * @param idCASO
	 * @param tipoAudiencia
	 * @return Audiencia          Si la audiencia es nula
	 */
	public Audiencia consultarUltimaAudiencia(Long idCaso, String tipoAudiencia) {
		
		Query query = mp.getEntityManager().createQuery(" SELECT a "
				+ "					     FROM Audiencia a "
				+ "						WHERE a.idCaso = :idCaso "
				+ "						  AND a.tipoAudiencia = :tipoAudiencia "
				+ "						  AND a.estadoRegistro = :estadoRegistro "
				+ "						ORDER BY a.horaInicio DESC");
		
		query.setParameter("idCaso", idCaso);
		query.setParameter("tipoAudiencia", tipoAudiencia);
		query.setParameter("estadoRegistro", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		List<Audiencia> audiencias = (List<Audiencia>) query.getResultList();
		if (!audiencias.isEmpty())
			return audiencias.get(0);
		return null;
	}
	
	/**
	 * Las audiencias por tipo
	 * 
	 * @param idCASO
	 * @param tipoAudiencia
	 * @return Audiencia          Si la audiencia es nula
	 */
	public List<Audiencia> consultarAudienciasPorTipoEstado(String tipoAudiencia, String estado) {
		
		Query query = mp.getEntityManager().createQuery(" SELECT a "
				+ "					     FROM Audiencia a "
				+ "						WHERE  "
				+ "						      a.tipoAudiencia = :tipoAudiencia "
				+ "						  AND a.estado = :estado "
				+ "						  AND a.estadoRegistro = :estadoRegistro "
				+ "						  AND a.horaInicio > :fecha "
				+ "                       ORDER BY a.horaInicio , a.idCaso");
		
		query.setParameter("tipoAudiencia", tipoAudiencia);
		query.setParameter("estado", estado);
		query.setParameter("fecha", UtilOperaciones.obtenerFechaComienzoDelDia(new Date()));
		query.setParameter("estadoRegistro", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return (List<Audiencia>) query.getResultList();
	}
	
	/**
	 * Método encargado de consultar las audiencias de un caso según su estado
	 * 
	 * @param idCaso
	 * @param estado
	 * @return
	 */
	public List<Audiencia> consultarAudienciasCasoPorTipoYEstado(Long idCaso, String tipoAudiencia, String estado) {
		StringBuilder jpqlQuery = new StringBuilder();

		jpqlQuery.append("SELECT a FROM Audiencia a ");
		jpqlQuery.append(" WHERE a.idCaso = :");
		jpqlQuery.append(Audiencia.ENTIDAD_AUDIENCIA_ID_CASO);
		jpqlQuery.append(" AND a.estado = :");
		jpqlQuery.append(Audiencia.ENTIDAD_AUDIENCIA_ESTADO);
		
		if (tipoAudiencia != null && !tipoAudiencia.isEmpty())
			jpqlQuery.append(" AND a.tipoAudiencia = :").append(Audiencia.ENTIDAD_AUDIENCIA_TIPO_AUDIENCIA);
		
		jpqlQuery.append(" AND a.estadoRegistro = :");
		jpqlQuery.append(Audiencia.ENTIDAD_AUDIENCIA_ESTADO_REGISTRO);

		Query query = mp.getEntityManager().createQuery(jpqlQuery.toString());

		query.setParameter(Audiencia.ENTIDAD_AUDIENCIA_ID_CASO, idCaso);
		query.setParameter(Audiencia.ENTIDAD_AUDIENCIA_ESTADO, estado);
		
		if (tipoAudiencia != null && !tipoAudiencia.isEmpty())
			query.setParameter(Audiencia.ENTIDAD_AUDIENCIA_TIPO_AUDIENCIA, tipoAudiencia);
		
		query.setParameter(Audiencia.ENTIDAD_AUDIENCIA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();
	}
	
	/**
	 * Metodo encargado de consultar las audiencias en estado pendiente de
	 * designacion, instalacion, y sorteo asociadas a un Caso.
	 * 
	 * @param idCaso
	 * @return List<Audiencia>
	 */
	public List<Audiencia> citacionAudienciasDiferentesPrimera(Long idCaso){
		StringBuilder jpqlQuery = new StringBuilder();
		
		jpqlQuery.append("SELECT a ");
		jpqlQuery.append("FROM Audiencia a ");
		jpqlQuery.append("WHERE (a.tipoAudiencia = : designacion OR a.tipoAudiencia = : instalacion OR a.tipoAudiencia = : sorteo ) ");
		jpqlQuery.append(" AND a.idCaso = :");
		jpqlQuery.append(Audiencia.ENTIDAD_AUDIENCIA_ID_CASO);
		jpqlQuery.append(" AND a.estado = : ");
		jpqlQuery.append(Audiencia.ENTIDAD_AUDIENCIA_ESTADO);
		jpqlQuery.append(" AND a.estadoRegistro = : ");
		jpqlQuery.append(Audiencia.ENTIDAD_AUDIENCIA_ESTADO_REGISTRO);

		Query query = mp.getEntityManager().createQuery(jpqlQuery.toString());
		query.setParameter(Audiencia.ENTIDAD_AUDIENCIA_ESTADO, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		query.setParameter(Audiencia.ENTIDAD_AUDIENCIA_ID_CASO, idCaso);	
		query.setParameter(Audiencia.ENTIDAD_AUDIENCIA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter("instalacion", UtilDominios.TIPO_AUDIENCIA_AUDIENCIA_DE_INSTALACION);
		query.setParameter("sorteo", UtilDominios.TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_DE_DESIGNACION);
		return query.getResultList();
	}

	/**
	 * Método que obtiene las  
	 * @param estado
	 * @return
	 */
	public List<AudienciaSorteoDTO> consultarAudienciasSorteo(String estado) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT     a.hora_inicio fecha, ");
		nativeQuery.append("           c.id_caso     idCaso    , ");
		nativeQuery.append("           c.nombre      nombreCaso, ");
		nativeQuery.append("se.nombre	tipoCaso, ");
		nativeQuery.append("ma.nombre as materia, ");
		nativeQuery.append("dc.nombre as tipoCuantia, ");
		nativeQuery.append("case c.preseleccion when 1 then 'Si' else 'No' end as preseleccion, ");
		nativeQuery.append("           CASE ");
		nativeQuery.append("                      WHEN ( ");
		nativeQuery.append("                                            CONVERT(VARCHAR(10), a.hora_inicio, 111) = CONVERT(VARCHAR(10), GETDATE(), 111) ");
		nativeQuery.append("                                 OR         ( ");
		nativeQuery.append("                                                       CONVERT(VARCHAR(10), DATEADD(dd, 1, GETDATE()), 111) = CONVERT(VARCHAR(10), a.hora_inicio, 111) ");
		nativeQuery.append("                                            AND        DATEPART(hh, GETDATE())                             >= ");
		nativeQuery.append("                                                       (SELECT valor_numerico ");
		nativeQuery.append("                                                       FROM    PARAMETROS_GENERALES ");
		nativeQuery.append("                                                       WHERE   codigo = ?5 ");
		nativeQuery.append("                                                       ) ");
		nativeQuery.append("                                            ) ");
		nativeQuery.append("                                 ) ");
		nativeQuery.append("                      THEN 1 ");
		nativeQuery.append("                      ELSE 0 ");
		nativeQuery.append("           END AS verElegible ");
		nativeQuery.append("FROM       audiencia a ");
		nativeQuery.append("           INNER JOIN caso c ON c.id_caso = a.id_caso ");
		nativeQuery.append("           INNER JOIN SERVICIO se ON se.id_servicio = c.id_servicio AND se.estado_registro = ?3 AND se.tipo = ?6 ");
		nativeQuery.append("           INNER JOIN SEDE sed ON sed.id_sede  = c.id_sede AND sed.estado_registro = ?3 ");
		nativeQuery.append("           INNER JOIN MATERIA ma on c.id_materia = ma.id_materia ");
		nativeQuery.append("           INNER JOIN DOMINIO dc on c.tipo_cuantia = dc.codigo and dc.dominio in (?7, ?8) ");
		nativeQuery.append("WHERE      a.estado             = ?2 ");
		nativeQuery.append("AND        a.tipo_audiencia     = ?1 ");
		nativeQuery.append("AND        a.hora_inicio        > GETDATE() ");
		nativeQuery.append("AND        a.estado_registro    = ?3 ");
		nativeQuery.append("AND        EXISTS ");
		nativeQuery.append("           (SELECT valor ");
		nativeQuery.append("           FROM    PARAMETRO_DE_SERVICIO p ");
		nativeQuery.append("           WHERE   id_servicio       = c.id_servicio ");
		nativeQuery.append("           AND     p.tipo_parametro  = ?4 ");
		nativeQuery.append("           AND     valor             = 1 ");
		nativeQuery.append("           AND     p.estado_registro = ?3 ");
		nativeQuery.append("           ) ");
		nativeQuery.append("ORDER BY   a.hora_inicio, ");
		nativeQuery.append("           c.id_caso");
		
		Query q = getEntityManager().createNativeQuery(nativeQuery.toString(), AudienciaSorteoDTO.class);
		q.setParameter(1, UtilDominios.TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_DE_DESIGNACION);
		q.setParameter(2, estado);
		q.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(4, UtilConstantes.TIPO_PARAMETRO_VISUALIZACION_SORTEO);
		q.setParameter(5, UtilConstantes.TIPO_PARAMETRO_VISUALIZACION_ELEGIBLES);
		q.setParameter(6, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		q.setParameter(7, UtilDominios.DOMINIO_TIPO_CUANTIA);
		q.setParameter(8, UtilDominios.DOMINIO_TIPO_CUANTIA_ARBITRAJE);
		
		return q.getResultList();
	}

	/**
	 * Método para obtener las audiencias programadas para la jornada
	 * @param idJornada
	 * @return
	 */
	public List<AudienciaDTO> consultarAudienciasPendientesPorJornada(Long idJornada) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT DISTINCT a.id_audiencia as idAudiencia, a.hora_inicio as horaInicio, a.hora_fin as horaFin, c.id_caso as idCaso, c.nombre as nombreCaso ");
		jpqlQuery.append(" FROM audiencia a ");
		jpqlQuery.append(" inner JOIN caso c on c.id_caso = a.id_caso and a.estado = ?1");
		jpqlQuery.append(" inner JOIN audiencia_turno_jornada atj on a.id_audiencia = atj.id_audiencia ");
		jpqlQuery.append(" inner JOIN turno_jornada tj on tj.id_turno_jornada = atj.id_turno_jornada ");
		jpqlQuery.append(" where tj.id_convenio = ?2");
		jpqlQuery.append(" AND c.estado_registro = ").append("'ACT'");
		jpqlQuery.append(" AND a.estado_registro = ").append("'ACT'");
		jpqlQuery.append(" AND atj.estado_registro = ").append("'ACT'");
		jpqlQuery.append(" AND tj.estado_registro = ").append("'ACT'");
		jpqlQuery.append(" order by c.nombre, a.hora_inicio ");
		Query query = getEntityManager().createNativeQuery(jpqlQuery.toString(), AudienciaDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		query.setParameter(2, idJornada);
		return query.getResultList();
	}	
	/**
	 * Método que obtiene la cantidad de audiencias dados los estados para el caso que se recibe
	 * @param idCaso
	 * @param filtroEstado
	 * @return
	 */
	public Long obtenerNumeroAudienciasCaso( Long idCaso, List<String> filtroEstado ){
		BigDecimal numeroAudiencias;
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT COUNT(*) ");
		nativeQuery.append(" FROM AUDIENCIA a ");
	
		nativeQuery.append(" WHERE a.estado_registro = ?1");
		if ( filtroEstado != null && !filtroEstado.isEmpty()){
			nativeQuery.append(" AND a.estado ").append(UtilConsultasSQL.clausulaInSQLStrings(filtroEstado));		
		}
		nativeQuery.append(" AND a.id_caso = ?2 ");
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), BigDecimal.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idCaso);
		
		try{
			numeroAudiencias =  (BigDecimal) query.getSingleResult();
		} catch (NoResultException e) {
			numeroAudiencias = null;
		}
		return numeroAudiencias != null ? numeroAudiencias.longValue() : null;
	}
	
	/**
	 * Método para obtener la información de la audiencia pendiente (hora inicio, hora fin, sede, id de la audiencia)
	 * @param idCaso
	 * @return
	 */
	public List<CitacionDTO> consultarInformacionAudienciaPendiente (Long idCaso){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select au.id_audiencia as idAudiencia, ");
		nativeQuery.append(" au.hora_inicio as horaInicio, ");
		nativeQuery.append(" au.hora_fin as horaFin, ");
		nativeQuery.append(" se.nombre as sedeAudiencia ");
		nativeQuery.append(" from audiencia au ");
		nativeQuery.append(" left join AGENDAMIENTO ag on ag.id_audiencia= au.id_audiencia  ");
		nativeQuery.append(" and ag.estado_registro = ?3 ");
		nativeQuery.append(" left join sala s on s.id_sala = ag.id_sala ");
		nativeQuery.append(" and s.estado_registro= ?3 ");
		nativeQuery.append(" left join sede se on se.id_sede=s.id_sede ");
		nativeQuery.append(" and se.estado_registro= ?3 ");
		nativeQuery.append(" where au.id_caso=?1 ");
		nativeQuery.append(" and au.estado = ?2 ");		
		nativeQuery.append(" and au.estado_registro = ?3 ");
		
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), CitacionDTO.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();

	}
	
	/**
	 * Obtiene la ultima audiencia de un caso y por el estado si se envia 
	 * 
	 * @param idCaso
	 * @param estadoAudiencia
	 * @return Audiencia
	 */
	public AudienciaDTO consultarUltimaAudienciaEstadoCaso(Long idCaso, String estadoAudiencia) {
		
		Audiencia audiencia;
		StringBuilder nativeQuery = new StringBuilder();
		try{
			nativeQuery.append(" SELECT TOP 1 * ");
			nativeQuery.append(" FROM Audiencia (nolock) ");
			nativeQuery.append(" WHERE id_caso =?1 ");
			nativeQuery.append(" AND estado_registro = ?2 ");
			if(estadoAudiencia != null)
				nativeQuery.append(" AND estado =?3");
			nativeQuery.append(" ORDER BY consecutivo DESC, hora_inicio DESC");
		
			Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), Audiencia.class);
			query.setParameter(1, idCaso);
			query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			if(estadoAudiencia != null)
				query.setParameter(3, estadoAudiencia);
			audiencia =  (Audiencia) query.getSingleResult();
		} catch (NoResultException e) {
			audiencia = null;
		}
		return audiencia != null? new AudienciaDTO().transformarSinDependencias(audiencia): null;
	}
	
	
	
	/**
	 * Obtiene el nombre del resultado de una audiencia 
	 * 
	 * @param idCaso
	 * @param estadoAudiencia
	 * @return Audiencia
	 */
	public String consultarNombreResultadoAudiencia(Long idAudiencia) {
		
		String nombreResultado;
		StringBuilder nativeQuery = new StringBuilder();
		try{
			nativeQuery.append(" select d.nombre ");
			nativeQuery.append(" from dominio d ");
			nativeQuery.append(" inner join AUDIENCIA A on d.codigo=a.resultado  ");
			nativeQuery.append(" where d.dominio= ?1 ");
			nativeQuery.append(" and d.codigo=a.resultado ");
			nativeQuery.append(" and a.id_audiencia=?2 ");			
			nativeQuery.append(" and a.estado_registro=?3 ");
		
			Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), String.class);
			query.setParameter(1, UtilDominios.DOMINIO_RESULTADOS_AUDIENCIA);
			query.setParameter(2, idAudiencia);
			query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			nombreResultado =  (String) query.getSingleResult();
		} catch (NoResultException e) {
			nombreResultado = null;
		}
		return nombreResultado;
	}
	
	/**
	 * Método para obtener la informacion de las audiencias programadas a las que se encuentra asociado el conciliador para la sede y la fecha
	 * @param idSede
	 * @param fechaAudiencia
	 * @param idPersona
	 * @return
	 */
	public List<AudienciasProgramadasDTO> consultarAudienciasProgramadasConciliador(Long idSede, Date fechaAudiencia, Long idPersona) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select distinct ap.id_agenda_persona as idAgendaPersona, ap.estado as estado, c.id_caso as idCaso, c.nombre as nombre, ");
		nativeQuery.append(" concat(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) as nombrePersona, ");
		nativeQuery.append(" se.nombre as sede, a.hora_inicio as fechaInicio, ap.observaciones as observaciones ");
		nativeQuery.append(" , CASE when ap.cumplio_horario is null or ap.cumplio_horario = 0 then 'false' else 'true' end as cumplioHorario ");
		nativeQuery.append(" , CASE when ap.justificacion_valida is null or ap.justificacion_valida = 0 then 'false' else 'true' end as valido ");
		nativeQuery.append(" from AGENDA_PERSONA ap ");
		nativeQuery.append(" inner join audiencia au on au.id_audiencia = ap.id_audiencia and au.estado_registro = ?4 ");
		nativeQuery.append(" inner join rol_persona_caso rpc on ap.id_persona = rpc.id_persona and rpc.id_caso = au.id_caso and rpc.tipo_nombramiento = ?8 and rpc.estado <> ?7 and rpc.estado_registro = ?4 ");
		nativeQuery.append(" inner join persona p on p.id_persona = rpc.id_persona and p.estado_registro = ?4 ");
		nativeQuery.append(" inner join CASO c on c.id_caso = rpc.id_caso and c.estado_registro = ?4 ");
		nativeQuery.append(" inner join AGENDAMIENTO a on a.id_audiencia = ap.id_audiencia and a.estado_registro = ?4 ");
		nativeQuery.append(" inner join SALA s on s.id_sala = a.id_sala and s.estado_registro = ?4 ");
		nativeQuery.append(" inner join SEDE se on se.id_sede = s.id_sede and se.estado_registro = ?4 ");
		nativeQuery.append(" where tipo_actividad_agenda = ?6 ");
		nativeQuery.append(" and ap.estado <> ?5 ");
		nativeQuery.append(" and ap.id_audiencia is not null ");
		nativeQuery.append(" and ap.estado_registro = ?4 ");
		nativeQuery.append(" and CAST(ap.fecha_inicio as Date) = CAST(?1 as Date)  ");
		nativeQuery.append(" and p.id_persona = ?2 ");
		nativeQuery.append(" and se.id_sede = ?3 ");
		
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), AudienciasProgramadasDTO.class);
		query.setParameter(1, fechaAudiencia);
		query.setParameter(2, idPersona);
		query.setParameter(3, idSede);
		query.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(5, UtilDominios.ESTADO_AGENDA_PERSONA_CANCELADA);
		query.setParameter(6, UtilDominios.TIPO_ACTIVIDAD_AGENDA_AUDIENCIA_CONCILIACION);
		query.setParameter(7, UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO);
		query.setParameter(8, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		
		return query.getResultList();
	}
	
	/**
	 * Método que obtiene la cantidad de  audiencias programadas en las que la persona se encuentra como conciliador,
	 * y adicionalmente si cumplio el horario y si presento una justificacion valida
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @param incumplio
	 * @param justificacion
	 * @return
	 */
	public BigDecimal consultarAudienciasProgramadas(Long idPersona,
			Date periodoDesde, Date periodoHasta, boolean incumplio, boolean justificacion) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select count(distinct a.id_audiencia) from audiencia a ");
		nativeQuery.append(" inner join agenda_persona ap on a.id_audiencia = ap.id_audiencia ");
		nativeQuery.append(" and ap.estado_registro = ?1 ");
		nativeQuery.append(" and ap.estado = ?2 ");
		nativeQuery.append(" and ap.id_persona = ?3 ");
			if(incumplio)
				nativeQuery.append(" and ap.cumplio_horario = 0 ");
			if(justificacion)
				nativeQuery.append(" and ap.justificacion_valida = 1 ");
		nativeQuery.append(" where a.resultado is not null and a.resultado <> ?4 ");
		nativeQuery.append(" and cast(a.hora_fin as date) between ?5 and ?6 ");
		nativeQuery.append(" and a.estado_registro = ?1 ");
		
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), BigDecimal.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_AGENDA_PERSONA_VALIDADA);
		query.setParameter(3, idPersona);
		query.setParameter(4, UtilDominios.RESULTADO_AUDIENCIA_REPROGRAMACION);
		query.setParameter(5, periodoDesde);
		query.setParameter(6, periodoHasta);
		
		return (BigDecimal) query.getSingleResult();
	}
	
	  /**
     * Consulta los casos con determinado resultado que aun no tengan acta.
     * @param resultadoAudiencia
     * @param tipoPeriodicidad
     * @param dias
     * @return
     */
    public List<InfoBasicaAlertasDTO> alertaResultadoPendienteDocmento( String resultadoAudiencia, String tipoPeriodicidad, 
    		Long dias, String tablaEncabezado, String tablaCierre, String tablaTextoEncabezado ){
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append(" DECLARE @TablaEncabezado varchar(max), ");
		nativeQuery.append(" @TablaCierre varchar(max), ");
		nativeQuery.append(" @TablaTextoEncabezado varchar(max) ");
		nativeQuery.append(" Set @TablaCierre = ?13 ");
		nativeQuery.append(" Set @TablaEncabezado = ?14 ");
		nativeQuery.append(" Set @TablaTextoEncabezado = ?15 ");

		nativeQuery.append("  (SELECT * ");
		nativeQuery.append("  FROM    (SELECT DISTINCT p.id_persona                                                                                                                    AS idPersona    , ");
		nativeQuery.append("                                            CONCAT(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) AS nombrePersona, ");
		nativeQuery.append(" @TablaEncabezado + @TablaTextoEncabezado + ");		
		nativeQuery.append("                           (SELECT DISTINCT t.idCaso     AS                                                                                                       td           , ");
		nativeQuery.append("                                            t.nombreCaso AS                                                                                                       td ");
		nativeQuery.append("                           FROM             ( SELECT    ca.id_caso                                              AS                                                                                             idCaso    , ");
		nativeQuery.append("                                                       ca.nombre                                                AS                                                                                             nombreCaso, ");
		nativeQuery.append("                                                       ce.id_centro                                             AS                                                                                             idCentro  , ");
				if (UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad)) {
					nativeQuery.append(" dbo.diasHabilesEntreDosFechas(au.hora_inicio, GETDATE()) AS diasResultadoAudiencia ");
				} else {
					nativeQuery.append(" DATEDIFF(dd, au.hora_inicio, GETDATE()) AS diasResultadoAudiencia ");
				}
		nativeQuery.append("                                            FROM       CASO ca ");
		nativeQuery.append("                                                       INNER JOIN SERVICIO se ");
		nativeQuery.append("                                                       ON         se.id_servicio = ca.id_servicio ");
		nativeQuery.append("                                                       AND        se.tipo        = ?2  ");
		nativeQuery.append("                                                       CROSS APPLY (SELECT  TOP 1 a.* ");
		nativeQuery.append("                                                                  FROM     AUDIENCIA a ");
		nativeQuery.append("                                                                  WHERE    ca.id_caso        = a.id_caso ");
		nativeQuery.append("                                                                  AND      a.estado_registro = ?1 ");
		nativeQuery.append("                                                                  AND      a.estado          = ?3 ");
		nativeQuery.append("                                                                  ORDER BY a.consecutivo DESC ");
		nativeQuery.append("                                                                  ) ");
		nativeQuery.append("                                                                  au ");
		nativeQuery.append("                                                       INNER JOIN RESULTADO_AUDIENCIA ra ");
		nativeQuery.append("                                                       ON         ra.id_audiencia    = au.id_audiencia ");
		nativeQuery.append("                                                       AND        ra.estado_registro = ?1 ");
		nativeQuery.append("                                                       INNER JOIN ROL_PERSONA_CASO rpc ");
		nativeQuery.append("                                                       ON         rpc.id_caso         = ca.id_caso ");
		nativeQuery.append("                                                       AND        rpc.id_persona      = p.id_persona ");
		nativeQuery.append("                                                       AND        rpc.id_rol          = ?9 ");
		nativeQuery.append("                                                       AND        rpc.estado          = ?10 ");
		nativeQuery.append("                                                       AND        rpc.estado_registro = ?1 ");
		nativeQuery.append("                                                       LEFT JOIN SEDE sed ");
		nativeQuery.append("                                                       ON         ca.id_sede         = sed.id_sede ");
		nativeQuery.append("                                                       AND        se.estado_registro = ?1 ");
		nativeQuery.append("                                                       LEFT JOIN CENTRO ce ");
		nativeQuery.append("                                                       ON         ce.id_centro       = sed.id_centro ");
		nativeQuery.append("                                                       AND        ce.estado_registro =?1 ");
		nativeQuery.append("                                            WHERE      ca.estado_registro            = ?1 ");
		nativeQuery.append("                                            AND        ca.estado_caso NOT IN  (?6, ?7, ?8) ");
		nativeQuery.append("                                            AND        ra.tipo_resultado_audiencia = ?4 ");
		nativeQuery.append("                                            AND ");
		nativeQuery.append("                                                       ( ");
		nativeQuery.append("                                                                  ra.estado       = ?11 ");
		nativeQuery.append("                                                       OR         ra.estado IS NULL ");
		nativeQuery.append("                                                       ) ");
		nativeQuery.append("                                            ) ");
		nativeQuery.append("                                            t ");
		nativeQuery.append("                           WHERE            t.diasResultadoAudiencia >= ?5 FOR xml RAW ('tr'), ");
		nativeQuery.append("                                            ELEMENTS ");
		nativeQuery.append("                           ) + @TablaCierre AS tabla ");
		nativeQuery.append("          FROM             PERSONA p ");
		nativeQuery.append("          ) ");
		nativeQuery.append("          AS reg ");
		nativeQuery.append("  WHERE   reg.tabla IS NOT NULL ");
		nativeQuery.append("  )");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(3, UtilDominios.ESTADO_AUDIENCIA_REALIZADA);
		query.setParameter(4, resultadoAudiencia);	
		query.setParameter(5, dias);
		query.setParameter(6, UtilDominios.ESTADO_CASO_CANCELADO);
		query.setParameter(7, UtilDominios.ESTADO_CASO_REGISTRADO);
		query.setParameter(8, UtilDominios.ESTADO_CASO_FALTA_DE_COMPETENCIA);
		query.setParameter(9, UtilConstantes.ID_ROL_CONCILIADOR);
		query.setParameter(10, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(11, UtilDominios.ESTADO_RESULTADO_SIN_DOCUMENTO);
		query.setParameter(13, tablaCierre);
		query.setParameter(14, tablaEncabezado);
		query.setParameter(15, tablaTextoEncabezado);
		
    	return query.getResultList();
	}
    /**
     * Consulta si el caso en el cual se encuentra como conciliador cuenta con una audiencia programada
     * @param idPersona
     * @param idCaso
     * @return
     */
    public InfoBasicaAlertasDTO audienciasPendienteCasoConciliador(Long idPersona, Long idCaso) {
    	InfoBasicaAlertasDTO infoAlertas;
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select c.nombre as nombreCaso, (case when c.id_servicio = 9 ");
		nativeQuery.append(" then (select ce.nombre from centro ce inner join convenio co on ce.id_centro = co.id_centro and co.id_convenio = c.id_convenio) ");
		nativeQuery.append(" else (select ce.nombre from centro ce inner join sede s on ce.id_centro = s.id_centro and s.id_sede = c.id_sede) end ) nombreCentro ");
		nativeQuery.append("from caso c ");
		nativeQuery.append("inner join rol_persona_caso rpc on c.id_caso = rpc.id_caso and rpc.id_persona = ?3 and rpc.estado = ?2 and rpc.estado_registro = ?1 ");
		nativeQuery.append("where not exists (select id_audiencia from audiencia a ");
		nativeQuery.append("where a.estado = ?4 ");
		nativeQuery.append("and a.estado_registro = ?1 ");
		nativeQuery.append("and a.id_caso = c.id_caso) ");
		nativeQuery.append("and c.id_caso = ?5 ");
		nativeQuery.append("and c.estado_registro = ?1 ");
		nativeQuery.append("and c.estado_caso not in (?6, ?7, ?8) ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(3, idPersona);
		query.setParameter(4, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		query.setParameter(5, idCaso);
		query.setParameter(6, UtilDominios.ESTADO_CASO_CANCELADO );
		query.setParameter(7, UtilDominios.ESTADO_CASO_REGISTRADO);
		query.setParameter(8, UtilDominios.ESTADO_CASO_FALTA_DE_COMPETENCIA);

		try {
			infoAlertas = (InfoBasicaAlertasDTO) query.getSingleResult();
		} catch (NonUniqueResultException  | NoResultException e) {
			infoAlertas = null;
		}
		
		return infoAlertas;
    }

	/**
     * Consulta los casos con determinado resultado que aun no tengan acta.
     * @param resultadoAudiencia
     * @param tipoPeriodicidad
     * @param dias
     * @return
     */
    public List<InfoBasicaAlertasDTO> alertaCerrarAudiencia(String tablaEncabezado,
			String tablaCierre, String tablaTextoEncabezado, String metodoNombramiento){
		
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append(" DECLARE @TablaEncabezado varchar(max), ");
		nativeQuery.append(" @TablaCierre varchar(max), ");
		nativeQuery.append(" @TablaTextoEncabezado varchar(max) ");
		nativeQuery.append(" Set @TablaCierre = ?13 ");
		nativeQuery.append(" Set @TablaEncabezado = ?14 ");
		nativeQuery.append(" Set @TablaTextoEncabezado = ?15 ");

		nativeQuery.append(" (SELECT * FROM (SELECT DISTINCT p.id_persona AS idPersona, ");
		nativeQuery.append(
				" CONCAT(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) AS nombrePersona, ");
		nativeQuery.append(" @TablaEncabezado + @TablaTextoEncabezado + (SELECT ");
		nativeQuery.append(" DISTINCT c.id_caso AS td, ");
		nativeQuery.append(" ISNULL(c.nombre, '') AS td, ");		
		nativeQuery.append(" a.hora_inicio as td, ce.nombre as td, ");
		nativeQuery.append(" (CASE WHEN ?4 = ?5 THEN dbo.diasHabilesEntreDosFechas(a.hora_inicio, GETDATE()) else ");
		nativeQuery.append("  DATEDIFF ( dd , a.hora_inicio , GETDATE()) END ) AS td ");
		nativeQuery.append(" from caso c ");
		nativeQuery.append(" inner join AUDIENCIA a on a.id_caso=c.id_caso ");
		nativeQuery.append(" inner join servicio s on c.id_servicio = s.id_servicio ");		
		nativeQuery.append(" inner join rol_persona_caso rpc on rpc.id_caso = c.id_caso ");		
		nativeQuery.append(" , centro ce  ");
		nativeQuery.append(" where ");
		nativeQuery.append(" s.tipo=?6 ");			
		nativeQuery.append(" and c.estado_caso not in (?7, ?8, ?9, ?10) ");
		nativeQuery.append(" and ce.id_centro = (case when c.id_servicio=?3 then (select co.id_centro from convenio co where co.id_convenio=c.id_convenio and co.estado_registro=?1) else ");
		nativeQuery.append(" (SELECT se.id_centro FROM CASO cas INNER JOIN SEDE se ON se.id_sede = cas.id_sede WHERE cas.id_caso = c.id_caso AND se.estado_registro = ?1 ) end ) ");
		nativeQuery.append(" and a.estado=?2 ");
		nativeQuery.append(" AND a.ESTADO_REGISTRO=?1 ");
		nativeQuery.append(" and cast(a.hora_inicio as date) < cast(getdate() as date) ");
		nativeQuery.append(" and rpc.id_persona = p.id_persona ");
		nativeQuery.append(" AND rpc.id_rol = ?11 ");
		nativeQuery.append(" AND rpc.estado = ?12 ");
		nativeQuery.append(" AND rpc.estado_registro = ?1 ");
		nativeQuery.append(" FOR xml RAW ('tr'), ELEMENTS) + @TablaCierre AS tabla  ");
		nativeQuery.append(" FROM PERSONA p ) as t ");
		nativeQuery.append(" WHERE t.tabla IS NOT NULL)  ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(13, tablaCierre);
		query.setParameter(14, tablaEncabezado);
		query.setParameter(15, tablaTextoEncabezado);
		
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		query.setParameter(3, UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA);
		query.setParameter(4, metodoNombramiento);
		query.setParameter(5, UtilDominios.TIPO_PERIODICIDAD_HABIL);
		query.setParameter(6, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(7, UtilDominios.ESTADO_CASO_REGISTRADO);
		query.setParameter(8, UtilDominios.ESTADO_CASO_CERRADO);
		query.setParameter(9, UtilDominios.ESTADO_CASO_FALTA_DE_COMPETENCIA);
		query.setParameter(10, UtilDominios.ESTADO_CASO_CANCELADO);
		query.setParameter(11, UtilConstantes.ID_ROL_CONCILIADOR);
		query.setParameter(12, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		
		
		
		return query.getResultList();
	}
	
	/** Alerta Vencimiento para programar designación, VENDESG
     * Consulta los casos que llevan un numero de dias de radicado y no se les ha programado audiencia
     * @param resultadoAudiencia
     * @param tipoPeriodicidad
     * @param dias
     * @return List<InfoBasicaAlertasDTO>
     */
    public List<InfoBasicaAlertasDTO> alertaVencimientoProgramarDesignacion( String tipoPeriodicidad, Long dias ){

		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append(" select t.idCaso, t.nombreCaso, t.fechaRadicacionCaso, t.idCentro ");
		nativeQuery.append(" from ( ");
		nativeQuery.append(" select ca.id_caso as idCaso, ");
		nativeQuery.append(" ca.nombre as nombreCaso, ");
		nativeQuery.append(" ca.fecha_radicacion as fechaRadicacionCaso, ");
		nativeQuery.append(" se.id_centro as idCentro, ");
		
		if (UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad)) {
			nativeQuery.append(" dbo.diasHabilesEntreDosFechas(ca.fecha_radicacion, GETDATE()) as diasRadicacionCaso ");
		} else {
			nativeQuery.append(" DATEDIFF(dd, ca.fecha_radicacion, GETDATE()) as diasRadicacionCaso ");
		}
		
		nativeQuery.append(" from caso ca ");
		nativeQuery.append(" inner join sede se ");
		nativeQuery.append(" on se.id_sede = ca.id_sede ");
		nativeQuery.append(" inner join servicio ser ");
		nativeQuery.append(" on ser.id_servicio = ca.id_servicio ");
		nativeQuery.append(" left join audiencia a ");
		nativeQuery.append(" on a.id_caso = ca.id_caso ");
		nativeQuery.append(" and a.tipo_audiencia in ( ?2 , ?3 ) ");
		nativeQuery.append(" and a.estado_registro = ?1 ");
		nativeQuery.append(" where a.id_caso is null ");
		nativeQuery.append(" and ser.tipo = ?5 ");
		nativeQuery.append(" and ca.estado_caso not in ( ?6 , ?7, ?8, ?9, ?10, ?11 ) ");
		nativeQuery.append(" and ca.estado_registro = ?1 ");
		nativeQuery.append(" and se.estado_registro = ?1 ");
		nativeQuery.append(" and ser.estado_registro = ?1 ");
		nativeQuery.append(" and not exists (select 1 from rol_persona_caso rpc  ");
		nativeQuery.append(" inner join PARAMETRO_SERVICIO_SORTEO pss ");
		nativeQuery.append(" on pss.id_servicio = ca.id_servicio ");
		nativeQuery.append(" and pss.id_rol = rpc.id_rol ");
		nativeQuery.append(" where rpc.id_caso = ca.id_caso) ");
		nativeQuery.append(" and not exists (select 1 from audiencia au ");
		nativeQuery.append(" where au.id_caso = ca.id_caso and au.tipo_audiencia not in  (?12, ?13)) ");
		nativeQuery.append(" ) t ");
		nativeQuery.append(" where t.diasRadicacionCaso >= ?4 ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.TIPO_AUDIENCIA_PREARBITRAL_DESIGNACION_POR_PARTES);
		query.setParameter(3, UtilDominios.TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_DE_DESIGNACION);
		query.setParameter(4, dias);
		query.setParameter(5, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		query.setParameter(6, UtilDominios.ESTADO_CASO_CERRADO);
		query.setParameter(7, UtilDominios.ESTADO_CASO_CANCELADO);
		query.setParameter(8, UtilDominios.ESTADO_CASO_SUSPENDIDO);
		query.setParameter(9, UtilDominios.ESTADO_CASO_EN_CREACION);
		query.setParameter(10, UtilDominios.ESTADO_CASO_SUSPENDIDO_POR_REQUERIMIENTO);
		query.setParameter(11, UtilDominios.ESTADO_CASO_SUSPENDIDO_POR_REQUERIMIENTO);
		query.setParameter(12, UtilDominios.TIPO_AUDIENCIA_AUDIENCIA_DE_INSTALACION);
		query.setParameter(13, UtilDominios.TIPO_AUDIENCIA_PREARBITRAL_APERTURA);

		return query.getResultList();
	}
    
    /** alerta A-16, Registrar resultado de audiencia, REGRAUD
     * @param tipoPeriodicidad, si cuenta dias habiles/calendario
     * @param dias: dias que se va a validar sin registrar el resultado
     * @return List<InfoBasicaAlertasDTO>, lista de casos con su respectivo centro
     */
	public List<InfoBasicaAlertasDTO> alertaRegistrarResultadoAudiencia( String tipoPeriodicidad, Long dias ){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select d.nombre as tipoAudiencia, ");
		nativeQuery.append(" a.hora_fin as fechaAudiencia, ");
		nativeQuery.append(" ca.id_caso as idCaso, ");
		nativeQuery.append(" ca.nombre as nombreCaso, ");
		nativeQuery.append(" se.id_centro as idCentro ");
		nativeQuery.append(" from caso ca ");
		nativeQuery.append(" inner join sede se ");
		nativeQuery.append(" on se.id_sede = ca.id_sede ");
		nativeQuery.append(" inner join servicio ser ");
		nativeQuery.append(" on ser.id_servicio = ca.id_servicio ");
		nativeQuery.append(" inner join audiencia a ");
		nativeQuery.append(" on a.id_caso = ca.id_caso ");
		nativeQuery.append(" inner join dominio d ");
		nativeQuery.append(" on d.codigo = a.tipo_audiencia ");
		nativeQuery.append(" where d.codigo_dom_padre = ?2 ");
		nativeQuery.append(" and a.estado = ?3 ");
		if (UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad))
			nativeQuery.append(" and dbo.diasHabilesEntreDosFechas( a.hora_fin, getdate() ) >= ?4 ");
		else
			nativeQuery.append(" and DATEDIFF( dd, a.hora_fin, GETDATE() ) >= ?4 ");
		nativeQuery.append(" and ser.tipo in (?5, ?6) ");
		nativeQuery.append(" and ca.estado_registro = ?1 ");
		nativeQuery.append(" and se.estado_registro = ?1 ");
		nativeQuery.append(" and ser.estado_registro = ?1 ");
		nativeQuery.append(" and a.estado_registro = ?1 ");

		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.AGRUPADOR_TIPO_AUDIENCIA_ARBITRAL);
		query.setParameter(3, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		query.setParameter(4, dias);
		query.setParameter(5, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		query.setParameter(6, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);

		return query.getResultList();
	}
	
	 /** alerta A-7, Fijación Audiencia de Instalación
     * @param tipoPeriodicidad, dias habiles/calendario
     * @param dias: dias que se va a validar sin registrar el resultado
     * @return List<InfoBasicaAlertasDTO>, lista de casos sin audiencia de instalación y con todos los arbitros aceptados
     */
	public List<InfoBasicaAlertasDTO> alertaFijacionAudienciaInstalacion( String tipoPeriodicidad, Long dias ){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" with consulta as ");
		nativeQuery.append(" (select c.id_caso as idCaso, c.nombre as nombreCaso, c.fecha_radicacion as fechaRadicacion,  ");
		nativeQuery.append(" c.cant_funcionarios_principales, sed.id_centro as idCentro ,  ");
		if (UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad)){
			nativeQuery.append("  dbo.diasHabilesEntreDosFechas(  ");	
		}
		else{
			nativeQuery.append(" datediff(dd,  ");
		}
		
		nativeQuery.append(" (select top 1 erpc.fecha_de_asignacion from EVENTO_ROL_PERSONA_CASO erpc  ");
		nativeQuery.append(" where erpc.id_rol in (select pss.id_rol from parametro_servicio_sorteo pss where pss.estado_registro=?1)   ");
		nativeQuery.append(" AND c.id_caso = erpc.id_caso ");
		nativeQuery.append(" and erpc.estado_asignado = ?2 ");
		nativeQuery.append(" order by erpc.fecha_de_asignacion desc) , GETDATE()) as fechaEventoArbitro ");
		nativeQuery.append(" from caso c  ");
		nativeQuery.append(" left join AUDIENCIA aud on aud.id_caso=c.id_caso and aud.tipo_audiencia=?3 and aud.estado_registro=?1 ");
		nativeQuery.append(" inner join servicio s on s.id_servicio=c.id_servicio ");
		nativeQuery.append(" inner join sede sed on sed.id_sede=c.id_sede ");
		nativeQuery.append(" where c.id_servicio<>?4 ");
		nativeQuery.append(" and aud.id_caso is null  ");
		nativeQuery.append(" and s.tipo=?5 ");
		nativeQuery.append(" and c.etapa=?6 ");
		nativeQuery.append(" and s.estado_registro=?1 ");
		nativeQuery.append(" and c.estado_registro=?1 ");
		nativeQuery.append(" and (select count(*) from ROL_PERSONA_CASO rpc ");
		nativeQuery.append(" inner join persona p on p.id_persona=rpc.id_persona ");
		nativeQuery.append(" inner join rol_persona rp on rp.id_persona=rpc.id_persona and rp.id_rol=rpc.id_rol ");
		nativeQuery.append(" where rpc.id_caso=c.id_caso  ");
		nativeQuery.append(" and rpc.id_rol in (select pss.id_rol from parametro_servicio_sorteo pss where pss.estado_registro=?1)  ");
		nativeQuery.append(" AND rpc.tipo_nombramiento=?7 and rpc.estado=?2 ");
		nativeQuery.append(" and p.estado_registro=?1 ");
		nativeQuery.append(" and c.estado_caso not in ( ?9 ) ");
		nativeQuery.append(" and rp.estado_registro=?1) = c.cant_funcionarios_principales) ");
		nativeQuery.append(" select cca.idCaso as idCaso, cca.nombreCaso as nombreCaso, cca.fechaRadicacion as fechaRadicacionCaso, cca.idCentro as idCentro ");
		nativeQuery.append(" from consulta cca ");
		nativeQuery.append(" where cca.fechaEventoArbitro >=?8 ");
				
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(3, UtilDominios.TIPO_AUDIENCIA_AUDIENCIA_DE_INSTALACION);
		query.setParameter(4, UtilConstantes.ID_SERVICIO_ARBITRAJE_INTERNACIONAL);		
		query.setParameter(5, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		query.setParameter(6, UtilDominios.ETAPA_CASO_PREARBITRAL);	
		query.setParameter(7, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(8, dias);
		query.setParameter(9, UtilDominios.ESTADO_CASO_CERRADO);

		return query.getResultList();
	}
	
	/** Alerta A-19, Rendir cuentas RENDCUE
	 * @param tipoPeriodicidad, si cuenta dias habiles/calendario
	 * @param dias, numero de dias que debe validar el sistema
	 * @return List<InfoBasicaAlertasDTO>, lista de casos con su respectivo centro
	 */
	public List<InfoBasicaAlertasDTO> alertaRendircuentas( String tipoPeriodicidad, Long dias ){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select distinct ca.id_caso as idCaso, ");
		nativeQuery.append(" ca.nombre as nombreCaso, ");
		nativeQuery.append(" rpc.id_persona as idPersona, ");
		nativeQuery.append(" se.id_centro as idCentro ");
		nativeQuery.append(" from caso ca ");
		nativeQuery.append(" inner join sede se ");
		nativeQuery.append(" on se.id_sede = ca.id_sede ");
		nativeQuery.append(" inner join servicio ser ");
		nativeQuery.append(" on ser.id_servicio = ca.id_servicio ");
		nativeQuery.append(" inner join rol_persona_caso rpc ");
		nativeQuery.append(" on rpc.id_caso = ca.id_caso ");
		nativeQuery.append(" inner join audiencia a ");
		nativeQuery.append(" on a.id_caso = ca.id_caso ");
		nativeQuery.append(" left join gasto_tribunal gt ");
		nativeQuery.append(" on gt.id_caso = ca.id_caso ");
		nativeQuery.append(" and gt.estado_registro = ?1 ");
		nativeQuery.append(" where ser.tipo = ?2 ");
		nativeQuery.append(" and gt.id_caso is null ");
		nativeQuery.append(" and a.tipo_audiencia = ?3 ");
		nativeQuery.append(" and a.estado in ( ?4 , ?5 ) ");
		if (UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad))
			nativeQuery.append(" and dbo.diasHabilesEntreDosFechas( a.hora_fin, getdate() ) = ?6 ");
		else
			nativeQuery.append(" and DATEDIFF( dd, a.hora_fin, GETDATE() ) = ?6 ");
		nativeQuery.append(" and rpc.id_rol = ( select id_rol from rol where nombre = ?7 and estado_registro = ?1 ) ");
		nativeQuery.append(" and rpc.es_presidente = 1 ");
		nativeQuery.append(" and rpc.tipo_nombramiento = ?8 ");
		nativeQuery.append(" and rpc.estado in ( ?9 , ?10 ) ");
		nativeQuery.append(" and ca.estado_registro = ?1 ");
		nativeQuery.append(" and se.estado_registro = ?1 ");
		nativeQuery.append(" and ser.estado_registro = ?1 ");
		nativeQuery.append(" and a.estado_registro = ?1 ");
		nativeQuery.append(" and rpc.estado_registro = ?1 ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		query.setParameter(3, UtilDominios.TIPO_AUDIENCIA_ARBITRAL_LAUDO);
		query.setParameter(4, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		query.setParameter(5, UtilDominios.ESTADO_AUDIENCIA_REALIZADA);
		query.setParameter(6, dias);
		query.setParameter(7, UtilDominios.ROL_PERSONA_ARBITRO);
		query.setParameter(8, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(9, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		query.setParameter(10, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		
		return query.getResultList();
	}
	
	/** Alerta A-24, Alarma de Cierre de Audiencias
	 * @param tipoPeriodicidad, si cuenta dias habiles/calendario
	 * @param dias, numero de dias que debe validar el sistema
	 * @return List<InfoBasicaAlertasDTO>, lista de casos con su id y nombre
	 */
	public List<InfoBasicaAlertasDTO> alertaCierreDeAudiencias( String tipoPeriodicidad, Long dias ){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select c.id_caso as idCaso, c.nombre as nombreCaso,  aud.hora_inicio fechaAudiencia from CASO C ");
		nativeQuery.append(" inner join audiencia aud on c.id_caso=aud.id_caso ");
		nativeQuery.append(" INNER JOIN servicio s on s.id_servicio=c.id_servicio ");
		nativeQuery.append(" where s.tipo in (?1, ?10) ");
		nativeQuery.append(" and c.etapa=?2 ");
		nativeQuery.append(" and c.estado_caso not in (?3,?4) ");
		nativeQuery.append(" and c.estado_registro=?5 ");
		nativeQuery.append(" and aud.estado_registro=?5 ");
		nativeQuery.append(" and aud.estado= ?9 ");
		nativeQuery.append(" AND aud.tipo_audiencia in (select codigo from dominio where dominio=?6 and codigo_dom_padre=?7) ");
	
		if (UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad)){
			nativeQuery.append(" and dbo.diasHabilesEntreDosFechas(aud.hora_inicio, GETDATE())=ISNULL(?8,1) ");
		}			
		else{
			nativeQuery.append(" and datediff(dd,aud.hora_inicio,GETDATE())=ISNULL(?8,1) ");
		}
					
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		query.setParameter(2, UtilDominios.ETAPA_CASO_PREARBITRAL);
		query.setParameter(3, UtilDominios.ESTADO_CASO_CERRADO);
		query.setParameter(4, UtilDominios.ESTADO_CASO_CANCELADO);
		query.setParameter(5, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(6, UtilDominios.DOMINIO_TIPO_AUDIENCIA);
		query.setParameter(7, UtilDominios.AGRUPADOR_TIPO_AUDIENCIA_PREARBITRAL);
		query.setParameter(8, dias);
		query.setParameter(9, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		query.setParameter(10, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);
		
		return query.getResultList();
	}
	
	// protected region metodos adicionales end
        
	/**
	 * Metodo que permite la cantidad de arbitros designados a una audiencia.
	 * 
	 * @return List<String>: lista arbitros correspondiente a principales y suplentes respectivamente
	 */
	public List<Long> consultarCantFuncionariosPendientes(Long idCaso) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select cant_funcionarios_principales - (select count(*) ");
		nativeQuery.append(" from rol_persona_caso  rpc ");
		nativeQuery.append(" inner join caso c on rpc.id_caso = c.id_caso ");
		nativeQuery.append(" inner join PARAMETRO_SERVICIO_SORTEO pss ");
		nativeQuery.append(" on c.id_servicio = pss.id_servicio ");
		nativeQuery.append(" where rpc.id_caso = ?1 ");
		nativeQuery.append(" and rpc.id_rol = pss.id_rol and tipo_nombramiento = ?2 ");
		nativeQuery.append(" and metodo_nombramiento = ?3 and estado in (?4, ?5) ");
		nativeQuery.append(" and rpc.motivo_inactivacion is null ");
		nativeQuery.append(" and rpc.estado_registro = ?6) as cant_funcionarios_principales, ");
		nativeQuery.append(" cant_funcionarios_suplentes  - (select count(*) ");
		nativeQuery.append(" from rol_persona_caso rpc ");
		nativeQuery.append(" inner join caso c on rpc.id_caso = c.id_caso ");
		nativeQuery.append(" inner join PARAMETRO_SERVICIO_SORTEO pss ");
		nativeQuery.append(" on c.id_servicio = pss.id_servicio ");
		nativeQuery.append(" where rpc.id_caso=?1 ");
		nativeQuery.append(" and rpc.id_rol = pss.id_rol and tipo_nombramiento = ?8 ");
		nativeQuery.append(" and metodo_nombramiento = ?3 and estado = ?7 ");
		nativeQuery.append(" and rpc.motivo_inactivacion is null ");
		nativeQuery.append(" and rpc.estado_registro = ?6) as cant_funcionarios_suplentes ");
		nativeQuery.append(" from NOMBRAMIENTO_PERSONA n ");
		nativeQuery.append(" where n.id_caso=?1 ");
		nativeQuery.append(" and metodo_de_nombramiento = ?3 and n.estado_registro = ?6 ");
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString());
		query.setParameter(1, idCaso);
		query.setParameter(2, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(3, UtilDominios.METODO_NOMBRAMIENTO_SORTEO);
		query.setParameter(4, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		query.setParameter(5, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(6, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(7, UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO);
		query.setParameter(8, UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE);
		
		return query.getResultList();
	}
}

