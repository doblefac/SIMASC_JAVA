package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PreseleccionadoDesignadoDTO  implements Serializable{	

	private static final long serialVersionUID = 1L;
	private Long idPersona;
	private Long idCaso;
	private String nombreCompleto;	
	private String tipoPreseleccion;	
	private boolean designadoPreviamente;
	
    public PreseleccionadoDesignadoDTO(){
    }

    public boolean isDesignadoPreviamente() {
		return designadoPreviamente;
	}
    
	public void setDesignadoPreviamente(boolean designadoPreviamente) {
		this.designadoPreviamente = designadoPreviamente;
	}


	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public String getTipoPreseleccion() {
		return tipoPreseleccion;
	}
	public void setTipoPreseleccion(String tipoPreseleccion) {
		this.tipoPreseleccion = tipoPreseleccion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (designadoPreviamente ? 1231 : 1237);
		result = prime * result + ((idCaso == null) ? 0 : idCaso.hashCode());
		result = prime * result + ((idPersona == null) ? 0 : idPersona.hashCode());
		result = prime * result + ((nombreCompleto == null) ? 0 : nombreCompleto.hashCode());
		result = prime * result + ((tipoPreseleccion == null) ? 0 : tipoPreseleccion.hashCode());
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
		PreseleccionadoDesignadoDTO other = (PreseleccionadoDesignadoDTO) obj;
		if (designadoPreviamente != other.designadoPreviamente)
			return false;
		if (idCaso == null) {
			if (other.idCaso != null)
				return false;
		} else if (!idCaso.equals(other.idCaso))
			return false;
		if (idPersona == null) {
			if (other.idPersona != null)
				return false;
		} else if (!idPersona.equals(other.idPersona))
			return false;
		if (nombreCompleto == null) {
			if (other.nombreCompleto != null)
				return false;
		} else if (!nombreCompleto.equals(other.nombreCompleto))
			return false;
		if (tipoPreseleccion == null) {
			if (other.tipoPreseleccion != null)
				return false;
		} else if (!tipoPreseleccion.equals(other.tipoPreseleccion))
			return false;
		return true;
	}
	
}
