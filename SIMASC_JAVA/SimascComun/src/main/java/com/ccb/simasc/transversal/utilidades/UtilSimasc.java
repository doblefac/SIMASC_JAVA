package com.ccb.simasc.transversal.utilidades;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Decoder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.ccb.simasc.transversal.entidades.Persona;

/**
 * Clase utilitaria con metodos utilitarios transversales al sistema
 * @author lvalbuena
 *
 */
public class UtilSimasc {
	
	/**
	 * Transforma el día que devuelve la clase Calendar a su correspondiente
	 * representación en el dominio de DIAS_SORTEOS. Ej. Calendar.SUNDAY
	 * devuelve corresponde al entero 1 y lo convierte al dominio
	 * DIAS_SORTEOS_DOMINGO
	 */
	public static String convertirDiaCalendarADiaDominio(int diaCalendario) {
		String dominioDia = "";
		switch (diaCalendario) {
			case Calendar.SUNDAY:
				dominioDia = UtilDominios.DIAS_SORTEOS_DOMINGO;
				break;
			case Calendar.MONDAY:
				dominioDia = UtilDominios.DIAS_SORTEOS_LUNES;
				break;
			case Calendar.TUESDAY:
				dominioDia = UtilDominios.DIAS_SORTEOS_MARTES;
				break;
			case Calendar.WEDNESDAY:
				dominioDia = UtilDominios.DIAS_SORTEOS_MIERCOLES;
				break;
			case Calendar.THURSDAY:
				dominioDia = UtilDominios.DIAS_SORTEOS_JUEVES;
				break;
			case Calendar.FRIDAY:
				dominioDia = UtilDominios.DIAS_SORTEOS_VIERNES;
				break;
			case Calendar.SATURDAY:
				dominioDia = UtilDominios.DIAS_SORTEOS_SABADO;
				break;
			
		}
		return dominioDia;
	}
	
	
	/**
     * Metodo que recibe el nombre del reporte y entrega dicho nombre con el formato
     * definido por la CCB
     * @param String nombreReporte
     * @return String nombreReporte con formato
     */
    public static String generarNombreReporte(String nombreReporte){
		Date fechaReporte = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("ddMMyyyyHHmmss");
		nombreReporte = nombreReporte+formateador.format(fechaReporte);
		return nombreReporte;
	}
    
    /**
     * Metodo que recibe la fecha inicial y fecha final validando que la fecha inicio sea 
     * menor que la fecha fin
     * @param Date fechaInicial, fechaFinal
     * @return boolean
     */
    public static boolean validarFechas(Date fechaInicial, Date fechaFinal){
		boolean isFechasValidas = false;
        if(fechaInicial!=null && fechaFinal!=null){
			if(fechaInicial.after(fechaFinal))
				isFechasValidas = false;
			else
				isFechasValidas = true;
		}
        return isFechasValidas;
	}
    
    /**
     * Calcula la lista basado en el tipo de cuantia
     * @param tipoCuantia
     * @return
     */
    public static String obtenerListaPorTipoCuantia(String tipoCuantia) {
    	String lista = null;
    	
    	switch (tipoCuantia) {
			case UtilDominios.TIPO_CUANTIA_MAYOR :
				lista = UtilDominios.TIPO_LISTA_A;
				break;
			case UtilDominios.TIPO_CUANTIA_MENOR :
				lista = UtilDominios.TIPO_LISTA_B;
				break;
			default :
				lista = UtilDominios.TIPO_LISTA_A;
    	}
    	return lista;
    }
    
    /**
     * Convierte fecha tipo Date a Calendar
     * @param date
     * @return
     */
    public static Calendar toCalendar(Date date) {    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	return cal;
    }
    
    /**
     * Valida si el string pasado es un numero
     * @param valor
     * @return
     */
    public static boolean esNumero(String valor) {
    	boolean esNumero = true;    	
    	try {
    		double numero = Double.parseDouble(valor);
    		
    	} catch(NumberFormatException ex)  {
    		esNumero = false;
    	}    	
    	return esNumero;    	
    }
    
    /**
     * Agrega dias a la fecha pasada por parametro
     * @param fecha a la cual se le van a agregar dias
     * @param dias cantidad de dias a agregar
     * @return
     */
    public static Date agregarDiasAFecha(Date fecha, int dias) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(fecha);
    	c.add(Calendar.DATE, dias);
    	return c.getTime();    	    
    }
    
    
    /**
     * Realiza ajuste al formato de fecha para utilizar en querys de reportes
     * @param fecha fecha a formatear
     * @param formatoHora hora de formato, inicio del dia o final del dia
     * @return fecha formateada lista para usar en un query
     */
    public static String ajustarFechaParaReporte(Date fecha, String formatoHora) {
    	DateFormat dateFormat = new SimpleDateFormat(UtilConstantes.FORMATO_FECHA_ANIO_MES_DIA);
    	String fechaFormato = dateFormat.format(fecha);
    	return fechaFormato + UtilConstantes.CARACTER_ESPACIO + formatoHora;
    }
    
    public static String listaToString(List<?> lista) {
    	
    	String cadena = "";
    	for(Object objetoLista: lista) {    		
    		cadena += objetoLista.toString() + ",";    		
    	}
    	
    	return cadena.substring(0, cadena.length()-1);
    	
    }
    
	
    
    /**
     * Realiza la validacion de los siguientes atributos que son obligatorios para crear un usuario
     * en MAUC.
     * @param Persona
     * @return true si cumple con la validación y false si no cumple;
     */
    public static boolean validarUsuarioCrearEnMauc(Persona persona) {
    	if(persona.getTipoDocumento() != null && persona.getNumeroDocumento() != null 
    			 && persona.getPrimerNombreORazonSocial() != null && persona.getPrimerApellido() != null
    			 && !persona.getCorreoElectronicoList().isEmpty()  && !persona.getTelefonoList().isEmpty()
    			 ) {
    		return true;
    	}
    	return false;
}
    
    public static String reemplazarCadenasTexto(Map<String, String> valoresReemplazar, String cadena ) {
    	
    	String cadenaFinal = cadena;
    	
    	for (Map.Entry<String, String> valorReemplazar : valoresReemplazar.entrySet()) {    		
    		cadenaFinal = cadenaFinal.replace(valorReemplazar.getKey(), valorReemplazar.getValue());            
        }
    	
    	return cadenaFinal;
    	
    }
    
    public static String decodificarBase64Cadena(String base64String) {
    	
    	Decoder decoder = Base64.getDecoder();    	    	
    	return new String(decoder.decode(base64String));
    	
    }
    
    public static String obtenerAtributoStringJSON(String atributo, String json) throws JsonProcessingException, IOException {
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	JsonNode jsonNode = objectMapper.readTree(json);    	    	
    	return jsonNode.get(atributo).asText();
    }
    
    public static String reemplazaTextosCadena(Map<String, String> valoresReemplazar, String cadena) {
    	
    	for (Map.Entry<String, String> valorReemplazar : valoresReemplazar.entrySet()) {
    		cadena = cadena.replace(valorReemplazar.getKey(), valorReemplazar.getValue());
    	}
    	return cadena;
    }
    
}
