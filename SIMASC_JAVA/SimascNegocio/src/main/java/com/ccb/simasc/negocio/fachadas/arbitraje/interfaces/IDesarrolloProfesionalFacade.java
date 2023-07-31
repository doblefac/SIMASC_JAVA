package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.DesarrolloProfesionalDTO;
import com.ccb.simasc.transversal.entidades.DesarrolloProfesional;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link DesarrolloProfesional}
 * @author sMartinez
 *
 */
@Local
public interface IDesarrolloProfesionalFacade extends IAccesoFacade<DesarrolloProfesional, Long, DesarrolloProfesionalDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Método para consultar los datos de desarrollo profesional de un usuario de acuerdo al tipo de desarrollo
	 * @param nombreUsuario
	 * @param tipoDesarrollo
	 * @return
	 */
	public List<DesarrolloProfesional> consultarDesarrolloProfesional (Long idPersona, String tipoDesarrollo, Date fechaDesde, Date fechaHasta);
	
	/**
	 * Método para actualizar un desarrollo profesional
	 * @param desarrolloProfesional
	 */
	public void actualizarDesarrolloProfesional (DesarrolloProfesional desarrolloProfesional);
	
	/**
	 * Transforma List<DesarrolloProfesionalDTO> a List<DesarrolloProfesionalDTO> sin dependencias.
	 * @param idPersona
	 * @param tipoDesarrollo
	 * @return List<DesarrolloProfesionalDTO>
	 */
	public List<DesarrolloProfesionalDTO> consultarDesarrolloProfesionalSinDependencias( Long idPersona, String tipoDesarrollo, Date periodo );
	
	/**
	 * Método para consultar  desarrollo profesional
	 * @param DesarrolloProfesionalDTO
	 */
	public List<DesarrolloProfesionalDTO> consultarDesarrolloProfesional (DesarrolloProfesionalDTO desarrolloProfesional);
	
	// protected region metodos adicionales end
	
}
