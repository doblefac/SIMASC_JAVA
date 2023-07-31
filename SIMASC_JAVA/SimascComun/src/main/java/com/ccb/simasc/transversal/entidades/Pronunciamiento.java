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
@Table(name="PRONUNCIAMIENTO")
@NamedQuery(name = "Pronunciamiento.findAll", query = "SELECT p FROM Pronunciamiento p")
public class Pronunciamiento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PRONUNCIAMIENTO_PK = "idPronunciamiento";
	public static final String ENTIDAD_PRONUNCIAMIENTO_PRONUNCIAMIENTO = "pronunciamiento";
	public static final String ENTIDAD_PRONUNCIAMIENTO_TIENE_RELACION_CON_PARTES = "tieneRelacionConPartes";
	public static final String ENTIDAD_PRONUNCIAMIENTO_MAXIMO_DE_TRIBUNALES_ENTIDAD_PUBLICA = "maximoDeTribunalesEntidadPublica";
	public static final String ENTIDAD_PRONUNCIAMIENTO_MAXIMO_DE_TRIBUNALES_ENTIDAD_PUBLICA_LEY1682 = "maximoDeTribunalesEntidadPublicaLey1682";
	public static final String ENTIDAD_PRONUNCIAMIENTO_FECHA = "fecha";
	public static final String ENTIDAD_PRONUNCIAMIENTO_REVELACIONES = "revelaciones";
	public static final String ENTIDAD_PRONUNCIAMIENTO_MOTIVO_DECLINACION = "motivoDeclinacion";
	public static final String ENTIDAD_PRONUNCIAMIENTO_OBSERVACIONES = "observaciones";
	public static final String ENTIDAD_PRONUNCIAMIENTO_ID_DOCUMENTO = "idDocumento";
	public static final String ENTIDAD_PRONUNCIAMIENTO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PRONUNCIAMIENTO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PRONUNCIAMIENTO_ESTADO_REGISTRO_PRONUNCIAMIENTO = "estadoRegistroPronunciamiento";			
	public static final String ENTIDAD_PRONUNCIAMIENTO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_PRONUNCIAMIENTO_PROCEDI_RECUPE_EMPRESARIAL = "procedimientoRecuperacionEmpresarial";			
	public static final String ENTIDAD_PRONUNCIAMIENTO_TRAMITE_ADMINISTRATIVO = "tramitesAdministrativo";
    private static final String[] ATRIBUTOS_ENTIDAD_PRONUNCIAMIENTO
            = {ENTIDAD_PRONUNCIAMIENTO_TIENE_RELACION_CON_PARTES, ENTIDAD_PRONUNCIAMIENTO_PK, ENTIDAD_PRONUNCIAMIENTO_PROCEDI_RECUPE_EMPRESARIAL, ENTIDAD_PRONUNCIAMIENTO_TRAMITE_ADMINISTRATIVO, ENTIDAD_PRONUNCIAMIENTO_REVELACIONES, ENTIDAD_PRONUNCIAMIENTO_ESTADO_REGISTRO, ENTIDAD_PRONUNCIAMIENTO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_PRONUNCIAMIENTO_PRONUNCIAMIENTO, ENTIDAD_PRONUNCIAMIENTO_MAXIMO_DE_TRIBUNALES_ENTIDAD_PUBLICA_LEY1682, ENTIDAD_PRONUNCIAMIENTO_MAXIMO_DE_TRIBUNALES_ENTIDAD_PUBLICA, ENTIDAD_PRONUNCIAMIENTO_ID_DOCUMENTO, ENTIDAD_PRONUNCIAMIENTO_MOTIVO_DECLINACION, ENTIDAD_PRONUNCIAMIENTO_FECHA, ENTIDAD_PRONUNCIAMIENTO_ID_USUARIO_MODIFICACION, ENTIDAD_PRONUNCIAMIENTO_OBSERVACIONES};

	@Id
    @Column(name="id_pronunciamiento")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idPronunciamiento;
    
	@Column(name="pronunciamiento")
	private String pronunciamiento;		
    
	@Column(name="tiene_relacion_con_partes")
	private boolean tieneRelacionConPartes;		
    
	@Column(name="maximo_de_tribunales_entidad_publica")
	private boolean maximoDeTribunalesEntidadPublica;		
    
	@Column(name="maximo_de_tribunales_entidad_publica_ley_1682")
	private boolean maximoDeTribunalesEntidadPublicaLey1682;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha")
	private Date fecha;		
    
	@Column(name="revelaciones")
	private String revelaciones;		
    
	@Column(name="motivo_declinacion")
	private String motivoDeclinacion;		
    
	@Column(name="observaciones")
	private String observaciones;		
    
	@Column(name="id_documento")
	private Long idDocumento;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_documento", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documento;
		
	@OneToMany(mappedBy="pronunciamiento")
    private List<RolPersonaCaso> rolPersonaCasoList;
	
	@Column(name="procedi_recupe_empresarial")
	private boolean procedimientoRecuperacionEmpresarial;
	
	
	@Column(name="tramite_administrativo")
	private boolean tramitesAdministrativos;
	
	
    public Pronunciamiento(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdPronunciamiento(){
		return this.idPronunciamiento;
	}
	
	public void setIdPronunciamiento(Long idPronunciamiento){
		this.idPronunciamiento = idPronunciamiento;
	}
	
	public String getPronunciamiento(){
		return this.pronunciamiento;
	}
	
	public void setPronunciamiento(String pronunciamiento){
		this.pronunciamiento = pronunciamiento;
	}
		
	public boolean getTieneRelacionConPartes(){
		return this.tieneRelacionConPartes;
	}
	
	public void setTieneRelacionConPartes(boolean tieneRelacionConPartes){
		this.tieneRelacionConPartes = tieneRelacionConPartes;
	}
		
	public boolean getMaximoDeTribunalesEntidadPublica(){
		return this.maximoDeTribunalesEntidadPublica;
	}
	
	public void setMaximoDeTribunalesEntidadPublica(boolean maximoDeTribunalesEntidadPublica){
		this.maximoDeTribunalesEntidadPublica = maximoDeTribunalesEntidadPublica;
	}
		
	public boolean getMaximoDeTribunalesEntidadPublicaLey1682(){
		return this.maximoDeTribunalesEntidadPublicaLey1682;
	}
	
	public void setMaximoDeTribunalesEntidadPublicaLey1682(boolean maximoDeTribunalesEntidadPublicaLey1682){
		this.maximoDeTribunalesEntidadPublicaLey1682 = maximoDeTribunalesEntidadPublicaLey1682;
	}
		
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
		
	public String getRevelaciones(){
		return this.revelaciones;
	}
	
	public void setRevelaciones(String revelaciones){
		this.revelaciones = revelaciones;
	}
		
	public String getMotivoDeclinacion(){
		return this.motivoDeclinacion;
	}
	
	public void setMotivoDeclinacion(String motivoDeclinacion){
		this.motivoDeclinacion = motivoDeclinacion;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
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
		

    public List<RolPersonaCaso> getRolPersonaCasoList(){
		return this.rolPersonaCasoList;
	}
	
	public void setRolPersonaCasoList(List<RolPersonaCaso> rolPersonaCasoList){
		this.rolPersonaCasoList = rolPersonaCasoList;
	}
			
    public Documento getDocumento(){
		return this.documento; 
	}
	
	public void setDocumento(Documento documento){
		this.documento = documento;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PRONUNCIAMIENTO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idPronunciamiento);        
        hash = 37 * hash + Objects.hashCode(this.pronunciamiento);
        hash = 37 * hash + (this.tieneRelacionConPartes ? 0 : 1);
        hash = 37 * hash + (this.maximoDeTribunalesEntidadPublica ? 0 : 1);
        hash = 37 * hash + (this.maximoDeTribunalesEntidadPublicaLey1682 ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.revelaciones);
        hash = 37 * hash + Objects.hashCode(this.motivoDeclinacion);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Pronunciamiento que se pasa
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
        final Pronunciamiento other = (Pronunciamiento) obj;
        
        if (!Objects.equals(this.idPronunciamiento, other.idPronunciamiento)) {
            return false;
        }
        
        if (!Objects.equals(this.pronunciamiento, other.pronunciamiento)) {
            return false;
        }
        
        if (!Objects.equals(this.tieneRelacionConPartes, other.tieneRelacionConPartes)) {
            return false;
        }
        
        if (!Objects.equals(this.maximoDeTribunalesEntidadPublica, other.maximoDeTribunalesEntidadPublica)) {
            return false;
        }
        
        if (!Objects.equals(this.maximoDeTribunalesEntidadPublicaLey1682, other.maximoDeTribunalesEntidadPublicaLey1682)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (!Objects.equals(this.revelaciones, other.revelaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.motivoDeclinacion, other.motivoDeclinacion)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
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


	public boolean isProcedimientoRecuperacionEmpresarial() {
		return procedimientoRecuperacionEmpresarial;
	}


	public void setProcedimientoRecuperacionEmpresarial(boolean procedimientoRecuperacionEmpresarial) {
		this.procedimientoRecuperacionEmpresarial = procedimientoRecuperacionEmpresarial;
	}


	public boolean isTramitesAdministrativos() {
		return tramitesAdministrativos;
	}


	public void setTramitesAdministrativos(boolean tramitesAdministrativos) {
		this.tramitesAdministrativos = tramitesAdministrativos;
	}
	
    
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

