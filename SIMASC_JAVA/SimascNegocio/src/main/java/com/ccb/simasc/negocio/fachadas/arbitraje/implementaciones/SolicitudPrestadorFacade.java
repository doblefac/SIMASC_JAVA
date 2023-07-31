package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorEstadoPersonaTipoServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorHistoricoEstadoPersonaTipoServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroServicioSorteo;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorSolicitudPrestador;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDesarrolloProfesionalFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEvaluacionConciliadorFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IMembresiaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISolicitudPrestadorFacade;
import com.ccb.simasc.transversal.dto.DesarrolloProfesionalDTO;
import com.ccb.simasc.transversal.dto.MembresiaDTO;
import com.ccb.simasc.transversal.dto.RolPersonaCasoDTO;
import com.ccb.simasc.transversal.dto.SolicitudPrestadorDTO;
import com.ccb.simasc.transversal.dto.VerificarSolicitudCambioListaDTO;
import com.ccb.simasc.transversal.dto.reportes.FiltroReportesDTO;
import com.ccb.simasc.transversal.entidades.EstadoPersonaTipoServicio;
import com.ccb.simasc.transversal.entidades.EstadoPersonaTipoServicioPK;
import com.ccb.simasc.transversal.entidades.EvaluacionConciliador;
import com.ccb.simasc.transversal.entidades.HistoricoEstadoPersonaTipoServicio;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.entidades.SolicitudPrestador;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link SolicitudPrestador}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class SolicitudPrestadorFacade extends AccesoFacade<SolicitudPrestador, Long, SolicitudPrestadorDTO> implements ISolicitudPrestadorFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorSolicitudPrestador manejadorSolicitudPrestador;	

	@EJB 
	private ManejadorParametroServicioSorteo manejadorParametroServicioSorteo;
	
	@EJB
	private ManejadorRol manejadorRol;
	
	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;
	
	@EJB
	private IPersonaFacade personaFacade;
	
	@EJB
	private IRolPersonaFacade rolPersonaFacade;
	
	@EJB
	private IMembresiaFacade membresiaFacade;
	
	@EJB
	private IRolPersonaCasoFacade rolPersonaCasoFacade;
	
	@EJB
	private IDesarrolloProfesionalFacade desarrolloProfesionalFacade;
	
	@EJB
	private ManejadorRolPersona manejadorRolPersona;	

	@EJB
	private IEvaluacionConciliadorFacade evaluacionConciliadorFacade;
	
	@EJB
	private ManejadorEstadoPersonaTipoServicio manejadorEstadoPersonaTipoServicio;
	
	@EJB
	private ManejadorHistoricoEstadoPersonaTipoServicio manejadorHistoricoEstadoPersonaTipoServicio;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorSolicitudPrestador;
	}

	@Override
	public SolicitudPrestadorDTO transformarSinDependencias(SolicitudPrestador obj) {
		SolicitudPrestadorDTO dto = new SolicitudPrestadorDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public SolicitudPrestadorDTO transformarConDependencias(SolicitudPrestador obj) {
		SolicitudPrestadorDTO dto = new SolicitudPrestadorDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public SolicitudPrestador transformarEntidadConDependencias(SolicitudPrestador obj) {
		
		return new SolicitudPrestadorDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public SolicitudPrestador transformarEntidadSinDependencias(SolicitudPrestador obj) {
		return new SolicitudPrestadorDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/*
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * ISolicitudPrestadorFacade#solicitarCambioLista(com.ccb.simasc.transversal.entidades.
	 * SolicitudPrestador)
	 */
	@Override
	public void solicitarCambioLista( SolicitudPrestador solicitudCambioLista ){
		validarSolicitudCambioLista( solicitudCambioLista );
		
		SolicitudPrestador solicitudCambioListaCrear = transformarEntidadSinDependencias( solicitudCambioLista );
		manejadorSolicitudPrestador.crear(solicitudCambioListaCrear);
		
		List<Rol> rolesDeArbitraje = manejadorParametroServicioSorteo.consultarRolesPrestadores();
		
		boolean solicitudDeArbitraje = rolesDeArbitraje.contains(solicitudCambioLista.getRolPersona().getRol());
		
		correoRolPersonaCasoFacade.notificarJefeAnalistaSolicitudCambioLista(solicitudCambioLista, 
				solicitudDeArbitraje, solicitudCambioLista.getIdCentros());
	}
	
	/** CON-F-118
	 * Valida que el solicitante no cuente con una solicitud para el rol en estado pendiente. 
	 * @author prendon
	 * @param solicitudCambioListaDTO
	 */
	private void validarSolicitudCambioLista( SolicitudPrestador solicitudCambioLista ){
		SolicitudPrestador solicitudCambioListaBuscada =  manejadorSolicitudPrestador
				.consultarSolicitudPrestadorPersonaEstadoTipo(solicitudCambioLista.getRolPersona().getIdRolPersona(), 
															   solicitudCambioLista.getEstadoSolicitud(),
															   solicitudCambioLista.getTipo());
		if( solicitudCambioListaBuscada != null ){
			throw new SIMASCNegocioExcepcion(String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR560.toString())));
		}
	}
	
	/*
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * ISolicitudPrestadorFacade#consultarSolicitudesCambioLista(com.ccb.simasc.transversal.entidades.
	 * SolicitudCambioLista)
	 */
	@Override
	public List<SolicitudPrestador> consultarSolicitudesCambioLista( String estadoSolicitud, FiltroReportesDTO filtros ){
		return (List<SolicitudPrestador>) transformarEntidadesColeccionConDependencias(manejadorSolicitudPrestador
				.consultarSolicitudesCambioLista( filtros.getNombreSolicitante(),
												 estadoSolicitud,
												 filtros.getTipos(),
												 filtros.getCentros() ),
										  new ArrayList<SolicitudPrestador>());
	}
	
	/*
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * ISolicitudPrestadorFacade#consultarSolicitudVerificacion(com.ccb.simasc.transversal.dto.
	 * VerificarSolicitudCambioListaDTO)
	 */
	@Override
	public VerificarSolicitudCambioListaDTO consultarSolicitudVerificacion( Long idSolicitud ){
		VerificarSolicitudCambioListaDTO verificarSolicitudCambioListaDTO = new VerificarSolicitudCambioListaDTO();
		
		SolicitudPrestador solicitudCambioLista = transformarEntidadConDependencias(manejadorSolicitudPrestador.buscar( idSolicitud ));
		verificarSolicitudCambioListaDTO.setSolicitudCambioLista(solicitudCambioLista);
		
		List<Rol> rolesDeArbitraje = manejadorParametroServicioSorteo.consultarRolesPrestadores();
		boolean isSolicitudDeArbitraje = rolesDeArbitraje.contains(solicitudCambioLista.getRolPersona().getRol());
		verificarSolicitudCambioListaDTO.setSolicitudDeArbitraje(isSolicitudDeArbitraje);
		
		if( !isSolicitudDeArbitraje ){
			List<BigDecimal> duracion = manejadorRolPersona
										.duracionRolPersonaPorRolPersonaLista( solicitudCambioLista.getRolPersona().getRol().getNombre(),
												solicitudCambioLista.getRolPersona().getIdPersona(),
												Arrays.asList(UtilDominios.TIPO_LISTA_B));
			verificarSolicitudCambioListaDTO.setDuracion( (!duracion.isEmpty())? duracion.get(0).longValue(): 0L );
			
			List<RolPersonaCasoDTO> casosPersonaDTO = rolPersonaCasoFacade
					.consultarCasosPersonaRolEstadoTipo( solicitudCambioLista.getRolPersona().getIdPersona(), 
							Arrays.asList( solicitudCambioLista.getRolPersona().getRol().getNombre() ),
							null, Arrays.asList(UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL));
			verificarSolicitudCambioListaDTO.setCasosPersona( (!casosPersonaDTO.isEmpty())? casosPersonaDTO.size() : 0L );
			
			//porcentaje de acuerdos, el porcentaje va dentro de los resultados de la ultima evaluacion
			
			List<DesarrolloProfesionalDTO> desarrolloDTO = desarrolloProfesionalFacade
					.consultarDesarrolloProfesionalSinDependencias( solicitudCambioLista.getRolPersona().getIdPersona(),
																	UtilDominios.CODIGO_TIPO_DESARROLLO_PROFESIONAL_CURSO,
																	UtilOperaciones.adicionarAnosFecha( new Date(), -2) );
			verificarSolicitudCambioListaDTO.setDesarrolloDTO(desarrolloDTO);
			
			List<EvaluacionConciliador> evaluacionesConciliador = evaluacionConciliadorFacade
					.consultarEvaluacionConciliador(solicitudCambioLista.getRolPersona().getIdPersona(), null, null);
			verificarSolicitudCambioListaDTO.setEvaluacionConciliador( (!evaluacionesConciliador.isEmpty())? evaluacionesConciliador.get(0) :
																											 new EvaluacionConciliador());
			
			List<MembresiaDTO> membresiasDTO = membresiaFacade
					.consultarMembresiasPersona(solicitudCambioLista.getRolPersona().getIdPersona());
			verificarSolicitudCambioListaDTO.setMembresiaDTO( (!membresiasDTO.isEmpty())? membresiasDTO.get(0) : new MembresiaDTO() );
		}
		return verificarSolicitudCambioListaDTO;
	}
	
	/*
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * ISolicitudPrestadorFacade#verificarSolicitudCambioLista(com.ccb.simasc.transversal.dto.
	 * SolicitudCambioListaDTO)
	 */
	@Override
	public void verificarSolicitudCambioLista( SolicitudPrestador solicitudCambioLista ){
		manejadorSolicitudPrestador.actualizar(transformarEntidadSinDependencias(solicitudCambioLista));
		
		if( UtilDominios.ESTADO_SOLICITUD_PRESTADOR_APROBADA.equals(solicitudCambioLista.getEstadoSolicitud()) ){
			List<RolPersona> rolesPersona = rolPersonaFacade
												.consultarRolPersonaPrestadorPersonaRolLista(solicitudCambioLista.getRolPersona().getIdPersona(), 
																							 solicitudCambioLista.getRolPersona().getRol().getNombre(),
																							 UtilDominios.TIPO_LISTA_B,
																							 Arrays.asList(UtilDominios.ESTADO_CONCILIADOR_ACTIVO),
																							 null);
			rolPersonaFacade.cambiarPrestadorDeLista(solicitudCambioLista, rolesPersona);
		}
		correoRolPersonaCasoFacade.notificarSolicitanteCambioLista(solicitudCambioLista);
	}
	
	
	@Override
	public List<SolicitudPrestadorDTO> consultarSolicitudesRealizadas(String idRol, String tipoSolicitud){
		
		String nombreRol;
		Rol rol = manejadorRol.buscar(Long.parseLong(idRol));
		if(rol.getNombre().equals(UtilDominios.ROL_PERSONA_JEFE_DE_ARBITRAJE)){
			nombreRol = UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA;
		}else{
			nombreRol = UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS;
		}
		return manejadorSolicitudPrestador.consultarSolicitudesRealizadas(nombreRol, tipoSolicitud);
	}

	
	@Override
	public List<SolicitudPrestadorDTO> consultarSolicitudesTipoRolPersona(List<Long> idRolPersona, String tipoSolicitud){
		
		return (List<SolicitudPrestadorDTO>) transformarColeccionSinDependencias(manejadorSolicitudPrestador.consultarSolicitudesTipoRolPersona(idRolPersona, tipoSolicitud), new ArrayList<SolicitudPrestadorDTO>());
	}
	
	@Override
	public void crearSolicitudPrestador(SolicitudPrestadorDTO idRolPersona, String idPersonaModificacion, String tipoServicio, Long idPersona, String nombreUsuario, String nombreRol){
		
		//Mensaje a enviar
		List<String> mensajeCorreo = new ArrayList<String>();
		String mensaje;
		//Rol a enviar correo
		List<String> rolesConsulta = new ArrayList<String>();
		if(UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS.equals(tipoServicio)){
			rolesConsulta.add(UtilDominios.ROL_PERSONA_JEFE_DE_CONCILIACION);
		}else{
			rolesConsulta.add(UtilDominios.ROL_PERSONA_JEFE_DE_ARBITRAJE);
		}
		List<Persona> personasNotificar = manejadorRolPersona.consultarPesonasPorRolPersonaCentro(rolesConsulta, idRolPersona.getIdCentros(), new Date());
		
		//Valores predeterminados para todos los tipos de solicitud
		SolicitudPrestador solicitud = new SolicitudPrestador();
		solicitud.setDescripcionSolicitud(idRolPersona.getDescripcionSolicitud());
		solicitud.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		solicitud.setTipo(idRolPersona.getTipo());
		solicitud.setFechaSolicitud(new Date());
		solicitud.setIdUsuarioModificacion(idPersonaModificacion);
		solicitud.setFechaUltimaModificacion(new Date());
		solicitud.setIdRolPersona(idRolPersona.getIdRolPersona());
		// Si se adjunto documento
		if(idRolPersona.getIdDocumento()!=null){
			solicitud.setIdDocumento(idRolPersona.getIdDocumento());
		}
		
		//Si el tipo de solicitud es suspensión
		if(UtilDominios.TIPO_SOLICITUD_PRESTADOR_SUSPENSION.equals(idRolPersona.getTipo())){
			solicitud.setTipo(UtilDominios.TIPO_SOLICITUD_PRESTADOR_SUSPENSION);
			solicitud.setEstadoSolicitud(UtilDominios.ESTADO_SOLICITUD_PRESTADOR_APROBADA);
			
			//Se suspende el usuario
			EstadoPersonaTipoServicioPK estadoPk = new EstadoPersonaTipoServicioPK(idPersona, tipoServicio);
			EstadoPersonaTipoServicio personaSuspension = manejadorEstadoPersonaTipoServicio.buscar(estadoPk);
			personaSuspension.setEstado(UtilDominios.ESTADO_ARBITROS_SUSPENDIDO);
			personaSuspension.setFechaUltimaModificacion(new Date());
			manejadorEstadoPersonaTipoServicio.actualizar(personaSuspension);
			
			//Se crea historico
			HistoricoEstadoPersonaTipoServicio historico = new HistoricoEstadoPersonaTipoServicio();
			if(tipoServicio.equals(UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA)){
				historico.setEstado(UtilDominios.ESTADO_ARBITROS_HABILITADO);
			}else{
				historico.setEstado(UtilDominios.ESTADO_CONCILIADOR_ACTIVO);	
			}
			historico.setIdPersona(idPersona);
			historico.setTipoServicio(tipoServicio);
			historico.setFecha(new Date());
			historico.setFechaUltimaModificacion(new Date());
			historico.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			manejadorHistoricoEstadoPersonaTipoServicio.crear(historico);
			
			//Correo
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO378.toString(),nombreUsuario,
					df.format(solicitud.getFechaSolicitud()),idRolPersona.getDescripcionSolicitud());
			mensajeCorreo.add(mensaje);
			correoRolPersonaCasoFacade.envioDeCorreo(UtilConstantes.ASUNTO_TIPO_SOLICITUD_SUSPENSION+nombreRol, personasNotificar, null, null, mensajeCorreo, null, null, null, true);			
			
			
		}
		//Si el tipo de solicitud es activación
		else if(UtilDominios.TIPO_SOLICITUD_PRESTADOR_ACTIVACION.equals(idRolPersona.getTipo())){
			solicitud.setTipo(UtilDominios.TIPO_SOLICITUD_PRESTADOR_ACTIVACION);
			solicitud.setEstadoSolicitud(UtilDominios.ESTADO_SOLICITUD_PRESTADOR_PENDIENTE);
			
			//Correo
			mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO379.toString(),nombreUsuario,nombreRol);
			mensajeCorreo.add(mensaje);
			correoRolPersonaCasoFacade.envioDeCorreo(UtilConstantes.ASUNTO_TIPO_SOLICITUD_ACTIVACION+nombreRol, personasNotificar, null, null, mensajeCorreo, null, null, null, true);			
			
		}
		//Si el tipo de solicitud es adición o cambio de materia
		else if(UtilDominios.TIPO_SOLICITUD_PRESTADOR_ADICION_CAMBIO_MATERIA.equals(idRolPersona.getTipo())){
			solicitud.setTipo(UtilDominios.TIPO_SOLICITUD_PRESTADOR_ADICION_CAMBIO_MATERIA);
			solicitud.setEstadoSolicitud(UtilDominios.ESTADO_SOLICITUD_PRESTADOR_PENDIENTE);
			
			//Correo
			mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO386.toString(),nombreUsuario);
			mensajeCorreo.add(mensaje);
			correoRolPersonaCasoFacade.envioDeCorreo(UtilConstantes.ASUNTO_TIPO_SOLICITUD_ADICION_CAMBIO_MATERIA, personasNotificar, null, null, mensajeCorreo, null, null, null, true);			
			
		}
		
		//Se crea la solicitud
		manejadorSolicitudPrestador.crear(solicitud);
		
	}
	
	
	// protected region metodos adicionales end
	
}
