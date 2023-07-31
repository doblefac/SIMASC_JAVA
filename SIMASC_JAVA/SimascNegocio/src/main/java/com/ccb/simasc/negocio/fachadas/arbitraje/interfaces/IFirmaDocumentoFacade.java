package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.CasosPendientesFirmaDTO;
import com.ccb.simasc.transversal.dto.DatosFirmaActaConstantciaDTO;
import com.ccb.simasc.transversal.dto.FirmaDocumentoDTO;
import com.ccb.simasc.transversal.dto.PartesEstadoFirmaDTO;
import com.ccb.simasc.transversal.dto.formularios.DatosTokenDTO;
import com.ccb.simasc.transversal.entidades.FirmaDocumento;
import com.ccb.simasc.transversal.entidades.FirmaDocumentoPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link FirmaDocumento}
 * @author sMartinez
 *
 */
@Local
public interface IFirmaDocumentoFacade extends IAccesoFacade<FirmaDocumento, FirmaDocumentoPK, FirmaDocumentoDTO> {

	

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 *Metodo encargado de solicitar el token de clave segura 
	 * @param datosToken
	 */
	public String solicitarTokenClaveSegura(DatosTokenDTO datosToken);


	/**
	 * Consuta 
	 * @param idCaso
	 * @return
	 */
	public List<PartesEstadoFirmaDTO> consultarFirmaPartes(Long idCaso, Long idDocumento);


	/**
	 *  Consulta la lista de casos pendientes de firma por el conciliador
	 * @param idPersona
	 * @return
	 */
	public List<CasosPendientesFirmaDTO> casosPendientesFirmaConciliador(Long idPersona);

	/**
	 * Método para habilitar las partes que van a firmar el acta
	 * @param partesHabilitadas
	 * @param idDocumento
	 */
	public void habilitarPartes(List<PartesEstadoFirmaDTO> partesHabilitadas, Long idDocumento);
	
	/**
	 * Método encargado de firmar el acta o constancia resultado de una
	 * audiencia virtual
	 * 
	 * @param datosFirmaActaConstantciaDTO
	 * @param direccionIPOrigen
	 * @param direccionMACOrigen
	 */
	public void firmarActaConstancia(DatosFirmaActaConstantciaDTO datosFirmaActaConstantciaDTO);
	
	/**
     * Consulta que verifica que la persona tenga la solicitud de firma en el caso indicado
     * en caso de que exista mas de un documento se toma el ultimo que se haya emitido
     * @param idPersona
     * @param idCaso
     * @return
     */
	public List<FirmaDocumento> obtenerParteFirmante(Long idPersona, Long idCaso);
	// protected region metodos adicionales end
	
}
