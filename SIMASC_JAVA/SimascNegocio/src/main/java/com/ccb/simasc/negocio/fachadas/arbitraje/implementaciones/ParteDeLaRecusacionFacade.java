package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorParteDeLaRecusacion;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParteDeLaRecusacionFacade;
import com.ccb.simasc.transversal.dto.ParteDeLaRecusacionDTO;
import com.ccb.simasc.transversal.entidades.ParteDeLaRecusacion;
import com.ccb.simasc.transversal.entidades.ParteDeLaRecusacionPK;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ParteDeLaRecusacion}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ParteDeLaRecusacionFacade extends AccesoFacade<ParteDeLaRecusacion, ParteDeLaRecusacionPK, ParteDeLaRecusacionDTO> implements IParteDeLaRecusacionFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorParteDeLaRecusacion manejadorParteDeLaRecusacion;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorParteDeLaRecusacion;
	}

	@Override
	public ParteDeLaRecusacionDTO transformarSinDependencias(ParteDeLaRecusacion obj) {
		ParteDeLaRecusacionDTO dto = new ParteDeLaRecusacionDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ParteDeLaRecusacionDTO transformarConDependencias(ParteDeLaRecusacion obj) {
		ParteDeLaRecusacionDTO dto = new ParteDeLaRecusacionDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ParteDeLaRecusacion transformarEntidadConDependencias(ParteDeLaRecusacion obj) {
		ParteDeLaRecusacion dto = new ParteDeLaRecusacion();
		dto = new ParteDeLaRecusacionDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ParteDeLaRecusacion transformarEntidadSinDependencias(ParteDeLaRecusacion obj) {
		ParteDeLaRecusacion dto = new ParteDeLaRecusacion();
		dto = new ParteDeLaRecusacionDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public ParteDeLaRecusacion crearParteDeRecusacion(Long idRolParte, Long idPersonaParte, Long idCasoParte, Long idRecusacion) {
		ParteDeLaRecusacion pr = new ParteDeLaRecusacion();
		ParteDeLaRecusacionPK pk = new ParteDeLaRecusacionPK(idRolParte, idPersonaParte, idCasoParte, idRecusacion);
		pr.setParteDeLaRecusacionPK(pk);
		pr.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		pr.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		pr.setFechaUltimaModificacion(new Date());
		return manejadorParteDeLaRecusacion.crearParteDeRecusacion(pr);						
	}
	
	
	@Override
	public Collection<Persona> consultarPartesRecusacion(Long idRecusacion) {
		return manejadorParteDeLaRecusacion.consultarPartesRecusacion(idRecusacion);						
	}
	
	// protected region metodos adicionales end
	
}
