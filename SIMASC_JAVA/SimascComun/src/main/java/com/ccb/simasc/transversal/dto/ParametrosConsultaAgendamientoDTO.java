package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Objeto que contiene los parametros de consulta del agendamiento
 * 
 * @author jcepeda
 *
 */
public class ParametrosConsultaAgendamientoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int diasMinimos;
	private int diasMaximos;
	private boolean filtrarHoras;
	private String duracionAudiencia;
	private String horaInicial;
	private String horaFinal;
	private Long idSede;
	private Long idCaso;
	private Long idConciliador;
	private Date fechaAgendamiento;
	
	public ParametrosConsultaAgendamientoDTO() {
		
	}

	public ParametrosConsultaAgendamientoDTO(int diasMinimos, int diasMaximos, boolean filtrarHoras,
			String duracionAudiencia, String horaInicial, String horaFinal, Long idSede, Long idCaso,
			Long idConciliador, Date fechaAgendamiento) {
		super();
		this.diasMinimos = diasMinimos;
		this.diasMaximos = diasMaximos;
		this.filtrarHoras = filtrarHoras;
		this.duracionAudiencia = duracionAudiencia;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.idSede = idSede;
		this.idCaso = idCaso;
		this.idConciliador = idConciliador;
		this.fechaAgendamiento = fechaAgendamiento;
	}



	/**
	 * @return the diasMinimos
	 */
	public int getDiasMinimos() {
		return diasMinimos;
	}

	/**
	 * @param diasMinimos
	 *            the diasMinimos to set
	 */
	public void setDiasMinimos(int diasMinimos) {
		this.diasMinimos = diasMinimos;
	}

	/**
	 * @return the diasMaximos
	 */
	public int getDiasMaximos() {
		return diasMaximos;
	}

	/**
	 * @param diasMaximos
	 *            the diasMaximos to set
	 */
	public void setDiasMaximos(int diasMaximos) {
		this.diasMaximos = diasMaximos;
	}

	/**
	 * @return the filtrarHoras
	 */
	public boolean isFiltrarHoras() {
		return filtrarHoras;
	}

	/**
	 * @param filtrarHoras
	 *            the filtrarHoras to set
	 */
	public void setFiltrarHoras(boolean filtrarHoras) {
		this.filtrarHoras = filtrarHoras;
	}

	/**
	 * @return the duracionAudiencia
	 */
	public String getDuracionAudiencia() {
		return duracionAudiencia;
	}

	/**
	 * @param duracionAudiencia
	 *            the duracionAudiencia to set
	 */
	public void setDuracionAudiencia(String duracionAudiencia) {
		this.duracionAudiencia = duracionAudiencia;
	}

	/**
	 * @return the horaInicial
	 */
	public String getHoraInicial() {
		return horaInicial;
	}

	/**
	 * @param horaInicial
	 *            the horaInicial to set
	 */
	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}

	/**
	 * @return the horaFinal
	 */
	public String getHoraFinal() {
		return horaFinal;
	}

	/**
	 * @param horaFinal
	 *            the horaFinal to set
	 */
	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	/**
	 * @return the idSede
	 */
	public Long getIdSede() {
		return idSede;
	}

	/**
	 * @param idSede
	 *            the idSede to set
	 */
	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}

	/**
	 * @return the idCaso
	 */
	public Long getIdCaso() {
		return idCaso;
	}

	/**
	 * @param idCaso
	 *            the idCaso to set
	 */
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}

	/**
	 * @return the idConciliador
	 */
	public Long getIdConciliador() {
		return idConciliador;
	}

	/**
	 * @param idConciliador
	 *            the idConciliador to set
	 */
	public void setIdConciliador(Long idConciliador) {
		this.idConciliador = idConciliador;
	}

	/**
	 * @return the fechaAgendamiento
	 */
	public Date getFechaAgendamiento() {
		return fechaAgendamiento;
	}

	/**
	 * @param fechaAgendamiento
	 *            the fechaAgendamiento to set
	 */
	public void setFechaAgendamiento(Date fechaAgendamiento) {
		this.fechaAgendamiento = fechaAgendamiento;
	}

}
