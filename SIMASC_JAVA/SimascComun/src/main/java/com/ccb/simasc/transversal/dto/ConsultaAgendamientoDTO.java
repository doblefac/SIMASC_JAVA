package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto que contiene los parametros para obtener las fechas disponibles 
 * para el agendamiento de una audiencia
 * @author jnmartinez
 *
 */
@XmlRootElement
public class ConsultaAgendamientoDTO implements Serializable {

	private Long idConvenio;
	private Long idConciliador;
	private Date fechaAgendamiento;
	private String horaAgendamiento;
	private Long idCaso;
	private Long idSede;
	private String operacion;
	private boolean filtrarHoras;
	private boolean turnoUnico;
	private Date fechaInicio;
	private Date fechaFin;
	private Long idMateria;
	private Long idServicio;
	private int cantidadArbitrosSuplentes;
	private int cantidadArbitrosPrincipales;
	private String tipoAudiencia;
	
	
	
	public String getTipoAudiencia() {
		return tipoAudiencia;
	}



	public void setTipoAudiencia(String tipoAudiencia) {
		this.tipoAudiencia = tipoAudiencia;
	}



	public ConsultaAgendamientoDTO() {
		
	}

	

	public int getCantidadArbitrosSuplentes() {
		return cantidadArbitrosSuplentes;
	}



	public void setCantidadArbitrosSuplentes(int cantidadArbitrosSuplentes) {
		this.cantidadArbitrosSuplentes = cantidadArbitrosSuplentes;
	}



	public int getCantidadArbitrosPrincipales() {
		return cantidadArbitrosPrincipales;
	}



	public void setCantidadArbitrosPrincipales(int cantidadArbitrosPrincipales) {
		this.cantidadArbitrosPrincipales = cantidadArbitrosPrincipales;
	}



	public Date getFechaInicio() {
		return fechaInicio;
	}



	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}



	public Date getFechaFin() {
		return fechaFin;
	}



	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}



	public boolean isFiltrarHoras() {
		return filtrarHoras;
	}



	public void setFiltrarHoras(boolean filtrarHoras) {
		this.filtrarHoras = filtrarHoras;
	}



	public Long getIdConvenio() {
		return idConvenio;
	}


	public void setIdConvenio(Long idConvenio) {
		this.idConvenio = idConvenio;
	}


	public Long getIdConciliador() {
		return idConciliador;
	}


	public void setIdConciliador(Long idConciliador) {
		this.idConciliador = idConciliador;
	}


	public Date getFechaAgendamiento() {
		return fechaAgendamiento;
	}


	public void setFechaAgendamiento(Date fechaAgendamiento) {
		this.fechaAgendamiento = fechaAgendamiento;
	}


	public String getHoraAgendamiento() {
		return horaAgendamiento;
	}


	public void setHoraAgendamiento(String horaAgendamiento) {
		this.horaAgendamiento = horaAgendamiento;
	}


	public Long getIdCaso() {
		return idCaso;
	}


	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}


	public Long getIdSede() {
		return idSede;
	}


	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}


	public String getOperacion() {
		return operacion;
	}


	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}



	public boolean isTurnoUnico() {
		return turnoUnico;
	}



	public void setTurnoUnico(boolean turnoUnico) {
		this.turnoUnico = turnoUnico;
	}



	public Long getIdMateria() {
		return idMateria;
	}



	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}



	public Long getIdServicio() {
		return idServicio;
	}



	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	
	
}
