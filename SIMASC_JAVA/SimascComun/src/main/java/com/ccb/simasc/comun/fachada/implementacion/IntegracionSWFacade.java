package com.ccb.simasc.comun.fachada.implementacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.interfaces.IIntegracionSWFacade;
import com.ccb.simasc.comun.webservice.integracion.interfaces.IClienteSWMinisterio;
import com.ccb.simasc.comun.webservice.integracion.interfaces.IClienteSWPup;
import com.ccb.simasc.comun.webservice.integracion.interfaces.IClienteSWSirep;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorEvento;
import com.ccb.simasc.integracion.manejadores.ManejadorFechaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorHonorariosFijados;
import com.ccb.simasc.integracion.manejadores.ManejadorPagoCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorZonaGeografica;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.transversal.dto.formularios.DatosCasoMinisterioDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioDatosClienteDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioGenerarLiquidacionDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioGenerarPagoPup;
import com.ccb.simasc.transversal.dto.formularios.LiquidacionMinisterioDTO;
import com.ccb.simasc.transversal.dto.formularios.ProcesoReliquidacionDTO;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.DetallePagoCaso;
import com.ccb.simasc.transversal.entidades.Evento;
import com.ccb.simasc.transversal.entidades.HonorariosFijados;
import com.ccb.simasc.transversal.entidades.PagoCaso;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.simasc.clientes.ministerio.Mensaje;

/**
 * @author fguzman
 *
 */
@Stateless
@LocalBean
public class IntegracionSWFacade implements Serializable, IIntegracionSWFacade {

	private static final long serialVersionUID = 1L;

	@EJB
	IClienteSWMinisterio clienteSWMinisterio;

	/**
	 * Comunicacion con el cliente de sirep
	 */
	@EJB
	IClienteSWSirep clienteSWSirep;

	@EJB
	IClienteSWPup clienteSWPup;

	@EJB
	ManejadorAudiencia manejadorAudiencia;

	@EJB
	ManejadorCaso manejadorCaso;

	@EJB
	ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	ManejadorPersona manejadorPersona;

	@EJB
	ManejadorHonorariosFijados manejadorHonorariosFijados;

	@EJB
	ManejadorFechaCaso manejadorFechaCaso;

	@EJB
	ManejadorParametrosGenerales manejadorParametrosGenerales;

	@EJB
	ManejadorPagoCaso manejadorPagoCaso;

	@EJB
	ManejadorEvento manejadorEvento;

	@EJB
	ManejadorZonaGeografica manejadorZonaGeografica;

	// contexto de sesion de usuario.
	@Inject
	private ApplicationRequestContext appContext;

	@Override
	public String agregarCasoArbitrajeMinisterio(Long idCaso) {
		DatosCasoMinisterioDTO datosCaso = new DatosCasoMinisterioDTO();
		String resultado;
		// Consultar el caso
		Caso caso = manejadorCaso.buscar(idCaso);
		if (caso.getMotivoCierre() == null) {
			String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR232.toString());
			throw new SimascException(mensaje);
		}
		Date fechaCierreCaso = manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_CIERRE);
		if (fechaCierreCaso == null) {
			String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR234.toString());
			throw new SimascException(mensaje);
		}
		List<String> lstRoles = new ArrayList<>();
		lstRoles.add(UtilDominios.ROL_PERSONA_ARBITRO);
		List<Persona> arbitrosMinisterio = manejadorRolPersonaCaso.consultarArbitrosMinisterio(idCaso, lstRoles);
		List<Persona> arbitrosExternos = manejadorRolPersonaCaso.consultarArbitrosExternos(idCaso, lstRoles);
		if (arbitrosMinisterio.isEmpty() && arbitrosExternos.isEmpty()) {
			String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR229.toString());
			throw new SimascException(mensaje);
		}
		// consultar los datos del secretario
		RolPersonaCaso secretario = manejadorRolPersonaCaso.consultarSecretarioDelCaso(idCaso);
		if (secretario == null) {
			String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR228.toString());
			throw new SimascException(mensaje);
		}
		// Consultar las partes del caso
		List<Persona> partesCaso = manejadorPersona.getConsultarPartesCaso(idCaso);
		if (partesCaso.isEmpty()) {
			String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR230.toString());
			throw new SimascException(mensaje);
		}
		// Consultar los honorarios fijados
		HonorariosFijados honorariosFijados = manejadorHonorariosFijados.consultarPorIdCaso(idCaso);

		datosCaso.setCaso(caso);
		datosCaso.setFechaCierreCaso(fechaCierreCaso);
		datosCaso.setArbitrosExternos(arbitrosExternos);
		datosCaso.setArbitrosMinisterio(arbitrosMinisterio);
		datosCaso.setSecretario(secretario);
		datosCaso.setPartesCaso(partesCaso);
		datosCaso.setHonorariosFijados(honorariosFijados);

		resultado = clienteSWMinisterio.agregarCasoArbitrajeMinisterio(datosCaso);
		return resultado;
	}

	@Override
	public String agregarCasoAmigableComposicionMinisterio(Long idCaso) {
		String resultado;
		DatosCasoMinisterioDTO datosCaso = new DatosCasoMinisterioDTO();
		List<Persona> amigableComponedor = new ArrayList<>();
		Caso caso = manejadorCaso.buscar(idCaso);
		if (caso.getMotivoCierre() == null) {
			String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR232.toString());
			throw new SimascException(mensaje);
		}
		Date fechaCierreCaso = manejadorFechaCaso.consultaFechaPorCasoTipo(idCaso,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_CIERRE);
		if (fechaCierreCaso == null) {
			String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR234.toString());
			throw new SimascException(mensaje);
		}
		List<String> lstRoles = new ArrayList<>();
		lstRoles.add(UtilDominios.ROL_PERSONA_ARBITRO);
		List<Persona> arbitrosMinisterio = manejadorRolPersonaCaso.consultarArbitrosMinisterio(idCaso, lstRoles);
		List<Persona> arbitrosExternos = manejadorRolPersonaCaso.consultarArbitrosExternos(idCaso, lstRoles);
		amigableComponedor.addAll(arbitrosMinisterio);
		amigableComponedor.addAll(arbitrosExternos);
		if (arbitrosMinisterio.isEmpty() && arbitrosExternos.isEmpty()) {
			String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR229.toString());
			throw new SimascException(mensaje);
		}
		// Consultar las partes del caso
		List<Persona> partesCaso = manejadorPersona.getConsultarPartesCaso(idCaso);
		if (partesCaso.isEmpty()) {
			String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR230.toString());
			throw new SimascException(mensaje);
		}
		// consultar la fecha de cierre del caso

		datosCaso.setFechaCierreCaso(fechaCierreCaso);
		datosCaso.setCaso(caso);
		datosCaso.setAmigableComponedor(amigableComponedor);
		datosCaso.setPartesCaso(partesCaso);
		resultado = clienteSWMinisterio.agregarCasoAmigableComposicionMinisterio(datosCaso);

		return resultado;
	}

	/**
	 * TRANS-041 Transversal-Generacion-de-orden Implementacion del metodo crear
	 * datos basicos cliente Sirep
	 */
	@Override
	public Map<String, String> crearDatosBasicosClienteSirep(FormularioDatosClienteDTO datosBasicos) {
		return clienteSWSirep.crearDatosBasicosClienteSirep(datosBasicos);
	}

	/**
	 * TRANS-041 Transversal-Generacion-de-orden Consulta los datos basicos del
	 * cliente en el sistema externo sirep
	 */
	@Override
	public FormularioDatosClienteDTO consultarDatosBasicosClienteSirep(String tipoIdentificacion,
			String numeroIdentificacion) {
		return clienteSWSirep.consultarDatosBasicosClienteSirep(tipoIdentificacion, numeroIdentificacion);

	}

	/**
	 * CON-F-095 Método que realiza el llamado al proceso de reliquidación de pup
	 * realiza la liquidacion de pup
	 */
	@Override
	public FormularioGenerarLiquidacionDTO realizarLiquidacion(ProcesoReliquidacionDTO reliquidacion) {
		return clienteSWPup.generarLiquidacionPup(reliquidacion);
	}

	/**
	 * TRANS-041 Transversal-Generacion-de-orden
	 * 
	 */
	@Override
	public String generarOrdenDePagoPup(FormularioGenerarPagoPup datosLiquidacion) {
		return clienteSWPup.generarOrdenPagoPup(datosLiquidacion.getDatosLiquidacion(),
				datosLiquidacion.getParametros(), datosLiquidacion.getNumeroCliente());
	}

	@Override
	public Object[] agregarCasoConciliacionMinisterio(Long idCaso) {
		Object[] resultados = new Object[3];
		resultados[2] = false;
		Mensaje mensaje;
		DatosCasoMinisterioDTO datosCaso = new DatosCasoMinisterioDTO();
		Caso caso = manejadorCaso.buscar(idCaso);
		datosCaso.setCaso(caso);
		datosCaso.setCrearEventoMinisterio(false);
		List<String> estados = new ArrayList<>();
		estados.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		List<RolPersonaCaso> conciliadoresRpc = manejadorRolPersonaCaso
				.consultaConciliadoresCasoEstadoNombramiento(idCaso, estados, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		if (!conciliadoresRpc.isEmpty()) {
			Persona persona = manejadorPersona.buscar(conciliadoresRpc.get(0).getRolPersonaCasoPK().getIdPersona());
			if (this.documentoValido(persona)) {
				datosCaso.setConciliador(persona);
			}
		}
		// setea convocantes y convocados
		this.consultaPartesConciliacion(datosCaso, idCaso);
		this.validacionPartesConcilicion(datosCaso);

		datosCaso.setParametros(this.obtnerParamentros(UtilConstantes.TIPO_PARAMETRO_USUARIO_MIN));
		datosCaso.setLiquidacion(this.obtenerLiquidacionConciliacion(caso));

		// datosCaso.setPartesCaso(partesCaso);
		mensaje = clienteSWMinisterio.agregarCasoConciliacionMinisterio(datosCaso);
		resultados[0] = procesarResultadoConciliacion(mensaje, datosCaso, idCaso);
		resultados[1] = mensaje;
		resultados[2] = datosCaso.isCrearEventoMinisterio();
		return resultados;
	}

	private String procesarResultadoConciliacion(Mensaje mensaje, DatosCasoMinisterioDTO datosCaso, Long idCaso) {
		StringBuilder respuestaMinisterio = new StringBuilder(mensaje.getSicAccion().getValue());

		if (mensaje.getSicIdOrigen() != null && mensaje.getSicIdResultado() != null
				&& mensaje.getSicIdResultado().getValue() != null) {
			respuestaMinisterio.append(" con código: ").append(mensaje.getSicIdResultado().getValue());
			datosCaso.setCrearEventoMinisterio(true);
			this.crearEventoEnvioMinisterio(idCaso, mensaje.getSicIdResultado().getValue());
		}

		return respuestaMinisterio.toString();
	}

	private void consultaPartesConciliacion(DatosCasoMinisterioDTO datosCaso, Long idCaso) {
		// Consultar las partes del caso
		List<RolPersonaCaso> partesCaso = manejadorRolPersonaCaso.consultarPartesCaso(idCaso);
		List<RolPersonaCaso> convocantes = new ArrayList<RolPersonaCaso>();
		List<RolPersonaCaso> convocados = new ArrayList<RolPersonaCaso>();
		for (RolPersonaCaso rolPersonaFor : partesCaso) {
			if (this.documentoValido(rolPersonaFor.getPersona())) {
				this.validarSexo(rolPersonaFor);
				if (UtilDominios.ROL_PERSONA_CONVOCANTE.equals(rolPersonaFor.getRol().getNombre())) {
					convocantes.add(rolPersonaFor);

				} else if (UtilDominios.ROL_PERSONA_CONVOCADO.equals(rolPersonaFor.getRol().getNombre())) {
					convocados.add(rolPersonaFor);

				}
			}
		}

		if (!convocantes.isEmpty()) {
			datosCaso.setConvocantes(convocantes);
		}

		if (!convocados.isEmpty()) {
			datosCaso.setConvocados(convocados);
		}

	}

	private void validacionPartesConcilicion(DatosCasoMinisterioDTO datosCaso) {
		if (datosCaso.getConvocantes() == null || datosCaso.getConvocantes().isEmpty()) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR330.toString()));
		}
		if ((datosCaso.getConvocantes() == null || datosCaso.getConvocantes().isEmpty())
				&& !UtilDominios.RESULTADO_CASO_CONCILIACION_INASISTENCIA.equals(datosCaso.getCaso().getResultado())) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR330.toString()));
		}
	}

	private boolean documentoValido(Persona persona) {
		return persona.getNumeroDocumento() != null && persona.getTipoDocumento() != null
				&& !persona.getNumeroDocumento().isEmpty() && !persona.getTipoDocumento().isEmpty();

	}

	private void validarSexo(RolPersonaCaso rolPersonaCaso) {
		if (UtilDominios.TIPO_PERSONA_NATURAL.equals(rolPersonaCaso.getPersona().getTipoPersona())
				&& !(UtilDominios.SEXOS_FEMENINO.equals(rolPersonaCaso.getPersona().getSexo())
						|| UtilDominios.SEXOS_MASCULINO.equals(rolPersonaCaso.getPersona().getSexo()))) {
			List<String> args = new ArrayList<>();
			args.add(rolPersonaCaso.getPersona().getNombreCompleto() != null
					? rolPersonaCaso.getPersona().getNombreCompleto()
					: "");

			throw new SimascException(String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR331.toString()), args.toArray()));
		}
	}

	/**
	 * obitne los parametros generales del servicio a usar
	 * 
	 * @param tipo
	 * @return
	 */
	private List<ParametrosGenerales> obtnerParamentros(String tipo) {
		List<ParametrosGenerales> params = null;
		int cont = 0;
		params = manejadorParametrosGenerales.obtenerParametrosPorTipo(tipo);

		for (int i = 0; i < params.size(); i++) {
			if (params.get(i).getCodigo().equals(UtilDominios.PARAMENTROS_CODIGO_USUARIO)) {
				if (params.get(i).getValorTexto() != null)
					cont++;
				else
					throw new SimascException(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR335.toString()));
			}
			if (params.get(i).getCodigo().equals(UtilDominios.PARAMENTROS_CODIGO_CLAVE)) {
				if (params.get(i).getValorTexto() != null)
					cont++;
				else
					throw new SimascException(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR336.toString()));
			}
		}
		if (cont != 2)
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR337.toString()));

		return params;
	}

	/**
	 * obtiene los datos de liquidacion del caso
	 * 
	 * @param idCaso
	 * @return
	 */
	private LiquidacionMinisterioDTO obtenerLiquidacionConciliacion(Caso caso) {
		LiquidacionMinisterioDTO liquidacion = new LiquidacionMinisterioDTO();
		Long valorCentro = 0L;
		Long valorConciliador = 0L;
		Long valorAdicionales = 0L;
		Long valorTotal = 0L;

		ParametrosGenerales ParNumeroAudiencias = manejadorParametrosGenerales
				.buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_NUMERO_AUDIENCIAS_PARA_RECOBRO);
		ParametrosGenerales ParPorcentajeAudiencia = manejadorParametrosGenerales
				.buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_PORCENTAJE_COBRO_ADICIONAL_AUDIENCIA);
		int maximoNumAudiencias = 0;
		Long porcentaAudiencia = 0L;

		if (ParPorcentajeAudiencia == null || ParNumeroAudiencias == null
				|| ParPorcentajeAudiencia.getValorNumerico() == null) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR339.toString()));
		} else {
			maximoNumAudiencias = ParNumeroAudiencias.getValorNumerico().intValue();
			porcentaAudiencia = ParPorcentajeAudiencia.getValorNumerico();
		}

		List<Audiencia> audiencias = manejadorAudiencia.consultarAudienciasCasoPorTipoYEstado(caso.getIdCaso(), null,
				UtilDominios.ESTADO_AUDIENCIA_REALIZADA);
		liquidacion.setNumeroEncuentroAdicional(
				audiencias.size() - maximoNumAudiencias > 0 ? audiencias.size() - maximoNumAudiencias : 0);
		InformacionFiltro filtroActivo = new InformacionFiltro(TipoFiltro.EXACTO,
				PagoCaso.ENTIDAD_PAGO_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		InformacionFiltro filtroCaso = new InformacionFiltro(TipoFiltro.EXACTO, PagoCaso.ENTIDAD_PAGO_CASO_ID_CASO,
				caso.getIdCaso());

		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(filtroActivo);
		filtros.add(filtroCaso);

		List<PagoCaso> pagosCaso = manejadorPagoCaso.consultar(filtros, null, null);

		for (PagoCaso pagoCasoFor : pagosCaso) {
			if (pagoCasoFor.getValor() != null) {
				valorTotal += pagoCasoFor.getValor();
			}
			if (pagoCasoFor.getDetallePagoCasoList() != null) {
				for (DetallePagoCaso detalleFor : pagoCasoFor.getDetallePagoCasoList()) {
					switch (detalleFor.getServicioCaja()) {
					case UtilDominios.TIPO_SERVICIO_CAJA_GASTOS_ADMINISTRATIVOS:
						valorConciliador += detalleFor.getValor();
						break;
					case UtilDominios.TIPO_SERVICIO_CAJA_HONORARIOS_DEL_CONCILIADOR_CENTRO_UNO:
					case UtilDominios.TIPO_SERVICIO_CAJA_HONORARIOS_DEL_CONCILIADOR_CENTRO_DOS:
					case UtilDominios.TIPO_SERVICIO_CAJA_HONORARIOS_DEL_CONCILIADOR_CENTRO_TRES:
						valorCentro += detalleFor.getValor();
						break;
					default:
						break;
					}
				}
			}
			valorAdicionales = valorTotal * porcentaAudiencia / 100;
		}

		liquidacion.setValorCorrespondienteCentro(valorCentro);
		liquidacion.setValorCorrespondienteConciliador(valorConciliador);
		liquidacion.setValorEncuentrosAdicionales(valorAdicionales);
		liquidacion.setValorServicioTotalConciliacion(valorTotal);

		return liquidacion;
	}

	/**
	 * Crea el evento del ministerio
	 * 
	 * @param idCaso
	 */
	private void crearEventoEnvioMinisterio(Long idCaso, String observaciones) {
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		}
		Evento evento = new Evento();
		evento.setIdCaso(idCaso);
		evento.setFechaEvento(new Date());
		evento.setFechaUltimaModificacion(new Date());
		evento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		evento.setIdUsuarioModificacion(usuarioModificacion);
		evento.setTipoEvento(UtilDominios.TIPO_EVENTO_ENVIO_MINISTERIO);
		evento.setObservaciones(observaciones);
		manejadorEvento.crear(evento);
	}

	@Override
	public void actualizarDatosContactoClienteSirep(FormularioDatosClienteDTO datosBasicos) {
		clienteSWSirep.ActualizarDatosBasicosClienteSirep(datosBasicos);
	}

}
