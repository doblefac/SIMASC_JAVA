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
import com.ccb.simasc.integracion.manejadores.ManejadorFuncionalidad;
import com.ccb.simasc.integracion.manejadores.ManejadorFuncionalidadRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFuncionalidadFacade;
import com.ccb.simasc.transversal.dto.FuncionalidadDTO;
import com.ccb.simasc.transversal.entidades.Funcionalidad;
import com.ccb.simasc.transversal.entidades.FuncionalidadRol;
import com.ccb.simasc.transversal.entidades.FuncionalidadRolPK;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Funcionalidad}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class FuncionalidadFacade extends AccesoFacade<Funcionalidad, String, FuncionalidadDTO> implements IFuncionalidadFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorFuncionalidad manejadorFuncionalidad;
	
	@EJB
	private ManejadorFuncionalidadRol manejadorFuncionalidadRol;
	
	@EJB
	private ManejadorRol manejadorRol;
	
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorFuncionalidad;
	}

	@Override
	public FuncionalidadDTO transformarSinDependencias(Funcionalidad obj) {
		return new FuncionalidadDTO().transformarSinDependencias(obj);
	}

	@Override
	public FuncionalidadDTO transformarConDependencias(Funcionalidad obj) {
		return new FuncionalidadDTO().transformarConDependencias(obj);
	}

	@Override
	public Funcionalidad transformarEntidadConDependencias(Funcionalidad obj) {
		return new FuncionalidadDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Funcionalidad transformarEntidadSinDependencias(Funcionalidad obj) {
		return new FuncionalidadDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFuncionalidadFacade#consultarFuncionalidadesRol(java.lang.Long, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionalidad> consultarFuncionalidadesRol(Long idRol, String tipoServicio, boolean obtenerHijos, boolean obtenerPermisos) {
		return (List<Funcionalidad>) new FuncionalidadDTO()
				.transformarEntidadesConDependenciasRecursivo(
						manejadorFuncionalidad.consultarFuncionalidadesPadre(tipoServicio), idRol, obtenerHijos, obtenerPermisos);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFuncionalidadFacade#actualizarFuncionalidadesRol(java.util.List, java.lang.Long)
	 */
	@Override
	public void actualizarFuncionalidadesRol(List<Funcionalidad> funcionalidades, Long idRol) {
		for (Funcionalidad funcionalidad : funcionalidades) {
			if (funcionalidad.getFuncionalidadList() != null && !funcionalidad.getFuncionalidadList().isEmpty()) {
				actualizarFuncionalidadesRol(funcionalidad.getFuncionalidadList(), idRol);
			}
			boolean acceso = funcionalidad.isAcceso();
			Rol rol = manejadorRol.buscar(idRol);
			List<FuncionalidadRol> funcionalidadesRol = manejadorFuncionalidadRol.consultar(filtro(TipoFiltro.EXACTO,
					FuncionalidadRol.ENTIDAD_FUNCIONALIDAD_ROL_PK_NOMBRE_FUNCIONALIDAD, funcionalidad.getNombre()),
					null, null);
			funcionalidad.setFuncionalidadRolList(funcionalidadesRol);
			if (acceso) {
				concederAcceso(funcionalidad, rol);
			} else {
				removerAcceso(funcionalidad, rol);
			}
		}
	}

	/**
	 * Método que concede acceso a una funcionalidad a un rol
	 * @param funcionalidad
	 * @param rol
	 */
	private void concederAcceso(Funcionalidad funcionalidad, Rol rol){
		FuncionalidadRol funcionalidadRolRemover = null;
		if(funcionalidad.getFuncionalidadRolList() != null && !funcionalidad.getFuncionalidadRolList().isEmpty()) {
			for(FuncionalidadRol funcionalidadRol: funcionalidad.getFuncionalidadRolList()) {
				if(funcionalidadRol.getRol().equals(rol)) {
					funcionalidadRolRemover = funcionalidadRol;
					break;
				}
			}
			if (funcionalidadRolRemover != null)
				manejadorFuncionalidadRol.eliminar(funcionalidadRolRemover);
		}
	}
	
	/**
	 * Método para remover el acceso a una funcionalidad a un rol especifico
	 * @param funcionalidad
	 * @param rol
	 */
	private void removerAcceso(Funcionalidad funcionalidad, Rol rol) {
		FuncionalidadRol funcionalidadRol = null;
		if(funcionalidad.getFuncionalidadRolList() != null && !funcionalidad.getFuncionalidadRolList().isEmpty()) {
			for(FuncionalidadRol funcionalidadRolConsulta: funcionalidad.getFuncionalidadRolList()) {
				if(funcionalidadRolConsulta.getRol().equals(rol)) {
					funcionalidadRol = funcionalidadRolConsulta;
					break;
				}
			}			
		}
		if (funcionalidadRol == null) {
			funcionalidadRol = nuevaFuncionalidadRol(funcionalidad.getNombre(), rol.getIdRol());
			manejadorFuncionalidadRol.crear(funcionalidadRol);
		}
	}
	
	/**
	 * Método que instancia una nueva FuncionalidadRol
	 * @param funcionalidadNombre
	 * @param idRol
	 * @return
	 */
	private FuncionalidadRol nuevaFuncionalidadRol(String funcionalidadNombre, Long idRol) {
		FuncionalidadRol funcionalidadRol = new FuncionalidadRol();
		funcionalidadRol.setFuncionalidadRolPK(new FuncionalidadRolPK(funcionalidadNombre, idRol));
		funcionalidadRol.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
		funcionalidadRol.setFechaUltimaModificacion(new Date());
		String usuarioActual = appContext != null && appContext.getContextoSesion() != null 
							&& appContext.getContextoSesion().getNombreUsuario() != null 
				? appContext.getContextoSesion().getNombreUsuario() : UtilConstantes.USUARIO_DEFECTO_SIMASC;
		funcionalidadRol.setIdUsuarioModificacion(usuarioActual);
		return funcionalidadRol;
	}
	
	private List<InformacionFiltro> filtro(TipoFiltro tipo, String campo, Object parametro){
		List<InformacionFiltro> filtros = new ArrayList<InformacionFiltro>();
		InformacionFiltro filtro2 = new InformacionFiltro(tipo, campo, parametro);
		filtros.add(filtro2);	
		return filtros;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFuncionalidadFacade#consultarFuncionalidadesPorPadre(java.lang.String, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionalidad> consultarFuncionalidadesPorPadre(String funcionalidadPadre, Long idRol, boolean obtenerHijos, boolean obtenerPermisos) {
		return (List<Funcionalidad>) new FuncionalidadDTO()
				.transformarEntidadesConDependenciasRecursivo(
						manejadorFuncionalidad.consultar(filtro(TipoFiltro.EXACTO,
								Funcionalidad.ENTIDAD_FUNCIONALIDAD_NOMBRE_FUNCIONALIDAD_PADRE, funcionalidadPadre)
								, null, null), idRol, obtenerHijos, obtenerPermisos);
	}
	// protected region metodos adicionales end


	
}
