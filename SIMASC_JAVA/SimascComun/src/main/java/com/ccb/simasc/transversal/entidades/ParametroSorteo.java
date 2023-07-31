package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
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
@Table(name="PARAMETRO_SORTEO")
@NamedQuery(name = "ParametroSorteo.findAll", query = "SELECT p FROM ParametroSorteo p")
public class ParametroSorteo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	
	@Temporal(javax.persistence.TemporalType.TIME)
	@Column(name="hora_inicio")
	private Date horaInicio;
	
	//lvalbuena: en la consulta se genera tipo EAGER, para obtener la info
	//completa del sorteo
	@OneToMany(mappedBy="parametroSorteo", cascade=CascadeType.ALL)
    private List<DiaSorteo> diaSorteoList;
	
	@OneToMany(mappedBy="parametroSorteo", cascade=CascadeType.ALL)			
	private List<ParamEstadoArbitroPreseleccion> paramEstadoArbitroPreseleccionList;
	
	@OneToMany(mappedBy="parametroSorteo", cascade=CascadeType.ALL)
    private List<ParametroServicioSorteo> parametroServicioSorteoList;

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PARAMETRO_SORTEO_PK = "idParametrosSorteo";
	public static final String ENTIDAD_PARAMETRO_SORTEO_HORA_INICIO = "horaInicio";
	public static final String ENTIDAD_PARAMETRO_SORTEO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PARAMETRO_SORTEO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PARAMETRO_SORTEO_ESTADO_REGISTRO_PARAMETROSORTEO = "estadoRegistroParametroSorteo";			
	public static final String ENTIDAD_PARAMETRO_SORTEO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_PARAMETRO_SORTEO
            = {ENTIDAD_PARAMETRO_SORTEO_HORA_INICIO, ENTIDAD_PARAMETRO_SORTEO_ESTADO_REGISTRO, ENTIDAD_PARAMETRO_SORTEO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_PARAMETRO_SORTEO_PK, ENTIDAD_PARAMETRO_SORTEO_ID_USUARIO_MODIFICACION};

	@Id
    @Column(name="id_parametros_sorteo")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idParametrosSorteo;	
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
	
	
    public ParametroSorteo(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdParametrosSorteo(){
		return this.idParametrosSorteo;
	}
	
	public void setIdParametrosSorteo(Long idParametrosSorteo){
		this.idParametrosSorteo = idParametrosSorteo;
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
		

    public List<DiaSorteo> getDiaSorteoList(){
		return this.diaSorteoList;
	}
	
	public void setDiaSorteoList(List<DiaSorteo> diaSorteoList){
		this.diaSorteoList = diaSorteoList;
	}
			
    public List<ParamEstadoArbitroPreseleccion> getParamEstadoArbitroPreseleccionList(){
		return this.paramEstadoArbitroPreseleccionList;
	}
	
	public void setParamEstadoArbitroPreseleccionList(List<ParamEstadoArbitroPreseleccion> paramEstadoArbitroPreseleccionList){
		this.paramEstadoArbitroPreseleccionList = paramEstadoArbitroPreseleccionList;
	}
			
    public List<ParametroServicioSorteo> getParametroServicioSorteoList(){
		return this.parametroServicioSorteoList;
	}
	
	public void setParametroServicioSorteoList(List<ParametroServicioSorteo> parametroServicioSorteoList){
		this.parametroServicioSorteoList = parametroServicioSorteoList;
	}
			

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PARAMETRO_SORTEO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idParametrosSorteo);        
        hash = 37 * hash + Objects.hashCode(this.horaInicio);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParametroSorteo que se pasa
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
        final ParametroSorteo other = (ParametroSorteo) obj;
        
        if (!Objects.equals(this.idParametrosSorteo, other.idParametrosSorteo)) {
            return false;
        }
        
        if (!Objects.equals(this.horaInicio, other.horaInicio)) {
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

	public Date getHoraInicio(){
		return this.horaInicio;
	}
	
	public void setHoraInicio(Date horaInicio){
		this.horaInicio = horaInicio;
	}
    
	// protected region metodos adicionales end

} 

