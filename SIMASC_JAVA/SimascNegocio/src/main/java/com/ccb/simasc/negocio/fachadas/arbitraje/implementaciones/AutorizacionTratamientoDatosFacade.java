package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorAutorizacionTratamientoDatos;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAutorizacionTratamientoDatosFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.AutorizacionTratamientoDatosDTO;
import com.ccb.simasc.transversal.entidades.AutorizacionTratamientoDatos;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link AutorizacionTratamientoDatos}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class AutorizacionTratamientoDatosFacade extends AccesoFacade<AutorizacionTratamientoDatos, Long, AutorizacionTratamientoDatosDTO> implements IAutorizacionTratamientoDatosFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorAutorizacionTratamientoDatos manejadorAutorizacionTratamientoDatos;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorAutorizacionTratamientoDatos;
	}

	@Override
	public AutorizacionTratamientoDatosDTO transformarSinDependencias(AutorizacionTratamientoDatos obj) {
		return new AutorizacionTratamientoDatosDTO().transformarSinDependencias(obj);
	}

	@Override
	public AutorizacionTratamientoDatosDTO transformarConDependencias(AutorizacionTratamientoDatos obj) {
		return new AutorizacionTratamientoDatosDTO().transformarConDependencias(obj);
	}

	@Override
	public AutorizacionTratamientoDatos transformarEntidadConDependencias(AutorizacionTratamientoDatos obj) {
		return new AutorizacionTratamientoDatosDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public AutorizacionTratamientoDatos transformarEntidadSinDependencias(AutorizacionTratamientoDatos obj) {
		return new AutorizacionTratamientoDatosDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	
	/**
	 * Crear la autorizacion de datos retornando el id de autorizacion
	 * @param autorizacion
	 * @return
	 */
	@Override
	public Long crearAutorizacionDatos(AutorizacionTratamientoDatos autorizacion){
		manejadorAutorizacionTratamientoDatos.crearSinAtributosAuditoria(autorizacion);
		return autorizacion.getIdAutorizacionTratamientoDatos();
	}
	
	
	

	// protected region metodos adicionales end
	
}
