package com.ccb.simasc.comun.util;


public class Constantes {

    public static final String db = "jdbc/INSTANCIAOB";	
	public static final String ERROR_01 = "Se presento un error al crear la conexion";
	public static final String ERROR_02 = "Se presento error en WS consultarArchivoOnbase : Error al consultar parametroGeneral";
	public static final String ERROR_03 = "Se presento error en WS consultarArchivoOnbase : Error al obtener documento";
	public static final String ERROR_04 = "Se presento error al cerrar cursor";
	public static final String CODIGO_PARAMETRO = "WSONBASE";
	public static final String CODIGO_PUERTO_ONBASE = "PUERTOONBASE";
	public static final String ESTADO_DOC_ONBASE = "REC";
	
	
	//Consultas
	
	public static final String QUERY_ONBASE_CONSUL_COD_DOCUMENTO = "SELECT itemnum FROM hsi.itemdata WHERE itemname like 'ruta: ?%'"+
	                                                               " and itemtypegroupnum in (158,159)";
}
