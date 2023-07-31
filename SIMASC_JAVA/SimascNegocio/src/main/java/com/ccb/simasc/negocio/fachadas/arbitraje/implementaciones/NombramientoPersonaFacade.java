package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorNombramientoPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.INombramientoPersonaFacade;
import com.ccb.simasc.transversal.dto.NombramientoPersonaDTO;
import com.ccb.simasc.transversal.entidades.NombramientoPersona;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link NombramientoPersona}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class NombramientoPersonaFacade extends AccesoFacade<NombramientoPersona, Long, NombramientoPersonaDTO> implements INombramientoPersonaFacade {
	
	// protected region atributos on begin
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorNombramientoPersona manejadorNombramientoPersona;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorNombramientoPersona;
	}

	@Override
	public NombramientoPersonaDTO transformarSinDependencias(NombramientoPersona obj) {
		NombramientoPersonaDTO dto = new NombramientoPersonaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public NombramientoPersonaDTO transformarConDependencias(NombramientoPersona obj) {
		NombramientoPersonaDTO dto = new NombramientoPersonaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public NombramientoPersona transformarEntidadConDependencias(NombramientoPersona obj) {
		NombramientoPersona dto = new NombramientoPersona();
		dto = new NombramientoPersonaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public NombramientoPersona transformarEntidadSinDependencias(NombramientoPersona obj) {
		NombramientoPersona dto = new NombramientoPersona();
		dto = new NombramientoPersonaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	public int obtenerNumeroArbitrosPactados(Long idCaso,String metodoNombramiento,String tipoDesignacion){
		return manejadorNombramientoPersona.consultarNumeroArbitrosPactados(idCaso, metodoNombramiento, tipoDesignacion,null);
	}
	
	public int obtenerNumeroArbitrosPorNombrar(Long idCaso,String metodoNombramiento,String tipoDesignacion, Long idTercero){
		return manejadorNombramientoPersona.consultarNumeroArbitrosPactados(idCaso, metodoNombramiento, tipoDesignacion,idTercero) - manejadorRolPersonaCaso.consultarNumeroArbitrosNombrados(idCaso, metodoNombramiento, tipoDesignacion,idTercero);
	}

	@Override
	public NombramientoPersona crearNombramiento(NombramientoPersona nPersona) {
		return this.manejadorNombramientoPersona.crearNombramientoPersona(nPersona);
	}
	
	@Override
	public HashMap<String, Object> obtenerMensajePacto(Long idCaso, String metodoNombramiento,Long idTerceroAutoridad) throws SIMASCNegocioExcepcion{
		HashMap<String, Object> detalleNombramiento = new HashMap<>();
		List<NombramientoPersona> nombramientosTipo = new ArrayList<NombramientoPersona>();
		nombramientosTipo = 
		manejadorNombramientoPersona.obtenerNombramientoPorPersonaCaso(idCaso, metodoNombramiento, idTerceroAutoridad);
		if(nombramientosTipo.size() == 0){			
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR221.toString()));
		}
		NombramientoPersona nombramiento = new NombramientoPersonaDTO().transformarEntidadSinDependencias(nombramientosTipo.get(0));
		detalleNombramiento.put("nombramiento", nombramiento);

		Integer principalesPactados = 
				manejadorNombramientoPersona.consultarNumeroArbitrosPactados(idCaso, metodoNombramiento,
						UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL,idTerceroAutoridad);
		Integer SuplentesPactados = 
				manejadorNombramientoPersona.consultarNumeroArbitrosPactados(idCaso, metodoNombramiento,
						UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE,idTerceroAutoridad);
		Integer principalesNombrados =
				manejadorRolPersonaCaso.consultarNumeroArbitrosNombrados(idCaso, metodoNombramiento,
						UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL, idTerceroAutoridad);
		Integer suplentesNombrados =
				manejadorRolPersonaCaso.consultarNumeroArbitrosNombrados(idCaso, metodoNombramiento,
						UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE, idTerceroAutoridad);
		
		
		
		//TODO falta restar los arbitros no nombrados, inclusion d parametro en el mer
		String mensaje = "Se debe nombrar (" + (principalesPactados - principalesNombrados) + ") árbitro(s) Principal(es) y/o ("
				+ (SuplentesPactados-suplentesNombrados) + ") árbitro(s) Suplente(s).";
		detalleNombramiento.put("mensaje", mensaje);
		
		detalleNombramiento.put("numeroPrinciplaes", (principalesPactados - principalesNombrados));
		detalleNombramiento.put("numeroSuplentes", (SuplentesPactados-suplentesNombrados));

	
		return detalleNombramiento;
		
	}

	
	
	// protected region metodos adicionales end
	
}
