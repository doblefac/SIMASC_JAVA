package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.dto.formularios.InfoAdicionalResultadoDTO;
import com.ccb.simasc.transversal.entidades.ResultadoAudiencia;

// protected region imports dto end

/**
 * DAO que contiene la informacion de la entidad ResultadoAudienciaDTO que se
 * transmite por los servicios REST. Solo se transmiten los atributos simples,
 * es decir, se omiten aquellos atributos que definen relaciones con otras
 * entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ResultadoAudienciaDTO extends IDTO<ResultadoAudiencia> implements Serializable {

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idResultadoAudiencia;

	private String tipoResultadoAudiencia;
	private String observaciones;
	private String estado;
	private String idUsuarioModificacion;
	private Date fechaUltimaModificacion;
	private String estadoRegistro;
	private Long idAudiencia;
	private Long idDocumento;
	private Long idUbicacion;
	private List<ObligacionDTO> obligacionList;
	
	private AudienciaDTO audienciaDTO;

	/* el sgte DTO es informacion de consulta para un resultado de audiencia */
	
	private InfoAdicionalResultadoDTO infoAdicional;


	public ResultadoAudienciaDTO() {
		// protected region procedimientos adicionales de inicializacion on
		// begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
	}

	public Long getIdResultadoAudiencia() {
		return this.idResultadoAudiencia;
	}

	public void setIdResultadoAudiencia(Long idResultadoAudiencia) {
		this.idResultadoAudiencia = idResultadoAudiencia;
	}

	public String getTipoResultadoAudiencia() {
		return this.tipoResultadoAudiencia;
	}

	public void setTipoResultadoAudiencia(String tipoResultadoAudiencia) {
		this.tipoResultadoAudiencia = tipoResultadoAudiencia;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public Long getIdAudiencia() {
		return this.idAudiencia;
	}

	public void setIdAudiencia(Long idAudiencia) {
		this.idAudiencia = idAudiencia;
	}

	public Long getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Long getIdUbicacion() {
		return this.idUbicacion;
	}

	public void setIdUbicacion(Long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}
	

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idResultadoAudiencia);
		hash = 37 * hash + Objects.hashCode(this.tipoResultadoAudiencia);
		hash = 37 * hash + Objects.hashCode(this.observaciones);
		hash = 37 * hash + Objects.hashCode(this.estado);
		hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
		hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
		hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
		hash = 37 * hash + Objects.hashCode(this.idAudiencia);
		hash = 37 * hash + Objects.hashCode(this.idDocumento);
		hash = 37 * hash + Objects.hashCode(this.idUbicacion);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad ResultadoAudienciaDTO
	 * que se pasa como parametro comprobando que comparten los mismos valores
	 * en cada uno de sus atributos. Solo se tienen en cuenta los atributos
	 * simples, es decir, se omiten aquellos que definen una relacion con otra
	 * tabla.
	 *
	 * @param obj
	 *            Instancia de la categoría a comprobar iguales.
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
		final ResultadoAudienciaDTO other = (ResultadoAudienciaDTO) obj;

		if (!Objects.equals(this.idResultadoAudiencia, other.idResultadoAudiencia)) {
			return false;
		}

		if (!Objects.equals(this.tipoResultadoAudiencia, other.tipoResultadoAudiencia)) {
			return false;
		}

		if (!Objects.equals(this.observaciones, other.observaciones)) {
			return false;
		}

		if (!Objects.equals(this.estado, other.estado)) {
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

		if (!Objects.equals(this.idAudiencia, other.idAudiencia)) {
			return false;
		}

		if (!Objects.equals(this.idDocumento, other.idDocumento)) {
			return false;
		}

		return Objects.equals(this.idUbicacion, other.idUbicacion);

	}

	@Override
	public ResultadoAudienciaDTO transformarSinDependencias(ResultadoAudiencia obj) {
		ResultadoAudienciaDTO resultadoAudienciaDTO = new ResultadoAudienciaDTO();

		resultadoAudienciaDTO.setIdResultadoAudiencia(obj.getIdResultadoAudiencia());
		resultadoAudienciaDTO.setTipoResultadoAudiencia(obj.getTipoResultadoAudiencia());
		resultadoAudienciaDTO.setObservaciones(obj.getObservaciones());
		resultadoAudienciaDTO.setEstado(obj.getEstado());
		resultadoAudienciaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		resultadoAudienciaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		resultadoAudienciaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		resultadoAudienciaDTO.setIdAudiencia(obj.getIdAudiencia());
		resultadoAudienciaDTO.setIdDocumento(obj.getIdDocumento());
		resultadoAudienciaDTO.setIdUbicacion(obj.getIdUbicacion());

		return resultadoAudienciaDTO;
	}

	@Override
	public ResultadoAudienciaDTO transformarConDependencias(ResultadoAudiencia obj) {
		ResultadoAudienciaDTO resultadoAudienciaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		// TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end

		return resultadoAudienciaDTO;
	}

	@Override
	public ResultadoAudiencia transformarEntidadSinDependencias(ResultadoAudiencia obj) {
		ResultadoAudiencia resultadoAudiencia = new ResultadoAudiencia();

		resultadoAudiencia.setIdResultadoAudiencia(obj.getIdResultadoAudiencia());

		resultadoAudiencia.setTipoResultadoAudiencia(obj.getTipoResultadoAudiencia());
		resultadoAudiencia.setObservaciones(obj.getObservaciones());
		resultadoAudiencia.setEstado(obj.getEstado());
		resultadoAudiencia.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		resultadoAudiencia.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		resultadoAudiencia.setEstadoRegistro(obj.getEstadoRegistro());
		resultadoAudiencia.setIdAudiencia(obj.getIdAudiencia());
		resultadoAudiencia.setIdDocumento(obj.getIdDocumento());
		resultadoAudiencia.setIdUbicacion(obj.getIdUbicacion());

		return resultadoAudiencia;
	}

	@Override
	public ResultadoAudiencia transformarEntidadConDependencias(ResultadoAudiencia obj) {
		ResultadoAudiencia resultadoAudiencia = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on
		// begin
		// Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end

		return resultadoAudiencia;
	}

	@Override
	public Collection transformarColeccionConDependencias(Collection<ResultadoAudiencia> coleccion) {
		List<ResultadoAudienciaDTO> resultadoAudienciaDTOList = new ArrayList<>();
		for (ResultadoAudiencia c : coleccion)
			resultadoAudienciaDTOList.add(transformarConDependencias(c));
		return resultadoAudienciaDTOList;
	}

	@Override
	public Collection transformarColeccionSinDependencias(Collection<ResultadoAudiencia> coleccion) {
		List<ResultadoAudienciaDTO> resultadoAudienciaDTOList = new ArrayList<>();
		for (ResultadoAudiencia c : coleccion)
			resultadoAudienciaDTOList.add(transformarSinDependencias(c));
		return resultadoAudienciaDTOList;
	}

	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ResultadoAudiencia> coleccion) {
		List<ResultadoAudiencia> resultadoAudienciaList = new ArrayList<>();
		for (ResultadoAudiencia c : coleccion)
			resultadoAudienciaList.add(transformarEntidadConDependencias(c));
		return resultadoAudienciaList;
	}

	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ResultadoAudiencia> coleccion) {
		List<ResultadoAudiencia> resultadoAudienciaList = new ArrayList<>();
		for (ResultadoAudiencia c : coleccion)
			resultadoAudienciaList.add(transformarEntidadSinDependencias(c));
		return resultadoAudienciaList;
	}

	public InfoAdicionalResultadoDTO getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(InfoAdicionalResultadoDTO infoAdicional) {
		this.infoAdicional = infoAdicional;
	}

	public List<ObligacionDTO> getObligacionList() {
		return obligacionList;
	}

	public void setObligacionList(List<ObligacionDTO> obligacionList) {
		this.obligacionList = obligacionList;
	}

	public AudienciaDTO getAudienciaDTO() {
		return audienciaDTO;
	}

	public void setAudienciaDTO(AudienciaDTO audienciaDTO) {
		this.audienciaDTO = audienciaDTO;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
