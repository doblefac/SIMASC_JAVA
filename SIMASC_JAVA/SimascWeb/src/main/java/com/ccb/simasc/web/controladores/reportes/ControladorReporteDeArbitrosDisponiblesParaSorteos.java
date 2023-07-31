package com.ccb.simasc.web.controladores.reportes;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.arbitraje.MateriaFacade;
import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.negocio.arbitraje.ServicioFacade;
import com.ccb.simasc.transversal.dto.ArbitrosDisponiblesSorteoDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteArbitrosDisponiblesParaSorteosDTO;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;



/**
 * Clase controladora para generar el reporte de arbitros para sorteos
 * @author aalvarez
 *
 */
@ManagedBean(name = "controladorReporteArbitrosDisponiblesParaSorteosView")
@ViewScoped
public class ControladorReporteDeArbitrosDisponiblesParaSorteos extends ControladorReporte implements Serializable {
	private static final Logger logger = LogManager.getLogger(ControladorReporteDeArbitrosDisponiblesParaSorteos.class.getName());
	
	@EJB
	MateriaFacade materiaFacade;

	@EJB
	ServicioFacade servicioFacade;

	@EJB
	ReporteFacade reporteFacade;

	private static final long serialVersionUID = 1L;

	private List<ReporteArbitrosDisponiblesParaSorteosDTO> reporteArbitroParaSorteosDTO;	
	private Long numeroCasoSeleccionado;
	private Date fechaSorteoSeleccionada;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			limpiarCampos();
			total = 0;
		}
		limpiar = true;
	}


	@PostConstruct
	public void postConstruct() {
		limpiarCampos();
		nombreReporte = UtilSimasc.generarNombreReporte("Reporte_arbitro_para_sorteos");
	}

	@PreDestroy
	public void preDestroy() {
		limpiarCampos();
	}
	
	public void limpiarCampos() {
		reporteArbitroParaSorteosDTO = new ArrayList<>();
		numeroCasoSeleccionado=null;
		fechaSorteoSeleccionada=null;
	}

	/**
	 * Metodo encargado de consultar la informacion del reporte
	 */
	public void buscar() {
		this.limpiar = false;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		filtrosBusquedaReporte= new HashMap<>();
		
		try {
			if (validarCampos()) {
				reporteArbitroParaSorteosDTO = reporteFacade.getReporteArbitrosDisponiblesSorteo(
						numeroCasoSeleccionado, fechaSorteoSeleccionada);
				if (reporteArbitroParaSorteosDTO.size() != 0) {
					visible = false;
				} else {
					visible = true;
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
									MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString())));
				}				
				setTotal(reporteArbitroParaSorteosDTO.size());
			}		
		} catch (Exception e) {
			limpiarFiltros();
			visible = true;
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, e.getMessage()));
		}

	}

	/**
	 * Metodo encargado de limpiar los filtros de busqueda
	 */
	public void limpiarFiltros() {
		numeroCasoSeleccionado = null;
		fechaSorteoSeleccionada = null;
		reporteArbitroParaSorteosDTO = new ArrayList<>();

	}

	/**
	 * Metodo encargado de validar los campos del formulario
	 */
	public boolean validarCampos() throws Exception {
		boolean valido = true;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		if (fechaSorteoSeleccionada == null && (numeroCasoSeleccionado == null || numeroCasoSeleccionado == 0)) {
			valido = false;
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN,UtilConstantes.ENCABEZADO_MENSAJE_ERROR, 
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO650.toString())));
		}
		return valido;
	}
	
	/**
	 * Gernera el excel con el resultado del reporte
	 * se usa Apache POI
	 */
	public void generarExcel() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    try {
	    	//libro de excel
	        HSSFWorkbook workbook = new HSSFWorkbook();        
	        //hoja de calculo
	        HSSFSheet sheet = workbook.createSheet("Arbitros disponibles en un Sorteo");        
	        
	        int fila = 0;
	        for (ReporteArbitrosDisponiblesParaSorteosDTO reporteDto : reporteArbitroParaSorteosDTO) {
	        	sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 12));
	        	Row encabezado = sheet.createRow(fila++);
	        	
	        	Cell celda = encabezado.createCell(0);
	            celda.setCellValue(reporteDto.getIdCaso() + " - " + reporteDto.getNombreCaso());            
	            
	            Row encvCasoFila = sheet.createRow(fila++);
	            encvCasoFila.createCell(0).setCellValue("Fecha Sorteo");
	            encvCasoFila.createCell(1).setCellValue("Código del caso");
	            encvCasoFila.createCell(2).setCellValue("Partes del caso");
	            encvCasoFila.createCell(3).setCellValue("Servicio");
				encvCasoFila.createCell(4).setCellValue("Consumo");
	            encvCasoFila.createCell(5).setCellValue("Cuantía");
	            encvCasoFila.createCell(6).setCellValue("Tipo de cuantía");
	            encvCasoFila.createCell(7).setCellValue("Materia");
	            encvCasoFila.createCell(8).setCellValue("Preselección");
	            encvCasoFila.createCell(9).setCellValue("Preselección por las partes o por la CCB");
	            encvCasoFila.createCell(10).setCellValue("Tipo de Sorteo");
	            encvCasoFila.createCell(11).setCellValue("Liberación de lista");
	            encvCasoFila.createCell(12).setCellValue("Funcionario que realizó el sorteo");
	            
	            Row dataCasoFila = sheet.createRow(fila++);
	            dataCasoFila.createCell(0).setCellValue(reporteDto.getFechaSorteo());                        
	            dataCasoFila.createCell(1).setCellValue(reporteDto.getIdCaso());
	            dataCasoFila.createCell(2).setCellValue(reporteDto.getNombreCaso());
	            dataCasoFila.createCell(3).setCellValue(reporteDto.getTipoCaso());
				dataCasoFila.createCell(4).setCellValue(reporteDto.getConsumo());				
	            dataCasoFila.createCell(5).setCellValue(reporteDto.getCuantia());
	            dataCasoFila.createCell(6).setCellValue(reporteDto.getTipoCuantia());
	            dataCasoFila.createCell(7).setCellValue(reporteDto.getMateria());
	            dataCasoFila.createCell(8).setCellValue(reporteDto.getPreseleccion());
	            dataCasoFila.createCell(9).setCellValue(reporteDto.getTipoPreseleccion());
	            dataCasoFila.createCell(10).setCellValue(reporteDto.getTipoSorteo());
	            dataCasoFila.createCell(11).setCellValue(reporteDto.getLiberacionLista());
	            dataCasoFila.createCell(12).setCellValue(reporteDto.getFuncionarioSorteo());
	            sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 3));
	            
	            Row encvArbFila = sheet.createRow(fila++);
	            
	            sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 3));
	            encvArbFila.createCell(0).setCellValue("Nombre");	            
	            encvArbFila.createCell(4).setCellValue("Nombrado previamente");
	            encvArbFila.createCell(5).setCellValue("Designado en el sorteo");
	            encvArbFila.createCell(6).setCellValue("Número aleatorio designado para el sorteo");
	            encvArbFila.createCell(7).setCellValue("Liberado");
	            
	            for (ArbitrosDisponiblesSorteoDTO arbitro : reporteDto.getArbitros()) {
	            	Row datavArbFila = sheet.createRow(fila++);
	            	sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 3));
	            	datavArbFila.createCell(0).setCellValue(arbitro.getNombre());
	            	datavArbFila.createCell(4).setCellValue(arbitro.getNombradoPreviamente());
	            	datavArbFila.createCell(5).setCellValue(arbitro.getDesignadoSorteo() == null ? "":arbitro.getDesignadoSorteo());
	            	datavArbFila.createCell(6).setCellValue(arbitro.getNumeroAleatorioDesignado() == null ? "":arbitro.getNumeroAleatorioDesignado().toString());
					datavArbFila.createCell(7).setCellValue(arbitro.getLiberado());
	            }	            
	            sheet.createRow(fila++);        	
	        }	        	        
		    ExternalContext externalContext = facesContext.getExternalContext();
		    externalContext.setResponseContentType("application/vnd.ms-excel");
		    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + this.nombreReporte + ".xls\"");
			workbook.write(externalContext.getResponseOutputStream());
			workbook.close();
			
		} catch (IOException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
					UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, e.getMessage()));
			logger.error("Error: ", e);
		}
	    facesContext.responseComplete();
	}


	public List<ReporteArbitrosDisponiblesParaSorteosDTO> getReporteArbitroParaSorteosDTO() {
		return reporteArbitroParaSorteosDTO;
	}
	public void setReporteArbitroParaSorteosDTO(
			List<ReporteArbitrosDisponiblesParaSorteosDTO> reporteArbitroParaSorteosDTO) {
		this.reporteArbitroParaSorteosDTO = reporteArbitroParaSorteosDTO;
	}
	public Long getNumeroCasoSeleccionado() {
		return numeroCasoSeleccionado;
	}
	public void setNumeroCasoSeleccionado(Long numeroCasoSeleccionado) {
		this.numeroCasoSeleccionado = numeroCasoSeleccionado;
	}
	public Date getFechaSorteoSeleccionada() {
		return fechaSorteoSeleccionada;
	}
	public void setFechaSorteoSeleccionada(Date fechaSorteoSeleccionada) {
		this.fechaSorteoSeleccionada = fechaSorteoSeleccionada;
	}
}
