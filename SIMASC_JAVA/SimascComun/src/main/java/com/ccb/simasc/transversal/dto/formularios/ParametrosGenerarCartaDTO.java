package com.ccb.simasc.transversal.dto.formularios;

import java.util.List;

import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.PlantillaCarta;

public class ParametrosGenerarCartaDTO {
	
	private String idPlantilla;
	private Long idCaso;
	private Long idAudiencia;
	private String indicadorNotificacion;
	private String indicadorEnvio;
	private boolean envioPorLotes;
	private List<Long> listaIdNotificados;
	private List<Long> listaIdInvitados;
	private List<String> nombreSeleccionados;
	private List<Long> idsDocumentos;
	private PlantillaCarta plantillaCarta;
	private Caso caso;
	
	public String getIdPlantilla() {
		return idPlantilla;
	}
	public void setIdPlantilla(String idPlantilla) {
		this.idPlantilla = idPlantilla;
	}
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public String getIndicadorNotificacion() {
		return indicadorNotificacion;
	}
	public void setIndicadorNotificacion(String indicadorNotificacion) {
		this.indicadorNotificacion = indicadorNotificacion;
	}
	public List<Long> getListaIdNotificados() {
		return listaIdNotificados;
	}
	public void setListaIdNotificados(List<Long> listaIdNotificados) {
		this.listaIdNotificados = listaIdNotificados;
	}
	public List<Long> getListaIdInvitados() {
		return listaIdInvitados;
	}
	public void setListaIdInvitados(List<Long> listaIdInvitados) {
		this.listaIdInvitados = listaIdInvitados;
	}
	public Long getIdAudiencia() {
		return idAudiencia;
	}
	public void setIdAudiencia(Long idaudiencia) {
		this.idAudiencia = idaudiencia;
	}
	public List<String> getNombreSeleccionados() {
		return nombreSeleccionados;
	}
	public void setNombreSeleccionados(List<String> nombreSeleccionados) {
		this.nombreSeleccionados = nombreSeleccionados;
	}
	public List<Long> getIdsDocumentos() {
		return idsDocumentos;
	}
	public void setIdsDocumentos(List<Long> idsDocumentos) {
		this.idsDocumentos = idsDocumentos;
	}
	
	/**
	 * @return the indicadorEnvio
	 */
	public String getIndicadorEnvio() {
		return indicadorEnvio;
	}
	/**
	 * @param indicadorEnvio the indicadorEnvio to set
	 */
	public void setIndicadorEnvio(String indicadorEnvio) {
		this.indicadorEnvio = indicadorEnvio;
	}
	/**
	 * @return the envioPorLotes
	 */
	public boolean isEnvioPorLotes() {
		return envioPorLotes;
	}
	/**
	 * @param envioPorLotes the envioPorLotes to set
	 */
	public void setEnvioPorLotes(boolean envioPorLotes) {
		this.envioPorLotes = envioPorLotes;
	}
	/**
	 * @return the plantillaCarta
	 */
	public PlantillaCarta getPlantillaCarta() {
		return plantillaCarta;
	}
	/**
	 * @param plantillaCarta the plantillaCarta to set
	 */
	public void setPlantillaCarta(PlantillaCarta plantillaCarta) {
		this.plantillaCarta = plantillaCarta;
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
