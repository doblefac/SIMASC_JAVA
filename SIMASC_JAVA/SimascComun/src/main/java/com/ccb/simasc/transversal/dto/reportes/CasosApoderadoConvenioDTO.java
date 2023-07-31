package com.ccb.simasc.transversal.dto.reportes;

import java.util.Date;

/**
 * DTO necesario para manejar la informacion de la consulta consultarCasosApoderadoConvenio del manejador de reportes
 * para el caso de uso CON-F-040
 * @author prendon
 *
 */
public class CasosApoderadoConvenioDTO {
	
	private Long idCaso;
	private String nombreCaso;
	private String casoHechos;
	private Date horaAudiencia;
	private String nombreSede;
	private String nombreConciliador;
	private String nombreApoderado;
	private String nombreConvenio;
	
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	public String getCasoHechos() {
		return casoHechos;
	}
	public void setCasoHechos(String casoHechos) {
		this.casoHechos = casoHechos;
	}
	public Date getHoraAudiencia() {
		return horaAudiencia;
	}
	public void setHoraAudiencia(Date horaAudiencia) {
		this.horaAudiencia = horaAudiencia;
	}
	public String getNombreSede() {
		return nombreSede;
	}
	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}
	public String getNombreConciliador() {
		return nombreConciliador;
	}
	public void setNombreConciliador(String nombreConciliador) {
		this.nombreConciliador = nombreConciliador;
	}
	public String getNombreApoderado() {
		return nombreApoderado;
	}
	public void setNombreApoderado(String nombreApoderado) {
		this.nombreApoderado = nombreApoderado;
	}
	public String getNombreConvenio() {
		return nombreConvenio;
	}
	public void setNombreConvenio(String nombreConvenio) {
		this.nombreConvenio = nombreConvenio;
	}
}

