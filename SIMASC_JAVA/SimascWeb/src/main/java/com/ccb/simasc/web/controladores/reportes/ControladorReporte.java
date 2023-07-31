package com.ccb.simasc.web.controladores.reportes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.event.SelectEvent;

import com.ccb.simasc.negocio.arbitraje.ListaFacade;
import com.ccb.simasc.negocio.arbitraje.ServicioFacade;
import com.ccb.simasc.transversal.dto.ListaDTO;
import com.ccb.simasc.transversal.dto.ServicioDTO;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

public abstract class ControladorReporte {
	
	@EJB
	ServicioFacade servicioFacade;	
	@EJB
	ListaFacade listaFacade;
	
	protected String fechaIni;
	protected String fechaFin;
	protected String codigoCaso;
	protected String nombreCaso;
	protected String nombreDocumento;
	protected List<Dominio> motivoCierre;	
	protected Map<String, Object> filtrosBusquedaReporte;	
	protected String materiaSeleccionada;
	protected String motivoSeleccionado;
	protected Date fechaInicialSeleccionada;
	protected Date fechaFinSeleccionada;
	protected String nombreReporte;
	protected boolean visible;
	protected int total = 0;
	protected boolean limpiar = true;	
	protected List<ServicioDTO> tipoCasoDTOs;
	protected List<ListaDTO> tipoListaDTOs;
	protected List<Servicio> tipoCaso;
	protected String tipoCasoSeleccionadoReporte;	
	
	public void preRenderComp() {		
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			this.setTotal(0);			
			visible = true;
		}		
		limpiar = true;
	}
	
	@PostConstruct
	public void postConstruct() {				
		limpiarCampos();
	}

	@PreDestroy
	public void preDestroy() {				
		limpiarCampos();
	}
	
	public void limpiarCampos() {
		visible = true;
		fechaInicialSeleccionada=null;
		fechaFinSeleccionada=null;					
		filtrosBusquedaReporte = new HashMap<>();
	}
	
	/**
	 * Metodo encargado de recuperar el valor del componente grafico para fecha
	 */
	public void onDateSelect(SelectEvent event) {
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date fecha = (Date) event.getObject();
		fechaIni = format.format(fecha);
	}

	/**
	 * Metodo encargado de recuperar el valor del componente grafico para fecha
	 */
	public void onDateSelectF(SelectEvent event) {
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date fecha = (Date) event.getObject();
		fechaFin = format.format(fecha);
	}
	
	/**
	 * Metodo encargado de dar formato al archivo excel generado
	 * @param Object document
	 */
	public void generarXLS(Object document) {
        HSSFWorkbook documento = (HSSFWorkbook) document;
        HSSFSheet hoja = documento.getSheetAt(0);
        HSSFRow encabezado = hoja.getRow(0);
         
        HSSFCellStyle cellStyle = documento.createCellStyle();  
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         
        for(int i=0; i < encabezado.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = encabezado.getCell(i);
            cell.setCellStyle(cellStyle);
        }
    }
	
	/**
	 * Metodo encargado de cargar las listas de filtrado
	 */
	public void cargarListas() {
		tipoCasoDTOs = servicioFacade.getBusquedaServicios();						
		List<ServicioDTO> l1 = new ArrayList<>();
		for(ServicioDTO serv : tipoCasoDTOs) {			
			if(serv.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
				l1.add(serv);		
			}
		}
		tipoCasoDTOs = l1;
		
		List<ListaDTO> l2 = new ArrayList<>();
		tipoListaDTOs = listaFacade.getListasDTO();				
		for(ListaDTO tipoA : tipoListaDTOs) {
			if(tipoA.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
				l2.add(tipoA);
			}
		}
		tipoListaDTOs = l2;

	}
	
	public void cargaServiciosParamSorteo() {
		tipoCasoDTOs = servicioFacade.getBusquedaServiciosParametroSorteo();
	}
	
	
	public List<Dominio> getMotivoCierre() {
		return motivoCierre;
	}
	public void setMotivoCierre(List<Dominio> motivoCierre) {
		this.motivoCierre = motivoCierre;
	}
	public Map<String, Object> getFiltrosBusquedaReporte() {
		return filtrosBusquedaReporte;
	}
	public void setFiltrosBusquedaReporte(Map<String, Object> filtrosBusquedaReporte) {
		this.filtrosBusquedaReporte = filtrosBusquedaReporte;
	}
	public String getMateriaSeleccionada() {
		return materiaSeleccionada;
	}
	public void setMateriaSeleccionada(String materiaSeleccionada) {
		this.materiaSeleccionada = materiaSeleccionada;
	}
	public String getMotivoSeleccionado() {
		return motivoSeleccionado;
	}
	public void setMotivoSeleccionado(String motivoSeleccionado) {
		this.motivoSeleccionado = motivoSeleccionado;
	}
	public Date getFechaInicialSeleccionada() {
		return fechaInicialSeleccionada;
	}
	public void setFechaInicialSeleccionada(Date fechaInicialSeleccionada) {
		this.fechaInicialSeleccionada = fechaInicialSeleccionada;
	}
	public Date getFechaFinSeleccionada() {
		return fechaFinSeleccionada;
	}
	public void setFechaFinSeleccionada(Date fechaFinSeleccionada) {
		this.fechaFinSeleccionada = fechaFinSeleccionada;
	}
	public String getNombreReporte() {
		return nombreReporte;
	}
	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public boolean isLimpiar() {
		return limpiar;
	}
	public void setLimpiar(boolean limpiar) {
		this.limpiar = limpiar;
	}
	public String getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
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
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public List<ServicioDTO> getTipoCasoDTOs() {
		return tipoCasoDTOs;
	}

	public void setTipoCasoDTOs(List<ServicioDTO> tipoCasoDTOs) {
		this.tipoCasoDTOs = tipoCasoDTOs;
	}

	public List<ListaDTO> getTipoListaDTOs() {
		return tipoListaDTOs;
	}

	public void setTipoListaDTOs(List<ListaDTO> tipoListaDTOs) {
		this.tipoListaDTOs = tipoListaDTOs;
	}

	public List<Servicio> getTipoCaso() {
		return tipoCaso;
	}

	public void setTipoCaso(List<Servicio> tipoCaso) {
		this.tipoCaso = tipoCaso;
	}

	public String getTipoCasoSeleccionadoReporte() {
		return tipoCasoSeleccionadoReporte;
	}

	public void setTipoCasoSeleccionadoReporte(String tipoCasoSeleccionadoReporte) {
		this.tipoCasoSeleccionadoReporte = tipoCasoSeleccionadoReporte;
	}

	
}
