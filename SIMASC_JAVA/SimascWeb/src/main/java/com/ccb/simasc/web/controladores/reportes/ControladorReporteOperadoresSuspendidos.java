package com.ccb.simasc.web.controladores.reportes;

import static com.ccb.simasc.transversal.utilidades.UtilReflection.obtenerValorLlaves;

import java.io.Serializable;
import java.util.ArrayList;
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

import com.ccb.simasc.negocio.arbitraje.GeneradorListasDeValores;
import com.ccb.simasc.negocio.arbitraje.ListaFacade;
import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.negocio.arbitraje.ServicioFacade;
import com.ccb.simasc.negocio.transversal.FachadaDominios;
import com.ccb.simasc.transversal.dto.ArbitroDTO;
import com.ccb.simasc.transversal.dto.DominioDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteOperadoresSuspendidosDTO;
import com.ccb.simasc.transversal.entidades.DominioPK;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;


/**
 * Clase controladora para generar el reporte de operadores suspendidos
 * @author aacevedo
 *
 */
@ManagedBean(name = "controladorReporteOperadoresSuspendidosView")
@ViewScoped
public class ControladorReporteOperadoresSuspendidos extends ControladorMaterias implements Serializable{

	@EJB
	ReporteFacade reporteFacade;
	
	@EJB
	ServicioFacade servicioFacade;
	

	@EJB
	ListaFacade listaFacade;

	@EJB
	FachadaDominios dominioFacade;

	
	@EJB
	private GeneradorListasDeValores generadorListas;
	

	private static final long serialVersionUID = 1L;

	
	private transient List<ReporteOperadoresSuspendidosDTO> reporteOperadores;		
	private List<ArbitroDTO> arbitros;
	private List<DominioDTO> motivoDTOs;
	private Long arbitroSeleccionado;

	
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderCompO() {		
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {			
			this.setTotal(0);
			arbitroSeleccionado = (long)-1;
			motivoSeleccionado = null;		
			visible = true;
			reporteOperadores = new ArrayList<>();						
		}		
		limpiar = true;
	}


	@PostConstruct
	public void postConstructO() {	
		nombreReporte = UtilSimasc.generarNombreReporte("Reporte_operadores_suspendidos");
		visible = true;		
		fechaInicialSeleccionada=null;
		fechaFinSeleccionada=null;			
		reporteOperadores = new ArrayList<>();		
		filtrosBusquedaReporte = new HashMap<>();
		tipoCaso = new ArrayList<>();
		tipoCasoDTOs = new ArrayList<>();
		arbitros = new ArrayList<>();
		arbitroSeleccionado = null;
		tipoCasoSeleccionadoReporte = null;
		motivoSeleccionado = null;		
		consultarListaReporte();
		cargaServiciosParamSorteo();
		
	}

	@PreDestroy
	public void preDestroyO() {						
		tipoCasoSeleccionadoReporte= null;
		motivoSeleccionado = null;
		arbitroSeleccionado = null;
		fechaInicialSeleccionada=null;
		fechaFinSeleccionada=null;
		filtrosBusquedaReporte = new HashMap<>();
		reporteOperadores = new ArrayList<>();				
		tipoCaso = new ArrayList<>();
		tipoCasoDTOs = new ArrayList<>();	
		motivoDTOs = new ArrayList<>();
		visible = true;		
	}


	/**
	 * Metodo encargado de consultar la informacion del reporte 
	 */
	public void botonBuscar() {
		this.limpiar = false;
		filtrosBusquedaReporte= new HashMap<>();	
		FacesContext facesContextOperadores = FacesContext.getCurrentInstance();			
		try {
			if(validarCamposO()) {
				if (fechaInicialSeleccionada != null) {
					filtrosBusquedaReporte.put("fechaInicial", fechaInicialSeleccionada);
				}
				if (fechaFinSeleccionada != null) {
					filtrosBusquedaReporte.put("fechaFinal", fechaFinSeleccionada);
				}
				if (tipoCasoSeleccionadoReporte != null && !tipoCasoSeleccionadoReporte.equals("")) {			
					filtrosBusquedaReporte.put("tipoCaso", tipoCasoSeleccionadoReporte);
				}							
				if(!motivoSeleccionado.equals("0")){
					Map<String,String> llavesMotivo = obtenerValorLlaves(DominioPK.class, motivoSeleccionado);
					filtrosBusquedaReporte.put("motivo", llavesMotivo.get("codigo"));
				}
				if (arbitroSeleccionado != -1 && arbitroSeleccionado != null) {
					filtrosBusquedaReporte.put("operador", arbitroSeleccionado);
				}
				
				limpiarFiltros();
				reporteOperadores = reporteFacade.getReporteOperadoresSuspendidos(filtrosBusquedaReporte);				
				if (reporteOperadores.size() != 0) {
					visible = false;
					nombreReporte = UtilSimasc.generarNombreReporte("Reporte_operadores_suspendidos");				
				} else {				
					FacesContext contextOperadores = FacesContext.getCurrentInstance();
					contextOperadores.addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA,
									MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString())));				
				}
				setTotal(reporteOperadores.size());
			}
		} catch (Exception e) {
			total = 0;
			limpiarFiltros();
			visible = true;
			facesContextOperadores.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, e.getMessage()));
		}


	}
	
	/**
	 * Metodo encargado de limpiar los filtros de busqueda
	 */
	public void limpiarFiltros() {	
		reporteOperadores = new ArrayList<>();	
		materiaSeleccionada = null;
		motivoSeleccionado = null;
		tipoCasoSeleccionadoReporte = null;
		arbitroSeleccionado = null;	
		fechaInicialSeleccionada = null;
		fechaFinSeleccionada = null;				
	}

	
	/**
	 *  Metodo encargado de validar obligatoriedad de los campos del reporte
	 * @return Boolean
	 */
	public boolean validarCamposO(){
		boolean banderaO = true;				
		boolean banderaF = true;		
		
		if (fechaInicialSeleccionada != null || fechaFinSeleccionada != null) {								
			if((fechaInicialSeleccionada == null || fechaFinSeleccionada == null)) {
				banderaF =  false;
			}
			if(!banderaF) {		
				banderaO = false;
				total = 0;
				reporteOperadores = new ArrayList<>();								
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,  UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, 
						 MensajesSimasc.getInstancia().getMensajePorKey(
									MensajesEnum.INFO043.toString())));
			}
		}		
		
				
		return banderaO;
	} 
	
			
	
	/**
	 * Consulta listas necestarias de este reporte
	 * 
	 */
	public void consultarListaReporte() {
		arbitros = generadorListas.consultarArbitrosDTO();
		motivoDTOs =dominioFacade.getDominiosDTO(UtilDominios.DOMINIO_MOTIVO_ESTADO);						
		List<DominioDTO> l1 = new ArrayList<>();
		for(DominioDTO m : motivoDTOs) {			
			if(m.getDominioPK().getCodigo().equals(UtilDominios.MOTIVO_CONTESTACION_EXTEMPORANEA) ||
				m.getDominioPK().getCodigo().equals(UtilDominios.MOTIVO_NO_ACEPTACION) ||
				m.getDominioPK().getCodigo().equals(UtilDominios.MOTIVO_NO_CONTESTACION)) {
					l1.add(m);		
			}
		}
		motivoDTOs = l1;
		
		
	}


	/**
	 * Getters y Setters de las variables de la clase
	 */

	public List<ReporteOperadoresSuspendidosDTO> getReporte() {
		return reporteOperadores;
	}


	public void setReporte(List<ReporteOperadoresSuspendidosDTO> reporte) {
		this.reporteOperadores = reporte;
	}


	public List<DominioDTO> getMotivoDTOs() {
		return motivoDTOs;
	}


	public void setMotivoDTOs(List<DominioDTO> motivoDTOs) {
		this.motivoDTOs = motivoDTOs;
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
