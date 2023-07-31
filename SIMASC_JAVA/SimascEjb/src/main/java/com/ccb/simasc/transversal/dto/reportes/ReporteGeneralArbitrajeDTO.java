package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

//import com.ccb.simasc.transversal.dto.FuncionarioExternoDTO;

@XmlRootElement
public class ReporteGeneralArbitrajeDTO {


	private int consecutivo;		
	private int codigoCaso;		
	private String nombreCaso;		
	private String tipoCaso;		
	private String fechaRadicacion;		
	private String tipoPacto;		
	private String tipoCuantia;
	private String valorPretensiones;
	private String materia;		
	private String tipoTramite;		
	private String etapaTramite;		
	private String convocante;		
	private String apoderadoConvocante;		
	private String convocado;
	private String apoderadoConvocado;	
	private String procurador;		
	private String nombreAbogado;		
	private String auxiliarAdministrativo;		
	//private List<FuncionarioExternoDTO> arbitros;
	private String arbitrosLista;	
	private String secretario;		
	private String sede;
	private String radicacionVirtual;	
	private String fechaPrimeraAudiencia;		
	private Integer numeroDiasAtencion;		
	private String fechaInstalacion;		
	private Integer numeroDiasInstalacion;		
	private String casoInactivo;		
	private String motivoCierre;
	private String consumo;
	private String amparoPobreza;
	private String concedeAmparo;
	private String medidasCautelares;
	private String fechaDecision;
	private String fechaDeCierre;
	private String metodoNombramiento;
	
	
	public int getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(int consecutivo) {
		this.consecutivo = consecutivo;
	}
	public int getCodigoCaso() {
		return codigoCaso;
	}
	public void setCodigoCaso(int codigoCaso) {
		this.codigoCaso = codigoCaso;
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
	public String getFechaRadicacion() {
		return fechaRadicacion;
	}
	public void setFechaRadicacion(String fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}
	public String getTipoPacto() {
		return tipoPacto;
	}
	public void setTipoPacto(String tipoPacto) {
		this.tipoPacto = tipoPacto;
	}
	
	public String getTipoCuantia() {
		return tipoCuantia;
	}
	
	public void setTipoCuantia(String tipoCuantia) {
		this.tipoCuantia = tipoCuantia;
	}
	public String getValorPretensiones() {
		return valorPretensiones;
	}
	public void setValorPretensiones(String valorPretensiones) {
		this.valorPretensiones = valorPretensiones;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public String getTipoTramite() {
		return tipoTramite;
	}
	public void setTipoTramite(String tipoTramite) {
		this.tipoTramite = tipoTramite;
	}
	public String getEtapaTramite() {
		return etapaTramite;
	}
	public void setEtapaTramite(String etapaTramite) {
		this.etapaTramite = etapaTramite;
	}
	public String getConvocante() {
		return convocante;
	}
	public void setConvocante(String convocante) {
		this.convocante = convocante;
	}
	public String getApoderadoConvocante() {
		return apoderadoConvocante;
	}
	public void setApoderadoConvocante(String apoderadoConvocante) {
		this.apoderadoConvocante = apoderadoConvocante;
	}
	public String getConvocado() {
		return convocado;
	}
	public void setConvocado(String convocado) {
		this.convocado = convocado;
	}
	public String getApoderadoConvocado() {
		return apoderadoConvocado;
	}
	public void setApoderadoConvocado(String apoderadoConvocado) {
		this.apoderadoConvocado = apoderadoConvocado;
	}
	public String getProcurador() {
		return procurador;
	}
	public void setProcurador(String procurador) {
		this.procurador = procurador;
	}
	public String getNombreAbogado() {
		return nombreAbogado;
	}
	public void setNombreAbogado(String nombreAbogado) {
		this.nombreAbogado = nombreAbogado;
	}
	public String getAuxiliarAdministrativo() {
		return auxiliarAdministrativo;
	}
	public void setAuxiliarAdministrativo(String auxiliarAdministrativo) {
		this.auxiliarAdministrativo = auxiliarAdministrativo;
	}
	/*
	public List<FuncionarioExternoDTO> getArbitros() {
		return arbitros;
	}
	public void setArbitros(List<FuncionarioExternoDTO> arbitros) {
		this.arbitros = arbitros;
	}*/
	public String getSecretario() {
		return secretario;
	}
	public void setSecretario(String secretario) {
		this.secretario = secretario;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	
	public String getRadicacionVirtual() {				
		if (UtilDominios.SEDE_VIRTUAL.equals(radicacionVirtual)) {
			radicacionVirtual = UtilConstantes.SI;
		} else {
			radicacionVirtual = UtilConstantes.NO;
		}
		return radicacionVirtual;
	}
	
	public void setRadicacionVirtual(String radicacionVirtual) {
		this.radicacionVirtual = radicacionVirtual;
	}
	public String getFechaPrimeraAudiencia() {
		return fechaPrimeraAudiencia;
	}
	public void setFechaPrimeraAudiencia(String fechaPrimeraAudiencia) {
		this.fechaPrimeraAudiencia = fechaPrimeraAudiencia;
	}
	public Integer getNumeroDiasAtencion() {
		return numeroDiasAtencion;
	}
	public void setNumeroDiasAtencion(Integer numeroDiasAtencion) {
		this.numeroDiasAtencion = numeroDiasAtencion;
	}
	public String getFechaInstalacion() {
		return fechaInstalacion;
	}
	public void setFechaInstalacion(String fechaInstalacion) {
		this.fechaInstalacion = fechaInstalacion;
	}
	public Integer getNumeroDiasInstalacion() {
		return numeroDiasInstalacion;
	}
	public void setNumeroDiasInstalacion(Integer numeroDiasInstalacion) {
		this.numeroDiasInstalacion = numeroDiasInstalacion;
	}
	public String getCasoInactivo() {
		return casoInactivo;
	}
	public void setCasoInactivo(String casoInactivo) {
		this.casoInactivo = casoInactivo;
	}
	public String getMotivoCierre() {
		return motivoCierre;
	}
	public void setMotivoCierre(String motivoCierre) {
		this.motivoCierre = motivoCierre;
	}
	public String getArbitrosLista() {
		return arbitrosLista;
	}
	public void setArbitrosLista(String arbitrosLista) {
		this.arbitrosLista = arbitrosLista;
	}
	/**
	 * @return the consumo
	 */
	public String getConsumo() {
		return consumo;
	}
	/**
	 * @param consumo the consumo to set
	 */
	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}
	/**
	 * @return the amparoPobreza
	 */
	public String getAmparoPobreza() {
		return amparoPobreza;
	}
	/**
	 * @param amparoPobreza the amparoPobreza to set
	 */
	public void setAmparoPobreza(String amparoPobreza) {
		this.amparoPobreza = amparoPobreza;
	}
	/**
	 * @return the concedeAmparo
	 */
	public String getConcedeAmparo() {
		return concedeAmparo;
	}
	/**
	 * @param concedeAmparo the concedeAmparo to set
	 */
	public void setConcedeAmparo(String concedeAmparo) {
		this.concedeAmparo = concedeAmparo;
	}
	/**
	 * @return the medidasCautelares
	 */
	public String getMedidasCautelares() {
		return medidasCautelares;
	}
	/**
	 * @param medidasCautelares the medidasCautelares to set
	 */
	public void setMedidasCautelares(String medidasCautelares) {
		this.medidasCautelares = medidasCautelares;
	}
	/**
	 * @return the fechaDecision
	 */
	public String getFechaDecision() {
		return fechaDecision;
	}
	/**
	 * @param fechaDecision the fechaDecision to set
	 */
	public void setFechaDecision(String fechaDecision) {
		this.fechaDecision = fechaDecision;
	}
	/**
	 * @return the fechaDeCierre
	 */
	public String getFechaDeCierre() {
		return fechaDeCierre;
	}
	/**
	 * @param fechaDeCierre the fechaDeCierre to set
	 */
	public void setFechaDeCierre(String fechaDeCierre) {
		this.fechaDeCierre = fechaDeCierre;
	}
	/**
	 * @return the metodoNombramiento
	 */
	public String getMetodoNombramiento() {
		return metodoNombramiento;
	}
	/**
	 * @param metodoNombramiento the metodoNombramiento to set
	 */
	public void setMetodoNombramiento(String metodoNombramiento) {
		this.metodoNombramiento = metodoNombramiento;
	}
	
	
	
	
	
	
}
