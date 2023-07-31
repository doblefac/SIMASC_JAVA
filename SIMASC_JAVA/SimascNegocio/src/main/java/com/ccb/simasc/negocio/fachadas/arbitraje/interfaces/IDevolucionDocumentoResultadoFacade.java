package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.DevolucionDocumentoDTO;
import com.ccb.simasc.transversal.dto.DevolucionDocumentoResultadoDTO;
import com.ccb.simasc.transversal.dto.ModificarActasConstanciasDevueltasDTO;
import com.ccb.simasc.transversal.entidades.DevolucionDocumentoResultado;
import com.ccb.simasc.transversal.excepciones.EstadosCasoException;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link DevolucionDocumentoResultado}
 * @author sMartinez
 *
 */
@Local
public interface IDevolucionDocumentoResultadoFacade extends IAccesoFacade<DevolucionDocumentoResultado, Long, DevolucionDocumentoResultadoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * Consulta las actas o constancias de un caso que han devuelto en control de legalidad para se modificadas
	 * @param idCaso
	 * @return List<ModificarActasConstanciasDevueltasDTO>
	 */
	public List<ModificarActasConstanciasDevueltasDTO> consultarActasConstanciasDevueltasCaso( Long idCaso );
	
	/** CON-F-084
	 * Metodo que modifica la informacion de las actas o constancias devueltas.
	 * @param modificarActasConstanciasDevueltasDTO: DTO que contiene la informacion para modificar las actas o constancias devueltas
	 */
	public void modificarActasConstanciasDevueltas( ModificarActasConstanciasDevueltasDTO modificarActasConstanciasDevueltasDTO, String idUsuarioModificacion ) throws EstadosCasoException;
	
	/**
	 * Metodo que permite hacer el registro de la devolucion de documento.
	 * 
	 * @param devolucionDocumentoDTO:
	 *            DTO con la informacion de devolucion de documento.
	 * @param usuarioModificacion: Usuario en sesion que realiza la modificacion.
	 * @throws EstadosCasoException 
	 */
	public void guardarDevolucionDocumento(DevolucionDocumentoDTO devolucionDocumentoDTO, String usuarioModificacion) throws EstadosCasoException;
	
	// protected region metodos adicionales end
	
}
