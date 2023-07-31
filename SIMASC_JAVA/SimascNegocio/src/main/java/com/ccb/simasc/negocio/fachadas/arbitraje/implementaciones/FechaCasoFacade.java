package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

import java.util.Arrays;

// Escriba en esta seccion sus modificaciones

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorFechaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroDeServicio;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDiaFestivoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFechaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrosGeneralesFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISuspensionFacade;
import com.ccb.simasc.transversal.dto.FechaCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.HitosCasoDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.FechaCaso;
import com.ccb.simasc.transversal.entidades.FechaCasoPK;
import com.ccb.simasc.transversal.entidades.ParametroDeServicio;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link FechaCaso}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class FechaCasoFacade extends AccesoFacade<FechaCaso, FechaCasoPK, FechaCasoDTO> implements IFechaCasoFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada


	
	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private ManejadorAudiencia manejadorAudiencia;

	@EJB
	private ManejadorFechaCaso manejadorFechaCaso;
	
	@EJB
	private ManejadorParametroDeServicio manejadorParametroDeServicio;

	@EJB
	private IParametrosGeneralesFacade parametrosGeneralesFacade;
	
	@EJB
	private ISuspensionFacade suspensionFacade;
	
	@EJB
	private IDiaFestivoFacade diaFestivoFacade;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorFechaCaso;
	}

	@Override
	public FechaCasoDTO transformarSinDependencias(FechaCaso obj) {
		FechaCasoDTO dto = new FechaCasoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public FechaCasoDTO transformarConDependencias(FechaCaso obj) {
		FechaCasoDTO dto = new FechaCasoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public FechaCaso transformarEntidadConDependencias(FechaCaso obj) {
		FechaCaso dto = new FechaCaso();
		dto = new FechaCasoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public FechaCaso transformarEntidadSinDependencias(FechaCaso obj) {
		FechaCaso dto = new FechaCaso();
		dto = new FechaCasoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Obtiene los hitos editables de un caso
	 * 
	 * @param idCaso
	 * @param tipoFecha
	 * @return
	 */
	@Override
	public HitosCasoDTO obtenerHitosCaso(Long idCaso) {
		HitosCasoDTO hitosCaso = new HitosCasoDTO();

		hitosCaso.setIdCaso(idCaso);

		// hitos desde la tabla fechaCaso
		hitosCaso.setAdmisionDemanda(manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_ADMISION_DEMANDA));
		hitosCaso.setCierreEtapaProbatoria(manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_CIERRE_ETAPA_PROBATORIA));
		hitosCaso.setCierre(
				manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso, UtilDominios.ESTADO_TIPO_FECHA_CASO_CIERRE));
		hitosCaso.setContestacionDemanda(manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_CONSTENTACION_DEMANDA));
		hitosCaso.setDemanda(
				manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso, UtilDominios.ESTADO_TIPO_FECHA_CASO_DEMANDA));
		hitosCaso.setDemandaReconvencion(manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_DEMANDA_RECONVENCION));
		hitosCaso.setDesignacionArbitros(manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_DESIGNACION_ARBITROS));
		hitosCaso.setFijacionGastosHonorarios(manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_FIJACION_GASTOS_HONORARIOS));
		hitosCaso.setInstalacion(
				manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso, UtilDominios.ESTADO_TIPO_FECHA_CASO_INSTALACION));
		hitosCaso.setLlamamientoGarantia(manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_LLAMAMIENTO_GARANTIA));
		hitosCaso.setNotificacionDemandado(manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_NOTIFICACION_DEMANDADO));
		hitosCaso.setTramiteRecursoAnulacion(manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_TRAMITE_RECURSO_ANULACION));
		hitosCaso.setTrasladoExcepcionesMerito(manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_TRASLADO_EXCEPCIONES_MERITO));
		hitosCaso.setTribunalConsignaCAC(manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_TRIBUNAL_CONSIGNA_CAC));
		hitosCaso.setInicioConteoTerminos(manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso, 
				UtilDominios.ESTADO_TIPO_FECHA_CASO_INICIO_CONTEO_TERMINOS));

		// hitos de Audiencias

		hitosCaso.setAudienciaAclaracion(manejadorAudiencia.consultarUltimaAudiencia(idCaso,
				UtilDominios.TIPO_AUDIENCIA_ARBITRAL_ACLARACION) == null
						? manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
								UtilDominios.ESTADO_TIPO_FECHA_CASO_AUDIENCIA_ACLARACIONES_CORREO)
						: manejadorAudiencia
								.consultarUltimaAudiencia(idCaso, UtilDominios.TIPO_AUDIENCIA_ARBITRAL_ACLARACION)
								.getHoraInicio());

		hitosCaso.setAudienciaAlegatos(manejadorAudiencia.consultarUltimaAudiencia(idCaso,
				UtilDominios.TIPO_AUDIENCIA_ARBITRAL_ALEGATOS) == null
						? manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
								UtilDominios.ESTADO_TIPO_FECHA_CASO_AUDIENCIA_ALEGATOS)
						: manejadorAudiencia
								.consultarUltimaAudiencia(idCaso, UtilDominios.TIPO_AUDIENCIA_ARBITRAL_ALEGATOS)
								.getHoraInicio());

		hitosCaso.setAudienciaConciliacion(manejadorAudiencia.consultarUltimaAudiencia(idCaso,
				UtilDominios.TIPO_AUDIENCIA_ARBITRAL_CONCILIACION) == null
						? manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
								UtilDominios.ESTADO_TIPO_FECHA_CASO_AUDIENCIA_CONCILIACION)
						: manejadorAudiencia
								.consultarUltimaAudiencia(idCaso, UtilDominios.TIPO_AUDIENCIA_ARBITRAL_CONCILIACION)
								.getHoraInicio());

		hitosCaso.setAudienciaLaudo(
				manejadorAudiencia.consultarUltimaAudiencia(idCaso, UtilDominios.TIPO_AUDIENCIA_ARBITRAL_LAUDO) == null
						? manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
								UtilDominios.ESTADO_TIPO_FECHA_CASO_AUDIENCIA_LAUDO)
						: manejadorAudiencia
								.consultarUltimaAudiencia(idCaso, UtilDominios.TIPO_AUDIENCIA_ARBITRAL_LAUDO)
								.getHoraInicio());

		hitosCaso.setAudienciaPruebas(manejadorAudiencia.consultarUltimaAudiencia(idCaso,
				UtilDominios.TIPO_AUDIENCIA_ARBITRAL_PRUEBAS) == null
						? manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
								UtilDominios.ESTADO_TIPO_FECHA_CASO_AUDIENCIA_PRUEBAS)
						: manejadorAudiencia
								.consultarUltimaAudiencia(idCaso, UtilDominios.TIPO_AUDIENCIA_ARBITRAL_PRUEBAS)
								.getHoraInicio());

		hitosCaso.setAudienciaPrimeraTramite(manejadorAudiencia.consultarUltimaAudiencia(idCaso,
				UtilDominios.TIPO_AUDIENCIA_ARBITRAL_PRIMERA) == null
						? manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
								UtilDominios.ESTADO_TIPO_FECHA_CASO_AUDIENCIA_PRIMERA_TRAMITE)
						: manejadorAudiencia
								.consultarUltimaAudiencia(idCaso, UtilDominios.TIPO_AUDIENCIA_ARBITRAL_PRIMERA)
								.getHoraInicio());

		return hitosCaso;
	}

	/**
	 * Obtiene los hitos editables de un caso
	 * 
	 * @param idCaso
	 * @param tipoFecha
	 * @return
	 */
	@Override
	public void actualizarHitosCaso(HitosCasoDTO hitos) {

		Long idCaso = hitos.getIdCaso();
		Caso caso = manejadorCaso.buscar(idCaso);

		// CAMPOS EDITABLES
		FechaCaso admisionDemanda = manejadorFechaCaso.consultaFechaCaso(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_ADMISION_DEMANDA);
		Date fecha = admisionDemanda == null ? null : admisionDemanda.getFecha();
		if (fecha != null || hitos.getAdmisionDemanda() != null) {
			if (!(fecha != null && hitos.getAdmisionDemanda() != null
					&& fecha.compareTo(hitos.getAdmisionDemanda()) == 0)) {
				FechaCaso f = new FechaCaso();
				FechaCasoPK pk = new FechaCasoPK(UtilDominios.ESTADO_TIPO_FECHA_CASO_ADMISION_DEMANDA, idCaso);
				f.setCaso(caso);
				f.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				f.setFecha(hitos.getAdmisionDemanda());
				f.setFechaCasoPK(pk);
				f.setFechaUltimaModificacion(new Date());
				f.setIdUsuarioModificacion(hitos.getIdUsuario().toString());
				manejadorFechaCaso.actualizarFechaCaso(f);
			}
		}

		FechaCaso cierreEtapaAprobatoria = manejadorFechaCaso.consultaFechaCaso(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_CIERRE_ETAPA_PROBATORIA);
		fecha = cierreEtapaAprobatoria == null ? null : cierreEtapaAprobatoria.getFecha();
		if (fecha != null || hitos.getCierreEtapaProbatoria() != null) {
			if (!(fecha != null && hitos.getCierreEtapaProbatoria() != null
					&& fecha.compareTo(hitos.getCierreEtapaProbatoria()) == 0)) {
				FechaCaso f = new FechaCaso();
				FechaCasoPK pk = new FechaCasoPK(UtilDominios.ESTADO_TIPO_FECHA_CASO_CIERRE_ETAPA_PROBATORIA, idCaso);

				f.setFechaCasoPK(pk);
				f.setCaso(caso);
				f.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				f.setFecha(hitos.getCierreEtapaProbatoria());
				f.setFechaUltimaModificacion(new Date());
				f.setIdUsuarioModificacion(hitos.getIdUsuario().toString());
				manejadorFechaCaso.actualizarFechaCaso(f);
			}
		}

		FechaCaso contestacionDemana = manejadorFechaCaso.consultaFechaCaso(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_CONSTENTACION_DEMANDA);
		fecha = contestacionDemana == null ? null : contestacionDemana.getFecha();
		if (fecha != null || hitos.getContestacionDemanda() != null) {
			if (!(fecha != null && hitos.getContestacionDemanda() != null
					&& fecha.compareTo(hitos.getContestacionDemanda()) == 0)) {
				FechaCaso f = new FechaCaso();
				FechaCasoPK pk = new FechaCasoPK(UtilDominios.ESTADO_TIPO_FECHA_CASO_CONSTENTACION_DEMANDA, idCaso);

				f.setFechaCasoPK(pk);
				f.setCaso(caso);
				f.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				f.setFecha(hitos.getContestacionDemanda());
				f.setFechaUltimaModificacion(new Date());
				f.setIdUsuarioModificacion(hitos.getIdUsuario().toString());
				manejadorFechaCaso.actualizarFechaCaso(f);
			}
		}

		FechaCaso demandaReconvencion = manejadorFechaCaso.consultaFechaCaso(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_DEMANDA_RECONVENCION);
		fecha = demandaReconvencion == null ? null : demandaReconvencion.getFecha();
		if (fecha != null || hitos.getDemandaReconvencion() != null) {
			if (!(fecha != null && hitos.getDemandaReconvencion() != null
					&& fecha.compareTo(hitos.getDemandaReconvencion()) == 0)) {
				FechaCaso f = new FechaCaso();
				FechaCasoPK pk = new FechaCasoPK(UtilDominios.ESTADO_TIPO_FECHA_CASO_DEMANDA_RECONVENCION, idCaso);

				f.setFechaCasoPK(pk);
				f.setCaso(caso);
				f.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				f.setFecha(hitos.getDemandaReconvencion());
				f.setFechaUltimaModificacion(new Date());
				f.setIdUsuarioModificacion(hitos.getIdUsuario().toString());
				manejadorFechaCaso.actualizarFechaCaso(f);
			}
		}

		FechaCaso llamamientoGarantia = manejadorFechaCaso.consultaFechaCaso(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_LLAMAMIENTO_GARANTIA);
		fecha = llamamientoGarantia == null ? null : llamamientoGarantia.getFecha();
		if (fecha != null || hitos.getLlamamientoGarantia() != null) {
			if (!(fecha != null && hitos.getLlamamientoGarantia() != null
					&& fecha.compareTo(hitos.getLlamamientoGarantia()) == 0)) {
				FechaCaso f = new FechaCaso();
				FechaCasoPK pk = new FechaCasoPK(UtilDominios.ESTADO_TIPO_FECHA_CASO_LLAMAMIENTO_GARANTIA, idCaso);

				f.setFechaCasoPK(pk);
				f.setCaso(caso);
				f.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				f.setFecha(hitos.getLlamamientoGarantia());
				f.setFechaUltimaModificacion(new Date());
				f.setIdUsuarioModificacion(hitos.getIdUsuario().toString());
				manejadorFechaCaso.actualizarFechaCaso(f);
			}
		}

		FechaCaso notificacionDemandado = manejadorFechaCaso.consultaFechaCaso(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_NOTIFICACION_DEMANDADO);
		fecha = notificacionDemandado == null ? null : notificacionDemandado.getFecha();
		if (fecha != null || hitos.getNotificacionDemandado() != null) {
			if (!(fecha != null && hitos.getNotificacionDemandado() != null
					&& fecha.compareTo(hitos.getNotificacionDemandado()) == 0)) {
				FechaCaso f = new FechaCaso();
				FechaCasoPK pk = new FechaCasoPK(UtilDominios.ESTADO_TIPO_FECHA_CASO_NOTIFICACION_DEMANDADO, idCaso);

				f.setFechaCasoPK(pk);
				f.setCaso(caso);
				f.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				f.setFecha(hitos.getNotificacionDemandado());
				f.setFechaUltimaModificacion(new Date());
				f.setIdUsuarioModificacion(hitos.getIdUsuario().toString());
				manejadorFechaCaso.actualizarFechaCaso(f);
			}
		}

		FechaCaso trasladoExpecionMerito = manejadorFechaCaso.consultaFechaCaso(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_TRASLADO_EXCEPCIONES_MERITO);
		fecha = trasladoExpecionMerito == null ? null : trasladoExpecionMerito.getFecha();
		if (fecha != null || hitos.getTrasladoExcepcionesMerito() != null) {
			if (!(fecha != null && hitos.getTrasladoExcepcionesMerito() != null
					&& fecha.compareTo(hitos.getTrasladoExcepcionesMerito()) == 0)) {
				FechaCaso f = new FechaCaso();
				FechaCasoPK pk = new FechaCasoPK(UtilDominios.ESTADO_TIPO_FECHA_CASO_TRASLADO_EXCEPCIONES_MERITO,
						idCaso);

				f.setFechaCasoPK(pk);
				f.setCaso(caso);
				f.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				f.setFecha(hitos.getTrasladoExcepcionesMerito());
				f.setFechaUltimaModificacion(new Date());
				f.setIdUsuarioModificacion(hitos.getIdUsuario().toString());
				manejadorFechaCaso.actualizarFechaCaso(f);
			}
		}

		// //////////////////////////////INSERTABLES
		// ////////////////////////////////

		FechaCaso audienciaAclaracion = manejadorFechaCaso.consultaFechaCaso(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_AUDIENCIA_ACLARACIONES_CORREO);
		if (audienciaAclaracion == null && hitos.getAudienciaAclaracion() != null) {
			FechaCaso f = new FechaCaso();
			FechaCasoPK pk = new FechaCasoPK(UtilDominios.ESTADO_TIPO_FECHA_CASO_AUDIENCIA_ACLARACIONES_CORREO, idCaso);

			f.setFechaCasoPK(pk);
			f.setCaso(caso);
			f.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			f.setFecha(hitos.getAudienciaAclaracion());
			f.setFechaUltimaModificacion(new Date());
			f.setIdUsuarioModificacion(hitos.getIdUsuario().toString());
			manejadorFechaCaso.crear(f);
		}

		FechaCaso audienciaAlegatos = manejadorFechaCaso.consultaFechaCaso(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_AUDIENCIA_ALEGATOS);
		if (audienciaAlegatos == null && hitos.getAudienciaAlegatos() != null) {
			FechaCaso f = new FechaCaso();
			FechaCasoPK pk = new FechaCasoPK(UtilDominios.ESTADO_TIPO_FECHA_CASO_AUDIENCIA_ALEGATOS, idCaso);

			f.setFechaCasoPK(pk);
			f.setCaso(caso);
			f.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			f.setFecha(hitos.getAudienciaAlegatos());
			f.setFechaUltimaModificacion(new Date());
			f.setIdUsuarioModificacion(hitos.getIdUsuario().toString());
			manejadorFechaCaso.crear(f);
		}

		FechaCaso audienciaConciliacion = manejadorFechaCaso.consultaFechaCaso(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_AUDIENCIA_CONCILIACION);
		if (audienciaConciliacion == null && hitos.getAudienciaConciliacion() != null) {
			FechaCaso f = new FechaCaso();
			FechaCasoPK pk = new FechaCasoPK(UtilDominios.ESTADO_TIPO_FECHA_CASO_AUDIENCIA_CONCILIACION, idCaso);

			f.setFechaCasoPK(pk);
			f.setCaso(caso);
			f.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			f.setFecha(hitos.getAudienciaConciliacion());
			f.setFechaUltimaModificacion(new Date());
			f.setIdUsuarioModificacion(hitos.getIdUsuario().toString());
			manejadorFechaCaso.crear(f);
		}

		FechaCaso audienciaLaudo = manejadorFechaCaso.consultaFechaCaso(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_AUDIENCIA_LAUDO);
		if (audienciaLaudo == null && hitos.getAudienciaLaudo() != null) {
			FechaCaso f = new FechaCaso();
			FechaCasoPK pk = new FechaCasoPK(UtilDominios.ESTADO_TIPO_FECHA_CASO_AUDIENCIA_LAUDO, idCaso);

			f.setFechaCasoPK(pk);
			f.setCaso(caso);
			f.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			f.setFecha(hitos.getAudienciaLaudo());
			f.setFechaUltimaModificacion(new Date());
			f.setIdUsuarioModificacion(hitos.getIdUsuario().toString());
			manejadorFechaCaso.crear(f);
		}

		FechaCaso audienciaPruebas = manejadorFechaCaso.consultaFechaCaso(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_AUDIENCIA_PRUEBAS);
		if (audienciaPruebas == null && hitos.getAudienciaPruebas() != null) {
			FechaCaso f = new FechaCaso();
			FechaCasoPK pk = new FechaCasoPK(UtilDominios.ESTADO_TIPO_FECHA_CASO_AUDIENCIA_PRUEBAS, idCaso);

			f.setFechaCasoPK(pk);
			f.setCaso(caso);
			f.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			f.setFecha(hitos.getAudienciaPruebas());
			f.setFechaUltimaModificacion(new Date());
			f.setIdUsuarioModificacion(hitos.getIdUsuario().toString());
			manejadorFechaCaso.crear(f);
		}

		FechaCaso audienciaPrimeraTramite = manejadorFechaCaso.consultaFechaCaso(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_AUDIENCIA_PRIMERA_TRAMITE);
		if (audienciaPrimeraTramite == null && hitos.getAudienciaPrimeraTramite() != null) {
			FechaCaso f = new FechaCaso();
			FechaCasoPK pk = new FechaCasoPK(UtilDominios.ESTADO_TIPO_FECHA_CASO_AUDIENCIA_PRIMERA_TRAMITE, idCaso);

			f.setFechaCasoPK(pk);
			f.setCaso(caso);
			f.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			f.setFecha(hitos.getAudienciaPrimeraTramite());
			f.setFechaUltimaModificacion(new Date());
			f.setIdUsuarioModificacion(hitos.getIdUsuario().toString());
			manejadorFechaCaso.crear(f);
		}

		FechaCaso audienciaFijacionGastos = manejadorFechaCaso.consultaFechaCaso(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_FIJACION_GASTOS_HONORARIOS);
		if (audienciaFijacionGastos == null && hitos.getFijacionGastosHonorarios() != null) {
			FechaCaso f = new FechaCaso();
			FechaCasoPK pk = new FechaCasoPK(UtilDominios.ESTADO_TIPO_FECHA_CASO_FIJACION_GASTOS_HONORARIOS, idCaso);

			f.setFechaCasoPK(pk);
			f.setCaso(caso);
			f.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			f.setFecha(hitos.getFijacionGastosHonorarios());
			f.setFechaUltimaModificacion(new Date());
			f.setIdUsuarioModificacion(hitos.getIdUsuario().toString());
			manejadorFechaCaso.crear(f);
		}

		FechaCaso tribunalConsignaCAC = manejadorFechaCaso.consultaFechaCaso(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_TRIBUNAL_CONSIGNA_CAC);
		if (tribunalConsignaCAC == null && hitos.getTribunalConsignaCAC() != null) {
			FechaCaso f = new FechaCaso();
			FechaCasoPK pk = new FechaCasoPK(UtilDominios.ESTADO_TIPO_FECHA_CASO_TRIBUNAL_CONSIGNA_CAC, idCaso);

			f.setFechaCasoPK(pk);
			f.setCaso(caso);
			f.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			f.setFecha(hitos.getTribunalConsignaCAC());
			f.setFechaUltimaModificacion(new Date());
			f.setIdUsuarioModificacion(hitos.getIdUsuario().toString());
			manejadorFechaCaso.crear(f);
		}

	}

	/**
	 * Registra una fecha en fechaCaso
	 * 
	 * @param fecha
	 * @param tipoFecha
	 * @param idCaso
	 * @param idUsuario
	 * @return
	 */

	@Override
	public FechaCaso registrarFechaCaso(Date fecha, String tipoFecha, Long idCaso, String idUsuario) {
		FechaCaso f = new FechaCaso();
		FechaCasoPK pk = new FechaCasoPK(tipoFecha, idCaso);
		Caso caso = manejadorCaso.buscar(idCaso);

		f.setFechaCasoPK(pk);
		f.setCaso(caso);
		f.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		f.setFecha(fecha);
		f.setFechaUltimaModificacion(new Date());
		f.setIdUsuarioModificacion(idUsuario);
		return manejadorFechaCaso.actualizarFechaCaso(f);
	}

	/**
	 * Calcula la fecha limite para cierre del caso, si no existe la fecha, la
	 * calcula.
	 * 
	 * @param idCaso
	 * @return
	 */

	@Override
	public Date calcularFechaLimiteParaCierreDeCaso(Long idCaso) {
		Date fechaIniContTer = manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_INICIO_CONTEO_TERMINOS);
		if (fechaIniContTer == null) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR155.toString()));
		}
		//Se consulta el caso
		Caso caso = manejadorCaso.buscarCasoActivo(idCaso);
		
		//Se consulta suspensiones
		int diasInterrupcciones = suspensionFacade.obtenerDiasCalendarioSuspendidos(idCaso,
				UtilDominios.TIPO_SUSPENSION_INTERRUPCION);
		int diasSuspensiones = suspensionFacade.obtenerDiasCalendarioSuspendidos(idCaso,
				UtilDominios.TIPO_SUSPENSION_SUSPENSION_ARBITRAL);
		int diasProrrogas = suspensionFacade.obtenerDiasCalendarioSuspendidos(idCaso,
				UtilDominios.TIPO_SUSPENSION_PRORROGA);		
		int diasSuspendidoPartes = suspensionFacade.obtenerDiasSuspendidosPartes(idCaso);
		
		Date fechaLimiteCierreCaso = null;
		//Se calcula la fecha limite segun el tipo de termino
		if(UtilDominios.TIPO_TERMINO_DE_PROCESO_TERMINOS_DE_LEY.equals(caso.getTerminosDeProceso())){
			
			List<ParametroDeServicio> parametroDeServicio = manejadorParametroDeServicio.consultarParametrosDeServicio(
					Arrays.asList(UtilConstantes.DIAS_LIMITE_PARA_CIERRE_DE_CASO), caso.getIdServicio(), UtilDominios.DIAS_LIMITE_PARA_CIERRE_DE_CASO);
			
			if(parametroDeServicio == null) 
				throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR037.toString()));
							
			int diasLimiteParaCerrarCaso = Integer.parseInt(parametroDeServicio.get(0).getValor());
									
			int mesesLimiteParaCerrarCaso = (diasLimiteParaCerrarCaso >= UtilConstantes.DIAS_PROMEDIO_MES
					? ((diasLimiteParaCerrarCaso + UtilConstantes.DIAS_PROMEDIO_MES - 1) / UtilConstantes.DIAS_PROMEDIO_MES)
					: 1);
			
			fechaLimiteCierreCaso = UtilOperaciones.adicionarDiasFecha(UtilOperaciones.adicionarMesesFecha(fechaIniContTer, mesesLimiteParaCerrarCaso),
				diasInterrupcciones + diasSuspensiones + diasProrrogas);	
		}
		else{
			Long diasDuracion = null;
			int diasSuspensionesInt = diasInterrupcciones + diasSuspensiones + diasProrrogas;
			try {
				 diasDuracion = Long.parseLong(caso.getDuracion());	
								
			} catch (Exception e) {
				throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR920.toString()));
			}
			
			fechaLimiteCierreCaso = this.calcularFechaLimitePorPeriodicidad(caso, diasSuspensionesInt, fechaIniContTer, diasDuracion);		
		}
		if(fechaLimiteCierreCaso != null && diaFestivoFacade.validarSiFechaEsHabil(fechaLimiteCierreCaso)){
			fechaLimiteCierreCaso = diaFestivoFacade.restarDiasHabilesAFecha(fechaLimiteCierreCaso, (long) 1);
		}
		return fechaLimiteCierreCaso;
		
	}
	
	private Date calcularFechaLimitePorPeriodicidad(Caso caso, int diasSuspensionesInt, Date fechaIniContTer, Long diasDuracion){
		
		Date fechaLimiteCierreCaso = null;
		
		if(UtilDominios.PERIODICIDAD_TERMINOS_DIA.equals(caso.getPeriodicidadTerminos()) && UtilDominios.TIPO_PERIODICIDAD_TERMINOS_HABIL.equals(caso.getTipoPeriodicidadTerminos())){
			fechaLimiteCierreCaso = this.diaFestivoFacade.sumarDiasHabilesAFecha(fechaIniContTer, diasDuracion);
			fechaLimiteCierreCaso = UtilOperaciones.adicionarDiasFecha(fechaLimiteCierreCaso, diasSuspensionesInt);
				
		}else{
			if(UtilDominios.PERIODICIDAD_TERMINOS_DIA.equals(caso.getPeriodicidadTerminos())){
				fechaLimiteCierreCaso = UtilOperaciones.adicionarDiasFecha(UtilOperaciones.adicionarDiasFecha(fechaIniContTer,Integer.parseInt(caso.getDuracion()))
						,diasSuspensionesInt);	
			}else if(UtilDominios.PERIODICIDAD_TERMINOS_MES.equals(caso.getPeriodicidadTerminos())){
				fechaLimiteCierreCaso = UtilOperaciones.adicionarDiasFecha(UtilOperaciones.adicionarMesesFecha(fechaIniContTer,Integer.parseInt(caso.getDuracion()))
						,diasSuspensionesInt);	
			}else{
				fechaLimiteCierreCaso = UtilOperaciones.adicionarDiasFecha(UtilOperaciones.adicionarMesesFecha(fechaIniContTer,Integer.parseInt(caso.getDuracion())*12)
						,diasSuspensionesInt);	
			}
			
		}
		
		return fechaLimiteCierreCaso;
		
	}
	
	/**
	 * Calcula los días faltantes de un caso para que se cumpla su término de
	 * cierre
	 * 
	 * @param idCaso
	 * @param diasTranscurridos
	 * @return
	 */
	@Override
	public int calcularDiasFaltantesParaCierreDeCaso(Long idCaso, int diasTranscurridos) {
		// Se consulta el caso
		Caso caso = manejadorCaso.buscarCasoActivo(idCaso);
		int diasInterrupcciones = suspensionFacade.obtenerDiasHabilesSuspendidos(idCaso,
				UtilDominios.TIPO_SUSPENSION_INTERRUPCION);
		int diasSuspensiones = suspensionFacade.obtenerDiasHabilesSuspendidos(idCaso,
				UtilDominios.TIPO_SUSPENSION_SUSPENSION_ARBITRAL);
		int diasProrrogas = suspensionFacade.obtenerDiasHabilesSuspendidos(idCaso,
				UtilDominios.TIPO_SUSPENSION_PRORROGA);

		// Consultar dia limite para cierre de caso de acuerdo al servicio
		// asociado
		List<ParametroDeServicio> parametroDeServicio = manejadorParametroDeServicio.consultarParametrosDeServicio(
				Arrays.asList(UtilConstantes.DIAS_LIMITE_PARA_CIERRE_DE_CASO), caso.getIdServicio(),
				UtilDominios.DIAS_LIMITE_PARA_CIERRE_DE_CASO);

		if (parametroDeServicio == null)
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR037.toString()));

		int diasParaCerrarCaso = Integer.parseInt(parametroDeServicio.get(0).getValor()) + diasInterrupcciones
				+ diasSuspensiones + diasProrrogas;

		return (diasParaCerrarCaso - diasTranscurridos);
	}

	@Override
	public void creaFechaCasoReApertura(Caso caso, String usuarioModificacion) {
		FechaCasoPK fechaCasoPK = new FechaCasoPK(UtilDominios.ESTADO_TIPO_FECHA_CASO_CIERRE,
				caso.getIdCaso());
		
		FechaCaso fechaCaso = new FechaCaso();
		fechaCaso.setFechaCasoPK(fechaCasoPK);
				
		manejadorFechaCaso.eliminar(fechaCaso);

		fechaCasoPK = new FechaCasoPK(UtilDominios.TIPO_FECHA_CASO_REAPERTURA,
				caso.getIdCaso());

		fechaCaso = new FechaCaso();
		fechaCaso.setCaso(caso);
		fechaCaso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		fechaCaso.setFecha(new Date());
		fechaCaso.setFechaUltimaModificacion(new Date());
		fechaCaso.setIdUsuarioModificacion(usuarioModificacion);
		fechaCaso.setFechaCasoPK(fechaCasoPK);

		manejadorFechaCaso.crear(fechaCaso);
		
	}

	// protected region metodos adicionales end

}
