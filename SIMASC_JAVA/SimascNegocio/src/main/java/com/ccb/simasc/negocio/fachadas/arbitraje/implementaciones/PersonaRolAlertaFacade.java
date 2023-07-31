package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta seccion sus modificaciones

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorAlerta;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaRolAlerta;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaRolAlertaFacade;
import com.ccb.simasc.transversal.dto.AlertaBasicaDTO;
import com.ccb.simasc.transversal.dto.PersonaRolAlertaDTO;
import com.ccb.simasc.transversal.dto.RolBasicoDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Alerta;
import com.ccb.simasc.transversal.entidades.PersonaRolAlerta;
import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link PersonaRolAlerta}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class PersonaRolAlertaFacade extends AccesoFacade<PersonaRolAlerta, BigDecimal, PersonaRolAlertaDTO>
		implements IPersonaRolAlertaFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	@EJB
	private ManejadorPersonaRolAlerta manejadorPersonaRolAlerta;

	@EJB
	private ManejadorAlerta manejadorAlerta;

	@EJB
	private ManejadorRolPersona manejadorRolPersona;

	@Inject
	private ApplicationRequestContext appContext;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorPersonaRolAlerta;
	}

	@Override
	public PersonaRolAlertaDTO transformarSinDependencias(PersonaRolAlerta obj) {
		PersonaRolAlertaDTO dto = new PersonaRolAlertaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public PersonaRolAlertaDTO transformarConDependencias(PersonaRolAlerta obj) {
		PersonaRolAlertaDTO dto = new PersonaRolAlertaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public PersonaRolAlerta transformarEntidadConDependencias(PersonaRolAlerta obj) {
		return new PersonaRolAlertaDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public PersonaRolAlerta transformarEntidadSinDependencias(PersonaRolAlerta obj) {
		return new PersonaRolAlertaDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public Alerta modificarNotificadosPorAlertaBasica(AlertaBasicaDTO alertaBasica) {
		Alerta alerta = manejadorAlerta.buscar(alertaBasica.getAlerta().getIdAlerta());
		if (alerta == null) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR315.toString()));
		}
		List<PersonaRolAlerta> listaCopia = new ArrayList<PersonaRolAlerta>(alerta.getPersonaRolAlertaList());
		this.actualizarNotificados(listaCopia, alertaBasica.getRoles(), alertaBasica.getPersonas(),
				alerta.getIdAlerta());
		return alerta;
	}

	/**
	 * actualiza la lista de notificados respeto los roles y las personas que envia
	 * 
	 * @param notificados
	 * @param roles
	 * @param personas
	 */
	private void actualizarNotificados(List<PersonaRolAlerta> notificados, List<RolBasicoDTO> roles,
			List<PersonaBasicaDTO> personas, Long idAlerta) {
		this.actualizarEstadoPersonaRolAlerta(notificados, UtilDominios.ESTADO_REGISTRO_INACTIVO);

		if (roles != null) {
			this.actualizarRoles(roles, notificados, idAlerta);
		}

		if (personas != null) {
			this.actualizarPersonas(personas, notificados, idAlerta);
		}

	}

	/**
	 * actulaiza los roles por la lista de notificados,si no lo actualiza lo crea
	 * 
	 * @param roles
	 * @param notificados
	 * @param idAlerta
	 */
	private void actualizarRoles(List<RolBasicoDTO> roles, List<PersonaRolAlerta> notificados, Long idAlerta) {
		for (RolBasicoDTO rolFor : roles) {
			boolean actuAliazado = false;
			for (PersonaRolAlerta personaRolFor : notificados) {
				if (personaRolFor.getIdRol() == rolFor.getIdRol()) {
					actualizarEstadoPersonaRolAlerta(personaRolFor, UtilDominios.ESTADO_REGISTRO_ACTIVO);
					actuAliazado = true;
					break;
				}
			}
			if (!actuAliazado) {
				this.crearPersonaRolAlerta(idAlerta, rolFor.getIdRol(), null);
			}
		}
	}

	/**
	 * Actualiza las personas por la lista de notificados, si no existe la crea
	 * 
	 * @param personas
	 * @param notificados
	 * @param idAlerta
	 */
	private void actualizarPersonas(List<PersonaBasicaDTO> personas, List<PersonaRolAlerta> notificados,
			Long idAlerta) {
		for (PersonaBasicaDTO personaFor : personas) {
			boolean actuAliazado = false;
			for (PersonaRolAlerta personaRolFor : notificados) {
				if (personaRolFor.getIdPersona() == personaFor.getIdPersona()) {
					actualizarEstadoPersonaRolAlerta(personaRolFor, UtilDominios.ESTADO_REGISTRO_ACTIVO);
					actuAliazado = true;
					break;
				}
			}
			if (!actuAliazado) {
				this.crearPersonaRolAlerta(idAlerta, null, personaFor.getIdPersona());
			}
		}
	}

	@Override
	public void crearPersonaRolAlerta(Long idAlerta, Long idRol, Long idPersona) {
		String idUsuario = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			idUsuario = appContext.getContextoSesion().getNombreUsuario();
		}
		PersonaRolAlerta personarolAlerta = new PersonaRolAlerta();
		personarolAlerta.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		personarolAlerta.setFechaUltimaModificacion(new Date());
		personarolAlerta.setIdUsuarioModificacion(idUsuario);
		personarolAlerta.setIdAlerta(idAlerta);
		if ((idRol != null && idPersona != null) || (idRol == null && idPersona == null)) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR316.toString()));
		}
		personarolAlerta.setIdPersona(idPersona);
		personarolAlerta.setIdRol(idRol);
		manejadorPersonaRolAlerta.crear(personarolAlerta);
	}

	@Override
	public void crearPersonaRolAlertaPorListas(Long idAlerta, List<RolBasicoDTO> roles,
			List<PersonaBasicaDTO> personas) {

		if (roles != null) {
			for (RolBasicoDTO rolFor : roles) {
				this.crearPersonaRolAlerta(idAlerta, rolFor.getIdRol(), null);
			}
		}
		if (personas != null) {
			for (PersonaBasicaDTO personaFor : personas) {
				this.crearPersonaRolAlerta(idAlerta, null, personaFor.getIdPersona());
			}
		}
	}

	/**
	 * actualiza una lista de PersonaRolAlerta por estado
	 * 
	 * @param personaRolAl
	 * @param estado
	 */
	private void actualizarEstadoPersonaRolAlerta(List<PersonaRolAlerta> personaRolAl, String estado) {
		if (personaRolAl != null) {
			for (PersonaRolAlerta personaRolAlertaFor : personaRolAl) {
				this.actualizarEstadoPersonaRolAlerta(personaRolAlertaFor, estado);
			}
		}
	}

	/**
	 * actuliza PersonaRolAlerta por estado
	 * 
	 * @param personaRolAl
	 * @param estado
	 */
	private void actualizarEstadoPersonaRolAlerta(PersonaRolAlerta personaRolAl, String estado) {
		String idUsuario = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			idUsuario = appContext.getContextoSesion().getNombreUsuario();
		}
		personaRolAl.setEstadoRegistro(estado);
		personaRolAl.setFechaUltimaModificacion(new Date());
		personaRolAl.setIdUsuarioModificacion(idUsuario);
		manejadorPersonaRolAlerta.actualizar(personaRolAl);

	}

	@Override
	public PersonaRolAlerta obtenerPersonaRolAlertaId50(Long idPersona, Long idRol) {
		return manejadorPersonaRolAlerta.obtenerPersonaRolAlertaId50(idPersona, idRol);
	}

	@Override
	public List<PersonaRolAlerta> obtenerListaPersonaRolAlertaByIdAlerta(Long idAlerta) {
		return manejadorPersonaRolAlerta.obtenerListaPersonaRolAlertaByIdAlerta(idAlerta);
	}

	public List<Long> consultaPersonasNotificarPorIdAlerta(Long idAlerta) {
		List<PersonaRolAlerta> personasRolAlerta = obtenerListaPersonaRolAlertaByIdAlerta(idAlerta);

		List<Long> personasNotificar = new ArrayList<>();

		for (PersonaRolAlerta personaRolAlerta : personasRolAlerta) {

			List<RolPersona> personasPorRoles = manejadorRolPersona
					.consultarRolPersonasPorRolesByIdRol(personaRolAlerta.getIdRol());

			for (RolPersona rolPersona : personasPorRoles) {
				personasNotificar.add(rolPersona.getIdPersona());
			}
		}
		return personasNotificar;
	}

	// protected region metodos adicionales end

}
