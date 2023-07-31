package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin

// Escriba en esta sección sus modificaciones

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.FiltosPreseleccionArbitros;
import com.ccb.simasc.transversal.dto.FiltrosPreseleccionArbitrosCCB;
import com.ccb.simasc.transversal.dto.InformacionPrestadorDTO;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.dto.PersonaMateriaAsignadaDTO;
import com.ccb.simasc.transversal.dto.PreferenciasDTO;
import com.ccb.simasc.transversal.dto.PreseleccionadoDTO;
import com.ccb.simasc.transversal.dto.PreseleccionadoDesignadoDTO;
import com.ccb.simasc.transversal.dto.formularios.BusquedaRolesActivosDTO;
import com.ccb.simasc.transversal.dto.formularios.DatosBasicosCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltrosSeleccionConciliadorDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioDatosClienteDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioParteDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioParteEnmascaradoDTO;
import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.dto.formularios.ParteCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.dto.formularios.RadicacionSolicitudDTO;
import com.ccb.simasc.transversal.dto.formularios.RadicacionSolicitudEnmascaradoDTO;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Ubicacion;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;

// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Persona}
 * 
 * @author sMartinez
 *
 */
@Local
public interface IPersonaFacade extends IAccesoFacade<Persona, Long, PersonaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	/**
	 * Consulta las personas por nombre del rol
	 * 
	 * @param nombreRol
	 * @return Collection<Persona>
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<Persona> consultarPersonasPorRol(String nombreRol) throws SIMASCNegocioExcepcion;

	/**
	 * Retorna los datos basicos de una persona(id,nombre) por rol
	 * 
	 * @param nombreRol
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<PersonaBasicaDTO> consultarPersonasBasicaPorRol(String nombreRol) throws SIMASCNegocioExcepcion;

	/**
	 * Obtiene la lista de personas con el rol parametrizado para reparto de abogado
	 * por tipo de servicio
	 * 
	 * @param idServicio
	 * @return
	 */
	public List<PersonaBasicaDTO> consultarAbogadosPorServicio(Long idServicio);

	/**
	 * Obtiene los arbitros para preseleccion teniendo en cuenta los parametros
	 * generales definidos en la tabla PARAM_ESTADOS_ARBITRO_PRESELECCION
	 * 
	 * @param tipoServicio
	 * @return List<GenericoDTO>
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<GenericoDTO> consultarArbitrosPreseleccion(String tipoServicio) throws SIMASCNegocioExcepcion;

	/**
	 * Adiciona un tercero o autoridad judicial al caso
	 * 
	 * @param datosPersona
	 * @param idCaso
	 * @param nombreRol
	 * @throws SIMASCNegocioExcepcion
	 */
	public void adicionarTerceroOAutoridad(Persona datosPersona, Long idCaso, String nombreRol)
			throws SIMASCNegocioExcepcion;

	/**
	 * Consulta las personas por el rol del caso
	 * 
	 * @param idCaso
	 * @param nombreRol
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public Collection<Persona> consultarPorRolCaso(Long idCaso, String nombreRol) throws SIMASCNegocioExcepcion;

	/**
	 * Metodo que permite adiciona un arbitro a un caso
	 * 
	 * @param persona
	 * @param idCaso
	 * @param nombreRol
	 * @throws SIMASCNegocioExcepcion
	 */
	public void adicionarArbitroCaso(List<Persona> persona, Long idCaso, String nombreRol)
			throws SIMASCNegocioExcepcion;

	/**
	 * Metodo que consulta una parte por numero de cedula e id del caso
	 * 
	 * @param numeroCedula
	 * @param nombreRol
	 * @param idCaso
	 * @return FormularioParteDTO
	 * @throws SIMASCNegocioExcepcion
	 */
	public FormularioParteDTO consultarPartePorCedulaPorCaso(String numeroCedula, Long idCaso)
			throws SIMASCNegocioExcepcion;

	/**
	 * 
	 * @param idPersona
	 * @param idCaso
	 * @return
	 */
	public FormularioParteDTO consultarPorIdPersonaPorCaso(Long idPersona, Long idCaso);

	/**
	 * Metodo que persiste la informacion de una persona tipo abogado demandante o
	 * abogado demandado
	 * 
	 * @param formularioParteDTO
	 * @return String
	 * @throws SIMASCNegocioExcepcion
	 */
	public Long guardarInformacionParte(FormularioParteDTO formularioParteDTO) throws SIMASCNegocioExcepcion;

	/**
	 * Consulta los arbitros asociados al caso con los datos parametrizados en la
	 * tabla rol
	 * 
	 * @param idCaso
	 * @param nombreRol
	 * @return Collection<Persona>
	 */
	public Collection<Persona> consultarArbitrosPorRolCaso(Long idCaso, String nombreRol);

	/**
	 * Metodo que consulta las personas con roles Demandante, Demandando, Apoderado
	 * Demandante y Apoderado Demandado relacionadas a un Caso
	 * 
	 * @param idCaso
	 * @return List<FormularioParteDTO>
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<FormularioParteDTO> consultarPartesCaso(Long idCaso) throws SIMASCNegocioExcepcion;

	/**
	 * Devuelve los nombres de las personas con los rolesÂ Conciliador, arbitro, y
	 * Secretario de tribunal para el servicio que se pasa como parametro
	 *
	 * @param tipoServicio
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<PersonaBasicaDTO> consultarPrestadoresDelServicio(String tipoServicio) throws SIMASCNegocioExcepcion;

	/**
	 * Metodo que consulta una persona por numero de cedula, rol y id del caso
	 * 
	 * @param idPersona
	 * @param nombreRol
	 * @param idCaso
	 * @return FormularioParteDTO
	 * @throws SIMASCNegocioExcepcion
	 */
	public FormularioParteDTO consultarPartePorIdPorRol(Long idPersona, String nombreRol, Long idCaso)
			throws SIMASCNegocioExcepcion;

	/**
	 * Consulta los datos basicos de las personas por rol
	 * 
	 * @param nombreRol
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public Collection<PersonaBasicaDTO> consultarDatosBasicosPersonasPorRol(String nombreRol)
			throws SIMASCNegocioExcepcion;

	/**
	 * Metodo que se encarga de crear una ubicacion para Persona
	 * 
	 * @param direccion
	 * @param latitud
	 * @param longitud
	 * @param idZonaGeografica
	 * @param tipo
	 * @param idPersona
	 * @param idUbicacion
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public Ubicacion crearUbicacionPersona(String direccion, BigDecimal latitud, BigDecimal longitud,
			String idZonaGeografica, String tipo, Long idPersona, Long idUbicacion) throws SIMASCNegocioExcepcion;

	/**
	 * Metodo que se encarga de crear el correo electronico para una persona
	 * 
	 * @param correoElectronico
	 * @param tipoCorreo
	 * @param IdPersona
	 * @throws SIMASCNegocioExcepcion
	 */
	public void crearCorreoPersona(String correoElectronico, String tipoCorreo, Long idPersona)
			throws SIMASCNegocioExcepcion;

	/**
	 * Metodo que consulta los arbitros principales en un caso
	 * 
	 * @param idCaso
	 * @param nombreRol
	 * @return Collection<PersonaBasicaDTO>
	 */
	public Collection<PersonaBasicaDTO> consultarArbitrosPrincipales(Long idCaso, List<String> nombreRol);

	/**
	 * Metodo encargado de guardar los arbitros preseleccionados para un caso
	 * 
	 * @param arbitrosPreseleccionados
	 * @param idCaso
	 * @throws SIMASCNegocioExcepcion
	 */
	public void guardarArbitrosPreseleccionados(List<GenericoDTO> arbitrosPreseleccionados, Long idCaso)
			throws SIMASCNegocioExcepcion;

	/**
	 * Metodo encargado de eliminar los arbitros preseleccionados para un caso
	 * 
	 * @param arbitrosPreseleccionados
	 * @param idCaso
	 * @throws SIMASCNegocioExcepcion
	 */
	public void eliminarArbitrosPreseleccionados(List<GenericoDTO> arbitrosPreseleccionados, Long idCaso)
			throws SIMASCNegocioExcepcion;

	/**
	 * Metodo encargado de consultar los arbitros preseleccionados y activos a un
	 * caso antes de realizar un sorteo
	 * 
	 * @param idCaso
	 * @return List<PreseleccionadoDTO>
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<PreseleccionadoDTO> consultarPreseleccionadosCaso(Long idCaso) throws SIMASCNegocioExcepcion;

	public boolean validarCompletitudPartes(Long idCaso);

	/**
	 * Obtiene la informacion de la parte asociada a un acuse de falla de envio de
	 * correo electronico.
	 * 
	 * @param idCaso
	 * @param idPersona
	 * @param idAudiencia
	 * @return ParteCasoDTO
	 * @throws SIMASCNegocioExcepcion
	 */
	public ParteCasoDTO consultarParteCorreo(Long idCaso, Long idPersona, Long idAudiencia)
			throws SIMASCNegocioExcepcion;

	/**
	 * consulta el secretario en estado POR o ACE
	 * 
	 * @param idCaso
	 * @return
	 */
	public List<PersonaBasicaDTO> consultaSecretarioActivoCaso(Long idCaso);

	/**
	 * Metodo que retorna la persona sisatema
	 * 
	 * @return
	 */
	public Persona getPersonaSistema();

	/**
	 * Consulta las personas de un caso por rol y por una lista de estados
	 * 
	 * @param idCaso
	 * @param nombreRol
	 * @param estados
	 * @return
	 */
	public List<Persona> consultarArbitrosCasoPorEstados(Long idCaso, List<String> estados);

	/**
	 * Consulta todas las partes de un caso espscificando una lista de roles a tener
	 * en cuenta
	 * 
	 * @param idCaso
	 * @return
	 */
	public List<Persona> consultarPartesDelCaso(Long idCaso);

	/**
	 * Metodo que se encarga de modificar un arbitro a estado litigante si es
	 * seleccionado como apoderado en un caso
	 * 
	 * @param idPersona
	 * @return
	 */
	public Boolean inactivacionLitigante(Long idPersona, Long idServicio);

	/**
	 * 
	 * @param idCaso
	 * @return
	 */
	public Map<String, Object> obtenerListasParaAsignarArbitro(Long idCaso);

	/**
	 * Método encargado de validar la existencia de una persona que esta tramitando
	 * una solicitud en el sistema
	 * 
	 * @param personaBasicaDTO
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public RadicacionSolicitudDTO validarIdentificacionPersona(PersonaBasicaDTO personaBasicaDTO)
			throws SIMASCNegocioExcepcion;

	/**
	 * Método encargado de consultar una persona por tipo de documento y numero
	 * 
	 * @param tipoDocumento
	 * @param numeroDocumento
	 * @param transformarDependencias
	 * @return Persona sin dependencias
	 * @throws SIMASCNegocioExcepcion
	 */
	public Persona consultarPersonaPorIdentificacion(String tipoDocumento, String numeroDocumento,
			boolean transformarDependencias) throws SIMASCNegocioExcepcion;

	/**
	 * almacena las preferencias de una persona
	 * 
	 * @param preferenciasDTO
	 * @throws SIMASCNegocioExcepcion
	 */
	public void almacenarPreferencias(PreferenciasDTO preferenciasDTO) throws SIMASCNegocioExcepcion;

	/**
	 * Valida la existencia de una persona en SIREP por tipo y número de
	 * identificación, si no hay resultados busca a la persona en SIMASC con los
	 * mismos parametros
	 * 
	 * TRANS-F-041
	 * 
	 * @param personaBasicaDTO
	 * @return
	 */
	public FormularioDatosClienteDTO validarIdentificacionPagadorSolicitud(PersonaBasicaDTO personaBasicaDTO);

	/**
	 * Método encargado de la invocación del servicio de web de SIREP para la
	 * creación de un usuario en dicho sistema
	 * 
	 * @param formularioDatosClienteDTO
	 */
	public Map<String, String> crearDatosBasicosClienteSIREP(FormularioDatosClienteDTO formularioDatosClienteDTO);

	/**
	 * Método que asocia la persona natural a la persona juridica
	 * 
	 * @param idPersonaJuridica
	 * @param idPersonaNatural
	 */
	public void adicionarPersonaNatural(Long idPersonaJuridica, Long idPersonaNatural);

	List<PersonaBasicaDTO> consultarConciliadoresFiltros(FiltrosSeleccionConciliadorDTO filtrosSeleccionConciliadorDTO);

	/**
	 * Consulta las personas por nombre del rol
	 * 
	 * @param nombreRol
	 * @return Collection<Persona>
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<PersonaMateriaAsignadaDTO> consultarMateriasAsignadas(Long idPersona) throws SIMASCNegocioExcepcion;

	/**
	 * actuliza el resumen de hoja de vida de una persona
	 * 
	 * @param persona
	 * @throws SIMASCNegocioExcepcion
	 */
	public void actualizarResumenHojaVida(PersonaDTO persona) throws SIMASCNegocioExcepcion;

	/**
	 * actualiza los datos de una persona con profesión pregrado
	 * 
	 * @param persona
	 * @throws SIMASCNegocioExcepcion
	 */
	public void actualizarDatosPersonaPRE(Long idPersona, Long profesion, String numeroTarjetaPro)
			throws SIMASCNegocioExcepcion;

	/**
	 * Metodo que consulta una persona por numero y tipo de identificacion
	 * 
	 * @param numeroCedula
	 * @param tipoId
	 * @return FormularioParteDTO
	 * @throws SIMASCNegocioExcepcion
	 */
	public FormularioParteDTO getConsultarPartePorCedula(String numeroCedula, String tipoId)
			throws SIMASCNegocioExcepcion;

	/**
	 * Metodo encargado de obtener las partes asociadas a una Solicitud de Servicio
	 * de Conciliacion Tramite Ordinario
	 *
	 * CON-F-125
	 * 
	 * @param idSolicitudServicio
	 * @return List<FormularioParteDTO>
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<FormularioParteDTO> consultarPartesSolicitudServicio(Long idSolicitudServicio)
			throws SIMASCNegocioExcepcion;

	/**
	 * Metodo encargado de obtener las partes asociadas a una Solicitud de Servicio
	 * de recuperacion empresarial
	 *
	 * CON-F-125
	 * 
	 * Y DE de equidad
	 *
	 * EQU-F-004
	 * 
	 * @param idSolicitudServicio
	 * @return List<FormularioParteDTO>
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<FormularioParteDTO> consultarPartesSolicitudServicioRec(Long idSolicitudServicio)
			throws SIMASCNegocioExcepcion;
	
	/**
	 * Metodo encargado de validar si ya existen las partes Convocante y Convocado
	 * asociados a la Solicitud de un Servicio de Conciliacion de Tramite Ordinario.
	 * 
	 * CON-F-125
	 * 
	 * @param idSolicitudServicio
	 * @return Boolean
	 */
	public Boolean validarCompletitudPartesSolicitudServicio(Long idSolicitudServicio);

	/**
	 * Metodo encargado de validar si ya existen las partes Convocante y Convocado
	 * asociados a la Solicitud de un Servicio de recuperacion empresarial
	 * 
	 * @param idSolicitudServicio
	 * @return Boolean
	 */
	public Boolean validarCompletitudPartesSolicitudServicioRec(Long idSolicitudServicio);

	/**
	 * Metodo encargado de realizar una eliminacion logica de los registros
	 * asociados a una parte en una Solicitud de Servicio de Conciliacion.
	 * 
	 * CON-F-125
	 * 
	 * @param idSolicitudServicio
	 * @param idPersona
	 * @throws SIMASCNegocioExcepcion
	 */
	public void eliminarParteSolicitudServicio(Long idSolicitudServicio, Long idPersona) throws SIMASCNegocioExcepcion;

	/**
	 * Metodo encargado de obtener los conciliadores por centro
	 * 
	 * CON-F-124
	 * 
	 * @param estado
	 * @param centros
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<PersonaBasicaDTO> consultarConciliadoresPorCentros(BusquedaRolesActivosDTO filtros)
			throws SIMASCNegocioExcepcion;

	/**
	 * Metodo encargado de obtener los Mediadores por centro
	 * 
	 * CON-F-124
	 * 
	 * @param estado
	 * @param centros
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<PersonaBasicaDTO> consultarMediadoresPorCentros(BusquedaRolesActivosDTO filtros)
			throws SIMASCNegocioExcepcion;

	/**
	 * consulta la personasBasicas por tipo de funcionario.
	 * 
	 * @param tiposFuncionario
	 * @return
	 */
	public List<PersonaBasicaDTO> cosultarPersonaBasicaPorTipoFuncionario(List<String> tiposFuncionario);

	/**
	 * Devuelve la lista de conciliadores que tengan casos pendientes de cobro
	 * 
	 * @return
	 */
	public List<Persona> conciliadoresCasosPendienteCobro();

	/**
	 * Metodo que permite consultar las partes de conciliacion del caso.
	 * 
	 * @param idCaso:            Identificador del caso.
	 * @param sinIdentificacion: bandera que indica si se filtra las partes que no
	 *                           tienen numero identificacion.
	 * @return List<PersonaBasicaDTO>: Lista de partes.
	 */
	public List<ParteCasoDTO> consultarPartesConciliacionCaso(Long idCaso, boolean sinIdentificacion, boolean bandera);

	/**
	 * Metodo que busca los conciliadores que sus casos estan en acuerdo y han
	 * tenido seguimiento. CON-C-051
	 * 
	 * @author LRUIZ
	 * @param centros
	 * @return
	 */
	public List<Persona> conciliadoresSeguimientoCasos(List<CentroDTO> centros);

	/**
	 * Método que realiza la consulta de los conciliadores que tienen audiencias
	 * programadas para la fecha enviada
	 * 
	 * @param fechaAudiencia
	 * @return lista de conciliadores
	 */
	public List<PersonaBasicaDTO> consultarConciliadoresConAudienciasProgramada(Date fechaAudiencia);

	/**
	 * Consulta una persona por tipo de persona, tipo de documento y numero de
	 * documento CON-C-006
	 * 
	 * @param filtros
	 * @return
	 */
	public Persona consultarPersonaTipoYNumeroDocumento(Persona filtros);

	/**
	 * TRANS-F-020 Consulta la informacion del prestador de servicio de acuerdo al
	 * TRANS-F-020
	 * 
	 * @param idPersona
	 * @param nombreRol
	 * @return InformacionPrestadorDTO
	 */
	public InformacionPrestadorDTO consultarInformacionPrestador(Long idPersona, Long idRol);

	/**
	 * Método que realiza la creacion o actualizacion de una persona
	 * 
	 * @param personaDTO
	 * @return
	 */
	public Persona crearPersona(PersonaDTO personaDTO);

	public List<Persona> consultarFuncionariosCAC();

	public Boolean inactivacionNoContestacion(Long idPersona, Long idServicio);

	public Boolean inactivacionSegunMotivo(Long idPersona, String Motivo, Long idRol, Long idServicio);

	public List<GenericoDTO> consultarArbitrosInternacionalPreseleccion(String tipoServicio,
			FiltosPreseleccionArbitros filtros);

	public List<GenericoDTO> consultarArbitrosPreseleccionFiltros(String tipoServicio,
			FiltosPreseleccionArbitros filtros);

	public void eliminarDefinitivoParteSolicitudServicio(Long idSolicitudServicio, Long idPersona)
			throws SIMASCNegocioExcepcion;

	public Boolean asignarRolArbitroSocial(Long idPersona, String Motivo, Long idRol, boolean pronunciamiento);

	public void actualizaDatosContactoClienteSIREP(FormularioDatosClienteDTO formularioDatosClienteDTO);

	public List<GenericoDTO> consultarArbitrosPreseleccionCCBFiltros(FiltrosPreseleccionArbitrosCCB filtros);
	
	public void bloqueaArbitrosReaAperturaCaso(DatosBasicosCasoDTO datosBasicosCasoDTO, String usuarioModificacion) throws Exception;
	// protected region metodos adicionales end
	
	/**
	 * Método encargado de validar la existencia de una persona que esta tramitando
	 * una solicitud en el sistema enmascarando los datos privados
	 * 
	 * @param personaBasicaDTO
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public RadicacionSolicitudEnmascaradoDTO validarIdentificacionPersonaEnmascarando(PersonaBasicaDTO personaBasicaDTO)
			throws SIMASCNegocioExcepcion;
	
	/**
	 * Metodo que consulta una parte por numero de cedula e id del caso 
	 * y devuelve los datos esmascarando los datos privados
	 * 
	 * @param numeroCedula
	 * @param nombreRol
	 * @param idCaso
	 * @return FormularioParteDTO
	 * @throws SIMASCNegocioExcepcion
	 */
	public FormularioParteEnmascaradoDTO consultarPartePorCedulaPorCasoEnmascarando(String numeroCedula, Long idCaso)
			throws SIMASCNegocioExcepcion;
	
	public FormularioDatosClienteDTO validarIdentificacionEnmascaradoPagadorSolicitud(PersonaBasicaDTO personaBasicaDTO);

	public List<PreseleccionadoDesignadoDTO> consultarPreseleccionadosCasoDesignado(Long idCaso);
}
