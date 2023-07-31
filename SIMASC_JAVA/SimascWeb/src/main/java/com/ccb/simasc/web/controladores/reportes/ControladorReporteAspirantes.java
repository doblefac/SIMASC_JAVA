package com.ccb.simasc.web.controladores.reportes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.transversal.dto.reportes.ReporteAspirantesDTO;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;


@ManagedBean(name = "controladorReporteAspirantes")
@ViewScoped
public class ControladorReporteAspirantes implements Serializable {
	
	@EJB
	ReporteFacade reporteFacade;
	
	private static final long serialVersionUID = 1L;	

	private List<ReporteAspirantesDTO> listaReporte;
	private boolean exportarBloqueado;
	private String nombreReporte;
	private String filasPaginador;
	private boolean limpiar = true;
	private int total;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			listaReporte = new ArrayList<ReporteAspirantesDTO>();
			total = 0;
			exportarBloqueado = true;
		}
		limpiar = true;
		this.buscar();
	}

	@PostConstruct
	public void postConstruct(){
		listaReporte = new ArrayList<ReporteAspirantesDTO>();
		exportarBloqueado = true;

		// ***************************************************************************************************
		// ***************** El siguiente dato debe ser un parametro *****************************************
		// ***************************************************************************************************
		filasPaginador = "20";
	}

	@PreDestroy
	public void preDestroy(){
		listaReporte = new ArrayList<ReporteAspirantesDTO>();
		exportarBloqueado = true;
	}
	
	public void buscar() {
		this.limpiar = false;
		listaReporte = reporteFacade.getDatosReporteAspirantes();
		exportarBloqueado = listaReporte.isEmpty();
		if (listaReporte == null || listaReporte.isEmpty()) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString())));
		}
		total = listaReporte.size();
	}

	public void exportarExcel(){
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String date = sdf.format(new Date());
		nombreReporte = "Reporte_aspirantes" + date;
	}
	public List<ReporteAspirantesDTO> getListaReporte() {
		return listaReporte;
	}
	public void setListaReporte(List<ReporteAspirantesDTO> listaReporte) {
		this.listaReporte = listaReporte;
	}
	public boolean isExportarBloqueado() {
		return exportarBloqueado;
	}
	public void setExportarBloqueado(boolean exportarBloqueado) {
		this.exportarBloqueado = exportarBloqueado;
	}
	public String getNombreReporte() {
		return nombreReporte;
	}
	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}
	public String getFilasPaginador() {
		return filasPaginador;
	}
	public void setFilasPaginador(String filasPaginador) {
		this.filasPaginador = filasPaginador;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	
}
