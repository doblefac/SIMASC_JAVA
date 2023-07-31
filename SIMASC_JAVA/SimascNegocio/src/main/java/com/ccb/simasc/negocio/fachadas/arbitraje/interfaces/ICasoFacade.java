package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin

// Escriba en esta sección sus modificaciones

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.transversal.dto.CasoDTO;
import com.ccb.simasc.transversal.dto.CasosControlLegalidadDTO;
import com.ccb.simasc.transversal.dto.CasosNoAsignadosDTO;
import com.ccb.simasc.transversal.dto.CasosSinCerrarDTO;
import com.ccb.simasc.transversal.dto.CierreConciliacionDTO;
import com.ccb.simasc.transversal.dto.EventoDTO;
import com.ccb.simasc.transversal.dto.InformacionCasoDTO;
import com.ccb.simasc.transversal.dto.PagoCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.CapturaResultadoJornadaDTO;
import com.ccb.simasc.transversal.dto.formularios.CasoAsignadoDTO;
import com.ccb.simasc.transversal.dto.formularios.CasoCerradoDTO;
import com.ccb.simasc.transversal.dto.formularios.CasoIncompletoDTO;
import com.ccb.simasc.transversal.dto.formularios.DatosBasicosCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.DatosReversarResultadoDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltroCasosAsignadosDTO;
import com.ccb.simasc.transversal.dto.formularios.RadicarCasoConvenioDTO;
import com.ccb.simasc.transversal.dto.formularios.SuspenderDTO;
import com.ccb.simasc.transversal.dto.reportes.CasosCobradosDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.PagoCaso;
import com.ccb.simasc.transversal.excepciones.EstadosCasoException;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;

// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Caso}
 * 
 * @author sMartinez
 *
 */
@Local
public interface ICasoFacade extends IAccesoFacade<Caso, Long, CasoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	public DatosBasicosCasoDTO obtenerDatosBasicosCaso(Long id);

	public void editarDatosBasicos(DatosBasicosCasoDTO dto) throws Exception;

	public boolean validarValorPretenciones(BigDecimal valor, String tipoCuantia);

	public PagoCaso crearCaso(Caso caso, PagoCaso pago);

	public Collection<Caso> obtenerEntidadesPatron(String patron, List<Caso> arrayList, boolean dependencias);

	public Object validarPactoRegistrado(Long id);

	/**
	 * Listar casos asignados Consulta los casos asignados al funcionario que se
	 * especifica en el DTO y aplicando los filtros que se pasan como parametro.
	 * 
	 * @param filtros
	 * @param rol
	 *            del usuario que está realizando la consulta
	 * @return
	 */
	 public List<CasoAsignadoDTO> consultarCasosAsignados(FiltroCasosAsignadosDTO filtros, String idRol);
	 public List<CasoIncompletoDTO> consultarCasosIncompletos();
	 public List<CasoDTO> obtenerEntidadesPorEtapa(String etapa);
	 
	/**
	 * Listar casos asignados Consulta los casos asignados al funcionario que se
	 * especifica en el DTO y aplicando los filtros que se pasan como parametro.
	 * Este metodo debe ser llamado cuando la persona tiene el rol de
	 * digitalizador.
	 *
	 * @param filtros
	 * @return List<CasoAsignadoDTO>
	 */
	 public List<CasoAsignadoDTO> consultarCasosAsignadosDigitalizador(FiltroCasosAsignadosDTO filtros);
	 public void crearEventoRutaCaso(Caso caso, EventoDTO eventoDTO);
	 public void actualizarCasoDocumentos(Caso caso);
	 
	 /**
	  * Metodo que suspende un caso y genera el evento de "suspension" o "en requerimiento"
	  * 
	  * @param suspenderDTO
	  */
	 public void adicionarSuspension(SuspenderDTO suspenderDTO); 

	/**
	 * Modifica el tramite en la etapa pre-arbitral
	 * 
	 * @param suspenderDTO
	 * @throws SIMASCNegocioExcepcion
	 */
	 public void modificarSuspension(SuspenderDTO suspenderDTO);
	
	/**
	 * Permite realizar el cierre de un caso. Se envia el contexto de sesion
	 * para enviar los correos correspondientes dependiendo del usuario en
	 * sesion
	 * 
	 * @param casoCerradoDTO
	 * @param cs
	 * @throws SIMASCNegocioExcepcion
	 */
	public void cerrarCaso(CasoCerradoDTO casoCerradoDTO, ContextoDeSesion cs);

	/**
	 * Consulta el nombre de un caso
	 * 
	 * @param idCASO
	 */
	public String obtenerNombreCaso(Long idCaso);

	public void actualizarTerminos(DatosBasicosCasoDTO datosBasicos);

	/**
	 * 
	 * @param caso
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public Caso actualizarCaso(Caso caso, String usuarioMoficacion);
	
	/**
	  * Actualiza la etapa del caso cuando éste es de tipo arbitraje internacional
	  * y todas las partes han sido notificadas
	  * @param caso
	  * @param etapa caso
	  * @return 
	  * @throws SIMASCNegocioExcepcion
	  */
	public CasoDTO actualizarEtapaCaso(Long idCaso, String etapaCaso, Long plantillaCarta, String usuarioMoficacion) throws SIMASCNegocioExcepcion;

	/**
	 * 
	 * @param idCaso
	 * @return
	 */
	public String generarNombreCaso(Long idCaso);

	/**
	 * Metodo que adiciona los documentos no recibidos durante la radicacion del
	 * caso
	 * 
	 * @param caso
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public String agregarDocumentosNoRecibidos(CasoDTO caso);

	/**
	 * resila las suspensiones del caso y modifica su estado
	 * 
	 * @param IdCaso
	 * @throws SIMASCNegocioExcepcion
	 */
	public boolean actualizarEstdoSuspension(Long IdCaso);

	/**
	 * Desactiva un caso que quedo pendiente por radicar
	 * 
	 * @param caso
	 * @throws SIMASCNegocioExcepcion
	 */
	public void inactivarCasoRadicacion(Caso caso);

	/**
	 * se actualiza el estado de suspension de todos los casos que tengan algun
	 * registro en la tabla suspension.
	 * 
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public boolean actualizarCasosEstdoSuspension();

	/**
	 * Metodo que pone al caso seleccionado en NoAcreditado
	 * 
	 * @param idcaso
	 * @return
	 */
	public void pactoNoAcreditado(Long idCaso, String usuarioMoficacion);

	/***
	 * Metodo encargado de cambiar el estado de de un caso
	 * 
	 * @param idCaso
	 * @param estadoCaso
	 * @throws SIMASCNegocioExcepcion
	 */
	public void modificarEstadoCaso(Long idCaso, String estadoCaso);

	/**
	 * crea el tramite de un caso en conciliacion
	 * 
	 * @param idOrdenPago
	 * @param idUsuario
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public Caso creacionCasoTramiteOrdinario(Long idOrdenPago, String idUsuario, PagoCasoDTO pagoCasoDTO, String realPath);

	/**
	 * Metodo transversal encargado de pasar de un estado inicial a un estado
	 * final pasando por las validaciones del motor de estados
	 * 
	 * @param idCaso
	 *            id del casos el cual va a cambiar el estado
	 * @param nuevoEstado
	 *            codigo del dominio del estado final
	 * @param fechaEvento
	 *            fecha donde se va a generar el evento, si es nula se toma la
	 *            fecha actual
	 * @param tipoEvento
	 *            tipo del evento a generar por el cambio de estado
	 * @param observaciones
	 *            observaciones para el evento
	 * @throws EstadosCasoException
	 *             excepcion generada en el caso que no sea permitido realizar
	 *             el cambio de estado
	 */
	public void cambiarEstadoCaso(Long idCaso, String nuevoEstado, Date fechaEvento, String tipoEvento,
			String observaciones) throws EstadosCasoException;

	/**
	 * Realiza el cierre del caso cuando no hay competencia
	 * 
	 * @param idCaso
	 * @param idPersona
	 * @param motivo
	 * @param observaciones
	 * @throws EstadosCasoException
	 */
	public void cerrarCasoConciliacionNoCompetencia(Long idCaso, String observaciones, Boolean banderaEquidad, String mensaje) throws EstadosCasoException;

	/**
	 * Metodo que genera un caso
	 * 
	 * @param caso
	 * @return
	 */
	public Caso crearCaso(Caso caso);

	/**
	 * Metodo que crea un caso convenio
	 * 
	 * @param radicarCasoConvenio
	 * @param idUsuario
	 * @param rolPrincipal
	 * @return
	 */
	public Caso crearCasoConvenio(RadicarCasoConvenioDTO radicarCasoConvenio, String idUsuario, String rolPrincipal,
			String nombreUsuario);

	/**
	 * Método que realiza la creacion de los casos y las partes a partir de la
	 * informacion que viene en el documento
	 * 
	 * @param documento
	 * @param idConvenio
	 * @param idServicio
	 * @param numeroCasos
	 * @param nombre
	 */
	public void cargueCasosDesdeArchivo(InputStream documento, Long idConvenio, Long idServicio, Long numeroCasos,
			String nombre) throws EstadosCasoException;

	/**
	 * genera el cierre del caso de conciliacion.
	 * 
	 * @param cierreConciliacion
	 * @return mesaje del ministerio
	 * @throws EstadosCasoException
	 */
	public void cerrarCasoConciliacion(CierreConciliacionDTO cierreConciliacion) throws EstadosCasoException;

	/**
	 * Obtiene un caso dado su id y el tipo de servicio
	 * 
	 * @param idCaso
	 * @param tipoServicio
	 * @return
	 */
	public Caso obtenerCasoPorIdTipoServicio(Long idCaso, String tipoServicio);

	/**
	 * consulta los casos pendientes que no han sido asignados a conciliador.
	 * 
	 * @author aperez.
	 * 
	 * @return List<CasosNoAsignadosDTO>: Lista de los casos no asignados.
	 */
	public List<CasosNoAsignadosDTO> consultarCasosPendientesPorReparto(List<Long> idCentros);

	/**
	 * Obtiene la informacion del caso para la modificacion de parametros para
	 * reparto del caso (CONF-103).
	 * 
	 * @author aperez.
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 * @return InformacionCasoDTO: DTO que contiene informacion del caso.
	 */
	public InformacionCasoDTO obtenerInformacionCasoPorIdCaso(Long idCaso);

	/**
	 * Marca los casos que ya fueron cobrados por un conciliador
	 * 
	 * CON-C-028
	 * 
	 * @param fechaCobro
	 * @param casosAMarcar
	 * @param idUsuario
	 */
	public void marcarCasosCobrados(String fechaCobro, List<CasosCobradosDTO> casosAMarcar, Long idUsuario);

	/**
	 * Método para consultar los casos asociados a un convenio que no cuente con
	 * audiencia
	 * 
	 * @param idConvenio
	 * @return
	 */
	public List<CasoDTO> consultarCasosConvenioAudienciaPendiente(Long idConvenio);

	/**
	 * Método que registra el resultado de un caso de jornada
	 * 
	 * @param resultado
	 */
	public void registrarResultadoJornada(CapturaResultadoJornadaDTO resultado) throws EstadosCasoException;

	/**
	 * Método que realiza el cierre del caso de jornada
	 * 
	 * @param idCaso
	 * @param resultado
	 * @throws EstadosCasoException
	 */
	public void cerrarCasoJornada(Long idCaso, String resultado, Long idArea, Long idAsunto, Long idClasificacion) throws EstadosCasoException;

	/**
	 * Método que invoca al servicio de ministerio y se encarga del guardado de
	 * los documentos devueltos por el servicio
	 * 
	 * @param idCaso
	 * @return
	 */
	public String agregarCasoMinisterioConciliacion(Long idCaso, String idUsuario) throws EstadosCasoException;

	/**
	 * Consulta la lista de casos con sus dependencias de pagos y facturacion
	 * 
	 * @param idCaso
	 * @return
	 */
	public Caso consultarCasosConPagos(Long idCaso);

	/**
	 * Método para obtener los datos del caso del cual se reversará el resultado
	 * 
	 * @param idCaso
	 * @return
	 */
	public List<DatosReversarResultadoDTO> consultarDatoCasoReversarJornada(Long idCaso);

	/**
	 * Método que realiza el proceso de reversar resultado para un caso de
	 * jornada
	 * 
	 * @param idCaso
	 */
	public void reversarResultadoCasoJornada(Long idCaso);

	/**
	 * 7-02-2018 pRendon Consulta un caso por su id y que este activo el estado
	 * del registro
	 * 
	 * @param idCaso
	 * @return CasoDTO
	 */
	public CasoDTO consultarCasoActivo(Long idCaso);

	/**
	 * CON-F-076 Consulta el caso con dependencias de convenio.
	 * 
	 * @author LRUIZ
	 * @param idCaso
	 * @return
	 */
	public Caso consultarCasoConConvenio(Long idCaso);

	/**
	 * Método para consultar casos sin cerrar asignados al conciliador CON-F-102
	 * 
	 * @param idCaso
	 * @param idConciliador
	 */
	public List<CasosSinCerrarDTO> consultarCasosSinCerrarConciliador(Long idConciliador);

	/**
	 * Metodo que actuliza el caso y la facturacion de conciliacion
	 * 
	 * @param caso
	 * @param factura
	 */
	public void actualizarCasoConciliacion(Caso caso, Boolean factura);

	/**
	 * Metodo que permite realizar la reapertura del caso.
	 * 
	 * @param idCaso:
	 *            Indentificador del caso.
	 * @param observaciones:
	 *            Observaciones de la reapertura del caso.
	 */
	public void reabrirCaso(Long idCaso, String observaciones);

	/**
	 * Metodo que permite consultar los casos para control de legalidad.
	 * 
	 * @param reasignarAnalista:
	 *            parametro que indica si la consulta es para reasignarAnalista
	 *            (CONF111), de lo contrario la consulta pertenece CONF126.
	 * @return List<CasosControlLegalidadDTO>
	 */
	public List<CasosControlLegalidadDTO> consultarCasosParaControlLegalidad( boolean reasignarAnalista, String rol, Long idPersona);

	/**
	 * Metodo que llama a cerrar caso y liquidar devolucion de dinero Genera
	 * carta de cierre por no competencia y carta de devolución de dinero
	 * 
	 * @return void
	 */
	public void digitarInformacionConstanciaNoCompetencia(Long idCaso, String observaciones,
			String idPersonaModificacion, Long idPersona) throws EstadosCasoException;
	
	/**
	 * Metodo que permite actualizar el resultado del caso.
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 * @param resultadoCaso:
	 *            Resultado general del caso.
	 * @param usuarioModificacion:
	 *            persona en sesion que realiza la modificacion.
	 */
	public void actualizarResultadoCaso(Long idCaso, String resultadoCaso, String usuarioModificacion);

	/**
	 * 
	 * @param idCaso
	 * @return
	 */
	public Boolean validarNombramientoArbitros(Long idCaso);
	
	public List<Caso> obtenerCasosActivosArbitro (Long idPersona, Long idMateria);
	
	public Boolean validaCuantiaMayor(String valorPrentenciones);
	// protected region metodos adicionales end

	public void realizaReaperturaCaso(DatosBasicosCasoDTO datosBasicosCasoDTO, String usuarioModificacion) throws Exception;
	
	public Boolean habilitaCambioEtapa(Long idCaso);

	public void cambiaEstadoPronunciamientoCasoInsolvencia(String tipoPronunciamiento, Long idCaso, Long idPersona);
	
	//public void nuevoRepartoEquidad(String tipoPronunciamiento, Long idCaso, Long idPersona,Long idServicio);
	
}
