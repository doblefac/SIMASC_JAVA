package com.ccb.simasc.web.controladores.reportes;

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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.event.SelectEvent;

import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasoSecretarioDTO;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;
import com.ccb.simasc.web.comun.BaseMBean;

@ManagedBean(name = "controladorReporteCasoSecretarioView")
@ViewScoped
public class ControladorReporteCasoSecretario extends BaseMBean {

	private static final long serialVersionUID = 1L;
	
	@EJB
	ReporteFacade reporteFacade;
	
	private Date fechaInicial;
	private Date fechaFinal;
	private Long codigoCaso;
	private String nombreCaso;
	private Integer resultadoPagina;
	private boolean botonExcelDisabled;
	private String nombreReporte;
	private boolean requeridoCalendario;
	//private String numeroIdentificacion;
	private FacesContext facesContext;

	private List<ReporteCasoSecretarioDTO> reporteCasoSecretarioDTOs;
	
	private int totalArbitros=0;
	private boolean limpiar = true;
	
	/**
	 * id del usuario de sesion activo
	 */
	private String usuarioSesion;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			reporteCasoSecretarioDTOs = new ArrayList<>();
			totalArbitros = 0;
			botonExcelDisabled = true;
		}
		limpiar = true;
	}

	public ControladorReporteCasoSecretario() {
	}

	@PostConstruct
	public void postConstruct() {
		usuarioSesion = this.getUserNameSession();
		// 20 resultados por pagina valor por defecto
		reporteCasoSecretarioDTOs = new ArrayList<>();
		resultadoPagina = new Integer(20);
		botonExcelDisabled = true;
		requeridoCalendario = true;	
		totalArbitros = 0;
	}

	@PreDestroy
	public void preDestroy() {
		reporteCasoSecretarioDTOs = new ArrayList<>();
		botonExcelDisabled = true;
		requeridoCalendario = true;	
	}

	/**
	 * Metodo encargado de generar el reporte caso asignado secretario con
	 * validaciones de campos
	 */
	public void generarReporte() {
		this.limpiar = false;
		facesContext = FacesContext.getCurrentInstance();
		botonExcelDisabled = true;
		
		if(fechaInicial != null && fechaFinal != null){
			if(UtilSimasc.validarFechas(fechaInicial, fechaFinal)){
				reporteCasoSecretarioDTOs = reporteFacade.getReporteCasoSecretario(fechaInicial, fechaFinal, codigoCaso,
						nombreCaso, usuarioSesion);
				this.validarReporteGenerado(reporteCasoSecretarioDTOs);				
			
			}else{
				totalArbitros =0;
				reporteCasoSecretarioDTOs = new ArrayList<>();
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,  UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, 
						 MensajesSimasc.getInstancia().getMensajePorKey(
									MensajesEnum.INFO037.toString())));
				limpiarFormulario();			
			
			}
			
		}else if (codigoCaso != null && codigoCaso > 0 ) {
			
			reporteCasoSecretarioDTOs = reporteFacade.getReporteCasoSecretario(fechaInicial, fechaFinal, codigoCaso,
							nombreCaso, usuarioSesion);
					this.validarReporteGenerado(reporteCasoSecretarioDTOs);
					
		} else {			
				this.limpiarFormulario();
				totalArbitros =0;
				reporteCasoSecretarioDTOs = new ArrayList<>();
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,  UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, 
							 MensajesSimasc.getInstancia().getMensajePorKey(
										MensajesEnum.INFO032.toString())));
		}
		setTotalArbitros(reporteCasoSecretarioDTOs.size());
		this.limpiarFormulario();
		
	}
	
	/**
	 *  Metodo encargado de validar obligatoriedad de los campos del reporte
	 * @return Boolean
	 */
	public Boolean validarCampos(){
		if (fechaInicial == null || fechaFinal == null) {
			this.limpiarFormulario();
			totalArbitros =0;
			reporteCasoSecretarioDTOs = new ArrayList<>();			
			facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,  UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, 
					 MensajesSimasc.getInstancia().getMensajePorKey(
								MensajesEnum.INFO032.toString())));

			return false;
		}
			return true;
	}

	/**
	 * Metodo que valida si existe codigo de caso para habilitar o no
	 * obligatoriedad del calendario
	 */
	public void validarCodigoCaso() {
		if (codigoCaso > 0)
			requeridoCalendario = false;
		else
			requeridoCalendario = true;
	}

	/**
	 * Metodo que valida si existen elementos en el reporte, sino envia una
	 * notificacion al usuario
	 * 
	 * @param reporteCasoSecretarioDTOs
	 */
	private void validarReporteGenerado(List<ReporteCasoSecretarioDTO> reporteCasoSecretarioDTOs) {
		if (reporteCasoSecretarioDTOs != null && reporteCasoSecretarioDTOs.size() >= 1) {
			nombreReporte = UtilSimasc.generarNombreReporte("Reporte_casos_asignados_a_secretario");
			botonExcelDisabled = false;
			this.limpiarFormulario();
		} else {
			this.limpiarFormulario();
			facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO, 
					 MensajesSimasc.getInstancia().getMensajePorKey(
								MensajesEnum.INFO031.toString())));

		}
	}

	/**
	 * Metodo encargado de limpiar el formulario y reestablecer valores
	 */
	private void limpiarFormulario() {
		fechaInicial = null;
		fechaFinal = null;
		codigoCaso = null;
		nombreCaso = "";
		requeridoCalendario = true;
//		numeroIdentificacion = null;
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
	 * @return the fechaInicial
	 */
	public Date getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * @param fechaInicial
	 *            the fechaInicial to set
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
	 * @param fechaFinal
	 *            the fechaFinal to set
	 */
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * @return the codigoCaso
	 */
	public Long getCodigoCaso() {
		return codigoCaso;
	}

	/**
	 * @param codigoCaso
	 *            the codigoCaso to set
	 */
	public void setCodigoCaso(Long codigoCaso) {
		this.codigoCaso = codigoCaso;
	}

	/**
	 * @return the nombreCaso
	 */
	public String getNombreCaso() {
		return nombreCaso;
	}

	/**
	 * @param nombreCaso
	 *            the nombreCaso to set
	 */
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}

	/**
	 * @return the resultadoPagina
	 */
	public Integer getResultadoPagina() {
		return resultadoPagina;
	}

	/**
	 * @param resultadoPagina
	 *            the resultadoPagina to set
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
	 * @param botonExcelDisabled
	 *            the botonExcelDisabled to set
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
	 * @param nombreReporte
	 *            the nombreReporte to set
	 */
	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	/**
	 * @return the reporteCasoSecretarioDTOs
	 */
	public List<ReporteCasoSecretarioDTO> getReporteCasoSecretarioDTOs() {
		return reporteCasoSecretarioDTOs;
	}

	/**
	 * @param reporteCasoSecretarioDTOs
	 *            the reporteCasoSecretarioDTOs to set
	 */
	public void setReporteCasoSecretarioDTOs(List<ReporteCasoSecretarioDTO> reporteCasoSecretarioDTOs) {
		this.reporteCasoSecretarioDTOs = reporteCasoSecretarioDTOs;
	}

	/**
	 * @return the requeridoCalendario
	 */
	public boolean isRequeridoCalendario() {
		return requeridoCalendario;
	}

	/**
	 * @param requeridoCalendario
	 *            the requeridoCalendario to set
	 */
	public void setRequeridoCalendario(boolean requeridoCalendario) {
		this.requeridoCalendario = requeridoCalendario;
	}

	/**
	 * @return the numeroIdentificacion
	 */
//	public String getNumeroIdentificacion() {
//		return numeroIdentificacion;
//	}
//
//	/**
//	 * @param numeroIdentificacion the numeroIdentificacion to set
//	 */
//	public void setNumeroIdentificacion(String numeroIdentificacion) {
//		this.numeroIdentificacion = numeroIdentificacion;
//	}

	/**
	 * @return the facesContext
	 */
	public FacesContext getFacesContext() {
		return facesContext;
	}

	/**
	 * @param facesContext the facesContext to set
	 */
	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public int getTotalArbitros() {
		return totalArbitros;
	}

	public void setTotalArbitros(int totalArbitros) {
		this.totalArbitros = totalArbitros;
	}

	/**
	 * @return the usuarioSesion
	 */
	public String getUsuarioSesion() {
		return usuarioSesion;
	}

	/**
	 * @param usuarioSesion the usuarioSesion to set
	 */
	public void setUsuarioSesion(String usuarioSesion) {
		this.usuarioSesion = usuarioSesion;
	}
}
