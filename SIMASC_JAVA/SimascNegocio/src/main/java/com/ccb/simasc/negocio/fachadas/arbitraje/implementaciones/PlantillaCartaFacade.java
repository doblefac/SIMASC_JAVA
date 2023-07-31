package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorDominio;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorPlantillaCarta;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorValorPlantillaCarta;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPlantillaCartaFacade;
import com.ccb.simasc.transversal.dto.PlantillaCartaDTO;
import com.ccb.simasc.transversal.dto.PlantillaVariablesCartaEditorDTO;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.PlantillaCarta;
import com.ccb.simasc.transversal.entidades.ValorPlantillaCarta;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;
import com.ccb.simasc.transversal.utilidades.variablesDinamicasCartas;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link PlantillaCarta}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class PlantillaCartaFacade extends AccesoFacade<PlantillaCarta, Long, PlantillaCartaDTO> implements IPlantillaCartaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
    @Inject
    private ApplicationRequestContext appContext;
	
	@EJB
	private ManejadorPlantillaCarta manejadorPlantillaCarta;
	
	@EJB
	private ManejadorValorPlantillaCarta manejadorValorPlantillaCarta;
	
	@EJB
	private ManejadorDominio manejadorDominio;
	
	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;
	
	@EJB
	private ManejadorRol manejadorRol;
	
	@EJB
	private variablesDinamicasCartas variablesCartas;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorPlantillaCarta;
	}

	@Override
	public PlantillaCartaDTO transformarSinDependencias(PlantillaCarta obj) {
		return new PlantillaCartaDTO().transformarSinDependencias(obj);
	}

	@Override
	public PlantillaCartaDTO transformarConDependencias(PlantillaCarta obj) {
		return new PlantillaCartaDTO().transformarConDependencias(obj);
	}

	@Override
	public PlantillaCarta transformarEntidadConDependencias(PlantillaCarta obj) {
		return new PlantillaCartaDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public PlantillaCarta transformarEntidadSinDependencias(PlantillaCarta obj) {
		return new PlantillaCartaDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPlantillaCartaFacade#obtenerPlantillasCartas()
	 */
	@Override
	public List<PlantillaCarta> obtenerPlantillasCartas() {
		
		List<InformacionFiltro> filtros = new ArrayList<>();
		
		// Establecer filtro para consultar plantillas parametrizadas
		filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		
		// -- Validar Roles asociados para carta PCADP -- 
		
		// Obtener Nombre Rol de usuario en sesión
		String idRolUsuario = appContext.getContextoSesion().getRolPrincipal();
		String nombreRolUsuario = manejadorRol.consultarNombreRolPorId(Long.parseLong(idRolUsuario));
		
		// Comprobar si el Rol tiene permitida la generación de la carta PCCAA
		boolean generacionHabilitada = false;
		List<ParametrosGenerales> parametros = manejadorParametrosGenerales.obtenerParametrosPorTipo(UtilConstantes.ROLES_PCADP);	
		
		for(ParametrosGenerales parametro : parametros) {
			if(parametro.getValorTexto().equals(nombreRolUsuario)) {
				generacionHabilitada = true;
				break;
			}
		}
		
		// Si la generación no esta habilitada se procede a agregar un filtro con el fin de excluir la plantilla PCADP
		
		if(!generacionHabilitada) {			
			filtros.add(new InformacionFiltro(TipoFiltro.DIFERENTE, PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_NOMBRE,
					UtilDominios.NOMBRE_PLANTILLA_CARTA_AUDIENCIAS_DIFERENTES_PRIMERA));
		}
		
		List<PlantillaCarta> plantillas = manejadorPlantillaCarta.consultar(filtros, null, null);
		
		return plantillas;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces
	 * .IPlantillaCartaFacade#obtenerPlantillasFiltros(java.lang.String, java.lang.Long)
	 */
	@Override
	public List<PlantillaCarta> obtenerPlantillasFiltros(String tipoServicio, Long idPlantilla) {
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		
		filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_TIPO_SERVICIO,
				tipoServicio));
		
		if(idPlantilla != null && idPlantilla != -1){
			filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_PK,
					idPlantilla));		}
		
		return (List<PlantillaCarta>) new PlantillaCartaDTO()
				.transformarColeccionEntidadesConDependencias(manejadorPlantillaCarta.consultar(filtros, null, null));
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces
	 * .IPlantillaCartaFacade#crearPlantillaCarta(com.ccb.simasc.transversal.dto.PlantillaVariablesCartaEditorDTO)
	 */
	@Override
	public Long crearPlantillaCarta(PlantillaVariablesCartaEditorDTO plantilla) {
		
		if(appContext != null && appContext.getContextoSesion() != null){

			String usuarioActual = appContext.getContextoSesion().getNombreUsuario();
			
			persistirPlantilla(usuarioActual, plantilla);
			manejadorValorPlantillaCarta.actualizarValorPlantilla(usuarioActual, UtilDominios.ESTADO_REGISTRO_INACTIVO,
					plantilla.getPlantillaCarta().getIdPlantillaCarta());
			List<ValorPlantillaCarta> valorTagPlantilla = crearFiltros(plantilla.getPlantillaCarta().getIdPlantillaCarta());
			
			for(ValorPlantillaCarta tag : plantilla.getVariablesPlantilla()){
				ValorPlantillaCarta valorPlantilla = buscarTag(valorTagPlantilla, tag.getNombreValorDinamico());
				if(valorPlantilla != null && valorPlantilla.getIdValorPlantillaCarta() != null){
					valorPlantilla.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
					valorPlantilla.setIdUsuarioModificacion(usuarioActual);
					valorPlantilla.setFechaUltimaModificacion(new Date());
					manejadorValorPlantillaCarta.actualizar(valorPlantilla);
				}else{
					tag.setIdPlantillaCarta(plantilla.getPlantillaCarta().getIdPlantillaCarta());
					tag.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
					manejadorValorPlantillaCarta.crear(tag);
				}
			}	
		}
		
		return plantilla.getPlantillaCarta().getIdPlantillaCarta();
	}
	
	/**
	 * Metodo para verificar si el tag ya se encuentra asociado a la plantilla
	 * @param tags
	 * @param nombre
	 * @return
	 */
	private ValorPlantillaCarta buscarTag(List<ValorPlantillaCarta> tags, String nombre) {
		ValorPlantillaCarta valorPlantilla = null;
		for (ValorPlantillaCarta valorPlantillaCarta : tags) {
			if (valorPlantillaCarta.getNombreValorDinamico().equals(nombre)) {
				valorPlantilla = valorPlantillaCarta;
				break;
			}
		}
		return valorPlantilla;
	}
	
	/**
	 * Metodo que realiza la creacion o la actualizacion de la plantilla 
	 * si es actualizacion se valida que el nombre no se encuentre repetido
	 * @param nombreUsuario
	 * @param plantilla
	 */
	private void persistirPlantilla(String nombreUsuario, PlantillaVariablesCartaEditorDTO plantilla) {
		plantilla.getPlantillaCarta().setFechaUltimaModificacion(new Date());
		plantilla.getPlantillaCarta().setIdUsuarioModificacion(nombreUsuario);
		if(plantilla.getPlantillaCarta().getIdPlantillaCarta() != null) {
			if(manejadorDominio.existeNombrePlantilla(plantilla.getNombrePlantilla(), plantilla.getPlantillaCarta().getNombre()))
				throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR600.toString()));
			else
				manejadorPlantillaCarta.actualizar(plantilla.getPlantillaCarta());
		}else {
			if(manejadorDominio.existeNombrePlantilla(plantilla.getNombrePlantilla(), null))
				throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR600.toString()));
			else
				manejadorPlantillaCarta.crear(plantilla.getPlantillaCarta());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPlantillaCartaFacade#crearFiltros(java.lang.Long)
	 */
	@Override
	public List<ValorPlantillaCarta> crearFiltros(Long idPlantillaCarta) {
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, ValorPlantillaCarta.ENTIDAD_VALOR_PLANTILLA_CARTA_ID_PLANTILLA_CARTA,
					idPlantillaCarta));
		
		return manejadorValorPlantillaCarta.consultar(filtros, null, null);
	}
	
	@Override
	public PlantillaCartaDTO consultarPlantillaServicioCaso(Long idCaso, String nombrePlantilla){
		PlantillaCarta plantilla =  manejadorPlantillaCarta.consultarPlantillaPorNombre(idCaso, nombrePlantilla);
		if( plantilla == null ){
			throw new SIMASCNegocioExcepcion(String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR601.toString())));
		}
		return transformarSinDependencias( plantilla );
	}

	@Override
	public List<PlantillaCarta> consultarPlantillaNombre(String nombre, String tipoServicio) {
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		
		filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_NOMBRE,
				nombre));
		
		filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_TIPO_SERVICIO,
				tipoServicio));		

		return (List<PlantillaCarta>) new PlantillaCartaDTO()
				.transformarColeccionEntidadesConDependencias(manejadorPlantillaCarta.consultar(filtros, null, null));
	}
	
	@Override
	public PlantillaCartaDTO consultarPlantillaServicioNombre(Long idServicio, String nombrePlantilla, Long idCaso) {
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		
		filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_NOMBRE,
				nombrePlantilla));
		
		filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_ID_SERVICIO,
				idServicio));		

		List<PlantillaCarta> plantillas = manejadorPlantillaCarta.consultar(filtros, null, null);
		if(plantillas != null && !plantillas.isEmpty()) {
		
		 PlantillaCartaDTO plantillaCartaDTO = new PlantillaCartaDTO().transformarSinDependencias(plantillas.get(0)); 
		 Map<String, String> variablesReemplazar = variablesCartas.getInformacionPlantilla(plantillaCartaDTO.getIdPlantillaCarta(), idCaso, null, null, null);
		 plantillaCartaDTO.setPlantillaHtml(UtilSimasc.reemplazaTextosCadena(variablesReemplazar, plantillaCartaDTO.getPlantillaHtml()));
		 
		 return plantillaCartaDTO;
			
		}
		
		return null;
	}

	@Override
	public List<PlantillaCarta> obtenerPlantillasEquidad() {
				
		return manejadorPlantillaCarta.consultarPlantillasEquidad();
	}
	
	// protected region metodos adicionales end
	
}
