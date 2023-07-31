package com.ccb.simasc.comun.webservice.integracion.implementacion;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.webservice.integracion.interfaces.IClienteSWFirmaDigital;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.transversal.dto.DatosFirmaActaConstantciaDTO;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.excepciones.SimascNegocioPruebaException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;
import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;
import com.simasc.clientes.esignServiceClient.ArgumentWS;
import com.simasc.clientes.esignServiceClient.ElectronicSignatureWS;
import com.simasc.clientes.esignServiceClient.RespuestaWS;
import com.simasc.clientes.esignServiceClient.WSDigitalPDFDelegate;
import com.simasc.clientes.esignServiceClient.WSDigitalPDFService;

/**
 * Clase que se comunica con el sistema de firma digital pdf
 * @author lruiz
 *
 */
@Stateless
@LocalBean
public class ClienteSWFirmaDigital implements IClienteSWFirmaDigital {

	private WSDigitalPDFDelegate esignDelegate;
	
	private WSDigitalPDFService esignService;
	
	private static final Logger logger = LogManager.getLogger(ClienteSWFirmaDigital.class.getName());
	
	private static final String NUMERO_CEDULA = "Numero de cedula del firmante";
	private static final String NOMBRE_COMPLETO = "Nombre completo del firmante";
	private static final String DIRECCION_IP = "Direccion IP del equipo donde se solicito aplicar la firma";
	private static final String DIRECCION_MAC = "Direccion MAC del equipo donde se solicito aplicar la firma. Obligatorio";
	private static final String IDENTIFICADOR_SUCURSAL = "Identificador de la Sucursal donde se solicito aplicar la firma";
	private static final String APLICATIVO = "Aplicativo que aplico la firma";
	
	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;

	@PostConstruct
	public void init() {
		String urlFirma = consultaUrl();
		try {
			esignService = new WSDigitalPDFService(new URL(urlFirma));		
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
		}
	}
	
	private String consultaUrl() {
		String url = null;
		ParametrosGenerales parametroUrl = manejadorParametrosGenerales
				.buscar(UtilParamGenerales.WS_WEB_SERVICE_FIRMA);
		if (parametroUrl != null && parametroUrl.getValorTexto() != null) {
			url = parametroUrl.getValorTexto();
		} else {
			throw new SimascException("No existe el parametro de la url para la firma de documentos");
		}
		return url;

	}
	
	@Override
	public byte[] firmarPDF(String ruta, String archivo, Persona persona, DatosFirmaActaConstantciaDTO datos,
			boolean isDigital) throws SimascNegocioPruebaException {
		String idCliente = manejadorParametrosGenerales.buscar(UtilParamGenerales.WS_ID_CLIENTE).getValorTexto();
		String password = manejadorParametrosGenerales.buscar(UtilParamGenerales.WS_INGRESO).getValorTexto();
		String politica;
		if (isDigital) {	
			System.out.println("*****************Es digital***********");
			politica = manejadorParametrosGenerales.buscar(UtilParamGenerales.WS_POLITICA_DIGITAL).getValorTexto();			
			if(politica == null){
				throw new SimascNegocioPruebaException(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR652.toString()));
			}
		} else {
			if(persona != null){
				System.out.println("*****************Es persona***********"+persona.getIdPolitica()+"*******");
				politica = persona.getIdPolitica();
			} else{
				politica = manejadorParametrosGenerales.buscar(UtilParamGenerales.WS_POLITICA_ELECTRONICA).getValorTexto();
				System.out.println("*****************Else politica***********"+politica);
			}
			if(politica == null){
				throw new SimascNegocioPruebaException(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR652.toString()));
			}
		}
		byte[] pdf = null;
		int intentos = 3;
		while (intentos > 0) {
			try {
				esignDelegate = esignService.getWSDigitalPDFPort();
				pdf = UtilOperaciones.convertToByte(ruta, archivo);
				RespuestaWS respuesta = esignDelegate.procesarPDF(idCliente, password, politica, pdf, null, "-1", null,
						null, generarDatosFirmaElectronica(persona, datos));
				if (respuesta.getRespuestaObj().getMensajes() != null
						&& !respuesta.getRespuestaObj().getMensajes().isEmpty()) {
					throw new SimascException(respuesta.getRespuestaObj().getMensajes().get(0).getMensaje());
				}
				pdf = respuesta.getPdf();
				intentos = 0;
			} catch (Exception e) {
				if (intentos == 1) {
					logger.error("Error: ", e);
					throw new SimascNegocioPruebaException(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR086.toString()));
				} else {
					intentos -= 1;
				}
			}
		}
		return pdf;
	}
	
	private ElectronicSignatureWS generarDatosFirmaElectronica(Persona persona, DatosFirmaActaConstantciaDTO datos) {
		ElectronicSignatureWS firmaElectronica = null;
		if (persona != null && datos.getIdRol() != null) {
			firmaElectronica = new ElectronicSignatureWS();
			String identificadorSucursal = manejadorParametrosGenerales.buscar(UtilParamGenerales.CODIGO_IDENTIFICADOR_SUCURSAL_FIRMA).getValorTexto();
			String tipo = manejadorParametrosGenerales.buscar(UtilParamGenerales.CODIGO_TIPO_FIRMA).getValorTexto();
			firmaElectronica.getArguments().add(agregarArgumento(NUMERO_CEDULA, persona.getNumeroDocumento()));
			firmaElectronica.getArguments().add(agregarArgumento(NOMBRE_COMPLETO, persona.getNombreCompleto()));
			firmaElectronica.getArguments().add(agregarArgumento(DIRECCION_IP, datos.getDireccionIPOrigen()));
			firmaElectronica.getArguments().add(agregarArgumento(DIRECCION_MAC, datos.getDireccionMACOrigen()));
			firmaElectronica.getArguments().add(agregarArgumento(IDENTIFICADOR_SUCURSAL, identificadorSucursal));
			firmaElectronica.getArguments().add(agregarArgumento(APLICATIVO, datos.getClaveSegura()));
			
			firmaElectronica.setElectronicData(UtilOperaciones.getMd5(datos.getClaveSegura()).getBytes());
			firmaElectronica.setType(tipo);
		}
		
		return firmaElectronica;
	}
	
	private ArgumentWS agregarArgumento(String nombre, String valor) {
		ArgumentWS argumentCedula = new ArgumentWS();
		argumentCedula.setNombre(nombre);
		argumentCedula.setValor(valor);
		return argumentCedula;
	}

}
