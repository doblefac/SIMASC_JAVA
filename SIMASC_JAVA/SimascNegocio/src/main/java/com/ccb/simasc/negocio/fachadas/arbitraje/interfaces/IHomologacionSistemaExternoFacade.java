package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.HomologacionSistemaExternoDTO;
import com.ccb.simasc.transversal.entidades.HomologacionSistemaExterno;
import com.ccb.simasc.transversal.entidades.HomologacionSistemaExternoPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link HomologacionSistemaExterno}
 * @author sMartinez
 *
 */
@Local
public interface IHomologacionSistemaExternoFacade extends IAccesoFacade<HomologacionSistemaExterno, HomologacionSistemaExternoPK, HomologacionSistemaExternoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
	public HomologacionSistemaExterno obtenerHomologacionPorSistemaExterno(HomologacionSistemaExterno homologacion);
	public List<HomologacionSistemaExterno> consultarHomologacionesPorSistemaExterno(String nombreSistemaExterno);
	public void actulizarHomologacionSistemaExterno(HomologacionSistemaExterno homologacion);
	public void desactivarHomologacionSistemaExterno(HomologacionSistemaExterno homologacion);
	
}
