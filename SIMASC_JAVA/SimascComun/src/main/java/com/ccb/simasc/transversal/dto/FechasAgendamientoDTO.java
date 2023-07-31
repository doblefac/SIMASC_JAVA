package com.ccb.simasc.transversal.dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FechasAgendamientoDTO {

	private Date fecha;
	private List<FormatoHoraAudienciaDTO> horas;
	
	public FechasAgendamientoDTO() {
		// TODO Auto-generated constructor stub
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<FormatoHoraAudienciaDTO> getHoras() {
		return horas;
	}

	public void setHoras(List<FormatoHoraAudienciaDTO> horas) {
		this.horas = horas;
	}
	
	
}
