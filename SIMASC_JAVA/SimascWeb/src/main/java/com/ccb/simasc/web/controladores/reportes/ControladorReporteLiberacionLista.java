package com.ccb.simasc.web.controladores.reportes;

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
import javax.faces.context.FacesContext;

import com.ccb.simasc.negocio.arbitraje.ListaFacade;
import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.negocio.arbitraje.ServicioFacade;
import com.ccb.simasc.transversal.dto.SorteoLiberacionListaDTO;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;


/**
 * Clase controladora para generar el reporte de liberacion lista
 * @author aacevedo
 *
 */
@ManagedBean(name = "controladorReporteLiberacionListaView")
@ViewScoped
public class ControladorReporteLiberacionLista extends ControladorMaterias implements Serializable{

	@EJB
	ReporteFacade reporteFacade;
	
	@EJB
	ServicioFacade servicioFacade;	

	@EJB
	ListaFacade listaFacade;

	private static final long serialVersionUID = 1L;	

	private transient List<SorteoLiberacionListaDTO> reporteLiberacionLista;
	private String tipoCasoSeleccionado;
	private String tipoListaSeccionado;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {		
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			this.setTotal(0);		
			visible = true;
		}		
		limpiar = true;
	}


	@PostConstruct
	public void postConstruct() {		
		nombreReporte = UtilSimasc.generarNombreReporte("Reporte_liberacion_lista");
		tipoCaso = new ArrayList<>();
		tipoCasoDTOs = new ArrayList<>();
		tipoListaDTOs = new ArrayList<>();	
		tipoCasoSeleccionado = null;
		cargarListas();
	}

	@PreDestroy
	public void preDestroy() {			
		tipoCaso = new ArrayList<>();
		tipoCasoDTOs = new ArrayList<>();
		tipoListaDTOs = new ArrayList<>();	
		tipoCasoSeleccionado= null;
	}


	/**
	 * Metodo encargado de consultar la informacion del reporte 
	 */
	public void botonBuscar() {
		this.limpiar = false;
		filtrosBusquedaReporte= new HashMap<>();	
		FacesContext facesContxt = FacesContext.getCurrentInstance();			
		try {			
			if(validarCamposF()) {
				
				if (fechaInicialSeleccionada != null) {
					filtrosBusquedaReporte.put("fechaInicialLibList", fechaInicialSeleccionada);
				}
				if (fechaFinSeleccionada != null) {
					filtrosBusquedaReporte.put("fechaFinalLibList", fechaFinSeleccionada);
				}
				if (tipoCasoSeleccionado != null && !tipoCasoSeleccionado.equals("")) {			
					filtrosBusquedaReporte.put("tipoCasoLibList", tipoCasoSeleccionado);
				}			
				if (materiaSeleccionada != null && !materiaSeleccionada.equals("")) {
					filtrosBusquedaReporte.put("materiaLibList", materiaSeleccionada);
				}
				if(!tipoListaSeccionado.equals("0")){
					filtrosBusquedaReporte.put("tipoListaCodigoLibList", tipoListaSeccionado);
				}
				limpiarFiltros();
				reporteLiberacionLista = reporteFacade.getReporteLiberacionLista(filtrosBusquedaReporte);
				
				if (reporteLiberacionLista.size() != 0) {
					visible = false;
					nombreReporte = UtilSimasc.generarNombreReporte("Reporte_liberacion_lista");				
				} else {				
					FacesContext fcontext = FacesContext.getCurrentInstance();
					fcontext.addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA,
									MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString())));				
				}
				setTotal(reporteLiberacionLista.size());
			}
		} catch (Exception e) {
			total = 0;
			limpiarFiltros();
			visible = true;
			facesContxt.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, e.getMessage()));
		}
	}
	
	/**
	 * Metodo encargado de limpiar los filtros de busqueda
	 */
	public void limpiarFiltros() {		
		fechaInicialSeleccionada = null;
		fechaFinSeleccionada = null;	
		materiaSeleccionada = null;
		tipoCasoSeleccionado = null;
		tipoListaSeccionado = null;
		reporteLiberacionLista = new ArrayList<>();						
	}
	
	/**
	 *  Metodo encargado de validar obligatoriedad de los campos del reporte
	 * @return Boolean
	 */
	public boolean validarCamposF(){
		boolean bandera = true;	
		boolean banderaF = true;		
		
		if (fechaInicialSeleccionada != null || fechaFinSeleccionada != null) {								
			if((fechaInicialSeleccionada == null || fechaFinSeleccionada == null)) {
				banderaF =  false;
			}
			if(!banderaF) {		
				bandera = false;
				total = 0;
				reporteLiberacionLista = new ArrayList<>();								
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,  UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, 
						 MensajesSimasc.getInstancia().getMensajePorKey(
									MensajesEnum.INFO043.toString())));
			}
		}		
		
				
		return bandera;
	}
	
	public List<SorteoLiberacionListaDTO> getReporte() {
		return reporteLiberacionLista;
	}
	public void setReporte(List<SorteoLiberacionListaDTO> reporte) {
		this.reporteLiberacionLista = reporte;
	}
	public String getTipoCasoSeleccionado() {
		return tipoCasoSeleccionado;
	}
	public void setTipoCasoSeleccionado(String tipoCasoSeleccionado) {
		this.tipoCasoSeleccionado = tipoCasoSeleccionado;
	}
	public String getTipoListaSeccionado() {
		return tipoListaSeccionado;
	}
	public void setTipoListaSeccionado(String tipoListaSeccionado) {
		this.tipoListaSeccionado = tipoListaSeccionado;
	}
}
