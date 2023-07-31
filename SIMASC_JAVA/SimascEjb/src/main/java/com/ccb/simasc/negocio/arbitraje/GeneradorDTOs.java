package com.ccb.simasc.negocio.arbitraje;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.transversal.dto.ArbitroDTO;
import com.ccb.simasc.transversal.entidades.Persona;

/**
 * Utilidad para generar cualquiera de los DTOs de reportes.
 * 
 * @author jsoto
 *
 */
@Stateless
@LocalBean
public class GeneradorDTOs {
	
	/**
	 * Genera la información básica de un arbitro a partir de la 
	 * información en persona
	 * @param persona
	 * @return
	 */
	public ArbitroDTO generarArbitroDTO(Persona persona){
		
		ArbitroDTO arbitroDTO = new ArbitroDTO();
		
		arbitroDTO.setIdPersona(persona.getIdPersona());
		arbitroDTO.setNombreCompleto(persona.getNombreCompleto());
		
		return arbitroDTO;
	}

}
