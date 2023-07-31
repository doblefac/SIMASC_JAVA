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
import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import java.util.Date;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="EVENTO_CCB")
@NamedQuery(name = "EventoCcb.findAll", query = "SELECT p FROM EventoCcb p")
public class EventoCcb implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@Transient
	private List<Long> centros;
	
	@Transient
	private boolean educacionContinua;
	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_EVENTO_CCB_PK = "idEventoCcb";
	public static final String ENTIDAD_EVENTO_CCB_ESTADO = "estado";
	public static final String ENTIDAD_EVENTO_CCB_FECHA_INICIO = "fechaInicio";
	public static final String ENTIDAD_EVENTO_CCB_FECHA_FIN = "fechaFin";
	public static final String ENTIDAD_EVENTO_CCB_TIPO_EVENTO_CCB = "tipoEventoCcb";
	public static final String ENTIDAD_EVENTO_CCB_LUGAR = "lugar";
	public static final String ENTIDAD_EVENTO_CCB_DESCRIPCION = "descripcion";
	public static final String ENTIDAD_EVENTO_CCB_TEMAS_TRATADOS = "temasTratados";
	public static final String ENTIDAD_EVENTO_CCB_RESUMEN = "resumen";
	public static final String ENTIDAD_EVENTO_CCB_PERSONA_ACTA = "personaActa";
	public static final String ENTIDAD_EVENTO_CCB_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_EVENTO_CCB_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_EVENTO_CCB_ESTADO_REGISTRO_EVENTOCCB = "estadoRegistroEventoCcb";			
	public static final String ENTIDAD_EVENTO_CCB_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_EVENTO_CCB_CAPACITADOR = "capacitador";
	public static final String ENTIDAD_EVENTO_CCB_TIPO_CAPACITACION = "tipoCapacitacion";
	public static final String ENTIDAD_EVENTO_CCB_MATERIA = "materia";
	public static final String ENTIDAD_EVENTO_CCB_MATERIA_OTROS = "materiaOtros";
	
	
	
    private static final String[] ATRIBUTOS_ENTIDAD_EVENTO_CCB
            = {ENTIDAD_EVENTO_CCB_RESUMEN, ENTIDAD_EVENTO_CCB_FECHA_FIN, ENTIDAD_EVENTO_CCB_DESCRIPCION, ENTIDAD_EVENTO_CCB_TEMAS_TRATADOS, ENTIDAD_EVENTO_CCB_FECHA_INICIO, ENTIDAD_EVENTO_CCB_LUGAR, ENTIDAD_EVENTO_CCB_PERSONA_ACTA, ENTIDAD_EVENTO_CCB_FECHA_ULTIMA_MODIFICACION, ENTIDAD_EVENTO_CCB_TIPO_EVENTO_CCB, ENTIDAD_EVENTO_CCB_ID_USUARIO_MODIFICACION, ENTIDAD_EVENTO_CCB_PK, ENTIDAD_EVENTO_CCB_ESTADO, ENTIDAD_EVENTO_CCB_ESTADO_REGISTRO, ENTIDAD_EVENTO_CCB_CAPACITADOR, ENTIDAD_EVENTO_CCB_TIPO_CAPACITACION, ENTIDAD_EVENTO_CCB_MATERIA, ENTIDAD_EVENTO_CCB_MATERIA_OTROS};

	@Id
    @Column(name="id_evento_ccb")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idEventoCcb;
    
	@Column(name="estado")
	private String estado;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_inicio")
	private Date fechaInicio;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_fin")
	private Date fechaFin;		
    
	@Column(name="tipo_evento_ccb")
	private String tipoEventoCcb;		
    
	@Column(name="lugar")
	private String lugar;		
    
	@Column(name="descripcion")
	private String descripcion;		
    
	@Column(name="temas_tratados")
	private String temasTratados;		
    
	@Column(name="resumen")
	private String resumen;		
    
	@Column(name="persona_acta")
	private String personaActa;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;
	
	@Column(name="capacitador")
	private String capacitador;	
	
	@Column(name="tipo_capacitacion")
	private String tipoCapacitacion;		

	@Column(name="materia")
	private String materia;	
	
	@Column(name="materia_otros")
	private String materiaOtros;	

	@OneToMany(mappedBy="eventoCcb")
    private List<Documento> documentoList;
	@OneToMany(mappedBy="eventoCcb")
    private List<PersonaEventoCcb> personaEventoCcbList;
	
	
    public EventoCcb(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdEventoCcb(){
		return this.idEventoCcb;
	}
	
	public void setIdEventoCcb(Long idEventoCcb){
		this.idEventoCcb = idEventoCcb;
	}
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Date getFechaInicio(){
		return this.fechaInicio;
	}
	
	public void setFechaInicio(Date fechaInicio){
		this.fechaInicio = fechaInicio;
	}
		
	public Date getFechaFin(){
		return this.fechaFin;
	}
	
	public void setFechaFin(Date fechaFin){
		this.fechaFin = fechaFin;
	}
		
	public String getTipoEventoCcb(){
		return this.tipoEventoCcb;
	}
	
	public void setTipoEventoCcb(String tipoEventoCcb){
		this.tipoEventoCcb = tipoEventoCcb;
	}
		
	public String getLugar(){
		return this.lugar;
	}
	
	public void setLugar(String lugar){
		this.lugar = lugar;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
		
	public String getTemasTratados(){
		return this.temasTratados;
	}
	
	public void setTemasTratados(String temasTratados){
		this.temasTratados = temasTratados;
	}
		
	public String getResumen(){
		return this.resumen;
	}
	
	public void setResumen(String resumen){
		this.resumen = resumen;
	}
		
	public String getPersonaActa(){
		return this.personaActa;
	}
	
	public void setPersonaActa(String personaActa){
		this.personaActa = personaActa;
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
		

    public List<Documento> getDocumentoList(){
		return this.documentoList;
	}
	
	public void setDocumentoList(List<Documento> documentoList){
		this.documentoList = documentoList;
	}
			
    public List<PersonaEventoCcb> getPersonaEventoCcbList(){
		return this.personaEventoCcbList;
	}
	
	public void setPersonaEventoCcbList(List<PersonaEventoCcb> personaEventoCcbList){
		this.personaEventoCcbList = personaEventoCcbList;
	}
	/**
	 * @return the capacitador
	 */
	public String getCapacitador() {
		return capacitador;
	}


	/**
	 * @param capacitador the capacitador to set
	 */
	public void setCapacitador(String capacitador) {
		this.capacitador = capacitador;
	}


	/**
	 * @return the tipoCapacitacion
	 */
	public String getTipoCapacitacion() {
		return tipoCapacitacion;
	}


	/**
	 * @param tipoCapacitacion the tipoCapacitacion to set
	 */
	public void setTipoCapacitacion(String tipoCapacitacion) {
		this.tipoCapacitacion = tipoCapacitacion;
	}


	/**
	 * @return the materia
	 */
	public String getMateria() {
		return materia;
	}


	/**
	 * @param materia the materia to set
	 */
	public void setMateria(String materia) {
		this.materia = materia;
	}


	/**
	 * @return the materiaOtros
	 */
	public String getMateriaOtros() {
		return materiaOtros;
	}


	/**
	 * @param materiaOtros the materiaOtros to set
	 */
	public void setMateriaOtros(String materiaOtros) {
		this.materiaOtros = materiaOtros;
	}
			

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_EVENTO_CCB) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idEventoCcb);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.fechaInicio);
        hash = 37 * hash + Objects.hashCode(this.fechaFin);
        hash = 37 * hash + Objects.hashCode(this.tipoEventoCcb);
        hash = 37 * hash + Objects.hashCode(this.lugar);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.temasTratados);
        hash = 37 * hash + Objects.hashCode(this.resumen);
        hash = 37 * hash + Objects.hashCode(this.personaActa);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.capacitador);
        hash = 37 * hash + Objects.hashCode(this.tipoCapacitacion);
        hash = 37 * hash + Objects.hashCode(this.materia);
        hash = 37 * hash + Objects.hashCode(this.materiaOtros);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad EventoCcb que se pasa
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
        final EventoCcb other = (EventoCcb) obj;
        
        if (!Objects.equals(this.idEventoCcb, other.idEventoCcb)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFin, other.fechaFin)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoEventoCcb, other.tipoEventoCcb)) {
            return false;
        }
        
        if (!Objects.equals(this.lugar, other.lugar)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.temasTratados, other.temasTratados)) {
            return false;
        }
        
        if (!Objects.equals(this.resumen, other.resumen)) {
            return false;
        }
        
        if (!Objects.equals(this.personaActa, other.personaActa)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.capacitador, other.capacitador)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.materia, other.materia)) {
            return false;
        }
        
        if (!Objects.equals(this.materiaOtros, other.materiaOtros)) {
            return false;
        }    

        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }



	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * @return the centros
	 */
	public List<Long> getCentros() {
		return centros;
	}


	/**
	 * @param centros the centros to set
	 */
	public void setCentros(List<Long> centros) {
		this.centros = centros;
	}
	
	
	
	/**
	 * @return the isEducacionContinua
	 */
	public boolean isEducacionContinua() {
		return educacionContinua;
	}


	/**
	 * @param isEducacionContinua the isEducacionContinua to set
	 */
	public void setEducacionContinua(boolean educacionContinua) {
		this.educacionContinua = educacionContinua;
	}


	/**
	 * Método para comparar los datos basicos de un evento y verificar si son iguales
	 * @param other evento con el cual se reliza la comparacion
	 * @return true si los datos coinciden
	 */
	public boolean comparar(EventoCcb other) {
		return Objects.equals(this.idEventoCcb, other.idEventoCcb) && Objects.equals(this.estado, other.estado) 
				&& Objects.equals(this.fechaInicio, other.fechaInicio) && Objects.equals(this.fechaFin, other.fechaFin)
				&& Objects.equals(this.tipoEventoCcb, other.tipoEventoCcb) && Objects.equals(this.lugar, other.lugar)
				&& Objects.equals(this.descripcion, other.descripcion);
	}

	// protected region metodos adicionales end

} 

