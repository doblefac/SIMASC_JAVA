package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.CambioEstadoSuplenteDTO;
import com.ccb.simasc.transversal.dto.PendientePronunciamientoDTO;
import com.ccb.simasc.transversal.dto.PronunciamientoDTO;
import com.ccb.simasc.transversal.dto.formularios.InfoCasoParaPronunciamientoDTO;
import com.ccb.simasc.transversal.dto.formularios.InfoRegistroPronunciamientoDTO;
import com.ccb.simasc.transversal.entidades.Pronunciamiento;
import com.ccb.simasc.transversal.excepciones.EstadosCasoException;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Pronunciamiento}
 * @author sMartinez
 *
 */
@Local
public interface IPronunciamientoFacade extends IAccesoFacade<Pronunciamiento, Long, PronunciamientoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * ARB-F-052 Pronunciamiento del árbitro a la designación
	 * Consulta el caso al cual ha sido asignado el operador y que está pendiente de pronunciamiento. 
	 *  
	 * En caso que haya más de un caso asignado se consultan los casos en los que ha sido asignado 
	 * el operador que se pasa como parámetro y en caso de 
	 * que sea más de uno se devuelve el caso en el que se asigno de primeras (la fecha más vieja).
	 * 
	 * @param idPersona el id del usuario operador para el cual se va a consultar su asignación
	 * @return El código del caso pendiente de pronunciamiento. Si no se encuentra ningún caso se devuelve el número -1.
	 */	
	public Long consultarCasoPendienteDePronunciamiento(Long idPersona) throws SIMASCNegocioExcepcion;	
	

	/**
	 * Requerimiento ARB-F-072. Crea o actualiza la informaciÃ³n del pronunciamiento del Ã¡rbitro
	 * 
	 * @param infoPronunciamientoDTO
	 * @param idPersona
	 * @param idCaso
	 * @param idUsuario
	 * @throws SIMASCNegocioExcepcion
	 */
	public Long guardarPronunciamiento(InfoRegistroPronunciamientoDTO infoPronunciamiento, Long idPersona, Long idCaso);
	
	/**
	 * Requerimiento ARB-F-072. Consulta el pronunciamiento del arbitro.
	 * 
	 * @param idPersona
	 * @param idCaso
	 * @throws SIMASCNegocioExcepcion
	 */
	public InfoRegistroPronunciamientoDTO consultarPronunciamiento(Long idPersona, Long idCaso)
			throws SIMASCNegocioExcepcion;
	
	/**
	 * ARB-F-052. Consulta la información del caso para que el árbitro realicé el pronunciamiento.
	 * 
	 * @param idCaso
	 * @param idPersona
	 * @return
	 */
	public InfoCasoParaPronunciamientoDTO consultarInfoCasoAsignadoArbitro(Long idCaso, Long idPersona);
	
	/**
	 * Requerimiento ARB-F-052. Registra el pronunciamiento del arbitro ya sea
	 * que haya sido realizado por el mismo arbitro o este siendo registrado por
	 * otro usuario del sistema.
	 * 
	 * @param infoPronunciamientoDTO
	 * @param idPersona
	 * @param idCaso
	 * @param extemporaneo
	 * @throws SIMASCNegocioExcepcion
	 */
	public Long registrarPronunciamiento(boolean extemporaneo,Pronunciamiento pronunciamiento, Long idPersona, Long idCaso, String idUsuario)
			throws SIMASCNegocioExcepcion;
	
	/**
	 * Metodo que permite reversar un pronunciamiento de un arbitro principal que
	 * declino o no se pronuncio. Devuelve uno o dos mensajes. El primero si hubo reversamiento de l
	 * la designación del antiguo suplente como principal (si hubo suplente) y otro para confirmar
	 * el reversamiento del rechazo a la desiganción del árbitro que declino la designación.
	 * 
	 * @param idPersona
	 * @param idCaso
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<String> reversarPronunciamiento(CambioEstadoSuplenteDTO cambioEstadoSuplenteDTO) throws SIMASCNegocioExcepcion;
	
	/**
	 * pronunciamiento de un conciliador en tipo convenio
	 * @param pronunciamientos
	 * @throws EstadosCasoException 
	 */
	public void pronunciamientoConciliador(List<PendientePronunciamientoDTO> pronunciamientos, String usuario) throws EstadosCasoException;

	/**
	 * crear el pronunciamiento de 
	 * @param pronunciamientos
	 * @return
	 */
	public Pronunciamiento crearPronunciamiento(PendientePronunciamientoDTO pronunciamientos);


	/**
	 * Ejeuta la alerta de nombramiento de suplente
	 * @param idCaso
	 * @param idPersona
	 * @return  retorna true si se ejecuto de manera correcta el metodo
	 * @throws EstadosCasoException
	 */
	public void alertaNombramientoSuplente(Long idCaso, Long idPersona, Long idRol) throws EstadosCasoException;

	public void generarDocumentoPronunciamiento(Long idPersona, Long idCaso, Long idPronunciamiento) throws SIMASCNegocioExcepcion;
	
	
	// protected region metodos adicionales end


	
}
