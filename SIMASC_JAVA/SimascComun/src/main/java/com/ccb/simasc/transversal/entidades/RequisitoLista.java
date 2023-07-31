package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name="REQUISITO_LISTA")
@NamedQuery(name = "RequisitoLista.findAll", query = "SELECT p FROM RequisitoLista p")
public class RequisitoLista implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_REQUISITO_LISTA_PK_ID_LISTA = "requisitoListaPK.idLista";
			
	public static final String ENTIDAD_REQUISITO_LISTA_PK_ID_REQUISITO = "requisitoListaPK.idRequisito";
	public static final String ENTIDAD_REQUISITO_LISTA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_REQUISITO_LISTA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_REQUISITO_LISTA_ESTADO_REGISTRO_REQUISITOLISTA = "estadoRegistroRequisitoLista";			
	public static final String ENTIDAD_REQUISITO_LISTA_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_REQUISITO_LISTA
            = {ENTIDAD_REQUISITO_LISTA_ID_USUARIO_MODIFICACION, ENTIDAD_REQUISITO_LISTA_PK_ID_LISTA, ENTIDAD_REQUISITO_LISTA_PK_ID_REQUISITO, ENTIDAD_REQUISITO_LISTA_ESTADO_REGISTRO, ENTIDAD_REQUISITO_LISTA_FECHA_ULTIMA_MODIFICACION};

	@EmbeddedId
	private RequisitoListaPK requisitoListaPK;
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_lista", referencedColumnName="id_lista", insertable = false, updatable = false)
    private Lista lista;
		
	@ManyToOne
	@JoinColumn(name="id_requisito", referencedColumnName="id_requisito", insertable = false, updatable = false)
    private Requisito requisito;
		
	
	
    public RequisitoLista(){
		requisitoListaPK = new RequisitoListaPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public RequisitoListaPK getRequisitoListaPK(){
		return this.requisitoListaPK;
	}
	
	public void setRequisitoListaPK(RequisitoListaPK requisitoListaPK){
		this.requisitoListaPK   = requisitoListaPK ;
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
		

    public Lista getLista(){
		return this.lista; 
	}
	
	public void setLista(Lista lista){
		this.lista = lista;
	}
    public Requisito getRequisito(){
		return this.requisito; 
	}
	
	public void setRequisito(Requisito requisito){
		this.requisito = requisito;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_REQUISITO_LISTA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.requisitoListaPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RequisitoLista que se pasa
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
        final RequisitoLista other = (RequisitoLista) obj;
        
        if (!Objects.equals(this.requisitoListaPK, other.requisitoListaPK)) {
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

