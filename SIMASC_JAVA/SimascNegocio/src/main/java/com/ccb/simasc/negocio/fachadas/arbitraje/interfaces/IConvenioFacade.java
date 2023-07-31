package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ConvenioDTO;
import com.ccb.simasc.transversal.dto.CrearConvenioDTO;
import com.ccb.simasc.transversal.dto.InformacionFiltroDTO;
import com.ccb.simasc.transversal.entidades.Convenio;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Convenio}
 * @author sMartinez
 *
 */
@Local
public interface IConvenioFacade extends IAccesoFacade<Convenio, Long, ConvenioDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/**
	 * Consulta los convenios disponibles en la base de datos
	 * que se encuentren activos
	 * @return List<ConvenioDTO>
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<ConvenioDTO> consultarConvenios() throws SIMASCNegocioExcepcion;
	
	/**
	 * Metodo que consulta los convenios dada una lista de filtros 
	 * @param filtrosParametro
	 * @return List<ConvenioDTO>
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<ConvenioDTO> consultarConveniosFiltros(List<InformacionFiltroDTO> filtrosParametro) throws SIMASCNegocioExcepcion;
	
	/**
	 * Método encargado de consultar los convenios vigentes por tipo de convenio
	 * 
	 * @param tipoConvenio
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<ConvenioDTO> consultarConveniosVigentes(String tipoConvenio, Long idServicio) throws SIMASCNegocioExcepcion;
	
	/**
	 * Método para consultar los convenios relacionados a una persona, que se encuentren vigentes, 
	 * teniendo en cuenta el tipo de convenio y el rol de la persona
	 * @param idPersona
	 * @param idRol
	 * @param fechaVigencia
	 * @param tipoConvenio
	 * @return
	 */
	public List<Convenio> consultarConvenioPorRelacionado(Long idPersona, Long idRol, Date fechaVigencia, String tipoConvenio);
	
	/**
	 * Método para obtener las jornadas realacionadas a los centros
	 * @param centros
	 * @param ejecutadas
	 * @return
	 */
	public List<ConvenioDTO> consultarJornadas(List<Long> centros, boolean ejecutadas);
	
	/**
	 * Método para la creacion de un convenio de tipo jornada
	 * @param convenio
	 * @return
	 */
	public Convenio actualizarConvenio(Convenio convenio);
	
	/**
	 * Retorna los convenios que esten activos a la fecha actual que sean tipo de convenio : convenio. 
	 * @param centros
	 * @return List<ConvenioDTO>
	 */
	public List<ConvenioDTO> consultarConveniosPorCentro( List<String> centros, Date fechaConsulta );
	
	
	/**
	 * Retorna todos los convenios según nombre o código sin importar su estado  
	 * @param centros
	 * @return List<ConvenioDTO>
	 */
	public List<ConvenioDTO> consultarConveniosPorNombreCodigo( String nombreConvenio, Long idConvenio, List<Long> centros );
	
	/**
	 * Retorna el convenio creado   
	 * @param CrearConvenioDTO
	 * @return ConvenioDTO
	 */
	public ConvenioDTO crearConvenio( CrearConvenioDTO convenio, String idUsuario );
	
	
	/**
	 * Retorna la informacion del convenio y la relacionada a éste  
	 * @param idConvenio
	 * @return CrearConvenioDTO
	 */
	public CrearConvenioDTO consultarInformacionConvenio(Long idConvenio);
	
	
	/**
	 * Actualiza la informacion del convenio 
	 * @param informacionConvenio
	 * @return 
	 */
	public void actualizarInformacionConvenio(CrearConvenioDTO informacionConvenio, String idUsuario);
	
	/**
	 * Obtiene los convenios vigentes según los centros especificados
	 * 
	 * @param centros
	 * @return
	 */
	public List<ConvenioDTO> consultarConveniosVigentesPorCentro(List<String> centros);
	
	// protected region metodos adicionales end
	
}
