package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorCarpeta;
import com.ccb.simasc.integracion.manejadores.ManejadorCorrerTraslado;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorNotificacionDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICarpetaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICuadernoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IGestorDocumentalFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.INotificacionDocumentoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.transversal.dto.CarpetaDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.CuadernoDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.ParametrosCargueDocumentoDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosCarpetaDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Carpeta;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.CorrerTraslado;
import com.ccb.simasc.transversal.entidades.CorrerTrasladoPK;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.NotificacionDocumento;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Carpeta}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class CarpetaFacade extends AccesoFacade<Carpeta, Long, CarpetaDTO> implements ICarpetaFacade {
	private static final Logger logger = LogManager.getLogger(CarpetaFacade.class.getName());
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorCarpeta manejadorCarpeta;
	
	@EJB
	private ManejadorDocumento manejadorDocumento;
	
	@EJB
	private ManejadorCorrerTraslado manejadorCorrerTraslado;
	
	@EJB
	private IDominioFacade dominioFacade;
	
	@EJB
	private ParametrosGeneralesFacade parametrosGeneralesFacade;
	
	@EJB
	private ManejadorPersona manejadorPersona;
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	
	@EJB
    private IDocumentoFacade documentoFacade; 

	@EJB
    private ICasoFacade casoFacade; 

	@EJB
    private IPersonaFacade personaFacade; 

	@EJB
    private ICorreoElectronicoFacade correoElectronicoFacade;
	
	@EJB
	private IRolPersonaCasoFacade rolPersonaCasoFacade;
	
    @EJB
    private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade; 
    
	@EJB
	private IGestorDocumentalFacade gestorDocumentalFacade;
	
	@EJB 
	private IEventoFacade eventoFacade;
	
	@EJB
	private INotificacionDocumentoFacade notificacionDocumentoFacade;
	
	@EJB
	private ManejadorNotificacionDocumento manejadorNotificacionDocumento;
	
	@EJB
	private ICuadernoFacade cuadernoFacade;
	
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;


	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorCarpeta;
	}

	@Override
	public CarpetaDTO transformarSinDependencias(Carpeta obj) {
		CarpetaDTO dto = new CarpetaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public CarpetaDTO transformarConDependencias(Carpeta obj) {
		CarpetaDTO dto = new CarpetaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Carpeta transformarEntidadConDependencias(Carpeta obj) {
		Carpeta dto = new Carpeta();
		dto = new CarpetaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Carpeta transformarEntidadSinDependencias(Carpeta obj) {
		Carpeta dto = new Carpeta();
		dto = new CarpetaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public List<Carpeta> obtenerCarpetaCuadernoPorCaso(Long idCaso) {	
		return manejadorCarpeta.obtenerCudernosPorCaso(idCaso);
	}
	
	@Override
	public void guardarInformacionDocumento(ParametrosCarpetaDTO carpetaInfo) {
		//consultamos el documento anterior 
		Documento documento= documentoFacade.buscar(carpetaInfo.getIdDocumento());
		// se actualiza con la información del formulario
		documento.setDescripcion(carpetaInfo.getDescripcion());
		documento.setFechaRadicacion(carpetaInfo.getFechaRadicacion());
		documento.setIdPersonaRemitente(carpetaInfo.getIdRemitente());
		documento.setNumeroFolios(carpetaInfo.getNumeroFolios());
		documento.setFechaRadicacion(carpetaInfo.getFechaRadicacion());	
		if(carpetaInfo.getIdCarpeta() > 0 && documento.getIdCarpeta() != carpetaInfo.getIdCarpeta()){
			documento.setIdCarpeta(carpetaInfo.getIdCarpeta());
		}
		// se actualiza el documento si se cambio
		String usuarioModificacion = null;
		if(carpetaInfo.getIdDocumentoAnterior() != -1L || carpetaInfo.isRemplazarDocumento()) {
			if(carpetaInfo.getDocumento() != null && carpetaInfo.getDocumento().getIdDocumento() != null ){
				documento.setUrl(carpetaInfo.getDocumento().getUrl());
				documento.setTipoArchivo(carpetaInfo.getDocumento().getTipoArchivo());
				documento.setNombre(carpetaInfo.getDocumento().getNombre());
			}
		}
		if(appContext!= null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() != null  ){
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		}else{
			throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR530.toString()));
		}
		documento.setPublicado(true);
		documento.setIdUsuarioModificacion(usuarioModificacion);
		documento.setFechaUltimaModificacion(new Date());
		documento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);

		//Correr Traslado
		if (carpetaInfo.isNotificarPartes()) {
			
			//Notificacion a las partes
			Caso caso= casoFacade.buscar(carpetaInfo.getIdCaso());
			ParametrosEnvioCorreoDTO parametrosCorreo2 = new ParametrosEnvioCorreoDTO();
			//String cuerpo = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_CORREO_TRASLADO, UtilDominios.CUERPO_CORREO_TRASLADO);
			ParametrosGenerales url = parametrosGeneralesFacade.consultarPorCodigo(UtilConstantes.PARAMETRO_CODIGO_URL_ACCESO);
			parametrosCorreo2.setAsunto(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_CORREO_TRASLADO, UtilDominios.ASUNTO_CORREO_TRASLADO));
			//Partes seleccionadas
			List<PersonaBasicaDTO> partesCaso = carpetaInfo.getPersonasTraslado();								
			Date fechaTraslado = new Date();
			if(partesCaso != null){
				for(PersonaBasicaDTO parte:partesCaso){
					List<String> cuerpoCorreoPartes = new ArrayList<>();
					String cuerpoCorreo = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO501.toString(), parte.getNombreCompleto(), caso.getNombre(), documento.getNombre(), url.getValorTexto());
					cuerpoCorreoPartes.add(cuerpoCorreo);
					List<CorreoElectronico> correosPartes = new ArrayList<>();
					List<CorreoElectronico> listCorreosPartes = new ArrayList<>();
					RolPersonaCaso rpc = manejadorRolPersonaCaso.consultarPersonaAsignadaCaso(parte.getIdPersona(), caso.getIdCaso());
					correosPartes = correoElectronicoFacade.consultaCorreosPersona(parte.getIdPersona());
					for(CorreoElectronico correo:correosPartes){
						listCorreosPartes.add(correo);
					}
					if(rpc != null && rpc.getRolPersonaCasoPK() != null && rpc.getRolPersonaCasoPK().getIdRol() != null){
						CorrerTraslado traslado = new CorrerTraslado();
						CorrerTrasladoPK trasladoPK = new CorrerTrasladoPK();
						trasladoPK.setFechaTraslado(fechaTraslado);
						trasladoPK.setIdCaso(caso.getIdCaso());
						trasladoPK.setIdDocumento(documento.getIdDocumento());
						trasladoPK.setIdRol(rpc.getRolPersonaCasoPK().getIdRol());
						trasladoPK.setIdPersona(parte.getIdPersona());				
						List<CorreoElectronicoDTO> correosPartesDTO = (List<CorreoElectronicoDTO>) correoElectronicoFacade.transformarColeccionConDependencias(listCorreosPartes, new ArrayList<CorreoElectronicoDTO>());
						//Parametros
						parametrosCorreo2.setCuerpoCorreo(cuerpoCorreoPartes);
						parametrosCorreo2.setRolPersonaCaso(correosPartesDTO);
						List<CorreoElectronico> correoElectronicoList2 = new ArrayList<>();
						for (RolPersonaCaso rolPersona : caso.getRolPersonaCasoList()) {
							if (rolPersona.getRol().getNombre().equals(UtilDominios.ROL_PERSONA_ABOGADO)) {
								correoElectronicoList2 = rolPersona.getPersona().getCorreoElectronicoList();
							}
						}
						for (CorreoElectronico correoPrincipal : correoElectronicoList2) {
							if (correoPrincipal.getTipo().equals(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL)) {
								CorreoElectronicoDTO correoDTO = new CorreoElectronicoDTO();
								correoDTO = correoElectronicoFacade.transformarConDependencias(correoPrincipal);
								parametrosCorreo2.setRemitente(correoDTO);
							}
						}
						
						parametrosCorreo2.setIdCaso(caso.getIdCaso());
						parametrosCorreo2.setTipo(UtilDominios.TIPO_PLANTILLAS_CORREO_GENERAL);
						parametrosCorreo2.setCertificado(carpetaInfo.isEnviarCorreoCertificado());
						correoRolPersonaCasoFacade.enviarCorreo(parametrosCorreo2);
						traslado.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
						traslado.setCorrerTrasladoPK(trasladoPK);
						manejadorCorrerTraslado.crear(traslado);
					}
				}
			}
		}
		manejadorDocumento.actualizar(documento);
	}
	
	public void generarCuadernos(Long casoId) {
		List<CuadernoDTO> cuadernos = cuadernoFacade.obtenerCuadernos();
		ParametrosCargueDocumentoDTO parametrosDocumento = new ParametrosCargueDocumentoDTO();
		parametrosDocumento.setIdCasoOSolicitud(casoId);
		String directorioCaso = this.gestorDocumentalFacade.obtenerDirectorioCasoOSolicitud(parametrosDocumento, false);
		for(CuadernoDTO obj : cuadernos){
			Carpeta cuaderno = manejadorCarpeta.obtenerCarpetaCuadernoCaso(casoId, obj.getIdCuaderno());
			//si no se encuentra el cuaderno para el caso se crea.
			if(cuaderno == null){
				Carpeta carpeta=new Carpeta();
				carpeta.setEsCuaderno(true);
				carpeta.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				carpeta.setFechaUltimaModificacion(new Date());
				carpeta.setNombre(obj.getNombre());
				carpeta.setIdCaso(casoId);
				carpeta.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
				carpeta.setPath(directorioCaso);
				carpeta.setIdCuaderno(obj.getIdCuaderno());
		        manejadorCarpeta.crear(carpeta);
			}
		}
//		nombresCarpetas.add(UtilConstantes.NOMBRE_CUADERNO_PRINCIPAL);
//		nombresCarpetas.add(UtilConstantes.NOMBRE_CUADERNO_PRUEBAS);
//		nombresCarpetas.add(UtilConstantes.NOMBRE_CUADERNO_ADMON_GASTOS);
//		nombresCarpetas.add(UtilConstantes.NOMBRE_CUADERNO_MEDIDAS_CAUTELARES);		
//		Map<String, String> infoCarpetas = gestorDocumentalFacade.crearCarpetasCaso(nombresCarpetas, casoId, null);
		
		
			
	}

	@Override
	public void enviarAlExpediente(List<Documento> docs, Long idCarpeta) {
		
		for(Documento doc:docs){
			Documento documento = documentoFacade.buscar(doc.getIdDocumento());
			InputStream documento1 = null;
			try {
				documento1 = new FileInputStream(documento.getUrl());
			} catch (FileNotFoundException e) {
				logger.error("Error: ", e);
			}
			documento.setIdCarpeta(idCarpeta);
			documento.setNombre(documento.getNombre().replace(",", ""));
			String pathDoc = gestorDocumentalFacade.subirDocumento(documento.getIdCaso(), idCarpeta, documento1, documento.getNombre(), documento.getTipoArchivo(), null);
			gestorDocumentalFacade.eliminarDocumento(documento.getUrl());
			documento.setUrl(pathDoc);
			documento.setPublicado(true);
			documentoFacade.actualizar(documento);
			Caso caso = casoFacade.buscar(documento.getIdCaso());
			String observacionesevento = MensajesSimasc.getInstancia()
					.getMensajePorKey(MensajesEnum.INFO003.toString());
			observacionesevento = observacionesevento.replace("denominacion", documento.getNombre());
			eventoFacade.registrarEvento(caso, UtilDominios.TIPO_EVENTO_PUBLICACION_DOCUMENTO, observacionesevento,
					UtilConstantes.USUARIO_DEFECTO_SIMASC, new Timestamp(new Date().getTime()),
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
			
		}
	}	
	
	public ParametrosCarpetaDTO obtenerDocumentoCaso(Long idCaso, Long codigoDoc) {
		Documento documento = new Documento();
		documento = manejadorDocumento.buscar(codigoDoc);
		ParametrosCarpetaDTO carpetaInfo= new ParametrosCarpetaDTO();
		carpetaInfo.setDescripcion(documento.getDescripcion());
		carpetaInfo.setNumeroFolios(documento.getNumeroFolios());
		carpetaInfo.setPublicado(documento.getPublicado());
		carpetaInfo.setIdCaso(idCaso);
		carpetaInfo.setNombreDocumento(documento.getNombre());
		carpetaInfo.setFechaRadicacion(documento.getFechaRadicacion());
		if (documento.getIdPersonaRemitente() != null) {
			carpetaInfo.setIdRemitente(documento.getIdPersonaRemitente());
		}
		if (documento.getIdCarpeta() != null) {
			carpetaInfo.setIdCarpeta(documento.getIdCarpeta());
		}
		carpetaInfo.setTipoArchivo(documento.getTipoArchivo());
		Carpeta cuaderno = new Carpeta();
		
		if(documento.getIdCarpeta()!=null){
			cuaderno = manejadorCarpeta.buscar(documento.getIdCarpeta());
			if(cuaderno.getIdCarpetaContenedora()!=null){
				while(cuaderno.getIdCarpetaContenedora()!=null){
					cuaderno = manejadorCarpeta.buscar(cuaderno.getIdCarpetaContenedora());
				}			
			}
			carpetaInfo.setIdCuaderno(cuaderno.getIdCarpeta());			
		}
		
		NotificacionDocumento notificacionDocumento= new NotificacionDocumento();

		InformacionFiltro f = new InformacionFiltro(TipoFiltro.EXACTO, NotificacionDocumento.ENTIDAD_NOTIFICACION_DOCUMENTO_ID_DOCUMENTO, codigoDoc); 
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(f);
		List<NotificacionDocumento> notificacionList = manejadorNotificacionDocumento.consultar(filtros, null, null);			
		
		if(notificacionList.size()>0){
			for(NotificacionDocumento notificacion:notificacionList){
				carpetaInfo.setTipoNotificacion(notificacion.getTipoNotificacion());
				carpetaInfo.setNorma(notificacion.getNorma());
				carpetaInfo.setAsunto(notificacion.getAsunto()!=null?notificacion.getAsunto():"");
				carpetaInfo.setTermino(notificacion.getTermino()!=null?notificacion.getTermino():"");
				carpetaInfo.setProvidencia(notificacion.getProvidencia()!=null?notificacion.getProvidencia():"");
				carpetaInfo.setFechaNotificacion(notificacion.getFechaFijacion());
			}
		}			
		return carpetaInfo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Documento> obtenerDocumentoCarpeta(Long idCaso) {
		List<Documento> listDocumentos = new ArrayList<>();
		List<Documento> listDocumentosConsultados = new ArrayList<>();
		InformacionFiltro f = new InformacionFiltro(TipoFiltro.EXACTO, Documento.ENTIDAD_DOCUMENTO_ID_CASO, idCaso);
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(f);
		listDocumentos = manejadorDocumento.consultar(filtros, null, null);
		DocumentoDTO transformador = new DocumentoDTO();
		for (Documento doc : listDocumentos) {
			if (doc.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
				listDocumentosConsultados.add(doc);
			}
		}
		return (List<Documento>) transformador.transformarColeccionEntidadesSinDependencias(listDocumentosConsultados);
	}
	
	@Override	
	public Carpeta crearCarpeta(Long idCuaderno, String nombre) {
		Carpeta cuaderno= manejadorCarpeta.buscar(idCuaderno);
		Carpeta carpeta= new Carpeta();
		carpeta.setIdCaso(cuaderno.getIdCaso());
		carpeta.setEsCuaderno(false);
		carpeta.setIdCarpetaContenedora(idCuaderno);
		carpeta.setNombre(nombre);
		carpeta.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		carpeta.setFechaUltimaModificacion(new Date());
		carpeta.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		String path= cuaderno.getPath()+File.separator+nombre;
		carpeta.setPath(path);
		gestorDocumentalFacade.crearCarpetaCuaderno(nombre,cuaderno.getPath());
		Carpeta carpetaCreada = manejadorCarpeta.actualizarCarpeta(carpeta);
		return carpetaCreada;
		
	}
	
	@Override
	public Documento cambiarDestinoDocumento(Long idCarpetaActual, Long idCarpetaDestino, Long idDocumento) {
		Documento documento = documentoFacade.buscar(idDocumento);
		InputStream inputStream = null;
		String urlActual = documento.getUrl();
		try {
			documento.setNombre(documento.getNombre().replace(",", ""));
			inputStream = new FileInputStream(documento.getUrl());
			String pathDoc = gestorDocumentalFacade.subirDocumento(documento.getIdCaso(), idCarpetaDestino, inputStream,
					documento.getNombre(), documento.getTipoArchivo().toLowerCase(), null);
			documento.setUrl(pathDoc);
			documento.setPublicado(true);
			documento.setIdCarpeta(idCarpetaDestino);
			documento = manejadorDocumento.actualizarDocumentoDigitalizado(documento);

			if (urlActual != null && !urlActual.isEmpty())
				gestorDocumentalFacade.eliminarDocumento(urlActual);
		} catch (FileNotFoundException e) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR237.toString()), e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("Error: ", e);
				}
			}
		}

		return documento;
	}
	
	@Override
	public void eliminarCarpeta(Long idCarpeta) {
		Carpeta carpetaEliminar = manejadorCarpeta.buscar(idCarpeta);
		InformacionFiltro f = new InformacionFiltro(TipoFiltro.EXACTO, Documento.ENTIDAD_DOCUMENTO_ID_CARPETA, carpetaEliminar.getIdCarpeta()); 
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(f);
		List<Documento> listDocumentos= manejadorDocumento.consultar(filtros, null, null);
		
		for(Documento doc:listDocumentos){
			gestorDocumentalFacade.eliminarDocumento(doc.getUrl());
			doc.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
			manejadorDocumento.actualizarDocumentoDigitalizado(doc);
		}
		gestorDocumentalFacade.eliminarCarpeta(carpetaEliminar.getPath());
		carpetaEliminar.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			carpetaEliminar.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
		} else {
			carpetaEliminar.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		}		
		carpetaEliminar.setFechaUltimaModificacion(new Date());
		manejadorCarpeta.actualizarCarpeta(carpetaEliminar);
		
	}
	
	/**
	 * MÃ©todo que reemplaza el tag en el asunto si existe por el valor recibido
	 * @param cadena asunto 
	 * @param tag valor a buscar en el asunto
	 * @param valor nuevo valor por el que se cambiara el tag
	 * @return asunto con el tag reemplazado
	 */
	private String reemplazarTag(String cadena, String tag, String valor) {
		String nueva = cadena;
		if (valor != null)
			nueva = cadena.replace(tag, valor);
		return nueva;
	}

	@Override
	public List<Carpeta> obtenerCarpetasPorIdContenedor(Long idCarpetaContenedora) {
		return manejadorCarpeta.obtenerCarpetasySubcarpetas(idCarpetaContenedora);
	}

	@Override
	public void modificarUbicacionDocuemnto(long documento, long idCarpeta) {
		if(documento != 0) {
			manejadorDocumento.modificarUbicacionDocuemnto(idCarpeta, documento);
		}
		
	}

	@Override
	public void modificarUbicacionCarpeta(long carpeta, long carpetaContenedora) {
		Carpeta cuaderno= manejadorCarpeta.buscar(carpetaContenedora);
		Carpeta carpetaModificar = manejadorCarpeta.buscar(carpeta);
		if(carpetaModificar != null) {
			carpetaModificar.setIdCarpetaContenedora(carpetaContenedora);
			carpetaModificar.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			carpetaModificar.setFechaUltimaModificacion(new Date());
			String path= cuaderno.getPath()+File.separator+carpetaModificar.getNombre();
			carpetaModificar.setPath(path);
			 manejadorCarpeta.actualizarCarpeta(carpetaModificar);
		}
	}

	@Override
	public String obtenerCantidadCarpetasIDocumentos(long idCarpeta) {
		int documentos =0 ;
		int cantCarpetas = 0;
		Carpeta carpetaActual = manejadorCarpeta.buscar(idCarpeta);
		
		documentos = manejadorDocumento.obtenerCantidadDocumentoPorCarpeta(idCarpeta);
		List<Carpeta> carpetas = manejadorCarpeta.obtenerCarpetasySubcarpetas(idCarpeta);
		
		cantCarpetas = carpetas.size();
		for(Carpeta carpeta : carpetas) {
			documentos = documentos + carpeta.getDocumentoList().size();
			cantCarpetas = cantCarpetas +carpeta.getCarpetaList().size();
			
			for(Carpeta subcarpeta : carpeta.getCarpetaList()) {
				documentos = documentos + subcarpeta.getDocumentoList().size();
				cantCarpetas = cantCarpetas +subcarpeta.getCarpetaList().size();
				
				for(Carpeta carpetica : subcarpeta.getCarpetaList()) {
					documentos = documentos + carpetica.getDocumentoList().size();
					cantCarpetas = cantCarpetas +carpetica.getCarpetaList().size();
				}
			}
			
		}
		
		return "La Carpeta "+ carpetaActual.getNombre() +"-"+ carpetaActual.getIdCarpeta() + " tiene "+ cantCarpetas+" carpetas y "+ documentos+" documentos";
		
	}
	
	
	
	// protected region metodos adicionales end




	
}
