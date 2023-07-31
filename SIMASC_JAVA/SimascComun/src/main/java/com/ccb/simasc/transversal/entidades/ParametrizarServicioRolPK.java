package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ParametrizarServicioRolPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_servicio")
    
    private Long idServicio;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_rol")
    
    private Long idRol;       

	public ParametrizarServicioRolPK(){
		
	}

    public ParametrizarServicioRolPK(Long idServicio, Long idRol) {
		this.idServicio = idServicio;       
		this.idRol = idRol;       
    }

    
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		
    
	public Long getIdRol(){
		return this.idRol;
	}
	
	public void setIdRol(Long idRol){
		this.idRol = idRol;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        hash = 37 * hash + Objects.hashCode(this.idRol);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParametrizarServicioRolPK que se pasa
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
        final ParametrizarServicioRolPK other = (ParametrizarServicioRolPK) obj;
        
        
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        return Objects.equals(this.idRol, other.idRol);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idServicio");
		 cadena.append(this.idServicio);
	 	cadena.append(", ");
         
	     cadena.append("idRol");
		 cadena.append(this.idRol);
         
     	return cadena.toString(); 
     }

} 
