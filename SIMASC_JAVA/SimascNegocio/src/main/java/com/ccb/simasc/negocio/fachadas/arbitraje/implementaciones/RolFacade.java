package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.implementacion.DominioFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorFuncionalidad;
import com.ccb.simasc.integracion.manejadores.ManejadorFuncionalidadRol;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroServicioSorteo;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaSolicitud;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorTipoDeServicioRol;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolFacade;
import com.ccb.simasc.transversal.dto.DominioBasicoDTO;
import com.ccb.simasc.transversal.dto.FuncionalidadDTO;
import com.ccb.simasc.transversal.dto.RolBasicoDTO;
import com.ccb.simasc.transversal.dto.RolDTO;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.DominioPK;
import com.ccb.simasc.transversal.entidades.Funcionalidad;
import com.ccb.simasc.transversal.entidades.FuncionalidadRol;
import com.ccb.simasc.transversal.entidades.FuncionalidadRolPK;
import com.ccb.simasc.transversal.entidades.PersonaSolicitud;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Rol}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class RolFacade extends AccesoFacade<Rol, Long, RolDTO> implements IRolFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorRol manejadorRol;
	
	@EJB
	private ManejadorRolPersona manejadorRolPersona;
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	
	@EJB
	private ManejadorParametroServicioSorteo manejadorParametroServicioSorteo;
	
	@EJB
	private ManejadorTipoDeServicioRol manejadorTipoDeServicioRol;
	
	@EJB
	private ManejadorPersonaSolicitud manejadorPersonaSolicitud;
	
	@EJB
	private ManejadorFuncionalidad manejadorFuncionalidad;
	
	@EJB
	private DominioFacade dominioFacade;
	
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;
	
	@EJB
	private ManejadorFuncionalidadRol manejadorFuncionalidadRol;
	
	@EJB
	private ManejadorServicio manejadorServicio;
	

	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorRol;
	}

	@Override
	public RolDTO transformarSinDependencias(Rol obj) {
		RolDTO dto = new RolDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public RolDTO transformarConDependencias(Rol obj) {
		RolDTO dto = new RolDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Rol transformarEntidadConDependencias(Rol obj) {
		return new RolDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Rol transformarEntidadSinDependencias(Rol obj) {
		
		return new RolDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	@Override
	public List<Rol> obtenerRolesPorPersona(Long idPersona) {
		Set<Rol> roles = new HashSet<Rol>();
		List<Rol> rolesActivos = manejadorRolPersona.consultarRolesPersonaActivosPorPersona(idPersona);
        List<Rol> rolesInactivos = manejadorRolPersona.consultarRolesInactivosPorPersona(idPersona);
        List<Long> rPCs = manejadorRolPersonaCaso.consultarRolesCasoPersona(idPersona);
        roles.addAll(rolesActivos);

        for(Long rpc : rPCs){
            Rol r =  manejadorRol.buscar(rpc);
            if(!roles.contains(r) && !rolesInactivos.contains(r))
                roles.add(r);
        }
        
        return agregarFuncionalidades(new ArrayList<Rol>(roles));
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolFacade#
	 * obtenerRolesPorPersonaSolicitante(Long)
	 */
	public Rol obtenerRolPersonaSolicitante(Long idPersona) {
		List<PersonaSolicitud> personasSolicitud = manejadorPersonaSolicitud.consultar(
				filtro(TipoFiltro.EXACTO, PersonaSolicitud.ENTIDAD_PERSONA_SOLICITUD_PK_ID_PERSONA, idPersona), null,
				null);

		for (PersonaSolicitud personaSolicitud : personasSolicitud) {
			Rol rol = manejadorRol.buscar(personaSolicitud.getPersonaSolicitudPK().getIdRol());
			if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(rol.getEstadoRegistro())
					&& UtilDominios.ROL_PERSONA_SOLICITANTE.equals(rol.getNombre())) {
				return rol;
			}
		}

		return null;
	}

	@Override
	public List<Rol> obtenerMapaFuncionalidades() {
		List<Rol> roles = manejadorRol.consultar(null, null,null);
		return agregarFuncionalidades(roles);
	}
	
	@Override
	public Rol obtenerRolPorNombre(String nombreRol){
		Rol rol = null;
		rol= manejadorRol.consultar(filtro(TipoFiltro.EXACTO, Rol.ENTIDAD_ROL_NOMBRE,nombreRol), null, null).get(0);
		return rol;
	}
	
	private List<InformacionFiltro> filtro(TipoFiltro tipo, String campo, Object parametro){
		List<InformacionFiltro> filtros = new ArrayList<InformacionFiltro>();
		InformacionFiltro filtro2 = new InformacionFiltro(tipo, campo, parametro);
		filtros.add(filtro2);	
		return filtros;
	}
	
	
	private List<Rol> agregarFuncionalidades(List<Rol> rolesATransformar){
		List<Rol> resultado = new ArrayList<>();
		//Transformadores
		RolDTO rDto = new RolDTO();
		FuncionalidadDTO fDto = new FuncionalidadDTO();
		for(Rol r : rolesATransformar){
			Rol rDetached = rDto.transformarEntidadSinDependencias(r);
			List<Funcionalidad> funcs = new ArrayList<>();
			List<FuncionalidadRol> funcionalidadRolList = manejadorFuncionalidadRol.obtenerFuncionalidadesRol(r.getIdRol());
			
			for(FuncionalidadRol fr : funcionalidadRolList){
				Funcionalidad f = fDto.transformarEntidadSinDependencias(fr.getFuncionalidad());
				f.setEstadoRegistro(fr.getEstadoRegistro());
				funcs.add(f);
			}
			rDetached.setFuncionalidadList(funcs);
			resultado.add(rDetached);
		}
		return resultado;
	}
	
	/**
	 * ADM-C-020
	 * Consulta la lista de roles por los que puede filtrar el usuario en la funcionalidad de consuta de usuarios dependiendo del rol que tenga
	 * @return
	 */
	@Override
	public List<RolBasicoDTO> consultarListaRolesParaConsultaUsuarios(String nombreRolUsuario){
		
		String grupoUsuario = this.consultarGrupoUsuario(nombreRolUsuario);
		List<RolBasicoDTO> listaRoles = null;
		
		switch (grupoUsuario){
			case UtilDominios.AGRUPADOR_RPCU_ROLES_DIRECCION:
				listaRoles = consultarListaRolesPorAgrupadorConsultaUsuario(UtilDominios.AGRUPADOR_RPCU_ROLES_PERMITIDOS_DIRECTOR_CAC);
				break;
			case UtilDominios.AGRUPADOR_RPCU_ROLES_ARBITRAJE:
				listaRoles = consultarListaRolesPorAgrupadorConsultaUsuario(UtilDominios.AGRUPADOR_RPCU_ROLES_PERMITIDOS_ARBITRAJE);
				break;
			case UtilDominios.AGRUPADOR_RPCU_ROLES_CONCILIACION:
				listaRoles = consultarListaRolesPorAgrupadorConsultaUsuario(UtilDominios.AGRUPADOR_RPCU_ROLES_PERMITIDOS_CONCILIACION);
				break;
			case UtilDominios.AGRUPADOR_RPCU_ROLES_INTERNACIONAL:
				listaRoles = consultarListaRolesPorAgrupadorConsultaUsuario(UtilDominios.AGRUPADOR_RPCU_ROLES_PERMITIDOS_INTERNACIONAL);
				break;
			case UtilDominios.AGRUPADOR_RPCU_ROLES_EQUIDAD:
				listaRoles = consultarListaRolesPorAgrupadorConsultaUsuario(UtilDominios.AGRUPADOR_RPCU_ROLES_PERMITIDOS_EQUIDAD);
				break;
			default:
				throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR503.toString()));
		}
		
		return listaRoles;
	}
	
	/**
	 * ADM-C-020
	 * Devuelve la lista de roles que puede consultar el usuario dependiendo del grupo al que pertenece (Arbitraje, Conciliación o Dirección).
	 * Los roles se ordenan alfabeticamente.
	 * @param agrupadorConsultaUsuario
	 * @return
	 */
	public List<RolBasicoDTO> consultarListaRolesPorAgrupadorConsultaUsuario(String agrupadorConsultaUsuario){
		List<Dominio> rolesDTO = dominioFacade.obtenerDominiosAgrupados(
				UtilDominios.DOMINIO_AGRUPADOR_ROL_PERSONA_CONSULTA_USUARIO, agrupadorConsultaUsuario);
		SortedSet<RolBasicoDTO> roles = new TreeSet<>();
		for(Dominio rolDTO : rolesDTO){
			roles.add(new RolBasicoDTO(rolDTO.getDominioPK().getCodigo(), rolDTO.getNombre(),
					manejadorParametroServicioSorteo.rolTieneLista(rolDTO.getDominioPK().getCodigo())));
		}
		List<RolBasicoDTO> rolesList = new ArrayList<>();
		rolesList.addAll(roles);
		
		for(int i= 0 ; rolesList.size() > i ; i++) {
			rolesList.get(i).setTipoServicio(manejadorRol.
					consultarTipoServicioRolPorNombre(rolesList.get(i).getCodigoDominio()));
		}
		return rolesList;
	}
	
	/**
	 * Identifica si el usuario pertenece al grupo de arbitraje, conciliación o dirección
	 * @param agrupadorConsultaUsuario
	 * @return
	 */
	@Override
	public String consultarGrupoUsuario(String nombreRolUsuario){
		
		String grupo = "";
		
		if(dominioFacade.validarGrupoContieneDominioPorCodigo(UtilDominios.DOMINIO_AGRUPADOR_ROL_PERSONA_CONSULTA_USUARIO, UtilDominios.AGRUPADOR_RPCU_ROLES_DIRECCION, nombreRolUsuario)){
			grupo = UtilDominios.AGRUPADOR_RPCU_ROLES_DIRECCION;			
		}else if(dominioFacade.validarGrupoContieneDominioPorCodigo(UtilDominios.DOMINIO_AGRUPADOR_ROL_PERSONA_CONSULTA_USUARIO, UtilDominios.AGRUPADOR_RPCU_ROLES_ARBITRAJE, nombreRolUsuario)){
			grupo = UtilDominios.AGRUPADOR_RPCU_ROLES_ARBITRAJE;
		}else if(dominioFacade.validarGrupoContieneDominioPorCodigo(UtilDominios.DOMINIO_AGRUPADOR_ROL_PERSONA_CONSULTA_USUARIO, UtilDominios.AGRUPADOR_RPCU_ROLES_CONCILIACION, nombreRolUsuario)){
			grupo = UtilDominios.AGRUPADOR_RPCU_ROLES_CONCILIACION;
		}else if(dominioFacade.validarGrupoContieneDominioPorCodigo(UtilDominios.DOMINIO_AGRUPADOR_ROL_PERSONA_CONSULTA_USUARIO, UtilDominios.AGRUPADOR_RPCU_ROLES_INTERNACIONAL, nombreRolUsuario)){
			grupo = UtilDominios.AGRUPADOR_RPCU_ROLES_INTERNACIONAL;
		}else if(dominioFacade.validarGrupoContieneDominioPorCodigo(UtilDominios.DOMINIO_AGRUPADOR_ROL_PERSONA_CONSULTA_USUARIO, UtilDominios.AGRUPADOR_RPCU_ROLES_EQUIDAD, nombreRolUsuario)){
			grupo = UtilDominios.AGRUPADOR_RPCU_ROLES_EQUIDAD;
		}else{
			throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR503.toString()));
		}
			
		return grupo;
		
	}
	
	/**
	 * Consulta los dominios de los roles por el agrupador AGRUPADOR_ROL_PERSONA
	 *  
	 * @return
	 */
	public List<DominioBasicoDTO> consultarRolesPorClasificador(String codigoClasificador){
		List<Dominio> roles = dominioFacade.obtenerDominiosAgrupados(UtilDominios.DOMINIO_AGRUPADOR_ROL_PERSONA, codigoClasificador);
		
		return dominioFacade.transformarListaEntidadAListaDominioBasicoDTO(roles);
	}
	
	/**
	 * Consulta los códigos de dominios de los roles agrupados por el agrupador que se pase como parámetro del grupo AGRUPADOR_ROL_PERSONA 
	 */
	public List<String> consultarCodigosRolesPorClasificador(String codigoClasificador){
		List<Dominio> roles= dominioFacade.obtenerDominiosAgrupados(UtilDominios.DOMINIO_AGRUPADOR_ROL_PERSONA, codigoClasificador);
		List<String> codigosRoles = new ArrayList<>();
		if(roles!=null){
			for(Dominio rol : roles){
				codigosRoles.add(rol.getDominioPK().getCodigo());
			}
		}
		
		return codigosRoles;
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolFacade#consultarRolesPorServicio(java.lang.Long)
	 */
	@Override
	public List<RolDTO> consultarRolesPorServicio(String tipoServicio) {
		return (List<RolDTO>) transformarColeccionSinDependencias(manejadorRol.consultarRolPorServicio(tipoServicio),
				new ArrayList<RolDTO>());
	}

	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolFacade#crearRol(com.ccb.simasc.transversal.dto.RolDTO, java.lang.String)
	 */
	@Override
	public Long crearRol(RolDTO rolDTO, String tipoServicio){
		Rol rol = null;
		String codigo = manejadorRol.validarRolNoExistente(rolDTO.getNombre());
		if (codigo != null && !codigo.isEmpty()) {
			insertarRol(rolDTO, codigo, tipoServicio);
			rol = obtenerRolPorNombre(codigo);
			insertarDominio(rolDTO, codigo);
		} else {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR280.toString()));
		}
		return rol.getIdRol();
	}
	
	/**
	 * Método para insertar un nuevo rol
	 * @param rolDTO
	 * @param nombre
	 */
	private void insertarRol(RolDTO rolDTO, String nombre, String tipoServicio) {
		Rol nuevoRol = new Rol();
		nuevoRol.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		nuevoRol.setFechaCreacion(new Date());
		nuevoRol.setFechaUltimaModificacion(new Date());
		String usuarioActual = appContext.getContextoSesion().getIdUsuario() != null 
				? appContext.getContextoSesion().getNombreUsuario() : UtilConstantes.USUARIO_DEFECTO_SIMASC;
		nuevoRol.setIdUsuarioModificacion(usuarioActual);
		nuevoRol.setNombre(nombre);
		nuevoRol.setPreseleccion(rolDTO.getPreseleccion());
		nuevoRol.setTipoServicio(tipoServicio);
		manejadorRol.crear(nuevoRol);
		nuevoRol.setFuncionalidadRolList(insertarPermisos(nuevoRol.getIdRol()));
		manejadorRol.actualizar(nuevoRol);
	}
	

	
	/**
	 * Método para negar el acceso a todas las funcionalidades de la aplicacion para el nuevo rol
	 * @param idRol
	 * @return
	 */
	private List<FuncionalidadRol> insertarPermisos(Long idRol) {
		List<Funcionalidad> funcionalidades = manejadorFuncionalidad.consultar(filtro(TipoFiltro.DIFERENTE,
				Funcionalidad.ENTIDAD_FUNCIONALIDAD_NOMBRE_TIPO_FUNCIONALIDAD, UtilDominios.TIPO_FUNCIONALIDAD_SORTEO),
				null, null);
		List<FuncionalidadRol> funcionalidadesRol = new ArrayList<FuncionalidadRol>();
		for (Funcionalidad funcionalidad : funcionalidades) {
			FuncionalidadRol funcionalidadRol = new FuncionalidadRol();
			FuncionalidadRolPK pk = new FuncionalidadRolPK();
			pk.setIdRol(idRol);
			pk.setNombreFuncionalidad(funcionalidad.getNombre());
			funcionalidadRol.setFuncionalidadRolPK(pk);
			funcionalidadRol.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
			funcionalidadRol.setFechaUltimaModificacion(new Date());
			String usuarioActual = appContext.getContextoSesion().getNombreUsuario() != null 
					? appContext.getContextoSesion().getNombreUsuario() : UtilConstantes.USUARIO_DEFECTO_SIMASC;
			funcionalidadRol.setIdUsuarioModificacion(usuarioActual);
			funcionalidadesRol.add(funcionalidadRol);
		}
		
		return funcionalidadesRol;
	}
	
	/**
	 * Método para insertar un nuevo dominio
	 * @param rolDTO
	 * @param codigo
	 */
	private void insertarDominio(RolDTO rolDTO, String codigo) {
		Dominio nuevoDominio = new Dominio();
		nuevoDominio.setDescripcion(rolDTO.getNombre());
		nuevoDominio.setNombre(rolDTO.getNombre());
		nuevoDominio.setDominioPK(new DominioPK(codigo, UtilDominios.DOMINIO_ROL_PERSONA));
		dominioFacade.getManejadorCrud().crearSinAtributosAuditoria(nuevoDominio);
		dominioFacade.recargarDominios();
	}
	
	/**
	 * ADM-C-021
	 * ADM-C-003
	 * Consulta los roles que definen lista, es decir, aquellos definidos en la tabla
	 * ParametroServicioSorteo con el indicador sorteo_con_lista en verdadero.
	 * @return
	 */
	public List<String> consultarNombreRolesQueManejanLista(){
		
		List<Rol> roles = this.manejadorRol.consultarRolesQueManejanLista();
		List<String> nombreRoles = new ArrayList<>();
		for(Rol rol : roles){
			nombreRoles.add(rol.getNombre());
		}
		
		return nombreRoles;
	}
	
	/**
	 * ADM-C-003
	 * Consulta los roles que definen materia, es decir, aquellos definidos en la tabla
	 * ParametroServicioSorteo con el indicador sorteo_con_materia en verdadero.
	 * @return
	 */
	public List<String> consultarNombreRolesQueManejanMateria(){
		
		List<Rol> roles = this.manejadorRol.consultarRolesQueManejanMateria();
		List<String> nombreRoles = new ArrayList<>();
		for(Rol rol : roles){
			nombreRoles.add(rol.getNombre());
		}
		
		return nombreRoles;
	}	
	 	
	@Override
	public RolBasicoDTO convertirRolARolBasico(Rol rol){
		RolBasicoDTO rolbasico = new RolBasicoDTO();
		rolbasico.setIdRol(rol.getIdRol());
		rolbasico.setCodigoDominio(rol.getNombre());
		rolbasico.setTipoServicio((rol.getTipoServicio()));
		String dominio = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ROL_PERSONA, rol.getNombre());
		rolbasico.setNombre(dominio);		
		return rolbasico;		
	}
	
	@Override
	public List<RolBasicoDTO> convertirRolesARolBasico(List<Rol> roles){
		List<RolBasicoDTO> rolesBasico = new ArrayList<RolBasicoDTO>();
		if(roles != null && !roles.isEmpty()){
			for (Rol rol : roles) {
				rolesBasico.add(this.convertirRolARolBasico(rol));
			}			
		}
		return rolesBasico;
	}
	

	@Override
	public List<RolBasicoDTO> consutarRolesBasicosPorListaTipoServicio(List<String> tipoServicio, boolean transversales){
		
		List<Rol> roles = manejadorRol.consutarRolesPorListaTipoServicio(tipoServicio, transversales);		
		List<RolBasicoDTO> rolBasico = this.convertirRolesARolBasico(roles);
		if(rolBasico != null && !rolBasico.isEmpty()){
			Collections.sort(rolBasico, new Comparator<RolBasicoDTO>() {
				@Override
				public int compare(RolBasicoDTO o1, RolBasicoDTO o2){
					return o1.getNombre().compareTo(o2.getNombre());
				}
			});			
		}
		
		return rolBasico;
		
	}
	
	@Override
	public List<RolDTO> consultarRolesPorPersona(Long idPersona){
		
		return (List<RolDTO>) transformarColeccionSinDependencias(manejadorRolPersona.consultarRolesPersona(idPersona),new ArrayList<RolDTO>());
	}
	@Override
	public List<Long> obtenerServiciosPorRolSorteo( Long codigoRol){
	
		return manejadorRol.obtenerServiciosPorRolSorteo(codigoRol);
		
	}
	@Override
	public List<RolDTO> consultarRolesSorteo(){
		List<RolDTO> listaRoles = (List<RolDTO>) transformarColeccionSinDependencias(manejadorRol.consultarRolesSorteo(),new ArrayList<RolDTO>());
		for (RolDTO obj : listaRoles){
			obj.setNombreCompleto(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ROL_PERSONA, obj.getNombre()));
		}
		return listaRoles;
	}

	@Override
	public void actualizarRol(Rol rol) {
		String usuarioActual = appContext.getContextoSesion().getIdUsuario() != null 
				? appContext.getContextoSesion().getNombreUsuario() : UtilConstantes.USUARIO_DEFECTO_SIMASC;
				rol.setIdUsuarioModificacion(usuarioActual);
				rol.setFechaUltimaModificacion(new Date());
				manejadorRol.actualizar(rol);
		
	}

	@Override
	public Rol consultarRolPorId(Long idRol) {
		RolDTO rolbasico = manejadorRol.consultarRolBasicoPorId(idRol);
		Rol rol = new Rol();
		rol.setAplicaMauc(rolbasico.isAplicaMauc());
		rol.setIdRol(rolbasico.getIdRol());
		rol.setNombre(rolbasico.getNombre());
		rol.setEstadoRegistro(rolbasico.getEstadoRegistro());
		rol.setFechaUltimaModificacion(rolbasico.getFechaUltimaModificacion());
		rol.setFechaCreacion(rolbasico.getFechaCreacion());
		rol.setIdUsuarioModificacion(rolbasico.getIdUsuarioModificacion());
		rol.setPreseleccion(rolbasico.getPreseleccion());
		rol.setTipoServicio(rolbasico.getTipoServicio());
		
		if(rol.getIdRol() == null) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR201.toString());
			throw new SimascException(mensajeError);
		}
		return rol;
	}

	
	// protected region metodos adicionales end
	
}
