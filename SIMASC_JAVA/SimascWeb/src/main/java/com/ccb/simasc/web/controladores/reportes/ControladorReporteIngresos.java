package com.ccb.simasc.web.controladores.reportes;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.ccb.simasc.negocio.arbitraje.MateriaFacade;
import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.negocio.transversal.FachadaDominios;
import com.ccb.simasc.transversal.dto.DominioDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteIngresosDTO;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;

/**
 * Clase controladora para generar el reporte de ingresos
 * @author aalvarez
 *
 */
@ManagedBean(name = "controladorReporteIngresosView")
@ViewScoped
public class ControladorReporteIngresos extends ControladorReporte implements Serializable{


	@EJB
	MateriaFacade materiaFacade;

	@EJB
	ReporteFacade reporteFacade;
	
	@EJB
	FachadaDominios dominioFacade;

	private static final long serialVersionUID = 1L;

	private List<ReporteIngresosDTO> reporteIngresosDTOs;	
	private List<DominioDTO> pagadosDTOs;
	private List<Dominio> pagados;
	private String pagoSeleccionado;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			reporteIngresosDTOs = new ArrayList<ReporteIngresosDTO>();
			total = 0;
			visible = true;
		}
		limpiar = true;
	}


	@PostConstruct
	public void postConstruct() {
		reporteIngresosDTOs = new ArrayList<ReporteIngresosDTO>();
		pagadosDTOs = new ArrayList<DominioDTO>();
		filtrosBusquedaReporte = new HashMap<>();
		visible = true;
		pagoSeleccionado=null;
		fechaInicialSeleccionada=null;
		fechaFinSeleccionada=null;
		nombreReporte = UtilSimasc.generarNombreReporte("Reporte_ingresos");
		cargarListas();
	}

	@PreDestroy
	public void preDestroy() {
		reporteIngresosDTOs = new ArrayList<ReporteIngresosDTO>();
		pagadosDTOs = new ArrayList<DominioDTO>();
		filtrosBusquedaReporte = new HashMap<>();
		visible = true;
		pagoSeleccionado=null;
		fechaInicialSeleccionada=null;
		fechaFinSeleccionada=null;
	}
	
	/**
	 * Metodo encargado de cargar las listas de filtrado
	 */
	public void cargarListas() {
		
		
		pagados = dominioFacade.getDominios(UtilDominios.DOMINIO_PAGADOS);
		for (Dominio dominio : pagados) {
			DominioDTO newDominio = new DominioDTO();
			newDominio.setNombre(dominio.getNombre());
			newDominio.setDominioPK(dominio.getDominioPK());
			newDominio.setDescripcion(dominio.getDescripcion());
			pagadosDTOs.add(newDominio);
		}

	}

	
	/**
	 * Metodo encargado de consultar la informacion del reporte
	 */
	public void buscar() {
		this.limpiar = false;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		filtrosBusquedaReporte= new HashMap<>();
		String codigo=null;
		String dominio=null;
		
		try {
			validarCampos();
			if (pagoSeleccionado != null && !pagoSeleccionado.equals("")) {
				String[] parts = pagoSeleccionado.split(",");
				codigo = parts[0].substring(6); 
				dominio = parts[1].substring(7); 
				filtrosBusquedaReporte.put("codigo", codigo);
				filtrosBusquedaReporte.put("dominio", dominio);
			}
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			if (fechaInicialSeleccionada != null) {
				filtrosBusquedaReporte.put("fechaInicial",df.format(fechaInicialSeleccionada));
			}
			if (fechaFinSeleccionada != null) {
				filtrosBusquedaReporte.put("fechaFinal", df.format(fechaFinSeleccionada));
			}
			limpiarFiltros();
			reporteIngresosDTOs = reporteFacade.getReporteIngresos(filtrosBusquedaReporte);
			setTotal(reporteIngresosDTOs.size());
			if (reporteIngresosDTOs.size() != 0) {
				visible = false;
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString())));

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
		reporteIngresosDTOs = new ArrayList<ReporteIngresosDTO>();
		pagoSeleccionado=null;
	}

	/**
	 * Metodo encargado de validar los campos del formulario
	 */
	public void validarCampos() throws Exception {

		StringBuilder campo = new StringBuilder();
		if (fechaInicialSeleccionada == null) {
			campo.append("Fecha Inicial ");
		}
		if (fechaFinSeleccionada == null) {
			campo.append("Fecha Final ");
		}
		if (fechaInicialSeleccionada == null || fechaFinSeleccionada == null) {
			throw new Exception( MensajesSimasc.getInstancia().getMensajePorKey(
					MensajesEnum.INFO032.toString()) + campo);
		}
		if (fechaInicialSeleccionada != null && fechaFinSeleccionada != null) {
			if (fechaInicialSeleccionada.after(fechaFinSeleccionada)) {
				throw new Exception( MensajesSimasc.getInstancia().getMensajePorKey(
						MensajesEnum.INFO037.toString()));
			}
		}

	}	

	public List<ReporteIngresosDTO> getReporteIngresosDTOs() {
		return reporteIngresosDTOs;
	}

	public void setReporteIngresosDTOs(List<ReporteIngresosDTO> reporteIngresosDTOs) {
		this.reporteIngresosDTOs = reporteIngresosDTOs;
	}

	public List<DominioDTO> getPagadosDTOs() {
		return pagadosDTOs;
	}

	public void setPagadosDTOs(List<DominioDTO> pagadosDTOs) {
		this.pagadosDTOs = pagadosDTOs;
	}

	public List<Dominio> getPagados() {
		return pagados;
	}

	public void setPagados(List<Dominio> pagados) {
		this.pagados = pagados;
	}


	public String getPagoSeleccionado() {
		return pagoSeleccionado;
	}

	public void setPagoSeleccionado(String pagoSeleccionado) {
		this.pagoSeleccionado = pagoSeleccionado;
	}
}
