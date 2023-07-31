package com.ccb.simasc.web.controladores.reportes;

import static com.ccb.simasc.transversal.utilidades.UtilReflection.obtenerValorLlaves;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.ccb.simasc.negocio.arbitraje.FuncionarioExternoFacade;
import com.ccb.simasc.negocio.arbitraje.GeneradorListasDeValores;
import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.negocio.transversal.FachadaDominios;
import com.ccb.simasc.transversal.dto.ArbitroDTO;
import com.ccb.simasc.transversal.dto.DominioDTO;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteAudienciaDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.DominioPK;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

@ManagedBean(name = "controladorReporteAudiencias")
@ViewScoped
public class ControladorReporteAudiencias implements Serializable {

	@EJB
	FuncionarioExternoFacade funcionarioExternofacade;

	@EJB
	FachadaDominios dominioFacade;

	@EJB
	ReporteFacade reporteFacade;
	
	@EJB
	private GeneradorListasDeValores generadorListas;

	private static final long serialVersionUID = 1L;	

	private String codigoCaso;
	private String nombreCaso;
	private Date fechaIni;
	private Date fechaFin;
	private List<PersonaDTO> abogadosDTOs;
	private List<PersonaDTO> secretariosDTOs;
	private List<DominioDTO> tipoAudienciasDTOs;
	private List<ArbitroDTO> arbitrosDTOs;
	private List<DominioDTO> estadoAudienciaDTOs;

	private String abogadoSeleccionado;
	private String secretarioSeleccionado;
	private String tipoAudienciaSeleccionada;
	private String arbitroSeleccionado;
	private String estadoSeccionado;

	private Caso caso;

	private List<ReporteAudienciaDTO> listaReporte;

	private boolean obligatorioFechas;
	private boolean exportarBloqueado;
	private String nombreReporte;
	private String filasPaginador;
	private boolean limpiar = true;
	private int total;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			listaReporte = new ArrayList<ReporteAudienciaDTO>();
			total= 0;
			exportarBloqueado = true;
		}
		limpiar = true;
	}

	@PostConstruct
	public void postConstruct(){
		fechaIni = null;
		fechaFin = null;
		codigoCaso = null;
		nombreCaso = null;
		abogadosDTOs = new ArrayList<PersonaDTO>();
		secretariosDTOs = new ArrayList<PersonaDTO>();
		tipoAudienciasDTOs = new ArrayList<DominioDTO>();
		arbitrosDTOs = new ArrayList<ArbitroDTO>();
		estadoAudienciaDTOs = new ArrayList<DominioDTO>();
		listaReporte = new ArrayList<ReporteAudienciaDTO>();
		obligatorioFechas =true;
		exportarBloqueado = true;
		// ***************************************************************************************************
		// ***************** El siguiente dato debe ser un parametro *****************************************
		// ***************************************************************************************************
		filasPaginador = "20";

		cargarListas();


	}

	@PreDestroy
	public void preDestroy(){
		fechaIni = null;
		fechaFin = null;
		codigoCaso = null;
		nombreCaso = null;
		abogadosDTOs = new ArrayList<PersonaDTO>();
		secretariosDTOs = new ArrayList<PersonaDTO>();
		tipoAudienciasDTOs = new ArrayList<DominioDTO>();
		arbitrosDTOs = new ArrayList<ArbitroDTO>();
		estadoAudienciaDTOs = new ArrayList<DominioDTO>();
		listaReporte = new ArrayList<ReporteAudienciaDTO>();
		obligatorioFechas =true;
		exportarBloqueado = true;
		// ***************************************************************************************************
		// ***************** El siguiente dato debe ser un parametro *****************************************
		// ***************************************************************************************************
		filasPaginador = "20";
	}

	public void cargarListas(){
		abogadosDTOs = funcionarioExternofacade.getBusquedaFuncionarios(UtilDominios.ROL_PERSONA_ABOGADO);
		secretariosDTOs = funcionarioExternofacade.getBusquedaFuncionarios(UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
		tipoAudienciasDTOs = dominioFacade.getDominiosDTO(UtilDominios.DOMINIO_TIPO_AUDIENCIA);
		
//		arbitrosDTOs = funcionarioExternofacade.getBusquedaFuncionarios(UtilDominios.ROL_PERSONA_ARBITRO);
		arbitrosDTOs = generadorListas.consultarArbitrosDTO();
		
		estadoAudienciaDTOs = dominioFacade.getDominiosDTO(UtilDominios.DOMINIO_ESTADO_AUDIENCIA);


	}

	public void buscar(){
		this.limpiar = false;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String,Object> filtros= new HashMap<>();
		listaReporte = new ArrayList<ReporteAudienciaDTO>();

		if (codigoCaso !=null){
			filtros.put("codigoCaso", codigoCaso);
		}
		if (nombreCaso !=null){
			filtros.put("nombreCaso", nombreCaso);
		}
		if (fechaIni !=null){
			filtros.put("fechaIni", fechaIni);
		}
		if (fechaFin !=null){
			filtros.put("fechaFin", fechaFin);
		}
		try {
			filtros.put("abogado", abogadoSeleccionado);
			filtros.put("secretario", secretarioSeleccionado);
			filtros.put("arbitro", arbitroSeleccionado);

			if(!estadoSeccionado.equals("0")){
				Map<String,String> llavesEstado = obtenerValorLlaves(DominioPK.class, estadoSeccionado);
				filtros.put("estado", llavesEstado.get("codigo"));
			}

			if(!tipoAudienciaSeleccionada.equals("0")){
				Map<String,String> llavesTipoAudiencia = obtenerValorLlaves(DominioPK.class, tipoAudienciaSeleccionada);
				filtros.put("tipoAudiencia", llavesTipoAudiencia.get("codigo"));
			}
		} catch (ClassNotFoundException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,e.getMessage(), ""));
		}

		if (validarCampos()){
			// listaReporte = reporteFacade.getDatosReporteAudiencia(filtros);
			listaReporte = reporteFacade.getDatosReporteAudienciaNuevo(filtros);
			exportarBloqueado = listaReporte.isEmpty();
			if(listaReporte.isEmpty()){
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO, MensajesSimasc.getInstancia().getMensajePorKey(
					MensajesEnum.INFO035.toString())));
			}
		}else{
			exportarBloqueado = true;
		}
		total = listaReporte.size();
		limpiarFiltros();
		obligatorioFechas = true;
	}

	public void exportarExcel(){
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String date = sdf.format(new Date());
		nombreReporte = "Reporte_audiencias" + date;
	}

	public boolean cambiarCodigoCaso(){
		obligatorioFechas = codigoCaso.equals("");
		return false;
	}


	public boolean validarCampos(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if((fechaIni==null || fechaFin==null) && obligatorioFechas){
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, MensajesSimasc.getInstancia().getMensajePorKey(
					MensajesEnum.INFO036.toString())));
			return false;
		}
		if(fechaFin != null && fechaIni != null ){ 
			if(fechaIni.after(fechaFin)){
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, MensajesSimasc.getInstancia().getMensajePorKey(
					MensajesEnum.INFO037.toString())));
				return false;
			}
		}
		return true;
	}

	public void limpiarFiltros(){

		codigoCaso=null;
		nombreCaso=abogadoSeleccionado=secretarioSeleccionado=tipoAudienciaSeleccionada=arbitroSeleccionado=estadoSeccionado=null;
		fechaIni=fechaFin=null;

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


	public List<PersonaDTO> getAbogadosDTOs() {
		return abogadosDTOs;
	}


	public void setAbogadosDTOs(List<PersonaDTO> abogadosDTOs) {
		this.abogadosDTOs = abogadosDTOs;
	}


	public List<PersonaDTO> getSecretariosDTOs() {
		return secretariosDTOs;
	}


	public void setSecretariosDTOs(List<PersonaDTO> secretariosDTOs) {
		this.secretariosDTOs = secretariosDTOs;
	}


	public List<DominioDTO> getTipoAudienciasDTOs() {
		return tipoAudienciasDTOs;
	}


	public void setTipoAudienciasDTOs(List<DominioDTO> tipoAudienciasDTOs) {
		this.tipoAudienciasDTOs = tipoAudienciasDTOs;
	}


	public List<ArbitroDTO> getArbitrosDTOs() {
		return arbitrosDTOs;
	}


	public void setArbitrosDTOs(List<ArbitroDTO> arbitrosDTOs) {
		this.arbitrosDTOs = arbitrosDTOs;
	}


	public List<DominioDTO> getEstadoAudienciaDTOs() {
		return estadoAudienciaDTOs;
	}


	public void setEstadoAudienciaDTOs(List<DominioDTO> estadoAudienciaDTOs) {
		this.estadoAudienciaDTOs = estadoAudienciaDTOs;
	}


	public String getAbogadoSeleccionado() {
		return abogadoSeleccionado;
	}


	public void setAbogadoSeleccionado(String abogadoSeleccionado) {
		this.abogadoSeleccionado = abogadoSeleccionado;
	}


	public String getSecretarioSeleccionado() {
		return secretarioSeleccionado;
	}


	public void setSecretarioSeleccionado(String secretarioSeleccionado) {
		this.secretarioSeleccionado = secretarioSeleccionado;
	}


	public String getTipoAudienciaSeleccionada() {
		return tipoAudienciaSeleccionada;
	}


	public void setTipoAudienciaSeleccionada(String tipoAudienciaSeleccionada) {
		this.tipoAudienciaSeleccionada = tipoAudienciaSeleccionada;
	}


	public String getArbitroSeleccionado() {
		return arbitroSeleccionado;
	}


	public void setArbitroSeleccionado(String arbitroSeleccionado) {
		this.arbitroSeleccionado = arbitroSeleccionado;
	}


	public String getEstadoSeccionado() {
		return estadoSeccionado;
	}


	public void setEstadoSeccionado(String estadoSeccionado) {
		this.estadoSeccionado = estadoSeccionado;
	}


	public Caso getCaso() {
		return caso;
	}


	public void setCaso(Caso caso) {
		this.caso = caso;
	}


	public List<ReporteAudienciaDTO> getListaReporte() {
		return listaReporte;
	}


	public void setListaReporte(List<ReporteAudienciaDTO> listaReporte) {
		this.listaReporte = listaReporte;
	}


	public boolean isObligatorioFechas() {
		return obligatorioFechas;
	}


	public void setObligatorioFechas(boolean obligatorioFechas) {
		this.obligatorioFechas = obligatorioFechas;
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}




}
