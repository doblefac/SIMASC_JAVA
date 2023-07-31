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
//import com.ccb.simasc.transversal.dto.FuncionarioExternoDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasosAceptadosRechazadosSecretarioDTO;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;
/**
 * Clase controladora para generar el reporte de 
 * casos aceptados y rechazados por secretarios
 * @author aalvarez
 *
 */
@ManagedBean(name = "controladorReporteCasosAceptadosRechazadosSecretarioView")
@ViewScoped
public class ControladorReporteCasosAceptadosRechazadosSecretario extends ControladorReporte implements Serializable{

	@EJB
	FuncionarioExternoFacade funcionarioExternoFacade;
	
	@EJB
	ReporteFacade reporteFacade;

	private static final long serialVersionUID = 1L;

	private List<PersonaDTO> funcionarioExternoDTOs;// secretario
	private List<ReporteCasosAceptadosRechazadosSecretarioDTO> reporteCasosAceptadosRechazadosDTOs;
	private String secretarioSeleccionado;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			reporteCasosAceptadosRechazadosDTOs = new ArrayList<>();
		}
	}
	
	@PostConstruct
	public void postConstruct() {
		limpiarCampos();
		nombreReporte = UtilSimasc.generarNombreReporte("Reporte_casos_aceptados_rechazados_secretario");
		cargarListas();
	}

	@PreDestroy
	public void preDestroy() {
		limpiarCampos();
	}
	
	public void limpiarCampos() {
		funcionarioExternoDTOs = new ArrayList<PersonaDTO>();
		reporteCasosAceptadosRechazadosDTOs = new ArrayList<>();
		secretarioSeleccionado=null;
	}

	/**
	 * Metodo encargado de cargar las listas de filtrado
	 */
	public void cargarListas() {
		funcionarioExternoDTOs = funcionarioExternoFacade.getBusquedaFuncionarios(UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
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
			if (secretarioSeleccionado != null  && !secretarioSeleccionado.equals("TODOS")) {
				filtrosBusquedaReporte.put("idPersona", secretarioSeleccionado);
			}
			if (fechaInicialSeleccionada != null) {
				filtrosBusquedaReporte.put("fechaInicial", fechaInicialSeleccionada);
			}
			if (fechaFinSeleccionada != null) {
				filtrosBusquedaReporte.put("fechaFinal", fechaFinSeleccionada);
			}
			limpiarFiltros();
			reporteCasosAceptadosRechazadosDTOs = reporteFacade.getReporteCasosAceptadosRechazadosSecretario(filtrosBusquedaReporte);
			if (reporteCasosAceptadosRechazadosDTOs.size() != 0) {
				visible = false;
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString())));

			}
			
			setTotal(reporteCasosAceptadosRechazadosDTOs.size());

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
		secretarioSeleccionado = null;
		fechaInicialSeleccionada = null;
		fechaFinSeleccionada = null;
		reporteCasosAceptadosRechazadosDTOs = new ArrayList<>();

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

	public List<PersonaDTO> getFuncionarioExternoDTOs() {
		return funcionarioExternoDTOs;
	}
	public void setFuncionarioExternoDTOs(List<PersonaDTO> funcionarioExternoDTOs) {
		this.funcionarioExternoDTOs = funcionarioExternoDTOs;
	}
	public List<ReporteCasosAceptadosRechazadosSecretarioDTO> getReporteCasosAceptadosRechazadosDTOs() {
		return reporteCasosAceptadosRechazadosDTOs;
	}
	public void setReporteCasosAceptadosRechazadosDTOs(
			List<ReporteCasosAceptadosRechazadosSecretarioDTO> reporteCasosAceptadosRechazadosDTOs) {
		this.reporteCasosAceptadosRechazadosDTOs = reporteCasosAceptadosRechazadosDTOs;
	}
	public String getSecretarioSeleccionado() {
		return secretarioSeleccionado;
	}
	public void setSecretarioSeleccionado(String secretarioSeleccionado) {
		this.secretarioSeleccionado = secretarioSeleccionado;
	}
	
}
