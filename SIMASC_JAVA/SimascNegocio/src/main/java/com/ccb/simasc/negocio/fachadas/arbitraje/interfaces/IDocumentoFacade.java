package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import java.io.InputStream;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.DatosVerificacionParteFirmaDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.formularios.BusquedaSemanticaDTO;
import com.ccb.simasc.transversal.dto.formularios.DocumentosDigitalizadorDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltroCasosAsignadosDTO;
import com.ccb.simasc.transversal.dto.formularios.InfoCasoParaPronunciamientoDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosCarpetaDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PlantillaCarta;
import com.ccb.simasc.transversal.entidades.Pronunciamiento;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.simasc.clientes.ministerio.ArrayOfbase64Binary;




// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Documento}
 * @author sMartinez
 *
 */
@Local
public interface IDocumentoFacade extends IAccesoFacade<Documento, Long, DocumentoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	 
	/**
	 * Consulta los documentos en estado de registro activos del caso
	 * @param casoId
	 * @return List<Documento>
	 * @throws SIMASCNegocioExcepcion
	 */
	 public List<Documento> consultarPorCaso(Long casoId);
	 
	/**
	 * Consulta los documentos en estado de registro activos del caso y servicio
	 * especifico
	 * 
	 * @param casoId
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<Documento> consultarPorCasoTipoServicio(Long casoId, String tipoServicio);
	 
	 public void eliminarDocumento(Long id, String idUsuario);
	 public List<Long> guardarDocumento(List<Documento> documento);
	 
	/**
	 * Consulta un documento por el nombre del documento, por el codigo del caso
	 * y por el tipo de servicio
	 * 
	 * @param documento
	 * @return DocumentoDTO
	 * @throws SIMASCNegocioExcepcion
	 */
	 public DocumentoDTO consultarPorNombreServicioCaso(Documento documento);
	 
	/**
	 * Permite actualizar el documento cambiando el estado de digitalizado y
	 * registrado
	 * 
	 * @param documento
	 * @throws SIMASCNegocioExcepcion
	 */
	public void digitalizarDocumento(Documento documento);
	 
	/**
	 * Permite actualizar el documento cambiando el estado de digitalizado y
	 * registrado
	 * 
	 * @param idCaso
	 * @param nombreDocumento
	 * @param extension
	 * @param documentoStream
	 * @param documento
	 * @param idUsuarioModificacion
	 * @throws Exception
	 */
	public void digitalizarDocumento(Long idCaso, String nombreDocumento, String extension, InputStream documentoStream,
			Documento documento, String idUsuarioModificacion) throws Exception;
	 
	 /**
	  * Consulta si la audiencia ya cuenta con un acta de resultado
	  * 
	  * @param documentoDTO
	  * @return
	  * @throws SIMASCNegocioExcepcion
	  */
	 public DocumentoDTO obtenerActaAudiencia(DocumentoDTO documentoDTO);
	 
	 /**
	  * Obtener la lista de audios que se tiene la audiencia
	  * 
	  * @param dto
	  * @return
	  * @throws SIMASCNegocioExcepcion
	  */
	 public List<DocumentoDTO> obtenerAudiosAudiencia(DocumentoDTO dto);
	 
	 /**
	  * Guardar un audio en la base
	  * @param documentoDTO
	  * @return
	  * @throws SIMASCNegocioExcepcion
	  */
	 public Long guardarAudio(DocumentoDTO documentoDTO);
	 
	 /**
	  * Guardar acta de resultado de audiencia y enviar notificacion por correo
	  * @param lstDocumentos
	  * @param idCaso
	  * @param idAudiencia
	  * @return
	  * @throws SIMASCNegocioExcepcion
	  */
	 public List<Long> guardaActa(List<Documento> lstDocumentos, Long idCaso, Long idAudiencia, Long idPersona);
	
	 /**
	  * 
	  */
	 public Documento consultarPorId(Long id);
	public List<Persona> consultarRemitentes(Long idCaso);
	public List<Documento> consultarDocumentosSecretaria(ParametrosCarpetaDTO carpetaInfo);
	public void actualizarDocumentos(List<Documento> lstDocumento);
	public List<Documento> consultarPorCasoTipo(Long casoId, String tipo);
	public List<Documento> consultarPorCasoTipo(Long casoId, List<String> tipos);
	/**
	 * realiza el registro y la creacion de los envento de la modificacion de la demanda
	 * @param documento
	 * @param usuario
	 * @throws SIMASCNegocioExcepcion
	 */
	public void registroDocumentoDemanda(Documento documento, String usuario);
	
	/**
	 * Obtiene un documento de un caso especifico dado su identificador
	 * 
	 * @param idCaso
	 * @param idDcoumento
	 * @return
	 */
	public DocumentoDTO obtenerDocumentoCaso(Long idCaso, Long idDocumento);
	
	/**
	 *  Obtiene el ultimo documento de una persona por tipo
	 * 
	 * @param idPersona
	 * @param tipo
	 * @return
	 */
	public DocumentoDTO obtenerDocumentoPersonaPorTipo(Long idPersona, String tipo);
	
	
	/*
	 * Registra el documento subido a las partes, creando el evento y enviando los correos 
	 * TRANS-F-014
	 * @param idCaso
	 * @param tipoDocumento
	 * @return
	 * 
	 */
	public void registrarDocumentoPartes(Long idCaso,Long idDocumento);
	
	
	/**
	 * realiza el registro y la creacion de los envento de la modificacion de la demanda
	 * @param documento
	 * @param usuario
	 * @throws SIMASCNegocioExcepcion
	 */
	public void modificacionDemandaParte(Long idCaso, Long idDocumento, Long idPersona);
	
	/**
	 * Consulta los documentos en estado de registro activos de la solicitud de
	 * servicio
	 * 
	 * ARB-F-110
	 * 
	 * @param idSolicitudServicio
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<Documento> consultarPorSolicitudServicio(Long idSolicitudServicio);
	
	/**
	 * Guarda un documento en la base de datos asociado a una solicitud de
	 * servicio
	 * 
	 * @param documento
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public Long guardarDocumentoSolicitudServicio(Documento documento);
	

	/**
	 * Actualiza la informacion en bd de los documentos
	 * @param documento
	 * @throws SIMASCNegocioExcepcion
	 */
	public void actualizarInfoDocumento(DocumentoDTO documento);
	
	/**
	 * Método que se encarga del cargue de los documentos que se obtienen del servicio del ministerio
	 * @param listaDocumentos
	 */
	public void guardarDocumentosMinisterio(ArrayOfbase64Binary listaDocumentos, Long idCaso) throws Exception;
	
	/**
	 * Método que realiza el borrado fisico de los documentos 
	 * @param idCaso
	 * @param usuarioModificacion
	 */
	public void eliminarDocumentosCasoJornada(Long idCaso, String usuarioModificacion);
	
	/**
	 * Crea un evento de tipo Radicacion de documento
	 * @param documento
	 */
	
	public void crearEvento(Documento documento);
	/**
	 * Obtiene los documentos que se encuentran en estado por digitalizar
	 * @param filtros
	 * @return
	 */
	public List<DocumentosDigitalizadorDTO> consultarDocumentosPorDigitalizar(FiltroCasosAsignadosDTO filtros);
	
	/**
	 * Si el boolean es true publica el documento, caso contrario reversa publicación
	 * @param Documento documento
	 * @param Long idCaso
	 * @param boolean publicar 
	 * @return
	 */
	public void publicarReversarDocumento(Documento documento, Long idCaso, boolean publicar, String idPersonaModificacion);
	
	/**
	 * Consulta los documentos bajo los filtros dados 
	 * @param Long idCaso
	 * @param String nombreDocumento
	 * @param String tipoDocumento  
	 * @return List<DocumentoDTO>
	 */
	public List<DocumentoDTO> consultarDocumentos(Long idCaso, String nombreDocumento, String tipoDocumento);
	
	/**
	 * Método encargado de validar los datos de una persona en Certicamara para
	 * la emisión del certificado de firma digital de actas
	 * 
	 * @param datosVerificacionParteFirmaDTO
	 * @param sessionId
	 * @return
	 */
	public void verificarParteFirma(DatosVerificacionParteFirmaDTO datosVerificacionParteFirmaDTO, String sessionId);
	 		
	/**
	 * Método encargado de actualizar el idCarpeta en la tabla de documentos
	 * 
	 * @param idCaso
	 * @param documentoList
	 * @return
	 */
	public void actualizaCarpetaDocumento(Long idCaso);
	// protected region metodos adicionales end
	
	public void generaDocumentosNotificacion(Caso caso,  List<RolPersonaCaso> rolPersonaCasoList, String realPath);
	
	public void migraDocumentosSolicitudCaso(Long idSolicitudServicio, Long idCaso);
	
	public void  generarDocumentoPronunciamiento(InfoCasoParaPronunciamientoDTO infoCasoParaPronunciamientoDTO,Pronunciamiento pronunciamiento, Long idCaso,PlantillaCarta plantillaCarta, Persona persona) throws SIMASCNegocioExcepcion;
	
	public List<DocumentoDTO>consultarDocumentosPorIdCarpeta(Long idCarpetaContenedora);
	
	public List<BusquedaSemanticaDTO> consultarBusqueda(Long idCaso, String keyword, String tipoDocumento, String fechaInicial, String fechaFinal);
	
	public void generarIndiceElectronico(Long idCaso, Boolean firma);
	
	public int consultarCantidadDocumentosIguales(Long idDocumento);
}
