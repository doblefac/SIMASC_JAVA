package com.ccb.simasc.integracion.manejadores.utilidades;

import java.util.ArrayList;
import java.util.List;

import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.enumeraciones.TipoOperador;
import com.ccb.simasc.transversal.dto.InformacionFiltroDTO;

/**
 * Clase que representa una condición de filtrado para un consulta JPQL. La condición
 * de define con un campo, un operador de comparación (=,!=,{@literal <}, etc), un valor y un 
 * tipo de operador de concatenación (OR o AND) para unir esta condicion con
 * otra condición.
 * 
 * @author jsoto
 */
public class InformacionFiltro {

	public final String campo;
	public final TipoFiltro tipo;
	public final Object valor;
	public final TipoOperador operador;

        /**
         * Por defecto se selecciona un tipo de operador AND.
         * @param tipo Tipo del filtro
         * @param campo Nombre del campo 
         * @param valor Valor por el cual filtrar el campo
         */
	public InformacionFiltro(TipoFiltro tipo, String campo, Object valor) {
		this.tipo = tipo;
		this.campo = campo;
		this.valor = valor;
		this.operador = TipoOperador.AND;
	}

        /**
         * 
         * @param tipo Tipo del filtro
         * @param campo Nombre del campo
         * @param valor Valor por el cual filtrar el campo
         * @param tipoOperador Operador de concatenación
         */
	public InformacionFiltro(TipoFiltro tipo, String campo, Object valor,
			TipoOperador tipoOperador) {
		this.tipo = tipo;
		this.campo = campo;
		this.valor = valor;
		this.operador = tipoOperador;
	}
	
	/**
	 * Crea un filtro simple y lo devuelve como una lista de filtros.
	 * 
	 * @param tipo TipoFiltro
	 * @param campo Campo sobre el cual se va a aplicar el filtro
	 * @param valor 
	 * @return Una lista de filtro con el filtro definido por los parámetros
	 */
	public static List<InformacionFiltro> newInformacionFiltroList(TipoFiltro tipo, String campo, Object valor){
		List<InformacionFiltro> lista = new ArrayList<>();
		lista.add(new InformacionFiltro(tipo, campo, valor));		
		return lista;
	}
	
	/**
	 * Convierte de InformacionFiltroDTO a InformacionFiltro
	 * @param filtrosParametro
	 * @return
	 */
	public static List<InformacionFiltro> traducirFiltros(List<InformacionFiltroDTO> filtrosParametro){
		List<InformacionFiltro> retorna = new ArrayList<InformacionFiltro>();
		for (InformacionFiltroDTO filtro : filtrosParametro) {	
			if(filtro.getTipo() != null && filtro.getOperador() != null){
				InformacionFiltro f = new InformacionFiltro(TipoFiltro.valueOf(filtro.getTipo()), filtro.getCampo(),
						filtro.getValor(),TipoOperador.valueOf(filtro.getOperador()));
				retorna.add(f);
			}else if(filtro.getTipo() != null){
				InformacionFiltro f = new InformacionFiltro(TipoFiltro.valueOf(filtro.getTipo()), filtro.getCampo(),
						filtro.getValor());
				retorna.add(f);
			}
			
		}
		return retorna;
	}
        

}