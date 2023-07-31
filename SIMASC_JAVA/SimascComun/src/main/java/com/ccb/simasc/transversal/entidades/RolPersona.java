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
@Table(name="ROL_PERSONA")
@NamedQuery(name = "RolPersona.findAll", query = "SELECT p FROM RolPersona p")
public class RolPersona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_ROL_PERSONA_PK = "idRolPersona";
	public static final String ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA = "fechaInicioVigencia";
	public static final String ENTIDAD_ROL_PERSONA_FECHA_FIN_VIGENCIA = "fechaFinVigencia";
	public static final String ENTIDAD_ROL_PERSONA_ACTA_APROBACION = "actaAprobacion";
	public static final String ENTIDAD_ROL_PERSONA_FECHA_ACTA_APROBACION = "fechaActaAprobacion";
	public static final String ENTIDAD_ROL_PERSONA_REPORTADO_SICAAC = "reportadoSicaac";
	public static final String ENTIDAD_ROL_PERSONA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_ROL_PERSONA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_ROL_PERSONA_ESTADO_REGISTRO_ROLPERSONA = "estadoRegistroRolPersona";			
	public static final String ENTIDAD_ROL_PERSONA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_ROL_PERSONA_ID_ROL = "idRol";
	public static final String ENTIDAD_ROL_PERSONA_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_ROL_PERSONA_ID_CENTRO = "idCentro";
	public static final String ENTIDAD_ROL_PERSONA_ID_LISTA = "idLista";
    private static final String[] ATRIBUTOS_ENTIDAD_ROL_PERSONA
            = {ENTIDAD_ROL_PERSONA_ESTADO_REGISTRO, ENTIDAD_ROL_PERSONA_FECHA_ACTA_APROBACION, ENTIDAD_ROL_PERSONA_ID_ROL, ENTIDAD_ROL_PERSONA_ID_CENTRO, ENTIDAD_ROL_PERSONA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_ROL_PERSONA_FECHA_FIN_VIGENCIA, ENTIDAD_ROL_PERSONA_PK, ENTIDAD_ROL_PERSONA_ID_PERSONA, ENTIDAD_ROL_PERSONA_ID_USUARIO_MODIFICACION, ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA, ENTIDAD_ROL_PERSONA_ACTA_APROBACION, ENTIDAD_ROL_PERSONA_ID_LISTA, ENTIDAD_ROL_PERSONA_REPORTADO_SICAAC};

	@Id
    @Column(name="id_rol_persona")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idRolPersona;
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_inicio_vigencia")
	private Date fechaInicioVigencia;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_fin_vigencia")
	private Date fechaFinVigencia;		
    
	@Column(name="acta_aprobacion")
	private String actaAprobacion;		
    
	@Column(name="fecha_acta_aprobacion")
	private Date fechaActaAprobacion;		
    
	@Column(name="reportado_sicaac")
	private boolean reportadoSicaac;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_rol")
	private Long idRol;		
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Column(name="id_centro")
	private Long idCentro;		
    
	@Column(name="id_lista")
	private Long idLista;		

	@ManyToOne
	@JoinColumn(name="id_centro", referencedColumnName="id_centro", insertable = false, updatable = false)
    private Centro centro;
		
	@ManyToOne
	@JoinColumn(name="id_lista", referencedColumnName="id_lista", insertable = false, updatable = false)
    private Lista lista;
		
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	@ManyToOne
	@JoinColumn(name="id_rol", referencedColumnName="id_rol", insertable = false, updatable = false)
    private Rol rol;
		
	@OneToMany(mappedBy="rolPersona")
    private List<SolicitudPrestador> solicitudPrestadorList;
	
	
    public RolPersona(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdRolPersona(){
		return this.idRolPersona;
	}
	
	public void setIdRolPersona(Long idRolPersona){
		this.idRolPersona = idRolPersona;
	}
	
	public Date getFechaInicioVigencia(){
		return this.fechaInicioVigencia;
	}
	
	public void setFechaInicioVigencia(Date fechaInicioVigencia){
		this.fechaInicioVigencia = fechaInicioVigencia;
	}
		
	public Date getFechaFinVigencia(){
		return this.fechaFinVigencia;
	}
	
	public void setFechaFinVigencia(Date fechaFinVigencia){
		this.fechaFinVigencia = fechaFinVigencia;
	}
		
	public String getActaAprobacion(){
		return this.actaAprobacion;
	}
	
	public void setActaAprobacion(String actaAprobacion){
		this.actaAprobacion = actaAprobacion;
	}
		
	public Date getFechaActaAprobacion(){
		return this.fechaActaAprobacion;
	}
	
	public void setFechaActaAprobacion(Date fechaActaAprobacion){
		this.fechaActaAprobacion = fechaActaAprobacion;
	}
		
	public boolean getReportadoSicaac(){
		return this.reportadoSicaac;
	}
	
	public void setReportadoSicaac(boolean reportadoSicaac){
		this.reportadoSicaac = reportadoSicaac;
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
		
	public Long getIdRol(){
		return this.idRol;
	}
	
	public void setIdRol(Long idRol){
		this.idRol = idRol;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Long getIdCentro(){
		return this.idCentro;
	}
	
	public void setIdCentro(Long idCentro){
		this.idCentro = idCentro;
	}
		
	public Long getIdLista(){
		return this.idLista;
	}
	
	public void setIdLista(Long idLista){
		this.idLista = idLista;
	}
		

    public List<SolicitudPrestador> getSolicitudPrestadorList(){
		return this.solicitudPrestadorList;
	}
	
	public void setSolicitudPrestadorList(List<SolicitudPrestador> solicitudPrestadorList){
		this.solicitudPrestadorList = solicitudPrestadorList;
	}
			
    public Centro getCentro(){
		return this.centro; 
	}
	
	public void setCentro(Centro centro){
		this.centro = centro;
	}
    public Lista getLista(){
		return this.lista; 
	}
	
	public void setLista(Lista lista){
		this.lista = lista;
	}
    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}
    public Rol getRol(){
		return this.rol; 
	}
	
	public void setRol(Rol rol){
		this.rol = rol;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_ROL_PERSONA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idRolPersona);        
        hash = 37 * hash + Objects.hashCode(this.fechaInicioVigencia);
        hash = 37 * hash + Objects.hashCode(this.fechaFinVigencia);
        hash = 37 * hash + Objects.hashCode(this.actaAprobacion);
        hash = 37 * hash + Objects.hashCode(this.fechaActaAprobacion);
        hash = 37 * hash + (this.reportadoSicaac ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idRol);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idCentro);
        hash = 37 * hash + Objects.hashCode(this.idLista);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RolPersona que se pasa
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
        final RolPersona other = (RolPersona) obj;
        
        if (!Objects.equals(this.idRolPersona, other.idRolPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicioVigencia, other.fechaInicioVigencia)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFinVigencia, other.fechaFinVigencia)) {
            return false;
        }
        
        if (!Objects.equals(this.actaAprobacion, other.actaAprobacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaActaAprobacion, other.fechaActaAprobacion)) {
            return false;
        }
        
        if (!Objects.equals(this.reportadoSicaac, other.reportadoSicaac)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoRegistro, other.estadoRegistro)) {
            return false;
        }
        
        if (!Objects.equals(this.idRol, other.idRol)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idCentro, other.idCentro)) {
            return false;
        }
        
        return Objects.equals(this.idLista, other.idLista);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

