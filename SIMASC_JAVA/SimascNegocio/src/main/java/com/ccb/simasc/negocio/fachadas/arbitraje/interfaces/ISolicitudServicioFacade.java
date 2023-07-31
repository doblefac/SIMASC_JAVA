package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.transversal.dto.SolicitudServicioDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioGenerarLiquidacionDTO;
import com.ccb.simasc.transversal.dto.formularios.ProcesoReliquidacionDTO;
import com.ccb.simasc.transversal.dto.formularios.RadicacionSolicitudDTO;
import com.ccb.simasc.transversal.dto.formularios.SolicitudConciliacionDTO;
import com.ccb.simasc.transversal.dto.formularios.SolicitudConciliacionEnmascaradoDTO;
import com.ccb.simasc.transversal.entidades.SolicitudServicio;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link SolicitudServicio}
 * @author sMartinez
 *
 */
@Local
public interface ISolicitudServicioFacade extends IAccesoFacade<SolicitudServicio, Long, SolicitudServicioDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * Método encargado de crear una Solicitud de Servicio.
	 * 
	 * ARB-F-108
	 * 
	 * @param solicitudConciliacionDTO
	 * @return Long idSolicitudServicio
	 * @throws SIMASCNegocioExcepcion
	 */
	public Long crearSolicitudServicio(RadicacionSolicitudDTO radicacionSolicitudDTO) throws SIMASCNegocioExcepcion;
	
	/**
	 * Método encargado de consultar un servicio de solicitud a traves de un id
	 * de solicitud.
	 * 
	 * ARB-F-110
	 * 
	 * @param idSolicitudServicio
	 * @return RadicacionSolicitudDTO
	 * @throws SIMASCNegocioExcepcion
	 */
	public RadicacionSolicitudDTO consultarSolicitudServicio(Long idSolicitudServicio)
			throws SIMASCNegocioExcepcion;
	
	/**
	 * Método encargado de validar que las partes mínimas de una solicitud de
	 * servicio hayan sido registradas en el sistema
	 * 
	 * @param idSolicitudServicio
	 * @return
	 */
	public boolean validarPartesSolicitudServicio(Long idSolicitudServicio);
	
	/**
	 * Método encargado de obtener las solicitudes de servicio vigentes (menos
	 * de 5 días hábiles) por solicitante
	 * 
	 * @param idPersona
	 * @return
	 */
	public List<SolicitudServicioDTO> consultarSolicitudesVigentesPorSolicitante(Long idPersona);
	
	/**
	 * Método encargado de la liquidación de una solicitud de servicio
	 * o de la reliquidación de un caso de tramite ordinario en el
	 * sistema SIREP
	 * 
	 * @param reliquidacion
	 * @return
	 */
	public FormularioGenerarLiquidacionDTO liquidar(ProcesoReliquidacionDTO reliquidacion);

	/**
	 * Metodo encargado de crear una Solicitud de Servicio de Conciliacion.
	 * 
	 * CON-F-124
	 * 
	 * @param solicitudConciliacionDTO
	 * @return Long idSolicitudServicio
	 * @throws SIMASCNegocioExcepcion
	 */
	public Long crearSolicitudConciliacion(SolicitudConciliacionDTO solicitudConciliacionDTO) throws SIMASCNegocioExcepcion;

	/**
	 * Metodo encargado de modificar una Solicitud de Servicio de Conciliacion.
	 * 
	 * CON-F-124
	 * 
	 * @param solicitudConciliacionDTO
	 * @return SolicitudConciliacionDTO
	 * @throws SIMASCNegocioExcepcion
	 */
	public void modificarSolicitudConciliacion(SolicitudConciliacionDTO solicitudConciliacionDTO) throws SIMASCNegocioExcepcion;
	
	/**
	 * Metodo encargado de consultar un servicio de solicitud de conciliacion a
	 * traves de un id de solicitud.
	 * 
	 * CON-F-124
	 * 
	 * @param idSolicitud
	 * @return SolicitudConciliacionDTO
	 * @throws SIMASCNegocioExcepcion
	 */
	public SolicitudConciliacionDTO consultarSolicitudConciliacionServicio(Long idSolicitud) throws SIMASCNegocioExcepcion;
	
	/**
	 * TRANS-041 Transversal-Generacion-de-orden
	 * actualiza la solicitud de servicio
	 * @param idSolicitud,idOrdenPago
	 * @return
	 */
	public void actualizarSolicitudServicio(Long idSolicitud,Long idOrdenPago, ContextoDeSesion cs);
	
	public SolicitudServicio consultarSolicitudPorOrden(Long numeroOrden);
	// protected region metodos adicionales end
	
	public SolicitudConciliacionEnmascaradoDTO consultarSolicitudConciliacionEnmascarando(Long idSolicitud) throws SIMASCNegocioExcepcion;
	

	
}
