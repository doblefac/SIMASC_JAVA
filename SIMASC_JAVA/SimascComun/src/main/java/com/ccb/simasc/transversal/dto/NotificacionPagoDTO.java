package com.ccb.simasc.transversal.dto;

import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.PagoCaso;

public class NotificacionPagoDTO {

	private PagoCaso pagoCaso;
	private Caso caso;
	/**
	 * @return the pagoCaso
	 */
	public PagoCaso getPagoCaso() {
		return pagoCaso;
	}
	/**
	 * @param pagoCaso the pagoCaso to set
	 */
	public void setPagoCaso(PagoCaso pagoCaso) {
		this.pagoCaso = pagoCaso;
	}
	/**
	 * @return the caso
	 */
	public Caso getCaso() {
		return caso;
	}
	/**
	 * @param caso the caso to set
	 */
	public void setCaso(Caso caso) {
		this.caso = caso;
	}
	
	
}
