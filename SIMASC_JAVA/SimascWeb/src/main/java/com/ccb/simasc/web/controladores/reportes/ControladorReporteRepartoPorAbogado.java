package com.ccb.simasc.web.controladores.reportes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.ccb.simasc.transversal.dto.reportes.ReporteRepartoPorAbogadoDTO;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

@ManagedBean(name = "controladorReporteReportePorAbogado")
@ViewScoped
public class ControladorReporteRepartoPorAbogado implements Serializable {

	@EJB
	FuncionarioExternoFacade funcionarioExternofacade;

	@EJB
	ReporteFacade reporteFacade;

	private static final long serialVersionUID = 1L;	

	private Date fechaIni;
	private Date fechaFin;
	private List<PersonaDTO> abogadosDTOs;

	private String abogadoSeleccionado;

	private List<ReporteRepartoPorAbogadoDTO> listaReporte;

	private boolean exportarBloqueado;
	private String nombreReporte;
	private String filasPaginador;
	private int diasHabiles;
	
	private int totalArbitros =0;
	private boolean limpiar = true;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			listaReporte = new ArrayList<ReporteRepartoPorAbogadoDTO>();
			totalArbitros = 0;
			exportarBloqueado = true;
		}
		limpiar = true;
	}


	@PostConstruct
	public void postConstruct(){

		abogadosDTOs = new ArrayList<PersonaDTO>();
		listaReporte = new ArrayList<ReporteRepartoPorAbogadoDTO>();
		exportarBloqueado = true;

		// ***************************************************************************************************
		// ***************** Los siguientes dato debe ser un parametro *****************************************
		// ***************************************************************************************************
		filasPaginador = "20";
		diasHabiles = 10;

		// ***************************************************************************************************
		limpiarFiltros();
		cargarListas();

	}

	@PreDestroy
	public void preDestroy(){

		abogadosDTOs = new ArrayList<PersonaDTO>();
		listaReporte = new ArrayList<ReporteRepartoPorAbogadoDTO>();
		exportarBloqueado = true;

	}

	public void cargarListas(){

		abogadosDTOs = funcionarioExternofacade.getBusquedaFuncionarios(UtilDominios.ROL_PERSONA_ABOGADO);		

	}

	public void buscar(){
		this.limpiar = false;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String,Object> filtros= new HashMap<>();
	
		filtros.put("fechaIni", fechaIni);
		filtros.put("fechaFin", fechaFin);
		filtros.put("abogado", abogadoSeleccionado);
		filtros.put("diasHabiles", diasHabiles);

		if (validarCampos()){
			listaReporte = reporteFacade.getReporteRepartoPorAbogado(filtros);
			exportarBloqueado = listaReporte.isEmpty();
			if(listaReporte.isEmpty()){
				facesContext.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
								 MensajesSimasc.getInstancia().getMensajePorKey(
											MensajesEnum.INFO035.toString())));
				limpiarFiltros();
			}

		}else{
			exportarBloqueado = true;
		}
		setTotalArbitros(listaReporte.size());
		limpiarFiltros();
	}


	public void exportarExcel(){
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String date = sdf.format(new Date());
		nombreReporte = "Reporte_reparto_abogado" + date;
	}


	public boolean validarCampos(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if(fechaIni==null || fechaFin==null){
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, MensajesSimasc.getInstancia().getMensajePorKey(
					MensajesEnum.INFO043.toString())));
			return false;
		}
		if(fechaFin != null && fechaIni != null ){ 
			if(fechaIni.after(fechaFin)){
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA,  MensajesSimasc.getInstancia().getMensajePorKey(
						MensajesEnum.INFO037.toString())));
				return false;
			}
		}
		return true;
	}

	public void limpiarFiltros(){

		abogadoSeleccionado=null;
		fechaIni=fechaFin=null;

	}


	public Date getFechaIni() {
		return fechaIni;
	}


	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public List<PersonaDTO> getAbogadosDTOs() {
		return abogadosDTOs;
	}


	public void setAbogadosDTOs(List<PersonaDTO> abogadosDTOs) {
		this.abogadosDTOs = abogadosDTOs;
	}


	public String getAbogadoSeleccionado() {
		return abogadoSeleccionado;
	}


	public void setAbogadoSeleccionado(String abogadoSeleccionado) {
		this.abogadoSeleccionado = abogadoSeleccionado;
	}


	public List<ReporteRepartoPorAbogadoDTO> getListaReporte() {
		return listaReporte;
	}


	public void setListaReporte(List<ReporteRepartoPorAbogadoDTO> listaReporte) {
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


	public int getDiasHabiles() {
		return diasHabiles;
	}


	public void setDiasHabiles(int diasHabiles) {
		this.diasHabiles = diasHabiles;
	}

	public int getTotalArbitros() {
		return totalArbitros;
	}

	public void setTotalArbitros(int totalArbitros) {
		this.totalArbitros = totalArbitros;
	}



}
