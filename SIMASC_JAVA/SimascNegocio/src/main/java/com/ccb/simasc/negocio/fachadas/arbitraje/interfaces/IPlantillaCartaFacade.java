package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.PlantillaCartaDTO;
import com.ccb.simasc.transversal.dto.PlantillaVariablesCartaEditorDTO;
import com.ccb.simasc.transversal.entidades.PlantillaCarta;
import com.ccb.simasc.transversal.entidades.ValorPlantillaCarta;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link PlantillaCarta}
 * @author sMartinez
 *
 */
@Local
public interface IPlantillaCartaFacade extends IAccesoFacade<PlantillaCarta, Long, PlantillaCartaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * Método encargado de obtener las plantillas de las cartas cuyo estado de
	 * registro sea 'Activo'
	 * 
	 * @return
	 */
	public List<PlantillaCarta> obtenerPlantillasCartas();

	/**
	 * Método que obtiene el listado de plantillas de acuerdo al tipo de servicio
	 * y si se recibe el id de la plantilla 
	 * @param tipoServicio
	 * @param idPlantilla
	 * @return
	 */
	public List<PlantillaCarta> obtenerPlantillasFiltros(String tipoServicio, Long idPlantilla);

	/**
	 * Método para realizar la creacion o actualizacion de una plantilla 
	 * junto con los tags que contenga
	 * @param plantilla
	 * @return
	 */
	public Long crearPlantillaCarta(PlantillaVariablesCartaEditorDTO plantilla);
	
	/**
	 * Metodo que consulta una plantilla buscada por su idCaso y por el nombre de la plantilla (el nombre de la
	 * plantilla es un dominio)
	 * @param idCaso
	 * @return PlantillaCartaDTO
	 */
	public PlantillaCartaDTO consultarPlantillaServicioCaso(Long idCaso, String nombrePlantilla);

	/**
	 * Método que consulta los valores de los tags de la plantilla
	 * @param tag
	 * @param plantilla
	 * @return
	 */
	public List<ValorPlantillaCarta> crearFiltros(Long idPlantillaCarta);
	
	
	public List<PlantillaCarta> consultarPlantillaNombre(String nombre, String tipoServicio);
	
	public PlantillaCartaDTO consultarPlantillaServicioNombre(Long idServicio, String nombrePlantilla , Long idCaso);
	
	public List<PlantillaCarta> obtenerPlantillasEquidad();
	
	// protected region metodos adicionales end
	
}
