package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorPublicacion;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPublicacionFacade;
import com.ccb.simasc.transversal.dto.PublicacionDTO;
import com.ccb.simasc.transversal.entidades.Publicacion;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Publicacion}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class PublicacionFacade extends AccesoFacade<Publicacion, Long, PublicacionDTO> implements IPublicacionFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorPublicacion manejadorPublicacion;
	
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorPublicacion;
	}

	@Override
	public PublicacionDTO transformarSinDependencias(Publicacion obj) {
		PublicacionDTO dto = new PublicacionDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public PublicacionDTO transformarConDependencias(Publicacion obj) {
		PublicacionDTO dto = new PublicacionDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Publicacion transformarEntidadConDependencias(Publicacion obj) {
		Publicacion dto = new Publicacion();
		dto = new PublicacionDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Publicacion transformarEntidadSinDependencias(Publicacion obj) {
		Publicacion dto = new Publicacion();
		dto = new PublicacionDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * ADM-F-035
	 */
	@Override
	public List<Publicacion> consultarPublicacionesPersona(Long idPersona){
		List<Publicacion> publicaciones = this.manejadorPublicacion.consultarPublicacionesPersona(idPersona);
		if (publicaciones != null && !publicaciones.isEmpty()){
			publicaciones = (List<Publicacion>) new PublicacionDTO().transformarColeccionEntidadesSinDependencias(publicaciones);
		}
		
		return publicaciones;
	}
	
	/**
	 * ADM-F-035
	 * Actualiza o crea la publicación que se pasa como parámetro
	 */
	@Override
	public void actualizarPublicacion(Publicacion publicacion) {
		String usuarioModificacion;
		publicacion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		publicacion.setFechaUltimaModificacion(new Date());
		if(appContext!= null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() != null  ){
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		}else{
			throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR530.toString()));
		}
		publicacion.setIdUsuarioModificacion(usuarioModificacion);
		actualizar(publicacion);
	}
	// protected region metodos adicionales end
	
}
