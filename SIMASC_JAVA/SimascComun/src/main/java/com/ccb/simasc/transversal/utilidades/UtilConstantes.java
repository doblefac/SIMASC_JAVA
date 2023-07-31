
package com.ccb.simasc.transversal.utilidades;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Clase con constantes consultadas por diferentes clases de la aplicación
 * 
 * @author jsoto
 */
public class UtilConstantes {

	public static final Double MAXIMO_NUMERO_ITERACIONES = 10d;
	public static final String VERSION = "7.1.0-0";
	public static final String SI = "SI";
	public static final String NO = "NO";
	public static final String SEPARADOR_HTTP_ORDER_BY = "$";
	public static final String CARACTER_DE_ESCAPE = "\\";
	public static final String SEPARADOR_PARAMETROS_CONSULTA = "&";
	public static final String SEPARADOR_ALEATORIOS_SORTEO = ";";
	public static final String CARACTER_ESPACIO = " ";
	public static final String CARACTER_COMA = ",";
	public static final String CARACTER_ARROBA = "@";
	public static final String CARACTER_GUION_ALTO = "-";
	public static final String CARACTER_GUION_BAJO = "_";
	public static final String PAGINA_BIENVENIDA = "index?faces-redirect=true";
	public static final String PAGINA_LOGIN = "login";
	public static final String AUDITABLE_IDUSUARIOMODIFICACION = "idUsuarioModificacion";
	public static final String AUDITABLE_FECHAULTIMAMODIFICACION = "fechaUltimaModificacion";
	public static final String CONTEXTO_SESION = "contextoSesion";

	// Constantes reparto
	public static final String PARAM_ID_SERVICIO = "idServicio";
	public static final String PARAM_TIPO_AGRUPAMIENTO = "tipoAgrupamiento";
	public static final String PARAM_TIPO_SERVICIO = "tipoServicio";
	public static final String PARAM_ID_ROL = "idRol";
	public static final String PARAM_ESTADO_REGISTRO = "estadoRegistro";
	public static final int MINUTOS_MEDIA_HORA = 30;
	public static final int MINUTOS_UNA_HORA = 60;
	public static final int MINUTOS_DOS_HORAS = 120;
	public static final int MINUTOS_ENTRE_TURNOS = 30;

	// numeros
	public static final int CERO = 0;
	public static final int UNO = 1;
	public static final int DOS = 2;
	public static final int TRES = 3;
	public static final int CUATRO = 4;
	public static final int CINCO = 5;

	// varios
	public static final String COMA = ",";
	public static final String ESPACIO = " ";
	public static final String DOS_PUNTOS = ":";

	public static final String[] CAMPOS_AUDITABLES = { UtilConstantes.AUDITABLE_FECHAULTIMAMODIFICACION,
			UtilConstantes.AUDITABLE_IDUSUARIOMODIFICACION };
	// Textos
	public static final String TEXTO_Y_OTROS = "y otros ";
	public static final String TEXTO_SUBTOTAL = "Subtotal";
	public static final String TEXTO_VALOR_TOTAL = "Valor total";

	// ManejadorCrud
	public static final String NULL_VALUE = "NULL";
	public static final String NOT_NULL_VALUE = "NOT NULL";

	// UtilReflection
	public static final String LONG_PRIMITIVE = "long";
	public static final String INT_PRIMITIVE = "int";

	// Servicios
	public static final String CODIGO_SERVICIO_ARBITRAJE = "ARBITRAJE";
	public static final String CODIGO_SERVICIO_CONCILIACION = "CONCILIACION";
	public static final String ARBITRAJE_INTERNACIONAL = "ARBITRAJE INTERNACIONAL";
	public static final String SERVICIO_CONVENIO_CONCILIACION = "CONVENIO CONCILIACIÓN";

	public static final String NOMBRAMIENTO_PRINCIPAL = "PRINCIPAL";
	public static final String NOMBRAMIENTO_SUPLENTE = "SUPLENTE";

	// PARTICIPACIÓN DE TERCEROS
	public static final String CODIGO_PARTICIPANTE_PARTE = "DEM";
	public static final String CODIGO_PARTICIPANTE_CONTRAPARTE = "DMD";

	// TIPOS DE EVENTOS
	public static final String TIPO_EVENTO_ERROR = "ERR";
	public static final String TIPO_EVENTO_ACCION = "ACC";

	// TIPOS CUANTIA
	public static final String TIPO_CUANTIA_MAYOR = "MAYOR";
    public static final String TIPO_CUANTIA_MENOR = "MENOR";
    public static final String TIPO_CUANTIA_INDERTERMINADA = "INDETERMINADA";
    public static final String TIPO_CUANTIA_CON_CUANTIA = "CON CUANTIA";
    public static final String TIPO_CUANTIA_INDETERMINADO_ABREVIADO = "CUANTIA INDETERMINADA";
    public static final String TEXTO_INDETERMINADA = "Indeterminada";

	// Disponibilidad de arbitro para sorteo
	public static final String ARBITRO_DISPONIBLE_SORTEO = "SI";
	public static final String ARBITRO_NO_DISPONIBLE_SORTEO = "NO";
	public static final int VALOR_SEMILLA_SORTEO = 999999999;

	// Habilitado para el servicio materia
	public static final String ARBITRO_HABILITADO_SERVICIO_MATERIA = "Habilitado";
	public static final String ARBITRO_NO_HABILITADO_SERVICIO_MATERIA = "No habilitado";

	public static final String REPORTE_ESTADO_ARBITRO_FECHA_DISPONIBILIDAD_NO_APLICA = "NA";

	public static final String SMLMV = "SMLMV";
	public static final String UVT = "UVT";
	public static final String IVA = "IVA";
	public static final BigDecimal SEPARADOR_TIPO_CUANTIA = new BigDecimal("400");
	public static final String TIPO_CUANTIA_A = "A";
	public static final String TIPO_CUANTIA_B = "B";
	
	public static final String LIMITSMLMV = "LIMTSMLMV";
	public static final String LIMITUVT = "LIMTUVT";

	public static final Long LISTA_A = 1L;
	public static final Long LISTA_B = 2L;

	public static final String ENCABEZADO_MENSAJE_INFORMATIVO = "Información";
	public static final String ENCABEZADO_MENSAJE_ADVERTENCIA = "Advertencia";
	public static final String ENCABEZADO_MENSAJE_ERROR = "Error";

	// manejo de excepciones
	public static final char ADVERTENCIA = 'W';
	public static final char ERROR = 'E';
	public static final char INFO = 'I';

	//
	public static final String USUARIO_DEFECTO_SIMASC = "SIMASC_USUARIO";

	// Gestor Domuental
	public static final String CARACTER_SEPARADOR = ";";
	public static final String CARACTER_SALTO_LINEA = "\n";
	public static final String CARACTER_PUNTO = ".";
	public static final String EXTENSION_ARCHIVO_TXT = "txt";
	public static final String EXTENSION_ARCHIVO_PDF = "pdf";
	public static final String EXTENSION_ARCHIVO_ZIP = "zip";
	public static final String EXTENSION_ARCHIVO_HTML = "html";

	public static final String ROL_PERSONA_EXTERNO = "EXT";

	// Parametros de correo
	public static final String PARAMETROS_CORREO = "CORREO";
	public static final String TLS = "TLS";
	public static final String PUERTO = "PUERTO";
	public static final String PROTOCOL = "PROTOCOL";
	public static final String HOST = "HOST";

	public static final int DIAS_MAXIMOS_PARA_PRONUNCIAMIENTO = 5;

	// Fechas y horas
	public static final String FORMATO_FECHA_ANIO_MES_DIA = "yyyy/MM/dd";
	public static final String FORMATO_FECHA_ANIO_MES_DIA_HORA = "yyyy/MM/dd HH:mm:ss";
	public static final String FORMATO_HORA_INICIO = "00:00:00.000";
	public static final String FORMATO_HORA_FIN = "23:59:00.000";
	public static final String FORMATO_FECHA_TIMESTAMP = "yyMMddHHmmssSSS";
	public static final String FORMATO_FECHA_DIA_MES_ANIO = "dd/MM/yyyy";
	public static final String FORMATO_FECHA_DIA_MES_ANIO_HORA = "dd/MM/yyyy HH:mm:ss";
	public static final String FORMATO_FECHA_ANIO_MES_DIA_GUION = "yyyy-MM-dd";
	public static final String FORMATO_FECHA_HORA_MINUTOS = "HH:mm";

	// Roles
	public static final String[] ROLES_ARBITROS = { UtilDominios.ROL_PERSONA_ARBITRO_EXTERNO,
			UtilDominios.ROL_PERSONA_ARBITRO, UtilDominios.ROL_PERSONA_ARBITRO_INTERNACIONAL,
			UtilDominios.ROL_PERSONA_ARBITRO_SOCIAL, UtilDominios.ROL_PERSONA_AMIGABLE_COMPONEDOR, UtilDominios.ROL_PERSONA_PERITO,
			UtilDominios.ROL_PERSONA_ARBITRO_RECUPERACION, UtilDominios.ROL_PERSONA_ARBITRO_ADHOC, UtilDominios.ROL_PERSONA_MEDIADOR,
			UtilDominios.ROL_PERSONA_ARBITRO_RECUSACION };
	
	public static final String[] ROLES_OPERADORES = { UtilDominios.ROL_PERSONA_ARBITRO_EXTERNO,
			UtilDominios.ROL_PERSONA_ARBITRO, UtilDominios.ROL_PERSONA_ARBITRO_INTERNACIONAL,
			UtilDominios.ROL_PERSONA_ARBITRO_SOCIAL, UtilDominios.ROL_PERSONA_AMIGABLE_COMPONEDOR, UtilDominios.ROL_PERSONA_PERITO,
			UtilDominios.ROL_PERSONA_ARBITRO_RECUPERACION, UtilDominios.ROL_PERSONA_ARBITRO_ADHOC, UtilDominios.ROL_PERSONA_MEDIADOR, 
			UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL, UtilDominios.ROL_PERSONA_ARBITRO_RECUSACION };
	
	public static final String[] ROLES_CONCILIADOR = { UtilDominios.ROL_PERSONA_CONCILIADOR,
		UtilDominios.ROL_PERSONA_CONCILIADOR_EQUIDAD, UtilDominios.ROL_PERSONA_CONCILIADOR_EN_INSOLVENCIA };
	// Los estados que se tienen en cuenta para presentar al arbitro que se va
	// apronunciar
	public static final String[] ESTADOS_ARBITROS_PARA_INFORMAR = { UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR,
			UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO };

	public static final String CADENA_VACIA = "";
	public static final String RETORNO_PARAMETROS_VALOR_NUMERICO = "valorNumerico";
	public static final String RETORNO_PARAMETROS_VALOR_TEXTO = "valorTexto";
	public static final String RETORNO_PARAMETROS_VALOR_FECHA = "valorFecha";
	public static final String RETORNO_PARAMETROS_VALOR_BOOLEANO = "valorBooleano";
	public static final String TIPO_ORDENAMIENTO_ASCENDENTE = "ASC";
	public static final String TIPO_ORDENAMIENTO_DESCENDENTE = "DESC";


	// SEGURIDAD
	public static final String APIKEY = "u8q3XOa;F|t<du;14'-fI[Sq@E^yI4iO4P4~60V3.Un5_.&M7i<P09@s=C^8561";
	public static final String OBJECT_AES_KEY = "9ffcc6bdc7ecfa8bc564e579ac952efa";
	public static final String HEADER_DOCUMENT_KEY = "documento";
	public static final String PERSONA_SESSION_ATTRIBUTE_KEY = "personaSesion";
	public static final String USERNAME_SESSION_ATTRIBUTE_KEY = "userSesion";
	public static final String FUNCIONALIDADES_SESSION_ATTRIBUTE_KEY = "funcionSesion";
	public static final String PANTALLA_SESSION_ATTRIBUTE_KEY = "pantallaSorteo";
	public static final String LOGIN_ERROR = "Error, usuario o contraseña inválidos";
	public static final String LOGIN_ERROR_TO_INVALIDATE = "Error, usuario o contraseña invalidos, queda un intento para bloquear el usuario";
	public static final String USER_NOT_VALID = "El usuario se encuentra inactivo, por favor comuníquese con el administrador del sistema.";
	public static final String USER_NOT_EXIST = "El usuario no se encuentra registrado.";
	public static final String SOLICITANTE_NO_EXISTE = "Sr. Usuario las credenciales ingresadas no corresponden a un usuario registrado en el sistema, recuerde que el usuario corresponde al número de identificación y la contraseña corresponde al correo electrónico registrado por el solicitante.";
	public static final String TIME_SESION_ERROR = "El usuario ha iniciado sesión recientemente, por favor espere %s minutos.";
	public static final String TIME_SESION_SECONDS_ERROR = "El usuario ha iniciado sesión recientemente, por favor espere %s segundos.";
	public static final String BLOCK_USER_ERROR = "La contraseña se encuentra bloqueada por favor comuníquese con el administrador del sistema.";
	public static final String INGRESO_EXPIRADO = "Sr. Usuario, su contraseña se encuentra expirada por favor comuníquese con el administrador del sistema.";
	public static final String CAMBIO_INGRESO_SIN_COINCIDENCIA = "Sr. Usuario la contraseña actual no coincide con alguna contraseña valida";
	public static final String INGRESO_UTILIZADO = "Sr. Usuario la contraseña ya ha sido utilizada anteriormente.";
	public static final String USER_ERROR = "Sr. Usuario en este momento no se puede generar una nueva clave para el usuario seleccionado, por favor intente más tarde.";
	public static final String ERROR_CORREO = "Fallo al envíar correo";
	public static final String ASUNTO_GENERACION = "Generación de clave";
	public static final String ASUNTO_OLVIDO = "Recuperación de clave";
	public static final String CUERPO_CORREO = "su nueva contraseña es: %s";
	public static final String TOKEN_INVALIDO = "el token es invalido";
	public static final String PERSONA_NO_EXISTE = "No existe una persona registrada con ese numero y tipo de documento";
	public static final String ASUNTO_REGISTRO_USUARIO = "Usuario Registrado";

	public static final Long SECOND_TO_MILLIS = 1000l;
	public static final Long MINUTES_TO_SECONDS = 60l;
	public static final Long HOURS_TO_MINUTES = 60l;
	public static final Long DAYS_TO_HOURS = 24l;

	public static final String TIEMPO_VIDA_SESION_MINUTOS = "TVSM";
	public static final Long TIEMPO_VIDA_JWT_HORAS = 6l;
	public static final Long TIEMPO_VIDA_JWT = TIEMPO_VIDA_JWT_HORAS * HOURS_TO_MINUTES * MINUTES_TO_SECONDS
			* SECOND_TO_MILLIS;
	public static final Long MINUTES_TO_MILLIS = MINUTES_TO_SECONDS * SECOND_TO_MILLIS;
	public static final Integer DIAS_CALENDARIO_CLAVE_EXPIRACION = 30;

	public static final int DIAS_ACTUALIZA_FECHA_VENCIMIENTO = -2;
	public static final int DIAS_GENERAR_CLAVE_FECHA_VENCIMIENTO = -1;
	public static final int DIAS_FECHA_VENCIMIENTO = 32;
	public static final int GENERADOR_MIN = (int) 1e+8;
	public static final int GENERADOR_MAX = ((int) 1e+9) - 1;
	public static final int NUMERO_CLAVES_ACTIVAS = 7;

	// PERFIL
	public static final String USUARIO_NULL_ERROR = "Usuario nulo";
	public static final String USUARIO_ERROR = "Error al obtener perfiles";

	public static final Integer INTENTOS = 3;
	public static final String TIPO_SESION_KEY = "tipoSesion";
	public static final String ESTAMPA = "estampa";

	public static final String TIMEOUT = "timeOut";
	public static final String REINTENTOS = "NoIntentos";
	public static final String TIPO_SESION_VALOR_OK = "OK";
	public static final String TIPO_SESION_VALOR_ERROR = "ERROR";
	public static final String EMPTY_RESPONSE = "EMPTY";

	public static final String INTENTO_SESION_ERROR = "Se han hecho %s intentos invalidos, el usuario ha sido bloqueado por favor comuníquese con el administrador del sistema.";
	public static final String REALM = "defaultWIMFileBasedRealm";
	public static final String LOGIN = "WSLogin";

	public static final long PAIS_COLOMBIA = 169;

	// Transcripción
	public static final String ESTADO_TRANSCRIPCION_POR_TRANSCRIBIR = "Por transcribir";
	public static final String ESTADO_TRANSCRIPCION_TRANSCRIPCION_FINALIZADA = "Transcripcion finalizada";
	public static final String ASUNTO_CORREO_REASIGNACION_TRANSCRIPCION = "Reasignación de transcripción de audio";
	public static final String ASUNTO_CORREO_ASIGNACION_TRANSCRIPCION = "Asignación de transcripción de audio";

	public static final String ACTOR_SECRETARIO = "Secretario";
	public static final String ACTOR_ARBITRO = "Arbitro";
	public static final String ACTOR_CAC = "CAC";

	public static final String ASUNTO_CORREO_PROGRAMACION_AUDIENCIA = "Programación de audiencia";

	public static final String ASUNTO_CORREO_RESULTADO_AUDIENCIA = "Cargue de soportes de audiencia";
	public static final String CUERPO_CORREO_RESULTADO_AUDIENCIA_ACTA = "Se ha realizado cargue del acta ";
	public static final String CUERPO_CORREO_RESULTADO_AUDIENCIA_AUDIOS = "y el(los) audio(s)";
	
	public static final String ASUNTO_CORREO_RADICACION_DOCUMENTOS = "Radicación de documento";
	public static final String CUERPO_CORREO_RADICACION_DOCUMENTOS = "Le informamos que se radicó el documento ";
	
	//Token Structure 
	public static final String TOKEN_IDPERSONA="idpersona";
	public static final String TOKEN_NOMBRE_PERSONA="nombrepersona";
	public static final String TOKEN_CLAVE_EXPIRADA="claveExpirada";
	public static final String TOKEN_MAUC="tokenMauc";
	//Header Structure
	public static final String HEADER_TOKEN="token";
	public static final String HEADER_SESSION_CONTEXT="session-context";
	public static final String[] URLS_ADMITIDAS = { "/rol/", "/seguridad/", "/usuario/obtenerUsuarioId/",
			"/gestorDocumental/recuperarDocumento/", "/gestorDocumental/recuperarDocumentoTransversal/", "/dominio/consultarDominios", 
			"/plantillacarta/consultarPlantillaServicioNombre/", "/captcha/verificarSitio/" };
	public static final String HEADER_REFERER = "Referer";
	public static final String PROJECT_STAGE = "compilation.unit.stage";
	public static final String PROJECT_STAGE_DEV = "Development";
	public static final String PROJECT_STAGE_PRD = "Production";
	public static final String PROJECT_PROPERTIES = "/WEB-INF/app.properties";
	// CODIGOS CORREOS ELECTRONICOS PARA BUSCAR EN TABLA PARAMETRO
	public static final String CODIGO_CORREO_ARBITRAJE = "COR_ARB";
	public static final String CODIGO_CORREO_CONCILIACION = "COR_CON";
	
	

	// Materia SIN MATERIA para arbitraje internacional
	public static final String SIN_MATERIA = "SIN MATERIA";

	// Reparto
	public static final String ASIGNACION_FUNCIONARIO = "Se ha asignado el funcionario ";
	public static final String DESIGNACION = "Designacion";
	public static final String ASIGNACION_CASO_CONTROL_LEGALIDAD = "Asignación del caso para control de legalidad";

	// Nombres de cuadernos
	public static final String NOMBRE_CUADERNO_PRINCIPAL = "Cuaderno Principal";
	public static final String NOMBRE_CUADERNO_PRUEBAS = "Cuaderno de pruebas";
	public static final String NOMBRE_CUADERNO_ADMON_GASTOS = "Cuaderno de Administración de gastos";
	public static final String NOMBRE_CUADERNO_MEDIDAS_CAUTELARES = "Cuaderno de Medidas Cautelares";

	// parametro meses limite para cierre de caso
	public static final String MESES_LIMITE_PARA_CIERRE_DE_CASO = "MLCC";
	public static final String DIAS_LIMITE_PARA_CIERRE_DE_CASO = "DIAS_LIMITE_PARA_CIERRE_DE_CASO";
	public static final String CIERRE_CASO = "Cierre de Caso";
	public static final int DIAS_PROMEDIO_MES = 30;

	// parametro dias máximos de suspension en un caso
	public static final String DIAS_LIMITE_DE_SUSPENSIONES_CASO = "DIALIMSU";

	// PARAMETROS PARA MANEJAR LA CANTIDAD DE ARBITROS EN EL CASO
	public static final String CANTIDAD_ARBITROS_UNO = "UNO";
	public static final String CANTIDAD_ARBITROS_TRES = "TRES";

	// Nombre documento acuse fallo envío de correo desde SIMASC (no es
	// certimail)
	public static final String NOMBRE_DOCUMENTO_FALLO_ENVIO_CORREO = "Fallo_envio_correo_";
	
	
	// Nombre del documento de radicación de casos de trámite ordinario
	public static final String NOMBRE_DOCUMENTO_TRAMITE_ORDINARIO = "Radicacion caso de tramite ordinario";
	// Nombre del documento de radicación de casos de insolvencia
	public static final String NOMBRE_DOCUMENTO_TRAMITE_INSOLVENCIA = "Radicación solitud de Insolvencia";
	
	public static final String NOMBRE_DOCUMENTO_PRONUNCIAMIENTO = "PRONUNCIAMIENTO - ";
	public static final String NOMBRE_DOCUMENTO_INDICE_ELECTRONICO = "INDICE ELECTRONICO - ";
	// indicador de correo certificado
	public static final String INDICADOR_CORREO_CERTIFICADO = "REF:%";

	// Templates envio de correos
	public static final String RUTA_TEMPLATES = "/templates/";
	public static final String TEMPLATE_CAMBIO_CLAVE = "correoclaveTemplate.ftl";
	public static final String TEMPLATE_CARTAS_IMPRESION = "CartasPendientesTemplate.ftl";
	public static final String TEMPLATE_USUARIO_SIN_CLAVE = "crearUsuarioSinClave.ftl";

	// Templates PDF
	public static final String RUTA_TEMPLATES_HTML = "/templateshtml/";
	public static final String TEMPLATE_PAGINA_UNO = "RadicacionPage1.ftl";
	public static final String TEMPLATE_PERSONA_NATURAL = "RadicacionPersonaNatural.ftl";
	public static final String TEMPLATE_PERSONA_JURIDICA = "RadicacionPersonaJuridica.ftl";
	public static final String TEMPLATE_PERSONA_CONVOCANTE = "RadicacionPersonaConvocante.ftl";
	public static final String TEMPLATE_PERSONA_CONVOCADO = "RadicacionPersonaConvocado.ftl";
	public static final String TEMPLATE_INICIO_CONFLICTO = "InicioConflicto.ftl";
	public static final String TEMPLATE_RADICACION_MEDIACION = "RadicacionMediacion.ftl";
	public static final String TEMPLATE_RADICACION_INSOLVENCIA = "RadicacionInsolvencia.ftl";
	public static final String TEMPLATE_RADICACION_PARTES_PERSONA_NATURAL_MEDIACION = "RadicacionPartesPersonaNaturalMediacion.ftl";
	public static final String TEMPLATE_RADICACION_PARTES_PERSONA_JURIDICA_MEDIACION = "RadicacionPartesPersonaJuridicaMediacion.ftl";
	public static final String TEMPLATE_RADICACION_MEDIACION_DOCUMENTOS_APORTADOS = "RadicacionMediacionDocumentosAportados.ftl";
	// Datos del usuario de consulta para el ministerio
	public static final String USUARIO_CLV = "Servicio.2016";
	public static final String USUARIO_MODULO_ARBITRAJE = "CASOS DE ARBITRAJE";
	public static final String USUARIO_MODULO_AMIGABLE = "CASOS DE AMIGABLE COMPOSICIÓN";
	public static final String USUARIO_MODULO_CONCILIACION = "CASOS DE CONCILIACIÓN";
	public static final String USUARIO_NOMBRE_ROL = "PERSONAL ADMINISTRATIVO";
	public static final String USUARIO_NOMBRE = "CC99999999990001";
	public static final String USUARIO_NOMBRE_ORGANIZACION = "CENTRO DE ARBITRAJE, CONCILIACIÓN Y AMIGABLE COMPOSICIÓN DE LA CÝMARA DE COMERCIO DE BOGOTÝ - AUTORIZADO PARA CONOCER DE LOS PROCEDIMIENTOS DE INSOLVENCIA ECONÓMICA DE LA PERSONA NATURAL NO COMERCIANTE";
	public static final String USUARIO_TIPO_AMBITO = "CENTRO";

	// Datos del ministerio
	public static final Long TIPO_FINALIDAD_MINISTERIO_CONCILIACION = 1L;
	public static final Long ESCALADA_CONFLICTO_CON_VIOLENCIA = 1L;
	public static final Long ESCALADA_CONFLICTO_SIN_VIOLENCIA = 2L;
	public static final String TIPO_PARAMETRO_USUARIO_MIN = "USUARIO_MINISTERIO";

	// Templates Cartas
	public static final String TEMPLATE_CARTA_AUDIENCIA = "plantillaCitacionAudiencias.ftl";
	public static final String NOMBRE_TEMPLATE_CARTAS_DOCUMENTOS_RADICADOS ="Plantilla de cartas para documentos radicados de arbitraje o de amigable composición";

	// Parametros Generales
	public static final String NO_APLICA = "NA";
	public static final String DIAS_CIERRE_CASO = "NDCC";
	public static final String TIPO_PARAMETRO_CIERRE_CASO = "CIERRE_CASO";
	public static final String TIPO_PARAMETRO_PLAZO_RESPUESTA = "PLAZO_RESPUESTA_PETICION";
	public static final String TIPO_PARAMETRO_ROL_TIPO_PETICION = "ROL_TIPO_PETICION";

	// RUTAS DE WEBSERVICE
	public static final String URL_SERVICIO_MINISTERIO_JUSTICIA = "http://201.217.213.208:86/SICAACWebService.svc?wsdl";
	public static final String URL_SERVCIO_SIREP_CREAR = "http://ihsd:80/WSRegistros/services/AutoridadCompetenteWS?wsdl";
	public static final String URL_SERVCIO_SIREP_CONSULTAR = "http://ihsd:80/WSClientes/services/ServiciosClientesWS?wsdl";
	public static final String URL_SERVCIO_SIREP_ACTUALIZAR = "http://ihsd:80/WSClientes/services/ServiciosClientesWS?wsdl";
	public static final String URL_SERVCIO_PUP = "http://appqas.ccb.org.co/WCFPUPXrecaudos/Servicio.svc?wsdl";
	public static final String URL_SERVCIO_PUP_MOCK = "http://appqas.ccb.org.co/WCFPUPXrecaudos/Servicio.svc?wsdl";

	public static final String CODIGO_PARAMETRO_URL_MINISTERIO = "URLMIN";
	public static final String CODIGO_PARAMETRO_URL_AUTORIDAD_COM = "URLSIR_C";
	public static final String CODIGO_PARAMETRO_URL_SERVICIO_CLIENTE = "URLSIR_B";
	public static final String CODIGO_PARAMETRO_URL_PUP = "URLPUP";

	public static final String CODIGO_SIREP_EXITOSO = "0000";
	public static final String CODIGO_SIREP_ERROR_UNO = "0101";
	public static final String CODIGO_SIREP_ERROR_DOS = "0102";
	public static final String CODIGO_SIREP_ERROR_TRES = "0103";

	public static final String CAMPO_VACIO = " ";
	public static final String ORGANIZACION_SECTOR_ECONOMICO = "0";
	public static final String ORGANIZACION_TIPO_EMPRESA = "PRIVADA";
	public static final long CODIGO_PAIS_COLOMBIA = 170;
	public static final long CODIGO_CIUDAD_BOGOTA = 11001;
	public static final String CODIGO_NACIONALIDAD_COLOMBIA = "0169";
	public static final String PERSONA_GENERO_MASCULINO = "MASCULINO";
	public static final String PERSONA_GENERO_FEMENINO = "FEMENINO";
	public static final String PERSONA_NATURALEZA_NATURAL = "NATURAL";
	public static final String PERSONA_NATURALEZA_JURIDICA = "JURÝDICA";
	public static final String PROFESION_PERSONA_ENTIDAD_TARJETA_PROFESIONAL = "CONSEJO SUPERIOR DE LA JUDICATURA";
	public static final long PROFESION_ID_INSTITUCION = 300;
	public static final String DOCUMENTO_NOMBBRE = "Acta";
	public static final String DOCUMENTO_TIPO = "PDF";
	public static final long ID_DOCUMENTO_TIPO = 44;
	public static final String DOCUMENTO_ESTADO_ENVIADO = "ENV";
	public static final String ID_SERVICIO_ARBITRAJE_SOCIAL = "3";
	public static final String ID_SERVICIO_ARBITRAJE_INTERNACIONAL = "4";
	public static final String ESTADO_CASO_EN_CREACION = "ENCRE";
	public static final String ESTADO_CASO_ADMITIDO = "ADMITIDO";

	public static final long CODIGO_PROFESION_NO_INFORMA = 6464;
	public static final String CODIGO_ESCOLARIDAD_NO_INFORMA = "13";
	public static final String CODIGO_ESTRATO_NO_INFORMA = "0";
	public static final String CODIGO_SECTOR_NO_INFORMA = "0";
	public static final String CODIGO_ENTIDAD_EDUCATIVA_NO_INFORMA = "300";
	public static final String CODIGO_ENTIDAD_TARJETA_NO_INFORMA = "0";

	/**
	 * Dominio de servicio
	 */

	public static final Long ID_SERVICIO_CONCILIACION_TRAMITE_ORDINARIO = 1L;
	public static final Long ID_SERVICIO_ARBITRAJ_SOCIAL = 3L;
	public static final Long ID_SERVICIO_CONCILIACION_CONVENIO = 8L;
	public static final Long ID_SERVICIO_CONCILIACION_JORNADA = 9L;
	public static final Long ID_SERVICIO_AMIGABLE_COMPOSICION = 6L;
	public static final Long ID_SERVICIO_PERITAJE = 7L;
	public static final Long ID_SERVICIO_DESIGNACION_ARBITROS = 10L;
	public static final Long ID_SERVICIO_CONCILIACION_MEDIACION = 11L;
	public static final Long ID_SERVICIO_RECUPERACION_EMPRESARIAL = 13L;
	public static final Long ID_SERVICIO_ARBITRAJE = 2L;
	public static final Long ID_SERVICIO_INSOLVENCIA = 16L;
	public static final Long ID_SERVICIO_EQUIDAD = 17L;
	public static final Long ID_SERVICIO_ARBITRAJE_ABREVIADO = 5L;

	public static final String SISTEMA_EXTERNO_MINISTERIO = "MINS";
	public static final String SISTEMA_EXTERNO_MAUC = "MAUC";

	// Datos para crear un cliente nuevo en SIREP
	public static final String SISTEMA_EXTERNO_SIREP = "SIREP";
	public static final String SIREP_TIPO_DIRECCION = "163";

	public static final String FUNCIONALIDAD_SORTEO = "SORTEO";

	// Datos sistema externo pagos
	public static final String SISTEMA_EXTERNO_PAGOS = "PAGOC";
	public static final String CODIGO_SEDE_VIRTUAL = "VIRT";

	// Constantes de idioma
	public static final String LOCALE_IDIOMA_ESPANIOL = "es";
	public static final String LOCALE_PAIS_COLOMBIA = "CO";

	public static final String[] MESES = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
			" ;Septiembre", "Octubre", "Noviembre", "Diciembre" };

	// Constantes de métodos de autenticación
	public static final String METODO_AUTENTICACION_INTRANET = "INTRANET";
	public static final String METODO_AUTENTICACION_LDAP = "LDAP";

	// ADM-C-021
	// Correo de notificación al crear usuario
	public static final String ASUNTO_CORREO_NOTIFICACION_CREACION_USUARIO = "Creación de usuario";

	// Constantes de tipo zona geografica
	public static final int TIPO_ZONA_GEOGRAFICA_PAIS = 1;
	public static final int TIPO_ZONA_GEOGRAFICA_DEPARTAMENTO = 2;
	public static final int TIPO_ZONA_GEOGRAFICA_CIUDAD = 3;

	// Correo electronico
	public static final String NOMBRE_ARCHIVO_ADJUNTOS = "Comunicaciones - Adjuntos - %s";

	// CODIGO PREFIJO GESTOR DOCUMENTAL ON BASE
	public static final String CODIGO_PREFIJO_ONBASE = "RUTA_DOC";

	public static final String TIPO_PARAMETRO_GESTOR_DOCU = "GES_DOCU";

	public static Map<String, Object> getProjectProperties(List<String> keys) throws IOException {
		Map<String, Object> olst = new HashMap<String, Object>();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream is = classLoader.getResourceAsStream(PROJECT_PROPERTIES);
		Properties p = new Properties();
		try {
			p.load(is);
			for (String key : keys) {
				Object value = p.getProperty(key);
				olst.put(key, value);
			}
		} catch (Exception io) {
		}
		return olst;
	}

	// Headers
	public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
	public static final String X_EXTRA_HEADER = "X-extra-header";
	public static final String HEADER_FILENAME = "filename";

	public static final String VALOR_UNDEFINED = "undefined";

	public static final String PATTERN_EMAIL = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
	public static final String EXTENSION_XLSX = "xlsx";
	public static final String EXTENSION_XLS = "xls";

	// Constantes para determinar el agendamiento a revisar
	public static final String PRIMERA_AUDIENCIA = "primera audiencia";
	public static final String OTRAS_AUDIENCIAS = "otras audiencias";
	public static final String REPARTO = "reparto";

	// tags de plantilla
	public static final String VALOR_PLANTILLA_NOMBRE_USUARIO = "personaQueGeneraCartaP";
	public static final String VALOR_PLANTILLA_DIRECCION_ENVIO = "direccionEnvio";
	public static final String VALOR_PLANTILLA_TELEFONO_ENVIO = "telefonoEnvio";
	public static final String VALOR_PLANTILLA_RADICADO_CARTA = "radicadoP";
	public static final String VALOR_PLANTILLA_CIUDAD_ENVIO = "ciudadEnvio";
	public static final String VALOR_PLANTILLA_CODIGO_CASO = "codigoCaso";

	// constantes Booleanas
	public static final String TRUE = "true";
	public static final String FALSE = "false";

	// asunto correos
	public static final String ASUNTO_CASOS_CONCILIACION_POR_COBRAR = "Casos de conciliación ordinarios por cobrar";
	public static final String CUERPO_TOTAL_COBRAR_CONCILIADORES = "Valor total a cobrar por todos los conciliadores :";
	public static final String ASUNTO_CORREO_REAPERTURA_CASO = "Reapertura de caso";
	public static final String ASUNTO_DEVOLUCION_CONTROL_LEGALIDAD = "Devolución por control de legalidad";
	public static final String ASUNTO_RADICACION_ESPECIAL = "Radicación petición especial";
	public static final String ASUNTO_TIPO_SOLICITUD_SUSPENSION = "Suspensión de ";
	public static final String ASUNTO_TIPO_SOLICITUD_ADICION_CAMBIO_MATERIA = "Solicitud cambio o adición de especialidad ";
	public static final String ASUNTO_TIPO_SOLICITUD_ACTIVACION = "Activación de ";
	public static final String ASUNTO_FACTURACION_CASOS_CONVENIO = "Casos de convenio a facturar";

	// Constante Busqueda Tabla Persona
	public static final String BUSQUEDA_TABLA_PERSONA = "((CONCAT(p.primer_nombre_o_razon_social,p.segundo_nombre,p.primer_apellido,p.segundo_apellido) like '%?15%' ) or"
			+ "(CONCAT(p.primer_nombre_o_razon_social,p.segundo_nombre) like '%?15%') or"
			+ "(CONCAT(p.primer_apellido,p.primer_nombre_o_razon_social) like '%?15%') or "
			+ "(CONCAT(p.primer_nombre_o_razon_social,p.primer_apellido) like '%?15%')  )";

	// obligaciones
	public static final String COMPROMISO_OBLIGACION_DAR = "Pagar cuota(s)";

	// observaciones cambio estado control de legalidad
	public static final String CAMBIO_ESTADO_CONTROL_LEGALIDAD = "Estado caso pendiente para control de legalidad.";
	public static final String CAMBIO_ESTADO_CONTROL_CALIDAD = "Estado caso pendiente para control de calidad.";
	public static final String CAMBIO_ESTADO_CONTROL_CALIDAD_CERRADO = "Estado caso cerrado control de calidad.";

	// observaciones evento modificacion acta o constancia por motivo de
	// devolucion
	public static final String CAMBIO_ESTADO_CASO_POR_MODIFICACION_ACTA_O_CONSTANCIA = "Se cambia el estado del caso por motivo de modificación de un acta o constancia de resultado de audiencia.";

	public static final String CAMBIO_ESTADO_CASO_POR_PROGRAMACION_AUDIENCIA = "Se cambia el estado del caso por motivo de programación de audiencia.";

	// observaciones evento devolucion conciliador cambio estado del caso
	public static final String CAMBIO_ESTADO_CASO_POR_DEVOLUCION_DOCUMENTO = "Se cambia el estado del caso por motivo de devolución del documento al conciliador.";

	// observaciones evento asignacion de caso a auxiliar
	public static final String CASO_SIN_ASIGNACION_DE_AUXILIAR = "Caso sin asignación de auxiliar";
	
	public static final String CASO_SIN_ASIGNACION_DE_SECRETARIA_CONCILIACION = "Caso sin asignación de secretaria de conciliación";


	// tipos de documentos
	public static final String TIPO_DOCUMENTO_DEL_ACTA = "del Acta";
	public static final String TIPO_DOCUMENTO_DE_LA_CONSTANCIA = "de la Constancia";

	// servicio sirep
	public static final String PUP_USUARIO_ID = "TV1";
	public static final String PUP_SERVICIO_ID = "SERV";
	public static final Integer PUP_SOLICITUD_APLICATIVO_ID = 12122;
	public static final String PUP_ID_SN = "0522";
	public static final String PUP_ID_SUCURSAL_LIQUIDA = "43";

	public static final String PUP_TIPO_PARAMETRO = "PARAMETROS_PUP";
	public static final String PUP_DATA_ADICIONAL = "CAC";

	/* Constantes de parametros de reempazo en alertas */
	public static final String PARAM_ALERTA_CODIGO_CASO = "[CODIGO_CASO]";
	public static final String PARAM_ALERTA_NOMBRE_CASO = "[NOMBRE_CASO]";
	public static final String PARAM_ALERTA_NOMBRE_CENTRO = "[NOMBRE_CENTRO]";
	public static final String PARAM_ALERTA_PLAZO_CONTESTACION = "[PLAZO_CONTESTACION]";
	public static final String PARAM_ALERTA_NOMBRE_AUXILIAR = "[NOMBRE_AUXILIAR]";
	public static final String PARAM_ALERTA_PARAMETROS_HORAS_ACEPTACION = "[PARAMETRO_HORAS_ACEPTACION]";
	public static final String PARAM_ALERTA_PARAMETROS_FECHA_AUDIENCIA = "[FECHA_AUDIENCIA]";
	public static final String PARAM_ALERTA_PARAMETROS_NUMERO_DIAS_TRANSCURRIDOS = "[NUMERO_DIAS_TRANSCURRIDOS]";
	public static final String PARAM_ALERTA_TABLA = "[TABLA]";
	public static final String PARAM_ALERTA_NOMBRE_CONVENIO = "[NOMBRE_CONVENIO]";
	public static final String PARAM_ALERTA_FECHA_RADICACION = "[FECHA_RADICACION]";
	public static final String PARAM_ALERTA_TIEMPO_CIERRE = "[TIEMPO_CIERRE]";
	public static final String PARAM_ALERTA_TABLA_CIERRES = "[TABLA_CIERRES]";
	public static final String PARAM_ALERTA_CANTIDAD_CARTAS = "[CANTIDAD_CARTAS]";
	public static final String PARAM_ALERTA_TABLA_CARTAS = "[TABLA_CARTAS]";
	public static final String PARAM_ALERTA_NOMBRE_ANALISTA_ASIGNADO = "[ANALISTA_ASIGNADO]";
	public static final String PARAM_ALERTA_FECHA_FIN_SUSPENSION = "[FECHA_FIN_SUSPENSION]";
    public static final String PARAM_ALERTA_NUMERO_HORAS = "[NUMERO_HORAS]";
    public static final String PARAM_ALERTA_TIPO_PETICION = "[TIPO_PETICION]";
    public static final String PARAM_ALERTA_TABLA_SORTEOS = "[TABLA_SORTEOS]";
    public static final String PARAM_ALERTA_TABLA_SIN_COMUNICACION = "[TABLA_SIN_COMUNICACION]";
    public static final String PARAM_ALERTA_TABLA_SIN_ACTUALIZACION = "[TABLA_SIN_ACTUALIZACION]";
    public static final String PARAM_ALERTA_TABLA_CASOS = "[TABLA_CASOS]";
    public static final String PARAM_ALERTA_NUMERO_DIAS = "[NUMERO_DIAS]";
    public static final String PARAM_ALERTA_TIPO_AUDIENCIA = "[TIPO_AUDIENCIA]";
    public static final String PARAM_ALERTA_TABLA_DESIGNACION = "[TABLA_DESIGNACION]";
    public static final String PARAM_ALERTA_NOMBRE_SECRETARIO = "[NOMBRE_SECRETARIO]";
    public static final String PARAM_ALERTA_FECHA_PLAZO_CONSIGNACION = "[FECHA_PLAZO_CONSIGNACION]";
    public static final String PARAM_ALERTA_ROL_NO_PAGO = "[ROL_NO_PAGO]";
    public static final String PARAM_ALERTA_FECHA_INSTALACION = "[FECHA_INSTALACION]";
    public static final String PARAM_ALERTA_NUMERO_ORDEN = "[NUMERO_ORDEN]";
    public static final String PARAM_ALERTA_ROL = "[ROL]";
    public static final String PARAM_ENLACE = "[ENLACE]";
    public static final String PARAM_CODIGO_CASO = "[CODIGO_CASO]";
    public static final String PARAM_NOMBRE_CASO = "[NOMBRE_CASO]";
    public static final String PARAM_FECHA_ACTUAL = "[FECHA_ACTUAL]";
    public static final String PARAM_ALERTA_TIPO_SERVICIO = "[TIPO_SERVICIO]";
	public static final String PARAM_ALERTA_TIPO_MATERIA = "[TIPO_MATERIA]";
	public static final String PARAM_ALERTA_TIPO_LISTA = "[TIPO_LISTA]";
	public static final String PARAM_ALERTA_TABLA_OPERADORES_LIBERADOS = "[TABLA_OPERADORES_LIBERADOS]";
	public static final String PARAM_ALERTA_NOMBRE_ABOGADO = "[NOMBRE_ABOGADO]";
	public static final String PARAM_ALERTA_TABLA_CON_EQUIDAD_REC = "[TABLA_CON_EQUIDAD_RECHAZO]";
    
	/* Constantes estilos tabla alerta */
	public static final String PARAM_ALERTA_TABLA_ENCABEZADO = "<html><head> <style> td {padding-left:5px;padding-right:5px;padding-top:1px;padding-bottom:1px;font-size:11pt;}</style></head><body><table cellpadding=0 cellspacing=0 border=1>";
	public static final String PARAM_ALERTA_TABLA_CIERRE = "</table></body></html>";
	public static final String PARAM_ALERTA_TABLA_TEXTO_ENCABEZADO_CONRADD = "<tr><td  width= 100px><b>Código del caso</b></td><td><b>Nombre del caso</b></td></tr>";
	public static final String PARAM_ALERTA_TABLA_TEXTO_ENCABEZADO_RADAPOS = "<tr><td  width= 100px><b>Código del caso</b></td><td><b>Nombre del caso</b></td><td><b>Nombre de la persona que radicó el caso</b></td></tr>";
	public static final String PARAM_ALERTA_TABLA_TEXTO_ENCABEZADO_VENMEMB = "<tr><td  width= 100px><b>Nombre del conciliador</b></td><td><b>Número de días para vencimiento</b></td></tr>";
	public static final String PARAM_ALERTA_TABLA_TEXTO_ENCABEZADO_CERAU = "<tr><td  width= 100px><b>Código del caso</b></td><td><b>Nombre del caso</b></td><td><b>Fecha audiencia</b></td><td><b>Centro</b></td><td><b>Días hábiles</b></td></tr>";
	
	/*
	 *Constantes codigo alertas  
	 */
	public static final String COD_ALERTA_SOBRE_ASIGNACION_MIN_TRANSCRIPCION = "SOBASGM";
	public static final String COD_ALERTA_FIJACION_AUDIENCIA_PRIMERA_TRAMITE = "FIJAUT";
	
	// codigo estrato cero
	public static final String CODIGO_ESTRATO_CERO = "ES0";

	// Carta tipo acta o constancia
	public static final String TEXTO_CARTA_ACTA_CONSTANCIA = "Acta o constancia";

	// parametros ejecucion alerta VENMEMB
	public static final Long PARAM_ALERTA_VENMEMB_DIA_MINIMO_EJECUCION = 20L;
	public static final Long PARAM_ALERTA_VENMEMB_DIA_MAXIMO_EJECUCION = 30L;
	
	//parametros tiempo estudio arbitraje SINTRA1, SINTRA2
    public static final Long PARAM_ALERTA_TIEMPO_ESTUDIO_CASO_ARBITRAJE = 16L;
    
    //Tipo parametro general minutos maximos transcripcion
    public static final String TIPO_MINUTOS_MAXIMOS_TRANSCRIPCION = "MINUTOS_MAXIMO_TRANSCRIPCION";
    
    // Roles asociados a carta PCADP    
    public static final String ROLES_PCADP = "ROLES_PCADP";
    public static final String ROL_PCADP_ABOGADO = "PCADPABO";
    public static final String ROL_PCADP_JARBITRAJE = "PCADPJAR";
 
    // Envio de correos
    public static final String URL_ENVIO_CORREOS = "http://correos.ccb.org.co/";
    
    public static final String TIPO_PARAMETRO_VISUALIZACION_SORTEO = "VISORT";
    public static final String TIPO_PARAMETRO_VISUALIZACION_ELEGIBLES = "VISENL";

    /**
     * Tags para reemplazar en asunto de cartas
     */
    public static final String TAG_ASUNTO_ID_CARTA = "#carta#";
    public static final String TAG_ASUNTO_ID_CASO = "#caso#";
    public static final String TAG_ASUNTO_NOMBRE_CASO = "#nombre#";
    public static final String TAG_ASUNTO_NOMBRE_ROL = "#rol#";
    public static final String TAG_ASUNTO_NOMBRE_PLANTILLA = "#plantilla#";
    public static final String TAG_ASUNTO_NOMBRE_DOCUMENTO = "#nombreDocumento#";
    public static final String TAG_ASUNTO_URL_DOCUMENTO = "#urlDocumento#";
    public static final String TAG_ASUNTO_TIPO_AUDIENCIA = "#audiencia#";
    
    
    public static final String PARAMETRO_CODIGO_URL_ACCESO = "URL_SIMA";

    public static final Integer LIMITE_ASUNTO_CORREO = 128;
    
	// Plazo Re-Programacion            
    public static final Integer LIMITE_REPROGRAMACION_DIAS = 90;
	
	// Generación de clave
	public static final String URL_HIPERVINCULO_GENERAR_CLAVE = "URL_CLAV";
	public static final String TIPO_URL_HIPERVINCULO_GENERAR_CLAVE = "HIPERVINCULO_SIMASC_CLAVE";
	
	// Constantes para consumo del servicio de Certicamara de Clave Segura
	public static final String LLAVE_PARAMETRO_TIPO_DOCUMENTO = "txtTipoDocumento=";
	public static final String LLAVE_PARAMETRO_NUMERO_DOCUMENTO = "&txtNumDocumento=";
	public static final String LLAVE_PARAMETRO_NOMBRES = "&txtNombres=";
	public static final String LLAVE_PARAMETRO_PRIMER_APELLIDO = "&txtPrimerApellido=";
	public static final String LLAVE_PARAMETRO_SEGUNDO_APELLIDO = "&txtSegundoApellido=";
	public static final String LLAVE_PARAMETRO_TOKEN_ENTRADA = "&txtTokenEntrada=";
	public static final String LLAVE_PARAMETRO_APLICACION_ID = "&txtAplicacionId=";
	public static final String LLAVE_VALOR_PARAMETRO_OLVIDO = "&olvido=0";
	public static final String LLAVE_PARAMETRO_TELEFONO_MOVIL = "&telefonoMovil=";
	public static final String LLAVE_PARAMETRO_PAGINAS = "&paginas=";
	public static final String LLAVE_PARAMETRO_VALIDACION = "&validacion=";
	public static final String LLAVE_PARAMETRO_SESSION_ID = "&sessionId=";
	
	public static final String CODIGO_INDICATIVO_COLOMBIA = "57";
	
	public static final String METODO_HTTP_POST = "POST";
	public static final String LLAVE_ENCABEZADO_HTTP_AUTORIZACION = "Authorization";
	public static final String LLAVE_ENCABEZADO_HTTP_TIPO_CONTENIDO = "Content-Type";
	public static final String LLAVE_ENCABEZADO_HTTP_LONGITUD_CONTENIDO = "Content-Length";
	public static final String LLAVE_ENCABEZADO_HTTP_CONTENIDO_PROCESAR = "Accept";
	public static final String LLAVE_ENCABEZADO_HTTP_AGENTE_USUARIO = "User-Agent";
	public static final String VALOR_ENCABEZADO_HTTP_TIPO_CONTENIDO = "application/x-www-form-urlencoded";
	public static final String VALOR_ENCABEZADO_HTTP_CONTENIDO_PROCESAR = "*/*";
	public static final String VALOR_ENCABEZADO_HTTP_AGENTE_USUARIO = "http_requester/0.1";
	
	// Constantes para la función de obtención de una dirección MAC a traves de
	// su IP
	public static final String LLAVE_ENCABEZADO_HTTP_DIRECCION_IP_ORIGEN = "X-Forwarded-For";
	public static final String PROPIEDAD_NOMBRE_SISTEMA_OPERATIVO = "os.name";
	public static final String EXP_REG_DIRECCION_MAC_WIN = "[0-9a-f]+-[0-9a-f]+-[0-9a-f]+-[0-9a-f]+-[0-9a-f]+-[0-9a-f]+";
	public static final String EXP_REG_DIRECCION_MAC_UNIX = "[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+";
	public static final String INDICADOR_SISTEMA_OPERATIVO_WINDOWS = "win";

	// Codigo id rol Conciliador
	public static final String ID_ROL_CONCILIADOR= "52";
	public static final  long ID_ROL_CONVOCANTE = 101;
	public static final  long  ID_ROL_CONVOCADO =102;
	public static final  long ID_ROL_APODERADO_CONVOCANTE = 103;
	public static final  long ID_ROL_APODERADO_CONVOCADO = 104;
	public static final  long ID_ROL_ARBITRO = 1L;
	public static final  long ID_ROL_ARBITRO_SOCIAL = 62L;
	public static final  long ID_ROL_ARBITRO_INTERNACIONAL = 38L;
	public static final  long ID_ROL_AMIGABLE_COMPONEDOR = 39L;
	public static final  long ID_ROL_PERITO = 40L;
	public static final  long ID_ROL_ARBITRO_RECUPERACION = 126L;
	public static final  long ID_ROL_SOLICITANTE = 106L;
	public static final  long ID_ROL_CONCILIADOR_EQUIDAD= 61L;
	
	public static final String PARAMETRO_CODIGO_URL_AUT_TOKEN_JWT = "URAUTOKENJWT";
	public static final String PARAMETRO_CODIGO_URL_TOKEN_JWT = "URLTOKENJWT";
	public static final String PARAMETRO_CODIGO_AUT_TOKEN_JWT = "AUTTOKENJWT";
	public static final String PARAMETRO_CODIGO_PAT_TOKEN_JWT = "PATTOKENJWT";
	public static final String PARAMETRO_CODIGO_URL_SERV_SAVE = "URLSERVSAVE";
	public static final String PARAMETRO_CODIGO_URL_SERV_READ ="URLSERVREAD";
	public static final String PARAMETRO_CODIGO_URL_SERV_CASES = "URLSERVCASES";//urlserver/cases
	public static final String PARAMETRO_CODIGO_URLWSONBASE = "URLWSONBASE";
	public static final String PARAMETRO_CODIGO_URL_RECIBO_PAGO ="URLRECPAGO";
	public static final String PARAMETRO_CODIGO_URL_SITE_VERIFY_CAPTCHA ="URLVERIFYCAP";
	public static final String PARAMETRO_CODIGO_KEY_SECRET_CAPTCHA ="SECRETCAP";
	
	//creacion audiencia mediacion
	public static final int CANT_FUNCIONARIOS_PRINCIPALES_MEDIACION = 1; 
	public static final int CANT_FUNCIONARIOS_SUPLENTES_MEDIACION = 0; 
	public static final boolean PRESELECCION_MEDIACION = false;
	public static final long DIAS_MAXIMOS_VIGENCIA_MEDIACION = 5L;
	
	public static final long ID_ALERTA_MEDIACION = 51L;
	
	//Tipo de servicio para obtener header y footer de la plantilla
	public static final String TIPO_SERVICIO_ANY = "ANY";
	public static final String HEADER_MAIL = "HEAMAIL";
	public static final String FOOTER_MAIL = "FOOMAIL";
	
	//nombre constantes parameto de servicio
	public static final String TIEMPO_MAX_PRONUNCIAMIENTO_CASO = "TIEMPO_MAX_PRONUNCIAMIENTO_CASO";
	public static final String TIPO_PARAMETRO_TIEMPO_PRONUNCIAMIENTO_CASO = "TMAX_PRC";
	public static final String NOTIFICACION_ROL_CARTA_PCDRA = "NOTIFICACION_ROL_CARTA_PCDRA";
	public static final String TIPO_PARAMETRO_NOTIFICACION_ROL_CARTA= "NOTPCDRA";
	public static final String DEMANDANTES = "DEMANDANTES";
	public static final String TIPO_PARAMETRO_ROL_DEMANDANTES = "ROL_DTES";
	public static final String DEMANDADOS = "DEMANDADOS";
	public static final String TIPO_PARAMETRO_ROL_DEMANDADOS = "ROL_DDOS";	
	public static final String ENLACE = "ENLACE";
	public static final String APLICA_NOTIFICACION_ARBITRO = "APLICA_NOTIFICACION_ARBITRO";
	public static final String TIPO_PARAMETRO_APLICA_NOTIFICACION_ARBITRO = "APNOTARB";
	public static final String APLICA_NOTIFICACION_VENCIMIENTO_ORDEN = "APLICA_NOTIFICACION_VENCIMIENTO_ORDEN";
	public static final String TIPO_PARAMETRO_APLICA_NOTIFICACION_VENCIMIENTO_ORDEN = "APNOTVEN";
	public static final String ESTADOS_ARBITRO_SOCIAL = "ESTADOS_ARBITRO_SOCIAL";
	public static final String TIPO_PARAMETRO_ROL_ARBITRO_SOCIAL = "ROLARBSO";
	public static final String FECHA_APROBACION = "FECHA_APROBACION";
	public static final String NOTIFICACION_ROL_CARTA_MED_CAUTELARES = "NOTIFICACION_ROL_CARTA_MED_CAUTELARES";
	public static final String INDICADOR_PROGRAMA_DESIGNA = "INDICADOR_PROGRAMA_DESIGNA";
	public static final String TIPO_PARAMETRO_PERSONAS_CREACION_USUARIO = "CRE_USU";
	public static final String PERSONAS_CREACION_USUARIO = "PERSONAS_CREACION_USUARIO";
	public static final String NOM_SERVICIO_PLANTILLA_NOTCREUSR = "NOM_SERVICIO_PLANTILLA_NOTCREUSR";
	public static final String TIPO_PARAMETRO_NOMBRE_SERVICIO_PLANTILLA_NOTCREUSR = "NOM_SERV";
	
	//Parametros notificacion creacion usuario parte
	public static final String NOMBRE_PLANTILLA_NOTIFICACION_GENERACION_CLAVE = "NOCREUSR";
	public static final String NOMBRE_PLANTILLA_NOTIFICACION_SIN_GENERACION_CLAVE = "NOUSRSNC";
	public static final String PARAMETRO_DEMANDANTES = "demandantesP";
	public static final String PARAMETRO_DEMANDADOS = "demandandosP";
	public static final String PARAMETRO_CLAVE = "claveP";
	public static final String PARAMETRO_ENLACE = "enlaceP"; 
	public static final String PARAMETRO_CASO = "casoP"; 
	public static final String PARAMETRO_NOMBRE_SERVICIO = "nomservicioP"; 
	public static final String ASUNTO_NOTIFICACION_USUARIO = "Radicación arbitraje caso casoP entre demandantesP y demandandosP";
	
	//Parametros notificacion radicar documento
	public static final String NOMBRE_PLANTILLA_RADICAR_DOCUMENTO_CAC= "RADDOC";
	public static final String NOMBRE_DOCUMENTO= "nombreDocumento";
	public static final String ID_CASO= "idCaso";
	public static final String NOMBRE_CASO= "nombreC";
	public static final String NOMBRE_PERSONA= "persona";
	
	public static final String CODIGO_ALERTA_NOTIFICACION_ARBITRO = "NOTVENAR";
	
	public static final String MOTIVO_CAMBIO_ESTADO_ARBITRO_SORTEO = "Cambio de estado por asignación de caso entre 400 y 500 salarios";
	
	public static final String ID_PLANTILLA_CARTA_DOCUMENTOS_RADICADOS = "12";
	
	public static final String NOMBRE_PLANTILLA_CARTA_DOCUMENTOS_RADICADOS = "Radicación de caso";
	
	public static final Long ID_SIN_MATERIA = 188L;
	
	public static final String NOMBRE_CASO_ARBITRAJE_INTERNACIONAL = "Caso de arbitraje internacional";
	
	public static final String URL_ACCESO_MAUC = "URL_ACCESO_MAUC";
	
	public static final String URL_LOGIN_SIMASC = "URL_LOGIN_SIMASC";
	
	public static final String CODIGO_PLATAFORMA_MAUC = "CODIGO_PLATAFORMA_MAUC";
	
	public static final String URL_CREAR_USUARIO_MAUC = "URL_CREAR_USUARIO_MAUC";
	
	public static final String URL_CLAVE_MAUC = "URL_CLAVE_MAUC";
	

	public static final String RES_MAUC_USUARIO_EXISTE = "El usuario ya se encuentra registrado";
	public static final String VAL_SERVIDOR_COOKIE = "VAL_SERVIDOR_COOKIE";
	public static final String DOMINIO_SERVIDOR = "DOMINIO_SERVIDOR";
	
	//Pamarametros URL inicio de sesion
	
	public static final String PARAMETRO_PLATAFORMA = "Pplataforma";
	public static final String PARAMETRO_TIPO_DOCUMENTO = "PtipoDocumento"; 
	public static final String PARAMETRO_NUMERO_DOCUMENTO = "PnumeroDocumento"; 
	
	public static final String ATRIBUTO_JSON_TOKEN_MAUC = "username";
	public static final String PREADMISION = "Preadmisión";
	
	public static final String ALGORITMO_SEMILLA = "SHA1PRNG";
		
	public UtilConstantes(){}

}