package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

import com.ccb.simasc.transversal.dto.SolicitudServicioDTO;
import com.ccb.simasc.transversal.dto.formularios.CondicionesGeneralesDTO;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="SOLICITUD_SERVICIO")
@NamedQuery(name = "SolicitudServicio.findAll", query = "SELECT p FROM SolicitudServicio p")
public class SolicitudServicio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_SOLICITUD_SERVICIO_PK = "idSolicitudServicio";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_TIPO_CUANTIA = "tipoCuantia";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_CUANTIA = "cuantia";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_HECHOS = "hechos";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_PRETENSIONES = "pretensiones";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_INICIO_DE_CONFLICTO = "inicioDeConflicto";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_ID_LUGAR_CONFLICTO = "idLugarConflicto";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_PARTE_QUE_SOLICITA_SERVICIO = "parteQueSolicitaServicio";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_ID_SEDE = "idSede";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_ID_SERVICIO = "idServicio";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_ID_MATERIA = "idMateria";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_FECHA_INICIO_AUDIENCIA = "fechaInicioAudiencia";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_FECHA_FIN_AUDIENCIA = "fechaFinAudiencia";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_TIPO_DE_AUDIENCIA = "tipoDeAudiencia";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_ID_ORDEN_DE_PAGO = "idOrdenDePago";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_ESTADO_REGISTRO_SOLICITUDSERVICIO = "estadoRegistroSolicitudServicio";			
	public static final String ENTIDAD_SOLICITUD_SERVICIO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_ID_AUTORIZACION_TRATAMIENTO_DATOS = "idAutorizacionTratamientoDatos";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_TIPO_PERITAJE = "tipoPeritaje";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_TIPO_TRAMITE = "tipoTramite";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_ID_CASO_ANTERIOR = "idCasoAnterior";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_PAGO_MEDIACION = "pagoMediacion";
	public static final String ENTIDAD_SOLICITUD_SERVICIO_MEDIDAS_CAUTELARES = "medidasCautelares";
    private static final String[] ATRIBUTOS_ENTIDAD_SOLICITUD_SERVICIO
            = {ENTIDAD_SOLICITUD_SERVICIO_CUANTIA, ENTIDAD_SOLICITUD_SERVICIO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_SOLICITUD_SERVICIO_ID_USUARIO_MODIFICACION, ENTIDAD_SOLICITUD_SERVICIO_ESTADO_REGISTRO, ENTIDAD_SOLICITUD_SERVICIO_PK, ENTIDAD_SOLICITUD_SERVICIO_PARTE_QUE_SOLICITA_SERVICIO, ENTIDAD_SOLICITUD_SERVICIO_HECHOS, ENTIDAD_SOLICITUD_SERVICIO_FECHA_INICIO_AUDIENCIA, ENTIDAD_SOLICITUD_SERVICIO_ID_AUTORIZACION_TRATAMIENTO_DATOS, ENTIDAD_SOLICITUD_SERVICIO_TIPO_DE_AUDIENCIA, ENTIDAD_SOLICITUD_SERVICIO_PRETENSIONES, ENTIDAD_SOLICITUD_SERVICIO_FECHA_FIN_AUDIENCIA, ENTIDAD_SOLICITUD_SERVICIO_ID_ORDEN_DE_PAGO, ENTIDAD_SOLICITUD_SERVICIO_TIPO_CUANTIA, ENTIDAD_SOLICITUD_SERVICIO_ID_LUGAR_CONFLICTO, ENTIDAD_SOLICITUD_SERVICIO_FECHA_CREACION, ENTIDAD_SOLICITUD_SERVICIO_ID_SERVICIO, ENTIDAD_SOLICITUD_SERVICIO_ID_SEDE, ENTIDAD_SOLICITUD_SERVICIO_INICIO_DE_CONFLICTO, ENTIDAD_SOLICITUD_SERVICIO_ID_MATERIA,ENTIDAD_SOLICITUD_SERVICIO_TIPO_PERITAJE,ENTIDAD_SOLICITUD_SERVICIO_TIPO_TRAMITE,ENTIDAD_SOLICITUD_SERVICIO_ID_CASO_ANTERIOR,ENTIDAD_SOLICITUD_SERVICIO_PAGO_MEDIACION,ENTIDAD_SOLICITUD_SERVICIO_MEDIDAS_CAUTELARES};

	@Id
    @Column(name="id_solicitud_servicio")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idSolicitudServicio;
    
	@Column(name="tipo_cuantia")
	private String tipoCuantia;		
    
	@Column(name="cuantia")
	private String cuantia;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;		
    
	@Column(name="hechos")
	private String hechos;		
    
	@Column(name="pretensiones")
	private String pretensiones;		
    
	@Column(name="inicio_de_conflicto")
	private String inicioDeConflicto;		
    
	@Column(name="id_lugar_conflicto")
	private String idLugarConflicto;		
    
	@Column(name="parte_que_solicita_servicio")
	private String parteQueSolicitaServicio;		
    
	@Column(name="id_sede")
	private Long idSede;		
    
	@Column(name="id_servicio")
	private Long idServicio;		
    
	@Column(name="id_materia")
	private Long idMateria;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_inicio_audiencia")
	private Date fechaInicioAudiencia;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_fin_audiencia")
	private Date fechaFinAudiencia;		
    
	@Column(name="tipo_de_audiencia")
	private String tipoDeAudiencia;		
    
	@Column(name="id_orden_de_pago")
	private Long idOrdenDePago;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_autorizacion_tratamiento_datos")
	private Long idAutorizacionTratamientoDatos;	
	
	@Column(name="tipo_peritaje")
	private String tipoPeritaje;
	
	@Column(name="tipo_tramite")
	private String tipoTramite;
	
	@Column(name="id_caso_anterior")
	private Long idCasoAnterior;
	
	@Column(name="medidas_cautelares")
	private boolean medidasCautelares;
	
	@Column(name="pago_mediacion")
	private BigDecimal pagoMediacion;
	
	@Column(name="valor_mora")
	private String valorMora;
	
	@Column(name="cant_acreedor")
	private String cantAcreedor;
	
	@Column(name="cant_deuda")
	private String cantDeuda;

	@Column(name="domicilio")
	private String domicilio;
	
	@Column(name="saldo_capital")
	private String saldoCapital;
	
	@Column(name="tipo_persona")
	private String tipoPersona;
	
	@Column(name="arbitraje_consumo")
	private boolean arbitrajeConsumo;
	
	@Column(name="tipo_conflicto")
	private String tipoConflicto;
	
	@Column(name="entero_servicio")
	private String enteroServicio;
	
	@ManyToOne
	@JoinColumn(name="id_autorizacion_tratamiento_datos", referencedColumnName="id_autorizacion_tratamiento_datos", insertable = false, updatable = false)
    private AutorizacionTratamientoDatos autorizacionTratamientoDatos;
		
	@ManyToOne
	@JoinColumn(name="id_sede", referencedColumnName="id_sede", insertable = false, updatable = false)
    private Sede sede;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_servicio", referencedColumnName="id_servicio", insertable = false, updatable = false),
	    @JoinColumn(name = "id_materia", referencedColumnName="id_materia", insertable = false, updatable = false)	    
	})		
    private ServicioMateria servicioMateria;
		
	@ManyToOne
	@JoinColumn(name="id_lugar_conflicto", referencedColumnName="id_zona_geografica", insertable = false, updatable = false)
    private ZonaGeografica zonaGeografica;
		
	@OneToMany(mappedBy="solicitudServicio")
    private List<Caso> casoList;
	@OneToMany(mappedBy="solicitudServicio")
    private List<Documento> documentoList;
	@OneToMany(mappedBy="solicitudServicio")
    private List<PersonaSolicitud> personaSolicitudList;
	
	
    public SolicitudServicio(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdSolicitudServicio(){
		return this.idSolicitudServicio;
	}
	
	public void setIdSolicitudServicio(Long idSolicitudServicio){
		this.idSolicitudServicio = idSolicitudServicio;
	}
	
	public String getTipoCuantia(){
		return this.tipoCuantia;
	}
	
	public void setTipoCuantia(String tipoCuantia){
		this.tipoCuantia = tipoCuantia;
	}
		
	public String getCuantia(){
		return this.cuantia;
	}
	
	public void setCuantia(String cuantia){
		this.cuantia = cuantia;
	}
		
	public Date getFechaCreacion(){
		return this.fechaCreacion;
	}
	
	public void setFechaCreacion(Date fechaCreacion){
		this.fechaCreacion = fechaCreacion;
	}
		
	public String getHechos(){
		return this.hechos;
	}
	
	public void setHechos(String hechos){
		this.hechos = hechos;
	}
		
	public String getPretensiones(){
		return this.pretensiones;
	}
	
	public void setPretensiones(String pretensiones){
		this.pretensiones = pretensiones;
	}
		
	public String getInicioDeConflicto(){
		return this.inicioDeConflicto;
	}
	
	public void setInicioDeConflicto(String inicioDeConflicto){
		this.inicioDeConflicto = inicioDeConflicto;
	}
		
	public String getIdLugarConflicto(){
		return this.idLugarConflicto;
	}
	
	public void setIdLugarConflicto(String idLugarConflicto){
		this.idLugarConflicto = idLugarConflicto;
	}
		
	public String getParteQueSolicitaServicio(){
		return this.parteQueSolicitaServicio;
	}
	
	public void setParteQueSolicitaServicio(String parteQueSolicitaServicio){
		this.parteQueSolicitaServicio = parteQueSolicitaServicio;
	}
		
	public Long getIdSede(){
		return this.idSede;
	}
	
	public void setIdSede(Long idSede){
		this.idSede = idSede;
	}
		
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		
	public Long getIdMateria(){
		return this.idMateria;
	}
	
	public void setIdMateria(Long idMateria){
		this.idMateria = idMateria;
	}
		
	public Date getFechaInicioAudiencia(){
		return this.fechaInicioAudiencia;
	}
	
	public void setFechaInicioAudiencia(Date fechaInicioAudiencia){
		this.fechaInicioAudiencia = fechaInicioAudiencia;
	}
		
	public Date getFechaFinAudiencia(){
		return this.fechaFinAudiencia;
	}
	
	public void setFechaFinAudiencia(Date fechaFinAudiencia){
		this.fechaFinAudiencia = fechaFinAudiencia;
	}
		
	public String getTipoDeAudiencia(){
		return this.tipoDeAudiencia;
	}
	
	public void setTipoDeAudiencia(String tipoDeAudiencia){
		this.tipoDeAudiencia = tipoDeAudiencia;
	}
		
	public Long getIdOrdenDePago(){
		return this.idOrdenDePago;
	}
	
	public void setIdOrdenDePago(Long idOrdenDePago){
		this.idOrdenDePago = idOrdenDePago;
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
		
	public Long getIdAutorizacionTratamientoDatos(){
		return this.idAutorizacionTratamientoDatos;
	}
	
	public void setIdAutorizacionTratamientoDatos(Long idAutorizacionTratamientoDatos){
		this.idAutorizacionTratamientoDatos = idAutorizacionTratamientoDatos;
	}
		

    public List<Caso> getCasoList(){
		return this.casoList;
	}
	
	public void setCasoList(List<Caso> casoList){
		this.casoList = casoList;
	}
			
    public List<Documento> getDocumentoList(){
		return this.documentoList;
	}
	
	public void setDocumentoList(List<Documento> documentoList){
		this.documentoList = documentoList;
	}
			
    public List<PersonaSolicitud> getPersonaSolicitudList(){
		return this.personaSolicitudList;
	}
	
	public void setPersonaSolicitudList(List<PersonaSolicitud> personaSolicitudList){
		this.personaSolicitudList = personaSolicitudList;
	}
			
    public AutorizacionTratamientoDatos getAutorizacionTratamientoDatos(){
		return this.autorizacionTratamientoDatos; 
	}
	
	public void setAutorizacionTratamientoDatos(AutorizacionTratamientoDatos autorizacionTratamientoDatos){
		this.autorizacionTratamientoDatos = autorizacionTratamientoDatos;
	}
    public Sede getSede(){
		return this.sede; 
	}
	
	public void setSede(Sede sede){
		this.sede = sede;
	}
    public ServicioMateria getServicioMateria(){
		return this.servicioMateria; 
	}
	
	public void setServicioMateria(ServicioMateria servicioMateria){
		this.servicioMateria = servicioMateria;
	}
    public ZonaGeografica getZonaGeografica(){
		return this.zonaGeografica; 
	}
	
	public void setZonaGeografica(ZonaGeografica zonaGeografica){
		this.zonaGeografica = zonaGeografica;
	}

	public String getTipoPeritaje() {
		return tipoPeritaje;
	}


	public void setTipoPeritaje(String tipoPeritaje) {
		this.tipoPeritaje = tipoPeritaje;
	}
	

	public String getTipoTramite() {
		return tipoTramite;
	}


	public void setTipoTramite(String tipoTramite) {
		this.tipoTramite = tipoTramite;
	}


	public Long getIdCasoAnterior() {
		return idCasoAnterior;
	}


	public void setIdCasoAnterior(Long idCasoAnterior) {
		this.idCasoAnterior = idCasoAnterior;
	}

	public boolean isMedidasCautelares() {
		return medidasCautelares;
	}


	public void setMedidasCautelares(boolean medidasCautelares) {
		this.medidasCautelares = medidasCautelares;
	}


	public BigDecimal getPagoMediacion() {
		return pagoMediacion;
	}


	public void setPagoMediacion(BigDecimal pagoMediacion) {
		this.pagoMediacion = pagoMediacion;
	}
	
	public boolean isArbitrajeConsumo() {
		return arbitrajeConsumo;
	}


	public void setArbitrajeConsumo(boolean arbitrajeConsumo) {
		this.arbitrajeConsumo = arbitrajeConsumo;
	}


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_SOLICITUD_SERVICIO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idSolicitudServicio);        
        hash = 37 * hash + Objects.hashCode(this.tipoCuantia);
        hash = 37 * hash + Objects.hashCode(this.cuantia);
        hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
        hash = 37 * hash + Objects.hashCode(this.hechos);
        hash = 37 * hash + Objects.hashCode(this.pretensiones);
        hash = 37 * hash + Objects.hashCode(this.inicioDeConflicto);
        hash = 37 * hash + Objects.hashCode(this.idLugarConflicto);
        hash = 37 * hash + Objects.hashCode(this.parteQueSolicitaServicio);
        hash = 37 * hash + Objects.hashCode(this.idSede);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        hash = 37 * hash + Objects.hashCode(this.idMateria);
        hash = 37 * hash + Objects.hashCode(this.fechaInicioAudiencia);
        hash = 37 * hash + Objects.hashCode(this.fechaFinAudiencia);
        hash = 37 * hash + Objects.hashCode(this.tipoDeAudiencia);
        hash = 37 * hash + Objects.hashCode(this.idOrdenDePago);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idAutorizacionTratamientoDatos);
        hash = 37 * hash + Objects.hashCode(this.tipoPeritaje);
        hash = 37 * hash + Objects.hashCode(this.tipoTramite);
        hash = 37 * hash + Objects.hashCode(this.idCasoAnterior);
        hash = 37 * hash + Objects.hashCode(this.pagoMediacion);
        hash = 37 * hash + Objects.hashCode(this.medidasCautelares);
        hash = 37 * hash + Objects.hashCode(this.arbitrajeConsumo); 
        hash = 37 * hash + Objects.hashCode(this.tipoConflicto);
        hash = 37 * hash + Objects.hashCode(this.enteroServicio); 
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad SolicitudServicio que se pasa
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
        final SolicitudServicio other = (SolicitudServicio) obj;
        
        if (!Objects.equals(this.idSolicitudServicio, other.idSolicitudServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoCuantia, other.tipoCuantia)) {
            return false;
        }
        
        if (!Objects.equals(this.cuantia, other.cuantia)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
            return false;
        }
        
        if (!Objects.equals(this.hechos, other.hechos)) {
            return false;
        }
        
        if (!Objects.equals(this.pretensiones, other.pretensiones)) {
            return false;
        }
        
        if (!Objects.equals(this.inicioDeConflicto, other.inicioDeConflicto)) {
            return false;
        }
        
        if (!Objects.equals(this.idLugarConflicto, other.idLugarConflicto)) {
            return false;
        }
        
        if (!Objects.equals(this.parteQueSolicitaServicio, other.parteQueSolicitaServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.idSede, other.idSede)) {
            return false;
        }
        
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.idMateria, other.idMateria)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicioAudiencia, other.fechaInicioAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFinAudiencia, other.fechaFinAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDeAudiencia, other.tipoDeAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.idOrdenDePago, other.idOrdenDePago)) {
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
        if (!Objects.equals(this.tipoPeritaje, other.tipoPeritaje)) {
            return false;
        }        
        if (!Objects.equals(this.tipoTramite, other.tipoTramite)) {
            return false;
        }
        if (!Objects.equals(this.idCasoAnterior, other.idCasoAnterior)) {
            return false;
        }
        if (!Objects.equals(this.pagoMediacion, other.pagoMediacion)) {
            return false;
        }
        if (!Objects.equals(this.medidasCautelares, other.medidasCautelares)) {
            return false;
        }
        if (!Objects.equals(this.arbitrajeConsumo, other.arbitrajeConsumo)) {
            return false;
        }
        if (!Objects.equals(this.tipoConflicto, other.tipoConflicto)) {
            return false;
        }
        if (!Objects.equals(this.enteroServicio, other.enteroServicio)) {
            return false;
        }
        
        return Objects.equals(this.idAutorizacionTratamientoDatos, other.idAutorizacionTratamientoDatos);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
    
    /**
	 * Metodo encargado de convertir la entidad SolicitudServicio a DTO
	 * SolicitudServicioDTO
	 * 
	 * @param solicitudServicio
	 * @return SolicitudServicioDTO
	 */
	public SolicitudServicioDTO convertirSolicitudServicioEntidadADTO(SolicitudServicio solicitudServicio) {
		SolicitudServicioDTO solicitudServicioDTO = new SolicitudServicioDTO();
		solicitudServicioDTO.setIdSolicitudServicio(solicitudServicio.getIdSolicitudServicio());
		solicitudServicioDTO.setHechos(solicitudServicio.getHechos());
		solicitudServicioDTO.setPretensiones(solicitudServicio.getPretensiones());
		solicitudServicioDTO.setTipoCuantia(solicitudServicio.getTipoCuantia());
		solicitudServicioDTO.setCuantia(solicitudServicio.getCuantia());
		solicitudServicioDTO.setInicioDeConflicto(solicitudServicio.getInicioDeConflicto());
		solicitudServicioDTO.setIdLugarConflicto(solicitudServicio.getIdLugarConflicto());
		solicitudServicioDTO.setParteQueSolicitaServicio(solicitudServicio.getParteQueSolicitaServicio());
		solicitudServicioDTO.setIdSede(solicitudServicio.getIdSede());
		solicitudServicioDTO.setIdServicio(solicitudServicio.getIdServicio());
		solicitudServicioDTO.setIdMateria(solicitudServicio.getIdMateria());
		solicitudServicioDTO.setFechaCreacion(solicitudServicio.getFechaCreacion());
		solicitudServicioDTO.setIdUsuarioModificacion(solicitudServicio.getIdUsuarioModificacion());
		solicitudServicioDTO.setFechaUltimaModificacion(solicitudServicio.getFechaUltimaModificacion());
		solicitudServicioDTO.setEstadoRegistro(solicitudServicio.getEstadoRegistro());
		solicitudServicioDTO.setIdAutorizacionTratamientoDatos(solicitudServicio.getIdAutorizacionTratamientoDatos());
		solicitudServicioDTO.setTipoPeritaje(solicitudServicio.getTipoPeritaje());
		solicitudServicioDTO.setIdOrdenDePago(solicitudServicio.getIdOrdenDePago());

		solicitudServicioDTO.setFechaInicioAudiencia(solicitudServicio.getFechaInicioAudiencia() != null
				? solicitudServicio.getFechaInicioAudiencia() : null);
		solicitudServicioDTO.setFechaFinAudiencia(
				solicitudServicio.getFechaFinAudiencia() != null ? solicitudServicio.getFechaFinAudiencia() : null);
		solicitudServicioDTO.setTipoDeAudiencia(
				solicitudServicio.getTipoDeAudiencia() != null ? solicitudServicio.getTipoDeAudiencia() : null);
		solicitudServicioDTO.setTipoTramite(solicitudServicio.getTipoTramite());
		solicitudServicioDTO.setIdCasoAnterior(solicitudServicio.getIdCasoAnterior());
		solicitudServicioDTO.setPagoMediacion(solicitudServicio.getPagoMediacion());
		solicitudServicioDTO.setMedidasCautelares(solicitudServicio.isMedidasCautelares());
		solicitudServicioDTO.setArbitrajeConsumo(solicitudServicio.isArbitrajeConsumo());
		solicitudServicioDTO.setTipoConflicto(solicitudServicio.getTipoConflicto());
		solicitudServicioDTO.setEnteroServicio(solicitudServicio.getEnteroServicio());
		
		CondicionesGeneralesDTO condiciones = new CondicionesGeneralesDTO();
		
		condiciones.setCantAcreedor(solicitudServicio.getCantAcreedor()!= null ? solicitudServicio.getCantAcreedor() : null);
		condiciones.setCantDeuda(solicitudServicio.getCantDeuda()!= null ? solicitudServicio.getCantDeuda() : null);
		condiciones.setDomicilio(solicitudServicio.getDomicilio()!= null ? solicitudServicio.getDomicilio() : null);
		condiciones.setSaldoCapital(solicitudServicio.getSaldoCapital()!= null ? solicitudServicio.getSaldoCapital() : null);
		condiciones.setTipoPersona(solicitudServicio.getTipoPersona()!= null ? solicitudServicio.getTipoPersona() : null);
		condiciones.setValorMora(solicitudServicio.getValorMora()!= null ? solicitudServicio.getValorMora() : null);		
		solicitudServicioDTO.setCondicionesGeneralesDTO(condiciones);
		
		return solicitudServicioDTO;
	}


	public String getValorMora() {
		return valorMora;
	}


	public void setValorMora(String valorMora) {
		this.valorMora = valorMora;
	}


	public String getCantAcreedor() {
		return cantAcreedor;
	}


	public void setCantAcreedor(String cantAcreedor) {
		this.cantAcreedor = cantAcreedor;
	}


	public String getCantDeuda() {
		return cantDeuda;
	}


	public void setCantDeuda(String cantDeuda) {
		this.cantDeuda = cantDeuda;
	}


	public String getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}


	public String getSaldoCapital() {
		return saldoCapital;
	}


	public void setSaldoCapital(String saldoCapital) {
		this.saldoCapital = saldoCapital;
	}


	public String getTipoPersona() {
		return tipoPersona;
	}


	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}


	public String getTipoConflicto() {
		return tipoConflicto;
	}


	public void setTipoConflicto(String tipoConflicto) {
		this.tipoConflicto = tipoConflicto;
	}


	public String getEnteroServicio() {
		return enteroServicio;
	}


	public void setEnteroServicio(String enteroServicio) {
		this.enteroServicio = enteroServicio;
	}

	// protected region metodos adicionales end

} 

