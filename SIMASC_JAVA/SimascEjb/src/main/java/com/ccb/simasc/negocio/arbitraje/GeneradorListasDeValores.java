package com.ccb.simasc.negocio.arbitraje;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.transversal.dto.ArbitroDTO;
import com.ccb.simasc.transversal.entidades.Persona;

/**
 * Clase que ofrece la generación de listas de valores (campos select) a 
 * la vista.
 * @author jsoto
 *
 */
@Stateless
@LocalBean
public class GeneradorListasDeValores {

	@EJB
	private ManejadorPersona manejadorPersona;
	
	@EJB
	private GeneradorDTOs generadorDTOs;
	
	public List<ArbitroDTO> consultarArbitrosDTO(){
		List<ArbitroDTO> arbitros = new ArrayList<>();
		
		List<Persona> personas = manejadorPersona.consultarArbitrosOrdenadosAlfabeticamente();
		
		for(Persona persona : personas){
			arbitros.add(generadorDTOs.generarArbitroDTO(persona));
		}
		
		return arbitros;
	}
	
	public List<ArbitroDTO> consultarOperadoresDTO(){
		List<ArbitroDTO> arbitros = new ArrayList<>();
		
		List<Persona> personas = manejadorPersona.consultarOperadoresOrdenadosAlfabeticamente();
		
		for(Persona persona : personas){
			arbitros.add(generadorDTOs.generarArbitroDTO(persona));
		}
		
		return arbitros;
	}
}
