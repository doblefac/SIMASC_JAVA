package com.ccb.simasc.integracion.utilidades;

/**
 * Clase con constantes consultadas por diferentes clases de la aplicación
 * 
 * @author jsoto
 */
public class UtilConstantes {	    
    
    public static final String SEPARADOR_HTTP_ORDER_BY = "$";    
    public static final String CARACTER_DE_ESCAPE = "\\";    
    public static final String SEPARADOR_PARAMETROS_CONSULTA = "&";
    public static final String CARACTER_ESPACIO = " ";
    
    //Textos
    public static final String TEXTO_Y_OTROS = "y otros ";
    
    //ManejadorCrud
    public static final String NULL_VALUE = "NULL";
    public static final String NOT_NULL_VALUE = "NOT NULL";
    
    //UtilReflection
    public static final String LONG_PRIMITIVE = "long";
    public static final String INT_PRIMITIVE = "int";        
    
    //Servicios
    public static final String CODIGO_SERVICIO_ARBITRAJE = "ARB";
    public static final String CODIGO_SERVICIO_CONCILIACION = "CON";
    
    //PARTICIPACIÓN DE TERCEROS
    public static final String CODIGO_PARTICIPANTE_PARTE = "PRT";
    public static final String CODIGO_PARTICIPANTE_CONTRAPARTE = "CPR";
    
    //TIPOS DE EVENTOS
    public static final String TIPO_EVENTO_ERROR = "ERR";
    public static final String TIPO_EVENTO_ACCION = "ACC";
    
    //ESTADO DE ARBITROS
    public static final String ESTADO_ARBITRO_RECHAZADO = "REC";
    public static final String ESTADO_ARBITRO_HABILITADO = "HAB";
    
    //ESTADO DE CASO
    public static final String ESTADO_CASO_CERRADO = "CER";
    
    //CODIGOS CORREOS ELECTRONICOS PARA BUSCAR EN TABLA PARAMETRO
    public static final String CODIGO_CORREO_ARBITRAJE = "COR_ARB";
    public static final String CODIGO_CORREO_CONCILIACION = "COR_CON";
    
    private UtilConstantes(){}
    
}

