package com.ccb.simasc.comun.webservice.integracion.implementacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBElement;
import com.ccb.simasc.comun.webservice.integracion.interfaces.IClienteSWMinisterio;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorHomologacionSistemaExterno;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorResultadoAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorZonaGeografica;
import com.ccb.simasc.transversal.dto.ResultadoMinisterioDTO;
import com.ccb.simasc.transversal.dto.formularios.DatosCasoMinisterioDTO;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.ValorHonorariosActor;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;
import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;
import com.simasc.clientes.ministerio.ArbitroExternoS;
import com.simasc.clientes.ministerio.ArrayOfArbitroExternoS;
import com.simasc.clientes.ministerio.ArrayOfConvocadoS;
import com.simasc.clientes.ministerio.ArrayOfConvocanteS;
import com.simasc.clientes.ministerio.ArrayOfIdentificacionS;
import com.simasc.clientes.ministerio.ArrayOfProfesionPersona;
import com.simasc.clientes.ministerio.ArrayOfResultadoDeUnCasoS;
import com.simasc.clientes.ministerio.ArrayOfSesionDeAudienciaArbitrajeS;
import com.simasc.clientes.ministerio.ArrayOfSesionDeAudienciaS;
import com.simasc.clientes.ministerio.Caso;
import com.simasc.clientes.ministerio.CasoClasificacionAsunto;
import com.simasc.clientes.ministerio.ConvocadoS;
import com.simasc.clientes.ministerio.ConvocanteS;
import com.simasc.clientes.ministerio.DocumentoFileStreamS;
import com.simasc.clientes.ministerio.DocumentoLaudo;
import com.simasc.clientes.ministerio.HechosDeUnCasoS;
import com.simasc.clientes.ministerio.ISICAACWebService;
import com.simasc.clientes.ministerio.IdentificacionS;
import com.simasc.clientes.ministerio.LiquidacionAmigablecomposicionS;
import com.simasc.clientes.ministerio.LiquidacionArbitraje;
import com.simasc.clientes.ministerio.LiquidacionC;
import com.simasc.clientes.ministerio.ManejoConflictoS;
import com.simasc.clientes.ministerio.Mensaje;
import com.simasc.clientes.ministerio.ObjectFactory;
import com.simasc.clientes.ministerio.Organizacion;
import com.simasc.clientes.ministerio.Persona;
import com.simasc.clientes.ministerio.ProfesionPersona;
import com.simasc.clientes.ministerio.ResultadoAmigableComposicionS;
import com.simasc.clientes.ministerio.ResultadoArbitraje;
import com.simasc.clientes.ministerio.ResultadoDeUnCasoS;
import com.simasc.clientes.ministerio.SICAACWebService;
import com.simasc.clientes.ministerio.SesionDeAudienciaArbitrajeS;
import com.simasc.clientes.ministerio.SesionDeAudienciaS;
import com.simasc.clientes.ministerio.Usuario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author fguzman
 *
 */
@Stateless
@LocalBean
public class ClienteSWMinisterio implements IClienteSWMinisterio {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LogManager.getLogger(ClienteSWMinisterio.class.getName());

	private SICAACWebService sicaacWebServicePort;
	private ISICAACWebService clientyeService;
	ObjectFactory generic = new ObjectFactory();

	/**
	 * Manejador utilizado para consultar funcionalidad relacionadas con la
	 * entidad ParametrosGenerales
	 */
	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;

	/**
	 * Manejador que consulta pais y ciudad
	 */
	@EJB
	ManejadorZonaGeografica manejadorZonaGeografica;

	@EJB
	ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	ManejadorPersona manejadorPersona;
	
	@EJB
	ManejadorResultadoAudiencia manejadorResultadoAudiencia;
	
	@EJB
	ManejadorDocumento manejadorDocumento;

	@EJB
	ManejadorHomologacionSistemaExterno manejadorHomologacionSistemaExterno;

	/**
	 * Se instancia el endpoint y el cliente del servicio a consumir
	 */
	@PostConstruct
	public void init() {
		String urlMinisterio = consultarUrl();

		try {
			sicaacWebServicePort = new SICAACWebService(new URL(urlMinisterio));
			clientyeService = sicaacWebServicePort.getBasicHttpBindingISICAACWebService();
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
		}

	}

	/**
	 * METODOS DE MINISTERIO
	 */

	@Override
	public Mensaje agregarCasoConciliacionMinisterio(DatosCasoMinisterioDTO datosCaso) {
		Usuario usuario = new Usuario();
		Mensaje mensaje = new Mensaje();
		IdentificacionS conciliador = null;
		ObjectFactory generico = new ObjectFactory();

		Caso crearcaso = this.crearCaso(datosCaso);
		CasoClasificacionAsunto casoclasificacion = this.crearCasoClasificacionAsunto(datosCaso);
		HechosDeUnCasoS hechos = this.creacionHechos(datosCaso);
		if (datosCaso.getConciliador() != null) {
			conciliador = this.crearConciliador(datosCaso);
		}
		ManejoConflictoS manejoConflicto = this.creacionManejoConflicto();
		ArrayOfSesionDeAudienciaS sesionesAudienciaConciliador = this.crearAudiencias(datosCaso);
		ArrayOfConvocadoS convocados = this.crearConvocados(datosCaso);
		ArrayOfConvocanteS convocantes = this.crearConvocantes(datosCaso);

		JAXBElement<String> modulo = generico.createUsuarioSicModulo(UtilConstantes.USUARIO_MODULO_CONCILIACION);
		usuario = this.crearUsuario(modulo, datosCaso);
		ArrayOfResultadoDeUnCasoS resultados = this.crearResultadoCasoConciliacion(datosCaso);

		LiquidacionC liquidacion = this.crearLiquidacionConciliacion(datosCaso);

		mensaje = clientyeService.agregarCasosConciliacion(crearcaso, casoclasificacion, convocantes, convocados,
				hechos, conciliador, manejoConflicto, sesionesAudienciaConciliador, liquidacion, resultados, usuario);

		return mensaje;
	}

	/**
	 * Metodo que utiliza el cliente del servicio de ministerio para enviar los
	 * casos de arbitraje
	 * 
	 * @param DatosCasoMinisterioDTO
	 */
	public String agregarCasoArbitrajeMinisterio(DatosCasoMinisterioDTO datosCaso) {

		Usuario usuario = new Usuario();
		Mensaje mensaje = new Mensaje();
		Double SMLMV = manejadorParametrosGenerales.buscar(UtilConstantes.SMLMV).getValorNumerico().doubleValue();
		int cantidadArbitros = 0;
		ObjectFactory generico = new ObjectFactory();
		JAXBElement<String> modulo = generico.createUsuarioSicModulo(UtilConstantes.USUARIO_MODULO_ARBITRAJE);
		usuario = this.obtenerDatosUsuario(modulo);
		Caso crearcaso = new Caso();
		CasoClasificacionAsunto clasificacion;
		crearcaso.setSicCasoActivo(false);
		crearcaso.setSicCasoRemitido(false);
		crearcaso.setSicCasoRemitido(false);
		crearcaso.setSicCasoReportado(false);
		crearcaso.setSicCuantiaIndeterminada(false);
		crearcaso.setSicCuantiaPretensiones(BigDecimal.ZERO);
		crearcaso.setSicIdCaso((long) 0);
		crearcaso.setSicIdSolicitanteServicio((long) 0);
		crearcaso.setSicIdDatosEntidadHabilitada((long) 0);
		crearcaso.setSicIdTiempoConflicto((long) 0);
		crearcaso.setSicIdTipoFinalidadMinisterio((long) 0);
		crearcaso.setSicIdUbicacionHechos((long) 0);
		crearcaso.setSicFechaRegistro(UtilOperaciones.covertirFechaXMLGregorianCalendar(new Date()));
		JAXBElement<String> descripcionHechos = generico.createCasoSicDescripcionHechos(UtilConstantes.CAMPO_VACIO);
		crearcaso.setSicDescripcionHechos(descripcionHechos);
		LiquidacionArbitraje liquidacion;

		if (datosCaso.getCaso().getIdServicio().toString().equals(UtilConstantes.ID_SERVICIO_ARBITRAJE_SOCIAL)) {
			crearcaso.setSicCasoGratuito(true);
		} else {
			crearcaso.setSicCasoGratuito(false);
		}
		JAXBElement<String> descripcionPretenciones = generico
				.createCasoSicDescripcionPretensiones(UtilConstantes.CAMPO_VACIO);
		crearcaso.setSicDescripcionPretensiones(descripcionPretenciones);
		crearcaso.setSicFechaSolicitud(
				UtilOperaciones.covertirFechaXMLGregorianCalendar(datosCaso.getCaso().getFechaRadicacion()));
		JAXBElement<String> codCasoString = generico
				.createCasoSicNumeroResgistro(datosCaso.getCaso().getIdCaso().toString());
		usuario.setSicIdOrigen(datosCaso.getCaso().getIdCaso());
		crearcaso.setSicNumeroResgistro(codCasoString);

		IdentificacionS secretario = new IdentificacionS();
		if (datosCaso.getSecretario().getPersona().getTipoDocumento().toString() != null) {
			if (datosCaso.getSecretario().getPersona().getTipoDocumento().toString() != null) {
				JAXBElement<String> numeroDocumentoSecretario = generico.createIdentificacionSSicNumeroDeDocumento(
						datosCaso.getSecretario().getPersona().getNumeroDocumento());
				secretario.setSicTipoDeDocumento(
						tipoDocumentoPersonaMinisterio(datosCaso.getSecretario().getPersona().getTipoDocumento()));
				secretario.setSicNumeroDeDocumento(numeroDocumentoSecretario);
			}
		} else {
			secretario = null;
		}
		clasificacion = new CasoClasificacionAsunto();
		// Validacion de asunto
		if (datosCaso.getCaso().getAreaAsuntoClasificacion() != null
				&& datosCaso.getCaso().getAreaAsuntoClasificacion().getIdClasificacion() != null) {
			crearcaso.setSicIdAsunto((long) datosCaso.getCaso().getAreaAsuntoClasificacion().getIdAsunto());
			clasificacion.setSicIdCasoClasificacionAsunto(
					(long) datosCaso.getCaso().getAreaAsuntoClasificacion().getIdClasificacion());
		} else {
			clasificacion.setSicIdCaso((long) 0);
			crearcaso.setSicIdAsunto((long) 15);
		}
		ArrayOfConvocanteS convocantes = new ArrayOfConvocanteS();
		ArrayOfConvocadoS convocados = new ArrayOfConvocadoS();
		for (com.ccb.simasc.transversal.entidades.Persona parte : datosCaso.getPartesCaso()) {
			RolPersonaCaso rolPersona = manejadorRolPersonaCaso.consultaRolPersonaId(parte.getIdPersona(),
					datosCaso.getCaso().getIdCaso(), UtilDominios.ESTADO_ROL_PERSONA_CASO_NO_APLICA);

			if (UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE.equals(rolPersona.getRol().getNombre())) {
				if (parte.getTipoPersona().equals(UtilDominios.TIPO_PERSONA_JURIDICO)) {
					JAXBElement<Organizacion> empresa = generico.createOrganizacion((this.cargarEmpresa(parte)));
					ConvocanteS convocante = new ConvocanteS();
					convocante.setSicidInvolucradoCaso((long) 0);
					convocante.setSictipoInvolucrado((long) 0);
					convocante.setSicparteOrganizacion(empresa);
					List<com.ccb.simasc.transversal.entidades.Persona> apoderados = manejadorPersona
							.getConsultarPartesCasoPorRol(datosCaso.getCaso().getIdCaso(),
									UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
					for (com.ccb.simasc.transversal.entidades.Persona persona : apoderados) {
						if (!persona.getSexo().equals(UtilDominios.SEXOS_INDEFINIDO)) {
							JAXBElement<Persona> apoderado = generico.createPersona(this.cargarPersona(persona));
							convocante.setSicapoderadoPersona(apoderado);
						}
					}
					convocantes.getConvocanteS().add(convocante);
				} else {
					JAXBElement<Persona> partePersona = generico.createPersona((this.cargarPersona(parte)));
					ConvocanteS convocante = new ConvocanteS();
					convocante.setSicpartePersona(partePersona);
					List<com.ccb.simasc.transversal.entidades.Persona> apoderados = manejadorPersona
							.getConsultarPartesCasoPorRol(datosCaso.getCaso().getIdCaso(),
									UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
					for (com.ccb.simasc.transversal.entidades.Persona persona : apoderados) {
						if (!persona.getSexo().equals(UtilDominios.SEXOS_INDEFINIDO)) {
							JAXBElement<Persona> apoderado = generico.createPersona(this.cargarPersona(persona));
							convocante.setSicapoderadoPersona(apoderado);
							JAXBElement<ArrayOfProfesionPersona> profesiones = generico
									.createArrayOfProfesionPersona(CargarProfesion(persona));
							convocante.setSiclistaProfesionesPersonaConvocante(profesiones);
						}
					}
					convocantes.getConvocanteS().add(convocante);
				}
			}
			if (UtilDominios.ROL_PERSONA_PARTE_DEMANDADA.equals(rolPersona.getRol().getNombre())) {
				if (parte.getTipoPersona().equals(UtilDominios.TIPO_PERSONA_JURIDICO)) {
					JAXBElement<Organizacion> empresa = generico.createOrganizacion((this.cargarEmpresa(parte)));
					ConvocadoS convocado = new ConvocadoS();
					convocado.setSicidInvolucradoCaso((long) 0);
					convocado.setSictipoInvolucrado((long) 0);
					convocado.setSicparteOrganizacion(empresa);
					List<com.ccb.simasc.transversal.entidades.Persona> apoderados = manejadorPersona
							.getConsultarPartesCasoPorRol(datosCaso.getCaso().getIdCaso(),
									UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
					for (com.ccb.simasc.transversal.entidades.Persona persona : apoderados) {
						if (!persona.getSexo().equals(UtilDominios.SEXOS_INDEFINIDO)) {
							JAXBElement<Persona> apoderado = generico.createPersona(this.cargarPersona(persona));
							convocado.setSicapoderadoPersona(apoderado);
						}
					}
					convocados.getConvocadoS().add(convocado);
				} else {
					JAXBElement<Persona> partePersona = generico.createPersona((this.cargarPersona(parte)));
					ConvocadoS convocado = new ConvocadoS();
					convocado.setSicpartePersona(partePersona);
					List<com.ccb.simasc.transversal.entidades.Persona> apoderados = manejadorPersona
							.getConsultarPartesCasoPorRol(datosCaso.getCaso().getIdCaso(),
									UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
					for (com.ccb.simasc.transversal.entidades.Persona persona : apoderados) {
						if (!persona.getSexo().equals(UtilDominios.SEXOS_INDEFINIDO)) {
							JAXBElement<Persona> apoderado = generico.createPersona(this.cargarPersona(persona));
							convocado.setSicapoderadoPersona(apoderado);
						}
					}
					convocados.getConvocadoS().add(convocado);
				}
			}
		}
		HechosDeUnCasoS hechos = new HechosDeUnCasoS();
		hechos.setSiccuantia(datosCaso.getCaso().getValorCuantia(new BigDecimal(SMLMV)).longValue());
		hechos.setSiccuantiaIndeterminada(false);
		JAXBElement<String> hechosString = generico.createHechosDeUnCasoSSicHechos(
				datosCaso.getCaso().getHechos() != null ? datosCaso.getCaso().getHechos() : UtilConstantes.CAMPO_VACIO);
		JAXBElement<String> pretencionesString = generico
				.createHechosDeUnCasoSSicPretenciones(datosCaso.getCaso().getPretensiones() != null
						? datosCaso.getCaso().getPretensiones() : UtilConstantes.CAMPO_VACIO);
		hechos.setSicHechos(hechosString);
		hechos.setSicPretenciones(pretencionesString);
		hechos.setSicidUbicacionHechos((long) 11001);

		ArrayOfIdentificacionS arbitros = new ArrayOfIdentificacionS();
		ArrayOfArbitroExternoS arbitrosExternos = new ArrayOfArbitroExternoS();
		// Registro arbitros ministerio
		for (com.ccb.simasc.transversal.entidades.Persona arbitroMinisterio : datosCaso.getArbitrosMinisterio()) {

			JAXBElement<String> identificacionString = generico
					.createIdentificacionSSicNumeroDeDocumento(arbitroMinisterio.getNumeroDocumento());
			IdentificacionS arbitroCamara = new IdentificacionS();
			arbitroCamara.setSicNumeroDeDocumento(identificacionString);
			arbitroCamara.setSicTipoDeDocumento(tipoDocumentoPersonaMinisterio(arbitroMinisterio.getTipoDocumento()));
			arbitros.getIdentificacionS().add(arbitroCamara);
			cantidadArbitros++;
		}
		// Registro arbitros externos
		for (com.ccb.simasc.transversal.entidades.Persona arbitroExterno : datosCaso.getArbitrosExternos()) {

			ArbitroExternoS arbitroPersona = new ArbitroExternoS();
			JAXBElement<Persona> arbitroExternoPersona = generico
					.createArbitroExternoSSicpartePersona(this.cargarPersona(arbitroExterno));
			arbitroPersona.setSicpartePersona(arbitroExternoPersona);
			ArrayOfProfesionPersona arrayProfesionesPersona = new ArrayOfProfesionPersona();
			ProfesionPersona profesionPersona = new ProfesionPersona();
			JAXBElement<String> entidadTarjetaProefesional = generico
					.createProfesionPersonaSicEntidadTarjetaProfesional(
							UtilConstantes.PROFESION_PERSONA_ENTIDAD_TARJETA_PROFESIONAL);
			profesionPersona.setSicEntidadTarjetaProfesional(entidadTarjetaProefesional);
			profesionPersona.setSicFechaDeGrado(UtilOperaciones.covertirFechaXMLGregorianCalendar(new Date()));
			profesionPersona.setSicIdInstitucionProfesion(UtilConstantes.PROFESION_ID_INSTITUCION);
			profesionPersona.setSicIdProfesion((long) 2);
			JAXBElement<String> numeroTarjetaProfesional = generico
					.createProfesionPersonaSicNumeroTarjetaProfesional(UtilConstantes.CAMPO_VACIO);
			profesionPersona.setSicNumeroTarjetaProfesional(numeroTarjetaProfesional);

			arrayProfesionesPersona.getProfesionPersona().add(profesionPersona);
			JAXBElement<ArrayOfProfesionPersona> lstProfesionesPersona = generico
					.createArbitroExternoSSiclistaProfesionesPersonaConvocante(arrayProfesionesPersona);

			arbitroPersona.setSiclistaProfesionesPersonaConvocante(lstProfesionesPersona);
			arbitrosExternos.getArbitroExternoS().add(arbitroPersona);
			cantidadArbitros++;

		}
		int sicIdDesignacionArbitros = retornarDesignacion(datosCaso);
		ArrayOfSesionDeAudienciaArbitrajeS audiencias = new ArrayOfSesionDeAudienciaArbitrajeS();
		SesionDeAudienciaArbitrajeS audiencia = new SesionDeAudienciaArbitrajeS();
		audiencias.getSesionDeAudienciaArbitrajeS().add(audiencia);
		audiencias = null;

		ResultadoArbitraje resultado = new ResultadoArbitraje();
		resultado.setSicFechaResultadoArbitraje(
				UtilOperaciones.covertirFechaXMLGregorianCalendar(datosCaso.getFechaCierreCaso()));
		resultado.setSicIdTipoResultadoArbitraje(
				Long.valueOf(this.obtenerResultadoCasoArbitraje(datosCaso.getCaso().getMotivoCierre())));

		DocumentoFileStreamS documentoResultado = new DocumentoFileStreamS();
		File documento = null;
		for (Documento acta : datosCaso.getCaso().getDocumentoList()) {
			if (acta.getTipoDocumento().equals(UtilDominios.TIPO_DOCUMENTO_DIG_CIERRE_CASO)) {
				documento = new File(acta.getUrl());
			}
		}

		if (documento != null) {
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(documento);
				byte[] buffer = new byte[8 * 1024];
				int bytesLeidos = 0;
				int sum = 0;
				while ((bytesLeidos = inputStream.read(buffer)) != -1) {
					sum += bytesLeidos;
				}
				inputStream.close();
				JAXBElement<String> documentoDescripcion = generico
						.createDocumentoFileStreamSSicDescripcion(UtilConstantes.DOCUMENTO_NOMBBRE);
				documentoResultado.setSicDescripcion(documentoDescripcion);
				documentoResultado.setSicbufferLength(sum);
				JAXBElement<byte[]> fileStreamSSicDocumento = generico.createDocumentoFileStreamSSicDocumento(buffer);
				documentoResultado.setSicDocumento(fileStreamSSicDocumento);
				JAXBElement<String> nombreDocumento = generico
						.createDocumentoFileStreamSSicNombreDocumento(UtilConstantes.DOCUMENTO_NOMBBRE);
				documentoResultado.setSicNombreDocumento(nombreDocumento);
				JAXBElement<String> tipoDocumento = generico
						.createDocumentoFileStreamSSicTipoArchivoDocumento(UtilConstantes.DOCUMENTO_TIPO);
				documentoResultado.setSicTipoArchivoDocumento(tipoDocumento);
				documentoResultado.setSicIdTipodeDocumento(UtilConstantes.ID_DOCUMENTO_TIPO);
				inputStream.close();
			} catch (IOException e) {
				logger.error("Error: ", e);
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						logger.error("Error: ", e);
					}
				}
			}
		} else {
			String mensajeErrorDocumento = MensajesSimasc.getInstancia()
					.getMensajePorKey(MensajesEnum.ERROR233.toString());
			throw new SimascException(mensajeErrorDocumento);

		}
		DocumentoLaudo resultadoLaudo = new DocumentoLaudo();
		DocumentoFileStreamS documentoLaudo = new DocumentoFileStreamS();
		if (resultado.getSicIdTipoResultadoArbitraje() == 2 || resultado.getSicIdTipoResultadoArbitraje() == 3) {
			// datos del laudo
			resultadoLaudo = new DocumentoLaudo();
			resultadoLaudo
					.setSicFechaGastosAdministrativos(UtilOperaciones.covertirFechaXMLGregorianCalendar(new Date()));
			resultadoLaudo.setSicFechaHonorariosArbitros(UtilOperaciones.covertirFechaXMLGregorianCalendar(new Date()));
			resultadoLaudo.setSicFechaLaudo(UtilOperaciones.covertirFechaXMLGregorianCalendar(new Date()));
			resultadoLaudo.setSicLaudoConValor(false);
			documentoLaudo = documentoResultado;
		} else {
			resultadoLaudo = null;
			documentoLaudo = null;
		}
		liquidacion = new LiquidacionArbitraje();
		if (datosCaso.getHonorariosFijados() != null) {
			for (ValorHonorariosActor valorActor : datosCaso.getHonorariosFijados().getValorHonorariosActorList()) {

				if (valorActor.getValorHonorariosActorPK().getTipoActor()
						.equals(UtilDominios.TIPO_ACTOR_CASO_ARBITRO)) {
					liquidacion.setSicValorCorrespondienteArbitro(valorActor.getValor());
				}
				if (valorActor.getValorHonorariosActorPK().getTipoActor().equals(UtilDominios.TIPO_ACTOR_CASO_CAC)) {
					liquidacion.setSicValorCorrespondienteCentro(valorActor.getValor());
				}
				if (valorActor.getValorHonorariosActorPK().getTipoActor()
						.equals(UtilDominios.TIPO_ACTOR_CASO_SECRETARIO)) {
					liquidacion.setSicValorCorrespondienteSecretario(valorActor.getValor());
				}

			}
			liquidacion.setSicContribucionEspecialArbitro(BigDecimal.ZERO);
			liquidacion.setSicContribucionEspecialCentro(BigDecimal.ZERO);
			// liquidacion.setSicIdCaso((long) datosCaso.getCaso().getIdCaso());
			liquidacion.setSicNumeroArbitros(cantidadArbitros);
			liquidacion.setSicServicioCuantiaIndeterminada(false);
			liquidacion.setSicValorServicioTotalArbitraje(
					liquidacion.getSicValorCorrespondienteArbitro().add(liquidacion.getSicValorCorrespondienteCentro()
							.add(liquidacion.getSicValorCorrespondienteSecretario())));
		}
		mensaje = clientyeService.agregarCasosArbitraje(crearcaso, clasificacion, convocantes, convocados, hechos,
				Long.valueOf(sicIdDesignacionArbitros), arbitros, arbitrosExternos, secretario, liquidacion, audiencias,
				resultado, documentoResultado, resultadoLaudo, documentoLaudo, usuario, null, null);

		return mensaje.getSicAccion().getValue();
	}

	/**
	 * Agrega un caso para amigable composicion
	 * 
	 * @param DatosCasoMinisterioDTO
	 * @return String
	 */
	public String agregarCasoAmigableComposicionMinisterio(DatosCasoMinisterioDTO datosCaso) {

		LiquidacionAmigablecomposicionS liquidacion;
		CasoClasificacionAsunto clasificacion;

		Usuario usuario = new Usuario();
		Mensaje mensaje = new Mensaje();
		ObjectFactory generico = new ObjectFactory();
		Double SMLMV = manejadorParametrosGenerales.buscar(UtilConstantes.SMLMV).getValorNumerico().doubleValue();
		JAXBElement<String> moduloAmigableComposicion = generico
				.createUsuarioSicModulo(UtilConstantes.USUARIO_MODULO_AMIGABLE);
		usuario = this.obtenerDatosUsuario(moduloAmigableComposicion);
		Caso crearcaso = new Caso();

		JAXBElement<String> numeroRegistroCaso = generico
				.createCasoSicNumeroResgistro(datosCaso.getCaso().getIdCaso().toString());
		usuario.setSicIdOrigen(datosCaso.getCaso().getIdCaso());
		usuario.setSicModulo(moduloAmigableComposicion);
		crearcaso.setSicNumeroResgistro(numeroRegistroCaso);

		if (datosCaso.getCaso().getIdServicio().toString().equals(UtilConstantes.ID_SERVICIO_ARBITRAJE_SOCIAL)) {
			crearcaso.setSicCasoGratuito(true);
		} else {
			crearcaso.setSicCasoGratuito(false);
		}
		JAXBElement<String> descripcionPretenciones = generico
				.createCasoSicDescripcionPretensiones(UtilConstantes.CAMPO_VACIO);
		crearcaso.setSicDescripcionPretensiones(descripcionPretenciones);
		crearcaso.setSicFechaSolicitud(
				UtilOperaciones.covertirFechaXMLGregorianCalendar(datosCaso.getCaso().getFechaRadicacion()));
		// Validacion de asunto
		crearcaso.setSicIdAsunto(Long.valueOf(15));

		clasificacion = new CasoClasificacionAsunto();

		/** Informacion de las partes **/
		ArrayOfConvocanteS convocantes = new ArrayOfConvocanteS();
		ArrayOfConvocadoS convocados = new ArrayOfConvocadoS();
		for (com.ccb.simasc.transversal.entidades.Persona parte : datosCaso.getPartesCaso()) {
			RolPersonaCaso rolPersona = manejadorRolPersonaCaso.consultaRolPersonaId(parte.getIdPersona(),
					datosCaso.getCaso().getIdCaso(), UtilDominios.ESTADO_ROL_PERSONA_CASO_NO_APLICA);

			if (UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE.equals(rolPersona.getRol().getNombre())) {
				if (parte.getTipoPersona().equals(UtilDominios.TIPO_PERSONA_JURIDICO)) {
					JAXBElement<Organizacion> empresa = generico.createOrganizacion((this.cargarEmpresa(parte)));
					ConvocanteS convocante = new ConvocanteS();
					convocante.setSicparteOrganizacion(empresa);
					List<com.ccb.simasc.transversal.entidades.Persona> apoderados = manejadorPersona
							.getConsultarPartesCasoPorRol(datosCaso.getCaso().getIdCaso(),
									UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
					for (com.ccb.simasc.transversal.entidades.Persona persona : apoderados) {
						if (!persona.getSexo().equals(UtilDominios.SEXOS_INDEFINIDO)) {
							JAXBElement<Persona> apoderado = generico.createPersona(this.cargarPersona(persona));
							convocante.setSicapoderadoPersona(apoderado);
						}
					}
					convocantes.getConvocanteS().add(convocante);
				} else {
					JAXBElement<Persona> partePersona = generico.createPersona((this.cargarPersona(parte)));
					ConvocanteS convocante = new ConvocanteS();
					convocante.setSicpartePersona(partePersona);
					List<com.ccb.simasc.transversal.entidades.Persona> apoderados = manejadorPersona
							.getConsultarPartesCasoPorRol(datosCaso.getCaso().getIdCaso(),
									UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
					for (com.ccb.simasc.transversal.entidades.Persona persona : apoderados) {
						if (!persona.getSexo().equals(UtilDominios.SEXOS_INDEFINIDO)) {
							JAXBElement<Persona> apoderado = generico.createPersona(this.cargarPersona(persona));
							convocante.setSicapoderadoPersona(apoderado);

							JAXBElement<ArrayOfProfesionPersona> profesiones = generico
									.createArrayOfProfesionPersona(CargarProfesion(persona));
							convocante.setSiclistaProfesionesPersonaConvocante(profesiones);
						}
					}
					convocantes.getConvocanteS().add(convocante);
				}
			}
			if (UtilDominios.ROL_PERSONA_PARTE_DEMANDADA.equals(rolPersona.getRol().getNombre())) {
				if (parte.getTipoPersona().equals(UtilDominios.TIPO_PERSONA_JURIDICO)) {
					JAXBElement<Organizacion> empresa = generico.createOrganizacion((this.cargarEmpresa(parte)));
					ConvocadoS convocado = new ConvocadoS();
					convocado.setSicparteOrganizacion(empresa);
					List<com.ccb.simasc.transversal.entidades.Persona> apoderados = manejadorPersona
							.getConsultarPartesCasoPorRol(datosCaso.getCaso().getIdCaso(),
									UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
					for (com.ccb.simasc.transversal.entidades.Persona persona : apoderados) {
						if (!persona.getSexo().equals(UtilDominios.SEXOS_INDEFINIDO)) {
							JAXBElement<Persona> apoderado = generico.createPersona(this.cargarPersona(persona));
							convocado.setSicapoderadoPersona(apoderado);
						}
					}
					convocados.getConvocadoS().add(convocado);
				} else {
					JAXBElement<Persona> partePersona = generico.createPersona((this.cargarPersona(parte)));
					ConvocadoS convocado = new ConvocadoS();
					convocado.setSicpartePersona(partePersona);
					List<com.ccb.simasc.transversal.entidades.Persona> apoderados = manejadorPersona
							.getConsultarPartesCasoPorRol(datosCaso.getCaso().getIdCaso(),
									UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
					for (com.ccb.simasc.transversal.entidades.Persona persona : apoderados) {
						if (!persona.getSexo().equals(UtilDominios.SEXOS_INDEFINIDO)) {
							JAXBElement<Persona> apoderado = generico.createPersona(this.cargarPersona(persona));
							convocado.setSicapoderadoPersona(apoderado);
						}
					}
					convocados.getConvocadoS().add(convocado);
				}
			}
		}
		HechosDeUnCasoS hechos = new HechosDeUnCasoS();
		hechos.setSiccuantia(datosCaso.getCaso().getValorCuantia(new BigDecimal(SMLMV)).longValue());
		hechos.setSiccuantiaIndeterminada(false);
		JAXBElement<String> hechosString = generico.createHechosDeUnCasoSSicHechos(
				datosCaso.getCaso().getHechos() != null ? datosCaso.getCaso().getHechos() : UtilConstantes.CAMPO_VACIO);
		hechos.setSicHechos(hechosString);
		JAXBElement<String> pretencionesString = generico
				.createHechosDeUnCasoSSicPretenciones(UtilConstantes.CAMPO_VACIO);
		hechos.setSicPretenciones(pretencionesString);
		hechos.setSicidUbicacionHechos((long) 11001);

		/* Consulta los amigables del caso */
		ArrayOfIdentificacionS amigables = new ArrayOfIdentificacionS();
		// Registro arbitros ministerio
		for (com.ccb.simasc.transversal.entidades.Persona amigableComponedor : datosCaso.getAmigableComponedor()) {
			IdentificacionS amigable = new IdentificacionS();
			JAXBElement<String> identificacionString = generico
					.createIdentificacionSSicNumeroDeDocumento(amigableComponedor.getNumeroDocumento());
			amigable.setSicNumeroDeDocumento(identificacionString);
			amigable.setSicTipoDeDocumento(tipoDocumentoPersonaMinisterio(amigableComponedor.getTipoDocumento()));
			amigables.getIdentificacionS().add(amigable);
		}

		ResultadoAmigableComposicionS resultado = new ResultadoAmigableComposicionS();
		// Fecha de cierre
		resultado.setSicFechaResultadoAmigableComposicion(
				UtilOperaciones.covertirFechaXMLGregorianCalendar(datosCaso.getFechaCierreCaso()));
		resultado.setSicIdTipoResultadoAmigableComposicion(
				Long.valueOf(this.obtenerResultadoCasoAmigable(datosCaso.getCaso().getMotivoCierre())));

		DocumentoFileStreamS documentoResultado = new DocumentoFileStreamS();
		File documento = null;
		for (Documento acta : datosCaso.getCaso().getDocumentoList()) {
			if (acta.getTipoDocumento().equals(UtilDominios.TIPO_DOCUMENTO_DIG_ACTA)
					&& acta.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
				documento = new File(acta.getUrl());
			}
		}

		if (documento != null) {
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(documento);
				byte[] buffer = new byte[8 * 1024];
				int bytesLeidos = 0;
				int sum = 0;
				while ((bytesLeidos = inputStream.read(buffer)) != -1) {
					sum += bytesLeidos;
				}
				inputStream.close();
				JAXBElement<String> documentoDescripcion = generico
						.createDocumentoFileStreamSSicDescripcion(UtilConstantes.DOCUMENTO_NOMBBRE);
				documentoResultado.setSicDescripcion(documentoDescripcion);
				documentoResultado.setSicbufferLength(bytesLeidos);
				JAXBElement<byte[]> fileStreamSSicDocumento = generico.createDocumentoFileStreamSSicDocumento(buffer);
				documentoResultado.setSicDocumento(fileStreamSSicDocumento);
				JAXBElement<String> nombreDocumento = generico
						.createDocumentoFileStreamSSicNombreDocumento(UtilConstantes.DOCUMENTO_NOMBBRE);
				documentoResultado.setSicNombreDocumento(nombreDocumento);
				JAXBElement<String> tipoDocumento = generico
						.createDocumentoFileStreamSSicTipoArchivoDocumento(UtilConstantes.DOCUMENTO_TIPO);
				documentoResultado.setSicTipoArchivoDocumento(tipoDocumento);
				documentoResultado.setSicIdTipodeDocumento(UtilConstantes.ID_DOCUMENTO_TIPO);
				inputStream.close();
			} catch (IOException e) {
				logger.error("Error: ", e);
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						logger.error("Error: ", e);
					}
				}
			}
		} else {
			String mensajeErrorDocumento = MensajesSimasc.getInstancia()
					.getMensajePorKey(MensajesEnum.ERROR233.toString());
			throw new SimascException(mensajeErrorDocumento);
		}
		liquidacion = new LiquidacionAmigablecomposicionS();
		JAXBElement<DocumentoFileStreamS> JAXdocumentoResultado = generico
				.createResultadoAmigableComposicionSSicdocumentoResultado(documentoResultado);
		resultado.setSicdocumentoResultado(JAXdocumentoResultado);

		mensaje = clientyeService.agregarCasosAmigableComposicion(crearcaso, clasificacion, convocantes, convocados,
				hechos, amigables, liquidacion, resultado, usuario);

		return mensaje.getSicAccion().getValue();
	}

	/**
	 * CREACION OBJETOS DEL MINISTERIO
	 */

	/**
	 * Obtiene el usuario del ministerio
	 * 
	 * @return usuario
	 */
	@Override
	public Usuario obtenerDatosUsuario(JAXBElement<String> modulo) {
		Usuario usuario = new Usuario();

		ObjectFactory generico = new ObjectFactory();
		// JAXBElement<String> contrasena = generico.createUsuarioSicContrasena(UtilConstantes.USUARIO_CONTRASENA);
		JAXBElement<String> contrasena = generico
				.createUsuarioSicContrasena(obtenerDatosParametricosUsuario(UtilParamGenerales.CODIGO_PARAMETRO_CLAVE));
		usuario.setSicContrasena(contrasena);

		usuario.setSicIdAmbito((long) 0);
		usuario.setSicIdOrigen((long) 0);
		usuario.setSicrolAmbito(null);

		usuario.setSicModulo(modulo);

		JAXBElement<String> nombreRol = generico.createUsuarioSicNombreRol(UtilConstantes.USUARIO_NOMBRE_ROL);
		usuario.setSicNombreRol(nombreRol);

		// JAXBElement<String> nombreUsuario = generico.createUsuarioSicNombreUsuario(UtilConstantes.USUARIO_NOMBRE);
		JAXBElement<String> nombreUsuario = generico.createUsuarioSicNombreUsuario(
				obtenerDatosParametricosUsuario(UtilParamGenerales.CODIGO_PARAMETRO_USUARIO));
		usuario.setSicNombreUsuario(nombreUsuario);

		JAXBElement<String> nombreOrganizacion = generico
				.createUsuarioSicnombreOrganizacion(UtilConstantes.USUARIO_NOMBRE_ORGANIZACION);
		usuario.setSicnombreOrganizacion(nombreOrganizacion);

		JAXBElement<String> tipoAmbito = generico.createUsuarioSictipoAmbito(UtilConstantes.USUARIO_TIPO_AMBITO);
		usuario.setSictipoAmbito(tipoAmbito);

		return usuario;
	}
	
	/**
	 * 
	 * 
	 * @param usuario
	 * @return
	 */
	private String obtenerDatosParametricosUsuario(String codigoParametro) {
		String valorParametro = null;
		try {
			valorParametro = (String) manejadorParametrosGenerales.obtenerValorParametroPorCodigoTipo(codigoParametro,
					UtilParamGenerales.TIPO_PARAMETRO_USUARIO_MIN,
					ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_VALOR_TEXTO);
		} catch (Exception e) {
			List<String> parametrosMensaje = new ArrayList<String>();
			parametrosMensaje.add(codigoParametro);
			parametrosMensaje.add(UtilParamGenerales.TIPO_PARAMETRO_USUARIO_MIN);
			String mensajeError = String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR901.toString()),
					parametrosMensaje.toArray());
			throw new SimascException(mensajeError);
		}

		return valorParametro;
	}

	/**
	 * Convierte los datos de una persona SIMASC a los datos de una Organizacion
	 * Ministerio
	 * 
	 * @param persona
	 * @return
	 */
	private Organizacion cargarEmpresa(com.ccb.simasc.transversal.entidades.Persona persona) {
		Organizacion empresa = new Organizacion();
		ObjectFactory generico = new ObjectFactory();

		agregarTelefonosOrganizacion(persona, empresa);
		agregarCorreosOrganizacion(persona, empresa);
		agregarDireccionOrganizacion(persona, empresa);

		if (persona.getIdPaisOrigen() == null)
			persona.setIdPaisOrigen(UtilConstantes.CODIGO_NACIONALIDAD_COLOMBIA);
		empresa.setSicIdNacionalidad(Long.parseLong(this.consultarHomologicaionTextoControlado(persona.getIdPaisOrigen(),
						UtilDominios.DOMINIO_CODIGO_PAIS_MINISTERIO)));

		JAXBElement<String> identificacion = generico.createOrganizacionSicIdentificacion(persona.getNumeroDocumento());
		empresa.setSicIdentificacion(identificacion);
		empresa.setSicIdTipoDocumentoIdentidad(this.tipoDocumentoPersonaMinisterio(persona.getTipoDocumento()));

		JAXBElement<String> idSectorEconomico = generico.createOrganizacionSicIdSectorEconomico(
				persona.getSectorDeLaEmpresa() != null ? persona.getSectorDeLaEmpresa() : "0000");
		empresa.setSicIdSectorEconomico(idSectorEconomico);

		JAXBElement<String> nombre = generico.createOrganizacionSicNombre(persona.getPrimerNombreORazonSocial());
		empresa.setSicNombre(nombre);
		JAXBElement<String> paginaWeb = generico.createOrganizacionSicPaginaWeb(
				persona.getPaginaWeb() != null ? persona.getPaginaWeb() : UtilConstantes.CAMPO_VACIO);
		empresa.setSicPaginaWeb(paginaWeb);

		JAXBElement<String> tipoOrganizacion = generico.createOrganizacionSicTipoOrganizacion(
				persona.getTipoDeEmpresa() != null ? persona.getTipoDeEmpresa() : "PRIVADA");
		empresa.setSicTipoOrganizacion(tipoOrganizacion);
		if (UtilDominios.TIPO_EMPRESA_PUBLICA.equals(persona.getTipoDeEmpresa())) {
			if (persona.getTipoDeEntidadPublica() != null && !persona.getTipoDeEntidadPublica().isEmpty()) {
				empresa.setSicIdTipoEntidadPublica(this.consultarHomologicaionNumericaControlado(
						persona.getTipoDeEntidadPublica(), UtilDominios.DOMINIO_TIPO_ENTIDAD_PUBLICA));
			} else {
				throw new SIMASCNegocioExcepcion("No se encuentra el tipo de entidad publica"); 
			}
		} else {
			empresa.setSicIdTipoEntidadPublica(null);
		}
		return empresa;

	}

	/**
	 * Arma el metodo de nombramiento
	 * 
	 * @param datosMinisterio
	 * @return
	 */
	public int retornarDesignacion(DatosCasoMinisterioDTO datosMinisterio) {
		List<com.ccb.simasc.transversal.entidades.Persona> totalArbitros = new ArrayList<>();
		totalArbitros.addAll(datosMinisterio.getArbitrosExternos());
		totalArbitros.addAll(datosMinisterio.getArbitrosMinisterio());
		StringBuilder designacion = new StringBuilder();
		for (com.ccb.simasc.transversal.entidades.Persona arbitro : totalArbitros) {
			RolPersonaCaso rolPersonaValido = new RolPersonaCaso();
			for (RolPersonaCaso rolPersonaCaso : arbitro.getRolPersonaCasoList()) {
				if (rolPersonaCaso.getCaso().getIdCaso().equals(datosMinisterio.getCaso().getIdCaso())) {
					rolPersonaValido = rolPersonaCaso;
				}
			}
			switch (rolPersonaValido.getMetodoNombramiento()) {
			case UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES:
				designacion.append("A");
				break;
			case UtilDominios.NOMBRAMIENTO_POR_LA_CCB:
				designacion.append("B");
				break;
			case UtilDominios.NOMBRAMIENTO_POR_LA_AUTORIDAD_JUDICIAL:
				designacion.append("C");
				break;
			case UtilDominios.NOMBRAMIENTO_POR_UN_TERCERO:
				designacion.append("C");
				break;
			default:
				break;
			}
		}
		return validacionDesignacion(designacion.toString());
	}

	/**
	 * Valida los nombramientos y retorna el resultado
	 * 
	 * @param designacion
	 * @return
	 */
	public int validacionDesignacion(String designacion) {

		int resultado = 0;
		// DESIGNADO POR LAS PARTES
		if (designacion.contains("A")) {
			logger.info("DESIGNADO POR LAS PARTES");
			resultado = 1;
		}
		// DESIGNADO POR EL CENTRO
		if (designacion.contains("B")) {
			logger.info("DESIGNADO POR EL CENTRO");
			resultado = 2;
		}
		// DESIGNADO POR UN TERCERO
		if (designacion.contains("C")) {
			logger.info("DESIGNADO POR UN TERCERO");
			resultado = 3;
		}
		// DESIGNADO POR LAS PARTES Y POR EL CENTRO
		if (designacion.contains("A") && designacion.contains("B")) {
			logger.info("DESIGNADO POR LAS PARTES Y POR EL CENTRO");
			resultado = 4;
		}
		// DESIGANDO POR EL CENTRO Y UN TERCERO
		if (designacion.contains("B") && designacion.contains("C")) {
			logger.info("DESIGANDO POR EL CENTRO Y UN TERCERO");
			resultado = 5;
		}
		// DESIGNADO POR LAS PARTES Y UN TERCERO
		if (designacion.contains("A") && designacion.contains("C")) {
			logger.info("DESIGNADO POR LAS PARTES Y UN TERCERO");
			resultado = 6;
		}
		// DESIGNADO POR LAS PARTES, POR EL CENTRO Y POR UN TERCERO
		if (designacion.contains("A") && designacion.contains("B") && designacion.contains("C")) {
			logger.info("DESIGNADO POR LAS PARTES, POR EL CENTRO Y POR UN TERCERO");
			resultado = 7;
		}
		return resultado;
	}

	// metodos de formacion de
	// data------------------------------------------------------------------------------
	/**
	 * Convierte los datos de una persona SIMASC a los datos de una Persona
	 * Ministerio
	 * 
	 * @param persona
	 * @return
	 */
	private Persona cargarPersona(com.ccb.simasc.transversal.entidades.Persona persona) {
		ObjectFactory generico = new ObjectFactory();
		Persona parte = new Persona();
		JAXBElement<String> ciudadExpedicion = generico
				.createPersonaSicCiudadExpedicionIdentificacion(persona.getCiudadDelDocumento() != null
						? String.valueOf(persona.getCiudadDelDocumento()) : UtilConstantes.CAMPO_VACIO);
		parte.setSicCiudadExpedicionIdentificacion(ciudadExpedicion);

		JAXBElement<String> estrato = generico.createPersonaSicEstrato(persona.getEstrato() != null && !UtilConstantes.CODIGO_ESTRATO_CERO.equals(persona.getEstrato())
				? obtenerEstratosPersona(persona.getEstrato()) : "NO INFORMA");
		parte.setSicEstrato(estrato);

		parte.setSicFechaNacimiento(UtilOperaciones.covertirFechaXMLGregorianCalendar(
				persona.getFechaDeNacimiento() != null ? persona.getFechaDeNacimiento() : new Date()));
		if (persona.getIdPaisOrigen() == null)
			persona.setIdPaisOrigen(UtilConstantes.CODIGO_NACIONALIDAD_COLOMBIA);
		parte.setSicIdNacionalidad(this.consultarHomologicaionNumericaControlado(persona.getIdPaisOrigen(),
				UtilDominios.DOMINIO_CODIGO_PAIS_MINISTERIO));
		parte.setSicIdTipoDocumento(this.tipoDocumentoPersonaMinisterio(persona.getTipoDocumento()));

		JAXBElement<String> numeroIdentificacion = generico
				.createPersonaSicIdentificacion(persona.getNumeroDocumento());
		parte.setSicIdentificacion(numeroIdentificacion);
		JAXBElement<String> naturalezaPersona = generico
				.createPersonaSicNaturaleza(UtilConstantes.PERSONA_NATURALEZA_NATURAL);
		parte.setSicNaturaleza(naturalezaPersona);

		JAXBElement<String> paginaWeb = generico.createPersonaSicPaginaWeb(
				persona.getPaginaWeb() != null ? persona.getPaginaWeb() : UtilConstantes.CAMPO_VACIO);
		parte.setSicPaginaWeb(paginaWeb);

		JAXBElement<String> sexo;
		sexo = generico.createPersonaSicSexo(persona.getSexo().equals(UtilDominios.SEXOS_FEMENINO)
				? UtilConstantes.PERSONA_GENERO_FEMENINO : UtilConstantes.PERSONA_GENERO_MASCULINO);

		parte.setSicSexo(sexo);

		JAXBElement<String> primerNombre = generico
				.createPersonaSicPrimerNombre(persona.getPrimerNombreORazonSocial() != null
						? persona.getPrimerNombreORazonSocial() : UtilConstantes.CADENA_VACIA);
		JAXBElement<String> segundoNombre = generico.createPersonaSicSegundoNombre(
				persona.getSegundoNombre() != null ? persona.getSegundoNombre() : UtilConstantes.CADENA_VACIA);
		JAXBElement<String> primerApellido = generico.createPersonaSicPrimerApellido(
				persona.getPrimerApellido() != null ? persona.getPrimerApellido() : UtilConstantes.CADENA_VACIA);
		JAXBElement<String> segundoApellido = generico.createPersonaSicSegundoApellido(
				persona.getSegundoApellido() != null ? persona.getSegundoApellido() : UtilConstantes.CADENA_VACIA);
		parte.setSicPrimerNombre(primerNombre);
		parte.setSicSegundoNombre(segundoNombre);
		parte.setSicPrimerApellido(primerApellido);
		parte.setSicSegundoApellido(segundoApellido);

		if (persona.getEscolaridad() != null) {
			parte.setSicIdGradoEscolaridad(obtenerEscolaridadPersona(persona.getEscolaridad()));
		} else {
			parte.setSicIdGradoEscolaridad((long) 13);
		}

		agregarTelefonosPersona(persona, parte);

		agregarDireccionPersona(persona, parte);

		agregarCorreosPersona(persona, parte);

		return parte;
	}

	private ArrayOfProfesionPersona CargarProfesion(com.ccb.simasc.transversal.entidades.Persona persona) {
		ObjectFactory generico = new ObjectFactory();
		ArrayOfProfesionPersona profesiones = new ArrayOfProfesionPersona();
		;
		ProfesionPersona profesion;

		if (persona.getProfesion() != null) {

			profesiones = new ArrayOfProfesionPersona();
			profesion = new ProfesionPersona();
			JAXBElement<String> entidadTarjetaProfesional = generico.createProfesionPersonaSicEntidadTarjetaProfesional(
					persona.getEntidadExpideTarjetaProfesional() != null ? persona.getEntidadExpideTarjetaProfesional()
							: UtilConstantes.CADENA_VACIA);
			profesion.setSicEntidadTarjetaProfesional(entidadTarjetaProfesional);
			if (persona.getFechaDeGrado() != null) {
				profesion.setSicFechaDeGrado(
						UtilOperaciones.covertirFechaXMLGregorianCalendar(persona.getFechaDeGrado()));
			}
			profesion.setSicIdInstitucionProfesion(
					persona.getInstitucionEducativa() != null ? Long.valueOf(persona.getInstitucionEducativa()) : 300L);
			profesion.setSicIdProfesion(
					persona.getProfesion().getIdProfesion() != null ? persona.getProfesion().getIdProfesion() : 6464L);
			JAXBElement<String> numeroTarjetaProfesional = generico
					.createProfesionPersonaSicNumeroTarjetaProfesional(persona.getNumeroTarjetaProfesional() != null
							? persona.getNumeroTarjetaProfesional() : UtilConstantes.CAMPO_VACIO);
			profesion.setSicNumeroTarjetaProfesional(numeroTarjetaProfesional);
			profesiones.getProfesionPersona().add(profesion);
		}
		return profesiones;
	}

	/**
	 * creacion de caso de ministerio
	 * 
	 * @param datosCaso
	 * @return
	 */
	private Caso crearCaso(DatosCasoMinisterioDTO datosCaso) {
		ObjectFactory generico = new ObjectFactory();
		Caso crearcaso = new Caso();
		crearcaso.setSicCasoActivo(false);
		crearcaso.setSicCasoGratuito(esCasoGratuito(datosCaso.getCaso().getIdServicio()));
		crearcaso.setSicFechaRegistro(UtilOperaciones.covertirFechaXMLGregorianCalendar(new Date()));
		crearcaso.setSicFechaSolicitud(
				UtilOperaciones.covertirFechaXMLGregorianCalendar(datosCaso.getCaso().getFechaRadicacion()));
		if (datosCaso.getCaso().getAreaAsuntoClasificacion() == null
				|| datosCaso.getCaso().getAreaAsuntoClasificacion().getIdAsunto() == null) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR332.toString()));
		}
		crearcaso.setSicIdAsunto(datosCaso.getCaso().getAreaAsuntoClasificacion().getIdAsunto());
		JAXBElement<String> codCasoString = generico
				.createCasoSicNumeroResgistro(datosCaso.getCaso().getIdCaso().toString());
		crearcaso.setSicIdSolicitanteServicio(
				this.obtenerSolicitanteServicio(datosCaso.getCaso().getParteQueSolicitaServicio()));
		crearcaso.setSicIdTiempoConflicto(datosCaso.getCaso().getInicioDeConflicto() != null
				? Long.valueOf((datosCaso.getCaso().getInicioDeConflicto())) : 0L);
		crearcaso.setSicIdTipoFinalidadMinisterio(UtilConstantes.TIPO_FINALIDAD_MINISTERIO_CONCILIACION);
		crearcaso.setSicIdUbicacionHechos(datosCaso.getCaso().getIdLugarDelConflicto() != null
				? Long.valueOf(datosCaso.getCaso().getIdLugarDelConflicto()) : UtilConstantes.CODIGO_CIUDAD_BOGOTA);
		crearcaso.setSicNumeroResgistro(codCasoString);

		return crearcaso;
	}

	/**
	 * Crea los hechos de un caso de ministerio
	 * 
	 * @param datosCaso
	 * @return
	 */
	private HechosDeUnCasoS creacionHechos(DatosCasoMinisterioDTO datosCaso) {
		ObjectFactory generico = new ObjectFactory();
		HechosDeUnCasoS hechos = new HechosDeUnCasoS();
		JAXBElement<String> hechosString = generico.createHechosDeUnCasoSSicHechos(
				datosCaso.getCaso().getHechos() != null ? datosCaso.getCaso().getHechos()
						: MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO290.toString()));
		hechos.setSicHechos(hechosString);
		JAXBElement<String> pretencionesString = generico.createHechosDeUnCasoSSicPretenciones(
				datosCaso.getCaso().getPretensiones() != null ? datosCaso.getCaso().getPretensiones()
						: MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO291.toString()));
		hechos.setSicPretenciones(pretencionesString);
		hechos.setSiccuantia(this.obtenerValorPretensiones(datosCaso.getCaso().getValorPretensiones()));
		hechos.setSiccuantiaIndeterminada(this.esCuantiaIndeterminada(datosCaso.getCaso().getTipoCuantia()));
		hechos.setSicidUbicacionHechos(datosCaso.getCaso().getIdLugarDelConflicto() != null
				? Long.valueOf(datosCaso.getCaso().getIdLugarDelConflicto()) : UtilConstantes.CODIGO_CIUDAD_BOGOTA);

		return hechos;
	}

	/**
	 * crea el manejo de conflicto de un caso de ministerio
	 * 
	 * @return
	 */
	private ManejoConflictoS creacionManejoConflicto() {
		ManejoConflictoS manejoConflicto = new ManejoConflictoS();
		manejoConflicto.setSicEscaladaDeConflicto(UtilConstantes.ESCALADA_CONFLICTO_SIN_VIOLENCIA);
		return manejoConflicto;
	}

	private Usuario crearUsuario(JAXBElement<String> modulo, DatosCasoMinisterioDTO datosCaso) {
		ObjectFactory generico = new ObjectFactory();
		Usuario usuario = new Usuario();
		this.agregarParametrosGenerales(datosCaso, usuario);

		JAXBElement<String> nombreRol = generico.createUsuarioSicNombreRol(UtilConstantes.USUARIO_NOMBRE_ROL);
		usuario.setSicNombreRol(nombreRol);
		JAXBElement<String> tipoAmbito = generico.createUsuarioSictipoAmbito(UtilConstantes.USUARIO_TIPO_AMBITO);
		usuario.setSictipoAmbito(tipoAmbito);
		usuario.setSicModulo(modulo);
		Long idCentro;
		if( UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA == datosCaso.getCaso().getIdServicio()
			&& datosCaso.getCaso().getConvenio() != null ){
			idCentro = datosCaso.getCaso().getConvenio().getIdCentro();
		} else{
			idCentro = datosCaso.getCaso().getSede().getIdCentro();
		}
		JAXBElement<String> nombreOrganizacion = generico.createUsuarioSicnombreOrganizacion(
				this.nombreOrganizacionPorCentro(idCentro));
		usuario.setSicnombreOrganizacion(nombreOrganizacion);
		return usuario;
	}

	private ResultadoDeUnCasoS obtenerResultadosConciliacionSinAudiencia(DatosCasoMinisterioDTO datosCaso) {
		ResultadoDeUnCasoS resultado = null;
		resultado = new ResultadoDeUnCasoS();
		resultado.setSicFechaRegistroResultldo(UtilOperaciones.covertirFechaXMLGregorianCalendar(new Date()));
		Long resultadoLong = this.consultarHomologicaionNumericaControlado(datosCaso.getCaso().getResultado(),
				UtilDominios.DOMINIO_RESULTADO_CASO_CONCILIACION);
		resultado.setSicTipoResultadoConciliacion(resultadoLong.intValue());
		return resultado;
	}

	private ArrayOfResultadoDeUnCasoS crearResultadoCasoConciliacion(DatosCasoMinisterioDTO datosCaso) {
		ArrayOfResultadoDeUnCasoS resultadosConciliacion = new ArrayOfResultadoDeUnCasoS();
		List<Audiencia> audiencias = datosCaso.getCaso().getAudienciaList();
		if (this.validacionSinDocumeto(datosCaso, audiencias)) {
			ResultadoDeUnCasoS resultado = this.obtenerResultadosConciliacionSinAudiencia(datosCaso);
			resultadosConciliacion.getResultadoDeUnCasoS().add(resultado);

		} else {
			List<String> tiposResultados = Arrays.asList(UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_PARCIAL,
					UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_TOTAL,
					UtilDominios.RESULTADO_AUDIENCIA_IMPOSIBILIDAD_DE_ACUERDO,
					UtilDominios.RESULTADO_AUDIENCIA_INASISTENCIA);
			
			List<ResultadoMinisterioDTO> resultados = manejadorResultadoAudiencia.consultarResultadosPorMinisterio(
					datosCaso.getCaso().getIdServicio(), datosCaso.getCaso().getIdCaso(), tiposResultados);
			
			for (ResultadoMinisterioDTO resultado : resultados) {
				Documento documento = manejadorDocumento.buscar(resultado.getIdDocumento());
				if( documento == null || !UtilDominios.ESTADO_REGISTRO_ACTIVO.equals( documento.getEstadoRegistro() ) )
					throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR306.toString()));
				DocumentoFileStreamS documentoResultado = this.creacionDocumento(documento);
				if (documentoResultado != null) {
					ResultadoDeUnCasoS resultadoCasoS = new ResultadoDeUnCasoS();
					JAXBElement<DocumentoFileStreamS> JAXdocumentoResultado = generic
							.createResultadoDeUnCasoSDocumentoResultado(documentoResultado);
					resultadoCasoS.setDocumentoResultado(JAXdocumentoResultado);
					resultadoCasoS.setSicFechaRegistroResultldo(
							UtilOperaciones.covertirFechaXMLGregorianCalendar(resultado.getHoraInicio()));
					Long resultadoLong = this.consultarHomologicaionNumericaControlado(resultado.getTipoResultado(),
							UtilDominios.DOMINIO_RESULTADOS_AUDIENCIA);
					resultadoCasoS.setSicTipoResultadoConciliacion(resultadoLong.intValue());
					resultadosConciliacion.getResultadoDeUnCasoS().add(resultadoCasoS);

				}
			}
			
		}

		if (resultadosConciliacion.getResultadoDeUnCasoS().isEmpty()) {
			throw new SIMASCNegocioExcepcion(
					String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR603.toString())));
		}

		return resultadosConciliacion;
	}

	/**
	 * Valida si es caso que se va a registrar necesita o no documento
	 * 
	 * @param datosCaso
	 * @param audiencias
	 * @return
	 */
	private boolean validacionSinDocumeto(DatosCasoMinisterioDTO datosCaso, List<Audiencia> audiencias) {
		return datosCaso.getCaso().getResultado() != null && (datosCaso.getCaso().getResultado()
				.equals(UtilDominios.RESULTADO_CASO_CONCILIACION_CANCELACION)
				|| datosCaso.getCaso().getResultado().equals(UtilDominios.RESULTADO_CASO_CONCILIACION_ARREGLO_DIRECTO)
				|| (datosCaso.getCaso().getResultado()
						.equals(UtilDominios.RESULTADO_CASO_CONCILIACION_FALTA_COMPETENCIA)
						&& !validarAudienciaRealizada(datosCaso, audiencias)));

	}

	/**
	 * Valida si ya existe una audiencia realizada activa.
	 * 
	 * @param datosCaso
	 * @param audiencias
	 * @return
	 */
	private boolean validarAudienciaRealizada(DatosCasoMinisterioDTO datosCaso, List<Audiencia> audiencias) {
		boolean audienciaRealizada = false;
		if (audiencias != null) {
			for (Audiencia audienciaFor : audiencias) {
				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(audienciaFor.getEstadoRegistro())
						&& UtilDominios.ESTADO_AUDIENCIA_REALIZADA.equals(audienciaFor.getEstado())) {
					audienciaRealizada = true;
					break;
				}

			}
		}

		return audienciaRealizada;

	}

	private LiquidacionC crearLiquidacionConciliacion(DatosCasoMinisterioDTO datosCaso) {
		LiquidacionC liquidacion = new LiquidacionC();
		if (datosCaso.getLiquidacion() != null) {
			liquidacion.setSicNumeroEncuentroAdicional(datosCaso.getLiquidacion().getNumeroEncuentroAdicional());

			liquidacion.setSicServicioCuantiaIndeterminada(
					this.esCuantiaIndeterminada(datosCaso.getCaso().getTipoCuantia()));
			liquidacion.setSicValorCorrespondienteCentro(
					new BigDecimal(datosCaso.getLiquidacion().getValorCorrespondienteCentro()));
			liquidacion.setSicValorCorrespondienteConciliador(
					new BigDecimal(datosCaso.getLiquidacion().getValorCorrespondienteConciliador()));
			liquidacion.setSicValorEncuentrosAdicionales(
					new BigDecimal(datosCaso.getLiquidacion().getValorEncuentrosAdicionales()));
			liquidacion.setSicValorServicioTotalConciliacion(
					new BigDecimal(datosCaso.getLiquidacion().getValorServicioTotalConciliacion()));
		}

		return liquidacion;
	}

	/**
	 * crear el caso clasificacion del ministerio de justicia
	 * 
	 * @param datosCaso
	 * @return
	 */
	private CasoClasificacionAsunto crearCasoClasificacionAsunto(DatosCasoMinisterioDTO datosCaso) {
		CasoClasificacionAsunto clasificacion = new CasoClasificacionAsunto();
		if (datosCaso.getCaso() != null && datosCaso.getCaso().getAreaAsuntoClasificacion() != null
				&& datosCaso.getCaso().getAreaAsuntoClasificacion().getIdClasificacion() != null) {
			clasificacion
					.setSicIdClasificacionAsunto(datosCaso.getCaso().getAreaAsuntoClasificacion().getIdClasificacion());
		}
		return clasificacion;
	}

	private ArrayOfConvocanteS crearConvocantes(DatosCasoMinisterioDTO datosCaso) {
		ObjectFactory generico = new ObjectFactory();
		ArrayOfConvocanteS convocantes = new ArrayOfConvocanteS();
		for (RolPersonaCaso convocanteFor : datosCaso.getConvocantes()) {
			ConvocanteS convocante = new ConvocanteS();
			if (convocanteFor.getPersona().getProfesion() != null) {
				JAXBElement<ArrayOfProfesionPersona> profesiones = generico
						.createConvocanteSSiclistaProfesionesPersonaConvocante(
								this.CargarProfesion(convocanteFor.getPersona()));
				convocante.setSiclistaProfesionesPersonaConvocante(profesiones);
			}
			if (convocanteFor.getPersona().getTipoPersona().equals(UtilDominios.TIPO_PERSONA_JURIDICO)) {
				Organizacion organizacion = this.cargarEmpresa(convocanteFor.getPersona());
				convocante.setSicparteOrganizacion(generico.createConvocanteSSicparteOrganizacion(organizacion));
			} else {
				Persona persona = this.cargarPersona(convocanteFor.getPersona());
				convocante.setSicpartePersona(generico.createConvocanteSSicpartePersona(persona));
			}

			if (convocanteFor.getRolPersonaCaso() != null && convocanteFor.getRolPersonaCaso().getPersona() != null) {
				this.agregarApoderadoConvocante(convocanteFor.getRolPersonaCaso().getPersona(), convocante);
			}
			convocantes.getConvocanteS().add(convocante);
		}

		return convocantes;
	}

	private ArrayOfConvocadoS crearConvocados(DatosCasoMinisterioDTO datosCaso) {
		ArrayOfConvocadoS convocados = new ArrayOfConvocadoS();

		ObjectFactory generico = new ObjectFactory();
		for (RolPersonaCaso convocadoFor : datosCaso.getConvocados()) {
			ConvocadoS convocado = new ConvocadoS();
			if (convocadoFor.getPersona().getProfesion() != null) {
				JAXBElement<ArrayOfProfesionPersona> profesiones = generico
						.createConvocadoSSiclistaProfesionesPersonaConvocado(
								this.CargarProfesion(convocadoFor.getPersona()));
				convocado.setSiclistaProfesionesPersonaConvocado(profesiones);
			}
			if (convocadoFor.getPersona().getTipoPersona().equals(UtilDominios.TIPO_PERSONA_JURIDICO)) {
				Organizacion organizacion = this.cargarEmpresa(convocadoFor.getPersona());
				convocado.setSicparteOrganizacion(generico.createConvocadoSSicparteOrganizacion(organizacion));
			} else {
				Persona persona = this.cargarPersona(convocadoFor.getPersona());
				convocado.setSicpartePersona(generico.createConvocadoSSicpartePersona(persona));
			}

			if (convocadoFor.getRolPersonaCaso() != null && convocadoFor.getRolPersonaCaso().getPersona() != null) {
				this.agregarApoderadoConvocado(convocadoFor.getRolPersonaCaso().getPersona(), convocado);
			}
			convocados.getConvocadoS().add(convocado);
		}

		return convocados;
	}

	/**
	 * creacion del conciliador del ministerio
	 * 
	 * @param datosCaso
	 * @return
	 */
	private IdentificacionS crearConciliador(DatosCasoMinisterioDTO datosCaso) {
		ObjectFactory generico = new ObjectFactory();
		IdentificacionS conciliador = new IdentificacionS();
		conciliador.setSicTipoDeDocumento(
				this.tipoDocumentoPersonaMinisterio(datosCaso.getConciliador().getTipoDocumento()));
		JAXBElement<String> numeroIdentificacion = generico
				.createIdentificacionSSicNumeroDeDocumento(datosCaso.getConciliador().getNumeroDocumento());
		conciliador.setSicNumeroDeDocumento(numeroIdentificacion);
		return conciliador;
	}

	private ArrayOfSesionDeAudienciaS crearAudiencias(DatosCasoMinisterioDTO datosCaso) {
		ObjectFactory generico = new ObjectFactory();
		ArrayOfSesionDeAudienciaS audiencias = new ArrayOfSesionDeAudienciaS();
		if (datosCaso.getCaso().getAudienciaList() != null && !datosCaso.getCaso().getAudienciaList().isEmpty()) {
			for (Audiencia audienciaFor : datosCaso.getCaso().getAudienciaList()) {
				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(audienciaFor.getEstadoRegistro())
						&& audienciaFor.getResultado() != null) {
					SesionDeAudienciaS audiencia = new SesionDeAudienciaS();
					audiencia.setSicFechaSesion(
							UtilOperaciones.covertirFechaXMLGregorianCalendar(audienciaFor.getHoraInicio()));
					JAXBElement<String> observaciones = generico
							.createSesionDeAudienciaSSicDetallesSesion(audienciaFor.getObservaciones() == null
									? UtilConstantes.CADENA_VACIA : audienciaFor.getObservaciones());
					audiencia.setSicDetallesSesion(observaciones);

					audiencias.getSesionDeAudienciaS().add(audiencia);
				}

			}
		}

		return audiencias;
	}

	private DocumentoFileStreamS creacionDocumento(Documento documento) {
		DocumentoFileStreamS documentoResultado = new DocumentoFileStreamS();
		File documentoFile = null;
		documentoFile = new File(documento.getUrl());
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(documentoFile);
			Path path = Paths.get(documento.getUrl());
			byte[] data = Files.readAllBytes(path);

			JAXBElement<String> nombreDocumento = generic
					.createDocumentoFileStreamSSicNombreDocumento(UtilConstantes.DOCUMENTO_NOMBBRE);
			documentoResultado.setSicNombreDocumento(nombreDocumento);

			JAXBElement<String> tipoDocumento = generic
					.createDocumentoFileStreamSSicTipoArchivoDocumento(UtilConstantes.DOCUMENTO_TIPO);
			documentoResultado.setSicTipoArchivoDocumento(tipoDocumento);

			JAXBElement<byte[]> fileStreamSSicDocumento = generic.createDocumentoFileStreamSSicDocumento(data);
			documentoResultado.setSicDocumento(fileStreamSSicDocumento);

			JAXBElement<String> documentoDescripcion = generic.createDocumentoFileStreamSSicDescripcion(
					documento.getDescripcion() != null ? documento.getDescripcion() : UtilConstantes.CADENA_VACIA);
			documentoResultado.setSicDescripcion(documentoDescripcion);
			documentoResultado.setSicIdTipodeDocumento(UtilConstantes.ID_DOCUMENTO_TIPO);
			documentoResultado.setSicbufferLength(data != null ? data.length : 0);
			inputStream.close();
		} catch (Exception e) {
			documentoResultado = null;
			logger.info(e.getMessage());

		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {					
					logger.error("Error: ", e);
				}
			}
		}
		return documentoResultado;
	}

	private void agregarApoderadoConvocante(com.ccb.simasc.transversal.entidades.Persona persona,
			ConvocanteS convocante) {
		ObjectFactory generico = new ObjectFactory();
		if (persona.getTipoPersona().equals(UtilDominios.TIPO_PERSONA_NATURAL)) {
			Persona apoderado = this.cargarPersona(persona);
			convocante.setSicapoderadoPersona(generico.createPersona(apoderado));
		}

	}

	private void agregarApoderadoConvocado(com.ccb.simasc.transversal.entidades.Persona persona, ConvocadoS convocado) {
		ObjectFactory generico = new ObjectFactory();
		if (persona.getTipoPersona().equals(UtilDominios.TIPO_PERSONA_NATURAL)) {
			Persona apoderado = this.cargarPersona(persona);
			convocado.setSicapoderadoPersona(generico.createPersona(apoderado));
		}

	}

	/**
	 * OPERACIONES Y HOMOLOGACIONES
	 * 
	 */

	private Long tipoDocumentoPersonaMinisterio(String tipoDocumento) {
		return this.consultarHomologicaionNumericaControlado(tipoDocumento,
				UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA);

	}

	/**
	 * Homologa codigos de escolaridad de SIMASC a codigos de Ministerio
	 * 
	 * @param escolaridad
	 * @return
	 */
	private Long obtenerEscolaridadPersona(String escolaridad) {
		return this.consultarHomologicaionNumericaControlado(escolaridad, "ESCOLARIDAD");
	}

	/**
	 * Homologa codigos de estrato de SIMASC a codigos de Ministerio
	 * 
	 * @param estrato
	 * @return
	 */
	private String obtenerEstratosPersona(String estrato) {
		String resultado = this.consultarHomologicaionTextoControlado(estrato, "ESTRATO");
		return resultado;
	}

	/**
	 * Motivo cierre para arbitraje
	 * 
	 * @param estrato
	 * @return
	 */
	private String obtenerResultadoCasoArbitraje(String motivoCierre) {
		String resultado = UtilConstantes.CAMPO_VACIO;
		resultado = manejadorHomologacionSistemaExterno.consultarCodigosSistemaExterno(
				UtilConstantes.SISTEMA_EXTERNO_MINISTERIO, motivoCierre, "MOTIVO_CIERRE");
		return resultado;
	}

	/**
	 * Homologa codigos de solicitante servicio de SIMASC a codigos de
	 * Ministerio
	 * 
	 * @param escolaridad
	 * @return
	 */
	private Long obtenerSolicitanteServicio(String solicitanteServicio) {
		long resultado = 0;
		if (solicitanteServicio != null) {
			resultado = this.consultarHomologicaionNumericaControlado(solicitanteServicio,
					UtilDominios.DOMINIO_PARTE_SOLICITA_SERVICIO);
		}

		return resultado;
	}

	/**
	 * Motivo cierre para amigable
	 * 
	 * @param motivoCierre
	 * @return
	 */
	private String obtenerResultadoCasoAmigable(String motivoCierre) {
		// String resultado = UtilConstantes.CAMPO_VACIO;
		String resultado = motivoCierre;
		// resultado =
		// manejadorHomologacionSistemaExterno.consultarCodigosSistemaExterno("MINS",
		// motivoCierre,
		// "MOTIVO_CIERRE_AMIGABLE");
		return resultado;
	}

	/**
	 * obtiene el valor de las pretensiones de un caso
	 * 
	 * @param valorPretensiones
	 * @return
	 */
	private Long obtenerValorPretensiones(String valorPretensiones) {
		long resultado;
		try {
			resultado = Long.valueOf(valorPretensiones);
		} catch (Exception e) {
			// Informacion pendiente de camara, en caso de que no sea numerico
			resultado = 0L;
		}
		return resultado;
	}

	/**
	 * Homologa codigos de solicitante servicio de SIMASC a codigos de
	 * Ministerio
	 * 
	 * @param escolaridad
	 * @return
	 */
	private Long consultarHomologicaionNumericaControlado(String codigoSimasc, String DominioSimasc) {
		long resultado = 0;
		String resultadoTexto = this.consultarHomologicaionTextoControlado(codigoSimasc, DominioSimasc);
		try {
			resultado = Long.valueOf(resultadoTexto);
		} catch (Exception e) {
			List<String> args = new ArrayList<>();
			args.add(codigoSimasc);
			args.add(DominioSimasc);
			throw new SIMASCNegocioExcepcion(String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR333.toString()), args.toArray()));
		}

		return resultado;
	}

	/**
	 * Homologa codigos de solicitante servicio de SIMASC a codigos de
	 * Ministerio
	 * 
	 * @param escolaridad
	 * @return
	 */
	private String consultarHomologicaionTextoControlado(String codigoSimasc, String DominioSimasc) {
		String resultado = UtilConstantes.CADENA_VACIA;
		try {
			resultado = manejadorHomologacionSistemaExterno.consultarCodigosSistemaExterno(
					UtilConstantes.SISTEMA_EXTERNO_MINISTERIO, codigoSimasc, DominioSimasc);
		} catch (Exception e) {
			List<String> args = new ArrayList<>();
			args.add(codigoSimasc);
			args.add(DominioSimasc);
			throw new SIMASCNegocioExcepcion(String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR334.toString()), args.toArray()));
		}
		return resultado;
	}

	/**
	 * valida si le caso es gratuito
	 * 
	 * @param idServicio
	 * @return
	 */
	private boolean esCasoGratuito(Long idServicio) {
		return (UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA.equals(idServicio)
				|| UtilConstantes.ID_SERVICIO_ARBITRAJ_SOCIAL.equals(idServicio));
	}

	/**
	 * valida si la cuantia es indeterminado en un caso
	 * 
	 * @param cuantia
	 * @return
	 */
	private boolean esCuantiaIndeterminada(String cuantia) {
		return UtilDominios.TIPO_CUANTIA_INDETERMINADO.equals(cuantia)
				|| UtilDominios.TIPO_CUANTIA_CONCILIACION_INDETERMINADO.equals(cuantia);
	}

	private String nombreOrganizacionPorCentro(Long centro) {
		String nombreOrg = UtilConstantes.USUARIO_NOMBRE_ORGANIZACION;
		if (((Long) 1L).equals(centro)) {
			nombreOrg = UtilConstantes.USUARIO_NOMBRE_ORGANIZACION;
		}
		return nombreOrg;
	}

	private void agregarTelefonosPersona(com.ccb.simasc.transversal.entidades.Persona personaFrom, Persona personaTo) {

		List<com.ccb.simasc.transversal.entidades.Telefono> Telefonos = personaFrom.getTelefonoList();
		JAXBElement<String> Telefono;
		ObjectFactory generico = new ObjectFactory();

		for (int i = 0; i < Telefonos.size(); i++) {
			if (Telefonos.get(i).getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)
					&& Telefonos.get(i).getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO)
					&& personaTo.getSicTelefono() == null) {
				Telefono = generico.createPersonaSicTelefono(Telefonos.get(i).getNumero().toString());
				personaTo.setSicTelefono(Telefono);
			}
			if (Telefonos.get(i).getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)
					&& Telefonos.get(i).getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_CELULAR)
					&& personaTo.getSicCelular() == null) {
				Telefono = generico.createPersonaSicCelular(Telefonos.get(i).getNumero().toString());
				personaTo.setSicCelular(Telefono);
			}
			if (Telefonos.get(i).getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)
					&& Telefonos.get(i).getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FAX)
					&& personaTo.getSicFax() == null) {
				Telefono = generico.createPersonaSicFax(Telefonos.get(i).getNumero().toString());
				personaTo.setSicFax(Telefono);
			}

		}
		if (personaTo.getSicCelular() == null) {
			Telefono = generico.createPersonaSicCelular(UtilConstantes.CADENA_VACIA);
			personaTo.setSicCelular(Telefono);
		}

		if (personaTo.getSicTelefono() == null) {
			Telefono = generico.createPersonaSicTelefono(UtilConstantes.CADENA_VACIA);
			personaTo.setSicTelefono(Telefono);
		}

		if (personaTo.getSicFax() == null) {
			Telefono = generico.createPersonaSicFax(UtilConstantes.CADENA_VACIA);
			personaTo.setSicFax(Telefono);
		}
	}

	private void agregarTelefonosOrganizacion(com.ccb.simasc.transversal.entidades.Persona personaFrom,
			Organizacion personaTo) {

		List<com.ccb.simasc.transversal.entidades.Telefono> Telefonos = personaFrom.getTelefonoList();
		JAXBElement<String> Telefono;
		ObjectFactory generico = new ObjectFactory();

		for (int i = 0; i < Telefonos.size(); i++) {
			if (Telefonos.get(i).getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)
					&& Telefonos.get(i).getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO)
					&& personaTo.getSicTelefono() == null) {
				Telefono = generico.createPersonaSicTelefono(Telefonos.get(i).getNumero().toString());
				personaTo.setSicTelefono(Telefono);
			}
			if (Telefonos.get(i).getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)
					&& Telefonos.get(i).getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_CELULAR)
					&& personaTo.getSicCelular() == null) {
				Telefono = generico.createPersonaSicCelular(Telefonos.get(i).getNumero().toString());
				personaTo.setSicCelular(Telefono);
			}
			if (Telefonos.get(i).getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)
					&& Telefonos.get(i).getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FAX)
					&& personaTo.getSicFax() == null) {
				Telefono = generico.createPersonaSicFax(Telefonos.get(i).getNumero().toString());
				personaTo.setSicFax(Telefono);
			}

		}
		if (personaTo.getSicCelular() == null) {
			Telefono = generico.createPersonaSicCelular(UtilConstantes.CADENA_VACIA);
			personaTo.setSicCelular(Telefono);
		}

		if (personaTo.getSicTelefono() == null) {
			Telefono = generico.createPersonaSicTelefono(UtilConstantes.CADENA_VACIA);
			personaTo.setSicTelefono(Telefono);
		}

		if (personaTo.getSicFax() == null) {
			Telefono = generico.createPersonaSicFax(UtilConstantes.CADENA_VACIA);
			personaTo.setSicFax(Telefono);
		}
	}

	private void agregarCorreosOrganizacion(com.ccb.simasc.transversal.entidades.Persona personaFrom,
			Organizacion personaTo) {

		List<com.ccb.simasc.transversal.entidades.CorreoElectronico> Correos = personaFrom.getCorreoElectronicoList();
		JAXBElement<String> Correo;
		ObjectFactory generico = new ObjectFactory();

		for (int i = 0; i < Correos.size(); i++) {
			if (Correos.get(i).getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)
					&& personaTo.getSicCorreo() == null) {
				Correo = generico.createPersonaSicCorreo(Correos.get(i).getDireccion().toString());
				personaTo.setSicCorreo(Correo);
			}

		}
		if (personaTo.getSicCorreo() == null) {
			Correo = generico.createPersonaSicCorreo(UtilConstantes.CADENA_VACIA);
			personaTo.setSicCorreo(Correo);
		}
	}

	private void agregarCorreosPersona(com.ccb.simasc.transversal.entidades.Persona personaFrom, Persona personaTo) {

		List<com.ccb.simasc.transversal.entidades.CorreoElectronico> Correos = personaFrom.getCorreoElectronicoList();
		JAXBElement<String> Correo;
		ObjectFactory generico = new ObjectFactory();

		for (int i = 0; i < Correos.size(); i++) {
			if (Correos.get(i).getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)
					&& personaTo.getSicCorreo() == null) {
				Correo = generico.createPersonaSicCorreo(Correos.get(i).getDireccion().toString());
				personaTo.setSicCorreo(Correo);
			}

		}
		if (personaTo.getSicCorreo() == null) {
			Correo = generico.createPersonaSicCorreo(UtilConstantes.CADENA_VACIA);
			personaTo.setSicCorreo(Correo);
		}
	}

	private void agregarDireccionPersona(com.ccb.simasc.transversal.entidades.Persona personaFrom, Persona personaTo) {
		ObjectFactory generico = new ObjectFactory();
		List<com.ccb.simasc.transversal.entidades.Ubicacion> Direcciones;
		JAXBElement<String> Direccion;

		com.ccb.simasc.transversal.entidades.ZonaGeografica zona;

		Direcciones = personaFrom.getUbicacionList();

		for (int i = 0; i < Direcciones.size(); i++) {
			if (Direcciones.get(i).getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)
					&& personaTo.getSicDireccion() == null) {
				Direccion = generico.createPersonaSicDireccion(Direcciones.get(i).getDireccion());
				personaTo.setSicDireccion(Direccion);

				if (Direcciones.get(i).getZonaGeografica()
						.getIdTipoZonaGeografica() == UtilConstantes.TIPO_ZONA_GEOGRAFICA_PAIS) {
					personaTo.setSicIdPais(Direcciones.get(i).getZonaGeografica().getIdZonaGeografica() != null
							? Long.parseLong(this.consultarHomologicaionTextoControlado(
									Direcciones.get(i).getZonaGeografica().getIdZonaGeografica(),
									UtilDominios.DOMINIO_CODIGO_PAIS_MINISTERIO))
							: null);
				} else if (Direcciones.get(i).getZonaGeografica()
						.getIdTipoZonaGeografica() == UtilConstantes.TIPO_ZONA_GEOGRAFICA_DEPARTAMENTO) {
					personaTo.setSicIdPais(Direcciones.get(i).getZonaGeografica().getIdZonaGeograficaPadre() != null
							? Long.parseLong(this.consultarHomologicaionTextoControlado(
									Direcciones.get(i).getZonaGeografica().getIdZonaGeograficaPadre(),
									UtilDominios.DOMINIO_CODIGO_PAIS_MINISTERIO))
							: null);
				} else if (Direcciones.get(i).getZonaGeografica()
						.getIdTipoZonaGeografica() == UtilConstantes.TIPO_ZONA_GEOGRAFICA_CIUDAD) {
					personaTo.setSicIdCiudad(
							Long.parseLong(Direcciones.get(i).getZonaGeografica().getIdZonaGeografica()));
					zona = manejadorZonaGeografica
							.buscar(Direcciones.get(i).getZonaGeografica().getIdZonaGeograficaPadre());// departamento
					zona = manejadorZonaGeografica.buscar(zona.getIdZonaGeograficaPadre());// Pais
					personaTo
							.setSicIdPais(zona.getIdZonaGeografica() != null
									? Long.parseLong(this.consultarHomologicaionTextoControlado(
											zona.getIdZonaGeografica(), UtilDominios.DOMINIO_CODIGO_PAIS_MINISTERIO))
									: null);
				}

			}
		}
		if (personaTo.getSicDireccion() == null) {
			personaTo.setSicDireccion(generico.createPersonaSicDireccion(UtilConstantes.CADENA_VACIA));
		}
		if (personaTo.getSicIdCiudad() == null) {
			personaTo.setSicIdCiudad(0L);
		}
		if (personaTo.getSicIdPais() == null) {
			personaTo.setSicIdPais(UtilConstantes.CODIGO_PAIS_COLOMBIA);

		}

	}

	private void agregarDireccionOrganizacion(com.ccb.simasc.transversal.entidades.Persona personaFrom,
			Organizacion personaTo) {
		ObjectFactory generico = new ObjectFactory();
		List<com.ccb.simasc.transversal.entidades.Ubicacion> Direcciones;
		JAXBElement<String> Direccion;

		com.ccb.simasc.transversal.entidades.ZonaGeografica zona;

		Direcciones = personaFrom.getUbicacionList();

		for (int i = 0; i < Direcciones.size(); i++) {
			if (Direcciones.get(i).getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)
					&& personaTo.getSicDireccion() == null) {
				Direccion = generico.createPersonaSicDireccion(Direcciones.get(i).getDireccion());
				personaTo.setSicDireccion(Direccion);

				if (Direcciones.get(i).getZonaGeografica()
						.getIdTipoZonaGeografica() == UtilConstantes.TIPO_ZONA_GEOGRAFICA_PAIS) {
					personaTo.setSicIdPais(Direcciones.get(i).getZonaGeografica().getIdZonaGeografica() != null
							? Long.parseLong(this.consultarHomologicaionTextoControlado(
									Direcciones.get(i).getZonaGeografica().getIdZonaGeografica(),
									UtilDominios.DOMINIO_CODIGO_PAIS_MINISTERIO))
							: null);
				} else if (Direcciones.get(i).getZonaGeografica()
						.getIdTipoZonaGeografica() == UtilConstantes.TIPO_ZONA_GEOGRAFICA_DEPARTAMENTO) {
					personaTo.setSicIdPais(Direcciones.get(i).getZonaGeografica().getIdZonaGeograficaPadre() != null
							? Long.parseLong(this.consultarHomologicaionTextoControlado(
									Direcciones.get(i).getZonaGeografica().getIdZonaGeograficaPadre(),
									UtilDominios.DOMINIO_CODIGO_PAIS_MINISTERIO))
							: null);
				} else if (Direcciones.get(i).getZonaGeografica()
						.getIdTipoZonaGeografica() == UtilConstantes.TIPO_ZONA_GEOGRAFICA_CIUDAD) {
					personaTo.setSicIdCiudad(
							Long.parseLong(Direcciones.get(i).getZonaGeografica().getIdZonaGeografica()));
					zona = manejadorZonaGeografica
							.buscar(Direcciones.get(i).getZonaGeografica().getIdZonaGeograficaPadre());// departamento
					zona = manejadorZonaGeografica.buscar(zona.getIdZonaGeograficaPadre());// Pais
					personaTo
							.setSicIdPais(zona.getIdZonaGeografica() != null
									? Long.parseLong(this.consultarHomologicaionTextoControlado(
											zona.getIdZonaGeografica(), UtilDominios.DOMINIO_CODIGO_PAIS_MINISTERIO))
									: null);
				}
			}
		}
		if (personaTo.getSicDireccion() == null) {
			personaTo.setSicDireccion(generico.createPersonaSicDireccion(UtilConstantes.CADENA_VACIA));
		}
		if (personaTo.getSicIdCiudad() == null) {
			personaTo.setSicIdCiudad(0L);
		}
		if (personaTo.getSicIdPais() == null) {
			personaTo.setSicIdPais(UtilConstantes.CODIGO_PAIS_COLOMBIA);
		}

	}

	/**
	 * añada la clave y el usuario de CCB con los parametros generales
	 * 
	 * @param datosCaso
	 * @param usuario
	 */
	private void agregarParametrosGenerales(DatosCasoMinisterioDTO datosCaso, Usuario usuario) {
		ObjectFactory generico = new ObjectFactory();
		String claveParm = null;
		String usuarioParm = null;
		for (ParametrosGenerales paramFor : datosCaso.getParametros()) {
			if (UtilDominios.PARAMENTROS_CODIGO_CLAVE.equals(paramFor.getCodigo())) {
				claveParm = paramFor.getValorTexto();
			} else if (UtilDominios.PARAMENTROS_CODIGO_USUARIO.equals(paramFor.getCodigo())) {
				usuarioParm = paramFor.getValorTexto();
			}
		}
		JAXBElement<String> contrasena = generico.createUsuarioSicContrasena(claveParm);
		usuario.setSicContrasena(contrasena);
		JAXBElement<String> nombreUsuario = generico.createUsuarioSicNombreUsuario(usuarioParm);
		usuario.setSicNombreUsuario(nombreUsuario);
	}

	/**
	 * Método encargado de obtener la url (endpoint) del servicio web de
	 * integración ministerio de justicia de los parámetros generales del
	 * sistema
	 * 
	 * @return
	 */
	private String consultarUrl() {
		String urlMinisterio = null;
		ParametrosGenerales parametroUrl = manejadorParametrosGenerales
				.buscar(UtilParamGenerales.CODIGO_PARAMETRO_URL_MINISTERIO);
		if (parametroUrl != null && parametroUrl.getValorTexto() != null) {
			urlMinisterio = parametroUrl.getValorTexto();
		} else {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR900.toString()));
		}
		return urlMinisterio;
	}
	
	/**
	 * Método encargado de registrar la solicitud en la radicación del caso
	 * @param caso -Contiene los datos del caso
	 * @return
	 */
	
}
