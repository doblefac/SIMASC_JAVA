package com.ccb.simasc.negocio.transversal;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.utilidades.GestionEventosServicio;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;

/**
 * Clase que gestiona comportamiento común para el desarrollo de los casos de
 * diferentes servicios.
 * 
 * @author fsandoval
 *
 */
@Stateless
@LocalBean
public class GestionCasosFachada {

	@EJB
	private ManejadorCaso manejadorCaso;
	@EJB
	private GestionEventosServicio eventoServicio;

	/**
	 * Autogenera el nombre a un caso dado, este nombre se conforma por las
	 * partes y contrapartes del caso.
	 * 
	 * @param idCaso
	 */
	public void generarNombreCaso(Long idCaso) {

		// define las variables locales
		Caso caso = null;
		String nombreCaso = "";

		// Consulta el caso por ID
		caso = manejadorCaso.buscar(idCaso);

		if (caso == null) {
			eventoServicio.registrarEvento(caso, UtilConstantes.TIPO_EVENTO_ERROR,
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR170.toString()) + idCaso,
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO023.toString()));
			return;
		}

		// Calcula el nombre con base en los datos del caso
		// Si el servicio del caso es conciliacion
		if (caso != null && caso.getServicioMateria().getServicio() != null && caso.getServicioMateria().getServicio()
				.getNombre().equals(UtilConstantes.CODIGO_SERVICIO_ARBITRAJE)) {
			nombreCaso = nombreCaso.concat(obtenerNombresParticipantes(caso, UtilConstantes.CODIGO_PARTICIPANTE_PARTE));
			nombreCaso = nombreCaso.concat(" VS. ");
			nombreCaso = nombreCaso
					.concat(obtenerNombresParticipantes(caso, UtilConstantes.CODIGO_PARTICIPANTE_CONTRAPARTE));
		} else if (caso != null && caso.getServicioMateria().getServicio() != null && caso.getServicioMateria()
				.getServicio().getNombre().equals(UtilConstantes.CODIGO_SERVICIO_CONCILIACION)) {
			nombreCaso = nombreCaso.concat(obtenerNombresParticipantes(caso, UtilConstantes.CODIGO_PARTICIPANTE_PARTE));
			nombreCaso = nombreCaso.concat(", ");
			nombreCaso = nombreCaso
					.concat(obtenerNombresParticipantes(caso, UtilConstantes.CODIGO_PARTICIPANTE_CONTRAPARTE));
		} else {
			// Excepción
			eventoServicio.registrarEvento(caso, UtilConstantes.TIPO_EVENTO_ERROR,
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR171.toString()),
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO153.toString()));
		}

		// Asigna el nombre al caso
		caso.setNombre(nombreCaso);
		// Persiste el caso
		manejadorCaso.actualizar(caso);
		// Genera el evento
		eventoServicio.registrarEvento(caso, UtilConstantes.TIPO_EVENTO_ACCION,
				MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO023.toString()),
				MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO001.toString()));
	}

	/**
	 * Dado un caso retorna el texto necesario para la conformación del nombre
	 * del caso, el cual esta dado por las personas que conforman la parte.
	 * 
	 * @param caso
	 * @param tipo
	 * @return
	 */
	private String obtenerNombresParticipantes(Caso caso, String tipo) {
		String nombres = "";
		int numeroPartes = 0;

		for (RolPersonaCaso p : caso.getRolPersonaCasoList()) {
			if (p.getRol().getNombre().equals(tipo)) {
				numeroPartes++;

				if (numeroPartes == 1) {
					nombres = nombres.concat(
							p.getPersona().getPrimerNombreORazonSocial() + " " + p.getPersona().getSegundoNombre() + " "
									+ p.getPersona().getPrimerApellido() + " " + p.getPersona().getSegundoApellido());
				} else if (numeroPartes == 2) {
					nombres = nombres.concat(" y ");
					nombres = nombres.concat(
							p.getPersona().getPrimerNombreORazonSocial() + " " + p.getPersona().getSegundoNombre() + " "
									+ p.getPersona().getPrimerApellido() + " " + p.getPersona().getSegundoApellido());
				} else if (numeroPartes == 3) {
					nombres = nombres.concat(" y otros ");
				} else {
					return nombres;
				}
			}
		}

		return nombres;
	}
}
