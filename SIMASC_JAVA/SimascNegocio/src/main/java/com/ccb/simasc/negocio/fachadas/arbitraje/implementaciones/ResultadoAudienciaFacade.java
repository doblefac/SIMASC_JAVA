package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorCuota;
import com.ccb.simasc.integracion.manejadores.ManejadorInasistencia;
import com.ccb.simasc.integracion.manejadores.ManejadorObligacion;
import com.ccb.simasc.integracion.manejadores.ManejadorObligacionParte;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroDeServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorResultadoAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlmacenamientoDocumentosFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IResultadoAudienciaFacade;
import com.ccb.simasc.negocio.fachadas.reparto.implementaciones.RepartoSvc;
import com.ccb.simasc.transversal.dto.CuotaDTO;
import com.ccb.simasc.transversal.dto.DatosEntradaRepartoDTO;
import com.ccb.simasc.transversal.dto.InasistenciaDTO;
import com.ccb.simasc.transversal.dto.ObligacionDTO;
import com.ccb.simasc.transversal.dto.ObligacionParteDTO;
import com.ccb.simasc.transversal.dto.ResultadoAudienciaDTO;
import com.ccb.simasc.transversal.dto.ResultadoCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.InfoAdicionalResultadoDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Cuota;
import com.ccb.simasc.transversal.entidades.CuotaPK;
import com.ccb.simasc.transversal.entidades.Inasistencia;
import com.ccb.simasc.transversal.entidades.InasistenciaPK;
import com.ccb.simasc.transversal.entidades.NombramientoPersona;
import com.ccb.simasc.transversal.entidades.Obligacion;
import com.ccb.simasc.transversal.entidades.ObligacionPK;
import com.ccb.simasc.transversal.entidades.ObligacionParte;
import com.ccb.simasc.transversal.entidades.ObligacionPartePK;
import com.ccb.simasc.transversal.entidades.ParametroDeServicio;
import com.ccb.simasc.transversal.entidades.ResultadoAudiencia;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.EstadosCasoException;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilParamServicio;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link ResultadoAudiencia}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ResultadoAudienciaFacade extends AccesoFacade<ResultadoAudiencia, Long, ResultadoAudienciaDTO>
		implements IResultadoAudienciaFacade {
	private static final Logger logger = LogManager.getLogger(ResultadoAudienciaFacade.class.getName());
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	// contexto de sesion de usuario.
	@Inject
	private ApplicationRequestContext appContext;

	@EJB
	private ManejadorResultadoAudiencia manejadorResultadoAudiencia;

	@EJB
	private ManejadorAudiencia manejadorAudiencia;

	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private ManejadorObligacion manejadorObligacion;

	@EJB
	private ManejadorObligacionParte manejadorObligacionParte;

	@EJB
	private ManejadorCuota manejadorCuota;

	@EJB
	private ManejadorRol manejadorRol;

	@EJB
	private ManejadorInasistencia manejadorInasistencia;

	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	private IDominioFacade dominioFacade;

	@EJB
	private CasoFacade casoFacade;

	@EJB
	private AudienciaFacade audienciaFacade;

	@EJB
	private ObligacionFacade obligacionFacade;

	@EJB
	private RepartoSvc repartosvcFacade;
	
	@EJB
	private IAlmacenamientoDocumentosFacade almacenamientoDocumentosFacade;
	
	@EJB
	private ManejadorParametroDeServicio manejadorParametroDeServicio;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorResultadoAudiencia;
	}

	@Override
	public ResultadoAudienciaDTO transformarSinDependencias(ResultadoAudiencia obj) {
		ResultadoAudienciaDTO dto = new ResultadoAudienciaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ResultadoAudienciaDTO transformarConDependencias(ResultadoAudiencia obj) {
		ResultadoAudienciaDTO dto = new ResultadoAudienciaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ResultadoAudiencia transformarEntidadConDependencias(ResultadoAudiencia obj) {
		ResultadoAudiencia dto = new ResultadoAudiencia();
		dto = new ResultadoAudienciaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ResultadoAudiencia transformarEntidadSinDependencias(ResultadoAudiencia obj) {
		ResultadoAudiencia dto = new ResultadoAudiencia();
		dto = new ResultadoAudienciaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public void registrarResultadoAudiencia(ResultadoAudienciaDTO resultadoDTO, String usuarioModificacion)
			throws SIMASCNegocioExcepcion {
		try {

			Audiencia audiencia = manejadorAudiencia.buscar(resultadoDTO.getIdAudiencia());

			List<ResultadoAudiencia> resultados = manejadorResultadoAudiencia
					.consultarResultadoAudiencias(audiencia.getIdAudiencia());

			String tipoResultado = resultadoDTO.getTipoResultadoAudiencia();

			// se verifica si se puede adicionar un resultado de audiencia.
			if (resultados != null && !resultados.isEmpty()) {
				validarRegistroResultado(resultados, tipoResultado);
			}

			// se registra un resultado de la audiencia
			ResultadoAudiencia resultadoAudiencia = guardarResultadoAudiencia(resultadoDTO, usuarioModificacion);

			// guardar obligaciones por resultado audiencia
			if (resultadoDTO.getObligacionList() != null)
				guardarObligacionesResultadoAudiencia(resultadoDTO.getObligacionList(), usuarioModificacion,
						resultadoAudiencia.getIdResultadoAudiencia());

			/*
			 * guardar inasistencia para tipo de resultado inasistencia o
			 * imposibilidad
			 */
			if (resultadoDTO.getAudienciaDTO() != null && resultadoDTO.getAudienciaDTO().getInasistenciaList() != null)
				guardarInasistencias(resultadoDTO.getAudienciaDTO().getInasistenciaList(), usuarioModificacion);

			String resultado = null;
			/*
			 * se obtiene el resultado general de la audiencia de acuerdo a las
			 * reglas de negocio.
			 */
			if (resultados != null && !resultados.isEmpty()) {
				resultado = obtenerResultadoAudiencia(resultados);
			} else {
				resultado = tipoResultado;
			}

			actualizarResultadoGeneralAudiencia(audiencia, resultado, usuarioModificacion);

			Iterator<Map.Entry<String, String>> eventoEstado = obtenerEstadoCaso(resultado).entrySet().iterator();
			actualizarEstadoCaso(eventoEstado, audiencia.getIdCaso());

		} catch (Exception e) {
			logger.error("Error: ", e);
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString()));
		}

	}

	private void actualizarResultadoGeneralAudiencia(Audiencia audiencia, String resultado,
			String idUsuarioModificacion) {

		audiencia.setResultado(resultado);
		audiencia.setFechaUltimaModificacion(new Date());
		audiencia.setIdUsuarioModificacion(idUsuarioModificacion);
		manejadorAudiencia.actualizar(audiencia);
	}

	private void actualizarEstadoCaso(Iterator<Map.Entry<String, String>> eventoEstado, Long idCaso)
			throws EstadosCasoException {
		while (eventoEstado.hasNext()) {

			Map.Entry<String, String> caso = eventoEstado.next();
			String estado = caso.getValue();
			String tipoEvento = caso.getKey();
			List<String> args = new ArrayList<>();
			args.add( dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ESTADO_CASO, estado).toLowerCase() );
			String observaciones = (String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO455.toString()), args.toArray()));
			casoFacade.cambiarEstadoCaso(idCaso, estado, new Date(), tipoEvento, observaciones);
		}
	}

	private void guardarObligacionesResultadoAudiencia(List<ObligacionDTO> obligaciones, String usuarioModificacion,
			Long idResultadoAudiencia) {
		for (ObligacionDTO obligacionDTO : obligaciones) {
			ObligacionPK obPK = obligacionDTO.getObligacionPK();
			Obligacion obligacion = manejadorObligacion.buscar(obPK);
			if (obligacion == null) {
				obligacion = new Obligacion();
				obPK = new ObligacionPK();
			}
			obPK.setIdResultadoAudiencia(idResultadoAudiencia);
			String tipoObligacion = obligacionDTO.getObligacionPK().getTipoObligacion();
			obPK.setTipoObligacion(tipoObligacion);
			obligacion.setObligacionPK(obPK);
			obligacion.setValorTotalAcuerdo(obligacionDTO.getValorTotalAcuerdo());
			obligacion.setFormaDePago(obligacionDTO.getFormaDePago());
			obligacion.setNombreBanco(obligacionDTO.getNombreBanco());
			obligacion.setNumeroCuenta(obligacionDTO.getNumeroCuenta());
			obligacion.setObservaciones(obligacionDTO.getObservaciones());
			obligacion.setIdUsuarioModificacion(usuarioModificacion);
			obligacion.setFechaUltimaModificacion(new Date());
			obligacion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			obligacion.setDireccion(obligacionDTO.getDireccion());
			if (tipoObligacion.equals(UtilDominios.OBLIGACION_DAR)) {
				obligacion.setCompromiso(UtilConstantes.COMPROMISO_OBLIGACION_DAR);
				obligacion.setFechaCompromiso(obtenerFechaCompromisoObligacionDar(obligacionDTO.getCuotaList()));
			} else {
				obligacion.setCompromiso(obligacionDTO.getCompromiso());
				obligacion.setFechaCompromiso(obligacionDTO.getFechaCompromiso());
			}
			manejadorObligacion.actualizar(obligacion);

			if (obligacion.getObligacionParteList() != null) {
				inactivarPartesObligacion(obligacion.getObligacionParteList());
			}
			guardarObligacionPartesPorResultado(obligacionDTO.getObligacionParteList(), usuarioModificacion,
					idResultadoAudiencia);
			if (obligacionDTO.getCuotaList() != null) {
				if (obligacion.getCuotaList() != null) {
					inactivarCuotas(obligacion.getCuotaList());
				}
				guardarCuotasPorObligacion(obligacionDTO.getCuotaList(), idResultadoAudiencia, usuarioModificacion);
			}

		}
	}

	private void guardarObligacionPartesPorResultado(List<ObligacionParteDTO> obligacionPartes,
			String usuarioModificacion, Long idResultadoAudiencia) {
		for (ObligacionParteDTO obParteDTO : obligacionPartes) {
			ObligacionPartePK obPartePK = obParteDTO.getObligacionPartePK();
			ObligacionParte oblParte = manejadorObligacionParte.buscar(obPartePK);
			if (oblParte == null) {
				oblParte = new ObligacionParte();
				obPartePK = new ObligacionPartePK();
			}
			obPartePK.setIdCaso(obParteDTO.getObligacionPartePK().getIdCaso());
			obPartePK.setIdPersona(obParteDTO.getObligacionPartePK().getIdPersona());
			obPartePK.setIdResultadoAudiencia(idResultadoAudiencia);
			obPartePK.setIdRol(obParteDTO.getObligacionPartePK().getIdRol());
			obPartePK.setTipoObligacion(obParteDTO.getObligacionPartePK().getTipoObligacion());
			oblParte.setObligacionPartePK(obPartePK);
			oblParte.setTipoParteResultado(obParteDTO.getTipoParteResultado());
			oblParte.setIdUsuarioModificacion(usuarioModificacion);
			oblParte.setFechaUltimaModificacion(new Date());
			oblParte.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			manejadorObligacionParte.actualizar(oblParte);
		}
	}

	private void guardarCuotasPorObligacion(List<CuotaDTO> cuotas, Long idResultadoAudiencia,
			String usuarioModificacion) {
		for (CuotaDTO cuotaDTO : cuotas) {
			CuotaPK cuotaPK = cuotaDTO.getCuotaPK();
			Cuota cuota = manejadorCuota.buscar(cuotaPK);
			if (cuota == null) {
				cuota = new Cuota();
				cuotaPK = new CuotaPK();
			}
			cuotaPK.setNumeroCuota(cuotaDTO.getCuotaPK().getNumeroCuota());
			cuotaPK.setTipoObligacion(cuotaDTO.getCuotaPK().getTipoObligacion());
			cuotaPK.setIdResultadoAudiencia(idResultadoAudiencia);
			cuota.setCuotaPK(cuotaPK);
			cuota.setIdUsuarioModificacion(usuarioModificacion);
			cuota.setFechaUltimaModificacion(new Date());
			cuota.setFechaPrevista(cuotaDTO.getFechaPrevista());
			cuota.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			manejadorCuota.actualizar(cuota);
		}
	}

	private ResultadoAudiencia guardarResultadoAudiencia(ResultadoAudienciaDTO resultadoDTO,
			String usuarioModificacion) {
		Long idResultadoAudiencia = resultadoDTO.getIdResultadoAudiencia();
		ResultadoAudiencia resultadoAudiencia = manejadorResultadoAudiencia.buscar(idResultadoAudiencia);
		if (resultadoAudiencia == null) {
			resultadoAudiencia = new ResultadoAudiencia();
		}
		resultadoAudiencia.setTipoResultadoAudiencia(resultadoDTO.getTipoResultadoAudiencia());
		resultadoAudiencia.setObservaciones(resultadoDTO.getObservaciones());
		resultadoAudiencia.setIdUsuarioModificacion(usuarioModificacion);
		resultadoAudiencia.setFechaUltimaModificacion(new Date());
		resultadoAudiencia.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		resultadoAudiencia.setIdAudiencia(resultadoDTO.getIdAudiencia());
		resultadoAudiencia.setEstado(UtilDominios.ESTADO_RESULTADO_SIN_DOCUMENTO);

		if (idResultadoAudiencia != null) {
			manejadorResultadoAudiencia.actualizar(resultadoAudiencia);
		} else {
			manejadorResultadoAudiencia.crear(resultadoAudiencia);
		}

		return resultadoAudiencia;
	}

	private Date obtenerFechaCompromisoObligacionDar(List<CuotaDTO> cuotas) {
		int cantidadCuotas = cuotas.size();
		CuotaDTO cuota = cuotas.get(cantidadCuotas - 1);
		return cuota.getFechaPrevista();
	}

	/**
	 * Metodo que permite guardar en la entidad Inasistencia, para tipos de
	 * resultados Inasistencia e imposibilidad.
	 * 
	 * @param inasistencias
	 * @param usuarioModificacion
	 */
	private void guardarInasistencias(List<InasistenciaDTO> inasistencias, String usuarioModificacion) {
		for (InasistenciaDTO inasistenciaDto : inasistencias) {
			InasistenciaPK inasistenciaPK = inasistenciaDto.getInasistenciaPK();
			Inasistencia inasistencia = manejadorInasistencia.buscar(inasistenciaPK);
			if (inasistencia == null) {
				inasistencia = new Inasistencia();
				inasistenciaPK = new InasistenciaPK();
			}

			inasistenciaPK.setIdAudiencia(inasistenciaDto.getInasistenciaPK().getIdAudiencia());
			inasistenciaPK.setIdCaso(inasistenciaDto.getInasistenciaPK().getIdCaso());
			inasistenciaPK.setIdPersona(inasistenciaDto.getInasistenciaPK().getIdPersona());
			inasistenciaPK.setIdRol(inasistenciaDto.getInasistenciaPK().getIdRol());
			inasistencia.setInasistenciaPK(inasistenciaPK);
			inasistencia.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			inasistencia.setFechaUltimaModificacion(new Date());
			inasistencia.setIdUsuarioModificacion(usuarioModificacion);
			inasistencia.setPresentaExcusa(inasistenciaDto.getPresentaExcusa());
			inasistencia.setTipo(inasistenciaDto.getTipo());
			inasistencia.setIdDocumento(inasistenciaDto.getIdDocumento());
			manejadorInasistencia.crear(inasistencia);
		}
	}

	/**
	 * Metodo que restringe que no se tenga mas de un resultado de audiencia si
	 * ya existe un tipo de resultado suspension, reprogramacion, cancelacion o
	 * falta de competencia.
	 * 
	 * @param resultados:
	 *            Resultados que actualmente tiene la audiencia.
	 * @param tipoResultado:
	 *            el tipo de resultado a registrar.
	 * @throws SIMASCNegocioExcepcion
	 */
	private void validarRegistroResultado(List<ResultadoAudiencia> resultados, String tipoResultado)
			throws SIMASCNegocioExcepcion {
		if (resultados.size() > 1 && (tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_CANCELADA)
				|| tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_SUSPENSION)
				|| tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_REPROGRAMACION)
				|| tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_FALTA_COMPETENCIA))) {

			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR163.toString()));

		} else if (validarResultado(resultados, UtilDominios.RESULTADO_AUDIENCIA_REPROGRAMACION)
				|| validarResultado(resultados, UtilDominios.RESULTADO_AUDIENCIA_CANCELADA)
				|| validarResultado(resultados, UtilDominios.RESULTADO_AUDIENCIA_SUSPENSION)
				|| validarResultado(resultados, UtilDominios.RESULTADO_AUDIENCIA_FALTA_COMPETENCIA)) {

			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR162.toString()));

		}

	}

	/**
	 * Metodo que permite establecer cual es el resultado general de la
	 * audiencia.
	 * 
	 * @param resultados:
	 *            resultados registrados de la audiencia.
	 * @return resultado general.
	 */
	private String obtenerResultadoAudiencia(List<ResultadoAudiencia> resultados) {

		String resultado = null;

		if (validarResultado(resultados, UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_TOTAL)) {
			resultado = UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_TOTAL;
		} else if (validarResultado(resultados, UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_PARCIAL)) {
			resultado = UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_PARCIAL;
		} else if (validarResultado(resultados, UtilDominios.RESULTADO_AUDIENCIA_IMPOSIBILIDAD_DE_ACUERDO)) {
			resultado = UtilDominios.RESULTADO_AUDIENCIA_IMPOSIBILIDAD_DE_ACUERDO;
		} else {
			resultado = UtilDominios.RESULTADO_AUDIENCIA_INASISTENCIA;
		}

		return resultado;

	}

	/**
	 * Metodo que permite obtener el estado del caso dependiendo del resultado
	 * general de la audiencia.
	 * 
	 * @param tipoResultado:
	 *            resultado de la audiencia.
	 * @return estado del caso.
	 */
	private Map<String, String> obtenerEstadoCaso(String tipoResultado) {
		Map<String, String> eventoEstado = new HashMap<String, String>();

		if (tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_PARCIAL)
				|| tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_TOTAL)
				|| tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_IMPOSIBILIDAD_DE_ACUERDO)
				|| tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_INASISTENCIA)
				|| tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_ACUERDO)) {

			eventoEstado.put(UtilDominios.TIPO_EVENTO_CASO_PENDIENTE_ENTREGA_DOCUMENTO_RESULTADO,
					UtilDominios.ESTADO_CASO_PENDIENTE_ENTREGA_DOCUMENTO_RESULTADO);
		} else if (tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_REPROGRAMACION)
				|| tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_SUSPENSION)
				|| tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_CANCELADA)
				|| tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_ARREGLO_DIRECTO)) {

			eventoEstado.put(UtilDominios.TIPO_EVENTO_CASO_CONCILIACION, UtilDominios.ESTADO_CASO_EN_CONCILIACION);		
		} else {
			eventoEstado.put(UtilDominios.TIPO_EVENTO_FALTA_DE_COMPENTENCIA,
					UtilDominios.ESTADO_CASO_FALTA_DE_COMPETENCIA);

		}
		return eventoEstado;
	}

	/**
	 * Metodo que permite validar si existe un tipo de resultado en los
	 * resultados que tiene actualmente la audiencia.
	 * 
	 * @param resultados:
	 *            Resultados que cuenta la audiencia.
	 * @param tipoResultado:
	 *            tipo de resultado de audiencia.
	 * @return true, si el resultado se encontro en la lista.
	 */
	private boolean validarResultado(List<ResultadoAudiencia> resultados, String tipoResultado) {
		boolean encontrado = false;
		for (ResultadoAudiencia resultado : resultados) {
			if (resultado.getTipoResultadoAudiencia().equals(tipoResultado)) {
				encontrado = true;
			}
		}
		return encontrado;
	}

	@Override
	public List<ResultadoAudienciaDTO> consultarResultadosAudiencia(Long idAudiencia) {
		List<ResultadoAudiencia> resultadosAudiencia = manejadorResultadoAudiencia
				.consultarResultadoAudiencias(idAudiencia);
		List<ResultadoAudienciaDTO> resultados = new ArrayList<ResultadoAudienciaDTO>();

		for (ResultadoAudiencia resultado : resultadosAudiencia) {
			ResultadoAudienciaDTO resultadoAudiencia = new ResultadoAudienciaDTO();
			resultadoAudiencia.setIdResultadoAudiencia(resultado.getIdResultadoAudiencia());
			resultadoAudiencia.setTipoResultadoAudiencia(resultado.getTipoResultadoAudiencia());
			resultadoAudiencia.setIdDocumento(resultado.getIdDocumento());
			resultadoAudiencia.setObservaciones(resultado.getObservaciones());
			InfoAdicionalResultadoDTO informacionResultado = obtenerInformacionResultadoAudiencia(
					resultado.getObligacionList());
			resultadoAudiencia.setInfoAdicional(informacionResultado);
			resultadoAudiencia.setAudienciaDTO(audienciaFacade.transformarConDependencias(resultado.getAudiencia()));
			List<ObligacionDTO> obligacionListDTO = null;
			if (resultado.getObligacionList() != null)
				obligacionListDTO = (List<ObligacionDTO>) obligacionFacade.transformarColeccionConDependencias(
						resultado.getObligacionList(), new ArrayList<ObligacionDTO>());
			resultadoAudiencia.setObligacionList(obligacionListDTO);

			resultados.add(resultadoAudiencia);
		}
		return resultados;
	}

	/**
	 * Metodo que permite calcular el valor total acuerdo de un resultado de
	 * audiencia y obtener las partes involucradas.
	 * 
	 * @param obligaciones:
	 *            Lista de obligaciones de un resultado de audiencia.
	 * @return InfoAdicionalResultadoDTO.
	 */
	private InfoAdicionalResultadoDTO obtenerInformacionResultadoAudiencia(List<Obligacion> obligaciones) {
		InfoAdicionalResultadoDTO infoResultado = new InfoAdicionalResultadoDTO();
		BigDecimal valorTotalAcuerdo = new BigDecimal(UtilConstantes.CERO);
		Set<PersonaBasicaDTO> temPersonaBasica = new LinkedHashSet<PersonaBasicaDTO>();
		List<PersonaBasicaDTO> partesInvolucradas = new ArrayList<PersonaBasicaDTO>();
		for (Obligacion obligacion : obligaciones) {
			BigDecimal valorAcuerdo = obligacion.getValorTotalAcuerdo();
			valorTotalAcuerdo = valorAcuerdo != null ? valorTotalAcuerdo.add(valorAcuerdo)
					: valorTotalAcuerdo.add(new BigDecimal(0));
			temPersonaBasica.addAll(obtenerPartesInvolucradas(obligacion.getObligacionParteList()));
		}
		partesInvolucradas.addAll(temPersonaBasica);
		infoResultado.setValorAcuerdo(valorTotalAcuerdo);
		infoResultado.setPartesInvolucradas(partesInvolucradas);
		return infoResultado;

	}

	/**
	 * Metodo que permite obtener las partes involucradas de una obligacion.
	 * 
	 * @param partesObligacion:
	 *            Lista de partes de una obligacion.
	 * @return Lista de partes involucradas. Lista de tipo PersonaBasicaDTO.
	 */
	private List<PersonaBasicaDTO> obtenerPartesInvolucradas(List<ObligacionParte> partesObligacion) {
		List<PersonaBasicaDTO> partesInvolucradas = new ArrayList<PersonaBasicaDTO>();
		Set<PersonaBasicaDTO> temPersonaBasica = new LinkedHashSet<PersonaBasicaDTO>();
		for (ObligacionParte parte : partesObligacion) {
			PersonaBasicaDTO personaBasica = PersonaBasicaDTO
					.newPersonaBasicaDTO(parte.getRolPersonaCaso().getPersona());
			temPersonaBasica.add(personaBasica);
		}
		partesInvolucradas.addAll(temPersonaBasica);
		return partesInvolucradas;

	}

	@Override
	public void actualizarDocumentoResultadoAudiencia(Long idDocumento, Long idResultadoAudiencia,
			String idUsuarioModificacion, Long idServicio) throws EstadosCasoException {

		ResultadoAudiencia resultadoAudiencia = manejadorResultadoAudiencia.buscar(idResultadoAudiencia);
		Long idDocumentoAntiguo = null;
		if(resultadoAudiencia.getIdDocumento() != null){
			idDocumentoAntiguo = new Long(resultadoAudiencia.getIdDocumento());
		}
		resultadoAudiencia.setIdDocumento(idDocumento);
		resultadoAudiencia.setEstado(UtilDominios.ESTADO_RESULTADO_POR_VALIDAR);
		resultadoAudiencia.setFechaUltimaModificacion(new Date());
		resultadoAudiencia.setIdUsuarioModificacion(idUsuarioModificacion);
		manejadorResultadoAudiencia.actualizar(resultadoAudiencia);
		
		String rolEvaluar = UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION;
		if(idServicio!= null) {
			List<ParametroDeServicio> parametroDeServicioNotificacionVencimientoPago = manejadorParametroDeServicio
					.consultarParametrosDeServicio(UtilParamServicio.ROL_ANALISTA_LEGALIDAD_CALIDAD,	idServicio);			
			if (parametroDeServicioNotificacionVencimientoPago != null
					&& !parametroDeServicioNotificacionVencimientoPago.isEmpty()) {
				rolEvaluar = parametroDeServicioNotificacionVencimientoPago.get(0).getValor();
			}
		}
		
		// rol analista de conciliacion
		List<String> roles = Arrays.asList(rolEvaluar);
		// estado asignacion
		List<String> estados = Arrays.asList(UtilDominios.ESTADO_ROL_PERSONA_CASO_ASIGNADO);

		Long idCaso = resultadoAudiencia.getAudiencia().getIdCaso();
		// consultar analista actualmente asignado al caso
		List<RolPersonaCaso> analista = manejadorRolPersonaCaso.consultarPersonasoPorRoleEstado(idCaso, roles, estados,
				true);
		if (analista == null || analista.isEmpty()) {
			realizarRepartoAnalista(resultadoAudiencia, idUsuarioModificacion,rolEvaluar );
			if(rolEvaluar.equals(UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION)) {
				casoFacade.cambiarEstadoCaso(idCaso, UtilDominios.ESTADO_CASO_ASIGNADO_CONTROL_DE_LEGALIDAD, new Date(),
						UtilDominios.TIPO_EVENTO_CASO_CONTROL_LEGALIDAD, UtilConstantes.CAMBIO_ESTADO_CONTROL_LEGALIDAD);
			}else{
				casoFacade.cambiarEstadoCaso(idCaso, UtilDominios.ESTADO_CASO_ASIGNADO_CONTROL_DE_CALIDAD, new Date(),
						UtilDominios.TIPO_EVENTO_CASO_CONTROL_CALIDAD, UtilConstantes.CAMBIO_ESTADO_CONTROL_CALIDAD);
			};
		}
		if(idDocumentoAntiguo != null){
			almacenamientoDocumentosFacade.eliminarDocumento(idDocumentoAntiguo, idUsuarioModificacion);
		}
		
	}

	/**
	 * Metodo que permite realizar el reparto del caso para un analista de
	 * conciliacion.
	 * 
	 * @param resultadoAudiencia:
	 *            objeto de tipo ResultadoAudiencia.
	 * @param idUsuarioModificacion:
	 *            usuario de modificacion.
	 */
	private void realizarRepartoAnalista(ResultadoAudiencia resultadoAudiencia, String idUsuarioModificacion, String nombreRol) {
		// Datos del reparto
		DatosEntradaRepartoDTO datosReparto = new DatosEntradaRepartoDTO();
		datosReparto.setIdCaso(resultadoAudiencia.getAudiencia().getIdCaso());
		datosReparto.setUsuario(idUsuarioModificacion);
		Rol rol = manejadorRol.consultarRolPorNombre(nombreRol);
		datosReparto.setIdRol(rol.getIdRol());
		try {
			if(nombreRol.equals(UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION)) {
				repartosvcFacade.reparto(datosReparto);
			}else{
				repartosvcFacade.repartoEquidad(datosReparto);
			};
		} catch (Exception e) {
			logger.error("Error: ", e);
			throw new SimascException(e.getMessage());
		}
	}

	@Override
	public void modificarResultadoAudiencia(ResultadoAudienciaDTO resultadoDTO, String usuarioModificacion) {
		// se actualiza un resultado de la audiencia
		ResultadoAudiencia resultadoAudiencia = guardarResultadoAudiencia(resultadoDTO, usuarioModificacion);
		// guardar obligaciones por resultado audiencia
		if (resultadoDTO.getObligacionList() != null)
			guardarObligacionesResultadoAudiencia(resultadoDTO.getObligacionList(), usuarioModificacion,
					resultadoAudiencia.getIdResultadoAudiencia());
		// guardar inasistencia para resultados inasistencia o imposibilidad
		if (resultadoDTO.getAudienciaDTO() != null && resultadoDTO.getAudienciaDTO().getInasistenciaList() != null) {
			inactivarInasistencias(resultadoDTO);
			guardarInasistencias(resultadoDTO.getAudienciaDTO().getInasistenciaList(), usuarioModificacion);
		}

	}

	/**
	 * Metodo que permite inactivar las partes de las obligaciones, cuando se
	 * realiza una modificacion en las partes.
	 * 
	 * @param obligacionPartes:
	 *            Lista de partes de la obligacion.
	 */
	private void inactivarPartesObligacion(List<ObligacionParte> obligacionPartes) {
		for (ObligacionParte oblParte : obligacionPartes) {
			oblParte.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
			manejadorObligacionParte.actualizar(oblParte);
		}
	}

	/**
	 * Metodo que permite inactivar las cuotas de las obligaciones, cuando se
	 * realiza una modificacion en la obligacion.
	 * 
	 * @param cuotas
	 */
	private void inactivarCuotas(List<Cuota> cuotas) {
		for (Cuota cuota : cuotas) {
			cuota.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
			manejadorCuota.actualizar(cuota);
		}
	}

	private void inactivarInasistencias(ResultadoAudienciaDTO resultadoDTO) {
		// Indica si el tipo de resultado es Inasistencia o Imposibilidad.
		String tipoInasistencia = resultadoDTO.getAudienciaDTO().getInasistenciaList().get(0).getTipo();
		List<InformacionFiltro> filtros = new ArrayList<InformacionFiltro>();
		InformacionFiltro filtro1 = new InformacionFiltro(TipoFiltro.EXACTO,
				Inasistencia.ENTIDAD_INASISTENCIA_PK_ID_AUDIENCIA, resultadoDTO.getIdAudiencia());
		InformacionFiltro filtro2 = new InformacionFiltro(TipoFiltro.EXACTO, Inasistencia.ENTIDAD_INASISTENCIA_TIPO,
				tipoInasistencia);
		InformacionFiltro filtro3 = new InformacionFiltro(TipoFiltro.EXACTO,
				NombramientoPersona.ENTIDAD_NOMBRAMIENTO_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		filtros.add(filtro1);
		filtros.add(filtro2);
		filtros.add(filtro3);
		List<Inasistencia> inasistencias = manejadorInasistencia.consultar(filtros, null, null);
		for (Inasistencia inasistencia : inasistencias) {
			inasistencia.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
			manejadorInasistencia.actualizar(inasistencia);
		}
	}
	// protected region metodos adicionales end

	@Override
	public List<ResultadoCasoDTO> consultarResultadosAudienciasCaso(Long idCaso) {
		List<String> tiposResultados = Arrays.asList(UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_PARCIAL,
				UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_TOTAL, UtilDominios.RESULTADO_AUDIENCIA_INASISTENCIA,
				UtilDominios.RESULTADO_AUDIENCIA_IMPOSIBILIDAD_DE_ACUERDO, UtilDominios.RESULTADO_AUDIENCIA_FRACASO, UtilDominios.RESULTADO_AUDIENCIA_ACUERDO);
		return manejadorResultadoAudiencia.consultarResultadosAudienciasCaso(idCaso, tiposResultados);
	}

	/**
	 * Metodo que permite actualizar el estado del resultado de audiencia.
	 * 
	 * @param estadoResultado:
	 *            estado del resultado.
	 * @param idResultadoAudiencia:
	 *            Identificador del resultado de audiencia.
	 * @param idUsuarioModificacion:
	 *            Usuario de sesion que realiza la modificacion.
	 */
	@Override
	public void actualizarEstadoResultado(String estadoResultado, Long idResultadoAudiencia,
			String idUsuarioModificacion) {
		ResultadoAudiencia resultadoAudiencia = manejadorResultadoAudiencia.buscar(idResultadoAudiencia);
		if (resultadoAudiencia != null) {
			resultadoAudiencia.setEstado(estadoResultado);
			resultadoAudiencia.setIdUsuarioModificacion(idUsuarioModificacion);
			resultadoAudiencia.setFechaUltimaModificacion(new Date());
			manejadorResultadoAudiencia.actualizar(resultadoAudiencia);
		}
	}
	/**
	 * Método encargado de eliminar un resultadoAudiencia dado su id
	 * 
	 */
	@Override
	public void eliminarResultadoAudiencia (Long idResultadoAudiencia){
		manejadorResultadoAudiencia.eliminarResultadoAudiencia(idResultadoAudiencia);
	}

}
