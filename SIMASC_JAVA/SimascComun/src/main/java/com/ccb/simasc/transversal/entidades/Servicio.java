package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="SERVICIO")
@NamedQuery(name = "Servicio.findAll", query = "SELECT p FROM Servicio p")
public class Servicio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	@OneToMany(mappedBy="servicio")
    private List<ParametrizarServicioRol> parametrizarServicioRolList;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_SERVICIO_PK = "idServicio";
	public static final String ENTIDAD_SERVICIO_NOMBRE = "nombre";
	public static final String ENTIDAD_SERVICIO_TIPO = "tipo";
	public static final String ENTIDAD_SERVICIO_OBSERVACIONES = "observaciones";
	public static final String ENTIDAD_SERVICIO_REQUIERE_SUPLENTE = "requiereSuplente";
	public static final String ENTIDAD_SERVICIO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_SERVICIO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_SERVICIO_ESTADO_REGISTRO_SERVICIO = "estadoRegistroServicio";			
	public static final String ENTIDAD_SERVICIO_LIQUIDABLE = "liquidable";
	public static final String ENTIDAD_SERVICIO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_SERVICIO_REQUIERE_PAGO = "requierePago";
	public static final String ENTIDAD_SERVICIO_APLICA_MAUC= "aplicaMauc";
	public static final String ENTIDAD_SERVICIO_TARIFADOR = "tarifador";
    private static final String[] ATRIBUTOS_ENTIDAD_SERVICIO
            = {ENTIDAD_SERVICIO_PK, ENTIDAD_SERVICIO_REQUIERE_SUPLENTE, ENTIDAD_SERVICIO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_SERVICIO_OBSERVACIONES, ENTIDAD_SERVICIO_NOMBRE, ENTIDAD_SERVICIO_TIPO, ENTIDAD_SERVICIO_LIQUIDABLE, ENTIDAD_SERVICIO_ESTADO_REGISTRO, ENTIDAD_SERVICIO_ID_USUARIO_MODIFICACION ,ENTIDAD_SERVICIO_APLICA_MAUC ,ENTIDAD_SERVICIO_REQUIERE_PAGO};

	@Id
    @Column(name="id_servicio")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idServicio;
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="tipo")
	private String tipo;		
    
	@Column(name="observaciones")
	private String observaciones;		
	
	@Column(name="requiere_suplente")
	private boolean requiereSuplente;		
    
	@Column(name="liquidable")
	private boolean liquidable;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;	
	
	@Column(name="aplica_mauc")
	private boolean aplicaMauc;
	
	@Column(name="url_retoma")
	private String urlRetoma;
	
	@Column(name="tarifador")
	private boolean tarifador;

	@OneToMany(mappedBy="servicio")
    private List<AgrupamientoRol> agrupamientoRolList;
	@OneToMany(mappedBy="servicio")
    private List<Asesoria> asesoriaList;
	@OneToMany(mappedBy="servicio")
    private List<PagoCaso> pagoCasoList;
	@OneToMany(mappedBy="servicio")
    private List<ParametrizacionTarifas> parametrizacionTarifasList;
	@OneToMany(mappedBy="servicio")
    private List<ParametroDeServicio> parametroDeServicioList;
	@OneToMany(mappedBy="servicio")
    private List<ParametroServicioSorteo> parametroServicioSorteoList;
	@OneToMany(mappedBy="servicio")
    private List<PlantillaCarta> plantillaCartaList;
	@OneToMany(mappedBy="servicio")
    private List<ServicioMateria> servicioMateriaList;
	@OneToMany(mappedBy="servicio")
    private List<ServicioSede> servicioSedeList;
	
	@Column(name = "requiere_pago")
	private boolean requierePago;
	
	
	
    public Servicio(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public boolean getRequiereSuplente(){
		return this.requiereSuplente;
	}
	
	public void setRequiereSuplente(boolean requiereSuplente){
		this.requiereSuplente = requiereSuplente;
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
		

    public List<AgrupamientoRol> getAgrupamientoRolList(){
		return this.agrupamientoRolList;
	}
	
	public void setAgrupamientoRolList(List<AgrupamientoRol> agrupamientoRolList){
		this.agrupamientoRolList = agrupamientoRolList;
	}
			
    public List<Asesoria> getAsesoriaList(){
		return this.asesoriaList;
	}
	
	public void setAsesoriaList(List<Asesoria> asesoriaList){
		this.asesoriaList = asesoriaList;
	}
			
    public List<PagoCaso> getPagoCasoList(){
		return this.pagoCasoList;
	}
	
	public void setPagoCasoList(List<PagoCaso> pagoCasoList){
		this.pagoCasoList = pagoCasoList;
	}
			
    public List<ParametrizacionTarifas> getParametrizacionTarifasList(){
		return this.parametrizacionTarifasList;
	}
	
	public void setParametrizacionTarifasList(List<ParametrizacionTarifas> parametrizacionTarifasList){
		this.parametrizacionTarifasList = parametrizacionTarifasList;
	}
			
    public List<ParametroDeServicio> getParametroDeServicioList(){
		return this.parametroDeServicioList;
	}
	
	public void setParametroDeServicioList(List<ParametroDeServicio> parametroDeServicioList){
		this.parametroDeServicioList = parametroDeServicioList;
	}
			
    public List<ParametroServicioSorteo> getParametroServicioSorteoList(){
		return this.parametroServicioSorteoList;
	}
	
	public void setParametroServicioSorteoList(List<ParametroServicioSorteo> parametroServicioSorteoList){
		this.parametroServicioSorteoList = parametroServicioSorteoList;
	}
			
    public List<PlantillaCarta> getPlantillaCartaList(){
		return this.plantillaCartaList;
	}
	
	public void setPlantillaCartaList(List<PlantillaCarta> plantillaCartaList){
		this.plantillaCartaList = plantillaCartaList;
	}
			
    public List<ServicioMateria> getServicioMateriaList(){
		return this.servicioMateriaList;
	}
	
	public void setServicioMateriaList(List<ServicioMateria> servicioMateriaList){
		this.servicioMateriaList = servicioMateriaList;
	}
			
    public List<ServicioSede> getServicioSedeList(){
		return this.servicioSedeList;
	}
	
	public void setServicioSedeList(List<ServicioSede> servicioSedeList){
		this.servicioSedeList = servicioSedeList;
	}
	
	public boolean getLiquidable() {
		return liquidable;
	}

	public void setLiquidable(boolean liquidable) {
		this.liquidable = liquidable;
	}
	
	public boolean isRequierePago() {
		return requierePago;
	}


	public void setRequierePago(boolean requierePago) {
		this.requierePago = requierePago;
	}

	public boolean getAplicaMauc() {
		return aplicaMauc;
	}

	public void setAplicaMauc(boolean aplicaMauc) {
		this.aplicaMauc = aplicaMauc;
	}
	
	public String getUrlRetoma() {
		return urlRetoma;
	}

	public void setUrlRetoma(String urlRetoma) {
		this.urlRetoma = urlRetoma;
	}
	
	public boolean getTarifador() {
		return tarifador;
	}
	
	public void setTarifador(boolean tarifador) {
		this.tarifador = tarifador;
	}


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_SERVICIO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idServicio);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + (this.requiereSuplente ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.liquidable);
        hash = 37 * hash + Objects.hashCode(this.requierePago);
        hash = 37 * hash + (this.aplicaMauc ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.urlRetoma);
        hash = 37 * hash + (this.tarifador ? 0 : 1);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Servicio que se pasa
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
        final Servicio other = (Servicio) obj;
        
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.requiereSuplente, other.requiereSuplente)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
        	return false;
        }
        
        if (!Objects.equals(this.liquidable, other.liquidable)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.requierePago, other.requierePago)) {
            return false;
        }
        
        if (!Objects.equals(this.urlRetoma, other.urlRetoma)) {
            return false;
        }
        
        if (!Objects.equals(this.tarifador, other.tarifador)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
    
    /**
	 * @return the parametrizarServicioRolList
	 */
	public List<ParametrizarServicioRol> getParametrizarServicioRolList() {
		return parametrizarServicioRolList;
	}


	/**
	 * @param parametrizarServicioRolList the parametrizarServicioRolList to set
	 */
	public void setParametrizarServicioRolList(List<ParametrizarServicioRol> parametrizarServicioRolList) {
		this.parametrizarServicioRolList = parametrizarServicioRolList;
	}
	
	

	// protected region metodos adicionales end

} 

