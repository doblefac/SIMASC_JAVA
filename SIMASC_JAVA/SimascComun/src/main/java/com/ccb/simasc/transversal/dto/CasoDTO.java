package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin

// Escriba en esta sección sus modificaciones

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.FacturacionCaso;
import com.ccb.simasc.transversal.entidades.Laudo;
import com.ccb.simasc.transversal.entidades.NombramientoPersona;
import com.ccb.simasc.transversal.entidades.PagoCaso;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports dto end

/**
 * DAO que contiene la informacion de la entidad CasoDTO que se transmite por
 * los servicios REST. Solo se transmiten los atributos simples, es decir, se
 * omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class CasoDTO extends IDTO<Caso> implements Serializable {

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	private static final long serialVersionUID = -7856720371940625695L;

	// protected region atributos end
	private Long idCaso;

	private String nombre;
	private Date fechaRadicacion;
	private String estadoCaso;
	private Date fechaEstado;
	private String etapa;
	private boolean amparoDePobreza;
	private boolean pendienteDePago;
	private String tipoCuantia;
	private Integer cantFuncionariosPrincipales;
	private String valorPretensiones;
	private String tipoRadicacion;
	private boolean preseleccion;
	private Long idServicio;
	private Long idMateria;
	private String tipoPacto;
	private String descripcionPacto;
	private String documentoPacto;
	private String tipoFallo;
	private String terminosDeProceso;
	private boolean competenciaCcb;
	private String tipoCompetenciaCcb;
	private String motivoDeNoCompetencia;
	private String hechos;
	private String pretensiones;
	private String tipoTramite;
	private String documentosNoRecibidos;
	private String duracion;
	private String resultado;
	private Long idCasoAnterior;
	private String moneda;
	private Integer diasParaProferirLaudo;
	private String inicioDeConflicto;
	private String parteQueSolicitaServicio;
	private String observaciones;
	private boolean pagado;
	private String motivoCierre;
	private Date fechaDeCobro;
	private BigDecimal valorDevueltoAlCliente;
	private String idUsuarioModificacion;
	private Date fechaUltimaModificacion;
	private String estadoRegistro;
	private Long idSede;
	private Long idConvenio;
	private Long idAreaAsuntoClasificacion;
	private String idLugarDelConflicto;
	private Long idSolicitudServicio;
	private String periodicidadTerminos;
	private String tipoPeriodicidadTerminos;
	private String idioma;
	private String leyAplicable;
	private String tipoPreseleccion;
	private String quienPreselecciona;
	private String tipoPeritaje;
	private boolean arbitrajeConsumo;
	private boolean concedeAmparo;
	private String tipoConflicto;
	private String enteroServicio;
	private String reglasProcedimiento;

	public CasoDTO() {
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
	}

	public Long getIdCaso() {
		return this.idCaso;
	}

	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaRadicacion() {
		return this.fechaRadicacion;
	}

	public void setFechaRadicacion(Date fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}

	public String getEstadoCaso() {
		return this.estadoCaso;
	}

	public void setEstadoCaso(String estadoCaso) {
		this.estadoCaso = estadoCaso;
	}

	public Date getFechaEstado() {
		return this.fechaEstado;
	}

	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	public String getEtapa() {
		return this.etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public boolean getAmparoDePobreza() {
		return this.amparoDePobreza;
	}

	public void setAmparoDePobreza(boolean amparoDePobreza) {
		this.amparoDePobreza = amparoDePobreza;
	}

	public boolean getPendienteDePago() {
		return this.pendienteDePago;
	}

	public void setPendienteDePago(boolean pendienteDePago) {
		this.pendienteDePago = pendienteDePago;
	}

	public String getTipoCuantia() {
		return this.tipoCuantia;
	}

	public void setTipoCuantia(String tipoCuantia) {
		this.tipoCuantia = tipoCuantia;
	}

	public Integer getCantFuncionariosPrincipales() {
		return this.cantFuncionariosPrincipales;
	}

	public void setCantFuncionariosPrincipales(Integer cantFuncionariosPrincipales) {
		this.cantFuncionariosPrincipales = cantFuncionariosPrincipales;
	}

	public String getValorPretensiones() {
		return this.valorPretensiones;
	}

	public void setValorPretensiones(String valorPretensiones) {
		this.valorPretensiones = valorPretensiones;
	}

	public String getTipoRadicacion() {
		return this.tipoRadicacion;
	}

	public void setTipoRadicacion(String tipoRadicacion) {
		this.tipoRadicacion = tipoRadicacion;
	}

	public boolean getPreseleccion() {
		return this.preseleccion;
	}

	public void setPreseleccion(boolean preseleccion) {
		this.preseleccion = preseleccion;
	}

	public Long getIdServicio() {
		return this.idServicio;
	}

	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}

	public Long getIdMateria() {
		return this.idMateria;
	}

	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}

	public String getTipoPacto() {
		return this.tipoPacto;
	}

	public void setTipoPacto(String tipoPacto) {
		this.tipoPacto = tipoPacto;
	}

	public String getDescripcionPacto() {
		return this.descripcionPacto;
	}

	public void setDescripcionPacto(String descripcionPacto) {
		this.descripcionPacto = descripcionPacto;
	}

	public String getDocumentoPacto() {
		return this.documentoPacto;
	}

	public void setDocumentoPacto(String documentoPacto) {
		this.documentoPacto = documentoPacto;
	}

	public String getTipoFallo() {
		return this.tipoFallo;
	}

	public void setTipoFallo(String tipoFallo) {
		this.tipoFallo = tipoFallo;
	}

	public String getTerminosDeProceso() {
		return this.terminosDeProceso;
	}

	public void setTerminosDeProceso(String terminosDeProceso) {
		this.terminosDeProceso = terminosDeProceso;
	}

	public boolean getCompetenciaCcb() {
		return this.competenciaCcb;
	}

	public void setCompetenciaCcb(boolean competenciaCcb) {
		this.competenciaCcb = competenciaCcb;
	}

	public String getTipoCompetenciaCcb() {
		return this.tipoCompetenciaCcb;
	}

	public void setTipoCompetenciaCcb(String tipoCompetenciaCcb) {
		this.tipoCompetenciaCcb = tipoCompetenciaCcb;
	}

	public String getMotivoDeNoCompetencia() {
		return this.motivoDeNoCompetencia;
	}

	public void setMotivoDeNoCompetencia(String motivoDeNoCompetencia) {
		this.motivoDeNoCompetencia = motivoDeNoCompetencia;
	}

	public String getHechos() {
		return this.hechos;
	}

	public void setHechos(String hechos) {
		this.hechos = hechos;
	}

	public String getPretensiones() {
		return this.pretensiones;
	}

	public void setPretensiones(String pretensiones) {
		this.pretensiones = pretensiones;
	}

	public String getTipoTramite() {
		return this.tipoTramite;
	}

	public void setTipoTramite(String tipoTramite) {
		this.tipoTramite = tipoTramite;
	}

	public String getDocumentosNoRecibidos() {
		return this.documentosNoRecibidos;
	}

	public void setDocumentosNoRecibidos(String documentosNoRecibidos) {
		this.documentosNoRecibidos = documentosNoRecibidos;
	}

	public String getDuracion() {
		return this.duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getResultado() {
		return this.resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Long getIdCasoAnterior() {
		return this.idCasoAnterior;
	}

	public void setIdCasoAnterior(Long idCasoAnterior) {
		this.idCasoAnterior = idCasoAnterior;
	}

	public String getMoneda() {
		return this.moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public Integer getDiasParaProferirLaudo() {
		return this.diasParaProferirLaudo;
	}

	public void setDiasParaProferirLaudo(Integer diasParaProferirLaudo) {
		this.diasParaProferirLaudo = diasParaProferirLaudo;
	}

	public String getInicioDeConflicto() {
		return this.inicioDeConflicto;
	}

	public void setInicioDeConflicto(String inicioDeConflicto) {
		this.inicioDeConflicto = inicioDeConflicto;
	}

	public String getParteQueSolicitaServicio() {
		return this.parteQueSolicitaServicio;
	}

	public void setParteQueSolicitaServicio(String parteQueSolicitaServicio) {
		this.parteQueSolicitaServicio = parteQueSolicitaServicio;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public boolean getPagado() {
		return this.pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public String getMotivoCierre() {
		return this.motivoCierre;
	}

	public void setMotivoCierre(String motivoCierre) {
		this.motivoCierre = motivoCierre;
	}

	public Date getFechaDeCobro() {
		return this.fechaDeCobro;
	}

	public void setFechaDeCobro(Date fechaDeCobro) {
		this.fechaDeCobro = fechaDeCobro;
	}

	public BigDecimal getValorDevueltoAlCliente() {
		return this.valorDevueltoAlCliente;
	}

	public void setValorDevueltoAlCliente(BigDecimal valorDevueltoAlCliente) {
		this.valorDevueltoAlCliente = valorDevueltoAlCliente;
	}

	public String getIdUsuarioModificacion() {
		return this.idUsuarioModificacion;
	}

	public void setIdUsuarioModificacion(String idUsuarioModificacion) {
		this.idUsuarioModificacion = idUsuarioModificacion;
	}

	public Date getFechaUltimaModificacion() {
		return this.fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public String getEstadoRegistro() {
		return this.estadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public Long getIdSede() {
		return this.idSede;
	}

	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}

	public Long getIdConvenio() {
		return this.idConvenio;
	}

	public void setIdConvenio(Long idConvenio) {
		this.idConvenio = idConvenio;
	}

	public Long getIdAreaAsuntoClasificacion() {
		return this.idAreaAsuntoClasificacion;
	}

	public void setIdAreaAsuntoClasificacion(Long idAreaAsuntoClasificacion) {
		this.idAreaAsuntoClasificacion = idAreaAsuntoClasificacion;
	}

	public String getIdLugarDelConflicto() {
		return this.idLugarDelConflicto;
	}

	public void setIdLugarDelConflicto(String idLugarDelConflicto) {
		this.idLugarDelConflicto = idLugarDelConflicto;
	}

	public Long getIdSolicitudServicio() {
		return this.idSolicitudServicio;
	}

	public void setIdSolicitudServicio(Long idSolicitudServicio) {
		this.idSolicitudServicio = idSolicitudServicio;
	}

	public String getPeriodicidadTerminos() {
		return this.periodicidadTerminos;
	}

	public void setPeriodicidadTerminos(String periodicidadTerminos) {
		this.periodicidadTerminos = periodicidadTerminos;
	}

	public String getTipoPeriodicidadTerminos() {
		return this.tipoPeriodicidadTerminos;
	}

	public void setTipoPeriodicidadTerminos(String tipoPeriodicidadTerminos) {
		this.tipoPeriodicidadTerminos = tipoPeriodicidadTerminos;
	}
	
	public boolean isArbitrajeConsumo() {
		return arbitrajeConsumo;
	}

	public void setArbitrajeConsumo(boolean arbitrajeConsumo) {
		this.arbitrajeConsumo = arbitrajeConsumo;
	}

	public String getReglasProcedimiento() {
		return reglasProcedimiento;
	}

	public void setReglasProcedimiento(String reglasProcedimiento) {
		this.reglasProcedimiento = reglasProcedimiento;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idCaso);
		hash = 37 * hash + Objects.hashCode(this.nombre);
		hash = 37 * hash + Objects.hashCode(this.fechaRadicacion);
		hash = 37 * hash + Objects.hashCode(this.estadoCaso);
		hash = 37 * hash + Objects.hashCode(this.fechaEstado);
		hash = 37 * hash + Objects.hashCode(this.etapa);
		hash = 37 * hash + (this.amparoDePobreza ? 0 : 1);
		hash = 37 * hash + (this.pendienteDePago ? 0 : 1);
		hash = 37 * hash + Objects.hashCode(this.tipoCuantia);
		hash = 37 * hash + Objects.hashCode(this.cantFuncionariosPrincipales);
		hash = 37 * hash + Objects.hashCode(this.valorPretensiones);
		hash = 37 * hash + Objects.hashCode(this.tipoRadicacion);
		hash = 37 * hash + (this.preseleccion ? 0 : 1);
		hash = 37 * hash + Objects.hashCode(this.idServicio);
		hash = 37 * hash + Objects.hashCode(this.idMateria);
		hash = 37 * hash + Objects.hashCode(this.tipoPacto);
		hash = 37 * hash + Objects.hashCode(this.descripcionPacto);
		hash = 37 * hash + Objects.hashCode(this.documentoPacto);
		hash = 37 * hash + Objects.hashCode(this.tipoFallo);
		hash = 37 * hash + Objects.hashCode(this.terminosDeProceso);
		hash = 37 * hash + (this.competenciaCcb ? 0 : 1);
		hash = 37 * hash + Objects.hashCode(this.tipoCompetenciaCcb);
		hash = 37 * hash + Objects.hashCode(this.motivoDeNoCompetencia);
		hash = 37 * hash + Objects.hashCode(this.hechos);
		hash = 37 * hash + Objects.hashCode(this.pretensiones);
		hash = 37 * hash + Objects.hashCode(this.tipoTramite);
		hash = 37 * hash + Objects.hashCode(this.documentosNoRecibidos);
		hash = 37 * hash + Objects.hashCode(this.duracion);
		hash = 37 * hash + Objects.hashCode(this.resultado);
		hash = 37 * hash + Objects.hashCode(this.idCasoAnterior);
		hash = 37 * hash + Objects.hashCode(this.moneda);
		hash = 37 * hash + Objects.hashCode(this.diasParaProferirLaudo);
		hash = 37 * hash + Objects.hashCode(this.inicioDeConflicto);
		hash = 37 * hash + Objects.hashCode(this.parteQueSolicitaServicio);
		hash = 37 * hash + Objects.hashCode(this.observaciones);
		hash = 37 * hash + (this.pagado ? 0 : 1);
		hash = 37 * hash + Objects.hashCode(this.motivoCierre);
		hash = 37 * hash + Objects.hashCode(this.fechaDeCobro);
		hash = 37 * hash + Objects.hashCode(this.valorDevueltoAlCliente);
		hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
		hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
		hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
		hash = 37 * hash + Objects.hashCode(this.idSede);
		hash = 37 * hash + Objects.hashCode(this.idConvenio);
		hash = 37 * hash + Objects.hashCode(this.idAreaAsuntoClasificacion);
		hash = 37 * hash + Objects.hashCode(this.idLugarDelConflicto);
		hash = 37 * hash + Objects.hashCode(this.idSolicitudServicio);
		hash = 37 * hash + Objects.hashCode(this.periodicidadTerminos);
		hash = 37 * hash + Objects.hashCode(this.tipoPeriodicidadTerminos);
		hash = 37 * hash + Objects.hashCode(this.tipoPeritaje);
		hash = 37 * hash + Objects.hashCode(this.quienPreselecciona);
		hash = 37 * hash + Objects.hashCode(this.tipoPreseleccion);
		hash = 37 * hash + Objects.hashCode(this.leyAplicable);
		hash = 37 * hash + Objects.hashCode(this.idioma);
		hash = 37 * hash + Objects.hashCode(this.arbitrajeConsumo);
		hash = 37 * hash + Objects.hashCode(this.concedeAmparo);
		hash = 37 * hash + Objects.hashCode(this.tipoConflicto);
	    hash = 37 * hash + Objects.hashCode(this.enteroServicio);
	    hash = 37 * hash + Objects.hashCode(this.reglasProcedimiento);
		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad CasoDTO que se pasa como
	 * parametro comprobando que comparten los mismos valores en cada uno de sus
	 * atributos. Solo se tienen en cuenta los atributos simples, es decir, se
	 * omiten aquellos que definen una relacion con otra tabla.
	 *
	 * @param obj Instancia de la categoría a comprobar iguales.
	 * @return Verdadero si esta instancia y la que se pasan como parametros son
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final CasoDTO other = (CasoDTO) obj;

		if (!Objects.equals(this.idCaso, other.idCaso)) {
			return false;
		}

		if (!Objects.equals(this.nombre, other.nombre)) {
			return false;
		}

		if (!Objects.equals(this.fechaRadicacion, other.fechaRadicacion)) {
			return false;
		}

		if (!Objects.equals(this.estadoCaso, other.estadoCaso)) {
			return false;
		}

		if (!Objects.equals(this.fechaEstado, other.fechaEstado)) {
			return false;
		}

		if (!Objects.equals(this.etapa, other.etapa)) {
			return false;
		}

		if (!Objects.equals(this.amparoDePobreza, other.amparoDePobreza)) {
			return false;
		}

		if (!Objects.equals(this.pendienteDePago, other.pendienteDePago)) {
			return false;
		}

		if (!Objects.equals(this.tipoCuantia, other.tipoCuantia)) {
			return false;
		}

		if (!Objects.equals(this.cantFuncionariosPrincipales, other.cantFuncionariosPrincipales)) {
			return false;
		}

		if (!Objects.equals(this.valorPretensiones, other.valorPretensiones)) {
			return false;
		}

		if (!Objects.equals(this.tipoRadicacion, other.tipoRadicacion)) {
			return false;
		}

		if (!Objects.equals(this.preseleccion, other.preseleccion)) {
			return false;
		}

		if (!Objects.equals(this.idServicio, other.idServicio)) {
			return false;
		}

		if (!Objects.equals(this.idMateria, other.idMateria)) {
			return false;
		}

		if (!Objects.equals(this.tipoPacto, other.tipoPacto)) {
			return false;
		}

		if (!Objects.equals(this.descripcionPacto, other.descripcionPacto)) {
			return false;
		}

		if (!Objects.equals(this.documentoPacto, other.documentoPacto)) {
			return false;
		}

		if (!Objects.equals(this.tipoFallo, other.tipoFallo)) {
			return false;
		}

		if (!Objects.equals(this.terminosDeProceso, other.terminosDeProceso)) {
			return false;
		}

		if (!Objects.equals(this.competenciaCcb, other.competenciaCcb)) {
			return false;
		}

		if (!Objects.equals(this.tipoCompetenciaCcb, other.tipoCompetenciaCcb)) {
			return false;
		}

		if (!Objects.equals(this.motivoDeNoCompetencia, other.motivoDeNoCompetencia)) {
			return false;
		}

		if (!Objects.equals(this.hechos, other.hechos)) {
			return false;
		}

		if (!Objects.equals(this.pretensiones, other.pretensiones)) {
			return false;
		}

		if (!Objects.equals(this.tipoTramite, other.tipoTramite)) {
			return false;
		}

		if (!Objects.equals(this.documentosNoRecibidos, other.documentosNoRecibidos)) {
			return false;
		}

		if (!Objects.equals(this.duracion, other.duracion)) {
			return false;
		}

		if (!Objects.equals(this.resultado, other.resultado)) {
			return false;
		}

		if (!Objects.equals(this.idCasoAnterior, other.idCasoAnterior)) {
			return false;
		}

		if (!Objects.equals(this.moneda, other.moneda)) {
			return false;
		}

		if (!Objects.equals(this.diasParaProferirLaudo, other.diasParaProferirLaudo)) {
			return false;
		}

		if (!Objects.equals(this.inicioDeConflicto, other.inicioDeConflicto)) {
			return false;
		}

		if (!Objects.equals(this.parteQueSolicitaServicio, other.parteQueSolicitaServicio)) {
			return false;
		}

		if (!Objects.equals(this.observaciones, other.observaciones)) {
			return false;
		}

		if (!Objects.equals(this.pagado, other.pagado)) {
			return false;
		}

		if (!Objects.equals(this.motivoCierre, other.motivoCierre)) {
			return false;
		}

		if (!Objects.equals(this.fechaDeCobro, other.fechaDeCobro)) {
			return false;
		}

		if (!Objects.equals(this.valorDevueltoAlCliente, other.valorDevueltoAlCliente)) {
			return false;
		}

		if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
			return false;
		}

		if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
			return false;
		}

		if (!Objects.equals(this.estadoRegistro, other.estadoRegistro)) {
			return false;
		}

		if (!Objects.equals(this.idSede, other.idSede)) {
			return false;
		}

		if (!Objects.equals(this.idConvenio, other.idConvenio)) {
			return false;
		}

		if (!Objects.equals(this.idAreaAsuntoClasificacion, other.idAreaAsuntoClasificacion)) {
			return false;
		}

		if (!Objects.equals(this.idLugarDelConflicto, other.idLugarDelConflicto)) {
			return false;
		}

		if (!Objects.equals(this.idSolicitudServicio, other.idSolicitudServicio)) {
			return false;
		}

		if (!Objects.equals(this.periodicidadTerminos, other.periodicidadTerminos)) {
			return false;
		}
		if (!Objects.equals(this.preseleccion, other.preseleccion)) {
			return false;
		}
		if (!Objects.equals(this.tipoPreseleccion, other.tipoPreseleccion)) {
			return false;
		}
		if (!Objects.equals(this.tipoPeritaje, other.tipoPeritaje)) {
			return false;
		}
		if (!Objects.equals(this.arbitrajeConsumo, other.arbitrajeConsumo)) {
			return false;
		}
		if (!Objects.equals(this.concedeAmparo, other.concedeAmparo)) {
			return false;
		}
		if (!Objects.equals(this.tipoConflicto, other.tipoConflicto)) {
	            return false;
	    }
	    if (!Objects.equals(this.enteroServicio, other.enteroServicio)) {
	            return false;
	    }
	    
	    if (!Objects.equals(this.reglasProcedimiento, other.reglasProcedimiento)) {
            return false;
    }
		
		return Objects.equals(this.tipoPeriodicidadTerminos, other.tipoPeriodicidadTerminos);

	}

	@Override
	public CasoDTO transformarSinDependencias(Caso obj) {
		CasoDTO casoDTO = new CasoDTO();

		casoDTO.setIdCaso(obj.getIdCaso());
		casoDTO.setNombre(obj.getNombre());
		casoDTO.setFechaRadicacion(obj.getFechaRadicacion());
		casoDTO.setEstadoCaso(obj.getEstadoCaso());
		casoDTO.setFechaEstado(obj.getFechaEstado());
		casoDTO.setEtapa(obj.getEtapa());
		casoDTO.setAmparoDePobreza(obj.getAmparoDePobreza());
		casoDTO.setPendienteDePago(obj.getPendienteDePago());
		casoDTO.setTipoCuantia(obj.getTipoCuantia());
		casoDTO.setCantFuncionariosPrincipales(obj.getCantFuncionariosPrincipales());
		casoDTO.setValorPretensiones(obj.getValorPretensiones());
		casoDTO.setTipoRadicacion(obj.getTipoRadicacion());
		casoDTO.setPreseleccion(obj.getPreseleccion());
		casoDTO.setIdServicio(obj.getIdServicio());
		casoDTO.setIdMateria(obj.getIdMateria());
		casoDTO.setTipoPacto(obj.getTipoPacto());
		casoDTO.setDescripcionPacto(obj.getDescripcionPacto());
		casoDTO.setDocumentoPacto(obj.getDocumentoPacto());
		casoDTO.setTipoFallo(obj.getTipoFallo());
		casoDTO.setTerminosDeProceso(obj.getTerminosDeProceso());
		casoDTO.setCompetenciaCcb(obj.getCompetenciaCcb());
		casoDTO.setTipoCompetenciaCcb(obj.getTipoCompetenciaCcb());
		casoDTO.setMotivoDeNoCompetencia(obj.getMotivoDeNoCompetencia());
		casoDTO.setHechos(obj.getHechos());
		casoDTO.setPretensiones(obj.getPretensiones());
		casoDTO.setTipoTramite(obj.getTipoTramite());
		casoDTO.setDocumentosNoRecibidos(obj.getDocumentosNoRecibidos());
		casoDTO.setDuracion(obj.getDuracion());
		casoDTO.setResultado(obj.getResultado());
		casoDTO.setIdCasoAnterior(obj.getIdCasoAnterior());
		casoDTO.setMoneda(obj.getMoneda());
		casoDTO.setDiasParaProferirLaudo(obj.getDiasParaProferirLaudo());
		casoDTO.setInicioDeConflicto(obj.getInicioDeConflicto());
		casoDTO.setParteQueSolicitaServicio(obj.getParteQueSolicitaServicio());
		casoDTO.setObservaciones(obj.getObservaciones());
		casoDTO.setPagado(obj.getPagado());
		casoDTO.setMotivoCierre(obj.getMotivoCierre());
		casoDTO.setFechaDeCobro(obj.getFechaDeCobro());
		casoDTO.setValorDevueltoAlCliente(obj.getValorDevueltoAlCliente());
		casoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		casoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		casoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		casoDTO.setIdSede(obj.getIdSede());
		casoDTO.setIdConvenio(obj.getIdConvenio());
		casoDTO.setIdAreaAsuntoClasificacion(obj.getIdAreaAsuntoClasificacion());
		casoDTO.setIdLugarDelConflicto(obj.getIdLugarDelConflicto());
		casoDTO.setIdSolicitudServicio(obj.getIdSolicitudServicio());
		casoDTO.setPeriodicidadTerminos(obj.getPeriodicidadTerminos());
		casoDTO.setTipoPeriodicidadTerminos(obj.getTipoPeriodicidadTerminos());
		casoDTO.setQuienPreselecciona(obj.getQuienPreselecciona());
		casoDTO.setTipoPreseleccion(obj.getTipoPreseleccion());
		casoDTO.setIdioma(obj.getIdioma());
		casoDTO.setLeyAplicable(obj.getLeyAplicable());
		casoDTO.setTipoPeritaje(obj.getTipoPeritaje());
		casoDTO.setArbitrajeConsumo(obj.isArbitrajeConsumo());
		casoDTO.setConcedeAmparo(obj.isConcedeAmparo());
		casoDTO.setTipoConflicto(obj.getTipoConflicto());
		casoDTO.setEnteroServicio(obj.getEnteroServicio());
		casoDTO.setReglasProcedimiento(obj.getReglasProcedimiento());
		return casoDTO;
	}

	@Override
	public CasoDTO transformarConDependencias(Caso obj) {
		CasoDTO casoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end

		return casoDTO;
	}

	@Override
	public Caso transformarEntidadSinDependencias(Caso obj) {
		Caso caso = new Caso();
		
		if(obj == null || obj.getIdCaso() == null) {
			return caso;
		}

		caso.setIdCaso(obj.getIdCaso());

		caso.setNombre(obj.getNombre());
		caso.setFechaRadicacion(obj.getFechaRadicacion());
		caso.setEstadoCaso(obj.getEstadoCaso());
		caso.setFechaEstado(obj.getFechaEstado());
		caso.setEtapa(obj.getEtapa());
		caso.setAmparoDePobreza(obj.getAmparoDePobreza());
		caso.setPendienteDePago(obj.getPendienteDePago());
		caso.setTipoCuantia(obj.getTipoCuantia());
		caso.setCantFuncionariosPrincipales(obj.getCantFuncionariosPrincipales());
		caso.setValorPretensiones(obj.getValorPretensiones());
		caso.setTipoRadicacion(obj.getTipoRadicacion());
		caso.setPreseleccion(obj.getPreseleccion());
		caso.setIdServicio(obj.getIdServicio());
		caso.setIdMateria(obj.getIdMateria());
		caso.setTipoPacto(obj.getTipoPacto());
		caso.setDescripcionPacto(obj.getDescripcionPacto());
		caso.setDocumentoPacto(obj.getDocumentoPacto());
		caso.setTipoFallo(obj.getTipoFallo());
		caso.setTerminosDeProceso(obj.getTerminosDeProceso());
		caso.setCompetenciaCcb(obj.getCompetenciaCcb());
		caso.setTipoCompetenciaCcb(obj.getTipoCompetenciaCcb());
		caso.setMotivoDeNoCompetencia(obj.getMotivoDeNoCompetencia());
		caso.setHechos(obj.getHechos());
		caso.setPretensiones(obj.getPretensiones());
		caso.setTipoTramite(obj.getTipoTramite());
		caso.setDocumentosNoRecibidos(obj.getDocumentosNoRecibidos());
		caso.setDuracion(obj.getDuracion());
		caso.setResultado(obj.getResultado());
		caso.setIdCasoAnterior(obj.getIdCasoAnterior());
		caso.setMoneda(obj.getMoneda());
		caso.setDiasParaProferirLaudo(obj.getDiasParaProferirLaudo());
		caso.setInicioDeConflicto(obj.getInicioDeConflicto());
		caso.setParteQueSolicitaServicio(obj.getParteQueSolicitaServicio());
		caso.setObservaciones(obj.getObservaciones());
		caso.setPagado(obj.getPagado());
		caso.setMotivoCierre(obj.getMotivoCierre());
		caso.setFechaDeCobro(obj.getFechaDeCobro());
		caso.setValorDevueltoAlCliente(obj.getValorDevueltoAlCliente());
		caso.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		caso.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		caso.setEstadoRegistro(obj.getEstadoRegistro());
		caso.setIdSede(obj.getIdSede());
		caso.setIdConvenio(obj.getIdConvenio());
		caso.setIdAreaAsuntoClasificacion(obj.getIdAreaAsuntoClasificacion());
		caso.setIdLugarDelConflicto(obj.getIdLugarDelConflicto());
		caso.setIdSolicitudServicio(obj.getIdSolicitudServicio());
		caso.setPeriodicidadTerminos(obj.getPeriodicidadTerminos());
		caso.setTipoPeriodicidadTerminos(obj.getTipoPeriodicidadTerminos());
		caso.setQuienPreselecciona(obj.getQuienPreselecciona());
		caso.setTipoPreseleccion(obj.getTipoPreseleccion());
		caso.setIdioma(obj.getIdioma());
		caso.setLeyAplicable(obj.getLeyAplicable());
		caso.setTipoPeritaje(obj.getTipoPeritaje());
		caso.setArbitrajeConsumo(obj.isArbitrajeConsumo());
		caso.setConcedeAmparo(obj.isConcedeAmparo());
		caso.setTipoConflicto(obj.getTipoConflicto());
		caso.setEnteroServicio(obj.getEnteroServicio());
		caso.setReglasProcedimiento(obj.getReglasProcedimiento());

		if (obj.getNombramientoPersonaList() != null && !obj.getNombramientoPersonaList().isEmpty()) {
			List<NombramientoPersona> nombramientoPersonaList = new ArrayList<>();

			for (NombramientoPersona nombramientoPersona : obj.getNombramientoPersonaList()) {
				if (nombramientoPersona.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
					nombramientoPersonaList.add(nombramientoPersona);
				}
			}

			caso.setNombramientoPersonaList(nombramientoPersonaList);
		}

		return caso;
	}

	@Override
	public Caso transformarEntidadConDependencias(Caso obj) {
		Caso caso = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		// Escriba en esta seccion sus modificaciones
		NombramientoPersonaDTO dto = new NombramientoPersonaDTO();
		caso.setNombramientoPersonaList((List<NombramientoPersona>) dto
				.transformarColeccionEntidadesSinDependencias(obj.getNombramientoPersonaList()));
		LaudoDTO laudodto = new LaudoDTO();
		caso.setLaudoList((List<Laudo>) laudodto.transformarColeccionEntidadesSinDependencias(obj.getLaudoList()));
		// protected region modificaciones transformarEntidadConDependencias end

		return caso;
	}
	
	public Caso transformarEntidadConDependenciasNombramiento(Caso obj) {
		Caso caso = transformarEntidadSinDependencias(obj);
		NombramientoPersonaDTO dto = new NombramientoPersonaDTO();
		caso.setNombramientoPersonaList((List<NombramientoPersona>) dto
				.transformarColeccionEntidadesSinDependencias(obj.getNombramientoPersonaList()));		
		return caso;
	}

	@Override
	public Collection transformarColeccionConDependencias(Collection<Caso> coleccion) {
		List<CasoDTO> casoDTOList = new ArrayList<>();
		for (Caso c : coleccion)
			casoDTOList.add(transformarConDependencias(c));
		return casoDTOList;
	}

	@Override
	public Collection transformarColeccionSinDependencias(Collection<Caso> coleccion) {
		List<CasoDTO> casoDTOList = new ArrayList<>();
		for (Caso c : coleccion)
			casoDTOList.add(transformarSinDependencias(c));
		return casoDTOList;
	}

	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Caso> coleccion) {
		List<Caso> casoList = new ArrayList<>();
		for (Caso c : coleccion)
			casoList.add(transformarEntidadConDependencias(c));
		return casoList;
	}

	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Caso> coleccion) {
		List<Caso> casoList = new ArrayList<>();
		for (Caso c : coleccion)
			casoList.add(transformarEntidadSinDependencias(c));
		return casoList;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	public Caso tranformarEntidadConDependenciasPago(Caso obj) {
		Caso caso = transformarEntidadSinDependencias(obj);
		if (obj.getPagoCasoList() != null && !obj.getPagoCasoList().isEmpty()) {
			caso.setPagoCasoList((List<PagoCaso>) new PagoCasoDTO()
					.transformarColeccionEntidadesConDependenciaDetalle(obj.getPagoCasoList()));
		}
		if (obj.getFacturacionCasoList() != null && !obj.getFacturacionCasoList().isEmpty()) {
			caso.setFacturacionCasoList((List<FacturacionCaso>) new FacturacionCasoDTO()
					.transformarColeccionEntidadesSinDependencias((obj.getFacturacionCasoList())));
		}
		return caso;
	}

	/**
	 * Obtengo los casos con convenios tipo convenio CON-C-076
	 * 
	 * @author LRUIZ
	 * @param obj
	 * @return
	 */
	public Caso transformarEntidadConDependenciaConvenio(Caso obj) {
		Caso caso = transformarEntidadSinDependencias(obj);
		if (obj.getConvenio() != null
				&& obj.getConvenio().getTipoConvenio().equalsIgnoreCase(UtilDominios.TIPO_CONVENIO_CONVENIO))
			caso.setConvenio(obj.getConvenio());
		return caso;
	}
	// protected region metodos adicionales end

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getLeyAplicable() {
		return leyAplicable;
	}

	public void setLeyAplicable(String leyAplicable) {
		this.leyAplicable = leyAplicable;
	}

	public String getTipoPreseleccion() {
		return tipoPreseleccion;
	}

	public void setTipoPreseleccion(String tipoPreseleccion) {
		this.tipoPreseleccion = tipoPreseleccion;
	}

	public String getQuienPreselecciona() {
		return quienPreselecciona;
	}

	public void setQuienPreselecciona(String quienPreselecciona) {
		this.quienPreselecciona = quienPreselecciona;
	}

	public String getTipoPeritaje() {
		return tipoPeritaje;
	}

	public void setTipoPeritaje(String tipoPeritaje) {
		this.tipoPeritaje = tipoPeritaje;
	}

	public boolean isConcedeAmparo() {
		return concedeAmparo;
	}

	public void setConcedeAmparo(boolean concedeAmparo) {
		this.concedeAmparo = concedeAmparo;
	}

	public String getTipoConflicto() {
		return tipoConflicto;
	}

	public void setTipoConflicto(String tipoConflicto) {
		this.tipoConflicto = tipoConflicto;
	}

	public String getEnteroServicio() {
		return enteroServicio;
	}

	public void setEnteroServicio(String enteroServicio) {
		this.enteroServicio = enteroServicio;
	}

}
