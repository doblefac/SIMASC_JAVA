package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;

import java.io.*;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronico;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorServicio;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlmacenamientoDocumentosFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoRadicadoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IGestorDocumentalFacade;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.dto.ParametrosCargueDocumentoDTO;import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

/**
 * 
 * 
 * @author jcepeda
 *
 */
@Stateless
@LocalBean
public class AlmacenamientoDocumentosFacade implements IAlmacenamientoDocumentosFacade {

	private static final Logger logger = LogManager.getLogger(AlmacenamientoDocumentosFacade.class.getName());
	/**
	 * 
	 */
	@EJB
	private IDocumentoFacade documentoFacade;

	/**
	 * 
	 */
	@EJB
	private IGestorDocumentalFacade gestorDocumentalFacade;

	/**
	 * 
	 */
	@EJB
	ManejadorDocumento manejadorDocumento;

	/**
	 * 
	 */
	@EJB
	private RolPersonaCasoFacade rolPersonaCasoFacade;

	@EJB
	private CorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;
	
	/*
	 * 
	 */
	@EJB
	private ManejadorCaso manejadorCaso;

	/**
	 * 
	 */
	@EJB
	private ManejadorRol manejadorRol;

	@EJB
	private ManejadorPersona manejadorPersona;
	
	@EJB
	private ManejadorCorreoElectronico manejadorCorreoElectronico;
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	
	@EJB
	private ParametrosGeneralesFacade parametrosGeneralesFacade;
	
	@EJB
	private ManejadorServicio manejadorServicio;
	
	@EJB
	private IDocumentoRadicadoFacade documentoRadicadoFacade;
	
	
	private static final Pattern pat = Pattern.compile(".*\"access_token\"\\s*:\\s*\"([^\"]+)\".*");
	
	
	/**
	 * @throws Exception 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlmacenamientoDocumentosFacade#guardarDocumento(java.lang.Long,
	 *      java.lang.String, java.lang.String, java.io.InputStream,
	 *      com.ccb.simasc.transversal.entidades.Documento, java.lang.String)
	 */
	@Override
	public List<Long> guardarDocumento(Long idCaso, String nombreDocumento, String extension,
			InputStream documentoStream, Documento documento, String idUsuarioModificacion, Long idPersona, ContextoDeSesion sesion) throws Exception {
		String pathDocumento = UtilConstantes.CADENA_VACIA;
		List<Long> idsDocumentos = new ArrayList<Long>();
		nombreDocumento = nombreDocumento.replace(",", "");
		documento.setNombre(nombreDocumento);
		try {
			// 1. Intenta guardar fisicamente el documento en el gestor
			// documental
			if (documentoStream.available() > 2) {
				if (idCaso != null || idPersona != null)
					documento.setEstadoDigitalizacion(UtilDominios.ESTADO_DIGITALIZACION_DIGITALIZADO);
					pathDocumento = gestorDocumentalFacade.subirDocumento(idCaso, documento.getIdCarpeta(), documentoStream,
							nombreDocumento, extension, idPersona);
			} else {
				documento.setEstadoDigitalizacion(UtilDominios.ESTADO_DIGITALIZACION_POR_DIGITALIZAR);
			}
			// 2. Intenta guardar la metadata del documento en base de datos
			List<Documento> documentos = new ArrayList<Documento>();
			if (!pathDocumento.isEmpty()) {
				documento.setUrl(pathDocumento);
			}
			documentos.add(documento);
			idsDocumentos = documentoFacade.guardarDocumento(documentos);
			
			if (idCaso != null){
				Caso caso = manejadorCaso.buscar(idCaso);
				// 3. Si el estado de digitalización del documento es 'Documento por
				// digitalizar' intenta realizar el reparto para el digitalizador
				if (UtilDominios.ESTADO_DIGITALIZACION_POR_DIGITALIZAR
						.equalsIgnoreCase(documento.getEstadoDigitalizacion())) {
					
					
					Rol rol = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_DIGITALIZADOR);
					rolPersonaCasoFacade.reparto(caso, rol);
				}
				
				// 4. Verificar si quien radica los documentos no es el secretario del caso
				// y notificar al secretario, si el caso no está cerrado
				if(!caso.getEstadoCaso().equals(UtilDominios.ESTADO_CASO_CERRADO))
				{
					if(sesion != null) {
						Rol rolUsuario = manejadorRol.buscar(Long.valueOf(sesion.getRolPrincipal()));
						if(rolUsuario != null && !UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL.equals(rolUsuario.getNombre())) {
							enviarCorreoRadicacion(idCaso, Long.valueOf(sesion.getIdUsuario()), nombreDocumento);
						}				
					}
				}
			}
			
			
		} catch (SIMASCNegocioExcepcion e) {
			// 2.1. Si hay algún error al tratar de guardar la metadata del
			// documento y el documento fisico fue almacenado en el gestor
			// documental, este último será eliminado para deshacer lacción
			// completa
			if (e.getMessage()
					.contains(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR088.toString()))
					&& !pathDocumento.isEmpty()) {
				gestorDocumentalFacade.eliminarDocumento(pathDocumento);
			}

			throw e;
		} catch (Exception e) {
			// 2.2. Si hay algún error al tratar de guardar la metadata del
			// documento y el documento fisico fue almacenado en el gestor
			// documental, este último será eliminado para deshacer lacción
			// completa
			if (e instanceof EJBTransactionRolledbackException && !pathDocumento.isEmpty()) {
				gestorDocumentalFacade.eliminarDocumento(pathDocumento);
			}

			throw e;
		}

		return idsDocumentos;
	}
	
	private void enviarCorreoRadicacion(Long idCaso, Long idPersona, String nombreDocumento) {
		RolPersonaCaso secretario = manejadorRolPersonaCaso.consultarPersonaPorRolCaso(idCaso, UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
		if(secretario == null) return;
		// El valor del parametro se debe actualizar de acuerdo al ambiente en el que se este trabajando
		ParametrosGenerales url = parametrosGeneralesFacade.consultarPorCodigo(UtilConstantes.PARAMETRO_CODIGO_URL_ACCESO);
		Caso caso = manejadorCaso.buscar(idCaso);
		Persona persona = manejadorPersona.buscar(idPersona);
		ParametrosEnvioCorreoDTO correoDTO = new ParametrosEnvioCorreoDTO();
		correoDTO.setAdjuntos(new ArrayList<DocumentoDTO>());
		correoDTO.setAsunto(UtilConstantes.ASUNTO_CORREO_RADICACION_DOCUMENTOS);
		String cuerpoCorreo = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO500.toString(), nombreDocumento, caso.getNombre(), persona.getNombreCompleto(), url.getValorTexto());//"http://localhost:3000"
		correoDTO.setIdCaso(idCaso);
		ArrayList<String> cuerpos = new ArrayList<String>();
		cuerpos.add(cuerpoCorreo);
		correoDTO.setCuerpoCorreo(cuerpos);
		CorreoElectronicoDTO correoSecretario = new CorreoElectronicoDTO();
		List<CorreoElectronico> correos = manejadorCorreoElectronico.consultaCorreosPersona(secretario.getRolPersonaCasoPK().getIdPersona());
		correoSecretario = correoSecretario.transformarConDependencias(correos.get(0));
		correoDTO.setRolPersonaCaso(Arrays.asList(correoSecretario));
		correoDTO.setTipo(UtilDominios.TIPO_PLANTILLAS_CORREO_GENERAL);
		CorreoElectronicoDTO correo = new CorreoElectronicoDTO();
		Persona remitente = manejadorPersona.consultarPersonasPorTipoPersona(UtilDominios.TIPO_PERSONA_SISTEMA, UtilDominios.ESTADO_PERSONA_ACTIVO);
		List<CorreoElectronico> correosR = manejadorCorreoElectronico.consultaCorreosPersona(remitente.getIdPersona());
		correo = correo.transformarConDependencias(correosR.get(0));
		correoDTO.setRemitente(correo);
		correoRolPersonaCasoFacade.enviarCorreo(correoDTO);
	}

	/**
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlmacenamientoDocumentosFacade#guardarDocumento(java.lang.Long,
	 *      java.lang.String, java.lang.String, java.io.InputStream,
	 *      com.ccb.simasc.transversal.entidades.Documento, java.lang.String)
	 */
	@Override
	public List<Long> actualizarDocumento(Long idCaso, String nombreDocumento, String extension,
			InputStream documentoStream, Documento documento, String idUsuarioModificacion, Long idPersona)
			throws Exception {
		String pathDocumento = UtilConstantes.CADENA_VACIA;
		List<Long> idsDocumentos = new ArrayList<Long>();
		nombreDocumento = nombreDocumento.replace(",", "");
		documento.setNombre(nombreDocumento);
		try {
			// 1. Intenta guardar fisicamente el documento en el gestor
			// documental
			pathDocumento = gestorDocumentalFacade.actualizarDocumento(idCaso, documento.getIdCarpeta(),
					nombreDocumento, extension, documentoStream, idPersona);

			// 2. Intenta guardar la metadata del documento en base de datos
			if (!pathDocumento.isEmpty()) {
				List<Documento> documentos = new ArrayList<Documento>();
				String fileSeparator = File.separator;
				if (fileSeparator.equals(UtilConstantes.CARACTER_DE_ESCAPE)) {
					fileSeparator = UtilConstantes.CARACTER_DE_ESCAPE + UtilConstantes.CARACTER_DE_ESCAPE;
				}

				String nuevoNombreDocumento = UtilConstantes.CADENA_VACIA;
				String[] rutaDocumento = pathDocumento.split(fileSeparator);

				if (rutaDocumento.length > 1) {
					nuevoNombreDocumento = rutaDocumento[rutaDocumento.length - 1];
				}

				documento.setNombre(nuevoNombreDocumento.isEmpty() ? nombreDocumento : nuevoNombreDocumento);
				documento.setUrl(pathDocumento);
				documento.setIdUsuarioModificacion(idUsuarioModificacion);

				if (documento.getEstadoDigitalizacion() == null) {
					documento.setEstadoDigitalizacion(UtilDominios.ESTADO_DIGITALIZACION_POR_DIGITALIZAR);
				}

				documentos.add(documento);
				idsDocumentos = documentoFacade.guardarDocumento(documentos);
			}
		} catch (SIMASCNegocioExcepcion e) {
			// 2.1. Si hay algún error al tratar de guardar la metadata del
			// documento y el documento fisico fue almacenado en el gestor
			// documental, este último será eliminado para deshacer lacción
			// completa
			if (e.getMessage()
					.contains(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR088.toString()))
					&& !pathDocumento.isEmpty()) {
				gestorDocumentalFacade.eliminarDocumento(pathDocumento);
			}

			throw e;
		} catch (Exception e) {
			// 2.2. Si hay algún error al tratar de guardar la metadata del
			// documento y el documento fisico fue almacenado en el gestor
			// documental, este último será eliminado para deshacer lacción
			// completa
			if (e instanceof EJBTransactionRolledbackException && !pathDocumento.isEmpty()) {
				gestorDocumentalFacade.eliminarDocumento(pathDocumento);
			}

			throw e;
		}

		return idsDocumentos;
	}

	/**
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlmacenamientoDocumentosFacade#eliminarDocumento(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public void eliminarDocumento(Long idDocumento, String idUsuario) throws SIMASCNegocioExcepcion {
		// 1. Consulta si el documento existe en la base de datos
		Documento documento = documentoFacade.consultarPorId(idDocumento);
		int cantidadDocumentosRepetidos = documentoFacade.consultarCantidadDocumentosIguales(idDocumento);
		if (documento != null && documento.getUrl() != null && !documento.getUrl().isEmpty() && cantidadDocumentosRepetidos < 2 ) {

			// 2. Si el documento existe y tiene una URL intenta eliminar el
			// documento fisico del gestor documental
			String pathDocumento = documento.getUrl();

			// 3. Válida la existencia fisica del documento
			if (gestorDocumentalFacade.validarExistenciaDocumentoFisico(new File(pathDocumento))) {
				boolean exitoEliminandoArchivo = gestorDocumentalFacade.eliminarDocumento(pathDocumento);

				// 3.1. Si el documento fisico pudo ser eliminado del gestor
				// documental procede a eliminar el la metadadta del mismo
				if (exitoEliminandoArchivo) {
					documentoFacade.eliminarDocumento(documento.getIdDocumento(), idUsuario);
				} else {
					throw new SIMASCNegocioExcepcion(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR187.toString()));
				}
			} else {
				// 3.2. Si el documento no existe fisicamente intenta eliminar
				// su metadata
				documentoFacade.eliminarDocumento(documento.getIdDocumento(), idUsuario);
			}
		}
		else {
			// 3.2. Si el documento no existe fisicamente intenta eliminar
			// su metadata
			documentoFacade.eliminarDocumento(documento.getIdDocumento(), idUsuario);
		}
	}

	/**
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlmacenamientoDocumentosFacade#validarDocumentoExiste(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public boolean validarDocumentoExiste(Long idCaso, String tipoDocumento) {
		return manejadorDocumento.consultarPorCasoYtipo(idCaso, tipoDocumento);
	}

	/**
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlmacenamientoDocumentosFacade#guardarDocumentoSolicitudServicio(Long,
	 *      String, String, InputStream, Documento, String)
	 */
	@Override
	public List<Long> guardarDocumentoSolicitudServicio(ParametrosCargueDocumentoDTO parametrosDocumento, Documento documento, String idUsuarioModificacion, InputStream file) //idSolicitudServicio, documentoStream 
			throws Exception {
		
		
		String pathDocumento = UtilConstantes.CADENA_VACIA;
		List<Long> idsDocumentos = new ArrayList<Long>();
		documento.setNombre(documento.getNombre().replace(",", ""));
		parametrosDocumento.setNombreDocumento(documento.getNombre());
		try {						
			// 1. Intenta guardar fisicamente el documento en el gestor
			// documental
			pathDocumento = gestorDocumentalFacade.subirDocumentoSolicitudServicio(parametrosDocumento, file); //Long idSolicitudServicio, InputStream documento,String nombreDocumento, String extension
					
			// 2. Intenta guardar la metadata del documento en base de datos
			if (!pathDocumento.isEmpty()) {
				documento.setUrl(pathDocumento);
			}
			Long idDocumento = documentoFacade.guardarDocumentoSolicitudServicio(documento);			
			if (idDocumento != null)
				idsDocumentos.add(idDocumento);

		} catch (SIMASCNegocioExcepcion e) {
			// 2.1. Si hay algún error al tratar de guardar la metadata del
			// documento y el documento fisico fue almacenado en el gestor
			// documental, este último será eliminado para deshacer lacción
			// completa
			if (e.getMessage()
					.contains(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR088.toString()))
					&& !pathDocumento.isEmpty()) {
				gestorDocumentalFacade.eliminarDocumento(pathDocumento);
			}

			throw e;
		} catch (Exception e) {
			// 2.2. Si hay algún error al tratar de guardar la metadata del
			// documento y el documento fisico fue almacenado en el gestor
			// documental, este último será eliminado para deshacer lacción
			// completa
			if (e instanceof EJBTransactionRolledbackException && !pathDocumento.isEmpty()) {
				gestorDocumentalFacade.eliminarDocumento(pathDocumento);
			}

			throw e;
		}

		return idsDocumentos;
	}
	
	@Override
	public String getClientCredentials() throws UnsupportedEncodingException {

		BufferedReader reader = null;
		HttpsURLConnection connection = null;
		String returnValue = "";
		ParametrosGenerales urlAutTokenJWT = parametrosGeneralesFacade.consultarPorCodigo(UtilConstantes.PARAMETRO_CODIGO_URL_AUT_TOKEN_JWT);
		ParametrosGenerales urlTokenJWT = parametrosGeneralesFacade.consultarPorCodigo(UtilConstantes.PARAMETRO_CODIGO_URL_TOKEN_JWT);
		ParametrosGenerales autTokenJWT = parametrosGeneralesFacade.consultarPorCodigo(UtilConstantes.PARAMETRO_CODIGO_AUT_TOKEN_JWT);

		byte[] postDataBytes = urlAutTokenJWT.getValorTexto().toString().getBytes("UTF-8");
		try {
			
			URL url = new URL(urlTokenJWT.getValorTexto());
			
			//inicio
			SSLContext sc = SSLContext.getInstance("SSL");
		      sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
		          new java.security.SecureRandom());
			//fin
			
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setSSLSocketFactory(sc.getSocketFactory());
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + autTokenJWT.getValorTexto());
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.getOutputStream().write(postDataBytes);
			

			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = null;
			StringWriter out = new StringWriter(
					connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
			String response = out.toString();
			Matcher matcher = pat.matcher(response);
			if (matcher.matches() && matcher.groupCount() > 0) {
				returnValue = matcher.group(1);
			}
		} catch (Exception e) {
			logger.error("Error : " + e.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
			connection.disconnect();
		}
		return returnValue;
	}
	
	@Override
	public List<Long> guardarDocumentoOtros(ParametrosCargueDocumentoDTO parametrosDocumento,Documento documento, String idUsuarioModificacion, InputStream file) throws Exception{
		
		String pathDocumento = UtilConstantes.CADENA_VACIA;
		List<Long> idsDocumentos = new ArrayList<>();
		documento.setNombre(documento.getNombre().replace(",", ""));
		parametrosDocumento.setNombreDocumento(documento.getNombre());
		try {
			// 1. Intenta guardar fisicamente el documento en el gestor
			// documental
			
			
			
			if (file.available() > 2) {
				pathDocumento = gestorDocumentalFacade.guardarDocumentoOtros(documento.getIdCarpeta(), parametrosDocumento, file);
				documento.setEstadoDigitalizacion(UtilDominios.ESTADO_DIGITALIZACION_DIGITALIZADO);
			} 
			else {
				documento.setEstadoDigitalizacion(UtilDominios.ESTADO_DIGITALIZACION_POR_DIGITALIZAR);
			}
			// 2. Intenta guardar la metadata del documento en base de datos
			List<Documento> documentos = new ArrayList<Documento>();
			if (!pathDocumento.isEmpty()) {
				documento.setUrl(pathDocumento);
			}
			documentos.add(documento);
			idsDocumentos = documentoFacade.guardarDocumento(documentos);

			
		} catch (SIMASCNegocioExcepcion e) {
			// 2.1. Si hay algún error al tratar de guardar la metadata del
			// documento y el documento fisico fue almacenado en el gestor
			// documental, este último será eliminado para deshacer lacción
			// completa
			if (e.getMessage()
					.contains(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR088.toString()))
					&& !pathDocumento.isEmpty()) {
				gestorDocumentalFacade.eliminarDocumento(pathDocumento);
			}

			throw e;
		} catch (Exception e) {
			// 2.2. Si hay algún error al tratar de guardar la metadata del
			// documento y el documento fisico fue almacenado en el gestor
			// documental, este último será eliminado para deshacer lacción
			// completa
			if (e instanceof EJBTransactionRolledbackException && !pathDocumento.isEmpty()) {
				gestorDocumentalFacade.eliminarDocumento(pathDocumento);
			}

			throw e;
		}

		return idsDocumentos;
		
	}
	
	@Override
	public void eliminarListadoDocumentos(List<Long> documentos, String idUsuario) throws SIMASCNegocioExcepcion {
		
		for (Long idDocumento : documentos) {
			this.eliminarDocumento(idDocumento, idUsuario);
		}
	}

}

class TrustAnyTrustManager implements X509TrustManager {
    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }
    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[] {};
    }
}