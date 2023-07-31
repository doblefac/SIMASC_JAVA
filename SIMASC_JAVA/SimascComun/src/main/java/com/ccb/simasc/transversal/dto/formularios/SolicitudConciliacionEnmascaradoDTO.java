package com.ccb.simasc.transversal.dto.formularios;

import java.io.Serializable;
import java.util.List;

import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoEnmascaradoDTO;
import com.ccb.simasc.transversal.dto.SolicitudServicioDTO;

public class SolicitudConciliacionEnmascaradoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private PersonaBasicaDTO personaBasicaDTO;
	private List<CorreoElectronicoEnmascaradoDTO> correoElectronicoPersona;
	private CentroDTO centro;
	private SolicitudServicioDTO solicitudServicioDTO;	
	
	public SolicitudConciliacionEnmascaradoDTO transformarAEnmascarando(SolicitudConciliacionDTO obj) {
		
		SolicitudConciliacionEnmascaradoDTO solicitudConciliacionEnmascaradoDTO = new SolicitudConciliacionEnmascaradoDTO();
		List<CorreoElectronicoEnmascaradoDTO> correosElectronicosEnmascaradosDTO = 
				new CorreoElectronicoDTO().transformarDTOColeccionEnmascarando(obj.getCorreoElectronicoPersona());
		
		solicitudConciliacionEnmascaradoDTO.setCentro(obj.getCentro());
		solicitudConciliacionEnmascaradoDTO.setPersonaBasicaDTO(obj.getPersonaBasicaDTO());
		solicitudConciliacionEnmascaradoDTO.setSolicitudServicioDTO(obj.getSolicitudServicioDTO());
		solicitudConciliacionEnmascaradoDTO.setCorreoElectronicoPersona(correosElectronicosEnmascaradosDTO);
		
		return solicitudConciliacionEnmascaradoDTO;
	}
	
	public PersonaBasicaDTO getPersonaBasicaDTO() {
		return personaBasicaDTO;
	}
	public void setPersonaBasicaDTO(PersonaBasicaDTO personaBasicaDTO) {
		this.personaBasicaDTO = personaBasicaDTO;
	}
	public List<CorreoElectronicoEnmascaradoDTO> getCorreoElectronicoPersona() {
		return correoElectronicoPersona;
	}
	public void setCorreoElectronicoPersona(List<CorreoElectronicoEnmascaradoDTO> correoElectronicoPersona) {
		this.correoElectronicoPersona = correoElectronicoPersona;
	}
	public CentroDTO getCentro() {
		return centro;
	}
	public void setCentro(CentroDTO centro) {
		this.centro = centro;
	}
	public SolicitudServicioDTO getSolicitudServicioDTO() {
		return solicitudServicioDTO;
	}
	public void setSolicitudServicioDTO(SolicitudServicioDTO solicitudServicioDTO) {
		this.solicitudServicioDTO = solicitudServicioDTO;
	}
	
	
}
