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
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorLaudo;
import com.ccb.simasc.integracion.manejadores.ManejadorRecursoLaudo;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ILaudoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRecursoLaudoFacade;
import com.ccb.simasc.transversal.dto.RecursoLaudoDTO;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.Evento;
import com.ccb.simasc.transversal.entidades.Laudo;
import com.ccb.simasc.transversal.entidades.RecursoLaudo;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link RecursoLaudo}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class RecursoLaudoFacade extends AccesoFacade<RecursoLaudo, Long, RecursoLaudoDTO> implements IRecursoLaudoFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorRecursoLaudo manejadorRecursoLaudo;
	
	@EJB 
	private ILaudoFacade laudoFacade;
	
	@EJB 
	private ManejadorLaudo manejadorLaudo; 
	
	@EJB
	private IEventoFacade eventoFacade;
	
	@EJB
	private IDominioFacade dominioFacade;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorRecursoLaudo;
	}

	@Override
	public RecursoLaudoDTO transformarSinDependencias(RecursoLaudo obj) {
		RecursoLaudoDTO dto = new RecursoLaudoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public RecursoLaudoDTO transformarConDependencias(RecursoLaudo obj) {
		RecursoLaudoDTO dto = new RecursoLaudoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public RecursoLaudo transformarEntidadConDependencias(RecursoLaudo obj) {
		RecursoLaudo dto = new RecursoLaudo();
		dto = new RecursoLaudoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public RecursoLaudo transformarEntidadSinDependencias(RecursoLaudo obj) {
		RecursoLaudo dto = new RecursoLaudo();
		dto = new RecursoLaudoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}


	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	@Override
	public RecursoLaudo consultarRecurso(Long idCaso, Long idRecurso) {
		RecursoLaudo recursoConsultado= manejadorRecursoLaudo.buscar(idRecurso);
		RecursoLaudoDTO recursoDTO = new RecursoLaudoDTO();
		return recursoDTO.transformarEntidadSinDependencias(recursoConsultado);
	}

	@Override
	public void actualizarRecurso(RecursoLaudo recursoLaudo, String idModificador) {
		recursoLaudo.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		recursoLaudo.setIdUsuarioModificacion(idModificador);
		recursoLaudo.setFechaUltimaModificacion(new Date());
		manejadorRecursoLaudo.actualizar(recursoLaudo);
		
	}

	@Override
	public void crearRecurso(Long idCaso, RecursoLaudo recursoLaudo) {
		InformacionFiltro f = new InformacionFiltro(TipoFiltro.EXACTO, Laudo.ENTIDAD_LAUDO_ID_CASO, idCaso); 
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(f);
		List<Laudo> laudoList = manejadorLaudo.consultar(filtros, null, null);		
		recursoLaudo.setIdLaudo(laudoList.get(0).getIdLaudo());		
		manejadorRecursoLaudo.crear(recursoLaudo);
		
		Evento evento = new Evento();
		evento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		evento.setFechaEvento(new Date());
		evento.setIdCaso(idCaso);
		evento.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
	    evento.setFechaUltimaModificacion(new Date());
		evento.setTipoEvento(UtilDominios.TIPO_EVENTO_RECURSO_CONTRA_LAUDO);
		String observaciones=MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO188.toString());
		Dominio dominioTipoLaudo = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_RECURSO_LAUDO,recursoLaudo.getTipo());
		Dominio dominioParte = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_PARTE_CONTRAPARTE,recursoLaudo.getParteQueInterpone());
		observaciones = observaciones.replace("tp", dominioParte.getNombre());
		observaciones = observaciones.replace("tr", dominioTipoLaudo.getNombre());
		observaciones = observaciones.replace("tf", recursoLaudo.getFecha().toString());
		evento.setObservaciones(observaciones);
		eventoFacade.crear(evento);
	}

	// protected region metodos adicionales end

	
}
