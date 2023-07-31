package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin

// Escriba en esta seccion sus modificaciones

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.transversal.dto.LlamadaDTO;
import com.ccb.simasc.transversal.dto.LlamadaPlanillaCorrespondenciaDTO;
import com.ccb.simasc.transversal.dto.ReporteCorreosDevueltosDTO;
import com.ccb.simasc.transversal.entidades.Llamada;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;

// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Llamada}
 * 
 * @author sMartinez
 *
 */
@Local
public interface ILlamadaFacade extends IAccesoFacade<Llamada, Long, LlamadaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Metodo que registra una llamada, tambien actualiza correos y envio de carta por
	 * correo si el parametro 'soloRegistroLlamada' esta en false.
	 * 
	 * @param llamadaDTO
	 * @param contextoSesion
	 * @return LlamadaDTO
	 * @throws SIMASCNegocioExcepcion
	 */
	public LlamadaDTO registrarLlamada(LlamadaDTO llamadaDTO, ContextoDeSesion contextoSesion);

	/**
	 * Metodo que obtiene el registro de gestion de llamadas realizadas de
	 * correos devueltos.
	 * 
	 * @param idCaso
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return List<LlamadaDTO>
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<LlamadaDTO> gestionCorreosDevueltos(Long idCaso, Date fechaInicial, Date fechaFinal);

	public void registrarLlamadaSeguimiento(Llamada llamada, String idUsuario);
	
	/**
	 * Metodo que registra la información de una llamada asociada a un correo
	 * @param llamada
	 * @param idUsuario
	 */
	public void registrarLlamadaCorreo(LlamadaPlanillaCorrespondenciaDTO llamada, String usuario);
	// protected region metodos adicionales end
	
	/**
	 * Metodo que retorna lista para el reporte de correos devueltos
	 * @param idCaso id del caso 
	 * @param fechaInicial fecha inicio para el reporte
	 * @param fechaFinal fecha fin para el reporte
	 */
	public List<ReporteCorreosDevueltosDTO> reporteCorreosDevueltos(Long idCaso, Date fechaInicial, Date FechaFinal);

}
