package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;

import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

/**
 * DTO utilizado para retornar cada registro consultado en el requerimiento TRANSF009 Listar casos asignados
 * Representa información del caso asociado a un funcionario
 * @author jsoto
 *
 */
public class CasoAsignadoDTO {
	
	private Long codigoCaso;
	private Long idServicio;
	private String nombreCaso;
	private Date fechaRadicacion;
	private String estadoCaso;
	private Date fechaUltimoEstado;
	//Nombre de la sede
	private String sede;
	//Nombre completo del funcionario asignado al caso que cumple con los parámetros de la consulta
	//Este campo no aplica para cuando el tipo de servicio es arbitraje (Plan Justicia)
	private String funcionarioAsignado;	
	//Resultado del caso (Si está disponible)
	private String resultado;
	//Servicio del caso (Solo aplica para cuando el servicio es de planJusticia)
	private String nombreServicio;
	//Informacion adicional del caso para saber a que pantalla direccionar
	private String etapa;
	//dominio del Tipo de servicio del caso 
	private String tipoServicio;
	
	public Long getCodigoCaso() {
		return codigoCaso;
	}
	public void setCodigoCaso(Long codigoCaso) {
		this.codigoCaso = codigoCaso;
	}
	
	public Long getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	
	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	public Date getFechaRadicacion() {
		return fechaRadicacion;
	}
	public void setFechaRadicacion(Date fechaRadiacion) {
		this.fechaRadicacion = fechaRadiacion;
	}
	public String getEstadoCaso() {
		return estadoCaso;
	}
	public void setEstadoCaso(String estadoCaso) {
		this.estadoCaso = estadoCaso;
	}
	public Date getFechaUltimoEstado() {
		return fechaUltimoEstado;
	}
	public void setFechaUltimoEstado(Date fechaUltimoEstado) {
		this.fechaUltimoEstado = fechaUltimoEstado;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public String getFuncionarioAsignado() {
		return funcionarioAsignado;
	}
	public void setFuncionarioAsignado(String funcionarioAsignado) {
		this.funcionarioAsignado = funcionarioAsignado;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}	
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}		
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	
	/**
	 * Genera el CasoAsignadoDTO a partir de la entidad Caso 
	 * Omite el funcionario asignado
	 * @param caso
	 * @param nombreFuncionarioAsignado Nombre del prestador del servicio por el cual se filtro la consulta
	 */
	public static CasoAsignadoDTO generarDTOParaPrestadoresDeServicio(Caso caso, String nombreFuncionarioAsignado){
		CasoAsignadoDTO dto = new CasoAsignadoDTO();
		dto.setCodigoCaso(caso.getIdCaso());
		dto.setFechaRadicacion(caso.getFechaRadicacion());		
		dto.setEstadoCaso(caso.getEstadoCaso());
		dto.setFechaUltimoEstado(caso.getFechaEstado());
		dto.setSede(caso.getSede().getNombre());
		if(caso.getServicioMateria().getServicio().getTipo().equals(UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA)
		|| caso.getServicioMateria().getServicio().getTipo().equals(UtilDominios.TIPO_SERVICIO_INTERNACIONAL)){
			dto.setFuncionarioAsignado(null);
			dto.setNombreServicio(caso.getServicioMateria().getServicio().getNombre());
		}else{
			dto.setFuncionarioAsignado(nombreFuncionarioAsignado);
			dto.setNombreServicio(null);
		}
		dto.setResultado(caso.getResultado());
		
		return dto;
	}
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

}
