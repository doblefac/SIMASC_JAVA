package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

import java.util.ArrayList;
import java.util.Date;

// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorDesarrolloProfesional;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDesarrolloProfesionalFacade;
import com.ccb.simasc.transversal.dto.DesarrolloProfesionalDTO;
import com.ccb.simasc.transversal.entidades.DesarrolloProfesional;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link DesarrolloProfesional}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class DesarrolloProfesionalFacade extends AccesoFacade<DesarrolloProfesional, Long, DesarrolloProfesionalDTO>
		implements IDesarrolloProfesionalFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	@EJB
	private ManejadorDesarrolloProfesional manejadorDesarrolloProfesional;

	// contexto de sesion de usuario.
	@Inject
	private ApplicationRequestContext appContext;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorDesarrolloProfesional;
	}

	@Override
	public DesarrolloProfesionalDTO transformarSinDependencias(DesarrolloProfesional obj) {
		return new DesarrolloProfesionalDTO().transformarSinDependencias(obj);
	}

	@Override
	public DesarrolloProfesionalDTO transformarConDependencias(DesarrolloProfesional obj) {
		return new DesarrolloProfesionalDTO().transformarConDependencias(obj);
	}

	@Override
	public DesarrolloProfesional transformarEntidadConDependencias(DesarrolloProfesional obj) {
		return new DesarrolloProfesionalDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public DesarrolloProfesional transformarEntidadSinDependencias(DesarrolloProfesional obj) {
		return new DesarrolloProfesionalDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IDesarrolloProfesionalFacade#consultarDesarrolloProfesional(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public List<DesarrolloProfesional> consultarDesarrolloProfesional(Long idPersona, String tipoDesarrollo, Date fechaDesde, Date fechaHasta) {
		return (List<DesarrolloProfesional>) transformarEntidadesColeccionConDependencias(
				manejadorDesarrolloProfesional.consultarDesarrolloProfesional(idPersona, tipoDesarrollo, fechaDesde, fechaHasta,null,null),
				new ArrayList<DesarrolloProfesional>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IDesarrolloProfesionalFacade#actualizarDesarrolloProfesional(com.ccb.
	 * simasc.transversal.entidades.DesarrolloProfesional)
	 */
	@Override
	public void actualizarDesarrolloProfesional(DesarrolloProfesional desarrolloProfesional) {
		String usuarioActual = appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null
						? appContext.getContextoSesion().getNombreUsuario() : UtilConstantes.USUARIO_DEFECTO_SIMASC;
								
		desarrolloProfesional.setIdUsuarioModificacion(usuarioActual);
		desarrolloProfesional.setFechaUltimaModificacion(new Date());
		desarrolloProfesional.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		actualizar(desarrolloProfesional);
		
		if (desarrolloProfesional.getIdDesarrolloProfesional() != null) {
			DesarrolloProfesional desaConsultado = this.buscar(desarrolloProfesional.getIdDesarrolloProfesional());
			desaConsultado.setIdMateria(desarrolloProfesional.getIdMateria());
			desaConsultado.setFechaFinal(desarrolloProfesional.getFechaFinal());
			actualizar(desaConsultado);			
		}
	}
	
	/*
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IDesarrolloProfesionalFacade#actualizarDesarrolloProfesional(com.ccb.
	 * simasc.transversal.entidades.DesarrolloProfesional)
	 */
	@Override
	public List<DesarrolloProfesionalDTO> consultarDesarrolloProfesionalSinDependencias( Long idPersona, String tipoDesarrollo, Date periodo ) {
		return (List<DesarrolloProfesionalDTO>) new DesarrolloProfesionalDTO().transformarColeccionSinDependencias( manejadorDesarrolloProfesional
				.consultarDesarrolloProfesional(idPersona, tipoDesarrollo, null, periodo,null,null));
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDesarrolloProfesionalFacade#consultarDesarrolloProfesional(com.ccb.simasc.transversal.dto.DesarrolloProfesionalDTO)
	 */
	public List<DesarrolloProfesionalDTO> consultarDesarrolloProfesional (DesarrolloProfesionalDTO desarrolloProfesional){
		
		String tipoCurso = null;
		if(desarrolloProfesional.getTipoCurso()!=null){
			tipoCurso = desarrolloProfesional.getTipoCurso();
		}
		List<String> materiasCurso = null;
		if(desarrolloProfesional.getMateriaCurso()!=null){
			materiasCurso = new ArrayList<String>();
			materiasCurso.add(desarrolloProfesional.getMateriaCurso());	
		}
		
		return (List<DesarrolloProfesionalDTO>) new DesarrolloProfesionalDTO().transformarColeccionSinDependencias( manejadorDesarrolloProfesional
				.consultarDesarrolloProfesional(desarrolloProfesional.getIdPersona(), desarrolloProfesional.getTipoDesarrolloProfesional(), null, null,materiasCurso,tipoCurso));
	}
	// protected region metodos adicionales end

}
