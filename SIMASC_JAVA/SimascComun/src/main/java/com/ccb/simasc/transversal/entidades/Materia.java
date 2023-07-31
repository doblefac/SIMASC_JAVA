package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name="MATERIA")
@NamedQuery(name = "Materia.findAll", query = "SELECT p FROM Materia p")
public class Materia implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_MATERIA_PK = "idMateria";
	public static final String ENTIDAD_MATERIA_NOMBRE = "nombre";
	public static final String ENTIDAD_MATERIA_OBSERVACIONES = "observaciones";
	public static final String ENTIDAD_MATERIA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_MATERIA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_MATERIA_ESTADO_REGISTRO_MATERIA = "estadoRegistroMateria";			
	public static final String ENTIDAD_MATERIA_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_MATERIA
            = {ENTIDAD_MATERIA_NOMBRE, ENTIDAD_MATERIA_OBSERVACIONES, ENTIDAD_MATERIA_ESTADO_REGISTRO, ENTIDAD_MATERIA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_MATERIA_PK, ENTIDAD_MATERIA_ID_USUARIO_MODIFICACION};

	@Id
    @Column(name="id_materia")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idMateria;
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="observaciones")
	private String observaciones;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@OneToMany(mappedBy="materia")
    private List<Asesoria> asesoriaList;
	@OneToMany(mappedBy="materia")
    private List<DesarrolloProfesional> desarrolloProfesionalList;
	@OneToMany(mappedBy="materia")
    private List<Especialidad> especialidadList;
	@OneToMany(mappedBy="materia")
    private List<ServicioMateria> servicioMateriaList;
	
	
    public Materia(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
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
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
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
		

    public List<Asesoria> getAsesoriaList(){
		return this.asesoriaList;
	}
	
	public void setAsesoriaList(List<Asesoria> asesoriaList){
		this.asesoriaList = asesoriaList;
	}
			
    public List<DesarrolloProfesional> getDesarrolloProfesionalList(){
		return this.desarrolloProfesionalList;
	}
	
	public void setDesarrolloProfesionalList(List<DesarrolloProfesional> desarrolloProfesionalList){
		this.desarrolloProfesionalList = desarrolloProfesionalList;
	}
			
    public List<Especialidad> getEspecialidadList(){
		return this.especialidadList;
	}
	
	public void setEspecialidadList(List<Especialidad> especialidadList){
		this.especialidadList = especialidadList;
	}
			
    public List<ServicioMateria> getServicioMateriaList(){
		return this.servicioMateriaList;
	}
	
	public void setServicioMateriaList(List<ServicioMateria> servicioMateriaList){
		this.servicioMateriaList = servicioMateriaList;
	}
			

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_MATERIA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idMateria);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Materia que se pasa
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
        final Materia other = (Materia) obj;
        
        if (!Objects.equals(this.idMateria, other.idMateria)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
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
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

