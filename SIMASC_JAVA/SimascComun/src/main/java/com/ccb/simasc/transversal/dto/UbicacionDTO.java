package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta sección sus modificaciones


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.Ubicacion;
import com.ccb.simasc.transversal.entidades.ZonaGeografica;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad UbicacionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class UbicacionDTO extends IDTO<Ubicacion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ZonaGeografica pais;
	private ZonaGeografica ciudad;
	
	// protected region atributos end
	private Long idUbicacion;

	private String direccion;
	private String direccionEnmascarada;
	private BigDecimal latitud;		
	private BigDecimal longitud;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private String idZonaGeografica;		
	private Long idPersona;		
	private String tipo;
	private String localidad;
	private String barrio;
	private List<TelefonoDTO> telefonoList;
	
    public UbicacionDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdUbicacion(){
		return this.idUbicacion;
	}
	
	public void setIdUbicacion(Long idUbicacion){
		this.idUbicacion = idUbicacion;
	}
	
	public String getDireccion(){
		return this.direccion;
	}
	
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}
		
	public BigDecimal getLatitud(){
		return this.latitud;
	}
	
	public void setLatitud(BigDecimal latitud){
		this.latitud = latitud;
	}
		
	public BigDecimal getLongitud(){
		return this.longitud;
	}
	
	public void setLongitud(BigDecimal longitud){
		this.longitud = longitud;
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
		
	public String getIdZonaGeografica(){
		return this.idZonaGeografica;
	}
	
	public void setIdZonaGeografica(String idZonaGeografica){
		this.idZonaGeografica = idZonaGeografica;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
	
    public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public List<TelefonoDTO> getTelefonoList() {
		return telefonoList;
	}

	public void setTelefonoList(List<TelefonoDTO> telefonoList) {
		this.telefonoList = telefonoList;
	}
	
	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getDireccionEnmascarada() {
		return direccionEnmascarada;
	}

	public void setDireccionEnmascarada(String direccionEnmascarada) {
		this.direccionEnmascarada = direccionEnmascarada;
	}

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idUbicacion);        
        hash = 37 * hash + Objects.hashCode(this.direccion);
        hash = 37 * hash + Objects.hashCode(this.latitud);
        hash = 37 * hash + Objects.hashCode(this.longitud);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idZonaGeografica);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.localidad);
        hash = 37 * hash + Objects.hashCode(this.barrio);
        hash = 37 * hash + Objects.hashCode(this.direccionEnmascarada);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad UbicacionDTO que se pasa
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
        final UbicacionDTO other = (UbicacionDTO) obj;
                
        if (!Objects.equals(this.idUbicacion, other.idUbicacion)) {
            return false;
        }
        
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        
        if (!Objects.equals(this.latitud, other.latitud)) {
            return false;
        }
        
        if (!Objects.equals(this.longitud, other.longitud)) {
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
        
        if (!Objects.equals(this.idZonaGeografica, other.idZonaGeografica)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        if (!Objects.equals(this.localidad, other.localidad)) {
            return false;
        }
        
        if (!Objects.equals(this.barrio, other.barrio)) {
            return false;
        }
        
        if (!Objects.equals(this.direccionEnmascarada, other.direccionEnmascarada)) {
            return false;
        }
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
    
    @Override
	public UbicacionDTO transformarSinDependencias(Ubicacion obj) {
		UbicacionDTO ubicacionDTO = new UbicacionDTO();
		
		ubicacionDTO.setIdUbicacion(obj.getIdUbicacion());
		ubicacionDTO.setDireccion(obj.getDireccion());
		ubicacionDTO.setLatitud(obj.getLatitud());
		ubicacionDTO.setLongitud(obj.getLongitud());
		ubicacionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		ubicacionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		ubicacionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		ubicacionDTO.setIdZonaGeografica(obj.getIdZonaGeografica());
		ubicacionDTO.setIdPersona(obj.getIdPersona());
		ubicacionDTO.setTipo(obj.getTipo());
		ubicacionDTO.setLocalidad(obj.getLocalidad());
		ubicacionDTO.setBarrio(obj.getBarrio());
		
		return ubicacionDTO;
	}

	@Override
	public UbicacionDTO transformarConDependencias(Ubicacion obj) {
		UbicacionDTO ubicacionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return ubicacionDTO;
	}

	@Override
	public Ubicacion transformarEntidadSinDependencias(Ubicacion obj) {
		Ubicacion ubicacion = new Ubicacion();
		
		ubicacion.setIdUbicacion(obj.getIdUbicacion());
		
		ubicacion.setDireccion(obj.getDireccion());
		ubicacion.setLatitud(obj.getLatitud());
		ubicacion.setLongitud(obj.getLongitud());
		ubicacion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		ubicacion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		ubicacion.setEstadoRegistro(obj.getEstadoRegistro());
		ubicacion.setIdZonaGeografica(obj.getIdZonaGeografica());
		ubicacion.setIdPersona(obj.getIdPersona());
		ubicacion.setTipo(obj.getTipo());
		ubicacion.setLocalidad(obj.getBarrio());	
		ubicacion.setBarrio(obj.getBarrio());	
		
		return ubicacion;
	}


	@Override
	public Ubicacion transformarEntidadConDependencias(Ubicacion obj) {
		Ubicacion ubicacion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return ubicacion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Ubicacion> coleccion) {
		List<UbicacionDTO> ubicacionDTOList = new ArrayList<>();
		for(Ubicacion c : coleccion)
			ubicacionDTOList.add(transformarConDependencias(c));
		return ubicacionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Ubicacion> coleccion) {
		List<UbicacionDTO> ubicacionDTOList = new ArrayList<>();
		for(Ubicacion c : coleccion)
			ubicacionDTOList.add(transformarSinDependencias(c));
		return ubicacionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Ubicacion> coleccion) {
		List<Ubicacion> ubicacionList = new ArrayList<>();
		for(Ubicacion c : coleccion)
			ubicacionList.add(transformarEntidadConDependencias(c));
		return ubicacionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Ubicacion> coleccion) {
		List<Ubicacion> ubicacionList = new ArrayList<>();
		for(Ubicacion c : coleccion)
			ubicacionList.add(transformarEntidadSinDependencias(c));
		return ubicacionList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	public ZonaGeografica getPais() {
		return pais;
	}



	public void setPais(ZonaGeografica pais) {
		this.pais = pais;
	}



	public ZonaGeografica getCiudad() {
		return ciudad;
	}



	public void setCiudad(ZonaGeografica ciudad) {
		this.ciudad = ciudad;
	}
	
	
	public List<Ubicacion> transformarColeccionDTOAColeccionEntidades(Collection<UbicacionDTO> coleccion) {
		List<Ubicacion> ubicacionList = new ArrayList<>();
		if(coleccion!=null){
			for(UbicacionDTO c : coleccion)
				ubicacionList.add(transformarDTOAEntidad(c));
		}
		
		return ubicacionList;
	}
	
	public Ubicacion transformarDTOAEntidad(UbicacionDTO obj) {
		Ubicacion ubicacion = new Ubicacion();
		
		ubicacion.setIdUbicacion(obj.getIdUbicacion());
		
		ubicacion.setDireccion(obj.getDireccion());
		ubicacion.setLatitud(obj.getLatitud());
		ubicacion.setLongitud(obj.getLongitud());
		ubicacion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		ubicacion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		ubicacion.setEstadoRegistro(obj.getEstadoRegistro());
		ubicacion.setIdZonaGeografica(obj.getIdZonaGeografica());
		ubicacion.setIdPersona(obj.getIdPersona());
		ubicacion.setTipo(obj.getTipo());
		ubicacion.setLocalidad(obj.getLocalidad());
		ubicacion.setBarrio(obj.getBarrio());
		
		return ubicacion;
	}
	// protected region metodos adicionales end

}
