package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.SedeDTO;
import com.ccb.simasc.transversal.dto.SedeTipoServicioDTO;
import com.ccb.simasc.transversal.entidades.Sede;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Sede}
 * @author sMartinez
 *
 */
@Local
public interface ISedeFacade extends IAccesoFacade<Sede, Long, SedeDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	 public List<Sede> consultarSedes();
	 
	 public void crearSede(SedeTipoServicioDTO sede);

	 public void actualizarSede(SedeTipoServicioDTO sede);

	 /**
	  * consulta las sedes por un centro en particular
	  * @param idCentro
	  * @return
	  */
	public List<SedeDTO> consultarSedesPorCentro(List<Long> idCentro);
	 
	 /**  pRendon 6-02-2018
	  * CON-F-106 Metodo que consulta las sedes de un caso por el mismo servicio  
	  * @param idCaso
	  * @return  List<SedeDTO>
	  */
	 public List<SedeDTO> consultarSedesPorCasoServicio( Long idCaso, Long idPersona );
	 
	/**
	 * Método que consulta las sedes en las que el conciliador tiene audiencia en la fecha recibida
	 * @param fecha fecha para la cual se consultan las sedes de las audiencias
	 * @param idPersona identificador del conciliador 
	 * @return lista de sedes
	 */
	public List<SedeDTO> consultarSedesAudienciaConciliador(Date fecha, Long idPersona);
	
	/**
	 * metodo encargado de traer las sedes por en las que se presta un servicio
	 * @param idSede
	 * @return
	 */
	public List<SedeDTO> consultarSedesPorServicio(Long idServicio);

	// protected region metodos adicionales end

	
}
