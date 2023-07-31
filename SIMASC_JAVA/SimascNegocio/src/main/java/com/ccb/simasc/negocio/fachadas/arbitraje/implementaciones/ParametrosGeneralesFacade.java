package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrosGeneralesFacade;
import com.ccb.simasc.transversal.dto.ParametrosGeneralesDTO;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ParametrosGenerales}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ParametrosGeneralesFacade extends AccesoFacade<ParametrosGenerales, String, ParametrosGeneralesDTO> implements IParametrosGeneralesFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	
	/**
	 * Manejador para consultar los parametros generales
	 */
	@EJB(beanName="ManejadorParametrosGeneralesBean")
	private ManejadorParametrosGenerales manejadorParametrosGenerales;	
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorParametrosGenerales;
	}

	@Override
	public ParametrosGeneralesDTO transformarSinDependencias(ParametrosGenerales obj) {
		ParametrosGeneralesDTO dto = new ParametrosGeneralesDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ParametrosGeneralesDTO transformarConDependencias(ParametrosGenerales obj) {
		ParametrosGeneralesDTO dto = new ParametrosGeneralesDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ParametrosGenerales transformarEntidadConDependencias(ParametrosGenerales obj) {
		return new ParametrosGeneralesDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public ParametrosGenerales transformarEntidadSinDependencias(ParametrosGenerales obj) {
		return new ParametrosGeneralesDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * Consulta los parametros generales por codigo
	 */
	@Override
	public ParametrosGenerales consultarPorCodigo(String codigo) {
		return manejadorParametrosGenerales.buscar(codigo);
	}
	
	
	@Override
	public List<ParametrosGenerales> consultarPorTipo(String tipo) {
		return manejadorParametrosGenerales.obtenerParametrosPorTipo(tipo);
	}

	public ParametrosGenerales consultarPorNombre(String nombre){
		return manejadorParametrosGenerales.obtenerParametroPorNombre(nombre);
	}
	
	@Override
	public void actualizarParametrosGenerales(List<ParametrosGenerales> parametrosGenerales, String idUsuario){
		
		manejadorParametrosGenerales.actualizarLista(parametrosGenerales);
	}
	
	// protected region metodos adicionales end
	
}
