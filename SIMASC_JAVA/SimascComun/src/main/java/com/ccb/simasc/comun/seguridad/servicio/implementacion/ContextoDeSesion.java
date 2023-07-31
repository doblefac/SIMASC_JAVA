package com.ccb.simasc.comun.seguridad.servicio.implementacion;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ContextoDeSesion {
	
	private String idUsuario;
	private String rolPrincipal;
	private String rolSecundario;
	private String nombreUsuario;
	
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getRolPrincipal() {
		return rolPrincipal;
	}
	public void setRolPrincipal(String rolPrincipal) {
		this.rolPrincipal = rolPrincipal;
	}
	public String getRolSecundario() {
		return rolSecundario;
	}
	public void setRolSecundario(String rolSecundario) {
		this.rolSecundario = rolSecundario;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	private static ContextoDeSesion lecturaDeContextoPorEncabezado(String encabezado){
		return (ContextoDeSesion) mapearValorEncabezado(encabezado, ContextoDeSesion.class);
	}
	/**
	 * Obtiene el valor del encabezado http
	 * @param headers Encabezados http
	 * @param headerKey llave de encabezado
	 * @return
	 */
	private static String obtenerValorEncabezado(HttpHeaders headers, String headerKey){
		try{
			return headers.getRequestHeaders().getFirst(headerKey);
		}catch(Exception e){
			return "{}";
		}
	}
	
	/**
	 * Obtiene el valor del encabezado http
	 * @param headers Encabezados http
	 * @param headerKey llave de encabezado
	 * @return
	 */
	private static String obtenerValorEncabezado(HttpServletRequest headers, String headerKey){
		try{
			return headers.getHeader(headerKey);
		}catch(Exception e){
			return "{}";
		}
	}
	/**
	 * Obtiene el contexto de sesion dados los encabezados de la petición
	 * @param headers
	 * @param headerKey
	 * @return
	 */
	public static ContextoDeSesion obtenerContextoDeSesion(HttpHeaders headers){
		return lecturaDeContextoPorEncabezado(obtenerValorEncabezado(headers, UtilConstantes.HEADER_SESSION_CONTEXT));
	}
	
	/**
	 * Obtiene el contexto de sesion dados los encabezados de la petición
	 * @param headers
	 * @param headerKey
	 * @return
	 */
	public static ContextoDeSesion obtenerContextoDeSesion(HttpServletRequest headers){
		return lecturaDeContextoPorEncabezado(obtenerValorEncabezado(headers, UtilConstantes.HEADER_SESSION_CONTEXT));
	}	
	
	/**
	 * Mapea el valor del encabezado dada su definición de clase
	 * @param <T>
	 * @param encabezado
	 * @param c
	 * @return
	 */
	public static <T> Object mapearValorEncabezado(String encabezado, Class<T> c){
		Object cs = new Object();
		try{
			ObjectMapper om = new ObjectMapper();
			cs = om.readValue(encabezado, c);
		}catch(Exception ex){}
		return cs;
	}
	
	
}
