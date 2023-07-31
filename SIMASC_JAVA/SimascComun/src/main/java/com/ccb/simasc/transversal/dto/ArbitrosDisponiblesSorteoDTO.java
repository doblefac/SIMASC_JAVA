package com.ccb.simasc.transversal.dto;

public class ArbitrosDisponiblesSorteoDTO {
	
	private String nombre;
	private String nombradoPreviamente;
	private Long numeroAleatorioDesignado;
	private String designadoSorteo;
	private String liberado;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombradoPreviamente() {
		return nombradoPreviamente;
	}
	public void setNombradoPreviamente(String nombradoPreviamente) {
		this.nombradoPreviamente = nombradoPreviamente;
	}
	public Long getNumeroAleatorioDesignado() {
		return numeroAleatorioDesignado;
	}
	public void setNumeroAleatorioDesignado(Long numeroAleatorioDesignado) {
		this.numeroAleatorioDesignado = numeroAleatorioDesignado;
	}
	public String getDesignadoSorteo() {
		return designadoSorteo;
	}
	public void setDesignadoSorteo(String designadoSorteo) {
		this.designadoSorteo = designadoSorteo;
	}
	public String getLiberado(){
		return liberado;
	}
	public void setLiberado(String liberado){
		this.liberado = liberado;
	}

}
