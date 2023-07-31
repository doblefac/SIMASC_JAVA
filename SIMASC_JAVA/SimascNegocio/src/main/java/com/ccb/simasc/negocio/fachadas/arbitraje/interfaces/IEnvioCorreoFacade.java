package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;

import javax.ejb.Local;

import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;

@Local
public interface IEnvioCorreoFacade {

	/**
	 * 
	 * 
	 * @param correoElectronicoDTO
	 * @param parametrosEnvioCorreoDTO
	 * @param idCaso
	 * @param causaFallo
	 * @return
	 * @throws Exception 
	 */
	public void registrarFalloEnvioCorreo(CorreoElectronicoDTO correoElectronicoDTO,
	ParametrosEnvioCorreoDTO parametrosEnvioCorreoDTO, Long idCaso, String causaFallo) throws Exception; 
	

}
