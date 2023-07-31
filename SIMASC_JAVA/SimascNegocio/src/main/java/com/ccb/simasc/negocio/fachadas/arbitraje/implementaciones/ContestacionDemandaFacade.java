package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorContestacionDemanda;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorEvento;
import com.ccb.simasc.integracion.manejadores.ManejadorParteContestacion;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IContestacionDemandaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDiaFestivoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.transversal.dto.ContestacionDemandaDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.ContestacionDemanda;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.Evento;
import com.ccb.simasc.transversal.entidades.ParteContestacion;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link ContestacionDemanda}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ContestacionDemandaFacade extends AccesoFacade<ContestacionDemanda, Long, ContestacionDemandaDTO>
		implements IContestacionDemandaFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	@EJB
	private ManejadorContestacionDemanda manejadorContestacionDemanda;

	@EJB
	private ManejadorParteContestacion manejadorParteContestacion;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	
	@EJB
	private ManejadorCaso  manejadorCaso;
	
	@EJB	
	private ManejadorDocumento manejadorDocumento; 
	
	@EJB
	private ManejadorEvento manejadorEvento;
	
	@EJB
	private ManejadorRol manejadorRol;

	@EJB
	private IEventoFacade eventoFacade;

	@EJB
	private IPersonaFacade personaFacade;

	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;
	
	@EJB
	private IDiaFestivoFacade diaFestivoFacade;
	
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorContestacionDemanda;
	}

	@Override
	public ContestacionDemandaDTO transformarSinDependencias(ContestacionDemanda obj) {
		ContestacionDemandaDTO dto = new ContestacionDemandaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ContestacionDemandaDTO transformarConDependencias(ContestacionDemanda obj) {
		ContestacionDemandaDTO dto = new ContestacionDemandaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ContestacionDemanda transformarEntidadConDependencias(ContestacionDemanda obj) {
		ContestacionDemanda dto = new ContestacionDemanda();
		dto = new ContestacionDemandaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ContestacionDemanda transformarEntidadSinDependencias(ContestacionDemanda obj) {
		ContestacionDemanda dto = new ContestacionDemanda();
		dto = new ContestacionDemandaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public void registrarContestacion(ContestacionDemanda contestacionDemanda) throws SIMASCNegocioExcepcion {
		Rol rolActual = null;
		if(appContext != null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getRolPrincipal() !=null){
			rolActual = manejadorRol.consultarRolPorId(Long.parseLong(appContext.getContextoSesion().getRolPrincipal()));
		}
		Date fechaContestacion  = contestacionDemanda.getFechaUltimaModificacion();
		List<String> nombresPartes = new ArrayList<String>();
		StringBuilder mensajeEvento = new StringBuilder();
		StringBuilder accionesParte = new StringBuilder("Contestación de la demanda");
		if (contestacionDemanda.getParteContestacionList() != null
				&& !contestacionDemanda.getParteContestacionList().isEmpty()) {
			mensajeEvento.append("la(s) partes ");
		} else if(rolActual != null && (UtilDominios.ROL_PERSONA_PARTE_DEMANDADA.equals(rolActual.getNombre()) ||
				UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE.equals(rolActual.getNombre())  || 
				UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO.equals(rolActual.getNombre()) ||
				UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE.equals(rolActual.getNombre()))){
			mensajeEvento.append("la parte ");
		}else{
			mensajeEvento.append("se ");
		}
		// Guardar la contestaicon de la demanda
		ContestacionDemanda contestacionSinDep = new ContestacionDemandaDTO()
				.transformarEntidadSinDependencias(contestacionDemanda);
		contestacionSinDep.setFechaUltimaModificacion(new Date());
		contestacionSinDep.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		manejadorContestacionDemanda.crear(contestacionSinDep);

		// guarda las partes que se pronunciaron
		if (contestacionDemanda.getParteContestacionList() != null && !contestacionDemanda.getParteContestacionList().isEmpty() ) {
			for (ParteContestacion parteFor : contestacionDemanda.getParteContestacionList()) {
				parteFor.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				parteFor.setFechaUltimaModificacion(new Date());
				parteFor.getParteContestacionPK()
						.setIdContestacionDemanda(contestacionSinDep.getIdContestacionDemanda());
				manejadorParteContestacion.crear(parteFor);
				Persona persona = manejadorPersona.buscar(parteFor.getParteContestacionPK().getIdPersona());
				nombresPartes.add(persona.getNombreCompleto());
			}
		}else if(rolActual != null && (UtilDominios.ROL_PERSONA_PARTE_DEMANDADA.equals(rolActual.getNombre()) ||
				UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE.equals(rolActual.getNombre()) || 
				UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO.equals(rolActual.getNombre()) ||
				UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE.equals(rolActual.getNombre())) ){		
			RolPersonaCaso rolPersonaCaso = 	
					manejadorRolPersonaCaso.consultarRolPersonaCaso( Long.parseLong(appContext.getContextoSesion().getIdUsuario()),
					contestacionDemanda.getIdCaso(), rolActual.getNombre());
			if(rolPersonaCaso == null){
				throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR519.toString()));		
				}
			ParteContestacion parteContestacion = new ParteContestacion();
			parteContestacion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			parteContestacion.setFechaUltimaModificacion(new Date());
			parteContestacion.getParteContestacionPK().setIdCaso(contestacionDemanda.getIdCaso());
			parteContestacion.getParteContestacionPK().setIdContestacionDemanda(contestacionSinDep.getIdContestacionDemanda());
			parteContestacion.getParteContestacionPK().setIdPersona(Long.parseLong(appContext.getContextoSesion().getIdUsuario()));
			parteContestacion.getParteContestacionPK().setIdRol(rolActual.getIdRol());
			manejadorParteContestacion.crear(parteContestacion);
			nombresPartes.add(rolPersonaCaso.getPersona().getNombreCompleto());		
			
			
		}

		// guarda el evento con su tipo y mensaje correspondiente.
		mensajeEvento.append(UtilOperaciones.nombresConComas(nombresPartes));

		if (contestacionDemanda.getIdDocumentoContestacion() != null) {

			eventoFacade.registrarEvento(contestacionDemanda.getIdCaso(), UtilDominios.TIPO_EVENTO_CONTESTACION_DEMANDA,
					mensajeEvento.toString() + "ha(n) presentado contestación a la demanda.",
					contestacionDemanda.getIdUsuarioModificacion(),fechaContestacion,UtilDominios.ESTADO_REGISTRO_ACTIVO);
		}

		if (contestacionDemanda.getIdDocumentoExcepciones() != null) {
			accionesParte.append(" | Presentación de excepciones");
			eventoFacade.registrarEvento(contestacionDemanda.getIdCaso(),	UtilDominios.TIPO_EVENTO_PRESENTACION_EXCEPCIONES,
					mensajeEvento.toString() + "ha(n) presentado excepciones a la demanda.",
					contestacionDemanda.getIdUsuarioModificacion(),fechaContestacion,UtilDominios.ESTADO_REGISTRO_ACTIVO);
			
		}

		if (contestacionDemanda.getIdDocumentoDemReconvencion() != null) {
			accionesParte.append(" | Presentación de reconvenciones");
			eventoFacade.registrarEvento(contestacionDemanda.getIdCaso(), UtilDominios.TIPO_EVENTO_PRESENTACION_RECONVENCION,
					mensajeEvento.toString() + "ha(n) presentado demanda de reconvención..",
					contestacionDemanda.getIdUsuarioModificacion(),fechaContestacion,UtilDominios.ESTADO_REGISTRO_ACTIVO);
		}

		// Envio de correos

		List<String> cuerpoCorreo = new ArrayList<String>();

		StringBuilder cuerpoCorreoCad = new StringBuilder("La parte ")
				.append(UtilOperaciones.nombreConComasYPunto(nombresPartes,false));
		cuerpoCorreoCad.append(" en el caso (").append(contestacionDemanda.getIdCaso())
				.append(" ha realizado las siguientes acciones ");
		cuerpoCorreoCad.append(accionesParte.toString()).append(").");
		cuerpoCorreo.add(cuerpoCorreoCad.toString());	
		String asunto = "Contestación de la demanda.";
		enviarCorreoRolesDelCaso(true, true, contestacionDemanda.getIdCaso(), cuerpoCorreo, asunto, true, contestacionDemanda.getIdUsuarioModificacion());
		
	}
	
	@Override
	public void registrarReconvencion(ContestacionDemanda contestacionDemanda) throws SIMASCNegocioExcepcion {
		Rol rolActual = null;
		if(appContext != null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getRolPrincipal() !=null){
			rolActual = manejadorRol.consultarRolPorId(Long.parseLong(appContext.getContextoSesion().getRolPrincipal()));
		}else{			
			throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR153.toString()));
		}
		Date fechaContestacion  = contestacionDemanda.getFechaUltimaModificacion();
		List<String> nombresPartes = new ArrayList<String>();
		StringBuilder mensajeEvento = new StringBuilder();	
		
		List<ContestacionDemanda> contestacionesPrevia =
				manejadorContestacionDemanda.consultarContestacionDemandaPorCaso(contestacionDemanda.getIdCaso());
		if(contestacionesPrevia == null || contestacionesPrevia.isEmpty()){
			throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR516.toString()));			
		}
		// Guardar la contestaicon de la demanda
		ContestacionDemanda contestacionSinDep = new ContestacionDemandaDTO().transformarEntidadSinDependencias(contestacionDemanda);
		contestacionSinDep.setFechaUltimaModificacion(new Date());
		contestacionSinDep.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		contestacionSinDep.setIdCaso(null);
		contestacionSinDep.setIdContestacionDemReconvencion(contestacionesPrevia.get(0).getIdContestacionDemanda());
		manejadorContestacionDemanda.crear(contestacionSinDep);
		if(rolActual == null){			
			throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR153.toString()));
		}
		
		RolPersonaCaso rolPersonaCaso = 	
					manejadorRolPersonaCaso.consultarRolPersonaCaso( Long.parseLong(appContext.getContextoSesion().getIdUsuario()),
					contestacionDemanda.getIdCaso(), rolActual.getNombre());
		ParteContestacion parteContestacion = new ParteContestacion();
		parteContestacion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		parteContestacion.setFechaUltimaModificacion(new Date());
		parteContestacion.getParteContestacionPK().setIdCaso(contestacionDemanda.getIdCaso());
		parteContestacion.getParteContestacionPK().setIdContestacionDemanda(contestacionSinDep.getIdContestacionDemanda());
		parteContestacion.getParteContestacionPK().setIdPersona(Long.parseLong(appContext.getContextoSesion().getIdUsuario()));
		parteContestacion.getParteContestacionPK().setIdRol(rolActual.getIdRol());
		manejadorParteContestacion.crear(parteContestacion);			
		

		// guarda el evento con su tipo y mensaje correspondiente.
		mensajeEvento.append("la parte ").append(rolPersonaCaso.getPersona().getNombreCompleto());	

		eventoFacade.registrarEvento(contestacionDemanda.getIdCaso(), UtilDominios.TIPO_EVENTO_CONTESTACION_DEMANDA,
					mensajeEvento.toString() + "ha presentado contestación a la demanda.",
					contestacionDemanda.getIdUsuarioModificacion(),fechaContestacion,UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		// CORREOS DE LOS ARBITROS

		List<String> cuerpoCorreo = new ArrayList<String>();
		StringBuilder cuerpoCorreoCad = new StringBuilder("La parte ")
				.append(UtilOperaciones.nombresConComas(nombresPartes));
		cuerpoCorreoCad.append(" en el caso (").append(contestacionDemanda.getIdCaso())
				.append(" ha realizado la contestacion de la demanda de reconvenciones.");
		cuerpoCorreo.add(cuerpoCorreoCad.toString());		
		String asunto = "Contestación de la demanda.";
		enviarCorreoRolesDelCaso(true, true, contestacionDemanda.getIdCaso(), cuerpoCorreo, asunto, true, contestacionDemanda.getIdUsuarioModificacion());
		

	}
	
	private void enviarCorreoRolesDelCaso(boolean arbitros , boolean secretario, Long idCaso,List<String> cuerpoCorreo , String asunto ,
				boolean isCertificado, String usuarioModificacion){
		List<String> estadosArbitros = new ArrayList<String>();
		estadosArbitros.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		List<RolPersonaCaso> personaDelCaso = new ArrayList<RolPersonaCaso>();

		if (arbitros) {
			List<RolPersonaCaso> arbitrosCaso = (List<RolPersonaCaso>) manejadorRolPersonaCaso.consultarArbitros(
					idCaso, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL, null, estadosArbitros);
			if(arbitrosCaso != null && !arbitrosCaso.isEmpty()){
				personaDelCaso.addAll(arbitrosCaso);
			}					
			Caso casoActual = manejadorCaso.buscar(idCaso);
			casoActual.setEstadoCaso(UtilDominios.ESTADO_CASO_CONTESTACION_DEMANDA);
			casoActual.setIdUsuarioModificacion(usuarioModificacion);
			casoActual.setFechaUltimaModificacion(new Date());
			manejadorCaso.actualizar(casoActual);			
		}
		if(secretario){
			RolPersonaCaso secretarioDelTribunal = manejadorRolPersonaCaso.consultarSecretarioDelCaso(idCaso);
			if(secretarioDelTribunal != null){
				personaDelCaso.add(secretarioDelTribunal);
			}			
		}
		
		if(!personaDelCaso.isEmpty()){
			correoRolPersonaCasoFacade.envioDeCorreo(asunto, null, personaDelCaso, null, cuerpoCorreo, idCaso, null, null,isCertificado);	
		}	
	}
	

	

	@Override
	public List<ContestacionDemanda> consultarContestacionDemandaPorCaso(Long idCaso) throws SIMASCNegocioExcepcion {
		
		 List<ContestacionDemanda> demandasContestadasSinDep = new ArrayList<ContestacionDemanda>();
		List<ContestacionDemanda> demandasContestadas = manejadorContestacionDemanda.consultarContestacionDemandaPorCaso(idCaso);
		for (ContestacionDemanda contestacionFor: demandasContestadas) {			
			
			demandasContestadasSinDep.add(new ContestacionDemandaDTO().transformarEntidadSinDependencias(contestacionFor));
		}
		
		return demandasContestadasSinDep;
	
	}
	
	@Override
	public Boolean validarFechaContestacion(Long idCaso, String nombreRol){
		Boolean validacionFecha = false;
		if(UtilDominios.ROL_PERSONA_PARTE_DEMANDADA.equals(nombreRol) || UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO.equals(nombreRol)){
			List<Documento> documentoAutoAdm =			
					manejadorDocumento.consultarDocumentosCasoTipo(idCaso, Arrays.asList(UtilDominios.TIPO_DOCUMENTO_DIG_AUTO));
			if(!documentoAutoAdm.isEmpty() && documentoAutoAdm.get(0).getFechaRadicacion() != null){
				int diasHabiles = 	diaFestivoFacade.obtenerDiasHabilesEntreFechas(
						documentoAutoAdm.get(0).getFechaRadicacion(), new Date());
				if(diasHabiles < 20){
					validacionFecha = true;
				}
			}else{
				throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR515.toString()));
			}
			
			
		}else if(UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE.equals(nombreRol) || UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE.equals(nombreRol)){
			ArrayList<String> eventos = new ArrayList<String>();
			eventos.add(UtilDominios.TIPO_EVENTO_PRESENTACION_RECONVENCION);
			List<Evento> eventosReconvecion = manejadorEvento.consultarEventoCaso(idCaso, eventos);
			if(!eventosReconvecion.isEmpty() && eventosReconvecion.get(0).getFechaEvento() != null){
				int diasHabiles = 	diaFestivoFacade.obtenerDiasHabilesEntreFechas(
						eventosReconvecion.get(0).getFechaEvento(), new Date());
				if(diasHabiles < 20){
					validacionFecha = true;
				}				
			}			

		}
		
		return validacionFecha;
	}
	
	@Override
	public Boolean validarContestacionReconvencion(Long idCaso){
		
		List<ContestacionDemanda> demandasContestadas = manejadorContestacionDemanda.consultarContestacionDemandaPorCaso(idCaso);
		if(demandasContestadas == null || demandasContestadas.isEmpty()){
			throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR516.toString()));
		}		
		ContestacionDemanda contestacionReconvencion = 	manejadorContestacionDemanda.consultarContestacionDemandaPorDemandaReconvencion
			(demandasContestadas.get(0).getIdContestacionDemanda());
		
		return contestacionReconvencion == null;		

	}
	
	
	
	
	

	// protected region metodos adicionales end

}
