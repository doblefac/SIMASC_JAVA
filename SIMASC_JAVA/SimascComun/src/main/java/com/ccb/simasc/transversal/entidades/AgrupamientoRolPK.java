package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class AgrupamientoRolPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_rol")
    
    private Long idRol;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_servicio")
    
    private Long idServicio;       
	@Basic(optional = false)
    @NotNull
    @Column(name="tipo_agrupamiento")
    
    private String tipoAgrupamiento;       

	public AgrupamientoRolPK(){
		
	}

    public AgrupamientoRolPK(Long idRol, Long idServicio, String tipoAgrupamiento) {
		this.idRol = idRol;       
		this.idServicio = idServicio;       
		this.tipoAgrupamiento = tipoAgrupamiento;       
    }

    
	public Long getIdRol(){
		return this.idRol;
	}
	
	public void setIdRol(Long idRol){
		this.idRol = idRol;
	}
		
    
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		
    
	public String getTipoAgrupamiento(){
		return this.tipoAgrupamiento;
	}
	
	public void setTipoAgrupamiento(String tipoAgrupamiento){
		this.tipoAgrupamiento = tipoAgrupamiento;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idRol);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        hash = 37 * hash + Objects.hashCode(this.tipoAgrupamiento);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AgrupamientoRolPK que se pasa
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
        final AgrupamientoRolPK other = (AgrupamientoRolPK) obj;
        
        
        if (!Objects.equals(this.idRol, other.idRol)) {
            return false;
        }
        
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        return Objects.equals(this.tipoAgrupamiento, other.tipoAgrupamiento);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idRol");
		 cadena.append(this.idRol);
	 	cadena.append(", ");
         
	     cadena.append("idServicio");
		 cadena.append(this.idServicio);
	 	cadena.append(", ");
         
	     cadena.append("tipoAgrupamiento");
		 cadena.append(this.tipoAgrupamiento);
         
     	return cadena.toString(); 
     }

} 
