package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class CuotaPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="numero_cuota")
    
    private Long numeroCuota;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_resultado_audiencia")
    
    private Long idResultadoAudiencia;       
	@Basic(optional = false)
    @NotNull
    @Column(name="tipo_obligacion")
    
    private String tipoObligacion;       

	public CuotaPK(){
		
	}

    public CuotaPK(Long numeroCuota, Long idResultadoAudiencia, String tipoObligacion) {
		this.numeroCuota = numeroCuota;       
		this.idResultadoAudiencia = idResultadoAudiencia;       
		this.tipoObligacion = tipoObligacion;       
    }

    
	public Long getNumeroCuota(){
		return this.numeroCuota;
	}
	
	public void setNumeroCuota(Long numeroCuota){
		this.numeroCuota = numeroCuota;
	}
		
    
	public Long getIdResultadoAudiencia(){
		return this.idResultadoAudiencia;
	}
	
	public void setIdResultadoAudiencia(Long idResultadoAudiencia){
		this.idResultadoAudiencia = idResultadoAudiencia;
	}
		
    
	public String getTipoObligacion(){
		return this.tipoObligacion;
	}
	
	public void setTipoObligacion(String tipoObligacion){
		this.tipoObligacion = tipoObligacion;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.numeroCuota);
        hash = 37 * hash + Objects.hashCode(this.idResultadoAudiencia);
        hash = 37 * hash + Objects.hashCode(this.tipoObligacion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CuotaPK que se pasa
     * como parametro comprobando que comparten los mismos valores en cada uno
     * de sus atributos. Solo se tienen en cuenta los atributos simples, es
     * decir, se omiten aquellos que definen una relacion con otra tabla.
     *
     * @param obj Instancia de la categoría a comprobar
     * @return Verdadero si esta instancia y la que se pasan como parametros son
     * iguales.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CuotaPK other = (CuotaPK) obj;
        
        
        if (!Objects.equals(this.numeroCuota, other.numeroCuota)) {
            return false;
        }
        
        if (!Objects.equals(this.idResultadoAudiencia, other.idResultadoAudiencia)) {
            return false;
        }
        
        return Objects.equals(this.tipoObligacion, other.tipoObligacion);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("numeroCuota");
		 cadena.append(this.numeroCuota);
	 	cadena.append(", ");
         
	     cadena.append("idResultadoAudiencia");
		 cadena.append(this.idResultadoAudiencia);
	 	cadena.append(", ");
         
	     cadena.append("tipoObligacion");
		 cadena.append(this.tipoObligacion);
         
     	return cadena.toString(); 
     }

} 
