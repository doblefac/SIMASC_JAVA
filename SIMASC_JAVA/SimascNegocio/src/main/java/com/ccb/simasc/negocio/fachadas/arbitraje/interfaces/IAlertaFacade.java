package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.AlertaBasicaDTO;
import com.ccb.simasc.transversal.dto.AlertaDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Alerta;
import com.ccb.simasc.transversal.entidades.Caso;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Alerta}
 * @author sMartinez
 *
 */
@Local
public interface IAlertaFacade extends IAccesoFacade<Alerta, Long, AlertaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones


	/**
	 * consulta las alertas por estado y tipo de servicio.
	 * @param estado
	 * @param tipoServicio
	 * @return
	 */
	public List<AlertaBasicaDTO> consultarAlertasPorServicioEstado(String estado, String tipoServicio);

	/**
	 * Actualiza una alaerta con sus roles y persona de envio
	 * @param alerta
	 */
	public void actualizarAlerta(AlertaBasicaDTO alerta);

	/**
	 * Metodo encargado de modificar los tags de los correos.
	 * @param plantillaHTML
	 * @param filtros
	 * @return
	 */
	public String reemplazarTextoAlertas(String plantillaHTML, Map<String, String> filtros);

	/**
	 * Consulta las persona y sus correos principales para notificar
	 * @param alerta
	 * @return
	 */

	public List<PersonaBasicaDTO> consultarPersonasNotificar(Long idCaso, Long idAlerta, List<Long> centros, Long idConvenio, List<Long> idPersonas);
	/**
	 * Actualiza las alertas que se van a ejecutar en el dia.
	 */
	public void actualizarEjecucionDiaria();


	/**
	 * Metodo encargado de actualizar a ejecutada una alerta
	 * @param idAlerta
	 * @param estado
	 */
	public void actualizarAlertaEjecutada(Long idAlerta, String estado);
	
	
	/**
	 * Metodo encargado de actualizar a ejecutada una alerta
	 * @param idAlerta
	 */
	public void actualizarAlertaEjecutada(Long idAlerta);
	
	public List<Alerta> consultaAlertaPorCodigo(String codigo);
	
	public void programaAlertaVencimientoPago(Caso caso, Long idPersona);
	// protected region metodos adicionales end
	
}
