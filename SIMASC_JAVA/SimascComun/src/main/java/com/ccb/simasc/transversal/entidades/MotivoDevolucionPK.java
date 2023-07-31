package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class MotivoDevolucionPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_devolucion")
    
    private Long idDevolucion;       
	@Basic(optional = false)
    @NotNull
    @Column(name="motivo")
    
    private String motivo;       

	public MotivoDevolucionPK(){
		
	}

    public MotivoDevolucionPK(Long idDevolucion, String motivo) {
		this.idDevolucion = idDevolucion;       
		this.motivo = motivo;       
    }

    
	public Long getIdDevolucion(){
		return this.idDevolucion;
	}
	
	public void setIdDevolucion(Long idDevolucion){
		this.idDevolucion = idDevolucion;
	}
		
    
	public String getMotivo(){
		return this.motivo;
	}
	
	public void setMotivo(String motivo){
		this.motivo = motivo;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idDevolucion);
        hash = 37 * hash + Objects.hashCode(this.motivo);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad MotivoDevolucionPK que se pasa
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
        final MotivoDevolucionPK other = (MotivoDevolucionPK) obj;
        
        
        if (!Objects.equals(this.idDevolucion, other.idDevolucion)) {
            return false;
        }
        
        return Objects.equals(this.motivo, other.motivo);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idDevolucion");
		 cadena.append(this.idDevolucion);
	 	cadena.append(", ");
         
	     cadena.append("motivo");
		 cadena.append(this.motivo);
         
     	return cadena.toString(); 
     }

} 
