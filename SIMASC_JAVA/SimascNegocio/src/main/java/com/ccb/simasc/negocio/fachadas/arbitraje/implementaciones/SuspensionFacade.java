package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorFechaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorHonorariosFijados;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaSuspension;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorSuspension;
import com.ccb.simasc.integracion.manejadores.ManejadorAlerta;
import com.ccb.simasc.integracion.manejadores.ManejadorProgramacionAlerta;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDiaFestivoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFechaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrosGeneralesFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISuspensionFacade;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.dto.ProgramacionAlertaDTO;
import com.ccb.simasc.transversal.dto.SuspensionDTO;
import com.ccb.simasc.transversal.entidades.Alerta;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.Evento;
import com.ccb.simasc.transversal.entidades.HonorariosFijados;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaSuspension;
import com.ccb.simasc.transversal.entidades.ProgramacionAlerta;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Suspension;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link Suspension}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class SuspensionFacade extends AccesoFacade<Suspension, Long, SuspensionDTO> implements ISuspensionFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	private static final Logger logger = LogManager.getLogger(SuspensionFacade.class.getName());

	@EJB
	private ManejadorFechaCaso manejadorFechaCaso;
	
	@EJB
	private ManejadorCaso manejadorCaso;
	
	@EJB
	private IFechaCasoFacade fechaCasoFacade;
	
	@EJB
	private ManejadorHonorariosFijados manejadorHonorariosFijados;
		
	@EJB
	private IParametrosGeneralesFacade parametrosGeneralesFacade;

	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;
	
	@EJB
	private IEventoFacade eventoFacade;

	@EJB
	private ICorreoElectronicoFacade correoElectronicoFacade;
	
	@EJB
	private IDominioFacade dominioFacade;
	
	@EJB
	private IPersonaFacade personaFacade;
	
	@EJB
	private ICasoFacade casoFacade;
	
	@EJB
	private IDiaFestivoFacade diaFestivoFacade;
	
	@EJB
	private ManejadorSuspension manejadorSuspension;
	

	@EJB
	private ManejadorPersonaSuspension manejadorPersonaSuspension;
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	
	@EJB
	private ManejadorProgramacionAlerta manejadorProgramacionAlerta;

	@EJB
	private ManejadorAlerta manejadorAlerta;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorSuspension;
	}

	@Override
	public SuspensionDTO transformarSinDependencias(Suspension obj) {
		SuspensionDTO dto = new SuspensionDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public SuspensionDTO transformarConDependencias(Suspension obj) {
		SuspensionDTO dto = new SuspensionDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Suspension transformarEntidadConDependencias(Suspension obj) {
		Suspension dto = new Suspension();
		dto = new SuspensionDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Suspension transformarEntidadSinDependencias(Suspension obj) {
		Suspension dto = new Suspension();
		dto = new SuspensionDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * 
	 */
	@Override
	public int obtenerDiasSuspendidos(Long idCaso, String tipo) {
		List<Suspension> suspensiones = (List<Suspension>) manejadorSuspension.consultaSuspensionesPorCasoTipo(idCaso,
				tipo);
		int dias = 0;
		for (Suspension suspension : suspensiones) {
			if (suspension.getFechaFinal() != null)
				dias += UtilOperaciones.calcularDiasEntreFechas(suspension.getFechaInicial(),
						suspension.getFechaFinal());
		}
		return dias;
	}
	
	@Override
	public int obtenerDiasSuspendidosPartes(Long idCaso) {
		List<Suspension> suspensiones = (List<Suspension>) manejadorSuspension.consultaSuspensionesPartesPorCasoTipo(idCaso);
		int dias = 0;
		for (Suspension suspension : suspensiones) {
			if (suspension.getFechaFinal() != null && suspension.getFechaInicial() != null)
				dias += UtilOperaciones.calcularDiasEntreFechas(suspension.getFechaInicial(),
						suspension.getFechaFinal());
		}
		return dias;
	}

	/**
	 * 
	 */
	@Override
	public int obtenerDiasHabilesSuspendidos(Long idCaso, String tipo) {
		List<Suspension> suspensiones = (List<Suspension>) manejadorSuspension.consultaSuspensionesPorCasoTipo(idCaso,
				tipo);
		int dias = 0;
		for (Suspension suspension : suspensiones) {
			if (suspension.getFechaFinal() != null && suspension.getFechaInicial() != null)
				dias += diaFestivoFacade.calcularDiasEntreDosFechas(suspension.getFechaInicial(),
						suspension.getFechaFinal());
		}
		return dias;
	}
	
	@Override
	public int obtenerDiasCalendarioSuspendidos(Long idCaso, String tipo) {
		List<Suspension> suspensiones = (List<Suspension>) manejadorSuspension.consultaSuspensionesPorCasoTipo(idCaso,
				tipo);
		int dias = 0;
		for (Suspension suspension : suspensiones) {
			if (suspension.getFechaFinal() != null && suspension.getFechaInicial() != null)
				dias += UtilOperaciones.calcularDiasEntreFechasLey(suspension.getFechaInicial(),
						suspension.getFechaFinal());
		}
		return dias;
	}
	
	@Override
	public int obtenerDiasHabilesSuspendidosCumplidos(Long idCaso, String tipo) {
		List<Suspension> suspensiones = (List<Suspension>) manejadorSuspension.consultaSuspensionesPorCasoTipo(idCaso,
				tipo);
		int dias = 0;
		for (Suspension suspension : suspensiones) {
			if (suspension.getFechaInicial().after(new Date())) {
				dias += 0;
			} else if (suspension.getFechaInicial().before(new Date())) {
				if (suspension.getFechaFinal() != null && suspension.getFechaFinal().after(new Date())) {
					dias += diaFestivoFacade.obtenerDiasHabilesEntreFechas(suspension.getFechaInicial(), new Date());
				} else if (suspension.getFechaFinal() != null && suspension.getFechaFinal().before(new Date())) {
					dias += diaFestivoFacade.obtenerDiasHabilesEntreFechas(suspension.getFechaInicial(),
							suspension.getFechaFinal());
				} else if (suspension.getFechaFinal() == null
						&& suspension.getTipo().equals(UtilDominios.TIPO_SUSPENSION_INTERRUPCION)) {
					dias += diaFestivoFacade.obtenerDiasHabilesEntreFechas(suspension.getFechaInicial(), new Date());
				}
			} else
				return dias;
		}
		return dias;
	}

	@Override
	public List<Suspension> consultarSuspensionId(Long idCaso) {
		List<Suspension> suspensiones =  new ArrayList<Suspension>();
		suspensiones = manejadorSuspension.consultarSuspensionIdCaso(idCaso);
		return (List<Suspension>) transformarEntidadesColeccionConDependencias(suspensiones, new ArrayList<Suspension>());
	}

	@Override
	public List<HashMap<String, Object>> obtenerSuspensiones(Long idCaso) {
		List<Suspension> suspensiones = (List<Suspension>) manejadorSuspension.consultarSuspensionIdCaso(idCaso);
		List<HashMap<String, Object>> suspensionesMap = new ArrayList<HashMap<String, Object>>();
		for (Suspension suspension : suspensiones) {
			if (UtilDominios.TIPO_SUSPENSION_SUSPENSION_ARBITRAL.equals(suspension.getTipo())
					|| UtilDominios.TIPO_SUSPENSION_INTERRUPCION.equals(suspension.getTipo())
					|| UtilDominios.TIPO_SUSPENSION_PRORROGA.equals(suspension.getTipo())) {
				HashMap<String, Object> suspensionMap = new HashMap<>();
				suspensionMap.put("idSuspension", suspension.getIdSuspension());
				suspensionMap.put("idCaso", suspension.getIdCaso());
				suspensionMap.put("tipo", suspension.getTipo());
				suspensionMap.put("fechaInicial", suspension.getFechaInicial());
				suspensionMap.put("fechaFinal", suspension.getFechaFinal());
				suspensionMap.put("motivo", suspension.getMotivo());
				suspensionMap.put("quienSuspende", suspension.getQuienSuspende());

				if (suspension.getFechaInicial() != null && suspension.getFechaFinal() != null) {
					suspensionMap.put("numeroDias", diaFestivoFacade.
							calcularDiasEntreDosFechas(suspension.getFechaInicial(), suspension.getFechaFinal()));
				}
				
				suspensionMap.put("devolucionExpedienteACac", suspension.getDevolucionExpedienteACac());
				
				if (suspension.getDevolucionExpedienteACac() && UtilDominios.TIPO_SUSPENSION_INTERRUPCION.equals(suspension.getTipo())) {
					List<String> tiposEvento = new ArrayList<String>();
					tiposEvento.add(UtilDominios.TIPO_EVENTO_DEVOLUCION_EXPEDIENTE_REINTEGRACION_TRIBUNAL);
					tiposEvento.add(UtilDominios.TIPO_EVENTO_DEVOLUCION_EXPEDIENTE_CIERRE_CASO);
					List<Evento> eventos = eventoFacade.consultarEventosCasoPorTipoEvento(suspension.getIdCaso(), tiposEvento);
					if (!eventos.isEmpty()) {
						suspensionMap.put("motivoDevolucion", eventos.get(0).getTipoEvento());
					}
				}
				
				suspensionMap.put("estadoRegistro", suspension.getEstadoRegistro());
				suspensionesMap.add(suspensionMap);
			}
		}
		return suspensionesMap;
	}

	@Override
	public void guardarSuspension(SuspensionDTO suspensionDTO) {
		Caso caso = manejadorCaso.buscar(suspensionDTO.getIdCaso());
		Suspension suspension = suspensionDTO.transformarEntidadSinDependencias(suspensionDTO);
		suspension.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		suspension.setFechaUltimaModificacion(new Date());
		boolean devolverExpediente;
		if (suspension.getIdSuspension() == null) {
			devolverExpediente = suspension.getDevolucionExpedienteACac();
			manejadorSuspension.crear(suspension);
		} else {
			Suspension suspAnt = manejadorSuspension.buscar(suspension.getIdSuspension());
			devolverExpediente = suspensionDTO.getDevolucionExpedienteACac() && !suspAnt.getDevolucionExpedienteACac();
			suspension = manejadorSuspension.actualizarSuspension(suspension);
		}

		String tipoEvento = "";
		String observaciones = "";
		int diasHabilesSuspension=0;

		switch (suspension.getTipo()) {
		case UtilDominios.TIPO_SUSPENSION_SUSPENSION_ARBITRAL: 
			
			int diasLimiteSuspension = 0;
			try{
				diasLimiteSuspension = Integer
						.parseInt(parametrosGeneralesFacade.consultarPorCodigo(UtilConstantes.DIAS_LIMITE_DE_SUSPENSIONES_CASO).getValorNumerico().toString());
			}catch(NullPointerException e){
				throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR156.toString()));
			}
			int diasHabilesSuspensiones = obtenerDiasHabilesSuspendidos(suspension.getIdCaso(),
					UtilDominios.TIPO_SUSPENSION_SUSPENSION_ARBITRAL);
			diasHabilesSuspension = diaFestivoFacade.calcularDiasEntreDosFechas(suspension.getFechaInicial(), suspension.getFechaFinal());
			
			if ((diasHabilesSuspensiones) > diasLimiteSuspension) {
				throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR154.toString()));
			}
			
			
			tipoEvento = UtilDominios.TIPO_EVENTO_SUSPENSION_CASO;
			observaciones = "Las partes han suspendido el caso (" + suspension.getIdCaso() + " á " + caso.getNombre() + ") por (" + diasHabilesSuspension + ") días";
			
			correrFechaLimiteParaCierreDeCaso(suspension.getIdCaso(),suspension.getIdUsuarioModificacion());
			recalcularPlazoPagoHonorarios(suspension);
			
			break;

		case UtilDominios.TIPO_SUSPENSION_INTERRUPCION:			
			// Cuando se edita la fecha final se corre la fecha limite de cierre
			if(suspension.getFechaFinal() != null){
				correrFechaLimiteParaCierreDeCaso(suspension.getIdCaso(),suspension.getIdUsuarioModificacion());
				recalcularPlazoPagoHonorarios(suspension);
			}
			
			if(devolverExpediente){
				
				// ******************** GENERACION DE EVENTO ********************
				
				String tipoEventoDev = suspensionDTO.getMotivoDevolucion();
				String desDev = suspensionDTO.getDescripcionDevolucion()!=null?suspensionDTO.getDescripcionDevolucion():suspensionDTO.getMotivo();
				eventoFacade.registrarEvento(suspension.getIdCaso(), tipoEventoDev, desDev, suspension.getIdUsuarioModificacion());
				
				// ******************** CASO PASA A ETAPA PREARBITRAL *************
				
				if(tipoEventoDev.equals(UtilDominios.TIPO_EVENTO_DEVOLUCION_EXPEDIENTE_REINTEGRACION_TRIBUNAL)){
					caso.setEtapa(UtilDominios.ETAPA_CASO_PREARBITRAL);
					casoFacade.actualizar(caso);	
				}
				// ******************** ENVIO DE CORREO AL ABOGADO **************
				List<String> cuerpoCorreo = new ArrayList<>();
				cuerpoCorreo.add(" El caso (" + suspension.getIdCaso() + " - " + caso.getNombre()
						+ ") ha sido interrumpido por el siguiente motivo" + " ("
						+ dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_EVENTO,
								suspensionDTO.getMotivoDevolucion())
						+ ") y el secretario hará devolución del expediente al centro");
				
				List<Persona> abogados = (List<Persona>) personaFacade.consultarPorRolCaso(caso.getIdCaso(), UtilDominios.ROL_PERSONA_ABOGADO);
				if(abogados.isEmpty()){
					throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR151.toString()));
				}
				List<CorreoElectronico> correosAbogado = correoElectronicoFacade.consultaCorreosPersona(abogados.get(0).getIdPersona());
				if(correosAbogado.isEmpty()){
					throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR152.toString()));
				}
				List<CorreoElectronicoDTO> correosAbogadoDTO = (List<CorreoElectronicoDTO>) correoElectronicoFacade.transformarColeccionConDependencias(correosAbogado, new ArrayList<CorreoElectronicoDTO>());
				List<DocumentoDTO> adjuntos = new ArrayList<DocumentoDTO>();
				
				//Parametros
				ParametrosEnvioCorreoDTO parametrosCorreo = new ParametrosEnvioCorreoDTO();
				parametrosCorreo.setAsunto("Devolución del expediente");
				parametrosCorreo.setCuerpoCorreo(cuerpoCorreo);
				parametrosCorreo.setRolPersonaCaso(correosAbogadoDTO);
				parametrosCorreo.setRemitente(correosAbogadoDTO.get(0));
				parametrosCorreo.setIdCaso(caso.getIdCaso());
				parametrosCorreo.setAdjuntos(adjuntos);
				parametrosCorreo.setTipo(UtilDominios.TIPO_PLANTILLAS_CORREO_GENERAL);
				
				try {
					correoRolPersonaCasoFacade.enviarCorreo(parametrosCorreo);
				} catch (SIMASCNegocioExcepcion e) {
					logger.error("Error: ", e);
				}
			}
			
			tipoEvento = UtilDominios.TIPO_EVENTO_INTERRUPCION_CASO;
			observaciones = "Se ha interrumpido el caso por el siguiente motivo: " + suspension.getMotivo() + ".";
			
			break;

		case UtilDominios.TIPO_SUSPENSION_PRORROGA:
			Date fechaLimiteCerrarCaso = fechaCasoFacade.calcularFechaLimiteParaCierreDeCaso(suspension.getIdCaso());
			suspension.setFechaInicial(UtilOperaciones.adicionarDiasFecha(fechaLimiteCerrarCaso,1)); // Se adiciona un dÃ­a ya que la prorroga inicia un dÃ­a despuÃ©s del dÃ­a limite de cierre
			if(fechaLimiteCerrarCaso.after(suspension.getFechaFinal())){
				throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR157.toString()));
			}
			correrFechaLimiteParaCierreDeCaso( suspension.getIdCaso(), suspension.getIdUsuarioModificacion());
			tipoEvento = UtilDominios.TIPO_EVENTO_PRORROGA_CASO;		
			observaciones = " Las partes han prorrogado el término para proferir laudo del caso"
					+ " (" + suspension.getIdCaso() + " â " + caso.getNombre() + ") hasta (" + UtilOperaciones.formatearFechaReporte(suspension.getFechaFinal()) + ")";
			break;
		}

		// Actualiza la ruta del caso
		eventoFacade.registrarEvento(suspension.getIdCaso(), tipoEvento, observaciones, suspension.getIdUsuarioModificacion());
	}
	
	
	public void correrFechaLimiteParaCierreDeCaso(Long idCaso, String idUsuario){ 
		Date nuevaFecha;
		nuevaFecha = fechaCasoFacade.calcularFechaLimiteParaCierreDeCaso(idCaso);
		fechaCasoFacade.registrarFechaCaso(nuevaFecha, UtilDominios.ESTADO_TIPO_FECHA_CASO_LIMITE_CIERRE, idCaso, idUsuario);					
	}
	
	private void recalcularPlazoPagoHonorarios(Suspension suspension){
		HonorariosFijados honorario = manejadorHonorariosFijados.consultarPorIdCaso(suspension.getIdCaso());
		
		if(honorario != null){
			if(suspension.getFechaInicial().after(honorario.getFechaFijacion()) && (suspension.getFechaInicial().compareTo(honorario.getFechaLimiteDePago()) == 0 || suspension.getFechaInicial().compareTo(honorario.getFechaLimiteDePago()) == -1)){
				int diasHabiles = diaFestivoFacade.obtenerDiasHabilesEntreFechas(suspension.getFechaInicial(), suspension.getFechaFinal());
				honorario.setFechaLimiteDePago(diaFestivoFacade.adicionarDiasHabilesFecha(honorario.getFechaLimiteDePago(), diasHabiles));
				manejadorHonorariosFijados.actualizar(honorario);
			}
		}
		
			
	}
	

	@Override  
	public int obtenerDiasHabiles(Date fechaIni, Date fechaFin){ 
		return diaFestivoFacade.obtenerDiasHabilesEntreFechas(fechaIni, fechaFin);
	}
	

	@Override
	public Date adicionarDiasHabilesFecha(Date fecha, int dias){ 
		return diaFestivoFacade.adicionarDiasHabilesFecha(fecha, dias);		
	}
	
	
	@Override
	public void crearSuspensionPreArb(Suspension suspension) throws SIMASCNegocioExcepcion {
		
		List<PersonaSuspension>  listaPersonas = new ArrayList<PersonaSuspension>();
		listaPersonas = suspension.getPersonaSuspensionList();
		Suspension suspensionSinDep = new SuspensionDTO().transformarEntidadSinDependencias(suspension);	
		Date fechaActual = new Date();
		fechaActual = UtilOperaciones.removerHora(fechaActual);
		manejadorSuspension.crear(suspensionSinDep);
		if(suspension.getFechaFinal() != null){			
			if(suspension.getFechaFinal().after(fechaActual) || suspension.getFechaFinal().equals(fechaActual)){
				Alerta alerta = manejadorAlerta.obtenerAlertaPorCodigo(UtilDominios.CODIGO_ALERTA_RE_ACTIVAR_CASO)
						.get(0);
				
				ProgramacionAlerta programacionAlerta = new ProgramacionAlerta();
				programacionAlerta.setEstado(UtilDominios.PROGRAMACION_ALERTA_PENDIENTE);
				programacionAlerta.setIdCaso(suspension.getIdCaso());
				programacionAlerta.setIdPersona(null);
				
				Calendar c =  Calendar.getInstance(); 
				c.setTime(suspension.getFechaFinal());
				c.add(Calendar.DAY_OF_MONTH, 1);
				
				programacionAlerta.setFechaEjecucion(c.getTime());
				programacionAlerta.setIdAlerta(alerta.getIdAlerta());
				manejadorProgramacionAlerta.crear(programacionAlerta);
			}			
		}
		Long idSuspension = suspensionSinDep.getIdSuspension();
			
		for (PersonaSuspension personaSuspensionFor : listaPersonas) {
			RolPersonaCaso rolPersonaFor =
			manejadorRolPersonaCaso.consultaRolPersonaId(personaSuspensionFor.getPersonaSuspensionPK().getIdPersona(),
					personaSuspensionFor.getPersonaSuspensionPK().getIdCaso(), null);
			personaSuspensionFor.getPersonaSuspensionPK().setIdSuspension(idSuspension);
			personaSuspensionFor.getPersonaSuspensionPK().setIdRol(rolPersonaFor.getRolPersonaCasoPK().getIdRol());
			manejadorPersonaSuspension.crear(personaSuspensionFor);
				}
			manejadorSuspension.getEntityManager().detach(suspensionSinDep);
		//llama a la actualizacion del estado del caso.
			casoFacade.actualizarEstdoSuspension(suspension.getIdCaso());			
		}
		

	@Override
	public void actualizarSuspensionPreArb(Suspension suspension) throws SIMASCNegocioExcepcion {
		Alerta alerta = manejadorAlerta.obtenerAlertaPorCodigo(UtilDominios.CODIGO_ALERTA_RE_ACTIVAR_CASO)
				.get(0);
		Date fechaActual = new Date();
		Suspension antiguaSuspension = manejadorSuspension.buscar(suspension.getIdSuspension());
		for (PersonaSuspension personaSuspensionFor : antiguaSuspension.getPersonaSuspensionList()) {		
			personaSuspensionFor.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
			manejadorPersonaSuspension.actualizar(personaSuspensionFor);
		}
		
		List<PersonaSuspension>  listaPersonas = new ArrayList<PersonaSuspension>();
		listaPersonas = suspension.getPersonaSuspensionList();			
		antiguaSuspension.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);		
		Suspension suspensionSinDep = new SuspensionDTO().transformarEntidadSinDependencias(antiguaSuspension);		
		manejadorSuspension.actualizar(suspensionSinDep);		
		
		Suspension nuevaSuspension = new SuspensionDTO().transformarEntidadSinDependencias(suspension);
		nuevaSuspension.setIdSuspension(null);
		manejadorSuspension.crear(nuevaSuspension);
		
		if(nuevaSuspension.getFechaFinal() != null){			
			if(!nuevaSuspension.getFechaFinal().equals(fechaActual)){
				Calendar c =  Calendar.getInstance(); 
				c.setTime(nuevaSuspension.getFechaFinal());
				c.add(Calendar.DAY_OF_MONTH, 1);
				if(antiguaSuspension.getFechaFinal() == null){
					ProgramacionAlerta programacionAlerta = new ProgramacionAlerta();
					programacionAlerta.setEstado(UtilDominios.PROGRAMACION_ALERTA_PENDIENTE);
					programacionAlerta.setIdCaso(nuevaSuspension.getIdCaso());
					programacionAlerta.setIdPersona(null);	
					programacionAlerta.setFechaEjecucion(c.getTime());
					programacionAlerta.setIdAlerta(alerta.getIdAlerta());
					manejadorProgramacionAlerta.crear(programacionAlerta);
				}else{									
					manejadorProgramacionAlerta.modificarAlertaReactivacionCaso(nuevaSuspension.getIdCaso(), antiguaSuspension.getFechaFinal(), c.getTime(), alerta.getIdAlerta());
				}
			}	
		}
					
		for (PersonaSuspension personaSuspensionFor : listaPersonas) {		
			personaSuspensionFor.getPersonaSuspensionPK().setIdSuspension(nuevaSuspension.getIdSuspension());
			RolPersonaCaso rolPersonaFor =
					manejadorRolPersonaCaso.consultaRolPersonaId(personaSuspensionFor.getPersonaSuspensionPK().getIdPersona(),
							personaSuspensionFor.getPersonaSuspensionPK().getIdCaso(), null);
			
			personaSuspensionFor.getPersonaSuspensionPK().setIdRol(rolPersonaFor.getRolPersonaCasoPK().getIdRol());
			
			manejadorPersonaSuspension.crear(personaSuspensionFor);
			
		}
		manejadorSuspension.getEntityManager().detach(suspensionSinDep);
		manejadorSuspension.getEntityManager().detach(nuevaSuspension);

		//llama a la actualizacion del estado del caso.
		casoFacade.actualizarEstdoSuspension(suspension.getIdCaso());

}
	
	
	

	// protected region metodos adicionales end

}
