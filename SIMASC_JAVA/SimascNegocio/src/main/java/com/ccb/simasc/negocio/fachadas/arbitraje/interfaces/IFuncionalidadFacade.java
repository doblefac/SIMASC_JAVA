package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.FuncionalidadDTO;
import com.ccb.simasc.transversal.entidades.Funcionalidad;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Funcionalidad}
 * @author sMartinez
 *
 */
@Local
public interface IFuncionalidadFacade extends IAccesoFacade<Funcionalidad, String, FuncionalidadDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Método para obtener las funcionalidades asociadas a un tipo de servicio
	 * y marcando aquellas en las que se encuentre el rol asociado
	 * @param idRol
	 * @param tipoServicio
	 * @param obtenerHijos
	 * @param obtenerPermisos
	 * @return
	 */
	public List<Funcionalidad> consultarFuncionalidadesRol(Long idRol, String tipoServicio
			, boolean obtenerHijos, boolean obtenerPermisos);
	
	/**
	 * Método que actualiza las funcionalidades asociadas al rol
	 * @param funcionalidades
	 * @param idRol
	 */
	public void actualizarFuncionalidadesRol(List<Funcionalidad> funcionalidades, Long idRol);
	
	/**
	 * Método para obtener el listado de funcionalidades que son hijas de una funcionalidad
	 * @param funcionalidadPadre
	 * @param idRol
	 * @param obtenerHijos
	 * @param obtenerPermisos
	 * @return
	 */
	public List<Funcionalidad> consultarFuncionalidadesPorPadre(String funcionalidadPadre, Long idRol
			, boolean obtenerHijos, boolean obtenerPermisos);
	// protected region metodos adicionales end
	
}
