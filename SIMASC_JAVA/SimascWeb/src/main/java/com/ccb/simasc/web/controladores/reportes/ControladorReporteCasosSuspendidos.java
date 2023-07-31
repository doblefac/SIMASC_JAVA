package com.ccb.simasc.web.controladores.reportes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ccb.simasc.negocio.arbitraje.FuncionarioCcbFacade;
import com.ccb.simasc.negocio.arbitraje.FuncionarioExternoFacade;
import com.ccb.simasc.negocio.arbitraje.MateriaFacade;
import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.negocio.arbitraje.SedeFacade;
import com.ccb.simasc.negocio.transversal.FachadaDominios;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasosSuspendidosDTO;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;

/**
 * Clase controladora para generar el reporte de casos suspendidos
 * @author aalvarez
 *
 */
@ManagedBean(name = "controladorReporteCasosSuspendidosView")
@ViewScoped
public class ControladorReporteCasosSuspendidos extends ControladorReporte implements Serializable{
	
	@EJB
	FuncionarioExternoFacade funcionarioExternoFacade;

	@EJB
	FuncionarioCcbFacade funcionarioCcbFacade;

	@EJB
	MateriaFacade materiaFacade;

	@EJB
	FachadaDominios dominioFacade;

	@EJB
	SedeFacade sedeFacade;

	@EJB
	ReporteFacade reporteFacade;

	private static final long serialVersionUID = 1L;

	private List<ReporteCasosSuspendidosDTO> reporteCasosSuspendidosDTOs;
	private int totalArbitros=0;
	private String codigoCaso;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			reporteCasosSuspendidosDTOs = new ArrayList<>();
			totalArbitros = 0;
			visible = true;
		}
		limpiar = true;
	}

	@PostConstruct
	public void postConstruct() {
		reporteCasosSuspendidosDTOs = new ArrayList<>();
		filtrosBusquedaReporte = new HashMap<>();
		visible = true;
		fechaInicialSeleccionada=null;
		fechaFinSeleccionada=null;	
		codigoCaso = null;
		nombreReporte = UtilSimasc.generarNombreReporte("Reporte_casos_suspendidos");

	}

	@PreDestroy
	public void preDestroy() {
		reporteCasosSuspendidosDTOs = new ArrayList<>();
		filtrosBusquedaReporte = new HashMap<>();
		visible = true;
		fechaInicialSeleccionada=null;
		fechaFinSeleccionada=null;	
		codigoCaso = null;
	}

	/**
	 * Metodo encargado de consultar la informacion del reporte
	 */
	public void buscar() {
		this.limpiar = false;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		filtrosBusquedaReporte= new HashMap<>();
		try {
			validarCampos();
			if (fechaInicialSeleccionada != null) {
				filtrosBusquedaReporte.put("fechaInicial", fechaIni);
			}
			if (fechaFinSeleccionada != null) {
				filtrosBusquedaReporte.put("fechaFinal", fechaFin);
			}
			if	(codigoCaso != null && !codigoCaso.equals("")) {
				filtrosBusquedaReporte.put("codigoCaso", codigoCaso);
			}
			limpiarFiltros();
			reporteCasosSuspendidosDTOs = reporteFacade.getReporteCasosSuspendidos(filtrosBusquedaReporte);
			if (reporteCasosSuspendidosDTOs.size() != 0) {
				visible = false;
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString())));

			}
			setTotalArbitros(reporteCasosSuspendidosDTOs.size());
		} catch (Exception e) {
			limpiarFiltros();
			visible = true;
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, e.getMessage()));
		}

	}

	/**
	 * Metodo encargado de limpiar los filtros de busqueda
	 */
	public void limpiarFiltros() {
		fechaInicialSeleccionada = null;
		fechaFinSeleccionada = null;
		reporteCasosSuspendidosDTOs = new ArrayList<>();
		codigoCaso = null;
	}

	/**
	 * Metodo encargado de validar los campos del formulario
	 */
	public void validarCampos() throws Exception {
		StringBuilder campo=new StringBuilder();
		if (fechaInicialSeleccionada == null) {
			campo.append("Fecha Inicial ");
		}
		if (fechaFinSeleccionada == null) {
			campo.append("Fecha Final ");
		}
		if (fechaInicialSeleccionada == null || fechaFinSeleccionada == null) {
			throw new Exception( MensajesSimasc.getInstancia().getMensajePorKey(
					MensajesEnum.INFO032.toString())+campo);
		}
		if (fechaInicialSeleccionada != null && fechaFinSeleccionada != null) {
			if (fechaInicialSeleccionada.after(fechaFinSeleccionada)) {
				throw new Exception( MensajesSimasc.getInstancia().getMensajePorKey(
						MensajesEnum.INFO037.toString()));
			}
		}

	}

	public List<ReporteCasosSuspendidosDTO> getReporteCasosSuspendidosDTOs() {
		return reporteCasosSuspendidosDTOs;
	}
	public void setReporteCasosSuspendidosDTOs(List<ReporteCasosSuspendidosDTO> reporteCasosSuspendidosDTOs) {
		this.reporteCasosSuspendidosDTOs = reporteCasosSuspendidosDTOs;
	}
	public int getTotalArbitros() {
		return totalArbitros;
	}
	public void setTotalArbitros(int totalArbitros) {
		this.totalArbitros = totalArbitros;
	}
	public String getCodigoCaso() {
		return codigoCaso;
	}
	public void setCodigoCaso(String codigoCaso) {
		this.codigoCaso = codigoCaso;
	}
}
