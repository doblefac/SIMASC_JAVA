package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.AlertaBasicaDTO;
import com.ccb.simasc.transversal.dto.PersonaRolAlertaDTO;
import com.ccb.simasc.transversal.dto.RolBasicoDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Alerta;
import com.ccb.simasc.transversal.entidades.PersonaRolAlerta;
import java.math.BigDecimal;
import java.util.List;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link PersonaRolAlerta}
 * @author sMartinez
 *
 */
@Local
public interface IPersonaRolAlertaFacade extends IAccesoFacade<PersonaRolAlerta, BigDecimal, PersonaRolAlertaDTO> {


	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * crea un registro en persona rol alerta.
	 * @param idAlerta
	 * @param idRol
	 * @param idPersona
	 */
	public void crearPersonaRolAlerta(Long idAlerta, Long idRol, Long idPersona);

	/**
	 * Crea personaRolAlera por lista de roles basicos o personaBasicas
	 * @param idAlerta
	 * @param roles
	 * @param persona
	 */
	public void crearPersonaRolAlertaPorListas(Long idAlerta, List<RolBasicoDTO> roles, List<PersonaBasicaDTO> persona);

	/**
	 * modifica los notificados con la lista de persona basicas y rolesbasicos que estan en el dto de alertaBasicaDTO
	 * @param alertaBasica
	 * @return
	 */
	public Alerta modificarNotificadosPorAlertaBasica(AlertaBasicaDTO alertaBasica);
	
	
	public PersonaRolAlerta obtenerPersonaRolAlertaId50(Long idPersona,Long idRol); 


	public List<PersonaRolAlerta> obtenerListaPersonaRolAlertaByIdAlerta(Long idAlerta);
	
	public List<Long> consultaPersonasNotificarPorIdAlerta(Long idAlerta);
	// protected region metodos adicionales end
	
}
