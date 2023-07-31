package com.ccb.simasc.transversal.dto.formularios;

import java.io.Serializable;
import java.util.List;

import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.SolicitudServicioDTO;

public class SolicitudConciliacionDTO implements Serializable{
	
	/*
	 * Se reutiliza componente PersonaBasiDTO y se extienden nuevos parametros
	 */
	
	private static final long serialVersionUID = 1L;
	
	//Seccion Datos del Solicitante
	private PersonaBasicaDTO personaBasicaDTO;
	private List<CorreoElectronicoDTO> correoElectronicoPersona;
	//Seccion Centro de Concialiacion
	private CentroDTO centro;
	//Seccion Descripcion del Caso
	private SolicitudServicioDTO solicitudServicioDTO;	
	
	public PersonaBasicaDTO getPersonaBasicaDTO() {
		return personaBasicaDTO;
	}
	public void setPersonaBasicaDTO(PersonaBasicaDTO personaBasicaDTO) {
		this.personaBasicaDTO = personaBasicaDTO;
	}
	public List<CorreoElectronicoDTO> getCorreoElectronicoPersona() {
		return correoElectronicoPersona;
	}
	public void setCorreoElectronicoPersona(List<CorreoElectronicoDTO> correoElectronicoPersona) {
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
