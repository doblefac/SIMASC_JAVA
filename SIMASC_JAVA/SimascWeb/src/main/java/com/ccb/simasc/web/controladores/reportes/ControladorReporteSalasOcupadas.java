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

import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.negocio.arbitraje.SedeFacade;
import com.ccb.simasc.transversal.dto.SedeDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteSalasOcupadasDTO;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

@ManagedBean(name = "controladorReporteSalasOcupadas")
@ViewScoped
public class ControladorReporteSalasOcupadas implements Serializable {

	@EJB
	ReporteFacade reporteFacade;

	@EJB
	SedeFacade sedeFacade;

	private static final long serialVersionUID = 1L;	

	private String codigoCaso;
	private String nombreCaso;
	private Date fechaIni;
	private List<SedeDTO> listaSedes;

	private String sedeSeleccionada;


	private List<ReporteSalasOcupadasDTO> listaReporte;

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
			listaReporte = new ArrayList<ReporteSalasOcupadasDTO>();
			total=0;
			exportarBloqueado = true;
		}
		limpiar = true;
	}

	@PostConstruct
	public void postConstruct(){
		
		listaSedes= new ArrayList<>();
		listaReporte = new ArrayList<ReporteSalasOcupadasDTO>();
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
		
		listaSedes= new ArrayList<>();
		listaReporte = new ArrayList<ReporteSalasOcupadasDTO>();
		obligatorioFechas =true;
		exportarBloqueado = true;
	}

	public void cargarListas(){
		// **********************Pendiente para cargar las sedesd de la base de datos
		listaSedes= sedeFacade.getBusquedaSedes();

	}

	public void buscar(){
		this.limpiar = false;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String,Object> filtros= new HashMap<>();
		
		filtros.put("fechaIni", fechaIni);
		filtros.put("nombreCaso", nombreCaso);
		filtros.put("codigoCaso", codigoCaso);
		filtros.put("sedeSeleccionada", sedeSeleccionada);

		if (validarCampos()){

			listaReporte = reporteFacade.getReporteSalasOcupadas(filtros);
			exportarBloqueado = listaReporte.isEmpty();
			setTotal(listaReporte.size());
			if(listaReporte.isEmpty()){
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO, MensajesSimasc.getInstancia().getMensajePorKey(
						MensajesEnum.INFO031.toString())));
			}

		}else{
			listaReporte = new ArrayList<ReporteSalasOcupadasDTO>();
			total=0;
			exportarBloqueado = true;
		}

		limpiarFiltros();
		obligatorioFechas = true;
	}

	public void exportarExcel(){
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String date = sdf.format(new Date());
		nombreReporte = "Reporte_salas_ocupadas" + date;
	}

	public boolean cambiarCodigoCaso(){
		obligatorioFechas = codigoCaso == null || codigoCaso.equals("");
		return false;
	}


	public boolean validarCampos(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if(fechaIni==null && (codigoCaso ==null || codigoCaso.equals(""))){
			listaReporte = new ArrayList<ReporteSalasOcupadasDTO>();
			total=0;
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, MensajesSimasc.getInstancia().getMensajePorKey(
					MensajesEnum.INFO044.toString())));
			
			return false;
		}

		return true;
	}

	public void limpiarFiltros(){

		codigoCaso=null;
		nombreCaso=null;
		fechaIni=null;
		sedeSeleccionada = null;

	}


	public ReporteFacade getReporteFacade() {
		return reporteFacade;
	}


	public void setReporteFacade(ReporteFacade reporteFacade) {
		this.reporteFacade = reporteFacade;
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




	public SedeFacade getSedeFacade() {
		return sedeFacade;
	}


	public void setSedeFacade(SedeFacade sedeFacade) {
		this.sedeFacade = sedeFacade;
	}


	public List<SedeDTO> getListaSedes() {
		return listaSedes;
	}


	public void setListaSedes(List<SedeDTO> listaSedes) {
		this.listaSedes = listaSedes;
	}


	public String getSedeSeleccionada() {
		return sedeSeleccionada;
	}


	public void setSedeSeleccionada(String sedeSeleccionada) {
		this.sedeSeleccionada = sedeSeleccionada;
	}


	public List<ReporteSalasOcupadasDTO> getListaReporte() {
		return listaReporte;
	}


	public void setListaReporte(List<ReporteSalasOcupadasDTO> listaReporte) {
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
