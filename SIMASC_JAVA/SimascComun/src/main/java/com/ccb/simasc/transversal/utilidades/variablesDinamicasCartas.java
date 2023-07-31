package com.ccb.simasc.transversal.utilidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.ManejadorPlantillaCarta;
import com.ccb.simasc.integracion.manejadores.ManejadorValorPlantillaCarta;
import com.ccb.simasc.transversal.entidades.ValorPlantillaCarta;


@Stateless
@LocalBean
public class variablesDinamicasCartas implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private transient EntityManager em;

	@EJB
	private ManejadorPlantillaCarta manejadorPlantillaCarta;
	
	@EJB
	private ManejadorValorPlantillaCarta manejadorValoresPlantillaCarta;

	/**
	 * Método encargado de obtener la información de la plantilla de una carta y
	 * reemplazar las variables de dicha plantilla
	 * 
	 * @param idPlantilla
	 * @param idCaso
	 * @param idPersona
	 * @param idAudiencia
	 * @param args
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public LinkedHashMap<String, String> getInformacionPlantilla(Long idPlantilla, Long idCaso, Long idPersona, Long idAudiencia, Map<String,Object> args) {
		LinkedHashMap<String, String> filtrosVariables = new LinkedHashMap<>();
		List<ValorPlantillaCarta> valoresPlantillaCarta = manejadorValoresPlantillaCarta.consultarValoresPlantilla(idPlantilla);
		
		boolean isAudiencia = esDirigidoA(valoresPlantillaCarta, UtilDominios.ID_AUDIENCIA);
		boolean isTipoParte = esDirigidoA(valoresPlantillaCarta, UtilDominios.ID_PERSONA);
		boolean isTipoInvitado = esDirigidoA(valoresPlantillaCarta, UtilDominios.ID_PERSONA_INVITADO);

		StringBuilder jpqlQuery = new StringBuilder();
		List<Object[]> rows = new ArrayList<>();
		for (ValorPlantillaCarta valor : valoresPlantillaCarta) {
			if (valor.getConsulta() != null) {

				jpqlQuery.append(valor.getConsulta());
				String concreteQuery = jpqlQuery.toString();			
				Boolean isDocRadArgs = false;
				Boolean isApoderadosArgs = false;
				Boolean isPartesSeleccionadasArgs = false;
				Boolean isJpql = !concreteQuery.contains("NATIVE:");
				Boolean isList = concreteQuery.contains("LIST:");
				Boolean isListU = concreteQuery.contains("LST_U:");
				Boolean isListY = concreteQuery.contains("LST_Y:");	
				concreteQuery = (isList) ? concreteQuery.replace("LIST:", "") : concreteQuery;
				concreteQuery = (isListU) ? concreteQuery.replace("LST_U:", "") : concreteQuery;
				concreteQuery = (isListY) ? concreteQuery.replace("LST_Y:", "") : concreteQuery;
				concreteQuery = (!isJpql) ? concreteQuery.replace("NATIVE:", "") : concreteQuery;
				
				if(args != null) {
					isDocRadArgs = UtilDominios.DOCUMENTOS_RADICADOS.equals(valor.getNombreValorDinamico())
							&& args.containsKey(UtilDominios.DOCUMENTOS_RADICADOS);
					isApoderadosArgs = UtilDominios.PERSONAS_APODERADOS.equals(valor.getNombreValorDinamico())
							&& args.containsKey(UtilDominios.PERSONAS_APODERADOS);
					isPartesSeleccionadasArgs = UtilDominios.LISTA_PARTES_SELECCIONADAS.equals(
							valor.getNombreValorDinamico()) && args.containsKey(UtilDominios.LISTA_PARTES_SELECCIONADAS);
				}
				
				if (isDocRadArgs)
					// Modificar consulta con idsDocumentos
					concreteQuery = concreteQuery.concat(" AND d.id_documento IN ").concat(
							configurarParametroDocumentos((List<Object>) args.get(UtilDominios.DOCUMENTOS_RADICADOS)));
				if (isApoderadosArgs)
					// Modifica la consulta con los id's de las personas
					concreteQuery = concreteQuery.concat(" AND rpc.id_persona IN ").concat(
							configurarParametroDocumentos((List<Object>) args.get(UtilDominios.PERSONAS_APODERADOS)));
				if (isPartesSeleccionadasArgs) {
					// Modifica la consulta con los id's de las partes
					if (concreteQuery.contains(UtilDominios.PARTES_SELECCIONADAS))
						concreteQuery = concreteQuery.replace(UtilDominios.PARTES_SELECCIONADAS,
								"AND r.id_persona IN " + configurarParametroDocumentos(
										(List<Object>) args.get(UtilDominios.LISTA_PARTES_SELECCIONADAS)));
				}
				
				Query query = (isJpql) ? em.createQuery(concreteQuery) : em.createNativeQuery(concreteQuery);
				int param = concreteQuery.split("\\?", -1).length - 1;
				if (isJpql) {
					query.setParameter("idCaso", idCaso);
					if( isTipoParte && idPersona != null && concreteQuery.contains("idPersona")) {
						query.setParameter("idPersona", idPersona);
					}
				} else {
					query.setParameter(1, idCaso);
					if (param > 0 && ((isTipoParte || isTipoInvitado) && isAudiencia)) {
						query.setParameter(2, idPersona);
						if(idAudiencia != null) {
							query.setParameter(3, idAudiencia);	
						}										
						if (args != null && param >= 3 && args.containsKey(UtilDominios.TIPO_DESTINATARIO))
							query.setParameter(4, args.get(UtilDominios.TIPO_DESTINATARIO));
					} else if (isTipoParte) {
						query.setParameter(2, idPersona);
					} else if (idAudiencia != null) {						
							query.setParameter(2, idAudiencia);
					}
				}
				if (isList) {
					rows = query.getResultList();
					filtrosVariables.put(valor.getNombreValorDinamico(), generarResultadoList(rows, isList));
				}
				else if(isListU){
					rows = query.getResultList();
					filtrosVariables.put(valor.getNombreValorDinamico(), generarResultadoUList(rows));
				}
				else if(isListY){
					List<Object> resultado = query.getResultList();
					filtrosVariables.put(valor.getNombreValorDinamico(), generarResultadoY(resultado));
				}
				else {
					List<Object> resultado = query.getResultList();
					filtrosVariables.put(valor.getNombreValorDinamico(), generarResultado(resultado, isList));
				}
				jpqlQuery = new StringBuilder();
			}
		}

		return filtrosVariables;
	}
	
	/**
	 * Método encargado de validar dentro de las banderas de la plantilla a
	 * quien o quienes se les genera las cartas: Invitados, partes o audiencia.
	 * 
	 * @param valorPlantillaCartas
	 * @param dirigido
	 * @return Boolean
	 */
	private Boolean esDirigidoA(List<ValorPlantillaCarta> valorPlantillaCartas, String dirigido) {
		Boolean validacion = false;
		for (ValorPlantillaCarta vpc : valorPlantillaCartas) {
			if (dirigido.equals(vpc.getNombreValorDinamico())) {
				validacion = true;
				break;
			}
		}
		return validacion;
	}
	
	private String configurarParametroDocumentos(List<Object> objetos){
		String retorno = "";
		retorno +="(";
		for(Object valor: objetos)
			retorno = retorno + valor.toString() +",";
		retorno = retorno.substring(0, retorno.lastIndexOf(','));
		retorno += ")";
		return retorno;
	}
	
	private String generarResultadoList(List<Object[]> rows, Boolean isList) {
		String resultado = "";
		if (isList) {
			for (Object row : rows) {
				resultado += ("<tr>");
				if (row instanceof Object[])
					for (int i = 0; i < ((Object[]) row).length; i++) {
						resultado += ("<td>" + ((Object[]) row)[i] + "</td>");
					}
				else
					resultado += row;

				resultado += ("</tr>");
			}
		}
		return resultado;
	}
	
	private String generarResultadoUList(List<Object[]> rows) {
		StringBuilder stringBuilder = new StringBuilder();
		
		for (Object row : rows) {			
			if (row instanceof Object[])
			{
				for (int i = 0; i < ((Object[]) row).length; i++) {
					stringBuilder.append("<li>" + ((Object[]) row)[i] + "</li>");
				}
			}
			else
			{
				stringBuilder.append("<li>" + row.toString() + "</li>");
			}
		}
		
		if(stringBuilder.toString().length() > 0)
		{
			stringBuilder.insert(0, "<ul>");
			stringBuilder.append("</ul>");
		}
		
		return stringBuilder.toString();
	}

	private String generarResultado(List<Object> list, Boolean isList){
		String resultado ="";
		String separador = ", ";
		for(Object elemento : list)
			if(elemento != null)
				resultado=resultado + (elemento.toString()+separador);		
		resultado = resultado.trim();
		return (resultado.length()>0)?resultado.substring(0, resultado.length()-1):resultado;
	}
	
	private String generarResultadoY(List<Object> list) {
		StringBuilder strBuilder = new StringBuilder();
		
		String separador = ", ";
		for(Object elemento : list){
			if(elemento != null) {
				if(list.size() >= 2 && elemento == list.get(list.size() - 1)) {
					strBuilder.append(" y ");
				}
				
				strBuilder.append(elemento.toString().trim());
				
				if(list.size() > 2 && elemento != list.get(list.size() - 1)) {
					strBuilder.append(separador);
				}
			}						
		}		
		
		return strBuilder.toString();
	}
}
