package com.ccb.simasc.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.negocio.arbitraje.CasoFacade;
import com.ccb.simasc.negocio.arbitraje.GestionArbitrajeFachada;
import com.ccb.simasc.negocio.arbitraje.RepartoFacade;
import com.ccb.simasc.negocio.transversal.GestionCasosFachada;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;

@ViewScoped
@ManagedBean
public class IndexMB implements Serializable{
	private static final Logger logger = LogManager.getLogger(IndexMB.class.getName());
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long numeroCaso = 0l;
	private Long numeroCasoH = 0l;
	private String tipoDocumento;
	private String numeroDocumento;
	private String razon;
	private RolPersonaCaso rpc = new RolPersonaCaso();
	private String nombres = "";
	
	private Long casoSelected;

	private String cargo;
	
	private List<Caso> listaCasos;
	
	@EJB private GestionCasosFachada gestionCasos;
	@EJB private GestionArbitrajeFachada gestionArbitraje;
	@EJB private CasoFacade casoFacade;
	@EJB private RepartoFacade repartoFacade;
	@EJB private ManejadorRol manejadorRol;
	@EJB private ManejadorCaso manejadorCaso;
	@PostConstruct

	public void init(){
		logger.info("Inicializando el Bean");
		listaCasos = casoFacade.getCasos();
	}
	
	public void generarNombreCaso(){
		
		logger.info("Desde el Bean " + numeroCaso);
		gestionCasos.generarNombreCaso(numeroCaso);
	}
	
	public void habilitarArbitro(){
		Long idPersona =new Long(numeroDocumento);
		gestionArbitraje.habilitarArbitro(numeroCasoH, razon, idPersona);		
		
	}

	public Long getNumeroCaso() {
		return numeroCaso;
	}

	public void setNumeroCaso(Long numeroCaso) {
		this.numeroCaso = numeroCaso;
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	public List<Caso> getListaCasos() {
		return this.listaCasos;
	}

	public void setListaCasos(List<Caso> listaCasos) {
		this.listaCasos = listaCasos;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public void reparto(){
		List<Rol> rol = manejadorRol.consultarRolesPorNombre(cargo);
		Caso casoSel = manejadorCaso.buscar(casoSelected);
		// rpc =repartoFacade.reparto(casoSel, rol.get(0));
		Persona persona = rpc.getPersona();
		nombres = persona.getPrimerNombreORazonSocial() + " " + persona.getSegundoNombre() + " " + persona.getPrimerApellido() + " " + persona.getSegundoApellido(); 
		
	}

	public RolPersonaCaso getRpc() {
		return rpc;
	}

	public void setRpc(RolPersonaCaso rpc) {
		this.rpc = rpc;
	}
	
	public Long getCasoSelected() {
		return casoSelected;
	}

	public void setCasoSelected(Long casoSelected) {
		this.casoSelected = casoSelected;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Long getNumeroCasoH() {
		return numeroCasoH;
	}

	public void setNumeroCasoH(Long numeroCasoH) {
		this.numeroCasoH = numeroCasoH;
	}

}
