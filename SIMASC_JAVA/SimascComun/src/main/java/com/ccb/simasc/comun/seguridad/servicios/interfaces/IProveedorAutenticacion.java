package com.ccb.simasc.comun.seguridad.servicios.interfaces;

import java.util.List;

import javax.ejb.Local;

/**
 * 
 * @author fguzman
 *
 */
@Local
public interface IProveedorAutenticacion {

	public boolean[] autenticar(String nombreUsuario, List<Object> parametrosValidacion,
			List<String> metodosAutenticacion);

	public boolean autenticar(List<Object> parametros);

}
