package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.Date;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.FechaCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.HitosCasoDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.FechaCaso;
import com.ccb.simasc.transversal.entidades.FechaCasoPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link FechaCaso}
 * @author sMartinez
 *
 */
@Local
public interface IFechaCasoFacade extends IAccesoFacade<FechaCaso, FechaCasoPK, FechaCasoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	public HitosCasoDTO obtenerHitosCaso(Long idCaso);

	public void actualizarHitosCaso(HitosCasoDTO hitos);

	public FechaCaso registrarFechaCaso(Date fecha, String tipoFecha, Long idCaso, String idUsuario);

	public Date calcularFechaLimiteParaCierreDeCaso(Long idCaso);
	
	public int calcularDiasFaltantesParaCierreDeCaso(Long idCaso, int diasTranscurridos);
	
	public void creaFechaCasoReApertura(Caso caso, String usuarioModificacion);
	
	// protected region metodos adicionales end
	
}
