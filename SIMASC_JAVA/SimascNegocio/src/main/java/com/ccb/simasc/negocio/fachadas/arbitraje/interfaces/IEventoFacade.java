package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.EventoDTO;
import com.ccb.simasc.transversal.dto.formularios.DatosBasicosCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.RutaDelCasoDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Evento;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Evento}
 * @author sMartinez
 *
 */
@Local
public interface IEventoFacade extends IAccesoFacade<Evento, Long, EventoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	/**
	 * Metodo encargado de realizar un registro de un evento en base de datos
	 * 
	 * @param caso
	 * @param tipoEvento
	 * @param observaciones
	 * @param userName
	 * @param fechaEvento
	 * @param estadoRegistro
	 */
	 public void registrarEvento(Caso caso, String tipoEvento, String observaciones, String userName, Date fechaEvento, String estadoRegistro);
	
	 public List<RutaDelCasoDTO> consultarEventosDeCaso(Long idCaso);
	 
	/**
	 * Método encargado de consultar los eventos de un caso por tipo de evento
	 * 
	 * @param idCaso
	 * @param tiposEvento
	 * @return
	 */
	public List<Evento> consultarEventosCasoPorTipoEvento(Long idCaso, List<String> tiposEvento);
	 
	/**
	 * Metodo encargado de realizar un registro de un evento en base de datos
	 * 
	 * @param idCaso
	 * @param tipoEvento
	 * @param observaciones
	 * @param idUsuarioModifica
	 * @param fechaEvento
	 * @param estadoRegistro
	 */
	 void registrarEvento(Long idCaso, String tipoEvento, String observaciones, String idUsuarioModifica,
			 Date fechaEvento, String estadoRegistro);
	 
	 /**
	  * Devuelve los eventos de un caso como entidades
	  * @param idCaso
	  * @return
	  */
	 public ArrayList<RutaDelCasoDTO> consultarEntidadesEventosDeCaso(Long idCaso);
	 
	 /**
		 * Metodo encargado de realizar un registro de un evento en la fecha actual con registro activo
		 * 
		 * @param idCaso
		 * @param tipoEvento
		 * @param observaciones
		 * @param userName
		 */
	 public void registrarEvento(Long idCaso, String tipoEvento, String observaciones, String idUsuarioModifica);
	 
	 /**
		 * Metodo encargado de consultar la ruta del caso
		 * 
		 * @param idCaso
		 */
	 public List<RutaDelCasoDTO> consultarEventosCasoTipo(Long idCaso);
	 
	 /**
		 * Metodo encargado de consultar el ultimo evento por cada tipo evento enviado por caso
		 * 
		 * @param idCaso
		 */
	 public List<EventoDTO> consultarUltimoEventoPorTipos(List<String> tipoEventos,Long idCaso);
	 
	 public void creaEventoReAperturaCaso(DatosBasicosCasoDTO caso, String usuarioModificacion );
	 
	 public List<EventoDTO> consultarEventosPorTipoOrdernadoFechaDesc(List<String> tipoEventos,Long idCaso);
	// protected region metodos adicionales end

	
}
