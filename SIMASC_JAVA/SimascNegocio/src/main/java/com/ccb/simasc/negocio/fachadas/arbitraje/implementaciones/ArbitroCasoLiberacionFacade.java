package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorArbitroCasoLiberacion;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IArbitroCasoLiberacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaFacade;
import com.ccb.simasc.transversal.dto.ArbitroCasoLiberacionDTO;
import com.ccb.simasc.transversal.entidades.ArbitroCasoLiberacion;
import com.ccb.simasc.transversal.entidades.ArbitroCasoLiberacionPK;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

@Stateless
@LocalBean
public class ArbitroCasoLiberacionFacade
		extends AccesoFacade<ArbitroCasoLiberacion, ArbitroCasoLiberacionPK, ArbitroCasoLiberacionDTO>
		implements IArbitroCasoLiberacionFacade {

	@EJB
	ManejadorArbitroCasoLiberacion manejadorArbitroCasoLiberacion;

	@EJB
	ICasoFacade casoFacade;

	@EJB
	IRolPersonaCasoFacade rolPersonaCasoFacade;

	@EJB
	IRolPersonaFacade rolPersonaFacade;

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorArbitroCasoLiberacion;
	}

	@Override
	public ArbitroCasoLiberacionDTO transformarSinDependencias(ArbitroCasoLiberacion obj) {		
		return null;
	}

	@Override
	public ArbitroCasoLiberacion transformarEntidadConDependencias(ArbitroCasoLiberacion obj) {
		return null;
	}

	@Override
	public ArbitroCasoLiberacion transformarEntidadSinDependencias(ArbitroCasoLiberacion obj) {
		return null;
	}

	@Override
	public ArbitroCasoLiberacionDTO transformarConDependencias(ArbitroCasoLiberacion obj) {
		return null;
	}

	@Override
	public ArbitroCasoLiberacion consultaArbitroCasoLiberacion(Long idPersona) {
		return manejadorArbitroCasoLiberacion.buscarArbitroPorId(idPersona);
	}

	@Override
	public void crear(ArbitroCasoLiberacion arbitroCasoLiberacion) {
		manejadorArbitroCasoLiberacion.crear(arbitroCasoLiberacion);
	}

	@Override
	public void habilitaArbitroSorteo(Long idPersona, Long idCaso, String idUsuario) {

		Caso caso = casoFacade.buscar(idCaso);

		if (caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_ARBITRAJE) || caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_ARBITRAJE_ABREVIADO)) {

			RolPersonaCaso rolPersonaCaso = rolPersonaCasoFacade.consultaPersonaAsignadaCaso(idPersona, idCaso);

			if (rolPersonaCaso.getMetodoNombramiento().equals(UtilDominios.METODO_NOMBRAMIENTO_SORTEO)) {

				List<RolPersona> rolPersona = rolPersonaFacade.obtenerRolPersona(rolPersonaCaso.getRol().getIdRol(),
						idPersona);

				boolean esCuantiaMayor = casoFacade.validaCuantiaMayor(caso.getValorPretensiones());

				if (rolPersona != null && !rolPersona.isEmpty() && rolPersona.get(0).getIdLista() != null
						&& rolPersona.get(0).getIdLista().equals(UtilConstantes.LISTA_A)
						&& Boolean.TRUE.equals(esCuantiaMayor)
						&& (caso.getTipoCuantia().equals(UtilDominios.TIPO_CUANTIA_MAYOR) || caso.getTipoCuantia()
								.equals(UtilDominios.TIPO_CUANTIA_ARBITRAJE_ABREVIADO_CON_CUANTIA))) {

					ArbitroCasoLiberacion arbitroCasoLiberacion = consultaArbitroCasoLiberacion(idPersona);
					if (arbitroCasoLiberacion == null) {

						arbitroCasoLiberacion = new ArbitroCasoLiberacion();
						ArbitroCasoLiberacionPK arbitroCasoLiberacionPK = new ArbitroCasoLiberacionPK(idPersona,
								idCaso);

						arbitroCasoLiberacion.setArbitroCasoLiberacionPK(arbitroCasoLiberacionPK);
						arbitroCasoLiberacion.setFechaUltimaModificacion(new Date());
						arbitroCasoLiberacion.setIdUsuarioModificacion(idUsuario);

						crear(arbitroCasoLiberacion);
						rolPersonaCasoFacade.habilitarArbitro(idCaso, idPersona, UtilConstantes.MOTIVO_CAMBIO_ESTADO_ARBITRO_SORTEO);

					}

				}
			}

		}

	}

	@Override
	public void habilitaArbitroSorteoCierreCaso(Long idPersona, Long idCaso, String idUsuario) {

		Caso caso = casoFacade.buscar(idCaso);

		if (caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_ARBITRAJE)) {

			RolPersonaCaso rolPersonaCaso = rolPersonaCasoFacade.consultaPersonaAsignadaCaso(idPersona, idCaso);
			List<RolPersona> rolPersona = rolPersonaFacade.obtenerRolPersona(rolPersonaCaso.getRol().getIdRol(),
					idPersona);

			boolean esCuantiaMayor = casoFacade.validaCuantiaMayor(caso.getValorPretensiones());

			if (rolPersona != null && !rolPersona.isEmpty() && rolPersona.get(0).getIdLista() != null
					&& rolPersona.get(0).getIdLista().equals(UtilConstantes.LISTA_A)
					&& Boolean.TRUE.equals(esCuantiaMayor)
					&& (caso.getTipoCuantia().equals(UtilDominios.TIPO_CUANTIA_MAYOR)
							|| caso.getTipoCuantia().equals(UtilDominios.TIPO_CUANTIA_CONCILIACION_INDETERMINADO))) {

				ArbitroCasoLiberacion arbitroCasoLiberacion = consultaArbitroCasoLiberacion(idPersona);

				if (arbitroCasoLiberacion != null) {
					rolPersonaCasoFacade.cambiarEstadoArbitroSorteo(rolPersonaCaso, caso,
								UtilConstantes.MOTIVO_CAMBIO_ESTADO_ARBITRO_SORTEO,
								UtilDominios.ESTADO_REGISTRO_ACTIVO);					
				}
			}
		}

	}

}
