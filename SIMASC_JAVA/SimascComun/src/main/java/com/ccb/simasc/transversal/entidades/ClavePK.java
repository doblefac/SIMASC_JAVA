package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ClavePK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="usuario_login")
    
    private String usuarioLogin;       
	@Basic(optional = false)
    @NotNull
    @Column(name="clave")
    
    private String clave;       

	public ClavePK(){
		
	}

    public ClavePK(String usuarioLogin, String clave) {
		this.usuarioLogin = usuarioLogin;       
		this.clave = clave;       
    }

    
	public String getUsuarioLogin(){
		return this.usuarioLogin;
	}
	
	public void setUsuarioLogin(String usuarioLogin){
		this.usuarioLogin = usuarioLogin;
	}
		
    
	public String getClave(){
		return this.clave;
	}
	
	public void setClave(String clave){
		this.clave = clave;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.usuarioLogin);
        hash = 37 * hash + Objects.hashCode(this.clave);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ClavePK que se pasa
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
        final ClavePK other = (ClavePK) obj;
        
        
        if (!Objects.equals(this.usuarioLogin, other.usuarioLogin)) {
            return false;
        }
        
        return Objects.equals(this.clave, other.clave);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("usuarioLogin");
		 cadena.append(this.usuarioLogin);
	 	cadena.append(", ");
         
	     cadena.append("clave");
		 cadena.append(this.clave);
         
     	return cadena.toString(); 
     }

} 
