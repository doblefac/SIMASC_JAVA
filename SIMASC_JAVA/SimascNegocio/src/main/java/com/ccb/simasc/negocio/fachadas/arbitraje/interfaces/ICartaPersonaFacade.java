package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin

// Escriba en esta seccion sus modificaciones

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.transversal.dto.CartaPendienteImpresionDTO;
import com.ccb.simasc.transversal.dto.CartaPersonaDTO;
import com.ccb.simasc.transversal.dto.cartas.LotesCartasDTO;
import com.ccb.simasc.transversal.dto.CorrespondenciaDTO;
import com.ccb.simasc.transversal.dto.LlamadaPlanillaCorrespondenciaDTO;
import com.ccb.simasc.transversal.dto.PlanillaCorrespondenciaCartaDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosGenerarCartaDTO;
import com.ccb.simasc.transversal.dto.reportes.PlanillaCorrespondenciaDTO;
import com.ccb.simasc.transversal.entidades.CartaPersona;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;

// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link CartaPersona}
 * 
 * @author sMartinez
 *
 */
@Local
public interface ICartaPersonaFacade extends IAccesoFacade<CartaPersona, Long, CartaPersonaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	Collection<CartaPersona> transformarCollection(Long idCaso) throws SIMASCNegocioExcepcion;

	public List<CartaPersona> generarCarta(ParametrosGenerarCartaDTO filtros, String idUsuario) throws SIMASCNegocioExcepcion;

	public List<LotesCartasDTO> generarLotesCartas(List<LotesCartasDTO> lotesCartas, ContextoDeSesion usuario);

	public List<CartaPersona> generarCartaAudiencias(ParametrosGenerarCartaDTO filtros) throws SIMASCNegocioExcepcion;

	public CartaPersonaDTO actualizarCarta(CartaPersona carta, Long idCaso);

	public void enviarCarta(CartaPersona carta, boolean indicadorImpresion, boolean indicadorNotificacion);

	public List<CartaPersona> consultarCartas(Long idCaso);

	public CartaPersona consultarCarta(Long idCarta);

	public List<CartaPendienteImpresionDTO> consultarCartasPendientesImpresion();

	public void actualizarEstadoCartaImpresa(Long idCarta);

	public void consultarCartasAudienciaPendientesImpresion();

	/**
	 * Metodo encargado de obtener la informacion para la generacion de cartas
	 * de citacion a Audiencias de Sorteo, Instalacion y Designacion.
	 * 
	 * @param idCaso
	 * @return String
	 * @throws SIMASCNegocioExcepcion
	 */
	public String obtenerCartasCitacionAudiencias(Long idCaso) throws SIMASCNegocioExcepcion;

	/**
	 * Método encargado de registrar el evento de notificación al árbitro del
	 * caso y actualizar el estado de la carta
	 * 
	 * @param cartaPersonaDTO
	 * @param envio
	 */
	public void confirmacionNotificacionArbitro(CartaPersonaDTO cartaPersonaDTO, boolean envio);

	/**
	 * Método que actualiza el estado de las cartas a enviada despues de que se
	 * genera la planilla de correspondencia
	 * 
	 * @param planilla
	 */
	public void actualizarEstadoPlanilla(List<PlanillaCorrespondenciaDTO> planilla);

	/**
	 * Metodo que permite consultar el estado de las correspondencias.
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 * @param estadosCarta:
	 *            Estados de carta de envio fisico.
	 * @return List<CorrespondenciaDTO>: Lista de correspondencia.
	 */
	public List<CorrespondenciaDTO> consultarCorrespondencia(Long idCaso, List<String> estadosCarta);

	/** CON-F-106 9-02-2018
	 * Metodo que genera las cartas a las partes de un caso tras crear una audiencia
	 * @author prendon
	 * @param idCaso
	 * @param idAudiencia
	 * @param consecutivoAudiencia
	 */
	public void generarCartasProgramacionAudiencias( Long idCaso, Long idAudiencia, Long consecutivoAudiencia );
	
	

	/** CON-C-052
	 * Metodo que genera las cartas a las partes de un caso tras crear una audiencia
	 * @author cagonzalez
	 * @param filtroBusqueda
	 */
	public PlanillaCorrespondenciaCartaDTO consultarInformacionEstadoCorrespondencia( PlanillaCorrespondenciaCartaDTO filtroBusqueda );
	
	/** CON-C-052
	 * Metodo que actualiza el estado y número guía de la carta
	 * @author cagonzalez
	 * @param cartas
	 */
	public void actualizarEstadoCorrespondencia( List<LlamadaPlanillaCorrespondenciaDTO> cartas, String idUsuario );
	// protected region metodos adicionales end
	
	public void enviarCartaCorreo(CartaPersona carta);

}
