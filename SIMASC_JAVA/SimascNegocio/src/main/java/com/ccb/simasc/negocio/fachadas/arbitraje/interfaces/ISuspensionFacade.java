package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.SuspensionDTO;
import com.ccb.simasc.transversal.entidades.Suspension;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Suspension}
 * @author sMartinez
 *
 */
@Local
public interface ISuspensionFacade extends IAccesoFacade<Suspension, Long, SuspensionDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Obtiene los dias de suspecion del caso
	 * @param idCaso
	 * @param tipo
	 * @return
	 */
	public int obtenerDiasSuspendidos(Long idCaso, String tipo);
	/**
	 * Obtiene la suspension del caso
	 * @param idCaso
	 * @return
	 */
	public List<HashMap<String, Object>> obtenerSuspensiones(Long idCaso);
	/**
	 * Obtiene los dias habiles suspendidos
	 * @param idCaso
	 * @param tipo
	 * @return
	 */
	public int obtenerDiasHabilesSuspendidos(Long idCaso, String tipo);
	
	/**
	 * Metodo que obtiene los dias habiles de suspension que se han cumplido a
	 * la fecha de consulta.
	 * 
	 * @param idCaso
	 * @param tipo
	 * @return int
	 */
	public int obtenerDiasHabilesSuspendidosCumplidos(Long idCaso, String tipo);
	
	/**
	 * retorna los tipos de suspensioines de un caso
	 * @param idCaso
	 * @param tipoSupensiones
	 * @return
	 */
	public List<Suspension> consultarSuspensionId(Long idCaso);
	
	/**
	 * generar la suspension del caso en etapa prearbitral
	 * @param suspension
	 * @throws SIMASCNegocioExcepcion
	 */
	public void crearSuspensionPreArb(Suspension suspension)  throws SIMASCNegocioExcepcion;
	
	
	/**
	 * actualiza la suspension del caso en etapa prearbitral
	 * @param suspension 
	 * @throws SIMASCNegocioExcepcion
	 */
	public void actualizarSuspensionPreArb(Suspension suspension)  throws SIMASCNegocioExcepcion;

	public int obtenerDiasHabiles(Date fechaIni, Date fechaFin);

	public Date adicionarDiasHabilesFecha(Date fecha, int dias);

	void guardarSuspension(SuspensionDTO suspensionDTO);
	public int obtenerDiasCalendarioSuspendidos(Long idCaso, String tipo);
	public int obtenerDiasSuspendidosPartes(Long idCaso);
	
	// protected region metodos adicionales end
	
}
