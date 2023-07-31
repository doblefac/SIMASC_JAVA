package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class DiaEjecucionPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="dia")
    
    private String dia;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_alerta")
    
    private Long idAlerta;       

	public DiaEjecucionPK(){
		
	}

    public DiaEjecucionPK(String dia, Long idAlerta) {
		this.dia = dia;       
		this.idAlerta = idAlerta;       
    }

    
	public String getDia(){
		return this.dia;
	}
	
	public void setDia(String dia){
		this.dia = dia;
	}
		
    
	public Long getIdAlerta(){
		return this.idAlerta;
	}
	
	public void setIdAlerta(Long idAlerta){
		this.idAlerta = idAlerta;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.dia);
        hash = 37 * hash + Objects.hashCode(this.idAlerta);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DiaEjecucionPK que se pasa
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
        final DiaEjecucionPK other = (DiaEjecucionPK) obj;
        
        
        if (!Objects.equals(this.dia, other.dia)) {
            return false;
        }
        
        return Objects.equals(this.idAlerta, other.idAlerta);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("dia");
		 cadena.append(this.dia);
	 	cadena.append(", ");
         
	     cadena.append("idAlerta");
		 cadena.append(this.idAlerta);
         
     	return cadena.toString(); 
     }

} 
