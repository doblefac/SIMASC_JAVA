package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class InfraestructuraSalaPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="codigo_recurso")
    
    private String codigoRecurso;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_sala")
    
    private Long idSala;       

	public InfraestructuraSalaPK(){
		
	}

    public InfraestructuraSalaPK(String codigoRecurso, Long idSala) {
		this.codigoRecurso = codigoRecurso;       
		this.idSala = idSala;       
    }

    
	public String getCodigoRecurso(){
		return this.codigoRecurso;
	}
	
	public void setCodigoRecurso(String codigoRecurso){
		this.codigoRecurso = codigoRecurso;
	}
		
    
	public Long getIdSala(){
		return this.idSala;
	}
	
	public void setIdSala(Long idSala){
		this.idSala = idSala;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.codigoRecurso);
        hash = 37 * hash + Objects.hashCode(this.idSala);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad InfraestructuraSalaPK que se pasa
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
        final InfraestructuraSalaPK other = (InfraestructuraSalaPK) obj;
        
        
        if (!Objects.equals(this.codigoRecurso, other.codigoRecurso)) {
            return false;
        }
        
        return Objects.equals(this.idSala, other.idSala);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("codigoRecurso");
		 cadena.append(this.codigoRecurso);
	 	cadena.append(", ");
         
	     cadena.append("idSala");
		 cadena.append(this.idSala);
         
     	return cadena.toString(); 
     }

} 
