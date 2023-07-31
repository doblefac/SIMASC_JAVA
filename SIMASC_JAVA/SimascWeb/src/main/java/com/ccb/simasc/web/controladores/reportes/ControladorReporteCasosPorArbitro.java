package com.ccb.simasc.web.controladores.reportes;

import static com.ccb.simasc.transversal.utilidades.UtilReflection.obtenerValorLlaves;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.arbitraje.FuncionarioExternoFacade;
import com.ccb.simasc.negocio.arbitraje.GeneradorListasDeValores;
import com.ccb.simasc.negocio.arbitraje.MateriaFacade;
import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.negocio.transversal.FachadaDominios;
import com.ccb.simasc.transversal.dto.ArbitroDTO;
import com.ccb.simasc.transversal.dto.DominioDTO;
import com.ccb.simasc.transversal.dto.MateriaDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasosPorArbitroDTO;
import com.ccb.simasc.transversal.entidades.DominioPK;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

@ManagedBean(name = "controladorReporteCasosPorArbitro")
@ViewScoped
public class ControladorReporteCasosPorArbitro implements Serializable {
	private static final Logger logger = LogManager.getLogger(ControladorReporteCasosPorArbitro.class.getName());
	@EJB
	FuncionarioExternoFacade funcionarioExternofacade;

	@EJB
	FachadaDominios dominioFacade;

	@EJB
	ReporteFacade reporteFacade;

	@EJB
	MateriaFacade materiaFacade;
	
	@EJB
	private GeneradorListasDeValores generadorListas;

	private static final long serialVersionUID = 1L;	

	private Date fechaIni;
	private Date fechaFin;
	private List<ArbitroDTO> arbitrosDTOs;
	private List<DominioDTO> pronunciamientosDTOs;
	private List<MateriaDTO> materiaDTOs;	
	private List<DominioDTO> tipoNombramientoDTOs;

	private String arbitroSeleccionado;
	private String pronunciamientoSeleccionado;
	private String materiaSeleccionada;
	private String tipoNombramientoSeccionado;

	private List<ReporteCasosPorArbitroDTO> listaReporte;

	private boolean exportarBloqueado;
	private String nombreReporte;
	private String filasPaginador;
	
	private int totalArbitros=0;
	private boolean limpiar = true;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			listaReporte = new ArrayList<ReporteCasosPorArbitroDTO>();
			totalArbitros = 0;
			exportarBloqueado = true;
		}
		limpiar = true;
	}

	@PostConstruct
	public void postConstruct(){

		arbitrosDTOs = new ArrayList<ArbitroDTO>();
		pronunciamientosDTOs = new ArrayList<DominioDTO>();
		materiaDTOs = new ArrayList<MateriaDTO>();
		tipoNombramientoDTOs = new ArrayList<DominioDTO>();	
		listaReporte = new ArrayList<ReporteCasosPorArbitroDTO>();
		exportarBloqueado = true;

		// ***************************************************************************************************
		// ***************** El siguiente dato debe ser un parametro *****************************************
		// ***************************************************************************************************
		filasPaginador = "20";
		limpiarFiltros();
		cargarListas();

	}

	@PreDestroy
	public void preDestroy(){

		arbitrosDTOs = new ArrayList<ArbitroDTO>();
		pronunciamientosDTOs = new ArrayList<DominioDTO>();
		materiaDTOs = new ArrayList<MateriaDTO>();
		tipoNombramientoDTOs = new ArrayList<DominioDTO>();	
		listaReporte = new ArrayList<ReporteCasosPorArbitroDTO>();
		exportarBloqueado = true;

	}

	public void cargarListas(){

		arbitrosDTOs = generadorListas.consultarArbitrosDTO(); 
		pronunciamientosDTOs = dominioFacade.getDominiosDTO(UtilDominios.DOMINIO_TIPO_PRONUNCIAMIENTO);
		materiaDTOs = materiaFacade.getBusquedaMaterias();
		this.ordenarMaterias();
		tipoNombramientoDTOs = dominioFacade.getDominiosDTO(UtilDominios.METODOS_DE_NOMBRAMIENTO);


	}

	public void buscar(){
		this.limpiar = false;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String,Object> filtros= new HashMap<>();
		
		try {
			filtros.put("arbitro", arbitroSeleccionado);
			if(!pronunciamientoSeleccionado.equals("0")){
				Map<String, String> llaves;
				llaves = obtenerValorLlaves(DominioPK.class, pronunciamientoSeleccionado);
				filtros.put("pronunciamientoSeleccionado", llaves.get("codigo"));
			}
			if(!tipoNombramientoSeccionado.equals("0")){	
				Map<String, String> llaves;
				llaves = obtenerValorLlaves(DominioPK.class, tipoNombramientoSeccionado);
				filtros.put("tipoNombramientoSeccionado", llaves.get("codigo"));
			}
		} catch (ClassNotFoundException e) {
			listaReporte = new ArrayList<ReporteCasosPorArbitroDTO>();
			totalArbitros = 0;
			logger.error("Error: ", e);
		}
		filtros.put("materiaSeleccionada", materiaSeleccionada);
		filtros.put("fechaIni", fechaIni);
		filtros.put("fechaFin", fechaFin);


		if (validarCampos()){
			listaReporte = reporteFacade.getReporteCasosPorArbitro(filtros);
			exportarBloqueado = listaReporte.isEmpty();
			if(listaReporte.isEmpty()){
				facesContext.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA,
								 MensajesSimasc.getInstancia().getMensajePorKey(
											MensajesEnum.INFO035.toString())));
			}

		}else{
			listaReporte = new ArrayList<ReporteCasosPorArbitroDTO>();
			totalArbitros = 0;		
			exportarBloqueado = true;
		}
		setTotalArbitros(listaReporte.size());
		limpiarFiltros();
	}

	public String[] obtenerLlaves(){
		arbitroSeleccionado = arbitroSeleccionado.replace("tipoDocumento", "");
		arbitroSeleccionado = arbitroSeleccionado.replace("numeroDocumento", "");
		arbitroSeleccionado = arbitroSeleccionado.replace(" ", "");
		return arbitroSeleccionado.split(",");	
	}

	public void exportarExcel(){
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String date = sdf.format(new Date());
		nombreReporte = "Reporte_casos_por_arbitros" + date;
	}


	public boolean validarCampos(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if(fechaIni==null || fechaFin==null){
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, MensajesSimasc.getInstancia().getMensajePorKey(
					MensajesEnum.INFO036.toString())));
			return false;
		}
		if(fechaFin != null && fechaIni != null ){ 
			if(fechaIni.after(fechaFin)){
				limpiarFiltros();
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA,  MensajesSimasc.getInstancia().getMensajePorKey(
						MensajesEnum.INFO037.toString())));

				return false;
			}
		}
		return true;
	}

	public void limpiarFiltros(){

		pronunciamientoSeleccionado=materiaSeleccionada=arbitroSeleccionado=tipoNombramientoSeccionado=null;
		fechaIni=fechaFin=null;

	}
	
	/**
	 * Realiza el ordenamiento de la lista de materias
	 */
	private void ordenarMaterias() {
		if (materiaDTOs != null && materiaDTOs.size() > 0) {
			Collections.sort(materiaDTOs, new Comparator<MateriaDTO>() {
		        @Override
		        public int compare(MateriaDTO m1, MateriaDTO m2) {
		            return  m1.getNombre().compareTo(m2.getNombre());
		        }
		    });				
		}			
	}


	public Date getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public String getArbitroSeleccionado() {
		return arbitroSeleccionado;
	}


	public void setArbitroSeleccionado(String arbitroSeleccionado) {
		this.arbitroSeleccionado = arbitroSeleccionado;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public List<ReporteCasosPorArbitroDTO> getListaReporte() {
		return listaReporte;
	}


	public void setListaReporte(List<ReporteCasosPorArbitroDTO> listaReporte) {
		this.listaReporte = listaReporte;
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


	public List<DominioDTO> getPronunciamientosDTOs() {
		return pronunciamientosDTOs;
	}


	public void setPronunciamientosDTOs(List<DominioDTO> pronunciamientosDTOs) {
		this.pronunciamientosDTOs = pronunciamientosDTOs;
	}


	public List<MateriaDTO> getMateriaDTOs() {
		return materiaDTOs;
	}


	public void setMateriaDTOs(List<MateriaDTO> materiaDTOs) {
		this.materiaDTOs = materiaDTOs;
	}


	public List<DominioDTO> getTipoNombramientoDTOs() {
		return tipoNombramientoDTOs;
	}


	public void setTipoNombramientoDTOs(List<DominioDTO> tipoNombramientoDTOs) {
		this.tipoNombramientoDTOs = tipoNombramientoDTOs;
	}


	public String getPronunciamientoSeleccionado() {
		return pronunciamientoSeleccionado;
	}


	public void setPronunciamientoSeleccionado(String pronunciamientoSeleccionado) {
		this.pronunciamientoSeleccionado = pronunciamientoSeleccionado;
	}


	public String getMateriaSeleccionada() {
		return materiaSeleccionada;
	}


	public void setMateriaSeleccionada(String materiaSeleccionada) {
		this.materiaSeleccionada = materiaSeleccionada;
	}


	public String getTipoNombramientoSeccionado() {
		return tipoNombramientoSeccionado;
	}


	public void setTipoNombramientoSeccionado(String tipoNombramientoSeccionado) {
		this.tipoNombramientoSeccionado = tipoNombramientoSeccionado;
	}


	public List<ArbitroDTO> getArbitrosDTOs() {
		return arbitrosDTOs;
	}


	public void setArbitrosDTOs(List<ArbitroDTO> arbitrosDTOs) {
		this.arbitrosDTOs = arbitrosDTOs;
	}

	public int getTotalArbitros() {
		return totalArbitros;
	}

	public void setTotalArbitros(int totalArbitros) {
		this.totalArbitros = totalArbitros;
	}





}
