package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorEvaluacionConciliador;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEvaluacionConciliadorFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.EvaluacionConciliadorDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.EvaluacionConciliador;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link EvaluacionConciliador}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class EvaluacionConciliadorFacade extends AccesoFacade<EvaluacionConciliador, Long, EvaluacionConciliadorDTO> implements IEvaluacionConciliadorFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorEvaluacionConciliador manejadorEvaluacionConciliador;
	
	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorEvaluacionConciliador;
	}

	@Override
	public EvaluacionConciliadorDTO transformarSinDependencias(EvaluacionConciliador obj) {
		return new EvaluacionConciliadorDTO().transformarSinDependencias(obj);
	}

	@Override
	public EvaluacionConciliadorDTO transformarConDependencias(EvaluacionConciliador obj) {
		return new EvaluacionConciliadorDTO().transformarConDependencias(obj);
	}

	@Override
	public EvaluacionConciliador transformarEntidadConDependencias(EvaluacionConciliador obj) {
		return new EvaluacionConciliadorDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public EvaluacionConciliador transformarEntidadSinDependencias(EvaluacionConciliador obj) {
		return new EvaluacionConciliadorDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public List<EvaluacionConciliador> consultarEvaluacionConciliador( Long idPersona, Date periodoDesde, Date periodoHasta ){
		return (List<EvaluacionConciliador>) new EvaluacionConciliadorDTO()
				.transformarColeccionEntidadesConDependencias(manejadorEvaluacionConciliador.consultarEvaluacionConciliador(idPersona, periodoDesde, periodoHasta));
	}

	@Override
	public List<PersonaBasicaDTO> consultaConciliadoresEvaluar(List<Long> centros) {
		return manejadorEvaluacionConciliador.consultarConciliadoresAEvaluar(centros);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IEvaluacionConciliadorFacade#actualizarEvaluacion(java.lang.Long,
	 * java.lang.Long, java.util.Date, java.util.Date)
	 */
	@Override
	public EvaluacionConciliador actualizarEvaluacion(Long idEvaluacion, Long idPersona, Date periodoDesde, Date periodoHasta) {
		EvaluacionConciliador evaluacion = null;
		if(idEvaluacion != null)
			evaluacion = buscar(idEvaluacion);
			
		if(evaluacion != null) {
			evaluacion.setFechaUltimaModificacion(new Date());
			actualizar(evaluacion);
		} else {
			evaluacion = new EvaluacionConciliador();
			evaluacion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			evaluacion.setIdPersona(idPersona);
			evaluacion.setPeriodoDesde(periodoDesde);
			evaluacion.setPeriodoHasta(periodoHasta);
			crear(evaluacion);
		}
		
		return evaluacion;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IEvaluacionConciliadorFacade#registrarResultadoEvaluacion(java.lang.Long,
	 * java.lang.Double)
	 */
	@Override
	public void registrarResultadoEvaluacion(Long idEvaluacion, Double resultado) {
		EvaluacionConciliador evaluacion = buscar(idEvaluacion);
		if(evaluacion != null) {
			evaluacion.setTotalEvaluacion(new BigDecimal(resultado).setScale(2, RoundingMode.DOWN));
			actualizar(evaluacion);
			StringBuilder a = new StringBuilder();
			a.append(UtilOperaciones.formatearFechaReporte(evaluacion.getPeriodoDesde()));
			a.append(" - ");
			a.append(UtilOperaciones.formatearFechaReporte(evaluacion.getPeriodoHasta()));
			correoRolPersonaCasoFacade.envioDeCorreo(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO360.toString()),
					Arrays.asList(evaluacion.getPersona()), null, null,
					Arrays.asList(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO361.toString(),
							evaluacion.getTotalEvaluacion().toString(), a.toString())),
					null, null, null, false);
		}
	}
	// protected region metodos adicionales end

	@Override
	public List<String> consultarAniosRegistroEvaluaciones() {
		return manejadorEvaluacionConciliador.consultarAniosRegistroEvaluaciones();
	}

	
}
