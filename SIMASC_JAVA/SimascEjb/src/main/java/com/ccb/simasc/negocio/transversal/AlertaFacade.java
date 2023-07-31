package com.ccb.simasc.negocio.transversal;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorAlerta;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.transversal.dto.AlertaDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Alerta;
import com.ccb.simasc.transversal.entidades.DiaEjecucion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Alerta}
 * @author sMartinez
 *
 */
@Stateless
public class AlertaFacade extends AccesoFacade<Alerta, Long, AlertaDTO> {
	
	// protected region atributos on begin
	private static final Logger logger = LogManager.getLogger(AlertaFacade.class.getName());
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorAlerta manejadorAlerta;
	

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
		return  new AlertaDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Alerta transformarEntidadSinDependencias(Alerta obj) {
		return new AlertaDTO().transformarEntidadSinDependencias(obj);
	}
	

	/**
	 * retorna la lista de dias en que la alerta se ejecuta
	 * @param alerta
	 * @return
	 */
	private List<String> cargarDiasEjecucion(Alerta alerta){
		
		List<String> diaEjecucion = new ArrayList<String>();
		
		if(alerta.getDiaEjecucionList() != null){
			for (DiaEjecucion diaFor : alerta.getDiaEjecucionList()) {
				if(UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(diaFor.getEstadoRegistro())){
					diaEjecucion.add(diaFor.getDiaEjecucionPK().getDia());					
				}
			}			
		}
		return diaEjecucion;
	}
	

			
	public String reemplazarTextoAlertas(String plantillaHTML, Map<String, String> filtros) {
		String plantilla = plantillaHTML;		

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
		return plantilla;
	}
	
	public List<PersonaBasicaDTO> consultarPersonasNotificar(Long idCaso, Long idAlerta, List<Long> centros, Long idConvenio, List<Long> idPersonas){
		List<PersonaBasicaDTO>  destinatarios = manejadorAlerta.consultarPersonasNotificar(idCaso,idAlerta,centros,idConvenio, idPersonas);
		logger.info("idAlerta:" + idAlerta + " destinatarios " + destinatarios.size());
		return destinatarios;
	}	

	
}
