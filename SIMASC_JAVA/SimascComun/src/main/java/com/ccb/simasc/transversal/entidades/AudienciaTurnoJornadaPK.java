package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class AudienciaTurnoJornadaPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_audiencia")    
    private Long idAudiencia;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_turno_jornada")
    
    private Long idTurnoJornada;       

	public AudienciaTurnoJornadaPK(){
		
	}

    public AudienciaTurnoJornadaPK(Long idAudiencia, Long idTurnoJornada) {
		this.idAudiencia = idAudiencia;       
		this.idTurnoJornada = idTurnoJornada;       
    }

    
	public Long getIdAudiencia(){
		return this.idAudiencia;
	}
	
	public void setIdAudiencia(Long idAudiencia){
		this.idAudiencia = idAudiencia;
	}
		
    
	public Long getIdTurnoJornada(){
		return this.idTurnoJornada;
	}
	
	public void setIdTurnoJornada(Long idTurnoJornada){
		this.idTurnoJornada = idTurnoJornada;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);
        hash = 37 * hash + Objects.hashCode(this.idTurnoJornada);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AudienciaTurnoJornadaPK que se pasa
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
        final AudienciaTurnoJornadaPK other = (AudienciaTurnoJornadaPK) obj;
        
        
        if (!Objects.equals(this.idAudiencia, other.idAudiencia)) {
            return false;
        }
        
        return Objects.equals(this.idTurnoJornada, other.idTurnoJornada);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idAudiencia");
		 cadena.append(this.idAudiencia);
	 	 cadena.append(", ");
         
	     cadena.append("idTurnoJornada");
		 cadena.append(this.idTurnoJornada);
         
     	return cadena.toString(); 
     }

} 
