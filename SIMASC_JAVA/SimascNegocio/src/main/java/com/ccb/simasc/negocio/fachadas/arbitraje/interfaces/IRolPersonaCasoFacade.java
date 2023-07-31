package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin

// Escriba en esta sección sus modificaciones

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.CambioConciliadorDTO;
import com.ccb.simasc.transversal.dto.CambioEstadoSuplenteDTO;
import com.ccb.simasc.transversal.dto.CasosAsignadosDTO;
import com.ccb.simasc.transversal.dto.PartesSeguimientoDTO;
import com.ccb.simasc.transversal.dto.PendientePronunciamientoDTO;
import com.ccb.simasc.transversal.dto.PersonaCasoListDTO;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.dto.RolPersonaCasoDTO;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.dto.cartas.LotesCartasDTO;
import com.ccb.simasc.transversal.dto.formularios.CambioEstadoRPC_DTO;
import com.ccb.simasc.transversal.dto.formularios.CasoAsignadoDTO;
import com.ccb.simasc.transversal.dto.formularios.DetalleArbitroDTO;
import com.ccb.simasc.transversal.dto.formularios.LotesCartasFiltrosDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.dto.formularios.RepartoDTO;
import com.ccb.simasc.transversal.dto.formularios.RolPersonaCasoDesignacionDTO;
import com.ccb.simasc.transversal.dto.formularios.VinculacionPersonaCasoDTO;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Pronunciamiento;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.RolPersonaCasoPK;
import com.ccb.simasc.transversal.excepciones.EstadosCasoException;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;

// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link RolPersonaCaso}
 * 
 * @author sMartinez
 *
 */
@Local
public interface IRolPersonaCasoFacade extends IAccesoFacade<RolPersonaCaso, RolPersonaCasoPK, RolPersonaCasoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	public void eliminarRolPersonasCaso(List<Persona> persona, Long idCaso, String rolPersona);

	public void eliminarArbitrosCaso(Long idCaso, String rolPersona);

	/**
	 * * Se elimina logicamente el registro de rol persona caso recibiendo como
	 * paramteros el id del caso, id de la persona y el rol.
	 * 
	 * @param idCaso
	 * @param idPersona
	 * @param rolPersona
	 * @throws SIMASCNegocioExcepcion
	 */
	public void eliminarRolPersonasCasoPorId(Long idCaso, Long idPersona, String rolPersona)
			throws SIMASCNegocioExcepcion;

	/**
	 * Adiciona un tercero o autoridad judicial al caso
	 * 
	 * @param dPersona
	 * @param idCaso
	 * @param nombreRol
	 * @param estado
	 */
	public void crearRolPersonaCaso(Long idPersona, Long idCaso, String nombreRol, String estado);

	/**
	 * Consulta los arbitros asociados al caso con los datos parametrizados en
	 * la tabla rol
	 * 
	 * @param idCaso
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public Collection<RolPersonaCaso> consultarArbitrosPorRolCaso(Long idCaso) throws SIMASCNegocioExcepcion;

	/**
	 * Cambia rol a la persona en un caso el id del caso, id de la persona y el
	 * rol.
	 * 
	 * @param idCaso
	 * @param idPersona
	 * @param idRol
	 * @throws SIMASCNegocioExcepcion
	 */
	public void cambiarRolAPersonaEnCaso(Long idCaso, Long idPersona, Long idRol) throws SIMASCNegocioExcepcion;

	/**
	 * Elimina a una persona con un rol de un caso
	 * 
	 * @param idCaso
	 * @param idPersona
	 * @param rolPersona
	 */
	public void eliminarRolPersonaCaso(Long idCaso, Long idPersona, String rolPersona);

	public void designarArbitroCaso(DetalleArbitroDTO detalleArbitroDTO) throws SIMASCNegocioExcepcion;

	/**
	 * Devuelve los detalles de los arbitros de un Caso
	 * @param idCaso
	 * @param conMotivoInactivacion: si esta en true, coloca arbitros inactivos en la consulta.
	 * @return List<DetalleArbitroDTO>
	 */
	 public List<DetalleArbitroDTO> consultarDetallesArbitros(Long idCaso, Boolean conMotivoInactivacion, Boolean conSecretarios) throws Exception;
	 
	/**
	 * Habilita un arbitro dado un cierre de caso, es decir que cambia su estado
	 * en la asignación a un caso dado un motivo especifico.
	 * 
	 * @param idCaso
	 * @param motivo
	 * @param idArbitro
	 * @return String
	 */
	public String habilitarArbitro(Long idCaso, Long idArbitro, String motivoHabilitacion);

	/**
	 * Se elimina logicamente el registro de rol persona caso recibiendo como
	 * paramteros el id del caso, id de la persona y el rol.
	 * 
	 * @param idCaso
	 * @param idPersona
	 * @param rolPersona
	 * @param tipoEvento
	 * @param observaciones
	 * @throws SIMASCNegocioExcepcion
	 */
	public void eliminarRolPersonaCasoConEvento(Long idCaso, Long idPersona, String rolPersona, String tipoEvento,
			String observaciones) throws SIMASCNegocioExcepcion;

	/**
	 * Hace las validaciones de negocio antes de cambiar de estados a los
	 * arbitros
	 * 
	 * @param cambioEstadoDTO
	 * @throws SIMASCNegocioExcepcion
	 */
	public void validarCambioEstadoArbitros(CambioEstadoRPC_DTO cambioEstadoDTO) throws SIMASCNegocioExcepcion;

	/**
	 * Cambia de estado a una lista de arbitros
	 * 
	 * @param cambioEstadoDTO
	 * @throws SIMASCNegocioExcepcion
	 */
	public void cambiarEstadoArbitros(CambioEstadoRPC_DTO cambioEstadoDTO) throws SIMASCNegocioExcepcion;

	/**
	 * Metodo principal del reparto de funcionario, consulta las personas que
	 * tienen el rol activo, en esta version se usaran 3 roles principales
	 * Abogado, Auxiliar Administrativo y Digitador, para los 3 roles de
	 * funcionarios internos de la CCB.
	 * 
	 * @param caso
	 * @param rol
	 * @return RolPersonaCaso
	 */
	public boolean reparto(Caso caso, Rol rol);

	/**
	 * Metodo utilizado para llamar al reparto
	 * 
	 * @param reparto
	 * @return
	 */
	public boolean reparto(RepartoDTO reparto);

	/**
	 * nombra el arbitro suplente como principal
	 * 
	 * @param cambioEstadoSuplenteDto
	 * @throws SIMASCNegocioExcepcion
	 */
	public boolean nombrarSuplentePrincipal(CambioEstadoSuplenteDTO cambioEstadoSuplenteDto)
			throws SIMASCNegocioExcepcion;

	/**
	 * Consulta los arbitros asociados al caso con los datos parametrizados en
	 * la tabla rol
	 * 
	 * @param idCaso
	 * @param nombreRol
	 * @return
	 */
	public Collection<RolPersonaCasoDesignacionDTO> consultarArbitrosDesignados(Long idCaso, String nombreRol);

	List<Persona> consultarArbitrosPrincipales(Long idCaso);

	/**
	 * consulta el rol que tiene la persona en el caso actual
	 * 
	 * @param casosAsignadosDTO
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public CasosAsignadosDTO obtenerRolDeCaso(CasosAsignadosDTO casosAsignadosDTO) throws SIMASCNegocioExcepcion;

	/**
	 * registra el pronunciamiento de un secretario.
	 * 
	 * @param pronunciamiento
	 * @param idPersona
	 * @param idCaso
	 * @throws SIMASCNegocioExcepcion
	 */
	public void pronunciamientoSecretario(Pronunciamiento pronunciamiento, String idPersona, Long idCaso)
			throws SIMASCNegocioExcepcion;

	/**
	 * Permite a una persona natural o jurídica consultar si se encuentra
	 * vinculado a un caso de arbitraje
	 * 
	 * @param tipoDoc
	 * @param identificacion
	 * @param nombre
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<VinculacionPersonaCasoDTO> consultaVinculacionPersonaCaso(String tipoDoc, String identificacion,
			String nombre) throws SIMASCNegocioExcepcion;

	/**
	 * Metodo que obtiene el parametro con el identificador de la siguiente
	 * asignacion que se debe realizar segun el rol
	 * 
	 * @param rol
	 * @return
	 */
	public ParametrosGenerales obtenerSiguiente(Rol rol);

	/**
	 * Realiza la asignacion del funcionario
	 * 
	 * @param rolPersonas
	 * @param posicion
	 * @return la persona que ha sido asignada
	 */
	public RolPersona realizarAsignacion(List<Number> rolPersonas, ParametrosGenerales posicion);

	/**
	 * Metodo para persistir la información del @RolPersonaCaso
	 * 
	 * @param caso
	 * @param rol
	 * @param persona
	 * @return La entidad persistida
	 */
	public RolPersonaCaso saveRPC(Caso caso, Rol rol, Persona persona, String estado);

	public void cambiarEstadoArbitroSorteo(RolPersonaCaso rolPersonaCaso, Caso caso, String observaciones,
			String estadoSorteo);

	/**
	 * Metodo que elimina del pacto arbitral a un tercero o autoridad judicial
	 * 
	 * @param idCaso
	 * @param idPersona
	 * @param nombreRol
	 */
	public void eliminarPactoTerceroOAutoridadJudicial(Long idCaso, Long idPersona, String nombreRol);

	/**
	 * metodo encargado de nombrar a un arbitro suplente como principal
	 * 
	 * @param cambioEstadoSuplenteDto
	 */
	public void nombrarSuplenteEspecificoPrincipal(CambioEstadoSuplenteDTO cambioEstadoSuplenteDto)
			throws SIMASCNegocioExcepcion;

	/**
	 * Método encargado de obtener el detalle de los arbitros de un caso que se
	 * encuentren en estado 'Aceptado' o 'Por aceptar'
	 * 
	 * @param idCaso
	 * @return
	 */
	public List<DetalleArbitroDTO> consultarDetalleArbitrosCaso(Long idCaso) throws Exception;

	/**
	 * Obtiene el secretario de arbitraje para el caso
	 * 
	 * @param idCaso
	 * @return
	 */
	public Long consultarSecretarioCaso(Long idCaso);

	/**
	 * retorna los datos de los casos pendientes de pronunciamiento
	 * 
	 * @param idPersona
	 * @return
	 */
	public List<PendientePronunciamientoDTO> casosPendientesPronunciamentoConciliador(Long idPersona, String rol);

	/**
	 * modifica el estado del rolpersonaCaso
	 * 
	 * @param idRol
	 * @param idPersona
	 * @param idCaso
	 * @param estado
	 * @param tipoNombramiento
	 * @return
	 */
	public RolPersonaCaso modificarEstadoRolPersonaCaso(Long idRol, Long idPersona, Long idCaso, String estado,
			String usuarioModificacion, String tipoNombramiento, Long pronunciamiento);

	/**
	 * Método que obtiene la lista de arbitros disponibles para el sorteo de un caso 
	 * que se recibe por parametro
	 * @param idCaso
	 * @return
	 */
	public List<PersonaBasicaDTO> consultarArbitrosDisponiblesSorteo(Long idCaso);

	/**
	 * nombra el suplente de un conciliador
	 * 
	 * @param pendientePronunciamiento
	 * @return el id del conciliador nombrado, -1 si no se nombro conciliador.
	 */
	public Long nombrarSuplenteConciliacion(PendientePronunciamientoDTO pendientePronunciamiento, Audiencia audiencia);

	/**
	 * Metodo que consulta las personas asociadas al caso cuyo rol este
	 * habilitado para el envio de cartas para el requerimiento CON-094.
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 * @param idServicios:
	 *            Lista de servicios.
	 * @return List<PersonaCasoListDTO>
	 */
	public List<PersonaCasoListDTO> consultarPersonasAsignadasCaso(Long idCaso, List<Long> idServicios);
	
	/**
	 * Generacion de lotes de cartas
	 * CON-F-074
	 * @param filtros
	 * @return
	 */

	public List<LotesCartasDTO> lotesCartasByFiltros(LotesCartasFiltrosDTO filtros);
	
	/**
	 *  Método para cambiar el conciliador, eliminando el designado, liberando la agenda del conciliador para
     * la fecha y hora de la audiencia y generando un log en la ruta del caso
	 * 
	 * @param CambioConciliadorDTO:
	 *            Información del nuevo conciliador con las observaciones dadas .
	
	 * @return void
	 */
	public void cambiarConciliador(CambioConciliadorDTO informacionConciliador, String idUsuarioModificacion) throws EstadosCasoException;
	
	
	/**
	 *  Método para consultar conciliadores acorde a regla de negocio RN_01 para cambio manual de conciliador
	 * 
	 * @param idCaso:
	 *            Id del caso.
	
	 * @return List<CambioConciliadorDTO>
	 */
	public List<CambioConciliadorDTO> obtenerConciliadoresCambio(Long idCaso);
	
	/** 30-01-2018 pRendon
	 * Metodo que busca las personas de un caso, dependiendo su rol y el centro
	 * @param roles
	 * @param centro
	 * @return List<PersonaBasicaDTO>
	 */
	public List<PersonaBasicaDTO> consultarPersonaCasoPorRolCentro( List<String> roles, List<String> centro );
	
	/**
	 * Retorna una lista de PersonaBasicaDTO a partir de un ROL_PERSONA_CASO
	 * @param idCaso
	 * @param estados
	 * @param tipoNombramiento
	 * @return List<PersonaBasicaDTO>
	 */
	public List<PersonaBasicaDTO> obtenerPersonaBasicaConciliadorCasoEstadoNombramiento( Long idCaso, List<String> estados,
			String tipoNombramiento );
	/**
	 * Consulta las partes para el seguimiento del caso
	 * CON-F-076
	 * @param idCaso
	 * @return
	 */
	public List<PartesSeguimientoDTO> consultarPartesSeguimiento(Long idCaso);
	
	/** CON-C-004
	 * Transfroma un List<RolPersonaCaso> a List<RolPersonaCasoDTO>
	 * @param idPersona
	 * @param nombreRol
	 * @param estados
	 * @param tipoNombramientos
	 * @return
	 */
	public List<RolPersonaCasoDTO> consultarCasosPersonaRolEstadoTipo(  Long idPersona, List<String> nombreRol,
			List<String> estados, List<String> tipoNombramientos );

	/**
	 * Metodo que permite reasignar analista al caso para control de legalidad.
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 * @param idPersona:
	 *            analista seleccionado para reasignar en el caso.
	 * @param fechaLimiteEstudioCaso:
	 *            fecha Limite para estudio del caso.
	 */
	public void reasignarAnalistaControlLegalidad(Long idCaso, Long idPersona, Date fechaLimiteEstudioCaso);
	
	/**
	 * Metodo que permite consultar la vinculación de un cliente a un caso
	 * 
	 * @param cliente:
	 *            numero y tipo de identificacion ó nombres completos 
	 */
	public List<CasoAsignadoDTO> consultarVinculacionClienteCaso(PersonaDTO cliente);

	/**
	 * Metodo que permite realizar la asignacion equitativa de los casos a los
	 * auxiliares administrativos.
	 * 
	 * @return List<InfoBasicaAlertasDTO>: Lista de la informacion de los casos
	 *         asignados.
	 */
	public List<InfoBasicaAlertasDTO> crearAsignacionCasosAuxiliaresADM();

	public Boolean inactivacionSegunMotivo(Long idPersona,
			String estadoArbitrosEmerito, Long idRol,Long idservicio);

	/**
	 *  verifica la existencia de una persona en un caso dada su idPersona y el idCaso
	 * @param idPersona
	 * @param idCaso
	 * @return
	 */
	public boolean personaExisteEnCaso(Long idPersona, Long idCaso);

	// protected region metodos adicionales end
	
	public List<RolPersonaCaso> consultarPartesCaso(Long idCaso);
	
	public RolPersonaCaso consultaPersonaAsignadaCaso(Long idPersona, Long idCaso);

	public void liberarSuplentes(Long idCaso);
	
	public void repartoInsolvencia(Caso caso);
	
}
