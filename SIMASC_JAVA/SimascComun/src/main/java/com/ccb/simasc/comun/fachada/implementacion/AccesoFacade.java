package com.ccb.simasc.comun.fachada.implementacion;


import java.util.Collection;

import javax.persistence.LockModeType;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionOrdenamiento;
import com.ccb.simasc.integracion.manejadores.utilidades.RangoConsulta;
import com.ccb.simasc.transversal.dto.IDTO;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;

/**
 * Implementación abstracta para una fachada de acceso RESTFULL
 * @author sMartinez
 *
 * @param <E>
 * @param <PK>
 */
@SuppressWarnings({"unchecked",	"rawtypes"})
public abstract class AccesoFacade<E extends Object,PK,DTO extends IDTO> implements IAccesoFacade<E,PK,DTO>{
	/**
	 * Constructor pore defecto
	 */
	public AccesoFacade(){	}
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public E buscar(PK id) {
		return (E)getManejadorCrud().buscar(id);
	}
	
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public E buscar(PK id, LockModeType lockMode) {
		return (E)getManejadorCrud().buscar(id, lockMode);
	}
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public E crear() {
		return (E)getManejadorCrud().crear();
	}
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void crear(E obj) {
		 getManejadorCrud().crear(obj);
	}
	
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void crearSinAtributosDeAuditoria(E obj) {
		 getManejadorCrud().crearSinAtributosAuditoria(obj);
	}
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void actualizar(E obj) {
		getManejadorCrud().actualizar(obj);
	}
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void actualizarSinAtributosDeAuditoria(E obj) {
		getManejadorCrud().actualizarSinAtributosAuditoria(obj);
	}
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void eliminar(E obj) {
		getManejadorCrud().eliminar(obj);
	}
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void eliminarPorId(PK id) {
		getManejadorCrud().eliminarPorId(id);
	}
	
	
	
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public Collection<DTO> transformarColeccionSinDependencias(Collection<E> coleccion, Collection<DTO> empty){
	  for(E entidad:coleccion)
		  empty.add(transformarSinDependencias(entidad));
	  return empty;
	}
	
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public Collection<DTO> transformarColeccionConDependencias(Collection<E> coleccion, Collection<DTO> empty){
	  for(E entidad:coleccion)
		  empty.add(transformarConDependencias(entidad));
	  return empty;
	}
	
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public Collection<E> transformarEntidadesColeccionSinDependencias(Collection<E> coleccion, Collection<E> empty){
	  for(E entidad:coleccion)
		  empty.add(transformarEntidadSinDependencias(entidad));
	  return empty;
	}
	
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public Collection<E> transformarEntidadesColeccionConDependencias(Collection<E> coleccion, Collection<E> empty){
	  for(E entidad:coleccion)
		  empty.add(transformarEntidadConDependencias(entidad));
	  return empty;
	}
	
	/**
	 * {@inheritDoc}}
	 */
	public Collection<DTO> obtenerPorFiltro(Collection<InformacionFiltro> filtros, Collection<InformacionOrdenamiento> informacionOrdenamiento, RangoConsulta rangoConsulta, Collection<DTO> empty, Boolean modoDependencias){
		Collection<E> elementos = getManejadorCrud().consultar(filtros, informacionOrdenamiento, rangoConsulta);
		return  (modoDependencias)?transformarColeccionConDependencias(elementos,empty):transformarColeccionSinDependencias(elementos, empty);
		
	}
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public Collection<DTO> obtenerTodos(Collection<DTO> empty,Boolean full) {
		Collection<E> coleccion = getManejadorCrud().consultar(null, null, null);
		return (full)?transformarColeccionConDependencias(coleccion,empty) : transformarColeccionSinDependencias(coleccion, empty);	
	}
	
	
	/**
	 * {@inheritDoc}}
	 */
	public Collection<E> obtenerEntidadesPorFiltro(Collection<InformacionFiltro> filtros, Collection<InformacionOrdenamiento> informacionOrdenamiento, RangoConsulta rangoConsulta, Collection<E> empty, Boolean modoDependencias){
		Collection<E> elementos = getManejadorCrud().consultar(filtros, informacionOrdenamiento, rangoConsulta);
		return  (modoDependencias)?transformarEntidadesColeccionConDependencias(elementos,empty):transformarEntidadesColeccionSinDependencias(elementos, empty);
		
	}
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public Collection<E> obtenerEntidadesTodos(Collection<E> empty,Boolean full) {
		Collection<E> coleccion = getManejadorCrud().consultar(null, null, null);
		return (full)?transformarEntidadesColeccionConDependencias(coleccion,empty) : transformarEntidadesColeccionSinDependencias(coleccion, empty);	
	}
	
	/**
	 * Se lanza la excepción con el código definido en MensajesEnum
	 * @param codigoMensaje
	 */
	public void lanzarSIMASCNegocioExcepcion(String codigoMensaje){
		throw new SIMASCNegocioExcepcion(
				MensajesSimasc.getInstancia().getMensajePorKey(codigoMensaje));
	}
	   
	   
}
