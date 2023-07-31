package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorCorrerTraslado;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorrerTrasladoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade;
import com.ccb.simasc.transversal.dto.CorrerTrasladoDTO;
import com.ccb.simasc.transversal.dto.CorrerTrasladoDocumentoDTO;
import com.ccb.simasc.transversal.entidades.CorrerTraslado;
import com.ccb.simasc.transversal.entidades.CorrerTrasladoPK;

@Stateless
@LocalBean
public class CorrerTrasladoFacade extends AccesoFacade<CorrerTraslado, CorrerTrasladoPK, CorrerTrasladoDTO> implements ICorrerTrasladoFacade {

	@EJB
	ManejadorCorrerTraslado manejadorCorrerTraslado;
	
	@EJB
	IDocumentoFacade documentoFacade;
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorCorrerTraslado;
	}

	@Override
	public CorrerTrasladoDTO transformarSinDependencias(CorrerTraslado obj) {
		return null;
	}

	@Override
	public CorrerTraslado transformarEntidadConDependencias(CorrerTraslado obj) {
		return null;
	}

	@Override
	public CorrerTraslado transformarEntidadSinDependencias(CorrerTraslado obj) {
		return null;
	}

	@Override
	public CorrerTrasladoDTO transformarConDependencias(CorrerTraslado obj) {
		return null;
	}
	
	public List<CorrerTrasladoDocumentoDTO> obtenerDocumentosTrasladoPorCaso(Long idCaso){
		return manejadorCorrerTraslado.consultarTrasladosPorCaso(idCaso);
	}

}
