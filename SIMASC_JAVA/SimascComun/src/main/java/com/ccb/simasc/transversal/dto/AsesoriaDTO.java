package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.Asesoria;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad AsesoriaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class AsesoriaDTO extends IDTO<Asesoria> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	//CON-C-1003
	private String personaAsesorada;
	private String tipoConsulta;
	private String funcionario;
	private String servicioAsesoria;
	
	//filtros CON-C-1003
	private Date fechaInicio;
	private Date fechaHasta;
	private List<CentroDTO> centros;
	
	//CON-C-049
	private String telefono;
	private String email;
	private String direccion;
	private String radicaCaso;
	// protected region atributos end
	private Long idAsesoria;

	private String observaciones;		
	private String tipoAsesoria;		
	private Date fechaAsesoria;		
	private Long cuantia;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idPersonaAsesora;		
	private Long idPersona;		
	private Long idMateria;		
	private Long idServicio;
	
    public AsesoriaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdAsesoria(){
		return this.idAsesoria;
	}
	
	public void setIdAsesoria(Long idAsesoria){
		this.idAsesoria = idAsesoria;
	}
	
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public String getTipoAsesoria(){
		return this.tipoAsesoria;
	}
	
	public void setTipoAsesoria(String tipoAsesoria){
		this.tipoAsesoria = tipoAsesoria;
	}
		
	public Date getFechaAsesoria(){
		return this.fechaAsesoria;
	}
	
	public void setFechaAsesoria(Date fechaAsesoria){
		this.fechaAsesoria = fechaAsesoria;
	}
		
	public Long getCuantia(){
		return this.cuantia;
	}
	
	public void setCuantia(Long cuantia){
		this.cuantia = cuantia;
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
		
	public Long getIdPersonaAsesora(){
		return this.idPersonaAsesora;
	}
	
	public void setIdPersonaAsesora(Long idPersonaAsesora){
		this.idPersonaAsesora = idPersonaAsesora;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Long getIdMateria(){
		return this.idMateria;
	}
	
	public void setIdMateria(Long idMateria){
		this.idMateria = idMateria;
	}
		
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idAsesoria);        
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.tipoAsesoria);
        hash = 37 * hash + Objects.hashCode(this.fechaAsesoria);
        hash = 37 * hash + Objects.hashCode(this.cuantia);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idPersonaAsesora);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idMateria);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AsesoriaDTO que se pasa
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
        final AsesoriaDTO other = (AsesoriaDTO) obj;
                
        if (!Objects.equals(this.idAsesoria, other.idAsesoria)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoAsesoria, other.tipoAsesoria)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaAsesoria, other.fechaAsesoria)) {
            return false;
        }
        
        if (!Objects.equals(this.cuantia, other.cuantia)) {
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
        
        if (!Objects.equals(this.idPersonaAsesora, other.idPersonaAsesora)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idMateria, other.idMateria)) {
            return false;
        }
        
        return Objects.equals(this.idServicio, other.idServicio);
                
    }
    
    @Override
	public AsesoriaDTO transformarSinDependencias(Asesoria obj) {
		AsesoriaDTO asesoriaDTO = new AsesoriaDTO();
		
		asesoriaDTO.setIdAsesoria(obj.getIdAsesoria());
		asesoriaDTO.setObservaciones(obj.getObservaciones());
		asesoriaDTO.setTipoAsesoria(obj.getTipoAsesoria());
		asesoriaDTO.setFechaAsesoria(obj.getFechaAsesoria());
		asesoriaDTO.setCuantia(obj.getCuantia());
		asesoriaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		asesoriaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		asesoriaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		asesoriaDTO.setIdPersonaAsesora(obj.getIdPersonaAsesora());
		asesoriaDTO.setIdPersona(obj.getIdPersona());
		asesoriaDTO.setIdMateria(obj.getIdMateria());
		asesoriaDTO.setIdServicio(obj.getIdServicio());
		
		return asesoriaDTO;
	}

	@Override
	public AsesoriaDTO transformarConDependencias(Asesoria obj) {
		AsesoriaDTO asesoriaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return asesoriaDTO;
	}

	@Override
	public Asesoria transformarEntidadSinDependencias(Asesoria obj) {
		Asesoria asesoria = new Asesoria();
		
		asesoria.setIdAsesoria(obj.getIdAsesoria());
		
		asesoria.setObservaciones(obj.getObservaciones());
		asesoria.setTipoAsesoria(obj.getTipoAsesoria());
		asesoria.setFechaAsesoria(obj.getFechaAsesoria());
		asesoria.setCuantia(obj.getCuantia());
		asesoria.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		asesoria.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		asesoria.setEstadoRegistro(obj.getEstadoRegistro());
		asesoria.setIdPersonaAsesora(obj.getIdPersonaAsesora());
		asesoria.setIdPersona(obj.getIdPersona());
		asesoria.setIdMateria(obj.getIdMateria());
		asesoria.setIdServicio(obj.getIdServicio());
		
		return asesoria;
	}


	@Override
	public Asesoria transformarEntidadConDependencias(Asesoria obj) {
		Asesoria asesoria = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return asesoria;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Asesoria> coleccion) {
		List<AsesoriaDTO> asesoriaDTOList = new ArrayList<>();
		for(Asesoria c : coleccion)
			asesoriaDTOList.add(transformarConDependencias(c));
		return asesoriaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Asesoria> coleccion) {
		List<AsesoriaDTO> asesoriaDTOList = new ArrayList<>();
		for(Asesoria c : coleccion)
			asesoriaDTOList.add(transformarSinDependencias(c));
		return asesoriaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Asesoria> coleccion) {
		List<Asesoria> asesoriaList = new ArrayList<>();
		for(Asesoria c : coleccion)
			asesoriaList.add(transformarEntidadConDependencias(c));
		return asesoriaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Asesoria> coleccion) {
		List<Asesoria> asesoriaList = new ArrayList<>();
		for(Asesoria c : coleccion)
			asesoriaList.add(transformarEntidadSinDependencias(c));
		return asesoriaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public String getPersonaAsesorada() {
		return personaAsesorada;
	}



	public void setPersonaAsesorada(String personaAsesorada) {
		this.personaAsesorada = personaAsesorada;
	}



	public String getTipoConsulta() {
		return tipoConsulta;
	}



	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}



	public String getFuncionario() {
		return funcionario;
	}



	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}



	public String getServicio() {
		return servicioAsesoria;
	}



	public void setServicio(String servicio) {
		this.servicioAsesoria = servicio;
	}



	public Date getFechaInicio() {
		return fechaInicio;
	}



	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}



	public Date getFechaHasta() {
		return fechaHasta;
	}



	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}



	public List<CentroDTO> getCentros() {
		return centros;
	}



	public void setCentros(List<CentroDTO> centros) {
		this.centros = centros;
	}



	public String getServicioAsesoria() {
		return servicioAsesoria;
	}



	public void setServicioAsesoria(String servicioAsesoria) {
		this.servicioAsesoria = servicioAsesoria;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getRadicaCaso() {
		return radicaCaso;
	}



	public void setRadicaCaso(String radicaCaso) {
		this.radicaCaso = radicaCaso;
	}

	// protected region metodos adicionales end

}
