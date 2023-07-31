package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorAgrupamientoRol;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgrupamientoRolFacade;
import com.ccb.simasc.transversal.dto.AgrupamientoRolDTO;
import com.ccb.simasc.transversal.entidades.AgrupamientoRol;
import com.ccb.simasc.transversal.entidades.AgrupamientoRolPK;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link AgrupamientoRol}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class AgrupamientoRolFacade extends AccesoFacade<AgrupamientoRol, AgrupamientoRolPK, AgrupamientoRolDTO> implements IAgrupamientoRolFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorAgrupamientoRol manejadorAgrupamientoRol;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorAgrupamientoRol;
	}

	@Override
	public AgrupamientoRolDTO transformarSinDependencias(AgrupamientoRol obj) {
		AgrupamientoRolDTO dto = new AgrupamientoRolDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public AgrupamientoRolDTO transformarConDependencias(AgrupamientoRol obj) {
		AgrupamientoRolDTO dto = new AgrupamientoRolDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public AgrupamientoRol transformarEntidadConDependencias(AgrupamientoRol obj) {
		AgrupamientoRol dto = new AgrupamientoRol();
		dto = new AgrupamientoRolDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public AgrupamientoRol transformarEntidadSinDependencias(AgrupamientoRol obj) {
		AgrupamientoRol dto = new AgrupamientoRol();
		dto = new AgrupamientoRolDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	@Override
	public List<Rol> obtenerRolesPorServicioYTipoAgrupador(Long idServicio, String tipoAgrupador)
			throws SIMASCNegocioExcepcion {

		List<Rol> roles = new ArrayList<Rol>();
		List<AgrupamientoRol> agrupamientoRol = new ArrayList<AgrupamientoRol>();
		List<InformacionFiltro> filtros = new ArrayList<InformacionFiltro>();
		filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, AgrupamientoRol.ENTIDAD_AGRUPAMIENTO_ROL_PK_ID_SERVICIO, idServicio));
		filtros.add(new InformacionFiltro(TipoFiltro.EXACTO,
				AgrupamientoRol.ENTIDAD_AGRUPAMIENTO_ROL_ESTADO_REGISTRO,UtilDominios.ESTADO_REGISTRO_ACTIVO));
		filtros.add(new InformacionFiltro(TipoFiltro.EXACTO,AgrupamientoRol.ENTIDAD_AGRUPAMIENTO_ROL_PK_TIPO_AGRUPAMIENTO, tipoAgrupador));
		
		agrupamientoRol = manejadorAgrupamientoRol.consultar(filtros, null, null);
		
		for (AgrupamientoRol agrupamientoFor : agrupamientoRol) {
			if (agrupamientoFor.getRol() != null){
				roles.add(agrupamientoFor.getRol());
			}			
		}	
		return roles;
	}

	// protected region metodos adicionales end
	
}
