package com.ccb.simasc.integracion.manejadores.utilidades;

import java.util.ArrayList;
import java.util.List;

import com.ccb.simasc.integracion.enumeraciones.TipoOrdenamiento;

public class InformacionOrdenamiento {	
	
	public final TipoOrdenamiento tipo;
	public final String campo;
	
	public InformacionOrdenamiento(TipoOrdenamiento tipo, String campo) {
		this.tipo = tipo;
		this.campo = campo;
	}        
	
	/**
	 * Crea un ordenamiento y lo devuelve como una lista de ordenamientos.
	 * @param tipo
	 * @param campo
	 * @return
	 */
	public static List<InformacionOrdenamiento> newInformacionOrdenamientoList(TipoOrdenamiento tipo, String campo){
		List<InformacionOrdenamiento> ordenamientos = new ArrayList<>();
		ordenamientos.add(new InformacionOrdenamiento(tipo, campo));
		
		return ordenamientos;
	}

}
