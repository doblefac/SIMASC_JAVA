package com.ccb.simasc.comun.seguridad.fachada.implementacion;

import java.security.Key;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.seguridad.fachada.interfaces.ISeguridadFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.comun.seguridad.servicios.interfaces.IProveedorAutenticacion;
import com.ccb.simasc.integracion.manejadores.ManejadorClave;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronico;
import com.ccb.simasc.integracion.manejadores.ManejadorHomologacionSistemaExterno;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorUsuario;
import com.ccb.simasc.transversal.dto.AutenticacionMaucDTO;
import com.ccb.simasc.transversal.dto.AutenticacionUsuarioDTO;
import com.ccb.simasc.transversal.dto.FuncionalidadDTO;
import com.ccb.simasc.transversal.dto.ObjetoCifradoDTO;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.dto.UsuarioDTO;
import com.ccb.simasc.transversal.dto.UsuarioSesionDTO;
import com.ccb.simasc.transversal.entidades.Clave;
import com.ccb.simasc.transversal.entidades.ClavePK;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Usuario;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;

import co.org.ccb.simasc.comun.correos.ArrayOfAdjuntoDTO;
import co.org.ccb.simasc.comun.correos.ArrayOfString;
import co.org.ccb.simasc.comun.correos.EnvioCorreoInDTO;
import co.org.ccb.simasc.comun.correos.EnvioCorreoServiceSoapProxy;
import co.org.ccb.simasc.comun.correos.template.CorreoClaveValues;
import co.org.ccb.simasc.comun.correos.template.TemplateParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Calse que provee los metodos de autenticación , cifrado y genrales de
 * seguridad
 * 
 * @author fguzman
 */
@Singleton
public class SeguridadFacade implements ISeguridadFacade {
	private static final Logger logger = LogManager.getLogger(SeguridadFacade.class.getName());
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	private Map<String, Map<String, Object>> usuariosAutenticados = new HashMap<String, Map<String, Object>>();
	/**
	 * Recurso que provee de los servicios necesarios para autenticar un usuario
	 */
	@EJB
	public IProveedorAutenticacion iProveedorAutenticacion;

	/**
	 * Recurso que provee los servicios de usuario
	 */
	@EJB
	private ManejadorUsuario manejadorUsuario;

	@EJB
	private ManejadorCorreoElectronico manejadorCorreo;

	/**
	 * Recurso que provee los servicios de persona
	 */
	@EJB
	private ManejadorPersona manejadorPersona;

	/**
	 * Recurso que provee los servicios de clave
	 */
	@EJB
	private ManejadorClave manejadorClave;

	/**
	 * Recurso que provee los servicios de parametros generales
	 */
	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;

	@EJB
	private ManejadorHomologacionSistemaExterno manejadorHomologacionSistemaExterno;

	// protected region atributos end

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// Estructura de indices general para autenticación
	public static final Integer VALIDAR_USUARIO = 0;
	public static final Integer VALIDAR_CONTRASENA = 1;
	public static final Integer VALIDAR_BLOQUEO_CONTRASENA = 2;
	public static final Integer RESULTADO_AUTENTIACIONES = 3;
	public static final Integer CONTRASENA_EXPIRADA = 4;
	public static final Integer USUARIO_INVALIDO = 5;
	// Estructura de indices general para Generar contraseña
	public static final Integer PASSWORD = 0;
	public static final Integer SHA512 = 1;
	// Constantes
	public static final Integer MENOR_LONGITUD_CONTRASENA = 7;
	public static final Integer MATH_RANDOM = 52;
	public static final Integer MODULO = 26;

	/**
	 * @author fguzman
	 * @descripcion autentica el usuario
	 * @param String      nombreUsuario
	 * @param List<Object parametrosValidacion
	 */
	@Override
	public String autenticarUsuario(String nombreUsuario, List<Object> parametros, List<String> metodosAutenticacion)
			throws SimascException {
		Map<String, Object> tienSesion = usuariosAutenticados.get(nombreUsuario);
		boolean[] autenticacion = { false, false, false, false, false, false };
		Usuario usuario = new Usuario();
		Persona persona = new Persona();
		autenticacion = iProveedorAutenticacion.autenticar(nombreUsuario, parametros, metodosAutenticacion);
		boolean esUsuarioAnonimo = (parametros.get(2) != null && (Boolean) parametros.get(2));
		String respuesta = UtilConstantes.EMPTY_RESPONSE;

		if (!autenticacion[VALIDAR_USUARIO])
			throw new SimascException(UtilConstantes.LOGIN_ERROR);

		if (!autenticacion[VALIDAR_CONTRASENA])
			throw new SimascException(UtilConstantes.LOGIN_ERROR);

		if (autenticacion[USUARIO_INVALIDO])
			throw new SimascException(UtilConstantes.USER_NOT_VALID);

		if (autenticacion[VALIDAR_BLOQUEO_CONTRASENA])
			throw new SimascException(UtilConstantes.BLOCK_USER_ERROR);

		if (autenticacion[VALIDAR_USUARIO] && autenticacion[RESULTADO_AUTENTIACIONES]) {
			if (tienSesion != null) {
				boolean cant = tienSesion.get(UtilConstantes.REINTENTOS) == UtilConstantes.INTENTOS;
				if (cant) {
					bloquearUsuario(nombreUsuario, (String) parametros.get(0));
					throw new SimascException(
							String.format(UtilConstantes.INTENTO_SESION_ERROR, UtilConstantes.INTENTOS));
				} else {
					Integer noIntentos = (Integer) tienSesion.get(UtilConstantes.REINTENTOS) + 1;
					tienSesion.put(UtilConstantes.REINTENTOS, noIntentos);
				}
				cant = tienSesion.get(UtilConstantes.REINTENTOS) == UtilConstantes.INTENTOS;
				if (cant) {
					throw new SimascException(UtilConstantes.LOGIN_ERROR_TO_INVALIDATE);
				}
			} else {
				// Se añade el usuario al la sesion de usuarios autenticados
				usuariosAutenticados.put(nombreUsuario, contextoSesion(UtilConstantes.TIPO_SESION_VALOR_ERROR));
				if (autenticacion[RESULTADO_AUTENTIACIONES]) {
					// Modificacion tres intentos
//					Map<String, Object> sesionNueva = usuariosAutenticados.get(nombreUsuario);
//					Integer noIntentos = (Integer) sesionNueva.get(UtilConstantes.REINTENTOS) + 1;
//					sesionNueva.put(UtilConstantes.REINTENTOS, noIntentos);
				}
			}
		}
		if (autenticacion[RESULTADO_AUTENTIACIONES]) {
			throw new SimascException(UtilConstantes.LOGIN_ERROR);
		} else {
			usuario = manejadorUsuario.buscar(nombreUsuario);
			persona = manejadorPersona.buscar(usuario.getIdPersona());
			if (tienSesion != null) {
				if (!esUsuarioAnonimo) {
					if ((Calendar.getInstance()).before((Calendar) tienSesion.get(UtilConstantes.TIMEOUT))
							&& ((String) tienSesion.get(UtilConstantes.TIPO_SESION_KEY))
									.equals(UtilConstantes.TIPO_SESION_VALOR_OK)) {
						Long diferenciaMillis = Math
								.abs(((Calendar) tienSesion.get(UtilConstantes.TIMEOUT)).getTimeInMillis()
										- Calendar.getInstance().getTimeInMillis());
						Long tiempoSegundos = (diferenciaMillis) / UtilConstantes.SECOND_TO_MILLIS;
						Long tiempoEspera = (tiempoSegundos) / UtilConstantes.MINUTES_TO_SECONDS;
						if (tiempoSegundos >= 60)
							throw new SimascException(
									String.format(UtilConstantes.TIME_SESION_ERROR, tiempoEspera.toString()));
						else
							throw new SimascException(
									String.format(UtilConstantes.TIME_SESION_SECONDS_ERROR, tiempoSegundos.toString()));
					} else {
						usuariosAutenticados.remove(nombreUsuario);
						usuariosAutenticados.put(nombreUsuario, contextoSesion(UtilConstantes.TIPO_SESION_VALOR_OK));
						respuesta = createJWT(nombreUsuario, usuario.getIdPersona().toString(),
								persona.getNombreCompleto(), UtilConstantes.TIEMPO_VIDA_JWT,
								autenticacion[CONTRASENA_EXPIRADA]);
						usuario.setUltimoAcceso(new Date());
						usuario.setFechaUltimaModificacion(new Date());
						manejadorUsuario.actualizar(usuario);
					}
				} else {
					respuesta = createJWT(nombreUsuario, usuario.getIdPersona().toString(), persona.getNombreCompleto(),
							UtilConstantes.TIEMPO_VIDA_JWT, autenticacion[CONTRASENA_EXPIRADA]);
				}
			} else {
				// Se añade el usuario al la sesion de usuarios autenticados
				usuariosAutenticados.put(nombreUsuario, contextoSesion(UtilConstantes.TIPO_SESION_VALOR_OK));
				respuesta = createJWT(nombreUsuario, usuario.getIdPersona().toString(), persona.getNombreCompleto(),
						UtilConstantes.TIEMPO_VIDA_JWT, autenticacion[CONTRASENA_EXPIRADA]);
				// Mejora F16 ARB-F-114
				usuario.setUltimoAcceso(new Date());
				usuario.setFechaUltimaModificacion(new Date());
				manejadorUsuario.actualizar(usuario);
			}
		}

		return respuesta;
	}

	/**
	 * Metodo que permite bloquear un usuario
	 * 
	 * @param nombreUsuario pk del usuario
	 */
	public void bloquearUsuario(String nombreUsuario, String pwd) {
		Usuario us = manejadorUsuario.buscar(nombreUsuario);
		List<Clave> clst = us.getClaveList();
		us.setEstado(UtilDominios.ESTADO_REGISTRO_INACTIVO);
		for (Clave c : clst) {
			c.setClaveBloqueada(true);
			manejadorClave.actualizar(c);
		}
		manejadorUsuario.actualizar(us);
		usuariosAutenticados.remove(nombreUsuario);
	}

	/**
	 * Método encargado de la construcción del cuerpo del correo
	 * 
	 * @param nombreUsuario
	 * @param clave
	 * @return
	 */
	private String cuerpoCorreo(String nombreUsuario, String clave) {
		String cuerpoCorreo = UtilConstantes.CADENA_VACIA;

		ParametrosGenerales parametrosHipervinculo = manejadorParametrosGenerales.obtenerParametrosPorCodigoYTipo(
				UtilConstantes.URL_HIPERVINCULO_GENERAR_CLAVE, UtilConstantes.TIPO_URL_HIPERVINCULO_GENERAR_CLAVE);

		if (parametrosHipervinculo != null) {
			if(clave != null) {
				CorreoClaveValues valores = new CorreoClaveValues(nombreUsuario, clave,
						parametrosHipervinculo.getValorTexto());
				cuerpoCorreo = TemplateParser.getInstancia().setAtributos(valores, UtilConstantes.TEMPLATE_CAMBIO_CLAVE);
			}else {
				CorreoClaveValues valores = new CorreoClaveValues(nombreUsuario, null,
						parametrosHipervinculo.getValorTexto());
				cuerpoCorreo = TemplateParser.getInstancia().setAtributos(valores, UtilConstantes.TEMPLATE_USUARIO_SIN_CLAVE);
			}
		} else {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR800.toString()));
		}

		return cuerpoCorreo;
	}

	/**
	 * Metodo que permite recuperación de contraseña de un usuario exixtente
	 * 
	 * @param usuario
	 */
	public void recuperarClave(String usuarioLogin, String correo) {
		try {
			Usuario usuario = manejadorUsuario.buscar(usuarioLogin);

			// Validación :: La persona debe tener al menos un correo
			List<CorreoElectronico> correosPersona = manejadorCorreo.consultaCorreosPersona(usuario.getIdPersona());

			if (correosPersona.isEmpty()) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR271.toString()));
			}

			// Desactivar todas las claves
			LinkedList<Clave> claves = usuarioClaves(usuario.getUsuarioLogin(), null);

			for (Clave clave : claves) {
				clave.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_CLAVE_INACTIVO);
				manejadorClave.actualizar(clave);
			}
			validarCantidadClaves(claves);
			Clave ultimaClave = claves.get(0);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, UtilConstantes.DIAS_ACTUALIZA_FECHA_VENCIMIENTO);
			ultimaClave.setFechaVencimiento(cal.getTime());
			ultimaClave.setFechaUltimaModificacion(new Date());
			Persona sistema = manejadorPersona.consultarPersonasPorTipoPersona(UtilDominios.TIPO_PERSONA_SISTEMA,
					UtilDominios.ESTADO_PERSONA_ACTIVO);
			ultimaClave.setIdUsuarioModificacion(sistema.getNumeroDocumento());
			manejadorClave.actualizar(ultimaClave);
			String[] newpassword = generador();// contraseña,SHA512
			Clave nuevaClave = new Clave();
			ClavePK nuevaClavePK = new ClavePK(usuario.getUsuarioLogin(), newpassword[SHA512]);
			nuevaClave.setClaveBloqueada(false);
			nuevaClave.setClavePK(nuevaClavePK);
			nuevaClave.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_CLAVE_ACTIVO);
			nuevaClave.setFechaUltimaModificacion(new Date());
			cal.clear();
			cal = Calendar.getInstance();
			cal.add(Calendar.DATE, UtilConstantes.DIAS_GENERAR_CLAVE_FECHA_VENCIMIENTO);
			nuevaClave.setFechaVencimiento(cal.getTime());
			nuevaClave.setIdUsuarioModificacion(sistema.getNumeroDocumento());
			manejadorClave.crear(nuevaClave);
			enviarCorreo(correo, null, UtilConstantes.ASUNTO_OLVIDO,
					cuerpoCorreo(usuario.getUsuarioLogin(), newpassword[PASSWORD]));

		} catch (Error e) {
			throw new SimascException(UtilConstantes.USER_ERROR);
		} catch (javax.xml.ws.soap.SOAPFaultException ex) {
			LogManager.getLogger(getClass()).error(ex.getMessage());
			throw new SimascException(UtilConstantes.ERROR_CORREO);
		}
	}

	/**
	 * Metodo que permite generar la contraseña de un usuario exixtente
	 * 
	 * @param usuario
	 */
	public void generarClave(UsuarioDTO usuario, String idModificador) {
		try {

			// Validación :: La persona debe tener al menos un correo
			List<CorreoElectronico> correosPersona = manejadorCorreo.consultaCorreosPersona(usuario.getIdPersona());

			if (correosPersona.isEmpty()) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR271.toString()));
			}

			// Desactivar todas las claves
			LinkedList<Clave> claves = usuarioClaves(usuario.getUsuarioLogin(), null);

			for (Clave clave : claves) {
				clave.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_CLAVE_INACTIVO);
				manejadorClave.actualizar(clave);
			}
			validarCantidadClaves(claves);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, UtilConstantes.DIAS_ACTUALIZA_FECHA_VENCIMIENTO);
			if (!claves.isEmpty()) {
				Clave ultimaClave = claves.get(0);

				ultimaClave.setFechaVencimiento(cal.getTime());
				ultimaClave.setFechaUltimaModificacion(new Date());
				ultimaClave.setIdUsuarioModificacion(idModificador);
				manejadorClave.actualizar(ultimaClave);
			}
			String[] newpassword = generador();// contraseña,SHA512
			Clave nuevaClave = new Clave();
			ClavePK nuevaClavePK = new ClavePK(usuario.getUsuarioLogin(), newpassword[SHA512]);
			nuevaClave.setClaveBloqueada(false);
			nuevaClave.setClavePK(nuevaClavePK);
			nuevaClave.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_CLAVE_ACTIVO);
			nuevaClave.setFechaUltimaModificacion(new Date());
			cal.clear();
			cal = Calendar.getInstance();
			cal.add(Calendar.DATE, UtilConstantes.DIAS_GENERAR_CLAVE_FECHA_VENCIMIENTO);
			nuevaClave.setFechaVencimiento(cal.getTime());
			nuevaClave.setIdUsuarioModificacion(idModificador);
			manejadorClave.crear(nuevaClave);
			
			if(usuario.getAplicaMauc()) {
				String cuerpoCorreo = cuerpoCorreo(usuario.getUsuarioLogin(), null);
				enviarCorreo(null, usuario.getIdPersona(), UtilConstantes.ASUNTO_REGISTRO_USUARIO,
						cuerpoCorreo);
			}else {
				enviarCorreo(null, usuario.getIdPersona(), UtilConstantes.ASUNTO_GENERACION,
						cuerpoCorreo(usuario.getUsuarioLogin(), newpassword[PASSWORD]));
			}


		} catch (Error e) {
			throw new SimascException(UtilConstantes.USER_ERROR);
		} catch (javax.xml.ws.soap.SOAPFaultException ex) {
			LogManager.getLogger(getClass()).error(ex.getMessage());
			throw new SimascException(UtilConstantes.ERROR_CORREO);
		}
	}

	public String generarClaveSinEnvioCorreo(UsuarioDTO usuario, String idModificador) {
		try {

			// Validación :: La persona debe tener al menos un correo
			List<CorreoElectronico> correosPersona = manejadorCorreo.consultaCorreosPersona(usuario.getIdPersona());

			if (correosPersona.isEmpty()) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR271.toString()));
			}

			// Desactivar todas las claves
			LinkedList<Clave> claves = usuarioClaves(usuario.getUsuarioLogin(), null);

			for (Clave clave : claves) {

				if (clave.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_CLAVE_ACTIVO)
						&& !clave.getClaveBloqueada()) {
					return "";
				}
				clave.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_CLAVE_INACTIVO);
				manejadorClave.actualizar(clave);
			}

			validarCantidadClaves(claves);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, UtilConstantes.DIAS_ACTUALIZA_FECHA_VENCIMIENTO);
			if (!claves.isEmpty()) {
				Clave ultimaClave = claves.get(0);

				ultimaClave.setFechaVencimiento(cal.getTime());
				ultimaClave.setFechaUltimaModificacion(new Date());
				ultimaClave.setIdUsuarioModificacion(idModificador);
				manejadorClave.actualizar(ultimaClave);
			}
			String[] newpassword = generador();// contraseña,SHA512
			Clave nuevaClave = new Clave();
			ClavePK nuevaClavePK = new ClavePK(usuario.getUsuarioLogin(), newpassword[SHA512]);
			nuevaClave.setClaveBloqueada(false);
			nuevaClave.setClavePK(nuevaClavePK);
			nuevaClave.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_CLAVE_ACTIVO);
			nuevaClave.setFechaUltimaModificacion(new Date());
			cal.clear();
			cal = Calendar.getInstance();
			cal.add(Calendar.DATE, UtilConstantes.DIAS_GENERAR_CLAVE_FECHA_VENCIMIENTO);
			nuevaClave.setFechaVencimiento(cal.getTime());
			nuevaClave.setIdUsuarioModificacion(idModificador);
			manejadorClave.crear(nuevaClave);

			return newpassword[PASSWORD];

		} catch (Error e) {
			throw new SimascException(UtilConstantes.USER_ERROR);
		} catch (javax.xml.ws.soap.SOAPFaultException ex) {
			LogManager.getLogger(getClass()).error(ex.getMessage());
			throw new SimascException(UtilConstantes.ERROR_CORREO);
		}
	}

	/**
	 * ADM-C-021 Genera la clave para el usuario que se pasa como parámetro
	 * 
	 * @param usuario
	 */
	public void generarClaveUsuario(Usuario usuario) {
		UsuarioDTO usuarioDTO = (new UsuarioDTO()).transformarSinDependencias(usuario);
		this.generarClaveSinUsuarioModificacion(usuarioDTO);
	}

	/**
	 * Metodo que permite generar la contraseña de un usuario exixtente
	 * 
	 * @param usuario
	 */
	public void generarClaveSinUsuarioModificacion(UsuarioDTO usuario) {
		try {

			// Validación :: La persona debe tener al menos un correo
			List<CorreoElectronico> correosPersona = manejadorCorreo.consultaCorreosPersona(usuario.getIdPersona());

			if (correosPersona.isEmpty()) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR271.toString()));
			}

			LinkedList<Clave> claves = usuarioClaves(usuario.getUsuarioLogin(),
					UtilDominios.ESTADO_REGISTRO_CLAVE_ACTIVO);
			validarCantidadClaves(claves);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, UtilConstantes.DIAS_ACTUALIZA_FECHA_VENCIMIENTO);
			if (!claves.isEmpty()) {
				Clave ultimaClave = claves.get(0);

				ultimaClave.setFechaVencimiento(cal.getTime());
				ultimaClave.setFechaUltimaModificacion(new Date());
				// ultimaClave.setIdUsuarioModificacion(idModificador);
				manejadorClave.actualizar(ultimaClave);
			}
			String[] newpassword = generador();// contraseña,SHA512
			Clave nuevaClave = new Clave();
			ClavePK nuevaClavePK = new ClavePK(usuario.getUsuarioLogin(), newpassword[SHA512]);
			nuevaClave.setClaveBloqueada(false);
			nuevaClave.setClavePK(nuevaClavePK);
			nuevaClave.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_CLAVE_ACTIVO);
			nuevaClave.setFechaUltimaModificacion(new Date());
			cal.clear();
			cal = Calendar.getInstance();
			cal.add(Calendar.DATE, UtilConstantes.DIAS_GENERAR_CLAVE_FECHA_VENCIMIENTO);
			nuevaClave.setFechaVencimiento(cal.getTime());
			// nuevaClave.setIdUsuarioModificacion(idModificador);
			manejadorClave.crear(nuevaClave);
			if(usuario.getAplicaMauc()) {
				String cuerpoCorreo = cuerpoCorreo(usuario.getUsuarioLogin(), null);
				enviarCorreo(null, usuario.getIdPersona(), UtilConstantes.ASUNTO_REGISTRO_USUARIO,
						cuerpoCorreo);
			}else {
				String cuerpoCorreo = cuerpoCorreo(usuario.getUsuarioLogin(), newpassword[PASSWORD]);
				enviarCorreo(null, usuario.getIdPersona(), UtilConstantes.ASUNTO_GENERACION,
						cuerpoCorreo);
			}


		} catch (Error e) {
			throw new SimascException(UtilConstantes.USER_ERROR);
		} catch (javax.xml.ws.soap.SOAPFaultException ex) {
			LogManager.getLogger(getClass()).error(ex.getMessage());
			throw new SimascException(UtilConstantes.ERROR_CORREO);
		}
	}

	/**
	 * Genera un contraseña aleatoria en SHA512
	 * 
	 * @return
	 */
	private String[] generador() {
		String[] retorna = { " ", " " };
		int randomNum = ThreadLocalRandom.current().nextInt(UtilConstantes.GENERADOR_MIN, UtilConstantes.GENERADOR_MAX);
		String ex = String.valueOf(Integer.toHexString(randomNum));
		if (ex.length() <= MENOR_LONGITUD_CONTRASENA)
			ex = ex + rndChar();
		ex = ex.toUpperCase();
		retorna[PASSWORD] = ex;
		retorna[SHA512] = DigestUtils.sha512Hex(ex);
		return retorna;
	}

	/**
	 * Metodo que retorna una letra
	 * 
	 * @return
	 */
	private static char rndChar() {
		int rnd = (int) (Math.random() * MATH_RANDOM);
		char base = (rnd < MODULO) ? 'A' : 'a';
		return (char) (base + rnd % MODULO);

	}

	/**
	 * Metodo que permite cambiar la contraseña de un usuario exixtente
	 * 
	 * @param usuario
	 */
	public void cambioClave(UsuarioDTO usuario, String idModificador) {
		if (validaCoincidenciaClave(usuario.getUsuarioLogin(), usuario.getAntiguaClave()))
			throw new SimascException(UtilConstantes.CAMBIO_INGRESO_SIN_COINCIDENCIA);
		if (validarExistenciaClave(usuario.getUsuarioLogin(), usuario.getNuevaClave())) {
			try {
				LinkedList<Clave> claves = usuarioClaves(usuario.getUsuarioLogin(), null);
				// Desactivar todas las claves
				for (Clave clave : claves) {
					clave.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_CLAVE_INACTIVO);
					manejadorClave.actualizar(clave);
				}
				validarCantidadClaves(claves);
				Clave ultimaClave = claves.get(0);
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, UtilConstantes.DIAS_ACTUALIZA_FECHA_VENCIMIENTO);
				ultimaClave.setFechaVencimiento(cal.getTime());
				ultimaClave.setFechaUltimaModificacion(new Date());
				manejadorClave.actualizar(ultimaClave);

				// Asignar nueva clave
				Clave nuevaClave = new Clave();
				ClavePK nuevaClavePK = new ClavePK(usuario.getUsuarioLogin(), usuario.getNuevaClave());
				nuevaClave.setClaveBloqueada(false);
				nuevaClave.setClavePK(nuevaClavePK);
				nuevaClave.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_CLAVE_ACTIVO);
				nuevaClave.setFechaUltimaModificacion(new Date());
				cal.add(Calendar.DATE, UtilConstantes.DIAS_FECHA_VENCIMIENTO);
				nuevaClave.setFechaVencimiento(cal.getTime());
				nuevaClave.setIdUsuarioModificacion(idModificador);
				manejadorClave.crear(nuevaClave);
			} catch (Error e) {
				throw new SimascException(UtilConstantes.USER_ERROR);
			}

		} else {
			throw new SimascException(UtilConstantes.INGRESO_UTILIZADO);
		}
	}

	/**
	 * Metodo que valida que la clave corresponda a alguna de las claves activas
	 * almacenadas en el sistema.
	 * 
	 * @return
	 */
	private boolean validaCoincidenciaClave(String idUsuario, String clave) {
		Boolean isOk = true;
		Usuario us = manejadorUsuario.buscar(idUsuario);
		List<Clave> claves = us.getClaveList();
		for (Clave c : claves)
			if (UtilDominios.ESTADO_REGISTRO_CLAVE_ACTIVO.equals(c.getEstadoRegistro())
					&& c.getClavePK().getClave().contentEquals(clave))
				isOk = false;
		return isOk;
	}

	/**
	 * Metodo que valida que la clave no corresponda a ninguna de las claves
	 * almacenadas en el sistema.
	 * 
	 * @return true si exite una clave igual a la ingresada, false de lo contrario
	 */
	private boolean validarExistenciaClave(String idUsuario, String clave) {
		Boolean isOk = true;
		Usuario us = manejadorUsuario.buscar(idUsuario);
		List<Clave> claves = us.getClaveList();
		for (Clave c : claves)
			if (c.getClavePK().getClave().contentEquals(clave))
				isOk = false;
		return isOk;
	}

	/**
	 * Metodo que valida si la cnatidad de claves almacinadas es mayor o igual a
	 * NUMERO_CLAVES_ACTIVAS "7" y elemina la clave mas antigua si la anterior
	 * condicion se cumple.
	 * 
	 * @param idUsuario
	 */
	private void validarCantidadClaves(LinkedList<Clave> claves) {
		if (claves.size() >= UtilConstantes.NUMERO_CLAVES_ACTIVAS) {
			List<Clave> ultimaClave = claves.subList(UtilConstantes.NUMERO_CLAVES_ACTIVAS - 1, claves.size());
			for (int i = 0; i < ultimaClave.size(); i++) {
				Clave password = ultimaClave.get(i);
				manejadorClave.eliminar(password);
			}
		}
	}

	/**
	 * Metodo que retorna la lista las claves activas de un usuario ordeadas
	 * descendentemente.
	 * 
	 * @param idUsuario
	 * @return
	 */
	private LinkedList<Clave> usuarioClaves(String idUsuario, String estado) {
		Usuario us = manejadorUsuario.buscar(idUsuario);
		LinkedList<Clave> clvLst = new LinkedList<Clave>();
		if (us.getClaveList() != null) {

			for (Clave c : us.getClaveList())
				if (estado == null || c.getEstadoRegistro().equals(estado))
					clvLst.add(c);
			Collections.sort(clvLst, new Comparator<Clave>() {
				@Override
				public int compare(Clave o1, Clave o2) {
					if (o1.getFechaVencimiento().before(o2.getFechaVencimiento()))
						return 1;
					else if (o2.getFechaVencimiento().before(o1.getFechaVencimiento()))
						return -1;
					else
						return 0;
				}
			});

		}
		return clvLst;
	}

	/**
	 * Metodo que retorna un Map<String,Object> que contiene la estampa y el timeout
	 * 
	 * @return Map<String,Object>
	 */
	private Map<String, Object> contextoSesion(String tipoSesion) {
		Map<String, Object> contextoSesion = new HashMap<String, Object>();
		Calendar estampa = Calendar.getInstance();
		Calendar timeout = Calendar.getInstance();
		Integer noIntentos = 1;
		timeout.setTimeInMillis(estampa.getTimeInMillis() + calcularTiempoSesion());
		contextoSesion.put(UtilConstantes.ESTAMPA, estampa);
		contextoSesion.put(UtilConstantes.TIMEOUT, timeout);
		contextoSesion.put(UtilConstantes.REINTENTOS, noIntentos);
		contextoSesion.put(UtilConstantes.TIPO_SESION_KEY, tipoSesion);
		return contextoSesion;
	}

	private Long calcularTiempoSesion() {
		Long retorno = 0l;

		try {
			retorno = manejadorParametrosGenerales.buscar(UtilConstantes.TIEMPO_VIDA_SESION_MINUTOS).getValorNumerico()
					* UtilConstantes.MINUTES_TO_MILLIS;
		} catch (Exception e) {
			// Control de errores a la hora de obtener la definición por parametro
			LogManager.getLogger(getClass()).error(e.getMessage());
		}
		return retorno;
	}

	/**
	 * @author fguzman
	 * @descripcion Obtiene el contexto de la sesion
	 * @param UsuarioDTO usuario
	 * @return contexto de sesion
	 */
	@Override
	public SessionContext obtenerConetexto(UsuarioDTO usuario) {
		return null;
	}

	/**
	 * @author fguzman
	 * @descripcion Revisa sobre las reglas de los usuarios si pueden ver la
	 *              funcionalidad
	 * @param UsuarioDTO    usuario
	 * @param funcionalidad funcionalidad
	 * @return retorna falso si no tiene permisos y verdadero si cuenta con los
	 *         permisos
	 * 
	 */
	@Override
	public boolean validarFuncionalidad(UsuarioDTO usuario, FuncionalidadDTO funcionalidad) {
		return true;
	}

	/**
	 * @author fguzman
	 * @descripcion cierra la sesion
	 * @param usuario
	 */
	@Override
	public void cerrarSesion(UsuarioDTO usuario) {
		

	}

	/**
	 * @author fguzman
	 * @descripcion Valida el token de la autenticación
	 * @param token
	 * @return la validacion del token
	 * 
	 */
	@Override
	public boolean validarJWT(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(UtilConstantes.APIKEY))
					.parseClaimsJws(token).getBody();
			Date now = new Date();
			return !now.after(claims.getExpiration());
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Crea un JSON Web Token
	 * 
	 * @param id
	 * @param issuer
	 * @param subject
	 * @param ttlMillis
	 * @return
	 */
	private String createJWT(String issuer, String idpersona, String nombrepersona, long ttlMillis, boolean isExpired) {

		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(UtilConstantes.APIKEY);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setIssuer(issuer).setIssuedAt(now)
				.claim(UtilConstantes.TOKEN_IDPERSONA, idpersona)
				.claim(UtilConstantes.TOKEN_NOMBRE_PERSONA, nombrepersona)
				.claim(UtilConstantes.TOKEN_CLAVE_EXPIRADA, isExpired).signWith(signatureAlgorithm, signingKey);

		// if it has been specified, let's add the expiration
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

	private String createJWTMAUC(AutenticacionMaucDTO autenticacionMAUC) {

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(UtilConstantes.APIKEY);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setIssuer(autenticacionMAUC.getIssuer()).setIssuedAt(now)
				.claim(UtilConstantes.TOKEN_IDPERSONA, autenticacionMAUC.getIdpersona())
				.claim(UtilConstantes.TOKEN_NOMBRE_PERSONA, autenticacionMAUC.getNombrepersona())
				.claim(UtilConstantes.TOKEN_CLAVE_EXPIRADA, autenticacionMAUC.isExpired())
				.signWith(signatureAlgorithm, signingKey)
				.claim(UtilConstantes.TOKEN_MAUC, autenticacionMAUC.getMaucToken());

		// if it has been specified, let's add the expiration
		if (autenticacionMAUC.getTtlMillis() >= 0) {
			long expMillis = nowMillis + autenticacionMAUC.getTtlMillis();
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();

	}

	/**
	 * Metodo que quita al usuario que le llega por parametro del mapa de contexto.
	 * 
	 * @param nombreUsuario
	 */
	@Override
	public void cerrarSesionUsuario(String nombreUsuario) {
		Map<String, Object> tieneSesion = usuariosAutenticados.get(nombreUsuario);
		if (tieneSesion != null)
			usuariosAutenticados.remove(nombreUsuario);
	}

	/**
	 * Metodo que descifra un objeto cifrado en AES
	 * 
	 * @param objetoCifrado
	 */
	@Override
	public String descifrarEncabezadoPeticion(ObjetoCifradoDTO objetoCifrado) throws SimascException {
		String textoDescifrado = null;

		try {
			String iv = objetoCifrado.getVectorInicializacion();
			String textoCifrado = objetoCifrado.getTextoCifrado();

			byte[] bytesIv = DatatypeConverter.parseHexBinary(iv);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(bytesIv);
			SecretKeySpec secretKeySpec = new SecretKeySpec(Hex.decodeHex(UtilConstantes.OBJECT_AES_KEY.toCharArray()),
					"AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

			byte[] bytesTextoCifrado = Hex.decodeHex(textoCifrado.toCharArray());
			byte[] bytesTextoDescifrado = cipher.doFinal(bytesTextoCifrado);

			textoDescifrado = new String(bytesTextoDescifrado);
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR186.toString()));
		}

		return textoDescifrado;
	}

	/**
	 * 
	 * 
	 * @param idReceptor   Id de persona
	 * @param asunto
	 * @param cuerpoCorreo
	 */
	public void enviarCorreo(String correoR, Long idReceptor, String asunto, String cuerpoCorreo) {

		EnvioCorreoServiceSoapProxy servicio = new EnvioCorreoServiceSoapProxy();
		// traer personas del sistema
		Persona sistema = new PersonaDTO().transformarEntidadConDependencias(
				manejadorPersona.consultarPersonasPorTipoPersona(UtilDominios.TIPO_PERSONA_SISTEMA,
						UtilDominios.ESTADO_PERSONA_ACTIVO));
		CorreoElectronico correoSistema = sistema.getCorreoElectronicoList().get(0);
		// trae persona receptor
		List<CorreoElectronico> correoReceptor = new ArrayList<CorreoElectronico>();		
		if (correoR == null) {			
			correoReceptor = manejadorCorreo.consultaCorreosPersona(idReceptor);
		}
		// EnvioDTO
		EnvioCorreoInDTO inDTO = new EnvioCorreoInDTO();
		inDTO.setDe(correoSistema.getDireccion());

		inDTO.setAsunto(asunto);
		inDTO.setContenido(cuerpoCorreo);
		String tipoContenido = null;
		String llave = null;
		List<ParametrosGenerales> parametros = manejadorParametrosGenerales
				.obtenerParametrosPorTipo(UtilParamGenerales.TIPO_CORREO);
		for (ParametrosGenerales parametrosGenerales : parametros) {
			switch (parametrosGenerales.getCodigo()) {
			case UtilParamGenerales.CORREO_LLAVE:
				llave = parametrosGenerales.getValorTexto();
				break;
			case UtilParamGenerales.CORREO_TIPO_CONTENIDO:
				tipoContenido = parametrosGenerales.getValorTexto();
				break;
			default:
				break;
			}
		}
		inDTO.setTipoContenido(tipoContenido);
		inDTO.setLlave(llave);
		ArrayOfAdjuntoDTO listaAdjuntos = new ArrayOfAdjuntoDTO();
		inDTO.setAdjuntos(listaAdjuntos);

		ArrayOfString listaDestinos = new ArrayOfString();
		if (correoR != null) {
			listaDestinos.getString().clear();
			listaDestinos.getString().add(correoR);
			inDTO.setPara(listaDestinos);
			servicio.envioCorreo(inDTO);
		} else if (!correoReceptor.isEmpty()) {
			for (CorreoElectronico correoReceptorFor : correoReceptor) {				
				listaDestinos.getString().clear();
				if (correoReceptorFor.getDireccion() != null && correoReceptorFor.getDireccion() != ""
						&& UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(correoReceptorFor.getEstadoRegistro()))
					;
				listaDestinos.getString().add(correoReceptorFor.getDireccion());
				inDTO.setPara(listaDestinos);
				servicio.envioCorreo(inDTO);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.comun.seguridad.fachada.interfaces.ISeguridadFacade#
	 * autenticarPersonaRetomaSolicitud(java.lang.String, java.lang.String)
	 */
	@Override
	public String autenticarPersonaRetomaSolicitud(String numeroDocumento, String correoElectronico) {
		Persona persona = manejadorPersona.consultarPersonaPorDocumentoYCorreoElectronico(numeroDocumento,
				correoElectronico);

		if (persona == null) {
			throw new SimascException(UtilConstantes.SOLICITANTE_NO_EXISTE);
		}

		return createJWT(persona.getNumeroDocumento(), persona.getIdPersona().toString(), persona.getNombreCompleto(),
				UtilConstantes.TIEMPO_VIDA_JWT, false);
	}

	@Override
	public boolean usuarioAplicaMauc(String numeroDocumento) {
		Usuario usuario = manejadorUsuario.buscar(numeroDocumento);
		if(usuario.getPersona().getTipoFuncionario() != null && usuario.getPersona().getTipoFuncionario().equals("CCB")) {
			logger.info("Es tipo funcionario ccb");
			return false;
		}else {
			if(usuario.getAplicaMauc()) {
				logger.info("APLICA MAUC");
				return true;
			}
			
			return false;
		}
	}
		
	public String validaMetodoAutenticacionUsuario(AutenticacionUsuarioDTO autenticacionUsuarioDTO) {

		Map<String, String> valoresReemplazar = new HashMap<String, String>();
		ParametrosGenerales parametrosGeneralesURLRetorno = new ParametrosGenerales();

		String codigoSistemaExternoTipoDocumento = manejadorHomologacionSistemaExterno.consultarCodigosSistemaExterno(
				UtilConstantes.SISTEMA_EXTERNO_MAUC, autenticacionUsuarioDTO.getTipoDocumento(),
				UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA);

		Persona persona = manejadorPersona.consultarPersonaPorIdentificacion(autenticacionUsuarioDTO.getTipoDocumento(),
				autenticacionUsuarioDTO.getNumeroDocumento());

		if (persona == null) {
			throw new SimascException(UtilConstantes.PERSONA_NO_EXISTE);
		}

		Usuario usuario = manejadorUsuario.consultarUsuarioPorPersonaEstado(persona.getIdPersona(),
				UtilDominios.ESTADO_PERSONA_ACTIVO);

		if (!persona.getTipoFuncionario().equals(UtilDominios.PERSONA_FUNCIONARIO_INTERNO)
				&& codigoSistemaExternoTipoDocumento != null && !codigoSistemaExternoTipoDocumento.isEmpty()
				&& usuario.getAplicaMauc()) {

			parametrosGeneralesURLRetorno = manejadorParametrosGenerales
					.obtenerParametroPorNombre(UtilConstantes.URL_ACCESO_MAUC);

			ParametrosGenerales parametrosGeneralesCodigoPlataforma = manejadorParametrosGenerales
					.obtenerParametroPorNombre(UtilConstantes.CODIGO_PLATAFORMA_MAUC);

			valoresReemplazar.put(UtilConstantes.PARAMETRO_PLATAFORMA,
					parametrosGeneralesCodigoPlataforma.getValorTexto());
			valoresReemplazar.put(UtilConstantes.PARAMETRO_TIPO_DOCUMENTO, codigoSistemaExternoTipoDocumento);

		} else {
			parametrosGeneralesURLRetorno = manejadorParametrosGenerales
					.obtenerParametroPorNombre(UtilConstantes.URL_LOGIN_SIMASC);
		}

		valoresReemplazar.put(UtilConstantes.PARAMETRO_NUMERO_DOCUMENTO, autenticacionUsuarioDTO.getNumeroDocumento());

		return UtilSimasc.reemplazarCadenasTexto(valoresReemplazar, parametrosGeneralesURLRetorno.getValorTexto());
	}

	@Override
	public String autenticarUsuarioMauc(String tokenMauc) {
		
		String[] tokenMaucObject = tokenMauc.split("\\."); 
		String payload = "";
		
		if(tokenMaucObject.length > 1) {
			payload = UtilSimasc.decodificarBase64Cadena(tokenMaucObject[1]);
		}
		 		
		String jsonUsuario = "";
		try {
			jsonUsuario = UtilSimasc.obtenerAtributoStringJSON(UtilConstantes.ATRIBUTO_JSON_TOKEN_MAUC, payload);
		} catch (Exception e) {
			throw new SimascException(UtilConstantes.TOKEN_INVALIDO);
		} 
		
		String tipoDocumentoMauc = jsonUsuario.substring(0, 1);
		String numeroDocumento = jsonUsuario.substring(1);
				
		String tipoDocumentoSimasc = manejadorHomologacionSistemaExterno.consultarCodigosSimasc(UtilConstantes.SISTEMA_EXTERNO_MAUC, tipoDocumentoMauc, UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA);
		
		Persona persona = manejadorPersona.consultarPersonaPorIdentificacion(tipoDocumentoSimasc,
				numeroDocumento);

		if (persona == null) {
			throw new SimascException(UtilConstantes.SOLICITANTE_NO_EXISTE);
		}
		
		AutenticacionMaucDTO autenticacionMAUC = new AutenticacionMaucDTO();
		
		autenticacionMAUC.setIssuer(numeroDocumento);
		autenticacionMAUC.setIdpersona(persona.getIdPersona().toString());
		autenticacionMAUC.setNombrepersona(persona.getNombreCompleto());
		autenticacionMAUC.setTtlMillis(UtilConstantes.TIEMPO_VIDA_JWT);
		autenticacionMAUC.setExpired(false);
		autenticacionMAUC.setMaucToken(tokenMauc);

		return createJWTMAUC(autenticacionMAUC);					
		
	}
	
	@Override
	public UsuarioSesionDTO obtenerUsuario(ContextoDeSesion ctx) {
		
		UsuarioSesionDTO usuarioSesion = new UsuarioSesionDTO();
		Usuario usuario = manejadorUsuario.buscar(ctx.getIdUsuario());
		Persona persona = manejadorPersona.buscar(usuario.getIdPersona());
		
		usuarioSesion.setIdPersona(usuario.getIdPersona());
		usuarioSesion.setIss(usuario.getUsuarioLogin());
		usuarioSesion.setNombrePersona(persona.getNombreCompleto());

		return usuarioSesion;
	}
	
	@Override
	public String obtenerExpToken(String jwt){
		String[] tokenObject = jwt.split("\\."); 
		String payload = "";
		
		if(tokenObject.length > 1) {
			payload = UtilSimasc.decodificarBase64Cadena(tokenObject[1]);
		}			 		
		String jsonExp = "";
		try {
			jsonExp = UtilSimasc.obtenerAtributoStringJSON("exp", payload);
		} catch (Exception e) {
			throw new SimascException(e.getMessage());
		} 
		
		String exp = jsonExp+"000";
		return exp;
	}
}
