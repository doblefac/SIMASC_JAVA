package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorFalloAlerta;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFalloAlertaFacade;
import com.ccb.simasc.transversal.dto.FalloAlertaDTO;
import com.ccb.simasc.transversal.entidades.FalloAlerta;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link FalloAlerta}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class FalloAlertaFacade extends AccesoFacade<FalloAlerta, Long, FalloAlertaDTO> implements IFalloAlertaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorFalloAlerta manejadorFalloAlerta;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorFalloAlerta;
	}

	@Override
	public FalloAlertaDTO transformarSinDependencias(FalloAlerta obj) {
		FalloAlertaDTO dto = new FalloAlertaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public FalloAlertaDTO transformarConDependencias(FalloAlerta obj) {
		FalloAlertaDTO dto = new FalloAlertaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public FalloAlerta transformarEntidadConDependencias(FalloAlerta obj) {
		FalloAlerta dto = new FalloAlerta();
		dto = new FalloAlertaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public FalloAlerta transformarEntidadSinDependencias(FalloAlerta obj) {
		FalloAlerta dto = new FalloAlerta();
		dto = new FalloAlertaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	@Override
	public void crearFalloAlertas(Long idAlerta, Long idProgramacion, String estado){
		FalloAlerta falloAlerta = new FalloAlerta();
		falloAlerta.setEstado(estado);
		if(idProgramacion == null){
			falloAlerta.setIdAlerta(idAlerta);			
		}
		falloAlerta.setIdProgramacionAlerta(idProgramacion);
		falloAlerta.setFechaFallo(new Date());
		manejadorFalloAlerta.crearSinAtributosAuditoria(falloAlerta);
	}

	// protected region metodos adicionales end
	
}
