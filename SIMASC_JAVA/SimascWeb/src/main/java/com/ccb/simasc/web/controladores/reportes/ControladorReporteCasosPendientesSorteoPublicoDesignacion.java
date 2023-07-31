package com.ccb.simasc.web.controladores.reportes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasosPendientesSorteoPublicoDesignacionDTO;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;

/**
 * Clase controladora para generar el reporte de casos pendientes
 * de sorteo publico de designacion
 * @author aalvarez
 *
 */
@ManagedBean(name = "controladorReporteCasosPendientesSorteoPublicoDesignacionView")
@ViewScoped
public class ControladorReporteCasosPendientesSorteoPublicoDesignacion implements Serializable{

	@EJB
	ReporteFacade reporteFacade;

	private static final long serialVersionUID = 1L;
	
	private List<ReporteCasosPendientesSorteoPublicoDesignacionDTO> reporteCasosPendientesSorteoPublicoDesignacionDTOs;
	private String nombreReporte;
	private boolean visible;
	private int totalArbitros=0;
	private boolean limpiar = true;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			reporteCasosPendientesSorteoPublicoDesignacionDTOs = new ArrayList<ReporteCasosPendientesSorteoPublicoDesignacionDTO>();
			totalArbitros = 0;
		}
		limpiar = true;
		this.buscar();
	}

	@PostConstruct
	public void postConstruct() {
		reporteCasosPendientesSorteoPublicoDesignacionDTOs = new ArrayList<ReporteCasosPendientesSorteoPublicoDesignacionDTO>();
		visible = false;
		nombreReporte = UtilSimasc.generarNombreReporte("Reporte_casos_pendientes_sorteo_publico_designacion");
	}

	@PreDestroy
	public void preDestroy() {
		reporteCasosPendientesSorteoPublicoDesignacionDTOs = new ArrayList<ReporteCasosPendientesSorteoPublicoDesignacionDTO>();
		visible = false;
	}

	/**
	 * Metodo encargado de consultar la informacion del reporte
	 */
	public void buscar() {
		this.limpiar = false;
		FacesContext facesContext;
		try {
			reporteCasosPendientesSorteoPublicoDesignacionDTOs = reporteFacade.getReporteCasosPendientesSorteoPublicoDesignacion();
			if (reporteCasosPendientesSorteoPublicoDesignacionDTOs != null
					 && !reporteCasosPendientesSorteoPublicoDesignacionDTOs.isEmpty()) {
				visible = false;
				setTotalArbitros(reporteCasosPendientesSorteoPublicoDesignacionDTOs.size());
			} else {
				facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString())));
			}	
		} catch (Exception e) {
			visible = true;
			facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString())));
		}
	}


	/**
	 * Getters y Setters de las variables de la clase
	 */
	public List<ReporteCasosPendientesSorteoPublicoDesignacionDTO> getReporteCasosPendientesSorteoPublicoDesignacionDTOs() {
		return reporteCasosPendientesSorteoPublicoDesignacionDTOs;
	}


	public void setReporteCasosPendientesSorteoPublicoDesignacionDTOs(
			List<ReporteCasosPendientesSorteoPublicoDesignacionDTO> reporteCasosPendientesSorteoPublicoDesignacionDTOs) {
		this.reporteCasosPendientesSorteoPublicoDesignacionDTOs = reporteCasosPendientesSorteoPublicoDesignacionDTOs;
	}


	public String getNombreReporte() {
		return nombreReporte;
	}


	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}


	public boolean isVisible() {
		return visible;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getTotalArbitros() {
		return totalArbitros;
	}

	public void setTotalArbitros(int totalArbitros) {
		this.totalArbitros = totalArbitros;
	}
}
