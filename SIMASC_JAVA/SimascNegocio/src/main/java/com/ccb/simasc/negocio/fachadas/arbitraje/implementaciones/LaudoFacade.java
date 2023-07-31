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
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorLaudo;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ILaudoFacade;
import com.ccb.simasc.transversal.dto.LaudoDTO;
import com.ccb.simasc.transversal.dto.RecursoLaudoDTO;
import com.ccb.simasc.transversal.dto.formularios.DetalleLaudoDTO;
import com.ccb.simasc.transversal.entidades.Evento;
import com.ccb.simasc.transversal.entidades.Laudo;
import com.ccb.simasc.transversal.entidades.RecursoLaudo;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Laudo}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class LaudoFacade extends AccesoFacade<Laudo, Long, LaudoDTO> implements ILaudoFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorLaudo manejadorLaudo;
	
	@EJB 
	private IEventoFacade eventoFacade;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorLaudo;
	}

	@Override
	public LaudoDTO transformarSinDependencias(Laudo obj) {
		LaudoDTO dto = new LaudoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public LaudoDTO transformarConDependencias(Laudo obj) {
		LaudoDTO dto = new LaudoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Laudo transformarEntidadConDependencias(Laudo obj) {
		Laudo dto = new Laudo();
		dto = new LaudoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Laudo transformarEntidadSinDependencias(Laudo obj) {
		Laudo dto = new Laudo();
		dto = new LaudoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}


	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	@Override
	public Boolean consultarExistenciaLaudo(Long idCaso) {
		InformacionFiltro f = new InformacionFiltro(TipoFiltro.EXACTO, Laudo.ENTIDAD_LAUDO_ID_CASO, idCaso); 
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(f);
		List<Laudo> laudo= manejadorLaudo.consultar(filtros, null, null);
		Boolean isLaudo= false;
		if(laudo.size()>0){
			isLaudo = true;
		}
		return isLaudo;
	}
	
	@Override
	public LaudoDTO guardarLaudo(Long idCaso, Laudo laudo) {
		laudo.setIdCaso(idCaso);
		laudo.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		laudo.setFechaUltimaModificacion(new Date());
		laudo.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		LaudoDTO laudoDTO = transformarSinDependencias(manejadorLaudo.actualizarLaudo(laudo));
		
		Evento evento = new Evento();
		evento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		evento.setFechaEvento(new Date());
		evento.setIdCaso(idCaso);
		evento.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
	    evento.setFechaUltimaModificacion(new Date());
		evento.setTipoEvento(UtilDominios.TIPO_EVENTO_LAUDO);
		StringBuilder observaciones = new StringBuilder();		
		observaciones.append(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO189.toString()))
				.append(UtilConstantes.CARACTER_ESPACIO).append(UtilOperaciones.formatearFechaReporte(laudo.getFecha()));
		evento.setObservaciones(observaciones.toString());
		eventoFacade.crear(evento);

		return laudoDTO;
	}
	
	/**
	 * Metodo que retorna un laudo dado el id del caso
	 * @param idCaso
	 * @return
	 */
	public Laudo consultarLaudo(Long idCaso) {
		Laudo laudo = new Laudo();
		InformacionFiltro f = new InformacionFiltro(TipoFiltro.EXACTO, Laudo.ENTIDAD_LAUDO_ID_CASO, idCaso); 
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(f);
		List<Laudo> laudos= manejadorLaudo.consultar(filtros, null, null);
		laudo = laudos.get(0);
		return laudo;
	}
	
	@Override
	public List<DetalleLaudoDTO> obtenerLaudos(Date fechaIni, Date fechaFin, String arbitros, String partes, 
    		String docParte, Long codigoCaso, Long materia, String estado) {
		
		List<DetalleLaudoDTO> result = new ArrayList<DetalleLaudoDTO>();	
		List<Laudo> laudos = manejadorLaudo.obtenerLaudos(fechaIni, fechaFin, codigoCaso, materia);
		for(Laudo l:laudos){
			boolean agregar = true;
			if(arbitros != null && !arbitros.equals("")){
				agregar = l.getCaso().getParticipanteCaso(UtilDominios.ROL_PERSONA_ARBITRO).trim().toUpperCase().contains(arbitros.trim().toUpperCase());
			}
			
			if(partes != null && !partes.equals("")){
				String nombresPartes  = l.getCaso().getParticipanteCaso(UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
				nombresPartes += l.getCaso().getParticipanteCaso(UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
				nombresPartes += l.getCaso().getParticipanteCaso(UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);
				nombresPartes += l.getCaso().getParticipanteCaso(UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE);
				
				agregar = agregar && nombresPartes.trim().toUpperCase().contains(partes.trim().toUpperCase());
			}
			
			if(docParte != null  && !docParte.equals("")){
				boolean docExiste = false; 
				
				for(RolPersonaCaso rpc : l.getCaso().getRolPersonaCasoList()){
					String rolesPartes = UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO + "," +
							UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE + "," +
							UtilDominios.ROL_PERSONA_PARTE_DEMANDADA +"," +
							UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE;
					if(rolesPartes.contains(rpc.getRol().getNombre())){
						docExiste = docExiste || rpc.getPersona().getNumeroDocumento().trim().equals(docParte.trim());
					}
				}
				agregar = agregar && docExiste;
			}
			
			if(estado != null && !estado.equals("")){
				agregar = agregar && estado.equals(l.getEstado());
			}
			
			if(agregar){
				DetalleLaudoDTO registro = new DetalleLaudoDTO();
				registro.setIdLaudo(l.getIdLaudo());
				registro.setNombreCaso(l.getCaso().getNombre());
				registro.setIdCaso(l.getCaso().getIdCaso());
				registro.setArbitros(l.getCaso().getParticipanteCaso(UtilDominios.ROL_PERSONA_ARBITRO));
				registro.setFecha(l.getFecha());
				registro.setEstado(l.getEstado());
				registro.setIdDocumento(l.getIdDocumento());
				ArrayList<RecursoLaudoDTO> recursos = new ArrayList<>();
				for(RecursoLaudo rl : l.getRecursoLaudoList()){
					RecursoLaudoDTO recurso = new RecursoLaudoDTO();
					recurso.setIdRecursoLaudo(rl.getIdRecursoLaudo());
					recurso.setTipo(rl.getTipo());
					recurso.setFecha(rl.getFecha());
					recurso.setIdDocumento(rl.getIdDocumento());
					recursos.add(recurso);
				}
				
				registro.setRecursos(recursos);
				result.add(registro);
			}
		} 
		return result;
	}
	
	// protected region metodos adicionales end

	
}
