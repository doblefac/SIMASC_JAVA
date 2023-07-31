package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin

// Escriba en esta sección sus modificaciones

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.RolPersonaCasoPK;
import com.ccb.simasc.transversal.entidades.Sorteo;

// protected region imports dto end

/**
 * DAO que contiene la informacion de la entidad RolPersonaCasoDTO que se
 * transmite por los servicios REST. Solo se transmiten los atributos simples,
 * es decir, se omiten aquellos atributos que definen relaciones con otras
 * entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class RolPersonaCasoDTO extends IDTO<RolPersonaCaso> implements Serializable {

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private RolPersonaCasoPK rolPersonaCasoPK;

	private String estado;
	private String motivoInactivacion;
	private String tipoNombramiento;
	private Integer ordenDeAsignacion;
	private String metodoNombramiento;
	private String tipoSuplencia;
	private boolean esPresidente;
	private String idUsuarioModificacion;
	private Date fechaUltimaModificacion;
	private String estadoRegistro;
	private Long idRolApoderado;
	private Long idPersonaApoderado;
	private Long idCasoApoderado;
	private Long idSorteo;
	private Long idPronunciamiento;
	private Long idRolPrincipalReemplazado;
	private Long idPersonaPrincipalReemplazado;
	private Long idCasoPrincipalReemplazado;
	private Long idPersonaTercero;

	public RolPersonaCasoDTO() {
		rolPersonaCasoPK = new RolPersonaCasoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
	}

	public RolPersonaCasoPK getRolPersonaCasoPK() {
		return this.rolPersonaCasoPK;
	}

	public void setRolPersonaCasoPK(RolPersonaCasoPK rolPersonaCasoPK) {
		this.rolPersonaCasoPK = rolPersonaCasoPK;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMotivoInactivacion() {
		return this.motivoInactivacion;
	}

	public void setMotivoInactivacion(String motivoInactivacion) {
		this.motivoInactivacion = motivoInactivacion;
	}

	public String getTipoNombramiento() {
		return this.tipoNombramiento;
	}

	public void setTipoNombramiento(String tipoNombramiento) {
		this.tipoNombramiento = tipoNombramiento;
	}

	public Integer getOrdenDeAsignacion() {
		return this.ordenDeAsignacion;
	}

	public void setOrdenDeAsignacion(Integer ordenDeAsignacion) {
		this.ordenDeAsignacion = ordenDeAsignacion;
	}

	public String getMetodoNombramiento() {
		return this.metodoNombramiento;
	}

	public void setMetodoNombramiento(String metodoNombramiento) {
		this.metodoNombramiento = metodoNombramiento;
	}

	public String getTipoSuplencia() {
		return this.tipoSuplencia;
	}

	public void setTipoSuplencia(String tipoSuplencia) {
		this.tipoSuplencia = tipoSuplencia;
	}

	public boolean getEsPresidente() {
		return this.esPresidente;
	}

	public void setEsPresidente(boolean esPresidente) {
		this.esPresidente = esPresidente;
	}

	public String getIdUsuarioModificacion() {
		return this.idUsuarioModificacion;
	}

	public void setIdUsuarioModificacion(String idUsuarioModificacion) {
		this.idUsuarioModificacion = idUsuarioModificacion;
	}

	public Date getFechaUltimaModificacion() {
		return this.fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public String getEstadoRegistro() {
		return this.estadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public Long getIdRolApoderado() {
		return this.idRolApoderado;
	}

	public void setIdRolApoderado(Long idRolApoderado) {
		this.idRolApoderado = idRolApoderado;
	}

	public Long getIdPersonaApoderado() {
		return this.idPersonaApoderado;
	}

	public void setIdPersonaApoderado(Long idPersonaApoderado) {
		this.idPersonaApoderado = idPersonaApoderado;
	}

	public Long getIdCasoApoderado() {
		return this.idCasoApoderado;
	}

	public void setIdCasoApoderado(Long idCasoApoderado) {
		this.idCasoApoderado = idCasoApoderado;
	}

	public Long getIdSorteo() {
		return this.idSorteo;
	}

	public void setIdSorteo(Long idSorteo) {
		this.idSorteo = idSorteo;
	}

	public Long getIdPronunciamiento() {
		return this.idPronunciamiento;
	}

	public void setIdPronunciamiento(Long idPronunciamiento) {
		this.idPronunciamiento = idPronunciamiento;
	}

	public Long getIdRolPrincipalReemplazado() {
		return this.idRolPrincipalReemplazado;
	}

	public void setIdRolPrincipalReemplazado(Long idRolPrincipalReemplazado) {
		this.idRolPrincipalReemplazado = idRolPrincipalReemplazado;
	}

	public Long getIdPersonaPrincipalReemplazado() {
		return this.idPersonaPrincipalReemplazado;
	}

	public void setIdPersonaPrincipalReemplazado(Long idPersonaPrincipalReemplazado) {
		this.idPersonaPrincipalReemplazado = idPersonaPrincipalReemplazado;
	}

	public Long getIdCasoPrincipalReemplazado() {
		return this.idCasoPrincipalReemplazado;
	}

	public void setIdCasoPrincipalReemplazado(Long idCasoPrincipalReemplazado) {
		this.idCasoPrincipalReemplazado = idCasoPrincipalReemplazado;
	}

	public Long getIdPersonaTercero() {
		return this.idPersonaTercero;
	}

	public void setIdPersonaTercero(Long idPersonaTercero) {
		this.idPersonaTercero = idPersonaTercero;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.rolPersonaCasoPK);
		hash = 37 * hash + Objects.hashCode(this.estado);
		hash = 37 * hash + Objects.hashCode(this.motivoInactivacion);
		hash = 37 * hash + Objects.hashCode(this.tipoNombramiento);
		hash = 37 * hash + Objects.hashCode(this.ordenDeAsignacion);
		hash = 37 * hash + Objects.hashCode(this.metodoNombramiento);
		hash = 37 * hash + Objects.hashCode(this.tipoSuplencia);
		hash = 37 * hash + (this.esPresidente ? 0 : 1);
		hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
		hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
		hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
		hash = 37 * hash + Objects.hashCode(this.idRolApoderado);
		hash = 37 * hash + Objects.hashCode(this.idPersonaApoderado);
		hash = 37 * hash + Objects.hashCode(this.idCasoApoderado);
		hash = 37 * hash + Objects.hashCode(this.idSorteo);
		hash = 37 * hash + Objects.hashCode(this.idPronunciamiento);
		hash = 37 * hash + Objects.hashCode(this.idRolPrincipalReemplazado);
		hash = 37 * hash + Objects.hashCode(this.idPersonaPrincipalReemplazado);
		hash = 37 * hash + Objects.hashCode(this.idCasoPrincipalReemplazado);
		hash = 37 * hash + Objects.hashCode(this.idPersonaTercero);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad RolPersonaCasoDTO que se
	 * pasa como parametro comprobando que comparten los mismos valores en cada uno
	 * de sus atributos. Solo se tienen en cuenta los atributos simples, es decir,
	 * se omiten aquellos que definen una relacion con otra tabla.
	 *
	 * @param obj Instancia de la categoría a comprobar iguales.
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
		final RolPersonaCasoDTO other = (RolPersonaCasoDTO) obj;

		if (!Objects.equals(this.rolPersonaCasoPK, other.rolPersonaCasoPK)) {
			return false;
		}

		if (!Objects.equals(this.estado, other.estado)) {
			return false;
		}

		if (!Objects.equals(this.motivoInactivacion, other.motivoInactivacion)) {
			return false;
		}

		if (!Objects.equals(this.tipoNombramiento, other.tipoNombramiento)) {
			return false;
		}

		if (!Objects.equals(this.ordenDeAsignacion, other.ordenDeAsignacion)) {
			return false;
		}

		if (!Objects.equals(this.metodoNombramiento, other.metodoNombramiento)) {
			return false;
		}

		if (!Objects.equals(this.tipoSuplencia, other.tipoSuplencia)) {
			return false;
		}

		if (!Objects.equals(this.esPresidente, other.esPresidente)) {
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

		if (!Objects.equals(this.idRolApoderado, other.idRolApoderado)) {
			return false;
		}

		if (!Objects.equals(this.idPersonaApoderado, other.idPersonaApoderado)) {
			return false;
		}

		if (!Objects.equals(this.idCasoApoderado, other.idCasoApoderado)) {
			return false;
		}

		if (!Objects.equals(this.idSorteo, other.idSorteo)) {
			return false;
		}

		if (!Objects.equals(this.idPronunciamiento, other.idPronunciamiento)) {
			return false;
		}

		if (!Objects.equals(this.idRolPrincipalReemplazado, other.idRolPrincipalReemplazado)) {
			return false;
		}

		if (!Objects.equals(this.idPersonaPrincipalReemplazado, other.idPersonaPrincipalReemplazado)) {
			return false;
		}

		if (!Objects.equals(this.idCasoPrincipalReemplazado, other.idCasoPrincipalReemplazado)) {
			return false;
		}

		return Objects.equals(this.idPersonaTercero, other.idPersonaTercero);

	}

	@Override
	public RolPersonaCasoDTO transformarSinDependencias(RolPersonaCaso obj) {
		RolPersonaCasoDTO rolPersonaCasoDTO = new RolPersonaCasoDTO();

		rolPersonaCasoDTO.setRolPersonaCasoPK(obj.getRolPersonaCasoPK());
		rolPersonaCasoDTO.setEstado(obj.getEstado());
		rolPersonaCasoDTO.setMotivoInactivacion(obj.getMotivoInactivacion());
		rolPersonaCasoDTO.setTipoNombramiento(obj.getTipoNombramiento());
		rolPersonaCasoDTO.setOrdenDeAsignacion(obj.getOrdenDeAsignacion());
		rolPersonaCasoDTO.setMetodoNombramiento(obj.getMetodoNombramiento());
		rolPersonaCasoDTO.setTipoSuplencia(obj.getTipoSuplencia());
		rolPersonaCasoDTO.setEsPresidente(obj.getEsPresidente());
		rolPersonaCasoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		rolPersonaCasoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		rolPersonaCasoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		rolPersonaCasoDTO.setIdRolApoderado(obj.getIdRolApoderado());
		rolPersonaCasoDTO.setIdPersonaApoderado(obj.getIdPersonaApoderado());
		rolPersonaCasoDTO.setIdCasoApoderado(obj.getIdCasoApoderado());
		rolPersonaCasoDTO.setIdSorteo(obj.getIdSorteo());
		rolPersonaCasoDTO.setIdPronunciamiento(obj.getIdPronunciamiento());
		rolPersonaCasoDTO.setIdRolPrincipalReemplazado(obj.getIdRolPrincipalReemplazado());
		rolPersonaCasoDTO.setIdPersonaPrincipalReemplazado(obj.getIdPersonaPrincipalReemplazado());
		rolPersonaCasoDTO.setIdCasoPrincipalReemplazado(obj.getIdCasoPrincipalReemplazado());
		rolPersonaCasoDTO.setIdPersonaTercero(obj.getIdPersonaTercero());

		return rolPersonaCasoDTO;
	}

	@Override
	public RolPersonaCasoDTO transformarConDependencias(RolPersonaCaso obj) {
		RolPersonaCasoDTO rolPersonaCasoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		// TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end

		return rolPersonaCasoDTO;
	}

	@Override
	public RolPersonaCaso transformarEntidadSinDependencias(RolPersonaCaso obj) {
		RolPersonaCaso rolPersonaCaso = new RolPersonaCaso();

		rolPersonaCaso.setRolPersonaCasoPK(obj.getRolPersonaCasoPK());

		rolPersonaCaso.setEstado(obj.getEstado());
		rolPersonaCaso.setMotivoInactivacion(obj.getMotivoInactivacion());
		rolPersonaCaso.setTipoNombramiento(obj.getTipoNombramiento());
		rolPersonaCaso.setOrdenDeAsignacion(obj.getOrdenDeAsignacion());
		rolPersonaCaso.setMetodoNombramiento(obj.getMetodoNombramiento());
		rolPersonaCaso.setTipoSuplencia(obj.getTipoSuplencia());
		rolPersonaCaso.setEsPresidente(obj.getEsPresidente());
		rolPersonaCaso.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		rolPersonaCaso.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		rolPersonaCaso.setEstadoRegistro(obj.getEstadoRegistro());
		rolPersonaCaso.setIdRolApoderado(obj.getIdRolApoderado());
		rolPersonaCaso.setIdPersonaApoderado(obj.getIdPersonaApoderado());
		rolPersonaCaso.setIdCasoApoderado(obj.getIdCasoApoderado());
		rolPersonaCaso.setIdSorteo(obj.getIdSorteo());
		rolPersonaCaso.setIdPronunciamiento(obj.getIdPronunciamiento());
		rolPersonaCaso.setIdRolPrincipalReemplazado(obj.getIdRolPrincipalReemplazado());
		rolPersonaCaso.setIdPersonaPrincipalReemplazado(obj.getIdPersonaPrincipalReemplazado());
		rolPersonaCaso.setIdCasoPrincipalReemplazado(obj.getIdCasoPrincipalReemplazado());
		rolPersonaCaso.setIdPersonaTercero(obj.getIdPersonaTercero());
		rolPersonaCaso.setNombramientoSorteo(obj.getNombramientoSorteo());
		rolPersonaCaso.setIdServicio(obj.getCaso().getIdServicio());
		
		if(obj.getSorteo() != null ) {
			Sorteo sorteo = new Sorteo();		
			sorteo.setPreseleccion(obj.getSorteo().isPreseleccion());
			sorteo.setTipoPreseleccion(obj.getSorteo().getTipoPreseleccion());
			
			rolPersonaCaso.setSorteo(sorteo);
		}
		
		return rolPersonaCaso;
	}

	@Override
	public RolPersonaCaso transformarEntidadConDependencias(RolPersonaCaso obj) {
		RolPersonaCaso rolPersonaCaso = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		// Escriba en esta seccion sus modificaciones
		if (obj.getPersona() != null)
			rolPersonaCaso.setPersona(new PersonaDTO().transformarEntidadSinDependencias(obj.getPersona()));
		if (obj.getRol() != null)
			rolPersonaCaso.setRol(new RolDTO().transformarEntidadSinDependencias(obj.getRol()));
		// protected region modificaciones transformarEntidadConDependencias end

		return rolPersonaCaso;
	}

	@Override
	public Collection transformarColeccionConDependencias(Collection<RolPersonaCaso> coleccion) {
		List<RolPersonaCasoDTO> rolPersonaCasoDTOList = new ArrayList<>();
		for (RolPersonaCaso c : coleccion)
			rolPersonaCasoDTOList.add(transformarConDependencias(c));
		return rolPersonaCasoDTOList;
	}

	@Override
	public Collection transformarColeccionSinDependencias(Collection<RolPersonaCaso> coleccion) {
		List<RolPersonaCasoDTO> rolPersonaCasoDTOList = new ArrayList<>();
		for (RolPersonaCaso c : coleccion)
			rolPersonaCasoDTOList.add(transformarSinDependencias(c));
		return rolPersonaCasoDTOList;
	}

	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<RolPersonaCaso> coleccion) {
		List<RolPersonaCaso> rolPersonaCasoList = new ArrayList<>();
		for (RolPersonaCaso c : coleccion)
			rolPersonaCasoList.add(transformarEntidadConDependencias(c));
		return rolPersonaCasoList;
	}

	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<RolPersonaCaso> coleccion) {
		List<RolPersonaCaso> rolPersonaCasoList = new ArrayList<>();
		for (RolPersonaCaso c : coleccion)
			rolPersonaCasoList.add(transformarEntidadSinDependencias(c));
		return rolPersonaCasoList;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	public RolPersonaCaso transformarEntidadConDependenciaDePersona(RolPersonaCaso obj) {
		RolPersonaCaso rolPersonaCaso = transformarEntidadSinDependencias(obj);

		rolPersonaCaso.setPersona(new PersonaDTO().transformarEntidadSinDependencias(obj.getPersona()));
		rolPersonaCaso.setRol(new RolDTO().transformarEntidadSinDependencias(obj.getRol()));

		return rolPersonaCaso;
	}

	// protected region metodos adicionales end

}
