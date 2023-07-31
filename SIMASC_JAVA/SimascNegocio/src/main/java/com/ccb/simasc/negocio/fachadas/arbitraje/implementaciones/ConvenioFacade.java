package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.LockModeType;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.enumeraciones.TipoOrdenamiento;
import com.ccb.simasc.integracion.manejadores.ManejadorConvenio;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronico;
import com.ccb.simasc.integracion.manejadores.ManejadorMateria;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPlantillaCarta;
import com.ccb.simasc.integracion.manejadores.ManejadorRelacionadoConvenio;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorSede;
import com.ccb.simasc.integracion.manejadores.ManejadorSedeConvenio;
import com.ccb.simasc.integracion.manejadores.ManejadorServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorTelefono;
import com.ccb.simasc.integracion.manejadores.ManejadorUbicacion;
import com.ccb.simasc.integracion.manejadores.ManejadorZonaGeografica;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionOrdenamiento;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IConvenioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITelefonoFacade;
import com.ccb.simasc.transversal.dto.ConvenioDTO;
import com.ccb.simasc.transversal.dto.CrearConvenioDTO;
import com.ccb.simasc.transversal.dto.InformacionFiltroDTO;
import com.ccb.simasc.transversal.dto.PlantillaCartaDTO;
import com.ccb.simasc.transversal.dto.TelefonoDTO;
import com.ccb.simasc.transversal.dto.UbicacionDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Convenio;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.Materia;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PlantillaCarta;
import com.ccb.simasc.transversal.entidades.RelacionadoConvenio;
import com.ccb.simasc.transversal.entidades.RelacionadoConvenioPK;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.Sede;
import com.ccb.simasc.transversal.entidades.SedeConvenio;
import com.ccb.simasc.transversal.entidades.SedeConvenioPK;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.entidades.Telefono;
import com.ccb.simasc.transversal.entidades.Ubicacion;
import com.ccb.simasc.transversal.entidades.ZonaGeografica;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Convenio}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ConvenioFacade extends AccesoFacade<Convenio, Long, ConvenioDTO> implements IConvenioFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorConvenio manejadorConvenio;
	
	@EJB
	private ManejadorTelefono manejadorTelefono;
	
	@EJB
	private ManejadorPersona manejadorPersona;
	
	@EJB
	private ManejadorUbicacion manejadorUbicacion;
	
	@EJB
	private ManejadorPlantillaCarta manejadorPlantillaCarta;
	
	@EJB
	private ManejadorSedeConvenio manejadorSedeConvenio;
	
	@EJB
	private ManejadorRelacionadoConvenio manejadorRelacionadoConvenio;
	
	@EJB
	private ManejadorCorreoElectronico manejadorCorreoElectronico;
	
	@EJB
	private ManejadorServicio manejadorServicio;
	
	
	@EJB 
	private ManejadorRol manejadorRol;
	
	@EJB
	private ManejadorZonaGeografica manejadorZonaGeografica;
	
	@EJB
	private ITelefonoFacade telefonoFacade;
	
	@EJB
	private ManejadorMateria manejadorMateria;
	
	@EJB
	private ManejadorSede manejadorSede;	

	
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorConvenio;
	}

	@Override
	public ConvenioDTO transformarSinDependencias(Convenio obj) {
		return new ConvenioDTO().transformarSinDependencias(obj);
	}

	@Override
	public ConvenioDTO transformarConDependencias(Convenio obj) {
		return new ConvenioDTO().transformarConDependencias(obj);
	}

	@Override
	public Convenio transformarEntidadConDependencias(Convenio obj) {
		return new ConvenioDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Convenio transformarEntidadSinDependencias(Convenio obj) {
		return new ConvenioDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IConvenioFacade#consultarConvenios()
	 */
	@Override
	public List<ConvenioDTO> consultarConvenios() {
		List<InformacionFiltro> filtros = new ArrayList<>();
		InformacionFiltro estadoRegistro = new InformacionFiltro(TipoFiltro.EXACTO,
				Convenio.ENTIDAD_CONVENIO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		filtros.add(estadoRegistro);
		InformacionOrdenamiento ifoOrden = new InformacionOrdenamiento(TipoOrdenamiento.ASCENDENTE,
				Convenio.ENTIDAD_CONVENIO_NOMBRE);
		return (List<ConvenioDTO>) new ConvenioDTO().transformarColeccionSinDependencias(
				manejadorConvenio.consultar(filtros, Arrays.asList(ifoOrden), null));
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IConvenioFacade#consultarConveniosFiltros(java.util.List)
	 */
	@Override
	public List<ConvenioDTO> consultarConveniosFiltros(List<InformacionFiltroDTO> filtrosParametro) {
		List<ConvenioDTO> retorno = new ArrayList<ConvenioDTO>();
		if(filtrosParametro != null && validarFiltros(filtrosParametro)){
			List<InformacionFiltro> seihe = InformacionFiltro.traducirFiltros(filtrosParametro);
			InformacionOrdenamiento ifoOrden = new InformacionOrdenamiento(TipoOrdenamiento.ASCENDENTE,Convenio.ENTIDAD_CONVENIO_NOMBRE);
			retorno = (List<ConvenioDTO>) new ConvenioDTO().transformarColeccionSinDependencias(manejadorConvenio.consultar(seihe,Arrays.asList(ifoOrden) , null));
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
			if(!Convenio.contieneAtributo(filtro.getCampo())) 
				return false;
		}
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IConvenioFacade#consultarConveniosVigentes(java.lang.String, java.lang.Long)
	 */
	@Override
	public List<ConvenioDTO> consultarConveniosVigentes(String tipoConvenio, Long idServicio) {
		return (List<ConvenioDTO>) new ConvenioDTO().transformarColeccionEntidadesSinDependencias(
				manejadorConvenio.consultarConveniosVigentes(tipoConvenio, idServicio));
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IConvenioFacade#consultarConvenioPorRelacionado(java.lang.Long, java.lang.Long, java.util.Date, java.lang.String)
	 */
	@Override
	public List<Convenio> consultarConvenioPorRelacionado(Long idPersona, Long idRol, Date fechaVigencia,
			String tipoConvenio) {
		return manejadorConvenio.consultarConvenioPorRelacionado(idPersona, idRol, fechaVigencia, tipoConvenio);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IConvenioFacade#consultarJornadasSinEjecutar()
	 */
	@Override
	public List<ConvenioDTO> consultarJornadas(List<Long> centros, boolean ejecutadas) {
		return (List<ConvenioDTO>) new ConvenioDTO().transformarColeccionEntidadesSinDependencias(
				manejadorConvenio.consultarJornadas(centros, ejecutadas));
	}

	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IConvenioFacade#actualizarConvenio(com.ccb.simasc.transversal.entidades.Convenio)
	 */
	@Override
	public Convenio actualizarConvenio(Convenio convenio) {
		Convenio modificado = convenio;
		modificado.setFechaUltimaModificacion(new Date());
		if(appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() != null
				&& !appContext.getContextoSesion().getNombreUsuario().isEmpty())
			modificado.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
		else
			modificado.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		if(convenio.getIdConvenio() != null)
			actualizar(modificado);
		else
			crear(modificado);
		return modificado;
	}
	
	@Override
	public List<ConvenioDTO> consultarConveniosPorCentro( List<String> centros, Date fechaConsulta ){
		Long rolConsulta = null;
		Long personaConsulta = null;
		if(appContext.getContextoSesion() == null || appContext.getContextoSesion().getIdUsuario() == null
				|| appContext.getContextoSesion().getRolPrincipal() == null ){
			throw new SimascException("Error con los datos de sesión del usuario");
		}
		Rol rolActual = manejadorRol.buscar(Long.parseLong( appContext.getContextoSesion().getRolPrincipal()));
		if(rolActual == null){
			throw new SimascException("Error con los datos de sesión del usuario");
		}
		
		if(UtilDominios.ROL_PERSONA_RESPONSABLE_DE_CONVENIO.equals( rolActual.getNombre()) ){
			rolConsulta = Long.parseLong(appContext.getContextoSesion().getRolPrincipal());
			personaConsulta = Long.parseLong( appContext.getContextoSesion().getIdUsuario());
		}
		
		
		List<Convenio> convenios = manejadorConvenio.consultarConveniosPorCentro( centros, fechaConsulta, personaConsulta, rolConsulta );
		return (List<ConvenioDTO>) new ConvenioDTO().transformarColeccionEntidadesSinDependencias(convenios);
	}
	
	
	@Override
	public List<ConvenioDTO> consultarConveniosPorNombreCodigo( String nombreConvenio, Long idConvenio, List<Long> centros ){
	
		String nombreDelConvenio = nombreConvenio;
		Long idDelConvenio = idConvenio;
		if(nombreDelConvenio.equals("-1")){
			nombreDelConvenio=null;
		}
		if(idDelConvenio==-1){
			idDelConvenio=null;
		}
		return manejadorConvenio.consultarConveniosPorNombreCodigo(nombreDelConvenio, idDelConvenio, centros);
	}
	
	
	@Override
	public ConvenioDTO crearConvenio( CrearConvenioDTO informacionConvenio, String idUsuario ){
		Persona persona = new Persona();
		//Busca la persona (Empresa) si existe actualiza información, caso contrario crea nuevo registro	
		if(informacionConvenio.getIdPersona()!=null){
			persona = manejadorPersona.buscar(informacionConvenio.getIdPersona());
			persona.setTipoPersona(informacionConvenio.getCodTipoPersona());
			persona.setTipoDocumento(informacionConvenio.getCodTipoDocumento());
			persona.setNumeroDocumento(informacionConvenio.getNumeroIdentificacion());
			persona.setPrimerNombreORazonSocial(informacionConvenio.getPrimerNombre());
			persona.setRepresentanteLegal(informacionConvenio.getNombreRepresentanteLegal());
			persona.setIdPaisOrigen(informacionConvenio.getIdPais());		
			manejadorPersona.actualizar(persona);
		}else{
			persona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			persona.setEstadoPersona(UtilDominios.ESTADO_PERSONA_ACTIVO);
			persona.setTipoPersona(informacionConvenio.getCodTipoPersona());
			persona.setPrimerNombreORazonSocial(informacionConvenio.getPrimerNombre());
			persona.setTipoDocumento(informacionConvenio.getCodTipoDocumento());
			persona.setNumeroDocumento(informacionConvenio.getNumeroIdentificacion());
			persona.setFechaUltimaModificacion(new Date());
			persona.setIdUsuarioModificacion(idUsuario);
			persona.setRepresentanteLegal(informacionConvenio.getNombreRepresentanteLegal());
			persona.setIdPaisOrigen(informacionConvenio.getIdPais());
			manejadorPersona.crear(persona);
			
		}
		
		this.crearActualizarTelefonoConvenio(informacionConvenio, idUsuario, persona.getIdPersona());
		this.actualizarCrearDireccion(informacionConvenio, idUsuario, persona.getIdPersona());
		this.crearActualizarCorreoConvenio(informacionConvenio, idUsuario, persona.getIdPersona());
		
		Convenio convenioCreado = this.insertarConvenio(informacionConvenio, idUsuario, persona.getIdPersona());

		this.crearRelacionadoConvenio(convenioCreado.getIdConvenio(), informacionConvenio.getIdFuncionarioResponsable(), idUsuario);	
		this.crearSedeConvenio(informacionConvenio.getIdSedes(), idUsuario, convenioCreado.getIdConvenio());
		if(informacionConvenio.getIdPlantilla()!=null){
			this.actualizarPlantillaCarta(convenioCreado.getIdConvenio(), informacionConvenio.getIdPlantilla());	
		}
		return (ConvenioDTO) transformarSinDependencias(convenioCreado);
	}
	
	private void actualizarPlantillaCarta(Long idConvenio, Long idPlantillaCarta){
	
		PlantillaCarta plantillaCarta = manejadorPlantillaCarta.buscar(idPlantillaCarta);
		plantillaCarta.setIdConvenio(idConvenio);
		manejadorPlantillaCarta.actualizar(plantillaCarta);

	}
	
	private void crearSedeConvenio(List<Long> sedes, String idUsuario, Long idConvenio){
		
		for (int i = 0; i < sedes.size(); i++) {
			SedeConvenio sedeConvenio = new SedeConvenio();
			sedeConvenio.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			sedeConvenio.setFechaUltimaModificacion(new Date());
			sedeConvenio.setIdUsuarioModificacion(idUsuario);
			SedeConvenioPK sedePk = new SedeConvenioPK();
			sedePk.setIdConvenio(idConvenio);
			sedePk.setIdSede(sedes.get(i));			
			sedeConvenio.setSedeConvenioPK(sedePk);
			manejadorSedeConvenio.crear(sedeConvenio);
		}
		
		
	}
	
	private void crearRelacionadoConvenio(Long idConvenio, Long idFuncionario,  String idUsuario){
		RelacionadoConvenio relacionadoConvenio = new RelacionadoConvenio();
		relacionadoConvenio.setIdUsuarioModificacion(idUsuario);
		relacionadoConvenio.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		relacionadoConvenio.setFechaUltimaModificacion(new Date());		
		
		List<Rol> rol = manejadorRol.consultarRolesPorNombre(UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION);

		RelacionadoConvenioPK relacionado = new RelacionadoConvenioPK();
		relacionado.setIdConvenio(idConvenio);
		relacionado.setIdPersona(idFuncionario);
		relacionado.setIdRol(rol.get(0).getIdRol());
		
		relacionadoConvenio.setRelacionadoConvenioPK(relacionado);
		manejadorRelacionadoConvenio.crear(relacionadoConvenio);
	}
	
	private Convenio insertarConvenio(CrearConvenioDTO informacion, String idUsuario, Long idPersona){
		
		Convenio nuevoConvenio = new Convenio();
		nuevoConvenio.setTipoConvenio(UtilDominios.TIPO_CONVENIO_CONVENIO);
		nuevoConvenio.setNombre(informacion.getNombreConvenio());
		nuevoConvenio.setFechaFinVigencia(null);
		nuevoConvenio.setFechaInicioVigencia(null);
		nuevoConvenio.setLimiteInferiorDiasProgramacionAudiencias(informacion.getNumeroDiasHabilesInicioReparto());
		nuevoConvenio.setLimiteSuperiorDiasProgramacionAudiencias(informacion.getMaximoDiasProgramacionAudiencias());
		nuevoConvenio.setRequiereSuplente(informacion.isRequiereSuplente());
		nuevoConvenio.setDuracionAudiencias(informacion.getDuracionAudiencia());
		nuevoConvenio.setDireccion(informacion.getDireccion());
		nuevoConvenio.setIdUsuarioModificacion(idUsuario);
		nuevoConvenio.setIdPersona(idPersona);
		nuevoConvenio.setFechaUltimaModificacion(new Date());
		Servicio servicioConvenio = manejadorServicio.consultarServicioporNombre(UtilDominios.SERVICIO_CONVENIO_CONCILIACION);		
		nuevoConvenio.setIdServicio(servicioConvenio.getIdServicio());
		nuevoConvenio.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		nuevoConvenio.setFechaFinVigencia(new Date());
		nuevoConvenio.setFechaInicioVigencia(new Date());
		nuevoConvenio.setIdMateria(informacion.getIdMateria());
		nuevoConvenio.setIdZonaGeografica(informacion.getIdPais());
		manejadorConvenio.crear(nuevoConvenio);
		
		return nuevoConvenio;
	}
	
	private void crearActualizarTelefonoConvenio(CrearConvenioDTO informacion, String idUsuario, Long idPersona){
		
		Date fechaActual = new Date();
		
		if(informacion.getIdTelefono()!=null){
			Telefono telefonoPersona = manejadorTelefono.buscar(informacion.getIdTelefono());
			if(telefonoPersona!=null){
			telefonoPersona.setIdPersona(informacion.getIdPersona());
			telefonoPersona.setNumero(informacion.getTelefono());
			manejadorTelefono.actualizar(telefonoPersona);
			}
		}else{
			Telefono nuevoTelefono = new Telefono();
			nuevoTelefono.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			nuevoTelefono.setFechaUltimaModificacion(fechaActual);
			nuevoTelefono.setIdPersona(idPersona);
			nuevoTelefono.setNumero(informacion.getTelefono());
			nuevoTelefono.setIdUsuarioModificacion(idUsuario);
			nuevoTelefono.setTipoTelefono(informacion.getTipoTelefono());
			manejadorTelefono.crear(nuevoTelefono);
		}
	}
	
	private void actualizarCrearDireccion(CrearConvenioDTO informacion, String idUsuario, Long idPersona){
		Ubicacion direccion = new Ubicacion();
		if(informacion.getIdDireccion()!=null){
			direccion = manejadorUbicacion.buscar(informacion.getIdDireccion());
			direccion.setDireccion(informacion.getDireccion());
			direccion.setFechaUltimaModificacion(new Date());
			if(informacion.getIdCiudad()!=null){
				direccion.setIdZonaGeografica(informacion.getIdCiudad());
			}  
			else{
				direccion.setIdZonaGeografica(informacion.getIdPais());
			}
			manejadorUbicacion.actualizar(direccion);
		}
		else{
			direccion.setDireccion(informacion.getDireccion());
			direccion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			direccion.setFechaUltimaModificacion(new Date());
			direccion.setIdPersona(idPersona);	
			direccion.setTipo(UtilDominios.TIPO_UBICACION_PRINCIPAL);
			if(informacion.getIdCiudad()!=null){
				direccion.setIdZonaGeografica(informacion.getIdCiudad());
			}
			else{
				direccion.setIdZonaGeografica(informacion.getIdPais());
			}
			direccion.setIdUsuarioModificacion(idUsuario);	
			manejadorUbicacion.crear(direccion);
		}
		
	}
	
	private void crearActualizarCorreoConvenio(CrearConvenioDTO informacion, String idUsuario, Long idPersona){
		CorreoElectronico correo = new CorreoElectronico();
		if(informacion.getIdCorreo()!=null){
			correo = manejadorCorreoElectronico.buscar(informacion.getIdCorreo());	
			correo.setFechaUltimaModificacion(new Date());
			correo.setIdUsuarioModificacion(idUsuario);
			correo.setDireccion(informacion.getCorreoElectronico());
			manejadorCorreoElectronico.actualizar(correo);
		}else{
			correo.setDireccion(informacion.getCorreoElectronico());
			correo.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			correo.setFechaUltimaModificacion(new Date());
			correo.setIdPersona(idPersona);
			correo.setTipo(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
			correo.setIdUsuarioModificacion(idUsuario);
			manejadorCorreoElectronico.crear(correo);
		}
		
			
	}
	
	@Override
	public CrearConvenioDTO consultarInformacionConvenio(Long idConvenio) {
		
		Convenio convenio = manejadorConvenio.buscar(idConvenio);
		Persona persona = manejadorPersona.buscar(convenio.getIdPersona());
		Materia materiaConvenio = manejadorMateria.buscar(convenio.getIdMateria());
		ZonaGeografica paisEmpresa = new ZonaGeografica();
		ZonaGeografica ciudad = new ZonaGeografica();
		UbicacionDTO direccion = new UbicacionDTO();
		List<Telefono> telefonos = new ArrayList<Telefono>(); 
		if(persona!=null && persona.getIdPersona()!=null){
			paisEmpresa = manejadorZonaGeografica.buscar(persona.getIdPaisOrigen()); 
			direccion = manejadorUbicacion.consultarPrimeraUbicacionPersona(persona.getIdPersona());
			if((direccion!=null && direccion.getIdZonaGeografica()!=null) && persona.getIdPaisOrigen().equals(UtilConstantes.CODIGO_NACIONALIDAD_COLOMBIA)){
				ciudad = manejadorZonaGeografica.buscar(direccion.getIdZonaGeografica());
			}
			telefonos = manejadorTelefono.consultarTelefonosPersona(persona.getIdPersona(), true);
		}
		
		 
		
		List<String> roles = Arrays.asList(UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION);
		
		List<PersonaBasicaDTO> funcionarioResponsable = manejadorRelacionadoConvenio.consultarPersonasRelacionadoConvenio(roles, idConvenio);
		
		List<SedeConvenio> sedesConvenio = manejadorSedeConvenio.consultarSedesConvenio(idConvenio);
		
		PlantillaCartaDTO plantillaFiltro = new PlantillaCartaDTO();
		plantillaFiltro.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		plantillaFiltro.setIdConvenio(idConvenio);
		
		List<PlantillaCarta> plantillaCartas = manejadorPlantillaCarta.consultarPlantillaCartaFiltros(plantillaFiltro, LockModeType.NONE);
		
		
		CrearConvenioDTO informacionConvenio = new CrearConvenioDTO();
		
		if(persona!=null && persona.getIdPersona()!=null){
			informacionConvenio.setIdPersona(persona.getIdPersona());
			informacionConvenio.setCodTipoPersona(persona.getTipoPersona());
			informacionConvenio.setCodTipoDocumento(persona.getTipoDocumento());
			informacionConvenio.setNumeroIdentificacion(persona.getNumeroDocumento());
			if((direccion!=null && direccion.getIdZonaGeografica()!=null) && persona.getIdPaisOrigen().equals(UtilConstantes.CODIGO_NACIONALIDAD_COLOMBIA)){
				informacionConvenio.setIdCiudad(ciudad.getIdZonaGeografica());	
				informacionConvenio.setNombreCiudad(ciudad.getNombre());
			}
			informacionConvenio.setPrimerNombre(persona.getPrimerNombreORazonSocial());
			informacionConvenio.setNombreRepresentanteLegal(persona.getRepresentanteLegal());
		}
		informacionConvenio.setPrimerNombre(convenio.getNombre());
		informacionConvenio.setDuracionAudiencia(convenio.getDuracionAudiencias());		
		informacionConvenio.setIdPais((paisEmpresa.getIdZonaGeografica()));		
		if((direccion!=null && direccion.getIdZonaGeografica()!=null)){
			informacionConvenio.setIdDireccion(direccion.getIdUbicacion());
			informacionConvenio.setDireccion(direccion.getDireccion());		
		}	
		
		informacionConvenio.setTelefonos((List<TelefonoDTO>) telefonoFacade.transformarColeccionSinDependencias(telefonos, new ArrayList<TelefonoDTO>()));		
		informacionConvenio.setIdMateria(convenio.getIdMateria());
		informacionConvenio.setNombreMateria(materiaConvenio.getNombre());
		informacionConvenio.setRequiereSuplente(convenio.getRequiereSuplente());
		informacionConvenio.setNumeroDiasHabilesInicioReparto(convenio.getLimiteInferiorDiasProgramacionAudiencias());		
		informacionConvenio.setMaximoDiasProgramacionAudiencias(convenio.getLimiteSuperiorDiasProgramacionAudiencias());
		if(!funcionarioResponsable.isEmpty()){
			informacionConvenio.setIdFuncionarioResponsable(funcionarioResponsable.get(0).getIdPersona());
			informacionConvenio.setNombreFuncionarioResponsable(funcionarioResponsable.get(0).getNombreCompleto());
		}
		List<String> nombreSedes = new ArrayList<String>();		
		List<Long> idSedes = new ArrayList<Long>();
		for (SedeConvenio sed : sedesConvenio) {
			Sede sede = manejadorSede.buscar(sed.getSedeConvenioPK().getIdSede());			
			nombreSedes.add(sede.getNombre());
			idSedes.add(sede.getIdSede());
		}
		informacionConvenio.setNombreConvenio(convenio.getNombre());
		informacionConvenio.setIdSedes(idSedes);
		informacionConvenio.setNombreSedes(nombreSedes);
		if(!plantillaCartas.isEmpty()){
			informacionConvenio.setIdPlantilla(plantillaCartas.get(0).getIdPlantillaCarta());
			informacionConvenio.setNombrePlantilla(plantillaCartas.get(0).getNombre());	
		}
		
				
		return informacionConvenio;
	}
	
	
	@Override
	public void actualizarInformacionConvenio(CrearConvenioDTO informacionConvenio, String idUsuario) {
		
		
		Persona persona = manejadorPersona.buscar(informacionConvenio.getIdPersona());
		if(persona!=null && persona.getIdPersona()!=null){
			persona.setIdPaisOrigen(informacionConvenio.getIdPais());
			persona.setPrimerNombreORazonSocial(informacionConvenio.getPrimerNombre());
			persona.setRepresentanteLegal(informacionConvenio.getNombreRepresentanteLegal());
			persona.setTipoDocumento(informacionConvenio.getCodTipoDocumento());
			persona.setNumeroDocumento(informacionConvenio.getNumeroIdentificacion());
	    	manejadorPersona.actualizar(persona);
	    }else{
	    	persona = new Persona();
	    	persona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			persona.setEstadoPersona(UtilDominios.ESTADO_PERSONA_ACTIVO);
			persona.setTipoPersona(informacionConvenio.getCodTipoPersona());
			persona.setPrimerNombreORazonSocial(informacionConvenio.getPrimerNombre());
			persona.setTipoDocumento(informacionConvenio.getCodTipoDocumento());
			persona.setNumeroDocumento(informacionConvenio.getNumeroIdentificacion());
			persona.setFechaUltimaModificacion(new Date());
			persona.setIdUsuarioModificacion(idUsuario);
			persona.setRepresentanteLegal(informacionConvenio.getNombreRepresentanteLegal());
			persona.setIdPaisOrigen(informacionConvenio.getIdPais());
			manejadorPersona.crear(persona);
	    }
		
		Ubicacion ubicacion = manejadorUbicacion.buscar(informacionConvenio.getIdDireccion());
		this.actualizarUbicacion(informacionConvenio, idUsuario, ubicacion, persona);
		
		
		Telefono telefono = manejadorTelefono.buscar(informacionConvenio.getIdTelefono());
		if(telefono!=null){
			telefono.setNumero(informacionConvenio.getTelefono());
			manejadorTelefono.actualizar(telefono);
		}else{
			Telefono nuevoTelefono = new Telefono();
			nuevoTelefono.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			nuevoTelefono.setFechaUltimaModificacion(new Date());
			nuevoTelefono.setIdPersona(persona.getIdPersona());
			nuevoTelefono.setNumero(informacionConvenio.getTelefono());
			nuevoTelefono.setIdUsuarioModificacion(idUsuario);
			nuevoTelefono.setTipoTelefono(informacionConvenio.getTipoTelefono());
			manejadorTelefono.crear(nuevoTelefono);
		}
			
		Convenio convenio = manejadorConvenio.buscar(informacionConvenio.getIdConvenio());
		convenio.setIdMateria(informacionConvenio.getIdMateria());
		convenio.setRequiereSuplente(informacionConvenio.isRequiereSuplente());
		convenio.setNombre(informacionConvenio.getNombreConvenio());
		convenio.setDuracionAudiencias(informacionConvenio.getDuracionAudiencia());
		convenio.setLimiteInferiorDiasProgramacionAudiencias(informacionConvenio.getNumeroDiasHabilesInicioReparto());
		convenio.setLimiteSuperiorDiasProgramacionAudiencias(informacionConvenio.getMaximoDiasProgramacionAudiencias());
		convenio.setIdPersona(persona.getIdPersona());
		convenio.setFechaUltimaModificacion(new Date());
		convenio.setDireccion(informacionConvenio.getDireccion());
		manejadorConvenio.actualizar(convenio);
		
		List<Rol> rol = manejadorRol.consultarRolesPorNombre(UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION);
		List<String> roles= new ArrayList<String>();
		roles.add(rol.get(0).getNombre());
		
		List<PersonaBasicaDTO> funcionarioAntiguo = manejadorRelacionadoConvenio.consultarPersonasRelacionadoConvenio(roles, informacionConvenio.getIdConvenio());
		
		if(!funcionarioAntiguo.isEmpty()){
		
			RelacionadoConvenioPK relPK = new RelacionadoConvenioPK();
			relPK.setIdConvenio(informacionConvenio.getIdConvenio());
			relPK.setIdPersona(funcionarioAntiguo.get(0).getIdPersona());
			relPK.setIdRol(rol.get(0).getIdRol());
			RelacionadoConvenio funcionarioResponsableAntiguo = manejadorRelacionadoConvenio.buscar(relPK);
			funcionarioResponsableAntiguo.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
			manejadorRelacionadoConvenio.actualizar(funcionarioResponsableAntiguo);
			
		}
		
		RelacionadoConvenioPK relPk = new RelacionadoConvenioPK();
		relPk.setIdConvenio(informacionConvenio.getIdConvenio());
		relPk.setIdPersona(informacionConvenio.getIdFuncionarioResponsable());
		relPk.setIdRol(rol.get(0).getIdRol());
		RelacionadoConvenio funcionarioResponsable = manejadorRelacionadoConvenio.buscar(relPk);
		
		if(funcionarioResponsable!=null){
			if(funcionarioResponsable.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_INACTIVO)){
				funcionarioResponsable.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				manejadorRelacionadoConvenio.actualizar(funcionarioResponsable);
			}
		}else{
		
			this.crearRelacionadoConvenio(informacionConvenio.getIdConvenio(), informacionConvenio.getIdFuncionarioResponsable(), idUsuario);
		}
		
		
		List<SedeConvenio> sedesAntiguas = manejadorSedeConvenio.consultarSedesConvenio(informacionConvenio.getIdConvenio());
		List<Long> idSedesNuevas = informacionConvenio.getIdSedes();
		List<SedeConvenio> sedesConvenioNueva = new ArrayList<SedeConvenio>();
		
		this.actualizarSedesConvenio(informacionConvenio, sedesAntiguas, idSedesNuevas, sedesConvenioNueva, idUsuario);
		
		PlantillaCartaDTO plantillaCarta = new PlantillaCartaDTO();
		plantillaCarta.setIdConvenio(informacionConvenio.getIdConvenio());
		plantillaCarta.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		List<PlantillaCarta> plantillaAntigua = manejadorPlantillaCarta.consultarPlantillaCartaFiltros(plantillaCarta, null);
		if(!plantillaAntigua.isEmpty()){
		PlantillaCarta plantillaAntiguaActualizacion = plantillaAntigua.get(0);
		plantillaAntiguaActualizacion.setIdConvenio(null);
		manejadorPlantillaCarta.actualizar(plantillaAntiguaActualizacion);
		}
		if(informacionConvenio.getIdPlantilla()!=null){
			PlantillaCarta plantilla = manejadorPlantillaCarta.buscar(informacionConvenio.getIdPlantilla());
			plantilla.setIdConvenio(informacionConvenio.getIdConvenio());
			manejadorPlantillaCarta.actualizar(plantilla);
		}
		
	}
	
	private void actualizarSedesConvenio(CrearConvenioDTO informacionConvenio,List<SedeConvenio> sedesAntiguas,List<Long> idSedesNuevas,List<SedeConvenio> sedesConvenioNueva, String idUsuario){
		
		for (SedeConvenio idSedeAntigua : sedesAntiguas) {
			idSedeAntigua.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
			idSedeAntigua.setFechaUltimaModificacion(new Date());
		}
		manejadorSedeConvenio.actualizarLista(sedesAntiguas);
		
		for (Long idSedeNueva : idSedesNuevas) {
			
			SedeConvenioPK sedePk = new SedeConvenioPK(informacionConvenio.getIdConvenio(), idSedeNueva);
			SedeConvenio sedeAdd = manejadorSedeConvenio.buscar(sedePk);
			if(sedeAdd!=null){
				sedeAdd.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			}else{
				sedeAdd = new SedeConvenio();
				sedeAdd.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				sedeAdd.setFechaUltimaModificacion(new Date());
				sedeAdd.setIdUsuarioModificacion(idUsuario);
				sedeAdd.setSedeConvenioPK(sedePk);
			}
			sedesConvenioNueva.add(sedeAdd);
		}
		if(!sedesConvenioNueva.isEmpty()){
			manejadorSedeConvenio.actualizarLista(sedesConvenioNueva);
		}
		
	}
	
	private void actualizarUbicacion(CrearConvenioDTO informacionConvenio, String idUsuario, Ubicacion ubicacionActualizar, Persona persona){
	
		Ubicacion ubicacion = ubicacionActualizar;
		
		if(ubicacion==null){
			ubicacion = new Ubicacion();
			ubicacion.setDireccion(informacionConvenio.getDireccion());
			ubicacion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			ubicacion.setFechaUltimaModificacion(new Date());
			ubicacion.setIdPersona(persona.getIdPersona());		
			if(informacionConvenio.getIdCiudad()!=null){
				ubicacion.setIdZonaGeografica(informacionConvenio.getIdCiudad());
			}
			else{
				ubicacion.setIdZonaGeografica(informacionConvenio.getIdPais());
			}
			ubicacion.setIdUsuarioModificacion(idUsuario);	
			manejadorUbicacion.crear(ubicacion);
		}
		else{
			if(informacionConvenio.getIdPais().equals(UtilConstantes.CODIGO_NACIONALIDAD_COLOMBIA)){
				ubicacion.setDireccion(informacionConvenio.getDireccion());
				ubicacion.setIdZonaGeografica(informacionConvenio.getIdCiudad());
				ubicacion.setFechaUltimaModificacion(new Date());
			}else{
				ubicacion.setIdZonaGeografica(informacionConvenio.getIdPais());
				ubicacion.setDireccion(informacionConvenio.getDireccion());
				ubicacion.setFechaUltimaModificacion(new Date());
			}
			manejadorUbicacion.actualizar(ubicacion);	
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IConvenioFacade#
	 * consultarConveniosVigentesPorCentro(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ConvenioDTO> consultarConveniosVigentesPorCentro(List<String> centros) {
		List<Convenio> convenios = manejadorConvenio.consultarConveniosVigentesPorCentro(centros);
		return (List<ConvenioDTO>) new ConvenioDTO().transformarColeccionEntidadesSinDependencias(convenios);
	}
	
	// protected region metodos adicionales end
	
}
