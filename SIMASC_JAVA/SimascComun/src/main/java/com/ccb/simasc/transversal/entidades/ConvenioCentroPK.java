package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ConvenioCentroPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_servicio")
    
    private Long idServicio;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_materia")
    
    private Long idMateria;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_convenio")
    
    private Long idConvenio;       

	public ConvenioCentroPK(){
		
	}

    public ConvenioCentroPK(Long idServicio, Long idMateria, Long idConvenio) {
		this.idServicio = idServicio;       
		this.idMateria = idMateria;       
		this.idConvenio = idConvenio;       
    }

    
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		
    
	public Long getIdMateria(){
		return this.idMateria;
	}
	
	public void setIdMateria(Long idMateria){
		this.idMateria = idMateria;
	}
		
    
	public Long getIdConvenio(){
		return this.idConvenio;
	}
	
	public void setIdConvenio(Long idConvenio){
		this.idConvenio = idConvenio;
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
        hash = 37 * hash + Objects.hashCode(this.idMateria);
        hash = 37 * hash + Objects.hashCode(this.idConvenio);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ConvenioCentroPK que se pasa
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
        final ConvenioCentroPK other = (ConvenioCentroPK) obj;
        
        
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.idMateria, other.idMateria)) {
            return false;
        }
        
        return Objects.equals(this.idConvenio, other.idConvenio);
                
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
         
	     cadena.append("idMateria");
		 cadena.append(this.idMateria);
	 	cadena.append(", ");
         
	     cadena.append("idConvenio");
		 cadena.append(this.idConvenio);
         
     	return cadena.toString(); 
     }

} 
