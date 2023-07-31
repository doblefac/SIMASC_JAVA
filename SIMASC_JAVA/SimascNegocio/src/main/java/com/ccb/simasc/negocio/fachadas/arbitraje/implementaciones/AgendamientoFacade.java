package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;

// protected region imports fachada on begin

// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.implementacion.DominioFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorAgendaPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorAgendamiento;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDisponibilidadPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorInfraestructuraSolicitadaAgendamiento;
import com.ccb.simasc.integracion.manejadores.ManejadorLogisticaSolicitadaAgendamiento;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroDeServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorSala;
import com.ccb.simasc.integracion.manejadores.ManejadorSede;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgendamientoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IConvenioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDiaFestivoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametroDeServicioFacade;
import com.ccb.simasc.transversal.dto.AgendamientoDTO;
import com.ccb.simasc.transversal.dto.ConsultaAgendamientoDTO;
import com.ccb.simasc.transversal.dto.FechasAgendamientoDTO;
import com.ccb.simasc.transversal.dto.FormatoHoraAudienciaDTO;
import com.ccb.simasc.transversal.dto.InfraestructuraSolicitadaAgendamientoDTO;
import com.ccb.simasc.transversal.dto.LogisticaSolicitadaAgendamientoDTO;
import com.ccb.simasc.transversal.dto.ParametroDeServicioDTO;
import com.ccb.simasc.transversal.dto.ParametrosConsultaAgendamientoDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltrosSalasDTO;
import com.ccb.simasc.transversal.entidades.Agendamiento;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Convenio;
import com.ccb.simasc.transversal.entidades.InfraestructuraSolicitadaAgendamiento;
import com.ccb.simasc.transversal.entidades.InfraestructuraSolicitadaAgendamientoPK;
import com.ccb.simasc.transversal.entidades.LogisticaSolicitadaAgendamiento;
import com.ccb.simasc.transversal.entidades.LogisticaSolicitadaAgendamientoPK;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Sala;
import com.ccb.simasc.transversal.entidades.Sede;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;
import com.ccb.simasc.transversal.utilidades.UtilParamServicio;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link Agendamiento}
 * 
 * @author sMartinez
 * 
 */
@Stateless
@LocalBean
public class AgendamientoFacade extends
		AccesoFacade<Agendamiento, Long, AgendamientoDTO> implements
		IAgendamientoFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	@EJB
	private ManejadorAgendamiento manejadorAgendamiento;

	@EJB
	private ManejadorInfraestructuraSolicitadaAgendamiento manejadorInfraestructuraSolicitadaAgendamiento;

	@EJB
	private ManejadorLogisticaSolicitadaAgendamiento manejadorLogisticaSolicitadaAgendamiento;

	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private ManejadorSala manejadorSala;

	@EJB
	private ManejadorRolPersona manejadorRolPersona;

	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	private ManejadorAgendaPersona manejadorAgendaPersona;

	@EJB
	private ManejadorParametroDeServicio manejadorParametroDeServicio;

	@EJB
	private ManejadorDisponibilidadPersona manejadorDisponibilidadPersona;

	@EJB
	private ManejadorSede manejadorSede;

	@EJB
	private DominioFacade dominioFacade;

	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;

	@EJB
	private IParametroDeServicioFacade parametroDeServicioFacade;

	@EJB
	private IConvenioFacade convenioFacade;

	@EJB
	private IDiaFestivoFacade diaFestivoFacade;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorAgendamiento;
	}

	@Override
	public AgendamientoDTO transformarSinDependencias(Agendamiento obj) {
		return new AgendamientoDTO().transformarSinDependencias(obj);
	}

	@Override
	public AgendamientoDTO transformarConDependencias(Agendamiento obj) {
		return new AgendamientoDTO().transformarConDependencias(obj);
	}

	@Override
	public Agendamiento transformarEntidadConDependencias(Agendamiento obj) {
		return new AgendamientoDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Agendamiento transformarEntidadSinDependencias(Agendamiento obj) {
		return new AgendamientoDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public List<AgendamientoDTO> buscarAgendamientoFiltros(
			FiltrosSalasDTO filtrosSalasDTO) throws SIMASCNegocioExcepcion {
		List<Agendamiento> agendamientos = manejadorAgendamiento
				.buscarAgendamientoSalaSede(filtrosSalasDTO);
		List<AgendamientoDTO> agendamientoDTOs = new ArrayList<>();
		for (Agendamiento it1 : agendamientos) {
			List<LogisticaSolicitadaAgendamientoDTO> logisticaDTOs = new ArrayList<>();
			List<InfraestructuraSolicitadaAgendamientoDTO> infraestructuraDTOs = new ArrayList<>();
			AgendamientoDTO dto = new AgendamientoDTO();
			dto.setIdSala(it1.getIdSala());
			dto.setNumeroSala(it1.getSala().getNumeroSala());
			dto.setCantidadDeAsistentes(it1.getSala()
					.getCapacidadDeAsistentes());
			dto.setIdSede(it1.getSala().getIdSede());
			dto.setTipoReunion(it1.getTipoReunion());
			dto.setIdAgendamiento(it1.getIdAgendamiento());
			dto.setResponsable(it1.getResponsable());
			dto.setDescripcion(it1.getDescripcion());
			dto.setNombretipoReunion(dominioFacade.getNombreDominio(
					UtilDominios.DOMINIO_TIPO_REUNION_AGENDAMIENTO,
					it1.getTipoReunion()));
			if (it1.getAudiencia() != null)
				dto.setIdCaso(it1.getAudiencia().getIdCaso());
			dto.setIdCaso(it1.getIdCaso());
			dto.setHoraInicio(it1.getHoraInicio());
			dto.setHoraFin(it1.getHoraFin());
			for (LogisticaSolicitadaAgendamiento it2 : it1
					.getLogisticaSolicitadaAgendamientoList()) {
				if (it2.getLogisticaSolicitadaAgendamientoPK()
						.getIdAgendamiento().compareTo(it1.getIdAgendamiento()) == 0L) {
					LogisticaSolicitadaAgendamientoDTO logisticaDTO = new LogisticaSolicitadaAgendamientoDTO();
					logisticaDTO.setLogisticaSolicitadaAgendamientoPK(it2
							.getLogisticaSolicitadaAgendamientoPK());
					logisticaDTO.setCodigoLogistica(it2.getLogistica()
							.getCodigoLogistica());
					logisticaDTO.setNombreLogistica(it2.getLogistica()
							.getNombre());
					logisticaDTO.setCantidad(it2.getCantidad());
					// logisticaDTO.setLogisticaSolicitadaAgendamientoPK(it2.getLogisticaSolicitadaAgendamientoPK());
					logisticaDTOs.add(logisticaDTO);
				}
				dto.setLstLogisticaSolicitada(logisticaDTOs);
			}

			for (InfraestructuraSolicitadaAgendamiento it2 : it1
					.getInfraestructuraSolicitadaAgendamientoList()) {
				if (it2.getInfraestructuraSolicitadaAgendamientoPK()
						.getIdAgendamiento().compareTo(it1.getIdAgendamiento()) == 0L) {
					InfraestructuraSolicitadaAgendamientoDTO infraestructuraDTO = new InfraestructuraSolicitadaAgendamientoDTO();
					infraestructuraDTO
							.setInfraestructuraSolicitadaAgendamientoPK(it2
									.getInfraestructuraSolicitadaAgendamientoPK());
					infraestructuraDTO.setCodigoInfraestructura(it2
							.getInfraestructura().getCodigoRecurso());
					infraestructuraDTO.setNombreInfraestructura(it2
							.getInfraestructura().getNombre());
					// infraestructuraDTO.setInfraestructuraSolicitadaAgendamientoPK(
					// it2.getInfraestructuraSolicitadaAgendamientoPK());
					infraestructuraDTOs.add(infraestructuraDTO);
				}
				dto.setLstInfraestructuraSolicitada(infraestructuraDTOs);
			}
			agendamientoDTOs.add(dto);
		}
		return agendamientoDTOs;
	}

	/**
	 * 
	 */
	@Override
	public void registrarAgendamiento(AgendamientoDTO agendamientoDTO) {
		if (agendamientoDTO.getIdSala() != null
				&& agendamientoDTO.getIdSala() == -1) {
			correoRolPersonaCasoFacade
					.enviarCorreoSecretariaSalas(agendamientoDTO);
		} else if (!manejadorSala.validarSalaOcupada(
				agendamientoDTO.getIdSala(), agendamientoDTO.getHoraInicio(),
				agendamientoDTO.getHoraFin())) {
			AgendamientoDTO transformador = new AgendamientoDTO();
			Agendamiento agendamiento = new Agendamiento();
			agendamiento.setHoraFin(agendamientoDTO.getHoraFin());
			agendamiento.setHoraInicio(agendamientoDTO.getHoraInicio());
			agendamiento.setResponsable(agendamientoDTO.getResponsable());

			agendamiento.setTipoReunion(agendamientoDTO.getTipoReunion());
			agendamiento.setCantidadDeAsistentes(agendamientoDTO
					.getCantidadDeAsistentes());
			agendamiento.setFechaUltimaModificacion(new Date());
			if (agendamientoDTO.getIdSala() != null)
				agendamiento.setIdSala(agendamientoDTO.getIdSala());
			else
				throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia()
						.getMensajePorKey(MensajesEnum.ERROR089.toString()));
			if (UtilDominios.TIPO_REUNION_AUDIENCIA.equals(agendamientoDTO
					.getTipoReunion())) {
				agendamiento.setIdAudiencia(agendamientoDTO.getIdAudiencia());
				agendamiento.setIdCaso(agendamientoDTO.getIdCaso());
				Caso caso = manejadorCaso.buscar(agendamientoDTO.getIdCaso());
				agendamiento.setCaso(caso);
			}
			if (UtilDominios.TIPO_REUNION_EXHIBICION_DOCUMENTOS
					.equals(agendamientoDTO.getTipoReunion())
					| UtilDominios.TIPO_REUNION_REVISION_EXPEDIENTES
							.equals(agendamientoDTO.getTipoReunion())) {
				agendamiento.setIdCaso(agendamientoDTO.getIdCaso());
				Caso caso = manejadorCaso.buscar(agendamientoDTO.getIdCaso());
				agendamiento.setCaso(caso);
			}
			agendamiento.setDescripcion(agendamientoDTO.getDescripcion());
			agendamiento
					.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			agendamiento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			agendamiento.setFechaUltimaModificacion(new Date());
			manejadorAgendamiento.crearAgendamiento(agendamiento);

			for (InfraestructuraSolicitadaAgendamientoDTO infraestructura : agendamientoDTO
					.getLstInfraestructuraSolicitada()) {

				InfraestructuraSolicitadaAgendamiento infra = new InfraestructuraSolicitadaAgendamiento();
				InfraestructuraSolicitadaAgendamientoPK infraPK = new InfraestructuraSolicitadaAgendamientoPK(
						agendamiento.getIdAgendamiento(),
						infraestructura.getCodigoInfraestructura());
				infra.setAgendamiento(transformador
						.transformarEntidadSinDependencias(agendamiento));
				infra.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
				infra.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				infra.setFechaUltimaModificacion(new Date());
				infra.setInfraestructuraSolicitadaAgendamientoPK(infraPK);
				manejadorInfraestructuraSolicitadaAgendamiento.crear(infra);
			}

			for (LogisticaSolicitadaAgendamientoDTO logistica : agendamientoDTO
					.getLstLogisticaSolicitada()) {
				LogisticaSolicitadaAgendamientoPK logisticaSolicitadaAgendamientoPK = new LogisticaSolicitadaAgendamientoPK(
						agendamiento.getIdAgendamiento(),
						logistica.getCodigoLogistica());
				LogisticaSolicitadaAgendamiento logis = new LogisticaSolicitadaAgendamiento();
				logis.setAgendamiento(transformador
						.transformarEntidadSinDependencias(agendamiento));
				logis.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
				logis.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				logis.setFechaUltimaModificacion(new Date());
				logis.setLogisticaSolicitadaAgendamientoPK(logisticaSolicitadaAgendamientoPK);
				logis.setCantidad(logistica.getCantidad() != null ? logistica
						.getCantidad() : 1);
				manejadorLogisticaSolicitadaAgendamiento.crear(logis);
			}
		} else {
			throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia()
					.getMensajePorKey(MensajesEnum.ERROR092.toString()));
		}

	}

	/**
	 * 
	 */
	@Override
	public void cancelarAgendamiento(Long idAgendamiento) {
		Agendamiento agendamiento = new Agendamiento();
		agendamiento = manejadorAgendamiento.buscar(idAgendamiento);
		List<LogisticaSolicitadaAgendamiento> lstLogistica = new ArrayList<>();
		List<InfraestructuraSolicitadaAgendamiento> lstInfra = new ArrayList<>();
		lstLogistica = manejadorLogisticaSolicitadaAgendamiento
				.buscarPorIdAgendamiento(idAgendamiento);
		for (LogisticaSolicitadaAgendamiento logistica : lstLogistica) {
			logistica.setAgendamiento(agendamiento);
			logistica.setFechaUltimaModificacion(new Date());
			logistica
					.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			logistica.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
			manejadorLogisticaSolicitadaAgendamiento.actualizar(logistica);
		}
		lstInfra = manejadorInfraestructuraSolicitadaAgendamiento
				.buscarPorIdAgendamiento(idAgendamiento);
		for (InfraestructuraSolicitadaAgendamiento infra : lstInfra) {
			infra.setAgendamiento(agendamiento);
			infra.setFechaUltimaModificacion(new Date());
			infra.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			infra.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
			manejadorInfraestructuraSolicitadaAgendamiento.actualizar(infra);
		}

		agendamiento.setFechaUltimaModificacion(new Date());
		agendamiento
				.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		agendamiento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
		manejadorAgendamiento.actualizar(agendamiento);

	}

	@Override
	public void actualizarAgendamiento(AgendamientoDTO agendamientoDTO)
			throws SIMASCNegocioExcepcion {
		Agendamiento agendamiento = manejadorAgendamiento
				.buscar(agendamientoDTO.getIdAgendamiento());

		if (!manejadorSala.validarSalaOcupada(agendamientoDTO.getIdSala(),
				agendamiento.getHoraInicio(), agendamiento.getHoraFin())) {

			agendamiento.setIdSala(agendamientoDTO.getIdSala());
			agendamiento
					.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			agendamiento.setFechaUltimaModificacion(new Date());
			manejadorAgendamiento.crearAgendamiento(agendamiento);

		} else {
			throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia()
					.getMensajePorKey(MensajesEnum.ERROR092.toString()));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgendamientoFacade#
	 * consultarAgendamiento(com.ccb.simasc.transversal.dto.
	 * ConsultaAgendamientoDTO)
	 */
	@Override
	public List<FechasAgendamientoDTO> consultarAgendamiento(
			ConsultaAgendamientoDTO consulta) {
		List<FechasAgendamientoDTO> agendamientoDisponible = null;
		List<ParametroDeServicioDTO> parametros;
		List<String> nombres = new ArrayList<>();
		String duracionAudiencia = null;
		Integer minimoDiasProgramacionAudiencias = null;
		Integer maximoDiasProgramacionAudiencias = null;
		Convenio convenio;
		
		ParametrosConsultaAgendamientoDTO parametrosConsultaAgendamientoDTO = null;

		nombres.add(UtilDominios.PARAMETRO_DE_SERVICIO_HORA_INICIO);
		nombres.add(UtilDominios.PARAMETRO_DE_SERVICIO_HORA_FIN);
		Long idServicio;
		if (consulta.getIdConvenio() != null) {
			idServicio = UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO;
			convenio = convenioFacade.buscar(consulta.getIdConvenio());
			duracionAudiencia = convenio.getDuracionAudiencias();
			if (!consulta.isTurnoUnico()) {
				minimoDiasProgramacionAudiencias = convenio
						.getLimiteInferiorDiasProgramacionAudiencias();
				maximoDiasProgramacionAudiencias = convenio
						.getLimiteSuperiorDiasProgramacionAudiencias();
			} else {
				minimoDiasProgramacionAudiencias = 0;
				maximoDiasProgramacionAudiencias = 1;
			}
		} else {
			
			if(consulta.getIdCaso() != null) {
				idServicio = manejadorCaso.buscar(consulta.getIdCaso()).getIdServicio();
			}else {
				idServicio = UtilConstantes.ID_SERVICIO_CONCILIACION_TRAMITE_ORDINARIO;
			}
			
			nombres.add(UtilDominios.PARAMETRO_DE_SERVICIO_MAX_DIAS_AUDIENCIA);
			nombres.add(UtilDominios.PARAMETRO_DE_SERVICIO_MIN_DIAS_AUDIENCIA);
			nombres.add(UtilDominios.PARAMETRO_DE_SERVICIO_DURACION_DE_AUDIENCIA);
		}
		parametros = parametroDeServicioFacade.consultarParametroDeServicio(
				nombres, idServicio,
				UtilDominios.TIPO_PARAMETRO_PROGRAMACION_AUDIENCIA);
		if (duracionAudiencia == null)
			duracionAudiencia = obtenerParametroPorNombre(parametros,
					UtilDominios.PARAMETRO_DE_SERVICIO_DURACION_DE_AUDIENCIA)
					.getValor();
		if (minimoDiasProgramacionAudiencias == null
				&& maximoDiasProgramacionAudiencias == null) {
			if (!consulta.isTurnoUnico()) {
				Long materiaExpress = null;
				if (consulta.getIdMateria() != null && idServicio != null) {
					materiaExpress = Long
							.valueOf((manejadorParametroDeServicio.consultarParametrosDeServicio(
									Arrays.asList(UtilParamServicio.MATERIA_CASOS_EXPRESS),
									idServicio,
									UtilParamServicio.TIPO_PARAMETRO_MATERIA_EXPRESS))
									.get(0).getValor());
				}
				if (materiaExpress != null
						&& consulta.getIdMateria().equals(materiaExpress)) {
					minimoDiasProgramacionAudiencias = 0;
					maximoDiasProgramacionAudiencias = Integer
							.valueOf((manejadorParametroDeServicio.consultarParametrosDeServicio(
									Arrays.asList(UtilParamServicio.MAX_DIAS_PROGRAMACION_AUDIENCIA),
									idServicio,
									UtilParamServicio.TIPO_PARAMETRO_PROGRAMACION_AUDIENCIA))
									.get(0).getValor());
				} else {
					minimoDiasProgramacionAudiencias = Integer
							.parseInt(obtenerParametroPorNombre(
									parametros,
									UtilDominios.PARAMETRO_DE_SERVICIO_MIN_DIAS_AUDIENCIA)
									.getValor());
					maximoDiasProgramacionAudiencias = Integer
							.parseInt(obtenerParametroPorNombre(
									parametros,
									UtilDominios.PARAMETRO_DE_SERVICIO_MAX_DIAS_AUDIENCIA)
									.getValor());
				}
			} else {
				minimoDiasProgramacionAudiencias = 0;
				maximoDiasProgramacionAudiencias = 1;
			}
		}

		if (!(UtilDominios.DURACION_AUDIENCIA_MEDIA_HORA
				.equals(duracionAudiencia)
				|| UtilDominios.DURACION_AUDIENCIA_UNA_HORA
						.equals(duracionAudiencia) || UtilDominios.DURACION_AUDIENCIA_DOS_HORAS
					.equals(duracionAudiencia))) {

			List<String> args = new ArrayList<>();
			args.add(duracionAudiencia);
			args.add(UtilDominios.DURACION_AUDIENCIA_MEDIA_HORA);
			args.add(UtilDominios.DURACION_AUDIENCIA_UNA_HORA);
			args.add(UtilDominios.DURACION_AUDIENCIA_DOS_HORAS);
			throw new SimascException(String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(
							MensajesEnum.ERROR483.toString()), args.toArray()));
		}

		switch (consulta.getOperacion()) {
		case UtilConstantes.REPARTO:
			parametrosConsultaAgendamientoDTO = new ParametrosConsultaAgendamientoDTO(
					minimoDiasProgramacionAudiencias,
					maximoDiasProgramacionAudiencias,
					consulta.isFiltrarHoras(), duracionAudiencia,
					obtenerParametroPorNombre(parametros,
							UtilDominios.PARAMETRO_DE_SERVICIO_HORA_INICIO)
							.getValor(), obtenerParametroPorNombre(parametros,
							UtilDominios.PARAMETRO_DE_SERVICIO_HORA_FIN)
							.getValor(), null, consulta.getIdCaso(),
					consulta.getIdConciliador(), null);
			agendamientoDisponible = obtenerFechas(parametrosConsultaAgendamientoDTO);
			break;
		case UtilConstantes.OTRAS_AUDIENCIAS:
		case UtilConstantes.PRIMERA_AUDIENCIA:
			parametrosConsultaAgendamientoDTO = new ParametrosConsultaAgendamientoDTO(
					minimoDiasProgramacionAudiencias,
					maximoDiasProgramacionAudiencias, true, duracionAudiencia,
					obtenerParametroPorNombre(parametros,
							UtilDominios.PARAMETRO_DE_SERVICIO_HORA_INICIO)
							.getValor(), obtenerParametroPorNombre(parametros,
							UtilDominios.PARAMETRO_DE_SERVICIO_HORA_FIN)
							.getValor(), consulta.getIdSede(),
					consulta.getIdCaso(), consulta.getIdConciliador(),
					consulta.getFechaAgendamiento());
			agendamientoDisponible = obtenerFechas(parametrosConsultaAgendamientoDTO);
			break;
		default:
			agendamientoDisponible = new ArrayList<>();
		}
		return agendamientoDisponible;
	}

	private ParametroDeServicioDTO obtenerParametroPorNombre(
			List<ParametroDeServicioDTO> parametros, String nombre) {
		ParametroDeServicioDTO parametro = null;
		for (ParametroDeServicioDTO parametroDeServicioDTO : parametros) {
			if (parametroDeServicioDTO.getParametroDeServicioPK().getNombre()
					.equals(nombre)) {
				parametro = parametroDeServicioDTO;
				break;
			}
		}
		return parametro;
	}

	private List<FechasAgendamientoDTO> obtenerFechas(
			ParametrosConsultaAgendamientoDTO parametrosConsultaAgendamientoDTO) {
		List<FechasAgendamientoDTO> agendamientoDisponible = new ArrayList<>();
		Sede sede = manejadorSede.buscar(parametrosConsultaAgendamientoDTO
				.getIdSede());
		Calendar cal = Calendar.getInstance();
		if (parametrosConsultaAgendamientoDTO.getFechaAgendamiento() == null) {
			cal.setTime(new Date());
		} else {
			cal.setTime(parametrosConsultaAgendamientoDTO
					.getFechaAgendamiento());
		}
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date fechaInicial = diaFestivoFacade.adicionarDiasHabilesFecha(
				cal.getTime(),
				parametrosConsultaAgendamientoDTO.getDiasMinimos());
		Date fechaFinal = diaFestivoFacade.adicionarDiasHabilesFecha(
				fechaInicial,
				parametrosConsultaAgendamientoDTO.getDiasMaximos());
		Date fecha = fechaInicial;
		List<RolPersonaCaso> conciliadores = null;
		if (parametrosConsultaAgendamientoDTO.getIdConciliador() == null
				&& parametrosConsultaAgendamientoDTO.getIdCaso() != null) {
			List<String> estados = new ArrayList<>();
			estados.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
			estados.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
			conciliadores = manejadorRolPersonaCaso
					.consultaConciliadoresCasoEstadoNombramiento(
							parametrosConsultaAgendamientoDTO.getIdCaso(),
							estados, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
			if (conciliadores.isEmpty()) {
				throw new SimascException(MensajesSimasc.getInstancia()
						.getMensajePorKey(MensajesEnum.ERROR535.toString()));
			} else {
				parametrosConsultaAgendamientoDTO
						.setIdConciliador(conciliadores.get(0)
								.getRolPersonaCasoPK().getIdPersona());
			}
		}

		do {
			FechasAgendamientoDTO fdto = new FechasAgendamientoDTO();
			fdto.setFecha(fecha);
			List<FormatoHoraAudienciaDTO> horasAudiencia = obtenerHorasParaAudiencia(
					fecha,
					parametrosConsultaAgendamientoDTO.getDuracionAudiencia(),
					parametrosConsultaAgendamientoDTO.getHoraInicial(),
					parametrosConsultaAgendamientoDTO.getHoraFinal());
			if (parametrosConsultaAgendamientoDTO.isFiltrarHoras()
					&& parametrosConsultaAgendamientoDTO.getIdConciliador() != null) {
				List<FormatoHoraAudienciaDTO> horas = filtrarHoras(
						horasAudiencia, sede,
						parametrosConsultaAgendamientoDTO.getIdConciliador(),
						fecha);
				fdto.setHoras(horas);
			} else {
				fdto.setHoras(horasAudiencia);
			}
			agendamientoDisponible.add(fdto);
			fecha = diaFestivoFacade.adicionarDiasHabilesFecha(fecha, 1);
		} while (fecha.before(fechaFinal));

		return agendamientoDisponible;
	}

	/**
	 * Metodo que permite obtener las horas de audiencia para el rango de
	 * fechas.
	 * 
	 * @param fecha
	 *            : Dia de la semana para verificar disponibilidad.
	 * @param duracion
	 *            : Duracion de la audiencia.
	 * @param horaInicial
	 *            : Hora de inicio de la fecha.
	 * @param horaFinal
	 *            : Hora fin de la fecha.
	 * @return List<FormatoHoraAudiencia>.
	 */
	private List<FormatoHoraAudienciaDTO> obtenerHorasParaAudiencia(Date fecha,
			String duracion, String horaInicial, String horaFinal) {
		Calendar inicial = Calendar.getInstance();
		inicial.setTime(fecha);
		inicial.set(Calendar.HOUR_OF_DAY,
				Integer.valueOf(horaInicial.split(":")[0]));
		Calendar ffinal = Calendar.getInstance();
		ffinal.setTime(fecha);
		ffinal.set(Calendar.HOUR_OF_DAY,
				Integer.valueOf(horaFinal.split(":")[0]));
		return UtilOperaciones.horasAudiencia(duracion, inicial.getTime(),
				ffinal.getTime());
	}

	/**
	 * Metodo que permite validar la disponibilidad de conciliador y turno para
	 * cada filtro de horas.
	 * 
	 * @param horasGeneradas
	 *            :
	 * @param idConciliador
	 *            : Identificador del conciliador.
	 * @param idCaso
	 *            : Identificador del caso.
	 * @param fecha
	 *            : dia de la semana para verificar disponibilidad.
	 * @return List<FormatorHoraAudienciaDTO>
	 */
	private List<FormatoHoraAudienciaDTO> filtrarHoras(
			List<FormatoHoraAudienciaDTO> horasGeneradas, Sede sede,
			Long idConciliador, Date fecha) {
		List<FormatoHoraAudienciaDTO> horasFiltradas = new ArrayList<>();
		for (FormatoHoraAudienciaDTO formatoHoraAudienciaDTO : horasGeneradas) {
			boolean disponibilidadConcilidador = validarDisponibilidadConciliador(
					idConciliador,
					fecha,
					formatoHoraAudienciaDTO.getFechaInicio(),
					formatoHoraAudienciaDTO.getFechaFin(),
					sede != null
							&& UtilDominios.SEDE_FISICA.equals(sede
									.getTipoSede()) ? sede.getIdSede() : null);
			boolean disponibilidadTurno = sede != null
					&& UtilDominios.SEDE_FISICA.equals(sede.getTipoSede()) ? verificarDisponibilidadTurno(
					sede.getIdSede(), formatoHoraAudienciaDTO.getFechaInicio(),
					formatoHoraAudienciaDTO.getFechaFin()) : true;
			if (disponibilidadConcilidador && disponibilidadTurno) {
				horasFiltradas.add(formatoHoraAudienciaDTO);
			}
		}
		return horasFiltradas;
	}

	private boolean verificarDisponibilidadTurno(Long idSede, Date horaInicio,
			Date horaFin) {
		return manejadorSala.existenSalasDisponibles(idSede, horaInicio,
				horaFin);
	}

	@Override
	public boolean validarDisponibilidadConciliador(Long idConciliador,
			Date fecha, Date horaInicio, Date horaFin, Long idSede) {
		if (idConciliador == null) {
			throw new SimascException(MensajesSimasc.getInstancia()
					.getMensajePorKey(MensajesEnum.ERROR536.toString()));
		}
		return validarDisponibilidadAgenda(idConciliador, fecha, horaInicio,
				horaFin, idSede);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgendamientoFacade
	 * #validarDisponibilidadAgenda(java.lang.Long, java.util.Date,
	 * java.util.Date, java.util.Date, java.lang.Long)
	 */
	@Override
	public boolean validarDisponibilidadAgenda(Long idConciliador, Date fecha,
			Date horaInicio, Date horaFin, Long idSede) {
		return manejadorDisponibilidadPersona.validarDisponibilidadPersona(
				fecha, horaInicio, horaFin, idSede, idConciliador)
				&& manejadorAgendaPersona.validarDisponibilidadPersona(
						idConciliador, horaInicio, horaFin);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgendamientoFacade
	 * #crearAgendamientoAudienciaConciliacion(java.lang.Long, java.util.Date,
	 * java.util.Date)
	 */
	@Override
	public Agendamiento crearAgendamientoAudienciaConciliacion(Long idCaso,
			Long idAudiencia, Long idSede, Date fechaInicio, Date fechaFin,
			String descripcion) {
		List<Sala> salasDisponibles = manejadorSala.buscarSalasDisponiblesSede(
				idSede, fechaInicio, fechaFin);
		if (salasDisponibles.isEmpty()) {
			throw new SimascException(MensajesSimasc.getInstancia()
					.getMensajePorKey(MensajesEnum.INFO187.toString()));
		}
		Agendamiento agendamientoAudiencia = new Agendamiento();
		agendamientoAudiencia.setIdAudiencia(idAudiencia);
		agendamientoAudiencia.setIdSala(salasDisponibles.get(0).getIdSala());
		agendamientoAudiencia.setHoraInicio(fechaInicio);
		agendamientoAudiencia.setHoraFin(fechaFin);
		agendamientoAudiencia
				.setTipoReunion(UtilDominios.TIPO_REUNION_AUDIENCIA);
		agendamientoAudiencia.setIdCaso(idCaso);
		agendamientoAudiencia
				.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		agendamientoAudiencia.setDescripcion(descripcion);
		manejadorAgendamiento.crear(agendamientoAudiencia);
		return agendamientoAudiencia;
	}

	// protected region metodos adicionales end

}
