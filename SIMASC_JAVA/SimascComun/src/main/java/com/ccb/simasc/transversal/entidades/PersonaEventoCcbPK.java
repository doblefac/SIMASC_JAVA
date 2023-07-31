package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class PersonaEventoCcbPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_evento_ccb")
    
    private Long idEventoCcb;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_persona")
    
    private Long idPersona;       

	public PersonaEventoCcbPK(){
		
	}

    public PersonaEventoCcbPK(Long idEventoCcb, Long idPersona) {
		this.idEventoCcb = idEventoCcb;       
		this.idPersona = idPersona;       
    }

    
	public Long getIdEventoCcb(){
		return this.idEventoCcb;
	}
	
	public void setIdEventoCcb(Long idEventoCcb){
		this.idEventoCcb = idEventoCcb;
	}
		
    
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idEventoCcb);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PersonaEventoCcbPK que se pasa
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
        final PersonaEventoCcbPK other = (PersonaEventoCcbPK) obj;
        
        
        if (!Objects.equals(this.idEventoCcb, other.idEventoCcb)) {
            return false;
        }
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idEventoCcb");
		 cadena.append(this.idEventoCcb);
	 	cadena.append(", ");
         
	     cadena.append("idPersona");
		 cadena.append(this.idPersona);
         
     	return cadena.toString(); 
     }

} 
