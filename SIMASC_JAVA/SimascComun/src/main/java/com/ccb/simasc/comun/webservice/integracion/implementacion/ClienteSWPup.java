package com.ccb.simasc.comun.webservice.integracion.implementacion;

import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ccb.simasc.comun.webservice.integracion.interfaces.IClienteSWPup;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorPagoCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorSolicitudServicio;
import com.ccb.simasc.transversal.dto.DetalleLiquidacionDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioGenerarLiquidacionDTO;
import com.ccb.simasc.transversal.dto.formularios.ProcesoReliquidacionDTO;
import com.ccb.simasc.transversal.dto.pup.generado.Detalle;
import com.ccb.simasc.transversal.dto.pup.generado.DetalleLiquidacionDto;
import com.ccb.simasc.transversal.dto.pup.generado.RealizarLiquidacionResponse;
import com.ccb.simasc.transversal.dto.pup.generado.RealizarLiquidacionResult;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.PagoCaso;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.SolicitudServicio;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.simasc.clientes.sirep.pup.IServicioPagos;
import com.simasc.clientes.sirep.pup.LiquidacionInDTO;
import com.simasc.clientes.sirep.pup.LiquidacionOutDTO;
import com.simasc.clientes.sirep.pup.ObjectFactory;
import com.simasc.clientes.sirep.pup.OrdenPagoInDTO;
import com.simasc.clientes.sirep.pup.OrdenPagoOutDTO;
import com.simasc.clientes.sirep.pup.ServicioPagos;

/**
 * @author fguzman
 *
 */
@Stateless
@LocalBean
public class ClienteSWPup implements IClienteSWPup {
	private static final Logger logger = LogManager.getLogger(ClienteSWPup.class.getName());
	private ServicioPagos pupWebServicePort;
	private IServicioPagos clienteService;

	@EJB
	private ManejadorSolicitudServicio manejadorSolicitudServicio;

	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private ManejadorPagoCaso manejadorPagoCaso;

	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;

	
	/**
	 * Se instancia el endpoint y el cliente del servicio a consumir
	 */
	@PostConstruct
	public void init() {
		String urlPup = consultaUrl();
		try {
			pupWebServicePort = new ServicioPagos(new URL(urlPup));
			clienteService = pupWebServicePort.getPagosBaisc();
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public FormularioGenerarLiquidacionDTO generarLiquidacionPup(ProcesoReliquidacionDTO reliquidacion) {
		int isLiquidacion = 1;
		String cuantiaIndeterminada;
		reliquidacion.setCuantia(reliquidacion.getCuantia());
		String cuantia = reliquidacion.getCuantia();
		String numeroRecibo = UtilConstantes.CADENA_VACIA;
		String dataAdicional = UtilConstantes.CADENA_VACIA;
		ParametrosGenerales servicicioId = manejadorParametrosGenerales
				.obtenerParametrosPorCodigoYTipo(UtilConstantes.PUP_SERVICIO_ID, UtilConstantes.PUP_TIPO_PARAMETRO);
		ParametrosGenerales salarioMinimo = manejadorParametrosGenerales.buscar(UtilConstantes.SMLMV);
		if (reliquidacion.getIsSolicitud()) {
			if (reliquidacion.getIdSolicitud() != null) {
				SolicitudServicio solicitud = manejadorSolicitudServicio.buscar(reliquidacion.getIdSolicitud());
				cuantiaIndeterminada = obtenerCuantiaTipoLiquidacion(solicitud.getTipoCuantia(),
						solicitud.getIdServicio(), solicitud.getTipoTramite(), solicitud.getCuantia(), solicitud.isArbitrajeConsumo());
				dataAdicional = obtenerDataAdicional(solicitud.getSede().getIdCentro(), solicitud.getIdServicio(), solicitud.isArbitrajeConsumo());
				if (UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA
						.equals(solicitud.getServicioMateria().getServicio().getTipo()) 
						|| UtilDominios.TIPO_SERVICIO_INTERNACIONAL
						.equals(solicitud.getServicioMateria().getServicio().getTipo())) {
					if (solicitud.getIdServicio() == UtilConstantes.ID_SERVICIO_RECUPERACION_EMPRESARIAL) {
						cuantia = reliquidacion.getCuantia();
					} else {
						cuantia = obtenerValorCuantia(solicitud.getCuantia(), solicitud.getTipoCuantia(),
								salarioMinimo.getValorNumerico());
					}
				}
				else if (cuantiaIndeterminada.equals("1")) {
					cuantia = solicitud.getCuantia();
				}
			} else {
				cuantiaIndeterminada = reliquidacion.getCuantia() == null ? "0" : "1";
				dataAdicional = obtenerDataAdicional(reliquidacion.getIdCentro(), reliquidacion.getIdServicio(), false);
			}
			isLiquidacion = 0;
		} else {
			Caso caso = manejadorCaso.buscar(reliquidacion.getIdSolicitud());
			
			if(reliquidacion.getIsReliquidacion()) {
				cuantiaIndeterminada =  "1";
			}else {
				cuantiaIndeterminada = obtenerCuantiaTipoLiquidacion(caso.getTipoCuantia(), caso.getIdServicio(),null, caso.getPretensiones(), caso.isArbitrajeConsumo());
			}
			
			if (UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA.equals(caso.getServicioMateria().getServicio().getTipo())
			|| UtilDominios.TIPO_SERVICIO_INTERNACIONAL.equals(caso.getServicioMateria().getServicio().getTipo()))
				cuantia = obtenerValorCuantia(caso.getValorPretensiones(), caso.getTipoCuantia(),
						salarioMinimo.getValorNumerico());
			List<PagoCaso> pago = manejadorPagoCaso.obtenerUltimoPagoCaso(reliquidacion.getIdSolicitud());
			if (reliquidacion.getPorcentajeAdicional() != null)
				dataAdicional = String.valueOf(reliquidacion.getPorcentajeAdicional());
			else
				dataAdicional = obtenerDataAdicional(caso.getSede().getIdCentro(), caso.getIdServicio(), caso.isArbitrajeConsumo());
			if (!pago.isEmpty())
				numeroRecibo = pago.get(0).getNumeroRecibo();
		}

		LiquidacionOutDTO resultado = consumoServicioLiquidacion(servicicioId, cuantia, numeroRecibo,
				cuantiaIndeterminada, isLiquidacion, dataAdicional,
				reliquidacion.getIdSolicitud() != null ? reliquidacion.getIdSolicitud().intValue() : null);

		realizarValidacionesReliquidar(resultado);
		return transformarResultadoLiquidacionPup(resultado, obtenerDetalle(resultado), reliquidacion.getIdSolicitud(), reliquidacion.getIsReliquidacion());
	}

	/**
	 * Metodo que realiza las validaciones necesarias para identificar si la
	 * resupuesta obtenida es valida si se presenta algun error lanza una excepcion
	 * de tipo {@link SimascException}
	 * 
	 * @param resultado
	 */
	private void realizarValidacionesReliquidar(LiquidacionOutDTO resultado) {
		if (UtilConstantes.CODIGO_SIREP_EXITOSO.equals(resultado.getErrorId().getValue())
				&& (resultado.getDetalle() == null || resultado.getDetalle().getValue() == null
						|| resultado.getDetalle().getValue().startsWith("Error")
						|| resultado.getDetalle().getValue().isEmpty()
						|| resultado.getDetalle().getValue().indexOf("<a:Detalle/>") >= 0))
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO383.toString()));

		if (UtilConstantes.CODIGO_SIREP_ERROR_UNO.equals(resultado.getErrorId().getValue())
				|| UtilConstantes.CODIGO_SIREP_ERROR_DOS.equals(resultado.getErrorId().getValue())
				|| UtilConstantes.CODIGO_SIREP_ERROR_TRES.equals(resultado.getErrorId().getValue()))
			throw new SimascException(resultado.getMensaje().getValue());
		else if (!UtilConstantes.CODIGO_SIREP_EXITOSO.equals(resultado.getErrorId().getValue()))
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO383.toString()));
	}

	/**
	 * Método que construye el objeto de la peticion y consume el servicio de la
	 * reliquidacion de pup
	 * 
	 * @param parametros
	 * @param vrBase
	 * @param numeroRecibo
	 * @param cuantiaIndeterminada
	 * @param isLiquidacion
	 * @param dataAdicional
	 * @return
	 */
	private LiquidacionOutDTO consumoServicioLiquidacion(ParametrosGenerales servicioId, String vrBase,
			String numeroRecibo, String cuantiaIndeterminada, int isLiquidacion, String dataAdicional,
			Integer solicitudAplicativoId) {
		ObjectFactory generico = new ObjectFactory();
		LiquidacionInDTO liquidacionInDTO = new LiquidacionInDTO();
		liquidacionInDTO.setServicioId(Integer.parseInt(servicioId.getValorTexto()));
		liquidacionInDTO.setSolicitudAplicativoid(solicitudAplicativoId);
		liquidacionInDTO.setUsuarioId(generico.createLiquidacionInDTOUsuarioId(UtilConstantes.PUP_USUARIO_ID));

		String detalle = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO380.toString(),
				vrBase != null ? vrBase : UtilConstantes.CADENA_VACIA, UtilConstantes.PUP_ID_SUCURSAL_LIQUIDA,
				cuantiaIndeterminada, UtilConstantes.PUP_ID_SN, numeroRecibo, String.valueOf(isLiquidacion),
				dataAdicional != null ? dataAdicional : UtilConstantes.CADENA_VACIA);
		liquidacionInDTO.setDetalle(generico.createLiquidacionInDTODetalle(detalle));

		LiquidacionOutDTO liquidacion = null;
		int intentos = 3;
		while (intentos > 0) {
			try {
				liquidacion = clienteService.realizarLiquidacion(liquidacionInDTO);
				break;
			} catch (Exception e) {
				if (intentos == 1) {
					throw new SimascException(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO383.toString()));
				} else {
					intentos -= 1;
				}
			}
		}
		return liquidacion;
	}

	/**
	 * Método que obtiene el codigo correspondiente al centro
	 * 
	 * @param idCentro
	 * @return
	 */
	private String obtenerDataAdicional(Long idCentro, Long idServicio, boolean arbitrajeConsumo) {
		
		String dataAdicional = UtilConstantes.PUP_DATA_ADICIONAL;
		String valorPorServicio="";
		
		if ((UtilConstantes.ID_SERVICIO_CONCILIACION_TRAMITE_ORDINARIO.equals(idServicio))
				|| (UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO.equals(idServicio))) {
			
			valorPorServicio = String.valueOf(UtilConstantes.ID_SERVICIO_CONCILIACION_TRAMITE_ORDINARIO)
					.concat(UtilConstantes.CARACTER_GUION_ALTO)
					.concat(String.valueOf(idCentro));
						
		} else if(UtilConstantes.ID_SERVICIO_ARBITRAJE_ABREVIADO.equals(idServicio)){
			
			valorPorServicio = String.valueOf(idServicio)
					.concat(UtilConstantes.CARACTER_GUION_ALTO)
					.concat(Boolean.compare(arbitrajeConsumo, false)+"");
		} else {
			valorPorServicio = String.valueOf(idServicio);
		}
		dataAdicional = dataAdicional.concat(valorPorServicio);
		return dataAdicional;
	}

	private String obtenerCuantiaTipoLiquidacion(String tipoCuantia, Long idServicio, String tipoTramite, String cuantia, boolean consumo) {
		
		String cuantiaCTR;
		
		if (UtilConstantes.ID_SERVICIO_PERITAJE.equals(idServicio))
			cuantiaCTR = UtilConstantes.CADENA_VACIA;
		
		else if ((UtilConstantes.ID_SERVICIO_CONCILIACION_TRAMITE_ORDINARIO.equals(idServicio))
				|| (UtilConstantes.ID_SERVICIO_CONCILIACION_MEDIACION.equals(idServicio))) {
			
			cuantiaCTR = UtilDominios.TIPO_CUANTIA_CONCILIACION_INDETERMINADO.equals(tipoCuantia) ? "0" : "1";
			
		}else if(UtilConstantes.ID_SERVICIO_RECUPERACION_EMPRESARIAL.equals(idServicio) && tipoTramite != null) {
				cuantiaCTR = tipoTramite.substring(0, 3);
			
		}else if(UtilConstantes.ID_SERVICIO_ARBITRAJE_ABREVIADO.equals(idServicio)) {
			
			cuantiaCTR =  calcularCuantiaArbitrajeAbreviado(tipoCuantia, cuantia, consumo);
					
		} else {
			cuantiaCTR = tipoCuantia;
		}
		return cuantiaCTR;
	}
	
	public String calcularCuantiaArbitrajeAbreviado(String tipoCuantia, String cuantia, boolean consumo) {
		
		String cuantiaCTR = UtilConstantes.CADENA_VACIA;
		ParametrosGenerales salarioMinimo = manejadorParametrosGenerales.buscar(UtilConstantes.SMLMV);
		ParametrosGenerales uvt = manejadorParametrosGenerales.buscar(UtilConstantes.UVT);
		ParametrosGenerales limitSalarioMinimo = manejadorParametrosGenerales.buscar(UtilConstantes.LIMITSMLMV);
		ParametrosGenerales limitUVT = manejadorParametrosGenerales.buscar(UtilConstantes.LIMITUVT);
		
		if(UtilDominios.TIPO_CUANTIA_ARBITRAJE_ABREVIADO_INDETERMINADO.equals(tipoCuantia)) {
			cuantiaCTR = UtilDominios.TIPO_CUANTIA_INDETERMINADO;
		}else {
			Long cuantiaSMMLV = salarioMinimo.getValorNumerico() * limitSalarioMinimo.getValorNumerico();
			Double cuantiaUVT = uvt.getValorNumerico() * Double.parseDouble(limitUVT.getValorTexto().replace(",", "."));
			if(consumo) {
				
				if(Long.parseLong(cuantia) > cuantiaSMMLV ) {
					cuantiaCTR =  UtilDominios.TIPO_CUANTIA_MAYOR;
				}else if(Double.parseDouble(cuantia) > cuantiaUVT && Long.parseLong(cuantia) <= cuantiaSMMLV ) {
					cuantiaCTR =  UtilDominios.TIPO_CUANTIA_MENOR;
				}
				
			}else {
				
				if(Long.parseLong(cuantia) > cuantiaSMMLV ) {
					cuantiaCTR =  UtilDominios.TIPO_CUANTIA_MAYOR;
				}else {
					cuantiaCTR =  UtilDominios.TIPO_CUANTIA_MENOR;
				}
			}
		}
		return cuantiaCTR;
	}

	public String obtenerValorCuantia(String pretensiones, String tipoCuantia, Long salarioMinimo) {
		BigDecimal cuantia = BigDecimal.ZERO;
		if (!(UtilDominios.TIPO_CUANTIA_INDETERMINADO.equals(tipoCuantia) || UtilDominios.TIPO_CUANTIA_ARBITRAJE_ABREVIADO_INDETERMINADO.equals(tipoCuantia))) {
			try {
				cuantia = new BigDecimal(Long.parseLong(pretensiones));
			} catch (Exception e) {
				cuantia = UtilConstantes.SEPARADOR_TIPO_CUANTIA.multiply(new BigDecimal(salarioMinimo));
			}
		}
		return cuantia.compareTo(BigDecimal.ZERO) == 0 ? UtilConstantes.CADENA_VACIA : cuantia.toPlainString();
	}

	private Object[] obtenerDetalle(LiquidacionOutDTO resultado) {
		final String inicioDetalle = "<Detalle>";
		final String finDetalle = "</Detalle>";
		final String inicioActos = "<ActosVigencia>";
		final String finActos = "</ActosVigencia>";
		Object[] resultados = new Object[2];
		Reader reader;
		RealizarLiquidacionResponse result = new RealizarLiquidacionResponse();
		try {
			String xml = resultado.getDetalle().getValue()
					.replace("<![CDATA[<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>", "")
					.replace("<![CDATA[", "").replace("]]]]>>", "").replace("]]>", "");
			int inicioBody = xml.indexOf("<s:Body>");
			int finBody = xml.indexOf("</s:Body>");
			if (inicioBody >= 0 && finBody > 0)
				xml = xml.substring(inicioBody + 8, finBody);

			if (xml.indexOf(":Detalle") >= 0) {
				reader = new StringReader(xml);
				xml = xml.replace("a:", "");
				xml = xml.substring(xml.indexOf(inicioDetalle), xml.indexOf(finDetalle) + finDetalle.length());
				result = convertirXml(RealizarLiquidacionResponse.class, reader);
			} else if(!xml.startsWith("<")){
				
				result.setRealizarLiquidacionResult(new RealizarLiquidacionResult());
				result.getRealizarLiquidacionResult().setMensaje(xml);
				
			}else{
				reader = new StringReader(
						"<a:Detalle xmlns:a=\"http://softwaresci.com/ccb/pupx/dto\" xmlns=\"http://softwaresci.com/ceet/contabilizador/servicios\">"
								.concat(xml).concat("</a:Detalle>"));
				xml = inicioDetalle.concat(xml).concat(finDetalle);
				Detalle detalle = convertirXml(Detalle.class, reader);
				result.setRealizarLiquidacionResult(new RealizarLiquidacionResult());
				result.getRealizarLiquidacionResult().setDetalle(detalle);
			}
			
			result.getRealizarLiquidacionResult().setMensaje(xml);

			inicioBody = xml.indexOf(inicioActos);
			finBody = xml.indexOf(finActos);
			if (inicioBody >= 0 && finBody > 0) {
				String cadenaBorrar = xml.substring(inicioBody, finBody + finActos.length());
				xml = xml.replace(cadenaBorrar, "");
			}
			resultados[0] = result;
			resultados[1] = xml;
		} catch (Exception e) {
			logger.error("Error: ", e);
		}

		return resultados;
	}

	/**
	 * Método que realiza la trasformacion de la cadena en formato xml a un arbol de
	 * objetos de java
	 * 
	 * @param clase
	 * @param reader
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private <T> T convertirXml(Class<T> clase, Reader reader) throws Exception {
		JAXBContext jc;
		Unmarshaller unmarshaller;
		jc = JAXBContext.newInstance(clase);
		unmarshaller = jc.createUnmarshaller();
		return (T) unmarshaller.unmarshal(reader);
	}

	/**
	 * 
	 * @param parametros
	 * @param codigo
	 * @return
	 */
	private ParametrosGenerales obtenerParametro(List<ParametrosGenerales> parametros, String codigo) {
		ParametrosGenerales parametroGeneral = null;
		for (ParametrosGenerales parametrosGenerales : parametros)
			if (parametrosGenerales.getCodigo().equals(codigo))
				parametroGeneral = parametrosGenerales;
		return parametroGeneral;
	}

	@Override
	public String generarOrdenPagoPup(FormularioGenerarLiquidacionDTO datosLiquidacion,
			List<ParametrosGenerales> parametros, String numeroCliente) {
		if (parametros == null) {
			parametros = manejadorParametrosGenerales.obtenerParametrosPorTipo(UtilConstantes.PUP_TIPO_PARAMETRO);
		}
		ObjectFactory generico = new ObjectFactory();
		OrdenPagoInDTO cotizacionInDTO = new OrdenPagoInDTO();
		cotizacionInDTO.setMonedaId(generico.createOrdenPagoInDTOMonedaId(datosLiquidacion.getMoneda()));
		cotizacionInDTO.setServicioId(
				Integer.parseInt(obtenerParametro(parametros, UtilConstantes.PUP_SERVICIO_ID).getValorTexto()));
		cotizacionInDTO.setSolicitudAplicativoid(datosLiquidacion.getIdSolicitudAplicativo().intValue());
		cotizacionInDTO.setUsuarioId(generico.createOrdenPagoInDTOUsuarioId(UtilConstantes.PUP_USUARIO_ID));
		cotizacionInDTO.setDetalle(generico
				.createOrdenPagoInDTODetalle(datosLiquidacion.getCuerpo().trim().replace("\r", "").replace("\n", "")));
		cotizacionInDTO.setNumeroCliente(generico.createOrdenPagoInDTONumeroCliente(numeroCliente));
		cotizacionInDTO.setSucursalId(generico.createOrdenPagoInDTOSucursalId(UtilConstantes.PUP_ID_SUCURSAL_LIQUIDA));

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 31);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		XMLGregorianCalendar gDateFormatted;
		try {
			if (datosLiquidacion.getIdServicio() == UtilConstantes.ID_SERVICIO_CONCILIACION_MEDIACION) {
				gDateFormatted = DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(format.format(datosLiquidacion.getFechaVigencia()));
			} else {
				gDateFormatted = DatatypeFactory.newInstance().newXMLGregorianCalendar(format.format(cal.getTime()));
			}

		} catch (DatatypeConfigurationException e1) {
			logger.error("Error:",e1);
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString()));
		}

		cotizacionInDTO.setFechaVigencia(generico.createOrdenPagoInDTOFechaVigencia(gDateFormatted));
		int intentos = 3;
		OrdenPagoOutDTO orden = null;
		while (intentos > 0) {
			try {
				orden = clienteService.generarOrdenCompra(cotizacionInDTO);
				if (!"true".equals(orden.getErrorId().getValue())) {
					throw new SimascException(orden.getMensaje().getValue());
				}
				break;
			} catch (Exception e) {
				if (intentos == 1) {
					throw new SimascException(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO383.toString()));
				} else {
					if (e instanceof SimascException)
						throw new SimascException(e.getMessage());
					intentos -= 1;
				}
			}
		}

		if (datosLiquidacion.getIsSolicitud()) {
			SolicitudServicio solicitud = manejadorSolicitudServicio
					.buscar(datosLiquidacion.getIdSolicitudAplicativo());
			solicitud.setIdOrdenDePago(Long.valueOf(orden.getNumeroOrden().getValue()));
			manejadorSolicitudServicio.actualizar(solicitud);
		}

		return orden.getNumeroOrden().getValue();
	}

	/**
	 * Método que convierte la respuesta del servicio de liquidacion a un dto con
	 * los datos necesarios
	 * 
	 * @param liquidacionOutDTO
	 * @param resultados        [0] = objeto parseado de detalle de la liquidacion
	 *                          {@link RealizarLiquidacionResponse} [1] = string con
	 *                          el detalle de la liquidacion
	 * @return
	 */
	private FormularioGenerarLiquidacionDTO transformarResultadoLiquidacionPup(LiquidacionOutDTO liquidacionOutDTO,
			Object[] resultados, Long idSolicitud, boolean isReliquidacion) {
		FormularioGenerarLiquidacionDTO formularioGenerarLiquidacionDTO = new FormularioGenerarLiquidacionDTO();
		
		List<DetalleLiquidacionDTO> detalle = new ArrayList<>();
		
		if(((RealizarLiquidacionResponse) resultados[0]).getRealizarLiquidacionResult()
				.getDetalle() != null
				&& ((RealizarLiquidacionResponse) resultados[0]).getRealizarLiquidacionResult()
				.getDetalle().getArrayOfDetalleLiquidacionDto() != null) {
			
			detalle = convertirDetalles(((RealizarLiquidacionResponse) resultados[0]).getRealizarLiquidacionResult()
					.getDetalle().getArrayOfDetalleLiquidacionDto().getDetalleLiquidacionDto());
		}
		
		if(((RealizarLiquidacionResponse) resultados[0]).getRealizarLiquidacionResult()
				.getDetalle() == null && liquidacionOutDTO.getMensaje().getValue().equals("TRANSACCION EXITOSA")) {
			formularioGenerarLiquidacionDTO.setMensaje("La nueva cuantía es menor a la cuantía original y/o el valor reliquidado es menor al valor del pago inicial");
		}else {
			formularioGenerarLiquidacionDTO.setDetalle(detalle);
			formularioGenerarLiquidacionDTO.setMensaje(resultados[0] != null ? liquidacionOutDTO.getMensaje().getValue()
					: MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO383.toString()));
			formularioGenerarLiquidacionDTO.setMoneda(liquidacionOutDTO.getMonedaId().getValue());
			formularioGenerarLiquidacionDTO.setValor(liquidacionOutDTO.getValorTotal());
			formularioGenerarLiquidacionDTO.setIdSolicitudAplicativo(idSolicitud);
			formularioGenerarLiquidacionDTO.setCuerpo(resultados[1] != null ? ((String) resultados[1]) : null);						
			
			if(isReliquidacion && resultados[0] != null) {
				
				String cuantia = ((RealizarLiquidacionResponse) resultados[0]).getRealizarLiquidacionResult()
						.getDetalle().getArrayOfDetalleLiquidacionDto().getDetalleLiquidacionDto().get(0).getValorBase().toString();
				
				actualizarCasoCuantia(idSolicitud, cuantia);
			}			
		}
		
		return formularioGenerarLiquidacionDTO;
	}
	
	private void actualizarCasoCuantia(Long idSolicitud, String cuantia) {
		
		Caso caso = manejadorCaso.buscar(idSolicitud);
		caso.setTipoCuantia(UtilDominios.TIPO_CUANTIA_CONCILIACION_DETERMINADO);			
		caso.setValorPretensiones(cuantia);			
		manejadorCaso.actualizar(caso);		
	}

	private List<DetalleLiquidacionDTO> convertirDetalles(List<DetalleLiquidacionDto> detalles) {
		List<DetalleLiquidacionDTO> convertidos = new ArrayList<DetalleLiquidacionDTO>();
		for (DetalleLiquidacionDto detalleLiquidacionDto : detalles) {
			DetalleLiquidacionDTO detalle = new DetalleLiquidacionDTO();
			detalle.setServicio(detalleLiquidacionDto.getServicio());
			detalle.setServicioId(detalleLiquidacionDto.getServicioId());
			detalle.setValorTotal(detalleLiquidacionDto.getValorTotal());
			detalle.setItem(detalleLiquidacionDto.getItem());
			convertidos.add(detalle);
		}
		return convertidos;
	}

	private String consultaUrl() {
		String url = null;
		ParametrosGenerales parametroUrl = manejadorParametrosGenerales.buscar(UtilConstantes.CODIGO_PARAMETRO_URL_PUP);
		if (parametroUrl != null && parametroUrl.getValorTexto() != null) {
			url = parametroUrl.getValorTexto();
		} else {
			throw new SimascException("No existe el parametro de la url para la pasarela de pagos");
		}
		return url;

	}

}
