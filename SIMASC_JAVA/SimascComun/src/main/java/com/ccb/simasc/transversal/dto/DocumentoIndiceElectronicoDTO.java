package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DocumentoIndizado")
public class DocumentoIndiceElectronicoDTO implements Serializable {

	private Long idDocumento;
	private String ciudad;
	private String servicioCaso;
	private String serie;
	private Long codigoCaso;
	private String nombreCaso;
	private String parteDemandante;
	private String parteDemandado;
	private int orden;
	private String nombreDocumento;
	private String tipoDocumento;
	private String cuaderno;
	private String carpeta;
	private Date fechaDocumento;
	private int paginaInicial;
	private int paginaFinal;
	private int cantidadPaginas;
	private String peso;
	private String formato;

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getServicioCaso() {
		return servicioCaso;
	}

	public void setServicioCaso(String servicioCaso) {
		this.servicioCaso = servicioCaso;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public Long getCodigoCaso() {
		return codigoCaso;
	}

	public void setCodigoCaso(Long codigoCaso) {
		this.codigoCaso = codigoCaso;
	}

	public String getParteDemandante() {
		return parteDemandante;
	}

	public void setParteDemandante(String parteDemandante) {
		this.parteDemandante = parteDemandante;
	}

	public String getParteDemandado() {
		return parteDemandado;
	}

	public void setParteDemandado(String parteDemandado) {
		this.parteDemandado = parteDemandado;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public String getTipoDocumental() {
		return tipoDocumento;
	}

	public void setTipoDocumental(String tipoDocumental) {
		this.tipoDocumento = tipoDocumental;
	}

	public String getCuaderno() {
		return cuaderno;
	}

	public void setCuaderno(String cuaderno) {
		this.cuaderno = cuaderno;
	}

	public String getCarpeta() {
		return carpeta;
	}

	public void setCarpeta(String carpeta) {
		this.carpeta = carpeta;
	}

	public Date getFechaDocumento() {
		return fechaDocumento;
	}

	public void setFechaDocumento(Date fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}

	public int getPaginaInicial() {
		return paginaInicial;
	}

	public void setPaginaInicial(int paginaInicial) {
		this.paginaInicial = paginaInicial;
	}

	public int getPaginaFinal() {
		return paginaFinal;
	}

	public void setPaginaFinal(int paginaFinal) {
		this.paginaFinal = paginaFinal;
	}

	public int getCantidadPaginas() {
		return cantidadPaginas;
	}

	public void setCantidadPaginas(int cantidadPaginas) {
		this.cantidadPaginas = cantidadPaginas;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getNombreCaso() {
		return nombreCaso;
	}

	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}



	private static final long serialVersionUID = 1L;

}
