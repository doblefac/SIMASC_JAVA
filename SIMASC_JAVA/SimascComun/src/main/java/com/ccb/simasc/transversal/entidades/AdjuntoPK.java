package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class AdjuntoPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_documento")
    
    private Long idDocumento;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_correo_rol_persona_caso")
    
    private Long idCorreoRolPersonaCaso;       

	public AdjuntoPK(){
		
	}

    public AdjuntoPK(Long idDocumento, Long idCorreoRolPersonaCaso) {
		this.idDocumento = idDocumento;       
		this.idCorreoRolPersonaCaso = idCorreoRolPersonaCaso;       
    }

    
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
		
    
	public Long getIdCorreoRolPersonaCaso(){
		return this.idCorreoRolPersonaCaso;
	}
	
	public void setIdCorreoRolPersonaCaso(Long idCorreoRolPersonaCaso){
		this.idCorreoRolPersonaCaso = idCorreoRolPersonaCaso;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        hash = 37 * hash + Objects.hashCode(this.idCorreoRolPersonaCaso);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AdjuntoPK que se pasa
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
        final AdjuntoPK other = (AdjuntoPK) obj;
        
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        
        return Objects.equals(this.idCorreoRolPersonaCaso, other.idCorreoRolPersonaCaso);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idDocumento");
		 cadena.append(this.idDocumento);
	 	cadena.append(", ");
         
	     cadena.append("idCorreoRolPersonaCaso");
		 cadena.append(this.idCorreoRolPersonaCaso);
         
     	return cadena.toString(); 
     }

} 
