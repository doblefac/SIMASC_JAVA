package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class AcusePK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_correo_rol_persona_caso")
    
    private Long idCorreoRolPersonaCaso;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_documento")
    
    private Long idDocumento;       
	@Basic(optional = false)
    @NotNull
    @Column(name="tipo")
    
    private String tipo;       

	public AcusePK(){
		
	}

    public AcusePK(Long idCorreoRolPersonaCaso, Long idDocumento, String tipo) {
		this.idCorreoRolPersonaCaso = idCorreoRolPersonaCaso;       
		this.idDocumento = idDocumento;       
		this.tipo = tipo;       
    }

    
	public Long getIdCorreoRolPersonaCaso(){
		return this.idCorreoRolPersonaCaso;
	}
	
	public void setIdCorreoRolPersonaCaso(Long idCorreoRolPersonaCaso){
		this.idCorreoRolPersonaCaso = idCorreoRolPersonaCaso;
	}
		
    
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
		
    
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idCorreoRolPersonaCaso);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AcusePK que se pasa
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
        final AcusePK other = (AcusePK) obj;
        
        
        if (!Objects.equals(this.idCorreoRolPersonaCaso, other.idCorreoRolPersonaCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        
        return Objects.equals(this.tipo, other.tipo);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idCorreoRolPersonaCaso");
		 cadena.append(this.idCorreoRolPersonaCaso);
	 	cadena.append(", ");
         
	     cadena.append("idDocumento");
		 cadena.append(this.idDocumento);
	 	cadena.append(", ");
         
	     cadena.append("tipo");
		 cadena.append(this.tipo);
         
     	return cadena.toString(); 
     }

} 
