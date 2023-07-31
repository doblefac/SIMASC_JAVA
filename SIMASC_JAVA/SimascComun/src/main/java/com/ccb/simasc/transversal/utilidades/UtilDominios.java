package com.ccb.simasc.transversal.utilidades;

/**
 * Clase con los nombres de los dominios definidos en la tabla dominio y los
 * cÃ³digos respectivos cada elemento dentro de cada uno de los dominios.
 * 
 * @author jsoto
 *
 */
public class UtilDominios {

	private UtilDominios() {
	}

	public static final String APLICACION_TIPO_FUNCIONALIDAD_ANGULAR = "ANGULAR";
	public static final String TIPO_FUNCIONALIDAD_SORTEO = "SOR";

	/**
	 * 
	 */
	public static final String DOMINIO_NOMBRE_PLANTILLA_CARTA = "NOMBRE_PLANTILLA_CARTA";
	public static final String NOMBRE_PLANTILLA_CARTA_SOLICITUD_TERCEROS = "PCSTD";
	public static final String NOMBRE_PLANTILLA_CARTA_CONSTANCIA_ASISTEN_PARTES = "PCCNP";
	public static final String NOMBRE_PLANTILLA_CARTA_DOCUMENTOS_RADICADOS = "PCDRA";
	public static final String NOMBRE_PLANTILLA_CARTA_PRONUNCIAMIENTO_ARBITROS = "PCPAR";
	public static final String NOMBRE_PLANTILLA_CARTA_CIERRE_CASO = "PCCIC";
	public static final String NOMBRE_PLANTILLA_CARTA_AUDIENCIAS_DIFERENTES_PRIMERA = "PCADP";
	public static final String NOMBRE_PLANTILLA_CARTA_PRIMERA_AUDIENCIA = "PCPAU";
	public static final String NOMBRE_PLANTILLA_CARTA_PRIMERA_AUDIENCIA_EQUIDAD_CONVOCADO = "PCPAUCON";
	public static final String NOMBRE_PLANTILLA_CARTA_PRIMERA_AUDIENCIA_EQUIDAD_SOLICITANTE = "PCPAUSOL";
	public static final String NOMBRE_PLANTILLA_CARTA_NOTIFICACION_ARBITRO = "PCNAR";
	public static final String NOMBRE_PLANTILLA_CARTA_NOTIFICACION_SECRETARIO = "PCNSE";
	public static final String NOMBRE_PLANTILLA_CARTA_SOLICITUD_TERCERO_DESIGNACION = "PCSTD";
	public static final String NOMBRE_PLANTILLA_CARTA_FORMATO_GENERICO_DIRIGIDO_ARBITROS = "PCDRA";
	public static final String NOMBRE_PLANTILLA_CARTA_FORMATO_GENERICO_ACTAS = "PCFGA";
	public static final String NOMBRE_PLANTILLA_CARTA_FORMATO_GENERICO_DIRIGIDA_ARBITROS = "PCFGDRA";
	public static final String NOMBRE_PLANTILLA_CARTA_FORMATO_GENERICO_DIRIGDO_PARTES = "PCFGDP";
	public static final String NOMBRE_PLANTILLA_CARTA_REQUERIMIENTOS_A_PARTES = "PCRAP";
	public static final String NOMBRE_PLANTILLA_CARTA_NOTIFICACION_ACEPTACION_ARBITROS = "PCCAA";
	public static final String NOMBRE_PLANTILLA_CARTA_ENTREGA_EXPEDIENTE_ARBITRO_RECUPERACION_EMPRESARIAL = "PCEARRM";
	public static final String NOMBRE_PLANTILLA_CARTA_NOTIFICACION_ASIGNACION_CONCILIADOR = "PCNAC";
	public static final String NOMBRE_PLANTILLA_CARTA_NOTIFICACION_COMUNICACION_CONCILIADOR_INSOLVENCIA = "PCNCI";
	
	
	public static final String NOMBRE_PLANTILLA_CARTA_NOTIFICACION_ARBITRO_INTERNACIONAL = "PCNARI";
	public static final String NOMBRE_PLANTILLA_CARTA_NOTIFICACION_ARBITRO_RECUPERACIONL = "PCNARREC";
	public static final String NOMBRE_PLANTILLA_CARTA_DEVOLUCION_DE_DINERO = "DEVODIN";
	public static final String NOMBRE_PLANTILLA_CARTA_RELIQUIDACION = "RELIQUID";

	
	public static final String DOMINIO_CLASIFICADOR_ACTAS_CONSTANCIAS = "PLANTILLAS_ACTCON";
	public static final String CODIGO_CLASIFICADOR_ACTAS_CONSTANCIAS = "AGRACTCO";
	/**
	 * 
	 */
	public static final String DOMINIO_CUADERNOS_DOCUMENTOS = "CUADERNOS_DOCUMENTOS";
	public static final String CUADERNOS_DOCUMENTOS_GASTOS = "GAS";
	public static final String CUADERNOS_DOCUMENTOS_OTROS = "OTR";
	public static final String CUADERNOS_DOCUMENTOS_PRINCIPAL = "PRI";
	public static final String CUADERNOS_DOCUMENTOS_PRUEBAS = "PRU";
	
	/**
	 * 
	 */
	public static final long ID_CUADERNO_PRINCIPAL = 1;
	public static final long ID_CUADERNO_PRUEBAS = 2;
	public static final long ID_CUADERNO_ADMINISTRACION_GASTOS = 3;
	public static final long ID_CUADERNO_MEDIDAS_CAUTELARES = 4;
	public static final long ID_CUADERNO_COMUNICACIONES_SISTEMA = 8;
	public static final long ID_CUADERNO_RADICACION_DE_DOCUMENTOS_CAC = 9;


	/**
	 * Representa los dias que se puede realizar sorteo
	 */
	public static final String DOMINIO_DIAS_SORTEOS = "DIAS_SORTEOS";
	public static final String DIAS_SORTEOS_JUEVES = "JUE";
	public static final String DIAS_SORTEOS_LUNES = "LUN";
	public static final String DIAS_SORTEOS_MARTES = "MAR";
	public static final String DIAS_SORTEOS_MIERCOLES = "MIE";
	public static final String DIAS_SORTEOS_VIERNES = "VIE";
	public static final String DIAS_SORTEOS_SABADO = "SAB";
	public static final String DIAS_SORTEOS_DOMINGO = "DOM";

	/**
	 * 
	 */
	public static final String DOMINIO_DISPONIBILIDAD = "DISPONIBILIDAD";
	public static final String DISPONIBILIDAD_NO = "NO";
	public static final String DISPONIBILIDAD_SI = "SI";

	/**
	 * 
	 */
	public static final String DOMINIO_ENTIDAD_TARJETA_PROFESIONAL = "ENTIDAD_TARJETA_PROFESIONAL";

	/**
	 * 
	 */
	public static final String DOMINIO_ESCOLARIDADES = "ESCOLARIDAD";

	public static final String EDUCACION_DE_LA_PRIMERA_INFANCIA = "EDPI";
	public static final String EDUCACION_BASICA_PRIMARIA = "EDBP";
	public static final String EDUCACION_BASICA_SECUNDARIA_O_SECUNDARIA_BAJA = "EDBS";
	public static final String DOCTORADO_O_EQUIVALENTE = "EDE";
	public static final String EDUCACION_TECNICA_PROFESIONAL_Y_TECNOLOGICA = "EDPT";
	public static final String ESPECIALIZACION_MAESTRIA_O_EQUIVALENTE = "EME";
	public static final String EDUCACION_MEDIA_O_SECUNDARIA_ALTA = "EMSA";
	public static final String EDUCACION_POSTSECUNDARIA_NO_SUPERIOR = "EPSNS";
	public static final String UNIVERSITARIO_O_EQUIVALENTE = "EUE";
	public static final String NINGUNA = "NIN";
	public static final String NO_INFORMA = "NOIF";

	/**
	 * 
	 */
	public static final String DOMINIO_ESTADO_AUDIENCIA = "ESTADO_AUDIENCIA";
	public static final String ESTADO_AUDIENCIA_APLAZADA = "APL"; // Audiencia
																	// Finalizada
	public static final String ESTADO_AUDIENCIA_CANCELADA = "CAN"; // Audiencia
																	// Cancelada
	public static final String ESTADO_AUDIENCIA_PENDIENTE = "PEN"; // Audiencia
																	// pendiente
																	// o por
																	// realizar
	public static final String ESTADO_AUDIENCIA_REALIZADA = "REA";// Audiencia
																	// Realizada
	public static final String ESTADO_AUDIENCIA_SUSPENDIDA = "SUS";// Audiencia
																	// Suspendida
	
	public static final String ESTADO_AUDIENCIA_ELIMINADA = "INA";

	/**
	 * 
	 */
	public static final String DOMINIO_ESTADO_CASO_PADRE = "EST_CASO_PADRE";
	public static final String DOMINIO_ESTADO_CASO = "ESTADO_CASO";
	public static final String ESTADO_CASO_CANCELADO = "CAN";
	public static final String ESTADO_CASO_CERRADO = "CER";
	public static final String ESTADO_CASO_CREADO = "CRE";
	public static final String ESTADO_CASO_RADICADO = "CRE";
	public static final String ESTADO_CASO_ESPERA_DE_SORTEO = "ESP";
	public static final String ESTADO_CASO_INTERRUMPIDO = "INT";
	public static final String ESTADO_CASO_PAGADO = "PAG";
	public static final String ESTADO_CASO_EN_PROCESO = "PRO";
	public static final String ESTADO_CASO_SORTEADO = "SOR";
	public static final String ESTADO_CASO_SUSPENDIDO = "SUS";
	public static final String ESTADO_CASO_SUSPENDIDO_POR_REQUERIMIENTO = "SPQ";
	public static final String ESTADO_CASO_CONTESTACION_DEMANDA = "CONDE";
	public static final String ESTADO_CASO_EN_CREACION = "ENCRE";

	/**
	 * Estado caso conciliacion
	 */
	public static final String ESTADO_CASO_PENDIENTE_POR_DESIGNACION = "PENDESIG";
	public static final String ESTADO_CASO_CONCILIADOR_DESIGNADO = "CONDESIG";
	public static final String ESTADO_CASO_FALTA_DE_COMPETENCIA = "FALTCOMP";
	public static final String ESTADO_CASO_EN_CONCILIACION = "ENCONCIL";
	public static final String ESTADO_CASO_DEVUELTO_EN_CONTROL_DE_LEGALIDAD = "DEVCOLEG";
	public static final String ESTADO_CASO_DEVUELTO_EN_CONTROL_DE_CALIDAD = "DEVCOCAL";
	public static final String ESTADO_CASO_ASIGNADO_CONTROL_DE_LEGALIDAD = "CONLEGAL";
	public static final String ESTADO_CASO_PENDIENTE_ENTREGA_DOCUMENTO_RESULTADO = "PENDOCRE";
	public static final String ESTADO_CASO_REABIERTO = "REABIERT";
	public static final String ESTADO_CASO_REGISTRADO = "REGISTRA";
	public static final String ESTADO_CASO_EN_ESTUDIO = "ENESTUD";
	public static final String ESTADO_CASO_ASIGNADO_CONTROL_DE_CALIDAD = "CONCALID";

	public static final String ESTADO_PADRE_CASO_ACTIO = "EST_ACT";
	public static final String ESTADO_PADRE_CASO_INACTIVO = "EST_INA";
	public static final String ESTADO_PADRE_CASO_SUSPENDIDO = "EST_SUS";

		/**
	 * Estado caso insolvencia
	 */
	public static final String ESTADO_CASO_DESISTIMIENTO = "DESIST";
	public static final String ESTADO_CASO_ADMITIDO = "ADMITIDO";
	public static final String ESTADO_CASO_DEVOLUCION = "DEVOLU";
	public static final String ESTADO_CASO_RECHAZADO = "RECHAZA";
	public static final String ESTADO_CASO_INADMITIDO = "INADMI";
	public static final String ESTADO_CASO_PREADMITIDO = "PREADMI";

	/**
	 * 
	 */
	public static final String DOMINIO_ESTADO_FUNC_EXTERNO = "ESTADO_FUNC_EXTERNO";
	public static final String ESTADO_FUNC_EXTERNO_ACTIVO = "ACT";
	public static final String ESTADO_FUNC_EXTERNO_INACTIVO = "INA";

	/**
	 * 
	 */
	public static final String DOMINIO_ESTADO_PERSONA = "ESTADO_PERSONA";
	public static final String ESTADO_PERSONA_ACTIVO = "ACT";
	public static final String ESTADO_PERSONA_INACTIVO = "INA";

	/**
	 * 
	 */
	public static final String DOMINIO_ESTADO_REGISTRO = "ESTADO_REGISTRO";
	public static final String ESTADO_REGISTRO_ACTIVO = "ACT";
	public static final String ESTADO_REGISTRO_INACTIVO = "INA";
	public static final String ESTADO_REGISTRO_ENCREACION = "ENCRE";
	/**
	 * 
	 */
	public static final String DOMINIO_ESTADO_ROL_PERSONA_CASO = "ESTADO_ROL_PERSONA_CASO";
	// designacion
	public static final String ESTADO_ROL_PERSONA_CASO_ACEPTADO = "ACE"; // Acepta
	public static final String ESTADO_ROL_PERSONA_CASO_INACTIVO = "INA"; // Inactivo
																			// por
																			// varios
																			// motivos
	public static final String ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR = "POR";// Designadp
	public static final String ESTADO_ROL_PERSONA_CASO_RECHAZADO = "REC"; // No
																			// acepta
																			// designacion
	public static final String ESTADO_ROL_PERSONA_CASO_RECUSADO = "RCU"; // Recusado
																			// a
																			// la
																			// espera
																			// de
																			// consfirmacion
	public static final String ESTADO_ROL_PERSONA_CASO_COMUNICADO = "COM"; // Recusado
																			// a
																			// la
																			// espera
																			// de
																			// consfirmacion
	public static final String ESTADO_ROL_PERSONA_CASO_NO_APLICA = "NA"; // estado
																			// para
																			// las
																			// partes
	public static final String ESTADO_ROL_PERSONA_CASO_DESIGNADO = "DESG"; // estado
																			// para
																			// los
																			// arbitros
																			// suplentes
	public static final String ESTADO_ROL_PERSONA_CASO_ASIGNADO = "ASG"; // estado
																			// para
																			// los
																			// las
																			// personas
																			// que
																			// son
																			// asignadas por
																			// sorteo y por reparto

	/**
	 * 
	 */
	public static final String DOMINIO_ESTADOS_PAGO_CASO = "ESTADOS_PAGO_CASO";
	public static final String ESTADOS_PAGO_CASO_CASO_ASOCIADO = "ASO";
	public static final String ESTADOS_PAGO_CASO_PENDIENTE_RADICACION = "PER";

	public static final String ESTADOS_PAGO_CASO_ACT = "ACT";

	/**
	 * representa los estados que puede tener un sorteo
	 */
	public static final String DOMINIO_ESTADO_SORTEO = "ESTADO_SORTEO";
	public static final String ESTADO_SORTEO_ESPERA_DE_EJECUCION = "ESP";
	public static final String ESTADO_SORTEO_CANCELADO = "CAN";
	public static final String ESTADO_SORTEO_REALIZADO = "REA";

	/**
	 * 
	 */
	public static final String DOMINIO_ESTRATOS = "ESTRATOS";
	public static final String ESTRATOS_NO_INFORMA = "ES0";
	public static final String ESTRATOS_1 = "ES1";
	public static final String ESTRATOS_2 = "ES2";
	public static final String ESTRATOS_3 = "ES3";
	public static final String ESTRATOS_4 = "ES4";
	public static final String ESTRATOS_5 = "ES5";
	public static final String ESTRATOS_6 = "ES6";

	/**
	 * 
	 */
	public static final String DOMINIO_ETAPA_CASO = "ETAPA_CASO";
	public static final String ETAPA_CASO_PREARBITRAL = "PRE";
	public static final String ETAPA_CASO_ARBITRAL = "ARB";
	public static final String ETAPAS_CONCILIACION = "CONCILIA";

	public static final String DOMINIO_INSTITUCIONES_EDUCATIVAS = "INSTITUCIONES_EDUCATIVAS";

	public static final String DOMINIO_MOTIVO_CIERRE = "MOTIVO_CIERRE";
	public static final String MOTIVO_CIERRE_RETIRO_DE_LA_DEMANDA = "MC1";
	public static final String MOTIVO_CIERRE_DECISION = "MC2";
	public static final String MOTIVO_CIERRE_ACUERDO_DE_LAS_PARTES = "MC3";
	public static final String MOTIVO_CIERRE_ACUERDO_CONCILIATORIO = "MC4";
	public static final String MOTIVO_CIERRE_DESISTIMIENTO = "MC5";
	public static final String MOTIVO_CIERRE_LAUDO = "MC6";
	public static final String MOTIVO_CIERRE_DECISION_JUDICIAL = "MC7";
	public static final String MOTIVO_CIERRE_OTROS = "MC8";

	/**
	 * 
	 */
	public static final String DOMINIO_MOTIVO_NO_COMPETENCIA = "MOTIVO_NO_COMPETENCIA";
	public static final String MOTIVO_NO_COMPETENCIA_COMPETENCIA_DE_LA_CCB = "CCB";
	public static final String MOTIVO_NO_COMPETENCIA_FACTOR_TERRITORIAL = "CFT";
	public static final String MOTIVO_NO_COMPETENCIA_OTRO_CENTRO_DE_ARBITRAJE = "OCA";

	/**
	 * 
	 */
	public static final String METODOS_DE_NOMBRAMIENTO = "METODOS_DE_NOMBRAMIENTO";
	public static final String NOMBRAMIENTO_POR_LAS_PARTES = "NPP";
	public static final String NOMBRAMIENTO_POR_LA_CCB = "NPC";
	public static final String NOMBRAMIENTO_POR_LA_AUTORIDAD_JUDICIAL = "NAJ";
	public static final String NOMBRAMIENTO_POR_UN_TERCERO = "NPT";

	/**
	 * Representa las diferentes nacionalidades deÃ± sistema.
	 */
	public static final String DOMINIO_NACIONALIDAD = "NACIONALIDAD";
	public static final String NACIONALIDAD_ARGENTINO = "ARG";
	public static final String NACIONALIDAD_BRAZILENIO = "BRA";
	public static final String NACIONALIDAD_CANADIENSE = "CAN";
	public static final String NACIONALIDAD_CHILENO = "CHI";
	public static final String NACIONALIDAD_COLOMBIANO = "COL";
	public static final String NACIONALIDAD_COSTARRICENSE = "COS";
	public static final String NACIONALIDAD_DOMINICANO = "DOM";
	public static final String NACIONALIDAD_MEXICANO = "MEX";
	public static final String NACIONALIDAD_PANAMENIO = "PAN";
	public static final String NACIONALIDAD_PARAGUAYO = "PAR";
	public static final String NACIONALIDAD_PERUANO = "PER";
	public static final String NACIONALIDAD_URUGUAYO = "URU";
	public static final String NACIONALIDAD_ESTADOUNIDENSE = "USA";
	public static final String NACIONALIDAD_VENEZOLANO = "VEN";

	/**
	 * 
	 */
	public static final String DOMINIO_PAGADOS = "PAGADOS";
	public static final String PAGADOS_SI = "PG1";
	public static final String PAGADOS_NO = "PG2";

	/**
	 * 
	 */
	public static final String DOMINIO_PROFESIONES = "PROFESIONES";
	public static final String PROFESIONES_COMPRADOR = "PF1";
	public static final String PROFESIONES_INGENIERO_DE_SISTEMAS = "PF2";
	public static final String PROFESIONES_ADMINISTRADOR_DE_EMPRESAS = "PF3";

	/**
	 * 
	 */
	public static final String DOMINIO_PRONUNCIAMIENTOS = "PRONUNCIAMIENTOS";
	public static final String PRONUNCIAMIENTOS_ACEPTA = "PAC";
	public static final String PRONUNCIAMIENTOS_DECLINA = "PDE";
	public static final String PRONUNCIAMIENTOS_NO_SE_PRONUNCIA = "PNP";

	// Resultados audiencia para designacion
	public static final String DOMINIO_RESULTADO_AUDIENCIA_DESIGNACION = "RESULTADO_AUDIENCIA_DESIGNACION";
	public static final String DOMINIO_RESULTADO_AUDIENCIA_DESIGNACION_ASIS_UNA_PARTE_NUEVA_FECHA_AUDIENCIA = "DAPFFNA";// DAPFFNA
																														// *
																														// Asiste
																														// una
																														// parte
																														// â€“
																														// Fija
																														// fecha
																														// para
																														// una
																														// nueva
																														// audiencia
	public static final String DOMINIO_RESULTADO_AUDIENCIA_DESIGNACION_ASIS_UNA_PARTE_ARBITROS_POR_SORTEO = "DAPANPS";// DAPANPS
																														// *
																														// Asiste
																														// una
																														// parte
																														// â€“
																														// Los
																														// Ã¡rbitros
																														// se
																														// nombrarÃ¡n
																														// por
																														// sorteo
	public static final String DOMINIO_RESULTADO_AUDIENCIA_DESIGNACION_ASIS_UNA_PARTE_JUEZ_SELECCIONA = "DAPJCNA";// DAPJCNA
																													// *
																													// Asiste
																													// una
																													// parte
																													// â€“
																													// Se
																													// acude
																													// a
																													// Juez
																													// Civil
																													// para
																													// nombrar
																													// los
																													// Ã¡rbitros
	public static final String DOMINIO_RESULTADO_AUDIENCIA_DESIGNACION_ASIS_DOS_PARTES_DESIGNAN_ARBITROS = "DADPDESA";// DADPDESA
																														// *
																														// Asisten
																														// las
																														// dos
																														// partes
																														// â€“
																														// Designan
																														// Ã¡rbitros
	public static final String DOMINIO_RESULTADO_AUDIENCIA_DESIGNACION_ASIS_DOS_PARTES_ARBITROS_POR_SORTEO = "DADPANPS";// DADPANPS
																														// *
																														// Asisten
																														// las
																														// dos
																														// partes
																														// â€“
																														// Los
																														// Ã¡rbitros
																														// se
																														// nombrarÃ¡n
																														// por
																														// sorteo
	public static final String DOMINIO_RESULTADO_AUDIENCIA_DESIGNACION_ASIS_DOS_PARTES_JUEZ_SELECCIONA = "DADPJCNA";// DADPJCNA
																													// *
																													// Asisten
																													// las
																													// dos
																													// partes
																													// â€“
																													// Se
																													// acude
																													// a
																													// Juez
																													// Civil
																													// para
																													// nombrar
																													// los
																													// Ã¡rbitros
	public static final String DOMINIO_RESULTADO_AUDIENCIA_DESIGNACION_SUSPENDIDA = "DADPSESA";// DADPSESA
																								// *
																								// Asisten
																								// las
																								// dos
																								// partes
																								// â€“
																								// Se
																								// suspende
																								// la
																								// audiencia
	public static final String DOMINIO_RESULTADO_AUDIENCIA_DESIGNACION_NO_ASISTEN_PARTES = "DNOALPAR";// DNOALPAR
																										// *
																										// No
																										// asisten
																										// las
																										// partes

	// Resultados audiencia para instalacion
	public static final String DOMINIO_RESULTADO_AUDIENCIA_INSTALACION = "RESULTADO_AUDI_INSTALACION";

	public static final String RESULTADO_AUDIENCIA_INSTALACION_INSTALACION = "IINS";// INSTALACION
	public static final String RESULTADO_AUDIENCIA_INSTALACION_ADMITE = "IADMI";// ADMITE
	public static final String RESULTADO_AUDIENCIA_INSTALACION_INADMITE = "IINAD";// INADMITE
	public static final String RESULTADO_AUDIENCIA_INSTALACION_RECHAZA = "RECH";// RECHAZA
	public static final String RESULTADO_AUDIENCIA_INSTALACION_FIJA_HONORARIOS = "IFHON";// FIJA
																							// HONORARIOS

	/**
	 * 
	 */
	public static final String DOMINIO_RESULTADO_CASO = "RESULTADO_CASO";
	public static final String RESULTADO_CASO_ARBITRAJE_ARREGLO_DIRECTO = "AAD";
	public static final String RESULTADO_CASO_ARBITRAJE_LAUDO = "ALA";
	public static final String RESULTADO_CASO_ARBITRAJE_NO_CONSIGNACION = "ANC";

	public static final String DOMINIO_RESULTADO_CASO_CONCILIACION = "RESULTADO_CASO_CONCILIACION";
	public static final String RESULTADO_CASO_CONCILIACION_ACUERDO = "CAC";
	public static final String RESULTADO_CASO_CONCILIACION_CANCELACION = "CCN";
	public static final String RESULTADO_CASO_CONCILIACION_ARREGLO_DIRECTO = "CDA";
	public static final String RESULTADO_CASO_CONCILIACION_IMPOSIBILIDAD = "CIM";
	public static final String RESULTADO_CASO_CONCILIACION_INASISTENCIA = "CIN";
	public static final String RESULTADO_CASO_CONCILIACION_FALTA_COMPETENCIA = "FALTCOMP";

	/**
	 * 
	 */
	public static final String DOMINIO_SECTOR_EMPRESA = "SECTOR_DE_LA_EMPRESA";

	/**
	 * 
	 */
	public static final String DOMINIO_SEXOS = "SEXOS";
	public static final String SEXOS_FEMENINO = "FEM";
	public static final String SEXOS_MASCULINO = "MAS";
	public static final String SEXOS_INDEFINIDO = "IND";
	public static final String NOMBRE_DOMINIO_MOTIVO_DE_DECLINACION = "MOTIVO_DE_DECLINACION";
	public static final String NOMBRE_DOMINIO_MOTIVOS_DE_RECHAZO_DE_DESIGNACION = "MOTIVOS_DE_RECHAZO_DE_DESIGNACION";

	// -------------------------------------------------------------------------
	// TIPOS DE AUDIENCIAS
	// -------------------------------------------------------------------------
	/**
	 * Agrupadores de los tipos de audiencias
	 */
	public static final String DOMINIO_AGRUPADOR_TIPO_AUDIENCIA = "AGRUPADOR_TIPO_AUDIENCIA";
	public static final String AGRUPADOR_TIPO_AUDIENCIA_PREARBITRAL = "ATAP";// agrupador_tipo_audiencia
																				// prearbitral
	public static final String AGRUPADOR_TIPO_AUDIENCIA_ARBITRAL = "ATAA";// agrupador_tipo_audiencia
																			// arbitral

	/**
	 * Representa los tipos de audiencias que se pueden realizar
	 */
	public static final String DOMINIO_TIPO_AUDIENCIA = "TIPO_AUDIENCIA";
	//AGRUPADOR_TIPO_AUDIENCIA PREARBITRAL
	public static final String TIPO_AUDIENCIA_PREARBITRAL_DESIGNACION_POR_PARTES = "DPP"; //ReuniÃ³n de designaciÃ³n por las partes
	public static final String TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_DE_DESIGNACION   = "SOR"; //Sorteo pÃºblico de designaciÃ³n
	public static final String TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_INTERNACIONAL_DE_DESIGNACION   = "SORINT"; //Sorteo pÃºblico de designaciÃ³n Internacional
	public static final String TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_MEDIADORES   = "SORMED"; //Sorteo pÃºblico de designaciÃ³n Internacional
	public static final String TIPO_AUDIENCIA_AUDIENCIA_DE_INSTALACION           = "INI"; //Audiencia de instalaciÃ³n (aplica para arbitraje)
	public static final String TIPO_AUDIENCIA_PREARBITRAL_APERTURA               = "APA"; //Audiencia de apertura (aplica para amigable composiciÃ³n)	
	public static final String TIPO_AUDIENCIA_PREARBITRAL_REUNION_INICIAL		 = "REUINI"; //Audiencia de reunion inicial (aplica para peritaje)
	public static final String TIPO_AUDIENCIA_PREARBITRAL_PRELIMINAR 			 = "APRE"; //Audiencia preliminar (aplica para arbitraje internacional)
	//AGRUPADOR_TIPO_AUDIENCIA ARBITRAL
	public static final String TIPO_AUDIENCIA_ARBITRAL_CONCILIACION = "AAC"; //Audiencia de ConciliaciÃ³n
	public static final String TIPO_AUDIENCIA_ARBITRAL_HONORARIOS   = "AAH"; //Audiencia de FijaciÃ³n de honorarios
	public static final String TIPO_AUDIENCIA_ARBITRAL_PRIMERA      = "AAP"; //Audiencia de Primera de tramite
	public static final String TIPO_AUDIENCIA_ARBITRAL_TRAMITE      = "AATRAM"; //Audiencia de tramite
	public static final String TIPO_AUDIENCIA_ARBITRAL_PRUEBAS      = "AAPRU"; //Audiencia de Pruebas
	public static final String TIPO_AUDIENCIA_ARBITRAL_ALEGATOS     = "AAALE"; //Audiencia de Alegatos
	public static final String TIPO_AUDIENCIA_ARBITRAL_LAUDO        = "LAU"; //Audiencia de Laudo
	public static final String TIPO_AUDIENCIA_ARBITRAL_ACLARACION   = "AAACLA"; //Audiencia de AclaraciÃ³n
	public static final String TIPO_AUDIENCIA_ARBITRAL_SORTEO_MEDIACION = "SORMED"; //Audiencia de Mediacion³n
	//-------------------------------------------------------------------------
	// FIN TIPOS DE AUDIENCIAS
	// -------------------------------------------------------------------------
	
	//-------------------------------------------------------------------------
	// TIPOS DE SORTEO
	// -------------------------------------------------------------------------
	public static final String DOMINIO_TIPO_DE_SORTEO = "TIPO_DE_SORTEO";
	public static final String TIPO_DE_SORTEO_ARBITROS_NACIONALES		= "1"; //Sorteo de arbitros nacionales
	public static final String TIPO_DE_SORTEO_ARBITROS_SOCIALES			= "2"; //Sorteo de arbitros sociales
	public static final String TIPO_DE_SORTEO_ARBITROS_INTERNACIONALES	= "3"; //Sorteo de arbitros Internacionales
	public static final String TIPO_DE_SORTEO_AMIGABLES_COMPONEDORES	= "4"; //sorteo de amigables componedores
	public static final String TIPO_DE_SORTEO_PERITOS					= "5"; //Sorteo de peritos
	public static final String TIPO_DE_SORTEO_NOMINACION_DE_ARBITROS	= "7"; //Sorteo de nominación de árbitros nacionales
	public static final String TIPO_DE_SORTEO_PUBLICO_MEDIADORES	     = "8"; //Sorteo de mediadores
	public static final String TIPO_DE_SORTEO_ARBITROS_PARA_RECUPERACION = "11"; //Sorteo de árbitros para la recuperación
	public static final String TIPO_DE_SORTEO_NOMINACION_DE_AMIGABLES	 = "15"; //Sorteo de nominación de amigables componedores
	public static final String TIPO_DE_SORTEO_NOMINACION_DE_PERITOS		 = "16"; //Sorteo de nominación de peritos
	public static final String TIPO_DE_SORTEO_SECRETARIOS		         = "18"; //SORTEO DE SECRETARIOS
	public static final String TIPO_DE_SORTEO_ARBITROS_RECUSACION        = "19"; //SORTEO DE ARBITROS DE RECUSACIÓN
	//-------------------------------------------------------------------------
	// FIN TIPOS DE SORTEO
	// -------------------------------------------------------------------------
	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_CORREO_ELECTRONICO = "TIPO_CORREO_ELECTRONICO";
	public static final String TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL = "PRI";
	public static final String TIPO_CORREO_ELECTRONICO_CORREO_SECUNDARIO = "SEC";
	public static final String TIPO_CORREO_ELECTRONICO_CORREO_TERCIARIO = "TERC";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_UBICACION = "TIPO_UBICACION";
	public static final String TIPO_UBICACION_PRINCIPAL = "PRI";
	public static final String TIPO_UBICACION_SECUNDARIA = "SEC";
	
	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_CUANTIA = "TIPO_CUANTIA";
	public static final String TIPO_CUANTIA_INDETERMINADO = "IND";
	public static final String TIPO_CUANTIA_MAYOR = "MAY";
	public static final String TIPO_CUANTIA_MENOR = "MEN";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_CUANTIA_ARBITRAJE = "TIPO_CUANTIA_ARBITRAJE";
	public static final String TIPO_CUANTIA_CON_CUANTIA = "ACUANTIA";
	public static final String TIPO_CUANTIA_INDETERMINADO_ABREVIADO = "AINDETER";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_CUANTIA_CONCILIACION = "TIPO_CUANTIA_CONCILIACION";
	public static final String TIPO_CUANTIA_CONCILIACION_DETERMINADO = "DETERMIN";
	public static final String TIPO_CUANTIA_CONCILIACION_INDETERMINADO = "INDETERM";
	
	public static final String TIPO_CUANTIA_ARBITRAJE_ABREVIADO_CON_CUANTIA = "ACUANTIA";
	public static final String TIPO_CUANTIA_ARBITRAJE_ABREVIADO_INDETERMINADO = "AINDETER";

	/**
	 * Hace referencia a los tipos de documentos
	 */
	public static final String DOMINIO_TIPO_DOCUMENTO = "TIPO_DOCUMENTO";
	public static final String TIPO_DOCUMENTO_CEDULA = "TD1";
	public static final String TIPO_DOCUMENTO_REGISTRO_CIVIL = "TD2";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_DOCUMENTO_DIG = "TIPO_DOCUMENTO_DIG";
	public static final String TIPO_DOCUMENTO_DIG_DEMANDA = "DD1";
	public static final String TIPO_DOCUMENTO_DIG_PODER = "DD2";
	public static final String TIPO_DOCUMENTO_DIG_CERTIFICADO_DE_EXISTENCIA = "DD3";
	public static final String TIPO_DOCUMENTO_DIG_CARTA = "DD4";
	public static final String TIPO_DOCUMENTO_DIG_PRONUNCIAMIENTO = "DD5";
	public static final String TIPO_DOCUMENTO_INDICE_ELECTRONICO = "DD90";
	public static final String TIPO_DOCUMENTO_DIG_ACTA = "DD6";
	public static final String TIPO_DOCUMENTO_DIG_AUDIO = "DD7";
	public static final String TIPO_DOCUMENTO_DIG_AUTO = "DD8";
	public static final String TIPO_DOCUMENTO_DIG_ACUSE = "DD9";
	public static final String TIPO_DOCUMENTO_DIG_RECUSACION = "DD10";
	public static final String TIPO_DOCUMENTO_DIG_CONTESTACION_DEMANDA = "DD11";
	public static final String TIPO_DOCUMENTO_DIG_EXCEPCIONES = "DD12";
	public static final String TIPO_DOCUMENTO_DIG_RECONVENCIONES = "DD13";
	public static final String TIPO_DOCUMENTO_DIG_LAUDO = "DD14";
	public static final String TIPO_DOCUMENTO_DIG_RECURSO_LAUDO = "DD15";
	public static final String TIPO_DOCUMENTO_DIG_DOCUMENTO_DIGITALIZADO = "DD16";
	public static final String TIPO_DOCUMENTO_DIG_FORMATO_SECRETARIA = "DSEC";
	public static final String TIPO_DOCUMENTO_DIG_ACTA_CONCILIACION = "DD22";
	public static final String TIPO_DOCUMENTO_DIG_RESPUESTA_MINISTERIO = "DD37";
	public static final String TIPO_DOCUMENTO_DIG_CONSTANCIA_SOLICITUD_PRORROGA = "DD38";
	public static final String TIPO_DOCUMENTO_DIG_CONSTANCIA_RESULTADO_AUDIENCIA = "DD39";
	public static final String TIPO_DOCUMENTO_DIG_PETICION_ESPECIAL = "DD43";
	public static final String TIPO_DOCUMENTO_DIG_MEDIDAS_CAUTELARES = "DD35";


	public static final String TIPO_DOCUMENTO_DIG_GASTO_TRIBUNAL = "GT1";

	public static final String TIPO_DOCUMENTO_DIG_CERTIFICADO_CONSTANCIA = "CCF";
	
	public static final String TIPO_DOCUMENTO_DIG_RADICACION_CASO = "RADCA";
	public static final String TIPO_DOCUMENTO_DIG_CIERRE_CASO = "DOCX";
	/**
	 * Representa los tipos de identificacion que puede tener una persona
	 */
	public static final String DOMINIO_TIPO_DOCUMENTO_PERSONA = "TIPO_DOCUMENTO_PERSONA";
	public static final String TIPO_DOCUMENTO_PERSONA_CEDULA_DE_CIUDADANIA = "CC";
	public static final String TIPO_DOCUMENTO_PERSONA_CEDULA_DE_EXTRANJERIA = "CE";
	public static final String TIPO_DOCUMENTO_PERSONA_NIT = "NIT";
	public static final String TIPO_DOCUMENTO_PERSONA_NIT_VALOR = "JUR";
	public static final String TIPO_DOCUMENTO_PERSONA_PERSONERIA_JURIDICA = "PRJ";
	public static final String TIPO_DOCUMENTO_PERSONA_PASAPORTE = "PSP";
	public static final String TIPO_DOCUMENTO_PERSONA_POR_VERIFICAR = "PVF";
	public static final String TIPO_DOCUMENTO_PERSONA_SIN_IDENTIFICACION = "SID";
	public static final String TIPO_DOCUMENTO_PERSONA_TARJETA_DE_IDENTIDAD = "TI";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_EMPRESA = "TIPO_EMPRESA";
	public static final String TIPO_EMPRESA_GRANDES = "TE1";
	public static final String TIPO_EMPRESA_MEDIANAS = "TE2";
	public static final String TIPO_EMPRESA_PEQUENIAS = "TE3";
	public static final String TIPO_EMPRESA_MICROEMPRESAS = "TE4";
	public static final String TIPO_EMPRESA_PRIVADA = "PRIVADA";
	public static final String TIPO_EMPRESA_PUBLICA = "PUBLICA";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_ENTIDAD_PUBLICA = "TIPO_ENTIDAD_PUBLICA";
	public static final String TIPO_ENTIDAD_PUBLICA_ORGANISMO_DE_CONTROL = "EP1";
	public static final String TIPO_ENTIDAD_PUBLICA_RAMA_JUDICIAL = "EP2";
	public static final String TIPO_ENTIDAD_PUBLICA_RAMA_LEGISLATIVA = "EP3";
	public static final String TIPO_ENTIDAD_PUBLICA_RAMA_EJECUTIVA = "EP4";

	
	public static final String DOMINIO_CODIGO_PAIS_MINISTERIO = "CODIGO_PAIS_MINISTERIO";
	
	/**
	 * ESTADO_EVENTO_CCB
	 */
	public static final String DOMINIO_ESTADO_EVENTO_CCB = "TIPO_EVENTO_CCB";
	public static final String ESTADO_EVENTO_CCB_CANCELADO = "CANCELA";
	public static final String ESTADO_EVENTO_CCB_REALIZADO = "REALIZA";
	public static final String ESTADO_EVENTO_CCB_PROGRAMADO = "PROGRAMA";
	
	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_EVENTO = "TIPO_EVENTO";
	public static final String TIPO_EVENTO_RELIQUIDACION = "RELIQUID";
	public static final String TIPO_EVENTO_ACREDITACION_PACTO_ARBITRAL = "APA";
	public static final String TIPO_EVENTO_CASO_CANCELADO = "CAN";
	public static final String TIPO_EVENTO_CASO_CONCILIACION = "CACON";
	public static final String TIPO_EVENTO_CASO_CONTROL_LEGALIDAD = "CLE";
	public static final String TIPO_EVENTO_CASO_CONTROL_CALIDAD = "CCAL";
	public static final String TIPO_EVENTO_CASO_PENDIENTE_ENTREGA_DOCUMENTO_RESULTADO = "PEDR";
	public static final String TIPO_EVENTO_CASO_DEVUELTO_CONTROL_LEGALIDAD = "CDCL";
	public static final String TIPO_EVENTO_CASO_DEVUELTO_CONTROL_CALIDAD = "CDCCAL";
	public static final String TIPO_EVENTO_CANCELACION_AUDIENCIA = "CAU";
	public static final String TIPO_EVENTO_CREACION_PACTO_ARBITRAL = "CPA";
	public static final String TIPO_EVENTO_RADICACION_DOCUMENTO = "DDC";
	public static final String TIPO_EVENTO_AUDIENCIA_REALIZADA = "EAR"; // Audiencia
																		// realizada
	public static final String TIPO_EVENTO_AUDIENCIA_APLAZADA = "AUAPLA"; // Audiencia
																			// aplazada
	public static final String TIPO_EVENTO_AUDIENCIA_PROGRAMADA = "AUPRO"; // Audiencia
																			// programada
	public static final String TIPO_EVENTO_AUDIENCIA_SUSPENDIDA = "AUSUS"; // Audiencia
																			// programada
	
	public static final String TIPO_EVENTO_AUDIENCIA_ELIMINADA = "AUELIM";
	
	public static final String TIPO_EVENTO_CAMBIO_DE_ABOGADO_DESIGNADO = "ECA";
	public static final String TIPO_EVENTO_DEVOLUCION_EXPEDIENTE = "EDE";
	public static final String TIPO_EVENTO_CASO_CERRADO = "ECC";
	public static final String TIPO_EVENTO_ENTREGA_EXPEDIENTE_SECRETARIO = "EEE";
	public static final String TIPO_EVENTO_ELEGIBLE = "ELE";
	public static final String TIPO_EVENTO_ACTUALIZACION_PACTO_ARBITRAL = "PAA";
	public static final String TIPO_EVENTO_ACTUALIZACION_FECHA_CASO = "AFC";
	public static final String TIPO_EVENTO_RADICACION_DE_CASO = "RAD";
	public static final String TIPO_EVENTO_EDICION_CARTA = "EDC";
	public static final String TIPO_EVENTO_ELIMINACION_DE_ARBITRO = "ELR";
	public static final String TIPO_EVENTO_MODIFICACION_DE_ESTADO_ARBITRO = "MEA";
	public static final String TIPO_EVENTO_REGISTRO_RECUSACION = "RRE";
	public static final String TIPO_EVENTO_CAMBIO_TIPO_NOMBRAMIENTO = "CTN";
	public static final String TIPO_EVENTO_REGISTRO_RESPUESTA_RECUSACION = "RSC";
	public static final String TIPO_EVENTO_CONFIRMACION_NOMBRAMIENTO = "CNO";
	public static final String TIPO_EVENTO_ACEPTACION_RECUSACION = "ARE";
	public static final String TIPO_EVENTO_REVERSAR_DESIGNACION_SUPLENTE = "RDS";
	public static final String TIPO_EVENTO_REVERSAR_PRONUNCIAMIENTO = "RPR";
	public static final String TIPO_EVENTO_PUBLICACION_DOCUMENTO = "PBD";
	public static final String TIPO_EVENTO_REVERSAR_DOCUMENTO="RVD";
	public static final String TIPO_EVENTO_ACEPTACION_DESIGNACION_ARBITRO = "ACE";
	public static final String TIPO_EVENTO_DESIGNACION_ARBITRO = "DESIG";// Evento
																			// de
																			// designacion
																			// de
																			// arbitro
	public static final String TIPO_EVENTO_DECLINACION_DESIGNACION_ARBITRO = "DEC";
	public static final String TIPO_EVENTO_ASIGNACION_ABOGADO = "ASGAB";
	public static final String TIPO_EVENTO_ASIGNACION_AUXILIAR = "ASGAU";
	public static final String TIPO_EVENTO_ASIGNACION_DIGITALIZADOR = "ASGDI";
	public static final String TIPO_EVENTO_ASIGNACION_PRESIDENTE_TRIBUNAL = "ASGPRS";
	public static final String TIPO_EVENTO_ASIGNACION_SECRETARIO_TRIBUNAL = "ASGSET";
	public static final String TIPO_EVENTO_SUSPENSION_CASO="SUS";
	public static final String TIPO_EVENTO_INTERRUPCION_CASO="INT";
	public static final String TIPO_EVENTO_PRORROGA_CASO="PRO";
	public static final String TIPO_EVENTO_SUSPENDIDO_REQUERIMIENTO="SPQ";	
	public static final String TIPO_EVENTO_FIN_SUSPENSION_CASO="FSUS";
	public static final String TIPO_EVENTO_FIN_INTERRUPCION_CASO="FINT";
	public static final String TIPO_EVENTO_FIN_PRORROGA_CASO="FPRO";
	public static final String TIPO_EVENTO_FIN_SUSPENSION_REQUERIMIENTO="FSPQ";
	public static final String TIPO_EVENTO_DEVOLUCION_EXPEDIENTE_REINTEGRACION_TRIBUNAL = "EDEREI";
	public static final String TIPO_EVENTO_DEVOLUCION_EXPEDIENTE_CIERRE_CASO = "EDECIE";
	public static final String TIPO_EVENTO_LAUDO = "LAU";
	public static final String TIPO_EVENTO_RECURSO_CONTRA_LAUDO = "RLA";
	public static final String TIPO_EVENTO_CARGUE_TRANSCRIPCION_AUDIO = "CTA";
	public static final String TIPO_EVENTO_MODIFICACION_DEMANDA = "MDE";
	public static final String TIPO_EVENTO_CONTESTACION_DEMANDA = "CONDE";
	public static final String TIPO_EVENTO_PRESENTACION_EXCEPCIONES = "EXECP";
	public static final String TIPO_EVENTO_PRESENTACION_RECONVENCION = "RECON";
	public static final String TIPO_EVENTO_NO_ACREDITA_PACTO_ARBITRAL = "ABOGNOAC";
	public static final String TIPO_EVENTO_SI_ACREDITA_PACTO_ARBITRAL = "ABOGSIAC";
	public static final String TIPO_EVENTO_DOCUMENTO_ADICIONADO_PARTE = "DOCPAR";
	public static final String TIPO_EVENTO_ENVIO_MINISTERIO = "MINJUS";
	public static final String TIPO_EVENTO_CASO_REGISTRADO = "REGISTRO";
	public static final String TIPO_EVENTO_CASO_REABIERTO = "REAPERTU";
	public static final String TIPO_EVENTO_CASO_SIN_ASIGNACION = "CSAA";
	public static final String TIPO_EVENTO_AUDIENCIA_INSTALACION_REALIZADA = "TEAIRE";
	public static final String TIPO_EVENTO_DESIGNACION_ARBITRO_PRINCIPAL = "TEDAP";

	/**
	 * TIPO EVENTO DE CONCILIACION
	 */
	public static final String TIPO_EVENTO_PENDIENTE_POR_DESIGNACION = "PENDESIG";
	public static final String TIPO_EVENTO_REPARTO = "REPARTO";
	public static final String TIPO_EVENTO_DESIGNACION = "DESIGNA";
	public static final String TIPO_EVENTO_FALTA_DE_COMPENTENCIA = "FALTCOMP";
	public static final String TIPO_EVENTO_PRONUNCIAMIENTO_CONCILIADOR = "PRONCONC";
	public static final String TIPO_EVENTO_DEVOLUCION_DINERO = "DEVDIN";
	public static final String TIPO_EVENTO_REPARTO_NO_EXITOSO = "MONOREP";
	public static final String TIPO_EVENTO_NOTIFICACION_CORREO_ELECTRONICO_CERTIFICADO = "NCEC";
	public static final String TIPO_EVENTO_CAMBIO_CONCILIADOR_PRINCIPAL_MANUAL = "CCPM";
	public static final String TIPO_EVENTO_CAMBIO_CONCILIADOR_PRINCIPAL_AUTOMATICO = "CCPA";
	public static final String TIPO_EVENTO_SOLICITUD_PRORROGA = "SOLIPROR";
	public static final String TIPO_EVENTO_RESPUESTA_PETICION = "RESPPETI";
	public static final String TIPO_EVENTO_REVERSAR_RESULTADO = "RRC";
	public static final String TIPO_EVENTO_REVERSAR_RESULTADO_AUDIENCIA = "RRA";
	public static final String TIPO_EVENTO_FIRMA_ELECTRONICA_ACTA = "FELEACT";
	public static final String TIPO_EVENTO_FIRMA_ELECTRONICA_ACTA_CONCILIADOR = "FEACTCON";
	
	/**
	 * TIPO EVENTO ENTREGA DOCUMENTO
	 */
	public static final String TIPO_EVENTO_ENTREGA_DOCUMENTO = "ENTDOC";

	/**
	 * DOMINIO MOTIVOS DEVOLUCIÃ“N EXPEDIENTE
	 */
	public static final String DOMINIO_MOTIVO_DEVOLUCION_EXPEDIENTE = "MOTIVO_DEVOLUCION_EXPEDIENTE";
	public static final String MOTIVO_DEVOLUCION_EXPEDIENTE_RETINTEGRACION_TRIBUNAL = "DREINT";
	public static final String MOTIVO_DEVOLUCION_EXPEDIENTE_CIERRE_CASO = "DCIERRE";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_FALLO = "TIPO_FALLO";
	public static final String TIPO_FALLO_EN_DERECHO = "DER";
	public static final String TIPO_FALLO_EQUIDAD = "EQU";
	public static final String TIPO_FALLO_TECNICO = "TEC";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_TELEFONO = "TIPO_TELEFONO";
	public static final String TIPO_TELEFONO_CELULAR = "CEL";
	public static final String TIPO_TELEFONO_FAX = "FAX";
	public static final String TIPO_TELEFONO_FIJO = "FIJ";

	/*
	 * Agrupadores de tipo de servicio
	 */
	public static final String DOMINIO_TIPO_AGRUPAMIENTO_ROL = "TIPO_AGRUPAMIENTO_ROL";
	public static final String TIPO_AGRUPAMIENTO_ROL_PRESTADOR_SERVICIO = "PSERVICI";

	// -------------------------------------------------------------------------
	// ROLES DEL SISTEMA
	// -------------------------------------------------------------------------
	/**
	 * Agrupacion de roles
	 */
	public static final String DOMINIO_AGRUPADOR_ROL_PERSONA = "AGRUPADOR_ROL_PERSONA";
	public static final String AGRUPADOR_ROL_PERSONA_FUNCIONARIOS_INTERNOS = "FINT";
	public static final String AGRUPADOR_ROL_PERSONA_FUNCIONARIOS_EXTERNOS = "FEXT";
	public static final String AGRUPADOR_ROL_PERSONA_APODERADOS = "APOD";
	public static final String AGRUPADOR_ROL_PERSONA_PARTES = "PPART";
	public static final String AGRUPADOR_ROL_PERSONA_OTROS = "POTROS";
	public static final String AGRUPADOR_ROL_PERSONA_TERCEROS = "PTER";
	public static final String AGRUPADOR_ROL_PERSONA_ARBITROS = "AARB";
	public static final String AGRUPADOR_ROL_PERSONA_PRESTADORES_SERVICIO = "PRESTADO";
	public static final String AGRUPADOR_ROL_PERSONA_ADMINISTRATIVO_U_OPERATIVO = "ADMIOPER";
	public static final String AGRUPADOR_ROL_PERSONA_PARTES_APLICACION = "PARTAPP";
	public static final String AGRUPADOR_ROL_PERSONA_ENVIO_CARTAS = "R_ENVCTA";

	public static final String DOMINIO_AGRUPADOR_ROL_PERSONA_CONSULTA_USUARIO = "AGRUPADOR_ROL_PERSONA_CONSULTA_USUARIO";
	// A continuaciÃ³n los dominios que definen los roles que puede consultar el
	// usuario si pertenece al grupo de arbitraje, conciliaciÃ³n o direcciÃ³n
	public static final String AGRUPADOR_RPCU_ROLES_PERMITIDOS_ARBITRAJE = "CUSUARB";
	public static final String AGRUPADOR_RPCU_ROLES_PERMITIDOS_CONCILIACION = "CUSUCONC";
	public static final String AGRUPADOR_RPCU_ROLES_PERMITIDOS_DIRECTOR_CAC = "CUSUDIRE";
	public static final String AGRUPADOR_RPCU_ROLES_PERMITIDOS_INTERNACIONAL = "CUSUINT";
	public static final String AGRUPADOR_RPCU_ROLES_PERMITIDOS_EQUIDAD = "CUSUEQ";
	// A continuaciÃ³n los dominios que definen los grupos de roles de Arbitraje,
	// ConciliaciÃ³n y direcciÃ³n
	// Dependiendo del grupo se pueden consultar determinados roles
	public static final String AGRUPADOR_RPCU_ROLES_ARBITRAJE = "CUSUROLA";
	public static final String AGRUPADOR_RPCU_ROLES_CONCILIACION = "CUSUROLC";
	public static final String AGRUPADOR_RPCU_ROLES_DIRECCION = "CUSUROLD";
	public static final String AGRUPADOR_RPCU_ROLES_INTERNACIONAL = "CUSUROLI";
	public static final String AGRUPADOR_RPCU_ROLES_EQUIDAD = "CUSUROLE";
	/**
	 * Roles existentes en el sistema
	 */
	public static final String DOMINIO_ROL_PERSONA = "ROL_PERSONA";
	public static final String DOMINIO_ROL_PERSONA_PARTE = "ROL_DE_PARTE_ARBITRAJE";
	public static final String DOMINIO_ROL_PERSONA_PARTE_EQUIDAD = "ROL_DE_PARTE_EQUIDAD";
	public static final String DOMINIO_ROL_PERSONA_PARTE_CON = "ROL_DE_PARTE_CONCILIACION";

	// AGRUPADOR_ROL_PERSONA_FUNCIONARIOS_INTERNOS
	public static final String ROL_PERSONA_ABOGADO = "ABO";
	public static final String ROL_PERSONA_ABOGADO_ARBITRAJE_INTERNACIONAL = "ABOINT";	
	public static final String ROL_PERSONA_JUDICANTE = "JUD";
	public static final String ROL_PERSONA_DIGITALIZADOR = "DIG";
	public static final String ROL_PERSONA_AUXILIAR_ADM = "AUX";
	public static final String ROL_PERSONA_DIRECTOR_CAC = "DCA";
	public static final String ROL_PERSONA_SUBDIRECTOR_CAC = "SUBDICAC";
	
	//AGRUPADOR_ROL_PERSONA_FUNCIONARIOS_EXTERNOS
	public static final String ROL_PERSONA_SECRETARIO_DE_TRIBUNAL = "SEC";
	public static final String ROL_PERSONA_SECRETARIO_DE_ARBITRAJE = "SECA";
	public static final String ROL_PERSONA_ARBITRO_RECUPERACION_EMPRESARIAL = "ARBREC";
	public static final String ROL_PERSONA_ARBITRO = "ARB";
	public static final String ROL_PERSONA_ASISTENTE_ARBITRO = "ASA";
	public static final String ROL_PERSONA_ARBITRO_INTERNACIONAL = "ARI";
	public static final String ROL_PERSONA_CONCILIADOR = "CON";
	public static final String ROL_PERSONA_CONCILIADOR_EQUIDAD = "CONCOM";
	public static final String ROL_PERSONA_CONCILIADOR_EN_INSOLVENCIA = "CONINS";
	public static final String ROL_PERSONA_CONCILIADOR_COMUNITARIO = "CONCOM";
	public static final String ROL_PERSONA_ARBITRO_EXTERNO = "ARE";
	public static final String ROL_PERSONA_PROCURADOR_JUDICIAL = "PRJ";
	public static final String ROL_PERSONA_AMIGABLE_COMPONEDOR = "AMC";
	public static final String ROL_PERSONA_PERITO = "PER";
	public static final String ROL_PERSONA_JUEZ_CIVIL_CIRCUITO = "JCC";
	public static final String ROL_PERSONA_ARBITRO_SOCIAL = "ARBS";
	public static final String ROL_PERSONA_ARBITRO_RECUPERACION = "ARBREC";
	public static final String ROL_PERSONA_ARBITRO_ADHOC = "ARBADHOC";
	public static final String ROL_PERSONA_MEDIADOR = "MED";
	public static final String ROL_PERSONA_ARBITRO_RECUSACION = "ARBRECU";	
	
	// AGRUPADOR_ROL_PERSONA_APODERADOS
	public static final String ROL_PERSONA_APODERADO_DEMANDANTE = "APD";
	public static final String ROL_PERSONA_APODERADO_DEMANDADO = "APO";
	public static final String ROL_PERSONA_REPRESENTANTE_DE_PARTE = "RDP";
	//AGRUPADOR_ROL_PERSONA_PARTES
	public static final String ROL_PERSONA_PARTE_DEMANDANTE = "DTE";
	public static final String ROL_PERSONA_PARTE_DEMANDADA = "DDA";
	public static final String ROL_PERSONA_PARTE_PROCURADOR_JUDICIAL = "PRJ";
	public static final String ROL_PERSONA_PARTE_CURADOR_AD_LITERM = "CAL";
	public static final String ROL_PERSONA_PARTE_AGENCIA = "AGE";
	// AGRUPADOR_ROL_PERSONA_OTROS
	public static final String ROL_PERSONA_ANALISTA_DE_CONCILIACION = "ACO";
	public static final String ROL_PERSONA_RESPONSABLE_DE_CONVENIO = "RCO";

	// AGRUPADOR_ROL_PERSONA_TERCEROS
	public static final String ROL_PERSONA_TERCERO = "TER";
	public static final String ROL_PERSONA_EXTERNO_AUTORIDAD_JUDICIAL = "AUJ";
	public static final String ROL_PERSONA_RADICADOR_CASOS_ARBITRAJE = "RADCA";
	public static final String ROL_PERSONA_ADMINISTRADOR_USUARIOS = "ADMINU";
	public static final String ROL_PERSONA_ESTUDIANTE_CONCILIACION = "ESTCON";
	public static final String ROL_PERSONA_ESTUDIANTE_ARBITRAJE = "ESTARB";

	// AGRUPADOR ROLES CONCILIACION
	public static final String ROL_PERSONA_SOLICITANTE = "SOLTAN";
	public static final String ROL_PERSONA_SOLICITANTE_EQUIDAD = "SOLICITA";
	public static final String ROL_PERSONA_CONVOCADO_EQUIDAD = "CONVOCAD";
	public static final String ROL_PERSONA_ANALISTA_DE_CONCILIACION_EQUIDAD = "COOREQ";
	public static final String DOMINIO_OCUPACION_DE_PARTE_EQUIDAD = "OCUPACION_DE_PARTE_EQUIDAD";
	public static final String DOMINIO_LOCALIDAD_DE_PARTE_EQUIDAD = "LOCALIDAD_DE_PARTE_EQUIDAD";
	public static final String DOMINIO_ROL_DE_PARTE_EQUIDAD = "ROL_DE_PARTE_EQUIDAD";
	public static final String DOMINIO_ACTIV_ECONOMICA_DE_PARTE_EQUIDAD = "ACTIV_ECONOMICA_DE_PARTE_EQUIDAD";
	public static final String DOMINIO_PROPIO_NEGOCIO_DE_PARTE_EQUIDAD = "PROPIO_NEGOCIO_DE_PARTE_EQUIDAD";
	public static final String ROL_PERSONA_CONVOCADO = "CONVODO";
	public static final String ROL_PERSONA_CONVOCANTE = "CONVOTE";
	public static final String ROL_PERSONA_APODERADO_CONVOCADO = "APODCDO";
	public static final String ROL_PERSONA_APODERADO_CONVOCANTE = "APODCTE";
	public static final String ROL_PERSONA_APODERADO_DEUDOR = "APODEUD";
	public static final String ROL_PERSONA_APODERADO_ACREEDOR = "APOACRE";
	public static final String ROL_PERSONA_APODERADO_CONVENIO = "APOCON";
	public static final String ROL_PERSONA_AGENCIA = "AGENCIA";
	public static final String ROL_PERSONA_CURADOR_AD_LITERM = "CURADOR";
	public static final String ROL_PERSONA_SECRETARIA_DE_CONCILIACION = "SECCON";
	public static final String ROL_PERSONA_JEFE_DE_CONCILIACION = "JEFECON";
	public static final String DOMINIO_AGRUPADOR_ROL_PARTE = "ROLPARTE";
	public static final String FILTRO_EMPRESA_CONVENIO = "EMPCONV";
	public static final String ROL_PERSONA_JEFE_DE_ARBITRAJE = "JEFEARB";
	public static final String ROL_PERSONA_DEUDOR = "DEUD";
	public static final String ROL_PERSONA_ACREEDOR = "ACRE";
	
	public static final String ROL_PERSONA_DEUDOR_RECUPERACION = "DEUDARB";
	public static final String ROL_PERSONA_ACREEDOR_RECUPERACION = "ACREARB";	
	public static final String ROL_PERSONA_APODERADO_DEUDOR_RECUPERACION = "APODEARB";
	public static final String ROL_PERSONA_APODERADO_ACREEDOR_RECUPERACION = "APOACARB";
	
	public static final String ROL_PERSONA_CONVOCADO_PDF = "CONVOCADO";
	
	// -------------------------------------------------------------------------
	// FIN ROLES DEL SISTEMA
	// -------------------------------------------------------------------------

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_FUNCIONARIO_INTERNO = "TIPO_FUNCIONARIO_INTERNO";
	public static final String TIPO_FUNCIONARIO_INTERNO_ABOGADO = "TFA";
	public static final String TIPO_FUNCIONARIO_INTERNO_DIGITALIZADOR = "TFD";
	public static final String TIPO_FUNCIONARIO_INTERNO_AUXILIAR_ADMINISTRATIVO = "TFX";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_FUNCIONARIO_MEDIADOR = "TIPO_FUNCIONARIO_MEDIADOR";
	public static final String TIPO_FUNCIONARIO_MEDIADOR_PRINCIPAL = "PRI";
	public static final String TIPO_FUNCIONARIO_MEDIADOR_RECHAZADO = "RCH";
	public static final String TIPO_FUNCIONARIO_MEDIADOR_SECRETARIO = "SEC";
	public static final String TIPO_FUNCIONARIO_MEDIADOR_SUPLENTE = "SUP";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_LISTA = "TIPO_LISTA";
	public static final String TIPO_LISTA_A = "A";
	public static final String TIPO_LISTA_B = "B";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_MANEJO_SUPLENCIA = "TIPO_MANEJO_SUPLENCIA";
	public static final String TIPO_MANEJO_SUPLENCIA_NUMERICA = "NUM";
	public static final String TIPO_MANEJO_SUPLENCIA_PERSONAL = "PER";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_MEDIADOR = "TIPO_MEDIADOR";
	public static final String TIPO_MEDIADOR_PRINCIPAL = "PRI";
	public static final String TIPO_MEDIADOR_RECHAZO = "REC";
	public static final String TIPO_MEDIADOR_SUPLENTE = "SUP";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_PACTO = "TIPO_PACTO";
	public static final String TIPO_PACTO_CLAUSULA_COMPROMISORIA = "CLA";
	public static final String TIPO_PACTO_COMPROMISO = "COM";
	public static final String TIPO_PACTO_NO_ACREDITADO = "NOA";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_PARTE_CONTRAPARTE = "TIPO_PARTE_CONTRAPARTE";
	public static final String TIPO_PARTE_CONTRAPARTE_APODERADO_DEMANDADO = "APO";
	public static final String TIPO_PARTE_CONTRAPARTE_APODERADO_DEMANDANTE = "APD";
	public static final String TIPO_PARTE_CONTRAPARTE_DEMANDANTE = "DTE";
	public static final String TIPO_PARTE_CONTRAPARTE_DEMANDADO = "DDA";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_PARTICIPANTE_CASO = "TIPO_PARTICIPANTE_CASO";
	public static final String TIPO_PARTICIPANTE_CASO_CONTRAPARTE = "CPT";
	public static final String TIPO_PARTICIPANTE_CASO_PARTE = "PRT";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_PERSONA = "TIPO_PERSONA";
	public static final String TIPO_PERSONA_JURIDICO = "JUR";
	public static final String TIPO_PERSONA_NATURAL = "NAT";
	public static final String TIPO_PERSONA_SISTEMA = "SIS";

	public static final String DOMINIO_TIPO_PERSONA_SELECCION = "TIPO_PERSONA_SELECCION";
	public static final String TIPO_PERSONA_SELECCION = "TSEL";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_PRONUNCIAMIENTO = "TIPO_PRONUNCIAMIENTO";
	public static final String TIPO_PRONUNCIAMIENTO_ACEPTA = "ACP";
	public static final String TIPO_PRONUNCIAMIENTO_DECLINA = "DEC";
	public static final String TIPO_PRONUNCIAMIENTO_NO_SE_PRONUNCIA = "NOP";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_RADICACION = "TIPO_RADICACION";
	public static final String TIPO_RADICACION_PRESENCIAL = "RAP";
	public static final String TIPO_RADICACION_VIRTUAL = "RAV";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_SELECCION_MEDIADOR = "TIPO_SELECCION_MEDIADOR";
	public static final String TIPO_SELECCION_MEDIADOR_DESIGNACION_DIRECTA = "DES";
	public static final String TIPO_SELECCION_MEDIADOR_SORTEO = "SOR";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_SERVICIO = "TIPO_SERVICIO";
	public static final String TIPO_SERVICIO_PLAN_JUSTICIA = "PJT";
	public static final String TIPO_SERVICIO_PLAN_DIALOGOS = "PDL";
	public static final String TIPO_SERVICIO_CONVIVENCIA = "COM";
	public static final String TIPO_SERVICIO_INTERNACIONAL = "INT";
	public static final String SERVICIO_CONVENIO_CONCILIACION = "CONVENIO CONCILIACIÓN";
	public static final String SERVICIO_CONCILIACION_TRAMITE_ORDINARIO = "CONCILIACIÓN TRÁMITE ORDINARIO";
	public static final String TIPO_DOCUMENTO_ARBITRAJE = "TIPO_DOCUMENTO_ARBITRAJE";
	public static final String TIPO_DOCUMENTO_CONCILIACION = "TIPO_DOCUMENTO_CONCILIACION";
	
	public static final String DOMINIO_TIPO_TRAMITE = "TIPO_TRAMITE";	
	
	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_SERVICIO_CAJA = "TIPO_SERVICIO_CAJA";
	// POR CODIGO
	public static final String TIPO_SERVICIO_CAJA_GASTOS_ADMINISTRATIVOS = "05030007";
	public static final String TIPO_SERVICIO_CAJA_HONORARIOS_DEL_CONCILIADOR = "05020098";
	public static final String TIPO_SERVICIO_CAJA_HONORARIOS_DEL_CONCILIADOR_CENTRO_UNO = "05030008";
	public static final String TIPO_SERVICIO_CAJA_HONORARIOS_DEL_CONCILIADOR_CENTRO_DOS = "05020014";
	public static final String TIPO_SERVICIO_CAJA_HONORARIOS_DEL_CONCILIADOR_CENTRO_TRES = "05020016";
	// POR NOMBRE
	public static final String TIPO_SERVICIO_CAJA_NOMBRE_HONORARIOS_DEL_CONCILIADOR = "HONORARIOS DEL CONCILIADOR";
	public static final String TIPO_SERVICIO_CAJA_NOMBRE_IVA = "IVA";
	public static final String TIPO_SERVICIO_CAJA_NOMBRE_GASTOS_ADMON_CONCILIACION = "GASTOS ADMINISTRACION DE CONCILIACION";
	public static final String TIPO_SERVICIO_CAJA_NOMBRE_IVA_POR_PAGAR = "IVA POR PAGAR";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_SERVICIO_SORTEO = "TIPO_SERVICIO_SORTEO";
	public static final String TIPO_SERVICIO_SORTEO_JORNADA_DE_ARBITRAJE = "JAR";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_TERMINO_DE_PROCESO = "TIPO_TERMINO_DE_PROCESO";
	public static final String TIPO_TERMINO_DE_PROCESO_TERMINOS_DE_LEY = "LEY";
	public static final String TIPO_TERMINO_DE_PROCESO_SENIALADOS_POR_LAS_PARTES = "PAR";

	/**
	 * 
	 */
	public static final String DOMINIO_TIPO_SEDE = "TIPO_SEDE";
	public static final String SEDE_FISICA = "FIS";
	public static final String SEDE_VIRTUAL = "VIR";

	/**
	 * 
	 */
	public static final String DOMINIO_MOTIVOS_INACTIVACION_ARBITRO = "MOTIVOS_INACTIVACION_ARBITRO";
	public static final String MOTIVO_RECUSACION = "REC";
	public static final String MOTIVO_SUSPENDIDO = "SUS";
	public static final String MOTIVO_INACTIVACION_DE_ROL = "INR";
	public static final String MOTIVO_SORTEO_ANULADO = "SOA";
	public static final String MOTIVO_RENUNCIA = "REN";
	public static final String MOTIVO_DESISTIMIENTO = "DES";
	public static final String MOTIVO_IMPEDIDO = "IMP";
	public static final String MOTIVO_RELEVO = "REL";
	public static final String MOTIVO_FALLECIDO = "FAL";
	public static final String MOTIVO_NO_NOTIFICACION = "NOT";
	public static final String MOTIVO_NO_INFORMA_RAZON = "NIR";
	public static final String MOTIVO_REVERSAR_DESIGNACION_SUPLENTE = "RDS";
	public static final String MOTIVO_REVERSAR_PRONUNCIAMIENTO = "RPN";
	public static final String MOTIVO_CONTESTACION_EXTEMPORANEA = "EACE";
	public static final String MOTIVO_NO_ACEPTACION = "EANA";
	public static final String MOTIVO_NO_CONTESTACION = "EANC";
	
	/**
	 * 
	 */
	public static final String DOMINIO_MOTIVOS_HABILITACION_ARBITRO = "MOTIVOS_HABILITACION_ARBITRO";
	public static final String MOTIVO_HABILITACION_ARBITRO_ACEPTA_RECUSACION = "ARE";
	public static final String MOTIVO_HABILITACION_ARBITRO_CAMBIO_ESTADO = "CAE";

	/**
	 * 
	 */
	public static final String DOMINIO_ESTADOS_RECUSACION = "ESTADOS_RECUSACION";
	public static final String ESTADO_RECUSACION_RECUSADO = "REC";
	public static final String ESTADO_RECUSACION_NOMBRAMIENTO_CONFIRMADO = "NCN";
	public static final String ESTADO_RECUSACION_NOMBRAMIENTO_RECHAZADO = "NRZ";
	public static final String ESTADO_RECUSACION_RESPUESTA_ACEPTADA = "RAC";
	public static final String ESTADO_RECUSACION_RESPUESTA_RECHAZADA = "RRZ";

	// TIPO DE REUNIONES PARA EL AGENDAMIENTO
	/**
	 * TIPO DE REUNIONES PARA EL AGENDAMIENTO
	 */
	public static final String DOMINIO_TIPO_REUNION_AGENDAMIENTO = "TIPO_REUNION_AGENDAMIENTO";
	public static final String TIPO_REUNION_AUDIENCIA = "TPA";
	public static final String TIPO_REUNION_CAPACITACION = "TPC";
	public static final String TIPO_REUNION_EXHIBICION_DOCUMENTOS = "TPD";
	public static final String TIPO_REUNION_REVISION_EXPEDIENTES = "TPE";

	/**
	 * TIPOS DE ACUSE DE CORREO
	 */
	/**
	 * TIPOS DE ACUSE DE CORREO
	 */
	public static final String DOMINIO_TIPO_ACUSE = "TIPO_ACUSE";
	public static final String TIPO_ACUSE_ENVIO = "ENV";
	public static final String TIPO_ACUSE_LECTURA = "LEC";
	public static final String TIPO_ACUSE_RECIBIDO = "REC";
	public static final String TIPO_ACUSE_FALLA = "FER";
	public static final String TIPO_ACUSE_MENSAJE = "MEN";
	public static final String TIPO_ACUSE_FALLO_SIMASC = "FES";

	/**
	 * TIPOS DE PLANTILLAS DE CORREO
	 */
	public static final String DOMINIO_TIPO_PLANTILLAS_CORREO = "TIPO_PLANTILLA_CORREO";
	public static final String TIPO_PLANTILLAS_CORREO_GENERAL = "GRAL";
	
	/**
	 * Dominio Motivo del Estado de los Arbitros 
	 */
	public static final String DOMINIO_MOTIVO_ESTADO = "MOTIVO_ESTADO";
	
	/**
	 * Dominio Motivo del Estado de los Arbitros 
	 */
	public static final String DOMINIO_MOTIVO_ESTADO_CONCILIADORES = "MOTIVO_ESTADO_CONCILIADORES";
	
	/**
	 * Estados de Operadores NO CONCILIADORES Sorteable, No sorteable, Designado en un caso
	 */
	public static final String DOMINIO_ESTADO_SORTEABLE = "SORT";
	public static final String DOMINIO_ESTADO_NO_SORTEABLE = "NSORT";
	public static final String DOMINIO_ESTADO_HABILITADO_NO_DISPO_PARA_SORTEO = "Designado en un caso";

	/**
	 * Estados de Operadores CONCILIADORES Activo, Inactivo
	 */
	public static final String DOMINIO_ESTADO_OPERADOR_ACTIVO = "ACT";
	public static final String DOMINIO_ESTADO_OPERADOR_INACTIVO= "INA";

	/**
	 * ESTADOS DE LOS ARBITROS
	 */
	public static final String DOMINIO_ESTADO_ARBITROS = "ESTADO_ARBITROS";
	public static final String ESTADO_ARBITROS_HABILITADO = "EAH";
	public static final String ESTADO_NO_DISPONIBILIDAD_PARA_SOCIAL = "EANODISP";
	public static final String ESTADO_CONCILIADOR_ACTIVO = "ECA";
	public static final String ESTADO_DESIGNADO_EN_CASO = "DEC";
	public static final String ESTADO_ARBITROS_INACTIVO_LITIGANDO = "EAIL"; // Inactivo
																			// litigando
	public static final String ESTADO_ARBITROS_INACTIVO_MATERIAS = "EAIM"; // Inactivo
																			// no
																			// reporta
																			// materias
	public static final String ESTADO_ARBITROS_INHABILITADO = "EAI";
	public static final String ESTADO_ARBITROS_RETIRADO = "EAR";
	public static final String ESTADO_ARBITROS_SINASIGNAR = "EASA";
	public static final String ESTADO_ARBITROS_EMERITO = "EAEME";
	public static final String ESTADO_ARBITROS_FALLECIDO = "EAREMUER";
	public static final String ESTADO_ARBITROS_RETIRADO_EXCLUSION = "EAREEXCL";
	public static final String ESTADO_ARBITROS_SUSPENDIDO = "EASUP"; // suspendido
																		// (cargo
																		// publico,
																		// residencia
																		// en el
																		// exterior
																		// o
																		// solicitud
																		// propia)
	public static final String ESTADO_ARBITROS_TEMPORAL_SORTEO = "EATS";

	/**
	 * ESTADOS DE LOS CONCILIADORES
	 */
	public static final String DOMINIO_ESTADO_CONCILIADORES = "ESTADO_CONCILIADORES";

	/**
	 * ESTADOS DE LOS MEDIADORES
	 */
	public static final String DOMINIO_ESTADO_MEDIADORES = "ESTADO_MEDIADORES";

	/**
	 * ESTADOS CARTA
	 */
	public static final String DOMINIO_ESTADO_CARTA = "ESTADO_CARTA";
	public static final String ESTADO_CARTA_EDITADA = "EDI";
	public static final String ESTADO_CARTA_ENVIADA = "ENV";
	public static final String ESTADO_CARTA_GENERADA = "GEN";
	public static final String ESTADO_CARTA_IMPRESA = "IMP";
	public static final String ESTADO_CARTA_PENDIENTE_IMPRESION = "PEN";

	public static final String DOMINIO_TIPO_NOMBRAMIENTO = "TIPO_NOMBRAMIENTO";
	public static final String TIPO_NOMBRAMIENTO_PRINCIPAL = "PRI";
	public static final String TIPO_NOMBRAMIENTO_SUPLENTE = "SUP";

	public static final String DOMINIO_AGRUPADOR_TIPO_ARCHIVO = "AGRUPADOR_TIPO_ARCHIVO";
	public static final String AGRUPADOR_TIPO_ARCHIVO_AUDIO = "AGTIARAU";

	public static final String DOMINIO_TIPO_ARCHIVO = "TIPO_ARCHIVO";
	public static final String TIPO_ARCHIVO_PDF = "PDF";
	public static final String TIPO_ARCHIVO_DOCX = "DOCX";
	public static final String TIPO_ARCHIVO_TXT = "TXT";
	public static final String TIPO_ARCHIVO_XML = "XML";
	public static final String TIPO_ARCHIVO_XSD = "XSD";

	public static final String DOMINIO_TIPO_ARCHIVO_INVALIDO = "TIPO_ARCHIVO_INVALIDO";

	public static final String DOMINIO_ESTADO_PERSONA_SORTEO = "ESTADO_PERSONA_SORTEO";
	public static final String ESTADO_PERSONA_SORTEO_ACTIVO = "ACT";
	public static final String ESTADO_PERSONA_SORTEO_INACTIVO = "INA";

	/**
	 * Estados de Clave
	 */
	public static final String ESTADO_REGISTRO_CLAVE_ACTIVO = "ACT";
	public static final String ESTADO_REGISTRO_CLAVE_INACTIVO = "INA";

	/**
	 * Tipos de fecha en la tabla de FechaCaso
	 */
	public static final String DOMINIO_TIPO_FECHA_CASO = "TIPO_FECHA_CASO";
	public static final String ESTADO_TIPO_FECHA_CASO_ADMISION_DEMANDA = "FADMDEM";
	public static final String ESTADO_TIPO_FECHA_CASO_AUDIENCIA_ACLARACIONES_CORREO = "FAUDACL";
	public static final String ESTADO_TIPO_FECHA_CASO_AUDIENCIA_ALEGATOS = "FAUDALE";
	public static final String ESTADO_TIPO_FECHA_CASO_AUDIENCIA_CONCILIACION = "FAUDCON";
	public static final String ESTADO_TIPO_FECHA_CASO_AUDIENCIA_LAUDO = "FAUDLAU";
	public static final String ESTADO_TIPO_FECHA_CASO_AUDIENCIA_PRUEBAS = "FAUDPRU";
	public static final String ESTADO_TIPO_FECHA_CASO_AUDIENCIA_PRIMERA_TRAMITE = "FAUDTRA";
	public static final String ESTADO_TIPO_FECHA_CASO_CIERRE_ETAPA_PROBATORIA = "FCIEEPR";
	public static final String ESTADO_TIPO_FECHA_CASO_CIERRE = "FCIERRE";
	public static final String ESTADO_TIPO_FECHA_CASO_CONSTENTACION_DEMANDA = "FCONDEM";
	public static final String ESTADO_TIPO_FECHA_CASO_DEMANDA = "FDEMANDA";
	public static final String ESTADO_TIPO_FECHA_CASO_DEMANDA_RECONVENCION = "FDEMREC";
	public static final String ESTADO_TIPO_FECHA_CASO_DESIGNACION_ARBITROS = "FDESARBI";
	public static final String ESTADO_TIPO_FECHA_CASO_DECISION_CIERRE = "FDESCIE";
	public static final String ESTADO_TIPO_FECHA_CASO_DEVOLUCION_EXPEDIENTE = "FDEVEXP";
	public static final String ESTADO_TIPO_FECHA_CASO_ENVIO_ARCHIVO_CENTRAL = "FENARCEN";
	public static final String ESTADO_TIPO_FECHA_CASO_ENTREGA_EXPEDIENTE_CAC = "FENTEXPC";
	public static final String ESTADO_TIPO_FECHA_CASO_ENTREGA_EXPEDIENTE_SECRETARIO = "FENTEXPS";
	public static final String ESTADO_TIPO_FECHA_CASO_FIJACION_GASTOS_HONORARIOS = "FFIJGAS";
	public static final String ESTADO_TIPO_FECHA_CASO_FIN_SUSPENSION = "FFINSUSP";
	public static final String ESTADO_TIPO_FECHA_CASO_INICIO_SUSPENSION = "FINISUSP";
	public static final String ESTADO_TIPO_FECHA_CASO_INSTALACION = "FINSTALA";
	public static final String ESTADO_TIPO_FECHA_CASO_LIMITE_CIERRE = "FLIMCIER";
	public static final String ESTADO_TIPO_FECHA_CASO_LLAMAMIENTO_GARANTIA = "FLLAMGAR";
	public static final String ESTADO_TIPO_FECHA_CASO_NOTIFICACION_DEMANDADO = "FNOTDEM";
	public static final String ESTADO_TIPO_FECHA_CASO_POSIBLE_LAUDO = "FPOSLAU";
	public static final String ESTADO_TIPO_FECHA_CASO_TRAMITE_RECURSO_ANULACION = "FTRAANUL";
	public static final String ESTADO_TIPO_FECHA_CASO_TRASLADO_EXCEPCIONES_MERITO = "FTRAEXC";
	public static final String ESTADO_TIPO_FECHA_CASO_TRIBUNAL_CONSIGNA_CAC = "FTRICONC";
	public static final String ESTADO_TIPO_FECHA_CASO_INICIO_CONTEO_TERMINOS = "FINICONT";
	public static final String DIAS_LIMITE_PARA_CIERRE_DE_CASO = "DLCC";
	public static final String DIAS_ALERTA_CIERRE_DE_CASO = "DLCC";
	public static final String ESTADO_TIPO_FECHA_CASO_COBRO = "FCOBRO";
	public static final String ESTADO_TIPO_FECHA_CASO_FACTURACION = "FFACTURA";
	public static final String TIPO_FECHA_CASO_CONSTITUCION = "FCONSTI"; 
	public static final String TIPO_FECHA_CASO_REAPERTURA = "FREAPCAS"; 
	public static final String DIAS_ENVIO_ALERTA_CIERRE_CASO = "ALER_CIE";
	
	/**
	 * Tipos de confirmacion de nombramientos de los Ã¡rbitros
	 */
	public static final String DOMINIO_TIPO_CONFIRMACION_NOMBRAMIENTO = "TIPO_CONFIRMACION_NOMBRAMIENTO";
	public static final String TIPO_CONFIRMACION_NOMBRAMIENTO_JUZGADO = "JUZGADO";
	public static final String TIPO_CONFIRMACION_NOMBRAMIENTO_ARBITRO = "ARBITRO";

	public static final String DOMINIO_ESTADO_DOCUMENTO = "ESTADO_DOCUMENTO";
	/**
	 * Estado de transcripcion de audio
	 */
	public static final String DOMINIO_ESTADO_TRANSCRIPCION = "ESTADO_TRANSCRIPCION";
	public static final String ESTADO_TRANSCRIPCION_POR_TRANSCRIBIR = "PTR";
	public static final String ESTADO_TRANSCRIPCION_TRANSCRIPCION_FINALIZADA = "TRA";
	public static final String ESTADO_TRANSCRIPCION_PENDIENTE = "PEN";
	public static final String ESTADO_TRANSCRIPCION_FINALIZADA = "FIN";

	/**
	 * Estado digitalizacion de un documento
	 */
	public static final String DOMINIO_ESTADO_DIGITALIZACION = "ESTADO_DIGITALIZACION";
	public static final String ESTADO_DIGITALIZACION_POR_DIGITALIZAR = "DPD";
	public static final String ESTADO_DIGITALIZACION_DIGITALIZADO = "DDO";

	/**
	 * Monedas Validas TIPO MONEDA
	 */

	public static final String DOMINIO_TIPO_MONEDA = "TIPO_MONEDA";
	public static final String TIPO_MONEDA_COP = "COP";
	public static final String TIPO_MONEDA_USD = "USD";

	/**
	 * tipos de suspencion TABLA SUSPENSION
	 */

	public static final String DOMINIO_TIPO_SUSPENSION = "TIPO_SUSPENSION";
	public static final String TIPO_SUSPENSION_SUSPENSION_PREARBITRAL = "SUSPRE";
	public static final String TIPO_SUSPENSION_SUSPENSION_ARBITRAL = "SUSARB";
	public static final String TIPO_SUSPENSION_INTERRUPCION = "INT";
	public static final String TIPO_SUSPENSION_PRORROGA = "PRO";
	public static final String TIPO_SUSPENSION_POR_REQUERIMIENTO = "SPQ";

	/**
	 * TIPOS DE TARIFAS PARA LA PARAMETRIZACION DE TARIFAS
	 */
	public static final String DOMINIO_TIPO_TARIFA = "TIPO_TARIFA";
	public static final String TIPO_TARIFA_LEGAL = "LEGAL";
	public static final String TIPO_TARIFA_INSTITUCIONAL = "INST";
	public static final String TIPO_TARIFA_OTRO = "OTRO";
	public static final String TIPO_TARIFA_INTERNACIONAL = "INTER";

	/**
	 * TIPO VALOR TARIFA PARA LA PARAMETRIZACION DE TARIFAS
	 */
	public static final String DOMINIO_TIPO_VALOR_TARIFA = "TIPO_VALOR_TARIFA";
	public static final String TIPO_VALOR_TARIFA_PORCENTAJE = "POR";
	public static final String TIPO_VALOR_TARIFA_SMD = "SMD";
	public static final String TIPO_VALOR_TARIFA_SMM = "SMM";
	public static final String TIPO_VALOR_TARIFA_VALOR = "VAL";
	public static final String TIPO_VALOR_TARIFA_UVT = "UVT";
	public static final String TIPO_VALOR_TARIFA_USD = "USD";

	/**
	 * tipo actor caso
	 */
	public static final String DOMINIO_TIPO_ACTOR_CASO = "TIPO_ACTOR_CASO";
	public static final String TIPO_ACTOR_CASO_ARBITRO = "ARB";
	public static final String TIPO_ACTOR_CASO_CAC = "CAC";
	public static final String TIPO_ACTOR_CASO_SECRETARIO = "SEC";

	/**
	 * tipo actor caso
	 */
	public static final String DOMINIO_TIPO_LIBRO_NOTIFICACION_DOCUMENTOS = "TIPO_LIBRO_NOTIFICACION_DOCUMENTOS";
	public static final String TIPO_LIBRO_NOTIFICACION_ESTADO = "ESTADO";
	public static final String TIPO_LIBRO_NOTIFICACION_LISTA = "LISTA";
	
	/**
	 * Tipo Libro
	 */
	public static final String TIPO_LIBRO_ACTA = "LIBACTA";
	public static final String TIPO_LIBRO_CONSTANCIA = "LIBCONS";
	public static final String TIPO_LIBRO_OTROS = "LIBOTRO";

	/**
	 * Tipos de llamada
	 */
	public static final String DOMINIO_TIPO_LLAMADA = "TIPO_LLAMADA";
	public static final String LLAMADA_CONFIRMACION_ASISTENCIA = "CONFASIS";
	public static final String LLAMADA_CONFIRMACION_CORREO = "CONFCORR";
	public static final String LLAMADA_SEGUIMIENTO_CASO = "SEGCASO";
	public static final String LLAMADA_CARTA_NO_ENTREGADA = "CARNOEN";

	/**
	 * Tipos de recurso laudo
	 */
	public static final String DOMINIO_TIPO_RECURSO_LAUDO = "TIPO_RECURSO_LAUDO";
	public static final String RECURSO_LAUDO_ANULACION = "ANU";
	public static final String RECURSO_LAUDO_REPOSICION = "REP";
	public static final String RECURSO_LAUDO_ACLARACION = "ACC";
	public static final String RECURSO_LAUDO_REVISION = "REV";

	/**
	 * Tipos de funcionario de persona
	 */
	public static final String DOMINIO_TIPO_FUNCIONARIO_PERSONA = "TIPO_FUNCIONARIO_PERSONA";
	public static final String PERSONA_FUNCIONARIO_INTERNO = "CCB";
	public static final String PERSONA_FUNCIONARIO_EXTERNO = "EXT";
	public static final String PERSONA_FUNCIONARIO_NO_FUNCIONARIO = "NOF";

	/**
	 * Tipos de parametros para plantillas
	 */
	public static final String DOMINIO_TIPO_PARAMETRO_PLANTILLA = "TIPO_PARAMETRO_PLANTILLA";
	public static final String ID_PERSONA = "IPP";
	public static final String ID_AUDIENCIA = "IDA";
	public static final String ID_PERSONA_INVITADO = "IPI";
	public static final String DOCUMENTOS_RADICADOS = "documentosP";
	public static final String TIPO_DESTINATARIO = "destinatarioP";
	public static final String PERSONAS_APODERADOS = "apoderadosP";
	public static final String LISTA_PARTES_SELECCIONADAS = "listaPartesSeleccionadasP";
	public static final String PARTES_SELECCIONADAS = "partesSeleccionadasConP";
	public static final String DOCUMENTOS_ADJUNTOS = "adjuntosP";

	/**
	 * Estado laudo(Se calcula de acuerdo a los resultados de los recursos, no
	 * es un campo)
	 */
	public static final String DOMINIO_ESTADO_LAUDO = "ESTADO_LAUDO";
	public static final String ESTADO_LAUDO_ANULADO = "ELA";
	public static final String ESTADO_LAUDO_NO_ANULADO = "ELN";
	public static final String ESTADO_LAUDO_PENDIENTE_ANULACION = "ELP";

	/**
	 * Resultado recurso
	 */
	public static final String DOMINIO_RESULTADO_RECURSO_LAUDO = "RESULTADO_RECURSO_LAUDO";
	public static final String RESULTADO_RECURSO_ANULACION_ANULADO = "ANULADO";
	public static final String RESULTADO_RECURSO_ANULACION_NO_ANULADO = "NOANULA";
	public static final String RESULTADO_RECURSO_ANULACION_PENDIENTE = "RECPEN";

	/**
	 * Estado usuario
	 */
	public static final String DOMINIO_ESTADO_USUARIO = "ESTADO_USUARIO";
	public static final String ESTADO_USUARIO_ACTIVO = "ACT";
	public static final String ESTADO_USUARIO_INACTIVO = "INA";

	/**
	 * Cantidad arbitros
	 */
	public static final String DOMINIO_CANTIDAD_ARBITROS = "CANTIDAD_ARBITRO";
	public static final String CANTIDAD_ARBITROS_UNO = "UNO";
	public static final String CANTIDAD_ARBITROS_TRES = "TRES";

	/**
	 * Idiomas
	 */
	public static final String DOMINIO_IDIOMAS = "IDIOMAS";

	/**
	 * dominio TIPO_DESARROLLO_PROFESIONAL
	 */
	public static final String DOMINIO_TIPO_DESARROLLO_PROFESIONAL = "TIPO_DESARROLLO_PROFESIONAL";
	public static final String CODIGO_TIPO_DESARROLLO_PROFESIONAL_CENTEXT = "CENTEXT";
	public static final String CODIGO_TIPO_DESARROLLO_PROFESIONAL_CURSO = "CURSO";
	public static final String CODIGO_TIPO_DESARROLLO_PROFESIONAL_EXPEDOCE = "EXPEDOCE";
	public static final String CODIGO_TIPO_DESARROLLO_PROFESIONAL_EXPEPROF = "EXPEPROF";
	public static final String CODIGO_TIPO_DESARROLLO_PROFESIONAL_TITULO = "TITULO";
	
	/**
	 * Tipos de curso desarrollo profesional
	 */
	public static final String DOMINIO_TIPO_CURSO = "TIPO_CURSO";
	public static final String TIPO_CURSO_CONGRESO = "CONGRESO";
	public static final String TIPO_CURSO_OTROS = "OTROS";
	public static final String TIPO_CURSO_SEMINARI = "SEMINARI";
	public static final String TIPO_CURSO_TALLER = "TALLER";

	/**
	 * Tipos de materia curso del desarrollo profesional
	 */
	public static final String DOMINIO_MATERIA_CURSO = "MATERIA_CURSO";
	public static final String MATERIA_CURSO_MASC = "MASC";
	public static final String MATERIA_CURSO_OTROS = "OTROS";

	/**
	 * Homologacion de sedes
	 */

	public static final String DOMINIO_HOMOLOGACION_SEDE = "SEDE_HOMOLOGACION";

	/*
	 * Dominios
	 */
	public static final String DOMINIO_MATERIA_ARBITRAJE = "MATERIA_ARBITRAJE";

	/*
	 * Agrupamiento rol
	 */

	public static final String AGRUPAMIENTO_MATERIA_ROL = "MATEROL";

	/*
	 * motivos de rechazo del conciliador
	 */
	public static final String DOMINIO_MOTIVO_RECHAZO_CONCILIADOR = "MOTIVO_DE_RECHAZO_CONCILIADOR";
	public static final String DOMINIO_MOTIVO_RECHAZO_CONCILIADOR_EQ = "MOTIVO_DE_RECHAZO_CONCILIADOR_EQU";
	public static final String MOTIVO_RECHAZO_CONCILIADOR_NO_DISPONIBLE = "NODISPON";
	public static final String MOTIVO_RECHAZO_CONCILIADOR_INHABILIDAD = "INHABILI";
	public static final String MOTIVO_RECHAZO_CONCILIADOR_INCAPACIDAD_MEDICA = "INCAPAC";
	public static final String MOTIVO_RECHAZO_CONCILIADOR_VACACIONES = "VACAS";
	public static final String MOTIVO_RECHAZO_CONCILIADOR_CALAMIDAD = "CALAMID";
	public static final String MOTIVO_RECHAZO_CONCILIADOR_FALTA_COMPETENCIA = "FALTCOMP";
	public static final String MOTIVO_RECHAZO_CONCILIADOR_OTRO = "OTRO";
	public static final String MOTIVO_RECHAZO_CONCILIADOR_EQU_CONFLICTO = "CONFINT";

	/**
	 * Motivos reliquidacion
	 */
	public static final String MOTIVO_RELIQUIDACION_CAMBIO_CUANTIA = "CAMBCUAN";
	public static final String MOTIVO_RELIQUIDACION_COBRO_ADICIONAL = "COBRADIC";
	public static final String MOTIVO_RELIQUIDACION_FALTA_COMPETENCIA = "FALTCOMP";
	public static final String MOTIVO_RELIQUIDACION_ARREGLO_DIRECTO = "ARREGDIR";
	public static final String MOTIVO_RELIQUIDACION_CANCELACION = "CANCELA";

	/**
	 * TIPO_RELIQUIDACION
	 */
	public static final String TIPO_RELIQUIDACION_DEVOLUCION = "DEVOLUCI";
	public static final String TIPO_RELIQUIDACION_RELIQUIDACION = "RELIQUID";

	/**
	 * Codigos de los parametros generales de porcentaje de devolucion
	 */
	public static final String CODIGO_PARAMETRO_GRAL_DEVOLUCION_HUBO_CITACION = "DEV_CITA";
	public static final String CODIGO_PARAMETRO_GRAL_DEVOLUCION_NO_CITACION = "DEV_NCIT";
	public static final String CODIGO_PARAMETRO_GRAL_DEVOLUCION_NO_COMPETENCIA = "DEV_NCOM";

	/**
	 * Codigos de los parametros generales reparto
	 */
	public static final String CODIGO_PARAMETRO_GRAL_VALOR_PRETENCIONES = "VLRPRET";
	public static final String CODIGO_PARAMETRO_GRAL_SALARIO_MINIMO = "SMLMV";
	public static final String TIPO_SERVICIO_PRESTADOR_SERVICIO = "PDL";
	public static final String PARAMETRO_GENERAL_MANEJO_CONCURRENCIA_EN_REPARTO = "MCON_REP";

	/**
	 * Codigos de los parametros plazo aceptacion conciliador
	 */
	
	public static final String PARAMETRO_GENERAL_PLAZO_ACEPTACION_CONCILIADOR = "PLAZO ACEPTACION CONCILIADOR";
	public static final String CODIGO_PARAMETRO_GRAL_PLAZO_ACEPTACION_CONCILIADOR_CCB = "ACE_CCB";
	public static final String CODIGO_PARAMETRO_GRAL_PLAZO_ACEPTACION_CONCILIADOR_PARTES = "ACE_PAR";
	public static final String CODIGO_PARAMETRO_GRAL_URL_ACCESO_SIMASC = "URL_SIMA";
	public static final String CODIGO_PARAMETRO_GRAL_PLAZO_ACEPTACION_CONCILIADOR_CCB_EQUIDAD = "ACE_CCBE";
	/*
	 * codigo parametro general cobro audiencias
	 */

	public static final String CODIGO_PARAMETRO_GRAL_NUMERO_AUDIENCIAS_PARA_RECOBRO = "ACE_CCB";
	public static final String CODIGO_PARAMETRO_GRAL_PORCENTAJE_COBRO_ADICIONAL_AUDIENCIA = "ADI_AUDI";

	/**
	 * RANGO_INICIO_CONFLICTO
	 */
	public static final String RANGO_INICIO_DE_CONFLICTO_UN_MES = "1";

	/**
	 * PARTE_SOLICITA_SERVICIO
	 */
	public static final String DOMINIO_PARTE_SOLICITA_SERVICIO = "PARTE_SOLICITA_SERVICIO";
	public static final String PARTE_SOLICITA_SERVICIO_UNA_PARTE = "UNAPARTE";
	public static final String PARTE_SOLICITA_SERVICIO_DOS_PARTES = "DOSPARTE";
	public static final String PARTE_SOLICITA_SERVICIO_ABOGADO = "ABOGPART";

	/**
	 * PARAMETRO_DE_SERVICIO
	 */
	public static final String TIPO_PARAMETRO_PROGRAMACION_AUDIENCIA = "PROGAUDI";
	public static final String PARAMETRO_DE_SERVICIO_HORA_INICIO = "HORA_INICIO_AGENDA";
	public static final String PARAMETRO_DE_SERVICIO_HORA_FIN = "HORA_FIN_AGENDA";
	public static final String PARAMETRO_DE_SERVICIO_HORA_INICIO_AUDIENCIA = "HORA_INICIO_AUDIENCIA";
	public static final String PARAMETRO_DE_SERVICIO_HORA_FIN_AUDIENCIA = "HORA_FIN_AUDIENCIA";
	public static final String PARAMETRO_DE_SERVICIO_DURACION_DE_AUDIENCIA = "DURACION_DE_AUDIENCIA";
	public static final String PARAMETRO_DE_SERVICIO_MAX_DIAS_AUDIENCIA = "MAXIMO_DE_DIAS_PARA_PROGRAMAR_AUDIENCIA";
	public static final String PARAMETRO_DE_SERVICIO_MIN_DIAS_AUDIENCIA = "MINIMO_DE_DIAS_PARA_PROGRAMAR_AUDIENCIA";
	public static final String PARAMETRO_DE_SERVICIO_ACEPTACION_CASO = "PLAZOACE";
	public static final String PARAMETRO_DE_SERVICIO_NOTIFICACION_VENCIMIENTO_PAGO= "NOTVENPA";
	public static final String PARAMETRO_DE_SERVICIO_CORREO_SERVICIO_CASO= "EMAILSER";

	/**
	 * DURACION_AUDIENCIA
	 */
	public static final String DURACION_AUDIENCIA_UNA_HORA = "UNAHORA";
	public static final String DURACION_AUDIENCIA_MEDIA_HORA = "MEDIAHOR";
	public static final String DURACION_AUDIENCIA_DOS_HORAS = "DOSHORA";

	/**
	 * TIPOS_MINISTERIO
	 */
	public static final String PARAMENTROS_CODIGO_USUARIO = "USUARIO";
	public static final String PARAMENTROS_CODIGO_CLAVE = "CLAVE";

	/**
	 * ESTADO_AGENDA_PERSONA
	 */

	public static final String ESTADO_AGENDA_PERSONA_CANCELADA = "CANCELA";
	public static final String ESTADO_AGENDA_PERSONA_PROGRAMADA = "PROGRAMA";
	public static final String ESTADO_AGENDA_PERSONA_VALIDADA = "VALIDADA";

	/**
	 * TIPO_ACTIVIDAD_AGENDA
	 */
	public static final String TIPO_ACTIVIDAD_AGENDA_CAPACITACION = "CAPACITA";
	public static final String TIPO_ACTIVIDAD_AGENDA_VACACIONES = "VACAS";
	public static final String TIPO_ACTIVIDAD_AGENDA_INCAPACIDAD_MEDICA = "INCAMEDI";
	public static final String TIPO_ACTIVIDAD_AGENDA_AUDIENCIA_CONCILIACION = "AUDICON";

	/**
	 * TIPO_EVENTO_CCB
	 * 
	 */
	public static final String TIPO_EVENTO_CCB_COMITE = "COMITE";
	
	/**
	 * RESULTADOS AUDIENCIA
	 */

	public static final String DOMINIO_RESULTADOS_AUDIENCIA = "RESULTADOS_AUDIENCIA";
	public static final String RESULTADO_AUDIENCIA_ACUERDO_TOTAL = "1";
	public static final String RESULTADO_AUDIENCIA_FALTA_COMPETENCIA = "10";
	public static final String RESULTADO_AUDIENCIA_IMPOSIBILIDAD_DE_ACUERDO = "2";
	public static final String RESULTADO_AUDIENCIA_ACUERDO_PARCIAL = "3";
	public static final String RESULTADO_AUDIENCIA_INASISTENCIA = "4";
	public static final String RESULTADO_AUDIENCIA_SUSPENSION = "5";
	public static final String RESULTADO_AUDIENCIA_RECESO = "6";
	public static final String RESULTADO_AUDIENCIA_REPROGRAMACION = "7";
	public static final String RESULTADO_AUDIENCIA_ARREGLO_DIRECTO = "8";
	public static final String RESULTADO_AUDIENCIA_ORIENTACION = "9";
	public static final String RESULTADO_AUDIENCIA_CANCELADA = "11";
	public static final String RESULTADO_AUDIENCIA_ACUERDO = "12";
	public static final String RESULTADO_AUDIENCIA_FRACASO = "13";
	
	/**
	 * ESTADOS RESULTADO AUDIENCIA
	 */
	public static final String DOMINIO_ESTADO_RESULTADO_AUDIENCIA = "ESTADO_RESULTADO_AUDIENCIA";
	public static final String ESTADO_RESULTADO_VALIDADO = "REAUVALI";
	public static final String ESTADO_RESULTADO_POR_VALIDAR = "REPVAL";
	public static final String ESTADO_RESULTADO_REPROGRAMAR_AUDIENCIA = "REAUREAU";
	public static final String ESTADO_RESULTADO_CORRIGE = "REAUCORR";
	public static final String ESTADO_RESULTADO_INSISTE = "REAUINSI";
	public static final String ESTADO_RESULTADO_DEVUELTO = "REAUDEVU";
	public static final String ESTADO_RESULTADO_SIN_DOCUMENTO = "SINDOC";

	/**
	 * DOMINIO TIPO_PERIODICIDAD
	 */

	public static final String DOMINIO_TIPO_PERIODICIDAD = "TIPO_PERIODICIDAD";
	public static final String TIPO_PERIODICIDAD_CALENDARIO = "CAL";
	public static final String TIPO_PERIODICIDAD_HABIL = "HAB";  

	public static final String DOMINIO_PERIODICIDAD = "PERIODICIDAD";
	public static final String PERIODICIDAD_DIA = "DIA";
	public static final String PERIODICIDAD_HORA = "HORA";
	public static final String PERIODICIDAD_SEGUNDO = "SEG";

	/**
	 * TIPO_CONVENIO
	 */
	public static final String TIPO_CONVENIO_CONVENIO = "CONVENIO";
	public static final String TIPO_CONVENIO_JORNADA = "JORNADA";

	/*
	 * ESTADO NOTIFICACION
	 */
	public static final String DOMINIO_ESTADO_NOTIFICACION = "ESTADO_NOTIFICACION";
	public static final String ESTADO_NOTIFICACION_ENVIADA = "ENVIAD";
	public static final String ESTADO_NOTIFICACION_NO_ENVIADA = "NOENVI";
	public static final String ESTADO_NOTIFICACION_FALLO_EJECUCION = "FALLEJE";
	public static final String ESTADO_NOTIFICACION_METODO_NO_ENCONTRADO = "METNOE";
	public static final String ESTADO_NOTIFICACION_FALLO_SIN_REMITENTE = "SINREM";

	/**
	 * TIPO_PETICION
	 */
	public static final String DOMINIO_TIPO_PETICION = "TIPO_PETICION";
	public static final String TIPO_PETICION_CAMBIO_CONCILIADOR = "CAMBCON";
	public static final String TIPO_PETICION_CAMBIO_FECHA = "CAMBFECH";
	public static final String TIPO_PETICION_CAMBIO_DEVOLUCION_DINERO = "DEVDINER";
	public static final String TIPO_PETICION_CAMBIO_INCUMPLIMIENTO = "INCUMPLI";
	public static final String TIPO_PETICION_DERECHO_PETICION = "DEREPETI";
	public static final String TIPO_PETICION_FOTOCOPIAS = "FOTOCOPI";
	public static final String TIPO_PETICION_OTROS = "PETIOTRO";
			

	/**
	 * TIPO_PARTE_PETICION
	 */
	public static final String DOMINIO_TIPO_PARTE_PETICION = "TIPO_PARTE_PETICION";
	public static final String TIPO_PARTE_PETICION_RECIBE_RESPUESTA = "RESPUEST";
	public static final String TIPO_PARTE_PETICION_SOLICITA_RESPUESTA = "SOLICITA";

	/**
	 * OBLIGACION
	 */
	public static final String DOMINIO_OBLIGACION = "OBLIGACION";
	public static final String OBLIGACION_DAR = "OBLDAR";
	public static final String OBLIGACION_HACER = "OBLHACER";
	public static final String OBLIGACION_NO_HACER = "OBLNOHAC";

	/**
	 * OBLIGACION PARTE
	 */
	public static final String DOMINIO_OBLIGACION_PARTE = "OBLIGACION_PARTE";
	public static final String OBLIGACION_PARTE_PAGA = "OBLPPAGA";
	public static final String OBLIGACION_PARTE_HACE = "OBLPHACE";
	public static final String OBLIGACION_PARTE_RECIBE = "OBLPRECI";
	public static final String OBLIGACION_PARTE_NO_HACE = "OBLPNOHA";

	/**
	 * FORMA_PAGO_OBLIGACION
	 */
	public static final String DOMINIO_FORMA_PAGO_OBLIGACION = "FORMA_PAGO_OBLIGACION";
	public static final String FORMA_PAGO_OBLIGACION_EFECTIVO = "FPEFECTI";
	public static final String FORMA_PAGO_OBLIGACION_CONSIGNACION = "FPCONSIG";
	public static final String FORMA_PAGO_OBLIGACION_CHEQUE = "FPCHEQUE";
	public static final String FORMA_PAGO_OBLIGACION_ESPECIE = "FPESPECI";
	public static final String FORMA_PAGO_OBLIGACION_TRABAJO = "FPTRABAJ";
	
	/**
	 * ESTADO_SOLICITUD_PRESTADOR
	 */
	public static final String DOMINIO_ESTADO_SOLICITUD_PRESTADOR = "ESTADO_SOLICITUD_PRESTADOR";
	public static final String ESTADO_SOLICITUD_PRESTADOR_PENDIENTE = "PEND";
	public static final String ESTADO_SOLICITUD_PRESTADOR_APROBADA = "APRO";
	public static final String ESTADO_SOLICITUD_PRESTADOR_RECHAZADA = "RECH";
	
	/**
	 * ESTADO_ASISTENCIA_EVENTO
	 */
	public static final String DOMINIO_ESTADO_ASISTENCIA_EVENTO = "ESTADO_ASISTENCIA_EVENTO";
	public static final String ESTADO_ASISTENCIA_EVENTO_ASISTE = "ASISTE";
	public static final String ESTADO_ASISTENCIA_EVENTO_NO_ASISTE = "NOASISTE";
	
	/**
	 * PARAMETRO_GENERAL_CORREO
	 */
	public static final String TIPO_PARAMETRO_GENERAL_CORREO = "CORREO";
	public static final String CODIGO_PARAMETRO_GENERAL_CORREO_SUFIJO = "SUFIJO";
	public static final String CODIGO_PARAMETRO_GENERAL_ENVIO_CERTIFICADO = "CERTIF";
	
	/**
	 * PARAMETRO_GENERAL_SORTEO_PENDIENTE
	 */
	public static final String TIPO_PARAMETRO_GENERAL_SORTEO_PENDIENTE = "CONSULTA_SORTEOS_PENDIENTES";
	public static final String CODIGO_PARAMETRO_GENERAL_CONSULTA_ARBITROS_DISPONIBLES = "VISENL";

	/**
	 * TIPO_TARIFA_CONTRATO
	 */
	public static final String CODIGO_TIPO_TARIFA_FIJA = "TTFIJ";
	public static final String CODIGO_TIPO_TARIFA_DINAMICA = "TTDIN";
	
	public static final String DOMINIO_TIPO_TARIFA_CONTRATO = "TIPO_TARIFA_CONTRATO";
	
	// Evaluacion de conciliadores
	
	public static final String DOMINIO_CLASIFICADOR_EVALUACION = "RESULTADOS_EVALUACION_CONCILIADOR";
	public static final String CODIGO_CLASIFICADOR_EVALUACION = "EVALUCON";
	
	public static final String DOMINIO_EVALUACION_CONCILIADOR = "EVALUACION_CONCILIADOR";
	public static final String EVALUACION_CONCILIADOR_CRITERIO_CALIDAD = "CRICALID";
	public static final String EVALUACION_CONCILIADOR_CRITERIO_EDUCACION_CON = "CRIEDUCO";
	public static final String EVALUACION_CONCILIADOR_CRITERIO_PARTICIPACION = "CRIPARTI";
	public static final String EVALUACION_CONCILIADOR_CRITERIO_PROCEDIMIENTOS = "CRIPROCE";
	public static final String CRITERIO_PORCENTAJE_TOTAL = "PORTOTAL";
	
	public static final String DOMINIO_CRITERIO_CALIDAD = "CRITERIO_CALIDAD";
	public static final String CRITERIO_CALIDAD_TOTAL_CASOS = "TOTCALCO";
	public static final String CRITERIO_CALIDAD_TOTAL_ACUERDOS = "TOTACU";
	public static final String CRITERIO_CALIDAD_TOTAL_ARREGLOS = "TOTARRDI";
	public static final String CRITERIO_CALIDAD_TOTAL_IMPOSIBILIDAD = "TOTIMPO";
	public static final String CRITERIO_CALIDAD_PORCENTAJE_ACUERDOS = "PORACUER";
	public static final String CRITERIO_CALIDAD_TOTAL_ACTAS_GENERADAS = "TOTACCON";
	public static final String CRITERIO_CALIDAD_TOTAL_DEVOLUCIONES = "TOTDEVAC";
	public static final String CRITERIO_CALIDAD_PORCENTAJE_NO_DEVUELTOS = "PORACNOD";
	
	public static final String DOMINIO_CRITERIO_PARTICIPACION = "CRITERIO_PARTICIPACION";
	public static final String CRITERIO_PARTICIPACION_TOTAL_EVENTOS = "TOTALACU";
	public static final String CRITERIO_PARTICIPACION_TOTAL_EVENTOS_ASISTIO = "TOTALAC";
	public static final String CRITERIO_PARTICIPACION_PORCENTAJE_EVENTO_ASISTENCIA = "PORACOTA";
	public static final String CRITERIO_PARTICIPACION_TOTAL_JORNADAS_PROGRAMADAS = "TOTJORPR";
	public static final String CRITERIO_PARTICIPACION_TOTAL_JORNADAS_ASISTIO = "TOTJORAS";
	public static final String CRITERIO_PARTICIPACION_PORCENTAJE_JORNADA_ASISTENCIA = "PORAJORN";
	
	public static final String DOMINIO_CRITERIO_PROCEDIMIENTOS = "CRITERIO_PROCEDIMIENTOS";
	public static final String CRITERIO_PROCEDIMIENTOS_AUDIENCIAS_PROGRAMADAS = "TOTAUPRO";
	public static final String CRITERIO_PROCEDIMIENTOS_AUDIENCIAS_INCUMPLIO = "TOTAINCU";
	public static final String CRITERIO_PROCEDIMIENTOS_AUDIENCIAS_JUSTIFICACION = "TOTAJUST";
	public static final String CRITERIO_PROCEDIMIENTOS_PORCENTAJE_ASIS_AUDIENCIAS = "PORCUAAU";
	public static final String CRITERIO_PROCEDIMIENTOS_PETICIONES_ESPECIALES = "TOTPETES";
	public static final String CRITERIO_PROCEDIMIENTOS_RESPUESTAS_EMITIDAS = "TOTARESE";
	public static final String CRITERIO_PROCEDIMIENTOS_PORCENTAJE_RESPUESTAS = "PORCRESE";
	public static final String CRITERIO_PROCEDIMIENTOS_CASOS_ACEPTADOS = "TOTCASAC";
	public static final String CRITERIO_PROCEDIMIENTOS_CASOS_CERRADOS = "TOTCASCE";
	public static final String CRITERIO_PROCEDIMIENTOS_CASOS_SOLICITUD = "TOTCASOL";
	public static final String CRITERIO_PROCEDIMIENTOS_PORCENTAJE_CIERRE = "PORCUMCI";
	
	/**
	 * Agrupador de documentos actos y constancias
	 */
	public static final String DOMINIO_AGRUPADOR_ACTAS_CONSTANCIAS = "AGRUPADOR_ACTA_CON";
	public static final String CODIGO_AGRUPADOR_ACTAS_CONSTANCIAS = "AGRACYCO";
	
	/**
	 * Dominios tipo de combinacion tarifa
	 * 		
	 */
	public static final String CODIGO_TIPO_COMBINACION_COMPLETA = "COMBCOMP";
	public static final String CODIGO_TIPO_COMBINACION_CUANTIA = "COMBCUAN";
	public static final String CODIGO_TIPO_COMBINACION_VOLUMEN = "COMBVOL";
	public static final String CODIGO_TIPO_COMBINACION_RESULTADO = "COMBRES";
	
	/**
	 * DOMINIO MOTIVO_DEVOLUCION_CASO
	 */
	public static final String DOMINIO_MOTIVO_DEVOLUCION_CASO = "MOTIVO_DEVOLUCION_CASO";
	public static final String DOMINIO_MOTIVO_DEVOLUCION_CASO_CALIDAD = "MOTIVO_DEVOLUCION_CASO_CALIDAD";
	
	/**
	 * DOMINIO TIPO DE INASISTENCIA
	 */
	public static final String DOMINIO_TIPO_INASISTENCIA = "INASIS";

	/**
	 * DOMINIO TIPO DE LEY APLICABLE
	 */
	public static final String DOMINIO_TIPO_LEY_APLICABLE = "LEYAPLI";
	
	/**
	 * PARAMETROS GENERALES ROLES TIPO PETICION
	 */
	public static final String ROL_TIPO_PETICION_DEVOLUCION_DINERO = "RTPDD";
	public static final String ROL_TIPO_PETICION_CAMBIO_CONCILIADOR = "RTPCC";
	public static final String ROL_TIPO_PETICION_INCUMPLIMIENTO = "RTPI";
	public static final String ROL_TIPO_PETICION_DERECHO_PETICION =  "RTPI";
	public static final String ROL_TIPO_PETICION_FOTOCOPIAS = "RTPF";
	public static final String ROL_TIPO_PETICION_OTROS = "RTPO";
	public static final String ROL_TIPO_PETICION_CAMBIO_FECHA = "RTPCF";
	
	/**
	 * NOMBRE DOMINIO HONORARIOS CONCILIADOR
	 */
	public static final String NOMBRE_DOMINIO_HONORARIOS_CONCILIADOR = "HONORARIOS CONCILIADOR";
	
	/**
	 * NOMBRE DOMINIO GASTOS ADMINISTRATIVOS
	 */
	public static final String NOMBRE_DOMINIO_GASTOS_ADMINISTRATIVOS = "GASTOS ADMINISTRATIVOS";
	
	/*
	 * Tipos de solicitud Prestador
	 */
	public static final String TIPO_SOLICITUD_PRESTADOR_ACTIVACION="ACTIVACI";
	public static final String TIPO_SOLICITUD_PRESTADOR_CAMBIO_LISTA="CAMLISTA";
	public static final String TIPO_SOLICITUD_PRESTADOR_ADICION_CAMBIO_MATERIA="ADICAMAT";
	public static final String TIPO_SOLICITUD_PRESTADOR_SUSPENSION="SUSPENCI";
	
	/*
	 * Estados de las alertas
	 */
	public static final String DOMINIO_ESTADO_ALERTA ="ESTADO_ALERTA";
	public static final String ESTADO_ALERTA_ACTIVA = "ACT";
	public static final String ESTADO_ALERTA_INACTIVA = "INA";

	/*
	 * Estado de ejecucion de las alertas
	 */
	
	public static final String DOMINIO_TIPO_ALERTA = "TIPO_ALERTA";
	public static final String TIPO_ALERTA_PARAMETRIZADA = "PARAMET";
	public static final String TIPO_ALERTA_NEGOCIO = "NEGOCIO";
	public static final String TIPO_ALERTA_AUTOMATICA = "AUTO";
	
	/*
	 * Estado programacion alerta
	 */
	public static final String DOMINIO_ESTADO_PROGRAMACION_ALERTA = "ESTADO_PROGRAMACION_ALERTA";
	public static final String PROGRAMACION_ALERTA_EJECUTADA = "EJEC";
	public static final String PROGRAMACION_ALERTA_PENDIENTE = "PEND";
	public static final String PROGRAMACION_ALERTA_CONDICION_NO_CUMPLIDA = "NOCUMP";
	public static final String PROGRAMACION_ALERTA_FALLO_EJECUCION= "FALLO";
	public static final String PROGRAMACION_ALERTA_EN_EJECUCION = "ENEJEC";
	
	
	/*
	 * codigo de las alertas
	 */
	public static final String DOMINIO_CODIGO_ALERTA_NOTVENCC = "NOTVENCC";

	
	
	/*
	 * Tipo de alerta
	 */
	public static final String DOMINIO_ESTADO_EJECUCION_ALERTA = "ESTADO_EJECUCION_ALERTA";
	public static final String ESTADO_EJECUCION_ALERTA_PENDIENTE = "PEND";
	public static final String ESTADO_EJECUCION_ALERTA_EJECUTADA ="EJEC";
	public static final String ESTADO_EJECUCION_ALERTA_FALLO_EJECUCION ="FALLO";
	
	/*
	 * Agrupador de roles del caso o transversales
	 */
	
	public static final String DOMINIO_AGRUPADOR_ROL_ENVIO_ALERTAS = "AGRUPADOR_ROL_ENVIO_ALERTA";
	public static final String CODIGO_AGRUPADOR_ROLES_DEL_CASO= "ROLXCASO";
	public static final String CODIGO_AGRUPADOR_ROLES_DEL_CONVENIO= "ROLXCONV";
	
	
	/*
	 * Dominios estado firma documento
	 * 
	 */
	public static final String DOMINIO_ESTADO_FIRMA_DOCUMENTO = "ESTADO_FIRMA_DOCUMENTO";
	public static final String ESTADO_DOCUMENTO_FIRMADO = "FIRM";	
	public static final String ESTADO_DOCUMENTO_SOLICITADO = "SOLICI";
	public static final String ESTADO_DOCUMENTO_SIN_SOLICITUD ="SINSOLI";	
	
	/*
	 * Dominios de las alertas programadas
	 */
	public static final String CODIGO_ALERTA_PENDIENTE_ACEPTACION = "NOT_";
	
	/**
	 * Dominio estilos tabla Alerta
	 */
	public static final String DOMINIO_TABLA_ALERTAS_ESTILOS = "TABLA_ALERTA_ESTILOS";
	public static final String CODIGO_ESTILOS_ENCABEZADO_TABLA_ALERTA = "TEA";
	public static final String CODIGO_ESTILOS_CIERRE_TABLA_ALERTA = "TCA";
	
	/**
	 * Codigos de alerta
	 */
	public static final String CODIGO_ALERTA_CONTROL_LEGALIDAD = "CONTLEG";
	public static final String CODIGO_ALERTA_PROGRAMACION_AUDIENCIA = "PROAUC";
	public static final String CODIGO_ALERTA_DOCUMENTOS_SIN_DIGITALIZAR = "DOCNODI";
	public static final String CODIGO_ALERTA_CORRECCION_ACTA_CONSTANCIA = "CORRACT";
	public static final String CODIGO_ALERTA_SIN_TRAMITAR_POR_ABOGADO1 = "SINTRA1";
	public static final String CODIGO_ALERTA_SIN_TRAMITAR_POR_ABOGADO2 = "SINTRA2";
	public static final String CODIGO_ALERTA_NO_PAGO_CONTRAPARTE_HONORARIOS = "PAGCONT";
	public static final String CODIGO_ALERTA_RE_ACTIVAR_ARBITRO = "ACTARB";
	public static final String CODIGO_ALERTA_RE_ACTIVAR_CASO = "ACTCASO";
	public static final String CODIGO_ALERTA_VENCIMIENTO_TERMINO_PAGO = "NOTVENPA";
	
	/*
	 * Parametro general de la alerta de "Tramite inactivo", Alerta: TRAINA
	 */
	public static final String CODIGO_PARAMETRO_GENERAL_TIEMPO_INACTIVIDAD = "TIEM_INA";
	public static final String TIPO_PARAMETRO_GENERAL_ALERTA_TRAMITE_INACTIVO = "ALERTA_TRAINA";
	
	/*
	 * codigo parametro general rango ejecucion dias alerta VENMEMB
	 */
	public static final String CODIGO_PARAMETRO_GRAL_MINIMO_DIA_EJECUCION_ALERTA_VENMEMB = "RMI_AVEN";
	public static final String CODIGO_PARAMETRO_GRAL_MAXIMO_DIA_EJECUCION_ALERTA_VENMEMB = "RMA_AVEN";
	
	/*
	 * codigo parametro general tiempo de estudio de caso de arbitraje
	 */
	public static final String CODIGO_PARAMETRO_GRAL_TIEMPO_ESTUDIO_CASO_ARBITRAJE= "TECA";
	
	/*
	 * CÃ³digo parametro general minutos maximo transcripcion
	 */
	public static final String CODIGO_MINUTOS_MAXIMO_TRANSCRIPCION = "MMT";
	
	public static final String PERIODICIDAD_TERMINOS_DIA = "PERD";
	public static final String PERIODICIDAD_TERMINOS_MES = "PERM";
	public static final String PERIODICIDAD_TERMINOS_ANIO = "PERA";
	public static final String TIPO_PERIODICIDAD_TERMINOS_HABIL = "HABIL";
	
	public static final String DOMINIO_ASUNTO_CARTA = "ASUNTO_CARTA";
	public static final String ASUNTO_CARTA_NOTIFICACION_ARBITRO = "PCNAR";
	public static final String ASUNTO_CARTA_NOTIFICACION_ARBITRO_INTERNACIONAL = "PCNARI";
	public static final String ASUNTO_CARTA_NOTIFICACION_SECRETARIO = "PCNSE";
	public static final String ASUNTO_CARTA_CONCILIACION = "CONCILIA";
	
	public static final String PRESELECCIONADO_POR_LA_CAMARA_DE_COMERCIO_BOGOTA = "PCCB";
	public static final String PRESELECCIONADO_POR_LAS_PARTES= "PART";
	
	public static final String SUSPENDIDO_POR_LA_CAMARA_DE_COMERCIO_BOGOTA = "SCCB";
	public static final String SUSPENDIDO_POR_LAS_PARTES= "SPART";
	
	public static final String DOMINIO_ESTADO_LOTE = "ESTADO_LOTE";
	public static final String ESTADO_LOTE_INICIADO = "LOTINI";
	public static final String ESTADO_LOTE_FINALIZADO = "LOTFIN";
	public static final String ESTADO_LOTE_FINALIZADO_ERROR = "LOTFINER";
	// Dominios tipo de preseleccion (UNICA O INDEPENDIENTE)
	public static final String DOMINIO_TIPO_PRESELECCION = "TIPO_PRESELECCION";
	public static final String TIPO_PRESELECCION_PRESELECCION_UNICA = "1";
	public static final String TIPO_PRESELECCION_PRESELECCION_INDEPENDIENTE = "2";
	
	// Dominios tipo de preseleccionado
	public static final String DOMINIO_TIPO_PRESELECCIONADO = "TIPO_PRESELECCIONADO";
	public static final String TIPO_PRESELECCIONADO_PRESELECCION_PRINCIPAL = "PRI";
	public static final String TIPO_PRESELECCIONADO_PRESELECCION_SUPLENTE = "SUP";
	
	// Tipo Peritaje
	public static final String TIPO_PERITAJE_VINCULANTE = "VINC";
	public static final String TIPO_PERITAJE_NO_VINCULANTE = "NOVINC";
	
	//Correo Traslado
	public static final String DOMINIO_CORREO_TRASLADO = "CORREO_TRASLADO";
	public static final String CUERPO_CORREO_TRASLADO = "CCTRAS";
	public static final String ASUNTO_CORREO_TRASLADO = "ACTRAS";
	
	//Tipo de sorteo mediacion
	public static final String TIPO_AUDIENCIA_MEDIACION = "MED";
	public static final String TIPO_AUDIENCIA_SORTEO_MEDIACION = "SORMED";
	
	//Rol 
	public static final int ROL_MEDIADOR = 119;
	
	public static final String DOCUMENTO_PRONUNCIAMIENTO_RECUPERACION_EMPRESARIAL = "PRREEM";
	public static final String DOCUMENTO_PRONUNCIAMIENTO_OTRO =  "PRNREEM";
	
	public static final String METODO_NOMBRAMIENTO_SORTEO = "NPC";
	
	public static final String ESTADO_MOTIVO_ACTIVO = "ACT";
	public static final String ESTADO_MOTIVO_INACTIVO = "INA";
	
	public static final String DOMINIO_CONSULTA_PREVIA = "CONSULTA_PREVIA";
	public static final String DOMINIO_TIPO_DE_EMPLEO = "TIPO_DE_EMPLEO";
	public static final String DOMINIO_ESTADOS_CIVILES = "ESTADOS_CIVILES";
	public static final String DOMINIO_ACTIV_ECONOMICA_EQUIDAD = "ACTIV_ECONOMICA_DE_PARTE_EQUIDAD";
	public static final String DOMINIO_PROPIO_NEGOCIO_EQUIDAD = "PROPIO_NEGOCIO_DE_PARTE_EQUIDAD";
	public static final String DOMINIO_OCUPACION_EQUIDAD = "OCUPACION_DE_PARTE_EQUIDAD";

	public static final String DOMINIO_QUIEN_PRESELECCIONA = "QUIEN_PRESELECCIONA";
	
	public static final String CODIGO_DOMINIO_TARIFA_REGLAMENTO_CAC_09 = "TINCAC09";
	public static final String CODIGO_DOMINIO_TARIFA_REGLAMENTO_UNCITRAL_09 = "TINUNI09";
	public static final String CODIGO_DOMINIO_TARIFA_REGLAMENTO_CAC_10 = "TINCAC10";
	public static final String CODIGO_DOMINIO_TARIFA_REGLAMENTO_UNCITRAL_10 = "TINUNI10";
	public static final String CODIGO_DOMINIO_TARIFA_INTERNACIONAL_ACELERADO = "TINACE";
	
	public static final String CODIGO_DOMINIO_ACTOR_ARBITRO_MINIMO = "ARBINMIN";
	public static final String CODIGO_DOMINIO_ACTOR_ARBITRO_MAXIMO = "ARBINMAX";
	public static final String CODIGO_DOMINIO_ACTOR_TARIFA_ADMINISTRATIVA = "TINADMIN";
	
}
