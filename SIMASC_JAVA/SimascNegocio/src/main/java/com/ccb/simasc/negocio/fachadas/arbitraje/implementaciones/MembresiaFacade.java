package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorEstadoPersonaRol;
import com.ccb.simasc.integracion.manejadores.ManejadorHistoricoEstadoPersonaRol;
import com.ccb.simasc.integracion.manejadores.ManejadorMembresia;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IMembresiaFacade;
import com.ccb.simasc.transversal.dto.MembresiaDTO;
import com.ccb.simasc.transversal.entidades.EstadoPersonaRol;
import com.ccb.simasc.transversal.entidades.EstadoPersonaRolPK;
import com.ccb.simasc.transversal.entidades.HistoricoEstadoPersonaRol;
import com.ccb.simasc.transversal.entidades.Membresia;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Membresia}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class MembresiaFacade extends AccesoFacade<Membresia, Long, MembresiaDTO> implements IMembresiaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorMembresia manejadorMembresia;
	
	@EJB
	private ManejadorEstadoPersonaRol manejadorEstadoPersonaRol;
	
	@EJB
	private ManejadorHistoricoEstadoPersonaRol manejadorHistoricoEstadoPersonaRol;
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorMembresia;
	}

	@Override
	public MembresiaDTO transformarSinDependencias(Membresia obj) {
		MembresiaDTO dto = new MembresiaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public MembresiaDTO transformarConDependencias(Membresia obj) {
		MembresiaDTO dto = new MembresiaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Membresia transformarEntidadConDependencias(Membresia obj) {
		Membresia dto = new Membresia();
		dto = new MembresiaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Membresia transformarEntidadSinDependencias(Membresia obj) {
		Membresia dto = new Membresia();
		dto = new MembresiaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	@Override
	public List<MembresiaDTO> consultarMembresiasPersona( Long idPersona ){
		return (List<MembresiaDTO>) new MembresiaDTO()
				.transformarColeccionSinDependencias( manejadorMembresia.consultarMembresiasPersona( idPersona) );
	}
	
	
	@Override
	public void crearMembresia(MembresiaDTO membresia, String idPersonaModificacion){
		
		EstadoPersonaRolPK actualizarConciliador = new EstadoPersonaRolPK(membresia.getIdPersona(), Long.valueOf(UtilConstantes.ID_ROL_CONCILIADOR), UtilConstantes.ID_SERVICIO_CONCILIACION_TRAMITE_ORDINARIO);
		EstadoPersonaRol conciliador = manejadorEstadoPersonaRol.buscar(actualizarConciliador);
		if(conciliador!=null){
			Membresia nuevaMembresia = new Membresia();
			nuevaMembresia.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			nuevaMembresia.setFechaFin(membresia.getFechaFin());
			nuevaMembresia.setFechaInicio(membresia.getFechaInicio());
			nuevaMembresia.setFechaUltimaModificacion(new Date());
			nuevaMembresia.setIdPersona(membresia.getIdPersona());
			nuevaMembresia.setIdUsuarioModificacion(idPersonaModificacion);
			nuevaMembresia.setNumeroReciboPago(membresia.getNumeroReciboPago());
			manejadorMembresia.crear(nuevaMembresia);
			
			
			conciliador.setEstadoRegistro(UtilDominios.ESTADO_CONCILIADOR_ACTIVO);
			conciliador.setFechaUltimaModificacion(new Date());
			conciliador.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			conciliador.setIdUsuarioModificacion(idPersonaModificacion);
			manejadorEstadoPersonaRol.actualizar(conciliador);
			
			HistoricoEstadoPersonaRol historico = new HistoricoEstadoPersonaRol();
			historico.setIdPersona(membresia.getIdPersona());
			historico.setIdServicio(UtilConstantes.ID_SERVICIO_CONCILIACION_TRAMITE_ORDINARIO);
			historico.setIdRol(Long.valueOf(UtilConstantes.ID_ROL_CONCILIADOR));
			historico.setIdMotivo(UtilDominios.ESTADO_CONCILIADOR_ACTIVO);
			historico.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			historico.setFechaUltimaModificacion(new Date());
			historico.setIdUsuarioModificacion(idPersonaModificacion);
			historico.setFecha(new Date());
			manejadorHistoricoEstadoPersonaRol.crear(historico);
		}else{
			throw new SIMASCNegocioExcepcion(
					String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR577.toString())));
		}
		
		
		
	}
	
	
	// protected region metodos adicionales end
	
}
