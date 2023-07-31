package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorDiaFestivo;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDiaFestivoFacade;
import com.ccb.simasc.transversal.dto.DiaFestivoDTO;
import com.ccb.simasc.transversal.dto.formularios.ProgramacionAudienciaDTO;
import com.ccb.simasc.transversal.entidades.DiaFestivo;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link DiaFestivo}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class DiaFestivoFacade extends AccesoFacade<DiaFestivo, Long, DiaFestivoDTO> implements IDiaFestivoFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorDiaFestivo manejadorDiaFestivo;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorDiaFestivo;
	}

	@Override
	public DiaFestivoDTO transformarSinDependencias(DiaFestivo obj) {
		DiaFestivoDTO dto = new DiaFestivoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public DiaFestivoDTO transformarConDependencias(DiaFestivo obj) {
		DiaFestivoDTO dto = new DiaFestivoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public DiaFestivo transformarEntidadConDependencias(DiaFestivo obj) {
		DiaFestivo dto = new DiaFestivo();
		dto = new DiaFestivoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public DiaFestivo transformarEntidadSinDependencias(DiaFestivo obj) {
		DiaFestivo dto = new DiaFestivo();
		dto = new DiaFestivoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * Retorna el número de días habiles entre dos fechas excluyendo fecha
	 * inicial y final
	 * 
	 * @param fecha
	 *            Inicial
	 * @param fecha
	 *            final
	 * @return días hábiles entre dos fechas
	 */
	@Override
	public Date adicionarDiasHabilesFecha(Date fechaInicial, int dias) {

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(fechaInicial);
		int anio = startCal.get(Calendar.YEAR);
		List<Date> listadoDiasFestivos = manejadorDiaFestivo.obtenerFestivosAnio( anio );
		int diasHabiles = 0;

		boolean isWorking;
		while (dias > diasHabiles) {
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			if ((startCal.get(Calendar.YEAR)) != anio) {
				anio = startCal.get(Calendar.YEAR);
				listadoDiasFestivos = manejadorDiaFestivo.obtenerFestivosAnio( anio );
			}

			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
					&& startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				isWorking = true;
				for (Iterator<Date> iter = listadoDiasFestivos.iterator(); iter.hasNext();) {
					Date diaFestivo = iter.next();
					Date date = UtilOperaciones.truncDate(startCal.getTime());
					if (date.compareTo(UtilOperaciones.truncDate(diaFestivo)) == 0) {
						isWorking = false;
						break;
					}
				}
				if (isWorking) {
					diasHabiles++;
				}
			}
		}

		return startCal.getTime();
	}

	/**
	 * Metodo que valida si una fecha es festivo
	 * @param fecha
	 * @return boolean: true, si la fecha es festivo
	 */
	@Override
	public boolean validarSiFechaEsFestivo( Date fecha ){
		DiaFestivo diaFestivo = manejadorDiaFestivo.consultarFecha(fecha);
		
		return diaFestivo != null;
	}

	@Override
	public int obtenerDiasHabilesEntreFechas(Date fechaInicial, Date fechaFinal) {
		return manejadorDiaFestivo.obtenerDiasEntreDosFechas(fechaInicial, fechaFinal);
	}
	
	@Override
	public Date sumarDiasHabilesAFecha(Date fechaInicio, Long dias){
		return manejadorDiaFestivo.sumarDiasHabilesAFecha(fechaInicio, dias);
	}
	@Override
	public Date restarDiasHabilesAFecha(Date fechaInicio, Long dias){
		return manejadorDiaFestivo.restarDiasHabilesAFecha(fechaInicio, dias);
	}

	@Override
	public int calcularDiasEntreDosFechas(Date fechaInicial, Date fechaFinal){
		return manejadorDiaFestivo.calcularDiasEntreDosFechas(fechaInicial, fechaFinal);
	}
	
	@Override
	public List<DiaFestivoDTO> consultarDiasFestivos(){
		return manejadorDiaFestivo.consultarDiasFestivos();
	}
	
	@Override
	public List<Date> consultarFestivosEntreFechas(ProgramacionAudienciaDTO programacion) {
		return manejadorDiaFestivo.consultarFestivosEntreFecha(programacion.getFechaInicioProgramarAudiencia(),
				programacion.getFechaFinProgramarAudiencia());
	}
	@Override
	public boolean validarSiFechaEsHabil(Date fecha){
		return fecha != null && (manejadorDiaFestivo.validarSiFechaEsHabil(fecha) < 0);
	}
	// protected region metodos adicionales end
	
}
