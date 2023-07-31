package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.Objects;
import java.io.Serializable;

/**

 */
@XmlRootElement
public class ReporteSalasOcupadasDTO implements Serializable{	

	
	private static final long serialVersionUID = 1L;
	private String sede;
	private String numeroSala;
	private String nombreCaso;		
	private String codigoCaso;		
	private String fechaAudiencia;				
	private String horaInicial;		
	private String horaFinal;
	private String arbitros;
	private String secretario;
	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
    public ReporteSalasOcupadasDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }
    
    

	public String getSede() {
		return sede;
	}



	public void setSede(String sede) {
		this.sede = sede;
	}



	public String getNumeroSala() {
		return numeroSala;
	}



	public void setNumeroSala(String numeroSala) {
		this.numeroSala = numeroSala;
	}



	public String getNombreCaso() {
		return nombreCaso;
	}



	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}



	public String getCodigoCaso() {
		return codigoCaso;
	}



	public void setCodigoCaso(String codigoCaso) {
		this.codigoCaso = codigoCaso;
	}



	public String getFechaAudiencia() {
		return fechaAudiencia;
	}



	public void setFechaAudiencia(String fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}



	public String getHoraInicial() {
		return horaInicial;
	}



	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}



	public String getHoraFinal() {
		return horaFinal;
	}



	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}



	public String getArbitros() {
		return arbitros;
	}



	public void setArbitros(String arbitros) {
		this.arbitros = arbitros;
	}



	public String getSecretario() {
		return secretario;
	}



	public void setSecretario(String secretario) {
		this.secretario = secretario;
	}



	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.sede);
        hash = 37 * hash + Objects.hashCode(this.numeroSala);
        hash = 37 * hash + Objects.hashCode(this.nombreCaso);
        hash = 37 * hash + Objects.hashCode(this.codigoCaso);        
        hash = 37 * hash + Objects.hashCode(this.fechaAudiencia);
        hash = 37 * hash + Objects.hashCode(this.horaInicial);
        hash = 37 * hash + Objects.hashCode(this.horaFinal);
        hash = 37 * hash + Objects.hashCode(this.arbitros);
        hash = 37 * hash + Objects.hashCode(this.secretario);
        
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
        
        final ReporteSalasOcupadasDTO other = (ReporteSalasOcupadasDTO) obj;
        if (!Objects.equals(this.sede, other.sede)) {
        	return false;
        }
        
        if (!Objects.equals(this.numeroSala, other.numeroSala)) {
            return false;
        }
                
        if (!Objects.equals(this.nombreCaso, other.nombreCaso)) {
        	return false;
        }
        if (!Objects.equals(this.codigoCaso, other.codigoCaso)) {
            return false;
        }
        
     
        
        if (!Objects.equals(this.fechaAudiencia, other.fechaAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.horaInicial, other.horaInicial)) {
            return false;
        }
        
        if (!Objects.equals(this.horaFinal, other.horaFinal)) {
            return false;
        }
        
        if (!Objects.equals(this.arbitros, other.arbitros)) {
            return false;
        }
              
        return Objects.equals(this.secretario, other.secretario);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

