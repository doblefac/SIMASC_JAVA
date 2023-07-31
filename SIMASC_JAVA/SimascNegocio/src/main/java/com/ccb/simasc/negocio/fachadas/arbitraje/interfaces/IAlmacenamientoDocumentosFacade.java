package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.transversal.dto.ParametrosCargueDocumentoDTO;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;

/**
 * 
 * 
 * @author jcepeda
 *
 */
@Local
public interface IAlmacenamientoDocumentosFacade {
	
	/**
	 * Servicio encargado de guardar un documento fisica y lógicamente (gestor
	 * documental y base de datos) asociado a un caso
	 * 
	 * @param idCaso
	 * @param nombreDocumento
	 * @param extension
	 * @param documentoStream
	 * @param documento
	 * @param idUsuarioModificacion
	 * @param idPersona
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 * @throws Exception
	 */
	public List<Long> guardarDocumento(Long idCaso, String nombreDocumento, String extension,
			InputStream documentoStream, Documento documento, String idUsuarioModificacion, Long idPersona, ContextoDeSesion sesion) throws Exception;

	/**
	 * Servicio encargado de la eliminación de un documento fisica y lógicamente
	 * (gestor documental y base de datos)
	 * 
	 * @param idDocumento
	 * @param idUsuario
	 * @throws SIMASCNegocioExcepcion
	 */
	public void eliminarDocumento(Long idDocumento, String idUsuario);

	/**
	 * Servicio encargado de validar la existencia de un documento en el gestor
	 * documental
	 * 
	 * @param idCaso
	 * @param tipoDocumento
	 * @return
	 */
	public boolean validarDocumentoExiste(Long idCaso, String tipoDocumento);
	
	/**
	 * Servicio encargado de la actualización de un documento asociado a un caso
	 * 
	 * @param idCaso
	 * @param nombreDocumento
	 * @param extension
	 * @param documentoStream
	 * @param documento
	 * @param idUsuarioModificacion
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 * @throws Exception
	 */
	public List<Long> actualizarDocumento(Long idCaso, String nombreDocumento, String extension,
			InputStream documentoStream, Documento documento, String idUsuarioModificacion, Long idPersona) throws Exception;
	
	/**
	 * Servicio encargado de guardar un documento fisica y lógicamente (gestor
	 * documental y base de datos) asociado a una solicitud de servicio
	 * 
	 * @param idSolicitudServicio
	 * @param nombreDocumento
	 * @param extension
	 * @param documentoStream
	 * @param documento
	 * @param idUsuarioModificacion
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 * @throws Exception
	 */
	public List<Long> guardarDocumentoSolicitudServicio(ParametrosCargueDocumentoDTO parametrosDocumento, Documento documento, String idUsuarioModificacion, InputStream file)
			throws Exception;
	
	
	
	public String getClientCredentials() throws UnsupportedEncodingException;
	
	
	/**
	 * Servicio encargado de guardar un documento fisica y lógicamente (gestor
	 * documental y base de datos) asociado a un evento o convenio
	 * 
	 * @param idSolicitudServicio
	 * @param nombreDocumento
	 * @param extension
	 * @param documentoStream
	 * @param documento
	 * @param idUsuarioModificacion
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 * @throws Exception
	 */
	public List<Long> guardarDocumentoOtros(ParametrosCargueDocumentoDTO parametrosCargueDocumento,Documento documentoEntity, String idUsuarioModificacion, InputStream file) throws Exception;
	
	public void eliminarListadoDocumentos(List<Long> documentos, String idUsuario) throws SIMASCNegocioExcepcion;
}
