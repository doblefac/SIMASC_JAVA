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

import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.transversal.dto.reportes.ReporteAudienciasProgramacionRefrigeriosDTO;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;

@ManagedBean(name = "controladorReporteAudienciasProgramacionRefrigeriosView")
@ViewScoped
public class ControladorReporteAudienciasProgramacionRefrigerios extends ControladorReporte implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	ReporteFacade reporteFacade;

	private List<ReporteAudienciasProgramacionRefrigeriosDTO> reporteAudienciasProgramacionRefrigeriosDTOs;
	private int totalArbitros;
	private boolean limpiar = true;

	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			reporteAudienciasProgramacionRefrigeriosDTOs = new ArrayList<>();
			totalArbitros = 0;
			visible = true;
			filtrosBusquedaReporte = new HashMap<>();
		}
		limpiar = true;
	}

	@PostConstruct
	public void postConstruct() {
		filtrosBusquedaReporte = new HashMap<>();
		visible = true;
		nombreReporte = UtilSimasc.generarNombreReporte("Reporte_audiencias_refrigerios");
		reporteAudienciasProgramacionRefrigeriosDTOs = new ArrayList<>();
		fechaInicialSeleccionada = null;
		fechaFinSeleccionada = null;
		fechaIni = null;
		fechaFin = null;
	}

	@PreDestroy
	public void preDestroy() {
		filtrosBusquedaReporte = new HashMap<>();
		visible = true;
		reporteAudienciasProgramacionRefrigeriosDTOs = new ArrayList<>();
		fechaInicialSeleccionada = null;
		fechaFinSeleccionada = null;
		fechaIni = null;
		fechaFin = null;
	}

	public void buscar() {
		this.limpiar = false;
		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			validarCampos();

			if (fechaInicialSeleccionada != null) {
				filtrosBusquedaReporte.put("fechaIni", fechaIni);
			}
			if (fechaFinSeleccionada != null) {
				filtrosBusquedaReporte.put("fechaFin", fechaFin);
			}
			limpiarFiltros();
			reporteAudienciasProgramacionRefrigeriosDTOs = reporteFacade
					.getReporteAudienciasProgramacionRefrigerios(filtrosBusquedaReporte);
			if (reporteAudienciasProgramacionRefrigeriosDTOs.size() != 0) {
				visible = false;
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString())));

			}
			setTotalArbitros(reporteAudienciasProgramacionRefrigeriosDTOs.size());

		} catch (Exception e) {
			limpiarFiltros();
			visible = true;
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, e.getMessage()));
		}

	}

	public void limpiarFiltros() {
		fechaInicialSeleccionada = null;
		fechaFinSeleccionada = null;
		reporteAudienciasProgramacionRefrigeriosDTOs = new ArrayList<>();

	}

	public void validarCampos() throws Exception {
		if ((fechaInicialSeleccionada == null || fechaFinSeleccionada == null)) {
			throw new Exception(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO032.toString()));
		}
		if (fechaInicialSeleccionada != null && fechaFinSeleccionada != null) {
			if (fechaInicialSeleccionada.after(fechaFinSeleccionada)) {
				throw new Exception(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO037.toString()));
			}
		}
	}

	public List<ReporteAudienciasProgramacionRefrigeriosDTO> getReporteAudienciasProgramacionRefrigeriosDTOs() {
		return reporteAudienciasProgramacionRefrigeriosDTOs;
	}

	public void setReporteAudienciasProgramacionRefrigeriosDTOs(
			List<ReporteAudienciasProgramacionRefrigeriosDTO> reporteAudienciasProgramacionRefrigeriosDTOs) {
		this.reporteAudienciasProgramacionRefrigeriosDTOs = reporteAudienciasProgramacionRefrigeriosDTOs;
	}

	public int getTotalArbitros() {
		return totalArbitros;
	}

	public void setTotalArbitros(int totalArbitros) {
		this.totalArbitros = totalArbitros;
	}
}
