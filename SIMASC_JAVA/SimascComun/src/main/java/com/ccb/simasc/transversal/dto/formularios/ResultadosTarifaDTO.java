package com.ccb.simasc.transversal.dto.formularios;

import java.util.List;

import com.ccb.simasc.transversal.dto.HonorariosFijadosDTO;
import com.ccb.simasc.transversal.dto.ParametrizacionTarifasDTO;

/**
 * DTO para el manejo de los calculos de tarifa 
 * @author jnmartinez
 *
 */
public class ResultadosTarifaDTO {

	private List<HonorariosFijadosDTO> resultados;
	private List<ParametrizacionTarifasDTO> rangos;
	
	public List<HonorariosFijadosDTO> getResultados() {
		return resultados;
	}
	public void setResultados(List<HonorariosFijadosDTO> resultados) {
		this.resultados = resultados;
	}
	public List<ParametrizacionTarifasDTO> getRangos() {
		return rangos;
	}
	public void setRangos(List<ParametrizacionTarifasDTO> rangos) {
		this.rangos = rangos;
	}
	
	
	
}
