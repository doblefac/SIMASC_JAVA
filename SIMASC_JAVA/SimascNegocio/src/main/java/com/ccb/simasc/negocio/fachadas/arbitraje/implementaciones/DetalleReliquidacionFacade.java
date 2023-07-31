package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorDetalleReliquidacion;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDetalleReliquidacionFacade;
import com.ccb.simasc.transversal.dto.DetalleReliquidacionDTO;
import com.ccb.simasc.transversal.entidades.DetalleReliquidacion;
import com.ccb.simasc.transversal.entidades.DetalleReliquidacionPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link DetalleReliquidacion}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class DetalleReliquidacionFacade extends AccesoFacade<DetalleReliquidacion, DetalleReliquidacionPK, DetalleReliquidacionDTO> implements IDetalleReliquidacionFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorDetalleReliquidacion manejadorDetalleReliquidacion;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorDetalleReliquidacion;
	}

	@Override
	public DetalleReliquidacionDTO transformarSinDependencias(DetalleReliquidacion obj) {
		return new DetalleReliquidacionDTO().transformarSinDependencias(obj);
	}

	@Override
	public DetalleReliquidacionDTO transformarConDependencias(DetalleReliquidacion obj) {
		return new DetalleReliquidacionDTO().transformarConDependencias(obj);
	}

	@Override
	public DetalleReliquidacion transformarEntidadConDependencias(DetalleReliquidacion obj) {
		return new DetalleReliquidacionDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public DetalleReliquidacion transformarEntidadSinDependencias(DetalleReliquidacion obj) {
		return new DetalleReliquidacionDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IDetalleReliquidacionFacade#crearDetallePago(java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.lang.String)
	 */
	@Override
	public void crearDetalleReliquidacion(Long idReliquidacion, Long itemId, Long valor, String servicioCaja) {
		DetalleReliquidacion detalle = new DetalleReliquidacion();
		DetalleReliquidacionPK pk = new DetalleReliquidacionPK();
		pk.setCodigoItem(itemId);
		pk.setIdReliquidacion(idReliquidacion);
		detalle.setDetalleReliquidacionPK(pk);
		detalle.setValor(valor);
		detalle.setServicioCaja(servicioCaja);
		detalle.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		crear(detalle);
	}
	// protected region metodos adicionales end
	
}
