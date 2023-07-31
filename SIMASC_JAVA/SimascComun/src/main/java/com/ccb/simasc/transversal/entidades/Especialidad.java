package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="ESPECIALIDAD")
@NamedQuery(name = "Especialidad.findAll", query = "SELECT p FROM Especialidad p")
public class Especialidad implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_ESPECIALIDAD_PK = "idEspecialidad";
	public static final String ENTIDAD_ESPECIALIDAD_ID_MATERIA = "idMateria";
	public static final String ENTIDAD_ESPECIALIDAD_NOMBRE = "nombre";
	public static final String ENTIDAD_ESPECIALIDAD_DESCRIPCION = "descripcion";
	public static final String ENTIDAD_ESPECIALIDAD_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_ESPECIALIDAD_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_ESPECIALIDAD_ESTADO_REGISTRO_ESPECIALIDAD = "estadoRegistroEspecialidad";			
	public static final String ENTIDAD_ESPECIALIDAD_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_ESPECIALIDAD
            = {ENTIDAD_ESPECIALIDAD_ID_MATERIA, ENTIDAD_ESPECIALIDAD_ESTADO_REGISTRO, ENTIDAD_ESPECIALIDAD_PK, ENTIDAD_ESPECIALIDAD_NOMBRE, ENTIDAD_ESPECIALIDAD_FECHA_ULTIMA_MODIFICACION, ENTIDAD_ESPECIALIDAD_ID_USUARIO_MODIFICACION, ENTIDAD_ESPECIALIDAD_DESCRIPCION};

	@Id
    @Column(name="id_especialidad")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idEspecialidad;
    
	@Column(name="id_materia")
	private Long idMateria;		
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="descripcion")
	private String descripcion;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_materia", referencedColumnName="id_materia", insertable = false, updatable = false)
    private Materia materia;
		
	@OneToMany(mappedBy="especialidad")
    private List<PersonaServicioMateria> personaServicioMateriaList;
	
	
    public Especialidad(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdEspecialidad(){
		return this.idEspecialidad;
	}
	
	public void setIdEspecialidad(Long idEspecialidad){
		this.idEspecialidad = idEspecialidad;
	}
	
	public Long getIdMateria(){
		return this.idMateria;
	}
	
	public void setIdMateria(Long idMateria){
		this.idMateria = idMateria;
	}
		
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
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
		

    public List<PersonaServicioMateria> getPersonaServicioMateriaList(){
		return this.personaServicioMateriaList;
	}
	
	public void setPersonaServicioMateriaList(List<PersonaServicioMateria> personaServicioMateriaList){
		this.personaServicioMateriaList = personaServicioMateriaList;
	}
			
    public Materia getMateria(){
		return this.materia; 
	}
	
	public void setMateria(Materia materia){
		this.materia = materia;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_ESPECIALIDAD) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idEspecialidad);        
        hash = 37 * hash + Objects.hashCode(this.idMateria);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Especialidad que se pasa
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
        final Especialidad other = (Especialidad) obj;
        
        if (!Objects.equals(this.idEspecialidad, other.idEspecialidad)) {
            return false;
        }
        
        if (!Objects.equals(this.idMateria, other.idMateria)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
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

