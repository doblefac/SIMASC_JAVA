package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ProgramacionAlertaDTO;
import com.ccb.simasc.transversal.entidades.ProgramacionAlerta;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link ProgramacionAlerta}
 * @author sMartinez
 *
 */
@Local
public interface IProgramacionAlertaFacade extends IAccesoFacade<ProgramacionAlerta, Long, ProgramacionAlertaDTO> {

	

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public List<ProgramacionAlerta> consultaProgramacionesPorEjecutar();
	
	public void crearProgramacionAlerta(ProgramacionAlerta programacionAlerta);

	// protected region metodos adicionales end
	
}
