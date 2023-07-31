package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorServicio;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IServicioFacade;
import com.ccb.simasc.transversal.dto.InformacionFiltroDTO;
import com.ccb.simasc.transversal.dto.ServicioDTO;
import com.ccb.simasc.transversal.entidades.Servicio;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Servicio}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ServicioFacade extends AccesoFacade<Servicio, Long, ServicioDTO> implements IServicioFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorServicio manejadorServicio;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorServicio;
	}

	@Override
	public ServicioDTO transformarSinDependencias(Servicio obj) {
		ServicioDTO dto = new ServicioDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ServicioDTO transformarConDependencias(Servicio obj) {
		ServicioDTO dto = new ServicioDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Servicio transformarEntidadConDependencias(Servicio obj) {
		return new ServicioDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Servicio transformarEntidadSinDependencias(Servicio obj) {
		return new ServicioDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	public List<Servicio> consultarServicios(){
		return manejadorServicio.consultar(null,null,null);
	}
	
	@Override
	public List<Servicio> consultarServiciosFiltros(List<InformacionFiltroDTO> filtrosParametro) {

		List<Servicio> retorno = new ArrayList<Servicio>();
		if(filtrosParametro != null && validarFiltros(filtrosParametro)){
			List<InformacionFiltro> filtros = InformacionFiltro.traducirFiltros(filtrosParametro);
			List<Servicio> lstServicios = new ArrayList<>();
			retorno = (List<Servicio>) transformarEntidadesColeccionSinDependencias(manejadorServicio.consultar(filtros, null, null),lstServicios);
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
			if(!Servicio.contieneAtributo(filtro.getCampo())) 
				return false;
		}
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IServicioFacade#getServicioPorNombre(java.lang.String)
	 */
	@Override
	public Servicio getServicioPorNombre(String nombre){
		Servicio servicioRetorno;
		List<InformacionFiltro> filtros = new ArrayList<InformacionFiltro>();
		InformacionFiltro f = new InformacionFiltro(TipoFiltro.EXACTO,Servicio.ENTIDAD_SERVICIO_NOMBRE ,nombre);
		filtros.add(f);
		servicioRetorno = manejadorServicio.consultar(filtros, null, null).get(0);
		return servicioRetorno;
	}
	
	/*
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IServicioFacade#consultarServiciosPorTipo(java.lang.String)
	 */
	@Override
	public List<ServicioDTO> consultarServiciosPorTipo( String tipo ){
		return (List<ServicioDTO>) transformarColeccionSinDependencias( manejadorServicio.consultarServiciosPorTipo(tipo), new ArrayList<ServicioDTO>() );
	}
	
	/*
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IServicioFacade#consultarServicioDelCaso(java.lang.Long)
	 */
	@Override
	public ServicioDTO consultarServicioDelCaso( Long idCaso ){
		return transformarSinDependencias( manejadorServicio.consultarServicioDelCaso(idCaso) );
	}
	
	/*public List<Servicio> consultarServiciosPorTipo(String tipoServicio){
		List<Servicio> listaServicios = consultarServicios();
		List<Servicio> serviciosPorTipo = new ArrayList<>();
		for (Servicio servicio : listaServicios) {
			if (servicio.getTipo().equalsIgnoreCase(tipoServicio)) {
				serviciosPorTipo.add(servicio);
			}
		}
		return serviciosPorTipo;
	}*/

	public Servicio consultarServicioPorIdServicio(Long idServicio){
		return manejadorServicio.buscar(idServicio);
	}

	@Override
	public List<ServicioDTO> consultarServiciosPorPersona(long idPersona) {		
		return manejadorServicio.consultarServiciosPorPersona(idPersona);
	}
	
	@Override
	public List<ServicioDTO> consultarServiciosParaTarifador() {		
		return manejadorServicio.consultarServiciosConTarifador();
	}

	@Override
	public List<ServicioDTO> consultarServiciosPorPersonaMateria(long idPersona) {
		return manejadorServicio.consultarServiciosPorPersonaMateria(idPersona);
	}
	
	
	
	// protected region metodos adicionales end
	
}
