package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.enumeraciones.TipoOrdenamiento;
import com.ccb.simasc.integracion.manejadores.ManejadorNorma;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionOrdenamiento;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.INormaFacade;
import com.ccb.simasc.transversal.dto.NormaDTO;
import com.ccb.simasc.transversal.entidades.Norma;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Norma}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class NormaFacade extends AccesoFacade<Norma, Long, NormaDTO> implements INormaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorNorma manejadorNorma;
	
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorNorma;
	}

	@Override
	public NormaDTO transformarSinDependencias(Norma obj) {
		NormaDTO dto = new NormaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public NormaDTO transformarConDependencias(Norma obj) {
		NormaDTO dto = new NormaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Norma transformarEntidadConDependencias(Norma obj) {
		Norma dto = new Norma();
		dto = new NormaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Norma transformarEntidadSinDependencias(Norma obj) {
		Norma dto = new Norma();
		dto = new NormaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones.
	
	@Override
	public List<Norma>  cosultarNormas(String estado, String nombre) throws SIMASCNegocioExcepcion{
		List<InformacionFiltro> filtros = null;
		if(estado != null || nombre != null){
			filtros = new ArrayList<>();
		}
		
		if(estado != null){
			InformacionFiltro filtroActivo = new InformacionFiltro(TipoFiltro.EXACTO,
					Norma.ENTIDAD_NORMA_ESTADO_REGISTRO, estado);			
			filtros.add(filtroActivo);
		}	
		
		if(nombre != null){
			InformacionFiltro filtroActivo = new InformacionFiltro(TipoFiltro.EXACTO,
					Norma.ENTIDAD_NORMA_NOMBRE, nombre);			
			filtros.add(filtroActivo);
		}	
		InformacionOrdenamiento ordenamientoFiltro = new InformacionOrdenamiento(TipoOrdenamiento.ASCENDENTE, Norma.ENTIDAD_NORMA_NOMBRE);
		List<InformacionOrdenamiento> ordenamiento = new ArrayList<InformacionOrdenamiento>();
		ordenamiento.add(ordenamientoFiltro);
		return manejadorNorma.consultar(filtros, ordenamiento, null);
		
	}
	
	@Override
	public void  eliminarNorma(Long idNorma) throws SIMASCNegocioExcepcion{
		Norma norma = manejadorNorma.buscar(idNorma);
		norma.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
		norma.setFechaUltimaModificacion(new Date());
		if(appContext != null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() !=null){
			norma.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
		}else{			
			norma.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		}
		manejadorNorma.actualizar(norma);	
		
	}
	
	@Override
	public void  crearNorma(Norma norma) throws SIMASCNegocioExcepcion{
		norma.setFechaUltimaModificacion(new Date());
		List<Norma> normasActivas = 
				this.cosultarNormas(UtilDominios.ESTADO_REGISTRO_ACTIVO, norma.getNombre());
		if(normasActivas == null || normasActivas.isEmpty()){
			manejadorNorma.crear(norma);
		}else{
			throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR540.toString()));		
		}
		
		
		
	}

	// protected region metodos adicionales end
	
}
