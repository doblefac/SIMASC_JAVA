package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorSede;
import com.ccb.simasc.integracion.manejadores.ManejadorTelefonoSede;
import com.ccb.simasc.integracion.manejadores.ManejadorTipoServicioSede;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISedeFacade;
import com.ccb.simasc.transversal.dto.SedeDTO;
import com.ccb.simasc.transversal.dto.SedeTipoServicioDTO;
import com.ccb.simasc.transversal.entidades.Sede;
import com.ccb.simasc.transversal.entidades.TelefonoSede;
import com.ccb.simasc.transversal.entidades.TipoServicioSede;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Sede}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class SedeFacade extends AccesoFacade<Sede, Long, SedeDTO> implements ISedeFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorSede manejadorSede;
	
	@EJB
	private ManejadorTipoServicioSede manejadorTipoServicioSede;
	
	@EJB
	private ManejadorTelefonoSede manejadorTelefonoSede;
	
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorSede;
	}

	@Override
	public SedeDTO transformarSinDependencias(Sede obj) {
		return new SedeDTO().transformarSinDependencias(obj);
	}

	@Override
	public SedeDTO transformarConDependencias(Sede obj) {
		return new SedeDTO().transformarConDependencias(obj);
	}

	@Override
	public Sede transformarEntidadConDependencias(Sede obj) {
		return new SedeDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Sede transformarEntidadSinDependencias(Sede obj) {
		return new SedeDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	public List<Sede> consultarSedes(){	
		return manejadorSede.consultar(null,null,null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISedeFacade#crearSede(com.ccb.simasc.transversal.dto.SedeTipoServicioDTO)
	 */
	@Override
	public void crearSede(SedeTipoServicioDTO sede) {
		if(appContext != null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getRolPrincipal() !=null){
			
			String usuarioActual = appContext.getContextoSesion().getIdUsuario();

			sede.getSede().setIdUsuarioModificacion(usuarioActual);
			sede.getSede().setFechaUltimaModificacion(new Date());
			
			for (TelefonoSede elementTelefono : sede.getSede().getTelefonoSedeList()) {
				elementTelefono.setFechaUltimaModificacion(new Date());
				elementTelefono.setIdUsuarioModificacion(usuarioActual);
			}
			
			manejadorSede.crear(sede.getSede());
			long idSedeGuardada = sede.getSede().getIdSede();
			
			for (TipoServicioSede element : sede.getTipoServicioSede()) {
				element.getTipoServicioSedePK().setIdSede(idSedeGuardada);
				element.setEstadoRegistro("ACT");
				element.setFechaUltimaModificacion(new Date());
				element.setIdUsuarioModificacion(usuarioActual);
				manejadorTipoServicioSede.crear(element);
			}	
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISedeFacade#crearSede(com.ccb.simasc.transversal.dto.SedeTipoServicioDTO)
	 */
	@Override
	public void actualizarSede(SedeTipoServicioDTO sede) {
		if(appContext != null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getRolPrincipal() !=null){
			
		
			String usuarioActual = appContext.getContextoSesion().getIdUsuario();

    		InformacionFiltro filtroTelefonoSede = new InformacionFiltro(TipoFiltro.EXACTO, TelefonoSede.ENTIDAD_TELEFONO_SEDE_ID_SEDE,
    				sede.getSede().getIdSede());
        	List<InformacionFiltro> filtrosTelefono = new ArrayList<>();
        	filtrosTelefono.add(filtroTelefonoSede);

        	List<TelefonoSede> telefonosSede = manejadorTelefonoSede.consultar(filtrosTelefono, null, null);
        	
        	for (TelefonoSede element : telefonosSede) {
        		element.setIdSede(element.getIdSede());
				element.setFechaUltimaModificacion(new Date());
				element.setIdUsuarioModificacion(usuarioActual);
				element.setEstadoRegistro("INA");
				manejadorTelefonoSede.actualizar(element);
        	}
			
			sede.getSede().setIdUsuarioModificacion(usuarioActual);
			sede.getSede().setFechaUltimaModificacion(new Date());
			
			for (TelefonoSede elementTelefono : sede.getSede().getTelefonoSedeList()) {
				elementTelefono.setFechaUltimaModificacion(new Date());
				elementTelefono.setIdUsuarioModificacion(usuarioActual);
			}
			
			manejadorSede.actualizar(sede.getSede());
			long idSedeGuardada = sede.getSede().getIdSede();
			
    		InformacionFiltro filtroTipoServicio = new InformacionFiltro(TipoFiltro.EXACTO,
    				TipoServicioSede.ENTIDAD_TIPO_SERVICIO_SEDE_PK_ID_SEDE,
    				sede.getSede().getIdSede());
        	List<InformacionFiltro> filtrosTipoServicioConsulta = new ArrayList<>();
        	filtrosTipoServicioConsulta.add(filtroTipoServicio);

        	List<TipoServicioSede> tipoServSede = manejadorTipoServicioSede.consultar(filtrosTipoServicioConsulta, null, null);
        	
        	for (TipoServicioSede element : tipoServSede) {
        		element.getTipoServicioSedePK().setIdSede(idSedeGuardada);
				element.setFechaUltimaModificacion(new Date());
				element.setIdUsuarioModificacion(usuarioActual);
				element.setEstadoRegistro("INA");
				manejadorTipoServicioSede.actualizar(element);
        	}
			
			for (TipoServicioSede element : sede.getTipoServicioSede()) {
				element.getTipoServicioSedePK().setIdSede(idSedeGuardada);
				element.setFechaUltimaModificacion(new Date());
				element.setIdUsuarioModificacion(usuarioActual);
				
				if(element.getTipoServicioSedePK().getIdSede() != null){
					manejadorTipoServicioSede.actualizar(element);
				}else{
					manejadorTipoServicioSede.crear(element);
				}
				
					
			}
			
		}
	}
	
	@Override
	public List<SedeDTO> consultarSedesPorCentro(List<Long> idCentro){
		List<Sede> sedes = 	manejadorSede.consultarSedesPorCentro(idCentro);			
		return (List<SedeDTO>) new SedeDTO().transformarColeccionSinDependencias(sedes);
	}
	
	@SuppressWarnings("unchecked")
	@Override
    public List<SedeDTO> consultarSedesPorServicio( Long idServicio ){
		return (List<SedeDTO>) new SedeDTO()
				.transformarColeccionSinDependencias(manejadorSede.consultarSedesPorServicio(idServicio));
	}

	@Override
	public List<SedeDTO> consultarSedesAudienciaConciliador(Date fecha, Long idPersona) {
		return manejadorSede.consultarSedesAudienciaConciliador(fecha, idPersona);
	}
	
	@Override
	public List<SedeDTO> consultarSedesPorCasoServicio(Long idCaso, Long idPersona) {
		return (List<SedeDTO>) new SedeDTO()
				.transformarColeccionSinDependencias(manejadorSede.consultarSedesPorCasoServicio(idCaso, idPersona));
	}
	
	
	
	// protected region metodos adicionales end

}
