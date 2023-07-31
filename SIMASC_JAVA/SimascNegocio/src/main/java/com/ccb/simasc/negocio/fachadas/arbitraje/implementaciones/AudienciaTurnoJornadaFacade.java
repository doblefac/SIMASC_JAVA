package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.integracion.manejadores.ManejadorAudienciaTurnoJornada;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAudienciaTurnoJornadaFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.AudienciaTurnoJornadaDTO;
import com.ccb.simasc.transversal.entidades.AudienciaTurnoJornada;
import com.ccb.simasc.transversal.entidades.AudienciaTurnoJornadaPK;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link AudienciaTurnoJornada}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class AudienciaTurnoJornadaFacade extends AccesoFacade<AudienciaTurnoJornada, AudienciaTurnoJornadaPK, AudienciaTurnoJornadaDTO> implements IAudienciaTurnoJornadaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorAudienciaTurnoJornada manejadorAudienciaTurnoJornada;
	
	// contexto de sesion de usuario.
	@Inject
	private ApplicationRequestContext appContext;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorAudienciaTurnoJornada;
	}

	@Override
	public AudienciaTurnoJornadaDTO transformarSinDependencias(AudienciaTurnoJornada obj) {
		return new AudienciaTurnoJornadaDTO().transformarSinDependencias(obj);
	}

	@Override
	public AudienciaTurnoJornadaDTO transformarConDependencias(AudienciaTurnoJornada obj) {
		return new AudienciaTurnoJornadaDTO().transformarConDependencias(obj);
	}

	@Override
	public AudienciaTurnoJornada transformarEntidadConDependencias(AudienciaTurnoJornada obj) {
		return new AudienciaTurnoJornadaDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public AudienciaTurnoJornada transformarEntidadSinDependencias(AudienciaTurnoJornada obj) {
		return new AudienciaTurnoJornadaDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAudienciaTurnoJornadaFacade#liberarTurnos(java.util.List)
	 */
	@Override
	public void liberarTurnos(List<AudienciaTurnoJornada> turnos) {
		for (AudienciaTurnoJornada turno : turnos) {
			actualizarEstadoTurno(turno, UtilDominios.ESTADO_REGISTRO_INACTIVO);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAudienciaTurnoJornadaFacade#ocuparTurno(java.lang.Long, java.lang.Long)
	 */
	@Override
	public AudienciaTurnoJornada ocuparTurno(Long idAudiencia, Long idTurnoJornada) {
		AudienciaTurnoJornada cambioTurno = new AudienciaTurnoJornada();
		AudienciaTurnoJornadaPK pk = new AudienciaTurnoJornadaPK(idAudiencia, idTurnoJornada);
		cambioTurno.setAudienciaTurnoJornadaPK(pk);
		actualizarEstadoTurno(cambioTurno, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return cambioTurno;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAudienciaTurnoJornadaFacade#actualizarEstadoTurno(com.ccb.simasc.transversal.entidades.AudienciaTurnoJornada, java.lang.String)
	 */
	@Override
	public void actualizarEstadoTurno(AudienciaTurnoJornada turno, String estado) {
		String usuario = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if (appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() != null
				&& appContext.getContextoSesion().getNombreUsuario().isEmpty()) {
			usuario = appContext.getContextoSesion().getNombreUsuario(); 
		}
		turno.setEstadoRegistro(estado);
		turno.setFechaUltimaModificacion(new Date());
		turno.setIdUsuarioModificacion(usuario);
		actualizar(turno);
	}
	// protected region metodos adicionales end
	
}
