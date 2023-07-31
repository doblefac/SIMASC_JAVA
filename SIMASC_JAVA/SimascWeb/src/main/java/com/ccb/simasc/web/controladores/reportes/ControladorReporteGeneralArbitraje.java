package com.ccb.simasc.web.controladores.reportes;

import java.io.Serializable;
import java.text.DateFormat;
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
import javax.faces.event.ValueChangeEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.SelectEvent;

import com.ccb.simasc.negocio.arbitraje.FuncionarioCcbFacade;
import com.ccb.simasc.negocio.arbitraje.FuncionarioExternoFacade;
import com.ccb.simasc.negocio.arbitraje.MateriaFacade;
import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.negocio.arbitraje.SedeFacade;
import com.ccb.simasc.negocio.arbitraje.ServicioFacade;
import com.ccb.simasc.negocio.transversal.FachadaDominios;
import com.ccb.simasc.transversal.dto.DominioDTO;
//import com.ccb.simasc.transversal.dto.FuncionarioCcbDTO;
//import com.ccb.simasc.transversal.dto.FuncionarioExternoDTO;
import com.ccb.simasc.transversal.dto.MateriaDTO;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.dto.SedeDTO;
import com.ccb.simasc.transversal.dto.ServicioDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteGeneralArbitrajeDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.entidades.ServicioMateria;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;

@ManagedBean(name = "controladorReportesView")
@ViewScoped
public class ControladorReporteGeneralArbitraje implements Serializable {

	@EJB
	FuncionarioExternoFacade funcionarioExternoFacade;

	@EJB
	FuncionarioCcbFacade funcionarioCcbFacade;

	@EJB
	MateriaFacade materiaFacade;

	@EJB
	SedeFacade sedeFacade;

	@EJB
	ReporteFacade reporteFacade;

	@EJB
	FachadaDominios dominioFacade;

	@EJB
	ServicioFacade servicioFacade;
	
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(ControladorReporteGeneralArbitraje.class.getName());

	private String fechaIni;
	private String fechaFin;
	private List<SedeDTO> sedeDTOs;
	private List<MateriaDTO> materiaDTOs;
	private List<PersonaDTO> funcionarioExternoDTOs;// abogado
	private List<PersonaDTO> funcionarioCcbDTOs;// Auxiliar
														// administrativo
	private List<DominioDTO> tipoCuantiaDTOs;
	private List<Dominio> tipoCuantia;
	private List<Dominio> etapaCaso;
	private List<Dominio> estadoCaso;
	private List<DominioDTO> estadoCasoDTOs;
	private List<DominioDTO> etapaCasoDTOs;
	private List<Servicio> tipoCaso;
	private List<ServicioDTO> tipoCasoDTOs;
	private List<ReporteGeneralArbitrajeDTO> reporteGeneralDTOs;
	private Map<String, Object> filtrosBusquedaReporte;
	private Caso caso;
	private String abogadoSeleccionado;
	private String auxiliarSeleccionado;
	private String materiaSeleccionada;
	private String cuantiaSeleccionada;
	private String radicacionVirtualSeleccionada;
	private String casoInactivoSeleccionado;
	private String etapaSeleccionada;
	private String sedeSeleccionada;
	private String tipoCasoSeleccionado;
	private Date fechaInicialSeleccionada;
	private Date fechaFinSeleccionada;
	private boolean visible;
	private String nombreReporte;
	private int totalArbitros =0;
	private boolean limpiar = true;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			reporteGeneralDTOs = new ArrayList<ReporteGeneralArbitrajeDTO>();
			totalArbitros = 0;
			visible = true;
			limpiarFiltros();
		}
		limpiar = true;
	}

	@PostConstruct
	public void postConstruct() {
		funcionarioExternoDTOs = new ArrayList<PersonaDTO>();
		funcionarioCcbDTOs = new ArrayList<PersonaDTO>();
		reporteGeneralDTOs = new ArrayList<ReporteGeneralArbitrajeDTO>();
		tipoCuantia = new ArrayList<Dominio>();
		tipoCuantiaDTOs = new ArrayList<DominioDTO>();
		etapaCasoDTOs = new ArrayList<DominioDTO>();
		tipoCaso = new ArrayList<Servicio>();
		setTipoCasoDTOs(new ArrayList<ServicioDTO>());
		sedeDTOs = new ArrayList<SedeDTO>();
		materiaDTOs = new ArrayList<MateriaDTO>();
		setEstadoCasoDTOs(new ArrayList<DominioDTO>());
		filtrosBusquedaReporte = new HashMap<>();
		visible = true;
		nombreReporte = UtilSimasc.generarNombreReporte("Reporte_general_arbitraje");
		cargarListas();
	}

	@PreDestroy
	public void preDestroy() {
		funcionarioExternoDTOs = new ArrayList<PersonaDTO>();
		funcionarioCcbDTOs = new ArrayList<PersonaDTO>();
		reporteGeneralDTOs = new ArrayList<ReporteGeneralArbitrajeDTO>();
		tipoCuantia = new ArrayList<Dominio>();
		setEstadoCasoDTOs(new ArrayList<DominioDTO>());
		tipoCuantiaDTOs = new ArrayList<DominioDTO>();
		etapaCasoDTOs = new ArrayList<DominioDTO>();
		tipoCaso = new ArrayList<Servicio>();
		materiaDTOs = new ArrayList<MateriaDTO>();
		setTipoCasoDTOs(new ArrayList<ServicioDTO>());
		sedeDTOs = new ArrayList<SedeDTO>();
		filtrosBusquedaReporte = new HashMap<>();
		visible = true;
	}
	
	public void cargarListas() {

		reporteGeneralDTOs = new ArrayList<ReporteGeneralArbitrajeDTO>();

		funcionarioExternoDTOs = funcionarioExternoFacade.getBusquedaFuncionarios(UtilDominios.ROL_PERSONA_ABOGADO);
		funcionarioCcbDTOs = funcionarioExternoFacade.getBusquedaFuncionarios(UtilDominios.ROL_PERSONA_AUXILIAR_ADM);
		etapaCaso = dominioFacade.getDominios(UtilDominios.DOMINIO_ETAPA_CASO);
		etapaCasoDTOs = toDominioDTO(etapaCaso);
		sedeDTOs = sedeFacade.getBusquedaSedes();
		tipoCuantia = dominioFacade.getDominios(UtilDominios.DOMINIO_TIPO_CUANTIA);
		estadoCaso = dominioFacade.getDominios(UtilDominios.DOMINIO_ESTADO_CASO);
		setEstadoCasoDTOs(toDominioDTO(estadoCaso));
		tipoCuantiaDTOs = toDominioDTO(tipoCuantia);
		materiaDTOs = materiaFacade.getBusquedaMaterias();
		this.ordenarMaterias();
		setTipoCasoDTOs(servicioFacade.getBusquedaServicios());
		this.ordenarServicios();		
	}
	
	/**
	 * Realiza el ordenamiento de la lista de materias
	 */
	private void ordenarServicios() {
		if (tipoCasoDTOs != null && tipoCasoDTOs.size() > 0) {
			Collections.sort(tipoCasoDTOs, new Comparator<ServicioDTO>() {
		        @Override
		        public int compare(ServicioDTO m1, ServicioDTO m2) {
		            return  m1.getNombre().compareTo(m2.getNombre());
		        }
		    });				
		}			
	}
	
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

	private List<DominioDTO> toDominioDTO(List<Dominio> listaEntidad) {
		List<DominioDTO> listaDTO = new ArrayList<>() ;
		for (Dominio dominio : listaEntidad) {
			DominioDTO newDominio = new DominioDTO();
			newDominio.setNombre(dominio.getNombre());
			newDominio.setDominioPK(dominio.getDominioPK());
			newDominio.setDescripcion(dominio.getDescripcion());
			listaDTO.add(newDominio);
		}
		return listaDTO;
	}

	public void buscar() {
		this.limpiar = false;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			validarCampos();
			filtrosBusquedaReporte = new HashMap<>();

			if (abogadoSeleccionado != null && !abogadoSeleccionado.equals("")) {
				filtrosBusquedaReporte.put("ka", abogadoSeleccionado);
			}
			if (auxiliarSeleccionado != null && !auxiliarSeleccionado.equals("")) {
				filtrosBusquedaReporte.put("kax", auxiliarSeleccionado);
			}
			if (casoInactivoSeleccionado != null && !casoInactivoSeleccionado.equals("")) {				
				filtrosBusquedaReporte.put("kc", casoInactivoSeleccionado);
			}
			if (cuantiaSeleccionada != null && !cuantiaSeleccionada.equals("")) {
				filtrosBusquedaReporte.put("kcu", cuantiaSeleccionada);
			}
			if (etapaSeleccionada != null && !etapaSeleccionada.equals("")) {
				filtrosBusquedaReporte.put("ke", etapaSeleccionada);
			}
			if (materiaSeleccionada != null && !materiaSeleccionada.equals("")) {
				filtrosBusquedaReporte.put("km", materiaSeleccionada);
			}
			if (radicacionVirtualSeleccionada != null && !radicacionVirtualSeleccionada.equals("")) {
				String tipoSede = UtilDominios.SEDE_FISICA;
				if(UtilConstantes.SI.equalsIgnoreCase(radicacionVirtualSeleccionada)) {
					tipoSede = UtilDominios.SEDE_VIRTUAL;
				}
				filtrosBusquedaReporte.put("kr", tipoSede);
			}
			if (sedeSeleccionada != null && !sedeSeleccionada.equals("")) {
				filtrosBusquedaReporte.put("kse", sedeSeleccionada);
			}
			if (tipoCasoSeleccionado != null && !tipoCasoSeleccionado.equals("")) {
				filtrosBusquedaReporte.put("kti", tipoCasoSeleccionado);
			}
			if (fechaInicialSeleccionada != null) {
				filtrosBusquedaReporte.put("kfi", fechaInicialSeleccionada);
			}
			if (fechaFinSeleccionada != null) {
				filtrosBusquedaReporte.put("kff", fechaFinSeleccionada);
			}
			reporteGeneralDTOs = reporteFacade.getReporteGeneralArbitraje(filtrosBusquedaReporte, fechaInicialSeleccionada, fechaFinSeleccionada);
//			limpiarFiltros();
			if (reporteGeneralDTOs.size() != 0) {
				visible = false;
			} else {
				reporteGeneralDTOs = new ArrayList<ReporteGeneralArbitrajeDTO>();
				totalArbitros = 0;
				visible = true;
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO035.toString())));

			}

			setTotalArbitros(reporteGeneralDTOs.size());
		} catch (Exception e) {
			logger.error(e);
//			limpiarFiltros();
			reporteGeneralDTOs = new ArrayList<ReporteGeneralArbitrajeDTO>();
			totalArbitros = 0;
			visible = true;
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA,
							e.getMessage()));

		}

	}

	public void limpiarFiltros() {
		abogadoSeleccionado = null;
		auxiliarSeleccionado = null;
		materiaSeleccionada = null;
		cuantiaSeleccionada = null;
		radicacionVirtualSeleccionada = null;
		casoInactivoSeleccionado = null;
		etapaSeleccionada = null;
		sedeSeleccionada = null;
		tipoCasoSeleccionado = null;
		fechaInicialSeleccionada = null;
		fechaFinSeleccionada = null;

	}

	public void validarCampos() throws Exception {
		if (fechaInicialSeleccionada != null && fechaFinSeleccionada != null) {
			if (fechaInicialSeleccionada.after(fechaFinSeleccionada)) {
//				limpiarFiltros();
				throw new Exception( MensajesSimasc.getInstancia().getMensajePorKey(
						MensajesEnum.INFO037.toString()));
			}
		}
		if (fechaInicialSeleccionada == null && fechaFinSeleccionada == null) {
			StringBuilder campos = new StringBuilder();
			if(fechaInicialSeleccionada == null){
				campos = campos.append(" Fecha Inicial");
			}
			if(fechaFinSeleccionada == null){
				campos = campos.append(" Fecha Final");
			}
			throw new Exception( MensajesSimasc.getInstancia().getMensajePorKey(
					MensajesEnum.INFO032.toString()) + campos.toString());
		}
	}

	/**
	 * Metodo encargado de cargar la lista de materias de acuerdo
	 * al tipo de servicio seleccionado
	 */
	public void cargarMaterias(ValueChangeEvent ev) {
		materiaDTOs = new ArrayList<MateriaDTO>();
		try {
			if (ev.getNewValue() != null) {
				String tipoCasoSeleccionado = (String) ev.getNewValue();
				if (tipoCasoSeleccionado != null) {
					materiaDTOs = materiaFacade.getBusquedaMateriasXServicio(ServicioMateria.ENTIDAD_SERVICIO_MATERIA_PK_ID_SERVICIO, tipoCasoSeleccionado);
				}
			}

		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public void onDateSelect(SelectEvent event) {
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date fecha = (Date) event.getObject();
		fechaIni = format.format(fecha);
	}

	public void onDateSelectF(SelectEvent event) {
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date fecha = (Date) event.getObject();
		fechaFin = format.format(fecha);
	}

	public String getAbogadoSeleccionado() {
		return abogadoSeleccionado;
	}

	public void setAbogadoSeleccionado(String abogadoSeleccionado) {
		this.abogadoSeleccionado = abogadoSeleccionado;
	}

	public List<SedeDTO> getSedeDTOs() {
		return sedeDTOs;
	}

	public void setSedeDTOs(List<SedeDTO> sedeDTOs) {
		this.sedeDTOs = sedeDTOs;
	}

	public List<MateriaDTO> getMateriaDTOs() {
		return materiaDTOs;
	}

	public void setMateriaDTOs(List<MateriaDTO> materiaDTOs) {
		this.materiaDTOs = materiaDTOs;
	}

	public List<PersonaDTO> getFuncionarioExternoDTOs() {
		return funcionarioExternoDTOs;
	}

	public void setFuncionarioExternoDTOs(List<PersonaDTO> funcionarioExternoDTOs) {
		this.funcionarioExternoDTOs = funcionarioExternoDTOs;
	}

	public List<PersonaDTO> getFuncionarioCcbDTOs() {
		return funcionarioCcbDTOs;
	}

	public void setFuncionarioCcbDTOs(List<PersonaDTO> funcionarioCcbDTOs) {
		this.funcionarioCcbDTOs = funcionarioCcbDTOs;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getRadicacionVirtualSeleccionada() {
		return radicacionVirtualSeleccionada;
	}

	public void setRadicacionVirtualSeleccionada(String radicacionVirtualSeleccionada) {
		this.radicacionVirtualSeleccionada = radicacionVirtualSeleccionada;
	}

	public String getCasoInactivoSeleccionado() {
		return casoInactivoSeleccionado;
	}

	public void setCasoInactivoSeleccionado(String casoInactivoSeleccionado) {
		this.casoInactivoSeleccionado = casoInactivoSeleccionado;
	}

	public List<DominioDTO> getEtapaCasoDTOs() {
		return etapaCasoDTOs;
	}

	public void setEtapaCasoDTOs(List<DominioDTO> etapaCasoDTOs) {
		this.etapaCasoDTOs = etapaCasoDTOs;
	}

	public Caso getCaso() {
		return caso;
	}

	public void setCaso(Caso caso) {
		this.caso = caso;
	}

	public String getAuxiliarSeleccionado() {
		return auxiliarSeleccionado;
	}

	public void setAuxiliarSeleccionado(String auxiliarSeleccionado) {
		this.auxiliarSeleccionado = auxiliarSeleccionado;
	}

	public String getMateriaSeleccionada() {
		return materiaSeleccionada;
	}

	public void setMateriaSeleccionada(String materiaSeleccionada) {
		this.materiaSeleccionada = materiaSeleccionada;
	}

	public String getCuantiaSeleccionada() {
		return cuantiaSeleccionada;
	}

	public void setCuantiaSeleccionada(String cuantiaSeleccionada) {
		this.cuantiaSeleccionada = cuantiaSeleccionada;
	}

	public String getEtapaSeleccionada() {
		return etapaSeleccionada;
	}

	public void setEtapaSeleccionada(String etapaSeleccionada) {
		this.etapaSeleccionada = etapaSeleccionada;
	}

	public String getSedeSeleccionada() {
		return sedeSeleccionada;
	}

	public void setSedeSeleccionada(String sedeSeleccionada) {
		this.sedeSeleccionada = sedeSeleccionada;
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

	public String getTipoCasoSeleccionado() {
		return tipoCasoSeleccionado;
	}

	public void setTipoCasoSeleccionado(String tipoCasoSeleccionado) {
		this.tipoCasoSeleccionado = tipoCasoSeleccionado;
	}

	public Map<String, Object> getFiltrosBusquedaReporte() {
		return filtrosBusquedaReporte;
	}

	public void setFiltrosBusquedaReporte(Map<String, Object> filtrosBusquedaReporte) {
		this.filtrosBusquedaReporte = filtrosBusquedaReporte;
	}

	public List<ReporteGeneralArbitrajeDTO> getReporteGeneralDTOs() {
		return reporteGeneralDTOs;
	}

	public void setReporteGeneralDTOs(List<ReporteGeneralArbitrajeDTO> reporteGeneralDTOs) {
		this.reporteGeneralDTOs = reporteGeneralDTOs;
	}

	public String getNombreReporte() {
		return nombreReporte;
	}

	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
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

	public int getTotalArbitros() {
		return totalArbitros;
	}

	public void setTotalArbitros(int totalArbitros) {
		this.totalArbitros = totalArbitros;
	}

	public List<Dominio> getEstadoCaso() {
		return estadoCaso;
	}

	public void setEstadoCaso(List<Dominio> estadoCaso) {
		this.estadoCaso = estadoCaso;
	}

	public List<DominioDTO> getEstadoCasoDTOs() {
		return estadoCasoDTOs;
	}

	public void setEstadoCasoDTOs(List<DominioDTO> estadoCasoDTOs) {
		this.estadoCasoDTOs = estadoCasoDTOs;
	}

}
