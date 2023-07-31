package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorAlerta;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroDeServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorPlantillaCarta;
import com.ccb.simasc.integracion.manejadores.ManejadorProgramacionAlerta;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlertaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDiaEjecucionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaRolAlertaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolFacade;
import com.ccb.simasc.transversal.dto.AlertaBasicaDTO;
import com.ccb.simasc.transversal.dto.AlertaDTO;
import com.ccb.simasc.transversal.dto.RolBasicoDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Alerta;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.DiaEjecucion;
import com.ccb.simasc.transversal.entidades.ParametroDeServicio;
import com.ccb.simasc.transversal.entidades.PersonaRolAlerta;
import com.ccb.simasc.transversal.entidades.PlantillaCarta;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link Alerta}
 * 
 * @author sMartinez
 *
 */
@Stateless
public class AlertaFacade extends AccesoFacade<Alerta, Long, AlertaDTO> implements IAlertaFacade {

	// protected region atributos on begin
	
	private static final Logger logger = LogManager.getLogger(AccesoFacade.class.getName());// En esta seccion se deben incluir los atributos de la fachada

	@EJB
	private ManejadorAlerta manejadorAlerta;

	@EJB
	private IRolFacade rolFacade;

	@EJB
	private IPersonaRolAlertaFacade personaRolAlertaFacade;

	@EJB
	private IDiaEjecucionFacade diaEjecucionFacade;

	@EJB
	private ManejadorPlantillaCarta manejadorPlantillaCarta;
	
	@EJB
	private ManejadorProgramacionAlerta manejadorProgramacionAlerta;
	
	@EJB
	private ManejadorParametroDeServicio manejadorParametroDeServicio;

	@Inject
	private ApplicationRequestContext appContext;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorAlerta;
	}

	@Override
	public AlertaDTO transformarSinDependencias(Alerta obj) {
		return new AlertaDTO().transformarSinDependencias(obj);
	}

	@Override
	public AlertaDTO transformarConDependencias(Alerta obj) {
		return new AlertaDTO().transformarConDependencias(obj);
	}

	@Override
	public Alerta transformarEntidadConDependencias(Alerta obj) {
		return new AlertaDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Alerta transformarEntidadSinDependencias(Alerta obj) {
		return new AlertaDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public List<AlertaBasicaDTO> consultarAlertasPorServicioEstado(String estado, String tipoServicio) {

		AlertaDTO filtroAlerta = new AlertaDTO();
		filtroAlerta.setEstado((estado != null && estado.equals("-1")) ? null : estado);
		filtroAlerta.setTipoServicio((tipoServicio != null && tipoServicio.equals("-1")) ? null : tipoServicio);
		List<Alerta> alertas = manejadorAlerta.consultarAlertasPorEstadoTipoServicio(filtroAlerta);
		List<AlertaBasicaDTO> alertaBasicaList = new ArrayList<AlertaBasicaDTO>();
		for (Alerta alertaFor : alertas) {
			AlertaBasicaDTO alertaBasica = new AlertaBasicaDTO();
			alertaBasica.setAlerta(new AlertaDTO().transformarEntidadSinDependencias(alertaFor));
			this.consultaNotificadosAlerta(alertaFor, alertaBasica);
			alertaBasica.setDiaEjecucion(this.cargarDiasEjecucion(alertaFor));
			alertaBasicaList.add(alertaBasica);

		}
		return alertaBasicaList;
	}

	/**
	 * consulta las persona o roles notificados de una alerta, con estado registro
	 * activo
	 * 
	 * @param alerta
	 * @param alertaBasica
	 */
	private void consultaNotificadosAlerta(Alerta alerta, AlertaBasicaDTO alertaBasica) {
		alertaBasica.setPersonas(new ArrayList<PersonaBasicaDTO>());
		alertaBasica.setRoles(new ArrayList<RolBasicoDTO>());
		if (alerta.getPersonaRolAlertaList() != null && !alerta.getPersonaRolAlertaList().isEmpty()) {
			for (PersonaRolAlerta personaRolFor : alerta.getPersonaRolAlertaList()) {
				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(personaRolFor.getEstadoRegistro())) {
					if (personaRolFor.getPersona() != null) {
						alertaBasica.getPersonas()
								.add(PersonaBasicaDTO.newPersonaBasicaDTO(personaRolFor.getPersona()));
					} else if (personaRolFor.getRol() != null) {
						alertaBasica.getRoles().add(rolFacade.convertirRolARolBasico(personaRolFor.getRol()));
					}
				}
			}
		}
	}

	/**
	 * retorna la lista de dias en que la alerta se ejecuta
	 * 
	 * @param alerta
	 * @return
	 */
	private List<String> cargarDiasEjecucion(Alerta alerta) {

		List<String> diaEjecucion = new ArrayList<String>();

		if (alerta.getDiaEjecucionList() != null) {
			for (DiaEjecucion diaFor : alerta.getDiaEjecucionList()) {
				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(diaFor.getEstadoRegistro())) {
					diaEjecucion.add(diaFor.getDiaEjecucionPK().getDia());
				}
			}
		}
		return diaEjecucion;
	}

	@Override
	public void actualizarAlerta(AlertaBasicaDTO alertaBasica) {
		String idUsuario = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			idUsuario = appContext.getContextoSesion().getNombreUsuario();
		}
		personaRolAlertaFacade.modificarNotificadosPorAlertaBasica(alertaBasica);
		Alerta AlertaConsulta = manejadorAlerta.buscar(alertaBasica.getAlerta().getIdAlerta());
		Alerta alerta = alertaBasica.getAlerta();
		diaEjecucionFacade.actualizarDiasEjecucion(AlertaConsulta, alertaBasica.getDiaEjecucion());
		alerta.setFechaUltimaModificacion(new Date());
		alerta.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		alerta.setIdUsuarioModificacion(idUsuario);
		manejadorAlerta.actualizar(alerta);
	}

	@Override
	public String reemplazarTextoAlertas(String plantillaHTML, Map<String, String> filtros) {

		List<PlantillaCarta> plantillaCartaList = manejadorPlantillaCarta
				.consultaPlantillaPorTipoServicio(UtilConstantes.TIPO_SERVICIO_ANY);

		PlantillaCarta plantillaCartaHeader = null;
		PlantillaCarta plantillaCartaFooter = null;
		
		for(PlantillaCarta plantillaCarta : plantillaCartaList) {
			if(plantillaCarta.getNombre().equals(UtilConstantes.HEADER_MAIL)) {
				plantillaCartaHeader = plantillaCarta;
			}else if(plantillaCarta.getNombre().equals(UtilConstantes.FOOTER_MAIL)) {
				plantillaCartaFooter = plantillaCarta;
			}
		}
				
		
		String plantilla = plantillaCartaHeader != null ? plantillaCartaHeader.getPlantillaHtml(): "";
				
		plantilla += plantillaHTML;

		Iterator<Entry<String, String>> entries;
		Boolean hasBeenReplaced = true;
		Double currentIterations = 0d;
		while (hasBeenReplaced && currentIterations < UtilConstantes.MAXIMO_NUMERO_ITERACIONES) {
			hasBeenReplaced = false;
			currentIterations++;
			entries = filtros.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = (Map.Entry) entries.next();
				String key = (String) entry.getKey();
				String value = (String) entry.getValue();

				if (plantilla.contains(key)) {
					plantilla = plantilla.replace(key, (value == null ? UtilConstantes.CADENA_VACIA : value));
					hasBeenReplaced = true;
				}
			}
		}
					
		plantilla += plantillaCartaFooter != null ? plantillaCartaFooter.getPlantillaHtml() : "";
		
		return plantilla;
	}

	@Override
	public List<PersonaBasicaDTO> consultarPersonasNotificar(Long idCaso, Long idAlerta, List<Long> centros,
			Long idConvenio, List<Long> idPersonas) {
		List<PersonaBasicaDTO> destinatarios = manejadorAlerta.consultarPersonasNotificar(idCaso, idAlerta, centros,
				idConvenio, idPersonas);
		logger.info("idAlerta:" + idAlerta + " destinatarios " + destinatarios.size());
		return destinatarios;
	}

	@Override
	public void actualizarEjecucionDiaria() {
		manejadorAlerta.actualizarEjecucionDiaria();
	}

	@Override
	public void actualizarAlertaEjecutada(Long idAlerta) {
		this.actualizarAlertaEjecutada(idAlerta, null);
	}

	@Override
	public void actualizarAlertaEjecutada(Long idAlerta, String estado) {
		Alerta alerta = manejadorAlerta.buscar(idAlerta);
		if (estado == null) {
			alerta.setEstadoEjecucion(UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} else {
			alerta.setEstadoEjecucion(estado);
		}
		logger.info("Cambio alerta ejecutada..." + alerta.getCodigo() + ":" + alerta.getEstadoEjecucion());
		alerta.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		alerta.setFechaUltimaModificacion(new Date());
		manejadorAlerta.actualizar(alerta);

	}
	
	@Override
	public List<Alerta> consultaAlertaPorCodigo(String codigo){
		return manejadorAlerta.obtenerAlertaPorCodigo(codigo);
	}
	
	@Override
	public void programaAlertaVencimientoPago(Caso caso, Long idPersona) {
		List<ParametroDeServicio> parametroDeServicioNotificacionVencimientoPago = manejadorParametroDeServicio
				.consultarParametrosDeServicio(UtilDominios.PARAMETRO_DE_SERVICIO_NOTIFICACION_VENCIMIENTO_PAGO,
						caso.getIdServicio());

		if (parametroDeServicioNotificacionVencimientoPago != null
				&& !parametroDeServicioNotificacionVencimientoPago.isEmpty()) {
			manejadorProgramacionAlerta.crearProgramacionAlertaDiasHabiles(caso.getIdCaso(), idPersona,
					UtilDominios.CODIGO_ALERTA_VENCIMIENTO_TERMINO_PAGO,
					Long.valueOf(parametroDeServicioNotificacionVencimientoPago.get(0).getValor()));
		}

	}

	// protected region metodos adicionales end

}
