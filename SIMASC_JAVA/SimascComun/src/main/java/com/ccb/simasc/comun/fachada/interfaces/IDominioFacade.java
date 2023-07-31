package com.ccb.simasc.comun.fachada.interfaces;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.EntityManager;

import com.ccb.simasc.transversal.dto.ConsultaDominioDTO;
import com.ccb.simasc.transversal.dto.DominioDTO;
import com.ccb.simasc.transversal.dto.formularios.TipoDocumentalDTO;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.DominioPK;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;

// protected region imports adicionales on begin
// Escriba en esta sección sus modificaciones

// protected region imports adicionales end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Dominio}
 * @author sMartinez
 *
 */
@Local
public interface IDominioFacade extends IAccesoFacade<Dominio, DominioPK, DominioDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	public void recargarDominios();
	public EntityManager getManejador();
	public List<Dominio> getDominios();
	public List<Dominio> getDominios(String nombreDominio);
	public List<DominioDTO> getDominiosDTO(String nombreDominio);
	public String getNombreDominio(String dominio,String codigo);
	public Dominio getObtenerDominio(String dominio,String codigo);
	List<Dominio> obtenerDominiosHijos(String nombreDominioPadre, String codigoPadre);
	public boolean validarGrupoContieneDominioPorCodigo(String nombreDominioPadre, String codigoPadre, String codigoDominioValidacion);
	public List<Dominio> obtenerDominiosAgrupados(String nombreDominioAgrupador, String codigoAgrupador);
	public List<DominioDTO> consultarDominiosPorNombre(List<String> nombreDominios);
	public DominioDTO consultarDominioDTO(String nomDominio, String codigo);
	public List<DominioDTO> obtenerMotivosInactivacionArbitros();
	public List<Dominio> obtenerDominiosPorCodigo(String dominio, List<String> codigos);
	public Dominio buscarDominioEnLista(List<Dominio> dominios, String codigoBusqueda);
	public List<DominioDTO> consultarDominiosPorClasificadores(List<ConsultaDominioDTO> parametrosBusqueda);
	public Dominio getDominioSinClasificadores(String nombreDominio, String codigo);
	/**
	 * consulta los dominios sin agrupador y sin dependecias.
	 * @param nombreDominio
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<Dominio> getDominioSinDependencias(String nombreDominio) throws SIMASCNegocioExcepcion;

	/**
	 * consulta un dominio sin agrupador y sin dependecias.
	 * @param nombreDominio
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public Dominio getObtenerDominioSinDependencia(String nombreDominio, String codigo);
	
	/**
	 * Método para buscar un dominio por el campo nombre
	 * @param nombreDominio
	 * @param nombre
	 * @return
	 */
	public Dominio buscarDominioPorNombre(String nombreDominio, String nombre);
	
	/**
	 * Se utiliza para obtener la descripcion del rol dependiendo del id del rol
	 * @param idRol el identificador del rol
	 * @return la informacion de la descripcion
	 */
	public String obtenerDominioRol(Long idRol);
	
	// protected region metodos adicionales end
	/**
	 * Se utiliza para obtener los motivos de inactivacion de un arbitro
	 * @param 
	 * @return listado de dominios
	 */
	public List<Dominio>obtenerDominioMotivosInactivacionArbitro();
	
	public List<Dominio> obtenerDominioPorClasificador(String dominioClasificador);
	
	public List<Dominio> obtenerDominioPorServicio(Long idServicio, String dominio);
	
	public List<TipoDocumentalDTO> consultarTipoDocumentalCaso(Long idCaso);
	
	public List<Dominio> obtenerTipoSorteoPorServicio(Long idServicio);
	
}
