package com.ccb.simasc.comun.webservice.integracion.implementacion;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.webservice.integracion.interfaces.IClienteSWToken;
import com.ccb.simasc.integracion.manejadores.ManejadorHomologacionSistemaExterno;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.transversal.dto.formularios.DatosTokenDTO;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;
import com.simasc.clientes.token.DTOCrearTokenEntrada;
import com.simasc.clientes.token.DTOCrearTokenSalida;
import com.simasc.clientes.token.IService;
import com.simasc.clientes.token.ObjectFactory;
import com.simasc.clientes.token.Service;

@Stateless
@LocalBean
public class ClienteSWToken implements IClienteSWToken {
	private static final Logger logger = LogManager.getLogger(ClienteSWToken.class.getName());
	private Service tokenWebServicePort;
	private IService clientService;
	
	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;
	
	@EJB
	private ManejadorHomologacionSistemaExterno manejadorHomologacionSistemaExterno;
	
	ObjectFactory generic = new ObjectFactory();
	
	/**
	 * Se instancia el endpoint y el cliente del servicio a consumir
	 */
	@PostConstruct
	public void init() {
		String urlToken = consutlaUrl();

		try {
			tokenWebServicePort = new Service(new URL(urlToken));
			clientService = tokenWebServicePort.getEpBasic();
		} catch (MalformedURLException e) {
			logger.error("Error: ", e);
		}

	}
	
	@Override
	public String solicitarTokenClaveSegura(DatosTokenDTO datosToken) {
		DTOCrearTokenEntrada dtoEntrada = new DTOCrearTokenEntrada();
		dtoEntrada.setIdAplicacion(generic.createDTOCrearTokenEntradaIdAplicacion(datosToken.getIdAplicacion() != null ? datosToken.getIdAplicacion() : UtilConstantes.ESPACIO));
		dtoEntrada.setIp(generic.createDTOCrearTokenEntradaIp(datosToken.getIp() != null ? datosToken.getIp() : UtilConstantes.ESPACIO));
		dtoEntrada.setNumeroDocumento(generic.createDTOCrearTokenEntradaNumeroDocumento(datosToken.getNumeroDocumento() != null ? datosToken.getNumeroDocumento() : UtilConstantes.ESPACIO));
		dtoEntrada.setTipoDocumento(generic.createDTOCrearTokenEntradaTipoDocumento(homologarTipoDocumentoSirep(datosToken.getTipoDocumento())));
		dtoEntrada.setSede(generic.createDTOCrearTokenEntradaSede(datosToken.getSede() != null ? datosToken.getSede() : UtilConstantes.ESPACIO));
		DTOCrearTokenSalida tokenSalida =	clientService.solicitarTokenClaveSegura(dtoEntrada);
		if (!tokenSalida.getCodigo().equals(1)) {
			System.err.println(tokenSalida.getMensajeError().getValue());
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR925.toString()));
		}
		
		return tokenSalida.getToken();
		
	}
	
	private String homologarTipoDocumentoSirep(String tipoDocumento) {
		return manejadorHomologacionSistemaExterno.consultarCodigosSistemaExterno(
				UtilConstantes.SISTEMA_EXTERNO_SIREP, tipoDocumento,
				UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA);
	}

	private String consutlaUrl(){
		String url = null;
		ParametrosGenerales parametroUrl = manejadorParametrosGenerales
				.buscar(UtilParamGenerales.WS_TOKEN);
		if (parametroUrl != null && parametroUrl.getValorTexto() != null) {
			url = parametroUrl.getValorTexto();
		} else {
			throw new SimascException("No existe el parametro de la url para el token");
		}
		return url;
	}

}
