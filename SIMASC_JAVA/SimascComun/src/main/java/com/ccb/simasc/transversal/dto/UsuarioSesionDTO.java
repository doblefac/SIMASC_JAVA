package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;


// protected region imports dto end


/**
 * DAO que contiene la informacion del token recibido
 * 
 * @author 
 */
@XmlRootElement
public class UsuarioSesionDTO implements Serializable{	

	private String iss;		
	private Long idPersona;
	private String nombrePersona;	
		
	
    public UsuarioSesionDTO(){
		
    }


	public String getIss() {
		return iss;
	}


	public void setIss(String iss) {
		this.iss = iss;
	}


	public Long getIdPersona() {
		return idPersona;
	}


	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}


	public String getNombrePersona() {
		return nombrePersona;
	}


	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPersona == null) ? 0 : idPersona.hashCode());
		result = prime * result + ((iss == null) ? 0 : iss.hashCode());
		result = prime * result + ((nombrePersona == null) ? 0 : nombrePersona.hashCode());
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
		UsuarioSesionDTO other = (UsuarioSesionDTO) obj;
		if (idPersona == null) {
			if (other.idPersona != null)
				return false;
		} else if (!idPersona.equals(other.idPersona))
			return false;		
		if (iss == null) {
			if (other.iss != null)
				return false;
		} else if (!iss.equals(other.iss))
			return false;
		if (nombrePersona == null) {
			if (other.nombrePersona != null)
				return false;
		} else if (!nombrePersona.equals(other.nombrePersona))
			return false;
		return true;
	}

}
