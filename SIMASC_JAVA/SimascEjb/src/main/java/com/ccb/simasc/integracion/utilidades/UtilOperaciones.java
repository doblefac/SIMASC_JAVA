package com.ccb.simasc.integracion.utilidades;

import java.util.ArrayList;
import java.util.List;

import com.ccb.simasc.transversal.entidades.Persona;

/**
 * Utilidades de conversión entre objetos o listas.
 * 
 * @author jsoto
 */
public class UtilOperaciones {

	private UtilOperaciones() {
	}

	/**
	 * Convierte una lista de objetos a una lista de su representación en String
	 * 
	 * @param objetos Lista de objetos a convertir a String
	 * @return Lista de cadena de caracteres. La lista es vacia si la lista del
	 *         parametro no contiene objetos o es nula.
	 */
	public static List<String> convertirListaObjetosAString(List<Object> objetos) {
		List<String> lista = new ArrayList<>();
		if (objetos != null && !objetos.isEmpty()) {
			for (Object objeto : objetos) {
				lista.add(objeto.toString());
			}
		}

		return lista;

	}

	public static String getNombreCompleto(Persona persona) {

		String nombreCompleto = "";
		if (persona.getNombreCompleto() != null && !persona.getNombreCompleto().isEmpty()) {
			return persona.getNombreCompleto();
		} else {
			if (persona.getPrimerNombreORazonSocial() != null && !persona.getPrimerNombreORazonSocial().isEmpty()) {
				nombreCompleto += persona.getPrimerNombreORazonSocial();
			}
			if (persona.getSegundoNombre() != null && !persona.getSegundoNombre().isEmpty()) {
				nombreCompleto += " " + persona.getSegundoNombre();
			}
			if (persona.getPrimerApellido() != null && !persona.getPrimerApellido().isEmpty()) {
				nombreCompleto += " " + persona.getPrimerApellido();
			}
			if (persona.getSegundoApellido() != null && !persona.getSegundoApellido().isEmpty()) {
				nombreCompleto += " " +persona.getSegundoApellido();
			}
			return nombreCompleto;
		}

	}
}
