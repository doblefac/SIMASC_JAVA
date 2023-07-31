package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="INFRAESTRUCTURA")
@NamedQuery(name = "Infraestructura.findAll", query = "SELECT p FROM Infraestructura p")
public class Infraestructura implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_INFRAESTRUCTURA_PK = "codigoRecurso";
	public static final String ENTIDAD_INFRAESTRUCTURA_NOMBRE = "nombre";
	public static final String ENTIDAD_INFRAESTRUCTURA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_INFRAESTRUCTURA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_INFRAESTRUCTURA_ESTADO_REGISTRO_INFRAESTRUCTURA = "estadoRegistroInfraestructura";			
	public static final String ENTIDAD_INFRAESTRUCTURA_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_INFRAESTRUCTURA
            = {ENTIDAD_INFRAESTRUCTURA_ESTADO_REGISTRO, ENTIDAD_INFRAESTRUCTURA_PK, ENTIDAD_INFRAESTRUCTURA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_INFRAESTRUCTURA_ID_USUARIO_MODIFICACION, ENTIDAD_INFRAESTRUCTURA_NOMBRE};

	@Id
    @Column(name="codigo_recurso")
	private String codigoRecurso;
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@OneToMany(mappedBy="infraestructura")
    private List<InfraestructuraSala> infraestructuraSalaList;
	@OneToMany(mappedBy="infraestructura")
    private List<InfraestructuraSolicitadaAgendamiento> infraestructuraSolicitadaAgendamientoList;
	
	
    public Infraestructura(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public String getCodigoRecurso(){
		return this.codigoRecurso;
	}
	
	public void setCodigoRecurso(String codigoRecurso){
		this.codigoRecurso = codigoRecurso;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
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
		

    public List<InfraestructuraSala> getInfraestructuraSalaList(){
		return this.infraestructuraSalaList;
	}
	
	public void setInfraestructuraSalaList(List<InfraestructuraSala> infraestructuraSalaList){
		this.infraestructuraSalaList = infraestructuraSalaList;
	}
			
    public List<InfraestructuraSolicitadaAgendamiento> getInfraestructuraSolicitadaAgendamientoList(){
		return this.infraestructuraSolicitadaAgendamientoList;
	}
	
	public void setInfraestructuraSolicitadaAgendamientoList(List<InfraestructuraSolicitadaAgendamiento> infraestructuraSolicitadaAgendamientoList){
		this.infraestructuraSolicitadaAgendamientoList = infraestructuraSolicitadaAgendamientoList;
	}
			

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_INFRAESTRUCTURA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.codigoRecurso);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Infraestructura que se pasa
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
        final Infraestructura other = (Infraestructura) obj;
        
        if (!Objects.equals(this.codigoRecurso, other.codigoRecurso)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
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

