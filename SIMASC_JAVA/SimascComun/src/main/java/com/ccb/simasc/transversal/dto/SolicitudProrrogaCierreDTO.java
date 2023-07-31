package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Contiene la informacion para la solicitud de prorroga de un cierre de caso (Conciliacion).
 * 
 * @author cagonzalez
 */
@XmlRootElement
public class SolicitudProrrogaCierreDTO  implements Serializable{
	
	private List<SolicitudProrrogaDTO> listaSolicitudProrroga;
	private Boolean permisosSolicitud;
	private Long diasCaso;
	/**
	 * @return the listaSolicitudProrroga
	 */
	public List<SolicitudProrrogaDTO> getListaSolicitudProrroga() {
		return listaSolicitudProrroga;
	}
	/**
	 * @param listaSolicitudProrroga the listaSolicitudProrroga to set
	 */
	public void setListaSolicitudProrroga(List<SolicitudProrrogaDTO> listaSolicitudProrroga) {
		this.listaSolicitudProrroga = listaSolicitudProrroga;
	}
	/**
	 * @return the permisosSolicitud
	 */
	public Boolean getPermisosSolicitud() {
		return permisosSolicitud;
	}
	/**
	 * @param permisosSolicitud the permisosSolicitud to set
	 */
	public void setPermisosSolicitud(Boolean permisosSolicitud) {
		this.permisosSolicitud = permisosSolicitud;
	}
	/**
	 * @return the diasCaso
	 */
	public Long getDiasCaso() {
		return diasCaso;
	}
	/**
	 * @param diasCaso the diasCaso to set
	 */
	public void setDiasCaso(Long diasCaso) {
		this.diasCaso = diasCaso;
	}
}
	