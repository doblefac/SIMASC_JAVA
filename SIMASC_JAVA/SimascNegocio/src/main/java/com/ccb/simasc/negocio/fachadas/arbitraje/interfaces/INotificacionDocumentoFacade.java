package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.NotificacionDocumentoDTO;
import com.ccb.simasc.transversal.entidades.NotificacionDocumento;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link NotificacionDocumento}
 * @author sMartinez
 *
 */
@Local
public interface INotificacionDocumentoFacade extends IAccesoFacade<NotificacionDocumento, Long, NotificacionDocumentoDTO> {


	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	public List<HashMap<String, Object>> obtenerEstadosOFijacionListas(String tipo, String palabraClave, Date fechaInicio,
			Date fechaFinal);
	public List<NotificacionDocumentoDTO> obtenerNofitificacionesPorCaso(Long idCaso);
	public void crearOactualizarNotificacionDocumento(NotificacionDocumentoDTO notificacion);
	public List<NotificacionDocumentoDTO> obtenerNofitificacionesPorId(Long idNotificacionDocumento);
	// protected region metodos adicionales end
	
}
