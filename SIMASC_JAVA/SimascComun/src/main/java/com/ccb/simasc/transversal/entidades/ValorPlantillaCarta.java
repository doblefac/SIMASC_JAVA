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
@Table(name="VALOR_PLANTILLA_CARTA")
@NamedQuery(name = "ValorPlantillaCarta.findAll", query = "SELECT p FROM ValorPlantillaCarta p")
public class ValorPlantillaCarta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_VALOR_PLANTILLA_CARTA_PK = "idValorPlantillaCarta";
	public static final String ENTIDAD_VALOR_PLANTILLA_CARTA_NOMBRE_VALOR_DINAMICO = "nombreValorDinamico";
	public static final String ENTIDAD_VALOR_PLANTILLA_CARTA_ID_PLANTILLA_CARTA = "idPlantillaCarta";
	public static final String ENTIDAD_VALOR_PLANTILLA_CARTA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_VALOR_PLANTILLA_CARTA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_VALOR_PLANTILLA_CARTA_ESTADO_REGISTRO_VALORPLANTILLACARTA = "estadoRegistroValorPlantillaCarta";			
	public static final String ENTIDAD_VALOR_PLANTILLA_CARTA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_VALOR_PLANTILLA_CARTA_DESCRIPCION = "descripcion";
	public static final String ENTIDAD_VALOR_PLANTILLA_CARTA_CONSULTA = "consulta";
	public static final String ENTIDAD_VALOR_PLANTILLA_CARTA_LLENADO_POR_CONSULTA = "llenadoPorConsulta";
	public static final String ENTIDAD_VALOR_PLANTILLA_CARTA_ID_TAG_PLANTILLA = "idTagPlantilla";
    private static final String[] ATRIBUTOS_ENTIDAD_VALOR_PLANTILLA_CARTA
            = {ENTIDAD_VALOR_PLANTILLA_CARTA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_VALOR_PLANTILLA_CARTA_ID_USUARIO_MODIFICACION, ENTIDAD_VALOR_PLANTILLA_CARTA_ID_TAG_PLANTILLA, ENTIDAD_VALOR_PLANTILLA_CARTA_ESTADO_REGISTRO, ENTIDAD_VALOR_PLANTILLA_CARTA_CONSULTA, ENTIDAD_VALOR_PLANTILLA_CARTA_PK, ENTIDAD_VALOR_PLANTILLA_CARTA_LLENADO_POR_CONSULTA, ENTIDAD_VALOR_PLANTILLA_CARTA_DESCRIPCION, ENTIDAD_VALOR_PLANTILLA_CARTA_NOMBRE_VALOR_DINAMICO, ENTIDAD_VALOR_PLANTILLA_CARTA_ID_PLANTILLA_CARTA};

	@Id
    @Column(name="id_valor_plantilla_carta")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idValorPlantillaCarta;
    
	@Column(name="nombre_valor_dinamico")
	private String nombreValorDinamico;		
    
	@Column(name="id_plantilla_carta")
	private Long idPlantillaCarta;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="descripcion")
	private String descripcion;		
    
	@Column(name="consulta")
	private String consulta;		
    
	@Column(name="llenado_por_consulta")
	private boolean llenadoPorConsulta;		
    
	@Column(name="id_tag_plantilla")
	private Long idTagPlantilla;		

	@ManyToOne
	@JoinColumn(name="id_plantilla_carta", referencedColumnName="id_plantilla_carta", insertable = false, updatable = false)
    private PlantillaCarta plantillaCarta;
		
	@ManyToOne
	@JoinColumn(name="id_tag_plantilla", referencedColumnName="id_tag_plantilla", insertable = false, updatable = false)
    private TagPlantilla tagPlantilla;
		
	
	
    public ValorPlantillaCarta(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdValorPlantillaCarta(){
		return this.idValorPlantillaCarta;
	}
	
	public void setIdValorPlantillaCarta(Long idValorPlantillaCarta){
		this.idValorPlantillaCarta = idValorPlantillaCarta;
	}
	
	public String getNombreValorDinamico(){
		return this.nombreValorDinamico;
	}
	
	public void setNombreValorDinamico(String nombreValorDinamico){
		this.nombreValorDinamico = nombreValorDinamico;
	}
		
	public Long getIdPlantillaCarta(){
		return this.idPlantillaCarta;
	}
	
	public void setIdPlantillaCarta(Long idPlantillaCarta){
		this.idPlantillaCarta = idPlantillaCarta;
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
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
		
	public String getConsulta(){
		return this.consulta;
	}
	
	public void setConsulta(String consulta){
		this.consulta = consulta;
	}
		
	public boolean getLlenadoPorConsulta(){
		return this.llenadoPorConsulta;
	}
	
	public void setLlenadoPorConsulta(boolean llenadoPorConsulta){
		this.llenadoPorConsulta = llenadoPorConsulta;
	}
		
	public Long getIdTagPlantilla(){
		return this.idTagPlantilla;
	}
	
	public void setIdTagPlantilla(Long idTagPlantilla){
		this.idTagPlantilla = idTagPlantilla;
	}
		

    public PlantillaCarta getPlantillaCarta(){
		return this.plantillaCarta; 
	}
	
	public void setPlantillaCarta(PlantillaCarta plantillaCarta){
		this.plantillaCarta = plantillaCarta;
	}
    public TagPlantilla getTagPlantilla(){
		return this.tagPlantilla; 
	}
	
	public void setTagPlantilla(TagPlantilla tagPlantilla){
		this.tagPlantilla = tagPlantilla;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_VALOR_PLANTILLA_CARTA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idValorPlantillaCarta);        
        hash = 37 * hash + Objects.hashCode(this.nombreValorDinamico);
        hash = 37 * hash + Objects.hashCode(this.idPlantillaCarta);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.consulta);
        hash = 37 * hash + (this.llenadoPorConsulta ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idTagPlantilla);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ValorPlantillaCarta que se pasa
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
        final ValorPlantillaCarta other = (ValorPlantillaCarta) obj;
        
        if (!Objects.equals(this.idValorPlantillaCarta, other.idValorPlantillaCarta)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreValorDinamico, other.nombreValorDinamico)) {
            return false;
        }
        
        if (!Objects.equals(this.idPlantillaCarta, other.idPlantillaCarta)) {
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
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.consulta, other.consulta)) {
            return false;
        }
        
        if (!Objects.equals(this.llenadoPorConsulta, other.llenadoPorConsulta)) {
            return false;
        }
        
        return Objects.equals(this.idTagPlantilla, other.idTagPlantilla);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

