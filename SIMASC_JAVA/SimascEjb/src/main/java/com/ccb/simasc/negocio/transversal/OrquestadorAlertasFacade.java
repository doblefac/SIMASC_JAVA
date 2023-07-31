package com.ccb.simasc.negocio.transversal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorAlerta;
import com.ccb.simasc.integracion.manejadores.ManejadorAlertasPendientes;
import com.ccb.simasc.integracion.manejadores.ManejadorArchivo;
import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorCartaPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorConvenio;
import com.ccb.simasc.integracion.manejadores.ManejadorDevolucionDocumentoResultado;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorDominio;
import com.ccb.simasc.integracion.manejadores.ManejadorEvento;
import com.ccb.simasc.integracion.manejadores.ManejadorEventoRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorHistoricoEstadoPersonaRol;
import com.ccb.simasc.integracion.manejadores.ManejadorPagoHonorarios;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPeticion;
import com.ccb.simasc.integracion.manejadores.ManejadorProgramacionAlerta;
import com.ccb.simasc.integracion.manejadores.ManejadorPronunciamiento;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorSedeConvenio;
import com.ccb.simasc.integracion.manejadores.ManejadorSuspension;
import com.ccb.simasc.integracion.manejadores.ManejadorTranscripcion;
import com.ccb.simasc.transversal.dto.AlertaDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Alerta;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

@Stateless
@LocalBean
public class OrquestadorAlertasFacade {
	private final String PREFIJOMETODOS = "alerta";
	private final String MENSAJE_SIN_SORTEO = "Actualmente no existen sorteos programados.";

	@EJB
	private ManejadorAlertasPendientes manejadorAlertaPendiente;

	@EJB
	private ManejadorAlerta manejadorAlerta;

	@EJB
	private ManejadorRol manejadorRol;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private AlertaFacade alertaFacade;

	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private ManejadorAudiencia manejadorAudiencia;

	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	private ManejadorProgramacionAlerta manejadorProgramacionAlerta;

	@EJB
	private ManejadorHistoricoEstadoPersonaRol manejadorHistoricoEstadoPersonaRol;

	@EJB
	private ManejadorDominio manejadorDominio;

	@EJB
	private ManejadorSedeConvenio manejadorSedeConvenio;

	@EJB
	private ManejadorConvenio manejadorConvenio;

	@EJB
	private ManejadorCartaPersona manejadorCartaPersona;

	@EJB
	private ManejadorDocumento manejadorDocumento;

	@EJB
	private ManejadorDevolucionDocumentoResultado manejadorDevolucionDocumentoResultado;

	@EJB
	private ManejadorSuspension manejadorSuspension;

	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;

	@EJB
	private ManejadorRolPersona manejadorRolPersona;

	@EJB
	private ManejadorPeticion manejadorPeticion;

	@EJB
	private ManejadorTranscripcion manejadorTranscripcion;

	@EJB
	private ManejadorEventoRolPersonaCaso manejadorEventoRolPersonaCaso;

	@EJB
	private ManejadorPronunciamiento manejadorPronunciamiento;

	@EJB
	private ManejadorEvento manejadorEvento;

	@EJB
	private ManejadorPagoHonorarios manejadorPagoHonorarios;

	@EJB
	private ManejadorArchivo manejadorArchivo;
	
	@EJB
	private CorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;
	
	@EJB
	private NotificacionFacade notificacionFacade;

	/*
	 * -------------------------------------------------------------------------
	 * --------------- --------------- SECCION DE METODOS DE EJECUCION DE ALERTAS
	 * ALERTAS TIPO: AUTOMATICO
	 * -------------------------------------------------------------------------
	 * ---------------
	 *
	 */

	public void alertaNOT_NPC(Long idCaso, Long idPersona, String metodoNombramiento) {
		String codigoAlerta = "NOT_" + metodoNombramiento;
		AlertaDTO filtroAlerta = new AlertaDTO();
		filtroAlerta.setEstado(UtilDominios.ESTADO_ALERTA_ACTIVA);
		filtroAlerta.setCodigo(codigoAlerta);
		filtroAlerta.setTipoAlerta(UtilDominios.TIPO_ALERTA_AUTOMATICA);
		List<Alerta> alertas = manejadorAlerta.consultarAlertasPorEstadoTipoServicio(filtroAlerta);

		if (!alertas.isEmpty()) {
			InfoBasicaAlertasDTO infoAlerta = manejadorCaso.alertaAceptacionConciliador(idCaso, metodoNombramiento);
			if (infoAlerta != null) {
				Map<String, String> filtros = new HashMap<String, String>();
				filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, infoAlerta.getIdCaso().toString());
				filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, infoAlerta.getNombreCaso());
				filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CENTRO, infoAlerta.getNombreCentro());
				filtros.put(UtilConstantes.PARAM_ALERTA_PLAZO_CONTESTACION,
						infoAlerta.getValorParametroNumerico() != null
								? infoAlerta.getValorParametroNumerico().toString()
								: "");
				String textoAlerta = alertaFacade.reemplazarTextoAlertas(alertas.get(0).getTexto(), filtros);
				List<Long> centros = new ArrayList<Long>();
				centros.add(infoAlerta.getIdCentro());
				List<Long> personas = new ArrayList<Long>();
				personas.add(idPersona);

				List<PersonaBasicaDTO> notificados = alertaFacade.consultarPersonasNotificar(null,
						alertas.get(0).getIdAlerta(), centros, null, personas);

				this.envioConNotificacion(notificados, textoAlerta, alertas.get(0));
			}
		}

	}
	
	private void envioConNotificacion(List<PersonaBasicaDTO> notificados, String textoCorreo, Alerta alerta) {
		ParametrosEnvioCorreoDTO parametrosEnvio = null;
		if (!notificados.isEmpty()) {
			parametrosEnvio = new ParametrosEnvioCorreoDTO();
			parametrosEnvio.setAsunto(alerta.getAsunto() != null ? alerta.getAsunto() : "Alerta sin asunto");
			parametrosEnvio.setPersonasEnvio(null);
			List<String> cuerpoCorreo = new ArrayList<String>();
			cuerpoCorreo.add(textoCorreo);
			parametrosEnvio.setCuerpoCorreo(cuerpoCorreo);

			for (PersonaBasicaDTO notificadoFor : notificados) {
				List<PersonaBasicaDTO> personaBasica = new ArrayList<PersonaBasicaDTO>();
				personaBasica.add(notificadoFor);
				parametrosEnvio.setPersonasEnvio(personaBasica);
				Boolean envioCorrecto = correoRolPersonaCasoFacade.enviarCorreoAlerta(parametrosEnvio);

				notificacionFacade.generarLogAlerta(textoCorreo,
						envioCorrecto ? UtilDominios.ESTADO_NOTIFICACION_ENVIADA
								: UtilDominios.ESTADO_NOTIFICACION_NO_ENVIADA,
						alerta.getIdAlerta(), notificadoFor.getIdPersona(), new Date());
			}

		} else {
			notificacionFacade.generarLogAlerta(textoCorreo, UtilDominios.ESTADO_NOTIFICACION_FALLO_SIN_REMITENTE,
					alerta.getIdAlerta(), null, new Date());

		}

	}

}
