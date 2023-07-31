package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlmacenamientoDocumentosFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICarpetaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IConvenioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoCcbFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IGestorDocumentalFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.transversal.dto.ParametrosCargueDocumentoDTO;
import com.ccb.simasc.transversal.entidades.Carpeta;
import com.ccb.simasc.transversal.entidades.Convenio;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.EventoCcb;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;

/**
 * Implementación de fachada RESTFULL para la gestión de documentos
 * 
 * @author jcepeda
 *
 */
@Stateless
@LocalBean
public class GestorDocumentalFacade implements IGestorDocumentalFacade {
	
	private static final Logger logger = LogManager.getLogger(GestorDocumentalFacade.class.getName());

	@EJB
	private IDocumentoFacade documentoFacade;

	@EJB
	private ICarpetaFacade carpetaFacade;
	
	@EJB
	private IDominioFacade dominioFacade;
	
	@EJB
	private IPersonaFacade personaFacade;
	
	@EJB
	private IEventoCcbFacade eventoCcbFacade;
	
	@EJB
	private IConvenioFacade convenioFacade;

	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;
	
	@EJB
	private IAlmacenamientoDocumentosFacade almacenamientoDocumentosFacade;
	
	@EJB
	private static ParametrosGeneralesFacade parametrosGeneralesFacade;
	
	// contexto de sesion de usuario.
	@Inject
	private ApplicationRequestContext appContext;

	/**
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IGestorDocumentalFacade#subirDocumento(Long,
	 *      Long, InputStream, String, String)
	 */
	@Override
	public String subirDocumento(Long idCaso, Long idCarpeta, InputStream documento, String nombreDocumento,
			String extension, Long idPersona) throws SIMASCNegocioExcepcion {
		return guardarDocumentoCaso(idCaso, idCarpeta, nombreDocumento, extension, documento, true, idPersona);
	}
	

	@Override
	public String subirDocumento(Long idCaso, Long idCarpeta, InputStream documento, String nombreDocumento,
			String extension, boolean conReemplazo, Long idPersona) throws SIMASCNegocioExcepcion {
		return guardarDocumentoCaso(idCaso, idCarpeta, nombreDocumento, extension, documento, conReemplazo, idPersona);
	}

	/**
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IGestorDocumentalFacade#actualizarDocumento(Long,
	 *      Long, String, String, InputStream)
	 */
	@Override
	public String actualizarDocumento(Long idCaso, Long idCarpeta, String nombreDocumento, String extension,
			InputStream nuevoDocumento, Long idPersona) throws SIMASCNegocioExcepcion {
		return actualizarDocumentoCaso(idCaso, idCarpeta, nombreDocumento, extension, nuevoDocumento, idPersona);
	}

	/**
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IGestorDocumentalFacade#recuperarDocumento(java.lang.Long,
	 *      java.lang.Long)
	 */
	@Override
	public File recuperarDocumento(Long idCaso, Long idDocumento) throws SIMASCNegocioExcepcion {
				
		Documento documentoE = documentoFacade.buscar(idDocumento);
		File documento =null;
		if(documentoE.getUrl().indexOf("http")>=0) {
			try {
				String token = almacenamientoDocumentosFacade.getClientCredentials();				
				documento = getFilesbyCase(token, (documentoE.getUrl().substring(documentoE.getUrl().indexOf(idCaso+""),documentoE.getUrl().length())),documentoE.getNombre()+"."+documentoE.getTipoArchivo());
			} catch (UnsupportedEncodingException e) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString()));
			}
		
		}else {
			
		documento = new File(documentoE.getUrl());
		}
		
		if (!documento.exists()) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString()));
		}
		return documento;
	}
	
	private static File getFilesbyCase(String token,String key,String name) {
		BufferedReader reader = null;
		Pattern pat = Pattern.compile(".*\"value\"\\s*:\\s*\"([^\"]+)\".*");
		File f=null;
		String pathtem=System.getenv("temp")+"\\"+name;
		ParametrosGenerales urlServSave = parametrosGeneralesFacade.consultarPorCodigo(UtilConstantes.PARAMETRO_CODIGO_URL_SERV_READ);
		try {
			URL url = new URL(urlServSave.getValorTexto() + key);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestProperty("Authorization", "Bearer " + token);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = null;
			StringWriter out = new StringWriter(
					connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
			while ((line = reader.readLine()) != null) 
				out.append(line);
			
			String response = out.toString();			
			Matcher matcher = pat.matcher(response);
			String bytesFile="";
			if (matcher.matches() && matcher.groupCount() > 0) 
				bytesFile = matcher.group(1);
			
			byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(bytesFile);			
			f = new File(pathtem);    			   
			Path path = Paths.get(f.getAbsolutePath());
			try {
			    Files.write(path, imageBytes);
			} catch (IOException ex) {
				return f;
			}
						
		} catch (Exception e) {
			logger.error(e.getMessage());
			return f;
		}
		return f;
	}
	
	
	@Override
	public Object recuperarDocumentoGestorDocumental(Long idCaso, Long idDocumento) throws SIMASCNegocioExcepcion {
		Documento documentoE = documentoFacade.buscar(idDocumento);
		String prefijoOnBase = (String) manejadorParametrosGenerales.obtenerValorParametroPorCodigoTipo(
				UtilConstantes.CODIGO_PREFIJO_ONBASE, UtilConstantes.TIPO_PARAMETRO_GESTOR_DOCU,
				ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_VALOR_TEXTO);
		Object documento;
		
		if(documentoE.getUrl().startsWith(prefijoOnBase)) {
			documento = documentoE.getUrl();
		} else if (documentoE.getUrl().indexOf("http")>=0){
			try {
				String token = almacenamientoDocumentosFacade.getClientCredentials();				
				documento = getFilesbyCase(token, (documentoE.getUrl().substring(documentoE.getUrl().indexOf(idCaso+""),documentoE.getUrl().length())),documentoE.getNombre()+"."+documentoE.getTipoArchivo());
			} catch (UnsupportedEncodingException e) {
				
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString()));
			}
			if (!((File) documento).exists()) {
				
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR573.toString()));
				
			}
		}	else {
			documento = new File(documentoE.getUrl());
			         
			if (!((File) documento).exists()) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR573.toString()));
			}
		}		
		return documento;
	}

	/**
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IGestorDocumentalFacade#eliminarDocumento(java.lang.String)
	 */
	@Override
	public boolean eliminarDocumento(String pathDocumento) throws SIMASCNegocioExcepcion {
		boolean resultado = false;

		try {
			File documento = new File(pathDocumento);

			if (documento.exists()) {
				resultado = documento.delete();
			}
		} catch (Exception e) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR187.toString()));
		}

		return resultado;
	}

	@Override
	public boolean eliminarCarpeta(String pathCarpeta) throws SIMASCNegocioExcepcion {
		boolean resultado = false;

		try {
			File carpeta = new File(pathCarpeta);

			if (carpeta.exists()) {
				resultado = carpeta.delete();
			}else{
				resultado = true;
			}
		} catch (Exception e) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR187.toString()));
		}

		return resultado;
	}


	 
	@Override
	public Map<String, String> crearCarpetasCaso(List<String> nombresCarpetas, Long idCaso, Long idPersona) {
		Map<String, String> directoriosCarpetasCaso = new HashMap<>();
		ParametrosCargueDocumentoDTO parametrosDocumento = new ParametrosCargueDocumentoDTO();
		parametrosDocumento.setIdCasoOSolicitud(idCaso);
		parametrosDocumento.setIdPersona(idPersona);
		String directorioCaso = obtenerDirectorioCasoOSolicitud(parametrosDocumento, false);

		// Valida que se haya podido crear el directorio del caso
		if (!directorioCaso.isEmpty()) {
			for (String nombreCarpeta : nombresCarpetas) {
//				File carpetaCaso = new File(directorioCaso + File.separator + nombreCarpeta);
				// ya no se crean carpetas
				File carpetaCaso = new File(directorioCaso);

				// Si la carpeta (cuaderno) del caso no existe la crea
				if (!carpetaCaso.exists()) {
					carpetaCaso.mkdir();
				}

				directoriosCarpetasCaso.put(carpetaCaso.getPath(), nombreCarpeta);
			}
		}

		return directoriosCarpetasCaso;
	}
	
	// UTILIDADES

	@Override
	public String crearCarpetaCuaderno(String nombreCarpeta, String path) {
		String directoriosCarpetasCaso = "";
		String directorioCaso = path;

		if (!directorioCaso.isEmpty()) {
//				File carpetaCaso = new File(directorioCaso + File.separator + nombreCarpeta);
				File carpetaCaso = new File(directorioCaso);
				if (!carpetaCaso.exists()) {
					carpetaCaso.mkdir();
				}

				directoriosCarpetasCaso = carpetaCaso.getPath();
		}

		return directoriosCarpetasCaso;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IGestorDocumentalFacade#validarExistenciaDocumentoFisico(java.io.File)
	 */
	@Override
	public boolean validarExistenciaDocumentoFisico(File documento) {
		return documento.exists();
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IGestorDocumentalFacade#recuperarDocumentoTransversal(java.lang.String)
	 */
	@Override
	public File recuperarDocumentoTransversal(String nombreDocumento) throws SIMASCNegocioExcepcion {
		ParametrosGenerales parametroRutaTranversal = manejadorParametrosGenerales
				.buscar(UtilParamGenerales.GESTOR_DOCUMENTAL_RUTA_TRANSVERSALES);
		File documento = null;

		if (parametroRutaTranversal != null) {
			String directorioaDocumento = parametroRutaTranversal.getValorTexto() + File.separator + nombreDocumento;
			documento = new File(directorioaDocumento);
		}

		return documento;
	}
	
	/**
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 *      IGestorDocumentalFacade#subirDocumentoSolicitudServicio(Long, Long,
	 *      InputStream, String, String)
	 */
	@Override
	public String subirDocumentoSolicitudServicio(ParametrosCargueDocumentoDTO parametrosDocumento, InputStream Documento) throws SIMASCNegocioExcepcion {  //idSolicitud, documento, nombreDocumento, extension
		return guardarDocumentoSolicitudServicio(parametrosDocumento, Documento,  //idSolicitud, nomnbreDocumento, extension, documento,true
				true);
	}
	
	/**
	 * Obtiene el directorio en el cual se almacenaran los documentos
	 * relacionados al caso. 
	 * Si el directorio no existe lo crea.
	 * 
	 * @param idCasoOSolicitud
	 * @return
	 */
	public String obtenerDirectorioCasoOSolicitud(ParametrosCargueDocumentoDTO parametros, boolean esSolicitudServicio) { // Long idCasoOSolicitud, boolean esSolicitudServicio, Long idPersona
		String directorioCaso = UtilConstantes.CADENA_VACIA;

		ParametrosGenerales parametroRutaGestorDocumental = manejadorParametrosGenerales
				.buscar(UtilParamGenerales.GESTOR_DOCUMENTAL_RUTA_GESTOR);
		ParametrosGenerales parametroPrefijoDirectorio;
		
		
		parametroPrefijoDirectorio = generarParametroPrefijoDirectorio(parametros,esSolicitudServicio);
		
		if (parametroRutaGestorDocumental != null && parametroPrefijoDirectorio != null) {
			String nombreDirectorioCaso = parametroPrefijoDirectorio.getValorTexto();
			if (parametros.getIdCasoOSolicitud() != null)
				nombreDirectorioCaso = nombreDirectorioCaso + String.valueOf(parametros.getIdCasoOSolicitud());
			String directorioSubidaDocumentoCaso = parametroRutaGestorDocumental.getValorTexto() + File.separator
					+ nombreDirectorioCaso;
			File directorioSubida = new File(directorioSubidaDocumentoCaso);

			if (!directorioSubida.exists()) {
				directorioSubida.mkdir();
			}

			directorioCaso = directorioSubida.getPath();

		}

		return directorioCaso;
	}

	/**
	 * Método encargado de escribir fisicamente el documento que se esta
	 * subiendo al gestor documental
	 * 
	 * @param idCaso
	 * @param nombreDocumento
	 * @param extension
	 * @param documento
	 */
	private String guardarDocumentoCaso(Long idCaso, Long idCarpeta, String nombreDocumento, String extension,
			InputStream documento, boolean esDocumentoNuevo, Long idPersona) throws SIMASCNegocioExcepcion {
		ParametrosCargueDocumentoDTO parametrosDocumento = new ParametrosCargueDocumentoDTO();
		parametrosDocumento.setIdCasoOSolicitud(idCaso);
		parametrosDocumento.setIdPersona(idPersona);
		String directorioSubidaDocumento = obtenerDirectorioCasoOSolicitud(parametrosDocumento, false); //idCaso, false, idPersona
		String carpetaCaso = "" ;//idCaso != null ? obtenerCarpetaCaso(idCarpeta, idCaso) : UtilConstantes.CADENA_VACIA;
		String pathDocumento;
		
		if (validarExtensionDocumento(extension)) {
			String nombrePath = (directorioSubidaDocumento
					+ (carpetaCaso.isEmpty() ? UtilConstantes.CADENA_VACIA : File.separator + carpetaCaso)
					+ File.separator + nombreDocumento + UtilConstantes.CARACTER_PUNTO + extension);
			File nuevoDocumento = new File(nombrePath);

			// Escritura al log para validar la ruta en la que se va a escribir
			// el archivo
			logger.info("Se va intentar escribir en disco el documento: " + nombreDocumento + ", en la ruta: "
					+ nombrePath + ", es documento nuevo: " + esDocumentoNuevo);

			if (nuevoDocumento.exists() && esDocumentoNuevo) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR084.toString()));
			}
			
			try {
				if (documento.available() > 0) {
					pathDocumento = escribirDocumentoDisco(documento, nuevoDocumento, nombreDocumento);
				} else {
					throw new SIMASCNegocioExcepcion(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR224.toString()));
				}
			} catch (IOException e) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR224.toString()));
			}
		} else {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR222.toString()));
		}

		return pathDocumento;
	}
	
	/**
	 * Método encargado de escribir fisicamente el documento que se esta
	 * subiendo al gestor documental
	 * 
	 * @param idSolicitudServicio
	 * @param nombreDocumento
	 * @param extension
	 * @param documento
	 */
	private String guardarDocumentoSolicitudServicio(ParametrosCargueDocumentoDTO parametros,InputStream documento, boolean esDocumentoNuevo) throws SIMASCNegocioExcepcion {  //Long idSolicitudServicio, String nombreDocumento, String extension,InputStream documento
		
		String directorioSubidaDocumento = obtenerDirectorioCasoOSolicitud(parametros, true); //idSolicitudServicio
		String pathDocumento;
		
		if (validarExtensionDocumento(parametros.getExtension())) {
			String nombrePath = (directorioSubidaDocumento + File.separator + parametros.getNombreDocumento()
					+ UtilConstantes.CARACTER_PUNTO + parametros.getExtension());
			File nuevoDocumento = new File(nombrePath);

			// Escritura al log para validar la ruta en la que se va a escribir
			// el archivo
			logger.info("Se va intentar escribir en disco el documento: " + parametros.getNombreDocumento()
					+ ", en la ruta: " + nombrePath + ", es documento nuevo: " + esDocumentoNuevo);

			if (nuevoDocumento.exists() && esDocumentoNuevo) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR084.toString()));
			}

			try {
			
				if (documento.available() > 0) {
					pathDocumento = escribirDocumentoDisco(documento, nuevoDocumento, parametros.getNombreDocumento());  
				} else {
					throw new SIMASCNegocioExcepcion(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR224.toString()));
				}
			} catch (IOException e) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR224.toString()));
			}
		} else {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR222.toString()));
		}

		return pathDocumento;
	}
	
	/**
	 * Método encargado de validar si la extensión de un documento se encuentra
	 * dentro del dominio TIPO_ARCHIVO
	 * 
	 * @param extension
	 * @return
	 */
	private boolean validarExtensionDocumento(String extension) {
		List<Dominio> dominios = dominioFacade.getDominios(UtilDominios.DOMINIO_TIPO_ARCHIVO_INVALIDO);
		List<String> extensionesValidasDocumentos = new ArrayList<String>();

		for (Dominio dominio : dominios) {
			extensionesValidasDocumentos.add(dominio.getNombre());
		}

		return (!extensionesValidasDocumentos.contains(extension.toUpperCase()));
	}
	
	/**
	 * Método encargado de escribir fisicamente el documento que se esta
	 * subiendo al gestor documental
	 * 
	 * Si el documento ya existe renombra el archivo que se esta subiendo
	 * 
	 * @param idCaso
	 * @param idCarpeta
	 * @param nombreDocumento
	 * @param extension
	 * @param documento
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	private String actualizarDocumentoCaso(Long idCaso, Long idCarpeta, String nombreDocumento, String extension,
			InputStream documento, Long idPersona) throws SIMASCNegocioExcepcion {
		ParametrosCargueDocumentoDTO parametrosDocumento = new ParametrosCargueDocumentoDTO();
		parametrosDocumento.setIdCasoOSolicitud(idCaso);
		parametrosDocumento.setIdPersona(idPersona);
		String directorioSubidaDocumento = obtenerDirectorioCasoOSolicitud(parametrosDocumento, false);
		String carpetaCaso = "";// idCaso != null ? obtenerCarpetaCaso(idCarpeta, idCaso) : UtilConstantes.CADENA_VACIA;
		String pathDocumento;

		if (validarExtensionDocumento(extension)) {
			File nuevoDocumento = new File(directorioSubidaDocumento
					+ (carpetaCaso.isEmpty() ? UtilConstantes.CADENA_VACIA : File.separator + carpetaCaso)
					+ File.separator + nombreDocumento + UtilConstantes.CARACTER_PUNTO + extension);

			if (nuevoDocumento.exists()) {
				String fechaActual = new SimpleDateFormat(UtilConstantes.FORMATO_FECHA_TIMESTAMP).format(new Date());
				nombreDocumento += UtilConstantes.CARACTER_GUION_BAJO;
				nombreDocumento += fechaActual;

				nuevoDocumento = new File(directorioSubidaDocumento
						+ (carpetaCaso.isEmpty() ? UtilConstantes.CADENA_VACIA : File.separator + carpetaCaso)
						+ File.separator + nombreDocumento + UtilConstantes.CARACTER_PUNTO + extension);
			}

			pathDocumento = escribirDocumentoDisco(documento, nuevoDocumento, nombreDocumento);
		} else {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR222.toString()));
		}

		return pathDocumento;
	}

	/**
	 * Método encargado de escribir un archivo en disco
	 * 
	 * @param inputStream
	 * @param file
	 * @param fileName
	 * @return
	 */
	@Override
	public String escribirDocumentoDisco(InputStream inputStream, File file, String fileName)
			throws SIMASCNegocioExcepcion {
		OutputStream outputStream = null;
		
		try {
			String directoryPath = file.getCanonicalPath().substring(0, file.getCanonicalPath().lastIndexOf(File.separator));
			File directory = new File(directoryPath);
			
			if (!directory.exists()) 
				directory.mkdirs();
			
			outputStream = new FileOutputStream(file);
			IOUtils.copy(inputStream, outputStream);

			return file.getCanonicalPath();
		} catch (FileNotFoundException e) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR085.toString()), e);
		} catch (IOException e) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR086.toString()) + fileName, e);
		} finally {
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}

	/**
	 * Método encargado de obtener el nombre de la carpeta especifica del caso
	 * al cual se esta subiendo un documento
	 * 
	 * @param idCarpeta
	 * @param idCaso
	 * @return
	 */
	private String obtenerCarpetaCaso(Long idCarpeta, Long idCaso) {
		String carpetaCaso = UtilConstantes.CADENA_VACIA;

		// Busca la carpeta por id
		Carpeta carpeta = carpetaFacade.buscar(idCarpeta);

		// Valida si la carpeta existe y pertenece al caso
		if (carpeta != null && carpeta.getIdCaso() != null && carpeta.getIdCaso().equals(idCaso)) {

			// Valida si la carpeta es cuaderno
			if (carpeta.getEsCuaderno()) {
				carpetaCaso = carpeta.getNombre();

			// Si la carpeta no es cuaderno y esta contenida dentro de
			// otra(s) busca la(s) carpeta(s) contenedora(s)
			} else if (carpeta.getIdCarpetaContenedora() != null) {
				Carpeta carpetaContenedora = carpetaFacade.buscar(carpeta.getIdCarpetaContenedora());
				carpetaCaso = carpetaContenedora.getNombre() + File.separator;
				while (carpetaContenedora.getIdCarpetaContenedora() != null) {
					carpetaCaso = carpetaContenedora.getNombre() + File.separator;
					carpetaContenedora = carpetaFacade.buscar(carpetaContenedora.getIdCarpetaContenedora());
				}
				carpetaCaso = carpetaCaso + carpeta.getNombre();
			}
		}

		return carpetaCaso;
	}
	
	/*
	 * 
	 */
	private ParametrosGenerales generarParametroPrefijoDirectorio(ParametrosCargueDocumentoDTO parametros,  boolean solicitudServicio){
		
		ParametrosGenerales prefijoDirectorio = new ParametrosGenerales();
		//Parámetro prefijo directorio idCaso 
		if(parametros.getIdCasoOSolicitud()!=null){
			prefijoDirectorio = prefijoIdCasoOSolicitudServicio(prefijoDirectorio, solicitudServicio);
		}
		//Parámetro prefijo directorio idPersona
		else if(parametros.getIdPersona()!=null){
			try {
				Persona persona = personaFacade.buscar(parametros.getIdPersona());
				prefijoDirectorio.setValorTexto(
						persona.getTipoDocumento() + '_' + persona.getNumeroDocumento());
			} catch(Exception e) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR243.toString()));
			}

		}
		else if(parametros.getIdEvento()!=null){
			try {
				EventoCcb eventoCcb = eventoCcbFacade.buscar(parametros.getIdEvento());
				prefijoDirectorio.setValorTexto(
						eventoCcb.getTipoEventoCcb() + '_' + eventoCcb.getIdEventoCcb());
			} catch(Exception e) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR243.toString()));
			}
		}
		else{
			try {
				Convenio convenio = convenioFacade.buscar(parametros.getIdConvenio());
				prefijoDirectorio.setValorTexto(
						convenio.getTipoConvenio() + '_' + convenio.getNombre() + '_' + convenio.getIdConvenio());
			} catch(Exception e) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR243.toString()));
			}
		}
		
		
		return prefijoDirectorio;
	}
	
	private ParametrosGenerales prefijoIdCasoOSolicitudServicio(ParametrosGenerales prefijo, boolean solicitudServicio){
		if (solicitudServicio) {
			prefijo = manejadorParametrosGenerales
					.buscar(UtilParamGenerales.GESTOR_DOCUMENTAL_PREFIJO_DIR_SOLICITUD);
		} else {
			prefijo = manejadorParametrosGenerales
					.buscar(UtilParamGenerales.GESTOR_DOCUMENTAL_PREFIJO_DIR_CASO);
		}
		return prefijo;
	}
	

	/**
	 * Método encargado de escribir fisicamente el documento que se esta
	 * subiendo al gestor documental para convenio o evento
	 * 
	 * @param idCarpeta
	 * @param parametrosDocumento
	 */
	@Override
	public String guardarDocumentoOtros(Long idCarpeta, ParametrosCargueDocumentoDTO parametrosDocumento, InputStream documento) throws SIMASCNegocioExcepcion {
		
		String directorioSubidaDocumento = obtenerDirectorioCasoOSolicitud(parametrosDocumento, false); 
		String carpetaCaso =  UtilConstantes.CADENA_VACIA;
		String pathDocumento;
		
		if (validarExtensionDocumento(parametrosDocumento.getExtension())) {
			File nuevoDocumento = new File(directorioSubidaDocumento
					+ (carpetaCaso.isEmpty() ? UtilConstantes.CADENA_VACIA : File.separator + carpetaCaso)
					+ File.separator + parametrosDocumento.getNombreDocumento() + UtilConstantes.CARACTER_PUNTO + parametrosDocumento.getExtension());

			if (nuevoDocumento.exists()) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR084.toString()));
			}
			
			try {
			
				if (documento.available() > 0) {
					pathDocumento = escribirDocumentoDisco(documento, nuevoDocumento, parametrosDocumento.getNombreDocumento());
				} else {
					throw new SIMASCNegocioExcepcion(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR224.toString()));
				}
			} catch (IOException e) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR224.toString()));
			}
		} else {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR222.toString()));
		}

		return pathDocumento;
	}

}
