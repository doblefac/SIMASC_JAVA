package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta seccion sus modificaciones

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorLoteGenerado;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaLote;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoTramiteOrdinarioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ILoteGeneradoFacade;
import com.ccb.simasc.transversal.dto.LoteGeneradoDTO;
import com.ccb.simasc.transversal.dto.cartas.GeneracionLoteDTO;
import com.ccb.simasc.transversal.dto.cartas.LotesCartasDTO;
import com.ccb.simasc.transversal.entidades.Agendamiento;
import com.ccb.simasc.transversal.entidades.LoteGenerado;
import com.ccb.simasc.transversal.entidades.PersonaLotePK;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link Agendamiento}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class LoteGeneradoFacade extends AccesoFacade<LoteGenerado, Long, LoteGeneradoDTO>
		implements ILoteGeneradoFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	@EJB
	private ManejadorLoteGenerado manejadorLoteGenerado;
	
	@EJB
	private ManejadorPersonaLote manejadorPersonaLote;
	
	@EJB
	private ICasoTramiteOrdinarioFacade casoTramiteOrdinarioFacade;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorLoteGenerado;
	}

	@Override
	public LoteGeneradoDTO transformarSinDependencias(LoteGenerado obj) {
		return new LoteGeneradoDTO().transformarSinDependencias(obj);
	}

	@Override
	public LoteGeneradoDTO transformarConDependencias(LoteGenerado obj) {
		return new LoteGeneradoDTO().transformarConDependencias(obj);
	}

	@Override
	public LoteGenerado transformarEntidadConDependencias(LoteGenerado obj) {
		return new LoteGeneradoDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public LoteGenerado transformarEntidadSinDependencias(LoteGenerado obj) {
		return new LoteGeneradoDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	@Override
	public GeneracionLoteDTO consultarLote(Long idPersona) {
		LoteGenerado lote = manejadorLoteGenerado.consularPrimerLoteGenerado(idPersona);
		if(lote == null)
			throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR928.toString()));
		if(UtilDominios.ESTADO_LOTE_INICIADO.equals(lote.getEstadoGeneracion()))
			throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR929.toString(), lote.getFechaGeneracion()));
		else if (UtilDominios.ESTADO_LOTE_FINALIZADO_ERROR.equals(lote.getEstadoGeneracion())) {
			casoTramiteOrdinarioFacade.eliminarRegistroGeneracion(lote.getIdLote());
			throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR930.toString(), lote.getFechaGeneracion()));
		}
		
		GeneracionLoteDTO loteGenerado = new GeneracionLoteDTO();
		List<LotesCartasDTO> partes = manejadorPersonaLote.obtenerPersonasNoGeneracion(lote.getIdLote());
		loteGenerado.setNombreDocumento(lote.getNombreDocumento());
		loteGenerado.setPartes(partes);
		
		for (LotesCartasDTO lotesCartasDTO : partes) {
			manejadorPersonaLote.eliminarPorId(new PersonaLotePK(lote.getIdLote(), lotesCartasDTO.getIdPersona(), lotesCartasDTO.getCodigoCaso()));
		}
		
		manejadorLoteGenerado.eliminarPorId(lote.getIdLote());
		return loteGenerado;
	}
	
	public void borrarLoteGenerado(Long idLote) {
		manejadorLoteGenerado.borradoLoteGenerado(idLote);
	}
	
	// protected region metodos adicionales end

}
