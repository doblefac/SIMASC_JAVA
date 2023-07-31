package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ValorHonorariosActorPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_honorarios_fijados")
    
    private Long idHonorariosFijados;       
	@Basic(optional = false)
    @NotNull
    @Column(name="tipo_actor")
    
    private String tipoActor;       

	public ValorHonorariosActorPK(){
		
	}

    public ValorHonorariosActorPK(Long idHonorariosFijados, String tipoActor) {
		this.idHonorariosFijados = idHonorariosFijados;       
		this.tipoActor = tipoActor;       
    }

    
	public Long getIdHonorariosFijados(){
		return this.idHonorariosFijados;
	}
	
	public void setIdHonorariosFijados(Long idHonorariosFijados){
		this.idHonorariosFijados = idHonorariosFijados;
	}
		
    
	public String getTipoActor(){
		return this.tipoActor;
	}
	
	public void setTipoActor(String tipoActor){
		this.tipoActor = tipoActor;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idHonorariosFijados);
        hash = 37 * hash + Objects.hashCode(this.tipoActor);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ValorHonorariosActorPK que se pasa
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
        final ValorHonorariosActorPK other = (ValorHonorariosActorPK) obj;
        
        
        if (!Objects.equals(this.idHonorariosFijados, other.idHonorariosFijados)) {
            return false;
        }
        
        return Objects.equals(this.tipoActor, other.tipoActor);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idHonorariosFijados");
		 cadena.append(this.idHonorariosFijados);
	 	cadena.append(", ");
         
	     cadena.append("tipoActor");
		 cadena.append(this.tipoActor);
         
     	return cadena.toString(); 
     }

} 
