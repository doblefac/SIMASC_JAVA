package com.ccb.simasc.negocio.arbitraje;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorProgramacionAlerta;
import com.ccb.simasc.integracion.utilidades.UtilOperaciones;
import com.ccb.simasc.negocio.transversal.CorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.transversal.OrquestadorAlertasFacade;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;

@Stateless
@LocalBean
public class EnvioNotificacionFacade {

	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;

	@EJB
	private ManejadorProgramacionAlerta manejadorProgramacionAlerta;

	@EJB
	private OrquestadorAlertasFacade orquestadorAlertasFacade;
	
	@EJB
	private ManejadorPersona manejadorPersona;
	
	@EJB
	private CorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;

	public void notificar(Caso caso, Persona personaMediador, Persona personaDeudor ) {

		manejadorProgramacionAlerta.crearProgramacionesAceptacionConciliacion(caso.getIdCaso(), personaMediador.getIdPersona(),
				UtilDominios.NOMBRAMIENTO_POR_LA_CCB);

		// se envía correo
		enviarCorreoAsignacion(caso, personaMediador, personaDeudor);

		// Notificacion de alerta
		orquestadorAlertasFacade.alertaNOT_NPC(caso.getIdCaso(), personaMediador.getIdPersona(),
				UtilDominios.NOMBRAMIENTO_POR_LA_CCB);

	}

	private void enviarCorreoAsignacion(Caso caso, Persona personaMediador, Persona personaDeudor) {

		String asunto;
		String cuerpo;
		
		ParametrosGenerales urlAccesoSIMASC = manejadorParametrosGenerales
				.buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_URL_ACCESO_SIMASC);
		
		Map<String,Object> mapaVariablesEstaticas = new HashMap();

		asunto = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO607.toString()),
				new Object[] { String.valueOf(caso.getIdCaso()) });
				
		cuerpo = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO606.toString());
								
        mapaVariablesEstaticas.put("ciudadP", "Bogotá");
        mapaVariablesEstaticas.put("dirigido_aP",UtilOperaciones.getNombreCompleto(personaMediador));
        mapaVariablesEstaticas.put("apellidoP",personaMediador.getPrimerApellido());
        mapaVariablesEstaticas.put("convocanteP",UtilOperaciones.getNombreCompleto(personaDeudor));
        mapaVariablesEstaticas.put("directorP",manejadorParametrosGenerales.buscar(UtilParamGenerales.DIRECTOR_CENTRO_CONCILIACION).getValorTexto());
        mapaVariablesEstaticas.put("cargoP",manejadorParametrosGenerales.buscar(UtilParamGenerales.CARGO).getValorTexto());
        mapaVariablesEstaticas.put("tipoDocumentoP",personaDeudor.getTipoDocumento());
        mapaVariablesEstaticas.put("numeroDocumentoP",personaDeudor.getNumeroDocumento());
        
        for (Map.Entry<String, Object> entry : mapaVariablesEstaticas.entrySet()) {
        	cuerpo = cuerpo.replace(entry.getKey(), entry.getValue().toString());
        }

		
		List<Persona> lstPersonas = new ArrayList<Persona>();
		lstPersonas.add(personaMediador);

		List<String> lstCuerpo = new ArrayList<String>();
		lstCuerpo.add(cuerpo);

		correoRolPersonaCasoFacade.envioDeCorreo(asunto, lstPersonas, null, null, lstCuerpo, caso.getIdCaso(), null, null,
				Boolean.FALSE);

	}
	
	

}
