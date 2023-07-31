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
import com.ccb.simasc.negocio.transversal.FachadaDominios;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteTranscripcionesDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

@ManagedBean(name = "controladorReporteTranscripciones")
@ViewScoped
public class ControladorReporteTranscripciones implements Serializable {

	@EJB
	FuncionarioExternoFacade funcionarioExternofacade;

	@EJB
	FachadaDominios dominioFacade;

	@EJB
	ReporteFacade reporteFacade;

	private static final long serialVersionUID = 1L;	

	private String codigoCaso;
	private String nombreCaso;
	private Date fechaIni;
	private Date fechaFin;
	private List<PersonaDTO> auxiliaresDTOs;

	private String auxiliarSeleccionado;

	private Caso caso;

	private List<ReporteTranscripcionesDTO> listaReporte;

	private boolean obligatorioFechas;
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
			listaReporte = new ArrayList<ReporteTranscripcionesDTO>();
			total = 0;
			exportarBloqueado = true;
		}
		limpiar = true;
	}

	@PostConstruct
	public void postConstruct(){

		auxiliaresDTOs = new ArrayList<PersonaDTO>();
		listaReporte = new ArrayList<ReporteTranscripcionesDTO>();
		obligatorioFechas =true;
		exportarBloqueado = true;
		// ***************************************************************************************************
		// ***************** El siguiente dato debe ser un parametro *****************************************
		// ***************************************************************************************************
		filasPaginador = "20";
		limpiarFiltros();
		cargarListas();

	}

	@PreDestroy
	public void preDestroy(){

		auxiliaresDTOs = new ArrayList<PersonaDTO>();
		listaReporte = new ArrayList<ReporteTranscripcionesDTO>();
		obligatorioFechas =true;
		exportarBloqueado = true;

	}

	public void cargarListas(){
		auxiliaresDTOs = funcionarioExternofacade.getBusquedaFuncionarios(UtilDominios.ROL_PERSONA_AUXILIAR_ADM);
	}

	public void buscar(){
		this.limpiar = false;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String,Object> filtros= new HashMap<>();


		if (codigoCaso !=null){
			filtros.put("codigoCaso", codigoCaso);
		}
		if (nombreCaso !=null){
			filtros.put("nombreCaso", nombreCaso);
		}
		if (fechaIni !=null){
			filtros.put("fechaIni", fechaIni);
		}
		if (fechaFin !=null){
			filtros.put("fechaFin", fechaFin);
		}
		filtros.put("auxiliar", auxiliarSeleccionado);
		
		if (validarCampos()){
			listaReporte = reporteFacade.getReporteTranscripciones(filtros);
			setTotal(listaReporte.size());
			exportarBloqueado = listaReporte.isEmpty();
			if(listaReporte.isEmpty()){
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO, MensajesSimasc.getInstancia().getMensajePorKey(
						MensajesEnum.INFO031.toString())));
			}
		}else{
			exportarBloqueado = true;
		}
		limpiarFiltros();
		obligatorioFechas = true;
	}

	public void exportarExcel(){
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String date = sdf.format(new Date());
		nombreReporte = "Reporte_transcripciones" + date;
	}

	public boolean cambiarCodigoCaso(){
		obligatorioFechas = codigoCaso.equals("");
		return false;
	}


	public boolean validarCampos(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if((fechaIni==null || fechaFin==null) && obligatorioFechas){
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA,  MensajesSimasc.getInstancia().getMensajePorKey(
					MensajesEnum.INFO043.toString())));
			listaReporte.clear();
			return false;
		}
		if(fechaFin != null && fechaIni != null ){ 
			if(fechaIni.after(fechaFin)){
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, MensajesSimasc.getInstancia().getMensajePorKey(
						MensajesEnum.INFO037.toString())));
				listaReporte.clear();				
				return false;
			}
		}
		return true;
	}

	public void limpiarFiltros(){

		codigoCaso=null;
		nombreCaso=auxiliarSeleccionado=null;
		fechaIni=fechaFin=null;

	}


	public String getCodigoCaso() {
		return codigoCaso;
	}


	public void setCodigoCaso(String codigoCaso) {
		this.codigoCaso = codigoCaso;
	}


	public String getNombreCaso() {
		return nombreCaso;
	}


	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
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


	public List<PersonaDTO> getAuxiliaresDTOs() {
		return auxiliaresDTOs;
	}


	public void setAuxiliaresDTOs(List<PersonaDTO> auxiliaresDTOs) {
		this.auxiliaresDTOs = auxiliaresDTOs;
	}


	public String getAuxiliarSeleccionado() {
		return auxiliarSeleccionado;
	}


	public void setAuxiliarSeleccionado(String auxiliarSeleccionado) {
		this.auxiliarSeleccionado = auxiliarSeleccionado;
	}


	public Caso getCaso() {
		return caso;
	}


	public void setCaso(Caso caso) {
		this.caso = caso;
	}


	public List<ReporteTranscripcionesDTO> getListaReporte() {
		return listaReporte;
	}


	public void setListaReporte(List<ReporteTranscripcionesDTO> listaReporte) {
		this.listaReporte = listaReporte;
	}


	public boolean isObligatorioFechas() {
		return obligatorioFechas;
	}


	public void setObligatorioFechas(boolean obligatorioFechas) {
		this.obligatorioFechas = obligatorioFechas;
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


	

}
