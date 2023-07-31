

package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.Objects;
import java.io.Serializable;

/**

 * @author 
 */
@XmlRootElement
public class ReporteRepartoPorAbogadoDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipo;
	private String codigoCaso;
	private String nombreCaso;		
	private String estadoCaso;		
	private String fechaRadicacionCaso;
	private String fechaAudienciaDesignacion;	
	private String numeroDiasRadicacionDesignacion;
	private String indicadorCumplimiento;
	private String fechaAudienciaInstalacion;
	private String nombreAbogado;
	private String consumo;
	private String solicitaAmparo;
	private String medidasCautelares;


	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
    public ReporteRepartoPorAbogadoDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }

    public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public String getEstadoCaso() {
		return estadoCaso;
	}

	public void setEstadoCaso(String estadoCaso) {
		this.estadoCaso = estadoCaso;
	}

	public String getFechaRadicacionCaso() {
		return fechaRadicacionCaso;
	}

	public void setFechaRadicacionCaso(String fechaRadicacionCaso) {
		this.fechaRadicacionCaso = fechaRadicacionCaso;
	}

	public String getFechaAudienciaDesignacion() {
		return fechaAudienciaDesignacion;
	}

	public void setFechaAudienciaDesignacion(String fechaAudienciaDesignacion) {
		this.fechaAudienciaDesignacion = fechaAudienciaDesignacion;
	}

	public String getNumeroDiasRadicacionDesignacion() {
		return numeroDiasRadicacionDesignacion;
	}

	public void setNumeroDiasRadicacionDesignacion(String numeroDiasRadicacionDesignacion) {
		this.numeroDiasRadicacionDesignacion = numeroDiasRadicacionDesignacion;
	}

	public String getIndicadorCumplimiento() {
		return indicadorCumplimiento;
	}

	public void setIndicadorCumplimiento(String indicadorCumplimiento) {
		this.indicadorCumplimiento = indicadorCumplimiento;
	}

	public String getFechaAudienciaInstalacion() {
		return fechaAudienciaInstalacion;
	}

	public void setFechaAudienciaInstalacion(String fechaAudienciaInstalacion) {
		this.fechaAudienciaInstalacion = fechaAudienciaInstalacion;
	}

	public String getNombreAbogado() {
		return nombreAbogado;
	}

	public void setNombreAbogado(String nombreAbogado) {
		this.nombreAbogado = nombreAbogado;
	}
	public String getConsumo() {
		return consumo;
	}

	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}

	public String getSolicitaAmparo() {
		return solicitaAmparo;
	}

	public void setSolicitaAmparo(String solicitaAmparo) {
		this.solicitaAmparo = solicitaAmparo;
	}

	public String getMedidasCautelares() {
		return medidasCautelares;
	}

	public void setMedidasCautelares(String medidasCautelares) {
		this.medidasCautelares = medidasCautelares;
	}

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.tipo);  
        hash = 37 * hash + Objects.hashCode(this.codigoCaso);        
        hash = 37 * hash + Objects.hashCode(this.nombreCaso);
        hash = 37 * hash + Objects.hashCode(this.estadoCaso);
        hash = 37 * hash + Objects.hashCode(this.fechaRadicacionCaso);
        hash = 37 * hash + Objects.hashCode(this.fechaAudienciaDesignacion);
        hash = 37 * hash + Objects.hashCode(this.numeroDiasRadicacionDesignacion);
        hash = 37 * hash + Objects.hashCode(this.indicadorCumplimiento);
        hash = 37 * hash + Objects.hashCode(this.fechaAudienciaInstalacion);
        hash = 37 * hash + Objects.hashCode(this.nombreAbogado);
        hash = 37 * hash + Objects.hashCode(this.consumo);
        hash = 37 * hash + Objects.hashCode(this.solicitaAmparo);
        hash = 37 * hash + Objects.hashCode(this.medidasCautelares);
        
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
        final ReporteRepartoPorAbogadoDTO other = (ReporteRepartoPorAbogadoDTO) obj;
        
        
        if (!Objects.equals(this.codigoCaso, other.codigoCaso)) {
            return false;
        }
        if (!Objects.equals(this.nombreCaso, other.nombreCaso)) {
        	return false;
        }
        
        if (!Objects.equals(this.estadoCaso, other.estadoCaso)) {
        	return false;
        }
        
        if (!Objects.equals(this.fechaRadicacionCaso, other.fechaRadicacionCaso)) {
        	return false;
        }
        if (!Objects.equals(this.fechaAudienciaDesignacion, other.fechaAudienciaDesignacion)) {
        	return false;
        }        
        
        if (!Objects.equals(this.numeroDiasRadicacionDesignacion, other.numeroDiasRadicacionDesignacion)) {
        	return false;
        }
        
        if (!Objects.equals(this.indicadorCumplimiento,
        		other.indicadorCumplimiento)
        		) {
        	return false;
        }
        if (!Objects.equals(this.fechaAudienciaInstalacion, other.fechaAudienciaInstalacion)) {
        	return false;
        }
        if (!Objects.equals(this.consumo, other.consumo)) {
        	return false;
        }
        if (!Objects.equals(this.solicitaAmparo, other.solicitaAmparo)) {
        	return false;
        }
        if (!Objects.equals(this.medidasCautelares, other.medidasCautelares)) {
        	return false;
        }
  
        
        return Objects.equals(this.nombreAbogado, other.nombreAbogado);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 


