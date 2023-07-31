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
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorCentro;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICentroFacade;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.entidades.Centro;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Centro}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class CentroFacade extends AccesoFacade<Centro, Long, CentroDTO> implements ICentroFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorCentro manejadorCentro;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorCentro;
	}

	@Override
	public CentroDTO transformarSinDependencias(Centro obj) {
		CentroDTO dto = new CentroDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public CentroDTO transformarConDependencias(Centro obj) {
		CentroDTO dto = new CentroDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Centro transformarEntidadConDependencias(Centro obj) {
		return new CentroDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Centro transformarEntidadSinDependencias(Centro obj) {
		return new CentroDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	@Override
	public List<CentroDTO> obtenerCentros(Long idServicio, Long idMateria)  {
		List<Centro> centros = manejadorCentro.obtenerCentros(idServicio, idMateria);
		List<CentroDTO> centroDTOs = new ArrayList<CentroDTO>();
		for(Centro it : centros){
			CentroDTO dto = it.convertirEntidadToCentroDto(it);
			centroDTOs.add(dto);
		}
		return centroDTOs;
	}	

	@Override
	public void actualizarParametrosCentro(List<CentroDTO> parametrosCentro, String idUsuario)  {
		for (CentroDTO centroDTO : parametrosCentro) {
			Centro centroModificado = manejadorCentro.buscar(centroDTO.getIdCentro());
			centroModificado.setDireccion(centroDTO.getDireccion());
			centroModificado.setDirector(centroDTO.getDirector());
			centroModificado.setFechaUltimaModificacion(new Date());
			centroModificado.setNombre(centroDTO.getNombre());
			centroModificado.setResolucion(centroDTO.getResolucion());
			manejadorCentro.actualizar(centroModificado);
		}
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<CentroDTO> obtenerCentros() {
		List<InformacionFiltro> filtros = new ArrayList<InformacionFiltro>();
		InformacionFiltro filtro1 = new InformacionFiltro(TipoFiltro.EXACTO,Centro.ENTIDAD_CENTRO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		filtros.add(filtro1);
		List<Centro> centros = manejadorCentro.consultar(filtros, null,null);
		List<CentroDTO> centrosDTO = (List<CentroDTO>) new CentroDTO().transformarColeccionConDependencias(centros);
		return centrosDTO;
	}	
	
	// protected region metodos adicionales end
	
}
