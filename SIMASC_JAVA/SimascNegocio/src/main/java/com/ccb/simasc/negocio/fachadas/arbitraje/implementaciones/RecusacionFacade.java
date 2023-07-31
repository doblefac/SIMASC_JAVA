package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorParteDeLaRecusacion;
import com.ccb.simasc.integracion.manejadores.ManejadorRecusacion;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICartaPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParteDeLaRecusacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRecusacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.transversal.dto.RecusacionDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosGenerarCartaDTO;
import com.ccb.simasc.transversal.dto.formularios.RegistroRecusacionDTO;
import com.ccb.simasc.transversal.entidades.CartaPersona;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Recusacion;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Recusacion}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class RecusacionFacade extends AccesoFacade<Recusacion, Long, RecusacionDTO> implements IRecusacionFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorRecusacion manejadorRecusacion;
	
	@EJB
	private ManejadorParteDeLaRecusacion manejadorPartesDeLaRecusacion;
	
	@EJB
	private ManejadorRol manejadorRol;
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	
	@EJB
	private IRolPersonaCasoFacade rolPersonaCasoFacade;
	
	@EJB
	private IParteDeLaRecusacionFacade partesDeLaRecusacionFacade;
	
	@EJB
	private IPersonaFacade personaFacade;
	
	@EJB
	private IEventoFacade eventoFacade;

	@EJB
	private ICorreoElectronicoFacade correoElectronicoFacade;
	
	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;
	
	@EJB
	private ICartaPersonaFacade cartaPersonaFacade;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorRecusacion;
	}

	@Override
	public RecusacionDTO transformarSinDependencias(Recusacion obj) {
		RecusacionDTO dto = new RecusacionDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public RecusacionDTO transformarConDependencias(Recusacion obj) {
		RecusacionDTO dto = new RecusacionDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Recusacion transformarEntidadConDependencias(Recusacion obj) {
		Recusacion dto = new Recusacion();
		dto = new RecusacionDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Recusacion transformarEntidadSinDependencias(Recusacion obj) {
		Recusacion dto = new Recusacion();
		dto = new RecusacionDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	@Override
	public Recusacion crearRecusacion(Recusacion obj) {
		return manejadorRecusacion.crearRecusacion(obj);
	}
	
	
	
	public Recusacion registrarRecusacion(Long idCaso, Long idArbitro,List<Long> partes, String motivo, Long idDocumento){
		Recusacion recusacion = new Recusacion();
	
		RolPersonaCaso rpc = manejadorRolPersonaCaso.consultarArbitroAsignadoAlCaso(idArbitro, idCaso);
		Long idRolArbitro = rpc.getRolPersonaCasoPK().getIdRol();
		
		rpc.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_RECUSADO);
		rpc.setFechaUltimaModificacion(new Date());
		rpc.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		manejadorRolPersonaCaso.crearRolPersonaCaso(rpc);
		
		recusacion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		recusacion.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		recusacion.setFechaUltimaModificacion(new Date());
		
		recusacion.setMotivoRecusacion(motivo);
		recusacion.setFechaRecusacion(new Date());
		recusacion.setEstado(UtilDominios.ESTADO_RECUSACION_RECUSADO);
		recusacion.setIdCasoArbitro(idCaso);
		recusacion.setIdRolArbitro(idRolArbitro);
		recusacion.setIdPersonaArbitro(idArbitro);
		recusacion.setIdDocRecusacion(idDocumento); // PEDIENTE DE DEFEINIR COMO SE ADJUBNTA UN DOCUMENTO
		recusacion = manejadorRecusacion.crearRecusacion(recusacion);
		manejadorRecusacion.getEntityManager().flush();
		for(Long idParte : partes){
			RolPersonaCaso parteRecusadora = manejadorRolPersonaCaso.consultarPersonaAsignadaCaso(idParte, idCaso);
			partesDeLaRecusacionFacade.crearParteDeRecusacion(parteRecusadora.getRol().getIdRol(), parteRecusadora.getPersona().getIdPersona(), idCaso, recusacion.getIdRecusacion());
		}
		return recusacion;
	}
	
	public Recusacion registrarRespuestaRecusacion(Long idRecusacion, Date fechaRespuesta, boolean acepta, Long idDocumento){
		Recusacion recusacion = manejadorRecusacion.buscar(idRecusacion);
		recusacion.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		recusacion.setFechaUltimaModificacion(new Date());
		
		recusacion.setFechaRespuestaArbitro(fechaRespuesta);
		recusacion.setAceptaRecusacion(acepta);
		recusacion.setEstado(acepta?UtilDominios.ESTADO_RECUSACION_RESPUESTA_ACEPTADA:UtilDominios.ESTADO_RECUSACION_RESPUESTA_RECHAZADA);
		recusacion.setIdDocRespuesta(idDocumento); // PEDIENTE DE DEFEINIR COMO SE ADJUBNTA UN DOCUMENTO
		return manejadorRecusacion.crearRecusacion(recusacion);
	}
	
	public Recusacion registrarConfirmacionRecusacion(Long idRecusacion, Date fechaConfirmacion, boolean confirmado, String juzgadoConfirmacion, Long idDocumento){
		Recusacion recusacion = manejadorRecusacion.buscar(idRecusacion);
		recusacion.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		recusacion.setFechaUltimaModificacion(new Date());
	
		recusacion.setFechaConfirmacion(fechaConfirmacion);
		recusacion.setConfirmacionNombramiento(confirmado);
		recusacion.setJuzgadoConfirmacion(juzgadoConfirmacion);
		recusacion.setEstado(confirmado?UtilDominios.ESTADO_RECUSACION_NOMBRAMIENTO_CONFIRMADO:UtilDominios.ESTADO_RECUSACION_NOMBRAMIENTO_RECHAZADO);
		recusacion.setIdDocConfirmacion(idDocumento); 
		return manejadorRecusacion.crearRecusacion(recusacion);
	}
	
	@Override
	public List<Object[]> obtenerRecusacionesArbitro(Long idCaso, Long idArbitro){
		List<Object[]> recusaciones = new ArrayList<>();
		recusaciones.addAll(manejadorRecusacion.consultarRecusacionesArbitro(idCaso, idArbitro));
		return recusaciones;
	}
	
	@Override
	public List<Object[]> obtenerRecusacionesArbitro(Long idArbitro){
		List<Object[]> recusaciones = new ArrayList<>();
		recusaciones.addAll(manejadorRecusacion.consultarRecusacionesArbitro(idArbitro));
		return recusaciones;
	}
	
	@Override
	public void registrarRecusacionArbitro(RegistroRecusacionDTO recusacionDTO) throws SIMASCNegocioExcepcion {
		// Registro de recusacion
		if(recusacionDTO.getIdRecusado() != null){
			registrarRecusacion(recusacionDTO.getIdCaso(), recusacionDTO.getIdRecusado(), recusacionDTO.getIdRecusan(),
					recusacionDTO.getMotivo(), recusacionDTO.getIdDocumentoRecusacion());
			
		}else{
			for (Long recusadosFor : recusacionDTO.getIdRecusados()) {				
					registrarRecusacion(recusacionDTO.getIdCaso(), recusadosFor, recusacionDTO.getIdRecusan(),
							recusacionDTO.getMotivo(), recusacionDTO.getIdDocumentoRecusacion());				
			}
		}
			
		String nombreArbitros ="";

		// Registro de evento
		if(recusacionDTO.getIdRecusado() != null){
			Persona arbitro = personaFacade.buscar(recusacionDTO.getIdRecusado());
			nombreArbitros = arbitro.getNombreCompleto();
		}else{
			List<String> nombres = new ArrayList<String>();
			for (Long personaFor : recusacionDTO.getIdRecusados()) {
				Persona arbitro = personaFacade.buscar(personaFor);
				if(arbitro != null){
					nombres.add(arbitro.getNombreCompleto());
				}						
			}
			if(!nombres.isEmpty()){
				nombreArbitros = UtilOperaciones.nombresConComas(nombres);
			}
		}
		String observaciones = "Se ha registrado una recusación para el(los) árbitro(s) " + nombreArbitros;
		eventoFacade.registrarEvento(recusacionDTO.getIdCaso(), UtilDominios.TIPO_EVENTO_REGISTRO_RECUSACION,
				observaciones, UtilConstantes.USUARIO_DEFECTO_SIMASC, new Date(), UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		Long idAbogado = envioCorreoRecusacion(recusacionDTO, nombreArbitros);		
		if(recusacionDTO.getIdRecusado() != null){
			envioCartaRecusacion(recusacionDTO.getIdCaso(), idAbogado);
			}

		}
	
	private void envioCartaRecusacion(Long idCaso, Long idNotificado){
		ParametrosGenerarCartaDTO filtros = new ParametrosGenerarCartaDTO();
		filtros.setIdCaso(idCaso);
		filtros.setIdPlantilla("5");
		filtros.setIndicadorNotificacion("SI");
		List<Long> notificados = new ArrayList<Long>();
		notificados.add(idNotificado);
		filtros.setListaIdNotificados(notificados);
		filtros.setListaIdInvitados(new ArrayList<Long>());
		List<CartaPersona> cartasGeneradasListDTO = cartaPersonaFacade.generarCarta(filtros,null);
		// ENVIO CARTA, codigo no modficado de lo que se entrego arbitraje
		cartaPersonaFacade.enviarCarta(cartasGeneradasListDTO.get(0), false, true);
		
	}
	/**
	 * envia el correo con la informacion de la recusacion al abogado del caso 
	 * @param recusacionDTO
	 * @param nombreArbitros
	 * @return idAbogado al cual se le envio el correo.
	 */
	private Long envioCorreoRecusacion(RegistroRecusacionDTO recusacionDTO, String nombreArbitros){
		Long idAbogado = null;
		List<Persona> abogados = (List<Persona>) personaFacade.consultarPorRolCaso(recusacionDTO.getIdCaso(),
				UtilDominios.ROL_PERSONA_ABOGADO);
		if (!abogados.isEmpty() && abogados.get(0) != null) {
			if (!abogados.get(0).getIdPersona().equals(recusacionDTO.getIdUsuario())) {
				List<String> cuerpoCorreo = new ArrayList<>();
				String nombresPartes = "";

				for (Long idParte : recusacionDTO.getIdRecusan()) {
					Persona parte = personaFacade.buscar(idParte);
					nombresPartes += parte.getNombreCompleto() + ", ";
				}
				nombresPartes = nombresPartes.substring(0, nombresPartes.length() - 2);
				
				cuerpoCorreo.add("La(s) parte(s) "+ nombresPartes + " recuso al(los) árbitro(s) "
						+ nombreArbitros + " al caso " + recusacionDTO.getIdCaso() + 
						". Por favor verifique la información y notifique a los árbitros. ");
				
				correoRolPersonaCasoFacade.envioDeCorreo("Recusación", abogados, null, null, cuerpoCorreo,
						recusacionDTO.getIdCaso(), null, null, false);
				
				idAbogado = abogados.get(0).getIdPersona();

			}
		} else {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR151.toString()));
		}
		return idAbogado;

	}
	

	// protected region metodos adicionales end
	
}
