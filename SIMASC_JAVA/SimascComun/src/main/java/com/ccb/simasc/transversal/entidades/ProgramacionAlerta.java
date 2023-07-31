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
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import java.util.Date;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="PROGRAMACION_ALERTA")
@NamedQuery(name = "ProgramacionAlerta.findAll", query = "SELECT p FROM ProgramacionAlerta p")
public class ProgramacionAlerta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PROGRAMACION_ALERTA_PK = "idProgramacionAlerta";
	public static final String ENTIDAD_PROGRAMACION_ALERTA_ID_CASO = "idCaso";
	public static final String ENTIDAD_PROGRAMACION_ALERTA_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_PROGRAMACION_ALERTA_FECHA_EJECUCION = "fechaEjecucion";
	public static final String ENTIDAD_PROGRAMACION_ALERTA_ESTADO = "estado";
	public static final String ENTIDAD_PROGRAMACION_ALERTA_ID_ALERTA = "idAlerta";
	public static final String ENTIDAD_PROGRAMACION_ALERTA_ID_DOCUMENTO = "idDocumento";
    private static final String[] ATRIBUTOS_ENTIDAD_PROGRAMACION_ALERTA
            = {ENTIDAD_PROGRAMACION_ALERTA_FECHA_EJECUCION, ENTIDAD_PROGRAMACION_ALERTA_PK, ENTIDAD_PROGRAMACION_ALERTA_ESTADO, ENTIDAD_PROGRAMACION_ALERTA_ID_CASO, ENTIDAD_PROGRAMACION_ALERTA_ID_PERSONA, ENTIDAD_PROGRAMACION_ALERTA_ID_DOCUMENTO, ENTIDAD_PROGRAMACION_ALERTA_ID_ALERTA};

	@Id
    @Column(name="id_programacion_alerta")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idProgramacionAlerta;
    
	@Column(name="id_caso")
	private Long idCaso;		
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ejecucion")
	private Date fechaEjecucion;		
    
	@Column(name="estado")
	private String estado;		
    
	@Column(name="id_alerta")
	private Long idAlerta;	
	
	@Column(name="id_documento")
	private Long idDocumento;	

	@ManyToOne
	@JoinColumn(name="id_alerta", referencedColumnName="id_alerta", insertable = false, updatable = false)
    private Alerta alerta;
		
	
	
    public ProgramacionAlerta(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdProgramacionAlerta(){
		return this.idProgramacionAlerta;
	}
	
	public void setIdProgramacionAlerta(Long idProgramacionAlerta){
		this.idProgramacionAlerta = idProgramacionAlerta;
	}
	
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Date getFechaEjecucion(){
		return this.fechaEjecucion;
	}
	
	public void setFechaEjecucion(Date fechaEjecucion){
		this.fechaEjecucion = fechaEjecucion;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Long getIdAlerta(){
		return this.idAlerta;
	}
	
	public void setIdAlerta(Long idAlerta){
		this.idAlerta = idAlerta;
	}
		

    public Alerta getAlerta(){
		return this.alerta; 
	}
	
	public void setAlerta(Alerta alerta){
		this.alerta = alerta;
	}	

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PROGRAMACION_ALERTA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idProgramacionAlerta);        
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.fechaEjecucion);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.idAlerta);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ProgramacionAlerta que se pasa
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
        final ProgramacionAlerta other = (ProgramacionAlerta) obj;
        
        if (!Objects.equals(this.idProgramacionAlerta, other.idProgramacionAlerta)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaEjecucion, other.fechaEjecucion)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        
        return Objects.equals(this.idAlerta, other.idAlerta);
                
    }



	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

