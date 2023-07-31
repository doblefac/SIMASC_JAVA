package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.DocumentoRadicadoDTO;
import com.ccb.simasc.transversal.entidades.DocumentoRadicado;

@Local
public interface IDocumentoRadicadoFacade extends IAccesoFacade<DocumentoRadicado, Long, DocumentoRadicadoDTO> {
	
	 public void guardarDocumentoRadicado(Long idDocumento);	 
}
