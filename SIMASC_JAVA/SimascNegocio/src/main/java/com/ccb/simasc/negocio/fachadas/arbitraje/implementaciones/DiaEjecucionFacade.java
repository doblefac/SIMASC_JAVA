package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta seccion sus modificaciones

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorDiaEjecucion;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDiaEjecucionFacade;
import com.ccb.simasc.transversal.dto.DiaEjecucionDTO;
import com.ccb.simasc.transversal.entidades.Alerta;
import com.ccb.simasc.transversal.entidades.DiaEjecucion;
import com.ccb.simasc.transversal.entidades.DiaEjecucionPK;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link DiaEjecucion}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class DiaEjecucionFacade extends AccesoFacade<DiaEjecucion, DiaEjecucionPK, DiaEjecucionDTO>
		implements IDiaEjecucionFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	@EJB
	private ManejadorDiaEjecucion manejadorDiaEjecucion;

	@Inject
	private ApplicationRequestContext appContext;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorDiaEjecucion;
	}

	@Override
	public DiaEjecucionDTO transformarSinDependencias(DiaEjecucion obj) {
		DiaEjecucionDTO dto = new DiaEjecucionDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public DiaEjecucionDTO transformarConDependencias(DiaEjecucion obj) {
		DiaEjecucionDTO dto = new DiaEjecucionDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public DiaEjecucion transformarEntidadConDependencias(DiaEjecucion obj) {
		DiaEjecucion dto = new DiaEjecucion();
		dto = new DiaEjecucionDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public DiaEjecucion transformarEntidadSinDependencias(DiaEjecucion obj) {
		DiaEjecucion dto = new DiaEjecucion();
		dto = new DiaEjecucionDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public void actualizarDiasEjecucion(Alerta alerta, List<String> diaEjecucion) {

		this.manejadorDiaEjecucion.eliminarListaFisicamente(alerta.getDiaEjecucionList());
		if (diaEjecucion != null && !diaEjecucion.isEmpty()) {
			for (String dia : diaEjecucion) {
				this.crearDiasEjecucion(alerta.getIdAlerta(), dia);

			}
		}

	}

	/**
	 * Metodo encaragdo de crear un dia de ejecucion
	 * @param idAlerta
	 * @param dia
	 */
	public void crearDiasEjecucion(Long idAlerta, String dia) {
		String idUsuario = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			idUsuario = appContext.getContextoSesion().getNombreUsuario();
		}
		DiaEjecucion diaEjecucion = new DiaEjecucion();
		diaEjecucion.getDiaEjecucionPK().setDia(dia);
		diaEjecucion.getDiaEjecucionPK().setIdAlerta(idAlerta);
		diaEjecucion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		diaEjecucion.setFechaUltimaModificacion(new Date());
		diaEjecucion.setIdUsuarioModificacion(idUsuario);
		manejadorDiaEjecucion.crear(diaEjecucion);

	}

	// protected region metodos adicionales end

}
