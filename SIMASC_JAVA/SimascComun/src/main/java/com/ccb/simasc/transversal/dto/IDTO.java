package com.ccb.simasc.transversal.dto;

import java.util.Collection;

/**
 * Interfaz generica de comportamiento para objetos de transferencia DTO
 * @author sMartinez
 *
 * @param <E>
 */
public abstract class IDTO<E> {
	public abstract IDTO transformarSinDependencias(E obj);
	public abstract IDTO transformarConDependencias(E obj);
	public abstract E transformarEntidadSinDependencias(E obj);
	public abstract E transformarEntidadConDependencias(E obj);
	 /**
	  * Transforma una colección de <E> en sus respectivos <DTO> con dependencias
	  * @param coleccion
	  * @return
	  */
	public abstract Collection transformarColeccionConDependencias(Collection<E> coleccion);
	/**
	 * Transforma una colección de <E> en sus respectivos <DTO> sin dependencias
	* @return
	*/
	public abstract Collection transformarColeccionSinDependencias (Collection<E> coleccion);
	 /**
	  * Transforma una colección de <E> en sus respectivos <DTO> con dependencias
	  * @param coleccion
	  * @return
	  */
	public abstract Collection transformarColeccionEntidadesConDependencias(Collection<E> coleccion);
	/**
	 * Transforma una colección de <E> en sus respectivos <DTO> sin dependencias
	* @return
	*/
	public abstract Collection transformarColeccionEntidadesSinDependencias (Collection<E> coleccion);
	
}
