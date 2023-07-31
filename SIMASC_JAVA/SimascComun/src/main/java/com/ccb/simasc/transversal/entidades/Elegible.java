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
@Table(name="ELEGIBLE")
@NamedQuery(name = "Elegible.findAll", query = "SELECT p FROM Elegible p")
public class Elegible implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_ELEGIBLE_PK_ID_SORTEO = "elegiblePK.idSorteo";
			
	public static final String ENTIDAD_ELEGIBLE_PK_ID_PERSONA = "elegiblePK.idPersona";
	public static final String ENTIDAD_ELEGIBLE_FECHA_DE_SELECCION = "fechaDeSeleccion";
	public static final String ENTIDAD_ELEGIBLE_ELEGIDO_POR_LIBERACION_LISTA = "elegidoPorLiberacionLista";
	public static final String ENTIDAD_ELEGIBLE_ALEATORIO_ASIGNADO = "aleatorioAsignado";
	public static final String ENTIDAD_ELEGIBLE_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_ELEGIBLE_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_ELEGIBLE_ESTADO_REGISTRO_ELEGIBLE = "estadoRegistroElegible";			
	public static final String ENTIDAD_ELEGIBLE_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_ELEGIBLE
            = {ENTIDAD_ELEGIBLE_ESTADO_REGISTRO, ENTIDAD_ELEGIBLE_FECHA_DE_SELECCION, ENTIDAD_ELEGIBLE_ALEATORIO_ASIGNADO, ENTIDAD_ELEGIBLE_ID_USUARIO_MODIFICACION, ENTIDAD_ELEGIBLE_ELEGIDO_POR_LIBERACION_LISTA, ENTIDAD_ELEGIBLE_PK_ID_SORTEO, ENTIDAD_ELEGIBLE_PK_ID_PERSONA, ENTIDAD_ELEGIBLE_FECHA_ULTIMA_MODIFICACION};

	@EmbeddedId
	private ElegiblePK elegiblePK;
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_seleccion")
	private Date fechaDeSeleccion;		
    
	@Column(name="elegido_por_liberacion_lista")
	private boolean elegidoPorLiberacionLista;		
    
	@Column(name="aleatorio_asignado")
	private Long aleatorioAsignado;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	@ManyToOne
	@JoinColumn(name="id_sorteo", referencedColumnName="id_sorteo", insertable = false, updatable = false)
    private Sorteo sorteo;
		
	
	
    public Elegible(){
		elegiblePK = new ElegiblePK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ElegiblePK getElegiblePK(){
		return this.elegiblePK;
	}
	
	public void setElegiblePK(ElegiblePK elegiblePK){
		this.elegiblePK   = elegiblePK ;
	}  
	
	public Date getFechaDeSeleccion(){
		return this.fechaDeSeleccion;
	}
	
	public void setFechaDeSeleccion(Date fechaDeSeleccion){
		this.fechaDeSeleccion = fechaDeSeleccion;
	}
		
	public boolean getElegidoPorLiberacionLista(){
		return this.elegidoPorLiberacionLista;
	}
	
	public void setElegidoPorLiberacionLista(boolean elegidoPorLiberacionLista){
		this.elegidoPorLiberacionLista = elegidoPorLiberacionLista;
	}
		
	public Long getAleatorioAsignado(){
		return this.aleatorioAsignado;
	}
	
	public void setAleatorioAsignado(Long aleatorioAsignado){
		this.aleatorioAsignado = aleatorioAsignado;
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
		

    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}
    public Sorteo getSorteo(){
		return this.sorteo; 
	}
	
	public void setSorteo(Sorteo sorteo){
		this.sorteo = sorteo;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_ELEGIBLE) {
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
        
        hash = 37 * hash + Objects.hashCode(this.elegiblePK);        
        hash = 37 * hash + Objects.hashCode(this.fechaDeSeleccion);
        hash = 37 * hash + (this.elegidoPorLiberacionLista ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.aleatorioAsignado);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Elegible que se pasa
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
        final Elegible other = (Elegible) obj;
        
        if (!Objects.equals(this.elegiblePK, other.elegiblePK)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeSeleccion, other.fechaDeSeleccion)) {
            return false;
        }
        
        if (!Objects.equals(this.elegidoPorLiberacionLista, other.elegidoPorLiberacionLista)) {
            return false;
        }
        
        if (!Objects.equals(this.aleatorioAsignado, other.aleatorioAsignado)) {
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

