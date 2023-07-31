package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorAgrupamientoRol;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaServicioMateria;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaServicioMateriaFacade;
import com.ccb.simasc.transversal.dto.PersonaServicioMateriaDTO;
import com.ccb.simasc.transversal.entidades.AgrupamientoRol;
import com.ccb.simasc.transversal.entidades.PersonaServicioMateria;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link PersonaServicioMateria}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class PersonaServicioMateriaFacade extends AccesoFacade<PersonaServicioMateria, Long, PersonaServicioMateriaDTO> implements IPersonaServicioMateriaFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorPersonaServicioMateria manejadorPersonaServicioMateria;
	
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;
	
    @EJB 
    private PersonaServicioMateriaFacade personaServicioMateriaFacade;
    
	
    @EJB
    private AgrupamientoRolFacade agrupamientoRolFacade; 
    

    @EJB
    private ManejadorAgrupamientoRol manejadorAgrupamientoRol; 
	// protected region atributos end
	
	
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorPersonaServicioMateria;
	}

	@Override
	public PersonaServicioMateriaDTO transformarSinDependencias(PersonaServicioMateria obj) {
		PersonaServicioMateriaDTO dto = new PersonaServicioMateriaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public PersonaServicioMateriaDTO transformarConDependencias(PersonaServicioMateria obj) {
		PersonaServicioMateriaDTO dto = new PersonaServicioMateriaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public PersonaServicioMateria transformarEntidadConDependencias(PersonaServicioMateria obj) {
		PersonaServicioMateria dto = new PersonaServicioMateria();
		dto = new PersonaServicioMateriaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public PersonaServicioMateria transformarEntidadSinDependencias(PersonaServicioMateria obj) {
		PersonaServicioMateria dto = new PersonaServicioMateria();
		dto = new PersonaServicioMateriaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISedeFacade#crearSede(com.ccb.simasc.transversal.dto.SedeTipoServicioDTO)
	 */
	@Override
	public void crearMateriaServicio(PersonaServicioMateria servicioMateria) {
		if(appContext != null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getRolPrincipal() !=null){
			
		//	InformacionFiltro filtros = new InformacionFiltro(TipoFiltro.EXACTO, AgrupamientoRol.ENTIDAD_AGRUPAMIENTO_ROL_PK_ID_ROL,
			InformacionFiltro filtros = new InformacionFiltro(TipoFiltro.EXACTO, AgrupamientoRol.ENTIDAD_AGRUPAMIENTO_ROL_PK_ID_SERVICIO,
					servicioMateria.getIdServicio());
        	List<InformacionFiltro> filtrosRol = new ArrayList<>();
        	filtrosRol.add(filtros);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
       	
			InformacionFiltro filtrosTipoMateria = new InformacionFiltro(TipoFiltro.EXACTO, AgrupamientoRol.ENTIDAD_AGRUPAMIENTO_ROL_PK_TIPO_AGRUPAMIENTO,
					UtilDominios.AGRUPAMIENTO_MATERIA_ROL);
        	filtrosRol.add(filtrosTipoMateria);
			
        	List<AgrupamientoRol> resultados = manejadorAgrupamientoRol.consultar(filtrosRol, null, null);

        	if(resultados!=null && !resultados.isEmpty() && resultados.get(0).getServicio() != null){
    			servicioMateria.setIdServicio(resultados.get(0).getServicio().getIdServicio());
    			if(resultados.get(0).getRol().getNombre().equals(UtilDominios.ROL_PERSONA_CONCILIADOR)){
    				Long cantidadCasosAsignados = this.manejadorPersonaServicioMateria.obtenerCantidadCasosAsignados(servicioMateria);
        			servicioMateria.setCantidadCasosAsignados(cantidadCasosAsignados);
    			}
    			personaServicioMateriaFacade.crear(servicioMateria);
        	}		
		}
	}

	// protected region metodos adicionales end
	
}
