/**
 * 
 */
package com.ccb.simasc.transversal.utilidades;

/**
 * Manejo de los codigos de los parametros de la tabla PARAMETROS GENERALES
 * Los parametros se agrupan por tipo, pueden existir tipos con un solo parametro.
 * @author lvalbuena
 *
 */
public class UtilParamGenerales {
	
	
	/**
	 * Parametros de tipo: CORREO
	 */
	public static final String TIPO_CORREO = "CORREO";
	public static final String CORREO_LLAVE = "LLAVE";
	public static final String CORREO_TIPO_CONTENIDO = "CONTENID";
	public static final String CORREO_ARBITRAJE = "COR_ARB";
	public static final String CORREO_CONCILIACION = "COR_CON";
	
	
	/**
	 * parametros de tipo: GESTOR_DOCUMENTAL
	 */
	public static final String TIPO_GESTOR_DOCUMENTAL = "GES_DOCU";
	public static final String GESTOR_DOCUMENTAL_RUTA = "RUTA_TEM";		
	public static final String GESTOR_DOCUMENTAL_RUTA_GESTOR = "RUTA_GES";
	public static final String GESTOR_DOCUMENTAL_PREFIJO_DIR_CASO = "PREF_DIR";
	public static final String GESTOR_DOCUMENTAL_PREFIJO_DIR_SOLICITUD = "PREF_SOL";
	public static final String GESTOR_DOCUMENTAL_PLANTILLA_TXT = "PTX";
	public static final String GESTOR_DOCUMENTAL_RUTA_TRANSVERSALES = "RUTA_TRV";

	/**
	 * Parametros estaticos cartas
	 */
	public static final String DIRECTOR_CENTRO_CONCILIACION="DCC";
	public static final String RUTA_DOCUMENTO_FIRMA= "RDF";
	public static final String CIUDAD="CIU";
	public static final String CARGO="CAR";
	public static final String DIRECTOR_PARAMETRO="DP";
	public static final String FIRMA_PARAMETRO="FP";
	public static final String CIUDAD_PARAMETRO="CP";
	public static final String CARGO_PARAMETRO="CG";
	public static final String RUTA_FIRMA_CONCILIADORES = "RDFC";
	
	/**
	 * Parámetros de tipo: PRONUNCIAMIENTO
	 */
	public static final String TIPO_PRONUNCIAMIENTO = "PRONUNCIAMIENTO";
	public static final String ASUNTO_CORREO = "ASU_COR";
	
	/**
	 * Parametros reparto
	 */
	public static final String TIPO_REPARTO_EQUITATIVO = "REPARTO_EQUITATIVO";
	public static final String PREFIJO_PARAMETROS_REPARTO = "REP_";
	public static final String REPARTO_ABOGADO_ARBITRAL = "REP_ABO";
	public static final String REPARTO_AUXILIAR_ADMINISTRATIVO = "REP_AUX";
	public static final String REPARTO_DIGITALIZADOR = "REP_DIG";
	
	/**
	 * Parametros asignación cíclica de casos reparto
	 */
	public static final String ACCR_ABOGADO_ARBITRAL = "ACCR_ABG";
	public static final String ACCR_AUX_AUXILIAR_ADMINISTRATIVO = "ACCR_AUX";
	public static final String ACCR_ANALISTA_CONCILIACION = "ACCR_ANC";
	public static final String ACCR_ANALISTA_CONCILIACION_EQUIDAD = "ACCR_ANE";
	
	/**
	 * Parametros Tareas automaticas caso de uso TRANS-F-011
	 */
	public static final String TAREA_PROGRAMADA_CARTAS_PENDIENTES="PROGRAMACION CARTAS PENDIENTES";
	public static final String TAREA_PROGRAMADA_CARTAS_PENDIENTES_DIA = "CAP_DIA";
	public static final String TAREA_PROGRAMADA_CARTAS_PENDIENTES_HORA = "CAP_HOR";
	public static final String TAREA_PROGRAMADA_CARTAS_PENDIENTES_MINUTO = "CAP_MIN";
	public static final String TAREA_PROGRAMADA_CARTAS_PENDIENTES_SEGUNDO = "CAP_SEG";
	
	/**
	 * URLS_SERVICIOS
	 */
	public static final String TIPO_PARAMETRO_URL_SERVICIOS = "URL_SERVICIOS";
	public static final String CODIGO_PARAMETRO_URL_MINISTERIO = "URLMIN";
	public static final String CODIGO_PARAMETRO_URL_CERTICAMARA = "URL_CERT";
	
	/**
	 * TIPOS_MINISTERIO
	 */
	public static final String TIPO_PARAMETRO_USUARIO_MIN = "USUARIO_MINISTERIO";
	public static final String CODIGO_PARAMETRO_USUARIO = "USUARIO";
	public static final String CODIGO_PARAMETRO_CLAVE = "CLAVE";
	
	/** CON-F-106
	 * Cierre de caso 
	 */
	public static final String TIPO_PARAMETRO_GENERAL_CIERRE_CASO = "CIERRE_CASO";
	public static final String CODIGO_CIERRE_CASO_NUMERO_DIAS = "NDCC";
	
	/**
	 * Parametros de tipo NUMERO AUDIENCIAS PARA RECOBRO
	 */
	public static final String NUMERO_AUDIENCIAS_PARA_RECOBRO = "NUMERO AUDIENCIAS PARA RECOBRO";
	public static final String NUMERO_AUDIENCIAS_PARA_RECOBRO_AUDI_REC = "AUDI_REC";
	
	/**
	 * Parametros de tipo PESO CRITERIOS EVALUACION
	 */
	public static final String TIPO_PESO_CRITERIOS_EVALUACION = "PESO_CRITERIOS_EVALUACION";
	
	
	/**
	 * Parametros de tipo URL_RETOMA_SOLICITUD
	 */
	public static final String TIPO_URL_RETOMA_SOLICITUD = "URL_RETOMA_SOLICITUD";
	public static final String CODIGO_URL_RETOMA_SOLICITUD = "URL_RET";
	
	/**
	 * Parámetros de tipo: PARAMETROS_CERTICAMARA
	 */
	public static final String TIPO_PARAMETROS_CERTICAMARA = "PARAMETROS_CERTICAMARA";
	public static final String CODIGO_USUARIO_SERVICIO_CERTICAMARA = "USU_CERT";
	public static final String CODIGO_CLAVE_SERVICIO_CERTICAMARA = "USU_CERT";
	public static final String CODIGO_ID_APLICACION_CCB_SERVICIO_CERTICAMARA = "ID_APP_CCB";
	public static final String CODIGO_PARAMETRO_PAGINAS_SERVICIO_CERTICAMARA = "PAGINAS";
	public static final String CODIGO_PARAMETRO_VALIDACION_SERVICIO_CERTICAMARA = "VALIDACION"; 

	
	/**
	 * Parametros de tipo: PARAMETROS_TOKEN
	 */
	public static final String CODIGO_ID_APLICACION_TOKEN = "IDAPP";
	public static final String CODIGO_ID_SEDE_TOKEN = "IDSEDE";
	
	/**
	 * Parametros de tipo: FIRMA_DIGITAL_PDF
	 */
	public static final String CODIGO_IDENTIFICADOR_SUCURSAL_FIRMA = "IDSUC";
	public static final String CODIGO_TIPO_FIRMA = "TIPOFIR";
	public static final String WS_ID_CLIENTE = "CLIENPDF";
	public static final String WS_INGRESO = "PASSPDF";
	public static final String WS_POLITICA_ELECTRONICA = "POLPDF";
	public static final String WS_POLITICA_DIGITAL = "POLDIG";
	public static final String WS_WEB_SERVICE_FIRMA = "WSPDF";

	// Constante para token
	public static final String WS_TOKEN = "WSTOKEN";
}
