package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin

// Escriba en esta sección sus modificaciones

import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.SalaDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltrosSalasDTO;
import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.entidades.InfraestructuraSala;
import com.ccb.simasc.transversal.entidades.Sala;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;

// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Sala}
 * 
 * @author sMartinez
 *
 */
@Local
public interface ISalaFacade extends IAccesoFacade<Sala, Long, SalaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/**
	 * Se obtienen todas las salas activas con id y nombre de la sala
	 * 
	 * @return List<GenericoDTO>
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<GenericoDTO> obtenerSalas() throws SIMASCNegocioExcepcion;

	/**
	 * @param filtrosSalasDTO
	 * @return
	 */
	public List<SalaDTO> buscarSalaDisponibles(FiltrosSalasDTO filtrosSalasDTO) throws SimascException;
	// protected region metodos adicionales end

	/**
	 * @param filtrosSalasDTO
	 * @return
	 */
	public void modificarInfraestructuraSala(List<InfraestructuraSala> salas);
	// protected region metodos adicionales end


}
