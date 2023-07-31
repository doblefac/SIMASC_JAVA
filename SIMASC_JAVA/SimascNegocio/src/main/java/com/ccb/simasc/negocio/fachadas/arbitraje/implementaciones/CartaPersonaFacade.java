package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta seccion sus modificaciones

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.LockModeType;
import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorCarpeta;
import com.ccb.simasc.integracion.manejadores.ManejadorCartaPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronico;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronicoRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorEvento;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroServicioSorteo;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPlantillaCarta;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorTelefono;
import com.ccb.simasc.integracion.manejadores.ManejadorUbicacion;
import com.ccb.simasc.integracion.manejadores.ManejadorValorPlantillaCarta;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionOrdenamiento;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICartaPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoTramiteOrdinarioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IGestorDocumentalFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IInvitadoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ILoteGeneradoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.INotificacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrosGeneralesFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaLoteFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IServicioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUsuarioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IZonaGeograficaFacade;
import com.ccb.simasc.transversal.dto.AudienciaDTO;
import com.ccb.simasc.transversal.dto.CartaAudienciaPendienteImpresionDTO;
import com.ccb.simasc.transversal.dto.CartaPendienteImpresionDTO;
import com.ccb.simasc.transversal.dto.CartaPersonaDTO;
import com.ccb.simasc.transversal.dto.ConsultaDominioDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.CorrespondenciaDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.DominioDTO;
import com.ccb.simasc.transversal.dto.LlamadaPlanillaCorrespondenciaDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.dto.PlanillaCorrespondenciaCartaDTO;
import com.ccb.simasc.transversal.dto.PlantillaCartaDTO;
import com.ccb.simasc.transversal.dto.ServicioDTO;
import com.ccb.simasc.transversal.dto.cartas.CartaCitacionAudienciasDTO;
import com.ccb.simasc.transversal.dto.cartas.LotesCartasDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosGenerarCartaDTO;
import com.ccb.simasc.transversal.dto.formularios.ParteCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.dto.reportes.PlanillaCorrespondenciaDTO;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Carpeta;
import com.ccb.simasc.transversal.entidades.CartaPersona;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.EventoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Invitado;
import com.ccb.simasc.transversal.entidades.LoteGenerado;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaLote;
import com.ccb.simasc.transversal.entidades.PersonaLotePK;
import com.ccb.simasc.transversal.entidades.PlantillaCarta;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.RolPersonaCasoPK;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.entidades.Telefono;
import com.ccb.simasc.transversal.entidades.Ubicacion;
import com.ccb.simasc.transversal.entidades.ValorPlantillaCarta;
import com.ccb.simasc.transversal.entidades.ZonaGeografica;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.GeneradorDocumentos;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;
import com.ccb.simasc.transversal.utilidades.variablesDinamicasCartas;
import com.ccb.simasc.transversal.utilidades.variablesEstaticasCarta;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import co.org.ccb.simasc.comun.correos.template.TemplateParser;
import co.org.ccb.simasc.comun.pdf.RadicacionSolicitudPDF;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link CartaPersona}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class CartaPersonaFacade extends AccesoFacade<CartaPersona, Long, CartaPersonaDTO>
		implements ICartaPersonaFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	private static final Logger logger = LogManager.getLogger(TemplateParser.class.getName());
	private static final String SALTO_LINEA = " <br/>";

	@EJB
	private ManejadorCartaPersona manejadorCartaPersona;

	@EJB
	private ManejadorParametroServicioSorteo manejadorParametroServicioSorteo;

	@EJB
	private ManejadorCorreoElectronicoRolPersonaCaso manejadorCorreoElectronicoRolPersonaCaso;

	@EJB
	private ManejadorPlantillaCarta manejadorPlantillaCarta;

	@EJB
	private ManejadorServicio manejadorServicio;

	@EJB
	private ManejadorDocumento manejadorDocumento;

	@EJB
	private ManejadorTelefono manejadorTelefono;

	@EJB
	private ManejadorCarpeta manejadorCarpeta;

	@EJB
	private IPersonaFacade personaFacade;

	@EJB
	private IInvitadoFacade invitadoFacade;

	@EJB
	private ICasoFacade casoFacade;

	@EJB
	private PlantillaCartaFacade plantillaCartaFacade;

	@EJB
	private IEventoFacade eventoFacade;

	@EJB
	private IGestorDocumentalFacade gestorDocumentalFacade;

	@EJB
	private IDominioFacade dominioFacade;

	@EJB
	private IParametrosGeneralesFacade parametrosGeneralesFacade;

	@EJB
	private IDocumentoFacade documentoFacade;

	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;

	@EJB
	private ICorreoElectronicoFacade correoElectronicoFacade;

	@EJB
	private variablesDinamicasCartas variablesDinamicasCartas;

	@EJB
	private ManejadorValorPlantillaCarta manejadorValoresPlantillaCarta;

	@EJB
	private ManejadorAudiencia manejadorAudiencia;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	private IEventoRolPersonaCasoFacade eventoRolPersonaCasoFacade;

	@EJB
	private IRolFacade rolFacade;

	@EJB
	private IUsuarioFacade usuarioFacade;

	@EJB
	private RadicacionSolicitudPDF generarPDF;

	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;

	@EJB
	private ManejadorUbicacion manejadorUbicacion;

	@EJB
	private ManejadorEvento manejadorEvento;

	@EJB
	private ManejadorCorreoElectronico manejadorCorreoElectronico;

	@EJB
	private IServicioFacade servicioFacade;

	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private IPersonaLoteFacade personaLoteFacade;

	@EJB
	private ILoteGeneradoFacade loteGeneradoFacade;

	@EJB
	private ICasoTramiteOrdinarioFacade casoTramiteOrdinarioFacade;

	@EJB
	private IZonaGeograficaFacade zonaGeograficaFacade;

	@EJB
	private INotificacionFacade notificacionFacade;

	// contexto de sesion de usuario.
	@Inject
	private ApplicationRequestContext appContext;

	private ExecutorService executor;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorCartaPersona;
	}

	@Override
	public CartaPersonaDTO transformarSinDependencias(CartaPersona obj) {
		return new CartaPersonaDTO().transformarSinDependencias(obj);
	}

	@Override
	public CartaPersonaDTO transformarConDependencias(CartaPersona obj) {
		return new CartaPersonaDTO().transformarConDependencias(obj);
	}

	@Override
	public CartaPersona transformarEntidadConDependencias(CartaPersona obj) {
		return new CartaPersonaDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public CartaPersona transformarEntidadSinDependencias(CartaPersona obj) {
		return new CartaPersonaDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public Collection<CartaPersona> transformarCollection(Long idCaso) {
		CartaPersonaDTO dto = new CartaPersonaDTO();
		InformacionFiltro f = new InformacionFiltro(TipoFiltro.EXACTO, CartaPersona.ENTIDAD_CARTA_PERSONA_ID_CASO,
				idCaso);
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(f);
		return dto.transformarColeccionEntidadesConDependencias(obtenerEntidadesPorFiltro(filtros,
				new ArrayList<InformacionOrdenamiento>(), null, new ArrayList<CartaPersona>(), true));
	}

	public String reemplazarTags(String plantillaHTML, Map<String, String> filtros, Long idCaso, String idUsuario) {
		String plantilla = plantillaHTML;
		ParametrosGenerales parametroDirectorCentro = parametrosGeneralesFacade
				.buscar(UtilParamGenerales.DIRECTOR_CENTRO_CONCILIACION, LockModeType.NONE);

		String imgEncoding = "";

		if (idCaso != null) {
			imgEncoding = obtenerFirmaConciliador(idCaso);
		} else {
			ParametrosGenerales parametroDocumentoFirma = parametrosGeneralesFacade
					.buscar(UtilParamGenerales.RUTA_DOCUMENTO_FIRMA, LockModeType.NONE);
			File f = new File(parametroDocumentoFirma.getValorTexto());
			imgEncoding = encodeFileToBase64Binary(f);
		}

		ParametrosGenerales parametroCiudad = parametrosGeneralesFacade.buscar(UtilParamGenerales.CIUDAD,
				LockModeType.NONE);
		ParametrosGenerales parametroCargo = parametrosGeneralesFacade.buscar(UtilParamGenerales.CARGO,
				LockModeType.NONE);

		ParametrosGenerales parametroDirectorCentroP = parametrosGeneralesFacade
				.buscar(UtilParamGenerales.DIRECTOR_PARAMETRO, LockModeType.NONE);
		ParametrosGenerales parametroDocumentoFirmaP = parametrosGeneralesFacade
				.buscar(UtilParamGenerales.FIRMA_PARAMETRO, LockModeType.NONE);
		ParametrosGenerales parametroCiudadP = parametrosGeneralesFacade.buscar(UtilParamGenerales.CIUDAD_PARAMETRO,
				LockModeType.NONE);
		ParametrosGenerales parametroCargoP = parametrosGeneralesFacade.buscar(UtilParamGenerales.CARGO_PARAMETRO,
				LockModeType.NONE);

		filtros.put(parametroDirectorCentroP.getValorTexto(), parametroDirectorCentro.getValorTexto());
		filtros.put(parametroDocumentoFirmaP.getValorTexto(), imgEncoding);
		filtros.put(parametroCiudadP.getValorTexto(), parametroCiudad.getValorTexto());
		filtros.put(parametroCargoP.getValorTexto(), parametroCargo.getValorTexto());

		if (idUsuario != null) {
			filtros.put(UtilConstantes.VALOR_PLANTILLA_NOMBRE_USUARIO,
					manejadorPersona.buscar(Long.parseLong(idUsuario)).getNombreCompleto());
		} else if (appContext.getContextoSesion() != null && appContext.getContextoSesion().getIdUsuario() != null) {
			filtros.put(UtilConstantes.VALOR_PLANTILLA_NOMBRE_USUARIO, manejadorPersona
					.buscar(Long.parseLong(appContext.getContextoSesion().getIdUsuario())).getNombreCompleto());
		}

		Iterator<Entry<String, String>> entries;
		Boolean hasBeenReplaced = true;
		Double currentIterations = 0d;
		while (hasBeenReplaced && currentIterations < UtilConstantes.MAXIMO_NUMERO_ITERACIONES) {
			hasBeenReplaced = false;
			currentIterations++;
			entries = filtros.entrySet().iterator();
			while (entries.hasNext()) {
				Entry<String, String> entry = entries.next();
				String key = entry.getKey();
				String value = entry.getValue();

				if (plantilla.contains(key)) {
					plantilla = plantilla.replace(key, (value == null ? UtilConstantes.CADENA_VACIA : value));
					hasBeenReplaced = true;
				}
			}
		}

		return plantilla;
	}

	private String obtenerFirmaConciliador(Long idCaso) {
		String imgEncoding = "";

		PersonaBasicaDTO conciliador = manejadorPersona.consultarPersonaPorRolCaso(idCaso,
				UtilDominios.ROL_PERSONA_CONCILIADOR);
		if (conciliador != null) {
			File f = obtenerArchivoFirma(conciliador.getNumeroDocumento());
			if (f.exists())
				imgEncoding = encodeFileToBase64Binary(f);
		}
		return imgEncoding;
	}

	private File obtenerArchivoFirma(String documentoConciliador) {
		ParametrosGenerales parametroDocumentoFirma = parametrosGeneralesFacade
				.buscar(UtilParamGenerales.RUTA_FIRMA_CONCILIADORES, LockModeType.NONE);
		String ruta = parametroDocumentoFirma.getValorTexto().concat(documentoConciliador);
		File f = new File(ruta.concat(".jpg"));
		if (!f.exists())
			f = new File(ruta.concat(".JPG"));
		return f;
	}

	private static String encodeFileToBase64Binary(File file) {
		String encodedfile = null;
		FileInputStream fileInputStreamReader = null;
		try {
			fileInputStreamReader = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			if (fileInputStreamReader.read(bytes) > 0) {
				String encoded = DatatypeConverter.printBase64Binary(bytes);
				String header = "data:image/";
				String extension = extension(file);
				String close = ";base64,";
				encodedfile = header + extension + close + encoded;
			}
		} catch (FileNotFoundException e) {
			logger.error("Error: ", e);
		} catch (IOException e) {
			logger.error("Error: ", e);
		} finally {
			try {
				if (fileInputStreamReader != null)
					fileInputStreamReader.close();
			} catch (IOException e) {
				logger.error("Error: ", e);
			}
		}

		return encodedfile;
	}

	private static String extension(File f) {
		String fullPath = f.getAbsolutePath();
		int dot = fullPath.lastIndexOf('.');
		return fullPath.substring(dot + 1);
	}

	@Override
	public List<CartaPersona> consultarCartas(Long idCaso) {
		return (List<CartaPersona>) transformarCollection(idCaso);
	}

	public String generarAsunto(CartaPersona cartaGenerada) {

		PlantillaCarta plantilla = manejadorPlantillaCarta.buscar(cartaGenerada.getIdPlantillaCarta());
		StringBuilder asunto = new StringBuilder();
		Dominio dominioNombrePlantillaCarta = dominioFacade
				.getDominioSinClasificadores(UtilDominios.DOMINIO_NOMBRE_PLANTILLA_CARTA, plantilla.getNombre());
		asunto.append(
				dominioNombrePlantillaCarta != null ? dominioNombrePlantillaCarta.getNombre() : plantilla.getNombre());

		String asuntoSinCaracteres = asunto.toString();
		asuntoSinCaracteres = asuntoSinCaracteres.replaceAll("á", "a");
		asuntoSinCaracteres = asuntoSinCaracteres.replaceAll("é", "e");
		asuntoSinCaracteres = asuntoSinCaracteres.replaceAll("í", "i");
		asuntoSinCaracteres = asuntoSinCaracteres.replaceAll("ó", "o");
		asuntoSinCaracteres = asuntoSinCaracteres.replaceAll("ú", "u");
		asuntoSinCaracteres = asuntoSinCaracteres.replaceAll("ñ", "n");
		asuntoSinCaracteres = asuntoSinCaracteres.replaceAll("Ñ", "N");
		asuntoSinCaracteres = asuntoSinCaracteres.replaceAll("Á", "A");
		asuntoSinCaracteres = asuntoSinCaracteres.replaceAll("É", "E");
		asuntoSinCaracteres = asuntoSinCaracteres.replaceAll("Í", "I");
		asuntoSinCaracteres = asuntoSinCaracteres.replaceAll("Ó", "O");
		asuntoSinCaracteres = asuntoSinCaracteres.replaceAll("Ú", "U");
		asuntoSinCaracteres = asuntoSinCaracteres.replaceAll("\\w\\s_-+", "");
		asuntoSinCaracteres = asuntoSinCaracteres.replaceAll("[\\\\/:*?\"<>|]", "");
		
		return asuntoSinCaracteres;
	}

	private Map<String, Object> mapearArgumentosEnFiltros(ParametrosGenerarCartaDTO filtros) {
		Map<String, Object> retorno = new HashMap<String, Object>();
		if (filtros != null) {
			if (filtros.getIdsDocumentos() != null && !filtros.getIdsDocumentos().isEmpty())
				retorno.put(UtilDominios.DOCUMENTOS_RADICADOS, filtros.getIdsDocumentos());
			if (filtros.getListaIdNotificados() != null && !filtros.getListaIdNotificados().isEmpty()) {
				retorno.put(UtilDominios.PERSONAS_APODERADOS, filtros.getListaIdNotificados());
				retorno.put(UtilDominios.LISTA_PARTES_SELECCIONADAS, filtros.getListaIdNotificados());
			}

		}
		return retorno;
	}

	private List<ValorPlantillaCarta> consultarValoresPlantilla(Long idPlantilla) {
		InformacionFiltro f = new InformacionFiltro(TipoFiltro.EXACTO,
				ValorPlantillaCarta.ENTIDAD_VALOR_PLANTILLA_CARTA_ID_PLANTILLA_CARTA, idPlantilla);
		List<InformacionFiltro> filtros1 = new ArrayList<>();
		filtros1.add(f);
		return manejadorValoresPlantillaCarta.consultar(filtros1, null, null);
	}

	private List<Invitado> llenarListaInvitados(List<Long> invitados) {
		List<Invitado> invitadosNotificar = new ArrayList<>();
		if (null != invitados) {
			for (Long id : invitados) {
				Invitado invitado = invitadoFacade.buscar(id);
				invitadosNotificar.add(invitado);
			}
		}
		return invitadosNotificar;
	}

	private List<Persona> llenarListaPersonas(List<Long> notificados) {
		List<Persona> personasNotificar = new ArrayList<>();
		if (null != notificados) {
			for (Long id : notificados) {
				Persona persona = personaFacade.buscar(id);
				personasNotificar.add(persona);
			}
		}
		return personasNotificar;
	}

	/**
	 * 
	 * @param filtros
	 * @param totalDeCartas
	 * @return
	 */
	private List<CartaPersona> generarCartas(ParametrosGenerarCartaDTO filtros, Boolean totalDeCartas,
			String idUsuario) {
		List<Long> personasNotificar = null;
		List<Invitado> invitadosNotificar = null;
		String asunto = null;
		Boolean notificacionGeneral = false;
		Long idPlantilla = Long.valueOf(filtros.getIdPlantilla());
		List<CartaPersona> cartas = new ArrayList<CartaPersona>();
		PlantillaCarta plantilla;
		if (filtros.getPlantillaCarta() != null)
			plantilla = filtros.getPlantillaCarta();
		else
			plantilla = plantillaCartaFacade.buscar(idPlantilla, LockModeType.NONE);
				
		Caso caso = casoFacade.buscar(filtros.getIdCaso());
		Long idServicio = caso.getServicioMateria().getServicio().getIdServicio();
		if(!idServicio.equals(UtilConstantes.ID_SERVICIO_ARBITRAJE_ABREVIADO)) {
			asunto = generarAsuntoCarta(plantilla.getNombre(), filtros.getIdAudiencia(), filtros.getIdCaso(), idServicio, null);
		}
		
		Map<String, Object> args = mapearArgumentosEnFiltros(filtros);
		Map<String, String> filtrosVariables = null;
		Map<String, String> filtrosVariablesEstaticas = variablesEstaticasCarta
				.getInformacionEstaticaPlantilla(filtros.getIdCaso());

		ServicioDTO servicio = servicioFacade.consultarServicioDelCaso(filtros.getIdCaso());
		List<DominioDTO> actasConstancias = dominioFacade.consultarDominiosPorClasificadores(
				Arrays.asList(new ConsultaDominioDTO(null, UtilDominios.CODIGO_CLASIFICADOR_ACTAS_CONSTANCIAS,
						UtilDominios.DOMINIO_CLASIFICADOR_ACTAS_CONSTANCIAS)));
		boolean isActaConstancia = validarPlantillaActaConstancia(actasConstancias, plantilla.getNombre());

		if (!isActaConstancia) {
			personasNotificar = filtros.getListaIdNotificados();
			invitadosNotificar = llenarListaInvitados(filtros.getListaIdInvitados());
		} else {
			Long usuarioModificacion = Long.valueOf(appContext.getContextoSesion().getIdUsuario());
			personasNotificar = Arrays.asList(usuarioModificacion);
			filtros.setIndicadorEnvio(UtilConstantes.NO);
			filtros.setIndicadorNotificacion(UtilConstantes.NO);
			filtros.setEnvioPorLotes(false);
		}

		if (servicio.getTipo().equals(UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA) ||
		servicio.getTipo().equals(UtilDominios.TIPO_SERVICIO_INTERNACIONAL)) {
			List<ValorPlantillaCarta> valoresPlantillaCarta = consultarValoresPlantilla(idPlantilla);
			Boolean isTipoParte = esDirigido(valoresPlantillaCarta, UtilDominios.ID_PERSONA);
			Boolean isAudiencia = esDirigido(valoresPlantillaCarta, UtilDominios.ID_AUDIENCIA);
			Boolean isTipoInvitado = esDirigido(valoresPlantillaCarta, UtilDominios.ID_PERSONA_INVITADO);
			if (isTipoParte) {
				generarCartasPartes(personasNotificar, cartas, filtrosVariablesEstaticas, filtros, isAudiencia, null,
						plantilla, asunto, idUsuario, plantilla.getNombre(), idServicio);
			}
			if (isTipoInvitado) {
				generarCartasInvitados(invitadosNotificar, cartas, filtrosVariablesEstaticas, filtros, isAudiencia,
						plantilla.getIdPlantillaCarta(), plantilla.getPlantillaHtml(), asunto);
				if (!isTipoParte) {
					generarCartasPartes(personasNotificar, cartas, filtrosVariablesEstaticas, filtros, isAudiencia,
							null, plantilla, asunto, idUsuario, plantilla.getNombre(), idServicio);
				}
			}
			if (isAudiencia && !isTipoInvitado && !isTipoParte) {
				filtrosVariables = variablesDinamicasCartas.getInformacionPlantilla(plantilla.getIdPlantillaCarta(),
						filtros.getIdCaso(), 0L, filtros.getIdAudiencia(), args);
				notificacionGeneral = true;
			} else if (!isAudiencia && !isTipoInvitado && !isTipoParte) {
				filtrosVariables = variablesDinamicasCartas.getInformacionPlantilla(plantilla.getIdPlantillaCarta(),
						filtros.getIdCaso(), 0L, 0L, args);
				notificacionGeneral = true;
			}
			if (notificacionGeneral)
				generarCartasNotificacionGeneral(cartas, filtrosVariables, personasNotificar, invitadosNotificar,
						filtros, filtrosVariablesEstaticas, plantilla.getPlantillaHtml(), asunto, plantilla.getNombre(), idServicio);
		} else {
			generarCartasPartes(personasNotificar, cartas, filtrosVariablesEstaticas, filtros,
					filtros.getIdAudiencia() != null, isActaConstancia, plantilla, asunto, idUsuario, plantilla.getNombre(), idServicio);
		}

		return regresarResultadoCartas(totalDeCartas, filtros.getIdCaso(), cartas);
	}

	/**
	 * MÃ©todo que genera el asunto de la carta dependiendo el tipo de carta a
	 * generar
	 * 
	 * @param codigoPlantilla codigo de la plantilla
	 * @param idAudiencia     identificador de la audiencia si existe
	 * @param idCaso          identificador del caso
	 * @return asunto generado
	 */
	private String generarAsuntoCarta(String codigoPlantilla, Long idAudiencia, Long idCaso, Long idServicio, Long idPersona) {
		
		String nombreRol = "";
		String nombreCaso = casoFacade.obtenerNombreCaso(idCaso);
		String asunto = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ASUNTO_CARTA, codigoPlantilla);
		if (asunto == null)
			asunto = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ASUNTO_CARTA,
					UtilDominios.ASUNTO_CARTA_CONCILIACION);
		
		if(!idServicio.equals(UtilConstantes.ID_SERVICIO_ARBITRAJE_ABREVIADO)) {
			nombreRol = obtenerNombreRol(codigoPlantilla, idCaso);
		}else if(idPersona != null){
			nombreRol = manejadorRolPersonaCaso.obtenerNombreRol(idCaso, idPersona);
		}
		
		String tipoAudiencia = obtenerTipoAudiencia(idAudiencia);
		String nombrePlantilla = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_NOMBRE_PLANTILLA_CARTA,
				codigoPlantilla);

		asunto = reemplazarTag(asunto, UtilConstantes.TAG_ASUNTO_ID_CASO, idCaso.toString());
		asunto = reemplazarTag(asunto, UtilConstantes.TAG_ASUNTO_NOMBRE_CASO, nombreCaso);
		asunto = reemplazarTag(asunto, UtilConstantes.TAG_ASUNTO_NOMBRE_ROL, nombreRol);
		asunto = reemplazarTag(asunto, UtilConstantes.TAG_ASUNTO_TIPO_AUDIENCIA, tipoAudiencia);
		asunto = reemplazarTag(asunto, UtilConstantes.TAG_ASUNTO_NOMBRE_PLANTILLA, nombrePlantilla);
		
		asunto = asunto.replaceAll("á", "a");
		asunto = asunto.replaceAll("é", "e");
		asunto = asunto.replaceAll("í", "i");
		asunto = asunto.replaceAll("ó", "o");
		asunto = asunto.replaceAll("ú", "u");
		asunto = asunto.replaceAll("ñ", "n");
		asunto = asunto.replaceAll("Ñ", "N");
		asunto = asunto.replaceAll("Á", "A");
		asunto = asunto.replaceAll("É", "E");
		asunto = asunto.replaceAll("Í", "I");
		asunto = asunto.replaceAll("Ó", "O");
		asunto = asunto.replaceAll("Ú", "U");
		asunto = asunto.replaceAll("\\w\\s_-+", "");
		asunto = asunto.replaceAll("[\\\\/:*?\"<>|]", "");
		return asunto;
	}

	/**
	 * MÃ©todo que reemplaza el tag en el asunto si existe por el valor recibido
	 * 
	 * @param cadena asunto
	 * @param tag    valor a buscar en el asunto
	 * @param valor  nuevo valor por el que se cambiara el tag
	 * @return asunto con el tag reemplazado
	 */
	private String reemplazarTag(String cadena, String tag, String valor) {
		String nueva = cadena;
		if (valor != null)
			nueva = cadena.replace(tag, valor);
		return nueva;
	}

	/**
	 * MÃ©todo que obtiene el nombre del rol que realizo la aceptacion dependiendo
	 * de los parametros de sorteo
	 * 
	 * @param nombrePlantilla codigo de la plantilla
	 * @param idCaso          identificador del caso
	 * @return nombre del rol, null si no es necesaria la busqueda
	 */
	public String obtenerNombreRol(String nombrePlantilla, Long idCaso) {
		String nombreRol = null;
		if (UtilDominios.ASUNTO_CARTA_NOTIFICACION_ARBITRO.equals(nombrePlantilla)
				|| UtilDominios.ASUNTO_CARTA_NOTIFICACION_ARBITRO_INTERNACIONAL.equals(nombrePlantilla)) {
			nombreRol = manejadorParametroServicioSorteo.obtenerRol(idCaso);
		} else if (UtilDominios.ASUNTO_CARTA_NOTIFICACION_SECRETARIO.equals(nombrePlantilla)) {
			nombreRol = dominioFacade.consultarDominioDTO(UtilDominios.DOMINIO_ROL_PERSONA,
					UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL).getNombre();
		}
		return nombreRol;
	}

	/**
	 * MÃ©todo que obtiene el nombre del tipo de la audiencia
	 * 
	 * @param idAudiencia identificador de la audiencia
	 * @return nombre de la audiencia, null si no existe audiencia
	 */
	private String obtenerTipoAudiencia(Long idAudiencia) {
		String tipoAudiencia = null;
		Audiencia audiencia = manejadorAudiencia.buscar(idAudiencia);
		if (audiencia != null) {
			tipoAudiencia = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_AUDIENCIA,
					audiencia.getTipoAudiencia());
		}

		return tipoAudiencia;
	}

	/**
	 * Método que verifica si el codigo de la plantilla se encuentra dentro de los
	 * dominios del clasificador de plantillas de actas y constancias
	 * 
	 * @param dominios
	 * @param codigoPlantilla
	 * @return
	 */
	private boolean validarPlantillaActaConstancia(List<DominioDTO> dominios, String codigoPlantilla) {
		boolean isActaConstancia = false;
		for (DominioDTO dominioDTO : dominios) {
			if (dominioDTO.getDominioPK().getCodigo().equals(codigoPlantilla)) {
				isActaConstancia = true;
				break;
			}
		}

		return isActaConstancia;
	}

	private List<CartaPersona> regresarResultadoCartas(boolean totalDeCartas, Long idCaso, List<CartaPersona> cartas) {
		List<CartaPersona> cartasGeneradasListDTO;
		if (!totalDeCartas)
			cartasGeneradasListDTO = (List<CartaPersona>) transformarEntidadesColeccionConDependencias(cartas,
					new ArrayList<CartaPersona>());
		else
			cartasGeneradasListDTO = (List<CartaPersona>) transformarCollection(idCaso);

		return cartasGeneradasListDTO;
	}

	/**
	 * Método para la generación de cartas de notificación general de arbitraje
	 * 
	 * @param cartas
	 * @param filtrosVariables
	 * @param personasNotificar
	 * @param invitadosNotificar
	 * @param filtros
	 * @param filtrosVariablesEstaticas
	 * @param plantillaHtml
	 */
	private void generarCartasNotificacionGeneral(List<CartaPersona> cartas, Map<String, String> filtrosVariables,
			List<Long> personasNotificar, List<Invitado> invitadosNotificar, ParametrosGenerarCartaDTO filtros,
			Map<String, String> filtrosVariablesEstaticas, String plantillaHtml, String asunto, String codigoPlantilla, Long idServicio) {
		
		String asuntoPorServicio = asunto;
		String nombresPersonasSeleccionados = obtenerNombresPersonasSeleccionadas(filtros);
		filtrosVariables.putAll(filtrosVariablesEstaticas);
		filtrosVariables.put("personasP", nombresPersonasSeleccionados);
		String cartaGeneradaConTags = reemplazarTags(plantillaHtml, filtrosVariables, filtros.getIdCaso(), null);
		for (Long persona : personasNotificar)
			if(asuntoPorServicio == null) {
				asuntoPorServicio = generarAsuntoCarta(codigoPlantilla, filtros.getIdAudiencia(), filtros.getIdCaso(), idServicio, persona);
			}else {
				cartas.addAll(crearCartaPersonaPorCorreo(filtros, persona, plantillaHtml, filtrosVariables, false, null,
						asuntoPorServicio, null));
			}
		for (Invitado invitado : invitadosNotificar) {
			CartaPersona cartaGeneradaInvitado = crearCartaInvitado(cartaGeneradaConTags, filtros, invitado, asunto);
			cartas.add(cartaGeneradaInvitado);
		}
	}

	/**
	 * Método para la generación de las cartas para las partes del caso
	 * 
	 * @param personasNotificar
	 * @param cartas
	 * @param filtrosVariablesEstaticas
	 * @param filtros
	 * @param isAudiencia
	 * @param idPlantillaCarta
	 * @param plantillaHtml
	 */
	private void generarCartasPartes(List<Long> personasNotificar, List<CartaPersona> cartas,
			Map<String, String> filtrosVariablesEstaticas, ParametrosGenerarCartaDTO filtros, boolean isAudiencia,
			Boolean isActaConstancia, PlantillaCarta plantilla, String asunto, String idUsuario, String codigoPlantilla, Long idServicio) {
		
		String asuntoPorServicio = asunto;
		Map<String, Object> args = mapearArgumentosEnFiltros(filtros);
		Map<String, String> filtrosVariablesDinamicas;
		if (filtros == null)
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR485.toString()));
		for (Long persona : personasNotificar) {
			args.put(UtilDominios.TIPO_DESTINATARIO, 0);
			if (isAudiencia) {
				filtrosVariablesDinamicas = variablesDinamicasCartas.getInformacionPlantilla(
						plantilla.getIdPlantillaCarta(), filtros.getIdCaso(), persona, filtros.getIdAudiencia(), args);
			} else {
				filtrosVariablesDinamicas = variablesDinamicasCartas.getInformacionPlantilla(
						plantilla.getIdPlantillaCarta(), filtros.getIdCaso(), persona, 0L, args);
			}
			filtrosVariablesDinamicas.putAll(filtrosVariablesEstaticas);

			
			if(asuntoPorServicio == null) {
				asuntoPorServicio = generarAsuntoCarta(codigoPlantilla, filtros.getIdAudiencia(), filtros.getIdCaso(), idServicio, persona);
			}
			
			cartas.addAll(crearCartaPersonaPorCorreo(filtros, persona, plantilla.getPlantillaHtml(),
					filtrosVariablesDinamicas, isActaConstancia, plantilla.getTipoServicio(), asuntoPorServicio, idUsuario));
		}
	}

	/**
	 * Método para la generación de las cartas para los invitados de una audiencia
	 * 
	 * @param invitadosNotificar
	 * @param cartas
	 * @param filtrosVariablesEstaticas
	 * @param filtros
	 * @param isAudiencia
	 * @param idPlantillaCarta
	 * @param plantillaHtml
	 */
	private void generarCartasInvitados(List<Invitado> invitadosNotificar, List<CartaPersona> cartas,
			Map<String, String> filtrosVariablesEstaticas, ParametrosGenerarCartaDTO filtros, boolean isAudiencia,
			Long idPlantillaCarta, String plantillaHtml, String asunto) {
		Map<String, Object> args = mapearArgumentosEnFiltros(filtros);
		Map<String, String> filtrosVariablesDinamicas;
		if (filtros == null)
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR485.toString()));
		for (Invitado invitado : invitadosNotificar) {
			args.put(UtilDominios.TIPO_DESTINATARIO, 1);
			if (isAudiencia) {
				filtrosVariablesDinamicas = variablesDinamicasCartas.getInformacionPlantilla(idPlantillaCarta,
						filtros.getIdCaso(), invitado.getIdInvitado(), filtros.getIdAudiencia(), args);
			} else {
				filtrosVariablesDinamicas = variablesDinamicasCartas.getInformacionPlantilla(idPlantillaCarta,
						filtros.getIdCaso(), invitado.getIdInvitado(), 0L, args);
			}
			filtrosVariablesDinamicas.putAll(filtrosVariablesEstaticas);
			String cartaGeneradaConTags = reemplazarTags(plantillaHtml, filtrosVariablesDinamicas, null, null);
			cartas.add(crearCartaInvitado(cartaGeneradaConTags, filtros, invitado, asunto));
		}
	}

	/**
	 * 
	 * 
	 * @param filtros
	 * @return
	 */
	private String obtenerNombresPersonasSeleccionadas(ParametrosGenerarCartaDTO filtros) {
		StringBuilder nombresPersonasSeleccionados = new StringBuilder();
		if (filtros.getListaIdNotificados() != null) {
			for (long iterator : filtros.getListaIdNotificados()) {
				Persona personaActual = manejadorPersona.buscar(iterator);
				if (personaActual != null) {
					RolPersonaCaso rolPersonaCasoActual = manejadorRolPersonaCaso
							.consultarPersonaAsignadaCaso(personaActual.getIdPersona(), filtros.getIdCaso());
					if (rolPersonaCasoActual != null && rolPersonaCasoActual.getRol() != null
							&& !(rolPersonaCasoActual.getRol().getNombre()
									.equals(UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE))
							&& !(rolPersonaCasoActual.getRol().getNombre()
									.equals(UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO))) {
						nombresPersonasSeleccionados.append("<p>").append(personaActual.getNombreCompleto())
								.append("</p>");
					}
				}
			}
		}
		return nombresPersonasSeleccionados.toString();
	}

	/**
	 * Metodo encargado de validar dentro de las banderas de la plantilla a quien o
	 * quienes se les genera las cartas: Invitados, partes o audiencia.
	 * 
	 * @param valorPlantillaCartas
	 * @param dirigido
	 * @return Boolean
	 */
	private Boolean esDirigido(List<ValorPlantillaCarta> valorPlantillaCartas, String dirigido) {
		Boolean validacion = false;
		for (ValorPlantillaCarta vpc : valorPlantillaCartas) {
			if (dirigido.equals(vpc.getNombreValorDinamico())) {
				validacion = true;
				break;
			}
		}
		return validacion;
	}

	private String obtenerCorreoPersona(List<CorreoElectronico> correos) {
		String correoPrincipal = null;
		List<CorreoElectronico> correoList = (correos == null) ? new ArrayList<CorreoElectronico>() : correos;
		for (CorreoElectronico correo : correoList) {
			if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(correo.getEstadoRegistro())
					&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL.equals(correo.getTipo())) {
				correoPrincipal = correo.getDireccion();
				break;
			}
		}

		return correoPrincipal;
	}

	/**
	 * Metodo encargado de generar Carta Persona por cada correo electronico y
	 * dependiendo de los parametros enviados.
	 * 
	 * @return CartaPersona
	 */
	private List<CartaPersona> crearCartaPersonaPorCorreo(ParametrosGenerarCartaDTO parametrosGenerarCartaDTO,
			Long idPersona, String plantillaHtml, Map<String, String> filtrosVariables, Boolean isActaConstancia,
			String tipoServicio, String asunto, String idUsuario) {
		List<CartaPersona> cartasGeneradas = new ArrayList<>();
		List<String> correos = manejadorCorreoElectronicoRolPersonaCaso
				.obtenerCorreosPersona(new RolPersonaCasoPK(idPersona, parametrosGenerarCartaDTO.getIdCaso(), null));

		boolean envioCertimail = isEnvioCertimail(parametrosGenerarCartaDTO.getIndicadorNotificacion());
		CartaPersona cartaGenerada = new CartaPersona();
		cartaGenerada.setIdPlantillaCarta(Long.valueOf(parametrosGenerarCartaDTO.getIdPlantilla()));
		cartaGenerada.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		cartaGenerada.setIdCaso(parametrosGenerarCartaDTO.getIdCaso());
		cartaGenerada.setIdPersona(idPersona);
		cartaGenerada.setIdAudiencia(parametrosGenerarCartaDTO.getIdAudiencia());
		cartaGenerada.setFechaGeneracion(new Date());
		if (idUsuario != null) {
			cartaGenerada
					.setIdUsuarioModificacion(manejadorPersona.buscar(Long.parseLong(idUsuario)).getNumeroDocumento());
		}

		if (correos == null || correos.isEmpty()) {
			cartasGeneradas.addAll(generarCartasUbicacion(cartaGenerada, idPersona, null, envioCertimail, plantillaHtml,
					isActaConstancia, parametrosGenerarCartaDTO.isEnvioPorLotes(), idUsuario));
		}
		for (String correo : correos) {

			if (parametrosGenerarCartaDTO.getIndicadorEnvio() != null) {
				if (parametrosGenerarCartaDTO.getIndicadorEnvio().equals(UtilConstantes.NO)) {
					cartasGeneradas.addAll(generarCartasUbicacion(cartaGenerada, idPersona, correo, envioCertimail,
							plantillaHtml, isActaConstancia, parametrosGenerarCartaDTO.isEnvioPorLotes(), idUsuario));
				} else {
					cartaGenerada.setContenido(UtilConstantes.CADENA_VACIA);
					generarCartaConCorreo(cartaGenerada, correo, envioCertimail);
					reemplazarTagsPersona(filtrosVariables, UtilConstantes.CADENA_VACIA, UtilConstantes.CADENA_VACIA,
							UtilConstantes.CADENA_VACIA, cartaGenerada.getIdCartaPersona());
					String asuntoCarta = reemplazarTag(asunto, UtilConstantes.TAG_ASUNTO_ID_CARTA,
							cartaGenerada.getIdCartaPersona().toString());
					cartaGenerada.setAsunto(asuntoCarta.length() > UtilConstantes.LIMITE_ASUNTO_CORREO
							? asuntoCarta.substring(0, UtilConstantes.LIMITE_ASUNTO_CORREO)
							: asuntoCarta);
					cartaGenerada.setContenido(reemplazarTags(plantillaHtml, filtrosVariables,
							parametrosGenerarCartaDTO.getIdCaso(), idUsuario));
					actualizar(cartaGenerada);
					cartasGeneradas.add(cartaGenerada);
					if (parametrosGenerarCartaDTO.isEnvioPorLotes())
						enviarCarta(cartaGenerada, false, true);

				}

			} else if (correo != null && !correo.isEmpty()) {
				cartaGenerada
						.setDireccionCorrespondencia(obtenerUbicacionPersona(cartaGenerada.getIdCaso(), idPersona));
				cartaGenerada.setContenido(reemplazarTags(plantillaHtml, filtrosVariables, null, idUsuario));
				generarCartaConCorreo(cartaGenerada, correo, envioCertimail);
				String asuntoCarta = reemplazarTag(asunto, UtilConstantes.TAG_ASUNTO_ID_CARTA,
						cartaGenerada.getIdCartaPersona().toString());
				cartaGenerada.setAsunto(asuntoCarta.length() > UtilConstantes.LIMITE_ASUNTO_CORREO
						? asuntoCarta.substring(0, UtilConstantes.LIMITE_ASUNTO_CORREO)
						: asuntoCarta);
				manejadorCartaPersona.actualizar(cartaGenerada);
				cartasGeneradas.add(cartaGenerada);
			}
		}
		return cartasGeneradas;
	}

	/**
	 * Obtiene la direcciÃ³n de correo electrÃ³nico de la lista de correos de la
	 * persona a la cual sera enviada la carta
	 * 
	 * Si tiene correo principal se retorna, de lo contrario se busca un correo
	 * electrÃ³nico activo
	 * 
	 * @param correoList
	 */
	private String obtenerDireccionCorreoElectronicoEnvio(Long idCaso, Long idPersona) {
		String direccionCorreoElectronico = null;
		List<String> correos = manejadorCorreoElectronicoRolPersonaCaso
				.obtenerCorreosPersona(new RolPersonaCasoPK(idPersona, idCaso, null));
		if (!correos.isEmpty())
			direccionCorreoElectronico = correos.get(0);

		return direccionCorreoElectronico;
	}

	private boolean isEnvioCertimail(String indicadorNotificacion) {
		return indicadorNotificacion != null && indicadorNotificacion.equals(UtilConstantes.SI) ? true : false;
	}

	private String obtenerUbicacionPersona(Long idCaso, Long idPersona) {
		String ubicacionPrincipal = UtilConstantes.CADENA_VACIA;
		List<Ubicacion> ubicacionList = manejadorUbicacion.consultarDireccionesCaso(idCaso, idPersona);
		for (Ubicacion ubicacion : ubicacionList)
			if (ubicacion.getDireccion() != null)
				ubicacionPrincipal = ubicacion.getDireccion().concat(", " + ubicacionPrincipal);

		return ubicacionPrincipal;
	}

	/**
	 * Método para generar una carta por cada una de las ubicaciones que tenga la
	 * persona que se pasa como parametro
	 * 
	 * @param cartaGenerada
	 * @param incluyeCorreo
	 * @param persona
	 * @param correoPrincipal
	 * @param envioCertimail
	 * @return
	 */
	private List<CartaPersona> generarCartasUbicacion(CartaPersona cartaGenerada, Long idPersona,
			String correoPrincipal, boolean envioCertimail, String plantillaHtml, Boolean isActaConstancia,
			boolean envioPorLotes, String idUsuario) {
		List<CartaPersona> cartasGeneradas = new ArrayList<>();
		Map<String, String> filtrosVariables = variablesDinamicasCartas.getInformacionPlantilla(
				cartaGenerada.getIdPlantillaCarta(), cartaGenerada.getIdCaso(), idPersona,
				cartaGenerada.getIdAudiencia(), null);
		filtrosVariables.putAll(variablesEstaticasCarta.getInformacionEstaticaPlantilla(cartaGenerada.getIdCaso()));

		if (isActaConstancia!= null && isActaConstancia) {
			cartaGenerada.setDireccionCorrespondencia(UtilConstantes.TEXTO_CARTA_ACTA_CONSTANCIA);
			cartaGenerada.setContenido("");
			camposCartaCorreo(false, cartaGenerada, null, false, envioPorLotes);
			reemplazarTagsPersona(filtrosVariables, "", "", "", cartaGenerada.getIdCartaPersona());
			cartaGenerada.setContenido(
					reemplazarTags(plantillaHtml, filtrosVariables, cartaGenerada.getIdCaso(), idUsuario));
			actualizar(cartaGenerada);
			cartasGeneradas.add(cartaGenerada);
		} else {
			List<Ubicacion> ubicacionList = filtrarDirecciones(cartaGenerada.getIdCaso(), idPersona);
			for (Ubicacion ubicacion : ubicacionList) {
				CartaPersona temp = new CartaPersonaDTO().transformarEntidadSinDependencias(cartaGenerada);
				String ciudad = "";
				if (ubicacion.getZonaGeografica() != null) {
					ciudad = ubicacion.getZonaGeografica().getNombre();
				} else if (ubicacion.getIdZonaGeografica() != null) {
					ZonaGeografica zonaGeografica = zonaGeograficaFacade.buscar(ubicacion.getIdZonaGeografica(),
							LockModeType.NONE);
					ciudad = zonaGeografica.getNombre();
				}
				String direccion = ubicacion.getDireccion() != null ? ubicacion.getDireccion() : "";
				temp.setDireccionCorrespondencia(direccion);
				temp.setCiudadCorrespondencia(ciudad);
				List<String> tipos = new ArrayList<>();
				tipos.add(UtilDominios.TIPO_TELEFONO_CELULAR);
				tipos.add(UtilDominios.TIPO_TELEFONO_FIJO);
				List<Telefono> tel = manejadorTelefono.consultarPorTipoYPersona(tipos, idPersona);
				temp.setTelefonosContacto(tel.isEmpty() ? "" : tel.get(0).getNumero());
				temp.setContenido("");
				camposCartaCorreo(false, temp, correoPrincipal, envioCertimail, envioPorLotes);
				reemplazarTagsPersona(filtrosVariables, direccion, ciudad, temp.getTelefonosContacto(),
						temp.getIdCartaPersona());
				temp.setContenido(
						reemplazarTags(plantillaHtml, filtrosVariables, cartaGenerada.getIdCaso(), idUsuario));
				actualizar(temp);
				cartasGeneradas.add(temp);
			}
		}

		return cartasGeneradas;
	}

	private void camposCartaCorreo(boolean incluyeCorreo, CartaPersona temp, String correoPrincipal,
			boolean envioCertimail, boolean envioPorLotes) {
		if (incluyeCorreo) {
			generarCartaConCorreo(temp, correoPrincipal, envioCertimail);
		} else {
			if (envioPorLotes)
				temp.setEstadoCarta(UtilDominios.ESTADO_CARTA_IMPRESA);
			else
				temp.setEstadoCarta(UtilDominios.ESTADO_CARTA_PENDIENTE_IMPRESION);
			manejadorCartaPersona.crear(temp);
		}
	}

	/**
	 * Método que filtra los registros de ubicaciones de la persona para obtener las
	 * que se encuentren activas
	 * 
	 * @param direcciones
	 * @return
	 */
	private List<Ubicacion> filtrarDirecciones(Long idCaso, Long idPersona) {
		List<Ubicacion> filtradas = manejadorUbicacion.consultarDireccionesCaso(idCaso, idPersona);

		if (filtradas.isEmpty()) {
			filtradas = new ArrayList<Ubicacion>();
			filtradas.add(new Ubicacion());

		}
		return filtradas;
	}

	/**
	 * Método para reemplazar los datos del usuario en la carta a generar
	 * 
	 * @param filtrosVariables
	 * @param direccion
	 * @param ciudad
	 * @param telefono
	 */
	private void reemplazarTagsPersona(Map<String, String> filtrosVariables, String direccion, String ciudad,
			String telefono, Long codigoRadicado) {
		filtrosVariables.put(UtilConstantes.VALOR_PLANTILLA_DIRECCION_ENVIO,
				direccion.isEmpty() ? direccion : direccion.concat(SALTO_LINEA));
		filtrosVariables.put(UtilConstantes.VALOR_PLANTILLA_CIUDAD_ENVIO,
				ciudad.isEmpty() ? ciudad : ciudad.concat(SALTO_LINEA));
		filtrosVariables.put(UtilConstantes.VALOR_PLANTILLA_TELEFONO_ENVIO,
				telefono.isEmpty() ? telefono : telefono.concat(SALTO_LINEA));
		filtrosVariables.put(UtilConstantes.VALOR_PLANTILLA_RADICADO_CARTA,
				codigoRadicado != null ? String.valueOf(codigoRadicado) : "");
	}

	/**
	 * método que incluye los datos de correo para una carta generada y la crea
	 * 
	 * @param cartaGenerada
	 * @param correoPrincipal
	 * @param envioCertimail
	 */
	private void generarCartaConCorreo(CartaPersona cartaGenerada, String correoPrincipal, boolean envioCertimail) {
		if(correoPrincipal != null) {
			cartaGenerada.setCorreoElectronico(correoPrincipal);
		}		
		cartaGenerada.setEstadoCarta(UtilDominios.ESTADO_CARTA_GENERADA);
		cartaGenerada.setEnvioCertimail(envioCertimail);
		manejadorCartaPersona.crear(cartaGenerada);
	}

	/**
	 * Metodo encargado de crear Carta Persona para Inivtado.
	 * 
	 * @param cartaGeneradaConTags
	 * @param parametrosGenerarCartaDTO
	 * @param invitado
	 * @return CartaPersona
	 */
	private CartaPersona crearCartaInvitado(String cartaGeneradaConTags,
			ParametrosGenerarCartaDTO parametrosGenerarCartaDTO, Invitado invitado, String asunto) {
		CartaPersona cartaPersona = new CartaPersona();
		cartaPersona.setContenido(cartaGeneradaConTags);
		cartaPersona.setIdPlantillaCarta(Long.valueOf(parametrosGenerarCartaDTO.getIdPlantilla()));
		cartaPersona.setEnvioCertimail(
				parametrosGenerarCartaDTO.getIndicadorNotificacion().equals(UtilConstantes.SI) ? true : false);
		cartaPersona.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		cartaPersona.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
		cartaPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		cartaPersona.setIdCaso(parametrosGenerarCartaDTO.getIdCaso());
		cartaPersona.setIdInvitado(invitado.getIdInvitado());
		cartaPersona.setCorreoElectronico(invitado.getCorreo());
		cartaPersona.setIdAudiencia(parametrosGenerarCartaDTO.getIdAudiencia());
		cartaPersona.setEstadoCarta(UtilDominios.ESTADO_CARTA_GENERADA);
		cartaPersona.setDireccionCorrespondencia(invitado.getDireccion().concat(" (" + invitado.getCiudad() + ")"));
		manejadorCartaPersona.crear(cartaPersona);

		String asuntoCarta = reemplazarTag(asunto, UtilConstantes.TAG_ASUNTO_ID_CARTA,
				cartaPersona.getIdCartaPersona().toString());
		cartaPersona.setAsunto(asuntoCarta.length() > UtilConstantes.LIMITE_ASUNTO_CORREO
				? asuntoCarta.substring(0, UtilConstantes.LIMITE_ASUNTO_CORREO)
				: asuntoCarta);
		manejadorCartaPersona.actualizar(cartaPersona);

		return cartaPersona;
	}

	@Override
	public List<CartaPersona> generarCarta(ParametrosGenerarCartaDTO filtros, String idUsuario) {
		PlantillaCarta plantillaCarta;
		Caso casoConvenio;
		if (filtros.getCaso() != null)
			casoConvenio = filtros.getCaso();
		else
			casoConvenio = manejadorCaso.obtenerDatosBasicosCaso(filtros.getIdCaso());

		if (filtros.getPlantillaCarta() != null) {
			plantillaCarta = filtros.getPlantillaCarta();
		} else {
			plantillaCarta = manejadorPlantillaCarta.buscar(Long.valueOf(filtros.getIdPlantilla()), LockModeType.NONE);
			// Si el caso es de convenio y se tiene plantilla se genera la carta del
			// convenio
			if (casoConvenio.getIdConvenio() != null
					&& UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO.equals(casoConvenio.getIdServicio())) {
				PlantillaCartaDTO plantillaBusqueda = new PlantillaCartaDTO();
				plantillaBusqueda.setIdConvenio(casoConvenio.getIdConvenio());
				plantillaBusqueda.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				List<PlantillaCarta> listaPlantillasBusqueda = manejadorPlantillaCarta
						.consultarPlantillaCartaFiltros(plantillaBusqueda, LockModeType.NONE);
				if (!listaPlantillasBusqueda.isEmpty()) {
					filtros.setIdPlantilla(listaPlantillasBusqueda.get(0).getIdPlantillaCarta().toString());
				}
			}
		}		

		// encargado de realizar validaciones previas por el tipo de
		// carta a generar
		List<Audiencia> audiencias = new ArrayList<Audiencia>();
		if (!filtros.isEnvioPorLotes())
			audiencias = manejadorAudiencia.consultarAudienciasCasoPorTipoYEstado(filtros.getIdCaso(), null,
					UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);

		// Si el caso es de conciliación se valida la existencia de una audiencia
		// pendiente
		if (((UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO.equals(casoConvenio.getIdServicio())
				|| UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA.equals(casoConvenio.getIdServicio())
				|| UtilConstantes.ID_SERVICIO_CONCILIACION_TRAMITE_ORDINARIO.equals(casoConvenio.getIdServicio())
				|| UtilConstantes.ID_SERVICIO_EQUIDAD.equals(casoConvenio.getIdServicio())))
				&& (UtilDominios.NOMBRE_PLANTILLA_CARTA_PRIMERA_AUDIENCIA.equals(plantillaCarta.getNombre())
						|| UtilDominios.NOMBRE_PLANTILLA_CARTA_PRIMERA_AUDIENCIA_EQUIDAD_CONVOCADO.equals(plantillaCarta.getNombre())
						|| UtilDominios.NOMBRE_PLANTILLA_CARTA_PRIMERA_AUDIENCIA_EQUIDAD_SOLICITANTE.equals(plantillaCarta.getNombre())
						|| UtilDominios.NOMBRE_PLANTILLA_CARTA_AUDIENCIAS_DIFERENTES_PRIMERA
								.equals(plantillaCarta.getNombre()))) {
			// Validación Audiencia
			if ((filtros.isEnvioPorLotes() && filtros.getIdAudiencia() == null)
					|| (!filtros.isEnvioPorLotes() && audiencias.isEmpty())) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR486.toString()));
			}
			// Validación conciliadores nombrados
			boolean primeraAudiencia = (UtilDominios.NOMBRE_PLANTILLA_CARTA_PRIMERA_AUDIENCIA
					.equals(plantillaCarta.getNombre())
					|| UtilDominios.NOMBRE_PLANTILLA_CARTA_PRIMERA_AUDIENCIA_EQUIDAD_CONVOCADO.equals(plantillaCarta.getNombre())
					|| UtilDominios.NOMBRE_PLANTILLA_CARTA_PRIMERA_AUDIENCIA_EQUIDAD_SOLICITANTE.equals(plantillaCarta.getNombre()));
			
			RolPersonaCaso conciliador = manejadorRolPersonaCaso.consultarPersonaPorRolCaso(casoConvenio.getIdCaso(),
					UtilDominios.ROL_PERSONA_CONCILIADOR);
			if (conciliador != null
					&& conciliador.getRolPersonaCasoPK().getIdRol().toString().equals(UtilConstantes.ID_ROL_CONCILIADOR)
					&& conciliador.getEstado().equals(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR)) {
				if (filtros.isEnvioPorLotes()) {
					throw new SIMASCNegocioExcepcion(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO278.toString()) + "IdCaso: "
									+ casoConvenio.getIdCaso());
				} else {
					throw new SIMASCNegocioExcepcion(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO278.toString()));
				}
			}

			if (!primeraAudiencia && (conciliador == null || conciliador.getCaso().getEstadoCaso()
					.equals(UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION))) {
				if (filtros.isEnvioPorLotes()) {
					throw new SIMASCNegocioExcepcion(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO278.toString()) + "IdCaso: "
									+ casoConvenio.getIdCaso());
				} else {
					throw new SIMASCNegocioExcepcion(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO278.toString()));
				}
			}
		}
		// Si el caso es de arbitraje se valida los tipos de audiencia según la
		// plantilla
		else {
			if (UtilDominios.NOMBRE_PLANTILLA_CARTA_PRIMERA_AUDIENCIA.equals(plantillaCarta.getNombre())) {
				boolean existeAudiencia = false;
				for (Audiencia au : audiencias) {
					existeAudiencia = validarTipoAudienciaServicio(au.getTipoAudiencia());
					if (existeAudiencia)
						break;
				}
				if (!existeAudiencia) {
					throw new SIMASCNegocioExcepcion(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR486.toString()));
				}
			} else if (UtilDominios.NOMBRE_PLANTILLA_CARTA_AUDIENCIAS_DIFERENTES_PRIMERA
					.equals(plantillaCarta.getNombre())) {
				if (audiencias.isEmpty()) {
					throw new SIMASCNegocioExcepcion(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR486.toString()));
				}
			}
		}
		return generarCartas(filtros, false, idUsuario);
	}

	private boolean validarTipoAudienciaServicio(String tipoAudiencia) {
		return UtilDominios.TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_DE_DESIGNACION.equalsIgnoreCase(tipoAudiencia)
				|| UtilDominios.TIPO_AUDIENCIA_PREARBITRAL_DESIGNACION_POR_PARTES.equalsIgnoreCase(tipoAudiencia);

	}

	private PlantillaCarta consultarPlantillaCitacion(Long idCaso) {

		// Plantilla a generar
		PlantillaCarta plantilla = new PlantillaCarta();
		List<String> estadosAudiencia = new ArrayList<String>();
		estadosAudiencia.add(UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		estadosAudiencia.add(UtilDominios.ESTADO_AUDIENCIA_REALIZADA);
		// Si el caso solo tiene una audiencia y es la primera se debe utilizar la
		// plantilla de citación a primera audiencia,
		if (manejadorAudiencia.obtenerNumeroAudienciasCaso(idCaso, estadosAudiencia).intValue() == UtilConstantes.UNO) {
			plantilla = manejadorPlantillaCarta.consultarPlantillaPorNombre(idCaso,
					UtilDominios.NOMBRE_PLANTILLA_CARTA_PRIMERA_AUDIENCIA);
		}
		// en caso contrario se utiliza la plantilla de citacion a audiencia diferente a
		// la primera
		else {
			plantilla = manejadorPlantillaCarta.consultarPlantillaPorNombre(idCaso,
					UtilDominios.NOMBRE_PLANTILLA_CARTA_AUDIENCIAS_DIFERENTES_PRIMERA);
		}
		return plantilla;
	}

	public List<LotesCartasDTO> generarLotesCartas(List<LotesCartasDTO> lotesCartas, ContextoDeSesion usuario) {

		Future<Integer> future = generarLotes(lotesCartas, usuario);

		return null;
	}

	public Future<Integer> generarLotes(final List<LotesCartasDTO> lotesCartas, final ContextoDeSesion usuario) {
		final String pathMerge = UUID.randomUUID().toString() + UtilConstantes.CARACTER_PUNTO
				+ UtilConstantes.EXTENSION_ARCHIVO_PDF;
		ParametrosGenerales parametroRutaTranversal = manejadorParametrosGenerales
				.buscar(UtilParamGenerales.GESTOR_DOCUMENTAL_RUTA_TRANSVERSALES, LockModeType.NONE);
		final String path = parametroRutaTranversal.getValorTexto() + File.separator;
		LoteGenerado loteGenerado = new LoteGenerado();
		loteGenerado.setFechaGeneracion(new Date());
		loteGenerado.setIdPersona(Long.valueOf(usuario.getIdUsuario()));
		loteGenerado.setNombreDocumento(pathMerge);
		loteGenerado.setEstadoGeneracion(UtilDominios.ESTADO_LOTE_INICIADO);

		loteGeneradoFacade.crearSinAtributosDeAuditoria(loteGenerado);

		final Long idLote = loteGenerado.getIdLote();

		executor = Executors.newSingleThreadExecutor();

		return executor.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				List<CartaPersona> cartasGeneradas = new ArrayList<>();
				Caso casoBusqueda = null;
				PlantillaCarta plantilla = null;
				AudienciaDTO audienciaPendiente = null;
				int i = 0;
				logger.info("Inicia Generación Masiva: " + lotesCartas.size() + " registros");
				try {
					for (LotesCartasDTO lote : lotesCartas) {
						i++;
						logger.info("Reg N° " + i + " Generando caso: " + lote.getCodigoCaso()
								+ " - Id persona: " + lote.getIdPersona());
						// si el caso es de convenio y se tiene un tipo de
						// plantilla, la carta generada debe ser la que
						// pertenezca al convenio
						if (casoBusqueda == null || !casoBusqueda.getIdCaso().equals(lote.getCodigoCaso())) {
							casoBusqueda = manejadorCaso.obtenerDatosBasicosCaso(lote.getCodigoCaso());
							if (casoBusqueda.getIdConvenio() != null && UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO
									.equals(casoBusqueda.getIdServicio())) {
								plantilla = buscarPlantillaConvenio(casoBusqueda.getIdConvenio(), lote.getCodigoCaso());
							} else {
								plantilla = consultarPlantillaCitacion(lote.getCodigoCaso());
							}
							audienciaPendiente = manejadorAudiencia.consultarUltimaAudienciaEstadoCaso(
									lote.getCodigoCaso(), UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
						}

						boolean generacion = true;
						if (lote.isEnviarCorreo())
							generacion = manejadorCorreoElectronico.tieneCorreosAsociados(lote.getIdPersona());
						if (plantilla != null && generacion)
							generacionCartaLote(plantilla, casoBusqueda, lote, usuario, audienciaPendiente,
									cartasGeneradas);
						else {
							PersonaLote personaLote = new PersonaLote();
							PersonaLotePK pk = new PersonaLotePK();
							pk.setIdCaso(lote.getCodigoCaso());
							pk.setIdLote(idLote);
							pk.setIdPersona(lote.getIdPersona());
							personaLote.setPersonaLotePK(pk);
							personaLote.setIdAudiencia(lote.getIdAudiencia());
							personaLote.setCorreo(lote.getCorreo());
							personaLote.setNombrePersona(lote.getNombreParte());
							personaLote.setRol(lote.getRol());
							personaLoteFacade.crearSinAtributosDeAuditoria(personaLote);
						}
					}
					// Generación del PDF del lote de cartas
					generarPDFLoteCartas(cartasGeneradas, path, pathMerge);
				} catch (Exception e) {
					logger.error("Error: ", e);
					casoTramiteOrdinarioFacade.actualizarFalloLotes(idLote);
					File documentoFinal = new File(path + pathMerge);
					documentoFinal.delete();
					if (e instanceof SIMASCNegocioExcepcion)
						throw e;
					else
						throw new SIMASCNegocioExcepcion(
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR927.toString()));
				}

				LoteGenerado lote = loteGeneradoFacade.buscar(idLote);
				lote.setEstadoGeneracion(UtilDominios.ESTADO_LOTE_FINALIZADO);
				loteGeneradoFacade.actualizarSinAtributosDeAuditoria(lote);
				return 0;
			}
		});
	}

	private void generacionCartaLote(PlantillaCarta plantilla, Caso casoBusqueda, LotesCartasDTO lote,
			ContextoDeSesion usuario, AudienciaDTO audienciaPendiente, List<CartaPersona> cartasGeneradas) {
		ParametrosGenerarCartaDTO parametrosCarta = new ParametrosGenerarCartaDTO();
		parametrosCarta.setPlantillaCarta(plantilla);
		parametrosCarta.setIdPlantilla(plantilla.getIdPlantillaCarta().toString());
		parametrosCarta.setCaso(casoBusqueda);
		parametrosCarta.setIdCaso(lote.getCodigoCaso());
		parametrosCarta.setEnvioPorLotes(true);
		parametrosCarta.setListaIdNotificados(Arrays.asList(lote.getIdPersona()));
		parametrosCarta.setIdAudiencia(lote.getIdAudiencia());
		if (lote.isEnviarCorreo()) {
			parametrosCarta.setIndicadorEnvio(UtilConstantes.SI);
			parametrosCarta.setIndicadorNotificacion(UtilConstantes.SI);
			generarCarta(parametrosCarta, usuario.getIdUsuario());
			String observacion = String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO453.toString()),
					Arrays.asList(
							Long.toString(audienciaPendiente != null && audienciaPendiente.getConsecutivo() != null
									? audienciaPendiente.getConsecutivo()
									: 1L),
							lote.getNombreParte(), lote.getCorreo()));
			Caso caso = new Caso();
			caso.setIdCaso(lote.getCodigoCaso());
			eventoFacade.registrarEvento(caso, UtilDominios.TIPO_EVENTO_NOTIFICACION_CORREO_ELECTRONICO_CERTIFICADO,
					observacion, usuario.getNombreUsuario(), new Date(), UtilDominios.ESTADO_REGISTRO_ACTIVO);
		} else {
			parametrosCarta.setIndicadorEnvio(UtilConstantes.NO);
			parametrosCarta.setIndicadorNotificacion(UtilConstantes.NO);
			cartasGeneradas.addAll(generarCarta(parametrosCarta, usuario.getIdUsuario()));
		}

	}

	private PlantillaCarta buscarPlantillaConvenio(Long idConvenio, Long idCaso) {
		PlantillaCarta plantilla = null;
		PlantillaCartaDTO plantillaBusqueda = new PlantillaCartaDTO();
		plantillaBusqueda.setIdConvenio(idConvenio);
		plantillaBusqueda.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		List<PlantillaCarta> listaPlantillasBusqueda = manejadorPlantillaCarta
				.consultarPlantillaCartaFiltros(plantillaBusqueda, LockModeType.NONE);
		if (!listaPlantillasBusqueda.isEmpty()) {
			plantilla = listaPlantillasBusqueda.get(0);
		} else {
			plantilla = consultarPlantillaCitacion(idCaso);
		}
		return plantilla;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICartaPersonaFacade#
	 * generarCartaAudiencias(com.ccb.simasc.transversal.dto.formularios.
	 * ParametrosGenerarCartaDTO)
	 */
	@Override
	public List<CartaPersona> generarCartaAudiencias(ParametrosGenerarCartaDTO filtros) {
		return generarCartas(filtros, false, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICartaPersonaFacade#
	 * actualizarCarta(com.ccb.simasc.transversal.entidades.CartaPersona,
	 * java.lang.Long)
	 */
	@Override
	public CartaPersonaDTO actualizarCarta(CartaPersona carta, Long idCaso) {
		ParametrosGenerales dest = parametrosGeneralesFacade.buscar(UtilParamGenerales.GESTOR_DOCUMENTAL_RUTA_GESTOR);
		ParametrosGenerales htmlPlantilla = parametrosGeneralesFacade
				.buscar(UtilParamGenerales.GESTOR_DOCUMENTAL_PLANTILLA_TXT);
		ParametrosGenerales parametroPrefijoCaso = parametrosGeneralesFacade
				.buscar(UtilParamGenerales.GESTOR_DOCUMENTAL_PREFIJO_DIR_CASO);

		Caso caso = new Caso();
		caso.setIdCaso(idCaso);
		String asunto = carta.getAsunto();
		if (asunto == null)
			asunto = generarAsunto(carta);
		
		String ruta = dest.getValorTexto() + asunto;
		if (ruta.length() > 246) {
			ruta = ruta.substring(0, 246);
			int tamanioNombre = 247 - dest.getValorTexto().length();
			asunto = asunto.substring(dest.getValorTexto().length() - 1, tamanioNombre);
		}
		GeneradorDocumentos.createPdf(carta.getContenido(), asunto, htmlPlantilla.getValorTexto(),
				dest.getValorTexto());
		InputStream documento = null;
		try {
			documento = new FileInputStream(ruta + ".pdf");
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		}
		Documento documentoN = new Documento();
		String nombreDocumento = asunto;

		documentoN.setIdCaso(carta.getIdCaso());
		documentoN.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		documentoN.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
		documentoN.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		documentoN.setNombre(nombreDocumento);
		documentoN.setNuevo(true);
		documentoN.setPublicado(false);
		documentoN.setTipoArchivo("PDF");
		documentoN.setTipoDocumento(UtilDominios.TIPO_DOCUMENTO_DIG_CARTA);
		documentoN.setUrl(dest.getValorTexto().concat(parametroPrefijoCaso.getValorTexto()).concat(idCaso.toString())
				.concat(File.separator).concat(asunto).concat(".pdf"));
		documentoN.setFechaAsignacion(new Date());
		documentoN.setFechaRadicacion(new Date());

		Carpeta carpeta = manejadorCarpeta.obtenerCarpetaCuadernoCaso(documentoN.getIdCaso(), UtilDominios.ID_CUADERNO_COMUNICACIONES_SISTEMA);
		if (carpeta != null) {
			documentoN.setIdCarpeta(carpeta.getIdCarpeta());
		}

		documentoN = manejadorDocumento.actualizarDocumentoDigitalizado(documentoN);

		try {
			gestorDocumentalFacade.subirDocumento(idCaso, carta.getIdCartaPersona(), documento, nombreDocumento,
					UtilConstantes.EXTENSION_ARCHIVO_PDF, false, null);
		} catch (SIMASCNegocioExcepcion e) {
			logger.error(e.getCause());
		} finally {
			String file = dest.getValorTexto().concat(nombreDocumento);
			file = file.concat(".pdf");
			File f = new File(file);
			try {
				f.delete();
				if (documento != null)
					documento.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}

		eventoFacade.registrarEvento(caso, UtilDominios.TIPO_EVENTO_EDICION_CARTA, asunto,
				UtilConstantes.USUARIO_DEFECTO_SIMASC, new Timestamp(new Date().getTime()),
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		carta.setIdDocumento(documentoN.getIdDocumento());
		actualizar(carta);

		return transformarSinDependencias(carta);

	}

	public void enviarCarta(CartaPersona carta, boolean indicadorImpresion, boolean indicadorNotificacion) {

		if (indicadorNotificacion) {
			CartaPersona cartaPersona = manejadorCartaPersona.buscar(carta.getIdCartaPersona());
			enviarCartaCorreo(cartaPersona);
			eventoCasoNotificacionArbitro(carta);
			eventoCasoNotificacionSecretario(carta);
		} else if (indicadorImpresion) {
			carta.setEstadoCarta(UtilDominios.ESTADO_CARTA_IMPRESA);
			actualizar(carta);

		} else {
			carta.setEstadoCarta(UtilDominios.ESTADO_CARTA_PENDIENTE_IMPRESION);
			actualizar(carta);

		}

	}

	@Override
	public void enviarCartaCorreo(CartaPersona carta) {
		ParametrosEnvioCorreoDTO parametrosEnvioCorreoDTO = new ParametrosEnvioCorreoDTO();
		parametrosEnvioCorreoDTO.setCartaPersona(transformarSinDependencias(carta));
		parametrosEnvioCorreoDTO.setAsunto(generarAsunto(carta));
		parametrosEnvioCorreoDTO.setCertificado(true);
		CorreoElectronicoDTO correo = new CorreoElectronicoDTO();
		correo.setIdPersona(carta.getIdPersona());
		correo.setDireccion(carta.getCorreoElectronico());
		correo.setIdUsuarioModificacion(carta.getIdUsuarioModificacion());
		List<CorreoElectronicoDTO> destinos = new ArrayList<CorreoElectronicoDTO>();
		destinos.add(correo);
		parametrosEnvioCorreoDTO.setIdCaso(carta.getIdCaso());
		parametrosEnvioCorreoDTO.setRolPersonaCaso(destinos);
		if (carta.getIdDocumento() != null) {
			Documento documento = documentoFacade.buscar(carta.getIdDocumento());
			DocumentoDTO documentoDTO = documentoFacade.transformarSinDependencias(documento);
			List<DocumentoDTO> documentosList = new ArrayList<DocumentoDTO>();
			documentosList.add(documentoDTO);
			parametrosEnvioCorreoDTO.setAdjuntos(documentosList);
		}
		Caso caso = casoFacade.buscar(carta.getIdCaso());

		if (carta.getIdInvitado() != null) {
			List<CorreoElectronicoDTO> idsInvitados = new ArrayList<>();
			CorreoElectronicoDTO correoDTO = new CorreoElectronicoDTO();
			correoDTO.setIdPersona(carta.getIdInvitado());
			correoDTO.setDireccion(carta.getInvitado().getCorreo());
			idsInvitados.add(correoDTO);
			parametrosEnvioCorreoDTO.setInvitados(idsInvitados);
		}

		Servicio servicio = servicioFacade.buscar(caso.getIdServicio());
		if (servicio.getTipo().equals(UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS)) {
			ParametrosGenerales parametrosGenerales = parametrosGeneralesFacade
					.buscar(UtilParamGenerales.CORREO_CONCILIACION);
			CorreoElectronicoDTO correoConciliacion = new CorreoElectronicoDTO();
			correoConciliacion.setDireccion(parametrosGenerales.getValorTexto());
			correoConciliacion.setIdPersona(Long.valueOf(appContext.getContextoSesion().getIdUsuario()));
			parametrosEnvioCorreoDTO.setRemitente(correoConciliacion);
		} else {
			List<CorreoElectronico> correoElectronicoList = new ArrayList<>();
			for (RolPersonaCaso rolPersona : caso.getRolPersonaCasoList()) {
				if (rolPersona.getRol().getNombre().equals(UtilDominios.ROL_PERSONA_ABOGADO)) {
					correoElectronicoList = rolPersona.getPersona().getCorreoElectronicoList();
				}
			}
			for (CorreoElectronico correoPrincipal : correoElectronicoList) {
				if (correoPrincipal.getTipo().equals(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL)) {
					CorreoElectronicoDTO correoDTO = correoElectronicoFacade
							.transformarConDependencias(correoPrincipal);
					parametrosEnvioCorreoDTO.setRemitente(correoDTO);
				}
			}
		}
		parametrosEnvioCorreoDTO.setTipo(UtilDominios.TIPO_PLANTILLAS_CORREO_GENERAL);
		List<String> cuerpoCorreo = new ArrayList<String>();
		cuerpoCorreo.add(carta.getContenido());
		parametrosEnvioCorreoDTO.setCuerpoCorreo(cuerpoCorreo);
		if (parametrosEnvioCorreoDTO.getRemitente() == null
				&& !servicio.getTipo().equals(UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS)) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR151.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		} else if (parametrosEnvioCorreoDTO.getRemitente() == null
				&& servicio.getTipo().equals(UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS)) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR341.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}

		boolean falloEnvio = correoRolPersonaCasoFacade.enviarCorreo(parametrosEnvioCorreoDTO);

		if (!falloEnvio) {
			carta.setEstadoCarta(UtilDominios.ESTADO_CARTA_ENVIADA);
			actualizar(carta);
		}

	}

	private void eventoCasoNotificacionArbitro(CartaPersona carta) {
		PlantillaCarta plantilla = manejadorPlantillaCarta.buscar(carta.getIdPlantillaCarta());
		if (UtilDominios.NOMBRE_PLANTILLA_CARTA_NOTIFICACION_ARBITRO.equals(plantilla.getNombre())
				|| UtilDominios.NOMBRE_PLANTILLA_CARTA_NOTIFICACION_ARBITRO_INTERNACIONAL.equals(plantilla.getNombre())
				|| UtilDominios.NOMBRE_PLANTILLA_CARTA_NOTIFICACION_ARBITRO_RECUPERACIONL
						.equals(plantilla.getNombre())) {
			RolPersonaCaso persona = manejadorRolPersonaCaso.consultarPersonaAsignadaCaso(carta.getIdPersona(),
					carta.getIdCaso());
			if (persona != null && persona.getRol() != null) {
				String nombreRol = persona.getRol().getNombre();
				if (nombreRol != null && !nombreRol.isEmpty()
						&& Arrays.asList(UtilConstantes.ROLES_ARBITROS).contains(nombreRol)) {
					// Consulta si ya existe un evento de comunicación para el
					// árbitro
					EventoRolPersonaCaso eventoComunicacionArbitro = eventoRolPersonaCasoFacade
							.consultarEventoComunicacionDesignacionArbitro(persona.getRolPersonaCasoPK().getIdRol(),
									persona.getRolPersonaCasoPK().getIdPersona(), carta.getIdCaso());

					// Valida si el árbitro ya fue comunicado
					if (eventoComunicacionArbitro != null) {
						// Si el evento ya existe lo actualiza
						eventoComunicacionArbitro.setFechaDeAsignacion(new Date());
						eventoComunicacionArbitro.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
						eventoComunicacionArbitro.setFechaUltimaModificacion(new Date());
						eventoRolPersonaCasoFacade.actualizar(eventoComunicacionArbitro);
					} else {
						// Si el evento no existe lo crea
						eventoRolPersonaCasoFacade.crearEventoRolPersonaCaso(
								UtilDominios.ESTADO_ROL_PERSONA_CASO_COMUNICADO, null, new Date(),
								UtilDominios.ESTADO_REGISTRO_ACTIVO, persona.getRolPersonaCasoPK().getIdRol(),
								persona.getRolPersonaCasoPK().getIdPersona(), carta.getIdCaso());
					}

					notificacionFacade.notificaVencimientoPronunciamientoarbitro(carta.getIdCaso(),
							persona.getRolPersonaCasoPK().getIdPersona());

				}
			}
		}
	}

	private void eventoCasoNotificacionSecretario(CartaPersona carta) {
		PlantillaCarta plantilla = manejadorPlantillaCarta.buscar(carta.getIdPlantillaCarta());
		if (UtilDominios.NOMBRE_PLANTILLA_CARTA_NOTIFICACION_SECRETARIO.equals(plantilla.getNombre())) {
			RolPersonaCaso persona = manejadorRolPersonaCaso.consultarRolPersonaCaso(carta.getIdPersona(),
					carta.getIdCaso(), UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
			if (persona != null) {
				// Consulta si ya existe un evento de comunicación para el
				// secretario
				EventoRolPersonaCaso eventoComunicacionSecretario = eventoRolPersonaCasoFacade
						.consultarEventoComunicacionDesignacionArbitro(persona.getRolPersonaCasoPK().getIdRol(),
								persona.getRolPersonaCasoPK().getIdPersona(), carta.getIdCaso());

				// Valida si el secretario ya fue comunicado
				if (eventoComunicacionSecretario != null) {
					// Si el evento ya existe lo actualiza
					eventoComunicacionSecretario.setFechaDeAsignacion(new Date());
					eventoComunicacionSecretario.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
					eventoComunicacionSecretario.setFechaUltimaModificacion(new Date());
					eventoRolPersonaCasoFacade.actualizar(eventoComunicacionSecretario);
				} else {
					// Si el evento no existe lo crea
					eventoRolPersonaCasoFacade.crearEventoRolPersonaCaso(
							UtilDominios.ESTADO_ROL_PERSONA_CASO_COMUNICADO, null, new Date(),
							UtilDominios.ESTADO_REGISTRO_ACTIVO, persona.getRolPersonaCasoPK().getIdRol(),
							persona.getRolPersonaCasoPK().getIdPersona(), carta.getIdCaso());
				}
			}
		}
	}

	@Override
	public List<CartaPendienteImpresionDTO> consultarCartasPendientesImpresion() {
		return manejadorCartaPersona.consultarCartasPendientesImpresion();
	}

	@Override
	public void actualizarEstadoCartaImpresa(Long idCarta) {
		CartaPersona cartaImpresa = buscar(idCarta);
		cartaImpresa.setEstadoCarta(UtilDominios.ESTADO_CARTA_IMPRESA);
		actualizar(cartaImpresa);
	}

	@Override
	public void consultarCartasAudienciaPendientesImpresion() {
		List<CartaAudienciaPendienteImpresionDTO> listCartasPendientes = manejadorCartaPersona
				.consultarCartasAudienciaPendientesImpresion();

		List<String> cuerpoCorreo = new ArrayList<>();
		ParametrosEnvioCorreoDTO parametrosEnvioCorreoDTO = new ParametrosEnvioCorreoDTO();
		parametrosEnvioCorreoDTO.setAsunto("Cartas Pendiente de Impresión");

		List<CorreoElectronicoDTO> correosDestino = new ArrayList<>();

		for (CartaAudienciaPendienteImpresionDTO dto : listCartasPendientes) {
			List<CorreoElectronicoDTO> correoElectronicoDTO = new ArrayList<>();
			CorreoElectronicoDTO correoSecretario = new CorreoElectronicoDTO();
			correoSecretario.setDireccion(dto.getDireccionSecretario());
			correoSecretario.setIdPersona(dto.getIdPersona().longValue());
			correoElectronicoDTO.add(correoSecretario);
			correosDestino.add(correoSecretario);
			parametrosEnvioCorreoDTO.setRolPersonaCaso(correosDestino);
			CorreoElectronicoDTO correoAbogado = new CorreoElectronicoDTO();
			correoAbogado.setDireccion(dto.getDireccionAbogado());
			correoAbogado.setIdPersona(dto.getIdPersona().longValue());
			parametrosEnvioCorreoDTO.setRemitente(correoAbogado);
			parametrosEnvioCorreoDTO.setIdCaso(dto.getIdCaso().longValue());
			parametrosEnvioCorreoDTO.setIdAudiencia(dto.getIdAudiencia().longValue());
			cuerpoCorreo.add("Sr. Funcionario se tiene " + dto.getCartasPendientes()
					+ " cantidad de cartas pendientes por imprimir, para realizar la impresión de estas, es necesario que ingrese por el menú conciliación a la funcionalidad cartas pendientes por imprimir");
			parametrosEnvioCorreoDTO.setCuerpoCorreo(cuerpoCorreo);
		}

		List<DocumentoDTO> adjuntos = new ArrayList<DocumentoDTO>();
		parametrosEnvioCorreoDTO.setAdjuntos(adjuntos);
		parametrosEnvioCorreoDTO.setTipo(UtilDominios.TIPO_PLANTILLAS_CORREO_GENERAL);
		correoRolPersonaCasoFacade.enviarCorreo(parametrosEnvioCorreoDTO);
	}

	@Override
	public CartaPersona consultarCarta(Long idCarta) {
		CartaPersona carta = manejadorCartaPersona.buscar(idCarta);
		CartaPersonaDTO transformador = new CartaPersonaDTO();
		return transformador.transformarEntidadSinDependencias(carta);
	}

	@Override
	public String obtenerCartasCitacionAudiencias(Long idCaso) {
		List<Audiencia> audiencias = manejadorAudiencia.citacionAudienciasDiferentesPrimera(idCaso);
		List<CartaCitacionAudienciasDTO> citacionAudienciasDTOs = new ArrayList<CartaCitacionAudienciasDTO>();

		Date fechaActual = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaActual);

		List<Dominio> dominios = dominioFacade.obtenerDominiosHijos(UtilDominios.DOMINIO_AGRUPADOR_ROL_PERSONA,
				UtilDominios.AGRUPADOR_ROL_PERSONA_ARBITROS);

		for (Audiencia it1 : audiencias) {
			Caso caso = it1.getCaso();
			List<RolPersonaCaso> rolPersonaCasos = caso.getRolPersonaCasoList();

			for (RolPersonaCaso it2 : rolPersonaCasos) {
				if (it2.getRol().getNombre().equalsIgnoreCase(UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE)
						|| it2.getRol().getNombre().equalsIgnoreCase(UtilDominios.ROL_PERSONA_PARTE_DEMANDADA)
						|| it2.getRol().getNombre().equalsIgnoreCase(UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE)
						|| it2.getRol().getNombre().equalsIgnoreCase(UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO)) {

					int contadorArbitros = 0;
					StringBuilder nombresArbitros = new StringBuilder();
					StringBuilder nombresConvocantes = new StringBuilder();
					StringBuilder nombresConvocados = new StringBuilder();

					CartaCitacionAudienciasDTO citacionAudienciasDTO = new CartaCitacionAudienciasDTO();
					citacionAudienciasDTO.setCiudad("Bogota");
					citacionAudienciasDTO.setFechaActual(new Date());
					citacionAudienciasDTO.setNombreParteDirigida(it2.getPersona().getNombreCompleto());
					citacionAudienciasDTO.setRolParteDirigida(it2.getRol().getNombre().trim());
					citacionAudienciasDTO.setCasoServicio(caso.getServicioMateria().getServicio().getNombre());
					citacionAudienciasDTO.setCasoCodigo(caso.getIdCaso().toString());
					citacionAudienciasDTO.setTipoAudiencia(it1.getTipoAudiencia());
					citacionAudienciasDTO.setFechaAudiencia(it1.getHoraInicio());
					citacionAudienciasDTO.setDireccionCentro(caso.getSede().getDireccion());

					for (RolPersonaCaso it4 : rolPersonaCasos) {
						if (it4.getRol().getNombre().equalsIgnoreCase(UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE)) {
							nombresConvocantes.append(it4.getPersona().getNombreCompleto().toUpperCase())
									.append(UtilConstantes.CARACTER_COMA).append(UtilConstantes.CARACTER_ESPACIO);
						}
						if (it4.getRol().getNombre().equalsIgnoreCase(UtilDominios.ROL_PERSONA_PARTE_DEMANDADA)) {
							nombresConvocados.append(it4.getPersona().getNombreCompleto().toUpperCase())
									.append(UtilConstantes.CARACTER_COMA).append(UtilConstantes.CARACTER_ESPACIO);
						}
						if (it4.getRol().getNombre().equalsIgnoreCase(UtilDominios.ROL_PERSONA_ABOGADO)
								&& citacionAudienciasDTO.getNombreDirector() == null) {
							citacionAudienciasDTO.setNombreDirector(it4.getPersona().getNombreCompleto());

							StringTokenizer cadena = new StringTokenizer(it4.getPersona().getNombreCompleto(), " ");
							StringBuilder resultado = new StringBuilder();

							while (cadena.hasMoreTokens()) {
								resultado.append(cadena.nextToken().charAt(0));
							}

							citacionAudienciasDTO.setInicialesDirector(resultado.toString().toUpperCase());
							citacionAudienciasDTO.setCargoDirector(dominioFacade.getNombreDominio(
									UtilDominios.DOMINIO_ROL_PERSONA, UtilDominios.ROL_PERSONA_ABOGADO));

							for (CorreoElectronico it6 : it4.getPersona().getCorreoElectronicoList()) {
								if (it6.getEstadoRegistro().equalsIgnoreCase(UtilDominios.ESTADO_REGISTRO_ACTIVO)
										&& it6.getTipo().equalsIgnoreCase(
												UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL)) {
									citacionAudienciasDTO.setCorreoDirector(it6.getDireccion());
									break;
								}
							}
						}
						for (Dominio it5 : dominios) {
							if (it4.getRol().getNombre().equalsIgnoreCase(it5.getDominioPK().getCodigo())
									&& it4.getEstadoRegistro().equalsIgnoreCase(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
								nombresArbitros.append(it4.getPersona().getNombreCompleto().toUpperCase())
										.append(UtilConstantes.CARACTER_COMA).append(UtilConstantes.CARACTER_ESPACIO);
								contadorArbitros++;
								break;
							}
						}
					}

					citacionAudienciasDTO.setNombreConvocante(nombresConvocantes.toString());
					citacionAudienciasDTO.setNombreConvocado(nombresConvocados.toString());

					List<Ubicacion> ubicaciones = it2.getPersona().getUbicacionList();

					if (!ubicaciones.isEmpty())
						citacionAudienciasDTO.setDireccion(ubicaciones.get(0).getDireccion());

					citacionAudienciasDTO
							.setDireccion(citacionAudienciasDTO.getDireccion() == null ? UtilConstantes.NO_APLICA
									: citacionAudienciasDTO.getDireccion());

					citacionAudienciasDTO.setTotalArbitros(contadorArbitros);
					citacionAudienciasDTO.setNombreArbitros(nombresArbitros.toString());
					citacionAudienciasDTOs.add(citacionAudienciasDTO);
				}
			}
		}

		StringBuilder plantilla = new StringBuilder();
		for (CartaCitacionAudienciasDTO it4 : citacionAudienciasDTOs) {
			plantilla.append(TemplateParser.getInstancia().setAtributos(it4, UtilConstantes.TEMPLATE_CARTA_AUDIENCIA));
		}

		return plantilla.toString();
	}

	@Override
	public void confirmacionNotificacionArbitro(CartaPersonaDTO cartaPersonaDTO, boolean envio) {
		CartaPersona cartaPersona = manejadorCartaPersona.buscar(cartaPersonaDTO.getIdCartaPersona());
		if (cartaPersona != null) {
//			if (envio) {
//				cartaPersona.setEstadoCarta(UtilDominios.ESTADO_CARTA_ENVIADA);
//				manejadorCartaPersona.actualizar(cartaPersona);
//			}
//			eventoCasoNotificacionArbitro(cartaPersona);
//			eventoCasoNotificacionSecretario(cartaPersona);

			eventoCasoNotificacionArbitro(cartaPersona);
			eventoCasoNotificacionSecretario(cartaPersona);
			if (envio) {
				cartaPersona.setEstadoCarta(UtilDominios.ESTADO_CARTA_ENVIADA);
				manejadorCartaPersona.actualizar(cartaPersona);
			}
		}
	}

	@Override
	public void actualizarEstadoPlanilla(List<PlanillaCorrespondenciaDTO> planilla) {
		for (PlanillaCorrespondenciaDTO planillaCorrespondenciaDTO : planilla) {
			CartaPersona cartaPersona = manejadorCartaPersona.buscar(planillaCorrespondenciaDTO.getIdCartaPersona());
			cartaPersona.setFechaEnvio(new Date());
			cartaPersona.setEstadoCarta(UtilDominios.ESTADO_CARTA_ENVIADA);
			cartaPersona.setFechaUltimaModificacion(new Date());
			actualizar(cartaPersona);
		}
	}

	@Override
	public List<CorrespondenciaDTO> consultarCorrespondencia(Long idCaso, List<String> estadosCarta) {
		return manejadorCartaPersona.consultarCorrespondencia(idCaso, estadosCarta);
	}

	/**
	 * Método encargado de la generación del PDF de un lote de cartas y retorna los
	 * casos a los cuales no pudo generar carta
	 * 
	 * @param loteCartasGeneradas
	 * @return
	 * @throws IOException
	 */
	private List<LotesCartasDTO> generarPDFLoteCartas(List<CartaPersona> loteCartasGeneradas, String path,
			String pathMerge) throws Exception {
		List<LotesCartasDTO> loteSinPlantilla = new ArrayList<>();
		if (!loteCartasGeneradas.isEmpty()) {
			String pathHmtl = UUID.randomUUID().toString() + UtilConstantes.CARACTER_PUNTO
					+ UtilConstantes.EXTENSION_ARCHIVO_HTML;
			String pathPdf = UUID.randomUUID().toString() + UtilConstantes.CARACTER_PUNTO
					+ UtilConstantes.EXTENSION_ARCHIVO_PDF;
			Document document = new Document();
			OutputStream output = new FileOutputStream(path + pathMerge);
			try {
				PdfWriter writer = PdfWriter.getInstance(document, output);
				document.open();
				PdfContentByte cb = writer.getDirectContent();

				int i = 0;

				for (CartaPersona carta : loteCartasGeneradas) {
					i++;
					logger.info("Reg_PDF N°" + i + " Escribiendo registro PDF - Id Carta: " + carta.getIdCartaPersona());
					generarPDF.createHtmlToexport(carta.getContenido(), path + pathHmtl);
					generarPDF.generatePDFFromHtml(path + pathHmtl, path + pathPdf);
					generarPDF.mergeSinBorrado(document, cb, writer, path + pathPdf, output);
				}

				output.flush();
				document.close();
				output.close();
			} catch (IOException e) {
				logger.error("Error: ", e);
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR927.toString()));
			} finally {
				gestorDocumentalFacade.eliminarDocumento(path + pathPdf);
				gestorDocumentalFacade.eliminarDocumento(path + pathHmtl);
				if (document.isOpen())
					document.close();
				try {
					if (output != null)
						output.close();
				} catch (IOException ioe) {
					logger.error("Error: ", ioe);
				}
			}
			LotesCartasDTO lotePdf = new LotesCartasDTO();
			lotePdf.setCodigoCaso(UtilConstantes.CERO);
			lotePdf.setNombreParte(pathMerge);
			loteSinPlantilla.add(lotePdf);
		}

		return loteSinPlantilla;
	}

	private List<LotesCartasDTO> generarPDFLoteCartas2(List<CartaPersona> loteCartasGeneradas) {
		List<LotesCartasDTO> loteSinPlantilla = new ArrayList<>();
		if (!loteCartasGeneradas.isEmpty()) {
			ParametrosGenerales parametroRutaTranversal = manejadorParametrosGenerales
					.buscar(UtilParamGenerales.GESTOR_DOCUMENTAL_RUTA_TRANSVERSALES);
			String path = parametroRutaTranversal.getValorTexto() + File.separator;
			String pathHmtl = UUID.randomUUID().toString() + UtilConstantes.CARACTER_PUNTO
					+ UtilConstantes.EXTENSION_ARCHIVO_HTML;
			String pathFinalPDF = UUID.randomUUID().toString() + UtilConstantes.CARACTER_PUNTO
					+ UtilConstantes.EXTENSION_ARCHIVO_PDF;
			try {
				File file = new File(path + pathFinalPDF);
				Document document = new Document();
				PdfCopy copy = new PdfCopy(document, new FileOutputStream(file));
//	        	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
				document.open();
				PdfReader reader;
				for (CartaPersona carta : loteCartasGeneradas) {
					reader = new PdfReader(carta.getContenido().getBytes(Charset.forName("UTF-8")));
					copy.addDocument(reader);
					reader.close();
//					generarPDF.createHtmlToexport(carta.getContenido(), path + pathHmtl);
//					
//					XMLWorkerHelper.getInstance().parseXHtml(writer, document,
//			                new FileInputStream(path + pathHmtl)); 
				}
				document.close();
			} catch (IOException e1) {
				logger.error("Error: ", e1);
			} catch (DocumentException e) {
				logger.error("Error: ", e);
			}
			LotesCartasDTO lotePdf = new LotesCartasDTO();
			lotePdf.setCodigoCaso(UtilConstantes.CERO);
			lotePdf.setNombreParte(pathFinalPDF);
			loteSinPlantilla.add(lotePdf);
		}

		return loteSinPlantilla;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * ICartaPersonaFacade#generarCartasProgramacionAudiencias(com.ccb.simasc.
	 * transversal.entidades. Audiencia)
	 */
	public void generarCartasProgramacionAudiencias(Long idCaso, Long idAudiencia, Long consecutivoAudiencia) {
		ParametrosGenerarCartaDTO parametrosCarta = new ParametrosGenerarCartaDTO();
		PlantillaCartaDTO plantillaDTO = plantillaCartaFacade.consultarPlantillaServicioCaso(idCaso,
				(consecutivoAudiencia != null && consecutivoAudiencia == 1)
						? UtilDominios.NOMBRE_PLANTILLA_CARTA_PRIMERA_AUDIENCIA
						: UtilDominios.NOMBRE_PLANTILLA_CARTA_AUDIENCIAS_DIFERENTES_PRIMERA);

		parametrosCarta.setIdPlantilla(plantillaDTO.getIdPlantillaCarta().toString());
		parametrosCarta.setIdCaso(idCaso);
		parametrosCarta.setIdAudiencia(idAudiencia);
		parametrosCarta.setIndicadorNotificacion(UtilConstantes.SI);
		parametrosCarta.setIndicadorEnvio(UtilConstantes.SI);
		parametrosCarta.setEnvioPorLotes(false);

		List<ParteCasoDTO> listaPartesCasoDTO = personaFacade.consultarPartesConciliacionCaso(idCaso, false, false);
		List<Long> listaIdNotificados = new ArrayList<Long>();
		for (ParteCasoDTO parte : listaPartesCasoDTO) {
			listaIdNotificados.add(parte.getIdPersona());
		}
		parametrosCarta.setListaIdNotificados(listaIdNotificados);
		generarCarta(parametrosCarta, null);

	}

	@Override
	public PlanillaCorrespondenciaCartaDTO consultarInformacionEstadoCorrespondencia(
			PlanillaCorrespondenciaCartaDTO filtroBusqueda) {

		List<LlamadaPlanillaCorrespondenciaDTO> informacionCarta = manejadorCartaPersona
				.consultarInformacionEstadoCorrespondencia(filtroBusqueda);
		PlanillaCorrespondenciaCartaDTO consultaPlanilla = filtroBusqueda;
		consultaPlanilla.setCorrespondencia(informacionCarta);
		return consultaPlanilla;
	}

	@Override
	public void actualizarEstadoCorrespondencia(List<LlamadaPlanillaCorrespondenciaDTO> cartas,
			String idPersonaModificacion) {

		for (LlamadaPlanillaCorrespondenciaDTO llamadaPlanillaCorrespondenciaDTO : cartas) {
			CartaPersona cartaActualizar = manejadorCartaPersona
					.buscar(llamadaPlanillaCorrespondenciaDTO.getNumeroCarta());
			cartaActualizar.setEstadoCarta(llamadaPlanillaCorrespondenciaDTO.getEstadoCarta());
			cartaActualizar.setNumeroGuia(llamadaPlanillaCorrespondenciaDTO.getNumeroGuia());
			cartaActualizar.setFechaUltimaModificacion(new Date());
			cartaActualizar.setIdUsuarioModificacion(idPersonaModificacion);
			if (llamadaPlanillaCorrespondenciaDTO.getFechaDevolucion() != null) {
				cartaActualizar.setFechaDevolucion(llamadaPlanillaCorrespondenciaDTO.getFechaDevolucion());
			}
			manejadorCartaPersona.actualizar(cartaActualizar);
		}

	}

	// protected region metodos adicionales end

}
