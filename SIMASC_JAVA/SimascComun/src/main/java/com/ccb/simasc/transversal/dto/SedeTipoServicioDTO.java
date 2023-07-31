package com.ccb.simasc.transversal.dto;

import java.util.List;

import com.ccb.simasc.transversal.entidades.Sede;
import com.ccb.simasc.transversal.entidades.TipoServicioSede;

public class SedeTipoServicioDTO {
	
	private Sede sede;
	private List<TipoServicioSede> tipoServicioSede;
	/**
	 * @return the sede
	 */
	public Sede getSede() {
		return sede;
	}
	/**
	 * @param sede the sede to set
	 */
	public void setSede(Sede sede) {
		this.sede = sede;
	}
	/**
	 * @return the tipoServicioSede
	 */
	public List<TipoServicioSede> getTipoServicioSede() {
		return tipoServicioSede;
	}
	/**
	 * @param tipoServicioSede the tipoServicioSede to set
	 */
	public void setTipoServicioSede(List<TipoServicioSede> tipoServicioSede) {
		this.tipoServicioSede = tipoServicioSede;
	}

}
