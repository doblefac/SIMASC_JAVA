package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorConvenio;
import com.ccb.simasc.integracion.manejadores.ManejadorTurnoJornada;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAudienciaTurnoJornadaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametroDeServicioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITurnoJornadaFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.FormatoHoraAudienciaDTO;
import com.ccb.simasc.transversal.dto.ParametroDeServicioDTO;
import com.ccb.simasc.transversal.dto.TurnoJornadaDTO;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Convenio;
import com.ccb.simasc.transversal.entidades.TurnoJornada;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link TurnoJornada}
 * @author cbenavides
 *
 */
@Stateless
@LocalBean
public class TurnoJornadaFacade extends AccesoFacade<TurnoJornada, Long, TurnoJornadaDTO> implements ITurnoJornadaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorTurnoJornada manejadorTurnoJornada;
	
	@EJB
	private ManejadorAudiencia manejadorAudiencia;
	
	@EJB
	private ManejadorConvenio manejadorConvenio;
	
	@EJB
	private IAudienciaTurnoJornadaFacade audienciaTurnoJornadaFacade;
	
	@EJB
	private IParametroDeServicioFacade parametroDeServicioFacade;
	
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;
    
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorTurnoJornada;
	}

	@Override
	public TurnoJornadaDTO transformarSinDependencias(TurnoJornada obj) {
		return new TurnoJornadaDTO().transformarSinDependencias(obj);
	}

	@Override
	public TurnoJornadaDTO transformarConDependencias(TurnoJornada obj) {
		return new TurnoJornadaDTO().transformarConDependencias(obj);
	}

	@Override
	public TurnoJornada transformarEntidadConDependencias(TurnoJornada obj) {
		return new TurnoJornadaDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public TurnoJornada transformarEntidadSinDependencias(TurnoJornada obj) {
		return new TurnoJornadaDTO().transformarEntidadSinDependencias(obj);
	}

	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITurnoJornadaFacade#actualizarTurnoAudiencia(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void actualizarTurnoAudiencia(Long idAudiencia, Long idNuevoTurno) {
		Audiencia audiencia = manejadorAudiencia.buscar(idAudiencia);
		// liberar turnos de la audiencia
		audienciaTurnoJornadaFacade.liberarTurnos(audiencia.getAudienciaTurnoJornadaList());
		TurnoJornada nuevoTurno = manejadorTurnoJornada.buscar(idNuevoTurno);
		
		// actualizar horas de la audiencia
		audiencia.setHoraInicio(nuevoTurno.getHoraInicio());
		audiencia.setHoraFin(nuevoTurno.getHoraFin());
		manejadorAudiencia.actualizar(audiencia);
		
		audienciaTurnoJornadaFacade.ocuparTurno(idAudiencia, nuevoTurno.getIdTurnoJornada());
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITurnoJornadaFacade#consultarTurnosDisplonibles(java.lang.Long)
	 */
	@Override
	public List<TurnoJornadaDTO> consultarTurnosDisplonibles(Long idConvenio) {
		return (List<TurnoJornadaDTO>) transformarColeccionSinDependencias(
				manejadorTurnoJornada.obtenerTurnosDisponibles(idConvenio), new ArrayList<TurnoJornadaDTO>());
	}

	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITurnoJornadaFacade#ampliarTurno(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void ampliarTurno(Long idAudiencia, Long idTurno, Long idTurnoConsecutivo) {
		Audiencia audiencia = manejadorAudiencia.buscar(idAudiencia);
		// liberar turnos de la audiencia
		audienciaTurnoJornadaFacade.liberarTurnos(audiencia.getAudienciaTurnoJornadaList());
		TurnoJornada nuevoTurno = manejadorTurnoJornada.buscar(idTurno);
		TurnoJornada consecutivo = manejadorTurnoJornada.buscar(idTurnoConsecutivo);
		
		// actualizar horas de la audiencia
		audiencia.setHoraInicio(nuevoTurno.getHoraInicio());
		audiencia.setHoraFin(consecutivo.getHoraFin());
		manejadorAudiencia.actualizar(audiencia);
		
		// ocupar turnos consecutivos
		audienciaTurnoJornadaFacade.ocuparTurno(idAudiencia, nuevoTurno.getIdTurnoJornada());
		audienciaTurnoJornadaFacade.ocuparTurno(idAudiencia, consecutivo.getIdTurnoJornada());
	}

	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITurnoJornadaFacade#consultarTurnosConsecutivos(java.lang.Long)
	 */
	@Override
	public List<TurnoJornadaDTO> consultarTurnosConsecutivos(Long idConvenio) {
		return manejadorTurnoJornada.consultarTurnosConsecutivos(idConvenio);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITurnoJornadaFacade#actualizarTurnosJornada(com.ccb.simasc.transversal.entidades.TurnoJornada)
	 */
	@Override
	public void actualizarTurnoJornada(TurnoJornada turno) {
		if (turno.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)
				&& !manejadorTurnoJornada.validarCruceTurnos(turno.getHoraInicio(), turno.getHoraFin(),
						turno.getIdConvenio(), turno.getIdTurnoJornada())) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR310.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		} else {
			turno.setFechaUltimaModificacion(new Date());
			if(appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() != null
					&& !appContext.getContextoSesion().getNombreUsuario().isEmpty())
				turno.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
			else
				turno.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			if(turno.getIdTurnoJornada() != null)
				actualizar(turno);
			else 
				crear(turno);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITurnoJornadaFacade#obtenerRangoHoras(java.lang.Long)
	 */
	@Override
	public List<FormatoHoraAudienciaDTO> obtenerRangoHoras(Long idConvenio) {
		Convenio convenio = manejadorConvenio.buscar(idConvenio);
		if (convenio == null || !convenio.getTipoConvenio().equals(UtilDominios.TIPO_CONVENIO_JORNADA)){
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR311.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);			
		}
		
		List<String> nombres = new ArrayList<>();
		nombres.add(UtilDominios.PARAMETRO_DE_SERVICIO_HORA_INICIO_AUDIENCIA);
		nombres.add(UtilDominios.PARAMETRO_DE_SERVICIO_HORA_FIN_AUDIENCIA);
		nombres.add(UtilDominios.PARAMETRO_DE_SERVICIO_DURACION_DE_AUDIENCIA);
		List<ParametroDeServicioDTO> parametros = parametroDeServicioFacade.consultarParametroDeServicio(nombres, UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA,
				UtilDominios.TIPO_PARAMETRO_PROGRAMACION_AUDIENCIA);
		
		String duracion = obtenerParametroPorNombre(parametros, UtilDominios.PARAMETRO_DE_SERVICIO_DURACION_DE_AUDIENCIA).getValor();
		String horaInicio = obtenerParametroPorNombre(parametros, UtilDominios.PARAMETRO_DE_SERVICIO_HORA_INICIO_AUDIENCIA).getValor();
		String horaFin = obtenerParametroPorNombre(parametros, UtilDominios.PARAMETRO_DE_SERVICIO_HORA_FIN_AUDIENCIA).getValor();
		Calendar inicial = Calendar.getInstance();
		inicial.setTime(convenio.getFechaInicioVigencia());
		inicial.set(Calendar.HOUR_OF_DAY, Integer.valueOf(horaInicio.split(":")[0]));
		Calendar ffinal = Calendar.getInstance();
		ffinal.setTime(convenio.getFechaInicioVigencia());
		ffinal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(horaFin.split(":")[0]));
		return UtilOperaciones.horasAudiencia(duracion, inicial.getTime(), ffinal.getTime());
	}

	/**
	 * Método que obtiene el objeto parametro de servicio de la lista y de acuerdo al nombre
	 * @param parametros
	 * @param nombre
	 * @return
	 */
	private ParametroDeServicioDTO obtenerParametroPorNombre(List<ParametroDeServicioDTO> parametros, String nombre) {
		ParametroDeServicioDTO parametro = null;
		for (ParametroDeServicioDTO parametroDeServicioDTO : parametros) {
			if (parametroDeServicioDTO.getParametroDeServicioPK().getNombre().equals(nombre)) {
				parametro = parametroDeServicioDTO;
				break;
			}
		}
		return parametro;
	}
	// protected region metodos adicionales end
	
}
