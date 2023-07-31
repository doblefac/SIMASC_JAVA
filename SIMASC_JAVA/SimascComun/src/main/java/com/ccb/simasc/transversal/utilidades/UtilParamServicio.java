/**
 * 
 */
package com.ccb.simasc.transversal.utilidades;

/**
 * Manejo de los codigos de los parametros de la tabla PARAMETRO DE SERVICIO
 * Los parametros se agrupan por tipo, pueden existir tipos con un solo parametro.
 * @author dpachon
 *
 */
public class UtilParamServicio {
	
	/**
	 * Parametros de tipo: PROGRAMACION AUDIENCIA
	 */
	public static final String TIPO_PARAMETRO_PROGRAMACION_AUDIENCIA = "PROGAUDI";
	public static final String MIN_DIAS_PROGRAMACION_AUDIENCIA = "MINIMO_DE_DIAS_PARA_PROGRAMAR_AUDIENCIA";
	public static final String MAX_DIAS_PROGRAMACION_AUDIENCIA = "MAXIMO_DE_DIAS_PARA_PROGRAMAR_AUDIENCIA";
	public static final String DURACION_DE_AUDIENCIA = "DURACION_DE_AUDIENCIA";
	
	/**
	 * Parametros de tipo: AUDIENCIAS PARA RECOBRO
	 */
	public static final String TIPO_PARAMETRO_AUDIENCIAS_RECOBRO = "AUDI_REC";
	public static final String NUMERO_AUDIENCIAS_PARA_RECOBRO = "NUMERO_AUDIENCIAS_PARA_RECOBRO";
	
	/**
	 * Parametros de tipo: CIERRE DE CASO
	 */
	public static final String TIPO_PARAMETRO_CIERRE_CASO = "CIERRECA";
	public static final String NUMERO_DIAS_CIERRE_CASO = "NUMERO_DIAS_CIERRE_CASO";
	
	/**
	 * Parametros de tipo: TIEMPO MAXIMO PARA CORRECCION DOCUMENTO
	 */
	public static final String TIPO_PARAMETRO_CORRECCION_DOCUMENTO = "PROGAUDI";
	public static final String TIEMPO_MAXIMO_PARA_CORRECCION_DOCUMENTO = "TIEMPO_MAXIMO_PARA_CORRECCION_DOCUMENTO";
	
	/**
	 * Parametros de tipo: MATERIA PARA CASOS EXPRESS
	 */
	public static final String TIPO_PARAMETRO_MATERIA_EXPRESS = "MAT_EXP";
	public static final String MATERIA_CASOS_EXPRESS = "MATERIA_CASOS_EXPRESS";
	
	/**
	 * Parametros de tipo: INSOLVENCIA
	 */
	public static final String MONTO_DEUDA_INSOLVENCIA_SMLMV = "MONTO_DEUDA_INSOLVENCIA_SMLMV";
	
	/**
	 * Parametros de tipo: EQUIDAD
	 */
	public static final String ROL_ANALISTA_LEGALIDAD_CALIDAD = "ROLANALC";
	public static final String BANDERA_PDF = "BANDERA";
}
