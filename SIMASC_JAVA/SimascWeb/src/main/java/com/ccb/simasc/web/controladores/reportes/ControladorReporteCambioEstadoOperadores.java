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

import com.ccb.simasc.negocio.arbitraje.GeneradorListasDeValores;
import com.ccb.simasc.negocio.arbitraje.ListaFacade;
import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.negocio.arbitraje.ServicioFacade;
import com.ccb.simasc.transversal.dto.ArbitroDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCambioEstadoOperadoresDTO;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;


/**
 * Clase controladora para generar el reporte de cambio estados de operadores
 * @author aacevedo
 *
 */
@ManagedBean(name = "controladorReporteCambioEstadoOperadoresView")
@ViewScoped
public class ControladorReporteCambioEstadoOperadores extends ControladorMaterias implements Serializable{

	@EJB
	ReporteFacade reporteFacade;
	
	@EJB
	ServicioFacade servicioFacade;
	

	@EJB
	ListaFacade listaFacade;
	
	@EJB
	private GeneradorListasDeValores generadorListas;
	

	private static final long serialVersionUID = 1L;

	
	private transient List<ReporteCambioEstadoOperadoresDTO> reporteEstados;			
	private List<ArbitroDTO> arbitros;	
	private String tipoListaSeccionado;
	private Long arbitroSeleccionado;

	
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {		
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			arbitroSeleccionado = (long)-1;
			this.setTotal(0);
			reporteEstados = new ArrayList<>();			
			visible = true;
		}		
		limpiar = true;
	}


	@PostConstruct
	public void postConstruct() {				
		visible = true;		
		fechaInicialSeleccionada=null;
		fechaFinSeleccionada=null;	
		nombreReporte = UtilSimasc.generarNombreReporte("Reporte_cambio_estado_operadores");
		reporteEstados = new ArrayList<>();		
		filtrosBusquedaReporte = new HashMap<>();
		tipoCaso = new ArrayList<>();
		tipoCasoDTOs = new ArrayList<>();
		materiaDTOs = new ArrayList<>();
		tipoListaDTOs = new ArrayList<>();	
		arbitros = new ArrayList<>();
		tipoCasoSeleccionadoReporte = null;
		cargarListas();
		consultarArbitros();
	}

	@PreDestroy
	public void preDestroy() {				
		visible = true;		
		fechaInicialSeleccionada=null;
		fechaFinSeleccionada=null;
		arbitroSeleccionado = null;
		reporteEstados = new ArrayList<>();				
		filtrosBusquedaReporte = new HashMap<>();
		tipoCaso = new ArrayList<>();
		tipoCasoDTOs = new ArrayList<>();
		materiaDTOs = new ArrayList<>();
		tipoListaDTOs = new ArrayList<>();	
		tipoCasoSeleccionadoReporte= null;
	}


	/**
	 * Metodo encargado de consultar la informacion del reporte 
	 */
	public void botonBuscar() {
		this.limpiar = false;
		filtrosBusquedaReporte= new HashMap<>();	
		FacesContext facesContextEstado = FacesContext.getCurrentInstance();			
		try {
			if(validarCampos()) {
				if (fechaInicialSeleccionada != null) {
					filtrosBusquedaReporte.put("fechaInicial", fechaInicialSeleccionada);
				}
				if (fechaFinSeleccionada != null) {
					filtrosBusquedaReporte.put("fechaFinal", fechaFinSeleccionada);
				}
				if (tipoCasoSeleccionadoReporte != null && !tipoCasoSeleccionadoReporte.equals("")) {			
					filtrosBusquedaReporte.put("tipoCaso", tipoCasoSeleccionadoReporte);
				}			
				if (materiaSeleccionada != null && !materiaSeleccionada.equals("")) {
					filtrosBusquedaReporte.put("materia", materiaSeleccionada);
				}
				if(!tipoListaSeccionado.equals("0")){
					filtrosBusquedaReporte.put("tipoListaCodigo", tipoListaSeccionado);
				}
				if (arbitroSeleccionado != -1 && arbitroSeleccionado != null) {
					filtrosBusquedaReporte.put("operador", arbitroSeleccionado);
				}
				
				limpiarFiltros();
				reporteEstados = reporteFacade.getReporteCambioEstadoOperadores(filtrosBusquedaReporte);				
				if (reporteEstados.size() != 0) {
					visible = false;
					nombreReporte = UtilSimasc.generarNombreReporte("Reporte_cambio_estado_operadores");				
				} else {				
					FacesContext contextEstado = FacesContext.getCurrentInstance();
					contextEstado.addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA,
									MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString())));				
				}
				setTotal(reporteEstados.size());
			}
		} catch (Exception e) {
			total = 0;
			limpiarFiltros();
			visible = true;
			facesContextEstado.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, e.getMessage()));
		}


	}
	
	/**
	 * Metodo encargado de limpiar los filtros de busqueda
	 */
	public void limpiarFiltros() {		
		fechaInicialSeleccionada = null;
		fechaFinSeleccionada = null;	
		materiaSeleccionada = null;
		tipoCasoSeleccionadoReporte = null;
		tipoListaSeccionado = null;
		arbitroSeleccionado = null;	
		reporteEstados = new ArrayList<>();		
	}

	
	/**
	 *  Metodo encargado de validar obligatoriedad de los campos del reporte
	 * @return Boolean
	 */
	public boolean validarCampos(){
		boolean bandera = true;		
		
		if (fechaInicialSeleccionada == null || fechaFinSeleccionada == null ) {					
			bandera =  false;
		}		
		if(!bandera) {			
			total = 0;
			reporteEstados = new ArrayList<>();								
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,  UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, 
					 MensajesSimasc.getInstancia().getMensajePorKey(
								MensajesEnum.INFO043.toString())));
		}
				
		return bandera;
	}
	
			
	
	/**
	 * Consulta las personas con el rol de arbitro
	 * 
	 */
	public void consultarArbitros() {
		arbitros = generadorListas.consultarArbitrosDTO();
	}


	/**
	 * Getters y Setters de las variables de la clase
	 */
	


	public List<Servicio> getTipoCaso() {
		return tipoCaso;
	}


	public void setTipoCaso(List<Servicio> tipoCaso) {
		this.tipoCaso = tipoCaso;
	}



	public String getTipoListaSeccionado() {
		return tipoListaSeccionado;
	}


	public void setTipoListaSeccionado(String tipoListaSeccionado) {
		this.tipoListaSeccionado = tipoListaSeccionado;
	}


	public List<ReporteCambioEstadoOperadoresDTO> getReporte() {
		return reporteEstados;
	}


	public void setReporte(List<ReporteCambioEstadoOperadoresDTO> reporte) {
		this.reporteEstados = reporte;
	}


	public List<ArbitroDTO> getArbitros() {
		return arbitros;
	}


	public void setArbitros(List<ArbitroDTO> arbitros) {
		this.arbitros = arbitros;
	}


	public Long getArbitroSeleccionado() {
		return arbitroSeleccionado;
	}


	public void setArbitroSeleccionado(Long arbitroSeleccionado) {
		this.arbitroSeleccionado = arbitroSeleccionado;
	}
	
	



}
