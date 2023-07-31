package com.ccb.simasc.transversal.dto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ccb.simasc.transversal.dto.formularios.DocumentoBasicoDTO;
import com.ccb.simasc.transversal.entidades.PartePeticion;
import com.ccb.simasc.transversal.entidades.Peticion;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

public class PeticionBasicaDTO {
	private Long idPeticion;
	private String tipo;	
	private String solicitante;
	private Date fechaRespuesta;		
	private String respuesta;
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;	
	private String texto;
	private Long idCaso;		
	private List<DocumentoBasicoDTO> documentos;
	private List<RolPersonaCasoDTO> parteRespuesta;
	
	/**
	 * @return the idPeticion
	 */
	public Long getIdPeticion() {
		return idPeticion;
	}
	/**
	 * @param idPeticion the idPeticion to set
	 */
	public void setIdPeticion(Long idPeticion) {
		this.idPeticion = idPeticion;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the solicitante
	 */
	public String getSolicitante() {
		return solicitante;
	}
	/**
	 * @param solicitante the solicitante to set
	 */
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}
	/**
	 * @return the fechaRespuesta
	 */
	public Date getFechaRespuesta() {
		return fechaRespuesta;
	}
	/**
	 * @param fechaRespuesta the fechaRespuesta to set
	 */
	public void setFechaRespuesta(Date fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}
	/**
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}
	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	/**
	 * @return the fechaUltimaModificacion
	 */
	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}
	/**
	 * @param fechaUltimaModificacion the fechaUltimaModificacion to set
	 */
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
	/**
	 * @return the estadoRegistro
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	/**
	 * @param estadoRegistro the estadoRegistro to set
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
	/**
	 * @return the idCaso
	 */
	public Long getIdCaso() {
		return idCaso;
	}
	/**
	 * @param idCaso the idCaso to set
	 */
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	/**
	 * @return the documentos
	 */
	public List<DocumentoBasicoDTO> getDocumentos() {
		return documentos;
	}
	/**
	 * @param documentos the documentos to set
	 */
	public void setDocumentos(List<DocumentoBasicoDTO> documentos) {
		this.documentos = documentos;
	}
	
	/**
	 * @return the parteRespuesta
	 */
	public List<RolPersonaCasoDTO> getParteRespuesta() {
		return parteRespuesta;
	}
	/**
	 * @param parteRespuesta the parteRespuesta to set
	 */
	public void setParteRespuesta(List<RolPersonaCasoDTO> parteRespuesta) {
		this.parteRespuesta = parteRespuesta;
	}
	
	/**
	 * convierte una peticion a peticion basicaDTO
	 * @param peticion
	 * @return
	 */
	public static PeticionBasicaDTO convertirPeticionBasica(Peticion peticion){

		PeticionBasicaDTO peticionBasica = new PeticionBasicaDTO();
		peticionBasica.setTipo(peticion.getTipo());
		peticionBasica.setTexto(peticion.getTexto());
		peticionBasica.setIdCaso(peticion.getIdCaso());
		peticionBasica.setRespuesta(peticion.getRespuesta());
		peticionBasica.setEstadoRegistro(peticion.getEstadoRegistro());
		peticionBasica.setIdPeticion(peticion.getIdPeticion());
		peticionBasica.setFechaRespuesta(peticion.getFechaRespuesta());
		peticionBasica.setFechaUltimaModificacion(peticion.getFechaUltimaModificacion());
		if(peticion.getPartePeticionList() != null){
			peticionBasica.setSolicitante(nombresConvocantes(peticion.getPartePeticionList()));						
		}
		return peticionBasica;
	}
	
	/**
	 * retorna el nombre de las partes que solicitaron 
	 * @param partes
	 * @return
	 */
	private static String nombresConvocantes(List<PartePeticion> partes){
		StringBuilder nombre = new StringBuilder();
		List<PartePeticion> partesSolicitantes = new ArrayList<PartePeticion>();
		for (PartePeticion parteFor : partes) {
			if(UtilDominios.TIPO_PARTE_PETICION_SOLICITA_RESPUESTA.equals(parteFor.getPartePeticionPK().getTipo()))
			partesSolicitantes.add(parteFor);
		}	
		
		if(!partesSolicitantes.isEmpty()){
			for (int i = 0; i < partesSolicitantes.size(); i++) {				
				if(i != 0){
					nombre.append(", ");					
				}
				nombre.append(partesSolicitantes.get(i).getRolPersonaCaso().getPersona().getNombreCompleto() );				
			}			
		}
		return nombre.toString();	
		
	}
	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}
	/**
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}


}
