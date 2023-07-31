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
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="AUDITORIA_SISTEMA_EXTERNO")
@NamedQuery(name = "AuditoriaSistemaExterno.findAll", query = "SELECT p FROM AuditoriaSistemaExterno p")
public class AuditoriaSistemaExterno implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_PK = "idAuditoriaSistemaExterno";
	public static final String ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_SERVICIO = "servicio";
	public static final String ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_ID_CASO = "idCaso";
	public static final String ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_OPERACION_SISTEMA_EXTERNO = "operacionSistemaExterno";
	public static final String ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_FECHA_OPERACION = "fechaOperacion";
	public static final String ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_USUARIO_OPERACION = "usuarioOperacion";
	public static final String ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_ID_CASO_MINJUSTICIA = "idCasoMinjusticia";
	public static final String ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_ESTADO_REGISTRO_AUDITORIASISTEMAEXTERNO = "estadoRegistroAuditoriaSistemaExterno";			
	public static final String ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_AUDITORIA_SISTEMA_EXTERNO
            = {ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_ID_CASO_MINJUSTICIA, ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_USUARIO_OPERACION, ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_ESTADO_REGISTRO, ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_ID_USUARIO_MODIFICACION, ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_ID_CASO, ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_SERVICIO, ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_OPERACION_SISTEMA_EXTERNO, ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_PK, ENTIDAD_AUDITORIA_SISTEMA_EXTERNO_FECHA_OPERACION};

	@Id
    @Column(name="id_auditoria_sistema_externo")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idAuditoriaSistemaExterno;
    
	@Column(name="servicio")
	private String servicio;		
    
	@Column(name="id_caso")
	private Long idCaso;		
    
	@Column(name="operacion_sistema_externo")
	private String operacionSistemaExterno;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_operacion")
	private Date fechaOperacion;		
    
	@Column(name="usuario_operacion")
	private String usuarioOperacion;		
    
	@Column(name="id_caso_minjusticia")
	private String idCasoMinjusticia;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	
	
    public AuditoriaSistemaExterno(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdAuditoriaSistemaExterno(){
		return this.idAuditoriaSistemaExterno;
	}
	
	public void setIdAuditoriaSistemaExterno(Long idAuditoriaSistemaExterno){
		this.idAuditoriaSistemaExterno = idAuditoriaSistemaExterno;
	}
	
	public String getServicio(){
		return this.servicio;
	}
	
	public void setServicio(String servicio){
		this.servicio = servicio;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public String getOperacionSistemaExterno(){
		return this.operacionSistemaExterno;
	}
	
	public void setOperacionSistemaExterno(String operacionSistemaExterno){
		this.operacionSistemaExterno = operacionSistemaExterno;
	}
		
	public Date getFechaOperacion(){
		return this.fechaOperacion;
	}
	
	public void setFechaOperacion(Date fechaOperacion){
		this.fechaOperacion = fechaOperacion;
	}
		
	public String getUsuarioOperacion(){
		return this.usuarioOperacion;
	}
	
	public void setUsuarioOperacion(String usuarioOperacion){
		this.usuarioOperacion = usuarioOperacion;
	}
		
	public String getIdCasoMinjusticia(){
		return this.idCasoMinjusticia;
	}
	
	public void setIdCasoMinjusticia(String idCasoMinjusticia){
		this.idCasoMinjusticia = idCasoMinjusticia;
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
		


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_AUDITORIA_SISTEMA_EXTERNO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idAuditoriaSistemaExterno);        
        hash = 37 * hash + Objects.hashCode(this.servicio);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.operacionSistemaExterno);
        hash = 37 * hash + Objects.hashCode(this.fechaOperacion);
        hash = 37 * hash + Objects.hashCode(this.usuarioOperacion);
        hash = 37 * hash + Objects.hashCode(this.idCasoMinjusticia);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AuditoriaSistemaExterno que se pasa
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
        final AuditoriaSistemaExterno other = (AuditoriaSistemaExterno) obj;
        
        if (!Objects.equals(this.idAuditoriaSistemaExterno, other.idAuditoriaSistemaExterno)) {
            return false;
        }
        
        if (!Objects.equals(this.servicio, other.servicio)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.operacionSistemaExterno, other.operacionSistemaExterno)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaOperacion, other.fechaOperacion)) {
            return false;
        }
        
        if (!Objects.equals(this.usuarioOperacion, other.usuarioOperacion)) {
            return false;
        }
        
        if (!Objects.equals(this.idCasoMinjusticia, other.idCasoMinjusticia)) {
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

