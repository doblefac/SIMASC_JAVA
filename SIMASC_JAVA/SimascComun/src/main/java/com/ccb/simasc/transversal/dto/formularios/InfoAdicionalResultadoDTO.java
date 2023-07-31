package com.ccb.simasc.transversal.dto.formularios;

import java.math.BigDecimal;
import java.util.List;

public class InfoAdicionalResultadoDTO {

	// partes involucradas de la obligacion
	private List<PersonaBasicaDTO> partesInvolucradas;
	// valor total acuerdo por resultado de audiencia
	private BigDecimal valorAcuerdo;

	public List<PersonaBasicaDTO> getPartesInvolucradas() {
		return partesInvolucradas;
	}

	public void setPartesInvolucradas(List<PersonaBasicaDTO> partesInvolucradas) {
		this.partesInvolucradas = partesInvolucradas;
	}

	public BigDecimal getValorAcuerdo() {
		return valorAcuerdo;
	}

	public void setValorAcuerdo(BigDecimal valorAcuerdo) {
		this.valorAcuerdo = valorAcuerdo;
	}

}
