package com.ccb.simasc.comun.webservice.integracion.interfaces;

import javax.ejb.Local;

import com.ccb.simasc.transversal.dto.formularios.DatosTokenDTO;

@Local
public interface IClienteSWToken {

	/**
	 * Metodo encargado de solicitar el token seguro
	 * @param datosToken
	 * @return
	 */
	public String solicitarTokenClaveSegura(DatosTokenDTO datosToken);

}
