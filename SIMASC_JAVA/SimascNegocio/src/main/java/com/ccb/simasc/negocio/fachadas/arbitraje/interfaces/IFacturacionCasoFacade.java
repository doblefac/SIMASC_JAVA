package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin

import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.FacturacionCasoDTO;
import com.ccb.simasc.transversal.dto.FiltrosTramiteOrdinarioDTO;
import com.ccb.simasc.transversal.dto.TramiteOrdinarioDTO;
import com.ccb.simasc.transversal.dto.reportes.FiltroReportesDTO;
import com.ccb.simasc.transversal.entidades.FacturacionCaso;
import com.ccb.simasc.transversal.dto.LiquidacionCasosConvenioDTO;

// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de
 * {@link FacturacionCaso}
 * 
 * @author sMartinez
 *
 */
@Local
public interface IFacturacionCasoFacade extends IAccesoFacade<FacturacionCaso, Long, FacturacionCasoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Realiza la consulta de los casos pendientes por cobrar en un rango de
	 * fecha.
	 * CON-C-027
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param idConciliador
	 * @return
	 */
	public List<TramiteOrdinarioDTO> casosPendienteCobroByConciliador(FiltrosTramiteOrdinarioDTO filtros);

	/**
	 * Calcula el Valor total por conciliador de los casos pendientes de cobros.
	 * 
	 * @param datosCasos
	 * @param idConciliador
	 * @return
	 */
	public List<TramiteOrdinarioDTO> calcularTotalByConciliador(List<TramiteOrdinarioDTO> datosCasos,
			long idConciliador);

	/**
	 * Metodo para aprobar los casos pendientes de cobro por el conciliador.
	 * 
	 * @param tramites
	 * @param idConciliador
	 * @param idUsuario
	 * @return
	 */
	public List<TramiteOrdinarioDTO> aprobarFacturaByConciliador(List<TramiteOrdinarioDTO> tramites, long idConciliador,
			long idUsuario);


	/**
	 * actualiza o crea el pago y la fecha de cobro de la facturacion del caso
	 * @param idCaso
	 * @param pagado
	 * @param fechaCobro
	 */
	public void actualizarFacturacionRutaCaso(FacturacionCaso facturacionCaso);

	/** CON-C-014 consulta la liquidacion de los casos del convenio que se seleccione
	 * @param filtros
	 * @return lista de casos con su liquidacion
	 */
	public List<LiquidacionCasosConvenioDTO> consultarLiquidacionCasosConvenio( FiltroReportesDTO filtros );
	
	/** CON-C-014 guarda la informacion de facturacion para casos de convenio y envia correo
	 * @return lista de casos con su liquidacion
	 */
	public void guardarFacturacionCasosConvenio( Long idConvenio , List<LiquidacionCasosConvenioDTO> listaFacturacion, String idUsuarioModificacion  );
	
	// protected region metodos adicionales end
}
