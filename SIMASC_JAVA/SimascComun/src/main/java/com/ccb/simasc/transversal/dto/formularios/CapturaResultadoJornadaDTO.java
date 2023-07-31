package com.ccb.simasc.transversal.dto.formularios;

/**
 * DTO utilizado para obtener los casos para registrar el cierre del caso de jornada
 * @author jnmartinez
 *
 */
public class CapturaResultadoJornadaDTO {

	private Long idConciliador;
	private String codigoResultado;
	private Long idCaso;
	private Long idDocumento;
	private Long idArea;
	private Long idAsunto;
	private Long idClasificacion;
	
	
	
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
	 * @return the idCaso
	 */
	public Long getIdCaso() {
		return idCaso;
	}
	/**
	 * @param idCaso the idCaso to set
	 */
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	/**
	 * @return the idDocumento
	 */
	public Long getIdDocumento() {
		return idDocumento;
	}
	/**
	 * @param idDocumento the idDocumento to set
	 */
	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}
	/**
	 * @return the codigoResultado
	 */
	public String getCodigoResultado() {
		return codigoResultado;
	}
	/**
	 * @param codigoResultado the codigoResultado to set
	 */
	public void setCodigoResultado(String codigoResultado) {
		this.codigoResultado = codigoResultado;
	}
	/**
	 * @return the idArea
	 */
	public Long getIdArea() {
		return idArea;
	}
	/**
	 * @param idArea the idArea to set
	 */
	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}
	/**
	 * @return the idAsunto
	 */
	public Long getIdAsunto() {
		return idAsunto;
	}
	/**
	 * @param idAsunto the idAsunto to set
	 */
	public void setIdAsunto(Long idAsunto) {
		this.idAsunto = idAsunto;
	}
	/**
	 * @return the idClasificacion
	 */
	public Long getIdClasificacion() {
		return idClasificacion;
	}
	/**
	 * @param idClasificacion the idClasificacion to set
	 */
	public void setIdClasificacion(Long idClasificacion) {
		this.idClasificacion = idClasificacion;
	}
	
	
}
