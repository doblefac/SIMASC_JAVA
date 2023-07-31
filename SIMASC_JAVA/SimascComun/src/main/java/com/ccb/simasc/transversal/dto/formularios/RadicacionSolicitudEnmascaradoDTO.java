package com.ccb.simasc.transversal.dto.formularios;

import java.io.Serializable;
import java.util.List;

import com.ccb.simasc.transversal.dto.CorreoElectronicoEnmascaradoDTO;
import com.ccb.simasc.transversal.dto.SolicitudServicioDTO;

public class RadicacionSolicitudEnmascaradoDTO implements Serializable {

	/*
	 * Se reutiliza componente PersonaBasicaDTO y se extienden nuevos parametros
	 */
	
	private static final long serialVersionUID = 1L;
	
	//Seccion Datos del Solicitante
	private PersonaBasicaDTO personaBasicaDTO;
	private List<CorreoElectronicoEnmascaradoDTO> correoElectronicoPersona;
	
	//Seccion Descripcion del Caso
	private SolicitudServicioDTO solicitudServicioDTO;	
	
	//Seccion Condiciones Generales insolvencia
	private CondicionesGeneralesDTO condicionesGeneralesDTO;
	
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
	public SolicitudServicioDTO getSolicitudServicioDTO() {
		return solicitudServicioDTO;
	}
	public void setSolicitudServicioDTO(SolicitudServicioDTO solicitudServicioDTO) {
		this.solicitudServicioDTO = solicitudServicioDTO;
	}
	public CondicionesGeneralesDTO getCondicionesGeneralesDTO() {
		return condicionesGeneralesDTO;
	}
	public void setCondicionesGeneralesDTO(CondicionesGeneralesDTO condicionesGeneralesDTO) {
		this.condicionesGeneralesDTO = condicionesGeneralesDTO;
	}
	
}
