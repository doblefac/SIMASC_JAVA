package com.ccb.simasc.ws.pagoCasoSimasc.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.view.facelets.TagAttributeException;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.ccb.simasc.integracion.manejadores.ManejadorHomologacionSistemaExterno;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoTramiteOrdinarioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPagoCasoFacade;
import com.ccb.simasc.transversal.dto.DetallePagoCasoDTO;
import com.ccb.simasc.transversal.dto.NotificacionPagoDTO;
import com.ccb.simasc.transversal.dto.PagoCasoDTO;
import com.ccb.simasc.transversal.entidades.DetallePagoCasoPK;
import com.ccb.simasc.transversal.entidades.PagoCaso;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.ws.pagoCasoSimasc.CrearPagoCasoFault;
import com.ccb.simasc.ws.pagoCasoSimasc.CrearPagoCasoFaultType;
import com.ccb.simasc.ws.pagoCasoSimasc.CrearPagoCasoReqType;
import com.ccb.simasc.ws.pagoCasoSimasc.CrearPagoCasoRespType;
import com.ccb.simasc.ws.pagoCasoSimasc.DetallePagoCasoType;
import com.ccb.simasc.ws.pagoCasoSimasc.ObjectFactory;
import com.sun.xml.ws.developer.SchemaValidation;

@WebService(endpointInterface = "com.ccb.simasc.ws.pagoCasoSimasc.impl.PagoCasoSimascWS", 
serviceName = "PagoCasoSimascWS", targetNamespace = "http://services.simasc", 
portName = "PagoCasoSimascWSPort", wsdlLocation = "/wsdl/PagoCasoSimascWS.wsdl")
@XmlSeeAlso({ ObjectFactory.class })
@SchemaValidation
public class PagoCasoSimascWSImpl implements PagoCasoSimascWS {

	/**
	 * EJB para comunicare con la fachada de negocio de pagos
	 */
	@EJB
	IPagoCasoFacade pagoCasoFacade;
	
	@EJB
	ICasoFacade casoFacade;
	
	@EJB
	ICasoTramiteOrdinarioFacade casoTramiteOrdinarioFacade;
	
	/**
	 * EJB que permite consultar los datos de los codigos homologados
	 */
	@EJB
	ManejadorHomologacionSistemaExterno manejadorHomologacionSistemaExterno;

	@Resource
	private WebServiceContext context;
	/**
	 * Operacion expuesta de simasc para actualizar los pagos 
	 */
	@Override
	public CrearPagoCasoRespType crearPagoCaso(CrearPagoCasoReqType crearPagoCasoRequest)
			throws CrearPagoCasoFault {

		PagoCasoDTO pagoCasoDTO = new PagoCasoDTO();
		List<DetallePagoCasoDTO> detallePagoCasoList = new ArrayList<>();
		NotificacionPagoDTO pagoCaso = null;
		CrearPagoCasoRespType resp = new CrearPagoCasoRespType();
		CrearPagoCasoFaultType foulInfo = new CrearPagoCasoFaultType();
		try {
		
			pagoCasoDTO.setNombrePersona(crearPagoCasoRequest.getPagoCaso().getNombreQuienPaga());
			pagoCasoDTO
					.setTipoDeDocumento(homologarTipoDocumentoSimasc(crearPagoCasoRequest.getPagoCaso().getTipoIdentificacion()));
			pagoCasoDTO.setNumeroDeDocumento(
					crearPagoCasoRequest.getPagoCaso().getNumeroIdentificacion());
			pagoCasoDTO.setNumeroRecibo(crearPagoCasoRequest.getPagoCaso().getNumeroRecibo());
			//Se deja por defecto el usuario del sistem externo sirep
			pagoCasoDTO.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			pagoCasoDTO.setValor(crearPagoCasoRequest.getPagoCaso().getValorPago().longValue());
			pagoCasoDTO.setEstado(UtilDominios.ESTADOS_PAGO_CASO_ACT);
			pagoCasoDTO.setIdOrdenDePago(Long.valueOf(crearPagoCasoRequest.getPagoCaso().getNumeroOrdenPago()));	
			Long idSedeConvertida = this.homologacionSede(crearPagoCasoRequest.getPagoCaso().getIdSede());
			pagoCasoDTO.setIdSede(idSedeConvertida);				
			

			for (DetallePagoCasoType detallePago : crearPagoCasoRequest.getPagoCaso()
					.getDetallePago()) {
				DetallePagoCasoDTO detallePagoCasoDTO = new DetallePagoCasoDTO();
				DetallePagoCasoPK detallePagoCasoPK = new DetallePagoCasoPK();
				detallePagoCasoDTO.setServicioCaja(detallePago.getServicioCaja());
				detallePagoCasoDTO.setValor(detallePago.getValor().longValue());
				detallePagoCasoPK.setCodigoItem(Long.valueOf(detallePago.getCodigoItem()));
				detallePagoCasoDTO.setDetallePagoCasoPK(detallePagoCasoPK);
				detallePagoCasoDTO.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
				detallePagoCasoList.add(detallePagoCasoDTO);
			}
			pagoCasoDTO.setDetallePagoCasoList(detallePagoCasoList);
			PagoCaso pagoCasoExiste = new PagoCaso();
			pagoCasoExiste = pagoCasoFacade
					.obtenerPagosPorNumeroRecibo(pagoCasoDTO.getNumeroRecibo());
			
			if (pagoCasoExiste != null) {
				foulInfo.setCodigoFault(501);
				String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR242.toString());
				foulInfo.setEnviarMensajeFault(mensajeError);
				throw new CrearPagoCasoFault(mensajeError, foulInfo);
			}
			ServletContext servletContext =
				    (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
			
			//pagoCaso = pagoCasoFacade.crearPagoCaso(pagoCasoDTO, servletContext.getRealPath("/"));
			pagoCaso = casoTramiteOrdinarioFacade.crearPagoCasoTramiteOrdinario(pagoCasoDTO,
					servletContext.getRealPath("/"));
			
			if (pagoCaso != null) {
				resp.setCodigo(new BigDecimal(pagoCaso.getPagoCaso().getIdCaso()).intValue());
				String mensaje;
				if (pagoCaso.getCaso() != null && UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION
						.equals(pagoCaso.getCaso().getEstadoCaso()))
					mensaje = MensajesEnum.INFO801.toString();
				else
					mensaje = MensajesEnum.INFO800.toString();
				resp.setMensajePago(String.format(MensajesSimasc.getInstancia().getMensajePorKey(mensaje),
						pagoCaso.getPagoCaso().getNumeroRecibo()));
				resp.setNumeroRecibo(pagoCaso.getPagoCaso().getNumeroRecibo());
				resp.setValorPago(new BigDecimal(pagoCaso.getPagoCaso().getValor()));
			} else {
				foulInfo.setCodigoFault(500);
				String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR243.toString());
				foulInfo.setEnviarMensajeFault(mensajeError);
				throw new CrearPagoCasoFault(mensajeError, foulInfo);
			}
		}catch (NumberFormatException | NullPointerException | ArithmeticException
				| TagAttributeException e) {
			foulInfo.setCodigoFault(502);
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR244.toString());
			foulInfo.setEnviarMensajeFault(mensajeError);
			throw new CrearPagoCasoFault(e.getMessage(), foulInfo);
		} catch (CrearPagoCasoFault e) {
			foulInfo.setCodigoFault(e.getFaultInfo().getCodigoFault());
			foulInfo.setEnviarMensajeFault(e.getFaultInfo().getEnviarMensajeFault());
			throw new CrearPagoCasoFault(e.getFaultInfo().getEnviarMensajeFault(), foulInfo);
		} catch (Exception e) {
			foulInfo.setCodigoFault(500);
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR243.toString());
			foulInfo.setEnviarMensajeFault(mensajeError);
			Throwable causa = ExceptionUtils.getRootCause(e);
			throw new CrearPagoCasoFault(causa != null ? causa.getMessage() : ExceptionUtils.getRootCauseMessage(e),
					foulInfo);
		}
		return resp;
	}
	
	/**
	 * Homologa los codigos de sirep
	 * @throws CrearPagoCasoFault 
	 */
	private String homologarTipoDocumentoSimasc(String tipoDocumento) throws CrearPagoCasoFault {
		String codigoHomologado = manejadorHomologacionSistemaExterno.consultarCodigosSimasc(UtilConstantes.SISTEMA_EXTERNO_MINISTERIO, tipoDocumento,
				UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA);
		if(codigoHomologado != null){
			return codigoHomologado;
		}else{
			CrearPagoCasoFaultType foulInfo = new CrearPagoCasoFaultType();
			foulInfo.setCodigoFault(500);
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR320.toString());
			foulInfo.setEnviarMensajeFault(mensajeError);
			throw new CrearPagoCasoFault("Error documentos", foulInfo);		
		}
	}
	
	/*
	 * homologa la sede y la convierte de string a Long
	 */
	private Long homologacionSede(String idSede) throws CrearPagoCasoFault{
		Long idSedeConvertida =-1L;
		try{
			idSedeConvertida = Long.parseLong(idSede);
		}catch(NumberFormatException  e){			
				String homologacion =
						manejadorHomologacionSistemaExterno.consultarCodigosSimasc(UtilConstantes.SISTEMA_EXTERNO_PAGOS,
								UtilConstantes.CODIGO_SEDE_VIRTUAL, UtilDominios.DOMINIO_HOMOLOGACION_SEDE);	
				if(homologacion != null){
					idSedeConvertida =Long.parseLong(homologacion);
				}else{
					CrearPagoCasoFaultType foulInfo = new CrearPagoCasoFaultType();
					foulInfo.setCodigoFault(500);
					String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR321.toString());
					foulInfo.setEnviarMensajeFault(mensajeError);
					throw new CrearPagoCasoFault("Error sedes", foulInfo);						
				}
		}	
		return idSedeConvertida;
	}

}
