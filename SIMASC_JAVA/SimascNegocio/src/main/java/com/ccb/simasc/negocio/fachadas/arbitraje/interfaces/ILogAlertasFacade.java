package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;

import javax.ejb.Local;

@Local
public interface ILogAlertasFacade {

	public void generarLogCaso() throws Exception;
	
	public void generarLogAlerta(String texto, String estado, Long idAlerta, Long idProgramacion,String codigoEjecucion ) throws Exception;
	

}
