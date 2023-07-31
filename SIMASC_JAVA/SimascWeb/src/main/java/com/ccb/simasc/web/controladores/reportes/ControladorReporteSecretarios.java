package com.ccb.simasc.web.controladores.reportes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ccb.simasc.transversal.dto.reportes.ReporteSecretariosDTO;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;

/**
 * Clase controladora para generar el reporte de secretarios
 * @author aalvarez
 *
 */
@ManagedBean(name = "controladorReporteSecretariosView")
@ViewScoped
public class ControladorReporteSecretarios extends ControladorReporte implements Serializable{

	@EJB
	FuncionarioExternoFacade funcionarioExternoFacade;

	@EJB
	ReporteFacade reporteFacade;
	
	private static final long serialVersionUID = 1L;

	private List<ReporteSecretariosDTO> reporteSecretariosDTOs;
	private List<PersonaDTO> secretarioDTOs;// secretario
	private Map<String, String> filtrosBusquedaReporte;
	private String secretarioSeleccionado;
	private String estadoSeleccionado;	
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			reporteSecretariosDTOs= new ArrayList<>();
		}
	}

	@PostConstruct
	public void postConstruct() {
		limpiarCampos();
		nombreReporte = UtilSimasc.generarNombreReporte("Reporte_secretarios");
		cargarListas();
	}

	@PreDestroy
	public void preDestroy() {
		limpiarCampos();
	}
	
	public void limpiarCampos() {
		secretarioDTOs = new ArrayList<>();
		filtrosBusquedaReporte = new HashMap<>();		
		secretarioSeleccionado=null;
		estadoSeleccionado=null;
		reporteSecretariosDTOs= new ArrayList<>();
	}

	/**
	 * Metodo encargado de cargar las listas de filtrado
	 */
	public void cargarListas() {
		secretarioDTOs = funcionarioExternoFacade.getBusquedaFuncionarios(UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
	}

	/**
	 * Metodo encargado de consultar la informacion del reporte
	*/
	public void buscar() {
		this.limpiar = false;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		filtrosBusquedaReporte= new HashMap<>();
				
		try {

			if (secretarioSeleccionado != null && !secretarioSeleccionado.equals("TODOS")) {
				filtrosBusquedaReporte.put("idPersona", secretarioSeleccionado);
			}
			if (estadoSeleccionado != null) {
				filtrosBusquedaReporte.put("estado", estadoSeleccionado);
			}
			limpiarFiltros();
			reporteSecretariosDTOs = reporteFacade.getReporteSecretarios(filtrosBusquedaReporte);
			setTotal(reporteSecretariosDTOs.size());
			if (reporteSecretariosDTOs.size() != 0) {
				visible = false;
			} else {
				
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString())));

			}

		} catch (Exception e) {
			limpiarFiltros();
			visible = true;
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO, e.getMessage()));
		}

	}

	/**
	 * Metodo encargado de limpiar los filtros de busqueda
	 */
	public void limpiarFiltros() {
		secretarioSeleccionado = null;
		estadoSeleccionado = null;
		reporteSecretariosDTOs = new ArrayList<>();


	}
	
	public List<ReporteSecretariosDTO> getReporteSecretariosDTOs() {
		return reporteSecretariosDTOs;
	}

	public void setReporteSecretariosDTOs(List<ReporteSecretariosDTO> reporteSecretariosDTOs) {
		this.reporteSecretariosDTOs = reporteSecretariosDTOs;
	}

	public List<PersonaDTO> getSecretarioDTOs() {
		return secretarioDTOs;
	}

	public void setSecretarioDTOs(List<PersonaDTO> secretarioDTOs) {
		this.secretarioDTOs = secretarioDTOs;
	}
	public String getSecretarioSeleccionado() {
		return secretarioSeleccionado;
	}

	public void setSecretarioSeleccionado(String secretarioSeleccionado) {
		this.secretarioSeleccionado = secretarioSeleccionado;
	}

	public String getEstadoSeleccionado() {
		return estadoSeleccionado;
	}

	public void setEstadoSeleccionado(String estadoSeleccionado) {
		this.estadoSeleccionado = estadoSeleccionado;
	}
}
