package com.ccb.simasc.transversal.dto.formularios;

import java.util.ArrayList;
import java.util.List;

import com.ccb.simasc.transversal.entidades.Documento;

/**
 * Dto para registrar la información requerida en el requerimiento TRANS-F-009
 * @author jsoto
 *
 */
public class DocumentoBasicoDTO {

	private Long idDocumento;
	private String tipoDocumento;
	private String nombreDocumento;
	private Integer numeroFolios;
	private String cuaderno;
	private String url;
	
	public Long getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	public Integer getNumeroFolios() {
		return numeroFolios;
	}
	public void setNumeroFolios(Integer numeroFolios) {
		this.numeroFolios = numeroFolios;
	}
	public String getCuaderno() {
		return cuaderno;
	}
	public void setCuaderno(String cuaderno) {
		this.cuaderno = cuaderno;
	}
	
	/**
	 * convierte una lista de documentos  a una lista de documentos basicos
	 * @param documentos
	 * @param estado
	 * @return
	 */
	public  static List<DocumentoBasicoDTO> tranformarEnDocumentoBasico(List<Documento> documentos, String estado){
		List<DocumentoBasicoDTO> documentosBasicos = new ArrayList<DocumentoBasicoDTO>();
		if(documentos != null && !documentos.isEmpty()){
			for (Documento documentoFor : documentos) {			
				if(estado != null &&  documentoFor.getEstadoRegistro().equals(estado)){
					documentosBasicos.add(convertirDocumentoBasico(documentoFor));					
				}
			}			
		}
		return documentosBasicos;		
	}
	
	/**
	 * Convierte un documento a documento Basico DTO
	 * @param documento
	 * @return
	 */
	public static DocumentoBasicoDTO convertirDocumentoBasico(Documento documento){
		DocumentoBasicoDTO documentoBasico = new DocumentoBasicoDTO();
		documentoBasico.setIdDocumento(documento.getIdDocumento());
		documentoBasico.setTipoDocumento(documento.getTipoDocumento());
		documentoBasico.setNombreDocumento(documento.getNombre());
		return documentoBasico;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
