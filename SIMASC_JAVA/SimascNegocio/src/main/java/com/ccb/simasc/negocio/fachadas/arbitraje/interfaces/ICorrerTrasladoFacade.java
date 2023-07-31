package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.CorrerTrasladoDTO;
import com.ccb.simasc.transversal.dto.CorrerTrasladoDocumentoDTO;
import com.ccb.simasc.transversal.entidades.CorrerTraslado;
import com.ccb.simasc.transversal.entidades.CorrerTrasladoPK;

@Local
public interface ICorrerTrasladoFacade extends IAccesoFacade<CorrerTraslado, CorrerTrasladoPK,CorrerTrasladoDTO> {

	public List<CorrerTrasladoDocumentoDTO> obtenerDocumentosTrasladoPorCaso(Long idCaso);
}
