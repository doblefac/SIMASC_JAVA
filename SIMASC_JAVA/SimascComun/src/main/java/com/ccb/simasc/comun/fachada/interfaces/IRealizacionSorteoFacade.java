package com.ccb.simasc.comun.fachada.interfaces;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.NombramientoPersona;
import com.ccb.simasc.transversal.entidades.ParametroSorteo;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaServicioMateria;
import com.ccb.simasc.transversal.excepciones.SimascException;

@Local
public interface IRealizacionSorteoFacade {

	/**
	 * Consulta las audiencias para sorteo de público de designación del día
	 * 
	 * @param fecha
	 * @return
	 */
	List<Audiencia> consultarAudienciasSorteoDelDia(Date fecha);
	
	/**
	 * Obtiene el nombramiento persona para realizar sorteo NPC
	 * @param caso
	 * @return
	 */
	public NombramientoPersona obtenerNombramientoPersonaCaso(Caso caso);

	/**
	 * Cancela el sorteo
	 * 
	 * @param caso
	 * @param fecha
	 * @throws SimascException
	 */
	void cancelarSorteoCaso(Audiencia audiencia) throws SimascException;

	/**
	 * Metodo para conformar la lista de funcionarios para el sorteo
	 * @param caso
	 * @param simulacion indica si se realiza una simulacion de liberacion de lista
	 * @return List<PersonaDTO>
	 */
	List<Persona> getConformarListaFuncionarioSorteo(Caso caso, boolean simulacion, String tipoSorteo);
	
	/**
	 * valida que los arbitros preseleccionados no sea menor a la 
	 * requerida por el caso
	 * @param personas
	 * @param caso
	 * @param tipoPreseleccionados ** si es preseleccion independiente (PRI , SUP) de lo contrario NULL
	 * @return true si la cantidad es suficiente, false de los contrario
	 */
	boolean validarCantidadMinArb(List<Persona> personas,  Audiencia audiencia ,String tipoPreseleccionados);
	
	/**
	 * Realiza la simulacion de liberacion de lista para realizar
	 * el sorteo del caso enviado por parametro.
	 * @param caso caso al cual se le va a simular la liberacion de lista
	 * @return true si la liberacion permite sorteo, false de lo contrario
	 */
	boolean simulariberacionLista(Caso caso, String tipoSorteo);
	
	/**
	 * Realiza la liberacion de lista para el caso enviado
	 * @param caso
	 */
	List<PersonaServicioMateria> liberarListaSorteo(Caso caso, String usuarioSesion, String tipoSorteo);

	/**
	 * Consulta los paramtros configurados para realizar sorteo
	 * @return
	 */
	ParametroSorteo consultarParametrosSorteo();
	

	/**
	 * Realiza el sorteo de las personas teniendo en cuenta la cantidad de principales y suplentes
	 * @param personasSorteo lista de personas que se van a sortear
	 * @param cantPrincipales cantidad de arbitros principales a sortear
	 * @param cantSuplentes cantidad de arbitros suplentes a sortear
	 * @return lista de personas que salieron favorecidas para el sorteo
	 */
	List<Persona> realizarSorteo(List<Persona> personasSorteo, int cantPrincipales, int cantSuplentes) throws NoSuchAlgorithmException;
	
	/***
	 * Consulta las personas preseleccionadas para el sorteo
	 * del caso enviado
	 * @param caso
	 * @param tipoPreseleccion
	 * @return lista de personas preseleccionadas
	 */
	List<Persona> obtenerPreseleccionados (Caso caso , String tipoPreseleccion);
	
	/**
	 * Registra el resultado del sorteo y actualiza las tablas 
	 * correspondientes
	 * @param audiencia
	 * @param arbitrosSeleccionados
	 */
	void registrarResultSorteo(Audiencia audiencia, List<Persona> arbitrosSeleccionados, String usuarioSesion);
	
	/**
	 * Valida si el dia enviado esta parametrizado para realizar sorteo. 
	 * @return true si el dia esta habilitado, false de lo contrario
	 */
	boolean validarDiaHabilitadoParaSorteo(Calendar fecha);
	
	/**
	 * Obtiene la audiencia a partir de su id
	 * @param idAudiencia
	 * @return
	 */
	public Audiencia obtenerAudiencia (Long idAudiencia);

	/**
	 * 
	 * @param time
	 * @param tipo  tipo de sorteo a realizar (sorteo o sorteo internacional)
	 * @return
	 */
	public List<Audiencia> obtenerAudienciasSorteoDelDiaYSigDiaHabil(String tipo);

}