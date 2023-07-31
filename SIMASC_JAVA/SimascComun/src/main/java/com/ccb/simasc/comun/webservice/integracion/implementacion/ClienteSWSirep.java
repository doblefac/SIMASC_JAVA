package com.ccb.simasc.comun.webservice.integracion.implementacion;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.ccb.simasc.comun.webservice.integracion.interfaces.IClienteSWSirep;
import com.ccb.simasc.integracion.manejadores.ManejadorHomologacionSistemaExterno;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorZonaGeografica;
import com.ccb.simasc.transversal.dto.formularios.FormularioDatosClienteDTO;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.ZonaGeografica;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.simasc.clientes.sirep.autoridadCompetente.ArrayOfDireccionVO;
import com.simasc.clientes.sirep.autoridadCompetente.AutoridadCompetenteWS;
import com.simasc.clientes.sirep.autoridadCompetente.AutoridadCompetenteWSService;
import com.simasc.clientes.sirep.autoridadCompetente.DatosBasicosClienteDTO;
import com.simasc.clientes.sirep.autoridadCompetente.DireccionVO;
import com.simasc.clientes.sirep.autoridadCompetente.ResultadoResponseDTO;
import com.simasc.clientes.sirep.autoridadCompetente.RmDireccionesVO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import co.org.ccb.sirep2.clientes.ws.ServiciosClientesWS;
import co.org.ccb.sirep2.clientes.ws.ServiciosClientesWSService;
import co.org.ccb.sirep2.clientes.ws.model.ActualizarClienteDatosContactoInDTO;
import co.org.ccb.sirep2.clientes.ws.model.ActualizarClienteDatosContactoOutDTO;
import co.org.ccb.sirep2.clientes.ws.model.ConsultarDatosBasicosClienteWSInDTO;
import co.org.ccb.sirep2.clientes.ws.model.ConsultarDatosBasicosClienteWSOutDTO;
import co.org.ccb.sirep2.clientes.ws.model.CrearClienteDatosContactoInDTO;
import co.org.ccb.sirep2.clientes.ws.model.CrearClienteDatosContactoOutDTO;

/**
 * Clase que se comunica con el sistema de sirep
 * 
 * @author fguzman
 *
 */
@Stateless
@LocalBean
public class ClienteSWSirep implements IClienteSWSirep {
	private static final Logger logger = LogManager.getLogger(ClienteSWSirep.class.getName());
	/**
	 * 
	 */
	private AutoridadCompetenteWSService sirepWebServicePort;
	/**
	 * 
	 */
	private ServiciosClientesWSService clienteSWConsultaDatosBasicos;

	/**
	 * Comunicacion con el cliente que permite consultar el cliente de sirep
	 */
	private ServiciosClientesWS clienteSirepConsultar;

	/**
	 * EJB que permite consultar los datos de los codigos homologados
	 */
	@EJB
	private ManejadorHomologacionSistemaExterno manejadorHomologacionSistemaExterno;

	@EJB
	private ManejadorZonaGeografica manejadorZonaGeografica;

	/**
	 * Manejador utilizado para consultar funcionalidad relacionadas con la entidad
	 * ParametrosGenerales
	 */
	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;

	/**
	 * Se instancia el endpoint y el cliente del servicio a consumir
	 */
	@PostConstruct
	public void init() {
		String urlServiciosClientes = consultaUrl(UtilConstantes.CODIGO_PARAMETRO_URL_SERVICIO_CLIENTE);
		String urlAutoridadCompetente = consultaUrl(UtilConstantes.CODIGO_PARAMETRO_URL_AUTORIDAD_COM);		
		try {
			sirepWebServicePort = new AutoridadCompetenteWSService(new URL(urlAutoridadCompetente));
			clienteSWConsultaDatosBasicos = new ServiciosClientesWSService(new URL(urlServiciosClientes));

			clienteSirepConsultar = clienteSWConsultaDatosBasicos.getServiciosClientesWS();
		} catch (MalformedURLException e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR238.toString());
			throw new SimascException(mensajeError);
		}
	}

	/**
	 * TRANS-041 Transversal-Generacion-de-orden Metodo que crea los datos basicos
	 * del cliente en sirep
	 */
	@Override
	public Map<String, String> crearDatosBasicosClienteSirep(FormularioDatosClienteDTO datosBasicos) {
		Map<String, String> resultado = new HashMap<>();
		AutoridadCompetenteWS clienteSirepCrear = sirepWebServicePort.getAutoridadCompetenteWS();
		String mensaje = null;
		String codigo = null;
		DatosBasicosClienteDTO datosBasicosClienteSirep = new DatosBasicosClienteDTO();
		try {
			datosBasicosClienteSirep.setTipoCliente(1);
			datosBasicosClienteSirep.setPrimerNombre(datosBasicos.getPrimerNombre());
			datosBasicosClienteSirep
					.setSegundoNombre(datosBasicos.getSegundoNombre() != null ? datosBasicos.getSegundoNombre()
							: UtilConstantes.CAMPO_VACIO);
			datosBasicosClienteSirep
					.setPrimerApellido(datosBasicos.getPrimerApellido() != null ? datosBasicos.getPrimerApellido()
							: UtilConstantes.CAMPO_VACIO);
			datosBasicosClienteSirep
					.setSegundoApellido(datosBasicos.getSegundoApellido() != null ? datosBasicos.getSegundoApellido()
							: UtilConstantes.CAMPO_VACIO);
			datosBasicosClienteSirep.setIdClase(homologarTipoDocumentoSirep(datosBasicos.getTipoIdentificacion()));
			datosBasicosClienteSirep.setDescIdClase(homologarTipoDocumentoSirep(datosBasicos.getTipoIdentificacion()));

			datosBasicosClienteSirep.setNumId(datosBasicos.getNumeroIdentificacion());
			datosBasicosClienteSirep.setDigitoVerificacion(
					datosBasicos.getDigitoVerificacion() != null ? datosBasicos.getDigitoVerificacion()
							: UtilConstantes.CAMPO_VACIO);
			datosBasicosClienteSirep.setNumRut(
					datosBasicos.getNumRut() != null ? datosBasicos.getNumRut() : UtilConstantes.CAMPO_VACIO);
			datosBasicosClienteSirep.setCtrCopropiedad(0);
			datosBasicosClienteSirep.setCtrPropMatriculado("0");
			ArrayOfDireccionVO direccion = new ArrayOfDireccionVO();
			DireccionVO direccionVO = new DireccionVO();
			RmDireccionesVO rmDireccionesVO = new RmDireccionesVO();
			rmDireccionesVO.setDireccion(
					datosBasicos.getDireccion() != null ? datosBasicos.getDireccion() : UtilConstantes.CAMPO_VACIO);
			rmDireccionesVO.setIdTipoDir(UtilConstantes.SIREP_TIPO_DIRECCION);
			rmDireccionesVO.setIdMunip(
					datosBasicos.getCiudad() != null ? datosBasicos.getCiudad() : UtilConstantes.CAMPO_VACIO);
			rmDireccionesVO.setIdZonaPostal(datosBasicos.getIdZonaPostal() != null ? datosBasicos.getIdZonaPostal()
					: UtilConstantes.CAMPO_VACIO);
			rmDireccionesVO.setIdZonaGeogr(datosBasicos.getIdZonaGeogrfica() != null ? datosBasicos.getIdZonaGeogrfica()
					: UtilConstantes.CAMPO_VACIO);
			rmDireccionesVO.setNumTel1(datosBasicos.getNumeroTelefono() != null ? datosBasicos.getNumeroTelefono()
					: UtilConstantes.CAMPO_VACIO);
			rmDireccionesVO.setNumTel2(
					datosBasicos.getNumeroTel2() != null ? datosBasicos.getNumeroTel2() : UtilConstantes.CAMPO_VACIO);
			rmDireccionesVO.setNumFax(
					datosBasicos.getNumeroFax() != null ? datosBasicos.getNumeroFax() : UtilConstantes.CAMPO_VACIO);
			rmDireccionesVO.setNumAa(
					datosBasicos.getNumeroAa() != null ? datosBasicos.getNumeroAa() : UtilConstantes.CAMPO_VACIO);
			rmDireccionesVO
					.setEmail(datosBasicos.getEmail() != null ? datosBasicos.getEmail() : UtilConstantes.CAMPO_VACIO);
			rmDireccionesVO.setDirUrl(
					datosBasicos.getDirURL() != null ? datosBasicos.getDirURL() : UtilConstantes.CAMPO_VACIO);
			rmDireccionesVO.setNumMovil(
					datosBasicos.getNumeroMovil() != null ? datosBasicos.getNumeroMovil() : UtilConstantes.CAMPO_VACIO);
			rmDireccionesVO.setCtrMensajes(datosBasicos.getCtrMensajes() != null ? datosBasicos.getCtrMensajes() : 0);
			rmDireccionesVO.setBarrioTextual(datosBasicos.getBarrioTextual() != null ? datosBasicos.getBarrioTextual()
					: UtilConstantes.CAMPO_VACIO);
			rmDireccionesVO.setIdBarrio(
					datosBasicos.getIdBarrio() != null ? datosBasicos.getIdBarrio() : UtilConstantes.CAMPO_VACIO);
			rmDireccionesVO.setCtrMensajesMail(
					datosBasicos.getCtrMensajesMail() != null ? datosBasicos.getCtrMensajesMail() : 0);
			direccionVO.setDireccion(rmDireccionesVO);
			direccion.getDireccionVO().add(direccionVO);
			datosBasicosClienteSirep.setDirecciones(direccion);
			datosBasicosClienteSirep
					.setIdNacionalidad(datosBasicos.getIdNacionalidad() != null ? datosBasicos.getIdNacionalidad()
							: UtilConstantes.CAMPO_VACIO);
			ResultadoResponseDTO crearClienteResultado = clienteSirepCrear
					.crearDatosBasicosCliente(datosBasicosClienteSirep);
			mensaje = crearClienteResultado.getMensajeError();
			codigo = crearClienteResultado.getCodigoError();

			if (UtilConstantes.CODIGO_SIREP_EXITOSO.equals(codigo)) {
				mensaje = crearClienteResultado.getResultado().getDescripcion();
				codigo = crearClienteResultado.getResultado().getCodigo();
				resultado.put("mensaje", mensaje);
				resultado.put("codigo", codigo);
			} else {
				String mensajeError = mensaje;
				throw new SimascException(mensajeError);
			}

		} catch (Exception e) {
			logger.error("Error: ", e);
			String mensajeError = "";
			if (mensaje != null) {
				mensajeError = mensaje;
			} else {
				mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR238.toString());
			}
			throw new SimascException(mensajeError);
		}
		return resultado;
	}

	/**
	 * TRANS-041 Transversal-Generacion-de-orden metodo que consulta los datos
	 * basicos del cliente en sirep
	 */
	@Override
	public FormularioDatosClienteDTO consultarDatosBasicosClienteSirep(String tipoIdentificacion,
			String numeroIdentificacion) {

		
		ConsultarDatosBasicosClienteWSInDTO datosBasicosCliente = new ConsultarDatosBasicosClienteWSInDTO();
		FormularioDatosClienteDTO clienteSirep = null;
		datosBasicosCliente.setIdClase(homologarTipoDocumentoSirep(tipoIdentificacion));
		datosBasicosCliente.setNumId(numeroIdentificacion);
		datosBasicosCliente.setTipoConsulta(UtilConstantes.CERO);
		int intentos = 3;
		while (intentos > 0) {
			try {				
				ConsultarDatosBasicosClienteWSOutDTO cliente = clienteSirepConsultar
						.consultarDatosBasicosCliente(datosBasicosCliente);
				
				if (cliente != null) {
					clienteSirep = convertirClienteSirepClienteSimasc(cliente);
				}
				intentos = -1;
			} catch (Exception e) {
				if (intentos == 1) {
					throw new SimascException(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO383.toString()));
				} else {
					intentos -= 1;
				}
			}
		}
		return clienteSirep;
	}

	/**
	 * TRANS-041 Transversal-Generacion-de-orden Metodo que permite actualizar a una
	 * persona en sirep
	 */
	@Override
	public ActualizarClienteDatosContactoOutDTO ActualizarDatosBasicosClienteSirep(FormularioDatosClienteDTO datosBasicos) {
		
		ActualizarClienteDatosContactoInDTO actualizarClienteDatosContactoInDTO = new ActualizarClienteDatosContactoInDTO();
		actualizarClienteDatosContactoInDTO.setNumCliente(datosBasicos.getNumCliente());
		actualizarClienteDatosContactoInDTO.setDireccion(datosBasicos.getDireccion());
		actualizarClienteDatosContactoInDTO.setCorreoElectronico(datosBasicos.getEmail());
		actualizarClienteDatosContactoInDTO.setTelefono1(datosBasicos.getNumeroTelefono());
		actualizarClienteDatosContactoInDTO.setIdMunicipio(datosBasicos.getCiudad());

		int intentos = 3;
		while (intentos > 0) {
			try {
				ActualizarClienteDatosContactoOutDTO actualizarClienteDatosContactoOutDTO = clienteSirepConsultar
						.actualizarClienteDatosContacto(actualizarClienteDatosContactoInDTO);

				if(actualizarClienteDatosContactoOutDTO.getCodigoError().equals(UtilConstantes.CODIGO_SIREP_EXITOSO)) {
					return actualizarClienteDatosContactoOutDTO;
				}
				intentos = -1;
			} catch (Exception e) {
				if (intentos == 1) {
					throw new SimascException(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO383.toString()));
				} else {
					intentos -= 1;
				}
			}
		}

		return null;
	}

	/**
	 * TRANS-041 Transversal-Generacion-de-orden Homologa los codigos de sirep
	 */
	private String homologarTipoDocumentoSirep(String tipoDocumento) {
		return manejadorHomologacionSistemaExterno.consultarCodigosSistemaExterno(UtilConstantes.SISTEMA_EXTERNO_SIREP,
				tipoDocumento, UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA);
	}

	/**
	 * Metodo que convierte los clientes de sirep en clientes de simasc
	 */
	private FormularioDatosClienteDTO convertirClienteSirepClienteSimasc(ConsultarDatosBasicosClienteWSOutDTO cliente) {
		FormularioDatosClienteDTO clienteSirep = new FormularioDatosClienteDTO();
		clienteSirep.setPrimerNombre(cliente.getPrimerNombre());
		clienteSirep.setSegundoNombre(cliente.getSegundoNombre());
		clienteSirep.setPrimerApellido(cliente.getPrimerApellido());
		clienteSirep.setSegundoApellido(cliente.getSegundoApellido());
		clienteSirep.setNumCliente(String.valueOf(cliente.getNumCliente()));
		if (cliente.getContacto() != null) {
			clienteSirep.setNumeroTelefono(cliente.getContacto().getTelefono());
			clienteSirep.setEmail(cliente.getContacto().getEmail());
		}
		if (cliente.getDireccion() != null) {
			clienteSirep.setDireccion(cliente.getDireccion().getDireccion());
			if (clienteSirep.getEmail() == null) {
				clienteSirep.setEmail(cliente.getDireccion().getEmail());
				clienteSirep.setNumeroTelefono(cliente.getDireccion().getNumTel1());
			}
			ZonaGeografica zona = null;
			String idMun = cliente.getDireccion().getIdMunip();
			if (idMun != null && idMun.startsWith("0")) {
				zona = manejadorZonaGeografica.buscar(idMun.substring(1, idMun.length()));
			} else if (idMun != null) {
				zona = manejadorZonaGeografica.buscar(idMun);
			}
			clienteSirep.setCiudadIdentificacion(zona != null ? zona : new ZonaGeografica());
		}
		clienteSirep.setNumCliente(String.valueOf(cliente.getNumCliente()));
		return clienteSirep;
	}

	private String consultaUrl(String parametro) {
		String url = null;
		ParametrosGenerales parametroUrl = manejadorParametrosGenerales.buscar(parametro);
		if (parametroUrl != null && parametroUrl.getValorTexto() != null) {
			url = parametroUrl.getValorTexto();
		} else {
			throw new SimascException("No existe el parametro de la url para sirep");
		}
		return url;

	}


	@Override
	public CrearClienteDatosContactoOutDTO crearDatosContactoClienteSirep(FormularioDatosClienteDTO datosBasicos) {
		
		CrearClienteDatosContactoInDTO crearClienteDatosContactoInDTO = new CrearClienteDatosContactoInDTO();
		
		crearClienteDatosContactoInDTO.setIdTipoIdentificacion(datosBasicos.getTipoIdentificacion());
		crearClienteDatosContactoInDTO.setNumeroDocumento(datosBasicos.getNumeroIdentificacion());		
		crearClienteDatosContactoInDTO.setDireccion(datosBasicos.getDireccion());
		crearClienteDatosContactoInDTO.setCorreoElectronico(datosBasicos.getEmail());
		crearClienteDatosContactoInDTO.setTelefono1(datosBasicos.getNumeroTelefono());
		crearClienteDatosContactoInDTO.setIdMunicipio(datosBasicos.getCiudad());

		int intentos = 3;
		while (intentos > 0) {
			try {
				CrearClienteDatosContactoOutDTO crearClienteDatosContactoOutDTO = clienteSirepConsultar
						.crearClienteDatosContacto(crearClienteDatosContactoInDTO);

				if(crearClienteDatosContactoOutDTO.getCodigoError().equals(UtilConstantes.CODIGO_SIREP_EXITOSO)) {
					return crearClienteDatosContactoOutDTO;
				}
				intentos = -1;
			} catch (Exception e) {
				if (intentos == 1) {
					throw new SimascException(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO383.toString()));
				} else {
					intentos -= 1;
				}
			}
		}

		return null;
	}
}
