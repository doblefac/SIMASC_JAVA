package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.DiaFestivoDTO;
import com.ccb.simasc.transversal.dto.formularios.ProgramacionAudienciaDTO;
import com.ccb.simasc.transversal.entidades.DiaFestivo;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link DiaFestivo}
 * @author sMartinez
 *
 */
@Local
public interface IDiaFestivoFacade extends IAccesoFacade<DiaFestivo, Long, DiaFestivoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public Date adicionarDiasHabilesFecha(Date fechaInicial, int dias);

	public boolean validarSiFechaEsFestivo(Date fecha);

	public int obtenerDiasHabilesEntreFechas(Date fechaInicial, Date fechaFinal);

	/**
	 * Retorna la fecha de la suma entre la fecha inicial y los días hábiles.
	 * 
	 * @param fechaInicio
	 * @param días
	 * @return
	 */
	public Date sumarDiasHabilesAFecha(Date fechaInicio, Long dias);

	public int calcularDiasEntreDosFechas(Date fechaInicial, Date fechaFinal);
	
	public List<DiaFestivoDTO> consultarDiasFestivos();
	
	public List<Date> consultarFestivosEntreFechas(ProgramacionAudienciaDTO programacion);
	// protected region metodos adicionales end

	public boolean validarSiFechaEsHabil(Date fecha);

	public Date restarDiasHabilesAFecha(Date fechaInicio, Long dias);
	
}
