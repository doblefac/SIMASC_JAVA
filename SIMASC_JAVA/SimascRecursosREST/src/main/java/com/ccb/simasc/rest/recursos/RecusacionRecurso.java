package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones.EventoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICartaPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParteDeLaRecusacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaServicioMateriaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRecusacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.CambioEstadoSuplenteDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosGenerarCartaDTO;
import com.ccb.simasc.transversal.dto.formularios.RegistroRecusacionDTO;
import com.ccb.simasc.transversal.entidades.CartaPersona;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.EventoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Recusacion;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.RolPersonaCasoPK;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports recurso end

/**
 * Recurso REST Recusacion
 * @author sMartinez
 */
@Path( "recusacion" )
@Stateless
public class RecusacionRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(RecusacionRecurso.class);
    private static final Class<Recusacion> enClass= Recusacion.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso

	@EJB
	private CartaPersonaRecurso cartaPersonaRecurso;
	
	@EJB
	private EventoRolPersonaCasoFacade eventoRolPersonaCasoFacade;

	@EJB
	private IRolPersonaCasoFacade rolPersonaCasoFacade;

	@EJB
	private ICartaPersonaFacade cartaPersonaFacade;

	@EJB
	private IPersonaFacade personaFacade;

	@EJB
	private ICasoFacade casoFacade;

	@EJB
	private IEventoFacade eventoFacade;

	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;

	@EJB
	private ICorreoElectronicoFacade correoElectronicoFacade;

	@EJB
	private IPersonaServicioMateriaFacade personaServicioMateriaFacade;

	@EJB
	private IParteDeLaRecusacionFacade parteDeLaRecusacionFacade;

	@EJB
	private IRecusacionFacade recusacionFacade;

	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Regsitra una recusaciÃ³n a un Ã¡rbitro
	 * 
	 * @param id
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/registrarRecusacionArbitro/")
	public Response registrarRecusacionArbitro(RegistroRecusacionDTO recusacionDTO) {
		Response response = null;
		try {
			recusacionFacade.registrarRecusacionArbitro(recusacionDTO);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>("") {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Regsitra una recusaciÃ³n a un Ã¡rbitro
	 * 
	 * @param id
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/responderRecusacionAboCor/")
	public Response responderRecusacionAboCor(RegistroRecusacionDTO recusacionDTO) {
		Response response = null;
		try {
			Recusacion recusacion = recusacionFacade.buscar(recusacionDTO.getIdRecusacion());
			Persona arbitro = personaFacade.buscar(recusacion.getIdPersonaArbitro());
			Caso caso = recusacion.getRolPersonaCaso().getCaso();
			RolPersonaCasoPK pk = new RolPersonaCasoPK(recusacion.getIdPersonaArbitro(),
					recusacion.getRolPersonaCaso().getCaso().getIdCaso(),
					recusacion.getRolPersonaCaso().getRol().getIdRol());
			RolPersonaCaso rpc = rolPersonaCasoFacade.buscar(pk);

			// SE GUARDA RESPUESTA A RECUSACION
			recusacionFacade.registrarRespuestaRecusacion(recusacionDTO.getIdRecusacion(),
					recusacionDTO.getFechaRespuesta(), recusacionDTO.isAceptaRecusacion(),
					recusacionDTO.getIdDocumentoRespuesta());
			Recusacion rec = recusacionFacade.registrarConfirmacionRecusacion(recusacionDTO.getIdRecusacion(),
					recusacionDTO.getFechaConfirmacion(), recusacionDTO.isConfirmaNombramiento(),
					recusacionDTO.getJuzgadoConfirmacion(), recusacionDTO.getIdDocumentoConfirmacion());

			// RUTA DEL CASO (RESPUESTA)
			String observaciones = "El árbitro (" + arbitro.getNombreCompleto() + ") ha ("
					+ (recusacionDTO.isAceptaRecusacion() ? "Aceptado" : "Rechazado") + ") la recusación";
			eventoFacade.registrarEvento(caso.getIdCaso(), UtilDominios.TIPO_EVENTO_REGISTRO_RESPUESTA_RECUSACION,
					observaciones, UtilConstantes.USUARIO_DEFECTO_SIMASC, new Date(),
					UtilDominios.ESTADO_REGISTRO_ACTIVO);

			// RUTA DEL CASO (CONFIRMACION NOMBRMIENTO)
			String evento = "";
			String descripcion = "";

			if (recusacionDTO.isConfirmaNombramiento()) {
				evento = UtilDominios.TIPO_EVENTO_CONFIRMACION_NOMBRAMIENTO;
				descripcion = "Se confirmó el nombramiento del Ã¡rbitro (" + arbitro.getNombreCompleto() + ")";
			} else {
				evento = UtilDominios.TIPO_EVENTO_ACEPTACION_RECUSACION;
				descripcion = "Se ha aceptado la recusación para el Ã¡rbitro (" + arbitro.getNombreCompleto() + ")";
			}

			eventoFacade.registrarEvento(caso.getIdCaso(), evento, descripcion, UtilConstantes.USUARIO_DEFECTO_SIMASC,
					new Date(), UtilDominios.ESTADO_REGISTRO_ACTIVO);

			// LIBERACION DE ARBITRO
			if (!recusacionDTO.isConfirmaNombramiento()) {

				if (rpc.getMetodoNombramiento() != null
						&& rpc.getMetodoNombramiento().equals(UtilDominios.NOMBRAMIENTO_POR_LA_CCB)) {
					// HABILITARLO *****************************
					rolPersonaCasoFacade.habilitarArbitro(rpc.getCaso().getIdCaso(), arbitro.getIdPersona(),
							UtilDominios.MOTIVO_HABILITACION_ARBITRO_ACEPTA_RECUSACION);
					// NOMBRAR ARBITRO SUPLENTE COMO PRINCIPAL
					CambioEstadoSuplenteDTO cambioEstado = new CambioEstadoSuplenteDTO();
					cambioEstado.setIdCaso(caso.getIdCaso());
					cambioEstado.setIdPersona(arbitro.getIdPersona());
					cambioEstado.setIdUsuario(recusacionDTO.getIdUsuario().toString());
					rolPersonaCasoFacade.nombrarSuplentePrincipal(cambioEstado);

				}
			}

			// ************************************* CORREOS Y CARTAS
			// *****************************************************************************

			if (!recusacionDTO.isAceptaRecusacion()) {
				List<Persona> arbitros = rolPersonaCasoFacade.consultarArbitrosPrincipales(caso.getIdCaso());
				List<Long> notificados = new ArrayList<>();
				if (!arbitros.isEmpty() && arbitros.size() > 1) { // si hay mÃ¡s
																	// de un
																	// arbitro
					for (Persona arb : arbitros) {
						if (!arb.getIdPersona().equals(arbitro.getIdPersona()))
							notificados.add(arb.getIdPersona());
					}
					rec.setTipoDeConfirmacion(UtilDominios.TIPO_CONFIRMACION_NOMBRAMIENTO_ARBITRO);
					// GENERACION DE CARTAS
					ParametrosGenerarCartaDTO filtros = new ParametrosGenerarCartaDTO();
					filtros.setIdCaso(caso.getIdCaso());
					filtros.setIdPlantilla("5");
					filtros.setIndicadorNotificacion("SI");
					filtros.setListaIdNotificados(notificados);
					filtros.setListaIdInvitados(new ArrayList<Long>());
					List<CartaPersona> cartasGeneradasListDTO = cartaPersonaFacade.generarCarta(filtros,null);

					// ENVIO CARTA
					for (CartaPersona cartaPersona : cartasGeneradasListDTO) {
						if (recusacionDTO.getIdDocumentoRespuesta() != null)
							cartaPersona.setIdDocumento(recusacionDTO.getIdDocumentoRespuesta());
						cartaPersonaFacade.enviarCarta(cartaPersona, false, true);
					}
				} else {// Se genera carta al Juez Civil
					rec.setTipoDeConfirmacion(UtilDominios.TIPO_CONFIRMACION_NOMBRAMIENTO_JUZGADO);
					ParametrosGenerarCartaDTO filtros = new ParametrosGenerarCartaDTO();
					filtros.setIdCaso(caso.getIdCaso());
					filtros.setIdPlantilla("5");
					filtros.setIndicadorNotificacion("NO");
					filtros.setListaIdInvitados(new ArrayList<Long>());
					List<CartaPersona> cartasGeneradasListDTO = cartaPersonaFacade.generarCarta(filtros,null);
				}

				// correos van de ultimo
			}

			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>("") {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Verifica si la confirmaciÃ³n existe dada una recusaciÃ³n
	 * 
	 * @return
	 */
	private Boolean existeConfirmacion(Recusacion recusacion) {
		return (recusacion.getFechaConfirmacion() != null || recusacion.getDocumentoConfirmacion() != null
				|| recusacion.getJuzgadoConfirmacion() != null);
	}

	/**
	 * Verifica si la confirmaciÃ³n existe dada una recusaciÃ³n
	 * 
	 * @return
	 */
	private Boolean existeRespuesta(Recusacion recusacion) {
		return (recusacion.getFechaRespuestaArbitro() != null || recusacion.getDocumentoRespuesta() != null);
	}

	/**
	 * Regsitra una recusaciÃ³n a un Ã¡rbitro
	 * 
	 * @param id
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/responderRecusacionArb/")
	public Response responderRecusacionArb(RegistroRecusacionDTO recusacionDTO) {
		Response response = null;
		try {

			Recusacion recusacion = recusacionFacade.buscar(recusacionDTO.getIdRecusacion());
			Boolean existeRespuestaARecusacion = existeRespuesta(recusacion);
			Persona arbitro = personaFacade.buscar(recusacion.getIdPersonaArbitro());
			Caso caso = recusacion.getRolPersonaCaso().getCaso();
			RolPersonaCasoPK pk = new RolPersonaCasoPK(recusacion.getIdPersonaArbitro(),
					recusacion.getRolPersonaCaso().getCaso().getIdCaso(),
					recusacion.getRolPersonaCaso().getRol().getIdRol());
			RolPersonaCaso rpc = rolPersonaCasoFacade.buscar(pk);

			// REGISTRO DE RESPUESTA
			recusacionFacade.registrarRespuestaRecusacion(recusacionDTO.getIdRecusacion(),
					recusacionDTO.getFechaRespuesta(), recusacionDTO.isAceptaRecusacion(),
					recusacionDTO.getIdDocumentoRespuesta());

			// REGISTRAR CONFIRMACIÃ“N
			recusacionFacade.registrarConfirmacionRecusacion(recusacionDTO.getIdRecusacion(),
					recusacionDTO.getFechaConfirmacion(), recusacionDTO.isConfirmaNombramiento(),
					recusacionDTO.getJuzgadoConfirmacion(), recusacionDTO.getIdDocumentoConfirmacion());

			// RUTA DEL CASO (RESPUETSA)
			String observaciones = "El árbitro " + arbitro.getNombreCompleto() + " ha "
					+ (recusacionDTO.isAceptaRecusacion() ? "Aceptado" : "Rechazado") + " la recusaciÃ³n";
			eventoFacade.registrarEvento(caso.getIdCaso(), UtilDominios.TIPO_EVENTO_REGISTRO_RESPUESTA_RECUSACION,
					observaciones, UtilConstantes.USUARIO_DEFECTO_SIMASC, new Date(),
					UtilDominios.ESTADO_REGISTRO_ACTIVO);

			// EXTENCIONES
			if (!existeRespuestaARecusacion && recusacionDTO.isAceptaRecusacion()) 
				eliminarArbitroDelCaso(rpc, caso, arbitro);
			
			if (existeConfirmacion(recusacion)) {
				if (recusacionDTO.isConfirmaNombramiento()) {
					// ASIGNAR ULTIMO ESTADO DEL ARBITRO EN EL CASO
					rpc.setEstado(obtenerUltimoEstadoRecusado(rpc));
					rolPersonaCasoFacade.actualizar(rpc);
					
				} else 
					// NOMBRAR ARBITRO SUPLENTE COMO PRINCIPAL
					eliminarArbitroDelCaso(rpc, caso, arbitro);
				
			}
			// ENVIO DE CORREO AL ABOGADO
			List<Persona> abogados = (List<Persona>) personaFacade.consultarPorRolCaso(caso.getIdCaso(),
					UtilDominios.ROL_PERSONA_ABOGADO);
			if (!existeRespuestaARecusacion && !abogados.isEmpty() && abogados.get(0) != null
					&& !abogados.get(0).getIdPersona().equals(recusacionDTO.getIdUsuario())) {
				List<String> cuerpoCorreo = new ArrayList<>();
				cuerpoCorreo
						.add("El árbitro " + arbitro.getNombreCompleto() + " se pronunció sobre la recusación del caso "
								+ caso.getIdCaso() + " " + caso.getNombre() + ".");
				List<CorreoElectronico> correosAbogado = correoElectronicoFacade
						.consultaCorreosPersona(abogados.get(0).getIdPersona());
				List<CorreoElectronicoDTO> correosAbogadoDTO = (List<CorreoElectronicoDTO>) correoElectronicoFacade
						.transformarColeccionConDependencias(correosAbogado, new ArrayList<CorreoElectronicoDTO>());
				List<DocumentoDTO> adjuntos = new ArrayList<DocumentoDTO>();

				// Parametros
				ParametrosEnvioCorreoDTO parametrosCorreo = new ParametrosEnvioCorreoDTO();
				parametrosCorreo.setAsunto("Respuesta recusación");
				parametrosCorreo.setCuerpoCorreo(cuerpoCorreo);
				parametrosCorreo.setRolPersonaCaso(correosAbogadoDTO);
				parametrosCorreo.setRemitente(correosAbogadoDTO.get(0));
				parametrosCorreo.setIdCaso(caso.getIdCaso());
				parametrosCorreo.setAdjuntos(adjuntos);
				parametrosCorreo.setTipo(UtilDominios.TIPO_PLANTILLAS_CORREO_GENERAL);

				correoRolPersonaCasoFacade.enviarCorreo(parametrosCorreo);

			}

			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>("") {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	/**
	 * Elimina un arbitro del contexto de designaciÃ³n, si el arbitro es nombrado por la ccb. este es habilitado para sorteo, se intentarÃ¡ buscar un suplente habilitado y designado para tomar el servicio del caso
	 * @param rpc
	 */
	private void eliminarArbitroDelCaso(RolPersonaCaso rpc, Caso caso, Persona arbitro){
		if (rpc.getMetodoNombramiento() != null
				&& rpc.getMetodoNombramiento().equals(UtilDominios.NOMBRAMIENTO_POR_LA_CCB))
			rolPersonaCasoFacade.habilitarArbitro(caso.getIdCaso(), arbitro.getIdPersona(),
					UtilDominios.MOTIVO_HABILITACION_ARBITRO_ACEPTA_RECUSACION);
		CambioEstadoSuplenteDTO cambioEstado = new CambioEstadoSuplenteDTO();
		cambioEstado.setIdCaso(caso.getIdCaso());
		cambioEstado.setIdPersona(arbitro.getIdPersona());
		cambioEstado.setIdUsuario(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		rolPersonaCasoFacade.nombrarSuplentePrincipal(cambioEstado);
	}
	
	/**
	 * Obtiene el ultimo estado asignado a la persona en el caso antes de ser
	 * recusado
	 * 
	 * @param rpc
	 * @return
	 */
	private String obtenerUltimoEstadoRecusado(RolPersonaCaso rpc) {
		List<EventoRolPersonaCaso> eventos = (rpc!=null)?rpc.getEventoRolPersonaCasoList():null;
		// Ordenar eventos de forma descendente
		if (eventos != null) {
			java.util.Collections.sort(eventos, new Comparator<EventoRolPersonaCaso>() {

				public int compare(EventoRolPersonaCaso o1, EventoRolPersonaCaso o2) {
					Integer result = o1.getFechaUltimaModificacion().compareTo(o2.getFechaUltimaModificacion()) * -1;
					if(result==0)
							result= o1.getFechaDeAsignacion().compareTo(o2.getFechaDeAsignacion()) * -1;
					return result;
				}
			});
			// Recorrer eventos
			Boolean hasBeenSelected = false;
			for (Integer i = 0; i < eventos.size() && !hasBeenSelected; i++) {
				EventoRolPersonaCaso eRpc = eventos.get(i);
				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(eRpc.getEstadoRegistro())
						&& !UtilDominios.ESTADO_ROL_PERSONA_CASO_RECUSADO.equals(eRpc.getEstadoAsignado())
						&& !UtilDominios.ESTADO_ROL_PERSONA_CASO_COMUNICADO.equals(eRpc.getEstadoAsignado())
					)
					return eRpc.getEstadoAsignado();
			}
		}
		return UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR;
	}

	/**
	 * Regsitra una recusaciÃ³n a un Ã¡rbitro
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerRecusaciones/{idCaso}/{idArbitro}")
	public Response obtenerRecusaciones(@PathParam("idCaso") Long idCaso, @PathParam("idArbitro") Long idArbitro) {
		Response response = null;

		List<HashMap<String, Object>> datosMap = new ArrayList<>();
		try {

			List<Object[]> recusaciones = recusacionFacade.obtenerRecusacionesArbitro(idCaso, idArbitro);

			for (Object[] rec : recusaciones) {
				Persona persona = personaFacade.buscar(Long.valueOf(rec[2].toString()));
				HashMap<String, Object> datos = new HashMap<String, Object>();
				datos.put("idRecusacion", rec[0].toString());
				datos.put("idCaso", rec[1].toString());
				datos.put("nombreArbitro", persona.getNombreCompleto());
				datos.put("fechaRecusacion", (rec[3] != null) ? ((Date) rec[3]).getTime() : null);
				datos.put("motivoRecusacion", rec[4]);
				datos.put("idDocRecusacion", rec[5]);
				datos.put("aceptaRecusacion", rec[6]);
				datos.put("fechaRespuestaArbitro", (rec[7] != null) ? ((Date) rec[7]).getTime() : null);
				datos.put("idDocRespuestaArbitro", rec[8]);
				datos.put("confirmacionNombramiento", rec[9]);
				datos.put("fechaConfirmacion", (rec[10] != null) ? ((Date) rec[10]).getTime() : null);
				datos.put("idDocConfirmacion", rec[11]);
				datos.put("juzgadoConfirmacion", rec[12]);
				datos.put("tipoDeConfirmacion", rec[13]);
				datos.put("nombreCaso", rec[14]);

				if (rec[9] != null && (boolean) rec[9]) {
					if (rec[13] != null && rec[13].equals(UtilDominios.TIPO_CONFIRMACION_NOMBRAMIENTO_ARBITRO)) {
						datos.put("confirmaNombramiento", "Árbitros del caso");
					} else {
						datos.put("confirmaNombramiento", rec[12]);
					}
				}

				List<String> partesRec = new ArrayList<String>();
				List<Persona> partes = (List<Persona>) parteDeLaRecusacionFacade
						.consultarPartesRecusacion(Long.valueOf(rec[0].toString()));
				for (Persona parte : partes) {
					partesRec.add(parte.getNombreCompleto());
				}
				datos.put("partesRecusan", partesRec);
				datosMap.add(datos);

			}

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<HashMap<String, Object>>>(datosMap) {
					}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Regsitra una recusaciÃ³n a un Ã¡rbitro
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerRecusaciones/{idArbitro}")
	public Response obtenerRecusaciones(@PathParam("idArbitro") Long idArbitro) {
		Response response = null;

		List<HashMap<String, Object>> datosMap = new ArrayList<>();
		try {

			List<Object[]> recusaciones = recusacionFacade.obtenerRecusacionesArbitro(idArbitro);

			for (Object[] rec : recusaciones) {
				Persona persona = personaFacade.buscar(Long.valueOf(rec[2].toString()));
				HashMap<String, Object> datos = new HashMap<String, Object>();
				datos.put("idRecusacion", rec[0].toString());
				datos.put("idCaso", rec[1].toString());
				datos.put("nombreArbitro", persona.getNombreCompleto());
				datos.put("fechaRecusacion", rec[3]);
				datos.put("motivoRecusacion", rec[4]);
				datos.put("idDocRecusacion", rec[5]);
				datos.put("aceptaRecusacion", rec[6]);
				datos.put("fechaRespuestaArbitro", rec[7]);
				datos.put("idDocRespuestaArbitro", rec[8]);
				datos.put("confirmacionNombramiento", rec[9]);
				datos.put("fechaConfirmacion", rec[10]);
				datos.put("idDocConfirmacion", rec[11]);
				datos.put("juzgadoConfirmacion", rec[12]);
				datos.put("tipoDeConfirmacion", rec[13]);
				datos.put("nombreCaso", rec[14]);

				if (rec[9] != null && (boolean) rec[9]) {
					if (rec[13] != null && rec[13].equals(UtilDominios.TIPO_CONFIRMACION_NOMBRAMIENTO_ARBITRO)) {
						datos.put("confirmaNombramiento", "Árbitros del caso");
					} else {
						datos.put("confirmaNombramiento", rec[12]);
					}
				}

				List<String> partesRec = new ArrayList<String>();
				List<Persona> partes = (List<Persona>) parteDeLaRecusacionFacade
						.consultarPartesRecusacion(Long.valueOf(rec[0].toString()));
				for (Persona parte : partes) {
					partesRec.add(parte.getNombreCompleto());
				}
				datos.put("partesRecusan", partesRec);
				datosMap.add(datos);
			}

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<HashMap<String, Object>>>(datosMap) {
					}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Regsitra una recusaciÃ³n a un Ã¡rbitro
	 * 
	 * @param id
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/actualizarRecusacion/")
	public Response actualizarRecusacion(RegistroRecusacionDTO recusacion) {
		Response response = null;

		try {
			Recusacion recu = recusacionFacade.buscar(recusacion.getIdRecusacion());
			recu.setIdDocRecusacion(recusacion.getIdDocumentoRecusacion());
			recu.setIdDocRespuesta(recusacion.getIdDocumentoRespuesta());
			recu.setIdDocConfirmacion(recusacion.getIdDocumentoConfirmacion());
			recusacionFacade.crearRecusacion(recu);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>("ok") {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	// protected region metodos adicionales end


}
