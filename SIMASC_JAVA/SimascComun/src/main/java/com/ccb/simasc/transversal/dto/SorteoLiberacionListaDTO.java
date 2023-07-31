package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class SorteoLiberacionListaDTO implements Serializable{	

	private static final long serialVersionUID = 7179764052906356502L;

	private Long idSorteo;
	private String servicio;		
	private String materia;		
	private String tipoLista;
	private Date fechaRealizacion;	
	private Long idCaso;
	private String nombreCaso;
	private int numeroOperadoresLiberados;
	private String consumo;
	private String tipoSorteo;
	
	
    public SorteoLiberacionListaDTO(){}


	public String getServicio() {
		return servicio;
	}


	public void setServicio(String servicio) {
		this.servicio = servicio;
	}


	public String getMateria() {
		return materia;
	}


	public void setMateria(String materia) {
		this.materia = materia;
	}


	public String getTipoLista() {
		return tipoLista;
	}


	public void setTipoLista(String tipoLista) {
		this.tipoLista = tipoLista;
	}


	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}


	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}


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


	public int getNumeroOperadoresLiberados() {
		return numeroOperadoresLiberados;
	}


	public void setNumeroOperadoresLiberados(int numeroOperadoresLiberados) {
		this.numeroOperadoresLiberados = numeroOperadoresLiberados;
	}


	public Long getIdSorteo() {
		return idSorteo;
	}


	public void setIdSorteo(Long idSorteo) {
		this.idSorteo = idSorteo;
	}
	

	public String getConsumo() {
		return consumo;
	}

	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaRealizacion == null) ? 0 : fechaRealizacion.hashCode());
		result = prime * result + ((idCaso == null) ? 0 : idCaso.hashCode());
		result = prime * result + ((idSorteo == null) ? 0 : idSorteo.hashCode());
		result = prime * result + ((materia == null) ? 0 : materia.hashCode());
		result = prime * result + ((nombreCaso == null) ? 0 : nombreCaso.hashCode());
		result = prime * result + numeroOperadoresLiberados;
		result = prime * result + ((servicio == null) ? 0 : servicio.hashCode());
		result = prime * result + ((tipoLista == null) ? 0 : tipoLista.hashCode());
		result = prime * result + ((consumo == null) ? 0 : consumo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SorteoLiberacionListaDTO other = (SorteoLiberacionListaDTO) obj;
		if (fechaRealizacion == null) {
			if (other.fechaRealizacion != null)
				return false;
		} else if (!fechaRealizacion.equals(other.fechaRealizacion))
			return false;
		if (idCaso == null) {
			if (other.idCaso != null)
				return false;
		} else if (!idCaso.equals(other.idCaso))
			return false;
		if (idSorteo == null) {
			if (other.idSorteo != null)
				return false;
		} else if (!idSorteo.equals(other.idSorteo))
			return false;
		if (materia == null) {
			if (other.materia != null)
				return false;
		} else if (!materia.equals(other.materia))
			return false;
		if (nombreCaso == null) {
			if (other.nombreCaso != null)
				return false;
		} else if (!nombreCaso.equals(other.nombreCaso))
			return false;
		if (numeroOperadoresLiberados != other.numeroOperadoresLiberados)
			return false;
		if (servicio == null) {
			if (other.servicio != null)
				return false;
		} else if (!servicio.equals(other.servicio))
			return false;
		if (tipoLista == null) {
			if (other.tipoLista != null)
				return false;
		} else if (!tipoLista.equals(other.tipoLista)){
			return false;
		} else if (!consumo.equals(other.consumo))
			return false;
		return true;
	}


	public String getTipoSorteo() {
		return tipoSorteo;
	}


	public void setTipoSorteo(String tipoSorteo) {
		this.tipoSorteo = tipoSorteo;
	}	
}
