package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TipoDocumentoFoliado")
public class TipoDocumentoFoliadoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<DocumentoIndiceElectronicoDTO> DocumentoIndizado;
	public List<DocumentoIndiceElectronicoDTO> getDocumentoIndizado() {
		return DocumentoIndizado;
	}
	public void setDocumentoIndizado(List<DocumentoIndiceElectronicoDTO> documentoIndizado) {
		DocumentoIndizado = documentoIndizado;
	}
	

	
	
}
