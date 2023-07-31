package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroDeServicio;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametroDeServicioFacade;
import com.ccb.simasc.transversal.dto.InformacionFiltroDTO;
import com.ccb.simasc.transversal.dto.ParametroDeServicioDTO;
import com.ccb.simasc.transversal.entidades.ParametroDeServicio;
import com.ccb.simasc.transversal.entidades.ParametroDeServicioPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ParametroDeServicio}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ParametroDeServicioFacade extends AccesoFacade<ParametroDeServicio, ParametroDeServicioPK, ParametroDeServicioDTO> implements IParametroDeServicioFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorParametroDeServicio manejadorParametroDeServicio;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorParametroDeServicio;
	}

	@Override
	public ParametroDeServicioDTO transformarSinDependencias(ParametroDeServicio obj) {
		return new ParametroDeServicioDTO().transformarSinDependencias(obj);
	}

	@Override
	public ParametroDeServicioDTO transformarConDependencias(ParametroDeServicio obj) {
		return new ParametroDeServicioDTO().transformarConDependencias(obj);
	}

	@Override
	public ParametroDeServicio transformarEntidadConDependencias(ParametroDeServicio obj) {
		return new ParametroDeServicioDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public ParametroDeServicio transformarEntidadSinDependencias(ParametroDeServicio obj) {
		return new ParametroDeServicioDTO().transformarEntidadSinDependencias(obj);
	}
	
	
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametroDeServicioFacade#consultarParametroDeServicioFiltros(java.util.List)
	 */
	@Override
	public List<ParametroDeServicioDTO> consultarParametroDeServicioFiltros(List<InformacionFiltroDTO> filtrosParametro) {
		List<ParametroDeServicioDTO> retorno = new ArrayList<ParametroDeServicioDTO>();
		if(filtrosParametro != null){
			List<InformacionFiltro> seihe = InformacionFiltro.traducirFiltros(filtrosParametro);
			retorno = (List<ParametroDeServicioDTO>) new ParametroDeServicioDTO().
					transformarColeccionSinDependencias(manejadorParametroDeServicio.consultar(seihe, null, null));
		}
		return retorno;
	}
	
	/**
	 * Metodo que validad si los atributos de los filtros coinciden con los de Convenio
	 * @param filtrosParametro
	 * @return
	 */
	private boolean validarFiltros(List<InformacionFiltroDTO> filtrosParametro){
		if(filtrosParametro.isEmpty()) return true;
		for (InformacionFiltroDTO filtro : filtrosParametro) {
			if(!ParametroDeServicio.contieneAtributo(filtro.getCampo())) 
				return false;
		}
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametroDeServicioFacade#consultarParametroDeServicio(java.util.List, java.lang.Long, java.lang.String)
	 */
	@Override
	public List<ParametroDeServicioDTO> consultarParametroDeServicio(List<String> nombres, Long idServicio,
			String tipoParametro) {
		return (List<ParametroDeServicioDTO>) new ParametroDeServicioDTO().
				transformarColeccionSinDependencias(manejadorParametroDeServicio.consultarParametrosDeServicio(nombres, idServicio, tipoParametro));
	}
	
	@Override
	public List<ParametroDeServicioDTO> consultarParametroDeServicioTipo(String tipo) {
		return (List<ParametroDeServicioDTO>) new ParametroDeServicioDTO().
				transformarColeccionSinDependencias(manejadorParametroDeServicio.consultarParametrosDeServicio(tipo, null));
	}
	
	@Override
	public void actualizarParametrosDeServicio(List<ParametroDeServicioDTO> parametrosDeServicio, String idUsuario) {
	
		for (ParametroDeServicioDTO parametroDeServicioDTO : parametrosDeServicio) {
			
			ParametroDeServicio parametroModificado = manejadorParametroDeServicio.buscar(parametroDeServicioDTO.getParametroDeServicioPK());
			parametroModificado.setFechaUltimaModificacion(new Date());
			parametroModificado.setIdUsuarioModificacion(idUsuario);
			parametroModificado.setValor(parametroDeServicioDTO.getValor());
			manejadorParametroDeServicio.actualizar(parametroModificado);
		}
	}

	@Override
	public ParametroDeServicioDTO consultarParametroDeServicioPorNombre(String nombre) {		
		return (ParametroDeServicioDTO ) new ParametroDeServicioDTO().
				transformarSinDependencias( manejadorParametroDeServicio.consultarParametroDeServicioPorNombre(nombre));
	}
	
	// protected region metodos adicionales end

	
}
