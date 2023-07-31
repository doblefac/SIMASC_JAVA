package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta sección sus modificaciones

import static com.ccb.simasc.transversal.utilidades.UtilOperaciones.nvl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.comun.fachada.interfaces.IIntegracionSWFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.comun.webservice.integracion.interfaces.IClienteSWFirmaDigital;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorAreaAsuntoClasificacion;
import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorConvenio;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorEstadoPersonaRol;
import com.ccb.simasc.integracion.manejadores.ManejadorEvento;
import com.ccb.simasc.integracion.manejadores.ManejadorFacturacionCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorFechaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorPagoCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroDeServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroServicioSorteo;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaServicioMateria;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaSolicitud;
import com.ccb.simasc.integracion.manejadores.ManejadorPlantillaCarta;
import com.ccb.simasc.integracion.manejadores.ManejadorProfesion;
import com.ccb.simasc.integracion.manejadores.ManejadorProgramacionAlerta;
import com.ccb.simasc.integracion.manejadores.ManejadorRelacionadoConvenio;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorServicioMateria;
import com.ccb.simasc.integracion.manejadores.ManejadorSolicitudProrroga;
import com.ccb.simasc.integracion.manejadores.ManejadorSolicitudServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorTipoDeServicioRol;
import com.ccb.simasc.integracion.manejadores.ManejadorZonaGeografica;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionAsignacion;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionOrdenamiento;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlertaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlmacenamientoDocumentosFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IArbitroCasoLiberacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICarpetaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICartaPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoTramiteOrdinarioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDiaFestivoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEstadoPersonaTipoServicioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFacturacionCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFechaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IMateriaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.INombramientoPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.INotificacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPagoCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrosGeneralesFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaSolicitudFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPreseleccionadoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IProgramacionAlertaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRelacionadoConvenioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IReliquidacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IServicioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISorteoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISuspensionFacade;
import com.ccb.simasc.negocio.fachadas.reparto.implementaciones.RepartoSvc;
import com.ccb.simasc.negocio.fachadas.reparto.interfaces.IRepartoSvc;
import com.ccb.simasc.transversal.dto.AudienciaDTO;
import com.ccb.simasc.transversal.dto.CasoDTO;
import com.ccb.simasc.transversal.dto.CasosControlLegalidadDTO;
import com.ccb.simasc.transversal.dto.CasosNoAsignadosDTO;
import com.ccb.simasc.transversal.dto.CasosSinCerrarDTO;
import com.ccb.simasc.transversal.dto.CierreConciliacionDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.DatosEntradaRepartoDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.DominioBasicoDTO;
import com.ccb.simasc.transversal.dto.EventoDTO;
import com.ccb.simasc.transversal.dto.FechasAgendamientoDTO;
import com.ccb.simasc.transversal.dto.FormatoHoraAudienciaDTO;
import com.ccb.simasc.transversal.dto.InformacionCasoDTO;
import com.ccb.simasc.transversal.dto.PagoCasoDTO;
import com.ccb.simasc.transversal.dto.UbicacionDTO;
import com.ccb.simasc.transversal.dto.formularios.CapturaResultadoJornadaDTO;
import com.ccb.simasc.transversal.dto.formularios.CasoAsignadoDTO;
import com.ccb.simasc.transversal.dto.formularios.CasoCerradoDTO;
import com.ccb.simasc.transversal.dto.formularios.CasoIncompletoDTO;
import com.ccb.simasc.transversal.dto.formularios.DatosBasicosCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.DatosReversarResultadoDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltroCasosAsignadosDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioParteDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosGenerarCartaDTO;
import com.ccb.simasc.transversal.dto.formularios.RadicarCasoConvenioDTO;
import com.ccb.simasc.transversal.dto.formularios.SuspenderDTO;
import com.ccb.simasc.transversal.dto.reportes.CasosCobradosDTO;
import com.ccb.simasc.transversal.entidades.Alerta;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.CartaPersona;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Convenio;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.Evento;
import com.ccb.simasc.transversal.entidades.FacturacionCaso;
import com.ccb.simasc.transversal.entidades.FechaCaso;
import com.ccb.simasc.transversal.entidades.FechaCasoPK;
import com.ccb.simasc.transversal.entidades.NombramientoPersona;
import com.ccb.simasc.transversal.entidades.PagoCaso;
import com.ccb.simasc.transversal.entidades.ParametroDeServicio;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaServicioMateria;
import com.ccb.simasc.transversal.entidades.PersonaSolicitud;
import com.ccb.simasc.transversal.entidades.PlantillaCarta;
import com.ccb.simasc.transversal.entidades.Profesion;
import com.ccb.simasc.transversal.entidades.ProgramacionAlerta;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.RolPersonaCasoPK;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.entidades.ServicioMateria;
import com.ccb.simasc.transversal.entidades.ServicioMateriaPK;
import com.ccb.simasc.transversal.entidades.SolicitudServicio;
import com.ccb.simasc.transversal.entidades.Suspension;
import com.ccb.simasc.transversal.entidades.ZonaGeografica;
import com.ccb.simasc.transversal.excepciones.EstadosCasoException;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;
import com.simasc.clientes.ministerio.Mensaje;

import co.org.ccb.simasc.comun.pdf.RadicacionSolicitudPDF;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link Caso}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class CasoFacade extends AccesoFacade<Caso, Long, CasoDTO> implements ICasoFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	private static final Logger logger = LogManager.getLogger(CasoFacade.class.getName());

	private static final String NOMBRE_DOCUMENTO = "Radicacion caso de convenio";
	private static final String VS = " VS. ";

	@EJB
	private IServicioFacade servicioFacade;

	@EJB
	private IRelacionadoConvenioFacade relacionadoConvenioFacade;

	@EJB
	private IDominioFacade dominioFacade;

	@EJB
	private IPagoCasoFacade pagoCasoFacade;

	@EJB
	private IEventoFacade eventoFacade;

	@EJB
	private IRolPersonaCasoFacade rolPersonaCasoFacade;

	@EJB
	private IRolPersonaFacade rolPersonaFacade;

	@EJB
	private IEventoRolPersonaCasoFacade eventoRolPersonaCasoFacade;

	@EJB
	private ISuspensionFacade suspensionFacade;

	@EJB
	private IFechaCasoFacade fechaCasoFacade;

	@EJB
	private INombramientoPersonaFacade nombramientoPersonaFacade;

	@EJB
	private ICartaPersonaFacade cartaPersonaFacade;

	@EJB
	private ICarpetaFacade carpetaFacade;

	@EJB
	private ICorreoElectronicoFacade correoElectronicoFacade;

	@EJB
	private IEstadoPersonaTipoServicioFacade estadoPersonaTipoServicioFacade;

	@EJB
	private IRolFacade rolFacade;

	@EJB
	private IPersonaSolicitudFacade personaSolicitudFacade;

	@EJB
	private IIntegracionSWFacade integracionSWFacade;

	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;

	@EJB
	private ManejadorPagoCaso manejadorPagoCaso;

	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	private ManejadorAreaAsuntoClasificacion manejadorAreaAsuntoClasificacion;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorEvento manejadorEvento;

	@EJB
	private ManejadorServicio manejadorServicio;

	@EJB
	private ManejadorFechaCaso manejadorFechaCaso;

	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;

	@EJB
	private ManejadorRol manejadorRol;

	@EJB
	private ManejadorPlantillaCarta manejadorPlantillaCarta;

	@EJB
	private ManejadorSolicitudServicio manejadorSolicitudServicio;

	@EJB
	private IReliquidacionFacade reliquidacionFacade;

	@EJB
	private ManejadorSolicitudProrroga manejadorSolicitudProrroga;

	@EJB
	private IDiaFestivoFacade diaFestivoFacade;

	@EJB
	private ISorteoFacade sorteoFacade;

	// contexto de sesion de usuario.
	@Inject
	private ApplicationRequestContext appContext;

	@EJB
	private ManejadorRelacionadoConvenio manejadorRelacionadoConvenio;

	@EJB
	private ManejadorConvenio manejadorConvenio;

	@EJB
	private ManejadorZonaGeografica manejadorZonaGeografica;

	@EJB
	private ManejadorProfesion manejadorProfesion;

	@EJB
	private AudienciaFacade audienciaFacade;

	@EJB
	private IPersonaFacade personaFacade;

	@EJB
	private IAlmacenamientoDocumentosFacade almacenamientoDocumentosFacade;

	@EJB
	private RadicacionSolicitudPDF radicacionSolicitudPDF;

	@EJB
	private RepartoSvc repartoSvc;

	@EJB
	private ManejadorDocumento manejadorDocumento;

	@EJB
	private ManejadorFacturacionCaso manejadorFacturacionCaso;

	@EJB
	private ManejadorAudiencia manejadorAudiencia;

	@EJB
	private ManejadorTipoDeServicioRol manejadorTipoDeServicioRol;

	@EJB
	private IDocumentoFacade documentoFacade;

	@EJB
	private IFacturacionCasoFacade facturacionCasoFacade;

	@EJB
	private ManejadorServicioMateria manejadorServicioMateria;

	@EJB
	private ManejadorParametroDeServicio manejadorParametroDeServicio;

	@EJB
	private ManejadorPersonaSolicitud manejadorPersonaSolicitud;

	@EJB
	private ManejadorProgramacionAlerta manejadorProgramacionAlerta;

	@EJB
	private ManejadorCorreoRolPersonaCaso manejadorCorreoRolPersonaCaso;

	@EJB
	private IClienteSWFirmaDigital clienteFirmaDigital;

	@EJB
	private IParametrosGeneralesFacade parametrosGeneralesFacade;

	@EJB
	private ManejadorRolPersona manejadorRolPersona;

	@EJB
	private ManejadorEstadoPersonaRol manejadorEstadoPersonaRol;

	@EJB
	private IArbitroCasoLiberacionFacade arbitroCasoLiberacionFacade;

	@EJB
	private ManejadorParametroServicioSorteo manejadorParametroServicioSorteo;

	@EJB
	private IMateriaFacade materiaFacade;

	@EJB
	private ManejadorPersonaServicioMateria manejadorPersonaServicioMateria;

	@EJB
	private IPreseleccionadoFacade preseleccionadoFacade;

	@EJB
	private INotificacionFacade notificacionFacade;
	
	@EJB
	private IAlertaFacade alertaFacade;
	
	@EJB
	private IProgramacionAlertaFacade programacionAlertaFacade;
	
	@EJB
	private ICasoTramiteOrdinarioFacade casoTramiteFacade;
	
	@EJB
	private IRepartoSvc repartoFacade;

	private String errorFila;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorCaso;
	}

	@Override
	public CasoDTO transformarSinDependencias(Caso obj) {
		return new CasoDTO().transformarSinDependencias(obj);
	}

	@Override
	public CasoDTO transformarConDependencias(Caso obj) {
		return new CasoDTO().transformarConDependencias(obj);
	}

	@Override
	public Caso transformarEntidadConDependencias(Caso obj) {
		return new CasoDTO().transformarEntidadConDependencias(obj);
	}
	
	public Caso transformarEntidadConDependenciasN(Caso obj) {
		return new CasoDTO().transformarEntidadConDependenciasNombramiento(obj);
	}

	@Override
	public Caso transformarEntidadSinDependencias(Caso obj) {
		return new CasoDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	public DatosBasicosCasoDTO obtenerDatosBasicosCaso(Long id) {

		Caso caso = manejadorCaso.buscarCasoActivo(id);

		InformacionFiltro f = new InformacionFiltro(TipoFiltro.EXACTO, PagoCaso.ENTIDAD_PAGO_CASO_ID_CASO, id);
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(f);
		List<PagoCaso> pagoCaso = (List<PagoCaso>) pagoCasoFacade.obtenerEntidadesPorFiltro(filtros,
				new ArrayList<InformacionOrdenamiento>(), null, new ArrayList<PagoCaso>(), false);
		List<Persona> abogados = manejadorRolPersonaCaso.consultarPersonasPorRolCaso(id,
				UtilDominios.ROL_PERSONA_ABOGADO);
		List<Persona> auxiliares = manejadorRolPersonaCaso.consultarPersonasPorRolCaso(id,
				UtilDominios.ROL_PERSONA_AUXILIAR_ADM);
		List<RolPersonaCaso> secretarios = manejadorRolPersonaCaso.consultarPersonasPorRolEstado(id,
				Arrays.asList(UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL),
				Arrays.asList(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO,
						UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR),
				false);
		List<Dominio> tipoRadicacion = dominioFacade.getDominioSinDependencias(UtilDominios.DOMINIO_TIPO_RADICACION);
		List<Dominio> tipoCaso = dominioFacade.getDominioSinDependencias(UtilDominios.DOMINIO_TIPO_SERVICIO);
		List<Dominio> tipoCuantia = dominioFacade.getDominioSinDependencias(UtilDominios.DOMINIO_TIPO_CUANTIA);

		List<RolPersonaCaso> abogadosCaso = manejadorRolPersonaCaso.consultarPorIdCasoyRol(id,
				UtilDominios.ROL_PERSONA_ABOGADO);

		DatosBasicosCasoDTO datos = new DatosBasicosCasoDTO();
		datos.setIdServicio(caso.getIdServicio());
		datos.setCaso(caso);
		if (!pagoCaso.isEmpty())
			datos.setPagoCaso(pagoCaso.get(0));
		datos.setTipoCaso(tipoCaso);
		datos.setTipoCuantia(tipoCuantia);
		datos.setTipoRadicacion(tipoRadicacion);
		if (!abogados.isEmpty()) {
			datos.setIdAbogado(abogados.get(0).getIdPersona());
		}

		if (abogadosCaso != null && !abogadosCaso.isEmpty()) {
			datos.setAbogadoAsignado(abogadosCaso.get(0).getPersona().getNombreCompleto());
		}

		if (!auxiliares.isEmpty()) {
			datos.setIdAuxiliar(auxiliares.get(0).getIdPersona());
			datos.setNombreAuxiliar(auxiliares.get(0).getNombreCompleto());
		}

		if (!secretarios.isEmpty()) {
			datos.setNombreSecretario(secretarios.get(0).getPersona().getNombreCompleto());
			// ARBF082
			for (CorreoElectronico correo : secretarios.get(0).getPersona().getCorreoElectronicoList()) {
				if (correo.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)
						&& correo.getTipo().equals(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL)) {
					datos.setCorreoSecretario(correo.getDireccion());
					break;
				}
			}
		}

		Date fechaEntreExpAct = manejadorFechaCaso.consultaFechaPorCasoTipo(caso.getIdCaso(),
				UtilDominios.ESTADO_TIPO_FECHA_CASO_ENTREGA_EXPEDIENTE_CAC);
		Date fechaDevreExpAct = manejadorFechaCaso.consultaFechaPorCasoTipo(caso.getIdCaso(),
				UtilDominios.ESTADO_TIPO_FECHA_CASO_ENVIO_ARCHIVO_CENTRAL);
		Date fechaCierre = manejadorFechaCaso.consultaFechaPorCasoTipo(caso.getIdCaso(),
				UtilDominios.ESTADO_TIPO_FECHA_CASO_CIERRE);
		datos.setFechaCierre(fechaCierre);
		datos.setFechaEntregaExpendienteCac(fechaEntreExpAct);
		datos.setFechaEnvioArchivoCentral(fechaDevreExpAct);
		// START ETAPA ARBITRAL
		int diasInterrupcciones = suspensionFacade.obtenerDiasHabilesSuspendidos(id,
				UtilDominios.TIPO_SUSPENSION_INTERRUPCION);
		int diasInterrupcionCumplidos = suspensionFacade.obtenerDiasHabilesSuspendidosCumplidos(id,
				UtilDominios.TIPO_SUSPENSION_INTERRUPCION);
		int diasSuspensiones = suspensionFacade.obtenerDiasHabilesSuspendidos(id,
				UtilDominios.TIPO_SUSPENSION_SUSPENSION_ARBITRAL);
		int diasSuspensionCalendario = suspensionFacade.obtenerDiasCalendarioSuspendidos(id,
				UtilDominios.TIPO_SUSPENSION_SUSPENSION_ARBITRAL);
		int diasInterrupcionCalendario = suspensionFacade.obtenerDiasCalendarioSuspendidos(id,
				UtilDominios.TIPO_SUSPENSION_INTERRUPCION);
		int diasSuspensionCumplidos = suspensionFacade.obtenerDiasHabilesSuspendidosCumplidos(id,
				UtilDominios.TIPO_SUSPENSION_SUSPENSION_ARBITRAL);
		int diasParaProferirLaudo = caso.getDiasParaProferirLaudo() == null ? 0 : caso.getDiasParaProferirLaudo();
		datos.setDiasAntesCierreProferirLaudo(diasParaProferirLaudo);

		Date fechaInicioConteoTerminos = manejadorFechaCaso.consultaFechaPorCasoTipo(id,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_INICIO_CONTEO_TERMINOS);

		if (fechaInicioConteoTerminos != null) {
			Date fechaLimiteCierreCasoAclaraComplem = fechaCasoFacade.calcularFechaLimiteParaCierreDeCaso(id);
			// Control de conteo de terminos
			if (fechaInicioConteoTerminos.after(new Date()))
				datos.setDiasTranscurridos(0);
			else {
				/**
				 * días transcurridos sin suspensiones e interrupciones se calculan a partir de
				 * la diferencia entre la Fecha inicio conteo de términos y la fecha del sistema
				 * en días calendario, incluyendo el día actual
				 */
				datos.setDiasSinSuspencionInterrupcion(
						UtilOperaciones.calcularDiasEntreFechasLey(fechaInicioConteoTerminos, new Date()));
				/**
				 * se calculan a partir de la diferencia entre la Fecha inicio conteo de
				 * términos y la fecha del sistema en días calendario, incluyendo el día actual,
				 * menos la suma del Total días calendario de suspensiones y Total días
				 * calendario de interrupción
				 */
				datos.setDiasTranscurridos(
						UtilOperaciones.calcularDiasEntreFechasLey(fechaInicioConteoTerminos, new Date())
								- (diasInterrupcionCumplidos + diasSuspensionCumplidos));
			}
			datos.setDiasFaltantes(
					UtilOperaciones.calcularDiasEntreFechasII(new Date(), fechaLimiteCierreCasoAclaraComplem));
			datos.setFechaPosibleProferirLaudo(diaFestivoFacade
					.restarDiasHabilesAFecha(fechaLimiteCierreCasoAclaraComplem, (long) diasParaProferirLaudo));
			datos.setFechaLimiteCierreCaso(fechaLimiteCierreCasoAclaraComplem);
			datos.setFechaInicioConteoTerminos(fechaInicioConteoTerminos);
		}
		datos.setTotalDiasInterrupcion(diasInterrupcciones);
		datos.setTotalDiasSuspension(diasSuspensiones);
		datos.setTotalDiasInterrupcionCalendario(diasInterrupcionCalendario);
		datos.setTotalDiasSuspensionCalendario(diasSuspensionCalendario);

		String materia = materiaFacade.buscar(caso.getIdMateria()).getNombre();
		datos.setMateriaCaso(materia);

		FechaCasoPK fechaCasoPK = new FechaCasoPK(UtilDominios.TIPO_FECHA_CASO_CONSTITUCION, id);
		FechaCaso fechaCasoConstitucion = fechaCasoFacade.buscar(fechaCasoPK);

		if (fechaCasoConstitucion != null) {
			datos.setFechaConstitucion(fechaCasoConstitucion.getFecha());
		}

		if (caso.getEstadoCaso().equals(UtilDominios.ESTADO_CASO_REABIERTO)) {

			List<String> tipoEventos = new ArrayList<>();
			tipoEventos.add(UtilDominios.TIPO_EVENTO_CASO_REABIERTO);
			List<EventoDTO> evento = eventoFacade.consultarEventosPorTipoOrdernadoFechaDesc(tipoEventos,
					caso.getIdCaso());

			datos.setObservacionesReAperturaCaso(evento.get(0).getObservaciones());
			datos.setRestauraEstadoSorteabilidad(caso.isRestauraEstadoSorteabilidad());
		}

		// END ETAPA ARBITRAL
		return datos;
	}

	@Override
	public Collection<Caso> obtenerEntidadesPatron(String patron, List<Caso> arrayList, boolean dependencias) {
		List<Caso> casos = manejadorCaso.consultarCasoPorPatron(patron);
		return (dependencias) ? transformarEntidadesColeccionConDependencias(casos, arrayList)
				: transformarEntidadesColeccionSinDependencias(casos, arrayList);
	}

	public void editarDatosBasicos(DatosBasicosCasoDTO dto) throws Exception {

		Caso caso = dto.getCaso();
		caso.setDiasParaProferirLaudo(dto.getDiasAntesCierreProferirLaudo());
		Date fechaEntreExpAct = manejadorFechaCaso.consultaFechaPorCasoTipo(caso.getIdCaso(),
				UtilDominios.ESTADO_TIPO_FECHA_CASO_ENTREGA_EXPEDIENTE_CAC);
		fechaCaso(dto, caso.getIdCaso(), fechaEntreExpAct, UtilDominios.ESTADO_TIPO_FECHA_CASO_ENTREGA_EXPEDIENTE_CAC);
		Date fechaDevreExpAct = manejadorFechaCaso.consultaFechaPorCasoTipo(caso.getIdCaso(),
				UtilDominios.ESTADO_TIPO_FECHA_CASO_DEVOLUCION_EXPEDIENTE);
		Date fechaEnvioArchivoCentral = manejadorFechaCaso.consultaFechaPorCasoTipo(caso.getIdCaso(),
				UtilDominios.ESTADO_TIPO_FECHA_CASO_ENVIO_ARCHIVO_CENTRAL);
		fechaCaso(dto, caso.getIdCaso(), fechaEnvioArchivoCentral,
				UtilDominios.ESTADO_TIPO_FECHA_CASO_ENVIO_ARCHIVO_CENTRAL);
		PagoCaso pagoCaso = dto.getPagoCaso();

		// se obtienen los valores antes de la actualizacion
		List<Persona> abogadosAnt = manejadorRolPersonaCaso.consultarPersonasPorRolCaso(caso.getIdCaso(),
				UtilDominios.ROL_PERSONA_ABOGADO);
		List<Persona> auxiliaresAnt = manejadorRolPersonaCaso.consultarPersonasPorRolCaso(caso.getIdCaso(),
				UtilDominios.ROL_PERSONA_AUXILIAR_ADM);
		Caso casoAnterior = buscar(caso.getIdCaso());

		// Se obtiene el usuario que hizo la modificación
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;

		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		}

		if(casoAnterior.getIdServicio().equals(UtilConstantes.ID_SERVICIO_ARBITRAJE) && !caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_ARBITRAJE)){
			// consultamos los arbitros principales del caso
			List<RolPersonaCaso> arbitrosPrincipales = manejadorRolPersonaCaso
					.consultarArbitrosPrincipalesCaso(caso.getIdCaso());

			for (RolPersonaCaso persona : arbitrosPrincipales) {
				rolPersonaFacade.finalizarRolArbitroSocial(persona.getPersona().getIdPersona());
			}
		}
		
		if(!casoAnterior.getIdServicio().equals(UtilConstantes.ID_SERVICIO_ARBITRAJE) && caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_ARBITRAJE)){
			// consultamos los arbitros principales del caso
			List<RolPersonaCaso> arbitrosPrincipales = manejadorRolPersonaCaso
					.consultarArbitrosPrincipalesCaso(caso.getIdCaso());

			for (RolPersonaCaso persona : arbitrosPrincipales) {
				personaFacade.asignarRolArbitroSocial(persona.getPersona().getIdPersona(), UtilDominios.ESTADO_ARBITROS_HABILITADO, UtilConstantes.ID_ROL_ARBITRO, false);				
			}
		}
		
		Date fechaEntreExpAnt = manejadorFechaCaso.consultaFechaPorCasoTipo(casoAnterior.getIdCaso(),
				UtilDominios.ESTADO_TIPO_FECHA_CASO_ENTREGA_EXPEDIENTE_CAC);
		Date fechaDevreExpAnt = manejadorFechaCaso.consultaFechaPorCasoTipo(casoAnterior.getIdCaso(),
				UtilDominios.ESTADO_TIPO_FECHA_CASO_DEVOLUCION_EXPEDIENTE);
		Long idAbogadoAnterior = abogadosAnt.isEmpty() ? null : abogadosAnt.get(0).getIdPersona();

		if (caso.getFechaRadicacion() != null && !caso.getFechaRadicacion().equals(casoAnterior.getFechaRadicacion())) {
			String observaciones = "Se ha cambiado la fecha de radicación del caso, fecha anterior : "
					+ UtilOperaciones.formatearFechaFormato(casoAnterior.getFechaRadicacion(), "dd/MM/yyyy")
					+ " fecha nueva : "
					+ UtilOperaciones.formatearFechaFormato(caso.getFechaRadicacion(), "dd/MM/yyyy");
			eventoFacade.registrarEvento(caso, UtilDominios.TIPO_EVENTO_ACTUALIZACION_FECHA_CASO, observaciones,
					UtilConstantes.USUARIO_DEFECTO_SIMASC, null, null);
		}

		// actualizar agobado
		if (dto.getIdAbogado() != null) {
			if (!abogadosAnt.isEmpty()) {
				// Obtiene al abogado asignado actualmente al caso
				Persona abogadoActualCaso = abogadosAnt.get(0);

				// Valida si el abogado a asignar no es el que esta actualmente
				// asignado
				if (!abogadoActualCaso.getIdPersona().equals(dto.getIdAbogado())) {
					if (!manejadorRolPersonaCaso.obtenerRolesPersonaCaso(dto.getIdAbogado(), caso.getIdCaso(),
							Arrays.asList(UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL)))
						throw new SIMASCNegocioExcepcion(
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR910.toString()));
					rolPersonaCasoFacade.eliminarRolPersonaCaso(caso.getIdCaso(), abogadoActualCaso.getIdPersona(),
							UtilDominios.ROL_PERSONA_ABOGADO);
					rolPersonaCasoFacade.crearRolPersonaCaso(dto.getIdAbogado(), caso.getIdCaso(),
							UtilDominios.ROL_PERSONA_ABOGADO, UtilDominios.ESTADO_ROL_PERSONA_CASO_ASIGNADO);
				}
				// Si el caso no tiene abogados asocia al que esta siendo
				// asignado
			} else {
				rolPersonaCasoFacade.crearRolPersonaCaso(dto.getIdAbogado(), caso.getIdCaso(),
						UtilDominios.ROL_PERSONA_ABOGADO, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
				manejadorProgramacionAlerta.crearProgramacionAlerta(caso.getIdCaso(), dto.getIdAbogado(),
						UtilDominios.CODIGO_ALERTA_SIN_TRAMITAR_POR_ABOGADO1, null);
				manejadorProgramacionAlerta.crearProgramacionAlerta(caso.getIdCaso(), dto.getIdAbogado(),
						UtilDominios.CODIGO_ALERTA_SIN_TRAMITAR_POR_ABOGADO2, null);
			}

		} else {

			if (manejadorRolPersonaCaso.validaRolExisteCaso(caso.getIdCaso(), UtilDominios.ROL_PERSONA_ABOGADO)) {
				RolPersonaCaso personaCaso = manejadorRolPersonaCaso.consultarPersonaPorRolCaso(caso.getIdCaso(),
						UtilDominios.ROL_PERSONA_ABOGADO);
				manejadorRolPersonaCaso.eliminar(personaCaso);
			}

		}

		// actualizar auxiliar
		actualizarAsignarAuxiliarAdministrativoCaso(dto, caso, auxiliaresAnt);

		// ARBF082 - actualizar presidente
		if (dto.getIdPresidente() != null) {

			// Se marca el nuevo presidente
			InformacionFiltro filtroCasoNuevo = new InformacionFiltro(TipoFiltro.EXACTO,
					RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_PK_ID_CASO, caso.getIdCaso());
			InformacionFiltro filtroPersonaNuevo = new InformacionFiltro(TipoFiltro.EXACTO,
					RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_PK_ID_PERSONA, dto.getIdPresidente());
			List<InformacionFiltro> filtrosNuevo = new ArrayList<>();
			filtrosNuevo.add(filtroCasoNuevo);
			filtrosNuevo.add(filtroPersonaNuevo);
			List<InformacionAsignacion> asignacionesNuevo = new ArrayList<>();
			asignacionesNuevo.add(new InformacionAsignacion("p.esPresidente", true));
			manejadorRolPersonaCaso.actualizarPorFiltros(asignacionesNuevo, filtrosNuevo);

			// Se desmarca el presidente anterior
			InformacionFiltro filtroCasoAnt = new InformacionFiltro(TipoFiltro.EXACTO,
					RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_PK_ID_CASO, caso.getIdCaso());
			InformacionFiltro filtroPersonaAnt = new InformacionFiltro(TipoFiltro.DIFERENTE,
					RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_PK_ID_PERSONA, dto.getIdPresidente());
			List<InformacionFiltro> filtrosAnt = new ArrayList<>();
			filtrosAnt.add(filtroCasoAnt);
			filtrosAnt.add(filtroPersonaAnt);
			List<InformacionAsignacion> asignacionesAnt = new ArrayList<>();
			asignacionesAnt.add(new InformacionAsignacion("p.esPresidente", false));
			manejadorRolPersonaCaso.actualizarPorFiltros(asignacionesAnt, filtrosAnt);
		}

		// ARBF082 - actualizar secretario
		if (dto.getIdSecretario() != null) {
			RolPersonaCaso secretarioActual = null;
			boolean secretarioSeleccionadoYaExistia = false;

			// consultar la lista de secretarios que han sido asignados al caso
			List<RolPersonaCaso> secretarioAnt = manejadorRolPersonaCaso.consultarPersonasoPorRoleEstado(
					caso.getIdCaso(), Arrays.asList(UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL),
					Arrays.asList(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO,
							UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR,
							UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO),
					true);

			// Buscar el secretario que está actualmente asignado al caso
			for (RolPersonaCaso secretario : secretarioAnt) {
				if (secretario.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
					secretarioActual = secretario;
					break;
				}
			}

			// Se verifica si el secretario seleccionado y el actual son el mismo
			if (!(secretarioActual != null
					&& secretarioActual.getPersona().getIdPersona().equals(dto.getIdSecretario()))) {

				// Se inhabilita el secretario anterior, si habia aceptado o estaba pendiente de
				// aceptar
				if (secretarioActual != null)
					rolPersonaCasoFacade.eliminarRolPersonaCaso(caso.getIdCaso(),
							secretarioActual.getPersona().getIdPersona(),
							UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);

				// Se verifica si el secretario a asignar ya habia sido asignado anteriormente
				for (RolPersonaCaso secretario : secretarioAnt) {
					if (secretario.getPersona().getIdPersona().equals(dto.getIdSecretario())) {
						rolPersonaCasoFacade.modificarEstadoRolPersonaCaso(secretario.getRolPersonaCasoPK().getIdRol(),
								dto.getIdSecretario(), caso.getIdCaso(),
								UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR, usuarioModificacion, null, null);
						secretarioSeleccionadoYaExistia = true;
						break;
					}
				}

				if (!secretarioSeleccionadoYaExistia) {
					if (!manejadorRolPersonaCaso.obtenerRolesPersonaCaso(dto.getIdAbogado(), caso.getIdCaso(),
							new ArrayList<String>()))
						throw new SIMASCNegocioExcepcion(
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR910.toString()));
					// Se registra al nuevo secretario
					rolPersonaCasoFacade.crearRolPersonaCaso(dto.getIdSecretario(), caso.getIdCaso(),
							UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL,
							UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);

					// se registra el evento en la tabla eventoRolPersonaCaso
					eventoRolPersonaCasoFacade.crearEventoRolPersonaCaso(
							UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR, null, new Date(),
							UtilDominios.ESTADO_REGISTRO_ACTIVO, Long.valueOf(10), dto.getIdSecretario(),
							caso.getIdCaso());
				}
			}
		}

		// se almacena el campo area_asunto_clasificacion
		// se busca el id dependiendo de los campos almacenados
		if (dto.getCaso().getAreaAsuntoClasificacion().getAsunto().getIdArea() != null
				&& dto.getCaso().getAreaAsuntoClasificacion().getIdAsunto() != null) {
			Long idAreaAsuntoClasificacion = manejadorAreaAsuntoClasificacion.obtenerIdPorLlaves(
					dto.getCaso().getAreaAsuntoClasificacion().getIdAsunto(),
					(dto.getCaso().getAreaAsuntoClasificacion().getIdClasificacion() == null ? Long.valueOf("0")
							: dto.getCaso().getAreaAsuntoClasificacion().getIdClasificacion()));
			caso.setIdAreaAsuntoClasificacion(idAreaAsuntoClasificacion);
			caso.setAreaAsuntoClasificacion(null);
		} else {
			caso.setIdAreaAsuntoClasificacion(null);
			caso.setAreaAsuntoClasificacion(null);
		}

		if (pagoCaso.getNumeroRecibo() != null && pagoCaso.getNumeroRecibo() != "") {
			if (pagoCaso != null && pagoCaso.getFechaPago() == null) {
				PagoCaso pago = manejadorPagoCaso.buscar(pagoCaso.getNumeroRecibo());
				if (pago == null || pago.getIdCaso() != null)

					throw new SIMASCNegocioExcepcion(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR902.toString()));

				pago.setIdCaso(dto.getCaso().getIdCaso());
				pago.setEstado(UtilDominios.ESTADOS_PAGO_CASO_CASO_ASOCIADO);
				manejadorPagoCaso.actualizar(pagoCaso);
			}
		}

		if (dto.getFechaConstitucion() != null) {
			fechaCasoFacade.registrarFechaCaso(dto.getFechaConstitucion(), UtilDominios.TIPO_FECHA_CASO_CONSTITUCION,
					dto.getCaso().getIdCaso(), usuarioModificacion);

			caso.setEtapa(UtilDominios.ETAPA_CASO_ARBITRAL);
		}

		if (dto.isReAperturaCaso()) {
			realizaReaperturaCaso(dto, usuarioModificacion);
			caso.setEstadoCaso(UtilDominios.ESTADO_CASO_REABIERTO);
			caso.setRestauraEstadoSorteabilidad(dto.isRestauraEstadoSorteabilidad());
		}

		manejadorCaso.crearCaso(caso);

		// Actualizacion de los eventos dependiendo de los campos modificados

		if (!nvl(dto.getIdAbogado(), "").equals(nvl(idAbogadoAnterior, ""))) {
			List<Persona> abogadosDesp = manejadorRolPersonaCaso.consultarPersonasPorRolCaso(caso.getIdCaso(),
					UtilDominios.ROL_PERSONA_ABOGADO);
			String nombreNuevoAbogado = abogadosDesp.isEmpty() ? null : abogadosDesp.get(0).getNombreCompleto();
			String tipoIdentificacionNuevoAbg = abogadosDesp.isEmpty() ? null : abogadosDesp.get(0).getTipoDocumento();

			String observaciones = "Se ha cambiado el abogado asignado del caso a la persona " + nombreNuevoAbogado
					+ " , con número de identificación " + dto.getIdAbogado() + " de tipo "
					+ tipoIdentificacionNuevoAbg;
			eventoFacade.registrarEvento(caso, UtilDominios.TIPO_EVENTO_CAMBIO_DE_ABOGADO_DESIGNADO, observaciones,
					UtilConstantes.USUARIO_DEFECTO_SIMASC, null, null); // Usuario
																		// pendiente
																		// por
																		// validar
		}
		if (!nvl(fechaEntreExpAct, "").equals(nvl(fechaEntreExpAnt, ""))) {
			String observaciones = "Se ha cambiado la fecha de entrega de expediente a secretario, de la fecha: "
					+ (fechaEntreExpAnt == null ? "" : UtilOperaciones.formatearFechaConsulta(fechaEntreExpAnt))
					+ " a la fecha: "
					+ (fechaEntreExpAct == null ? null : UtilOperaciones.formatearFechaConsulta(fechaEntreExpAct));
			eventoFacade.registrarEvento(caso, UtilDominios.TIPO_EVENTO_ENTREGA_EXPEDIENTE_SECRETARIO, observaciones,
					UtilConstantes.USUARIO_DEFECTO_SIMASC, new Date(), UtilDominios.ESTADO_REGISTRO_ACTIVO); // Usuario
																												// pendiente
																												// por
																												// validar
		}
		if (!nvl(fechaDevreExpAct, "").equals(nvl(fechaDevreExpAnt, ""))) {
			String observaciones = "Se ha cambiado la fecha de devolución de expediente, de la fecha: "
					+ (fechaDevreExpAnt == null ? "" : UtilOperaciones.formatearFechaConsulta(fechaDevreExpAnt))
					+ " a la fecha: "
					+ (fechaDevreExpAct == null ? null : UtilOperaciones.formatearFechaConsulta(fechaDevreExpAct));
			eventoFacade.registrarEvento(caso, UtilDominios.TIPO_EVENTO_DEVOLUCION_EXPEDIENTE, observaciones,
					UtilConstantes.USUARIO_DEFECTO_SIMASC, new Date(), UtilDominios.ESTADO_REGISTRO_ACTIVO); // Usuario
																												// pendiente
																												// por
																												// validar
		}
		// ARBF082
		if (dto.getIdPresidente() != null) {
			Persona persona = manejadorPersona.buscar(dto.getIdPresidente());
			String nombreNuevoPresidente = persona.getNombreCompleto();
			String observaciones = (String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO311.toString()),
					nombreNuevoPresidente));
			eventoFacade.registrarEvento(caso, UtilDominios.TIPO_EVENTO_ASIGNACION_PRESIDENTE_TRIBUNAL, observaciones,
					UtilConstantes.USUARIO_DEFECTO_SIMASC, new Date(), null);
		}

		if (dto.getIdSecretario() != null) {
			eventoFacade.registrarEvento(caso, UtilDominios.TIPO_EVENTO_ASIGNACION_SECRETARIO_TRIBUNAL,
					dto.getObservaciones(), UtilConstantes.USUARIO_DEFECTO_SIMASC, new Date(), null);
		}

		
	}

	/**
	 * 
	 * 
	 * @param dto
	 * @param idCaso
	 * @param fechaValidar
	 * @param tipoFecha
	 */
	public void fechaCaso(DatosBasicosCasoDTO dto, Long idCaso, Date fechaValidar, String tipoFecha) {
		FechaCaso fechaCaso = new FechaCaso();

		if (UtilDominios.ESTADO_TIPO_FECHA_CASO_ENTREGA_EXPEDIENTE_CAC.equals(tipoFecha)) {
			fechaCaso.setFecha(
					dto.getFechaEntregaExpendienteCac() != null ? dto.getFechaEntregaExpendienteCac() : fechaValidar);
			fechaCaso
					.setEstadoRegistro(dto.getFechaEntregaExpendienteCac() != null ? UtilDominios.ESTADO_REGISTRO_ACTIVO
							: UtilDominios.ESTADO_REGISTRO_INACTIVO);
		} else if (UtilDominios.ESTADO_TIPO_FECHA_CASO_ENVIO_ARCHIVO_CENTRAL.equals(tipoFecha)) {
			fechaCaso.setFecha(
					dto.getFechaEnvioArchivoCentral() != null ? dto.getFechaEnvioArchivoCentral() : fechaValidar);
			fechaCaso.setEstadoRegistro(dto.getFechaEnvioArchivoCentral() != null ? UtilDominios.ESTADO_REGISTRO_ACTIVO
					: UtilDominios.ESTADO_REGISTRO_INACTIVO);
		}

		if (fechaCaso.getFecha() != null) {
			FechaCasoPK fechaEntregaPK = new FechaCasoPK();
			fechaEntregaPK.setTipoFecha(tipoFecha);
			fechaEntregaPK.setIdCaso(idCaso);
			fechaCaso.setFechaCasoPK(fechaEntregaPK);
			fechaCaso.setFechaUltimaModificacion(new Date());
			fechaCaso.setIdUsuarioModificacion(String.valueOf(dto.getIdUsuario()));

			manejadorFechaCaso.actualizarFechaCaso(fechaCaso);
		}
	}

	public boolean validarValorPretenciones(BigDecimal valor, String tipoCuantia) {
		BigDecimal smlv = new BigDecimal(manejadorParametrosGenerales.buscar(UtilConstantes.SMLMV).getValorNumerico());
		BigDecimal separadorCuantia = smlv.multiply(UtilConstantes.SEPARADOR_TIPO_CUANTIA);
		switch (tipoCuantia) {
		case UtilDominios.TIPO_CUANTIA_MAYOR:
			return valor.compareTo(separadorCuantia) >= 0;
		case UtilDominios.TIPO_CUANTIA_MENOR:
			return valor.compareTo(separadorCuantia) < 0;
		default:
			return true;
		}

	}

	@Override
	public PagoCaso crearCaso(Caso caso, PagoCaso pago) {
		Caso casoCreado = manejadorCaso.actualizarCaso(caso);
		Long idCaso = casoCreado.getIdCaso();
		carpetaFacade.generarCuadernos(idCaso);
		pago.setIdCaso(casoCreado.getIdCaso());
		return pago;
	}

	public Object validarPactoRegistrado(Long id) {
		Object existePacto = true;
		Caso caso = manejadorCaso.buscar(id);
		if (caso.getTipoPacto() == null || caso.getDescripcionPacto() == null || caso.getDocumentoPacto() == null) {
			existePacto = false;
		} else {

			caso = (new CasoDTO()).transformarEntidadConDependenciasNombramiento(caso);
			manejadorCaso.getEntityManager().detach(caso);
			List<NombramientoPersona> listaNombramiento = new ArrayList<NombramientoPersona>();

			if (caso.getNombramientoPersonaList() != null && !caso.getNombramientoPersonaList().isEmpty()) {

				for (NombramientoPersona nombramientoFor : caso.getNombramientoPersonaList()) {
					if (UtilDominios.ESTADO_REGISTRO_CLAVE_ACTIVO.equals(nombramientoFor.getEstadoRegistro())) {
						listaNombramiento.add(nombramientoFor);

					}
				}
				caso.setNombramientoPersonaList(listaNombramiento);
			}

			existePacto = transformarEntidadConDependenciasN(caso);
		}

		return existePacto;
	}

	@Override
	public List<CasoDTO> obtenerEntidadesPorEtapa(String etapa) {
		return manejadorCaso.obtenerCasosPorEtapa(etapa);
	}

	/**
	 * Listar casos asignados Consulta los casos asignados al funcionario que se
	 * especifica en el DTO y aplicando los filtros que se pasan como parametro.
	 * 
	 * @param filtros
	 * @param idRolIC del usuario que está realizando la consulta
	 * @return
	 */
	@Override
	public List<CasoAsignadoDTO> consultarCasosAsignados(FiltroCasosAsignadosDTO filtros, String idRol) {

		List<CasoAsignadoDTO> casosAsignadosDTO = new ArrayList<>();

		try {
			String nombreRol = manejadorRol.consultarNombreRolPorId(Long.valueOf(idRol));
			filtros.setNombreRol(nombreRol);
			boolean noPrestadorDeServicio = !(manejadorRol.validarRolEsPrestadorDeServicio(nombreRol));

			casosAsignadosDTO = manejadorCaso.consultarCasosAsignados(filtros, noPrestadorDeServicio,
					esParte(nombreRol));
		} catch (SimascException e) {
			throw SIMASCNegocioExcepcion.transformTopException(e);
		}

		return casosAsignadosDTO;
	}

	@Override
	public void crearEventoRutaCaso(Caso caso, EventoDTO eventoDTO) {
		Evento evento = new Evento();
		evento.setCaso(caso);
		evento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		evento.setFechaEvento(new Date());
		evento.setFechaUltimaModificacion(new Date());
		// SE DEJA QUEMADO EL USUARIO DE SIMASC
		evento.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		evento.setObservaciones(eventoDTO.getObservaciones());
		if (caso.getEstadoCaso().equals(UtilDominios.ESTADO_CASO_CREADO))
			evento.setTipoEvento(UtilDominios.TIPO_EVENTO_CREACION_PACTO_ARBITRAL);
		else
			evento.setTipoEvento(caso.getEstadoCaso());
		evento.setIdCaso(caso.getIdCaso());
		manejadorEvento.crear(evento);

	}

	@Override
	public List<CasoAsignadoDTO> consultarCasosAsignadosDigitalizador(FiltroCasosAsignadosDTO filtros) {

		List<CasoAsignadoDTO> casosAsignadosDTO;
		try {
			casosAsignadosDTO = manejadorCaso.consultarCasosAsignadosDigitalizador(filtros);
		} catch (SimascException e) {
			throw SIMASCNegocioExcepcion.transformTopException(e);
		}

		return casosAsignadosDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade#
	 * actualizarCasoDocumentos(com.ccb.simasc.transversal.entidades.Caso)
	 */
	@Override
	public void actualizarCasoDocumentos(Caso caso) {
		Servicio servicio = manejadorServicio.buscar(caso.getIdServicio());
		for (Documento documento : caso.getDocumentoList()) {
			if (documento.isNuevo()) {
				documento.setTipoArchivo(UtilDominios.TIPO_ARCHIVO_PDF);
			}
		}
		this.actualizar(caso);

		for (Documento documento : caso.getDocumentoList()) {
			if (documento.isNuevo() && (UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS.equalsIgnoreCase(servicio.getTipo())
					|| UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA.equalsIgnoreCase(servicio.getTipo())
					|| UtilDominios.TIPO_SERVICIO_INTERNACIONAL.equalsIgnoreCase(servicio.getTipo()))) {
				documentoFacade.crearEvento(documento);
			}
		}

	}

	@Override
	public void adicionarSuspension(SuspenderDTO suspenderDTO) {
		try {
			Caso caso = manejadorCaso.buscarCasoActivo(suspenderDTO.getIdCaso());
			if (caso.getEtapa().equals(UtilDominios.ETAPA_CASO_PREARBITRAL)) {
				caso.setEstadoCaso(UtilDominios.ESTADO_CASO_SUSPENDIDO);
				caso.setFechaEstado(new Date());
				caso.setFechaUltimaModificacion(new Date());

				/* Cerrar eventos anteriores */
				for (int i = 0; i < caso.getEventoList().size(); i++) {

					Evento evInicial = null;
					Evento evSiguiente = null;

					if (caso.getEventoList().get(i).getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)
							&& (caso.getEventoList().get(i).getTipoEvento().equals(UtilDominios.ESTADO_CASO_SUSPENDIDO)
									|| caso.getEventoList().get(i).getTipoEvento()
											.equals(UtilDominios.ESTADO_CASO_SUSPENDIDO_POR_REQUERIMIENTO))) {

						evInicial = caso.getEventoList().get(i);

						if (i < caso.getEventoList().size() - 1)
							evSiguiente = caso.getEventoList().get(i + 1);

						evInicial.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
						manejadorEvento.actualizar(evInicial);

						if (evSiguiente != null
								&& evSiguiente.getTipoEvento().equals(UtilDominios.ESTADO_CASO_EN_PROCESO)
								&& evSiguiente.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
							evSiguiente.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
							manejadorEvento.actualizar(evSiguiente);
						}
					}
				}

				String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO180.toString());
				if (suspenderDTO.getObservacion().indexOf(mensaje) == -1) {
					eventoFacade.registrarEvento(caso, suspenderDTO.getCodigoSuspension(),
							suspenderDTO.getObservacion() + " " + suspenderDTO.getNombreParte(),
							UtilConstantes.USUARIO_DEFECTO_SIMASC, suspenderDTO.getFechaInicial(), null);
				} else {
					eventoFacade.registrarEvento(caso, suspenderDTO.getCodigoSuspension(),
							suspenderDTO.getObservacion() + " " + mensaje + " " + suspenderDTO.getNombreParte(),
							UtilConstantes.USUARIO_DEFECTO_SIMASC, suspenderDTO.getFechaInicial(), null);
				}
				if (suspenderDTO.getFechaFinal() != null) {
					if (suspenderDTO.getObservacion().indexOf(mensaje) == -1) {
						eventoFacade.registrarEvento(caso, UtilDominios.ESTADO_CASO_EN_PROCESO,
								suspenderDTO.getObservacion() + " " + suspenderDTO.getNombreParte(),
								UtilConstantes.USUARIO_DEFECTO_SIMASC, suspenderDTO.getFechaFinal(), null);
					} else {
						eventoFacade.registrarEvento(caso, UtilDominios.ESTADO_CASO_EN_PROCESO,
								suspenderDTO.getObservacion() + " " + mensaje + " " + suspenderDTO.getNombreParte(),
								UtilConstantes.USUARIO_DEFECTO_SIMASC, suspenderDTO.getFechaFinal(), null);
					}
				}
			}
		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
	}

	@Override
	public void modificarSuspension(SuspenderDTO suspenderDTO) {
		try {
			Evento eventoUno = manejadorEvento.buscar(suspenderDTO.getIdEventoInicial());
			Evento eventoDos;

			eventoUno.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
			eventoUno.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			eventoUno.setFechaUltimaModificacion(new Date());
			manejadorEvento.actualizarEvento(eventoUno);

			if (null != suspenderDTO.getIdEventoFinal()) {
				eventoDos = manejadorEvento.buscar(suspenderDTO.getIdEventoFinal());
				eventoDos.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
				eventoDos.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
				eventoDos.setFechaUltimaModificacion(new Date());
				manejadorEvento.actualizarEvento(eventoDos);
			}

			this.adicionarSuspension(suspenderDTO);
		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
	}

	@Override
	public void cerrarCaso(CasoCerradoDTO casoCerradoDTO, ContextoDeSesion cs) {
		try {

			Caso caso = manejadorCaso.buscarCasoActivo(casoCerradoDTO.getIdCaso());

			try {
				// uso de cerrar caso rest /cases
				ParametrosGenerales urlServCase = parametrosGeneralesFacade
						.consultarPorCodigo(UtilConstantes.PARAMETRO_CODIGO_URL_SERV_CASES);
				String url = urlServCase.getValorTexto();
				String json = "{\"idCaso\":\"" + caso.getIdCaso() + "\"}";
				StringEntity params = new StringEntity(json);

				String token = almacenamientoDocumentosFacade.getClientCredentials();
			
				CloseableHttpClient httpClient = HttpClients.createDefault();
				HttpPost httpPost = new HttpPost(url);
				httpPost.addHeader("content-type", "application/json");
				httpPost.addHeader("Authorization", "Bearer " + token);
				httpPost.setEntity(params);
				HttpResponse response = httpClient.execute(httpPost);
				if (response.getStatusLine().getStatusCode() == 201) {
					String resul = new BasicResponseHandler().handleResponse(response);
					logger.info(resul);
				}
				// fin de uso de cerrar caso rest
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

			if (!caso.getEstadoCaso().equals(UtilDominios.ESTADO_CASO_CERRADO)) {
				caso.setEstadoCaso(UtilDominios.ESTADO_CASO_CERRADO);
				caso.setFechaEstado(new Date());
				caso.setFechaUltimaModificacion(new Date());
				caso.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
				caso.setMotivoCierre(casoCerradoDTO.getMotivoCierre());
				manejadorCaso.actualizarCaso(caso);

				List<String> metodoNombramiento = Arrays.asList(UtilDominios.METODO_NOMBRAMIENTO_SORTEO);
				List<RolPersonaCaso> arbitrosCaso = manejadorRolPersonaCaso.consultarArbitros(caso.getIdCaso(), null,
						metodoNombramiento, null);

				if (!caso.getPreseleccion() || (caso.getPreseleccion() && caso.getQuienPreselecciona() != null
						&& !caso.getQuienPreselecciona().equals(UtilDominios.PRESELECCIONADO_POR_LAS_PARTES))) {
						
						for (RolPersonaCaso it1 : arbitrosCaso) {
						if (it1.getEstado().equalsIgnoreCase(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO)
								|| it1.getEstado().equalsIgnoreCase(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR)
								|| it1.getEstado().equalsIgnoreCase(UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO)) {
							it1.setMotivoInactivacion(UtilDominios.ESTADO_CASO_CERRADO);
							it1.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
							it1.setFechaUltimaModificacion(new Date());
							manejadorRolPersonaCaso.actualizar(it1);

							eventoRolPersonaCasoFacade.crearEventoRolPersonaCaso(UtilDominios.ESTADO_REGISTRO_INACTIVO,
									UtilDominios.TIPO_EVENTO_CASO_CERRADO, new Date(),
									UtilDominios.ESTADO_REGISTRO_ACTIVO, it1.getRol().getIdRol(),
									it1.getPersona().getIdPersona(), casoCerradoDTO.getIdCaso());

							if (it1.getEstado().equalsIgnoreCase(UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO)) {
														
								boolean esUltimoCaso = manejadorRolPersonaCaso.ultimoCasoDesignadoSuplente(it1.getPersona().getIdPersona(), casoCerradoDTO.getIdCaso());
								if (esUltimoCaso && manejadorParametroServicioSorteo.bloqueaSuplente(casoCerradoDTO.getIdCaso())) {
									rolPersonaCasoFacade.habilitarArbitro(casoCerradoDTO.getIdCaso(),
											it1.getPersona().getIdPersona(), UtilDominios.ESTADO_CASO_CERRADO);
								}
							} else {
								if (manejadorParametroServicioSorteo.liberaArbitrosCierre(casoCerradoDTO.getIdCaso())) {
									rolPersonaCasoFacade.habilitarArbitro(casoCerradoDTO.getIdCaso(),
											it1.getPersona().getIdPersona(), UtilDominios.ESTADO_CASO_CERRADO);									
								}
							}
						}

						// revisa si es un prestador de servicio y envia un correo
						// si hay un arbitro en inactivo litigante.
						if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(it1.getEstadoRegistro())
								&& (UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO.equals(it1.getRol().getNombre())
										|| UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE
												.equals(it1.getRol().getNombre())
										|| UtilDominios.ROL_PERSONA_APODERADO_ACREEDOR_RECUPERACION
												.equals(it1.getRol().getNombre())
										|| UtilDominios.ROL_PERSONA_APODERADO_DEUDOR_RECUPERACION
												.equals(it1.getRol().getNombre()))) {

							estadoPersonaTipoServicioFacade.notificacionArbitroLitigante(
									it1.getPersona().getIdPersona(), UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA,
									UtilDominios.ESTADO_ARBITROS_INACTIVO_LITIGANDO, it1.getCaso().getIdCaso());
						}

						// finalizar rol arbitro social cuando se cierra un caso de arbitraje
						if (caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_ARBITRAJE)) {
							rolPersonaFacade.finalizarRolArbitroSocial(it1.getPersona().getIdPersona());
						}
					}
				}
				// Evento de caso cerrado
				eventoFacade.registrarEvento(caso, UtilDominios.TIPO_EVENTO_CASO_CERRADO,
						casoCerradoDTO.getObservaciones(), UtilConstantes.USUARIO_DEFECTO_SIMASC,
						casoCerradoDTO.getFechaCierre() != null ? casoCerradoDTO.getFechaCierre() : new Date(),
						UtilDominios.ESTADO_REGISTRO_ACTIVO);

				if (casoCerradoDTO.getFechaEntrega() != null) {
					FechaCaso fechaCasoCAC = new FechaCaso();
					fechaCasoCAC.setCaso(caso);
					fechaCasoCAC.getFechaCasoPK().setIdCaso(caso.getIdCaso());
					fechaCasoCAC.getFechaCasoPK()
							.setTipoFecha(UtilDominios.ESTADO_TIPO_FECHA_CASO_ENTREGA_EXPEDIENTE_CAC);
					fechaCasoCAC.setFecha(casoCerradoDTO.getFechaCierre());
					fechaCasoCAC.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
					fechaCasoCAC.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
					fechaCasoCAC.setFechaUltimaModificacion(new Date());
					if(manejadorFechaCaso.consultaFechaPorCasoTipo(caso.getIdCaso(), UtilDominios.ESTADO_TIPO_FECHA_CASO_ENTREGA_EXPEDIENTE_CAC) != null){
						manejadorFechaCaso.actualizar(fechaCasoCAC);
					}
					else{
						manejadorFechaCaso.crear(fechaCasoCAC);
					}	
				}

				FechaCaso fechaCasoCierre = new FechaCaso();
				fechaCasoCierre.setCaso(caso);
				fechaCasoCierre.getFechaCasoPK().setIdCaso(caso.getIdCaso());
				fechaCasoCierre.getFechaCasoPK().setTipoFecha(UtilDominios.ESTADO_TIPO_FECHA_CASO_CIERRE);
				fechaCasoCierre.setFecha(casoCerradoDTO.getFechaCierre());
				fechaCasoCierre.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
				fechaCasoCierre.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				fechaCasoCierre.setFechaUltimaModificacion(new Date());
				manejadorFechaCaso.crear(fechaCasoCierre);

				String rolNombre = manejadorRol.buscar(Long.parseLong(cs.getRolPrincipal())).getNombre();
				if (rolNombre.equalsIgnoreCase(UtilDominios.TIPO_ACTOR_CASO_SECRETARIO)) {
					String correo = correoServicioCaso(caso);
					ParametrosGenerarCartaDTO parametrosCarta = new ParametrosGenerarCartaDTO();
					List<InformacionFiltro> filtros = new ArrayList<InformacionFiltro>();
					InformacionFiltro filtro = new InformacionFiltro(TipoFiltro.EXACTO,
							PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_NOMBRE,
							UtilDominios.NOMBRE_PLANTILLA_CARTA_CIERRE_CASO);
					filtros.add(filtro);
					String plantilla = manejadorPlantillaCarta.consultar(filtros, null, null).get(0)
							.getIdPlantillaCarta().toString();
					List<Long> destinatarios = new ArrayList<Long>();
					for (RolPersonaCaso it2 : caso.getRolPersonaCasoList()) {
						if (it2.getRol().getNombre().equalsIgnoreCase(UtilDominios.ROL_PERSONA_ABOGADO) || it2.getRol()
								.getNombre().equalsIgnoreCase(UtilDominios.ROL_PERSONA_ASISTENTE_ARBITRO)) {
							destinatarios.add(it2.getRolPersonaCasoPK().getIdPersona());
						}
					}
					if (!destinatarios.isEmpty()) {
						parametrosCarta.setIdCaso(casoCerradoDTO.getIdCaso());
						parametrosCarta.setIdAudiencia(null);
						parametrosCarta.setIdPlantilla(plantilla);
						parametrosCarta.setIndicadorNotificacion("SI");
						parametrosCarta.setListaIdNotificados(destinatarios);
						parametrosCarta.setListaIdInvitados(new ArrayList<Long>());
						List<CartaPersona> cartas = cartaPersonaFacade.generarCarta(parametrosCarta, null);
						for (CartaPersona cartaPersona : cartas) {
							cartaPersona.setCorreoElectronico(correo);
						}

						if (!cartas.isEmpty())
							cartaPersonaFacade.enviarCarta(cartas.get(0), false, true);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());			
			String msg = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO120.toString());
			throw new SIMASCNegocioExcepcion(msg);
		}
	}

	/**
	 * Metodo que retorna el tipo de correro dependiendo que el servicio del caso
	 * 
	 * @param caso
	 * @return
	 */
	private String correoServicioCaso(Caso caso) {
		String correoServicio = "";
		List<ParametroDeServicio> parametroDeCorreoServicioCaso = manejadorParametroDeServicio
				.consultarParametrosDeServicio(UtilDominios.PARAMETRO_DE_SERVICIO_CORREO_SERVICIO_CASO,
						caso.getIdServicio());
		if (parametroDeCorreoServicioCaso != null
				&& !parametroDeCorreoServicioCaso.isEmpty()) {
			correoServicio = parametroDeCorreoServicioCaso.get(0).getValor();
		}
		
		return correoServicio;
		
	}
	/**	Servicio servicio;
		ParametrosGenerales parametro;
		servicio = manejadorServicio.buscar(caso.getIdServicio());
		
		String codigo = UtilConstantes.CODIGO_SERVICIO_CONCILIACION.equals(servicio.getNombre())
				? UtilConstantes.CODIGO_CORREO_ARBITRAJE
				: UtilConstantes.CODIGO_CORREO_CONCILIACION;
		parametro = manejadorParametrosGenerales.buscar(codigo);
		return parametro.getValorTexto();**/
			
	

	@Override
	public String obtenerNombreCaso(Long idCaso) {
		return manejadorCaso.consultarNombreCaso(idCaso);
	}

	@Override
	public void actualizarTerminos(DatosBasicosCasoDTO datosBasicos) {
		Caso caso = manejadorCaso.buscar(datosBasicos.getCaso().getIdCaso());
		Integer diasProfLaudos = caso.getDiasParaProferirLaudo();

		if (diasProfLaudos == null || datosBasicos.getDiasAntesCierreProferirLaudo() != diasProfLaudos) {
			caso.setDiasParaProferirLaudo(datosBasicos.getDiasAntesCierreProferirLaudo());
			caso.setFechaUltimaModificacion(new Date());
			caso.setIdUsuarioModificacion(datosBasicos.getIdUsuario().toString());
			manejadorCaso.actualizar(caso);
		}

		FechaCaso inicioConteoTerminos = manejadorFechaCaso.consultaFechaCaso(datosBasicos.getCaso().getIdCaso(),
				UtilDominios.ESTADO_TIPO_FECHA_CASO_INICIO_CONTEO_TERMINOS);
		Date fecha = inicioConteoTerminos == null ? null : inicioConteoTerminos.getFecha();
		if ((fecha != null || datosBasicos.getFechaInicioConteoTerminos() != null)
				&& !(fecha != null && datosBasicos.getFechaInicioConteoTerminos() != null
						&& fecha.compareTo(datosBasicos.getFechaInicioConteoTerminos()) == 0)) {
			fechaCasoFacade.registrarFechaCaso(datosBasicos.getFechaInicioConteoTerminos(),
					UtilDominios.ESTADO_TIPO_FECHA_CASO_INICIO_CONTEO_TERMINOS, datosBasicos.getCaso().getIdCaso(),
					datosBasicos.getIdUsuario().toString());
		}
	}

	@Override
	public Caso actualizarCaso(Caso caso, String usuarioMoficacion) {
		List<NombramientoPersona> lstNombramientos = caso.getNombramientoPersonaList();
		caso.setNombramientoPersonaList(null);
		if(!caso.getPreseleccion()){
			manejadorCaso.eliminarDatosPreseleccion(caso.getIdCaso());
		}
		this.actualizar(caso);
		Caso casoTipoPeriodicidad = this.buscar(caso.getIdCaso());
		if (!UtilDominios.PERIODICIDAD_TERMINOS_DIA.equals(casoTipoPeriodicidad.getPeriodicidadTerminos())) {
			casoTipoPeriodicidad.setTipoPeriodicidadTerminos(null);
		}
		this.actualizar(casoTipoPeriodicidad);
		List<NombramientoPersona> lstNombramientoAntigua = this.buscar(caso.getIdCaso()).getNombramientoPersonaList();
		for (NombramientoPersona nombramiento : lstNombramientoAntigua) {
			nombramiento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
			nombramientoPersonaFacade.actualizar(nombramiento);
		}

		for (NombramientoPersona nombramientoPersonas : lstNombramientos) {
			if (nombramientoPersonas.getCantFuncionariosPrincipales() > 0
					&& nombramientoPersonas.getManejoDeSuplencia() != null) {
				Long idPersonaTercero = (UtilDominios.NOMBRAMIENTO_POR_UN_TERCERO
						.equals(nombramientoPersonas.getMetodoDeNombramiento())
						|| UtilDominios.NOMBRAMIENTO_POR_LA_AUTORIDAD_JUDICIAL
								.equals(nombramientoPersonas.getMetodoDeNombramiento()))
										? nombramientoPersonas.getIdPersona()
										: null;
				List<RolPersonaCaso> rolesPersonaCaso = manejadorRolPersonaCaso
						.consultarPrestadoresPorMetodoNombramientoTercero(nombramientoPersonas.getIdCaso(),
								nombramientoPersonas.getMetodoDeNombramiento(), idPersonaTercero);
				for (RolPersonaCaso rolPersonaCaso : rolesPersonaCaso) {
					rolPersonaCaso.setTipoSuplencia(nombramientoPersonas.getManejoDeSuplencia());
					rolPersonaCaso.setIdUsuarioModificacion(usuarioMoficacion);
					rolPersonaCaso.setFechaUltimaModificacion(new Date());
				}
				manejadorRolPersonaCaso.actualizarLista(rolesPersonaCaso);
				nombramientoPersonas.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
				nombramientoPersonas.setFechaUltimaModificacion(new Date());
				nombramientoPersonas.setIdCaso(caso.getIdCaso());
				nombramientoPersonaFacade.crearNombramiento(nombramientoPersonas);
			}
		}

		if (caso.getTipoPacto() != null) {
			EventoDTO eventoDTO = new EventoDTO();
			eventoDTO.setObservaciones("Se ha actualizado el pacto arbitral");
			this.crearEventoRutaCaso(caso, eventoDTO);
		}

		return caso;
	}

	@Override
	public CasoDTO actualizarEtapaCaso(Long idCaso, String etapaCaso, Long plantillaCarta, String usuarioMoficacion)
			throws SIMASCNegocioExcepcion {

		Caso caso = transformarEntidadSinDependencias(buscar(idCaso));
		PlantillaCarta plantilla = manejadorPlantillaCarta.buscar(plantillaCarta);
		if (UtilConstantes.ID_SERVICIO_ARBITRAJE_INTERNACIONAL.equals(caso.getIdServicio().toString())
				&& caso.getEtapa().equals(UtilDominios.ETAPA_CASO_PREARBITRAL)
				&& plantilla.getNombre().equals(UtilDominios.NOMBRE_PLANTILLA_CARTA_NOTIFICACION_ACEPTACION_ARBITROS)) {

			// Se obtienen las personas con rol apoderado demandante, apoderado demandado y
			// repersentante de parte asociadas al caso
			List<RolPersonaCaso> listaPartes = manejadorRolPersonaCaso.consultarPersonasoPorRoleEstado(idCaso,
					Arrays.asList(UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE, UtilDominios.ROL_PERSONA_PARTE_DEMANDADA),
					Arrays.asList(UtilConstantes.NO_APLICA), false);

			// Se obtienen las personas a las que se les ha enviado correo
			List<Long> listaPersonasCorreoEnviado = manejadorCorreoRolPersonaCaso
					.consultaEnvioCorreoNotificacionAceptacionArbitrosAcuse(idCaso);

			// se verifica si el caso es de arbitraje internacional, estaba en etapa
			// prearbitral, se envio la plantilla de notificacion de arbitros,
			// se envió correo a todas las partes y dicho correo efectivamente se envió
			if (!listaPartes.isEmpty() && !listaPersonasCorreoEnviado.isEmpty()) {
				if (listaPartes.size() == listaPersonasCorreoEnviado.size()) {
					caso.setEtapa(etapaCaso);
					actualizar(caso);
					return transformarSinDependencias(caso);
				}
			}
		}

		if (plantilla.getNombre()
				.equals(UtilDominios.NOMBRE_PLANTILLA_CARTA_ENTREGA_EXPEDIENTE_ARBITRO_RECUPERACION_EMPRESARIAL)) {
			caso.setEtapa(etapaCaso);
			actualizar(caso);
			rolPersonaCasoFacade.liberarSuplentes(caso.getIdCaso());
			return transformarSinDependencias(caso);

		}

		return new CasoDTO();
	}

	/**
	 * Autogenera el nombre a un caso dado, este nombre se conforma por las partes y
	 * contrapartes del caso.
	 * 
	 * @param idCaso
	 */
	@Override
	public String generarNombreCaso(Long idCaso) {

		// define las variables locales
		Caso caso;
		String nombreCaso = UtilConstantes.CADENA_VACIA;

		// Consulta el caso por ID
		caso = manejadorCaso.buscar(idCaso);

		// Obtiene el servicio-materia del caso
		ServicioMateria servicioMateria = caso.getServicioMateria();

		// Obtiene el servicio-materia del caso
		Servicio servicio;
		if (servicioMateria != null) {
			servicio = servicioMateria.getServicio();
		} else {
			servicio = manejadorServicio.buscar(caso.getIdServicio());
		}

		// Calcula el nombre con base en los datos del caso
		if (servicio != null && servicio.getTipo() != null)
			nombreCaso = generarNombreCasoPorTipoServicio(caso, servicio.getTipo());

		// Asigna el nombre al caso
		caso.setNombre(nombreCaso);
		// Persiste el caso
		manejadorCaso.actualizar(caso);
		return caso.getNombre();
	}

	/**
	 * Método encargado de generar el nombre de un caso dependiendo del tipo de
	 * servicio
	 * 
	 * @param caso
	 * @param tipoServicio
	 * @return
	 */
	private String generarNombreCasoPorTipoServicio(Caso caso, String tipoServicio) {
		String nombreCaso = UtilConstantes.CADENA_VACIA;
		String nombreDemandados = null;
		switch (tipoServicio) {
		case UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA:

			if (caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_RECUPERACION_EMPRESARIAL)) {
				nombreCaso = nombreCaso
						.concat(obtenerParticipantesArbitraje(caso, UtilDominios.ROL_PERSONA_DEUDOR_RECUPERACION));
				nombreDemandados = obtenerParticipantesArbitraje(caso, UtilDominios.ROL_PERSONA_ACREEDOR_RECUPERACION);
				if (!UtilConstantes.CADENA_VACIA.equals(nombreDemandados)) {
					nombreCaso = nombreCaso.concat(VS);
					nombreCaso = nombreCaso.concat(nombreDemandados);
				}
			} else {
				nombreCaso = nombreCaso
						.concat(obtenerParticipantesArbitraje(caso, UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE));
				nombreDemandados = obtenerParticipantesArbitraje(caso, UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);
				if (!UtilConstantes.CADENA_VACIA.equals(nombreDemandados)) {
					nombreCaso = nombreCaso.concat(VS);
					nombreCaso = nombreCaso.concat(nombreDemandados);
				}

			}

			break;

		case UtilDominios.TIPO_SERVICIO_INTERNACIONAL:
			nombreCaso = nombreCaso
			.concat(obtenerParticipantesArbitraje(caso, UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE));
			nombreDemandados = obtenerParticipantesArbitraje(caso, UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);
			if (!UtilConstantes.CADENA_VACIA.equals(nombreDemandados)) {
				nombreCaso = nombreCaso.concat(VS);
				nombreCaso = nombreCaso.concat(nombreDemandados);
			}
			break;

		case UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS:
			if (caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_CONCILIACION_MEDIACION)
					|| caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_INSOLVENCIA)) {
				nombreCaso = nombreCaso.concat(obtenerNombresParticipantes(caso, UtilDominios.ROL_PERSONA_DEUDOR));
				nombreCaso = nombreCaso.concat(", ");
				nombreCaso = nombreCaso.concat(obtenerNombresParticipantes(caso, UtilDominios.ROL_PERSONA_ACREEDOR));

			} else {
				nombreCaso = nombreCaso.concat(obtenerNombresParticipantes(caso, UtilDominios.ROL_PERSONA_CONVOCANTE));
				nombreCaso = nombreCaso.concat(", ");
				nombreCaso = nombreCaso.concat(obtenerNombresParticipantes(caso, UtilDominios.ROL_PERSONA_CONVOCADO));
			}

			break;
		case UtilDominios.TIPO_SERVICIO_CONVIVENCIA:
			if (caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_EQUIDAD)) {
				nombreCaso = nombreCaso.concat(obtenerNombresParticipantes(caso, UtilDominios.ROL_PERSONA_SOLICITANTE_EQUIDAD));
				nombreCaso = nombreCaso.concat(", ");
				nombreCaso = nombreCaso.concat(obtenerNombresParticipantes(caso, UtilDominios.ROL_PERSONA_CONVOCADO_EQUIDAD));
				logger.info("nombre caso equidad:"+ nombreCaso);
			}

			break;	
		default:
			nombreCaso = UtilConstantes.CADENA_VACIA;
			break;
		}

		return nombreCaso;
	}

	/**
	 * Dado un caso retorna el texto necesario para la conformación del nombre del
	 * caso, el cual esta dado por las personas que conforman la parte.
	 * 
	 * @param caso
	 * @param tipo
	 * @return
	 */
	private String obtenerNombresParticipantes(Caso caso, String tipo) {
		String nombres = "";
		int numeroPartes = 0;
		List<RolPersonaCaso> rolpersonaCasoActual = manejadorRolPersonaCaso
				.obtenerPersonasAsociadasACaso(caso.getIdCaso());

		for (RolPersonaCaso p : rolpersonaCasoActual) {
			Rol rolActual = manejadorRol.buscar(p.getRolPersonaCasoPK().getIdRol());
			if (rolActual.getNombre().equals(tipo)
					&& UtilDominios.ESTADO_REGISTRO_CLAVE_ACTIVO.equals(p.getEstadoRegistro())) {
				Persona personaActual = manejadorPersona.buscar(p.getRolPersonaCasoPK().getIdPersona());
				numeroPartes++;
				if (numeroPartes == 1) {
					nombres = nombres.concat(obtenerNombreParte(personaActual));
				} else if (numeroPartes == 2) {
					nombres = nombres.concat(" y ");
					nombres = nombres.concat(obtenerNombreParte(personaActual));
				} else if (numeroPartes == 3) {
					nombres = nombres.concat(" y otros ");
				} else {
					return nombres;
				}
			}
		}

		return nombres;
	}

	/**
	 * uno el nombre de una parte y lo regresa completo dependiendo del tipo de
	 * persona
	 * 
	 * @param personaActual
	 * @return
	 */
	private String obtenerNombreParte(Persona personaActual) {
		String segundoNombre = "";
		String primerApellido = "";
		String segundoApellido = "";

		String primerNombre = ((personaActual.getPrimerNombreORazonSocial() != null
				&& personaActual.getPrimerNombreORazonSocial().length() > 0)
						? personaActual.getPrimerNombreORazonSocial()
						: "");
		if (UtilDominios.TIPO_PERSONA_NATURAL.equals(personaActual.getTipoPersona())) {
			segundoNombre = ((personaActual.getSegundoNombre() != null && personaActual.getSegundoNombre().length() > 0)
					? " " + personaActual.getSegundoNombre()
					: "");
			primerApellido = ((personaActual.getPrimerApellido() != null
					&& personaActual.getPrimerApellido().length() > 0) ? " " + personaActual.getPrimerApellido() : "");
			segundoApellido = ((personaActual.getSegundoApellido() != null
					&& personaActual.getSegundoApellido().length() > 0) ? " " + personaActual.getSegundoApellido()
							: "");
		}

		return primerNombre + segundoNombre + primerApellido + segundoApellido;
	}

	/**
	 * crea el nombre de arbitrja con las reglas de nombramiento
	 * 
	 * @param caso
	 * @param rol
	 * @return
	 */
	private String obtenerParticipantesArbitraje(Caso caso, String rol) {
		StringBuilder nombresUnidos = new StringBuilder();
		List<Persona> persona = manejadorRolPersonaCaso.consultarPersonasPorRolCaso(caso.getIdCaso(), rol);
		if (persona != null && !persona.isEmpty()) {
			for (int i = 0; i < persona.size(); i++) {
				if (i == 0) {
					nombresUnidos.append(this.obtenerNombreParte(persona.get(i)));
				} else if (i == persona.size() - 1) {
					nombresUnidos.append(" y ").append(this.obtenerNombreParte(persona.get(i)));
				} else {
					nombresUnidos.append(", ").append(this.obtenerNombreParte(persona.get(i)));
				}
			}
		}
		return nombresUnidos.toString();
	}

	@Override
	public String agregarDocumentosNoRecibidos(CasoDTO caso) {
		try {
			Caso casoActual = manejadorCaso.buscar(caso.getIdCaso());
			casoActual.setDocumentosNoRecibidos(caso.getDocumentosNoRecibidos());
			manejadorCaso.actualizar(casoActual);
		} catch (Exception e) {
			logger.error(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString()), e);
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString()));
		}
		return UtilConstantes.TIPO_SESION_VALOR_OK;
	}

	@Override
	public boolean actualizarEstdoSuspension(Long idCaso) {
		List<Suspension> suspensiones = suspensionFacade.consultarSuspensionId(idCaso);
		Caso casoActual = manejadorCaso.buscar(idCaso);
		// valida que tenga suspensiones si no tiene, sale del metodo.
		if (suspensiones.isEmpty()) {
			return true;
		}
		boolean cambioDeEstado = false;
		boolean suspensioCaso = false;

		String estadoProvisional = UtilDominios.ESTADO_CASO_EN_PROCESO;
		String usuarioMoficacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		String tipoEvento = null;
		String observaciones = null;

		// revisa el estado que deberia tener del caso por las suspensiones que
		// tiene
		for (Suspension suspensionesFor : suspensiones) {
			Date fechaFinalFinDia = null;
			Date fechaIncialInicioDia = UtilOperaciones.obtenerFechaComienzoDelDia(suspensionesFor.getFechaInicial());

			if (suspensionesFor.getFechaFinal() != null) {
				fechaFinalFinDia = UtilOperaciones.obtenerFechaFinDelDia(suspensionesFor.getFechaFinal());
			}

			if (!suspensioCaso && fechaIncialInicioDia.compareTo(new Date()) <= 0
					&& (fechaFinalFinDia == null || fechaFinalFinDia.compareTo(new Date()) >= 0)) {
				suspensioCaso = true;

				// transforma el tipo de caso y evento al estandar.
				if (UtilDominios.TIPO_SUSPENSION_SUSPENSION_PREARBITRAL.equals(suspensionesFor.getTipo())
						|| UtilDominios.TIPO_SUSPENSION_SUSPENSION_ARBITRAL.equals(suspensionesFor.getTipo())) {
					estadoProvisional = UtilDominios.ESTADO_CASO_SUSPENDIDO;
					tipoEvento = UtilDominios.ESTADO_CASO_SUSPENDIDO;

				} else {
					estadoProvisional = suspensionesFor.getTipo();
					tipoEvento = suspensionesFor.getTipo();

				}
			}
		}
		List<String> listaEstadosSuspendido = new ArrayList<String>();
		listaEstadosSuspendido.add(UtilDominios.ESTADO_CASO_SUSPENDIDO);
		listaEstadosSuspendido.add(UtilDominios.ESTADO_CASO_INTERRUMPIDO);
		listaEstadosSuspendido.add(UtilDominios.ESTADO_CASO_SUSPENDIDO_POR_REQUERIMIENTO);
		listaEstadosSuspendido.add(UtilDominios.ESTADO_CASO_INTERRUMPIDO);

		if (suspensioCaso && !listaEstadosSuspendido.contains(casoActual.getEstadoCaso())) {
			cambioDeEstado = true;
			// el caso deberia estar suspendido y esta activo
			observaciones = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO200.toString());

		} else if (!suspensioCaso && listaEstadosSuspendido.contains(casoActual.getEstadoCaso())) {
			// el caso deberia estar activo y esta inactivo
			cambioDeEstado = true;
			// selecciona el tipo de fin de suspension del caso.
			switch (casoActual.getEstadoCaso()) {
			case UtilDominios.ESTADO_CASO_SUSPENDIDO:
				tipoEvento = UtilDominios.TIPO_EVENTO_FIN_SUSPENSION_CASO;
				observaciones = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO201.toString());
				break;

			case UtilDominios.ESTADO_CASO_SUSPENDIDO_POR_REQUERIMIENTO:
				tipoEvento = UtilDominios.TIPO_EVENTO_FIN_SUSPENSION_REQUERIMIENTO;
				observaciones = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO202.toString());
				break;
			case UtilDominios.ESTADO_CASO_INTERRUMPIDO:
				tipoEvento = UtilDominios.TIPO_EVENTO_FIN_INTERRUPCION_CASO;
				observaciones = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO203.toString());
				break;

			default:
				tipoEvento = UtilDominios.TIPO_EVENTO_FIN_SUSPENSION_CASO;
				observaciones = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO204.toString());
				break;
			}

			// Obtener estado previo a la suspension
			List<Dominio> dominiosEstadosActivos = dominioFacade
					.obtenerDominiosHijos(UtilDominios.DOMINIO_ESTADO_CASO_PADRE, UtilDominios.ESTADO_PADRE_CASO_ACTIO);
			List<String> dominiosActivo = new ArrayList<>();

			for (Dominio stringDominioFor : dominiosEstadosActivos) {
				if (stringDominioFor.getDominioPK().getCodigo() != null) {
					dominiosActivo.add(stringDominioFor.getDominioPK().getCodigo());
				}
			}
			List<Evento> eventosActivos = manejadorEvento.consultarUltimoEventoPorEstados(idCaso, dominiosActivo);
			// en caso de no tener eventos de activacion deja el estado PRO (en
			// proceso)
			if (!eventosActivos.isEmpty()) {
				estadoProvisional = eventosActivos.get(0).getTipoEvento();
			} else {
				estadoProvisional = UtilDominios.ESTADO_CASO_EN_PROCESO;
			}
		}

		// actualizaciones comumes del caso y creacion del evento, si el caso
		// debe cambiar de estado.
		if (cambioDeEstado) {
			casoActual.setFechaEstado(new Date());
			casoActual.setFechaUltimaModificacion(new Date());
			casoActual.setIdUsuarioModificacion(usuarioMoficacion);
			casoActual.setEstadoCaso(estadoProvisional);
			manejadorCaso.actualizar(casoActual);

			eventoFacade.registrarEvento(casoActual.getIdCaso(), tipoEvento, observaciones, usuarioMoficacion,
					new Date(), UtilDominios.ESTADO_REGISTRO_ACTIVO);

		}
		return true;
	}

	@Override
	public void inactivarCasoRadicacion(Caso caso) {
		Caso c = manejadorCaso.buscar(caso.getIdCaso());
		c.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
		manejadorCaso.actualizar(c);
	}

	@Override
	public boolean actualizarCasosEstdoSuspension() {
		List<Caso> casosSuspensiones = manejadorCaso.casoActivosConSupensiones();
		for (Caso casosFor : casosSuspensiones) {
			actualizarEstdoSuspension(casosFor.getIdCaso());
		}
		return true;
	}

	/**
	 * Metodo que pone al caso seleccionado en NoAcreditado
	 * 
	 * @param idcaso
	 * @return
	 */
	@Override
	public void pactoNoAcreditado(Long idCaso, String usuarioMoficacion) {
		Caso casoActual = manejadorCaso.buscar(idCaso);
		String tipoEvento = UtilDominios.TIPO_EVENTO_NO_ACREDITA_PACTO_ARBITRAL;
		casoActual.setTipoPacto(UtilDominios.TIPO_PACTO_NO_ACREDITADO);
		casoActual.setDescripcionPacto(UtilDominios.TIPO_PACTO_NO_ACREDITADO);
		casoActual.setDocumentoPacto(UtilDominios.TIPO_PACTO_NO_ACREDITADO);
		manejadorCaso.actualizar(casoActual);
		eventoFacade.registrarEvento(casoActual.getIdCaso(), tipoEvento, "", usuarioMoficacion, new Date(),
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
	}

	@Override
	public void modificarEstadoCaso(Long idCaso, String estadoCaso) {
		Caso caso = manejadorCaso.buscar(idCaso);
		caso.setEstadoCaso(estadoCaso);
		caso.setFechaEstado(new Date());
		manejadorCaso.actualizar(caso);
	}

	/**
	 * consulta si el rol enviado es una parte de la aplicacion
	 * 
	 * @param nombreRol
	 * @return
	 */
	private boolean esParte(String nombreRol) {
		boolean esParte = false;

		List<DominioBasicoDTO> dominios = rolFacade
				.consultarRolesPorClasificador(UtilDominios.AGRUPADOR_ROL_PERSONA_PARTES_APLICACION);
		if (dominios.isEmpty()) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR915.toString())); // ERROR532
		}
		for (DominioBasicoDTO dominioFor : dominios) {
			if (nombreRol.equals(dominioFor.getCodigo())) {
				esParte = true;
				break;
			}
		}
		return esParte;
	}

	@Override
	public Caso creacionCasoTramiteOrdinario(Long idOrdenPago, String idUsuario, PagoCasoDTO pagoCaso,
			String realPath) {

		Caso caso = migracionSolicitudServicioACaso(idOrdenPago, idUsuario);
		generarNombreCaso(caso.getIdCaso());
		Servicio servicioDelCaso = manejadorServicio.buscar(caso.getIdServicio());
		if (UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA.equals(servicioDelCaso.getTipo()) || UtilDominios.TIPO_SERVICIO_INTERNACIONAL.equals(servicioDelCaso.getTipo())) {
			Rol rolReparto = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_ABOGADO);
			rolPersonaCasoFacade.reparto(caso, rolReparto);
		} else {
			RadicarCasoConvenioDTO radicacion = crearObjetoRadicacion(caso);
			String nombreUsuario = UtilConstantes.USUARIO_DEFECTO_SIMASC;
			// guardado documento radicacion de caso
			guardarDocumentoRadicacion(radicacion, caso, nombreUsuario, realPath);

			String fallo = llamadoReparto(radicacion, nombreUsuario);
			if (fallo != null) {
				try {
					cambiarEstadoCaso(caso.getIdCaso(), UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION, new Date(),
							UtilDominios.TIPO_EVENTO_PENDIENTE_POR_DESIGNACION, String.format(
									MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO278.toString())));
				} catch (EstadosCasoException e) {
					logger.error(e.getMensaje());
				}
			}
		}
		return caso;
	}

	private RadicarCasoConvenioDTO crearObjetoRadicacion(Caso caso) {
		SolicitudServicio solicitud = manejadorSolicitudServicio.buscar(caso.getIdSolicitudServicio());
		List<FormularioParteDTO> partes = personaSolicitudFacade
				.consultarPartesSolicitudServicio(caso.getIdSolicitudServicio());
		RadicarCasoConvenioDTO radicacion = new RadicarCasoConvenioDTO();
		radicacion.setCaso(new Caso());
		radicacion.setParteInfo(partes);

		radicacion.getCaso().setIdCaso(caso.getIdCaso());
		radicacion.getCaso().setHechos(caso.getHechos());
		radicacion.getCaso().setPretensiones(caso.getPretensiones());
		radicacion.getCaso().setInicioDeConflicto(caso.getInicioDeConflicto());
		radicacion.getCaso().setIdLugarDelConflicto(caso.getIdLugarDelConflicto());
		radicacion.getCaso().setParteQueSolicitaServicio(caso.getParteQueSolicitaServicio());
		radicacion.getCaso().setIdMateria(caso.getIdMateria());
		radicacion.getCaso().setIdSede(caso.getIdSede());
		radicacion.getCaso().setEstadoCaso(caso.getEstadoCaso());
		radicacion.getCaso().setTipoCuantia(caso.getTipoCuantia());

		if (!UtilDominios.TIPO_CUANTIA_CONCILIACION_INDETERMINADO.equals(caso.getTipoCuantia()))
			radicacion.setCuantia(Long.valueOf(caso.getValorPretensiones()));
		String rol = UtilDominios.ROL_PERSONA_CONCILIADOR;
		if(caso!=  null && caso.getIdServicio()!= null && caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_EQUIDAD))
		rol = UtilDominios.ROL_PERSONA_CONCILIADOR_EQUIDAD;
		List<PersonaSolicitud> personaSolicitudList = manejadorPersonaSolicitud
				.consultarPersonaSolicitud(caso.getIdSolicitudServicio(), null,  rol);

		PersonaSolicitud personaSolicitud = null;
		if (!personaSolicitudList.isEmpty()) {
			personaSolicitud = personaSolicitudList.get(0);
		}

		if (personaSolicitud != null)
			radicacion.setConciliador(personaSolicitud.getPersonaSolicitudPK().getIdPersona());
		radicacion.setTipoAudiencia(solicitud.getTipoDeAudiencia());
		radicacion.setFechaAudiencia(solicitud.getFechaInicioAudiencia());
		radicacion.setFechaHoraInicio(solicitud.getFechaInicioAudiencia());
		radicacion.setFechaHoraFin(solicitud.getFechaFinAudiencia());

		return radicacion;
	}

	/**
	 * radica un caso a partir de una solicitud
	 * 
	 * @param idSolicitudServicio
	 * @param idUsuario
	 */
	private Caso migracionSolicitudServicioACaso(Long idOrdenPago, String idUsuario) {

		SolicitudServicio solicitudActual;
		List<SolicitudServicio> solicitudes = new ArrayList<SolicitudServicio>();

		if (idOrdenPago != null) {
			List<InformacionFiltro> filtros = new ArrayList<InformacionFiltro>();
			filtros.add(new InformacionFiltro(TipoFiltro.EXACTO,
					SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_ID_ORDEN_DE_PAGO, idOrdenPago));
			filtros.add(new InformacionFiltro(TipoFiltro.EXACTO,
					SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO));
			solicitudes = manejadorSolicitudServicio.consultar(filtros, null, null);

		}

		if (solicitudes == null || solicitudes.isEmpty()) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR585.toString()));
		} else {
			solicitudActual = solicitudes.get(0);
		}
		Servicio servicio = manejadorServicio.buscar(solicitudActual.getIdServicio());
		List<PersonaSolicitud> personaSolicitud = new ArrayList<PersonaSolicitud>();
		Caso casoActual = new Caso();
		casoActual.setIdSolicitudServicio(solicitudActual.getIdSolicitudServicio());
		casoActual.setTipoCuantia(solicitudActual.getTipoCuantia());
		if (!UtilDominios.TIPO_CUANTIA_CONCILIACION_INDETERMINADO.equals(solicitudActual.getTipoCuantia()))
			casoActual.setValorPretensiones(solicitudActual.getCuantia());
		casoActual.setIdServicio(solicitudActual.getIdServicio());
		casoActual.setIdMateria(solicitudActual.getIdMateria());
		casoActual.setFechaUltimaModificacion(new Date());
		casoActual.setIdUsuarioModificacion(idUsuario);
		casoActual.setFechaRadicacion(new Date());
		casoActual.setEstadoCaso(UtilDominios.ESTADO_CASO_CREADO);
		casoActual.setFechaEstado(new Date());
		casoActual.setEtapa(UtilDominios.ETAPAS_CONCILIACION);
		casoActual.setFechaEstado(new Date());
		casoActual.setAmparoDePobreza(false);
		casoActual.setPendienteDePago(false);
		casoActual.setTipoRadicacion(UtilDominios.TIPO_RADICACION_VIRTUAL);
		casoActual.setIdSede(solicitudActual.getIdSede());
		casoActual.setInicioDeConflicto(solicitudActual.getInicioDeConflicto());
		casoActual.setParteQueSolicitaServicio(solicitudActual.getParteQueSolicitaServicio());
		casoActual.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		casoActual.setArbitrajeConsumo(solicitudActual.isArbitrajeConsumo());
		casoActual.setTipoConflicto(solicitudActual.getTipoConflicto());
		casoActual.setEnteroServicio(solicitudActual.getEnteroServicio());
		if (UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA.equals(servicio.getTipo())
		|| UtilDominios.TIPO_SERVICIO_INTERNACIONAL.equals(servicio.getTipo())) {
			casoActual.setEtapa(UtilDominios.ETAPA_CASO_PREARBITRAL);
		} else if (UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS.equals(servicio.getTipo())) {
			casoActual.setHechos(solicitudActual.getHechos());
			casoActual.setPretensiones(solicitudActual.getPretensiones());
		}

		crear(casoActual);
		String eventoRadicacion = (String.format(
				MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO250.toString()),
				casoActual.getIdCaso()));
		eventoFacade.registrarEvento(casoActual.getIdCaso(), UtilDominios.TIPO_EVENTO_RADICACION_DE_CASO,
				eventoRadicacion, idUsuario);

		for (PersonaSolicitud personaFor : solicitudActual.getPersonaSolicitudList()) {
			if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(personaFor.getEstadoRegistro())
					&& !personaFor.getRol().getNombre().equals(UtilDominios.ROL_PERSONA_CONCILIADOR)
					&& !UtilDominios.ROL_PERSONA_SOLICITANTE.equals(personaFor.getRol().getNombre())) {
				personaSolicitud.add(personaFor);
			}
		}
		// migracion de las persona solicitud de un caso
		if (!personaSolicitud.isEmpty()) {
			personaSolicitudFacade.migracionPersonaSolicitudARolPersonaCaso(personaSolicitud, idUsuario,
					casoActual.getIdCaso());
		}

		return casoActual;

	}

	@Override
	public void cerrarCasoConciliacionNoCompetencia(Long idCaso, String observaciones, Boolean banderaEquidad, String mensaje) throws EstadosCasoException {
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		}
		Caso casoActual = manejadorCaso.buscar(idCaso);
		casoActual.setResultado(UtilDominios.RESULTADO_CASO_CONCILIACION_FALTA_COMPETENCIA);
		casoActual.setFechaUltimaModificacion(new Date());
		casoActual.setIdUsuarioModificacion(usuarioModificacion);
		manejadorCaso.actualizar(casoActual);
		
		if(!banderaEquidad) {
			this.cambiarEstadoCaso(idCaso, UtilDominios.ESTADO_CASO_FALTA_DE_COMPETENCIA, new Date(),
					UtilDominios.TIPO_EVENTO_FALTA_DE_COMPENTENCIA, observaciones);
			reliquidacionFacade.generarReliquidacionTipoDevolucion(idCaso,
				UtilDominios.RESULTADO_CASO_CONCILIACION_FALTA_COMPETENCIA);
		}else {
			this.cambiarEstadoCaso(idCaso, UtilDominios.ESTADO_CASO_RECHAZADO, new Date(),
					UtilDominios.TIPO_EVENTO_FALTA_DE_COMPENTENCIA, observaciones);
			//enviar correo al solicitante del rechazo			
			List<Persona> solicitante = manejadorRolPersonaCaso.consultarPersonasPorRolCaso(idCaso, UtilDominios.ROL_PERSONA_SOLICITANTE_EQUIDAD);			
			repartoFacade.envioCorreoRechazo(idCaso, solicitante.get(0).getIdPersona() ,mensaje);			
			
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade#
	 * cambiarEstadoCaso(java.lang.Long, java.lang.String, java.util.Date,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void cambiarEstadoCaso(Long idCaso, String nuevoEstado, Date fechaEvento, String tipoEvento,
			String observaciones) throws EstadosCasoException {
		
		Caso caso = this.buscar(idCaso);
		
		if (manejadorCaso.validarCambioEstadoCaso(caso.getEstadoCaso(), nuevoEstado)) {
			caso.setEstadoCaso(nuevoEstado);
			caso.setFechaUltimaModificacion(new Date());
			caso.setFechaEstado(new Date());
			manejadorCaso.actualizar(caso);

			String idSesion = null;
			if (appContext != null && appContext.getContextoSesion() != null
					&& appContext.getContextoSesion().getNombreUsuario() != null) {
				
				idSesion = appContext.getContextoSesion().getNombreUsuario();
				
			}
			eventoFacade.registrarEvento(idCaso, tipoEvento, observaciones, idSesion, fechaEvento,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);

		} else {
			
			List<String> args = new ArrayList<>();
			args.add(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ESTADO_CASO, caso.getEstadoCaso()));
			args.add(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ESTADO_CASO, nuevoEstado));
			String msg = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR250.toString()),
					args.toArray());
			throw new EstadosCasoException(msg);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade#
	 * crearCasoConvenio(RadicarCasoConvenioDTO, String, String, String)
	 */
	@Override
	public Caso crearCasoConvenio(RadicarCasoConvenioDTO radicarCasoConvenio, String idUsuario, String rolPrincipal,
			String nombreUsuario) {
		Map<Long, Long> correspondencias = new HashMap<>();
		Caso casoRetorno;
		Caso casoGuardar;
		casoGuardar = radicarCasoConvenio.getCaso();
		casoGuardar.setFechaEstado(new Date());
		casoGuardar.setEtapa(UtilDominios.ETAPAS_CONCILIACION);
		casoGuardar.setAmparoDePobreza(false);
		casoGuardar.setEstadoCaso(radicarCasoConvenio.getCaso().getEstadoCaso());
		casoGuardar.setHechos(radicarCasoConvenio.getCaso().getHechos());
		casoGuardar.setPretensiones(radicarCasoConvenio.getCaso().getPretensiones());
		casoGuardar.setParteQueSolicitaServicio(radicarCasoConvenio.getCaso().getParteQueSolicitaServicio());
		casoGuardar.setInicioDeConflicto(radicarCasoConvenio.getCaso().getInicioDeConflicto());
		casoGuardar.setIdLugarDelConflicto(radicarCasoConvenio.getCaso().getIdLugarDelConflicto());
		casoGuardar.setIdMateria(radicarCasoConvenio.getCaso().getIdMateria());
		casoGuardar.setIdSede(radicarCasoConvenio.getCaso().getIdSede());
		casoGuardar.setTipoCuantia(radicarCasoConvenio.getCaso().getTipoCuantia());
		if (radicarCasoConvenio.getCuantia() != null)
			casoGuardar.setValorPretensiones(String.valueOf(radicarCasoConvenio.getCuantia()));
		casoGuardar.setPendienteDePago(false);
		casoGuardar.setFechaUltimaModificacion(new Date());
		casoGuardar.setIdUsuarioModificacion(nombreUsuario);
		casoGuardar.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		casoGuardar.setIdServicio(
				servicioFacade.getServicioPorNombre(UtilConstantes.SERVICIO_CONVENIO_CONCILIACION).getIdServicio());
		casoRetorno = crearCaso(casoGuardar);
		//Validar que el caso no se cree doble		
		Integer cntCasos = 0;	
		Integer cntPartes = 0;
		Integer cntParteCaso = 0;
		
		for (FormularioParteDTO parte : radicarCasoConvenio.getParteInfo()) {
			++cntPartes;  	
			parte.setIdCaso(casoRetorno.getIdCaso());
			Long idParte = parte.getIdPersona();
			parte.setIdPersona(null);
			Long idPersona = personaFacade.guardarInformacionParte(parte);
			Long parametroIdPersona = idPersona;			
			parte.setIdPersona(idParte);
			correspondencias.put(parte.getIdPersona(), idPersona);
				
			cntCasos = manejadorRolPersonaCaso.consultarPersonaRolCasoDoble(parametroIdPersona);	
			if(cntCasos >= 2) {
				++cntParteCaso;
			}
		}		
		
		if ((cntPartes > cntParteCaso)) {			 
			// relacionar apoderados
			actualizarRolPersona(radicarCasoConvenio.getParteInfo(), correspondencias);
			generarNombreCaso(casoRetorno.getIdCaso());
			casoRetorno.setEstadoCaso(UtilDominios.ESTADO_CASO_RADICADO);
			manejadorCaso.actualizar(casoRetorno);
	
			// guardado documento radicacion de caso
			List<Long> documentos = guardarDocumentoRadicacion(radicarCasoConvenio, casoRetorno, nombreUsuario, null);
	
			try {
				// llamado reparto
				String fallo = llamadoReparto(radicarCasoConvenio, nombreUsuario);
				if (fallo != null)
					throw new SIMASCNegocioExcepcion(fallo);
	
				eventoFacade.registrarEvento(casoRetorno.getIdCaso(), UtilDominios.TIPO_EVENTO_RADICACION_DE_CASO,
						String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO250.toString()),
								Arrays.asList(String.valueOf(casoRetorno.getIdCaso()))),
						nombreUsuario);
	
				Rol rol = manejadorRol.consultarRolPorId(Long.parseLong(rolPrincipal));
				enviarCorreosConvenio(radicarCasoConvenio.getCaso().getIdConvenio(), rol.getNombre(), idUsuario,
						casoRetorno, documentos.get(0));
	
			} catch (Exception e) {
				for (Long idDocumento : documentos) {
					almacenamientoDocumentosFacade.eliminarDocumento(idDocumento, idUsuario);
				}
				throw new SIMASCNegocioExcepcion(e.getMessage());
			}
		}else {			
			casoRetorno = new Caso();			
			throw new SIMASCNegocioExcepcion("No se pudo radicar el caso, existe un caso registrado los ultimos 10 minutos con la información de las partes ingresadas");
		}

		return casoRetorno;
	}

	/**
	 * Método que realiza el proceso de reparto para la radicacion del caso de
	 * convenio
	 * 
	 * @param radicarCasoConvenio
	 * @param nombreUsuario
	 */
	private String llamadoReparto(RadicarCasoConvenioDTO radicarCasoConvenio, String nombreUsuario) {
		String fallo = null;
		DatosEntradaRepartoDTO datos = new DatosEntradaRepartoDTO();
		datos.setIdCaso(radicarCasoConvenio.getCaso().getIdCaso());
		datos.setIdSede(radicarCasoConvenio.getCaso().getIdSede());
		datos.setIdConciliador(radicarCasoConvenio.getConciliador());
		datos.setFechaAudiencia(radicarCasoConvenio.getFechaAudiencia());
		datos.setHoraAudiencia(radicarCasoConvenio.getFechaHoraInicio());
		datos.setUsuario(nombreUsuario);
		Rol rolConciliador = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_CONCILIADOR);
		datos.setIdRol(rolConciliador.getIdRol());
		try {
			repartoSvc.reparto(datos);
		} catch (SIMASCNegocioExcepcion e) {
			fallo = e.getMessage();
			// repartoSvc.fallaReparto( radicarCasoConvenio.getCaso().getIdCaso(),
			// e.getMessage(), nombreUsuario);
		} catch (Exception e) {
			logger.error("Error: ", e);
			Throwable causa = ExceptionUtils.getRootCause(e);
			fallo = causa != null ? causa.getMessage() : ExceptionUtils.getRootCauseMessage(e);
		}
		String cadenaConvertida = null;
		if (fallo != null) {
			cadenaConvertida = fallo;
			if (fallo.contains("exception")) {
				cadenaConvertida = fallo.split(UtilConstantes.DOS_PUNTOS)[1];
			}
			cadenaConvertida = cadenaConvertida.replace("\"", "");
		}
		return cadenaConvertida;
	}

	/**
	 * Método que realiza el proceso de creacion y almacenamiento del documento de
	 * radicación del caso
	 * 
	 * @param radicarCasoConvenio
	 * @param casoRetorno
	 */
	private List<Long> guardarDocumentoRadicacion(RadicarCasoConvenioDTO radicarCasoConvenio, Caso casoRetorno,
			String nombreUsuario, String realPath) {
		int intentos = 3;
		List<Long> documentos = new ArrayList<Long>();
		InputStream is = null;
		radicarCasoConvenio.getCaso().setIdCaso(casoRetorno.getIdCaso());
		Documento documentoEntity = new Documento();
		documentoEntity.setIdCaso(casoRetorno.getIdCaso());
		documentoEntity.setTipoArchivo(UtilDominios.TIPO_ARCHIVO_PDF);
		documentoEntity.setTipoDocumento(UtilDominios.TIPO_DOCUMENTO_DIG_RADICACION_CASO);
		documentoEntity.setNombre(NOMBRE_DOCUMENTO);
		documentoEntity.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		documentoEntity.setFechaUltimaModificacion(new Date());
		documentoEntity.setDescripcion(NOMBRE_DOCUMENTO);
		documentoEntity.setNuevo(true);

		String[] datosDocumentos = (String[]) radicacionSolicitudPDF.generarPDFRadicacion(radicarCasoConvenio, realPath)
				.toArray();
		while (intentos > 0) {
			try {
				byte[] bytes = clienteFirmaDigital.firmarPDF(datosDocumentos[0], datosDocumentos[1], null, null, false);
				is = new ByteArrayInputStream(bytes);
				documentos = almacenamientoDocumentosFacade.guardarDocumento(casoRetorno.getIdCaso(), NOMBRE_DOCUMENTO,
						UtilDominios.TIPO_ARCHIVO_PDF, is, documentoEntity, nombreUsuario, null, null);
				intentos = 0;
			} catch (Exception e) {
				if (intentos == 1) {
					logger.error(e.getMessage());
					throw new SIMASCNegocioExcepcion(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR086.toString()));
				} else {
					intentos -= 1;
				}
			} finally {
				try {
					if (is != null)
						is.close();
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}

		}

		return documentos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade#
	 * crearCaso(com.ccb.simasc.transversal.entidades.Caso)
	 */
	@Override
	public Caso crearCaso(Caso caso) {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		caso.setFechaRadicacion(date);
		caso.setFechaUltimaModificacion(date);
		caso.setFechaEstado(date);
		caso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		manejadorCaso.crear(caso);
		Long idCaso = caso.getIdCaso();
		carpetaFacade.generarCuadernos(idCaso);
		return caso;
	}

	/**
	 * Método para realizar el envio de correos dependiendo del rol del usuario
	 * 
	 * @param idRol
	 * @param idConvenio
	 * @param nombreRol
	 * @param idUsuario
	 * @param casoRetorno
	 */
	private void enviarCorreosConvenio(Long idConvenio, String nombreRol, String idUsuario, Caso casoRetorno,
			Long idDocumento) {
		List<Persona> personas = new ArrayList<Persona>();
		Persona usuario = manejadorPersona.buscar(Long.parseLong(idUsuario));
		/*
		 * if (UtilDominios.ROL_PERSONA_SECRETARIA_DE_CONCILIACION.equals(nombreRol)) {
		 * List<RelacionadoConvenioDTO> relacionadoList = relacionadoConvenioFacade
		 * .consultarRelacionadoConvenio(idConvenio,
		 * UtilDominios.ROL_PERSONA_APODERADO_CONVENIO); for (RelacionadoConvenioDTO
		 * relacionadoConvenio : relacionadoList) { Persona relacionado =
		 * manejadorPersona
		 * .buscar(relacionadoConvenio.getRelacionadoConvenioPK().getIdPersona());
		 * personas.add(relacionado); } }
		 */
		personas.add(usuario);
		enviarCorreoCasoConvenio(casoRetorno, personas, idDocumento);
	}

	/**
	 * Método que actualiza el el apoderado de un convocante o convocado
	 * 
	 * @param partes
	 * @param correspondencias
	 */
	private void actualizarRolPersona(List<FormularioParteDTO> partes, Map<Long, Long> correspondencias) {
		for (FormularioParteDTO formularioParteDTO : partes) {
			RolPersonaCaso rolPersonaCaso;
			if (formularioParteDTO.getIdRepresentado() != null && (formularioParteDTO.getRol().getDominioPK()
					.getCodigo().equals(UtilDominios.ROL_PERSONA_CONVOCADO)
					|| formularioParteDTO.getRol().getDominioPK().getCodigo()
							.equals(UtilDominios.ROL_PERSONA_CONVOCANTE))) {
				rolPersonaCaso = manejadorRolPersonaCaso.consultaRolPersonaId(
						correspondencias.get(formularioParteDTO.getIdPersona()), formularioParteDTO.getIdCaso(),
						UtilDominios.ESTADO_REGISTRO_ACTIVO);
				rolPersonaCaso.setIdPersonaApoderado(correspondencias.get(formularioParteDTO.getIdRepresentado()));
				manejadorRolPersonaCaso.actualizar(rolPersonaCaso);
			}
		}
	}

	/**
	 * Metodo que envia correo al crear un caso de conciliacion CON-F-096
	 * 
	 * @param caso
	 * @param personas
	 */

	private void enviarCorreoCasoConvenio(Caso caso, List<Persona> personas, Long idDocumento) {
		List<String> textoCorreo = new ArrayList<String>();
		String asunto = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO281.toString());
		if (personas != null && !personas.isEmpty()) {
			List<String> args2 = new ArrayList<>();
			args2.add(String.valueOf(caso.getIdCaso()));
			args2.add(caso.getNombre());
			Date fecha = audienciaFacade.obtenerAudienciasCaso(caso.getIdCaso()).get(0).getHoraInicio();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
			args2.add(dateFormatter.format(fecha));
			dateFormatter.applyPattern("hh:mm");
			args2.add(dateFormatter.format(fecha));
			if (caso.getValorPretensiones() != null) {
				args2.add(caso.getValorPretensiones());
			} else {
				args2.add(UtilConstantes.TEXTO_INDETERMINADA);
			}

			textoCorreo.add(String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO280.toString()), args2.toArray()));
			List<DocumentoDTO> documentos = new ArrayList<DocumentoDTO>();
			DocumentoDTO documento = documentoFacade.transformarSinDependencias(documentoFacade.buscar(idDocumento));
			documentos.add(documento);

			correoRolPersonaCasoFacade.envioDeCorreo(asunto, personas, null, null, textoCorreo, caso.getIdCaso(),
					documentos, null, false);

		}
	}

	/*
	 * CON-F-070 (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade#
	 * cargueCasosDesdeArchivo(java.io.InputStream, java.lang.Long, java.lang.Long,
	 * java.lang.Long)
	 */
	@Override
	public void cargueCasosDesdeArchivo(InputStream documento, Long idConvenio, Long idServicio, Long numeroCasos,
			String nombre) throws EstadosCasoException {
		try {
			Workbook workbook = getWorkbook(documento, nombre);
			Sheet sheet = workbook.getSheetAt(0);
			int cantidadRegistros = contarRegistros(sheet) + 2;
			if (cantidadRegistros != numeroCasos + 2) {
				throw new SimascException(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR302.toString()));
			}
			if (validarCamposObligatorios(sheet, cantidadRegistros)) {
				List<Dominio> sexos = dominioFacade.getDominios(UtilDominios.DOMINIO_SEXOS);
				List<Dominio> documentosPersona = dominioFacade
						.getDominios(UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA);
				List<Dominio> tipoPersona = dominioFacade.getDominios(UtilDominios.DOMINIO_TIPO_PERSONA);
				List<Dominio> rolPersona = dominioFacade.getDominios(UtilDominios.DOMINIO_ROL_PERSONA);
				List<Dominio> escolaridades = dominioFacade.getDominios(UtilDominios.DOMINIO_ESCOLARIDADES);
				List<Dominio> estratos = dominioFacade.getDominios(UtilDominios.DOMINIO_ESTRATOS);
				List<Dominio> sectoresEmpresa = dominioFacade.getDominios(UtilDominios.DOMINIO_SECTOR_EMPRESA);
				List<Dominio> instituciones = dominioFacade.getDominios(UtilDominios.DOMINIO_INSTITUCIONES_EDUCATIVAS);
				List<Dominio> entidades = dominioFacade.getDominios(UtilDominios.DOMINIO_ENTIDAD_TARJETA_PROFESIONAL);
				ZonaGeografica colombia = manejadorZonaGeografica.buscar(UtilConstantes.CODIGO_NACIONALIDAD_COLOMBIA);
				ZonaGeografica bogota = manejadorZonaGeografica
						.buscar(String.valueOf(UtilConstantes.CODIGO_CIUDAD_BOGOTA));
				Profesion profesion = manejadorProfesion.buscar(UtilConstantes.CODIGO_PROFESION_NO_INFORMA);
				for (int i = 2; i < cantidadRegistros; i++) {
					Row row = sheet.getRow(i);
					if (validarPartesCaso(row)) {
						Long idCaso = generarCaso(row, idServicio, idConvenio);

						generarParte(row, 7, idCaso, sexos, documentosPersona, tipoPersona, rolPersona, escolaridades,
								estratos, sectoresEmpresa, instituciones, entidades, colombia, bogota, profesion);
						generarParte(row, 30, idCaso, sexos, documentosPersona, tipoPersona, rolPersona, escolaridades,
								estratos, sectoresEmpresa, instituciones, entidades, colombia, bogota, profesion);
						generarParte(row, 53, idCaso, sexos, documentosPersona, tipoPersona, rolPersona, escolaridades,
								estratos, sectoresEmpresa, instituciones, entidades, colombia, bogota, profesion);
						generarParte(row, 76, idCaso, sexos, documentosPersona, tipoPersona, rolPersona, escolaridades,
								estratos, sectoresEmpresa, instituciones, entidades, colombia, bogota, profesion);
						generarParte(row, 99, idCaso, sexos, documentosPersona, tipoPersona, rolPersona, escolaridades,
								estratos, sectoresEmpresa, instituciones, entidades, colombia, bogota, profesion);
						generarParte(row, 122, idCaso, sexos, documentosPersona, tipoPersona, rolPersona, escolaridades,
								estratos, sectoresEmpresa, instituciones, entidades, colombia, bogota, profesion);

						generarNombreCaso(idCaso);
					} else {
						throw new SimascException(MensajesSimasc.getInstancia()
								.getMensajePorKey(MensajesEnum.ERROR307.toString(), this.getErrorFila()));
					}
				}
			} else {
				throw new SimascException(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR301.toString()));
			}

		} catch (IOException e) {
			logger.error("Error: ", e);
		}

	}

	/**
	 * Método que verifica que se encuentren las partes minimas para la creacion del
	 * caso
	 * 
	 * @param fila
	 * @return
	 */
	private boolean validarPartesCaso(Row fila) {
		Integer[] posiciones = { 7, 30, 53, 76, 99, 122 };
		return validarParte(fila, UtilDominios.ROL_PERSONA_CONVOCADO, posiciones)
				&& validarParte(fila, UtilDominios.ROL_PERSONA_CONVOCANTE, posiciones);
	}

	/**
	 * Método que verifica que se encuentre la parte que se recibe como parametro
	 * 
	 * @param fila
	 * @param nombreRol
	 * @param posiciones
	 * @return
	 */
	private boolean validarParte(Row fila, String nombreRol, Integer[] posiciones) {
		boolean encontrada = false;
		for (Integer posicion : posiciones) {
			if (validarDatosCampo(fila.getCell(posicion))
					&& nombreRol.equals(fila.getCell(posicion).getStringCellValue())) {
				encontrada = true;
				break;
			} else {
				this.setErrorFila(fila.getCell(posicion).getStringCellValue());

			}
		}
		return encontrada;
	}

	/**
	 * Método que crea el objeto que guardará el archivo dependiendo de la extensión
	 * que qu tenga
	 * 
	 * @param inputStream
	 * @param excelFilePath
	 * @return
	 * @throws IOException
	 */
	private Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
		Workbook workbook = null;

		if (excelFilePath.endsWith(UtilConstantes.EXTENSION_XLSX)) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith(UtilConstantes.EXTENSION_XLS)) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

	/**
	 * Método para contar la cantidad de registros en el archivo
	 * 
	 * @param hoja
	 * @return cantidad de registros validos en el documento
	 */
	private int contarRegistros(Sheet hoja) {
		int cantidad = 0;
		int posicion = 2;
		Iterator<Row> filaI = hoja.iterator();
		filaI.next();
		filaI.next();
		while (filaI.hasNext() && posicion <= 202) {
			Row fila = filaI.next();
			posicion++;
			if (validarDatosCampo(fila.getCell(0)))
				cantidad += 1;
		}
		return cantidad;
	}

	/**
	 * Método que valida los campos obligatorios en el archivo
	 * 
	 * @param sheet
	 * @return
	 */
	private boolean validarCamposObligatorios(Sheet sheet, int cantidadRegistros) {
		boolean valido = true;
		for (int i = 2; i < cantidadRegistros; i++) {
			valido = validarCamposObligatoriosFila(sheet.getRow(i));
			if (!valido)
				break;
		}
		return valido;
	}

	/**
	 * Método que valida los campos obligatorios por cada fila
	 * 
	 * @param fila
	 * @return
	 */
	private boolean validarCamposObligatoriosFila(Row fila) {
		return validarCamposObligatoriosCaso(fila) && validarCamposObligatoriosParte(fila, 7, 1)
				&& validarCamposObligatoriosParte(fila, 30, 2) && validarCamposObligatoriosParte(fila, 53, 3)
				&& validarCamposObligatoriosParte(fila, 76, 4) && validarCamposObligatoriosParte(fila, 99, 5)
				&& validarCamposObligatoriosParte(fila, 122, 6);
	}

	/**
	 * Método que valida los campos obligatorios para el caso
	 * 
	 * @param fila
	 * @return
	 */
	private boolean validarCamposObligatoriosCaso(Row fila) {
		return validarDatosCampo(fila.getCell(1)) && validarDatosCampo(fila.getCell(2))
				&& validarDatosCampo(fila.getCell(3));
	}

	/**
	 * Método que valida los campos obligatorios de una parte
	 * 
	 * @param fila
	 * @param posicion
	 * @return
	 */
	private boolean validarCamposObligatoriosParte(Row fila, int posicion, int parte) {
		int pos = posicion;
		boolean valido = true;
		boolean correoValido = true;
		Rol rol = null;

		if (validarCamposParteSinDiligenciar(fila, pos))
			return valido;
		try {
			if (validarDatosCampo(fila.getCell(pos))) {
				rol = manejadorRol.consultarRolPorNombre(fila.getCell(pos).getStringCellValue());
			} else {
				valido = false;
			}
			if (rol != null && !rol.getNombre().equals(UtilDominios.ROL_PERSONA_CONVOCANTE)) {
				valido = validarDatosCampo(fila.getCell(pos + 2)) && validarDatosCampo(fila.getCell(pos + 3))
						&& fila.getCell(pos + 3).toString().length() <= 30;
			}
			valido = valido && validarDatosCampo(fila.getCell(pos + 5))
					&& fila.getCell(pos + 5).toString().length() <= 600;
			if (valido && validarDatosCampo(fila.getCell(pos + 7))) {
				valido = valido && fila.getCell(pos + 7).toString().length() <= 50
						&& validarDatosCampo(fila.getCell(pos + 4));
				if (valido && validarDatosCampo(fila.getCell(pos + 6))
						&& fila.getCell(pos + 6).toString().length() > 50)
					valido = false;
				if (valido && validarDatosCampo(fila.getCell(pos + 8))
						&& fila.getCell(pos + 8).toString().length() > 50)
					valido = false;
			}

			valido = valido && ((validarDatosCampo(fila.getCell(pos + 10)) && validarDatosCampo(fila.getCell(pos + 11))
					&& fila.getCell(pos + 11).toString().length() <= 150)
					|| (validarDatosCampo(fila.getCell(pos + 18)) && fila.getCell(pos + 18).toString().length() <= 15)
					|| (validarDatosCampo(fila.getCell(pos + 19))
							&& fila.getCell(pos + 19).toString().length() <= 100));

			// validar correos
			if (valido) {
				correoValido = !(validarCorreo(fila, pos + 19) || validarCorreo(fila, pos + 20)
						|| validarCorreo(fila, pos + 21));
			}

			if (!correoValido)
				throw new SimascException(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR300.toString()));

		} catch (Exception e) {
			if (!(e instanceof SimascException))
			logger.error("Error: ", e);
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR300.toString()));
		}

		return valido;
	}

	/**
	 * Método para validar la estructura del correo
	 * 
	 * @param fila
	 * @param pos
	 * @return
	 */
	private boolean validarCorreo(Row fila, int pos) {
		return validarDatosCampo(fila.getCell(pos)) && (fila.getCell(pos).toString().length() > 100
				|| !UtilOperaciones.validateEmail(fila.getCell(pos).toString()));
	}

	/**
	 * Método que valida si la seccion de una parte no se encuentra diligenciada
	 * 
	 * @param fila
	 * @param pos
	 * @return
	 */
	private boolean validarCamposParteSinDiligenciar(Row fila, int pos) {
		boolean sinDiligenciar = false;

		for (int i = pos - 1; i < pos + 21; i++) {
			sinDiligenciar = !validarDatosCampo(fila.getCell(i));
			if (!sinDiligenciar)
				break;
		}
		return sinDiligenciar;
	}

	/**
	 * Método que valida si la celda tiene información
	 * 
	 * @param celda
	 * @return
	 */
	private boolean validarDatosCampo(Cell celda) {
		return celda != null && celda.getCellType() != Cell.CELL_TYPE_BLANK
				&& celda.getCellType() != Cell.CELL_TYPE_ERROR
				&& (celda.getCellType() == Cell.CELL_TYPE_FORMULA
						? celda.getCachedFormulaResultType() != Cell.CELL_TYPE_ERROR
						: true);
	}

	/**
	 * Método para crear el caso a partir de los datos del archivo
	 * 
	 * @param fila
	 * @throws EstadosCasoException
	 */
	private Long generarCaso(Row fila, Long idServicio, Long idConvenio) throws EstadosCasoException {
		Caso caso = new Caso();
		Caso casoGenerado;
		Convenio convenio = manejadorConvenio.buscar(idConvenio);
		Long idSede = null;
		caso.setIdMateria((long) fila.getCell(1).getNumericCellValue());
		if (manejadorServicioMateria.buscar(new ServicioMateriaPK(idServicio, caso.getIdMateria())) == null)
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR303.toString()));
		if (convenio.getIdServicio().equals(UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO)
				&& convenio.getSedeConvenioList() == null && convenio.getSedeConvenioList().isEmpty())
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR304.toString()));
		try {
			caso.setHechos(fila.getCell(2).toString());
			caso.setPretensiones(fila.getCell(3).toString());
			if (validarDatosCampo(fila.getCell(4)))
				caso.setValorPretensiones(String.valueOf((long) fila.getCell(4).getNumericCellValue()));
			if (validarDatosCampo(fila.getCell(5)))
				caso.setObservaciones(truncarCadena(fila.getCell(5).getStringCellValue(), 1000));
			caso.setEstadoCaso(UtilDominios.ESTADO_CASO_RADICADO);
			caso.setEtapa(UtilDominios.ETAPAS_CONCILIACION);
			caso.setAmparoDePobreza(false);
			caso.setPendienteDePago(idServicio.equals(UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO));

			if (convenio.getSedeConvenioList() != null && !convenio.getSedeConvenioList().isEmpty())
				idSede = convenio.getSedeConvenioList().get(0).getSedeConvenioPK().getIdSede();
			caso.setIdSede(idSede);
			caso.setParteQueSolicitaServicio(UtilDominios.PARTE_SOLICITA_SERVICIO_UNA_PARTE);
			caso.setInicioDeConflicto(UtilDominios.RANGO_INICIO_DE_CONFLICTO_UN_MES);
			caso.setIdLugarDelConflicto(String.valueOf(UtilConstantes.CODIGO_CIUDAD_BOGOTA));
			caso.setIdConvenio(idConvenio);
			caso.setIdServicio(idServicio);
			casoGenerado = crearCaso(caso);

			List<String> args2 = new ArrayList<>();
			args2.add(String.valueOf(casoGenerado.getIdCaso()));
			cambiarEstadoCaso(casoGenerado.getIdCaso(), UtilDominios.ESTADO_CASO_RADICADO, null,
					UtilDominios.TIPO_EVENTO_RADICACION_DE_CASO,
					String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO281.toString()),
							args2.toArray()));

			if (idServicio == UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO) {
				cambiarEstadoCaso(casoGenerado.getIdCaso(), UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION, null,
						UtilDominios.TIPO_EVENTO_PENDIENTE_POR_DESIGNACION,
						String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO278.toString()),
								args2.toArray()));
			}

		} catch (Exception e) {
			logger.error("Error: ", e);
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR300.toString()));
		}
		return casoGenerado.getIdCaso();
	}

	/**
	 * Método para generar una parte a partir de los datos del archivo
	 * 
	 * @param fila
	 * @param posicion
	 * @param idCaso
	 */
	private void generarParte(Row fila, int posicion, Long idCaso, List<Dominio> sexos, List<Dominio> documentosPersona,
			List<Dominio> tipoPersona, List<Dominio> rolPersona, List<Dominio> escolaridades, List<Dominio> estratos,
			List<Dominio> sectoresEmpresa, List<Dominio> instituciones, List<Dominio> entidades,
			ZonaGeografica colombia, ZonaGeografica bogota, Profesion profesion) {
		if (validarCamposParteSinDiligenciar(fila, posicion))
			return;
		FormularioParteDTO parte = new FormularioParteDTO();

		int pos = posicion;
		Rol rol = manejadorRol.consultarRolPorNombre(fila.getCell(pos).getStringCellValue());
		parte.setIdCaso(idCaso);
		parte.setRol(buscarDominioEnLista(rol.getNombre(), rolPersona));

		if (validarDatosCampo(fila.getCell(pos + 2)) && validarDatosCampo(fila.getCell(pos + 3))) {
			parte.setTipoIdentificacion(
					buscarDominioEnLista(fila.getCell(pos + 2).getStringCellValue(), documentosPersona));
			try {
				parte.setNumeroIdentificacion(
						truncarCadena(String.valueOf((long) fila.getCell(pos + 3).getNumericCellValue()), 30));
			} catch (Exception e) {
				parte.setNumeroIdentificacion(truncarCadena(fila.getCell(pos + 3).toString(), 30));
			}
		} else {
			parte.setTipoIdentificacion(
					buscarDominioEnLista(UtilDominios.TIPO_DOCUMENTO_PERSONA_SIN_IDENTIFICACION, documentosPersona));
		}

		parte.setPrimerNombre(truncarCadena(fila.getCell(pos + 5).toString(), 600));
		if (validarDatosCampo(fila.getCell(pos + 7)) && validarDatosCampo(fila.getCell(pos + 5))) {
			parte.setTipoSexo(buscarDominioPorNombre(fila.getCell(pos + 4).toString(), sexos));
			parte.setPrimerApellidoORazonSocial(truncarCadena(fila.getCell(pos + 7).toString(), 50));
			parte.setTipoPersona(buscarDominioEnLista(UtilDominios.TIPO_PERSONA_NATURAL, tipoPersona));
			if (!fila.getCell(pos + 6).getStringCellValue().trim().isEmpty())
				parte.setSegundoNombre(truncarCadena(fila.getCell(pos + 6).toString(), 50));
			if (!fila.getCell(pos + 8).getStringCellValue().trim().isEmpty())
				parte.setSegundoApellido(truncarCadena(fila.getCell(pos + 8).toString(), 50));
		} else {
			parte.setTipoPersona(buscarDominioEnLista(UtilDominios.TIPO_PERSONA_JURIDICO, tipoPersona));
		}

		List<UbicacionDTO> ubicaciones = new ArrayList<UbicacionDTO>();
		List<CorreoElectronicoDTO> correos = new ArrayList<CorreoElectronicoDTO>();
		if (validarDatosCampo(fila.getCell(pos + 10)) && validarDatosCampo(fila.getCell(pos + 11))) {
			ubicaciones.add(crearUbicacionDTO(
					String.valueOf((long) fila.getCell(pos + 10).getNumericCellValue()).substring(3),
					truncarCadena(fila.getCell(pos + 11).toString(), 150), UtilDominios.TIPO_UBICACION_PRINCIPAL));
		}

		if (validarDatosCampo(fila.getCell(pos + 13)) && validarDatosCampo(fila.getCell(pos + 14))) {
			ubicaciones.add(crearUbicacionDTO(
					String.valueOf((long) fila.getCell(pos + 13).getNumericCellValue()).substring(3),
					truncarCadena(fila.getCell(pos + 14).toString(), 150), UtilDominios.TIPO_UBICACION_SECUNDARIA));
		}

		if (validarDatosCampo(fila.getCell(pos + 16)) && validarDatosCampo(fila.getCell(pos + 17))) {
			ubicaciones.add(crearUbicacionDTO(
					String.valueOf((long) fila.getCell(pos + 16).getNumericCellValue()).substring(3),
					truncarCadena(fila.getCell(pos + 17).toString(), 150), UtilDominios.TIPO_UBICACION_SECUNDARIA));
		}

		parte.setLstUbicacion(ubicaciones);

		if (validarDatosCampo(fila.getCell(pos + 18))) {
			parte.setNumeroTelefonoUno(
					truncarCadena(String.valueOf((long) fila.getCell(pos + 18).getNumericCellValue()), 15));
		}

		if (validarDatosCampo(fila.getCell(pos + 19))) {
			correos.add(crearCorreoDTO(truncarCadena(fila.getCell(pos + 19).toString(), 100),
					UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL));
		}
		if (validarDatosCampo(fila.getCell(pos + 20))) {
			correos.add(crearCorreoDTO(truncarCadena(fila.getCell(pos + 20).toString(), 100),
					UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_SECUNDARIO));
		}
		if (validarDatosCampo(fila.getCell(pos + 21))) {
			correos.add(crearCorreoDTO(truncarCadena(fila.getCell(pos + 21).toString(), 100),
					UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_TERCIARIO));
		}
		parte.setCorreosElectronicos(correos);

		parte.setCiudadIdentificacion(bogota);
		parte.setNacionalidad(UtilConstantes.CODIGO_NACIONALIDAD_COLOMBIA);
		parte.setPais(colombia);
		parte.setCiudad(bogota);
		parte.setProfesion(profesion);
		parte.setEscolaridad(buscarDominioEnLista(UtilConstantes.CODIGO_ESCOLARIDAD_NO_INFORMA, escolaridades));
		parte.setEstrato(buscarDominioEnLista(UtilConstantes.CODIGO_ESTRATO_NO_INFORMA, estratos));
		parte.setSectorEmpresa(buscarDominioEnLista(UtilConstantes.CODIGO_SECTOR_NO_INFORMA, sectoresEmpresa));
		parte.setInstitucionEducativa(
				buscarDominioEnLista(UtilConstantes.CODIGO_ENTIDAD_EDUCATIVA_NO_INFORMA, instituciones));
		parte.setEntidadTarjetaProfesional(
				buscarDominioEnLista(UtilConstantes.CODIGO_ENTIDAD_TARJETA_NO_INFORMA, entidades));

		personaFacade.guardarInformacionParte(parte);
	}

	private Dominio buscarDominioPorNombre(String nombre, List<Dominio> dominios) {
		Dominio dominio = null;
		for (Dominio dom : dominios) {
			if (dom.getDominioPK() != null && dom.getNombre() != null && dom.getNombre().equals(nombre)) {
				dominio = dom;
				break;
			}
		}
		return dominio;
	}

	private Dominio buscarDominioEnLista(String codigo, List<Dominio> dominios) {
		Dominio dominio = null;
		for (Dominio dom : dominios) {
			if (dom.getDominioPK() != null && dom.getDominioPK().getCodigo().equals(codigo)) {
				dominio = dom;
				break;
			}
		}
		return dominio;
	}

	/**
	 * Método que devuelve un objeto UbicacionDTO
	 * 
	 * @param idZonaGeografica
	 * @param direccion
	 * @return
	 */
	private UbicacionDTO crearUbicacionDTO(String idZonaGeografica, String direccion, String tipo) {
		UbicacionDTO ubicacion = new UbicacionDTO();
		ubicacion.setIdZonaGeografica(idZonaGeografica);
		ubicacion.setDireccion(direccion);
		ubicacion.setTipo(tipo);
		return ubicacion;
	}

	/**
	 * Método que devuelve un objeto CorreoDTO
	 * 
	 * @param direccion
	 * @return
	 */
	private CorreoElectronicoDTO crearCorreoDTO(String direccion, String tipoCorreo) {
		CorreoElectronicoDTO correo = new CorreoElectronicoDTO();
		correo.setDireccion(direccion);
		correo.setDireccionAnterior(direccion);
		correo.setTipo(tipoCorreo);
		return correo;
	}

	/**
	 * Método que verifica si la cadena tiene el tamaño permitido de lo contrario la
	 * acorta al tamaño permitido
	 * 
	 * @param cadena
	 * @param tamanio
	 * @return
	 */
	private String truncarCadena(String cadena, int tamanio) {
		String nuevaCadena;
		if (cadena.length() > tamanio)
			nuevaCadena = cadena.substring(0, tamanio);
		else
			nuevaCadena = cadena;

		return nuevaCadena;
	}

	@Override
	public void cerrarCasoConciliacion(CierreConciliacionDTO cierreConciliacion) throws EstadosCasoException {
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		String observaciones = "";

		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		}
		Caso casoActual = manejadorCaso.buscar(cierreConciliacion.getIdCaso());
		casoActual.setResultado(cierreConciliacion.getMotivo());
		String motivoResultado = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_RESULTADO_CASO_CONCILIACION,
				cierreConciliacion.getMotivo());

		List<String> args = new ArrayList<>();
		args.add(motivoResultado != null ? motivoResultado : "");
		args.add(cierreConciliacion.getObservaciones());
		observaciones = (String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO285.toString()),
				args.toArray()));

		casoActual.setFechaUltimaModificacion(new Date());
		casoActual.setIdUsuarioModificacion(usuarioModificacion);

		Long idAreaAsuntoClasificacion = manejadorAreaAsuntoClasificacion.obtenerIdPorLlaves(
				cierreConciliacion.getIdAsunto(),
				cierreConciliacion.getIdClasificacion() != null ? cierreConciliacion.getIdClasificacion() : 0);
		if (idAreaAsuntoClasificacion != null)
			casoActual.setIdAreaAsuntoClasificacion(idAreaAsuntoClasificacion);

		manejadorCaso.actualizar(casoActual);
		String estadoCaso = null;
		String tipoEvento = null;
		if (UtilDominios.RESULTADO_CASO_CONCILIACION_FALTA_COMPETENCIA.equals(cierreConciliacion.getMotivo())) {
			estadoCaso = UtilDominios.ESTADO_CASO_FALTA_DE_COMPETENCIA;
			tipoEvento = UtilDominios.TIPO_EVENTO_FALTA_DE_COMPENTENCIA;
		} else {
			estadoCaso = UtilDominios.ESTADO_CASO_CANCELADO;
			tipoEvento = UtilDominios.TIPO_EVENTO_CASO_CANCELADO;
		}

		this.cambiarEstadoCaso(cierreConciliacion.getIdCaso(), estadoCaso, cierreConciliacion.getFechaSolicitud(),
				tipoEvento, observaciones);

		reliquidacionFacade.generarReliquidacionTipoDevolucion(cierreConciliacion.getIdCaso(),
				cierreConciliacion.getMotivo());

		// String mensajeMinisterio =
		// integracionSWFacade.agregarCasoConciliacionMinisterio(casoActual.getIdCaso());
		// return mensajeMinisterio;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade#
	 * obtenerCasoPorIdTipoServicio(Long, String)
	 */
	public Caso obtenerCasoPorIdTipoServicio(Long idCaso, String tipoServicio) {
		return this
				.transformarEntidadSinDependencias(manejadorCaso.consultarCasoPorIdTipoServicio(idCaso, tipoServicio));
	}

	/**
	 * Metodo que consulta los casos pendienes para reparto a conciliador.
	 * 
	 * @author aperez
	 * @param idCentros: Lista de centros que tiene asociado el usuario.
	 * @return List<CasosNoAsignadosDTO>: Lista de casos pendientes para reparto.
	 */
	@Override
	public List<CasosNoAsignadosDTO> consultarCasosPendientesPorReparto(List<Long> idCentros) {
		return manejadorCaso.listarCasosNoAsignadosPorReparto(idCentros);
	}

	/**
	 * Obtiene la informacion del caso para la modificacion de parametros para
	 * reparto del caso (CONF-103).
	 * 
	 * @author aperez.
	 * 
	 * @param: idCaso: Identificador del caso.
	 * @return InformacionCasoDTO: DTO que contiene informacion del caso.
	 */
	@Override
	public InformacionCasoDTO obtenerInformacionCasoPorIdCaso(Long idCaso) {
		InformacionCasoDTO informacionCasoDTO = manejadorCaso.obtenerInformacionCasoPorIdCaso(idCaso);
		if (informacionCasoDTO.getHoraInicioAudiencia() != null) {
			FormatoHoraAudienciaDTO horasMostrar = new FormatoHoraAudienciaDTO();
			horasMostrar.setFormatoFechas(UtilOperaciones.formatoHoraString(informacionCasoDTO.getHoraInicioAudiencia(),
					informacionCasoDTO.getHoraFinAudiencia()));
			horasMostrar.setFechaInicio(informacionCasoDTO.getHoraInicioAudiencia());
			horasMostrar.setFechaFin(informacionCasoDTO.getHoraFinAudiencia());

			FechasAgendamientoDTO fechaMostrar = new FechasAgendamientoDTO();
			fechaMostrar
					.setFecha(UtilOperaciones.obtenerFechaComienzoDelDia(informacionCasoDTO.getHoraInicioAudiencia()));
			fechaMostrar.setHoras(Arrays.asList(horasMostrar));
			informacionCasoDTO.setFechasAgendamientoDTO(fechaMostrar);
		}
		return informacionCasoDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade#
	 * marcarCasosCobrados(java.util.Date, java.util.List, java.lang.Long)
	 */
	@Override
	public void marcarCasosCobrados(String fechaCobro, List<CasosCobradosDTO> casosAMarcar, Long idUsuario) {
		try {
			Date fechaDeCobro = UtilOperaciones.parsearFechaFormato(fechaCobro,
					UtilConstantes.FORMATO_FECHA_ANIO_MES_DIA_GUION);
			// 1. Recorre la lista de casos a marcar como cobrados
			for (CasosCobradosDTO casoCobradoDTO : casosAMarcar) {
				Caso caso = manejadorCaso.buscar(casoCobradoDTO.getIdCaso());
				// 2. Valida la existencia del caso
				if (caso != null) {
					// 3. Si el caso existe obtiene los datos de facturación del
					// mismo
					FacturacionCaso facturacionCaso = manejadorFacturacionCaso
							.obtenerFacturacionCasoPorCaso(caso.getIdCaso());
					// 4. Si los datos de facturación existen, registra la fecha
					// de cobro y cambia el indicador de cobro a cobrado
					if (facturacionCaso != null) {
						facturacionCaso.setFechaDeCobro(fechaDeCobro);
						facturacionCaso.setCobrado(true);
						manejadorFacturacionCaso.actualizar(facturacionCaso);
					}
				}
			}
		} catch (Exception e) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade#
	 * consultarCasosConvenioAudienciaPendiente(java.lang.Long)
	 */
	@Override
	public List<CasoDTO> consultarCasosConvenioAudienciaPendiente(Long idConvenio) {
		if (idConvenio == null)
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR019.toString()));
		return manejadorCaso.consultarCasosConvenioAudienciaPendiente(idConvenio);
	}

	/**
	 * Consulta los casos con las dependias de facturaicon y de PAGO
	 */
	@Override
	public Caso consultarCasosConPagos(Long idCaso) {
		Caso caso = manejadorCaso.buscar(idCaso);
		if (caso == null) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR531.toString()));
		}

		return new CasoDTO().tranformarEntidadConDependenciasPago(caso);
	}

	/**
	 * Actualiza los casos desde la ficha tecnica de conciliacion
	 */
	@Override
	public void actualizarCasoConciliacion(Caso caso, Boolean factura) {
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		FacturacionCaso facturacion = null;
		if (caso.getFacturacionCasoList() != null && !caso.getFacturacionCasoList().isEmpty()) {
			facturacion = caso.getFacturacionCasoList().get(0);
		}
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		}
		
		if(UtilConstantes.ID_SERVICIO_INSOLVENCIA.equals(caso.getIdServicio()) 
				&& caso.getEstadoCaso().equals(UtilConstantes.ESTADO_CASO_ADMITIDO)) {
			
		List<Persona> personas = manejadorRolPersonaCaso.consultarPersonasPorRolCaso(caso.getIdCaso(),
					UtilDominios.ROL_PERSONA_CONCILIADOR);
		
		List<ParametroDeServicio> parametro = manejadorParametroDeServicio.consultarParametrosDeServicio(
				UtilDominios.DIAS_ALERTA_CIERRE_DE_CASO, UtilConstantes.ID_SERVICIO_INSOLVENCIA);
		
				if(!parametro.isEmpty()) {
					String[] parts = parametro.get(0).getValor().split(",");
					
					for(String valor : parts) {
						ProgramacionAlerta programacionAlerta = new ProgramacionAlerta();
						programacionAlerta.setIdCaso(caso.getIdCaso());
						programacionAlerta.setEstado(UtilDominios.ESTADO_ALERTA_ACTIVA);
						programacionAlerta.setIdPersona(personas.get(0).getIdPersona());
						programacionAlerta.setAlerta(new Alerta(UtilDominios.DOMINIO_CODIGO_ALERTA_NOTVENCC));
						programacionAlerta.setFechaEjecucion(UtilOperaciones.adicionarDiasFecha(new Date(),Integer.parseInt(valor)));
						programacionAlertaFacade.crearProgramacionAlerta(programacionAlerta);
					}
					
				}
		}
		
		Evento evento = new Evento();
		evento.setCaso(caso);
		evento.setEstadoRegistro(UtilDominios.ESTADO_ALERTA_ACTIVA);
		evento.setFechaEvento(new Date());
		evento.setFechaUltimaModificacion(new Date());
		evento.setIdUsuarioModificacion(usuarioModificacion);
		evento.setTipoEvento("MODACA");
		evento.setObservaciones(caso.getEstadoCaso());
		evento.setIdCaso(caso.getIdCaso());
		eventoFacade.crear(evento);
		
		caso.setFacturacionCasoList(null);
		caso.setFacturacionCasoList(null);
		caso.setFechaUltimaModificacion(new Date());
		caso.setIdUsuarioModificacion(usuarioModificacion);
		manejadorCaso.actualizar(caso);
		if (!UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO.equals(caso.getIdServicio())) {
			Caso casoact = manejadorCaso.buscar(caso.getIdCaso());
			casoact.setIdConvenio(null);
			manejadorCaso.actualizar(casoact);
		}

		if (factura && facturacion != null) {
			facturacionCasoFacade.actualizarFacturacionRutaCaso(facturacion);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade#
	 * registrarResultadoJornada(com.ccb.simasc.transversal.dto.formularios.
	 * CapturaResultadoJornadaDTO)
	 */
	@Override
	public void registrarResultadoJornada(CapturaResultadoJornadaDTO resultado) throws EstadosCasoException {
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;

		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		}

		List<Audiencia> audiencias = manejadorAudiencia.consultarAudienciasCasoPorTipoYEstado(resultado.getIdCaso(),
				null, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);

		if (!audiencias.isEmpty()) {
			// asociar conciliador al caso
			Rol rol = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_CONCILIADOR);
			RolPersonaCasoPK pk = new RolPersonaCasoPK(resultado.getIdConciliador(), resultado.getIdCaso(),
					rol.getIdRol());
			RolPersonaCaso conciliador = manejadorRolPersonaCaso.buscar(pk);
			if (conciliador != null) {
				conciliador.setFechaUltimaModificacion(new Date());
				conciliador.setIdUsuarioModificacion(usuarioModificacion);
				asignarDatosConciliadorPrincipal(conciliador);
				manejadorRolPersonaCaso.actualizar(conciliador);
			} else {
				conciliador = new RolPersonaCaso();
				conciliador.setRolPersonaCasoPK(pk);
				conciliador.setMetodoNombramiento(UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
				asignarDatosConciliadorPrincipal(conciliador);
				manejadorRolPersonaCaso.crear(conciliador);
			}

			// actualizar estado de la audiencia
			Audiencia audiencia = audiencias.get(0);
			if (resultado.getCodigoResultado().equals(UtilDominios.RESULTADO_CASO_CONCILIACION_CANCELACION)) {
				audiencia.setEstado(UtilDominios.ESTADO_AUDIENCIA_CANCELADA);
			} else {
				audiencia.setEstado(UtilDominios.ESTADO_AUDIENCIA_REALIZADA);
				audiencia.setResultado(obtenerCodigoHomologacionAudiencia(resultado.getCodigoResultado()));
			}
			audiencia.setFechaUltimaModificacion(new Date());
			audiencia.setIdUsuarioModificacion(usuarioModificacion);
			manejadorAudiencia.actualizar(audiencia);

			Documento documento = manejadorDocumento.buscar(resultado.getIdDocumento());
			if (documento != null) {
				documento.setIdAudiencia(audiencia.getIdAudiencia());
				documento.setFechaUltimaModificacion(new Date());
				documento.setIdUsuarioModificacion(usuarioModificacion);
				manejadorDocumento.actualizar(documento);
			}
			// registrar el cierre del caso
			cerrarCasoJornada(resultado.getIdCaso(), resultado.getCodigoResultado(), resultado.getIdArea(),
					resultado.getIdAsunto(), resultado.getIdClasificacion());
		} else {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR312.toString()));
		}
	}

	private void asignarDatosConciliadorPrincipal(RolPersonaCaso conciliador) {
		if (conciliador != null) {
			conciliador.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
			conciliador.setTipoNombramiento(UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
			conciliador.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade#
	 * cerrarCasoJornada(java.lang.Long, java.lang.String)
	 */
	@Override
	public void cerrarCasoJornada(Long idCaso, String resultado, Long idArea, Long idAsunto, Long idClasificacion)
			throws EstadosCasoException {
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		String observaciones = "";

		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		}
		Caso casoActual = manejadorCaso.buscar(idCaso);
		String motivoResultado = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_RESULTADO_CASO_CONCILIACION,
				resultado);
		casoActual.setResultado(resultado);

		List<String> args = new ArrayList<>();
		args.add(motivoResultado != null ? motivoResultado : "");
		args.add("");
		observaciones = (String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO285.toString()),
				args.toArray()));

		casoActual.setFechaUltimaModificacion(new Date());
		casoActual.setIdUsuarioModificacion(usuarioModificacion);
		Long idAreaAsuntoClasificacion = manejadorAreaAsuntoClasificacion.obtenerIdPorLlaves(idAsunto,
				idClasificacion != null ? idClasificacion : 0);
		if (idAreaAsuntoClasificacion != null)
			casoActual.setIdAreaAsuntoClasificacion(idAreaAsuntoClasificacion);

		manejadorCaso.actualizar(casoActual);
		this.cambiarEstadoCaso(idCaso, obtenerEstadoCaso(resultado), new Date(),
				UtilDominios.TIPO_EVENTO_CASO_REGISTRADO, observaciones);

	}

	/**
	 * Lista los casos que quedaron en estado en creacion.
	 * 
	 * @return
	 */
	@Override
	public List<CasoIncompletoDTO> consultarCasosIncompletos() {

		List<CasoIncompletoDTO> casosIncompletosDTO = new ArrayList<>();

		try {
			casosIncompletosDTO = manejadorCaso.consultarCasosIncompletos();
		} catch (SimascException e) {
			throw SIMASCNegocioExcepcion.transformTopException(e);
		}

		return casosIncompletosDTO;
	}

	/**
	 * Método encargado de relizar la asignación, o actualizarla, de una persona
	 * como auxiliar administrativo a un caso
	 * 
	 * @param dto
	 * @param caso
	 * @param auxiliaresAnt
	 */
	private void actualizarAsignarAuxiliarAdministrativoCaso(DatosBasicosCasoDTO dto, Caso caso,
			List<Persona> auxiliaresAnt) {
		if (dto.getIdAuxiliar() != null) {
			if (!auxiliaresAnt.isEmpty()) {
				Persona auxiliarActualCaso = auxiliaresAnt.get(0);

				if (auxiliarActualCaso.getIdPersona().intValue() != dto.getIdAuxiliar().intValue()) {
					// Elimina al auxiliar designado actualmente
					rolPersonaCasoFacade.eliminarRolPersonaCaso(caso.getIdCaso(), auxiliarActualCaso.getIdPersona(),
							UtilDominios.ROL_PERSONA_AUXILIAR_ADM);

					// Valida si la persona ya estuvo asignada al caso con el
					// rol auxiliar
					InformacionFiltro filtroNombreRol = new InformacionFiltro(TipoFiltro.EXACTO, "rol.nombre",
							UtilDominios.ROL_PERSONA_AUXILIAR_ADM);
					InformacionFiltro filtroPersona = new InformacionFiltro(TipoFiltro.EXACTO,
							RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_PK_ID_PERSONA, dto.getIdAuxiliar());
					InformacionFiltro filtroCaso = new InformacionFiltro(TipoFiltro.EXACTO,
							RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_PK_ID_CASO, caso.getIdCaso());

					List<InformacionFiltro> filtros = new ArrayList<>();
					filtros.add(filtroNombreRol);
					filtros.add(filtroPersona);
					filtros.add(filtroCaso);

					List<RolPersonaCaso> auxiliaresAsignadosAnt = manejadorRolPersonaCaso.consultar(filtros, null,
							null);
					RolPersonaCaso auxiliarYaAsignado = (auxiliaresAsignadosAnt != null
							&& !auxiliaresAsignadosAnt.isEmpty() && auxiliaresAsignadosAnt.size() == 1
									? auxiliaresAsignadosAnt.get(0)
									: null);

					// Si la persona ya estuvo asignada al caso con el rol
					// auxiliar se actualiza el estado de registro. En caso
					// contrario se realiza la nueva asignación
					if (auxiliarYaAsignado != null) {
						auxiliarYaAsignado.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
						manejadorRolPersonaCaso.actualizar(auxiliarYaAsignado);
					} else {
						rolPersonaCasoFacade.crearRolPersonaCaso(dto.getIdAuxiliar(), caso.getIdCaso(),
								UtilDominios.ROL_PERSONA_AUXILIAR_ADM, UtilDominios.ESTADO_ROL_PERSONA_CASO_ASIGNADO);
					}

				}

			} else {
				rolPersonaCasoFacade.crearRolPersonaCaso(dto.getIdAuxiliar(), caso.getIdCaso(),
						UtilDominios.ROL_PERSONA_AUXILIAR_ADM, UtilDominios.ESTADO_ROL_PERSONA_CASO_ASIGNADO);
			}

		} else {
			// Elimina auxiliar actual, si no tiene, no eliminar
			if (manejadorRolPersonaCaso.validaRolExisteCaso(caso.getIdCaso(), UtilDominios.ROL_PERSONA_AUXILIAR_ADM)) {
				RolPersonaCaso personaCaso = manejadorRolPersonaCaso
						.consultarPersonaPorRolCasoPrestador(caso.getIdCaso(), UtilDominios.ROL_PERSONA_AUXILIAR_ADM);
				manejadorRolPersonaCaso.eliminar(personaCaso);
			}
		}
	}

	private String obtenerEstadoCaso(String resultadoCaso) {
		String estadoCaso;
		switch (resultadoCaso) {
		case UtilDominios.RESULTADO_CASO_CONCILIACION_IMPOSIBILIDAD:
		case UtilDominios.RESULTADO_CASO_CONCILIACION_ACUERDO:
		case UtilDominios.RESULTADO_CASO_CONCILIACION_INASISTENCIA:
			estadoCaso = UtilDominios.ESTADO_CASO_PENDIENTE_ENTREGA_DOCUMENTO_RESULTADO;
			break;
		case UtilDominios.RESULTADO_CASO_CONCILIACION_ARREGLO_DIRECTO:
		case UtilDominios.RESULTADO_CASO_CONCILIACION_CANCELACION:
			estadoCaso = UtilDominios.ESTADO_CASO_CANCELADO;
			break;
		default: // FALTA DE COMPETENCIA
			estadoCaso = UtilDominios.ESTADO_CASO_FALTA_DE_COMPETENCIA;
		}
		return estadoCaso;
	}

	private String obtenerCodigoHomologacionAudiencia(String resultadoCaso) {
		String resultadoAudiencia;
		switch (resultadoCaso) {
		case UtilDominios.RESULTADO_CASO_CONCILIACION_IMPOSIBILIDAD:
			resultadoAudiencia = UtilDominios.RESULTADO_AUDIENCIA_IMPOSIBILIDAD_DE_ACUERDO;
			break;
		case UtilDominios.RESULTADO_CASO_CONCILIACION_ACUERDO:
			resultadoAudiencia = UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_TOTAL;
			break;
		case UtilDominios.RESULTADO_CASO_CONCILIACION_ARREGLO_DIRECTO:
			resultadoAudiencia = UtilDominios.RESULTADO_AUDIENCIA_ARREGLO_DIRECTO;
			break;
		case UtilDominios.RESULTADO_CASO_CONCILIACION_CANCELACION:
			resultadoAudiencia = UtilDominios.RESULTADO_AUDIENCIA_CANCELADA;
			break;
		case UtilDominios.RESULTADO_CASO_CONCILIACION_FALTA_COMPETENCIA:
			resultadoAudiencia = UtilDominios.RESULTADO_AUDIENCIA_FALTA_COMPETENCIA;
			break;
		default: // INASISTENCIA
			resultadoAudiencia = UtilDominios.RESULTADO_AUDIENCIA_INASISTENCIA;
		}
		return resultadoAudiencia;
	}

	@Override
	public String agregarCasoMinisterioConciliacion(Long idCaso, String idUsuario) throws EstadosCasoException {

		Object[] resultados = integracionSWFacade.agregarCasoConciliacionMinisterio(idCaso);
		if ((boolean) resultados[2]) {
			this.cambiarEstadoCaso(idCaso, UtilDominios.ESTADO_CASO_REGISTRADO, new Date(),
					UtilDominios.TIPO_EVENTO_CASO_REGISTRADO, (String) resultados[0]);
			fechaCasoFacade.registrarFechaCaso(new Date(), UtilDominios.ESTADO_TIPO_FECHA_CASO_CIERRE, idCaso,
					idUsuario);
		}
		try {
			if (((Mensaje) resultados[1]).getSicDocumentoResultado() != null
					&& ((Mensaje) resultados[1]).getSicDocumentoResultado().getValue() != null) {
				documentoFacade.guardarDocumentosMinisterio(
						((Mensaje) resultados[1]).getSicDocumentoResultado().getValue(), idCaso);
			}
		} catch (Exception e) {
			logger.error(e);
			resultados[0] = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR490.toString());
		}
		return (String) resultados[0];
	}

	@Override
	public List<DatosReversarResultadoDTO> consultarDatoCasoReversarJornada(Long idCaso) {
		List<DatosReversarResultadoDTO> datos = manejadorCaso.consultarDatoCasoReversarJornada(idCaso);
		if (datos.isEmpty())
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR496.toString()));
		return datos;
	}

	@Override
	public void reversarResultadoCasoJornada(Long idCaso) {
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;

		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getIdUsuario() != null)
			usuarioModificacion = appContext.getContextoSesion().getIdUsuario();

		// actualizar datos de audiencia
		Audiencia audiencia = manejadorAudiencia.consultarUltimaAudiencia(idCaso,
				UtilDominios.TIPO_AUDIENCIA_ARBITRAL_CONCILIACION);

		audiencia.setEstado(UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		audiencia.setResultado(null);
		audiencia.setFechaUltimaModificacion(new Date());
		audiencia.setIdUsuarioModificacion(usuarioModificacion);
		manejadorAudiencia.actualizar(audiencia);

		// borrado de documentos
		documentoFacade.eliminarDocumentosCasoJornada(idCaso, usuarioModificacion);

		// borrar resultado de caso
		Caso caso = manejadorCaso.buscar(idCaso);
		caso.setEstadoCaso(UtilDominios.ESTADO_CASO_RADICADO);
		caso.setResultado(null);
		caso.setFechaUltimaModificacion(new Date());
		caso.setIdUsuarioModificacion(usuarioModificacion);
		manejadorCaso.actualizar(caso);

		// borrar el conciliador del caso
		RolPersonaCaso conciliador = manejadorRolPersonaCaso.consultarPersonaPorRolCaso(idCaso,
				UtilDominios.ROL_PERSONA_CONCILIADOR);
		conciliador.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
		conciliador.setFechaUltimaModificacion(new Date());
		conciliador.setIdUsuarioModificacion(usuarioModificacion);
		manejadorRolPersonaCaso.actualizar(conciliador);

		// limpiar eventos del caso
		manejadorEvento.limpiarEvento(idCaso, Arrays.asList(UtilDominios.TIPO_EVENTO_RADICACION_DE_CASO,
				UtilDominios.TIPO_EVENTO_AUDIENCIA_PROGRAMADA));

		eventoFacade.registrarEvento(idCaso, UtilDominios.TIPO_EVENTO_REVERSAR_RESULTADO,
				"Se reverso el resultado del caso de jornada", usuarioModificacion);

	}

	@Override
	public Caso consultarCasoConConvenio(Long idCaso) {
		Caso caso = manejadorCaso.buscar(idCaso);
		if (caso.getResultado() != null && caso.getResultado().equals(UtilDominios.RESULTADO_CASO_CONCILIACION_ACUERDO))
			caso = new CasoDTO().transformarEntidadConDependenciaConvenio(caso);
		else {
			List<Long> args = new ArrayList<>();
			args.add(idCaso);
			throw new SimascException(String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR602.toString()), args.toArray()));
		}
		return caso;
	}

	@Override
	public List<CasosSinCerrarDTO> consultarCasosSinCerrarConciliador(Long idConciliador) {

		List<CasosSinCerrarDTO> listaCasosSinCerrar = manejadorCaso.consultarCasosSinCerrarConciliacion(idConciliador);
		String resultadoAudiencia = "";
		for (int i = 0; i < listaCasosSinCerrar.size(); i++) {

			AudienciaDTO ultimaAudiencia = manejadorAudiencia
					.consultarUltimaAudienciaEstadoCaso(listaCasosSinCerrar.get(i).getIdCaso(), null);
			if (ultimaAudiencia != null) {
				resultadoAudiencia = manejadorAudiencia
						.consultarNombreResultadoAudiencia(ultimaAudiencia.getIdAudiencia());
				listaCasosSinCerrar.get(i).setFechaUltimaAudiencia(ultimaAudiencia.getHoraInicio());
				listaCasosSinCerrar.get(i).setResultadoUltimaAudiencia(resultadoAudiencia);
			}

		}

		return listaCasosSinCerrar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade#
	 * consultarCasoActivo(java.lang.Long)
	 */
	@Override
	public CasoDTO consultarCasoActivo(Long idCaso) {
		return new CasoDTO().transformarSinDependencias(manejadorCaso.buscarCasoActivo(idCaso));
	}

	@Override
	public void reabrirCaso(Long idCaso, String observaciones) {
		Caso caso = manejadorCaso.buscar(idCaso);
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		}

		// crear evento del caso
		eventoFacade.registrarEvento(caso, UtilDominios.TIPO_EVENTO_CASO_REABIERTO, observaciones, usuarioModificacion,
				null, null);

		// borrar el resultado general del caso y actualiza el estado del caso
		// en conciliacion.
		actualizarResultadoEstadoCaso(caso, usuarioModificacion);

		// obtener el conciliador asignado del caso y generar la notificacion
		generarNotificacionConciliadorAsignado(caso);

	}

	/**
	 * Metodo que permite generar la notificacion para el conciliador asignado al
	 * caso.
	 * 
	 * @param caso
	 */
	private void generarNotificacionConciliadorAsignado(Caso caso) {
		List<String> estados = new ArrayList<String>();
		estados.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		estados.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		List<RolPersonaCaso> conciliadoresRPC = manejadorRolPersonaCaso.consultaConciliadoresCasoEstadoNombramiento(
				caso.getIdCaso(), estados, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		if (conciliadoresRPC != null && !conciliadoresRPC.isEmpty()) {
			RolPersonaCaso conciliador = conciliadoresRPC.get(0);
			List<Persona> lstPersonas = new ArrayList<Persona>();
			lstPersonas.add(conciliador.getPersona());
			List<String> lstCuerpo = new ArrayList<String>();
			String cuerpo = String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO306.toString()),
					String.valueOf(caso.getIdCaso()), caso.getNombre(), conciliador.getPersona().getNombreCompleto());
			lstCuerpo.add(cuerpo);
			correoRolPersonaCasoFacade.envioDeCorreo(UtilConstantes.ASUNTO_CORREO_REAPERTURA_CASO, lstPersonas, null,
					null, lstCuerpo, caso.getIdCaso(), null, null, Boolean.FALSE);
		}
	}

	/**
	 * Metodo que permite actualizar el resultado y estado del caso.
	 * 
	 * @param caso:                Caso modificado.
	 * @param usuarioModificacion: usuario que realiza la modificacion.
	 */
	private void actualizarResultadoEstadoCaso(Caso caso, String usuarioModificacion) {
		caso.setResultado(null);
		caso.setEstadoCaso(UtilDominios.ESTADO_CASO_EN_CONCILIACION);
		caso.setFechaUltimaModificacion(new Date());
		caso.setIdUsuarioModificacion(usuarioModificacion);
		manejadorCaso.actualizar(caso);
	}

	@Override
	public List<CasosControlLegalidadDTO> consultarCasosParaControlLegalidad(boolean reasignarAnalista, String rol,
			Long idPersona) {
		return manejadorCaso.consultarCasosParaControlLegalidad(reasignarAnalista, rol, idPersona);
	}

	@Override
	public void digitarInformacionConstanciaNoCompetencia(Long idCaso, String observaciones,
			String idPersonaModificacion, Long idConciliador) throws EstadosCasoException {

		List<String> argsTexto = new ArrayList<String>();
		Persona conciliador = manejadorPersona.buscar(idConciliador);
		argsTexto.add(conciliador.getNombreCompleto());
		argsTexto.add(observaciones);
		String mensaje = (String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO370.toString()),
				argsTexto.toArray()));
		cerrarCasoConciliacionNoCompetencia(idCaso, mensaje, false, mensaje);

	}

	@Override
	public void actualizarResultadoCaso(Long idCaso, String resultadoCaso, String usuarioModificacion) {
		Caso caso = manejadorCaso.buscar(idCaso);
		if (caso != null) {
			caso.setResultado(resultadoCaso);
			caso.setIdUsuarioModificacion(usuarioModificacion);
			caso.setFechaUltimaModificacion(new Date());
			manejadorCaso.actualizar(caso);
		}

	}

	@Override
	public Boolean validarNombramientoArbitros(Long idCaso) {
		return manejadorCaso.validarNombramientoArbitros(idCaso) != null ? true : false;
	}

	@Override
	public List<Caso> obtenerCasosActivosArbitro(Long idPersona, Long idMateria) {
		return manejadorCaso.obtenerCasosActivosArbitro(idPersona, idMateria);
	}

	@Override
	public Boolean validaCuantiaMayor(String valorPrentenciones) {
		try {
			Long valorPretenciones = Long.valueOf(valorPrentenciones.trim().replace(".", ""));
			ParametrosGenerales parametrosGenerales = parametrosGeneralesFacade
					.consultarPorCodigo(UtilConstantes.SMLMV);

			Long cuantiaCuatrocientosSMLV = parametrosGenerales.getValorNumerico() * 400;
			Long cuantiaQuinientosSMLV = parametrosGenerales.getValorNumerico() * 500;

			return ((valorPretenciones >= cuantiaCuatrocientosSMLV) && (valorPretenciones < cuantiaQuinientosSMLV));

		} catch (Exception e) {
			return false;
		}

	}

	public String getErrorFila() {
		return errorFila;
	}

	public void setErrorFila(String errorFila) {
		this.errorFila = errorFila;
	}

	@Override
	public void realizaReaperturaCaso(DatosBasicosCasoDTO datosBasicosCasoDTO, String usuarioModificacion)
			throws Exception {

		eventoFacade.creaEventoReAperturaCaso(datosBasicosCasoDTO, usuarioModificacion);

		fechaCasoFacade.creaFechaCasoReApertura(datosBasicosCasoDTO.getCaso(), usuarioModificacion);

		if(datosBasicosCasoDTO.isRestauraEstadoSorteabilidad()) {
			if(datosBasicosCasoDTO.getIdServicio().equals(UtilConstantes.ID_SERVICIO_ARBITRAJ_SOCIAL)){
				List<RolPersonaCaso> arbitrosPrincipales = manejadorRolPersonaCaso
						.consultarArbitrosPrincipalesCaso(datosBasicosCasoDTO.getCaso().getIdCaso());

				for (RolPersonaCaso persona : arbitrosPrincipales) {
					personaFacade.asignarRolArbitroSocial(persona.getPersona().getIdPersona(), UtilDominios.ESTADO_ARBITROS_HABILITADO, UtilConstantes.ID_ROL_ARBITRO, false);				
				}
			}	
			personaFacade.bloqueaArbitrosReaAperturaCaso(datosBasicosCasoDTO, usuarioModificacion);
		}				

	}
	
	public Boolean habilitaCambioEtapa(Long idCaso){
		Boolean habilita = false;
		Caso caso = manejadorCaso.buscar(idCaso);
		if(caso!= null){
			if(caso.getIdServicio().equals( new Long(UtilConstantes.ID_SERVICIO_ARBITRAJE_INTERNACIONAL))){
				//Se debe buscar la fecha de constitucion
				FechaCaso fecha = manejadorFechaCaso.consultaFechaCaso(idCaso, UtilDominios.TIPO_FECHA_CASO_CONSTITUCION);
				if(fecha != null){
					habilita = true;
				}
			}else{
				if(caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_RECUPERACION_EMPRESARIAL)){
					//Se debe buscar la carta de entrega del expediente
					List<CartaPersona> cartas = cartaPersonaFacade.consultarCartas(idCaso);
					for(CartaPersona carta : cartas){
						if(carta.getIdPlantillaCarta().equals(UtilDominios.NOMBRE_PLANTILLA_CARTA_ENTREGA_EXPEDIENTE_ARBITRO_RECUPERACION_EMPRESARIAL)){
							habilita = true;
						}
					}
				}else{
					List<Audiencia> audiencias = audienciaFacade.obtenerAudienciasCaso(idCaso);
					String liberarSuplentes = manejadorParametroServicioSorteo.audenciaLiberaSuplente(idCaso);
					// validamos que las audiencias programadas no sean del tipo especificado
					// en el campo audiencia_libera_suplente
					for (Audiencia audiencia : audiencias) {
						if (liberarSuplentes.equalsIgnoreCase(audiencia.getTipoAudiencia())) {
							habilita = true;
						}
					}
				}
			}
		}		
		return habilita;
	}
			
		

	@Override
	public void cambiaEstadoPronunciamientoCasoInsolvencia(String tipoPronunciamiento, Long idCaso, Long idPersona) {

		Caso caso = manejadorCaso.buscar(idCaso);
		logger.info("Pronunciamiento: "+tipoPronunciamiento);
		if (caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_INSOLVENCIA)) {
			if (tipoPronunciamiento.equals(UtilDominios.TIPO_PRONUNCIAMIENTO_NO_SE_PRONUNCIA)
					|| tipoPronunciamiento.equals(UtilDominios.TIPO_PRONUNCIAMIENTO_DECLINA)) {

				caso.setEstadoCaso(UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION);

				PersonaServicioMateria personaServicioMateria = manejadorPersonaServicioMateria
						.consultarPersonaServicioMateriaSinExcepcion(UtilConstantes.ID_SERVICIO_INSOLVENCIA,
								UtilConstantes.ID_SIN_MATERIA, idPersona);

				personaServicioMateria
						.setCantidadCasosAsignados(personaServicioMateria.getCantidadCasosAsignados() - 1);
				manejadorPersonaServicioMateria.actualizar(personaServicioMateria);
				
				CasoTramiteOrdinarioFacade casoTramite = new CasoTramiteOrdinarioFacade();
				
				RadicarCasoConvenioDTO radicacion = crearObjetoRadicacion(caso);
				try {
					casoTramite.llamarReparto(radicacion, UtilConstantes.USUARIO_DEFECTO_SIMASC);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}

			} else if (tipoPronunciamiento.equals(UtilDominios.TIPO_PRONUNCIAMIENTO_ACEPTA)) {
				caso.setEstadoCaso(UtilDominios.ESTADO_CASO_EN_ESTUDIO);

				RolPersonaCaso rolPersonaCaso = rolPersonaCasoFacade.consultaPersonaAsignadaCaso(idPersona, idCaso);

				notificacionFacade.notificaUsuarioPlantilla(
						UtilDominios.NOMBRE_PLANTILLA_CARTA_NOTIFICACION_COMUNICACION_CONCILIADOR_INSOLVENCIA,
						rolPersonaCaso, UtilConstantes.PREADMISION);

				alertaFacade.programaAlertaVencimientoPago(caso, idPersona);

			}
		}

		manejadorCaso.actualizar(caso);

	}
	
	
/*	@Override
	public void nuevoRepartoEquidad(String tipoPronunciamiento, Long idCaso, Long idPersona, Long idServicio) {
		Caso caso = new  Caso();
		caso.setIdCaso(idCaso);
		System.out.println(" nuevo reparto a  llamarRepartoEquidad");
		caso.setEstadoCaso(UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION);						
		caso.setIdServicio(idServicio);
		RadicarCasoConvenioDTO radicacion = new RadicarCasoConvenioDTO();
		radicacion.setCaso(caso);
		try {
			casoTramiteFacade.llamarRepartoEquidad(radicacion, UtilConstantes.USUARIO_DEFECTO_SIMASC);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		manejadorCaso.actualizar(caso);

	}*/

	// protected region metodos adicionales end

}
