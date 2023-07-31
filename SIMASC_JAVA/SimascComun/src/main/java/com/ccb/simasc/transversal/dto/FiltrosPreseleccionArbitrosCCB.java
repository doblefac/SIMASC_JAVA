package com.ccb.simasc.transversal.dto;

import java.util.List;

public class FiltrosPreseleccionArbitrosCCB {

	private List<Integer> idMaterias;
	private boolean sorteableTodasLasMaterias;
	private boolean asignableTodasLasMaterias;
	private Long idCaso;
	private Long idServicio;
	private String cuantia;
	private String nombreLista;
	private int cantidadMateriasAsignadas;
	private int cantidadMateriasSorteable;
	
	public List<Integer> getIdMaterias() {
		return idMaterias;
	}
	public void setIdMaterias(List<Integer> idMaterias) {
		this.idMaterias = idMaterias;
	}
	public boolean isSorteableTodasLasMaterias() {
		return sorteableTodasLasMaterias;
	}
	public void setSorteableTodasLasMaterias(boolean sorteableTodasLasMaterias) {
		this.sorteableTodasLasMaterias = sorteableTodasLasMaterias;
	}
	public boolean isAsignableTodasLasMaterias() {
		return asignableTodasLasMaterias;
	}
	public void setAsignableTodasLasMaterias(boolean asignableTodasLasMaterias) {
		this.asignableTodasLasMaterias = asignableTodasLasMaterias;
	}
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public Long getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	public String getCuantia() {
		return cuantia;
	}
	public void setCuantia(String cuantia) {
		this.cuantia = cuantia;
	}
	public String getNombreLista() {
		return nombreLista;
	}
	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}
	public int getCantidadMateriasAsignadas() {
		return cantidadMateriasAsignadas;
	}
	public void setCantidadMateriasAsignadas(int cantidadMateriasAsignadas) {
		this.cantidadMateriasAsignadas = cantidadMateriasAsignadas;
	}
	public int getCantidadMateriasSorteable() {
		return cantidadMateriasSorteable;
	}
	public void setCantidadMateriasSorteable(int cantidadMateriasSorteable) {
		this.cantidadMateriasSorteable = cantidadMateriasSorteable;
	}
			
	
	
}
