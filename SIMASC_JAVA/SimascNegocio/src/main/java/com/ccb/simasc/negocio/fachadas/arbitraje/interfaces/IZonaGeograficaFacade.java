package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ZonaGeograficaDTO;
import com.ccb.simasc.transversal.entidades.ZonaGeografica;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link ZonaGeografica}
 * @author sMartinez
 *
 */
@Local
public interface IZonaGeograficaFacade extends IAccesoFacade<ZonaGeografica, String, ZonaGeograficaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	public List<ZonaGeografica> getBusquedaPaises() throws SIMASCNegocioExcepcion;
	public List<ZonaGeografica> getBusquedaCiudadesXPais(String idPais) throws SIMASCNegocioExcepcion;
	public ZonaGeografica getBusquedaEntidadPorNombre(String nombre) throws SIMASCNegocioExcepcion;
	public List<ZonaGeografica> getBusquedaCiudadesXNacionalidad(String idZona);
	public List<ZonaGeograficaDTO> consultarPaisesPorDepartamento(List<String> idZonasGeograficas);
	// protected region metodos adicionales end
	
}
