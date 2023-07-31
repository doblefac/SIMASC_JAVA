package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.Objects;
import java.io.Serializable;

/**

 */
@XmlRootElement
public class ReporteAudienciaDTO implements Serializable{	

	
	private static final long serialVersionUID = 1L;
	private String codigoCaso;
	private String nombreCaso;		
	private String fechaRadicacionCaso;		
	private String tipoAudiencia;		
	private String fechaAudiencia;		
	private String nombreSecretario;		
	private String nombreArbitro;
	private String abogado;
	private String estado;
	private String observaciones;
	private String consumo;
	private String servicio;

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
    public ReporteAudienciaDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }




	public String getCodigoCaso() {
		return codigoCaso;
	}




	public void setCodigoCaso(String codigoCaso) {
		this.codigoCaso = codigoCaso;
	}




	public String getNombreCaso() {
		return nombreCaso;
	}




	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}




	public String getFechaRadicacionCaso() {
		return fechaRadicacionCaso;
	}




	public void setFechaRadicacionCaso(String fechaRadicacionCaso) {
		this.fechaRadicacionCaso = fechaRadicacionCaso;
	}




	public String getTipoAudiencia() {
		return tipoAudiencia;
	}




	public void setTipoAudiencia(String tipoAudiencia) {
		this.tipoAudiencia = tipoAudiencia;
	}




	public String getFechaAudiencia() {
		return fechaAudiencia;
	}




	public void setFechaAudiencia(String fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}




	public String getNombreSecretario() {
		return nombreSecretario;
	}




	public void setNombreSecretario(String nombreSecretario) {
		this.nombreSecretario = nombreSecretario;
	}




	public String getNombreArbitro() {
		return nombreArbitro;
	}




	public void setNombreArbitro(String nombreArbitro) {
		this.nombreArbitro = nombreArbitro;
	}




	public String getAbogado() {
		return abogado;
	}




	public void setAbogado(String abogado) {
		this.abogado = abogado;
	}




	public String getEstado() {
		return estado;
	}




	public void setEstado(String estado) {
		this.estado = estado;
	}




	public String getObservaciones() {
		return observaciones;
	}




	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	


	public String getConsumo() {
		return consumo;
	}




	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}
	
	


	public String getServicio() {
		return servicio;
	}




	public void setServicio(String servicio) {
		this.servicio = servicio;
	}




	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.codigoCaso);        
        hash = 37 * hash + Objects.hashCode(this.nombreCaso);
        hash = 37 * hash + Objects.hashCode(this.fechaRadicacionCaso);
        hash = 37 * hash + Objects.hashCode(this.tipoAudiencia);
        hash = 37 * hash + Objects.hashCode(this.fechaAudiencia);
        hash = 37 * hash + Objects.hashCode(this.nombreSecretario);
        hash = 37 * hash + Objects.hashCode(this.nombreArbitro);
        hash = 37 * hash + Objects.hashCode(this.abogado);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.consumo);
        hash = 37 * hash + Objects.hashCode(this.servicio);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CasoDTO que se pasa
     * como parámetro comprobando que comparten los mismos valores en cada uno
     * de sus atributos. Solo se tienen en cuenta los atributos simples, es
     * decir, se omiten aquellos que definen una relación con otra tabla.
     *
     * @param obj Instancia de la categoría a comprobar
     * iguales.
     * @return Verdadero si esta instancia y la que se pasan como parámetros son
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReporteAudienciaDTO other = (ReporteAudienciaDTO) obj;
                
        if (!Objects.equals(this.codigoCaso, other.codigoCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreCaso, other.nombreCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaRadicacionCaso, other.fechaRadicacionCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoAudiencia, other.tipoAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaAudiencia, other.fechaAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreSecretario, other.nombreSecretario)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreArbitro, other.nombreArbitro)) {
            return false;
        }
        
        if (!Objects.equals(this.abogado, other.abogado)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.consumo, other.consumo)) {
            return false;
        }
        if (!Objects.equals(this.servicio, other.servicio)) {
            return false;
        }
        
        return Objects.equals(this.observaciones, other.observaciones);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

