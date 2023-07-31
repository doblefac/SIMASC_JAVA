package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import java.util.Date;
import java.util.List;
import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.transversal.dto.AudienciaDTO;
import com.ccb.simasc.transversal.dto.AudienciaSorteoDTO;
import com.ccb.simasc.transversal.dto.AudienciaTranscripcionesPendientesDTO;
import com.ccb.simasc.transversal.dto.CitacionDTO;
import com.ccb.simasc.transversal.dto.ConsultaAgendamientoDTO;
import com.ccb.simasc.transversal.dto.RealizacionAudienciaDTO;
import com.ccb.simasc.transversal.dto.formularios.AudienciasProgramadasDTO;
import com.ccb.simasc.transversal.dto.formularios.DatosResultadoAudienciaDTO;
import com.ccb.simasc.transversal.dto.formularios.ProgramacionAudienciaDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasosSorteadosDTO;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.excepciones.EstadosCasoException;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascNegocioPruebaException;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Audiencia}
 * @author sMartinez
 *
 */
@Local
public interface IAudienciaFacade extends IAccesoFacade<Audiencia, Long, AudienciaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	/**
	 * Consulta todas las audiencias del caso enviado
	 * @param idCaso codigo del caso
	 * @return lista de audiencias para el caso
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<Audiencia> obtenerAudienciasCaso(Long idCaso);
	
	
	/**
	 * Genera el evento cuando se actualiza el estado de una audiencia
	 * @param audiencia
	 * @throws SIMASCNegocioExcepcion
	 */
	public void crearEventoRutaCaso(Audiencia audiencia);

	/**
	 * 
	 * @param idCaso
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<AudienciaDTO> obtenerAudienciasCasoDTO(AudienciaDTO filtroAudiencia);
	
	/**
	 * Consulta las audiencias por filtros y retorna los datos del DTO
	 * @param filtroAudiencias
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<AudienciaDTO> obtenerAudienciasFiltros(AudienciaDTO filtroAudiencias, ContextoDeSesion cs);

	/**
	 * Consulta la ultima audiencia registrada pendiente por realizar
	 * @param Long idCaso
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public AudienciaDTO obtenerAudienciaFiltros(Long idCaso);

	/**
	 * Registra el resultado de la audiencia
	 * @param resultadoDTO
	 * @return
	 */
	public void registrarResultadoAudiencia(DatosResultadoAudienciaDTO resultadoDTO);
	
	
	/**
	  * Consulta si el caso tiene alguna audiencia en estado pendiente, de ser
	  * asi retorna true, en caso contrario retorna false
	  * @param casoId codigo del caso a consultar
	  * @return true si tiene audiencia o audiencias en estado pendiente
	  * @throws SIMASCNegocioExcepcion
	  */
	 public boolean tieneAudienciasPendientes(Long casoId);
	 
	 /***
	  * Para programar una audiencia debe pasar una serie de validaciones.
	  * @throws SIMASCNegocioExcepcion
	  */
	 public void validacionProgramarAudiencia(Audiencia audiencia);
	 
	 /**
	  * Si es una audiencia de Laudo, valida si existen audiencias de Alegatos y pruebas, y
	  * si estas audiencias ya han sido realizadas.
	  * @param audiencia La audiencia que se va a registrar
	  * @return true si la audiencia de Laudo pasó la verificación
	  * @throws SIMASCNegocioExcepcion
	  */
	 public boolean validacionAudienciaLaudo(Audiencia audiencia) throws SIMASCNegocioExcepcion;
	 
	 /**
	  * Se valida el tipo de audiencia que se va a registrar junto a sus respectivas restricciones
	  * @param audiencia La audiencia que se va a registrar
	  * @throws SIMASCNegocioExcepcion
	  */
	 public void validarTipoAudiencia(Audiencia audiencia) throws SIMASCNegocioExcepcion;
	 
	 /**
	  * Realiza la programacion de una audiencia
	  * @param audiencia
	  * @return retorna el codigo de la audiencia generada
	  * @throws SIMASCNegocioExcepcion
	  */
	 public Long programarAudiencia(Audiencia audiencia,Boolean notificar);

	/**
	 * 
	 * @param idAudiencia
	 * @return
	 */
	public Audiencia consultarDatosBasicoAudiencia(Long idAudiencia);
	
	/**
	 * 
	 * 
	 * @param idPersona
	 * @return
	 */
	public List<AudienciaTranscripcionesPendientesDTO> consultarAudienciasTranscripcionesPendientes(Long idPersona,
			Long idRol); 

	/**
	 * Actualiza el estado de una audiencia
	 * @param audiencia
	 */
	public void actualizarAudiencia(Audiencia audiencia);
	
	/**
	 * Retorna las audiencias de sorteo publico designacion pendientes por ejecutar
	 * @param audiencia
	 */
	public List<AudienciaSorteoDTO> consultarAudienciasSorteoPendientes();
	
	/**
	 * Consulta las audiencias de un caso dado según su estado
	 * 
	 * @param idCaso
	 * @param estado
	 * @return
	 */
	public List<Audiencia> obtenerAudienciasCasoPorTipoYEstado(Long idCaso, String tipoAudiencia, String estado);
	
	/**
	 * Envia notificacion por correo de resultado de audiencia de etapa arbitral
	 * 
	 * @param idCaso
	 * @param idAudiencia
	 * @param idPersona
	 * @throws SIMASCNegocioExcepcion
	 */
	public void enviarNotificacionResultadoAudiencia(Long idCaso, Long idAudiencia, Long idPersona, Long idRol);
	
	/**
	 * Metodo encargado de obtener el rango entre fechas (Fecha Inicial - Fecha
	 * Final) para la programacion de una audiencia.
	 * 
	 * CON-F-124
	 * 
	 * @param fechaRadicacionCaso
	 * @return ProgramacionAudienciaDTO
	 * @throws SIMASCNegocioExcepcion
	 */
	public ProgramacionAudienciaDTO fechasProgramacionAudiencia(Long idCaso) 
			throws SIMASCNegocioExcepcion;


	/**
	 * metodo que retorna true si existio una citacion de audiencia para el caso
	 * @param idCaso
	 * @return
	 */
	public boolean existeCitacionAudiencia(Long idCaso);


	/**
	 * Metodo que retorna tre si se realizo una audiencia para el caso 
	 * @param idCaso
	 * @return
	 */
	public boolean existeRealiazacionAudiencia(Long idCaso);
	
	/**
	 * Método para obtener las audiencias programadas para una jornada
	 * @param idJornada
	 * @return
	 */
	public List<AudienciaDTO> consultarAudienciasPendientesPorJornada(Long idJornada);

	/**
	 * Metodo que consulta el numero de audiencias que se le han hecho a un caso
	 * y se le puede especificar por parametro que audiencias contar depediendo del estado
	 * @param idCaso
	 * @param filtroEstados
	 * @return
	 */
	public Long obtenerNumeroAudienciasCaso(Long idCaso, List<String> filtroEstados);
	
	
	/**
	 * Metodo que arma CitacionDTO a partir de consulta de partes (nombres, apellidos y correos electronicos),
	 * número de audiencia e información relacionada con la audiencia pendiente (fecha, hora y sede) 
	 * @param idCaso
	 * @return
	 */
	public  CitacionDTO consultarInformacionCitacion(Long idCaso);
	
	/**
	 * Método que realiza la programacion de audiencias de una jornada
	 * @param idConvenio
	 * @return
	 */
	public boolean programarAudiencias(Long idConvenio);
	
	/**
	 * Metodo que consulta la informacion de una audiencia pendiente
	 * @param idCaso
	 * @return CitacionDTO
	 */
	public List<CitacionDTO> consultarInformacionAudienciaPendiente(Long idCaso);
	
	/**
	 * Metodo que consulta el resultado y fecha de la ultima audiencia en estado realizado
	 * @param idCaso
	 * @return AudienciaDTO
	 */
	public AudienciaDTO consultarUltimaAudienciaRealizada(Long idCaso);
	
	/** CON-F-106
	 * Programa la audiencia de conciliacion
	 * @param consultaAgendamiento
	 * @author prendon
	 */
	public void programarAudienciaConciliacion(ConsultaAgendamientoDTO audienciaPorProgramar) throws SimascNegocioPruebaException;
	
	/**
	 * Metodo para revertir resultado de una audiencia
	 * @param audiencia
	 * @param usuario
	 * @return void()
	 */
	public void revertirResultadoAudiencia(AudienciaDTO audiencia, String usuario) throws EstadosCasoException;
	
	/**
	 * Método para obtener la informacion de las audiencias programadas a las que se encuentra asociado el conciliador para la sede y la fecha
	 * @param idSede
	 * @param fechaAudiencia
	 * @param idPersona
	 * @return
	 */
	public List<AudienciasProgramadasDTO> consultarAudienciasProgramadasConciliador(Long idSede, Date fechaAudiencia, Long idPersona);
	
	
	/**
	 * Metodo que permite registrar la audiencia realizada.
	 * 
	 * @author aperez.
	 * @param realizacionAudienciaDTO:
	 *            Dto con informacion de la audiencia.
	 * @param usuarioModificacion:usuario
	 *            que realiza la modificacion en registrar Audiencia realizada.
	 */
	public void registrarAudienciaRealizada(RealizacionAudienciaDTO realizacionAudienciaDTO,
			String usuarioModificacion);


	public List<Long> obtenerCantidadArbitrosDisponibles(Long idCaso);


	public List<ReporteCasosSorteadosDTO> getReporteCasosSorteados(Date fechaInicial,Date fechaFinal, Long tipoCaso);


	public void eliminarAudiencia(Audiencia audiencia);
	// protected region metodos adicionales end

}
