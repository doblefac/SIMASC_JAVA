package com.ccb.simasc.transversal.utilidades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class variablesEstaticasCarta {

	
	
	
	public static Map<String, String> getInformacionEstaticaPlantilla(Long idCaso) {
		Date fechaActual = new Date();		 
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaActual);
		int anio = cal.get(Calendar.YEAR);
		String mes = cal.getDisplayName(Calendar.MONTH, Calendar.LONG,
				new Locale(UtilConstantes.LOCALE_IDIOMA_ESPANIOL, UtilConstantes.LOCALE_PAIS_COLOMBIA));
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		String hora = dateFormat.format(cal.getTime());
		Map<String, String> mapaVariablesEstaticas;
		mapaVariablesEstaticas = new HashMap<>();
		mapaVariablesEstaticas.put("dia_cartaP", String.valueOf(dia));
		mapaVariablesEstaticas.put("mes_cartaP", String.valueOf(mes));
		mapaVariablesEstaticas.put("ano_cartaP", String.valueOf(anio));
		mapaVariablesEstaticas.put("hora_cartaP", hora);
		if (idCaso != null)
			mapaVariablesEstaticas.put(UtilConstantes.VALOR_PLANTILLA_CODIGO_CASO, String.valueOf(idCaso));
		
		return mapaVariablesEstaticas;
	}

}
