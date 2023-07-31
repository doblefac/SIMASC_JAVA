package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.LaudoDTO;
import com.ccb.simasc.transversal.dto.formularios.DetalleLaudoDTO;
import com.ccb.simasc.transversal.entidades.Laudo;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Laudo}
 * @author sMartinez
 *
 */
@Local
public interface ILaudoFacade extends IAccesoFacade<Laudo, Long, LaudoDTO> {

	

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public Boolean consultarExistenciaLaudo(Long idCaso);
	public LaudoDTO guardarLaudo(Long idCaso, Laudo laudo);
	public Laudo consultarLaudo(Long idCaso);
	public List<DetalleLaudoDTO> obtenerLaudos(Date fechaIni, Date fechaFin, String arbitros, String partes,
			String docParte, Long codigoCaso, Long materia, String estado);
	// protected region metodos adicionales end


	
}
