package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.Objects;
import java.io.Serializable;

/**

 */
@XmlRootElement
public class ReporteTranscripcionesDTO implements Serializable{	

	
	private static final long serialVersionUID = 1L;
	private String codigoCaso;
	private String nombreCaso;		
	private String auxiliar;		
	private String secretario;		
	private String minutosATranscribir;		
	private String fechaGrabacion;		
	private String fechaInicioTranscripcion;
	private String difGrabacionIniTranscripcion;
	private String fechaEntregaTranscripcion;
	private String difGrabacionEntTranscripcion;
	private String minutosPendientesTranscripcion;

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
    public ReporteTranscripcionesDTO(){
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





	public String getAuxiliar() {
		return auxiliar;
	}





	public void setAuxiliar(String auxiliar) {
		this.auxiliar = auxiliar;
	}





	public String getSecretario() {
		return secretario;
	}





	public void setSecretario(String secretario) {
		this.secretario = secretario;
	}





	public String getMinutosATranscribir() {
		return minutosATranscribir;
	}





	public void setMinutosATranscribir(String minutosATranscribir) {
		this.minutosATranscribir = minutosATranscribir;
	}





	public String getFechaGrabacion() {
		return fechaGrabacion;
	}





	public void setFechaGrabacion(String fechaGrabacion) {
		this.fechaGrabacion = fechaGrabacion;
	}





	public String getFechaInicioTranscripcion() {
		return fechaInicioTranscripcion;
	}





	public void setFechaInicioTranscripcion(String fechaInicioTranscripcion) {
		this.fechaInicioTranscripcion = fechaInicioTranscripcion;
	}





	public String getDifGrabacionIniTranscripcion() {
		return difGrabacionIniTranscripcion;
	}





	public void setDifGrabacionIniTranscripcion(String difGrabacionIniTranscripcion) {
		this.difGrabacionIniTranscripcion = difGrabacionIniTranscripcion;
	}





	public String getFechaEntregaTranscripcion() {
		return fechaEntregaTranscripcion;
	}





	public void setFechaEntregaTranscripcion(String fechaEntregaTranscripcion) {
		this.fechaEntregaTranscripcion = fechaEntregaTranscripcion;
	}





	public String getDifGrabacionEntTranscripcion() {
		return difGrabacionEntTranscripcion;
	}





	public void setDifGrabacionEntTranscripcion(String difGrabacionEntTranscripcion) {
		this.difGrabacionEntTranscripcion = difGrabacionEntTranscripcion;
	}





	public String getMinutosPendientesTranscripcion() {
		return minutosPendientesTranscripcion;
	}





	public void setMinutosPendientesTranscripcion(String minutosPendientesTranscripcion) {
		this.minutosPendientesTranscripcion = minutosPendientesTranscripcion;
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
        hash = 37 * hash + Objects.hashCode(this.auxiliar);
        hash = 37 * hash + Objects.hashCode(this.secretario);
        hash = 37 * hash + Objects.hashCode(this.minutosATranscribir);
        hash = 37 * hash + Objects.hashCode(this.fechaGrabacion);
        hash = 37 * hash + Objects.hashCode(this.fechaInicioTranscripcion);
        hash = 37 * hash + Objects.hashCode(this.difGrabacionIniTranscripcion);
        hash = 37 * hash + Objects.hashCode(this.fechaEntregaTranscripcion);
        hash = 37 * hash + Objects.hashCode(this.difGrabacionEntTranscripcion);
        
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
        final ReporteTranscripcionesDTO other = (ReporteTranscripcionesDTO) obj;
                
        if (!Objects.equals(this.codigoCaso, other.codigoCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreCaso, other.nombreCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.auxiliar, other.auxiliar)) {
            return false;
        }
        
        if (!Objects.equals(this.secretario, other.secretario)) {
            return false;
        }
        
        if (!Objects.equals(this.minutosATranscribir, other.minutosATranscribir)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaGrabacion, other.fechaGrabacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicioTranscripcion, other.fechaInicioTranscripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.difGrabacionIniTranscripcion, other.difGrabacionIniTranscripcion)) {
            return false;
        }
        if (!Objects.equals(this.fechaEntregaTranscripcion, other.fechaEntregaTranscripcion)) {
            return false;
        }
        
        return Objects.equals(this.difGrabacionEntTranscripcion, other.difGrabacionEntTranscripcion);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

