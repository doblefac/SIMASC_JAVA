package com.ccb.simasc.web.controladores.reportes;

import java.io.IOException;
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
import com.ccb.simasc.transversal.dto.ServicioDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasosSorteadosDTO;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;



/**
 * Clase controladora para generar el reporte de casos sorteados
 * @author aalvarez
 *
 */
@ManagedBean(name = "controladorReporteCasosSorteadosView")
@ViewScoped
public class ControladorReporteCasosSorteados extends ControladorReporte implements Serializable {
	private static final Logger logger = LogManager.getLogger(ControladorReporteCasosSorteados.class.getName());
	
	@EJB
	MateriaFacade materiaFacade;

	@EJB
	ServicioFacade servicioFacade;

	@EJB
	ReporteFacade reporteFacade;

	private static final long serialVersionUID = 1L;

	private List<Servicio> tipoCaso;
	private List<ReporteCasosSorteadosDTO> reporteCasosSorteadosDTOs;	
	private List<ServicioDTO> tipoCasoDTOs;
	private Long tipoCasoSeleccionado;
	private List <String> consumo;
	private String consumoSeleccionado;
	
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
		nombreReporte = UtilSimasc.generarNombreReporte("Reporte_casos_sorteados");
		cargarListas();
	}

	@PreDestroy
	public void preDestroy() {
		limpiarCampos();
	}
	
	public void limpiarCampos() {
		reporteCasosSorteadosDTOs = new ArrayList<>();
		tipoCasoDTOs = new ArrayList<>();
		tipoCasoSeleccionado=null;
		consumoSeleccionado = null;
	}

	/**
	 * Metodo encargado de cargar las listas de filtrado
	 */
	public void cargarListas() {
		consumo = new ArrayList<>();
		consumo.add("SI");
		consumo.add("NO");
		tipoCasoDTOs = servicioFacade.getBusquedaServicios();
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
				Boolean paramConsumo = null;
				if(!consumoSeleccionado.equals("-1")){
					paramConsumo = (consumoSeleccionado.equals("SI"));
				}				

				reporteCasosSorteadosDTOs = reporteFacade.getReporteCasosSorteados(
						fechaInicialSeleccionada, fechaFinSeleccionada, tipoCasoSeleccionado, paramConsumo);
				if (reporteCasosSorteadosDTOs.size() != 0) {
					visible = false;
				} else {
					visible = true;
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
									MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString())));
				}				
				setTotal(reporteCasosSorteadosDTOs.size());
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
		fechaInicialSeleccionada = null;
		fechaFinSeleccionada = null;
		reporteCasosSorteadosDTOs = new ArrayList<>();
		tipoCasoSeleccionado=null;
		consumoSeleccionado = null;
	}

	/**
	 * Metodo encargado de validar los campos del formulario
	 */
	public boolean validarCampos() throws Exception {
		boolean valido = true;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		StringBuilder campo = new StringBuilder();
		if (fechaInicialSeleccionada == null) {
			campo.append("Fecha Inicial ");
		}
		if (fechaFinSeleccionada == null) {
			campo.append("Fecha Final ");
		}
		if (fechaInicialSeleccionada == null || fechaFinSeleccionada == null) {
			valido = false;
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN,UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, 
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO032.toString())+campo));
		}
		if (fechaInicialSeleccionada != null && fechaFinSeleccionada != null) {			
			if (fechaInicialSeleccionada.after(fechaFinSeleccionada)) {
				valido = false;
				facesContext.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_WARN,UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, 
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO037.toString())));
			}
		}
		return valido;
	}
	
	/**
	 * Gernera el excel con el resultado del reporte
	 * se usa Apache POI
	 */
	public void generarExcel() {
		FacesContext facesCont = FacesContext.getCurrentInstance();
	    try {
	    	//libro de excel
	        HSSFWorkbook hSSFworkbook = new HSSFWorkbook();        
	        //hoja de calculo
	        HSSFSheet sheet = hSSFworkbook.createSheet("Casos Sorteados");        
	        
	        int fila = 0;
	        for (ReporteCasosSorteadosDTO reporteDto : reporteCasosSorteadosDTOs) {
	        	sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 8));
	        	Row encabezado = sheet.createRow(fila++);
	        	
	        	Cell celda = encabezado.createCell(0);
	            celda.setCellValue(reporteDto.getIdCaso() + " - " + reporteDto.getNombreCaso());            
	            
	            Row encvCasoFila = sheet.createRow(fila++);
	            encvCasoFila.createCell(0).setCellValue("Fecha Sorteo");
	            encvCasoFila.createCell(1).setCellValue("Servicio");
	            encvCasoFila.createCell(2).setCellValue("Materia");
	            encvCasoFila.createCell(3).setCellValue("Cuantía");
	            encvCasoFila.createCell(4).setCellValue("Valor");
	            encvCasoFila.createCell(5).setCellValue("Tipo Preselección");
	            encvCasoFila.createCell(6).setCellValue("Preselección");
	            encvCasoFila.createCell(7).setCellValue("Tipo de Sorteo");
	            encvCasoFila.createCell(8).setCellValue("Consumo");
	            Row dataCasoFila = sheet.createRow(fila++);
	            dataCasoFila.createCell(0).setCellValue(reporteDto.getFechaSorteo());                        
	            dataCasoFila.createCell(1).setCellValue(reporteDto.getTipoCaso());
	            dataCasoFila.createCell(2).setCellValue(reporteDto.getMateria());
	            dataCasoFila.createCell(3).setCellValue(reporteDto.getTipoCuantia());
	            dataCasoFila.createCell(4).setCellValue(reporteDto.getValorPretensiones());
	            dataCasoFila.createCell(5).setCellValue(reporteDto.getTipoPreseleccion());
	            dataCasoFila.createCell(6).setCellValue(reporteDto.getPreseleccionLabel());
	            dataCasoFila.createCell(7).setCellValue(reporteDto.getTipoSorteo());
	            dataCasoFila.createCell(8).setCellValue(reporteDto.getConsumo());
	            sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 3));
	            
	            Row encvArbFila = sheet.createRow(fila++);
	            
	            sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 3));
	            encvArbFila.createCell(0).setCellValue("Arbitro");	            
	            encvArbFila.createCell(4).setCellValue("Nombramiento");
	            
	            for (RolPersonaCaso arbitro : reporteDto.getArbitros()) {
	            	Row datavArbFila = sheet.createRow(fila++);
	            	sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 3));
	            	datavArbFila.createCell(0).setCellValue(arbitro.getPersona().getNombreCompleto());
	            	datavArbFila.createCell(4).setCellValue(arbitro.getNombramientoSorteo());
	            }	            
	            sheet.createRow(fila++);        	
	        }	        	        
		    ExternalContext externalCont = facesCont.getExternalContext();
		    externalCont.setResponseContentType("application/vnd.ms-excel");
		    externalCont.setResponseHeader("Content-Disposition", "attachment; filename=\"" + this.nombreReporte + ".xls\"");
		    hSSFworkbook.write(externalCont.getResponseOutputStream());
		    hSSFworkbook.close();
			
		} catch (IOException e) {
			facesCont.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
					UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, e.getMessage()));
			logger.error("Error: ", e);
		}
	    facesCont.responseComplete();
	}
	
	public List<ReporteCasosSorteadosDTO> getReporteCasosSorteadosDTOs() {
		return reporteCasosSorteadosDTOs;
	}
	public void setReporteCasosSorteadosDTOs(List<ReporteCasosSorteadosDTO> reporteCasosSorteadosDTOs) {
		this.reporteCasosSorteadosDTOs = reporteCasosSorteadosDTOs;
	}
	public Long getTipoCasoSeleccionado() {
		return tipoCasoSeleccionado;
	}
	public void setTipoCasoSeleccionado(Long tipoCasoSeleccionado) {
		this.tipoCasoSeleccionado = tipoCasoSeleccionado;
	}
	public List<Servicio> getTipoCaso() {
		return tipoCaso;
	}
	public void setTipoCaso(List<Servicio> tipoCaso) {
		this.tipoCaso = tipoCaso;
	}
	public List<ServicioDTO> getTipoCasoDTOs() {
		return tipoCasoDTOs;
	}
	public void setTipoCasoDTOs(List<ServicioDTO> tipoCasoDTOs) {
		this.tipoCasoDTOs = tipoCasoDTOs;
	}
	public List<String> getConsumo() {
		return consumo;
	}
	public void setConsumo(List<String> consumo) {
		this.consumo = consumo;
	}

	public String getConsumoSeleccionado() {
		return consumoSeleccionado;
	}

	public void setConsumoSeleccionado(String consumoSeleccionado) {
		this.consumoSeleccionado = consumoSeleccionado;
	}	
	
}
