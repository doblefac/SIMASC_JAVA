package com.ccb.simasc.transversal.utilidades;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Clase para las utilidades utilizadas en las consultas SQL
 * @author jsoto
 *
 */
public class UtilConsultasSQL {


	private UtilConsultasSQL() {}
	/**
	 * Añade comillas simples al texto que se pasa como parametro
	 * @param texto
	 * @return
	 */
	public static String conComillasSimples(String texto){
		StringBuilder sb = new StringBuilder();
		sb.append("'");
		sb.append(texto);
		sb.append("'");
		
		return sb.toString();
	}
	
	/**
	 * Devuelve el dominio correspondiente al estado de registro activo en comillas simples
	 * para utilizar en las consultas sql
	 * @return
	 */
	public static String estadoRegistroActivoSQL(){
		return conComillasSimples(UtilDominios.ESTADO_REGISTRO_ACTIVO);
	}
	
	/**
	 * Devuelve el dominio correspondiente al estado de registro inactivo en comillas simples
	 * para utilizar en las consultas sql
	 * @return
	 */
	public static String estadoRegistroInactivoSQL(){
		return conComillasSimples(UtilDominios.ESTADO_REGISTRO_INACTIVO);
	}
	
	/**
	 * Devuelve una sentencia like insertando el parametro que se pasa.
	 * Ej. "LIKE '%'+:nombreParametro+'%'"
	 * @param nombreParametro
	 * @return
	 */
	public static String likeClauseSQL(String nombreParametro){
		StringBuilder sb = new StringBuilder();
		sb.append("LIKE ");
		sb.append("'%'+:");
		sb.append(nombreParametro);
		sb.append("+'%'");
		
		return sb.toString();
	}
	
	/**
	 * Devuelve el parametro encerrado en el símbolo '%' para utilizarlo en 
	 * clausulas LIKE de SQL
	 * @param parametro
	 * @return
	 */
	public static String conPorcentajes(String parametro){
		return '%'+parametro+'%';
	}
	
	/**
	 * Devuelve el parametro encerrado en comillas simples seguido del símbolo '%' para utilizarlo en 
	 * clausulas LIKE de SQL
	 * @param parametro
	 * @return
	 */
	public static String conPorcentajesYComillas(String parametro){
		return conComillasSimples(conPorcentajes(parametro));
	}
	
	public static boolean isNull(Object objeto){
		return objeto == null;
	}
	
	public static boolean isNotNull(Object objeto){
		return objeto != null;		
	}
	
	/**
	 * La lista no puede venir vacia o si no se genera una clausua vacía no valida.
	 * @param lista
	 * @return
	 */
	public static String clausulaInSQLStrings(List lista){
		StringBuilder sb = new StringBuilder();
		sb.append("IN ( ");
		int listaSize = lista.size();
		for(int i=0;i<listaSize;i++){
			if(i<listaSize-1){
				sb.append(conComillasSimples(lista.get(i).toString()));
				sb.append(",");
			}else{
				sb.append(conComillasSimples(lista.get(i).toString()));
			}
		}
		sb.append(" ) ");
		
		return sb.toString();
	}
	
	/**
	 * La lista no puede venir vacia o si no se genera una clausua vacía no valida.
	 * @param lista
	 * @return
	 */
	public static String clausulaInSQLSNumeros(List lista){
		
		StringBuilder sb = new StringBuilder();
		sb.append("IN ( ");
		int listaSize = lista.size();
		if(!lista.isEmpty()){
			for(int i=0;i<listaSize;i++){
				if(i<listaSize-1){
					sb.append(lista.get(i) != null ? lista.get(i).toString() : UtilConstantes.CADENA_VACIA);
					sb.append(",");
				}else{
					sb.append(lista.get(i) != null ? lista.get(i).toString() : UtilConstantes.CADENA_VACIA);
				}
			}
			
		}
		sb.append(" ) ");
		
		return sb.toString();
	}
	
	 /**
     * Debido a que en los nombres de los parametros de las consultas no se puede insertar el caracter '.' y algunos
     * nombres compuestos de atributos de entidades se identifican con el nombre de la entidad del
     * PK compuesto, seguido del carÃ¡cter '.' y del nombre del atributo, se reemplaza el '.' por el caracter '_'.
     * 
     * @param nombreAtributo Nombre del atributo a parsear
     * @return El nombre del atributo parseado para su utilizaciÃ³n como nombre del parÃ¡metro.
     */
    public static String obtenerNombreParametro(String nombreAtributo){
        return nombreAtributo.replaceAll("[.]", "_");
    }
    
	/**
	 * Utilidad para analizar un registro de tipo String obtenido a través de una consulta SQL nativa.
	 * Previene que se lanze excepción en caso de que el valor sea nulo.
	 * @param registro
	 * @return
	 */
	public static String procesarRegistroString(Object registro){
		return (registro==null) ? null : (String) registro;
	}
	
	/**
	 * Utilidad para analizar un registro de tipo Date obtenido a través de una consulta SQL nativa.
	 * Previene que se lanze excepción en caso de que el valor sea nulo.
	 * @param registro
	 * @return
	 */
	public static Date procesarRegistroDate(Object registro){
		return (registro==null) ? null : (Date) registro;
	}
	
	/**
	 * Utilidad para analizar un registro de tipo Long obtenido a través de una consulta SQL nativa.
	 * Previene que se lanze excepción en caso de que el valor sea nulo.
	 * @param registro
	 * @return
	 */
	public static Long procesarRegistroLong(Object registro){
		return (registro==null) ? null : ((BigDecimal) registro).longValue();
	}
	
	public static String consultarNombresPersona(String nombreCompleto){
		
		
		String busqueda = "((CONCAT(p.primer_nombre_o_razon_social,p.segundo_nombre,p.primer_apellido,p.segundo_apellido) like '%"+nombreCompleto+"%' ) or "+
				"(CONCAT(p.primer_nombre_o_razon_social,p.segundo_nombre) like '%"+nombreCompleto+"%') or "+  
				"(CONCAT(p.primer_apellido,p.primer_nombre_o_razon_social) like '%"+nombreCompleto+"%') or "+
				"(CONCAT(p.primer_nombre_o_razon_social,p.primer_apellido) like '%"+nombreCompleto+"%')  )";
		
		return busqueda;
	}

	public static String likeSQL(String nombreParametro){
		String like=" like '%"+nombreParametro+"%'";
		
		return like;
	}
	
	/**
	 * @param alias, alias de la tabla usada
	 * @param nombreCompleto nombre de la persona a buscar
	 * @return String con la sub-consulta
	 */
	public static String consultarNombreCompletoPersonaAliasTabla( String alias, String nombreCompleto ){
		return MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO374.toString(), alias, likeSQL(nombreCompleto) ).toString();
	}
	
	/**
	 * @param alias, alias de la tabla usada
	 * @param nombres nombres de la persona a buscar
	 * @return String con la sub-consulta
	 */
	public static String consultarPorNombresPersonaAliasTabla( String alias, String nombres ){
		return MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO375.toString(), alias, likeSQL(nombres) ).toString();
	}
	
	/**
	 * @param alias, alias de la tabla usada
	 * @param apellidos apellidos de la persona a buscar
	 * @return String con la sub-consulta
	 */
	public static String consultarPorApellidosPersonaAliasTabla( String alias, String apellidos ){
		return MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO377.toString(), alias, likeSQL( apellidos ) ).toString();
	}
	
	/**
	 * Devuelve el dominio correspondiente al estado de registro en creacion en comillas simples
	 * para utilizar en las consultas sql
	 * @return
	 */
	public static String estadoRegistroEncreacionSQL(){
		return conComillasSimples(UtilDominios.ESTADO_REGISTRO_ENCREACION);
	}
}
