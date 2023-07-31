package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.RecusacionDTO;
import com.ccb.simasc.transversal.dto.formularios.RegistroRecusacionDTO;
import com.ccb.simasc.transversal.entidades.Recusacion;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Recusacion}
 * @author sMartinez
 *
 */
@Local
public interface IRecusacionFacade extends IAccesoFacade<Recusacion, Long, RecusacionDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Registra una recusacion
	 * 
	 * @param idCaso
	 * @param idArbitro
	 * @param partes
	 * @param motivo
	 * @param idDocumento
	 * @return Recusacion
	 */
	 public Recusacion registrarRecusacion(Long idCaso, Long idArbitro,List<Long> partes, String motivo, Long idDocumento);
	 
	/**
	 * Registra la respuesa del arbitro a la recusacion.
	 * 
	 * @param idRecusacion
	 * @param fechaRespuesta
	 * @param acepta
	 * @param idDocumento
	 * @return Recusacion
	 */
	 public Recusacion registrarRespuestaRecusacion(Long idRecusacion, Date fechaRespuesta, boolean acepta, Long idDocumento);
	 
	/**
	 * Registra la respuesa del arbitro a la recusacion.
	 * 
	 * @param idRecusacion
	 * @param fechaConfirmacion
	 * @param confirmado
	 * @param juzgadoConfirmacion
	 * @param idDocumento
	 * @return Recusacion
	 */
	 public Recusacion registrarConfirmacionRecusacion(Long idRecusacion, Date fechaConfirmacion, boolean confirmado, String juzgadoConfirmacion, Long idDocumento);
	 
	/**
	 * Obtiene una lista de las recusaciones de un arbitro en un caso
	 * 
	 * @param idCaso
	 * @param idArbitro
	 * @return List<Recusacion>
	 */
	 public List<Object[]> obtenerRecusacionesArbitro(Long idCaso, Long idArbitro);
	 
	 /**
	  * Obtiene una lista de las recusaciones de un arbitro en un caso
	  * @param idArbitro
	  * @return List<Recusacion>
	  */
	 public List<Object[]> obtenerRecusacionesArbitro(Long idArbitro);
	 
	 /**
	  * Registra la recusación de un árbitro
	  * 
	  * @param recusacionDTO
	  * @throws SIMASCNegocioExcepcion
	  */
	 public void registrarRecusacionArbitro(RegistroRecusacionDTO recusacionDTO) throws SIMASCNegocioExcepcion;

	Recusacion crearRecusacion(Recusacion obj);

	// protected region metodos adicionales end
	
}
