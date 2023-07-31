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
import com.ccb.simasc.transversal.dto.reportes.ReporteCasoParteDTO;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;
import com.ccb.simasc.web.controladores.ControladorDominios;

/**
 * Clase controladora para generar el reporte casos por parte
 * 
 * @author dpachon
 *
 */

@ManagedBean(name = "controladorReporteCasoParteView")
@ViewScoped
public class ControladorReporteCasoParte implements Serializable {

	@ManagedProperty(value="#{controladorDominios}")
	private ControladorDominios controladorDominios;
	
	@EJB
	ReporteFacade reporteFacade;
	
	private static final long serialVersionUID = 1L;

	private Date fechaInicial;
	private Date fechaFinal;
	private String nombreReporte;
	private String primerNombre;
	private String primerApellido;
	private List<Dominio> dominiosTipoRol;
	private String seleccionRol;
	private Integer resultadoPagina;
	private boolean botonExcelDisabled;
	private List<ReporteCasoParteDTO> reporteCasoParteDTOs;
	private boolean limpiar = true;
	private int total = 0;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			reporteCasoParteDTOs = new ArrayList<>();
			total = 0;
			botonExcelDisabled = true;
			reporteCasoParteDTOs.clear();
			limpiarFormulario();
		}
		limpiar = true;
	}

	public ControladorReporteCasoParte() {
	}

	@PostConstruct
	public void postConstruct() {
		reporteCasoParteDTOs = new ArrayList<>();
		// 20 resultados por pagina valor por defecto
		resultadoPagina = new Integer(20);
		botonExcelDisabled = true;
		dominiosTipoRol = controladorDominios.getDominios(UtilDominios.DOMINIO_TIPO_PARTE_CONTRAPARTE);
	}

	@PreDestroy
	public void preDestroy() {
		reporteCasoParteDTOs = new ArrayList<>();
		botonExcelDisabled = true;
	}
	
	/**
	 * Metodo encargado de generar el reporte caso por parte con validacion de
	 * campos
	 **/
	public void generarReporte() {
		this.limpiar = false;
		botonExcelDisabled = true;
		if(this.validarCampos()){
			if (UtilSimasc.validarFechas(fechaInicial, fechaFinal)) {
				reporteCasoParteDTOs = new ArrayList<>();				
				seleccionRol = seleccionRol.equals("APD") ? "APO" : seleccionRol.equals("APM") ? "APD" : seleccionRol;				
				reporteCasoParteDTOs = reporteFacade.getReporteCasoParte(fechaInicial, fechaFinal, seleccionRol, primerNombre, primerApellido);
				this.validarReporteGenerado(reporteCasoParteDTOs);
				setTotal(reporteCasoParteDTOs.size());
			} else {
				total =0;
				reporteCasoParteDTOs = new ArrayList<>();
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA,
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO037.toString())));
				this.limpiarFormulario();
			}
		}
	}
	
	/**
	 *  Metodo encargado de validar obligatoriedad de los campos del reporte
	 * @return Boolean
	 */
	public Boolean validarCampos(){
		FacesContext facesContext;
		facesContext = FacesContext.getCurrentInstance();

//		if (primerNombre != null && !primerNombre.equals("")){
//			if(primerApellido == null || primerApellido.equals("")){
//				facesContext.addMessage(null,
//						new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
//								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.info039.toString())));
//				return false;	
//			}
//		}
//			
//			
//		if (primerApellido != null && !primerApellido.equals("")){
//			if(primerNombre == null || primerNombre.equals("")){
//				facesContext.addMessage(null,
//						new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
//								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.info040.toString())));
//				return false;
//			}
//		}		

				
		if (fechaInicial == null || fechaFinal == null) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO032.toString())));
			reporteCasoParteDTOs = new ArrayList<>();
			total =0;
			this.limpiarFormulario();
			return false;
		}
	
		
			return true;
	}

	/**
	 * Metodo que valida si existen elementos en el reporte, sino envia una
	 * notificacion al usuario
	 * 
	 * @param reporteCasoParteDTOs
	 */
	private void validarReporteGenerado(List<ReporteCasoParteDTO> reporteCasoParteDTOs) {
		if (reporteCasoParteDTOs != null && reporteCasoParteDTOs.size() >= 1) {
			nombreReporte = UtilSimasc.generarNombreReporte("Reporte_casos_por_parte");
			botonExcelDisabled = false;
			this.limpiarFormulario();
		} else {
			this.limpiarFormulario();
			total =0;
			FacesContext facesContext;
			facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString())));
		}
	}

	/**
	 * Metodo encargado de limpiar el formulario
	 */
	public void limpiarFormulario() {
		fechaInicial = null;
		fechaFinal = null;
		primerNombre = null;
		primerApellido = null;
		seleccionRol = null;
		
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

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	/**
	 * @return the dominiosTipoRol
	 */
	public List<Dominio> getDominiosTipoRol() {
		return dominiosTipoRol;
	}

	/**
	 * @param dominiosTipoRol the dominiosTipoRol to set
	 */
	public void setDominiosTipoRol(List<Dominio> dominiosTipoRol) {
		this.dominiosTipoRol = dominiosTipoRol;
	}

	/**
	 * @return the seleccionRol
	 */
	public String getSeleccionRol() {
		return seleccionRol;
	}

	/**
	 * @param seleccionRol the seleccionRol to set
	 */
	public void setSeleccionRol(String seleccionRol) {
		this.seleccionRol = seleccionRol;
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
	 * @return the reporteCasoParteDTOs
	 */
	public List<ReporteCasoParteDTO> getReporteCasoParteDTOs() {
		return reporteCasoParteDTOs;
	}

	/**
	 * @param reporteCasoParteDTOs the reporteCasoParteDTOs to set
	 */
	public void setReporteCasoParteDTOs(List<ReporteCasoParteDTO> reporteCasoParteDTOs) {
		this.reporteCasoParteDTOs = reporteCasoParteDTOs;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
