package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.DocumentosEntregadosDTO;
import com.ccb.simasc.transversal.dto.EntregaDocumentoDTO;
import com.ccb.simasc.transversal.entidades.EntregaDocumento;
import com.ccb.simasc.transversal.entidades.EntregaDocumentoPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link EntregaDocumento}
 * @author sMartinez
 *
 */
@Local
public interface IEntregaDocumentoFacade extends IAccesoFacade<EntregaDocumento, EntregaDocumentoPK, EntregaDocumentoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/** CON-F-085
	 * retorna un List<EntregaDocumento> con dependencias
	 * @param idCaso
	 * @return List<EntregaDocumento>
	 */
	public List<DocumentosEntregadosDTO> consultarDocumentosEntregadosPartesCaso( Long idCaso );
	
	/**
	 * Hace la creacion de las entidades entregaDocumento y evento
	 * @param documentosAEntregar
	 * @param idCaso
	 */
	public void entregarDocumentos( List<DocumentosEntregadosDTO> documentosAEntregar, Long idCaso );
	
	// protected region metodos adicionales end
	
}
