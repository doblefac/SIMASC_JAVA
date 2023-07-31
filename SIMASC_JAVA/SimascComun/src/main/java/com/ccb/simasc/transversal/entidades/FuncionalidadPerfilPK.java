package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class FuncionalidadPerfilPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_perfil")
    
    private Long idPerfil;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_funcionalidad")
    
    private Long idFuncionalidad;       

	public FuncionalidadPerfilPK(){
		
	}

    public FuncionalidadPerfilPK(Long idPerfil, Long idFuncionalidad) {
		this.idPerfil = idPerfil;       
		this.idFuncionalidad = idFuncionalidad;       
    }

    
	public Long getIdPerfil(){
		return this.idPerfil;
	}
	
	public void setIdPerfil(Long idPerfil){
		this.idPerfil = idPerfil;
	}
		
    
	public Long getIdFuncionalidad(){
		return this.idFuncionalidad;
	}
	
	public void setIdFuncionalidad(Long idFuncionalidad){
		this.idFuncionalidad = idFuncionalidad;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idPerfil);
        hash = 37 * hash + Objects.hashCode(this.idFuncionalidad);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad FuncionalidadPerfilPK que se pasa
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
        final FuncionalidadPerfilPK other = (FuncionalidadPerfilPK) obj;
        
        
        if (!Objects.equals(this.idPerfil, other.idPerfil)) {
            return false;
        }
        
        return Objects.equals(this.idFuncionalidad, other.idFuncionalidad);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idPerfil");
		 cadena.append(this.idPerfil);
	 	cadena.append(", ");
         
	     cadena.append("idFuncionalidad");
		 cadena.append(this.idFuncionalidad);
         
     	return cadena.toString(); 
     }

} 
