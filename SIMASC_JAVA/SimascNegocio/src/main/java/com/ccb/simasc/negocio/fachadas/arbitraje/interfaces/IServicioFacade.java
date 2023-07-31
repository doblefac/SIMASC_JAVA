package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.InformacionFiltroDTO;
import com.ccb.simasc.transversal.dto.ServicioDTO;
import com.ccb.simasc.transversal.entidades.Servicio;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Servicio}
 * @author sMartinez
 *
 */
@Local
public interface IServicioFacade extends IAccesoFacade<Servicio, Long, ServicioDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	 public List<Servicio> consultarServicios();
	 
	 public List<Servicio> consultarServiciosFiltros(List<InformacionFiltroDTO> filtrosParametro);
	 
	 /**
	  * Metodo que retorna un Servicio dado su nombre
	  * @param nombre
	  * @return
	  */
	 public Servicio getServicioPorNombre(String nombre);
	 
	 /**
	  * Retorna una lista de servicios que su estado del registro se encuentre activo
	  * @param tipo tipo de servicio (PDL o PJT)
	  * @return  List<Servicio>
	  */
	 public List<ServicioDTO> consultarServiciosPorTipo( String tipo );
	 
	 /** CON-F-087, Consulta el servicio de un caso 
	  * @param idCaso
	  * @return ServicioDTO
	  */
	 public ServicioDTO consultarServicioDelCaso( Long idCaso );
	 
	 // public List<Servicio> consultarServiciosPorTipo(String tipoServicio);

	 public Servicio consultarServicioPorIdServicio(Long idServicio);
	// protected region metodos adicionales end
	 
	 public List<ServicioDTO> consultarServiciosPorPersona(long idPersona);
	 public List<ServicioDTO> consultarServiciosParaTarifador();
	 public List<ServicioDTO> consultarServiciosPorPersonaMateria(long idPersona);
	
}
