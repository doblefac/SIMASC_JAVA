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

import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorRelacionadoConvenio;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRelacionadoConvenioFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.RelacionadoConvenioDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.RelacionadoConvenio;
import com.ccb.simasc.transversal.entidades.RelacionadoConvenioPK;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link RelacionadoConvenio}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class RelacionadoConvenioFacade
		extends AccesoFacade<RelacionadoConvenio, RelacionadoConvenioPK, RelacionadoConvenioDTO>
		implements IRelacionadoConvenioFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	@EJB
	private ManejadorRelacionadoConvenio manejadorRelacionadoConvenio;

	@EJB
	private ManejadorRol manejadorRol;
	
	// contexto de sesion de usuario.
	@Inject
	private ApplicationRequestContext appContext;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorRelacionadoConvenio;
	}

	@Override
	public RelacionadoConvenioDTO transformarSinDependencias(RelacionadoConvenio obj) {
		return new RelacionadoConvenioDTO().transformarSinDependencias(obj);
	}

	@Override
	public RelacionadoConvenioDTO transformarConDependencias(RelacionadoConvenio obj) {
		return new RelacionadoConvenioDTO().transformarConDependencias(obj);
	}

	@Override
	public RelacionadoConvenio transformarEntidadConDependencias(RelacionadoConvenio obj) {
		return new RelacionadoConvenioDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public RelacionadoConvenio transformarEntidadSinDependencias(RelacionadoConvenio obj) {
		return new RelacionadoConvenioDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IRelacionadoConvenioFacade#getRelacionadoConvenioPorConvenioYRol(java.
	 * lang.Long, java.lang.String)
	 */
	@Override
	public List<RelacionadoConvenioDTO> consultarRelacionadoConvenio(Long idConvenio, String nombreRol) {
		Rol rol = manejadorRol.consultarRolPorNombre(nombreRol);
		List<InformacionFiltro> filtros = new ArrayList<InformacionFiltro>();
		InformacionFiltro f1 = new InformacionFiltro(TipoFiltro.EXACTO,
				RelacionadoConvenio.ENTIDAD_RELACIONADO_CONVENIO_PK_ID_CONVENIO, idConvenio);
		InformacionFiltro f2 = new InformacionFiltro(TipoFiltro.EXACTO,
				RelacionadoConvenio.ENTIDAD_RELACIONADO_CONVENIO_PK_ID_ROL, rol.getIdRol());
		InformacionFiltro f3 = new InformacionFiltro(TipoFiltro.EXACTO,
				RelacionadoConvenio.ENTIDAD_RELACIONADO_CONVENIO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		filtros.add(f1);
		filtros.add(f2);
		filtros.add(f3);
		return (List<RelacionadoConvenioDTO>) new RelacionadoConvenioDTO()
				.transformarColeccionConDependencias(manejadorRelacionadoConvenio.consultar(filtros, null, null));
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRelacionadoConvenioFacade#consultarConciliadoresRelacionadoConvenio(java.lang.Long)
	 */
	@Override
	public List<PersonaBasicaDTO> consultarConciliadoresRelacionadoConvenio(Long idConvenio) {
		return manejadorRelacionadoConvenio.consultarConciliadoresRelacionadoConvenio(idConvenio, null, UtilDominios.ESTADO_CONCILIADOR_ACTIVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRelacionadoConvenioFacade#asignarConciliadores(java.util.List)
	 */
	@Override
	public void asignarPersonasConvenio(List<RelacionadoConvenio> personas, String codigoRol, Long idConvenio) {
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null)
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		Rol rol = manejadorRol.consultarRolPorNombre(codigoRol);
		manejadorRelacionadoConvenio.inactivarRelacionadosPorRol(idConvenio, rol.getIdRol());
		for (RelacionadoConvenio relacionadoConvenio : personas) {
			relacionadoConvenio.getRelacionadoConvenioPK().setIdRol(rol.getIdRol());
			RelacionadoConvenio temp = buscar(relacionadoConvenio.getRelacionadoConvenioPK());
			if (temp != null) {
				temp.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				temp.setFechaUltimaModificacion(new Date());
				temp.setIdUsuarioModificacion(usuarioModificacion);
				actualizar(temp);
			} else {
				crear(relacionadoConvenio);				
			}
				
		}
		
	}

	/*
	 * (non-Javadoc) 
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IRelacionadoConvenioFacade#inactivarConciliador(java.lang.Long,
	 * java.lang.Long, java.lang.String)
	 */
	@Override
	public void cambiarEstadoPersona(Long idPersona, Long idConvenio, String estadoRegistro, String nombreRol) {
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null)
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		Rol rol = manejadorRol.consultarRolPorNombre(nombreRol);
		RelacionadoConvenioPK pk = new RelacionadoConvenioPK(idConvenio, idPersona, rol.getIdRol());
		RelacionadoConvenio relacionado = buscar(pk);
		if (relacionado != null) {
			relacionado.setIdUsuarioModificacion(usuarioModificacion);
			relacionado.setFechaUltimaModificacion(new Date());
			relacionado.setEstadoRegistro(estadoRegistro);
			actualizar(relacionado);
		}
	}

	@Override
	public List<PersonaBasicaDTO> consultarPersonasRelacionadoConvenio(List<String> nombreRoles,Long idConvenio){
		
		return manejadorRelacionadoConvenio.consultarPersonasRelacionadoConvenio(nombreRoles, idConvenio);
	}
	
	// protected region metodos adicionales end

	

}
