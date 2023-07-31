package com.ccb.simasc.web.controladores.reportes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.event.SelectEvent;

import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.negocio.arbitraje.ServicioFacade;
import com.ccb.simasc.transversal.dto.ServicioDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasoCuantiaDTO;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;
import com.ccb.simasc.web.controladores.ControladorDominios;

/**
 * Clase controladora para generar el reporte cuantia pretension
 * 
 * @author dpachon
 *
 */

@ManagedBean(name = "controladorReporteCasoCuantiaView")
@ViewScoped
public class ControladorReporteCasoCuantia implements Serializable{
	
	@ManagedProperty(value="#{controladorDominios}")
	private ControladorDominios controladorDominios;
	
	@EJB
	ReporteFacade reporteFacade;
	
	@EJB
	ServicioFacade servicioFacade;
	
	private static final long serialVersionUID = 1L;	
	
	private Date fechaInicial;
	private Date fechaFinal;
	private String seleccionCuantia;
	private Long seleccionCaso;
	private List<ServicioDTO> dominiosTipoCaso;
	private List<Dominio> dominiosTipoCuantia;
	
	private Integer resultadoPagina;
	private boolean botonExcelDisabled;
	private String nombreReporte;
	private int total;
	
	private List<ReporteCasoCuantiaDTO> reporteCasoCuantiaDTOs;
	
	public ControladorReporteCasoCuantia() {}
	private boolean limpiar = true;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			reporteCasoCuantiaDTOs = new ArrayList<ReporteCasoCuantiaDTO>();
			total = 0;
			botonExcelDisabled = true;
		}
		limpiar = true;
	}

	@PostConstruct
	public void postConstruct(){	
		reporteCasoCuantiaDTOs = new ArrayList<ReporteCasoCuantiaDTO>();
		seleccionCuantia = null;
		seleccionCaso = null;
		//20 resultados por pagina valor por defecto		
		resultadoPagina = new Integer(20);
		botonExcelDisabled = true;
		dominiosTipoCaso = servicioFacade.getBusquedaServicios();
		dominiosTipoCuantia = controladorDominios.getDominios(UtilDominios.DOMINIO_TIPO_CUANTIA);	
	}
	
	@PreDestroy
	public void preDestroy(){	
		reporteCasoCuantiaDTOs = new ArrayList<ReporteCasoCuantiaDTO>();
		seleccionCuantia = null;
		seleccionCaso = null;
		botonExcelDisabled = true;
	}
	
	
	/**
	 *Metodo encargado de generar el reporte cuantia pretension
	 *con validacion de campos
	 */
	public void generarReporte(){
		this.limpiar = false;
		botonExcelDisabled = true;	
		if(this.validarCampos()){
			if(UtilSimasc.validarFechas(fechaInicial,fechaFinal)){	
				reporteCasoCuantiaDTOs = reporteFacade.getReporteCasoCuantia(fechaInicial, fechaFinal, 
						seleccionCaso, seleccionCuantia);
				this.validarReporteGenerado(reporteCasoCuantiaDTOs);
			}else{
				reporteCasoCuantiaDTOs.clear();
				limpiarFormulario();
				FacesContext facesContext;
				facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,  UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA,  MensajesSimasc.getInstancia().getMensajePorKey(
								MensajesEnum.INFO037.toString())));
			}
		}
		total = reporteCasoCuantiaDTOs.size();
		
	}
	
	/**
	 *  Metodo encargado de validar obligatoriedad de los campos del reporte
	 * @return Boolean
	 */
	public Boolean validarCampos(){
		if (fechaInicial == null || fechaFinal == null) {
			FacesContext facesContext;
			facesContext = FacesContext.getCurrentInstance();
			this.limpiarFormulario();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,  UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA,  MensajesSimasc.getInstancia().getMensajePorKey(
					MensajesEnum.INFO032.toString())));
			return false;
		}
			return true;
	}
	
	/**
	 * Metodo que valida si existen elementos en el reporte, sino envia una
	 * notificacion al usuario
	 * 
	 * @param reporteCasoSecretarioDTOs
	 */
	private void validarReporteGenerado(List<ReporteCasoCuantiaDTO> reporteCasoCuantiaDTOs) {
		if (reporteCasoCuantiaDTOs != null && reporteCasoCuantiaDTOs.size() >= 1) {
			nombreReporte = UtilSimasc.generarNombreReporte("Reporte_cuantia_pretensiones");
			botonExcelDisabled = false;
			this.limpiarFormulario();
		} else {
			this.limpiarFormulario();
			FacesContext facesContext;
			facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,  MensajesSimasc.getInstancia().getMensajePorKey(
					MensajesEnum.INFO031.toString())));
		}
	}
	
	/**
	 *Metodo encargado de limpiar el formulario
	 */
	public void limpiarFormulario(){
		fechaInicial = null;
		fechaFinal = null;
		seleccionCuantia = null;
		seleccionCaso = null;
	}
	
	/**
	 * Metodo encargado de dar formato al archivo excel generado
	 * @param Object document
	 */
	public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
         
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = header.getCell(i);
            cell.setCellStyle(cellStyle);
        }
    }
	
	/**
	 * Metodo encargado de recuperar el valor del componente grafico para fecha
	 */
	public void onDateSelect(SelectEvent event) {
		fechaInicial = (Date) event.getObject();
	}

	/**
	 * Metodo encargado de recuperar el valor del componente grafico para fecha
	 */
	public void onDateSelectF(SelectEvent event) {
		fechaFinal = (Date) event.getObject();
	}

	/**
	 * @return the controladorDominios
	 */
	public ControladorDominios getControladorDominios() {
		return controladorDominios;
	}

	/**
	 * @param controladorDominios the controladorDominios to set
	 */
	public void setControladorDominios(ControladorDominios controladorDominios) {
		this.controladorDominios = controladorDominios;
	}

	/**
	 * @return the fechaInicial
	 */
	public Date getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * @param fechaInicial the fechaInicial to set
	 */
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	/**
	 * @return the fechaFinal
	 */
	public Date getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * @return the seleccionCuantia
	 */
	public String getSeleccionCuantia() {
		return seleccionCuantia;
	}

	/**
	 * @param seleccionCuantia the seleccionCuantia to set
	 */
	public void setSeleccionCuantia(String seleccionCuantia) {
		this.seleccionCuantia = seleccionCuantia;
	}

	/**
	 * @return the seleccionCaso
	 */
	public Long getSeleccionCaso() {
		return seleccionCaso;
	}

	/**
	 * @param seleccionCaso the seleccionCaso to set
	 */
	public void setSeleccionCaso(Long seleccionCaso) {
		this.seleccionCaso = seleccionCaso;
	}

	/**
	 * @return the dominiosTipoCaso
	 */
	public List<ServicioDTO> getDominiosTipoCaso() {
		return dominiosTipoCaso;
	}

	/**
	 * @param dominiosTipoCaso the dominiosTipoCaso to set
	 */
	public void setDominiosTipoCaso(List<ServicioDTO> dominiosTipoCaso) {
		this.dominiosTipoCaso = dominiosTipoCaso;
	}

	/**
	 * @return the dominiosTipoCuantia
	 */
	public List<Dominio> getDominiosTipoCuantia() {
		return dominiosTipoCuantia;
	}

	/**
	 * @param dominiosTipoCuantia the dominiosTipoCuantia to set
	 */
	public void setDominiosTipoCuantia(List<Dominio> dominiosTipoCuantia) {
		this.dominiosTipoCuantia = dominiosTipoCuantia;
	}
	
	/**
	 * @return the resultadoPagina
	 */
	public Integer getResultadoPagina() {
		return resultadoPagina;
	}

	/**
	 * @param resultadoPagina the resultadoPagina to set
	 */
	public void setResultadoPagina(Integer resultadoPagina) {
		this.resultadoPagina = resultadoPagina;
	}

	/**
	 * @return the botonExcelDisabled
	 */
	public boolean isBotonExcelDisabled() {
		return botonExcelDisabled;
	}

	/**
	 * @param botonExcelDisabled the botonExcelDisabled to set
	 */
	public void setBotonExcelDisabled(boolean botonExcelDisabled) {
		this.botonExcelDisabled = botonExcelDisabled;
	}

	/**
	 * @return the nombreReporte
	 */
	public String getNombreReporte() {
		return nombreReporte;
	}

	/**
	 * @param nombreReporte the nombreReporte to set
	 */
	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	/**
	 * @return the reporteCasoCuantiaDTOs
	 */
	public List<ReporteCasoCuantiaDTO> getReporteCasoCuantiaDTOs() {
		return reporteCasoCuantiaDTOs;
	}

	/**
	 * @param reporteCasoCuantiaDTOs the reporteCasoCuantiaDTOs to set
	 */
	public void setReporteCasoCuantiaDTOs(List<ReporteCasoCuantiaDTO> reporteCasoCuantiaDTOs) {
		this.reporteCasoCuantiaDTOs = reporteCasoCuantiaDTOs;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	

}
