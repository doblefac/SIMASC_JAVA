package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.DocumentoObligatorioDTO;
import com.ccb.simasc.transversal.entidades.DocumentoObligatorio;


@Local
public interface IDocumentoObligatorioFacade extends IAccesoFacade<DocumentoObligatorio, Long, DocumentoObligatorioDTO>{
	
	
	/**
	 * Se utiliza para consultar los documentos obligatorios que estan activos por servicio
	 * @param idServicio el identificador del servicio
	 * @return lista de los documentos obligatorios
	 */
	public List<DocumentoObligatorioDTO> consultarDocumentoObligatorioPorServicio(Long idServicio);

}
