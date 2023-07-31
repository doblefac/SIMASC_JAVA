package com.ccb.simasc.negocio.arbitraje;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaServicioMateria;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
/*import com.ccb.simasc.integracion.manejadores.ManejadorFuncionarioExterno;
import com.ccb.simasc.integracion.manejadores.ManejadorMediador;*/
import com.ccb.simasc.integracion.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.entidades.Caso;
/*import com.ccb.simasc.transversal.entidades.FuncionarioExterno;
import com.ccb.simasc.transversal.entidades.FuncionarioExternoPK;
import com.ccb.simasc.transversal.entidades.Mediador;*/
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaServicioMateria;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.utilidades.GestionEventosServicio;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

@LocalBean
@Stateless
public class GestionArbitrajeFachada {

	@EJB
	private ManejadorCaso manejadorCaso;
	/*
	 * @EJB private ManejadorMediador manejadorMediador;
	 * 
	 * @EJB private ManejadorFuncionarioExterno manejadorFuncionarioExterno;
	 */
	@EJB
	private GestionEventosServicio eventoServicio;
	@EJB
	private ManejadorPersona manejadorPersona;
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	@EJB
	private ManejadorPersonaServicioMateria manejadorPersonaServicioMateria;

	/**
	 * 
	 * Habilita un arbitro dado, es decir que cambia su estado en la asignación
	 * a un caso dado un motivo especifico.
	 * 
	 * @param idCaso
	 * @param motivo
	 * @param arbitroConsulta
	 */

	public void habilitarArbitro(Long idCaso, String motivo, Long arbitroConsulta) {

		// define las variables locales
		Persona arbitro = null;
		Caso caso;

		// Consulta al funcionario
		arbitro = manejadorPersona.buscar(arbitroConsulta);

		// Si el motivo es un rechazo actualiza el estado del funcionario
		for (RolPersonaCaso m : arbitro.getRolPersonaCasoList()) {
			caso = m.getCaso();
			if (m.getIdSorteo() != null && caso != null && caso.getIdCaso().equals(idCaso)) {

				caso = m.getCaso();
				// Valida el estado del caso, si esta cerrado NO se puede
				// realizar la habilitación del arbitro
				if (UtilConstantes.ESTADO_CASO_CERRADO.equals(caso.getEstadoCaso())) {

					System.err
							.println(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR150.toString()));
					eventoServicio.registrarEvento(caso, UtilConstantes.TIPO_EVENTO_ACCION,
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO152.toString())
									+ arbitro.toString(),
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO153.toString()));

					return;
				}

				// Actualiza el estado del arbitro en el caso
				m.setEstado(UtilConstantes.ESTADO_ARBITRO_RECHAZADO);
				manejadorRolPersonaCaso.actualizar(m);

				PersonaServicioMateria psm = manejadorPersonaServicioMateria
						.consultarPersonaServicioMateriaPorServicioMateriaPersona(caso.getIdServicio(),
								caso.getIdMateria(), m.getPersona().getIdPersona());
				psm.setEstadoParaSorteo(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				// Inserta el evento correspondiente
				eventoServicio.registrarEvento(caso, UtilConstantes.TIPO_EVENTO_ACCION,
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO154.toString())
								+ arbitro.toString() + motivo,
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO153.toString()));
			}
		}
	}
}
// 14087