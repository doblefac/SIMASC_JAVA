package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

import java.util.ArrayList;

// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorCuota;
import com.ccb.simasc.integracion.manejadores.ManejadorObligacion;
import com.ccb.simasc.integracion.manejadores.ManejadorObligacionParte;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IObligacionFacade;
import com.ccb.simasc.transversal.dto.CuotaDTO;
import com.ccb.simasc.transversal.dto.ObligacionDTO;
import com.ccb.simasc.transversal.dto.SeguimientoObligacionesDTO;
import com.ccb.simasc.transversal.entidades.Cuota;
import com.ccb.simasc.transversal.entidades.Obligacion;
import com.ccb.simasc.transversal.entidades.ObligacionPK;
import com.ccb.simasc.transversal.entidades.ObligacionParte;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link Obligacion}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ObligacionFacade extends AccesoFacade<Obligacion, ObligacionPK, ObligacionDTO>
		implements IObligacionFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	@EJB
	private ManejadorObligacion manejadorObligacion;

	@EJB
	private ManejadorObligacionParte manejadorObligacionParte;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorCuota manejadorCuota;
	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorObligacion;
	}

	@Override
	public ObligacionDTO transformarSinDependencias(Obligacion obj) {
		ObligacionDTO dto = new ObligacionDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ObligacionDTO transformarConDependencias(Obligacion obj) {
		ObligacionDTO dto = new ObligacionDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Obligacion transformarEntidadConDependencias(Obligacion obj) {
		Obligacion dto = new Obligacion();
		dto = new ObligacionDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Obligacion transformarEntidadSinDependencias(Obligacion obj) {
		Obligacion dto = new Obligacion();
		dto = new ObligacionDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public List<SeguimientoObligacionesDTO> consultarObligacionesCaso(Long idCaso) {
		List<SeguimientoObligacionesDTO> seguimientoObligaciones = new ArrayList<>();
		List<SeguimientoObligacionesDTO> obligaciones = manejadorObligacion.consultarObligacionesCaso(idCaso);
		for (SeguimientoObligacionesDTO obligacion : obligaciones) {
			if (obligacion.getTipoObligacion().equals(UtilDominios.OBLIGACION_DAR)){
				List<Cuota> cuotasin = new ArrayList<>();
				List<Cuota> cuotas = manejadorCuota.obtenerCuotasByIdResultadoConciliacion(obligacion.getIdResultadoAudiencia());
				for (Cuota c: cuotas){
					cuotasin.add(new CuotaDTO().transformarEntidadSinDependencias(c));
				}
				obligacion.setCuotas(cuotasin);
			}				
						
			seguimientoObligaciones.add(obligacionDeLasPartes(obligacion));
		}

		return seguimientoObligaciones;
	}

	private SeguimientoObligacionesDTO obligacionDeLasPartes(SeguimientoObligacionesDTO obligacion) {
		StringBuilder quienPaga = new StringBuilder();
		StringBuilder quienRecibe = new StringBuilder();
		StringBuilder quienSeObliga = new StringBuilder();
		List<ObligacionParte> obligacionesPartes = manejadorObligacionParte
				.consultarObligacionParteByIdResultadoAudiencia(obligacion.getIdResultadoAudiencia());
		if (!obligacionesPartes.isEmpty())
			for (ObligacionParte obligacionParte : obligacionesPartes) {
				Persona persona = manejadorPersona.buscar(obligacionParte.getObligacionPartePK().getIdPersona());
				if (obligacionParte.getObligacionPartePK().getTipoObligacion().equals(UtilDominios.OBLIGACION_DAR)
						&& obligacionParte.getTipoParteResultado().equals(UtilDominios.OBLIGACION_PARTE_PAGA))
					if (quienPaga.toString().isEmpty())
						quienPaga.append(persona.getNombreCompleto());
					else
						quienPaga .append(", ").append(persona.getNombreCompleto());
				else if (obligacionParte.getObligacionPartePK().getTipoObligacion().equals(UtilDominios.OBLIGACION_DAR)
						&& obligacionParte.getTipoParteResultado().equals(UtilDominios.OBLIGACION_PARTE_RECIBE))
					if (quienRecibe.toString().isEmpty())
						quienRecibe.append(persona.getNombreCompleto());
					else
						quienRecibe.append(", ").append(persona.getNombreCompleto());
				else if ((obligacionParte.getObligacionPartePK().getTipoObligacion()
						.equals(UtilDominios.OBLIGACION_HACER)
						&& obligacionParte.getTipoParteResultado().equals(UtilDominios.OBLIGACION_PARTE_HACE))
						|| (obligacionParte.getObligacionPartePK().getTipoObligacion()
								.equals(UtilDominios.OBLIGACION_NO_HACER)
								&& obligacionParte.getTipoParteResultado()
										.equals(UtilDominios.OBLIGACION_PARTE_NO_HACE)))
					if (quienSeObliga.toString().isEmpty()){
						quienSeObliga.append(persona.getNombreCompleto());
						obligacion.setQueDebeHacer(obligacion.getCompromiso());
					}						
					else
						quienSeObliga.append(", ").append(persona.getNombreCompleto());
			}
		obligacion.setQuienPagaAQuien(quienPaga.toString() + " paga(n) a " + quienRecibe.toString());
		obligacion.setQuienSeObliga(quienSeObliga.toString());
		return obligacion;
	}

	// protected region metodos adicionales end

}
