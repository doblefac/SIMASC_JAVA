package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorHistoricoExpediente;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IHistoricoExpedienteFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.HistoricoExpedienteDTO;
import com.ccb.simasc.transversal.entidades.HistoricoExpediente;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link HistoricoExpediente}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class HistoricoExpedienteFacade extends AccesoFacade<HistoricoExpediente, Long, HistoricoExpedienteDTO> implements IHistoricoExpedienteFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorHistoricoExpediente manejadorHistoricoExpediente;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorHistoricoExpediente;
	}

	@Override
	public HistoricoExpedienteDTO transformarSinDependencias(HistoricoExpediente obj) {
		HistoricoExpedienteDTO dto = new HistoricoExpedienteDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public HistoricoExpedienteDTO transformarConDependencias(HistoricoExpediente obj) {
		HistoricoExpedienteDTO dto = new HistoricoExpedienteDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public HistoricoExpediente transformarEntidadConDependencias(HistoricoExpediente obj) {
		HistoricoExpediente dto = new HistoricoExpediente();
		dto = new HistoricoExpedienteDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public HistoricoExpediente transformarEntidadSinDependencias(HistoricoExpediente obj) {
		HistoricoExpediente dto = new HistoricoExpediente();
		dto = new HistoricoExpedienteDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public List<HistoricoExpedienteDTO> consultarHistoricoExpedientePorCaso(Long idCaso) {
		
		
		return manejadorHistoricoExpediente.consultarHistoricoExpedientePorCaso(idCaso);
	}

	@Override
	public void registrarUbicacionFisicaExpediente(HistoricoExpedienteDTO expediente, String idPersonaModificacion){
		
		
		HistoricoExpediente expedienteFisico = new HistoricoExpediente();
		expedienteFisico.setIdCaso(expediente.getIdCaso());
		expedienteFisico.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		expedienteFisico.setFecha(expediente.getFecha());
		expedienteFisico.setFechaUltimaModificacion(new Date());
		if(expediente.getIdPersona()!=null){
			expedienteFisico.setIdPersona(expediente.getIdPersona());	
		}else{
			expedienteFisico.setIdPersona(null);
		}
		expedienteFisico.setIdUsuarioModificacion(idPersonaModificacion);
		expedienteFisico.setObservaciones(expediente.getObservaciones());
		expedienteFisico.setTipoEntrega(expediente.getTipoEntrega());
		
		manejadorHistoricoExpediente.crear(expedienteFisico);
		
	}
	 
	
	// protected region metodos adicionales end
	
}
