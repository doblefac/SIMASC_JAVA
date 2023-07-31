package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name="SERVICIO_MATERIA")
@NamedQuery(name = "ServicioMateria.findAll", query = "SELECT p FROM ServicioMateria p")
public class ServicioMateria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_SERVICIO_MATERIA_PK_ID_SERVICIO = "servicioMateriaPK.idServicio";
			
	public static final String ENTIDAD_SERVICIO_MATERIA_PK_ID_MATERIA = "servicioMateriaPK.idMateria";
	public static final String ENTIDAD_SERVICIO_MATERIA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_SERVICIO_MATERIA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_SERVICIO_MATERIA_ESTADO_REGISTRO_SERVICIOMATERIA = "estadoRegistroServicioMateria";			
	public static final String ENTIDAD_SERVICIO_MATERIA_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_SERVICIO_MATERIA
            = {ENTIDAD_SERVICIO_MATERIA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_SERVICIO_MATERIA_PK_ID_MATERIA, ENTIDAD_SERVICIO_MATERIA_ESTADO_REGISTRO, ENTIDAD_SERVICIO_MATERIA_PK_ID_SERVICIO, ENTIDAD_SERVICIO_MATERIA_ID_USUARIO_MODIFICACION};

	@EmbeddedId
	private ServicioMateriaPK servicioMateriaPK;
    
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
		
	@ManyToOne
	@JoinColumn(name="id_servicio", referencedColumnName="id_servicio", insertable = false, updatable = false)
    private Servicio servicio;
		
	@OneToMany(mappedBy="servicioMateria")
    private List<Caso> casoList;
	@OneToMany(mappedBy="servicioMateria")
    private List<Convenio> convenioList;
	@OneToMany(mappedBy="servicioMateria")
    private List<ConvenioCentro> convenioCentroList;
	@OneToMany(mappedBy="servicioMateria")
    private List<PersonaServicioMateria> personaServicioMateriaList;
	@OneToMany(mappedBy="servicioMateria")
    private List<ServicioMateriaCentro> servicioMateriaCentroList;
	@OneToMany(mappedBy="servicioMateria")
    private List<SolicitudServicio> solicitudServicioList;
	
	
    public ServicioMateria(){
		servicioMateriaPK = new ServicioMateriaPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ServicioMateriaPK getServicioMateriaPK(){
		return this.servicioMateriaPK;
	}
	
	public void setServicioMateriaPK(ServicioMateriaPK servicioMateriaPK){
		this.servicioMateriaPK   = servicioMateriaPK ;
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
		

    public List<Caso> getCasoList(){
		return this.casoList;
	}
	
	public void setCasoList(List<Caso> casoList){
		this.casoList = casoList;
	}
			
    public List<Convenio> getConvenioList(){
		return this.convenioList;
	}
	
	public void setConvenioList(List<Convenio> convenioList){
		this.convenioList = convenioList;
	}
			
    public List<ConvenioCentro> getConvenioCentroList(){
		return this.convenioCentroList;
	}
	
	public void setConvenioCentroList(List<ConvenioCentro> convenioCentroList){
		this.convenioCentroList = convenioCentroList;
	}
			
    public List<PersonaServicioMateria> getPersonaServicioMateriaList(){
		return this.personaServicioMateriaList;
	}
	
	public void setPersonaServicioMateriaList(List<PersonaServicioMateria> personaServicioMateriaList){
		this.personaServicioMateriaList = personaServicioMateriaList;
	}
			
    public List<ServicioMateriaCentro> getServicioMateriaCentroList(){
		return this.servicioMateriaCentroList;
	}
	
	public void setServicioMateriaCentroList(List<ServicioMateriaCentro> servicioMateriaCentroList){
		this.servicioMateriaCentroList = servicioMateriaCentroList;
	}
			
    public List<SolicitudServicio> getSolicitudServicioList(){
		return this.solicitudServicioList;
	}
	
	public void setSolicitudServicioList(List<SolicitudServicio> solicitudServicioList){
		this.solicitudServicioList = solicitudServicioList;
	}
			
    public Materia getMateria(){
		return this.materia; 
	}
	
	public void setMateria(Materia materia){
		this.materia = materia;
	}
    public Servicio getServicio(){
		return this.servicio; 
	}
	
	public void setServicio(Servicio servicio){
		this.servicio = servicio;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_SERVICIO_MATERIA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.servicioMateriaPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ServicioMateria que se pasa
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
        final ServicioMateria other = (ServicioMateria) obj;
        
        if (!Objects.equals(this.servicioMateriaPK, other.servicioMateriaPK)) {
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

