package com.ccb.simasc.transversal.dto.reportes;

import java.util.List;

import com.ccb.simasc.transversal.dto.ArbitrosDisponiblesSorteoDTO;

public class ReporteArbitrosDisponiblesParaSorteosDTO {


	private Long idSorteo;
	private String fechaSorteo;
	private Long idCaso;
	private String nombreCaso;
	private String tipoCaso;
	private String tipoCuantia;
	private String materia;
	private String preseleccion;
	private String tipoPreseleccion;
	private String liberacionLista;
	private String funcionarioSorteo;
	private String cuantia;
	private String consumo;
	private String liberadoSorteo;
	private String tipoSorteo;
	
	private List<ArbitrosDisponiblesSorteoDTO> arbitros;

	public Long getIdSorteo() {
		return idSorteo;
	}

	public void setIdSorteo(Long idSorteo) {
		this.idSorteo = idSorteo;
	}

	public String getFechaSorteo() {
		return fechaSorteo;
	}

	public void setFechaSorteo(String fechaSorteo) {
		this.fechaSorteo = fechaSorteo;
	}

	public Long getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}

	public String getNombreCaso() {
		return nombreCaso;
	}

	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}

	public String getTipoCaso() {
		return tipoCaso;
	}

	public void setTipoCaso(String tipoCaso) {
		this.tipoCaso = tipoCaso;
	}

	public String getTipoCuantia() {
		return tipoCuantia;
	}

	public void setTipoCuantia(String tipoCuantia) {
		this.tipoCuantia = tipoCuantia;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getPreseleccion() {
		return preseleccion;
	}

	public void setPreseleccion(String preseleccion) {
		this.preseleccion = preseleccion;
	}

	public String getTipoPreseleccion() {
		return tipoPreseleccion;
	}

	public void setTipoPreseleccion(String tipoPreseleccion) {
		this.tipoPreseleccion = tipoPreseleccion;
	}

	public String getLiberacionLista() {
		return liberacionLista;
	}

	public void setLiberacionLista(String liberacionLista) {
		this.liberacionLista = liberacionLista;
	}

	public String getFuncionarioSorteo() {
		return funcionarioSorteo;
	}

	public void setFuncionarioSorteo(String funcionarioSorteo) {
		this.funcionarioSorteo = funcionarioSorteo;
	}

	public List<ArbitrosDisponiblesSorteoDTO> getArbitros() {
		return arbitros;
	}

	public void setArbitros(List<ArbitrosDisponiblesSorteoDTO> arbitros) {
		this.arbitros = arbitros;
	}

	public String getCuantia() {
		return cuantia;
	}

	public void setCuantia(String cuantia) {
		this.cuantia = cuantia;
	}

	public String getConsumo() {
		return consumo;
	}

	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}

	public String getLiberadoSorteo() {
		return liberadoSorteo;
	}

	public void setLiberadoSorteo(String liberadoSorteo) {
		this.liberadoSorteo = liberadoSorteo;
	}

	public String getTipoSorteo() {
		return tipoSorteo;
	}

	public void setTipoSorteo(String tipoSorteo) {
		this.tipoSorteo = tipoSorteo;
	}
	
	
	
}
