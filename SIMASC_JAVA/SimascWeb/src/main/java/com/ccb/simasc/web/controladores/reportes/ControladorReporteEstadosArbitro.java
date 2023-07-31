package com.ccb.simasc.web.controladores.reportes;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.negocio.arbitraje.GeneradorListasDeValores;
import com.ccb.simasc.negocio.arbitraje.ReporteFacade;
import com.ccb.simasc.transversal.dto.ArbitroDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteEstadosArbitroCasosArbitroDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteEstadosArbitroHistoricoEstadoDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteEstadosArbitroMateriaDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteEstadosArbitroRolDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;

@ManagedBean(name = "ctrlEstadosArbitro")
@ViewScoped
public class ControladorReporteEstadosArbitro extends ControladorMaterias  {
	
	
	@EJB
	private ReporteFacade reporteFacade;

	@EJB
	private GeneradorListasDeValores generadorListas;
	
	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;

	private List<ArbitroDTO> arbitros;
	private List<ReporteEstadosArbitroCasosArbitroDTO> casosArbitro;
	private List<ReporteEstadosArbitroHistoricoEstadoDTO> estadosArbitro;
	private List<ReporteEstadosArbitroMateriaDTO> materiasArbitro;
	private List<ReporteEstadosArbitroRolDTO> rolesArbitro;

	private boolean exportarBloqueado;
	private Long arbitroSeleccionado;
	private int totalCasos;
	private int totalEstados;
	private int totalMaterias;
	private int totalRoles;
	
	/**
	 * Valida si entra al reporte para limpiar la pantalla
	 */
	public void preRenderComp() {
		if (FacesContext.getCurrentInstance().getRenderResponse() && this.limpiar) {
			arbitroSeleccionado = (long)-1;
			estadosArbitro = new ArrayList<>();
			casosArbitro = new ArrayList<>();
			totalCasos = 0;
			totalEstados = 0;
			totalMaterias = 0;
			totalRoles = 0;
			exportarBloqueado = true;
		}
		limpiar = true;
	}

	@PostConstruct
	public void postConstruct() {

		arbitros = new ArrayList<>();
		casosArbitro = new ArrayList<ReporteEstadosArbitroCasosArbitroDTO>();
		estadosArbitro = new ArrayList<ReporteEstadosArbitroHistoricoEstadoDTO>();
		materiasArbitro = new ArrayList<ReporteEstadosArbitroMateriaDTO>();
		rolesArbitro = new ArrayList<ReporteEstadosArbitroRolDTO>();
		exportarBloqueado = true;	

		consultarArbitros();

	}

	@PreDestroy
	public void preDestroy() {

		arbitros = new ArrayList<>();
		casosArbitro = new ArrayList<>();
		estadosArbitro = new ArrayList<>();
		materiasArbitro = new ArrayList<ReporteEstadosArbitroMateriaDTO>();
		rolesArbitro = new ArrayList<ReporteEstadosArbitroRolDTO>();
		exportarBloqueado = true;	

	}

	/**
	 * Consulta las personas con el rol de arbitro
	 * 
	 */
	public void consultarArbitros() {
		arbitros = generadorListas.consultarOperadoresDTO();
	}

	/**
	 * Carga la información del reporte correspondiente al arbitro que se
	 * seleccionó
	 */
	public void alSeleccionarArbitro() {
		this.limpiar = false;
		if (arbitroSeleccionado != -1) {
			if (arbitroSeleccionado != null) {
				ArbitroDTO arbitro = buscarArbitro(arbitroSeleccionado);
				consultarHistoricoEstadosArbitro(arbitro);
				consultarCasosArbitro(arbitro);
				consultarMateriasArbitro(arbitro);
				consultarRolesArbitro(arbitro);
				validarConsultaRetornoValores();
			}
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,  MensajesSimasc.getInstancia().getMensajePorKey(
							MensajesEnum.INFO042.toString())));
		}
	}

	private ArbitroDTO buscarArbitro(Long idArbitro) {
		ArbitroDTO arbitroBuscado = null;
		for (ArbitroDTO arbitro : arbitros) {
			if (arbitro.getIdPersona().equals(idArbitro)) {
				arbitroBuscado = arbitro;
				break;
			}
		}

		return arbitroBuscado;
	}

	/**
	 * Valida si la consulta de estados y casos retorno un valor, en caso de que
	 * no lo haga despliega un mensaje informativo al usuario y bloquea el botón
	 * de exportación.
	 */
	private void validarConsultaRetornoValores() {
		if (estadosArbitro.isEmpty() && casosArbitro.isEmpty() && materiasArbitro.isEmpty() && rolesArbitro.isEmpty()) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString())));
		}
		exportarBloqueado = estadosArbitro.isEmpty() && casosArbitro.isEmpty() && materiasArbitro.isEmpty()
				&& rolesArbitro.isEmpty();

	}

	/**
	 * Consulta el historioco de estados en el tipo de servicio PJT
	 * 
	 * @param arbitro
	 *            El arbitro seleccionado
	 * @throws ParseException 
	 */
	private void consultarHistoricoEstadosArbitro(ArbitroDTO arbitro) {
		estadosArbitro = reporteFacade.consultarEstadosServicioMateriaArbitro(arbitro);
		setTotalEstados(estadosArbitro.size());
	}

	/**
	 * Consulta la informacion de los casos en los que se encuentra asignado el
	 * arbittro seleccionado.
	 * 
	 * @param arbitro
	 *            El arbitro seleccionado
	 */
	private void consultarCasosArbitro(ArbitroDTO arbitro) {
		casosArbitro = reporteFacade.consultarCasosArbitro(arbitro);
		setTotalCasos(casosArbitro.size());
	}
	
	private void consultarMateriasArbitro(ArbitroDTO arbitro) {
		materiasArbitro = reporteFacade.consultarMateriasArbitro(arbitro);
		setTotalMaterias(materiasArbitro.size());
	}
	
	private void consultarRolesArbitro(ArbitroDTO arbitro) {
		rolesArbitro = reporteFacade.consultarRolesArbitro(arbitro);
		setTotalRoles(rolesArbitro.size());
	}

	public void exportarExcel() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMyyyyhhmmss");
		String date = sdf.format(new Date());
		nombreReporte = "Reporte_estado_operador" + date;
	}

	/**
	 * Devuelve el valor de la cuantía del caso calculado a partir del salario
	 * mínimo
	 * 
	 * @param caso
	 * @return
	 */
	public BigDecimal getValorCuantiaCaso(Caso caso) {
		BigDecimal smlv = new BigDecimal(manejadorParametrosGenerales.buscar(UtilConstantes.SMLMV).getValorNumerico());
		return caso.getValorCuantia(smlv);
	}

	public boolean isExportarBloqueado() {
		return exportarBloqueado;
	}

	public void setExportarBloqueado(boolean exportarBloqueado) {
		this.exportarBloqueado = exportarBloqueado;
	}

	public String getNombreReporte() {
		return UtilSimasc.generarNombreReporte("Reporte_estados_arbitro");
	}
	

	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	public List<ArbitroDTO> getArbitros() {
		return arbitros;
	}

	public void setArbitros(List<ArbitroDTO> arbitros) {
		this.arbitros = arbitros;
	}

	public List<ReporteEstadosArbitroCasosArbitroDTO> getCasosArbitro() {
		return casosArbitro;
	}

	public void setCasosArbitro(List<ReporteEstadosArbitroCasosArbitroDTO> casosArbitro) {
		this.casosArbitro = casosArbitro;
	}

	public List<ReporteEstadosArbitroHistoricoEstadoDTO> getEstadosArbitro() {
		return estadosArbitro;
	}

	public void setEstadosArbitro(List<ReporteEstadosArbitroHistoricoEstadoDTO> estadosArbitro) {
		this.estadosArbitro = estadosArbitro;
	}

	public Long getArbitroSeleccionado() {
		return arbitroSeleccionado;
	}

	public void setArbitroSeleccionado(Long arbitroSeleccionado) {
		this.arbitroSeleccionado = arbitroSeleccionado;
	}

	public int getTotalCasos() {
		return totalCasos;
	}

	public void setTotalCasos(int totalCasos) {
		this.totalCasos = totalCasos;
	}

	public int getTotalEstados() {
		return totalEstados;
	}

	public void setTotalEstados(int totalEstados) {
		this.totalEstados = totalEstados;
	}

	/**
	 * @return the totalMaterias
	 */
	public int getTotalMaterias() {
		return totalMaterias;
	}

	/**
	 * @param totalMaterias the totalMaterias to set
	 */
	public void setTotalMaterias(int totalMaterias) {
		this.totalMaterias = totalMaterias;
	}

	/**
	 * @return the totalRoles
	 */
	public int getTotalRoles() {
		return totalRoles;
	}

	/**
	 * @param totalRoles the totalRoles to set
	 */
	public void setTotalRoles(int totalRoles) {
		this.totalRoles = totalRoles;
	}

	/**
	 * @return the materiasArbitro
	 */
	public List<ReporteEstadosArbitroMateriaDTO> getMateriasArbitro() {
		return materiasArbitro;
	}

	/**
	 * @param materiasArbitro the materiasArbitro to set
	 */
	public void setMateriasArbitro(List<ReporteEstadosArbitroMateriaDTO> materiasArbitro) {
		this.materiasArbitro = materiasArbitro;
	}

	/**
	 * @return the rolesArbitro
	 */
	public List<ReporteEstadosArbitroRolDTO> getRolesArbitro() {
		return rolesArbitro;
	}

	/**
	 * @param rolesArbitro the rolesArbitro to set
	 */
	public void setRolesArbitro(List<ReporteEstadosArbitroRolDTO> rolesArbitro) {
		this.rolesArbitro = rolesArbitro;
	}

	

}
