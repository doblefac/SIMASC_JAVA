package com.ccb.simasc.negocio.arbitraje;
// protected region imports fachada on begin

import java.io.Serializable;

// Escriba en esta seccion sus modificaciones

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorFechaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorSuspension;
import com.ccb.simasc.transversal.dto.formularios.HitosCasoDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.FechaCaso;
import com.ccb.simasc.transversal.entidades.FechaCasoPK;
import com.ccb.simasc.transversal.entidades.Suspension;
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
public class FechaCasoFacade extends AbstractFacade<Dominio> implements Serializable {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	@EJB
	private ManejadorSuspension manejadorSuspension;
	
	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private ManejadorAudiencia manejadorAudiencia;

	@EJB
	private ManejadorFechaCaso manejadorFechaCaso;

	// protected region atributos end

	public FechaCasoFacade() {
		super(Dominio.class);
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
		;
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

	
	public FechaCaso registrarFechaCaso(Date fecha, String tipoFecha, Long idCaso, Long idUsuario) {
		FechaCaso f = new FechaCaso();
		FechaCasoPK pk = new FechaCasoPK(tipoFecha, idCaso);
		Caso caso = manejadorCaso.buscar(idCaso);

		f.setFechaCasoPK(pk);
		f.setCaso(caso);
		f.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		f.setFecha(fecha);
		f.setFechaUltimaModificacion(new Date());
		f.setIdUsuarioModificacion(idUsuario.toString());
		return manejadorFechaCaso.actualizarFechaCaso(f);
	}

	/**
	 * Calcula la fecha limite para cierre del caso, si no existe la fecha, la
	 * calcula.
	 * 
	 * @param idCaso
	 * @return
	 * @throws Exception 
	 */

	
	public Date calcularFechaLimiteParaCierreDeCaso(Long idCaso){
		Date fechaIniContTer = manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_INICIO_CONTEO_TERMINOS);
		if (fechaIniContTer == null) {
			return null;
		}
		int diasInterrupcciones = obtenerDiasHabilesSuspendidos(idCaso,
				UtilDominios.TIPO_SUSPENSION_INTERRUPCION);
		int diasSuspensiones = obtenerDiasHabilesSuspendidos(idCaso,
				UtilDominios.TIPO_SUSPENSION_SUSPENSION_ARBITRAL);
		int diasProrrogas = obtenerDiasHabilesSuspendidos(idCaso,
				UtilDominios.TIPO_SUSPENSION_PRORROGA);
		int mesesLimiteParaCerrarCaso = 6;
		return UtilOperaciones.adicionarDiasHabilesFecha(UtilOperaciones.adicionarMesesFecha(fechaIniContTer, mesesLimiteParaCerrarCaso),
				diasInterrupcciones + diasSuspensiones + diasProrrogas);
	}
	
	private int obtenerDiasHabilesSuspendidos(Long idCaso, String tipo) {
		List<Suspension> suspensiones = (List<Suspension>) manejadorSuspension.consultaSuspensionesPorCasoTipo(idCaso,
				tipo);
		int dias = 0;
		for (Suspension suspension : suspensiones) {
			if (suspension.getFechaFinal() != null && suspension.getFechaInicial() != null)
				dias += UtilOperaciones.obtenerDiasHabilesEntreFechas(suspension.getFechaInicial(),
						suspension.getFechaFinal());
		}
		return dias;
	}

	@Override
	protected EntityManager getEntityManager() {
		return null;
	}

	// protected region metodos adicionales end

}
