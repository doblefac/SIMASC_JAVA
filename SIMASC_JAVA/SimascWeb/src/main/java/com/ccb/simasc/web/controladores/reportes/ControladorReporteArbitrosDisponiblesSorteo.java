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
import javax.faces.event.ValueChangeEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.arbitraje.MateriaFacade;
import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.negocio.arbitraje.ServicioFacade;
import com.ccb.simasc.negocio.transversal.FachadaDominios;
import com.ccb.simasc.transversal.dto.DominioDTO;
import com.ccb.simasc.transversal.dto.MateriaDTO;
import com.ccb.simasc.transversal.dto.ServicioDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteArbitrosDispSorteoDTO;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;

/**
 * Clase controladora para generar el reporte de arbitros
 * disponibles para un sorteo
 * @author aalvarez
 *
 */
@ManagedBean(name = "controladorReporteArbitrosDisponiblesSorteo")
@ViewScoped
public class ControladorReporteArbitrosDisponiblesSorteo extends ControladorMaterias implements Serializable {

	
	@EJB
	MateriaFacade materiaFacade;

	@EJB
	FachadaDominios dominioFacade;

	
	@EJB
	ReporteFacade reporteFacade;
	
	@EJB
	ServicioFacade servicioFacade;

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(ControladorReporteArbitrosDisponiblesSorteo.class.getName());
	
	private String cuantiaSeleccionada;
	private String tipoCasoSeleccionado;
	private String tipoSorteoSeleccionado;
	private String codigoCaso;
	private boolean codigoVisible;
	private List<DominioDTO> tipoCuantiaDTOs;
	private List<Dominio> tipoCuantia;
	private boolean exportarBloqueado;
	private String nombreReporte;
	private String filasPaginador;
	private int totalArbitros = 0;
	private List<Servicio> tipoCaso;
	private List<ServicioDTO> tipoCasoDTOs;
	private List<DominioDTO> tipoSorteoDTOs;
	private List<ReporteArbitrosDispSorteoDTO> reporteArbitrosDisponiblesSorteoDTOs;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			reporteArbitrosDisponiblesSorteoDTOs = new ArrayList<ReporteArbitrosDispSorteoDTO>();
			totalArbitros = 0;
			limpiarFiltros();
			visible = true;
			codigoVisible = false;
		}
		limpiar = true;
	}

	@PostConstruct
	public void postConstruct() {
		
		tipoCuantia = new ArrayList<Dominio>();
		tipoCuantiaDTOs = new ArrayList<DominioDTO>();
		tipoCaso = new ArrayList<Servicio>();
		tipoCasoDTOs = new ArrayList<ServicioDTO>();
		materiaDTOs = new ArrayList<MateriaDTO>();
		filtrosBusquedaReporte = new HashMap<>();
		tipoCasoSeleccionado= null;
		tipoSorteoSeleccionado = null;
		cuantiaSeleccionada= null;
		materiaSeleccionada=null;
		exportarBloqueado=false;
		visible=false;
		reporteArbitrosDisponiblesSorteoDTOs = new ArrayList<ReporteArbitrosDispSorteoDTO>();
		nombreReporte = UtilSimasc.generarNombreReporte("Reporte_arbitros_disponibles_sorteo");
		totalArbitros = 0;
		visible = true;
		codigoCaso = null;
		codigoVisible = false;
		tipoSorteoDTOs = new ArrayList<DominioDTO>();
		cargarListas();
	}

	@PreDestroy
	public void preDestroy() {
		
		tipoCuantia = new ArrayList<Dominio>();
		tipoCuantiaDTOs = new ArrayList<DominioDTO>();
		tipoSorteoDTOs = new ArrayList<DominioDTO>();
		tipoCaso = new ArrayList<Servicio>();
		tipoCasoDTOs = new ArrayList<ServicioDTO>();
		materiaDTOs = new ArrayList<MateriaDTO>();
		filtrosBusquedaReporte = new HashMap<>();
		tipoCasoSeleccionado= null;
		tipoSorteoSeleccionado = null;
		cuantiaSeleccionada= null;
		materiaSeleccionada=null;
		exportarBloqueado=false;
		visible=false;
		reporteArbitrosDisponiblesSorteoDTOs = new ArrayList<ReporteArbitrosDispSorteoDTO>();
		totalArbitros = 0;
		visible = true;
		codigoCaso = null;
		codigoVisible = false;
	}
	
	/**
	 * Metodo encargado de cargar las listas de filtrado
	 */
	public void cargarListas() {
		tipoCasoDTOs = servicioFacade.getBusquedaServicios();
		tipoCuantia = dominioFacade.getDominios(UtilDominios.DOMINIO_TIPO_CUANTIA);		
		for (Dominio dominio : tipoCuantia) {
			DominioDTO newDominio = new DominioDTO();
			newDominio.setNombre(dominio.getNombre());
			newDominio.setDominioPK(dominio.getDominioPK());
			newDominio.setDescripcion(dominio.getDescripcion());
			tipoCuantiaDTOs.add(newDominio);
		}
	}
	
	public void changeServicio(ValueChangeEvent ev) {
		
		cargarMaterias(ev);
		
		try {
			if (ev.getNewValue() != null) {
				String idServicio = (String) ev.getNewValue();
				Long servicio = Long.parseLong(idServicio);
				if (idServicio != null) {
					tipoSorteoDTOs = dominioFacade.dominioPorServicio(servicio);
				}
			}
		} catch (Exception e) {
			logger.error("Error al cargar el tipo de sorteo ----> ", e.getMessage());
		}
	}

	/**
	 * Metodo encargado de consultar la informacion del reporte
	 */
	public void buscar() {
		this.limpiar = false;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		filtrosBusquedaReporte= new HashMap<>();
		reporteArbitrosDisponiblesSorteoDTOs = new ArrayList<ReporteArbitrosDispSorteoDTO>();
		totalArbitros = 0;
		String codigo = null;
		String dominio = null;
		try {			
			if (validarCampos()) {
				if (tipoCasoSeleccionado != null && !tipoCasoSeleccionado.equals("")) {						
					filtrosBusquedaReporte.put("tipoCaso", tipoCasoSeleccionado);
				}
				if (tipoSorteoSeleccionado != null && !tipoSorteoSeleccionado.equals("")) {	
					String[] parts = tipoSorteoSeleccionado.split(",");
					codigo = parts[0].substring(6); 
					filtrosBusquedaReporte.put("tipoSorteo", codigo);
				}
				if (cuantiaSeleccionada != null && !cuantiaSeleccionada.equals("")) {
					String[] parts = cuantiaSeleccionada.split(",");
					codigo = parts[0].substring(6); 
					dominio = parts[1].substring(7); 
					filtrosBusquedaReporte.put("codigo", codigo);
					filtrosBusquedaReporte.put("dominio", dominio);
				}
				if (materiaSeleccionada != null && !materiaSeleccionada.equals("")) {
					filtrosBusquedaReporte.put("materia", materiaSeleccionada);
				}
				if	(codigoCaso != null && !codigoCaso.equals("")) {
					filtrosBusquedaReporte.put("codigoCaso", codigoCaso);
				}
				//limpiarFiltros();
				reporteArbitrosDisponiblesSorteoDTOs = reporteFacade.getReporteArbitrosDisponiblesSorteo(
						filtrosBusquedaReporte);
				totalArbitros= reporteArbitrosDisponiblesSorteoDTOs.size();
				if (reporteArbitrosDisponiblesSorteoDTOs.size() != 0) {
					visible = false;
				} else {
					throw new Exception(MensajesSimasc.getInstancia().getMensajePorKey(
										MensajesEnum.INFO031.toString()));
				}				
			}			
		} catch (Exception e) {
			//limpiarFiltros();
			visible = true;
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
					UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO, e.getMessage()));
		}
	}

	/**
	 * Metodo encargado de limpiar los filtros de busqueda
	 */
	public void limpiarFiltros() {
		
		materiaSeleccionada = null;
		cuantiaSeleccionada = null;		
		tipoCasoSeleccionado = null;
		tipoSorteoSeleccionado = null;
	}

	/**
	 * Metodo encargado de validar los campos del formulario
	 */
	public boolean validarCampos()  {
		boolean valido = true;
		if ((tipoCasoSeleccionado == null || tipoCasoSeleccionado.equals(""))) {
			valido = false;
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA,
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO032.toString()) + MensajesSimasc.getInstancia().getMensajePorKey(
									MensajesEnum.INFO033.toString())));
			}
		if ((tipoSorteoSeleccionado == null || tipoSorteoSeleccionado.equals(""))) {
			valido = false;
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA,
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO032.toString()) + MensajesSimasc.getInstancia().getMensajePorKey(
									MensajesEnum.INFO033.toString())));
			}
		if(validaCodigoFiltro()) {
			this.codigoVisible = true;
		}else {
			this.codigoVisible = false;
		}
		
		return valido;
	}
	
	/**
	 * Metodo encargado de validar los campos del formulario
	 */
	
	public boolean validaCodigoFiltro() {
		boolean codigoValido = false;
		if((this.codigoCaso != null && !this.codigoCaso.equals(""))) {
			codigoValido = true;
		}			
		return codigoValido;
	}

	public List<DominioDTO> getTipoCuantiaDTOs() {
		return tipoCuantiaDTOs;
	}

	public void setTipoCuantiaDTOs(List<DominioDTO> tipoCuantiaDTOs) {
		this.tipoCuantiaDTOs = tipoCuantiaDTOs;
	}

	public List<Dominio> getTipoCuantia() {
		return tipoCuantia;
	}

	public void setTipoCuantia(List<Dominio> tipoCuantia) {
		this.tipoCuantia = tipoCuantia;
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

	public String getCuantiaSeleccionada() {
		return cuantiaSeleccionada;
	}

	public void setCuantiaSeleccionada(String cuantiaSeleccionada) {
		this.cuantiaSeleccionada = cuantiaSeleccionada;
	}

	public String getTipoCasoSeleccionado() {
		return tipoCasoSeleccionado;
	}

	public void setTipoCasoSeleccionado(String tipoCasoSeleccionado) {
		this.tipoCasoSeleccionado = tipoCasoSeleccionado;
	}

	public boolean isExportarBloqueado() {
		return exportarBloqueado;
	}

	public void setExportarBloqueado(boolean exportarBloqueado) {
		this.exportarBloqueado = exportarBloqueado;
	}

	public String getNombreReporte() {
		return nombreReporte;
	}

	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	public String getFilasPaginador() {
		return filasPaginador;
	}

	public void setFilasPaginador(String filasPaginador) {
		this.filasPaginador = filasPaginador;
	}

	public int getTotalArbitros() {
		return totalArbitros;
	}

	public void setTotalArbitros(int totalArbitros) {
		this.totalArbitros = totalArbitros;
	}
	public List<ReporteArbitrosDispSorteoDTO> getReporteArbitrosDisponiblesSorteoDTOs() {
		return reporteArbitrosDisponiblesSorteoDTOs;
	}

	public void setReporteArbitrosDisponiblesSorteoDTOs(
			List<ReporteArbitrosDispSorteoDTO> reporteArbitrosDisponiblesSorteoDTOs) {
		this.reporteArbitrosDisponiblesSorteoDTOs = reporteArbitrosDisponiblesSorteoDTOs;
	}

	public String getCodigoCaso() {
		return codigoCaso;
	}

	public void setCodigoCaso(String codigoCaso) {
		this.codigoCaso = codigoCaso;
	}

	public boolean isCodigoVisible() {
		return codigoVisible;
	}

	public void setCodigoVisible(boolean codigoVisible) {
		this.codigoVisible = codigoVisible;
	}

	public List<DominioDTO> getTipoSorteoDTOs() {
		return tipoSorteoDTOs;
	}

	public void setTipoSorteoDTOs(List<DominioDTO> tipoSorteoDTOs) {
		this.tipoSorteoDTOs = tipoSorteoDTOs;
	}

	public String getTipoSorteoSeleccionado() {
		return tipoSorteoSeleccionado;
	}

	public void setTipoSorteoSeleccionado(String tipoSorteoSeleccionado) {
		this.tipoSorteoSeleccionado = tipoSorteoSeleccionado;
	}
	
	
}
