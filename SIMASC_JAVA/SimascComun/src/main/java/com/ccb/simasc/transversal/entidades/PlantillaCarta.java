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
@Table(name="PLANTILLA_CARTA")
@NamedQuery(name = "PlantillaCarta.findAll", query = "SELECT p FROM PlantillaCarta p")
public class PlantillaCarta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PLANTILLA_CARTA_PK = "idPlantillaCarta";
	public static final String ENTIDAD_PLANTILLA_CARTA_NOMBRE = "nombre";
	public static final String ENTIDAD_PLANTILLA_CARTA_TIPO_SERVICIO = "tipoServicio";
	public static final String ENTIDAD_PLANTILLA_CARTA_PLANTILLA_HTML = "plantillaHtml";
	public static final String ENTIDAD_PLANTILLA_CARTA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PLANTILLA_CARTA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PLANTILLA_CARTA_ESTADO_REGISTRO_PLANTILLACARTA = "estadoRegistroPlantillaCarta";			
	public static final String ENTIDAD_PLANTILLA_CARTA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_PLANTILLA_CARTA_ID_SERVICIO = "idServicio";
	public static final String ENTIDAD_PLANTILLA_CARTA_ID_CONVENIO = "idConvenio";
    private static final String[] ATRIBUTOS_ENTIDAD_PLANTILLA_CARTA
            = {ENTIDAD_PLANTILLA_CARTA_ESTADO_REGISTRO, ENTIDAD_PLANTILLA_CARTA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_PLANTILLA_CARTA_ID_CONVENIO, ENTIDAD_PLANTILLA_CARTA_PK, ENTIDAD_PLANTILLA_CARTA_ID_USUARIO_MODIFICACION, ENTIDAD_PLANTILLA_CARTA_TIPO_SERVICIO, ENTIDAD_PLANTILLA_CARTA_ID_SERVICIO, ENTIDAD_PLANTILLA_CARTA_PLANTILLA_HTML, ENTIDAD_PLANTILLA_CARTA_NOMBRE};

	@Id
    @Column(name="id_plantilla_carta")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idPlantillaCarta;
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="tipo_servicio")
	private String tipoServicio;		
    
	@Column(name="plantilla_html")
	private String plantillaHtml;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_servicio")
	private Long idServicio;		
    
	@Column(name="id_convenio")
	private Long idConvenio;		

	@ManyToOne
	@JoinColumn(name="id_convenio", referencedColumnName="id_convenio", insertable = false, updatable = false)
    private Convenio convenio;
		
	@ManyToOne
	@JoinColumn(name="id_servicio", referencedColumnName="id_servicio", insertable = false, updatable = false)
    private Servicio servicio;
		
	@OneToMany(mappedBy="plantillaCarta")
    private List<CartaPersona> cartaPersonaList;
	@OneToMany(mappedBy="plantillaCarta")
    private List<ValorPlantillaCarta> valorPlantillaCartaList;
	
	
    public PlantillaCarta(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdPlantillaCarta(){
		return this.idPlantillaCarta;
	}
	
	public void setIdPlantillaCarta(Long idPlantillaCarta){
		this.idPlantillaCarta = idPlantillaCarta;
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
		
	public String getPlantillaHtml(){
		return this.plantillaHtml;
	}
	
	public void setPlantillaHtml(String plantillaHtml){
		this.plantillaHtml = plantillaHtml;
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
		
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		
	public Long getIdConvenio(){
		return this.idConvenio;
	}
	
	public void setIdConvenio(Long idConvenio){
		this.idConvenio = idConvenio;
	}
		

    public List<CartaPersona> getCartaPersonaList(){
		return this.cartaPersonaList;
	}
	
	public void setCartaPersonaList(List<CartaPersona> cartaPersonaList){
		this.cartaPersonaList = cartaPersonaList;
	}
			
    public List<ValorPlantillaCarta> getValorPlantillaCartaList(){
		return this.valorPlantillaCartaList;
	}
	
	public void setValorPlantillaCartaList(List<ValorPlantillaCarta> valorPlantillaCartaList){
		this.valorPlantillaCartaList = valorPlantillaCartaList;
	}
			
    public Convenio getConvenio(){
		return this.convenio; 
	}
	
	public void setConvenio(Convenio convenio){
		this.convenio = convenio;
	}
    public Servicio getServicio(){
		return this.servicio; 
	}
	
	public void setServicio(Servicio servicio){
		this.servicio = servicio;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PLANTILLA_CARTA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idPlantillaCarta);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.tipoServicio);
        hash = 37 * hash + Objects.hashCode(this.plantillaHtml);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        hash = 37 * hash + Objects.hashCode(this.idConvenio);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PlantillaCarta que se pasa
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
        final PlantillaCarta other = (PlantillaCarta) obj;
        
        if (!Objects.equals(this.idPlantillaCarta, other.idPlantillaCarta)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoServicio, other.tipoServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.plantillaHtml, other.plantillaHtml)) {
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
        
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        return Objects.equals(this.idConvenio, other.idConvenio);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

