package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.EspecialidadDTO;
import com.ccb.simasc.transversal.dto.MateriaBasicaDTO;
import com.ccb.simasc.transversal.dto.MateriaDTO;
import com.ccb.simasc.transversal.entidades.Materia;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Materia}
 * @author sMartinez
 *
 */
@Local
public interface IMateriaFacade extends IAccesoFacade<Materia, Long, MateriaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	 /**
	  * Consulta todas las materias en el contexto persistente
	  * @return
	  */
	 public List<Materia> consultarMaterias();
	 /**
	  * Consulta las materias asociadas a un servicio 
	  * @param idServicio
	  * @return
	  */
	 public List<Materia> consultarMateriasPorServicio(Long idServicio);
	 /**
	  * Consulta aquellas materias que poseen servicios asociados
	  * @return
	  */
	 public List<Materia> consultarMateriasConServicios();
	 
	 /**
     * ADM-C-003
     * Consulta las materias del servicio asociado al rol a través de la tabla
     * ParametroServicioSorteo y el sorteo indicador sorteo_con_materia es verdadero.
     * 
     * @param nombreRol Codigo de dominio del rol
     * @return
     */
	 public List<MateriaBasicaDTO> consultarMateriasRol(String nombreRol);
	 
	 /**
	 * ADM-C-029
	 * Consulta las materias del servicio asociado al rol a través 
	 * @param nombreRol Codigo de dominio del rol
	 * @return
	 */
	 public List<MateriaBasicaDTO> consultarMateriasporRolCONARB(String nombreRol);
	 
	 /**
	 * ADM-C-029
	 * Consulta las especialidades por materia 
	 * @param nombreRol Codigo de dominio del rol
	 * @return
	 */	 
	public List<EspecialidadDTO> consultarEspecialidadesMaterias(String idMateria);

	/** ADM-C-004 retorna la lista de las materias de la tabla materia que esten activas, esta consulta se puede parametrizar
	 * @return List<MateriaDTO>
	 */
	public List<MateriaDTO> consultarMateriasParametros();
	
	// protected region metodos adicionales end
	
}
