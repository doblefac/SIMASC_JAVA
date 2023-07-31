package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.ccb.simasc.transversal.dto.ParametrosCargueDocumentoDTO;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;

/**
 * 
 * 
 * @author jcepeda
 *
 */
@Local
public interface IGestorDocumentalFacade {

	/**
	 * Guarda un documento en el gestor documental asociado a un caso
	 * 
	 * @param idCaso
	 * @param idCarpeta
	 * @param documento
	 * @param nombreDocumento
	 * @param extension
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public String subirDocumento(Long idCaso, Long idCarpeta, InputStream documento, String nombreDocumento,
			String extension, Long idPersona) throws SIMASCNegocioExcepcion;

	
	/**
	 * Guarda un documento en el gestor documental
	 * 
	 * @param idCaso
	 * @param idCarpeta
	 * @param documento
	 * @param nombreDocumento
	 * @param extension
	 * @param conReemplazo
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public String subirDocumento(Long idCaso, Long idCarpeta, InputStream documento, String nombreDocumento,
			String extension, boolean conReemplazo, Long idPersona) throws SIMASCNegocioExcepcion;
	/**
	 * Actualiza un documento en el gestor documental
	 * 
	 * @param idCaso
	 * @param idDocumento
	 * @param nombreDocumento
	 * @param extension
	 * @param nuevoDocumento
	 * @throws SIMASCNegocioExcepcion
	 */
	public String actualizarDocumento(Long idCaso, Long idCarpeta, String nombreDocumento, String extension,
			InputStream nuevoDocumento, Long idPersona) throws SIMASCNegocioExcepcion;

	/**
	 * Recupera un documento del gestor documental
	 * 
	 * @param idCaso
	 * @param idDocumento
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public File recuperarDocumento(Long idCaso, Long idDocumento) throws SIMASCNegocioExcepcion;
	
	/**
	 * Recupera un documento del gestor documental o de on base
	 * @param idCaso
	 * @param idDocumento
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public Object recuperarDocumentoGestorDocumental(Long idCaso, Long idDocumento) throws SIMASCNegocioExcepcion;
	
	/**
	 * Elimina un documento del gestor documental
	 * 
	 * @param pathDocumento
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public boolean eliminarDocumento(String pathDocumento) throws SIMASCNegocioExcepcion;
	
	/**
	 * Crea las carpetas (cuadernos) de un caso fisicamente en el gestor
	 * documental
	 * 
	 * @param nombresCarpetas
	 * @param idCaso
	 * @param idPersona
	 * @return
	 */
	public Map<String, String> crearCarpetasCaso(List<String> nombresCarpetas, Long idCaso, Long idPersona);

	public String crearCarpetaCuaderno(String nombreCarpeta, String path);
	public String obtenerDirectorioCasoOSolicitud(ParametrosCargueDocumentoDTO parametros, boolean esSolicitudServicio);
	boolean eliminarCarpeta(String pathCarpeta) throws SIMASCNegocioExcepcion;
	
	/**
	 * Valida la existencia de un documento fisico 
	 * 
	 * @param documento
	 * @return
	 */
	public boolean validarExistenciaDocumentoFisico(File documento);
	
	/**
	 * Metodo encargado de recuperar los documentos transversales almacenados en
	 * el gestor los cuales no tienen registro en Base de Datos
	 * 
	 * @param nombreDocumento
	 * @return File
	 * @throws SIMASCNegocioExcepcion
	 */
	public File recuperarDocumentoTransversal(String nombreDocumento) throws SIMASCNegocioExcepcion;
	
	/**
	 * Guarda un documento en el gestor documental asociado a una solicitud de
	 * servicio
	 * 
	 * @param idCaso
	 * @param idCarpeta
	 * @param documento
	 * @param nombreDocumento
	 * @param extension
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public String subirDocumentoSolicitudServicio(ParametrosCargueDocumentoDTO parametrosDocumento, InputStream documento) throws SIMASCNegocioExcepcion;

	/**
	 * Método encargado de escribir fisicamente el documento que se esta
	 * subiendo al gestor documental para convenio o evento
	 * 
	 * @param idCarpeta
	 * @param parametrosDocumento
	 */
	public String guardarDocumentoOtros(Long idCarpeta, ParametrosCargueDocumentoDTO parametrosDocumento, InputStream documento) throws SIMASCNegocioExcepcion;
	
	/**
	 * Método que escribe un archivo en disco
	 * @param inputStream
	 * @param file
	 * @param fileName
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public String escribirDocumentoDisco(InputStream inputStream, File file, String fileName) throws SIMASCNegocioExcepcion;
}
