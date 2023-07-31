package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICaptchaVerificaSitioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrosGeneralesFacade;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

@Stateless
@LocalBean
public class CaptchaVerificaSitioFacade implements ICaptchaVerificaSitioFacade {
	
	@EJB
    private IParametrosGeneralesFacade parametrosGeneralesFacade; 

	@Override
	public String consumirServicioVerificacionDeSitio(String keyResponse) 
			throws SIMASCNegocioExcepcion, ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException {
				
		ParametrosGenerales parametroUrl = parametrosGeneralesFacade.consultarPorCodigo(UtilConstantes.PARAMETRO_CODIGO_URL_SITE_VERIFY_CAPTCHA);
		String urlBase = parametroUrl.getValorTexto();
		ParametrosGenerales parametroSecretKey = parametrosGeneralesFacade.consultarPorCodigo(UtilConstantes.PARAMETRO_CODIGO_KEY_SECRET_CAPTCHA);
		String secretKey = parametroSecretKey.getValorTexto();		
		
		SSLContext sc = SSLContext.getInstance("TLSv1.2");
	      sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
	          new java.security.SecureRandom());
	      
		CloseableHttpClient httpClient = HttpClients.custom().setSSLContext(sc).build();				
		String parametros = "secret="+secretKey+"&response="+keyResponse;
		HttpPost httpPost = new HttpPost(urlBase+parametros);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");		
		
		httpPost.getAllHeaders();		
		HttpResponse response = httpClient.execute(httpPost);		
		String result = new BasicResponseHandler().handleResponse(response);	
				
		if (response.getStatusLine().getStatusCode() == 200) {				
			return result;
		}else {
			throw new SIMASCNegocioExcepcion(
					"StatusCode: "+response.getStatusLine().getStatusCode()+" message: "+result);
		}
	}

}
