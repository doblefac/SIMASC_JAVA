package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO que contiene informacion del caso para el analista de conciliacion
 * (CONF-103).
 * 
 * @author aperez.
 *
 */
public class InformacionCasoDTO implements Serializable {

	private static final long serialVersionUID = -5759500187699973359L;
	
	private Long idCaso;
	private Long idConvenio;
	private Long tipoServicio;
	private Long idCentro;
	
	private String nombreCaso;
	private String tipoCuantia;
	private String cuantia;
	private String nombreConciliador;
	private String nombreConvenio;
	private Long idMateria;
	private String nombreMateria;
	private String nombreSede;
	private String motivoNoReparto;
	
	private Date horaInicioAudiencia;
	private Date horaFinAudiencia;
	private Date fechaAudienciaSolicitud;
	private FechasAgendamientoDTO fechasAgendamientoDTO;
	private String duracionAudiencias;
	private String nombreServicio;
	
	
	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public Date getHoraInicioAudiencia() {
		return horaInicioAudiencia;
	}

	public void setHoraInicioAudiencia(Date horaInicioAudiencia) {
		this.horaInicioAudiencia = horaInicioAudiencia;
	}

	public Date getHoraFinAudiencia() {
		return horaFinAudiencia;
	}

	public void setHoraFinAudiencia(Date horaFinAudiencia) {
		this.horaFinAudiencia = horaFinAudiencia;
	}

	public Date getFechaAudienciaSolicitud() {
		return fechaAudienciaSolicitud;
	}

	public void setFechaAudienciaSolicitud(Date fechaAudienciaSolicitud) {
		this.fechaAudienciaSolicitud = fechaAudienciaSolicitud;
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
	 * @return the idConvenio
	 */
	public Long getIdConvenio() {
		return idConvenio;
	}

	/**
	 * @param idConvenio the idConvenio to set
	 */
	public void setIdConvenio(Long idConvenio) {
		this.idConvenio = idConvenio;
	}
	
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
	 * @return the tipoServicio
	 */
	public Long getTipoServicio() {
		return tipoServicio;
	}

	/**
	 * @param tipoServicio the tipoServicio to set
	 */
	public void setTipoServicio(Long tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	/**
	 * @return the nombreCaso
	 */
	public String getNombreCaso() {
		return nombreCaso;
	}

	/**
	 * @param nombreCaso the nombreCaso to set
	 */
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}

	/**
	 * @return the cuantia
	 */
	public String getCuantia() {
		return cuantia;
	}

	/**
	 * @param cuantia the cuantia to set
	 */
	public void setCuantia(String cuantia) {
		this.cuantia = cuantia;
	}

	/**
	 * @return the nombreConciliador
	 */
	public String getNombreConciliador() {
		return nombreConciliador;
	}

	/**
	 * @param nombreConciliador the nombreConciliador to set
	 */
	public void setNombreConciliador(String nombreConciliador) {
		this.nombreConciliador = nombreConciliador;
	}

	/**
	 * @return the nombreConvenio
	 */
	public String getNombreConvenio() {
		return nombreConvenio;
	}

	/**
	 * @param nombreConvenio the nombreConvenio to set
	 */
	public void setNombreConvenio(String nombreConvenio) {
		this.nombreConvenio = nombreConvenio;
	}

	/**
	 * @return the nombreMateria
	 */
	public String getNombreMateria() {
		return nombreMateria;
	}

	/**
	 * @param nombreMateria the nombreMateria to set
	 */
	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	/**
	 * @return the nombreSede
	 */
	public String getNombreSede() {
		return nombreSede;
	}

	/**
	 * @param nombreSede the nombreSede to set
	 */
	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}

	/**
	 * @return the motivoNoReparto
	 */
	public String getMotivoNoReparto() {
		return motivoNoReparto;
	}

	/**
	 * @param motivoNoReparto the motivoNoReparto to set
	 */
	public void setMotivoNoReparto(String motivoNoReparto) {
		this.motivoNoReparto = motivoNoReparto;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuantia == null) ? 0 : cuantia.hashCode());
		result = prime * result + ((idCaso == null) ? 0 : idCaso.hashCode());
		result = prime * result + ((idConvenio == null) ? 0 : idConvenio.hashCode());
		result = prime * result + ((motivoNoReparto == null) ? 0 : motivoNoReparto.hashCode());
		result = prime * result + ((nombreCaso == null) ? 0 : nombreCaso.hashCode());
		result = prime * result + ((nombreConciliador == null) ? 0 : nombreConciliador.hashCode());
		result = prime * result + ((nombreConvenio == null) ? 0 : nombreConvenio.hashCode());
		result = prime * result + ((nombreMateria == null) ? 0 : nombreMateria.hashCode());
		result = prime * result + ((nombreSede == null) ? 0 : nombreSede.hashCode());
		result = prime * result + ((tipoServicio == null) ? 0 : tipoServicio.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformacionCasoDTO other = (InformacionCasoDTO) obj;
		if (cuantia == null) {
			if (other.cuantia != null)
				return false;
		} else if (!cuantia.equals(other.cuantia))
			return false;
		if (idCaso == null) {
			if (other.idCaso != null)
				return false;
		} else if (!idCaso.equals(other.idCaso))
			return false;
		if (idConvenio == null) {
			if (other.idConvenio != null)
				return false;
		} else if (!idConvenio.equals(other.idConvenio))
			return false;
		if (motivoNoReparto == null) {
			if (other.motivoNoReparto != null)
				return false;
		} else if (!motivoNoReparto.equals(other.motivoNoReparto))
			return false;
		if (nombreCaso == null) {
			if (other.nombreCaso != null)
				return false;
		} else if (!nombreCaso.equals(other.nombreCaso))
			return false;
		if (nombreConciliador == null) {
			if (other.nombreConciliador != null)
				return false;
		} else if (!nombreConciliador.equals(other.nombreConciliador))
			return false;
		if (nombreConvenio == null) {
			if (other.nombreConvenio != null)
				return false;
		} else if (!nombreConvenio.equals(other.nombreConvenio))
			return false;
		if (nombreMateria == null) {
			if (other.nombreMateria != null)
				return false;
		} else if (!nombreMateria.equals(other.nombreMateria))
			return false;
		if (nombreSede == null) {
			if (other.nombreSede != null)
				return false;
		} else if (!nombreSede.equals(other.nombreSede))
			return false;
		if (tipoServicio == null) {
			if (other.tipoServicio != null)
				return false;
		} else if (!tipoServicio.equals(other.tipoServicio))
			return false;
		return true;
	}

	/**
	 * @return the tipoCuantia
	 */
	public String getTipoCuantia() {
		return tipoCuantia;
	}

	/**
	 * @param tipoCuantia the tipoCuantia to set
	 */
	public void setTipoCuantia(String tipoCuantia) {
		this.tipoCuantia = tipoCuantia;
	}

	/**
	 * @return the fechasAgendamientoDTO
	 */
	public FechasAgendamientoDTO getFechasAgendamientoDTO() {
		return fechasAgendamientoDTO;
	}

	/**
	 * @param fechasAgendamientoDTO the fechasAgendamientoDTO to set
	 */
	public void setFechasAgendamientoDTO(FechasAgendamientoDTO fechasAgendamientoDTO) {
		this.fechasAgendamientoDTO = fechasAgendamientoDTO;
	}

	/**
	 * @return the duracionAudiencias
	 */
	public String getDuracionAudiencias() {
		return duracionAudiencias;
	}

	/**
	 * @param duracionAudiencias the duracionAudiencias to set
	 */
	public void setDuracionAudiencias(String duracionAudiencias) {
		this.duracionAudiencias = duracionAudiencias;
	}

	public Long getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}
	
	
	
}
