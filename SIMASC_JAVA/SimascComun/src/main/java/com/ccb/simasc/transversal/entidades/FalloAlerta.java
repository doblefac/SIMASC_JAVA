package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery; 
import javax.persistence.Table;
import javax.persistence.Temporal;
import java.util.Date;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="FALLO_ALERTA")
@NamedQuery(name = "FalloAlerta.findAll", query = "SELECT p FROM FalloAlerta p")
public class FalloAlerta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_FALLO_ALERTA_PK = "idFalloAlerta";
	public static final String ENTIDAD_FALLO_ALERTA_ESTADO = "estado";
	public static final String ENTIDAD_FALLO_ALERTA_FECHA_FALLO = "fechaFallo";
	public static final String ENTIDAD_FALLO_ALERTA_ID_PROGRAMACION_ALERTA = "idProgramacionAlerta";
	public static final String ENTIDAD_FALLO_ALERTA_ID_ALERTA = "idAlerta";
    private static final String[] ATRIBUTOS_ENTIDAD_FALLO_ALERTA
            = {ENTIDAD_FALLO_ALERTA_ID_ALERTA, ENTIDAD_FALLO_ALERTA_ID_PROGRAMACION_ALERTA, ENTIDAD_FALLO_ALERTA_FECHA_FALLO, ENTIDAD_FALLO_ALERTA_PK, ENTIDAD_FALLO_ALERTA_ESTADO};

	@Id
    @Column(name="id_fallo_alerta")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idFalloAlerta;
    
	@Column(name="estado")
	private String estado;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_fallo")
	private Date fechaFallo;		
    
	@Column(name="id_programacion_alerta")
	private Long idProgramacionAlerta;		
    
	@Column(name="id_alerta")
	private Long idAlerta;		

	
	
    public FalloAlerta(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdFalloAlerta(){
		return this.idFalloAlerta;
	}
	
	public void setIdFalloAlerta(Long idFalloAlerta){
		this.idFalloAlerta = idFalloAlerta;
	}
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Date getFechaFallo(){
		return this.fechaFallo;
	}
	
	public void setFechaFallo(Date fechaFallo){
		this.fechaFallo = fechaFallo;
	}
		
	public Long getIdProgramacionAlerta(){
		return this.idProgramacionAlerta;
	}
	
	public void setIdProgramacionAlerta(Long idProgramacionAlerta){
		this.idProgramacionAlerta = idProgramacionAlerta;
	}
		
	public Long getIdAlerta(){
		return this.idAlerta;
	}
	
	public void setIdAlerta(Long idAlerta){
		this.idAlerta = idAlerta;
	}
		


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_FALLO_ALERTA) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idFalloAlerta);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.fechaFallo);
        hash = 37 * hash + Objects.hashCode(this.idProgramacionAlerta);
        hash = 37 * hash + Objects.hashCode(this.idAlerta);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad FalloAlerta que se pasa
     * como parametro comprobando que comparten los mismos valores en cada uno
     * de sus atributos. Solo se tienen en cuenta los atributos simples, es
     * decir, se omiten aquellos que definen una relacion con otra tabla.
     *
     * @param obj Instancia de la categoría a comprobar
     * iguales.
     * @return Verdadero si esta instancia y la que se pasan como parametros son
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FalloAlerta other = (FalloAlerta) obj;
        
        if (!Objects.equals(this.idFalloAlerta, other.idFalloAlerta)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFallo, other.fechaFallo)) {
            return false;
        }
        
        if (!Objects.equals(this.idProgramacionAlerta, other.idProgramacionAlerta)) {
            return false;
        }
        
        return Objects.equals(this.idAlerta, other.idAlerta);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

