package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorTagPlantilla;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITagPlantillaFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.PlantillaVariablesCartaEditorDTO;
import com.ccb.simasc.transversal.dto.TagPlantillaDTO;
import com.ccb.simasc.transversal.entidades.PlantillaCarta;
import com.ccb.simasc.transversal.entidades.TagPlantilla;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link TagPlantilla}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class TagPlantillaFacade extends AccesoFacade<TagPlantilla, Long, TagPlantillaDTO> implements ITagPlantillaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorTagPlantilla manejadorTagPlantilla;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorTagPlantilla;
	}

	@Override
	public TagPlantillaDTO transformarSinDependencias(TagPlantilla obj) {
		TagPlantillaDTO dto = new TagPlantillaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public TagPlantillaDTO transformarConDependencias(TagPlantilla obj) {
		TagPlantillaDTO dto = new TagPlantillaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public TagPlantilla transformarEntidadConDependencias(TagPlantilla obj) {
		TagPlantilla dto = new TagPlantilla();
		dto = new TagPlantillaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public TagPlantilla transformarEntidadSinDependencias(TagPlantilla obj) {
		TagPlantilla dto = new TagPlantilla();
		dto = new TagPlantillaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public List<TagPlantilla> obtieneTagPorTipoServicio(String tipoServicio) throws SIMASCNegocioExcepcion {
		
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, TagPlantilla.ENTIDAD_TAG_PLANTILLA_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		
		filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, TagPlantilla.ENTIDAD_TAG_PLANTILLA_TIPO_SERVICIO,
				tipoServicio));
		
		return manejadorTagPlantilla.consultar(filtros, null, null);
		
	}
	
	// protected region metodos adicionales end
	
}
