package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.InfoReliquidacionPagoCaso;
import com.ccb.simasc.transversal.dto.NotificacionPagoDTO;
import com.ccb.simasc.transversal.dto.PagoCasoDTO;
import com.ccb.simasc.transversal.dto.ReliquidarPagoCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioGenerarPagoPup;
import com.ccb.simasc.transversal.entidades.PagoCaso;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link PagoCaso}
 * @author sMartinez
 *
 */
@Local
public interface IPagoCasoFacade extends IAccesoFacade<PagoCaso, String, PagoCasoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

 
		/**
		 * Obtiene el pago del caso por el numero del recibo
		 * @param numeroRecibo
		 * @return
		 */
		public PagoCaso obtenerPagoSolicitud(String numeroRecibo);

		/**
		 * Obtiene el pago del caso por estado
		 * @param estadoPago
		 * @return
		 */
		public Collection<PagoCaso> obtenerPagosCasosPorEstado(String estadoPago);

		/**
		 * Obtiene el pago del caso por el numero del recibo
		 * @param numRecibo
		 * @return
		 */
		public PagoCaso obtenerPagosPorNumeroRecibo(String numRecibo);
		
		/**
		 * Crea el pago caso y el detalle del pago caso
		 * @param pagoCasoDTO
		 * @return
		 */
		public NotificacionPagoDTO crearPagoCaso(PagoCasoDTO pagoCasoDTO, String realPath);
		
		/**
		 * Obtiene la información del pago del caso
		 * @param ReliquidarPagoCasoDTO
		 * @return
		 */		
		public List<ReliquidarPagoCasoDTO> listarPagoCaso(long idCaso);
		
		/**
		 * Obtiene el detalle de la información de los pagos del caso reliquidados
		 * @param ReliquidarPagoCasoDTO
		 * @return
		 */		
		public List<InfoReliquidacionPagoCaso> listarReliquidacionPagoCaso(long idCaso);
		
		/**
		 * Método que permite realizar el proceso de generación de orden de compra 
		 * @param datosOrden
		 * @return
		 */
		public String generarOrdenPago(FormularioGenerarPagoPup datosOrden);
		
		/**
		 * obtiene un pagoCaso dado un idCaso
		 */
		public PagoCaso obtenerPagoCasoPorCaso(long idCaso);
		
		public void creaDetallePagoCaso(PagoCaso pagoCaso, PagoCasoDTO pagoCasoDTO);
	 
	// protected region metodos adicionales end
	
}
