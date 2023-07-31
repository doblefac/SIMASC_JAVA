

package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.Objects;
import java.io.Serializable;
import java.math.BigDecimal;

/**

 * @author 
 */
@XmlRootElement
public class ReporteCasosPorArbitroDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String consecutivo;
	private BigDecimal codigoCaso;
	private String nombreCaso;		
	private String fechaRadicacionCaso;
	private String tipoCaso;
	private String estadoCaso;		
	private String cuantia;
	private String materia;
	private String arbitro;
	private String tipoNombramiento;
	private String fechaDesignacion;	
	private String fechaComunicacion;	
	private String pronunciamiento;
	private String fechaPronunciamiento;
	private String consumo;
	private String solicitaAmparo;
	private String concedeAmparo;

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
    public ReporteCasosPorArbitroDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public BigDecimal getCodigoCaso() {
		return codigoCaso;
	}

	public void setCodigoCaso(BigDecimal codigoCaso) {
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

	public String getTipoCaso() {
		return tipoCaso;
	}

	public void setTipoCaso(String tipoCaso) {
		this.tipoCaso = tipoCaso;
	}

	public String getEstadoCaso() {
		return estadoCaso;
	}

	public void setEstadoCaso(String estadoCaso) {
		this.estadoCaso = estadoCaso;
	}

	public String getCuantia() {
		return cuantia;
	}

	public void setCuantia(String cuantia) {
		this.cuantia = cuantia;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getArbitro() {
		return arbitro;
	}

	public void setArbitro(String arbitro) {
		this.arbitro = arbitro;
	}

	public String getTipoNombramiento() {
		return tipoNombramiento;
	}

	public void setTipoNombramiento(String tipoNombramiento) {
		this.tipoNombramiento = tipoNombramiento;
	}

	public String getFechaDesignacion() {
		return fechaDesignacion;
	}

	public void setFechaDesignacion(String fechaDesignacion) {
		this.fechaDesignacion = fechaDesignacion;
	}

	public String getFechaComunicacion() {
		return fechaComunicacion;
	}

	public void setFechaComunicacion(String fechaComunicacion) {
		this.fechaComunicacion = fechaComunicacion;
	}

	public String getPronunciamiento() {
		return pronunciamiento;
	}

	public void setPronunciamiento(String pronunciamiento) {
		this.pronunciamiento = pronunciamiento;
	}

	public String getFechaPronunciamiento() {
		return fechaPronunciamiento;
	}

	public void setFechaPronunciamiento(String fechaPronunciamiento) {
		this.fechaPronunciamiento = fechaPronunciamiento;
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

	public String getConcedeAmparo() {
		return concedeAmparo;
	}

	public void setConcedeAmparo(String concedeAmparo) {
		this.concedeAmparo = concedeAmparo;
	}
	

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.consecutivo);
        hash = 37 * hash + Objects.hashCode(this.codigoCaso);        
        hash = 37 * hash + Objects.hashCode(this.nombreCaso);
        hash = 37 * hash + Objects.hashCode(this.fechaRadicacionCaso);
        hash = 37 * hash + Objects.hashCode(this.tipoCaso);
        hash = 37 * hash + Objects.hashCode(this.estadoCaso);
        hash = 37 * hash + Objects.hashCode(this.cuantia);
        hash = 37 * hash + Objects.hashCode(this.materia);
        hash = 37 * hash + Objects.hashCode(this.arbitro);
        hash = 37 * hash + Objects.hashCode(this.tipoNombramiento);
        hash = 37 * hash + Objects.hashCode(this.fechaDesignacion);
        hash = 37 * hash + Objects.hashCode(this.fechaComunicacion);
        hash = 37 * hash + Objects.hashCode(this.pronunciamiento);
        hash = 37 * hash + Objects.hashCode(this.fechaPronunciamiento);
        hash = 37 * hash + Objects.hashCode(this.consumo);
        hash = 37 * hash + Objects.hashCode(this.solicitaAmparo);
        hash = 37 * hash + Objects.hashCode(this.concedeAmparo);
        
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
        final ReporteCasosPorArbitroDTO other = (ReporteCasosPorArbitroDTO) obj;
        
        if (!Objects.equals(this.consecutivo, other.consecutivo)) {
            return false;
        }
        
        if (!Objects.equals(this.codigoCaso, other.codigoCaso)) {
            return false;
        }
        if (!Objects.equals(this.nombreCaso, other.nombreCaso)) {
        	return false;
        }
        
        if (!Objects.equals(this.fechaRadicacionCaso, other.fechaRadicacionCaso)) {
        	return false;
        }
        
        if (!Objects.equals(this.tipoCaso, other.tipoCaso)) {
        	return false;
        }
        
        
        if (!Objects.equals(this.estadoCaso, other.estadoCaso)) {
        	return false;
        }
        
        if (!Objects.equals(this.cuantia, other.cuantia)) {
        	return false;
        }
        if (!Objects.equals(this.materia,
        		other.materia)
        		) {
        	return false;
        }
        if (!Objects.equals(this.arbitro, other.arbitro)) {
        	return false;
        }
        
        if (!Objects.equals(this.tipoNombramiento, other.tipoNombramiento)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDesignacion, other.fechaDesignacion)) {
            return false;
        }
        if (!Objects.equals(this.fechaComunicacion, other.fechaComunicacion)) {
            return false;
        } 
        
        if (!Objects.equals(this.pronunciamiento, other.pronunciamiento)) {
            return false;
        }
        
		if (!Objects.equals(this.consumo, other.consumo)) {
            return false;
        }

		if (!Objects.equals(this.solicitaAmparo, other.solicitaAmparo)) {
            return false;
        }

		if (!Objects.equals(this.concedeAmparo, other.concedeAmparo)) {
            return false;
        }

        return Objects.equals(this.fechaPronunciamiento, other.fechaPronunciamiento);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 


