package com.ccb.simasc.transversal.dto;

import java.util.List;

import com.ccb.simasc.transversal.entidades.EvaluacionConciliador;
import com.ccb.simasc.transversal.entidades.SolicitudPrestador;

public class VerificarSolicitudCambioListaDTO {
	
	/**
	 * boleano que determina si la solicitud que se esta usando es de un arbitro si esta en true
	 */
	private boolean solicitudDeArbitraje;
	/**
	 * contiene la informacion de la solicitud que se esta usando
	 */
	private SolicitudPrestador solicitudCambioLista;
	
	/**
	 * lista de membresiaDTO
	 */
	private MembresiaDTO membresiaDTO;
	
	/**
	 * Lista de los casos que ha atendido el prestador
	 */
	private Long casosPersona;
	
	/**
	 * Lista con el desarrollo profesional del prestador
	 */
	private List<DesarrolloProfesionalDTO> desarrolloDTO;
	
	/**
	 * duracion del prestador con ese rol en una lista
	 */
	private Long duracion;
	
	/**
	 * evaluacion del conciliador
	 */
	private EvaluacionConciliador evaluacionConciliador;
	
	public Long getDuracion() {
		return duracion;
	}
	public void setDuracion(Long duracion) {
		this.duracion = duracion;
	}

	public boolean isSolicitudDeArbitraje() {
		return solicitudDeArbitraje;
	}
	public void setSolicitudDeArbitraje(boolean solicitudDeArbitraje) {
		this.solicitudDeArbitraje = solicitudDeArbitraje;
	}
	public List<DesarrolloProfesionalDTO> getDesarrolloDTO() {
		return desarrolloDTO;
	}
	public void setDesarrolloDTO(List<DesarrolloProfesionalDTO> desarrolloDTO) {
		this.desarrolloDTO = desarrolloDTO;
	}
	public Long getCasosPersona() {
		return casosPersona;
	}
	public void setCasosPersona(Long casosPersona) {
		this.casosPersona = casosPersona;
	}
	public MembresiaDTO getMembresiaDTO() {
		return membresiaDTO;
	}
	public void setMembresiaDTO(MembresiaDTO membresiaDTO) {
		this.membresiaDTO = membresiaDTO;
	}
	public SolicitudPrestador getSolicitudCambioLista() {
		return solicitudCambioLista;
	}
	public void setSolicitudCambioLista(SolicitudPrestador solicitudCambioLista) {
		this.solicitudCambioLista = solicitudCambioLista;
	}
	public EvaluacionConciliador getEvaluacionConciliador() {
		return evaluacionConciliador;
	}
	public void setEvaluacionConciliador(EvaluacionConciliador evaluacionConciliador) {
		this.evaluacionConciliador = evaluacionConciliador;
	}
	
}


