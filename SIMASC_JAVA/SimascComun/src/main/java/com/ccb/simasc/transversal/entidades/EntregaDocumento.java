package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import javax.persistence.NamedQuery; 
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import java.util.Date;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="ENTREGA_DOCUMENTO")
@NamedQuery(name = "EntregaDocumento.findAll", query = "SELECT p FROM EntregaDocumento p")
public class EntregaDocumento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_ENTREGA_DOCUMENTO_PK_ID_DOCUMENTO = "entregaDocumentoPK.idDocumento";
			
	public static final String ENTIDAD_ENTREGA_DOCUMENTO_PK_ID_PERSONA = "entregaDocumentoPK.idPersona";
	public static final String ENTIDAD_ENTREGA_DOCUMENTO_FECHA_ENTREGA = "fechaEntrega";
	public static final String ENTIDAD_ENTREGA_DOCUMENTO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_ENTREGA_DOCUMENTO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_ENTREGA_DOCUMENTO_ESTADO_REGISTRO_ENTREGADOCUMENTO = "estadoRegistroEntregaDocumento";			
	public static final String ENTIDAD_ENTREGA_DOCUMENTO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_ENTREGA_DOCUMENTO
            = {ENTIDAD_ENTREGA_DOCUMENTO_PK_ID_DOCUMENTO, ENTIDAD_ENTREGA_DOCUMENTO_FECHA_ENTREGA, ENTIDAD_ENTREGA_DOCUMENTO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_ENTREGA_DOCUMENTO_ESTADO_REGISTRO, ENTIDAD_ENTREGA_DOCUMENTO_ID_USUARIO_MODIFICACION, ENTIDAD_ENTREGA_DOCUMENTO_PK_ID_PERSONA};

	@EmbeddedId
	private EntregaDocumentoPK entregaDocumentoPK;
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_entrega")
	private Date fechaEntrega;		
    
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
		
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	
	
    public EntregaDocumento(){
		entregaDocumentoPK = new EntregaDocumentoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public EntregaDocumentoPK getEntregaDocumentoPK(){
		return this.entregaDocumentoPK;
	}
	
	public void setEntregaDocumentoPK(EntregaDocumentoPK entregaDocumentoPK){
		this.entregaDocumentoPK   = entregaDocumentoPK ;
	}  
	
	public Date getFechaEntrega(){
		return this.fechaEntrega;
	}
	
	public void setFechaEntrega(Date fechaEntrega){
		this.fechaEntrega = fechaEntrega;
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
		

    public Documento getDocumento(){
		return this.documento; 
	}
	
	public void setDocumento(Documento documento){
		this.documento = documento;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_ENTREGA_DOCUMENTO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.entregaDocumentoPK);        
        hash = 37 * hash + Objects.hashCode(this.fechaEntrega);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad EntregaDocumento que se pasa
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
        final EntregaDocumento other = (EntregaDocumento) obj;
        
        if (!Objects.equals(this.entregaDocumentoPK, other.entregaDocumentoPK)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaEntrega, other.fechaEntrega)) {
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

