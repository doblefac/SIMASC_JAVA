package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReporteContenedorDTO {

	private String clave;
	private String valor;
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	@Override
	public int hashCode() {
		 int hash = 0;
	        hash += (clave != null ? clave.hashCode() : 0);
	        return hash;
	}
	@Override
	public boolean equals(Object object) {
		  if (!(object instanceof ReporteContenedorDTO)) {
	            return false;
	        }
		  ReporteContenedorDTO other = (ReporteContenedorDTO) object;
	        if ((this.clave == null && other.clave != null) || (this.clave != null && !this.clave.equals(other.clave))) {
	            return false;
	        }
	        return true;
	}
	
	
	
	
}
