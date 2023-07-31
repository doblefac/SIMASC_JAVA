package com.ccb.simasc.web.controladores.reportes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.ccb.simasc.transversal.dto.MateriaDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasosCerradosDTO;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;


/**
 * Clase controladora para generar el reporte de casos cerrados
 * @author aalvarez
 *
 */
@ManagedBean(name = "controladorReporteCasosCerradosView")
@ViewScoped
public class ControladorReporteCasosCerrados extends ControladorMaterias implements Serializable{


	@EJB
	MateriaFacade materiaFacade;

	@EJB
	FachadaDominios dominioFacade;

	@EJB
	ReporteFacade reporteFacade;

	private static final long serialVersionUID = 1L;
	
	private List<ReporteCasosCerradosDTO> reporteCasosCerradosDTOs;	
	private List<DominioDTO> motivoCierreDTOs;
	private int totalCasos = 0;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			reporteCasosCerradosDTOs = new ArrayList<>();
			setTotalCasos(0);		
		}
	}


	@PostConstruct
	public void postConstruct() {
		limpiarCampos();
		nombreReporte = UtilSimasc.generarNombreReporte("Reporte_casos_cerrados");
		cargarListas();
	}

	@PreDestroy
	public void preDestroy() {
		limpiarCampos();
	}
	
	public void limpiarCampos() {
		reporteCasosCerradosDTOs = new ArrayList<>();
		motivoCierreDTOs = new ArrayList<>();
	}

	/**
	 * Metodo encargado de cargar las listas de filtrado
	 */
	public void cargarListas() {
		
		materiaDTOs = materiaFacade.getBusquedaMaterias();
		motivoCierre = dominioFacade.getDominios(UtilDominios.DOMINIO_MOTIVO_CIERRE);
		for (Dominio dominio : motivoCierre) {
			DominioDTO newDominio = new DominioDTO();
			newDominio.setNombre(dominio.getNombre());
			newDominio.setDominioPK(dominio.getDominioPK());
			newDominio.setDescripcion(dominio.getDescripcion());
			motivoCierreDTOs.add(newDominio);
		}
		
		Collections.sort(motivoCierreDTOs, new Comparator<DominioDTO>() {
			@Override
			public int compare(DominioDTO o1, DominioDTO o2){
				return o1.getNombre().compareTo(o2.getNombre());
			}
		});
		
		Collections.sort(materiaDTOs, new Comparator<MateriaDTO>() {
			@Override
			public int compare(MateriaDTO o1, MateriaDTO o2){
				return o1.getNombre().compareTo(o2.getNombre());
			}
		});

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
			if (materiaSeleccionada != null && !materiaSeleccionada.equals("")) {
				filtrosBusquedaReporte.put("materia", materiaSeleccionada);
			}
			if (motivoSeleccionado != null && !motivoSeleccionado.equals("")) {
				String[] parts = motivoSeleccionado.split(",");
				codigo = parts[0].substring(6); 
				dominio = parts[1].substring(7); 
				filtrosBusquedaReporte.put("codigo", codigo);
				filtrosBusquedaReporte.put("dominio", dominio);
			}
			if (fechaInicialSeleccionada != null) {
				filtrosBusquedaReporte.put("fechaInicial", fechaInicialSeleccionada);
			}
			if (fechaFinSeleccionada != null) {
				filtrosBusquedaReporte.put("fechaFinal", fechaFinSeleccionada);
			}
			limpiarFiltros();
			reporteCasosCerradosDTOs = reporteFacade.getReporteCasosCerrados(filtrosBusquedaReporte);
			if (reporteCasosCerradosDTOs.size() != 0) {
				visible = false;
			} else {
				
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString())));

			}
			setTotalCasos(reporteCasosCerradosDTOs.size());
		} catch (Exception e) {
			totalCasos = 0;
			limpiarFiltros();
			visible = true;
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, e.getMessage()));
		}

	}

	/**
	 * Metodo encargado de limpiar los filtros de busqueda
	 */
	public void limpiarFiltros() {
		motivoSeleccionado = null;
		materiaSeleccionada = null;
		fechaInicialSeleccionada = null;
		fechaFinSeleccionada = null;
		reporteCasosCerradosDTOs = new ArrayList<ReporteCasosCerradosDTO>();
		

	}

	/**
	 * Metodo encargado de validar los campos del formulario
	 */
	public void validarCampos() throws Exception {
		StringBuilder campo=new StringBuilder();
		if (fechaInicialSeleccionada == null) {
			campo.append("Fecha Inicial ");
		}
		if (fechaFinSeleccionada == null) {
			campo.append("Fecha Final ");
		}
		if (fechaInicialSeleccionada == null || fechaFinSeleccionada == null) {
			reporteCasosCerradosDTOs.clear();
			setTotalCasos(0);
			visible = true;
			throw new Exception( MensajesSimasc.getInstancia().getMensajePorKey(
					MensajesEnum.INFO032.toString())+campo);
			
		}
		if (fechaInicialSeleccionada != null && fechaFinSeleccionada != null) {
			if (fechaInicialSeleccionada.after(fechaFinSeleccionada)) {				
				reporteCasosCerradosDTOs.clear();
				setTotalCasos(0);
				visible = true;
				throw new Exception( MensajesSimasc.getInstancia().getMensajePorKey(
						MensajesEnum.INFO037.toString()));
			}
		}
		
	}

	public List<ReporteCasosCerradosDTO> getReporteCasosCerradosDTOs() {
		return reporteCasosCerradosDTOs;
	}
	public void setReporteCasosCerradosDTOs(List<ReporteCasosCerradosDTO> reporteCasosCerradosDTOs) {
		this.reporteCasosCerradosDTOs = reporteCasosCerradosDTOs;
	}
	public List<DominioDTO> getMotivoCierreDTOs() {
		return motivoCierreDTOs;
	}
	public void setMotivoCierreDTOs(List<DominioDTO> motivoCierreDTOs) {
		this.motivoCierreDTOs = motivoCierreDTOs;
	}
	public int getTotalCasos() {
		return totalCasos;
	}
	public void setTotalCasos(int totalCasos) {
		this.totalCasos = totalCasos;
	}

}
