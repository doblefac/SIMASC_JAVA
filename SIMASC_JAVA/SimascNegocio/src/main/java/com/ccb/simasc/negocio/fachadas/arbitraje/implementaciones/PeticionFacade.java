package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPeticion;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrosGeneralesFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPartePeticionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPeticionFacade;
import com.ccb.simasc.transversal.dto.PartePeticionDTO;
import com.ccb.simasc.transversal.dto.PeticionBasicaDTO;
import com.ccb.simasc.transversal.dto.PeticionDTO;
import com.ccb.simasc.transversal.dto.RolPersonaCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.DocumentoBasicoDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.PartePeticionPK;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Peticion;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.RolPersonaCasoPK;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Peticion}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class PeticionFacade extends AccesoFacade<Peticion, Long, PeticionDTO> implements IPeticionFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorPeticion manejadorPeticion;
	
	@EJB
	private IPartePeticionFacade partePeticionFacade;
	
	@EJB
	private IEventoFacade eventoFacade;
	
	@EJB
	private IDominioFacade dominioFacade;
	
	@EJB
	private IParametrosGeneralesFacade parametrosGeneralesFacade;
	
	@EJB
	private ManejadorRol manejadorRol;
	
	@EJB
	private ManejadorRolPersona manejadorRolPersona;
	
	@EJB
	private ManejadorPersona manejadorPersona;
	
	@EJB 
	private ManejadorCaso manejadorCaso;
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	
	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;
	
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorPeticion;
	}

	@Override
	public PeticionDTO transformarSinDependencias(Peticion obj) {		 
		return new PeticionDTO().transformarSinDependencias(obj);
	}

	@Override
	public PeticionDTO transformarConDependencias(Peticion obj) {
		return new PeticionDTO().transformarConDependencias(obj);
	}

	@Override
	public Peticion transformarEntidadConDependencias(Peticion obj) {
		return new PeticionDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Peticion transformarEntidadSinDependencias(Peticion obj) {
		return  new PeticionDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	@Override
	public List<PeticionBasicaDTO> peticionesPorCaso(Long idCaso, String tipoPeticion){	
		List<PeticionBasicaDTO> peticionesBasicas = new ArrayList<PeticionBasicaDTO>();		
		
		List<Peticion> peticiones = manejadorPeticion.peticionesPorCaso(idCaso, tipoPeticion);	
		
		if(peticiones != null && !peticiones.isEmpty()){			
			for (Peticion peticionFor : peticiones) {
				PeticionBasicaDTO peticionDTO = PeticionBasicaDTO.convertirPeticionBasica(peticionFor);
				
				if(peticionFor.getDocumentoList() != null){
					peticionDTO.setDocumentos(DocumentoBasicoDTO.
							tranformarEnDocumentoBasico(peticionFor.getDocumentoList(), UtilDominios.ESTADO_REGISTRO_ACTIVO));
				}	
				peticionesBasicas.add(peticionDTO);
			}
			
		}
		
		return peticionesBasicas;
		
	}
	
	@Override
	public void contestarPeticion(PeticionBasicaDTO peticionDTO){
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;

		if(appContext!= null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() != null  ){
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		}
		Peticion peticion = manejadorPeticion.buscar(peticionDTO.getIdPeticion());
		peticion.setRespuesta(peticionDTO.getRespuesta());
		peticion.setFechaUltimaModificacion(new Date());
		peticion.setFechaRespuesta(new Date());
		peticion.setIdUsuarioModificacion(usuarioModificacion);		
		manejadorPeticion.actualizar(peticion);
		partePeticionFacade.crearPartePeticionPorListaPartes(peticionDTO.getParteRespuesta(),peticion.getIdPeticion(), 
				UtilDominios.TIPO_PARTE_PETICION_RECIBE_RESPUESTA);
		
		//generacion del evento
		String tipo =  dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_PETICION,  peticionDTO.getTipo());
		List<String> args = new ArrayList<>();
		args.add(tipo != null? tipo:"");
		String observaciones = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO330.toString()), args.toArray());		
		eventoFacade.registrarEvento(peticionDTO.getIdCaso(), UtilDominios.TIPO_EVENTO_RESPUESTA_PETICION, observaciones, usuarioModificacion);
				
	}
	
	@Override
	public PartePeticionDTO realizarPeticionEspecial(Long idCaso, Long idPersona, Long idRol, PeticionDTO peticionEspecial, String idUsuario){
		Peticion nuevaPeticionEspecial = new Peticion();
		List<RolPersonaCaso> rolPersona = manejadorRolPersonaCaso.consultarPartesCasoPersona(idCaso, idPersona);
		nuevaPeticionEspecial.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		nuevaPeticionEspecial.setFechaRadicacion(new Date());
		nuevaPeticionEspecial.setFechaUltimaModificacion(new Date());
		nuevaPeticionEspecial.setIdCaso(idCaso);
		nuevaPeticionEspecial.setTexto(peticionEspecial.getTexto());
		nuevaPeticionEspecial.setTipo(peticionEspecial.getTipo());
		nuevaPeticionEspecial.setIdUsuarioModificacion(idUsuario);
		manejadorPeticion.crear(nuevaPeticionEspecial);
		
		List<RolPersonaCasoDTO> partes = new ArrayList<RolPersonaCasoDTO>();
		RolPersonaCasoDTO partePeticion = new RolPersonaCasoDTO();
		RolPersonaCasoPK rolPersonaCasoParte = new RolPersonaCasoPK();
		rolPersonaCasoParte.setIdCaso(idCaso);
		rolPersonaCasoParte.setIdPersona(idPersona);
		rolPersonaCasoParte.setIdRol(rolPersona.get(0).getRol().getIdRol());
		partePeticion.setRolPersonaCasoPK(rolPersonaCasoParte);
		partes.add(partePeticion);
		partePeticionFacade.crearPartePeticionPorListaPartes(partes, nuevaPeticionEspecial.getIdPeticion(), UtilDominios.TIPO_PARTE_PETICION_SOLICITA_RESPUESTA);
		
		PartePeticionDTO respuesta = new PartePeticionDTO();
		PartePeticionPK respuestaPK = new PartePeticionPK();
		respuestaPK.setIdCaso(idCaso);
		respuestaPK.setIdPersona(idPersona);
		respuestaPK.setIdPeticion(nuevaPeticionEspecial.getIdPeticion());
		respuestaPK.setIdRol(idRol);
		respuestaPK.setTipo(peticionEspecial.getTipo());
		respuesta.setPartePeticionPK(respuestaPK);
		
		
		String[] roles = this.rolesPorTipoPeticion(peticionEspecial.getTipo());
		List<String> rolesConsulta = new ArrayList<String>();
		 
		for (int i = 0; i < roles.length; i++) {
			rolesConsulta.add(roles[i]);
		}
		
		Persona persona = manejadorPersona.buscar(idPersona);
		Caso caso = manejadorCaso.buscar(idCaso);
		List<Long> idCentros = new ArrayList<Long>(); 
		if(caso.getIdServicio()!=UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA){
			idCentros.add(caso.getSede().getIdCentro()); 
		}else{
			idCentros.add(caso.getConvenio().getIdCentro());
		}
		String tipoPeticion = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_PETICION, peticionEspecial.getTipo());
		List<Persona> personasNotificar = manejadorRolPersona.consultarPesonasPorRolPersonaCentro(rolesConsulta, idCentros, new Date());
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		List<String> mensajeCorreo = new ArrayList<String>();
		String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO376.toString(),persona.getNombreCompleto(),tipoPeticion,
				df.format(nuevaPeticionEspecial.getFechaRadicacion()),Long.toString(idCaso),caso.getNombre());
		mensajeCorreo.add(mensaje);
		correoRolPersonaCasoFacade.envioDeCorreo(UtilConstantes.ASUNTO_RADICACION_ESPECIAL, personasNotificar, null, null, mensajeCorreo, idCaso, null, null, false);			
		
		return respuesta;
	}
	
	@Override
	public void eliminarPeticionEspecial(PartePeticionDTO peticionEspecial){
		
//		PartePeticionPK parteEliminar = new PartePeticionPK(peticionEspecial.getPartePeticionPK().getIdRol(),
//				peticionEspecial.getPartePeticionPK().getIdPersona(), peticionEspecial.getPartePeticionPK().getIdCaso(),
//				peticionEspecial.getPartePeticionPK().getTipo(), 
//				peticionEspecial.getPartePeticionPK().getIdPeticion());
//		PartePeticion partePeticionEliminar = partePeticionFacade.buscar(parteEliminar);
//		partePeticionFacade.eliminar(partePeticionEliminar);
		Peticion peticionEliminar = manejadorPeticion.buscar(peticionEspecial.getPartePeticionPK().getIdPeticion());
		manejadorPeticion.eliminar(peticionEliminar);
	}
	
	private String[] rolesPorTipoPeticion( String tipoPeticion){
	
		List<ParametrosGenerales> codigosRol = parametrosGeneralesFacade.consultarPorTipo(UtilConstantes.TIPO_PARAMETRO_ROL_TIPO_PETICION);
		String[] roles = null;
		for (ParametrosGenerales parametrosGenerales : codigosRol) {
			
				roles= retornarRoles(tipoPeticion, parametrosGenerales);
				if(roles!=null){
					break;
				}
		}
		return roles;
	}
	
	private String[] retornarRoles(String tipoPeticion, ParametrosGenerales parametrosGenerales ){
		
		String[] roles = null;
		if((tipoPeticion.equals(UtilDominios.TIPO_PETICION_CAMBIO_DEVOLUCION_DINERO) && parametrosGenerales.getCodigo().equals(UtilDominios.ROL_TIPO_PETICION_DEVOLUCION_DINERO))
				|| (tipoPeticion.equals(UtilDominios.TIPO_PETICION_CAMBIO_FECHA) && parametrosGenerales.getCodigo().equals(UtilDominios.ROL_TIPO_PETICION_CAMBIO_FECHA))
				|| (tipoPeticion.equals(UtilDominios.TIPO_PETICION_CAMBIO_CONCILIADOR) && parametrosGenerales.getCodigo().equals(UtilDominios.ROL_TIPO_PETICION_CAMBIO_CONCILIADOR))
				|| (tipoPeticion.equals(UtilDominios.TIPO_PETICION_CAMBIO_INCUMPLIMIENTO) && parametrosGenerales.getCodigo().equals(UtilDominios.ROL_TIPO_PETICION_INCUMPLIMIENTO))
				|| (tipoPeticion.equals(UtilDominios.TIPO_PETICION_DERECHO_PETICION) && parametrosGenerales.getCodigo().equals(UtilDominios.ROL_TIPO_PETICION_DERECHO_PETICION))
				|| (tipoPeticion.equals(UtilDominios.TIPO_PETICION_FOTOCOPIAS) && parametrosGenerales.getCodigo().equals(UtilDominios.ROL_TIPO_PETICION_FOTOCOPIAS))
				|| (tipoPeticion.equals(UtilDominios.TIPO_PETICION_OTROS) && parametrosGenerales.getCodigo().equals(UtilDominios.ROL_TIPO_PETICION_OTROS))){
			roles =  parametrosGenerales.getValorTexto().split(",");						
		}
		return roles;
	
	}
	// protected region metodos adicionales end
	
}
