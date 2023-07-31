package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.implementacion.DominioFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorAgendamiento;
import com.ccb.simasc.integracion.manejadores.ManejadorInfraestructuraSala;
import com.ccb.simasc.integracion.manejadores.ManejadorSala;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISalaFacade;
import com.ccb.simasc.transversal.dto.SalaDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltrosSalasDTO;
import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.entidades.InfraestructuraSala;
import com.ccb.simasc.transversal.entidades.Sala;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link Sala}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class SalaFacade extends AccesoFacade<Sala, Long, SalaDTO> implements ISalaFacade {

	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada

	@EJB
	private ManejadorSala manejadorSala;

	@EJB
	private ManejadorAgendamiento manejadorAgendamiento;

	@EJB
	private ManejadorInfraestructuraSala manejadorInfraestructuraSala;

	@EJB
	private DominioFacade dominioFacade;

	@EJB
	private InfraestructuraSalaFacade infraestructuraSalaFacade;

	// contexto de sesion de usuario.
	@Inject
	private ApplicationRequestContext appContext;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorSala;
	}

	@Override
	public SalaDTO transformarSinDependencias(Sala obj) {
		SalaDTO dto = new SalaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public SalaDTO transformarConDependencias(Sala obj) {
		SalaDTO dto = new SalaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Sala transformarEntidadConDependencias(Sala obj) {
		Sala dto = new Sala();
		dto = new SalaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Sala transformarEntidadSinDependencias(Sala obj) {
		Sala dto = new Sala();
		dto = new SalaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	@Override
	public List<GenericoDTO> obtenerSalas() throws SIMASCNegocioExcepcion {
		InformacionFiltro filtro = new InformacionFiltro(TipoFiltro.EXACTO, Sala.ENTIDAD_SALA_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(filtro);
		List<Sala> salas = manejadorSala.consultar(filtros, null, null);
		List<GenericoDTO> dtos = new ArrayList<>();
		for (Sala it : salas) {
			GenericoDTO genericoDTO = new GenericoDTO();
			genericoDTO.setId(it.getIdSala().toString());
			genericoDTO.setNombre(it.getNumeroSala());
			dtos.add(genericoDTO);
		}
		return dtos;
	}

	@Override
	public List<SalaDTO> buscarSalaDisponibles(FiltrosSalasDTO filtrosSalasDTO) throws SimascException {
		List<SalaDTO> lstSalaDTO = new ArrayList<>();
		lstSalaDTO = manejadorSala.buscarSalaDisponibles(filtrosSalasDTO);
		return lstSalaDTO;
	}

	@Override
	public void modificarInfraestructuraSala(List<InfraestructuraSala> salasInfraestructura) throws SimascException  {
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getRolPrincipal() != null && !salasInfraestructura.isEmpty()) {

			String usuarioActual = appContext.getContextoSesion().getNombreUsuario();

			Long idSala = salasInfraestructura.get(0).getInfraestructuraSalaPK().getIdSala();

			InformacionFiltro filtrosInfraSala = new InformacionFiltro(TipoFiltro.EXACTO,
					InfraestructuraSala.ENTIDAD_INFRAESTRUCTURA_SALA_PK_ID_SALA, idSala);
			InformacionFiltro filtrosInfraSalaActivos = new InformacionFiltro(TipoFiltro.EXACTO,
					InfraestructuraSala.ENTIDAD_INFRAESTRUCTURA_SALA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			List<InformacionFiltro> filtros = new ArrayList<>();
			filtros.add(filtrosInfraSala);
			filtros.add(filtrosInfraSalaActivos);

			List<InfraestructuraSala> infraSala = (List<InfraestructuraSala>) manejadorInfraestructuraSala
					.consultar(filtros, null, null);

			for (InfraestructuraSala element : infraSala) {
				element.setFechaUltimaModificacion(new Date());
				element.setIdUsuarioModificacion(usuarioActual);
				element.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				if(!salasInfraestructura.contains(element)){
					element.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
				}
				manejadorInfraestructuraSala.actualizar(element);				
			}
			
			
			for(InfraestructuraSala element : salasInfraestructura){
				if(!infraSala.contains(element)){
					element.setFechaUltimaModificacion(new Date());
					element.setIdUsuarioModificacion(usuarioActual);
					element.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
					manejadorInfraestructuraSala.actualizar(element);
				}else {
					manejadorInfraestructuraSala.crear(element);
				}
			}
		}

	}


	// protected region metodos adicionales end

}
