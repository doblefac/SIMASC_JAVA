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
import com.ccb.simasc.transversal.dto.reportes.ReporteRadicadoDocumentosDTO;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;


/**
 * Clase controladora para generar el reporte de radicacion de documentos
 * @author aacevedo
 *
 */
@ManagedBean(name = "controladorReporteRadicacionDocumentosView")
@ViewScoped
public class ControladorReporteRadicacionDocumentos extends ControladorReporte implements Serializable{

	@EJB
	ReporteFacade reporteFacade;

	private static final long serialVersionUID = 1L;
	
	private transient List<ReporteRadicadoDocumentosDTO> reporteRadicadoDocumentos;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {		
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			reporteRadicadoDocumentos = new ArrayList<>();	
		}		
	}

	@PostConstruct
	public void postConstruct() {	
		codigoCaso = null;
		nombreCaso = null;
		nombreDocumento = null;
		nombreReporte = UtilSimasc.generarNombreReporte("Reporte_radicado_documentos");
		reporteRadicadoDocumentos = new ArrayList<>();	
	}

	@PreDestroy
	public void preDestroy() {
		codigoCaso = null;
		nombreCaso = null;
		nombreDocumento = null;
		reporteRadicadoDocumentos = new ArrayList<>();	
	}


	/**
	 * Metodo encargado de consultar la informacion del reporte 
	 */
	public void botonBuscar() {
		this.limpiar = false;
		filtrosBusquedaReporte= new HashMap<>();	
		FacesContext facesContext = FacesContext.getCurrentInstance();			
		try {
			if(validarCampos()) {
				if (nombreDocumento != null && !nombreDocumento.equals("")) {
					filtrosBusquedaReporte.put("nombreDocumento", nombreDocumento);
				}
				if (nombreCaso != null && !nombreCaso.equals("")) {
					filtrosBusquedaReporte.put("nombreCaso", nombreCaso);
				}
				if (nombreDocumento != null && !nombreDocumento.equals("")) {
					filtrosBusquedaReporte.put("nombreDocumento", nombreDocumento);
				}
				if (codigoCaso != null && !codigoCaso.equals("")) {
					filtrosBusquedaReporte.put("codigoCaso", codigoCaso);
				}
				if (fechaInicialSeleccionada != null) {
					filtrosBusquedaReporte.put("fechaInicial", fechaInicialSeleccionada);
				}
				if (fechaFinSeleccionada != null) {
					filtrosBusquedaReporte.put("fechaFinal", fechaFinSeleccionada);
				}
				limpiarFiltros();
				reporteRadicadoDocumentos = reporteFacade.getReporteRadicadoDocumentos(filtrosBusquedaReporte);				
				if (reporteRadicadoDocumentos.size() != 0) {
					visible = false;
					nombreReporte = UtilSimasc.generarNombreReporte("Reporte_radicado_documentos");				
				} else {				
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA,
									MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString())));				
				}
				setTotal(reporteRadicadoDocumentos.size());
			}
		} catch (Exception e) {
			total = 0;
			limpiarFiltros();
			visible = true;
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, e.getMessage()));
		}


	}
	
	/**
	 * Metodo encargado de limpiar los filtros de busqueda
	 */
	public void limpiarFiltros() {
		nombreCaso = "";
		nombreDocumento = "";		
		fechaInicialSeleccionada = null;
		fechaFinSeleccionada = null;			
		reporteRadicadoDocumentos = new ArrayList<>();				
		codigoCaso = null;
	}

	
	/**
	 *  Metodo encargado de validar obligatoriedad de los campos del reporte
	 * @return Boolean
	 */
	public boolean validarCampos(){
		boolean bandera = true;		
		boolean banderaFecha = true;
		boolean banderaCaso = true;
		boolean banderaNombreCaso = true;
		boolean banderaNombreDocumento = true;
		
		if (fechaInicialSeleccionada == null || fechaFinSeleccionada == null ) {					
			banderaFecha =  false;
		}
		if(codigoCaso == null || codigoCaso.equals("")) {
			banderaCaso = false;			
		}
		if(nombreCaso == null || nombreCaso.equals("")) {
			banderaNombreCaso = false;
		}
		if(nombreDocumento == null || nombreDocumento.equals("")) {
			banderaNombreDocumento = false;
		}
		
		if(!banderaFecha && !banderaCaso && !banderaNombreCaso && !banderaNombreDocumento) {
			bandera = false;
			total = 0;
			reporteRadicadoDocumentos = new ArrayList<>();
		}		
		
		if(!bandera) {			
			limpiarFiltros();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,  UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, 
					 MensajesSimasc.getInstancia().getMensajePorKey(
								MensajesEnum.INFO032.toString())));
		}
				
		return bandera;
	}	
	
	public List<ReporteRadicadoDocumentosDTO> getReporteRadicadoDocumentos() {
		return reporteRadicadoDocumentos;
	}

	public void setReporteRadicadoDocumentos(List<ReporteRadicadoDocumentosDTO> reporteRadicadoDocumentos) {
		this.reporteRadicadoDocumentos = reporteRadicadoDocumentos;
	}

}
