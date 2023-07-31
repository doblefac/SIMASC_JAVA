package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.List;

public class ParametrosSedesRepartoDTO implements Serializable {

	private static final long serialVersionUID = -5803129234678766423L;
	private Long idCentro;
	private Long idServicio;
	private Long idConciliador;
	private FormatoHoraAudienciaDTO formatoHora;
	private List<FormatoHoraAudienciaDTO> listaHoras;
	/**
	 * @return the idCentro
	 */
	public Long getIdCentro() {
		return idCentro;
	}
	/**
	 * @param idCentro the idCentro to set
	 */
	public void setIdCentro(Long idCentro) {
		this.idCentro = idCentro;
	}
	/**
	 * @return the idServicio
	 */
	public Long getIdServicio() {
		return idServicio;
	}
	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	/**
	 * @return the idConciliador
	 */
	public Long getIdConciliador() {
		return idConciliador;
	}
	/**
	 * @param idConciliador the idConciliador to set
	 */
	public void setIdConciliador(Long idConciliador) {
		this.idConciliador = idConciliador;
	}
	/**
	 * @return the formatoHora
	 */
	public FormatoHoraAudienciaDTO getFormatoHora() {
		return formatoHora;
	}
	/**
	 * @param formatoHora the formatoHora to set
	 */
	public void setFormatoHora(FormatoHoraAudienciaDTO formatoHora) {
		this.formatoHora = formatoHora;
	}
	/**
	 * @return the listaHoras
	 */
	public List<FormatoHoraAudienciaDTO> getListaHoras() {
		return listaHoras;
	}
	/**
	 * @param listaHoras the listaHoras to set
	 */
	public void setListaHoras(List<FormatoHoraAudienciaDTO> listaHoras) {
		this.listaHoras = listaHoras;
	}

	
	
}
