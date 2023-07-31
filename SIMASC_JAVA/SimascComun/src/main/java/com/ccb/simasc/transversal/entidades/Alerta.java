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
import java.util.Date;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="ALERTA")
@NamedQuery(name = "Alerta.findAll", query = "SELECT p FROM Alerta p")
public class Alerta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_ALERTA_PK = "idAlerta";
	public static final String ENTIDAD_ALERTA_NOMBRE = "nombre";
	public static final String ENTIDAD_ALERTA_TIPO_SERVICIO = "tipoServicio";
	public static final String ENTIDAD_ALERTA_TIPO_ALERTA = "tipoAlerta";
	public static final String ENTIDAD_ALERTA_CODIGO = "codigo";
	public static final String ENTIDAD_ALERTA_VALOR = "valor";
	public static final String ENTIDAD_ALERTA_ASUNTO = "asunto";
	public static final String ENTIDAD_ALERTA_DESCRIPCION_DE_ALERTA = "descripcionDeAlerta";
	public static final String ENTIDAD_ALERTA_TEXTO = "texto";
	public static final String ENTIDAD_ALERTA_PERIODICIDAD = "periodicidad";
	public static final String ENTIDAD_ALERTA_TIPO_PERIODICIDAD = "tipoPeriodicidad";
	public static final String ENTIDAD_ALERTA_ESTADO = "estado";
	public static final String ENTIDAD_ALERTA_HORA_EJECUCION = "horaEjecucion";
	public static final String ENTIDAD_ALERTA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_ALERTA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_ALERTA_ESTADO_REGISTRO_ALERTA = "estadoRegistroAlerta";			
	public static final String ENTIDAD_ALERTA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_ALERTA_ESTADO_EJECUCION = "estadoEjecucion";
    private static final String[] ATRIBUTOS_ENTIDAD_ALERTA
            = {ENTIDAD_ALERTA_ASUNTO, ENTIDAD_ALERTA_ID_USUARIO_MODIFICACION, ENTIDAD_ALERTA_ESTADO_REGISTRO, ENTIDAD_ALERTA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_ALERTA_TIPO_SERVICIO, ENTIDAD_ALERTA_CODIGO, ENTIDAD_ALERTA_PK, ENTIDAD_ALERTA_NOMBRE, ENTIDAD_ALERTA_PERIODICIDAD, ENTIDAD_ALERTA_ESTADO, ENTIDAD_ALERTA_VALOR, ENTIDAD_ALERTA_TIPO_PERIODICIDAD, ENTIDAD_ALERTA_DESCRIPCION_DE_ALERTA, ENTIDAD_ALERTA_HORA_EJECUCION,  ENTIDAD_ALERTA_TEXTO, ENTIDAD_ALERTA_TIPO_ALERTA, ENTIDAD_ALERTA_ESTADO_EJECUCION };

	@Id
    @Column(name="id_alerta")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idAlerta;
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="tipo_servicio")
	private String tipoServicio;		
    
	@Column(name="tipo_alerta")
	private String tipoAlerta;		
    
	@Column(name="codigo")
	private String codigo;		
    
	@Column(name="valor")
	private Long valor;		
    
	@Column(name="asunto")
	private String asunto;		
    
	@Column(name="descripcion_de_alerta")
	private String descripcionDeAlerta;		
    
	@Column(name="texto")
	private String texto;		
    
	@Column(name="periodicidad")
	private String periodicidad;		
    
	@Column(name="tipo_periodicidad")
	private String tipoPeriodicidad;		
    
	@Column(name="estado")
	private String estado;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="hora_ejecucion")
	private Date horaEjecucion;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="estado_ejecucion")
	private String estadoEjecucion;		

	@OneToMany(mappedBy="alerta")
    private List<DiaEjecucion> diaEjecucionList;
	@OneToMany(mappedBy="alerta")
    private List<Notificacion> notificacionList;
	@OneToMany(mappedBy="alerta")
    private List<PersonaRolAlerta> personaRolAlertaList;
	@OneToMany(mappedBy="alerta")
    private List<ProgramacionAlerta> programacionAlertaList;
	
	
    public Alerta(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }
    

	public Alerta(String codigo) {
		super();
		this.codigo = codigo;
	}


	public Long getIdAlerta(){
		return this.idAlerta;
	}
	
	public void setIdAlerta(Long idAlerta){
		this.idAlerta = idAlerta;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getTipoServicio(){
		return this.tipoServicio;
	}
	
	public void setTipoServicio(String tipoServicio){
		this.tipoServicio = tipoServicio;
	}
		
	public String getTipoAlerta(){
		return this.tipoAlerta;
	}
	
	public void setTipoAlerta(String tipoAlerta){
		this.tipoAlerta = tipoAlerta;
	}
		
	public String getCodigo(){
		return this.codigo;
	}
	
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
		
	public Long getValor(){
		return this.valor;
	}
	
	public void setValor(Long valor){
		this.valor = valor;
	}
		
	public String getAsunto(){
		return this.asunto;
	}
	
	public void setAsunto(String asunto){
		this.asunto = asunto;
	}
		
	public String getDescripcionDeAlerta(){
		return this.descripcionDeAlerta;
	}
	
	public void setDescripcionDeAlerta(String descripcionDeAlerta){
		this.descripcionDeAlerta = descripcionDeAlerta;
	}
		
	public String getTexto(){
		return this.texto;
	}
	
	public void setTexto(String texto){
		this.texto = texto;
	}
		
	public String getPeriodicidad(){
		return this.periodicidad;
	}
	
	public void setPeriodicidad(String periodicidad){
		this.periodicidad = periodicidad;
	}
		
	public String getTipoPeriodicidad(){
		return this.tipoPeriodicidad;
	}
	
	public void setTipoPeriodicidad(String tipoPeriodicidad){
		this.tipoPeriodicidad = tipoPeriodicidad;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Date getHoraEjecucion(){
		return this.horaEjecucion;
	}
	
	public void setHoraEjecucion(Date horaEjecucion){
		this.horaEjecucion = horaEjecucion;
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

    public List<DiaEjecucion> getDiaEjecucionList(){
		return this.diaEjecucionList;
	}
	
	public void setDiaEjecucionList(List<DiaEjecucion> diaEjecucionList){
		this.diaEjecucionList = diaEjecucionList;
	}
			
    public List<Notificacion> getNotificacionList(){
		return this.notificacionList;
	}
	
	public void setNotificacionList(List<Notificacion> notificacionList){
		this.notificacionList = notificacionList;
	}
			
    public List<PersonaRolAlerta> getPersonaRolAlertaList(){
		return this.personaRolAlertaList;
	}
	
	public void setPersonaRolAlertaList(List<PersonaRolAlerta> personaRolAlertaList){
		this.personaRolAlertaList = personaRolAlertaList;
	}
			
    public List<ProgramacionAlerta> getProgramacionAlertaList(){
		return this.programacionAlertaList;
	}
	
	public void setProgramacionAlertaList(List<ProgramacionAlerta> programacionAlertaList){
		this.programacionAlertaList = programacionAlertaList;
	}
	
	public String getEstadoEjecucion() {
		return estadoEjecucion;
	}

	public void setEstadoEjecucion(String estadoEjecucion) {
		this.estadoEjecucion = estadoEjecucion;
	}
			

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_ALERTA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idAlerta);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.tipoServicio);
        hash = 37 * hash + Objects.hashCode(this.tipoAlerta);
        hash = 37 * hash + Objects.hashCode(this.codigo);
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.asunto);
        hash = 37 * hash + Objects.hashCode(this.descripcionDeAlerta);
        hash = 37 * hash + Objects.hashCode(this.texto);
        hash = 37 * hash + Objects.hashCode(this.periodicidad);
        hash = 37 * hash + Objects.hashCode(this.tipoPeriodicidad);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.horaEjecucion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.estadoEjecucion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Alerta que se pasa
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
        final Alerta other = (Alerta) obj;
        
        if (!Objects.equals(this.idAlerta, other.idAlerta)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoServicio, other.tipoServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoAlerta, other.tipoAlerta)) {
            return false;
        }
        
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        
        if (!Objects.equals(this.asunto, other.asunto)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcionDeAlerta, other.descripcionDeAlerta)) {
            return false;
        }
        
        if (!Objects.equals(this.texto, other.texto)) {
            return false;
        }
        
        if (!Objects.equals(this.periodicidad, other.periodicidad)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoPeriodicidad, other.tipoPeriodicidad)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.horaEjecucion, other.horaEjecucion)) {
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
        
        return Objects.equals(this.estadoEjecucion, other.estadoEjecucion);
                
    }



	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

