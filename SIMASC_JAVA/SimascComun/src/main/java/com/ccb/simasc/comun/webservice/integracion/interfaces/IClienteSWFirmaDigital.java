package com.ccb.simasc.comun.webservice.integracion.interfaces;

import javax.ejb.Local;

import com.ccb.simasc.transversal.dto.DatosFirmaActaConstantciaDTO;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.excepciones.SimascNegocioPruebaException;

/**
 * 
 * @author lruiz
 *
 */
@Local
public interface IClienteSWFirmaDigital {
	/**
	 * Metodo que llama al webservice y realiza la firma del pdf
	 * @param ruta
	 * @param archivo
	 * @return
	 */
	public byte[] firmarPDF(String ruta, String archivo, Persona persona, DatosFirmaActaConstantciaDTO datos, boolean isDigital) throws SimascNegocioPruebaException;
}
