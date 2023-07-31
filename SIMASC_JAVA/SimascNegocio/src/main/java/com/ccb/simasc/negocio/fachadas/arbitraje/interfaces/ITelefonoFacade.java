package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.TelefonoDTO;
import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.entidades.Telefono;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Telefono}
 * @author sMartinez
 *
 */
@Local
public interface ITelefonoFacade extends IAccesoFacade<Telefono, Long, TelefonoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/**
	 * Se obtienes los telefonos asociados  una persona
	 * @param idPersona
	 * @return List<GenericoDTO>
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<GenericoDTO> consultarTelefonosPersona(Long idPersona) throws SIMASCNegocioExcepcion;
	
	/**
	 * elimina logicamente los telefonos activos de una persona
	 * @param idPersona
	 * @throws SIMASCNegocioExcepcion
	 */
	public void eliminarTelefonosPersona(Long idPersona) throws SIMASCNegocioExcepcion;
	/**
	 * Crea los telefonos de las personas sin realizar actualizacion.
	 * @param numeroTelefono
	 * @param tipoTelefono
	 * @param idPersona
	 */
	public void crearTelefonoPersona(String numeroTelefono, String tipoTelefono, Long idPersona, Long idTelefono, String estadoRegistro);
	
	public Long buscarTelefonosPersona(Long idPersona, String tipoTelefono) throws SIMASCNegocioExcepcion;
	
	// protected region metodos adicionales end
	
}
