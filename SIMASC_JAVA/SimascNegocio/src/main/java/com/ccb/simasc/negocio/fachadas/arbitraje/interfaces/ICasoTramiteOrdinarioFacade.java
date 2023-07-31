package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;

import javax.ejb.Local;

import com.ccb.simasc.transversal.dto.NotificacionPagoDTO;
import com.ccb.simasc.transversal.dto.PagoCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.RadicarCasoConvenioDTO;

@Local
public interface ICasoTramiteOrdinarioFacade {
	
	/**
	 * 
	 * 
	 * @param pagoCasoDTO
	 * @param realPath
	 * @return
	 */
	public NotificacionPagoDTO crearPagoCasoTramiteOrdinario(PagoCasoDTO pagoCasoDTO, String realPath);

	/**
	 * Método que realiza la actualizacion de fallo de la generacion de lotes
	 * @param idLote
	 */
	public void actualizarFalloLotes(Long idLote);
	
	/**
	 * Método que realiza el borrado del registro del lote generado luego de realizar la notificación
	 * @param idLote
	 */
	public void eliminarRegistroGeneracion(Long idLote);
	
	/**
	 * Se utiliza para crear el caso de pago de recuperacion empresarial
	 * @param idSolicitud
	 * @return el idCaso
	 */
	public Long crearPagoCasoRecuperacionEmpresarial(Long idSolicitud);
	
	public void llamarRepartoEquidad(RadicarCasoConvenioDTO radicarCasoConvenio, String nombreUsuario) throws Exception;
	
}
