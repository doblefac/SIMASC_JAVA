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

import com.ccb.simasc.negocio.arbitraje.FuncionarioExternoFacade;
import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteAuxiliarPromedioTranscripcionDTO;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;

/**
 * Clase controladora para generar el reporte del
 * promedio de minutos transcritos por auxiliar
 * @author aalvarez
 *
 */
@ManagedBean(name = "controladorReporteAuxiliarPromedioTranscripcionesView")
@ViewScoped
public class ControladorReporteAuxiliarPromedioTranscripciones extends ControladorReporte implements Serializable {

	@EJB
	FuncionarioExternoFacade funcionarioExternoFacade;
	
	@EJB
	ReporteFacade reporteFacade;

	private static final long serialVersionUID = 1L;

	private List<PersonaDTO> funcionarioExternoDTOs;
	private List<ReporteAuxiliarPromedioTranscripcionDTO> reporteAuxiliarPromedioTranscripcionesDTOs;	
	private String auxiliarSeleccionado;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			reporteAuxiliarPromedioTranscripcionesDTOs = new ArrayList<>();
			total = 0;
			visible = true;
		}
		limpiar = true;
	}
	
	@PostConstruct
	public void postConstruct() {
		funcionarioExternoDTOs = new ArrayList<PersonaDTO>();
		reporteAuxiliarPromedioTranscripcionesDTOs = new ArrayList<>();
		filtrosBusquedaReporte = new HashMap<>();
		visible = true;
		fechaInicialSeleccionada=null;
		fechaFinSeleccionada=null;
		auxiliarSeleccionado=null;
		nombreReporte = UtilSimasc.generarNombreReporte("Reporte_auxiliar_promedio_transcripciones");
		cargarListas();
	}

	@PreDestroy
	public void preDestroy() {
		funcionarioExternoDTOs = new ArrayList<PersonaDTO>();
		reporteAuxiliarPromedioTranscripcionesDTOs = new ArrayList<>();
		filtrosBusquedaReporte = new HashMap<>();
		visible = true;
		fechaInicialSeleccionada=null;
		fechaFinSeleccionada=null;
		auxiliarSeleccionado=null;
	}
	
	/**
	 * Metodo encargado de cargar las listas de filtrado
	 */
	public void cargarListas() {
		funcionarioExternoDTOs = funcionarioExternoFacade.getBusquedaFuncionarios(UtilDominios.ROL_PERSONA_AUXILIAR_ADM);
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
			if (auxiliarSeleccionado != null  && !auxiliarSeleccionado.equals("TODOS")) {
				filtrosBusquedaReporte.put("idPersona", auxiliarSeleccionado);
			}
			if (fechaInicialSeleccionada != null) {
				filtrosBusquedaReporte.put("fechaInicial", fechaIni);
			}
			if (fechaFinSeleccionada != null) {
				filtrosBusquedaReporte.put("fechaFinal", fechaFin);
			}
			limpiarFiltros();
			reporteAuxiliarPromedioTranscripcionesDTOs = reporteFacade.getReporteAuxiliarPromedioTranscripciones(filtrosBusquedaReporte);
			if (reporteAuxiliarPromedioTranscripcionesDTOs.size() != 0) {
				visible = false;
			} else {
				
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString())));

			}
			setTotal(reporteAuxiliarPromedioTranscripcionesDTOs.size());

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
		auxiliarSeleccionado = null;
		fechaInicialSeleccionada = null;
		fechaFinSeleccionada = null;
		reporteAuxiliarPromedioTranscripcionesDTOs = new ArrayList<>();

	}

	/**
	 * Metodo encargado de validar los campos del formulario
	 */
	public void validarCampos() throws Exception {
		StringBuilder campo=new StringBuilder();
		if (fechaInicialSeleccionada == null) {
			campo.append("Fecha Desde ");
		}
		if (fechaFinSeleccionada == null) {
			campo.append("Fecha Hasta ");
		}
		if (fechaInicialSeleccionada == null || fechaFinSeleccionada == null) {
			throw new Exception( MensajesSimasc.getInstancia().getMensajePorKey(
					MensajesEnum.INFO032.toString()) +campo);
		}
		if (fechaInicialSeleccionada != null && fechaFinSeleccionada != null) {
			if (fechaInicialSeleccionada.after(fechaFinSeleccionada)) {
				throw new Exception( MensajesSimasc.getInstancia().getMensajePorKey(
						MensajesEnum.INFO038.toString()));
			}
		}
	}

	public List<PersonaDTO> getFuncionarioExternoDTOs() {
		return funcionarioExternoDTOs;
	}
	public void setFuncionarioExternoDTOs(List<PersonaDTO> funcionarioExternoDTOs) {
		this.funcionarioExternoDTOs = funcionarioExternoDTOs;
	}
	public List<ReporteAuxiliarPromedioTranscripcionDTO> getReporteAuxiliarPromedioTranscripcionesDTOs() {
		return reporteAuxiliarPromedioTranscripcionesDTOs;
	}
	public void setReporteAuxiliarPromedioTranscripcionesDTOs(
			List<ReporteAuxiliarPromedioTranscripcionDTO> reporteAuxiliarPromedioTranscripcionesDTOs) {
		this.reporteAuxiliarPromedioTranscripcionesDTOs = reporteAuxiliarPromedioTranscripcionesDTOs;
	}
	public String getAuxiliarSeleccionado() {
		return auxiliarSeleccionado;
	}
	public void setAuxiliarSeleccionado(String auxiliarSeleccionado) {
		this.auxiliarSeleccionado = auxiliarSeleccionado;
	}
}
