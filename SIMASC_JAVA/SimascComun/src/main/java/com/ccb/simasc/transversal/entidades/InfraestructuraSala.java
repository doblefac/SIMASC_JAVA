package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="INFRAESTRUCTURA_SALA")
@NamedQuery(name = "InfraestructuraSala.findAll", query = "SELECT p FROM InfraestructuraSala p")
public class InfraestructuraSala implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_INFRAESTRUCTURA_SALA_PK_CODIGO_RECURSO = "infraestructuraSalaPK.codigoRecurso";
			
	public static final String ENTIDAD_INFRAESTRUCTURA_SALA_PK_ID_SALA = "infraestructuraSalaPK.idSala";
	public static final String ENTIDAD_INFRAESTRUCTURA_SALA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_INFRAESTRUCTURA_SALA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_INFRAESTRUCTURA_SALA_ESTADO_REGISTRO_INFRAESTRUCTURASALA = "estadoRegistroInfraestructuraSala";			
	public static final String ENTIDAD_INFRAESTRUCTURA_SALA_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_INFRAESTRUCTURA_SALA
            = {ENTIDAD_INFRAESTRUCTURA_SALA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_INFRAESTRUCTURA_SALA_ESTADO_REGISTRO, ENTIDAD_INFRAESTRUCTURA_SALA_PK_ID_SALA, ENTIDAD_INFRAESTRUCTURA_SALA_PK_CODIGO_RECURSO, ENTIDAD_INFRAESTRUCTURA_SALA_ID_USUARIO_MODIFICACION};

	@EmbeddedId
	private InfraestructuraSalaPK infraestructuraSalaPK;
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="codigo_recurso", referencedColumnName="codigo_recurso", insertable = false, updatable = false)
    private Infraestructura infraestructura;
		
	@ManyToOne
	@JoinColumn(name="id_sala", referencedColumnName="id_sala", insertable = false, updatable = false)
    private Sala sala;
		
	
	
    public InfraestructuraSala(){
		infraestructuraSalaPK = new InfraestructuraSalaPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public InfraestructuraSalaPK getInfraestructuraSalaPK(){
		return this.infraestructuraSalaPK;
	}
	
	public void setInfraestructuraSalaPK(InfraestructuraSalaPK infraestructuraSalaPK){
		this.infraestructuraSalaPK   = infraestructuraSalaPK ;
	}  
	
	public String getIdUsuarioModificacion(){
		return this.idUsuarioModificacion;
	}
	
	public void setIdUsuarioModificacion(String idUsuarioModificacion){
		this.idUsuarioModificacion = idUsuarioModificacion;
	}
		
	public Date getFechaUltimaModificacion(){
		return this.fechaUltimaModificacion;
	}
	
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion){
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
		
	public String getEstadoRegistro(){
		return this.estadoRegistro;
	}
	
	public void setEstadoRegistro(String estadoRegistro){
		this.estadoRegistro = estadoRegistro;
	}
		

    public Infraestructura getInfraestructura(){
		return this.infraestructura; 
	}
	
	public void setInfraestructura(Infraestructura infraestructura){
		this.infraestructura = infraestructura;
	}
    public Sala getSala(){
		return this.sala; 
	}
	
	public void setSala(Sala sala){
		this.sala = sala;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_INFRAESTRUCTURA_SALA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.infraestructuraSalaPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad InfraestructuraSala que se pasa
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
        final InfraestructuraSala other = (InfraestructuraSala) obj;
        
        if (!Objects.equals(this.infraestructuraSalaPK, other.infraestructuraSalaPK)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

