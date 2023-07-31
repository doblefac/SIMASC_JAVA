package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="DESARROLLO_PROFESIONAL")
@NamedQuery(name = "DesarrolloProfesional.findAll", query = "SELECT p FROM DesarrolloProfesional p")
public class DesarrolloProfesional implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_PK = "idDesarrolloProfesional";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_TIPO_DESARROLLO_PROFESIONAL = "tipoDesarrolloProfesional";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_NOMBRE = "nombre";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_INSTITUCION = "institucion";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_FECHA_INICIAL = "fechaInicial";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_FECHA_FINAL = "fechaFinal";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_FUNCIONES = "funciones";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_ID_MATERIA = "idMateria";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_NIVEL_ACADEMICO = "nivelAcademico";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_HORAS_CURSADAS = "horasCursadas";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_TIPO_CURSO = "tipoCurso";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_MATERIA_CURSO = "materiaCurso";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_NUMERO_HORAS = "numeroHoras";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_TIPO_CURSO_NO_DEFINIDO = "tipoCursoNoDefinido";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_MATERIA_CURSO_NO_DEFINIDA = "materiaCursoNoDefinida";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_NUMERO_AUDIENCIAS = "numeroAudiencias";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_ESTADO_REGISTRO_DESARROLLOPROFESIONAL = "estadoRegistroDesarrolloProfesional";			
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_ID_DOCUMENTO_CONSTANCIA = "idDocumentoConstancia";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_CALIFICACION = "calificacion";
	public static final String ENTIDAD_DESARROLLO_PROFESIONAL_LUGAR = "lugar";
    private static final String[] ATRIBUTOS_ENTIDAD_DESARROLLO_PROFESIONAL
            = {ENTIDAD_DESARROLLO_PROFESIONAL_FECHA_ULTIMA_MODIFICACION, ENTIDAD_DESARROLLO_PROFESIONAL_ID_DOCUMENTO_CONSTANCIA, ENTIDAD_DESARROLLO_PROFESIONAL_ID_PERSONA, ENTIDAD_DESARROLLO_PROFESIONAL_TIPO_CURSO_NO_DEFINIDO, ENTIDAD_DESARROLLO_PROFESIONAL_NIVEL_ACADEMICO, ENTIDAD_DESARROLLO_PROFESIONAL_NUMERO_HORAS, ENTIDAD_DESARROLLO_PROFESIONAL_NUMERO_AUDIENCIAS, ENTIDAD_DESARROLLO_PROFESIONAL_LUGAR, ENTIDAD_DESARROLLO_PROFESIONAL_CALIFICACION, ENTIDAD_DESARROLLO_PROFESIONAL_HORAS_CURSADAS, ENTIDAD_DESARROLLO_PROFESIONAL_ID_MATERIA, ENTIDAD_DESARROLLO_PROFESIONAL_PK, ENTIDAD_DESARROLLO_PROFESIONAL_FUNCIONES, ENTIDAD_DESARROLLO_PROFESIONAL_TIPO_DESARROLLO_PROFESIONAL, ENTIDAD_DESARROLLO_PROFESIONAL_NOMBRE, ENTIDAD_DESARROLLO_PROFESIONAL_FECHA_INICIAL, ENTIDAD_DESARROLLO_PROFESIONAL_INSTITUCION, ENTIDAD_DESARROLLO_PROFESIONAL_MATERIA_CURSO_NO_DEFINIDA, ENTIDAD_DESARROLLO_PROFESIONAL_ESTADO_REGISTRO, ENTIDAD_DESARROLLO_PROFESIONAL_TIPO_CURSO, ENTIDAD_DESARROLLO_PROFESIONAL_MATERIA_CURSO, ENTIDAD_DESARROLLO_PROFESIONAL_ID_USUARIO_MODIFICACION, ENTIDAD_DESARROLLO_PROFESIONAL_FECHA_FINAL};

	@Id
    @Column(name="id_desarrollo_profesional")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idDesarrolloProfesional;
    
	@Column(name="tipo_desarrollo_profesional")
	private String tipoDesarrolloProfesional;		
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="institucion")
	private String institucion;		
    
	@Column(name="fecha_inicial")
	private Date fechaInicial;		
    
	@Column(name="fecha_final")
	private Date fechaFinal;		
    
	@Column(name="funciones")
	private String funciones;		
    
	@Column(name="id_materia")
	private Long idMateria;		
    
	@Column(name="nivel_academico")
	private String nivelAcademico;		
    
	@Column(name="horas_cursadas")
	private Integer horasCursadas;		
    
	@Column(name="tipo_curso")
	private String tipoCurso;		
    
	@Column(name="materia_curso")
	private String materiaCurso;		
    
	@Column(name="numero_horas")
	private Integer numeroHoras;		
    
	@Column(name="tipo_curso_no_definido")
	private String tipoCursoNoDefinido;		
    
	@Column(name="materia_curso_no_definida")
	private String materiaCursoNoDefinida;		
    
	@Column(name="numero_audiencias")
	private Integer numeroAudiencias;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Column(name="id_documento_constancia")
	private Long idDocumentoConstancia;		
    
	@Column(name="calificacion")
	private Double calificacion;		
    
	@Column(name="lugar")
	private String lugar;		

	@ManyToOne
	@JoinColumn(name="id_documento_constancia", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documento;
		
	@ManyToOne
	@JoinColumn(name="id_materia", referencedColumnName="id_materia", insertable = false, updatable = false)
    private Materia materia;
		
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	
	
    public DesarrolloProfesional(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdDesarrolloProfesional(){
		return this.idDesarrolloProfesional;
	}
	
	public void setIdDesarrolloProfesional(Long idDesarrolloProfesional){
		this.idDesarrolloProfesional = idDesarrolloProfesional;
	}
	
	public String getTipoDesarrolloProfesional(){
		return this.tipoDesarrolloProfesional;
	}
	
	public void setTipoDesarrolloProfesional(String tipoDesarrolloProfesional){
		this.tipoDesarrolloProfesional = tipoDesarrolloProfesional;
	}
		
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getInstitucion(){
		return this.institucion;
	}
	
	public void setInstitucion(String institucion){
		this.institucion = institucion;
	}
		
	public Date getFechaInicial(){
		return this.fechaInicial;
	}
	
	public void setFechaInicial(Date fechaInicial){
		this.fechaInicial = fechaInicial;
	}
		
	public Date getFechaFinal(){
		return this.fechaFinal;
	}
	
	public void setFechaFinal(Date fechaFinal){
		this.fechaFinal = fechaFinal;
	}
		
	public String getFunciones(){
		return this.funciones;
	}
	
	public void setFunciones(String funciones){
		this.funciones = funciones;
	}
		
	public Long getIdMateria(){
		return this.idMateria;
	}
	
	public void setIdMateria(Long idMateria){
		this.idMateria = idMateria;
	}
		
	public String getNivelAcademico(){
		return this.nivelAcademico;
	}
	
	public void setNivelAcademico(String nivelAcademico){
		this.nivelAcademico = nivelAcademico;
	}
		
	public Integer getHorasCursadas(){
		return this.horasCursadas;
	}
	
	public void setHorasCursadas(Integer horasCursadas){
		this.horasCursadas = horasCursadas;
	}
		
	public String getTipoCurso(){
		return this.tipoCurso;
	}
	
	public void setTipoCurso(String tipoCurso){
		this.tipoCurso = tipoCurso;
	}
		
	public String getMateriaCurso(){
		return this.materiaCurso;
	}
	
	public void setMateriaCurso(String materiaCurso){
		this.materiaCurso = materiaCurso;
	}
		
	public Integer getNumeroHoras(){
		return this.numeroHoras;
	}
	
	public void setNumeroHoras(Integer numeroHoras){
		this.numeroHoras = numeroHoras;
	}
		
	public String getTipoCursoNoDefinido(){
		return this.tipoCursoNoDefinido;
	}
	
	public void setTipoCursoNoDefinido(String tipoCursoNoDefinido){
		this.tipoCursoNoDefinido = tipoCursoNoDefinido;
	}
		
	public String getMateriaCursoNoDefinida(){
		return this.materiaCursoNoDefinida;
	}
	
	public void setMateriaCursoNoDefinida(String materiaCursoNoDefinida){
		this.materiaCursoNoDefinida = materiaCursoNoDefinida;
	}
		
	public Integer getNumeroAudiencias(){
		return this.numeroAudiencias;
	}
	
	public void setNumeroAudiencias(Integer numeroAudiencias){
		this.numeroAudiencias = numeroAudiencias;
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
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Long getIdDocumentoConstancia(){
		return this.idDocumentoConstancia;
	}
	
	public void setIdDocumentoConstancia(Long idDocumentoConstancia){
		this.idDocumentoConstancia = idDocumentoConstancia;
	}
		
	public Double getCalificacion(){
		return this.calificacion;
	}
	
	public void setCalificacion(Double calificacion){
		this.calificacion = calificacion;
	}
		
	public String getLugar(){
		return this.lugar;
	}
	
	public void setLugar(String lugar){
		this.lugar = lugar;
	}
		

    public Documento getDocumento(){
		return this.documento; 
	}
	
	public void setDocumento(Documento documento){
		this.documento = documento;
	}
    public Materia getMateria(){
		return this.materia; 
	}
	
	public void setMateria(Materia materia){
		this.materia = materia;
	}
    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_DESARROLLO_PROFESIONAL) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idDesarrolloProfesional);        
        hash = 37 * hash + Objects.hashCode(this.tipoDesarrolloProfesional);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.institucion);
        hash = 37 * hash + Objects.hashCode(this.fechaInicial);
        hash = 37 * hash + Objects.hashCode(this.fechaFinal);
        hash = 37 * hash + Objects.hashCode(this.funciones);
        hash = 37 * hash + Objects.hashCode(this.idMateria);
        hash = 37 * hash + Objects.hashCode(this.nivelAcademico);
        hash = 37 * hash + Objects.hashCode(this.horasCursadas);
        hash = 37 * hash + Objects.hashCode(this.tipoCurso);
        hash = 37 * hash + Objects.hashCode(this.materiaCurso);
        hash = 37 * hash + Objects.hashCode(this.numeroHoras);
        hash = 37 * hash + Objects.hashCode(this.tipoCursoNoDefinido);
        hash = 37 * hash + Objects.hashCode(this.materiaCursoNoDefinida);
        hash = 37 * hash + Objects.hashCode(this.numeroAudiencias);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idDocumentoConstancia);
        hash = 37 * hash + Objects.hashCode(this.calificacion);
        hash = 37 * hash + Objects.hashCode(this.lugar);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DesarrolloProfesional que se pasa
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
        final DesarrolloProfesional other = (DesarrolloProfesional) obj;
        
        if (!Objects.equals(this.idDesarrolloProfesional, other.idDesarrolloProfesional)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDesarrolloProfesional, other.tipoDesarrolloProfesional)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.institucion, other.institucion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicial, other.fechaInicial)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFinal, other.fechaFinal)) {
            return false;
        }
        
        if (!Objects.equals(this.funciones, other.funciones)) {
            return false;
        }
        
        if (!Objects.equals(this.idMateria, other.idMateria)) {
            return false;
        }
        
        if (!Objects.equals(this.nivelAcademico, other.nivelAcademico)) {
            return false;
        }
        
        if (!Objects.equals(this.horasCursadas, other.horasCursadas)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoCurso, other.tipoCurso)) {
            return false;
        }
        
        if (!Objects.equals(this.materiaCurso, other.materiaCurso)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroHoras, other.numeroHoras)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoCursoNoDefinido, other.tipoCursoNoDefinido)) {
            return false;
        }
        
        if (!Objects.equals(this.materiaCursoNoDefinida, other.materiaCursoNoDefinida)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroAudiencias, other.numeroAudiencias)) {
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
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumentoConstancia, other.idDocumentoConstancia)) {
            return false;
        }
        
        if (!Objects.equals(this.calificacion, other.calificacion)) {
            return false;
        }
        
        return Objects.equals(this.lugar, other.lugar);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

