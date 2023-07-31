package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.integracion.manejadores.ManejadorPersonaEventoCcb;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaEventoCcbFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.PersonaEventoCcbDTO;
import com.ccb.simasc.transversal.entidades.PersonaEventoCcb;
import com.ccb.simasc.transversal.entidades.PersonaEventoCcbPK;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link PersonaEventoCcb}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class PersonaEventoCcbFacade extends AccesoFacade<PersonaEventoCcb, PersonaEventoCcbPK, PersonaEventoCcbDTO> implements IPersonaEventoCcbFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorPersonaEventoCcb manejadorPersonaEventoCcb;
	
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorPersonaEventoCcb;
	}

	@Override
	public PersonaEventoCcbDTO transformarSinDependencias(PersonaEventoCcb obj) {
		return new PersonaEventoCcbDTO().transformarSinDependencias(obj);
	}

	@Override
	public PersonaEventoCcbDTO transformarConDependencias(PersonaEventoCcb obj) {
		return new PersonaEventoCcbDTO().transformarConDependencias(obj);
	}

	@Override
	public PersonaEventoCcb transformarEntidadConDependencias(PersonaEventoCcb obj) {
		return new PersonaEventoCcbDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public PersonaEventoCcb transformarEntidadSinDependencias(PersonaEventoCcb obj) {
		return new PersonaEventoCcbDTO().transformarEntidadSinDependencias(obj);
	}

	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IPersonaEventoCcbFacade#actualizarAsociacion(java.lang.Long,
	 * java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public void actualizarAsociacion(Long idEventoCcb, Long idPersona, String estado, String estadoRegistro) {
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if(appContext!= null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() != null  )
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		PersonaEventoCcbPK pk = new PersonaEventoCcbPK(idEventoCcb, idPersona);
		PersonaEventoCcb personaEvento = buscar(pk);
		if (personaEvento == null) {
			personaEvento = new PersonaEventoCcb();
			personaEvento.setPersonaEventoCcbPK(pk);
			personaEvento.setEstadoRegistro(estadoRegistro);
			crear(personaEvento);
		}
		
		personaEvento.setEstado(estado);
		personaEvento.setEstadoRegistro(estadoRegistro);
		personaEvento.setFechaUltimaModificacion(new Date());
		personaEvento.setIdUsuarioModificacion(usuarioModificacion);
		actualizar(personaEvento);
	}
	
	// protected region metodos adicionales end
	
}
