package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO que contiene la información del reporte de radicacion de documentos
 * 
 * @author aacevedo
 */

@XmlRootElement
public class ReporteRadicadoDocumentosDTO {

	private Long codigoCaso;
	private String servicioCaso;	
	private String nombreCaso;
	private String fechaRadicacion;
	private String tipoDocumento;	
	private String nombreDocumento;
	private String fechaComunicacion;
	private String apoderadoDemandante;
	private String apoderadoDemandado;
	private String arbitros;
	private String secretario;
	private String abogados;
	private String consumo;

	
	public ReporteRadicadoDocumentosDTO(){
    }
	
	public Long getCodigoCaso() {
		return codigoCaso;
	}
	public void setCodigoCaso(Long codigoCaso) {
		this.codigoCaso = codigoCaso;
	}
	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	public String getFechaRadicacion() {
		return fechaRadicacion;
	}
	public void setFechaRadicacion(String fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}	
	public String getApoderadoDemandante() {
		return apoderadoDemandante;
	}
	public void setApoderadoDemandante(String apoderadoDemandante) {
		this.apoderadoDemandante = apoderadoDemandante;
	}
	public String getApoderadoDemandado() {
		return apoderadoDemandado;
	}
	public void setApoderadoDemandado(String apoderadoDemandado) {
		this.apoderadoDemandado = apoderadoDemandado;
	}
	public String getArbitros() {
		return arbitros;
	}
	public void setArbitros(String arbitros) {
		this.arbitros = arbitros;
	}
	public String getSecretario() {
		return secretario;
	}
	public void setSecretario(String secretario) {
		this.secretario = secretario;
	}
	public String getFechaComunicacion() {
		return fechaComunicacion;
	}
	public void setFechaComunicacion(String fechaComunicacion) {
		this.fechaComunicacion = fechaComunicacion;
	}
		
	/**
	 * @return the servicioCaso
	 */
	public String getServicioCaso() {
		return servicioCaso;
	}
	/**
	 * @param servicioCaso the servicioCaso to set
	 */
	public void setServicioCaso(String servicioCaso) {
		this.servicioCaso = servicioCaso;
	}
	
	/**
	 * @return the nombreDocumento
	 */
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	/**
	 * @param servicioCaso the servicioCaso to set
	 */
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	
	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param servicioCaso the servicioCaso to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	/**
	 * @return the abogados
	 */
	public String getAbogados() {
		return abogados;
	}
	/**
	 * @param servicioCaso the servicioCaso to set
	 */
	public void setAbogados(String abogados) {
		this.abogados = abogados;
	}

	public String getConsumo() {
		return consumo;
	}

	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}

	
}
