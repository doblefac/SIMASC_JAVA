package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorMateria;
import com.ccb.simasc.integracion.manejadores.ManejadorServicioMateria;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IMateriaFacade;
import com.ccb.simasc.transversal.dto.EspecialidadDTO;
import com.ccb.simasc.transversal.dto.MateriaBasicaDTO;
import com.ccb.simasc.transversal.dto.MateriaDTO;
import com.ccb.simasc.transversal.entidades.Materia;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Materia}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class MateriaFacade extends AccesoFacade<Materia, Long, MateriaDTO> implements IMateriaFacade {
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	
	@EJB
	private ManejadorServicioMateria manejadorServicioMateria;
	
	@EJB
	private ManejadorMateria manejadorMateria;

	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorMateria;
	}

	@Override
	public MateriaDTO transformarSinDependencias(Materia obj) {
		return new MateriaDTO().transformarSinDependencias(obj);
	}

	@Override
	public MateriaDTO transformarConDependencias(Materia obj) {
		return new MateriaDTO().transformarConDependencias(obj);
	}

	@Override
	public Materia transformarEntidadConDependencias(Materia obj) {
		return new MateriaDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Materia transformarEntidadSinDependencias(Materia obj) {
		return new MateriaDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	@Override
	public List<Materia> consultarMaterias(){	
		
		return (List<Materia>)transformarEntidadesColeccionSinDependencias(manejadorMateria.consultar(null,null, null), new ArrayList<Materia>());
	}
	
	@Override
	public List<Materia> consultarMateriasConServicios(){
		
		return (List<Materia>)transformarEntidadesColeccionConDependencias(manejadorMateria.consultarMateriasConServicios(), new ArrayList<Materia>());
	}
	
	@Override
	public List<Materia> consultarMateriasPorServicio(Long idServicio){
		
		return (List<Materia>)transformarEntidadesColeccionSinDependencias(manejadorMateria.consultarMateriasPorServicio(idServicio), new ArrayList<Materia>());
	}
	
	/**
     * ADM-C-003
     * Consulta las materias del servicio asociado al rol a través de la tabla
     * ParametroServicioSorteo y el sorteo indicador sorteo_con_materia es verdadero.
     * 
     * @param nombreRol Codigo de dominio del rol
     * @return
     */
	public List<MateriaBasicaDTO> consultarMateriasRol(String nombreRol){
		return manejadorMateria.consultarMateriasRol(nombreRol);
	}
	
	/**
     * ADM-C-029
     * Consulta las materias del servicio asociado al rol a través 
     * 
     * @param nombreRol Codigo de dominio del rol
     * @return
     */
	public List<MateriaBasicaDTO> consultarMateriasporRolCONARB(String nombreRol){
		return manejadorMateria.consultarMateriasporRolCONARB(nombreRol);
	}
	
	/**
     * ADM-C-029
     * Consulta las materias del servicio asociado al rol a través 
     *      * @return
     */
	public List<EspecialidadDTO> consultarEspecialidadesMaterias(String idMateria){
		return manejadorMateria.consultarEspecialidadesMaterias(idMateria);
	}
	
	@Override
	public List<MateriaDTO> consultarMateriasParametros(){
		return (List<MateriaDTO>) transformarColeccionSinDependencias( manejadorMateria.consultarMateriasParametros(), new ArrayList<MateriaDTO>() );
	}

	// protected region metodos adicionales end
	
}
