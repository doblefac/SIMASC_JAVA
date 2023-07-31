package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorContratoConvenio;
import com.ccb.simasc.integracion.manejadores.ManejadorConvenio;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IContratoConvenioFacade;
import com.ccb.simasc.transversal.dto.ContratoConvenioDTO;
import com.ccb.simasc.transversal.entidades.ContratoConvenio;
import com.ccb.simasc.transversal.entidades.Convenio;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ContratoConvenio}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ContratoConvenioFacade extends AccesoFacade<ContratoConvenio, String, ContratoConvenioDTO> implements IContratoConvenioFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorContratoConvenio manejadorContratoConvenio;
	
	@EJB ManejadorConvenio manejadorConvenio;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorContratoConvenio;
	}

	@Override
	public ContratoConvenioDTO transformarSinDependencias(ContratoConvenio obj) {
		ContratoConvenioDTO dto = new ContratoConvenioDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ContratoConvenioDTO transformarConDependencias(ContratoConvenio obj) {
		ContratoConvenioDTO dto = new ContratoConvenioDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ContratoConvenio transformarEntidadConDependencias(ContratoConvenio obj) {
		ContratoConvenio dto = new ContratoConvenio();
		dto = new ContratoConvenioDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ContratoConvenio transformarEntidadSinDependencias(ContratoConvenio obj) {
		ContratoConvenio dto = new ContratoConvenio();
		dto = new ContratoConvenioDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	@Override
	public void crearContratoConvenio(ContratoConvenioDTO contrato, String idPersonaModificacion) {
		
		ContratoConvenioDTO contratoDto = contrato;
		contratoDto.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		contratoDto.setIdUsuarioModificacion(idPersonaModificacion);
		
		ContratoConvenio contratoNuevo = transformarDTOAEntidadSinDependencias(contrato);
		manejadorContratoConvenio.crear(contratoNuevo);
		
		Convenio convenio = manejadorConvenio.buscar(contrato.getIdConvenio());
		convenio.setFechaFinVigencia(contrato.getFechaFin());
		convenio.setFechaUltimaModificacion(new Date());
		
		//Si no se tiene contratos se actualiza fecha inicio de vigencia del convenio
		Long numeroContratos = manejadorContratoConvenio.consultarNumeroContratosPorConvenio(contrato.getIdConvenio());
		if(numeroContratos<2L){
			convenio.setFechaInicioVigencia(contrato.getFechaInicio());		
		}
		manejadorConvenio.actualizar(convenio);
		
	}
	
	@Override
	public List<ContratoConvenioDTO> consultarContratoConvenio(ContratoConvenioDTO contrato){
		
		return  transformarListaDTOSinDependencias(manejadorContratoConvenio.consultarContratoConvenios(contrato));
	}

	
	private ContratoConvenio transformarDTOAEntidadSinDependencias(ContratoConvenioDTO contrato){
		
		ContratoConvenio contratoEntidad = new ContratoConvenio();
		contratoEntidad.setCodigoContrato(contrato.getCodigoContrato());
		contratoEntidad.setIdConvenio(contrato.getIdConvenio());
		contratoEntidad.setFechaInicio(contrato.getFechaInicio());
		contratoEntidad.setFechaFin(contrato.getFechaFin());
		contratoEntidad.setMaximoAudiencias(contrato.getMaximoAudiencias());
		contratoEntidad.setDiasCancelacion(contrato.getDiasCancelacion());
		contratoEntidad.setIncrementoAudienciaAdicional(contrato.getIncrementoAudienciaAdicional());
		contratoEntidad.setIdDocumento(contrato.getIdDocumento());
		contratoEntidad.setIdUsuarioModificacion(contrato.getIdUsuarioModificacion());
		contratoEntidad.setFechaUltimaModificacion(contrato.getFechaUltimaModificacion());
		contratoEntidad.setEstadoRegistro(contrato.getEstadoRegistro());
		
		return contratoEntidad;
		
	}
	
	private List<ContratoConvenioDTO> transformarListaDTOSinDependencias(List<ContratoConvenio> contratos){
		List<ContratoConvenioDTO> contratosSinDependencias = new ArrayList<ContratoConvenioDTO>();
		for (int i = 0; i < contratos.size(); i++) {
		    ContratoConvenioDTO con = transformarDTOSinDependencias(contratos.get(i));
			contratosSinDependencias.add(con);
		}
		return contratosSinDependencias;
		
	}
	
	private ContratoConvenioDTO transformarDTOSinDependencias(ContratoConvenio contrato){
		
		ContratoConvenioDTO contratoDTO = new ContratoConvenioDTO();
		contratoDTO.setCodigoContrato(contrato.getCodigoContrato());
		contratoDTO.setIdConvenio(contrato.getIdConvenio());
		contratoDTO.setFechaInicio(contrato.getFechaInicio());
		contratoDTO.setFechaFin(contrato.getFechaFin());
		contratoDTO.setMaximoAudiencias(contrato.getMaximoAudiencias());
		contratoDTO.setDiasCancelacion(contrato.getDiasCancelacion());
		contratoDTO.setIncrementoAudienciaAdicional(contrato.getIncrementoAudienciaAdicional());
		contratoDTO.setIdDocumento(contrato.getIdDocumento());
		contratoDTO.setIdUsuarioModificacion(contrato.getIdUsuarioModificacion());
		contratoDTO.setFechaUltimaModificacion(contrato.getFechaUltimaModificacion());
		contratoDTO.setEstadoRegistro(contrato.getEstadoRegistro());
		
		return contratoDTO;
	}
	
	@Override
	public void actualizarContratoConvenio(ContratoConvenioDTO contrato, String idUsuario){	
		 ContratoConvenioDTO contratoNuevo = contrato;
		 contratoNuevo.setCodigoContrato(null);
		 List<ContratoConvenio> contratoAntiguo = manejadorContratoConvenio.consultarContratoConvenios(contratoNuevo);
		 if(!contratoAntiguo.isEmpty()){
			 ContratoConvenio contratoAnt = contratoAntiguo.get(0);
			 contratoAnt.setFechaInicio(contratoNuevo.getFechaInicio());
			 contratoAnt.setFechaFin(contratoNuevo.getFechaFin());
			 contratoAnt.setMaximoAudiencias(contratoNuevo.getMaximoAudiencias());
			 contratoAnt.setDiasCancelacion(contratoNuevo.getDiasCancelacion());
			 contratoAnt.setIncrementoAudienciaAdicional(contratoNuevo.getIncrementoAudienciaAdicional());
			 contratoAnt.setIdUsuarioModificacion(idUsuario);
			 contratoAnt.setFechaUltimaModificacion(new Date());
			 manejadorContratoConvenio.actualizar(contratoAnt);
			 
			 Convenio convenio = contratoAnt.getConvenio();
			 convenio.setFechaFinVigencia(contratoAnt.getFechaFin());
			 convenio.setNumeroDeAudienciasRecobro(contratoAnt.getMaximoAudiencias());
			 convenio.setFechaUltimaModificacion(new Date());
			 convenio.setIdUsuarioModificacion(idUsuario);
		 }
		
	}
	
	// protected region metodos adicionales end
	
}
