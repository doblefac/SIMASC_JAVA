package com.ccb.simasc.web.controladores.reportes;

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

import com.ccb.simasc.negocio.arbitraje.FuncionarioExternoFacade;
import com.ccb.simasc.negocio.arbitraje.MateriaFacade;
import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.negocio.arbitraje.SedeFacade;
import com.ccb.simasc.negocio.transversal.FachadaDominios;
import com.ccb.simasc.transversal.dto.DominioDTO;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteDigitalizacionDTO;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;


/**
 * Clase controladora para generar el reporte de digitalizacion
 * @author aalvarez
 *
 */
@ManagedBean(name = "controladorReporteDigitalizacionView")
@ViewScoped
public class ControladorReporteDigitalizacion extends ControladorReporte{

	@EJB
	FuncionarioExternoFacade funcionarioExternoFacade;

	@EJB
	MateriaFacade materiaFacade;

	@EJB
	FachadaDominios dominioFacade;

	@EJB
	SedeFacade sedeFacade;

	@EJB
	ReporteFacade reporteFacade;

	private List<PersonaDTO> funcionarioCcbDTOs;
	private List<ReporteDigitalizacionDTO> reporteDigitalizacionDTOs;	
	private List<Dominio> tipoDoc;
	private List<DominioDTO> tipoDocDTOs;
	private List<DominioDTO> estadosDigitalizacion;
	private String funcionarioSeleccionado;
	private String tipoDocSeleccionado;
	private String estadoSeleccionado;
	private String idCaso;
	private int totalArbitros=0;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			reporteDigitalizacionDTOs = new ArrayList<>();
			totalArbitros =0;
			visible = true;
		}
		limpiar = true;
	}
		

	@PostConstruct
	public void postConstruct() {
		funcionarioCcbDTOs = new ArrayList<>();
		reporteDigitalizacionDTOs = new ArrayList<>();
		tipoDocDTOs = new ArrayList<DominioDTO>();
		estadosDigitalizacion = new ArrayList<>();
		filtrosBusquedaReporte = new HashMap<>();
		funcionarioSeleccionado=null;
		tipoDocSeleccionado=null;
		estadoSeleccionado=null;
		fechaInicialSeleccionada=null;
		fechaFinSeleccionada=null;
		idCaso = null;
		visible = true;
		nombreReporte = UtilSimasc.generarNombreReporte("Reporte_digitalizacion");
		cargarListas();
	}

	@PreDestroy
	public void preDestroy() {
		funcionarioCcbDTOs = new ArrayList<>();
		reporteDigitalizacionDTOs = new ArrayList<>();
		tipoDocDTOs = new ArrayList<>();
		estadosDigitalizacion = new ArrayList<>();
		filtrosBusquedaReporte = new HashMap<>();
		funcionarioSeleccionado=null;
		tipoDocSeleccionado=null;
		estadoSeleccionado=null;
		fechaInicialSeleccionada=null;
		fechaFinSeleccionada=null;
		idCaso = null;
		visible = true;
	}

	/**
	 * Metodo encargado de cargar las listas de filtrado
	 */
	public void cargarListas() {
		
		funcionarioCcbDTOs = funcionarioExternoFacade.getBusquedaFuncionarios(UtilDominios.ROL_PERSONA_DIGITALIZADOR);
		tipoDoc = dominioFacade.getDominios(UtilDominios.DOMINIO_TIPO_DOCUMENTO_DIG);
		for (Dominio dominio : tipoDoc) {
			DominioDTO newDominio = new DominioDTO();
			newDominio.setNombre(dominio.getNombre());
			newDominio.setDominioPK(dominio.getDominioPK());
			newDominio.setDescripcion(dominio.getDescripcion());
			tipoDocDTOs.add(newDominio);
		}
		
		List<Dominio> dominiosEstadoDigitalizacion = dominioFacade
				.getDominios(UtilDominios.DOMINIO_ESTADO_DIGITALIZACION);
		if (dominiosEstadoDigitalizacion != null && !dominiosEstadoDigitalizacion.isEmpty()) {
			for (Dominio dominio : dominiosEstadoDigitalizacion) {
				DominioDTO dominioDTO = new DominioDTO();
				dominioDTO.setDominioPK(dominio.getDominioPK());
				dominioDTO.setNombre(dominio.getNombre());
				dominioDTO.setDescripcion(dominio.getDescripcion());
				estadosDigitalizacion.add(dominioDTO);
			}
		}
	}

	/**
	 * Metodo encargado de consultar la informacion del reporte
	 */
	public void buscar() {
		FacesContext facesContext = FacesContext.getCurrentInstance();	
		this.limpiar = false;
		filtrosBusquedaReporte= new HashMap<>();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		
		try {
			validarCampos();
			if (funcionarioSeleccionado != null && !funcionarioSeleccionado.equals("")) {
				filtrosBusquedaReporte.put("idPersona", funcionarioSeleccionado);
			}
			if (tipoDocSeleccionado != null && !tipoDocSeleccionado.equals("")) {
				filtrosBusquedaReporte.put("codigo", tipoDocSeleccionado);
			}
			if (estadoSeleccionado != null && !estadoSeleccionado.equals("")) {
				filtrosBusquedaReporte.put("estado", estadoSeleccionado);
			}
			if (idCaso != null && !idCaso.equals("")) {
				filtrosBusquedaReporte.put("idCaso", idCaso);
			}
			if (fechaInicialSeleccionada != null) {				
				filtrosBusquedaReporte.put("fechaInicial", df.format(fechaInicialSeleccionada));
			}
			if (fechaFinSeleccionada != null) {
				filtrosBusquedaReporte.put("fechaFinal", df.format(fechaFinSeleccionada));
			}
			limpiarFiltros();
			
			reporteDigitalizacionDTOs = reporteFacade.getReporteDigitalizacion(filtrosBusquedaReporte);
			if (reporteDigitalizacionDTOs.size() != 0) {
				visible = false;
			} else {
				
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO035.toString())));

			}
			
			setTotalArbitros(reporteDigitalizacionDTOs.size());

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
		funcionarioSeleccionado = null;
		tipoDocSeleccionado = null;
		estadoSeleccionado = null;
		fechaInicialSeleccionada = null;
		fechaFinSeleccionada = null;
		idCaso = null;
		reporteDigitalizacionDTOs = new ArrayList<>();

	}

	/**
	 * Metodo encargado de validar los campos del formulario
	 */
	public void validarCampos() throws Exception {
		StringBuilder campo= new StringBuilder();
		
		if (fechaInicialSeleccionada == null) {
			campo.append("Fecha Inicial ");
		}
		if (fechaFinSeleccionada == null) {
			campo.append("Fecha Final ");
		}
		if ((fechaInicialSeleccionada == null || fechaFinSeleccionada == null)
				&& (idCaso == null || idCaso.equals(""))) {
			throw new Exception( MensajesSimasc.getInstancia().getMensajePorKey(
					MensajesEnum.INFO032.toString()) +campo);
		}
		if (fechaInicialSeleccionada != null && fechaFinSeleccionada != null) {
			if (fechaInicialSeleccionada.after(fechaFinSeleccionada)) {
				throw new Exception( MensajesSimasc.getInstancia().getMensajePorKey(
						MensajesEnum.INFO037.toString()));
			}
		}
	}

	public List<PersonaDTO> getFuncionarioCcbDTOs() {
		return funcionarioCcbDTOs;
	}
	public void setFuncionarioCcbDTOs(List<PersonaDTO> funcionarioCcbDTOs) {
		this.funcionarioCcbDTOs = funcionarioCcbDTOs;
	}
	public List<ReporteDigitalizacionDTO> getReporteDigitalizacionDTOs() {
		return reporteDigitalizacionDTOs;
	}
	public void setReporteDigitalizacionDTOs(List<ReporteDigitalizacionDTO> reporteDigitalizacionDTOs) {
		this.reporteDigitalizacionDTOs = reporteDigitalizacionDTOs;
	}
	public String getFuncionarioSeleccionado() {
		return funcionarioSeleccionado;
	}
	public void setFuncionarioSeleccionado(String funcionarioSeleccionado) {
		this.funcionarioSeleccionado = funcionarioSeleccionado;
	}
	public String getTipoDocSeleccionado() {
		return tipoDocSeleccionado;
	}
	public void setTipoDocSeleccionado(String tipoDocSeleccionado) {
		this.tipoDocSeleccionado = tipoDocSeleccionado;
	}
	public String getEstadoSeleccionado() {
		return estadoSeleccionado;
	}
	public void setEstadoSeleccionado(String estadoSeleccionado) {
		this.estadoSeleccionado = estadoSeleccionado;
	}
	public List<Dominio> getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(List<Dominio> tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public List<DominioDTO> getTipoDocDTOs() {
		return tipoDocDTOs;
	}
	public void setTipoDocDTOs(List<DominioDTO> tipoDocDTOs) {
		this.tipoDocDTOs = tipoDocDTOs;
	}
	public String getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(String idCaso) {
		this.idCaso = idCaso;
	}
	public int getTotalArbitros() {
		return totalArbitros;
	}
	public void setTotalArbitros(int totalArbitros) {
		this.totalArbitros = totalArbitros;
	}
	public List<DominioDTO> getEstadosDigitalizacion() {
		return estadosDigitalizacion;
	}
	public void setEstadosDigitalizacion(List<DominioDTO> estadosDigitalizacion) {
		this.estadosDigitalizacion = estadosDigitalizacion;
	}
}
