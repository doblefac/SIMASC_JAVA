package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class RequisitoListaPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_lista")
    
    private Long idLista;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_requisito")
    
    private Long idRequisito;       

	public RequisitoListaPK(){
		
	}

    public RequisitoListaPK(Long idLista, Long idRequisito) {
		this.idLista = idLista;       
		this.idRequisito = idRequisito;       
    }

    
	public Long getIdLista(){
		return this.idLista;
	}
	
	public void setIdLista(Long idLista){
		this.idLista = idLista;
	}
		
    
	public Long getIdRequisito(){
		return this.idRequisito;
	}
	
	public void setIdRequisito(Long idRequisito){
		this.idRequisito = idRequisito;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idLista);
        hash = 37 * hash + Objects.hashCode(this.idRequisito);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RequisitoListaPK que se pasa
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
        final RequisitoListaPK other = (RequisitoListaPK) obj;
        
        
        if (!Objects.equals(this.idLista, other.idLista)) {
            return false;
        }
        
        return Objects.equals(this.idRequisito, other.idRequisito);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idLista");
		 cadena.append(this.idLista);
	 	cadena.append(", ");
         
	     cadena.append("idRequisito");
		 cadena.append(this.idRequisito);
         
     	return cadena.toString(); 
     }

} 
