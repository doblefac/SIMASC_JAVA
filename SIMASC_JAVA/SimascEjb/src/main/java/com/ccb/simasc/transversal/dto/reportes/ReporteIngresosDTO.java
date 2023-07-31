

package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.Objects;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO que contiene la información del reporte de ingresos
 * 
 * @author aalvarez
 */
@XmlRootElement
public class ReporteIngresosDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String nombreCaso;		
	private String secretario;
	private String tipoParte;		
	private String telefono;
	private BigDecimal valorFijado;
	private String fechaDeFijacion;	
	private String fechaLimiteDePago;	
	private BigDecimal valorPagado;
	private String fechaDePago;

	
    public ReporteIngresosDTO(){
    }


	public String getNombreCaso() {
		return nombreCaso;
	}


	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}


	public String getSecretario() {
		return secretario;
	}


	public void setSecretario(String secretario) {
		this.secretario = secretario;
	}


	public String getTipoParte() {
		return tipoParte;
	}


	public void setTipoParte(String tipoParte) {
		this.tipoParte = tipoParte;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public BigDecimal getValorFijado() {
		return valorFijado;
	}


	public void setValorFijado(BigDecimal valorFijado) {
		this.valorFijado = valorFijado;
	}


	public String getFechaDeFijacion() {
		return fechaDeFijacion;
	}


	public void setFechaDeFijacion(String fechaDeFijacion) {
		this.fechaDeFijacion = fechaDeFijacion;
	}


	public String getFechaLimiteDePago() {
		return fechaLimiteDePago;
	}


	public void setFechaLimiteDePago(String fechaLimiteDePago) {
		this.fechaLimiteDePago = fechaLimiteDePago;
	}


	public BigDecimal getValorPagado() {
		return valorPagado;
	}


	public void setValorPagado(BigDecimal valorPagado) {
		this.valorPagado = valorPagado;
	}


	public String getFechaDePago() {
		return fechaDePago;
	}


	public void setFechaDePago(String fechaDePago) {
		this.fechaDePago = fechaDePago;
	}


	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
         
        hash = 37 * hash + Objects.hashCode(this.nombreCaso);    
        hash = 37 * hash + Objects.hashCode(this.secretario);
        hash = 37 * hash + Objects.hashCode(this.tipoParte);      
        hash = 37 * hash + Objects.hashCode(this.telefono);
        hash = 37 * hash + Objects.hashCode(this.valorFijado);
        hash = 37 * hash + Objects.hashCode(this.fechaDeFijacion);
        hash = 37 * hash + Objects.hashCode(this.fechaLimiteDePago);
        hash = 37 * hash + Objects.hashCode(this.valorPagado);
        hash = 37 * hash + Objects.hashCode(this.fechaDePago);
        
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
        final ReporteIngresosDTO other = (ReporteIngresosDTO) obj;
        
      
        if (!Objects.equals(this.nombreCaso, other.nombreCaso)) {
        	return false;
        }
     
        if (!Objects.equals(this.secretario, other.secretario)) {
        	return false;
        }
        
        
        if (!Objects.equals(this.tipoParte, other.tipoParte)) {
        	return false;
        }
             
        if (!Objects.equals(this.telefono,
        		other.telefono)
        		) {
        	return false;
        }
        if (!Objects.equals(this.valorFijado, other.valorFijado)) {
        	return false;
        }
        
        if (!Objects.equals(this.fechaDeFijacion, other.fechaDeFijacion)) {
            return false;
        }
        if (!Objects.equals(this.fechaLimiteDePago, other.fechaLimiteDePago)) {
            return false;
        } 
        
        if (!Objects.equals(this.valorPagado, other.valorPagado)) {
            return false;
        }
        
        return Objects.equals(this.fechaDePago, other.fechaDePago);
                
    }

} 


