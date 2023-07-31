/**
 * 26/02/2019
 * @author jnmartinez
 */
package com.ccb.simasc.transversal.dto.cartas;

import java.io.Serializable;
import java.util.List;

/**
 * @author jnmartinez
 *
 */
public class GeneracionLoteDTO implements Serializable {

	private String nombreDocumento;
	private List<LotesCartasDTO> partes;
	
	/**
	 * 
	 */
	public GeneracionLoteDTO() {
		
	}
	
	/**
	 * @return the nombreDocumento
	 */
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	/**
	 * @param nombreDocumento the nombreDocumento to set
	 */
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	/**
	 * @return the partes
	 */
	public List<LotesCartasDTO> getPartes() {
		return partes;
	}
	/**
	 * @param partes the partes to set
	 */
	public void setPartes(List<LotesCartasDTO> partes) {
		this.partes = partes;
	}
}
