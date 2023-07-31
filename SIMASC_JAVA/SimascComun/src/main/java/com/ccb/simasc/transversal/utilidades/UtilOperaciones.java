package com.ccb.simasc.transversal.utilidades;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.http.HTTPException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.ccb.simasc.transversal.dto.FormatoHoraAudienciaDTO;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;

/**
 * Utilidades de conversión entre objetos o listas.
 * 
 * @author jsoto
 */
public class UtilOperaciones {
	private static final Logger logger = LogManager.getLogger(UtilOperaciones.class.getName());
	final static int MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; // Milisegundos al
																// día

	private UtilOperaciones() {
	}

	/**
	 * Adiciona a la fecha recibida por par�metro los minutos recibidos por
	 * parametro y retorna el resultado
	 * 
	 * @param date
	 * @author Javier Estévez
	 * @return
	 */
	public static Date adicionarMinutos(Date date, int minutos) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutos);
		return cal.getTime();
	}

	/**
	 * Remueve las horas, minutos, segundos y milisegundos de la fecha recibida
	 * por parámetro
	 * 
	 * @param date
	 * @author Javier Estévez
	 * @return
	 */
	public static Date removerHora(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Crea una SIMASCNegocioExcepcion con el mensaje cuyo identificador se
	 * recibe por parámetro
	 * 
	 * @param idMensaje
	 *            identificador del mensaje
	 * @author Javier Estévez
	 * @return
	 */
	public static SIMASCNegocioExcepcion crearSIMASCNegocioExcepcion(String idMensaje) {

		return new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(idMensaje));
	}

	/**
	 * Crea una SIMASCNegocioExcepcion con el mensaje cuyo identificador se
	 * recibe por parámetro, incluyendo en el mensaje los parámetros recibidos
	 * 
	 * @param idMensaje
	 *            identificador del mensaje
	 * @return
	 */
	public static SIMASCNegocioExcepcion crearSIMASCNegocioExcepcion(String idMensaje, Object[] parametros) {
		return new SIMASCNegocioExcepcion(
				String.format(MensajesSimasc.getInstancia().getMensajePorKey(idMensaje), parametros));
	}

	/**
	 * Convierte una lista de objetos a una lista de su representación en String
	 * 
	 * @param objetos
	 *            Lista de objetos a convertir a String
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

	/**
	 * Copia el valor que contienen los atributos del objeto fuente a los
	 * atributos del objeto destino cuyos nombres sean exactamente iguales. Los
	 * atributos que no coinciden se omiten (Se dejan tal cual como estaban en
	 * el objeto destino).
	 * 
	 * @param destino
	 *            objeto al que se le van a setear los valores de sus atributos
	 * @param fuente
	 *            objeto del que se copian los valores de los atributos
	 */
	protected void copiarPropiedades(Object destino, Object fuente, Logger logger) {

		try {
			BeanUtils.copyProperties(destino, fuente);
		} catch (IllegalAccessException | InvocationTargetException ex) {
			logger.error(ex);
			throw new HTTPException(500);
		}

	}

	/**
	 * Setea la fecha con el tiempo 0 del día, es decir, la hora 0, minuto 0,
	 * segundo 0, y milisegundo 0
	 * 
	 * @param calendar
	 *            Fecha a modificar
	 * @return La fecha modificada
	 */
	public static Calendar setearInicioDelDia(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar;
	}

	/**
	 * evalua un objeto y en caso de que sea null devuleve el segundo objeto
	 * pasado por parametro.
	 * 
	 * @param objeto
	 *            a ser evaluado
	 * @param objeto2
	 *            objeto que se devuelve en caso de que el objeto sea null
	 */
	public static Object nvl(Object objeto, Object objeto2) {
		return (objeto == null ? objeto2 : objeto);
	}

	/**
	 * Setea la fecha con el ultimo momento del día, es decir, la hora 23,
	 * minuto 59, segundo 59, y milisegundo 9999
	 * 
	 * @param calendar
	 *            Fecha a modificar
	 * @return La fecha modificada
	 */
	public static Calendar setearFinDelDia(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		return calendar;
	}

	/**
	 * Devuelve la misma fecha (año, mes, dia) pero con el tiempo inicial del
	 * día (hora 0, minuto 0, segundo 0, milisegundo 0)
	 * 
	 * @param fecha
	 *            Fecha a modificar
	 * @return La fecha modificada
	 */
	public static Date obtenerFechaComienzoDelDia(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(fecha.getTime());
		calendar = setearInicioDelDia(calendar);
		return calendar.getTime();
	}

	/**
	 * Devuelve la misma fecha (año, mes, dia) pero con el tiempo final del día
	 * (hora 23, minuto 59, segundo 59, milisegundo 999)
	 * 
	 * @param fecha
	 *            Fecha a modificar
	 * @return La fecha modificada
	 */
	public static Date obtenerFechaFinDelDia(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(fecha.getTime());
		calendar = setearFinDelDia(calendar);
		return calendar.getTime();
	}

	/**
	 * Parsea la fecha a un string con formato yyyy-MM-dd HH:mm:ss.SSS que es la
	 * representacion en base de datos del tipo datetime
	 * 
	 * @param fecha
	 *            Fecha a parsear
	 * @return La fecha del parametro parseada
	 */
	public static String formatearFechaConsulta(Date fecha) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		return format.format(fecha);
	}

	public static String formatearFechaHora(Date fecha) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		return format.format(fecha);
	}

	/**
	 * Parsea la fecha a un string con formato yyyy/M/dd que es el estandar de
	 * las fechas en los reportes
	 * 
	 * @param fecha
	 *            Fecha a parsear
	 * @return La fecha del parametro parseada
	 */
	public static String formatearFechaReporte(Date fecha) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/M/dd");
		return format.format(fecha);
	}

	/**
	 * Parsea la fecha a un string con el formato especificado como parámetro
	 * 
	 * @param fecha
	 * @param formato
	 * @return
	 */
	public static String formatearFechaFormato(Date fecha, String formato) {
		SimpleDateFormat format = new SimpleDateFormat(formato);
		return format.format(fecha);
	}

	/**
	 * Obtiene una fecha a partir de una cadena indicando el formato especifico
	 * 
	 * @param fechaCadena
	 * @param formato
	 * @return
	 * @throws ParseException
	 */
	public static Date parsearFechaFormato(String fechaCadena, String formato) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(formato);
		return format.parse(fechaCadena);
	}

	/**
	 * Valida que las dos fechas correspondan al mismo día (sin tener en cuenta
	 * la hora)
	 * 
	 * @param fecha1
	 * @param fecha2
	 * @return
	 */
	public static boolean validarFechasDelMismoDia(Date fecha1, Date fecha2) {
		boolean iguales = false;
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(fecha1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(fecha2);

		if (validarIgualdadParametroCalendar(calendar1, calendar2, Calendar.YEAR)
				&& (validarIgualdadParametroCalendar(calendar1, calendar2, Calendar.MONTH))
				&& (validarIgualdadParametroCalendar(calendar1, calendar2, Calendar.DAY_OF_MONTH))) {

			iguales = true;
		}

		return iguales;

	}

	/**
	 * Valida que los dos calendar que se pasan como parametro tengan el mismo
	 * valor en el parametroCalendar
	 * 
	 * @param calendar1
	 * @param calendar2
	 * @param parametroCalendar
	 *            Parametro del calendario Ej. Calendar.DAY_OF_MONTH o
	 *            Calendar.YEAR
	 * @return
	 */
	private static boolean validarIgualdadParametroCalendar(Calendar calendar1, Calendar calendar2,
			int parametroCalendar) {
		return calendar1.get(parametroCalendar) == calendar2.get(parametroCalendar);
	}

	/**
	 * Retorna el número de días habiles entre dos fechas incluyendo fecha
	 * inicial y final
	 * 
	 * @param fecha
	 *            Inicial
	 * @param fecha
	 *            final
	 * @return días hábiles entre dos fechas
	 */
	public static int obtenerDiasHabilesEntreFechas(Date fechaInicial, Date fechaFinal) {

		int anyo = fechaInicial.getYear();
		List<Timestamp> listadoDiasFestivos = obtenerDiasFestivos(anyo);
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(fechaInicial);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(fechaFinal);

		int diasHabiles = 0;

		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			return 0;
		}

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(fechaFinal);
			endCal.setTime(fechaInicial);
		}

		boolean isTrue;
		do {
			// Modificación hecha si el año de la fecha inicial es diferente al
			// año de la fecha final
			if ((startCal.get(Calendar.YEAR) - 1900) != anyo) {
				anyo = (startCal.get(Calendar.YEAR) - 1900);
				listadoDiasFestivos = obtenerDiasFestivos(anyo);
			}

			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
					&& startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				isTrue = false;
				for (Iterator iter = listadoDiasFestivos.iterator(); iter.hasNext();) {
					Timestamp diaFestivo = (Timestamp) iter.next();
					Timestamp date = new Timestamp(startCal.getTimeInMillis());
					if (date.compareTo(truncDate(diaFestivo)) == 0) {
						isTrue = true;
						break;
					}
				}
				if (!isTrue) {
					++diasHabiles;
				}
			}
			startCal.add(Calendar.DAY_OF_MONTH, 1);
		} while (startCal.getTimeInMillis() < endCal.getTimeInMillis() + 1);

		return diasHabiles;
	}

	private static List<Timestamp> obtenerDiasFestivos(int anio) {

		Timestamp pascua = calcularPascua(anio);
		pascua = SiguienteDiaSemana(Calendar.FRIDAY, pascua, true, true);

		List<Timestamp> diasFestivos = new ArrayList<Timestamp>();

		IncluirFecha(diasFestivos, new Timestamp(anio, Calendar.JANUARY, 1, 0, 0, 0, 0)); // Primero
																							// de
																							// Enero
		IncluirFecha(diasFestivos,
				SiguienteDiaSemana(Calendar.MONDAY, new Timestamp(anio, Calendar.JANUARY, 6, 0, 0, 0, 0), false, true)); // Reyes
																															// magos
		IncluirFecha(diasFestivos,
				SiguienteDiaSemana(Calendar.MONDAY, new Timestamp(anio, Calendar.MARCH, 19, 0, 0, 0, 0), false, true)); // San
																														// Jose
		IncluirFecha(diasFestivos, SiguienteDiaSemana(Calendar.SUNDAY,
				new Timestamp(pascua.getYear(), pascua.getMonth(), pascua.getDate(), 0, 0, 0, 0), true, false)); // Domingo
																													// de
																													// Ramos
		IncluirFecha(diasFestivos, SiguienteDiaSemana(Calendar.THURSDAY,
				new Timestamp(pascua.getYear(), pascua.getMonth(), pascua.getDate(), 0, 0, 0, 0), true, true)); // Jueves
																												// Santo
		IncluirFecha(diasFestivos, SiguienteDiaSemana(Calendar.FRIDAY,
				new Timestamp(pascua.getYear(), pascua.getMonth(), pascua.getDate(), 0, 0, 0, 0), true, true)); // Viernes
																												// Santo

		IncluirFecha(diasFestivos, new Timestamp(anio, Calendar.MAY, 1, 0, 0, 0, 0)); // día
																						// del
																						// trabajo

		IncluirFecha(diasFestivos,
				adicionarDiasFecha(SiguienteDiaSemana(Calendar.MONDAY,
						new Timestamp(pascua.getYear(), pascua.getMonth(), pascua.getDate(), 0, 0, 0, 0), false, true),
						42)); // Ascensión de Jesús
		IncluirFecha(diasFestivos,
				adicionarDiasFecha(SiguienteDiaSemana(Calendar.MONDAY,
						new Timestamp(pascua.getYear(), pascua.getMonth(), pascua.getDate(), 0, 0, 0, 0), false, true),
						63)); // Corpus Christi
		IncluirFecha(diasFestivos,
				adicionarDiasFecha(SiguienteDiaSemana(Calendar.MONDAY,
						new Timestamp(pascua.getYear(), pascua.getMonth(), pascua.getDate(), 0, 0, 0, 0), false, true),
						70)); // Sagrado Corazón

		IncluirFecha(diasFestivos,
				SiguienteDiaSemana(Calendar.MONDAY, new Timestamp(anio, Calendar.JUNE, 29, 0, 0, 0, 0), false, true)); // san
																														// Pedro
																														// y
																														// san
																														// Pablo
		IncluirFecha(diasFestivos, new Timestamp(anio, Calendar.JULY, 20, 0, 0, 0, 0)); // Grito
																						// de
																						// Independencia
		IncluirFecha(diasFestivos, new Timestamp(anio, Calendar.AUGUST, 7, 0, 0, 0, 0)); // Batalla
																							// de
																							// Boyacá
		IncluirFecha(diasFestivos,
				SiguienteDiaSemana(Calendar.MONDAY, new Timestamp(anio, Calendar.AUGUST, 15, 0, 0, 0, 0), false, true)); // Asuncion
																															// de
																															// la
																															// virgen
		IncluirFecha(diasFestivos, SiguienteDiaSemana(Calendar.MONDAY,
				new Timestamp(anio, Calendar.OCTOBER, 12, 0, 0, 0, 0), false, true)); // Día
																						// de
																						// la
																						// Raza
		IncluirFecha(diasFestivos, SiguienteDiaSemana(Calendar.MONDAY,
				new Timestamp(anio, Calendar.NOVEMBER, 1, 0, 0, 0, 0), false, true)); // Día
																						// de
																						// todos
																						// los
																						// santos
		IncluirFecha(diasFestivos, SiguienteDiaSemana(Calendar.MONDAY,
				new Timestamp(anio, Calendar.NOVEMBER, 11, 0, 0, 0, 0), false, true)); // Independencia
																						// de
																						// Cartagena
		IncluirFecha(diasFestivos, new Timestamp(anio, Calendar.DECEMBER, 8, 0, 0, 0, 0)); // Inmaculada
																							// Concepción
		IncluirFecha(diasFestivos, new Timestamp(anio, Calendar.DECEMBER, 25, 0, 0, 0, 0)); // Navidad

		return diasFestivos;
	}

	private static Timestamp calcularPascua(int Anio) {
		int Anio2 = Anio;
		Anio = Anio + 1900;
		int a, b, c, d, e;
		int m = 24, n = 5;

		if (Anio >= 1583 && Anio <= 1699) {
			m = 22;
			n = 2;
		} else if (Anio >= 1700 && Anio <= 1799) {
			m = 23;
			n = 3;
		} else if (Anio >= 1800 && Anio <= 1899) {
			m = 23;
			n = 4;
		} else if (Anio >= 1900 && Anio <= 2099) {
			m = 24;
			n = 5;
		} else if (Anio >= 2100 && Anio <= 2199) {
			m = 24;
			n = 6;
		} else if (Anio >= 2200 && Anio <= 2299) {
			m = 25;
			n = 0;
		}

		a = Anio % 19;
		b = Anio % 4;
		c = Anio % 7;
		d = ((a * 19) + m) % 30;
		e = ((2 * b) + (4 * c) + (6 * d) + n) % 7;

		int dia = d + e;

		if (dia < 10) // Marzo
			return new Timestamp(Anio2, Calendar.MARCH, dia + 22, 0, 0, 0, 0);
		else // Abril
		{

			if (dia == 26)
				dia = 19;
			else if (dia == 25 && d == 28 && e == 6 && a > 10)
				dia = 18;
			else
				dia -= 9;

			return new Timestamp(Anio2, Calendar.APRIL, dia, 0, 0, 0, 0);
		}
	}

	private static Timestamp SiguienteDiaSemana(int DiaSemana, Timestamp fecha, boolean haciaAtras, boolean inclusive) {

		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.setTime(fecha);

		if (inclusive) {
			if (fechaCalendar.get(Calendar.DAY_OF_WEEK) == DiaSemana) {
				return fecha;
			}
		} else {
			if (haciaAtras)
				fecha = adicionarDiasFecha(fecha, -1);
			else
				fecha = adicionarDiasFecha(fecha, 1);
			fechaCalendar.setTime(fecha);
		}

		while (fechaCalendar.get(Calendar.DAY_OF_WEEK) != DiaSemana) {
			if (haciaAtras)
				fecha = adicionarDiasFecha(fecha, -1);
			else
				fecha = adicionarDiasFecha(fecha, 1);
			fechaCalendar.setTime(fecha);
		}

		return fecha;
	}

	private static Timestamp adicionarDiasFecha(Timestamp fecha, int dias) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(fecha.getTime());
		c.add(Calendar.DATE, dias);
		fecha.setTime(c.getTimeInMillis());
		return fecha;
	}

	private static void IncluirFecha(List<Timestamp> listaDias, Timestamp fecha) {
		if (listaDias.contains(fecha) == false)
			listaDias.add(fecha);
	}

	public static Date truncDate(Date t) {
		Date nuevaFecha = null;
		if (t != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			try {
				nuevaFecha = dateFormat.parse(dateFormat.format(t));
			} catch (ParseException e) { }
		}
		
		return nuevaFecha;

	}

	/**
	 * Calcula el número de días entre dos fechas
	 * 
	 * @param calendar1
	 * @param calendar2
	 * @param parametroCalendar
	 *            Parametro del calendario Ej. Calendar.DAY_OF_MONTH o
	 *            Calendar.YEAR
	 * @return
	 */
	public static Long calcularDiasEntreFechas(Date fechaInicial, Date fechaFinal) {
		return (fechaFinal.getTime() - fechaInicial.getTime()) / MILLSECS_PER_DAY;
	}

	/**
	 * Calcula el número de días entre dos fechas
	 * 
	 * @param calendar1
	 * @param calendar2
	 * @param parametroCalendar
	 *            Parametro del calendario Ej. Calendar.DAY_OF_MONTH o
	 *            Calendar.YEAR
	 * @return
	 */
	public static int calcularDiasEntreFechasI(Date fechaInicial, Date fechaFinal) {
		Long dias = (fechaFinal.getTime() - fechaInicial.getTime()) / MILLSECS_PER_DAY;
		return Integer.parseInt(dias.toString());
	}
	
	/**
	 * Calcula el número de días entre dos fechas redondeando el resultado
	 * haciendo uso de la función techo
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	public static int calcularDiasEntreFechasII(Date fechaInicial, Date fechaFinal) {
		BigDecimal milisegundosPorDia = new BigDecimal(MILLSECS_PER_DAY);
		BigDecimal dias = new BigDecimal(fechaFinal.getTime() - fechaInicial.getTime()).divide(milisegundosPorDia,
				RoundingMode.CEILING);
		return Integer.parseInt(dias.toString());
	}

	/**
	 * Calcula la cantidad de dias transcurrido entre la fecha de inicio y fin teniendo en cuenta
	 * el dia de la fecha inicial
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	public static int calcularDiasEntreFechasLey(Date fechaInicial, Date fechaFinal) {
		Date inicioDia = UtilOperaciones.obtenerFechaComienzoDelDia(fechaInicial);
		Date finDia = UtilOperaciones.obtenerFechaFinDelDia(fechaFinal);
		int dias  = 0;
		while(inicioDia.before(finDia)) {
			dias += 1;
			inicioDia = UtilOperaciones.adicionarDiasFecha(inicioDia, 1);
		}
		return dias;
	}
	
	public static Date adicionarMesesFecha(Date fecha, int meses) {
		Calendar c = Calendar.getInstance();
		Date dateNew = new Date();
		c.setTime(fecha);
		c.add(Calendar.MONTH, meses);
		dateNew.setTime(c.getTimeInMillis());
		return dateNew;
	}

	public static Date adicionarDiasFecha(Date fecha, int dias) {
		Calendar c = Calendar.getInstance();
		Date dateNew = new Date();
		c.setTimeInMillis(fecha.getTime());
		c.add(Calendar.DATE, dias);
		dateNew.setTime(c.getTimeInMillis());
		return dateNew;
	}

	public static Date adicionarAnosFecha(Date fecha, int anos) {
		Calendar c = Calendar.getInstance();
		Date dateNew = new Date();
		c.setTimeInMillis(fecha.getTime());
		c.add(Calendar.YEAR, anos);
		dateNew.setTime(c.getTimeInMillis());
		return dateNew;
	}
	
	/**
	 * Retorna el número de días habiles entre dos fechas excluyendo fecha
	 * inicial y final
	 * 
	 * @param fecha
	 *            Inicial
	 * @param fecha
	 *            final
	 * @return días hábiles entre dos fechas
	 */
	public static Date adicionarDiasHabilesFecha(Date fechaInicial, int dias) {

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(fechaInicial);
		int anyo = startCal.get(Calendar.YEAR) - 1900;
		List listadoDiasFestivos = obtenerDiasFestivos(anyo);
		int diasHabiles = 0;

		boolean isWorking;
		while (dias > diasHabiles) {
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			if ((startCal.get(Calendar.YEAR) - 1900) != anyo) {
				anyo = startCal.get(Calendar.YEAR) - 1900;
				listadoDiasFestivos = obtenerDiasFestivos(anyo);
			}

			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
					&& startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				isWorking = true;
				for (Iterator iter = listadoDiasFestivos.iterator(); iter.hasNext();) {
					Timestamp diaFestivo = (Timestamp) iter.next();
					Timestamp date = new Timestamp(startCal.getTimeInMillis());
					if (date.compareTo(truncDate(diaFestivo)) == 0) {
						isWorking = false;
						break;
					}
				}
				if (isWorking) {
					diasHabiles++;
				}
			}
		}

		return startCal.getTime();
	}

	public static XMLGregorianCalendar covertirFechaXMLGregorianCalendar(Date fecha) {
		GregorianCalendar c = new GregorianCalendar();
		XMLGregorianCalendar fechaResultado = null;
		try {
			c.setTime(fecha);
			fechaResultado = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			logger.error("Error: ", e);
		}
		return fechaResultado;
	}

	public static String nombresConComas(List<String> personas) {
		StringBuilder nombres = new StringBuilder("");
		for (String nombreFor : personas) {
			if (personas.size() > 1) {
				nombres.append(nombreFor).append(", ");
			} else if (personas.size() == 1) {
				nombres.append(nombreFor).append(". ");
			}

		}
		return nombres.toString();

	}

	/**
	 * retorna los nombres de la lista separados con comas
	 * 
	 * @param listaNombres
	 * @param puntoFinal
	 *            si al final de la cadena se desea un punto final
	 * @return
	 */
	public static String nombreConComasYPunto(List<String> listaNombres, boolean puntoFinal) {
		StringBuilder nombre = new StringBuilder();
		for (int i = 0; i < listaNombres.size(); i++) {
			if (listaNombres.size() - i != 1) {
				nombre.append(listaNombres.get(i)).append(", ");
			} else if (listaNombres.size() - i == 1 && puntoFinal) {
				nombre.append(listaNombres.get(i)).append(". ");
			} else {
				nombre.append(listaNombres.get(i));
			}
		}
		return nombre.toString();
	}

	/**
	 * Utilidad encargada de dar formato a un número decimal
	 * 
	 * @param numero
	 * @param posiciones
	 * @return
	 */
	public static Double formatearNumeroDecimal(Double numero, Integer posiciones) {
		Double factor = Math.pow(10, posiciones);
		numero *= factor;
		Long tmp = Math.round(numero);
		return (Double) (tmp / factor);
	}

	/**
	 * Obtiene el código de un dominio validando su nulidad
	 * 
	 * @param dominio
	 * @return código del dominio
	 */
	public static String obtenerCodigoDominio(Dominio dominio) {
		return (dominio != null ? dominio.obtenerCodigoDominio() : null);
	}

	/**
	 * Validate given email with regular expression.
	 * 
	 * @param email
	 *            email for validation
	 * @return true valid email, otherwise false
	 */
	public static boolean validateEmail(String email) {

		// Compiles the given regular expression into a pattern.
		Pattern pattern = Pattern.compile(UtilConstantes.PATTERN_EMAIL);

		// Match the given input against this pattern
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();

	}

	public static List<Date> obtenerHorasDia(Date hora) {
		List<Date> horasDia = new ArrayList<>();
		double salto = 0.5;
		for (double i = 0; i < 24; i = i + salto) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(hora);
			if (i % 1 == 0) {// si el numero es entero
				cal.set(Calendar.HOUR_OF_DAY, (int) i);
				cal.set(Calendar.MINUTE, 0);
			} else {
				cal.set(Calendar.HOUR_OF_DAY, (int) (i - (i % 1)));
				cal.set(Calendar.MINUTE, (int) ((i % 1) * 60));
			}
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			horasDia.add(cal.getTime());
		}
		return horasDia;
	}

	public static List<FormatoHoraAudienciaDTO> horasAudiencia(String codigoDominio, Date inicio, Date fin) {
		List<Date> horasDia = obtenerHorasDia(inicio);
		List<FormatoHoraAudienciaDTO> retorno = new ArrayList<>();
		int tiempoSeparacion = tiempoDeSeparacion(codigoDominio);
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(inicio);
		cal.setTime(inicio);
		cal.set(Calendar.HOUR_OF_DAY, cal2.get(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date finicio = cal.getTime();

		cal2.setTime(fin);
		cal.set(Calendar.HOUR_OF_DAY, cal2.get(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date ffin = cal.getTime();
		for (int n = 0; n < horasDia.size(); n++) {
			Date hora = horasDia.get(n);
			if (hora.compareTo(finicio) >= 0 && hora.compareTo(ffin) <= 0) {
				Date horaFinal = horasDia.get(n + tiempoSeparacion);
				if (horaFinal.compareTo(finicio) >= 0 && horaFinal.compareTo(ffin) <= 0) {
					FormatoHoraAudienciaDTO r = new FormatoHoraAudienciaDTO();
					String key = formatoHoraString(hora, horaFinal);
					r.setFechaInicio(hora);
					r.setFechaFin(horaFinal);
					r.setFormatoFechas(key);
					retorno.add(r);
				}
			}
		}
		return retorno;
	}

	public static String formatoHoraString(Date inicio, Date fin) {
		Calendar horaInicio = Calendar.getInstance();
		horaInicio.setTime(inicio);
		Calendar horaFin = Calendar.getInstance();
		horaFin.setTime(fin);
		String key = horaInicio.get(Calendar.HOUR_OF_DAY) + ":"
				+ ((horaInicio.get(Calendar.MINUTE) == 0) ? "00" : horaInicio.get(Calendar.MINUTE)) + "-"
				+ horaFin.get(Calendar.HOUR_OF_DAY) + ":"
				+ ((horaFin.get(Calendar.MINUTE) == 0) ? "00" : horaFin.get(Calendar.MINUTE));
		return key;
	}

	private static int tiempoDeSeparacion(String codigoDominio) {
		int retorna;
		switch (codigoDominio) {
		case UtilDominios.DURACION_AUDIENCIA_MEDIA_HORA:
			retorna = 1;
			break;
		case UtilDominios.DURACION_AUDIENCIA_UNA_HORA:
			retorna = 2;
			break;
		case UtilDominios.DURACION_AUDIENCIA_DOS_HORAS:
			retorna = 4;
			break;
		default:
			retorna = 0;
		}
		return retorna;
	}

	/**
	 * Retorna el precio formateado.
	 * 
	 * @param monto
	 * @return
	 */
	public static String formatoPesos(double monto) {
		Locale locale = new Locale("es", "CO");
		String valor = "$0";
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		if (Double.valueOf(monto) != null)
			valor = nf.format(monto);
		return valor;
	}
	
	/**
	 * Metodo que convierte el pdf en un array de byte
	 * 
	 * @param ruta
	 * @param archivo
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static byte[] convertToByte(String ruta, String archivo) throws FileNotFoundException, IOException {
		File file = new File(ruta + archivo);
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;

		try {
			fis = new FileInputStream(file);
			bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
			fis.close();
		} catch (Exception e) {
			logger.error("Error: ", e);
		} finally {
			if (fis != null)
				fis.close();
		}

		return (bos != null ? bos.toByteArray() : null);
	}

	/**
	 * Metodo que convierte en un archivo pdf los arrays de byte ya firmados
	 * 
	 * @param ruta
	 * @param archivo
	 * @return
	 * @throws IOException
	 */
	public static InputStream convertFromByte(String ruta, byte[] archivo, String nombreArchivo) throws IOException {
		String name = ruta + nombreArchivo;
		File somefile = new File(name);
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(somefile);
			fos.write(archivo);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			logger.error("Error: ", e);
		} finally {
			if (fos != null)
				fos.close();
		}

		return new FileInputStream(name);
	}

	/**
	 * Método encargado de obtener la dorección IP origen de una petición
	 * realizada a la aplicación
	 * 
	 * @param httpServletRequest
	 * @return
	 */
	public static String obtenerDireccionIPOrigen(HttpServletRequest httpServletRequest) {
		String direccionIPOrigen = null;

		if (httpServletRequest != null) {
			String encabezadoXForwardedFor = httpServletRequest
					.getHeader(UtilConstantes.LLAVE_ENCABEZADO_HTTP_DIRECCION_IP_ORIGEN);
			if (encabezadoXForwardedFor == null) {
				direccionIPOrigen = httpServletRequest.getRemoteAddr();
			} else {
				direccionIPOrigen = new StringTokenizer(encabezadoXForwardedFor, UtilConstantes.CARACTER_COMA)
						.nextToken().trim();
			}
		}

		return direccionIPOrigen;
	}
	

	/**
	 * Take from: https://www.geeksforgeeks.org/md5-hash-in-java/
	 * @param input
	 * @return
	 */
	public static String getMd5(String input) {
		try {

			// Static getInstance method is called with hashing MD5
			MessageDigest md = MessageDigest.getInstance("MD5");

			// digest() method is called to calculate message digest
			// of an input digest() return array of byte
			byte[] messageDigest = md.digest(input.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new SimascException(e.getMessage());
		}
	}

}
