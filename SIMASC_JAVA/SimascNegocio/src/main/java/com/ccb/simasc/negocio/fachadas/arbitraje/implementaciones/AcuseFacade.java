package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorAcuse;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAcuseFacade;
import com.ccb.simasc.transversal.dto.AcuseDTO;
import com.ccb.simasc.transversal.entidades.Acuse;
import com.ccb.simasc.transversal.entidades.AcusePK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Acuse}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class AcuseFacade extends AccesoFacade<Acuse, AcusePK, AcuseDTO> implements IAcuseFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorAcuse manejadorAcuse;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorAcuse;
	}

	@Override
	public AcuseDTO transformarSinDependencias(Acuse obj) {
		AcuseDTO dto = new AcuseDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public AcuseDTO transformarConDependencias(Acuse obj) {
		AcuseDTO dto = new AcuseDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Acuse transformarEntidadConDependencias(Acuse obj) {
		Acuse dto = new Acuse();
		dto = new AcuseDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Acuse transformarEntidadSinDependencias(Acuse obj) {
		Acuse dto = new Acuse();
		dto = new AcuseDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAcuseFacade#
	 * obtenerAcusesCorreoRolPersonaCaso(java.lang.Long, java.lang.String)
	 */
	@Override
	public AcuseDTO obtenerAcuseCorreoRolPersonaCaso(Long idCorreoRolPersonaCaso, String tipo) {
		Acuse acuse = manejadorAcuse.obtenerAcuseCorreoRolPersonaCaso(idCorreoRolPersonaCaso, tipo);
		return new AcuseDTO().transformarSinDependencias(acuse);
	}

	// protected region metodos adicionales end
	
}
