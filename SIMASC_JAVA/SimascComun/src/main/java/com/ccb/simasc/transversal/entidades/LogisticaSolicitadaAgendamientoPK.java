package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class LogisticaSolicitadaAgendamientoPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_agendamiento")
    
    private Long idAgendamiento;       
	@Basic(optional = false)
    @NotNull
    @Column(name="codigo_logistica")
    
    private String codigoLogistica;       

	public LogisticaSolicitadaAgendamientoPK(){
		
	}

    public LogisticaSolicitadaAgendamientoPK(Long idAgendamiento, String codigoLogistica) {
		this.idAgendamiento = idAgendamiento;       
		this.codigoLogistica = codigoLogistica;       
    }

    
	public Long getIdAgendamiento(){
		return this.idAgendamiento;
	}
	
	public void setIdAgendamiento(Long idAgendamiento){
		this.idAgendamiento = idAgendamiento;
	}
		
    
	public String getCodigoLogistica(){
		return this.codigoLogistica;
	}
	
	public void setCodigoLogistica(String codigoLogistica){
		this.codigoLogistica = codigoLogistica;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idAgendamiento);
        hash = 37 * hash + Objects.hashCode(this.codigoLogistica);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad LogisticaSolicitadaAgendamientoPK que se pasa
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
        final LogisticaSolicitadaAgendamientoPK other = (LogisticaSolicitadaAgendamientoPK) obj;
        
        
        if (!Objects.equals(this.idAgendamiento, other.idAgendamiento)) {
            return false;
        }
        
        return Objects.equals(this.codigoLogistica, other.codigoLogistica);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idAgendamiento");
		 cadena.append(this.idAgendamiento);
	 	cadena.append(", ");
         
	     cadena.append("codigoLogistica");
		 cadena.append(this.codigoLogistica);
         
     	return cadena.toString(); 
     }

} 
