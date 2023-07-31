package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Local;

import org.apache.http.client.ClientProtocolException;

import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;


@Local
public interface ICaptchaVerificaSitioFacade{
	
	public String consumirServicioVerificacionDeSitio(String keyResponse) 
			throws SIMASCNegocioExcepcion, ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException;
}
