package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.SolicitudPrestadorDTO;
import com.ccb.simasc.transversal.dto.VerificarSolicitudCambioListaDTO;
import com.ccb.simasc.transversal.dto.reportes.FiltroReportesDTO;
import com.ccb.simasc.transversal.entidades.SolicitudPrestador;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link SolicitudPrestador}
 * @author sMartinez
 *
 */
@Local
public interface ISolicitudPrestadorFacade extends IAccesoFacade<SolicitudPrestador, Long, SolicitudPrestadorDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/** CON-F-118
	 * Metodo que crea la solicitud de cambio de lista de acuerdo a la logica de negocio del CON-F-118
	 * @param solicitudCambioListaDTO
	 */
	public void solicitarCambioLista( SolicitudPrestador solicitudCambioLista );
	
	/**
	 * Metodo que transforma una List<SolicitudCambioLista> a List<SolicitudCambioLista> con dependencias 
	 * @param nombres
	 * @param estadoSolicitud
	 * @return List<SolicitudCambioLista>
	 */
	public List<SolicitudPrestador> consultarSolicitudesCambioLista( String estadoSolicitud, FiltroReportesDTO filtros );
	
	/** CON-C-004
	 * Metodo que prepara a VerificarSolicitudCambioListaDTO con la informacion necesaria 
	 * para hacer la verificacion de la solicitud de cambio de lista.
	 * @param idSolicitud
	 * @return VerificarSolicitudCambioListaDTO
	 */
	public VerificarSolicitudCambioListaDTO consultarSolicitudVerificacion( Long idSolicitud );
	
	/** CON-C-004
	 * Metodo que actualiza la solicitud de cambio de lista a un prestador y hace los cambios de lista 
	 * en RolPersona de acuerdo con las reglas de negocio del caso de uso CON-C-004
	 * @param solicitudCambioListaDTO
	 */
	public void verificarSolicitudCambioLista( SolicitudPrestador solicitudCambioLista );
	
	/** ADM-C-006
	 * Metodo que retorna las solicitudes realizadas por prestadores para plan dialogos o plan justicia segun el tipo de solicitud
	 * @param idRol
	 */
	public List<SolicitudPrestadorDTO> consultarSolicitudesRealizadas(String idRol, String tipoSolicitud);
	
	/** ADM-F-051
	 * Metodo que retorna las solicitudes realizadas por prestadores por el id rol persona, tipo de solicitud
	 * @param idRol
	 */
	public List<SolicitudPrestadorDTO> consultarSolicitudesTipoRolPersona(List<Long> idRolPersona, String tipoSolicitud);
	
	/** ADM-F-051
	 * Metodo crea una nueva solicitud según su tipo
	 * @param solicitud
	 */
	public void crearSolicitudPrestador(SolicitudPrestadorDTO idRolPersona, String idPersonaModificacion, String tipoServicio, Long idPersona, String nombreUsuario, String nombreRol);
	
	// protected region metodos adicionales end
	
}
