package com.ccb.simasc.comun.fachada.implementacion;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorDominio;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.transversal.dto.ConsultaDominioDTO;
import com.ccb.simasc.transversal.dto.DominioBasicoDTO;
import com.ccb.simasc.transversal.dto.DominioDTO;
import com.ccb.simasc.transversal.dto.formularios.TipoDocumentalDTO;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.DominioPK;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

/**
 * 
 * @author jsoto
 *
 */
@Singleton
@LocalBean
public class DominioFacade extends AccesoFacade<Dominio, DominioPK, DominioDTO> implements IDominioFacade {

	@EJB
	private ManejadorDominio manejadorDominio;

	private List<Dominio> dominiosBD;
	

	@PostConstruct
	public void init() {
		dominiosBD = manejadorDominio.consultarDominiosParaCargue();
	}
	
	@Override
	public EntityManager getManejador() {
		return getManejador();
	}

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorDominio;
	}

	@Override
	public DominioDTO transformarSinDependencias(Dominio obj) {
		DominioDTO dto = new DominioDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public DominioDTO transformarConDependencias(Dominio obj) {
		DominioDTO dto = new DominioDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Dominio transformarEntidadConDependencias(Dominio obj) {
	 
		return new DominioDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Dominio transformarEntidadSinDependencias(Dominio obj) {				
		return new DominioDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	/**
	 * Vuelve a cargar los dominios desde base de datos
	 */
	@Override
	public void recargarDominios() {
		dominiosBD = manejadorDominio.consultarDominiosParaCargue();
	}

	/**
	 * Retorna todos los dominios existentes en la base de datos desde la última
	 * carga.
	 * 
	 * @return the dominios
	 */
	@Override
	public List<Dominio> getDominios() {
		return dominiosBD;
	}

	/**
	 * Devuelve cada uno de los registros(Dominios) de un dominio especifico
	 * 
	 * @param nombreDominio
	 * @return
	 */
	@Override
	public List<Dominio> getDominios(String nombreDominio) {
		List<Dominio> dominiosNombre = new ArrayList<>();
		for (Dominio dominio : dominiosBD) {
			if (dominio.getDominioPK().getDominio().equalsIgnoreCase(nombreDominio)) {
				dominiosNombre.add(dominio);
			}
		}

		return dominiosNombre;
	}
	
	@Override
	public List<Dominio> getDominioSinDependencias(String nombreDominio) throws SIMASCNegocioExcepcion{
		List<Dominio> dominios = this.getDominios(nombreDominio);
		dominios = new DominioDTO().transformarColeccionEntidadesSinDependencias(dominios);
		return dominios;
		
	}

	@Override
	public List<DominioDTO> getDominiosDTO(String nombreDominio) {
		List<DominioDTO> dominiosNombre = new ArrayList<>();
		for (Dominio dominio : dominiosBD) {
			if (dominio.getDominioPK().getDominio().equals(nombreDominio)) {
				DominioDTO dominioDTO = new DominioDTO();
				dominioDTO.setNombre(dominio.getNombre());
				dominioDTO.setDescripcion(dominio.getDescripcion());
				dominioDTO.setDominioPK(dominio.getDominioPK());
				dominiosNombre.add(dominioDTO);
			}
		}

		return dominiosNombre;
	}

	/**
	 * Devuelve el nombre del dominio
	 * 
	 * @param dominio
	 *            : Dominio al que pertenece el codigo
	 * @param codigo
	 *            : Codigo de dominio
	 * @return
	 */
	@Override
	public String getNombreDominio(String dominio, String codigo) {
		String nombreDominio = null;
		for (Dominio dominioObjeto : dominiosBD) {
			if (dominioObjeto.getDominioPK().getDominio().equals(dominio)
					&& dominioObjeto.getDominioPK().getCodigo().equals(codigo)) {
				nombreDominio = dominioObjeto.getNombre();
				return nombreDominio;
			}
		}
		return nombreDominio;
	}
	
	/**
	 * Consulta los valores de los dominios que se pasan como parámetro
	 * @param nombreDominios
	 * @return
	 */
	public List<DominioDTO> consultarDominiosPorNombre(List<String> nombreDominios) {
		List<Dominio> dominios = new ArrayList<Dominio>();
		for (String it : nombreDominios) {
			dominios.addAll(this.getDominios(it));
		}			
		 
		
		return new DominioDTO().transformarColeccionSinDependencias(dominios);
	}
	
	/**
	 * Consulta los valores de los dominios definidos en los parametros consultados.
	 * @param nombreDominios
	 * @return
	 */
	@Override
	public List<DominioDTO> consultarDominiosPorClasificadores(List<ConsultaDominioDTO> parametrosBusqueda) {
		List<Dominio> dominios = new ArrayList<Dominio>();
		for (ConsultaDominioDTO parametroBusqueda : parametrosBusqueda) {
			if(parametroBusqueda.getCodigoClasificador()!=null && !parametroBusqueda.getCodigoClasificador().isEmpty() &&
					parametroBusqueda.getDominioClasificador()!=null && !parametroBusqueda.getDominioClasificador().isEmpty()){
				dominios.addAll(obtenerDominiosAgrupados(parametroBusqueda.getDominioClasificador(), parametroBusqueda.getCodigoClasificador()));
			}else if(parametroBusqueda.getDominioAConsultar()!=null && !parametroBusqueda.getDominioAConsultar().isEmpty()){
				dominios.addAll(this.getDominios(parametroBusqueda.getDominioAConsultar()));
			}
		}			
		 
		return new DominioDTO().transformarColeccionSinDependencias(dominios);
	}

	public List<DominioDTO> getBusquedaXDominio(String dominioNombre) {
		InformacionFiltro filtro = new InformacionFiltro(TipoFiltro.EXACTO, Dominio.ENTIDAD_DOMINIO_PK_DOMINIO,
				dominioNombre);
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(filtro);
		List<DominioDTO> listDominiosDTO = new ArrayList<DominioDTO>();

		try {
			List<Dominio> listDominios = manejadorDominio.consultar(filtros, null, null);

			for (Dominio dominio : listDominios) {
				DominioDTO dominioDTO = new DominioDTO();
				dominioDTO.setNombre(dominio.getNombre());
				dominioDTO.setDescripcion(dominio.getDescripcion());
				dominioDTO.setDominioPK(dominio.getDominioPK());
				listDominiosDTO.add(dominioDTO);

			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO155.toString()),
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO150.toString())));
		}

		return listDominiosDTO;
	}

	/**
	 * Devuelve el dominio
	 * 
	 * @param dominio
	 *            : Dominio al que pertenece el codigo
	 * @param codigo
	 *            : Codigo de dominio
	 * @return Dominio
	 */
	@Override
	public Dominio getObtenerDominio(String nombreDominio, String codigo) {
		Dominio dominio = null;
		if(nombreDominio!=null){
			dominio = new Dominio();
			for (Dominio dominioObjeto : dominiosBD) {
				if (dominioObjeto.getDominioPK().getDominio().equals(nombreDominio)
						&& dominioObjeto.getDominioPK().getCodigo().equals(codigo)) {
					dominio = dominioObjeto;
					return dominio;
				}
			}
		}
		
		return dominio;
	}
	
	/**
	 * Devuelve el dominio
	 * 
	 * @param dominio
	 *            : Dominio al que pertenece el codigo
	 * @param codigo
	 *            : Codigo de dominio
	 * @return Dominio
	 */
	@Override
	public Dominio getObtenerDominioSinDependencia(String nombreDominio, String codigo) {
		
		Dominio dominiosDependendencia = null;
		dominiosDependendencia = getObtenerDominio(nombreDominio,codigo);
		DominioDTO transforma = new DominioDTO();
		
		if(dominiosDependendencia!= null){
			return transforma.transformarEntidadSinDependencias(dominiosDependendencia);
		}else{
			return dominiosDependendencia;
		}
		
	}
	
	/**
	 * Devuelve el dominio quitando las referencias de los clasificadores
	 * @param nombreDominio
	 * @param codigo
	 * @return
	 */
	public Dominio getDominioSinClasificadores(String nombreDominio, String codigo){
		Dominio dominio = getObtenerDominio(nombreDominio, codigo);
		return (new DominioDTO()).transformarEntidadSinDependencias(dominio);
	}
	
	/**
	 * Consulta el dominio con los valores que se pasa como parametro. Devuelve el DTO.
	 * @param nomDominio
	 * @param codigo
	 * @return 
	 */
	public DominioDTO consultarDominioDTO(String nomDominio, String codigo) {
    	    	    	
		DominioPK pk = new DominioPK(codigo, nomDominio);
		Dominio dominio = this.buscar(pk);
			
    	return new DominioDTO().transformarSinDependencias(dominio);
	}

	/**
	 * Devuelve los hijos de un agrupamiento
	 * 
	 * @param dominio
	 *            : Dominio padre
	 * @param codigo
	 *            : Codigo de dominio padre
	 * @return Lista de dominios hijos
	 */
	@Override
	public List<Dominio> obtenerDominiosHijos(String nombreDominioPadre, String codigoPadre) {
		List<Dominio> dominiosHijos = new ArrayList<Dominio>();
		for (Dominio dominioObjeto : dominiosBD) {
			if (dominioObjeto.getDominioPadre() != null && dominioObjeto.getCodigoDomPadre() != null) {
				if (dominioObjeto.getDominioPadre().equals(nombreDominioPadre)
						&& dominioObjeto.getCodigoDomPadre().equals(codigoPadre)) {
					dominiosHijos.add(dominioObjeto);
				}
			}
		}
		return dominiosHijos;
	}

	/**
	 * Devuelve los dominios agrupados por un clasificador de dominio
	 * 
	 * @param nombreDominioAgrupador
	 * @param codigoAgrupador
	 * @return Lista de dominios hijos
	 */
	public List<Dominio> obtenerDominiosAgrupados(String nombreDominioAgrupador, String codigoAgrupador) {
		Dominio dominioAgrupador = this.getObtenerDominio(nombreDominioAgrupador, codigoAgrupador);
		
		return dominioAgrupador.obtenerDominiosAgrupados();
	}
	
	/**
	 * Valida si dentro de los dominios agrupados por el dominio agrupador que se define
	 * en los parámetros se encuentra el dominio con el codigo codigoDominioValidacion
	 * 
	 * @param nombreDominioPadre
	 * @param codigoPadre
	 * @param codigoDominioValidacion Codigo que se valida si existe en el grupo
	 * @return
	 */
	@Override
	public boolean validarGrupoContieneDominioPorCodigo(String nombreDominioPadre, String codigoPadre, String codigoDominioValidacion){
		List<Dominio> grupoDominios = this.obtenerDominiosAgrupados(nombreDominioPadre, codigoPadre);
		boolean existe = false;
		for(Dominio dominio : grupoDominios){
			if(dominio.getDominioPK().getCodigo().equals(codigoDominioValidacion)){
				existe = true;
				break;
			}
		}
		
		return existe;
	}

	
	public List<DominioDTO> obtenerMotivosInactivacionArbitros() {

		Dominio motivoRecusacion = this.getObtenerDominio(
				UtilDominios.DOMINIO_MOTIVOS_INACTIVACION_ARBITRO, UtilDominios.MOTIVO_RECUSACION);
		List<Dominio> motivosInactivacion = this
				.getDominios(UtilDominios.DOMINIO_MOTIVOS_INACTIVACION_ARBITRO);
		motivosInactivacion.remove(motivoRecusacion);

		
		return new DominioDTO().transformarColeccionSinDependencias(motivosInactivacion);
	}

	/**
	 * Transforma la entidad al DTO si el objeto no viene nulo
	 * @param obj
	 * @return
	 */
	public DominioBasicoDTO transformarEntidadADominioBasicoDTO(Dominio dominio) {
		
		DominioBasicoDTO dto = null;
		if(dominio!=null){
			dto = new DominioBasicoDTO(dominio.getDominioPK().getCodigo(), dominio.getNombre());
		}
		
		return dto;
	}
	
	/**
	 * Transforma la lista de entidades a una lista DTO. Se devuelve null
	 * si la lista que viene es nula. Si la lista viene vacía devuelve una lista vacía.
	 * @param obj
	 * @return
	 */
	public List<DominioBasicoDTO> transformarListaEntidadAListaDominioBasicoDTO(List<Dominio> lista) {
		
		List<DominioBasicoDTO> listaDTO = null;
		if(lista!=null){
			listaDTO = new ArrayList<>();
			for(Dominio dominio : lista){
				listaDTO.add(transformarEntidadADominioBasicoDTO(dominio));
			}
		}
		
		return listaDTO;
	}
	
	/**
	 * Obtiene los dominios correspondientes a los códigos "codigos" que se pasan como parámetro.
	 * La busqueda se hace sobre el dominio "dominio"
	 * @param dominio
	 * @param codigos
	 * @return
	 */
	public List<Dominio> obtenerDominiosPorCodigo(String dominio, List<String> codigos){
		List<Dominio> dominios = new ArrayList<>();
		List<Dominio> dominioBusqueda = this.getDominioSinDependencias(dominio);
		for(String codigo : codigos){
			Dominio domEncontrado = this.buscarDominioEnLista(dominioBusqueda, codigo);
			if(domEncontrado!=null){
				dominios.add(domEncontrado);
			}				
		}
		
		return dominios;
		
	}
	
	/**
	 * Se busca el dominio con código codigoBusqueda en la lista de dominios "dominios"
	 * @param dominios
	 * @param codigoBusqueda
	 * @return
	 */
	public Dominio buscarDominioEnLista(List<Dominio> dominios, String codigoBusqueda){
		Dominio dominio = null;
		for(Dominio dom : dominios){
			if(dom.getDominioPK()!=null && dom.getDominioPK().getCodigo()!=null && 
				dom.getDominioPK().getCodigo().equals(codigoBusqueda)){
				dominio = dom;
				break;
			}
		}
		
		return dominio;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.comun.fachada.interfaces.IDominioFacade#buscarDominioPorNombre(java.lang.String, java.lang.String)
	 */
	@Override
	public Dominio buscarDominioPorNombre(String nombreDominio, String nombre) {
		Dominio dominio = null;
		for(Dominio dom : dominiosBD){
			if(dom.getDominioPK() !=null && dom.getNombre() !=null && 
				dom.getNombre().equals(nombre) && dom.getDominioPK().getDominio().equals(nombreDominio)) {
				dominio = dom;
				break;
			}
		}
		return dominio;
	}

	
	@Override
	public String obtenerDominioRol(Long idRol){
	
		List<Dominio> lista = manejadorDominio.obtenerDominioRol(idRol);
		if(!lista.isEmpty()) {
			return lista.get(0).getNombre();
		}
		return "";
	}

	@Override
	public List<Dominio> obtenerDominioMotivosInactivacionArbitro() {		
		return manejadorDominio.obtenerDominioMotivosInactivacionArbitro();
	}
	
	@Override
	public List<Dominio> obtenerDominioPorClasificador(String dominioClasificador){
		return manejadorDominio.obtenerDominioPorClasificador(dominioClasificador);
	}
	
	@Override
	public List<Dominio> obtenerDominioPorServicio(Long idServicio, String dominio){
		return manejadorDominio.obtenerDominioPorServicio(idServicio, dominio);
	}

	@Override
	public List<TipoDocumentalDTO> consultarTipoDocumentalCaso(Long idCaso) {
		return manejadorDominio.tipoDocumentalCaso(idCaso);
	}
	
	@Override
	public List<Dominio> obtenerTipoSorteoPorServicio(Long idServicio){
		return manejadorDominio.tipoSorteoPorServicio(idServicio);
	}

	// protected region metodos adicionales end

}
