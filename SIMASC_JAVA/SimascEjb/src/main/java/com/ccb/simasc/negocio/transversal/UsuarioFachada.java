/**
 * 
 */
package com.ccb.simasc.negocio.transversal;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorFuncionalidadRol;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorUsuario;
import com.ccb.simasc.transversal.entidades.Funcionalidad;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.Usuario;

/**
 * @author lvalbuena
 *
 */
@Stateless
@LocalBean
public class UsuarioFachada {
	
	/**
	 * Maneja la info de usuarios
	 */
	@EJB
	private ManejadorUsuario manejadorUsuario;		
	
	/**
	 * Maneja la info de personas
	 */
	@EJB
	private ManejadorPersona manejadorPersona;
	
	@EJB
	private ManejadorFuncionalidadRol manejadorFuncionalidadRol;
	
	@EJB
	private ManejadorRolPersona manejadorRolPersona;
	
	/**
	 * obtiene el Usuario a partir de su username
	 * @param usuario
	 * @return
	 */
	public Usuario obtenerUsuario(String usuario) {
		return manejadorUsuario.buscar(usuario);		
	}
	
	/**
	 * Obtiene la persona a traves de su id
	 * @param idPersona
	 * @return
	 */
	public Persona obtenerPersona(Long idPersona) {
		Persona persona = manejadorPersona.buscar(idPersona);
		if (persona != null) {
			persona.setFuncionalidadesList(this.obtenerFuncionalidadesSession(idPersona));
		}
		return persona;
	}
	
	/**
	 * obtiene la funcionalidades de la persona activa en session
	 * @param idPersona
	 * @return
	 */
	public List<Funcionalidad> obtenerFuncionalidadesSession(Long idPersona) {		
		List<Rol> roles = manejadorRolPersona.consultarRolesPersona(idPersona);
		List<Long> rolesId = new ArrayList<Long>();
		if (roles != null && !roles.isEmpty()) {
			for (Rol rol : roles) {
				rolesId.add(rol.getIdRol());
			}
		}
		return manejadorFuncionalidadRol.obtenerFuncionalidadesActivasPorRoles(rolesId);
	}
}
