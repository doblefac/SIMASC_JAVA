package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorEstadoPersonaRol;
import com.ccb.simasc.integracion.manejadores.ManejadorLista;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICentroFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUsuarioFacade;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.ConsultaPrestadoresDTO;
import com.ccb.simasc.transversal.dto.RolPersonaDTO;
import com.ccb.simasc.transversal.dto.formularios.BusquedaRolesActivosDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltroConsultaPrestadoresDTO;
import com.ccb.simasc.transversal.dto.formularios.InfoPrestadorDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Centro;
import com.ccb.simasc.transversal.entidades.EstadoPersonaRol;
import com.ccb.simasc.transversal.entidades.EstadoPersonaRolPK;
import com.ccb.simasc.transversal.entidades.Lista;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.entidades.SolicitudPrestador;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link RolPersona}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class RolPersonaFacade extends AccesoFacade<RolPersona, Long, RolPersonaDTO> implements IRolPersonaFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorRolPersona manejadorRolPersona;
	
	@EJB
	private IRolFacade rolFacade;
	
	@EJB
	private IDominioFacade dominioFacade;
	
	@EJB
	private ICentroFacade centroFacade;
	
	@EJB
	private IUsuarioFacade usuarioFacade;
	
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;
    
	@EJB
	private ManejadorLista manejadorLista;
	
	// protected region atributos end
	
	@EJB
	private ManejadorRol manejadorRol;

	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	private ManejadorEstadoPersonaRol manejadorEstadoPersonaRol;
	
	@EJB
	private ManejadorPersona manejadorPersona;
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorRolPersona;
	}

	@Override
	public RolPersonaDTO transformarSinDependencias(RolPersona obj) {
		return new RolPersonaDTO().transformarSinDependencias(obj);
	}

	@Override
	public RolPersonaDTO transformarConDependencias(RolPersona obj) {
		return new RolPersonaDTO().transformarConDependencias(obj);
	}

	@Override
	public RolPersona transformarEntidadConDependencias(RolPersona obj) {
		return new RolPersonaDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public RolPersona transformarEntidadSinDependencias(RolPersona obj) {
		return new RolPersonaDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	@Override
	public List<RolPersona> obtenerRolPersonasPorRoles(List<String> roles){
	
		return manejadorRolPersona.consultarRolPersonasPorRoles(roles);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaFacade#asignarRol(com.ccb.simasc.transversal.entidades.RolPersona, java.lang.String)
	 */
	@Override
	public void asignarRol(RolPersona rolPersona, String nombreRol) {
		List<RolPersona> rolesActivos = manejadorRolPersona.consultarRolesAsignados(rolPersona.getIdPersona(), nombreRol);
		for (RolPersona rolP : rolesActivos) {
			if (rolP.getIdCentro() == rolPersona.getIdCentro()) {
				throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR505.toString()));
			}
		}
		Rol rolActual = rolFacade.obtenerRolPorNombre(nombreRol);
		rolPersona.setIdRol(rolActual.getIdRol());
		rolPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		String usuarioActual = appContext.getContextoSesion().getIdUsuario() != null 
				? appContext.getContextoSesion().getIdUsuario() : UtilConstantes.USUARIO_DEFECTO_SIMASC;
		rolPersona.setIdUsuarioModificacion(usuarioActual);
		rolPersona.setFechaUltimaModificacion(new Date());
		rolPersona.setRol(rolActual);
		crear(rolPersona);	 	
		//Validar si el rol aplica Mauc
		if(rolActual.getAplicaMauc() && rolPersona.getIdPersona()!= null) {			
		    Persona persona = manejadorPersona.buscar(rolPersona.getIdPersona());
			usuarioFacade.validarFuncionarioAplicaMauc(persona);	  		
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaFacade#consultarRolPersona(java.lang.Long)
	 */
	@Override
	public List<RolPersona> consultarRolPersona(Long idPersona) {
		return manejadorRolPersona.obtenerRolPesonaPorPersona(idPersona);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaFacade#obtenerCentrosActor(java.lang.Long)
	 */
	@Override
	public Collection<CentroDTO> obtenerCentrosActor(Long codigoUsuario) {
		List<Centro> centros = manejadorRolPersona.obtenerCentrosActor(codigoUsuario);
		return centroFacade.transformarColeccionSinDependencias(centros, new ArrayList<CentroDTO>());
	}
	
	/**
     * ADM-C-003 COnsulta lista de prestadores
     * Consulta los prestadores que cumplan los criterios de filtrado que se pasan como parámetro
     * @param filtro
     * @return
     */
	public  List<InfoPrestadorDTO> consultarListaPrestadores(FiltroConsultaPrestadoresDTO filtro){
		
		if(filtro.getPersonaJuridica()!=null && !filtro.getPersonaJuridica().isEmpty()){			
			filtro.setPersonaJuridica(filtro.getPersonaJuridica().replaceAll("\\s+",""));
		}
		if(filtro.getNombres()!=null && !filtro.getNombres().isEmpty()){			
			filtro.setNombres(filtro.getNombres().replaceAll("\\s+",""));
		}
		if(filtro.getApellidos()!=null && !filtro.getApellidos().isEmpty()){			
			filtro.setApellidos(filtro.getApellidos().replaceAll("\\s+",""));
		}
		
		return manejadorRolPersona.consultarListaPrestadores(filtro);				
	}

	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaFacade#obtenerRolPersona(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<RolPersona> obtenerRolPersona(Long idRol, Long idPersona) {
		return manejadorRolPersona.obtenerRolPersona(idRol, idPersona);
	}

	@Override
	public List<Persona> consultarPeritoSinAsociar(Long idPersonaJuridica) {
		return manejadorRolPersona.consultarPeritoSinAsociar(idPersonaJuridica);
	}
	
	@Override
	public List<PersonaBasicaDTO> conciliadoresActivosPorEstado(BusquedaRolesActivosDTO datosConsulta){
		List <PersonaBasicaDTO> personaBasica = new ArrayList<PersonaBasicaDTO>();
		List<Persona> personas = manejadorRolPersona.conciliadoresActivosPorEstadoRpc(datosConsulta);
		if(!personas.isEmpty()){			
			personaBasica =	(List<PersonaBasicaDTO>) PersonaBasicaDTO.newListaPersonaBasicaDTO(personas);
		}		
		return personaBasica;
		
	}
	
	@Override
	public List<PersonaBasicaDTO> consultarPesonasBasicaPorRolPersonaCentro(List<String> roles, List<Long> centro,Date fechaVigencia){
		List <PersonaBasicaDTO> personaBasica = new ArrayList<PersonaBasicaDTO>();
		List<Persona> personas = manejadorRolPersona.consultarPesonasPorRolPersonaCentro(roles, centro, fechaVigencia);
		if(!personas.isEmpty()){
			personaBasica =	(List<PersonaBasicaDTO>) PersonaBasicaDTO.newListaPersonaBasicaDTO(personas);
		}		
		return personaBasica;
	}
	
	@Override
	public List<RolPersona> consultarRolPersonaPrestadorPersonaRolLista( Long idPersona, String nombreRol, String nombreLista,
							List<String> estadoEPTS, List<String> idCentros ){
		List<RolPersona> rolesPersona = manejadorRolPersona
				.consultarRolPersonaPrestadorPersonaRolLista( idPersona, nombreRol, nombreLista, estadoEPTS, idCentros );
		return (List<RolPersona>) transformarEntidadesColeccionConDependencias(rolesPersona, new ArrayList<RolPersona>());
	}
	
	/*
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IRolPersonaFacade#cambiarPrestadorDeLista
	 */
	@Override
	public void cambiarPrestadorDeLista( SolicitudPrestador solicitudCambioLista, List<RolPersona> rolesPersona ){
		if( rolesPersona.isEmpty() ){
			throw new SIMASCNegocioExcepcion(String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR561.toString())));
		}
		
		Lista listaA = manejadorLista.consultarListaNombre(UtilDominios.TIPO_LISTA_A);
		
		List<RolPersona> rolesPersonaFinalizar = new ArrayList<RolPersona>();
		List<RolPersona> rolesPersonaCrear = new ArrayList<RolPersona>();
		for( RolPersona rolPersona : rolesPersona ){
			//para finalizar
			RolPersona rolPersonaFinalizar = transformarEntidadSinDependencias(rolPersona);
			rolPersonaFinalizar.setFechaFinVigencia( new Date() );
			rolesPersonaFinalizar.add(rolPersonaFinalizar);
			//para crear 
			RolPersona rolPersonaCrear = new RolPersona();
			rolPersonaCrear.setIdRolPersona(null);
			rolPersonaCrear.setFechaInicioVigencia( new Date());
			rolPersonaCrear.setFechaFinVigencia(null);
			rolPersonaCrear.setActaAprobacion(solicitudCambioLista.getRolPersona().getActaAprobacion());
			rolPersonaCrear.setFechaActaAprobacion(solicitudCambioLista.getRolPersona().getFechaActaAprobacion());
			rolPersonaCrear.setReportadoSicaac(rolPersona.getReportadoSicaac());
			rolPersonaCrear.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			rolPersonaCrear.setIdRol(rolPersona.getIdRol());
			rolPersonaCrear.setIdPersona(rolPersona.getIdPersona());
			rolPersonaCrear.setIdCentro(rolPersona.getIdCentro());
			rolPersonaCrear.setIdLista(listaA.getIdLista());
			rolesPersonaCrear.add(rolPersonaCrear);
		}
		manejadorRolPersona.actualizarLista(rolesPersonaFinalizar);
		manejadorRolPersona.crearLista(rolesPersonaCrear);
	}

	/*
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IRolPersonaFacade#consultarPersonasPorRolMateria( com.ccb.simasc.transversal.dto.ConsultaPrestadoresDTO )
	 */
	@Override
	public List<ConsultaPrestadoresDTO> consultarPersonasPorRolMateria( ConsultaPrestadoresDTO filtros ){
		return manejadorRolPersona.consultarPersonasPorRolMateria( filtros );
	}
	
	
	@Override
	public List<RolPersonaDTO> consultarRolPersonaVigentes( Long idPersona){
		return manejadorRolPersona.consultarRolPersona(idPersona);
	}
	
	
	public void finalizarRolArbitroSocial(Long idPersona)
	{
		Date date = new Date();
		Rol rol = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_ARBITRO_SOCIAL);
		
		List<RolPersona> rolAsignado = manejadorRolPersona.consultarRolesAsignados(idPersona, UtilDominios.ROL_PERSONA_ARBITRO_SOCIAL);
		Integer cntCasosAbiertos = manejadorRolPersonaCaso.consultarNumeroCasosAbiertosAceptados(idPersona);
		
		if(rolAsignado.size() > 0 && cntCasosAbiertos == 0) {
			rolAsignado.get(0).setFechaFinVigencia(new Timestamp(date.getTime()));
			manejadorRolPersona.actualizar(rolAsignado.get(0));
			
			EstadoPersonaRolPK pId = new EstadoPersonaRolPK();
			pId.setIdRol(rol.getIdRol());
			pId.setIdPersona(idPersona);
			EstadoPersonaRol estadoPersonaRol = manejadorEstadoPersonaRol.buscar(pId);
			
			//Se actualiza el estado en EstadoPersonaRol si la persona tiene el rol ROL_PERSONA_ARBITRO_SOCIAL 			
			if(estadoPersonaRol!= null && estadoPersonaRol.getEstadoPersonaRolPK()!= null && estadoPersonaRol.getEstadoPersonaRolPK().getIdRol()!=null) {			
				estadoPersonaRol.setIdMotivo(UtilDominios.ESTADO_NO_DISPONIBILIDAD_PARA_SOCIAL);
				estadoPersonaRol.setFechaAsignacion(date);
				manejadorEstadoPersonaRol.actualizar(estadoPersonaRol);
			}
		}		
	}
	
	public void finalizarRolArbitroSocialCambioEstado(Long idPersona)
	{
		Date date = new Date();
		Rol rol = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_ARBITRO_SOCIAL);
		
		List<RolPersona> rolAsignado = manejadorRolPersona.consultarRolesAsignados(idPersona, UtilDominios.ROL_PERSONA_ARBITRO_SOCIAL);
		if(rolAsignado.size() > 0) {
			rolAsignado.get(0).setFechaFinVigencia(new Timestamp(date.getTime()));
			manejadorRolPersona.actualizar(rolAsignado.get(0));
			
			EstadoPersonaRolPK pId = new EstadoPersonaRolPK();
			pId.setIdRol(rol.getIdRol());
			pId.setIdPersona(idPersona);
			EstadoPersonaRol estadoPersonaRol = manejadorEstadoPersonaRol.buscar(pId);
			if(estadoPersonaRol != null)
			{
				estadoPersonaRol.setIdMotivo(UtilDominios.ESTADO_NO_DISPONIBILIDAD_PARA_SOCIAL);
				estadoPersonaRol.setFechaAsignacion(new Timestamp(date.getTime()));
				manejadorEstadoPersonaRol.actualizar(estadoPersonaRol);
			}
		}
	}
	
	@Override
	public List<RolPersonaDTO> consultarRolPersonaPorServicio(Long idPersona, Long idServicio) {
		return manejadorRolPersona.consultarRolPersonaPorServicio(idPersona, idServicio);
	}
}
