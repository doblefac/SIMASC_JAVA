

package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.Objects;
import java.io.Serializable;

/**
 
 */
@XmlRootElement
public class ReporteAspirantesDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

	private String nombres;		
	private String identificacion;		
	private String reqCumplidos;
	private String reqNoCumplidos;

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
    public ReporteAspirantesDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }
	

	

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getReqCumplidos() {
		return reqCumplidos;
	}

	public void setReqCumplidos(String reqCumplidos) {
		this.reqCumplidos = reqCumplidos;
	}

	public String getReqNoCumplidos() {
		return reqNoCumplidos;
	}

	public void setReqNoCumplidos(String reqNoCumplidos) {
		this.reqNoCumplidos = reqNoCumplidos;
	}




	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;            
        hash = 37 * hash + Objects.hashCode(this.nombres); 
        hash = 37 * hash + Objects.hashCode(this.reqCumplidos);
        hash = 37 * hash + Objects.hashCode(this.identificacion);   
        hash = 37 * hash + Objects.hashCode(this.reqNoCumplidos);      
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
        final ReporteAspirantesDTO other = (ReporteAspirantesDTO) obj;
        
       
        if (!Objects.equals(this.nombres, other.nombres)) {
        	return false;
        }
        
    
        if (!Objects.equals(this.reqCumplidos, other.reqCumplidos)) {
        	return false;
        }
        
        
        if (!Objects.equals(this.identificacion, other.identificacion)) {
        	return false;
        }
        
        return Objects.equals(this.reqNoCumplidos, other.reqNoCumplidos);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 


