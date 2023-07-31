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
@Table(name="RECURSO_LAUDO")
@NamedQuery(name = "RecursoLaudo.findAll", query = "SELECT p FROM RecursoLaudo p")
public class RecursoLaudo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_RECURSO_LAUDO_PK = "idRecursoLaudo";
	public static final String ENTIDAD_RECURSO_LAUDO_ID_LAUDO = "idLaudo";
	public static final String ENTIDAD_RECURSO_LAUDO_TIPO = "tipo";
	public static final String ENTIDAD_RECURSO_LAUDO_PARTE_QUE_INTERPONE = "parteQueInterpone";
	public static final String ENTIDAD_RECURSO_LAUDO_FECHA = "fecha";
	public static final String ENTIDAD_RECURSO_LAUDO_SE_PRESENTO_EN_TERMINO = "sePresentoEnTermino";
	public static final String ENTIDAD_RECURSO_LAUDO_CAUSAL_DE_RECURSO_DE_ANULACION = "causalDeRecursoDeAnulacion";
	public static final String ENTIDAD_RECURSO_LAUDO_FECHA_REMISION = "fechaRemision";
	public static final String ENTIDAD_RECURSO_LAUDO_ENTIDAD_JUDICIAL = "entidadJudicial";
	public static final String ENTIDAD_RECURSO_LAUDO_DESPACHO_ASIGNADO = "despachoAsignado";
	public static final String ENTIDAD_RECURSO_LAUDO_NOMBRE_MAGISTRADO = "nombreMagistrado";
	public static final String ENTIDAD_RECURSO_LAUDO_RESULTADO = "resultado";
	public static final String ENTIDAD_RECURSO_LAUDO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_RECURSO_LAUDO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_RECURSO_LAUDO_ESTADO_REGISTRO_RECURSOLAUDO = "estadoRegistroRecursoLaudo";			
	public static final String ENTIDAD_RECURSO_LAUDO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_RECURSO_LAUDO_ID_DOCUMENTO = "idDocumento";
    private static final String[] ATRIBUTOS_ENTIDAD_RECURSO_LAUDO
            = {ENTIDAD_RECURSO_LAUDO_FECHA_REMISION, ENTIDAD_RECURSO_LAUDO_ID_USUARIO_MODIFICACION, ENTIDAD_RECURSO_LAUDO_ID_DOCUMENTO, ENTIDAD_RECURSO_LAUDO_TIPO, ENTIDAD_RECURSO_LAUDO_NOMBRE_MAGISTRADO, ENTIDAD_RECURSO_LAUDO_FECHA, ENTIDAD_RECURSO_LAUDO_ID_LAUDO, ENTIDAD_RECURSO_LAUDO_RESULTADO, ENTIDAD_RECURSO_LAUDO_SE_PRESENTO_EN_TERMINO, ENTIDAD_RECURSO_LAUDO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_RECURSO_LAUDO_PK, ENTIDAD_RECURSO_LAUDO_DESPACHO_ASIGNADO, ENTIDAD_RECURSO_LAUDO_PARTE_QUE_INTERPONE, ENTIDAD_RECURSO_LAUDO_ENTIDAD_JUDICIAL, ENTIDAD_RECURSO_LAUDO_CAUSAL_DE_RECURSO_DE_ANULACION, ENTIDAD_RECURSO_LAUDO_ESTADO_REGISTRO};

	@Id
    @Column(name="id_recurso_laudo")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idRecursoLaudo;
    
	@Column(name="id_laudo")
	private Long idLaudo;		
    
	@Column(name="tipo")
	private String tipo;		
    
	@Column(name="parte_que_interpone")
	private String parteQueInterpone;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha")
	private Date fecha;		
    
	@Column(name="se_presento_en_termino")
	private boolean sePresentoEnTermino;		
    
	@Column(name="causal_de_recurso_de_anulacion")
	private String causalDeRecursoDeAnulacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_remision")
	private Date fechaRemision;		
    
	@Column(name="entidad_judicial")
	private String entidadJudicial;		
    
	@Column(name="despacho_asignado")
	private String despachoAsignado;		
    
	@Column(name="nombre_magistrado")
	private String nombreMagistrado;		
    
	@Column(name="resultado")
	private String resultado;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_documento")
	private Long idDocumento;		

	@ManyToOne
	@JoinColumn(name="id_documento", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documento;
		
	@ManyToOne
	@JoinColumn(name="id_laudo", referencedColumnName="id_laudo", insertable = false, updatable = false)
    private Laudo laudo;
		
	
	
    public RecursoLaudo(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdRecursoLaudo(){
		return this.idRecursoLaudo;
	}
	
	public void setIdRecursoLaudo(Long idRecursoLaudo){
		this.idRecursoLaudo = idRecursoLaudo;
	}
	
	public Long getIdLaudo(){
		return this.idLaudo;
	}
	
	public void setIdLaudo(Long idLaudo){
		this.idLaudo = idLaudo;
	}
		
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		
	public String getParteQueInterpone(){
		return this.parteQueInterpone;
	}
	
	public void setParteQueInterpone(String parteQueInterpone){
		this.parteQueInterpone = parteQueInterpone;
	}
		
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
		
	public boolean getSePresentoEnTermino(){
		return this.sePresentoEnTermino;
	}
	
	public void setSePresentoEnTermino(boolean sePresentoEnTermino){
		this.sePresentoEnTermino = sePresentoEnTermino;
	}
		
	public String getCausalDeRecursoDeAnulacion(){
		return this.causalDeRecursoDeAnulacion;
	}
	
	public void setCausalDeRecursoDeAnulacion(String causalDeRecursoDeAnulacion){
		this.causalDeRecursoDeAnulacion = causalDeRecursoDeAnulacion;
	}
		
	public Date getFechaRemision(){
		return this.fechaRemision;
	}
	
	public void setFechaRemision(Date fechaRemision){
		this.fechaRemision = fechaRemision;
	}
		
	public String getEntidadJudicial(){
		return this.entidadJudicial;
	}
	
	public void setEntidadJudicial(String entidadJudicial){
		this.entidadJudicial = entidadJudicial;
	}
		
	public String getDespachoAsignado(){
		return this.despachoAsignado;
	}
	
	public void setDespachoAsignado(String despachoAsignado){
		this.despachoAsignado = despachoAsignado;
	}
		
	public String getNombreMagistrado(){
		return this.nombreMagistrado;
	}
	
	public void setNombreMagistrado(String nombreMagistrado){
		this.nombreMagistrado = nombreMagistrado;
	}
		
	public String getResultado(){
		return this.resultado;
	}
	
	public void setResultado(String resultado){
		this.resultado = resultado;
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
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
		

    public Documento getDocumento(){
		return this.documento; 
	}
	
	public void setDocumento(Documento documento){
		this.documento = documento;
	}
    public Laudo getLaudo(){
		return this.laudo; 
	}
	
	public void setLaudo(Laudo laudo){
		this.laudo = laudo;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_RECURSO_LAUDO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idRecursoLaudo);        
        hash = 37 * hash + Objects.hashCode(this.idLaudo);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.parteQueInterpone);
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + (this.sePresentoEnTermino ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.causalDeRecursoDeAnulacion);
        hash = 37 * hash + Objects.hashCode(this.fechaRemision);
        hash = 37 * hash + Objects.hashCode(this.entidadJudicial);
        hash = 37 * hash + Objects.hashCode(this.despachoAsignado);
        hash = 37 * hash + Objects.hashCode(this.nombreMagistrado);
        hash = 37 * hash + Objects.hashCode(this.resultado);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RecursoLaudo que se pasa
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
        final RecursoLaudo other = (RecursoLaudo) obj;
        
        if (!Objects.equals(this.idRecursoLaudo, other.idRecursoLaudo)) {
            return false;
        }
        
        if (!Objects.equals(this.idLaudo, other.idLaudo)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        if (!Objects.equals(this.parteQueInterpone, other.parteQueInterpone)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (!Objects.equals(this.sePresentoEnTermino, other.sePresentoEnTermino)) {
            return false;
        }
        
        if (!Objects.equals(this.causalDeRecursoDeAnulacion, other.causalDeRecursoDeAnulacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaRemision, other.fechaRemision)) {
            return false;
        }
        
        if (!Objects.equals(this.entidadJudicial, other.entidadJudicial)) {
            return false;
        }
        
        if (!Objects.equals(this.despachoAsignado, other.despachoAsignado)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreMagistrado, other.nombreMagistrado)) {
            return false;
        }
        
        if (!Objects.equals(this.resultado, other.resultado)) {
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
        
        return Objects.equals(this.idDocumento, other.idDocumento);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

