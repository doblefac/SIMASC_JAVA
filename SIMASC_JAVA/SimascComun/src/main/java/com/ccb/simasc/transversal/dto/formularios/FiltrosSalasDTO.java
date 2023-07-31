package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;
import java.util.List;

import com.ccb.simasc.transversal.dto.InfraestructuraDTO;
import com.ccb.simasc.transversal.dto.InfraestructuraSalaDTO;

/**
 * DAO que contene la información para la solicitud de la sala
 * Este formulario esta construido para los servicios REST
 * 
 * @author dpachon
 */
public class FiltrosSalasDTO {
	
	private Long idSede;
	private Long idSala;
	private String nombreSala;
	private Date fechaSolicitud;
	private Date horaInicio;
	private Date horaFin;	
	private List<InfraestructuraSalaDTO> infraestructuraSalaDTO;
	private List<InfraestructuraDTO> infraestructuraDTO; 
	private List<String> infraestructuras; 
	
	public Long getIdSede() {
		return idSede;
	}
	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}
	
	public String getNombreSala() {
		return nombreSala;
	}
	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public List<InfraestructuraSalaDTO> getInfraestructuraSalaDTO() {
		return infraestructuraSalaDTO;
	}
	public void setInfraestructuraSalaDTO(List<InfraestructuraSalaDTO> infraestructuraSalaDTO) {
		this.infraestructuraSalaDTO = infraestructuraSalaDTO;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Date getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}
	public Long getIdSala() {
		return idSala;
	}
	public void setIdSala(Long idSala) {
		this.idSala = idSala;
	}
	public List<InfraestructuraDTO> getInfraestructuraDTO() {
		return infraestructuraDTO;
	}
	public void setInfraestructuraDTO(List<InfraestructuraDTO> infraestructuraDTO) {
		this.infraestructuraDTO = infraestructuraDTO;
	}
	public List<String> getInfraestructuras() {
		return infraestructuras;
	}
	public void setInfraestructuras(List<String> infraestructuras) {
		this.infraestructuras = infraestructuras;
	}
}
