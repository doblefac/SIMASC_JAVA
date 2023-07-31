package com.ccb.simasc.comun.fachada.interfaces;

import java.util.Collection;
import javax.persistence.LockModeType;

import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionOrdenamiento;
import com.ccb.simasc.integracion.manejadores.utilidades.RangoConsulta;

/**
 * Interface general de acceso orientada a REST para fachadas en capa de negocio SIMASC 
 * @author smartinez
 *
 * @param <E> entidad de negocio
 * @param <PK> llave primaria
 */
public interface IAccesoFacade<E,PK,DTO> {
   /**
    * Realiza una busqueda de entidad por su llave primaria
    * @param id
    * @return entidad, null si no existe
    */
   public E buscar(PK id);
   /**
    * Realiza una busqueda de entidad por su llave primaria
    * @param id
    * @param lockMode Tipo de bloqueo que maneja la transacción
    * @return entidad, null si no existe
    */
   public E buscar(PK id, LockModeType lockMode);
   /**
    * Crea una entidad con campos vacios dado el tipo <E>
    * @return entidad <E>
    */
   public E crear();
   /**
    * Crea una entidad dado un objeto del tipo <E>
    * @param obj
    * @return
    */
   public void crear(E obj);
   /**
    * Actualiza una entidad de tipo <E> dado su definición de objeto
    * @param obj
    */
   public void actualizar(E obj);
   
   /**
    * Actualiza una entidad de tipo <E> dado su definición de objeto
    * sin los atributos de fecha ultima modificacion y nombre usuario modificacion
    * @param obj
    */
   public void actualizarSinAtributosDeAuditoria(E obj);
   
   /**
    * Crea una entidad de tipo <E> dado su definición de objeto
    * sin los atributos de fecha ultima modificacion y nombre usuario modificacion
    * @param obj
    */
   public void crearSinAtributosDeAuditoria(E obj);
   
   /**
    * Elimina una entidad de tipo <E> dado su definición de objeto
    * @param obj
    */
   public void eliminar(E obj);
   /**
    * Elimina una entidad de tipo <E> dado su id de tipo <PK>
    * @param obj
    */
   public void eliminarPorId(PK id);
   
   /**
    * Obtiene todas las entidades de tipo <DTO>
    * @param empty
    * @param b
    * @return
    */
   public Collection<DTO> obtenerTodos(Collection<DTO> empty,Boolean modoDependencias);
   
   /**
    * Obtiene todas las entidades de tipo <E>
    * @param empty
    * @param b
    * @return
    */
   public Collection<E> obtenerEntidadesTodos( Collection<E> empty, Boolean modoDependencias);
   
   
   
   /**
    * Obtiene todas las entidades de tipo <DTO> por filtro
    * @param filtros
    * @param informacionOrdenamiento
    * @param rangoConsulta
    * @param empty
    * @param modoDependencias
    * @return
    */
   public Collection<DTO> obtenerPorFiltro(Collection<InformacionFiltro> filtros, Collection<InformacionOrdenamiento> informacionOrdenamiento
		                                   , RangoConsulta rangoConsulta , Collection<DTO> empty, Boolean modoDependencias);
   
   /**
    * Obtiene todas las entidades de tipo <E> por filtro
    * @param filtros
    * @param informacionOrdenamiento
    * @param rangoConsulta
    * @param empty
    * @param modoDependencias
    * @return
    */
   public Collection<E> obtenerEntidadesPorFiltro(Collection<InformacionFiltro> filtros, Collection<InformacionOrdenamiento> informacionOrdenamiento
		                                   , RangoConsulta rangoConsulta , Collection<E> empty, Boolean modoDependencias);
   
   
   
   public IManejadorCrud getManejadorCrud();
   /**
    * Transforma una entidad de tipo <E> a su <DTO> sin dependencias
    * @param Obj
    * @return
    */
   public DTO transformarSinDependencias(E obj);
   
   /**
    * Transforma una entidad de tipo <E> a su <DTO> con dependencias
    * @param Obj
    * @return
    */
   public E transformarEntidadConDependencias(E obj);
   
   /**
    * Transforma una entidad de tipo <E> a su <DTO> sin dependencias
    * @param Obj
    * @return
    */
   public E transformarEntidadSinDependencias(E obj);
   
   /**
    * Transforma una entidad de tipo <E> a su <DTO> con dependencias
    * @param Obj
    * @return
    */
   public DTO transformarConDependencias(E obj);
   
   
   /**
    * Transforma una colección de <E> en sus respectivos <DTO> con dependencias
    * @param coleccion
    * @return
    */
   public Collection<DTO> transformarColeccionConDependencias(Collection<E> coleccion, Collection<DTO> empty);
   /**
    * Transforma una colección de <E> en sus respectivos <DTO> sin dependencias
    * @return
    */
   public Collection<DTO> transformarColeccionSinDependencias (Collection<E> coleccion, Collection<DTO> empty);
   
   
   /**
    * Transforma una colección de <E> en sus respectivos <E> con dependencias
    * @param coleccion
    * @return
    */
   public Collection<E> transformarEntidadesColeccionConDependencias(Collection<E> coleccion, Collection<E> empty);
  
   /**
    * Transforma una colección de <E> en sus respectivos <E> sin dependencias
    * @return
    */
   public Collection<E> transformarEntidadesColeccionSinDependencias (Collection<E> coleccion, Collection<E> empty);
}
