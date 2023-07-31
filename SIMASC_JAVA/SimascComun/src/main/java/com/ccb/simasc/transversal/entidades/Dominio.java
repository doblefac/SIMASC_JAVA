package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="DOMINIO")
@NamedQuery(name = "Dominio.findAll", query = "SELECT p FROM Dominio p")
public class Dominio implements Serializable, Comparable<Dominio>{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	//Lista de ClasificadorDominio en los que es agrupador
	@OneToMany(mappedBy="dominioClasificador")
    private List<ClasificadorDominio> clasificadorDominioListAgrupador;
	//Lista de ClasificadorDominio en los que es agrupado
	@OneToMany(mappedBy="dominioClasificado")
    private List<ClasificadorDominio> clasificadorDominioListAgrupado;
	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_DOMINIO_PK_CODIGO = "dominioPK.codigo";			
	public static final String ENTIDAD_DOMINIO_PK_DOMINIO = "dominioPK.dominio";
	public static final String ENTIDAD_DOMINIO_NOMBRE = "nombre";
	public static final String ENTIDAD_DOMINIO_DESCRIPCION = "descripcion";
	public static final String ENTIDAD_DOMINIO_CODIGO_DOM_PADRE = "codigoDomPadre";
	public static final String ENTIDAD_DOMINIO_DOMINIO_PADRE = "dominioPadre";
    private static final String[] ATRIBUTOS_ENTIDAD_DOMINIO
            = {ENTIDAD_DOMINIO_PK_CODIGO, ENTIDAD_DOMINIO_NOMBRE, ENTIDAD_DOMINIO_PK_DOMINIO, ENTIDAD_DOMINIO_CODIGO_DOM_PADRE, ENTIDAD_DOMINIO_DESCRIPCION, ENTIDAD_DOMINIO_DOMINIO_PADRE};

	@EmbeddedId
	private DominioPK dominioPK;
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="descripcion")
	private String descripcion;		
    
	@Column(name="codigo_dom_padre")
	private String codigoDomPadre;		
    
	@Column(name="dominio_padre")
	private String dominioPadre;		

	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "codigo_dom_padre", referencedColumnName="codigo", insertable = false, updatable = false),
	    @JoinColumn(name = "dominio_padre", referencedColumnName="dominio", insertable = false, updatable = false)	    
	})		
    private Dominio dominio;
		
	@OneToMany(mappedBy="dominio")
    private List<Dominio> dominioList;
	
	
    public Dominio(){
		dominioPK = new DominioPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }
 
	public DominioPK getDominioPK(){
		return this.dominioPK;
	}
	
	public void setDominioPK(DominioPK dominioPK){
		this.dominioPK   = dominioPK ;
	}  
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
		
	public String getCodigoDomPadre(){
		return this.codigoDomPadre;
	}
	
	public void setCodigoDomPadre(String codigoDomPadre){
		this.codigoDomPadre = codigoDomPadre;
	}
		
	public String getDominioPadre(){
		return this.dominioPadre;
	}
	
	public void setDominioPadre(String dominioPadre){
		this.dominioPadre = dominioPadre;
	}
			
    public List<Dominio> getDominioList(){
		return this.dominioList;
	}
	
	public void setDominioList(List<Dominio> dominioList){
		this.dominioList = dominioList;
	}
			
    public Dominio getDominio(){
		return this.dominio; 
	}
	
	public void setDominio(Dominio dominio){
		this.dominio = dominio;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_DOMINIO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.dominioPK);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.codigoDomPadre);
        hash = 37 * hash + Objects.hashCode(this.dominioPadre);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Dominio que se pasa
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
        final Dominio other = (Dominio) obj;
        
        if (!Objects.equals(this.dominioPK, other.dominioPK)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.codigoDomPadre, other.codigoDomPadre)) {
            return false;
        }
        
        return Objects.equals(this.dominioPadre, other.dominioPadre);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	public List<ClasificadorDominio> getClasificadorDominioListAgrupador() {
		return clasificadorDominioListAgrupador;
	}


	public void setClasificadorDominioListAgrupador(List<ClasificadorDominio> clasificadorDominioListAgrupador) {
		this.clasificadorDominioListAgrupador = clasificadorDominioListAgrupador;
	}


	public List<ClasificadorDominio> getClasificadorDominioListAgrupado() {
		return clasificadorDominioListAgrupado;
	}


	public void setClasificadorDominioListAgrupado(List<ClasificadorDominio> clasificadorDominioListAgrupado) {
		this.clasificadorDominioListAgrupado = clasificadorDominioListAgrupado;
	}
	
	/**
	 * Si es un dominio que agrupa a través de la entidad ClasificadorDominio devuelve todos los dominios
	 * que agrupa, de lo contrario devuelve una lista vacía
	 * @return
	 */
	public List<Dominio> obtenerDominiosAgrupados(){
		List<Dominio> dominios = new ArrayList<Dominio>();
		if(this.getClasificadorDominioListAgrupador()!=null && !this.getClasificadorDominioListAgrupador().isEmpty()){
			for(ClasificadorDominio clasificador : this.getClasificadorDominioListAgrupador()){
				dominios.add(clasificador.getDominioClasificado());
			}
		}
		return dominios;
	}
	
	/**
	 * Devuelve el valor del código definido en el dominio.
	 * Devuelve nulo si el código del dominio o el atributo dominioPK son nulos.
	 * @return
	 */
	public String obtenerCodigoDominio() {
		return (this.getDominioPK() == null || this.getDominioPK().getCodigo() == null) ? null
				: this.getDominioPK().getCodigo();
	}


	@Override
	public int compareTo(Dominio o) {		
		return this.getNombre().compareTo(o.getNombre());
	}
	
	// protected region metodos adicionales end

    
    
} 

