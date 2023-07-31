/**
 * 
 */
package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;
import java.util.List;

import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.HonorariosFijados;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;

/**
 * Datos del ministerio para enviar datos
 * @author fguzman
 *
 */
public class DatosCasoMinisterioDTO {
	
	private Caso caso;
	private List<Persona> arbitrosMinisterio;
	private List<Persona> arbitrosExternos;
	private List<Persona> amigableComponedor;
	private RolPersonaCaso secretario;
	private List<Persona> partesCaso;
	private HonorariosFijados honorariosFijados;
	private Date fechaCierreCaso;
	private Persona conciliador;
	private List<Persona> conciliadores;
	private LiquidacionMinisterioDTO liquidacion;
	private List<RolPersonaCaso> convocantes;
	private List<RolPersonaCaso> convocados;
	private List <ParametrosGenerales> params;
	private boolean crearEventoMinisterio;
	



	public Caso getCaso() {
		return caso;
	}

	public void setCaso(Caso caso) {
		this.caso = caso;
	}

	public RolPersonaCaso getSecretario() {
		return secretario;
	}

	public void setSecretario(RolPersonaCaso secretario) {
		this.secretario = secretario;
	}

	public List<Persona> getPartesCaso() {
		return partesCaso;
	}

	public void setPartesCaso(List<Persona> partesCaso) {
		this.partesCaso = partesCaso;
	}

	public HonorariosFijados getHonorariosFijados() {
		return honorariosFijados;
	}

	public void setHonorariosFijados(HonorariosFijados honorariosFijados) {
		this.honorariosFijados = honorariosFijados;
	}

	public List<Persona> getArbitrosExternos() {
		return arbitrosExternos;
	}

	public void setArbitrosExternos(List<Persona> arbitrosExternos) {
		this.arbitrosExternos = arbitrosExternos;
	}

	public List<Persona> getArbitrosMinisterio() {
		return arbitrosMinisterio;
	}

	public void setArbitrosMinisterio(List<Persona> arbitrosMinisterio) {
		this.arbitrosMinisterio = arbitrosMinisterio;
	}

	public List<Persona> getAmigableComponedor() {
		return amigableComponedor;
	}

	public void setAmigableComponedor(List<Persona> amigableComponedor) {
		this.amigableComponedor = amigableComponedor;
	}

	public Date getFechaCierreCaso() {
		return fechaCierreCaso;
	}

	public void setFechaCierreCaso(Date fechaCierreCaso) {
		this.fechaCierreCaso = fechaCierreCaso;
	}

	/**
	 * @return the conciliador
	 */
	public Persona getConciliador() {
		return conciliador;
	}

	/**
	 * @param conciliador the conciliador to set
	 */
	public void setConciliador(Persona conciliador) {
		this.conciliador = conciliador;
	}

	/**
	 * @return the conciliadores
	 */
	public List<Persona> getConciliadores() {
		return conciliadores;
	}

	/**
	 * @param conciliadores the conciliadores to set
	 */
	public void setConciliadores(List<Persona> conciliadores) {
		this.conciliadores = conciliadores;
	}

	/**
	 * @return the liquidacion
	 */
	public LiquidacionMinisterioDTO getLiquidacion() {
		return liquidacion;
	}

	/**
	 * @param liquidacion the liquidacion to set
	 */
	public void setLiquidacion(LiquidacionMinisterioDTO liquidacion) {
		this.liquidacion = liquidacion;
	}

	/**
	 * @return the convocantes
	 */
	public List<RolPersonaCaso> getConvocantes() {
		return convocantes;
	}

	/**
	 * @param convocantes the convocantes to set
	 */
	public void setConvocantes(List<RolPersonaCaso> convocantes) {
		this.convocantes = convocantes;
	}

	/**
	 * @return the convocados
	 */
	public List<RolPersonaCaso> getConvocados() {
		return convocados;
	}

	/**
	 * @param convocados the convocados to set
	 */
	public void setConvocados(List<RolPersonaCaso> convocados) {
		this.convocados = convocados;
	}
	
	public List<ParametrosGenerales> getParametros() {
		return params;
	}

	public void setParametros(List<ParametrosGenerales> params) {
		this.params = params;
	}

	/**
	 * @return the crearEventoMinisterio
	 */
	public boolean isCrearEventoMinisterio() {
		return crearEventoMinisterio;
	}

	/**
	 * @param crearEventoMinisterio the crearEventoMinisterio to set
	 */
	public void setCrearEventoMinisterio(boolean crearEventoMinisterio) {
		this.crearEventoMinisterio = crearEventoMinisterio;
	}


}
