package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.enumeraciones.TipoOrdenamiento;
import com.ccb.simasc.integracion.manejadores.ManejadorZonaGeografica;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionOrdenamiento;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IZonaGeograficaFacade;
import com.ccb.simasc.transversal.dto.ZonaGeograficaDTO;
import com.ccb.simasc.transversal.entidades.ZonaGeografica;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ZonaGeografica}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ZonaGeograficaFacade extends AccesoFacade<ZonaGeografica, String, ZonaGeograficaDTO> implements IZonaGeograficaFacade {
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	private final static Long ID_ZONA_GEOGRAFICA_PAISES = 1L;
	
	@EJB
	private ManejadorZonaGeografica manejadorZonaGeografica;
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorZonaGeografica;
	}

	@Override
	public ZonaGeograficaDTO transformarSinDependencias(ZonaGeografica obj) {
		ZonaGeograficaDTO dto = new ZonaGeograficaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ZonaGeograficaDTO transformarConDependencias(ZonaGeografica obj) {
		ZonaGeograficaDTO dto = new ZonaGeograficaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ZonaGeografica transformarEntidadConDependencias(ZonaGeografica obj) {
		ZonaGeografica dto = new ZonaGeografica();
		dto = new ZonaGeograficaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ZonaGeografica transformarEntidadSinDependencias(ZonaGeografica obj) {
		ZonaGeografica dto = new ZonaGeografica();
		dto = new ZonaGeograficaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	@Override
	public List<ZonaGeografica> getBusquedaPaises() throws SIMASCNegocioExcepcion{
		InformacionFiltro filtro = new InformacionFiltro(TipoFiltro.EXACTO, ZonaGeografica.ENTIDAD_ZONA_GEOGRAFICA_ID_TIPO_ZONA_GEOGRAFICA, ID_ZONA_GEOGRAFICA_PAISES);
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(filtro);
		InformacionOrdenamiento ifoOrden = new InformacionOrdenamiento(TipoOrdenamiento.ASCENDENTE,ZonaGeografica.ENTIDAD_ZONA_GEOGRAFICA_NOMBRE);
		
		List<ZonaGeografica> zonaGeograficas = manejadorZonaGeografica.consultar(filtros, Arrays.asList(ifoOrden), null);
		zonaGeograficas = (List) transformarEntidadesColeccionSinDependencias(zonaGeograficas, new ArrayList<ZonaGeografica>());
		return zonaGeograficas;
	}	

	@Override
	public List<ZonaGeografica> getBusquedaCiudadesXPais(String idPais) throws SIMASCNegocioExcepcion{
		List<ZonaGeografica> zonaGeograficas = manejadorZonaGeografica.obtenerCiudadesPorPais(idPais);
		zonaGeograficas = (List) transformarEntidadesColeccionSinDependencias(zonaGeograficas, new ArrayList<ZonaGeografica>());
		return zonaGeograficas;
	}
	
	@Override
	public ZonaGeografica getBusquedaEntidadPorNombre(String nombre) throws SIMASCNegocioExcepcion{
		InformacionFiltro filtro = new InformacionFiltro(TipoFiltro.LIKE, ZonaGeografica.ENTIDAD_ZONA_GEOGRAFICA_NOMBRE, nombre);
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(filtro);
		List<ZonaGeografica> zonaGeograficas = manejadorZonaGeografica.consultar(filtros, null, null);
		zonaGeograficas = (List) transformarEntidadesColeccionSinDependencias(zonaGeograficas, new ArrayList<ZonaGeografica>());
		return zonaGeograficas.get(0);
	}
	
	@Override
	public List<ZonaGeografica> getBusquedaCiudadesXNacionalidad(String idZona) {
		List<ZonaGeografica> zonaGeograficas = manejadorZonaGeografica.obtenerCiudadesPorNacionalidad(idZona);
		zonaGeograficas = (List) transformarEntidadesColeccionSinDependencias(zonaGeograficas, new ArrayList<ZonaGeografica>());
		List<ZonaGeografica> listCiudades = new ArrayList<>();
		for(ZonaGeografica zona:zonaGeograficas){
			ZonaGeografica ciudad= new ZonaGeografica();
			ciudad.setIdZonaGeografica(zona.getIdZonaGeografica());
			ciudad.setNombre(zona.getNombre());
			listCiudades.add(ciudad);
		}
		return listCiudades;
	}
	
	@Override
	public List<ZonaGeograficaDTO> consultarPaisesPorDepartamento(List<String> idZonasGeograficas){
		
		return manejadorZonaGeografica.consultarNombrePaisPorDepartamento(idZonasGeograficas);
	}
	// protected region metodos adicionales end
	
}
