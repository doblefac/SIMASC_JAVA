package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ParametroDeServicioPK implements Serializable{

private static final long serialVersionUID = 1L;
	

	@Basic(optional = false)
    @NotNull
    @Column(name="nombre")
    
    private String nombre;       
	@Basic(optional = false)
    @NotNull
    @Column(name="tipo_parametro")
    
    private String tipoParametro;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_servicio")
    
    private Long idServicio;       

	public ParametroDeServicioPK(){
		
	}


    public ParametroDeServicioPK(String nombre, String tipoParametro, Long idServicio) {
		this.nombre = nombre;       
		this.tipoParametro = tipoParametro;       
		this.idServicio = idServicio;       
    }

    
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
    
	public String getTipoParametro(){
		return this.tipoParametro;
	}
	
	public void setTipoParametro(String tipoParametro){
		this.tipoParametro = tipoParametro;
	}
		
    
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.tipoParametro);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParametroDeServicioPK que se pasa
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
        final ParametroDeServicioPK other = (ParametroDeServicioPK) obj;
        
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoParametro, other.tipoParametro)) {
            return false;
        }
        
        return Objects.equals(this.idServicio, other.idServicio);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("nombre");
		 cadena.append(this.nombre);
	 	 cadena.append(", ");         
	     cadena.append("tipoParametro");
		 cadena.append(this.tipoParametro);
	 	 cadena.append(", ");
         
	     cadena.append("idServicio");
		 cadena.append(this.idServicio);
         
     	return cadena.toString(); 
     }

} 
