/**
 * 
 */
package com.ccb.simasc.transversal.dto;

import java.util.List;

import com.ccb.simasc.transversal.entidades.PlantillaCarta;
import com.ccb.simasc.transversal.entidades.ValorPlantillaCarta;

/**
 * @author jlores
 *
 */
public class PlantillaVariablesCartaEditorDTO {

	private PlantillaCarta plantillaCarta;
	private List<ValorPlantillaCarta> variablesPlantilla;
	private String nombrePlantilla;
	
	
	
	
	public PlantillaCarta getPlantillaCarta() {
		return plantillaCarta;
	}
	public void setPlantillaCarta(PlantillaCarta plantillaCarta) {
		this.plantillaCarta = plantillaCarta;
	}
	public List<ValorPlantillaCarta> getVariablesPlantilla() {
		return variablesPlantilla;
	}
	public void setVariablesPlantilla(List<ValorPlantillaCarta> variablesPlantilla) {
		this.variablesPlantilla = variablesPlantilla;
	}
	/**
	 * @return the nombrePlantilla
	 */
	public String getNombrePlantilla() {
		return nombrePlantilla;
	}
	/**
	 * @param nombrePlantilla the nombrePlantilla to set
	 */
	public void setNombrePlantilla(String nombrePlantilla) {
		this.nombrePlantilla = nombrePlantilla;
	}
	
	

}
