package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronico;
import com.ccb.simasc.integracion.manejadores.ManejadorEstadoPersonaRol;
import com.ccb.simasc.integracion.manejadores.ManejadorHistoricoEstadoMotivoPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorHistoricoEstadoPersonaRol;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaServicioMateria;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEstadoPersonaRolFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaFacade;
import com.ccb.simasc.transversal.dto.ConsultaDominioDTO;
import com.ccb.simasc.transversal.dto.DominioDTO;
import com.ccb.simasc.transversal.dto.EstadoPersonaRolDTO;
import com.ccb.simasc.transversal.dto.HistoricoEstadoMotivoPersonaDTO;
import com.ccb.simasc.transversal.entidades.EstadoPersonaRol;
import com.ccb.simasc.transversal.entidades.EstadoPersonaRolPK;
import com.ccb.simasc.transversal.entidades.HistoricoEstadoPersonaRol;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaServicioMateria;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link EstadoPersonaRol}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class EstadoPersonaRolFacade extends AccesoFacade<EstadoPersonaRol, EstadoPersonaRolPK, EstadoPersonaRolDTO> implements IEstadoPersonaRolFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorHistoricoEstadoPersonaRol manejadorHistoricoEstadoPersonaRol;
	
	@EJB
	private ManejadorHistoricoEstadoMotivoPersona manejadorHistoricoEstadoMotivoPersona;
	
	@EJB
	private ManejadorEstadoPersonaRol manejadorEstadoPersonaRol;
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	
	@EJB
	private ManejadorRolPersona manejadorRolPersona;
	
	@EJB
	private ManejadorCorreoElectronico  manejadorCorreoElectronico;
	
	@EJB
	private ManejadorPersona manejadorPersona;
	
	@EJB
	private ManejadorRol manejadorRol;
	
	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;
	
	@EJB
	private IRolPersonaFacade rolPersonaFacade;

	@EJB
	private IPersonaFacade personaFacade;

	@EJB
	private IDominioFacade dominioFacade;
	
	@EJB
	private ManejadorPersonaServicioMateria manejadorPersonaServicioMateria;
	
	private  String ASUNTO_CORREO = "Notificación arbitro litigando";
	private  String CUERPO_CORREO = "El Dr. %1s, que actuaba como apoderado del caso No. %2s – %3s, se encuentra en estado "
			+ "Inactivo Litigando. Por favor verifique si se debe cambiar su estado.";
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorEstadoPersonaRol;
	}

	@Override
	public EstadoPersonaRolDTO transformarSinDependencias(EstadoPersonaRol obj) {
		EstadoPersonaRolDTO dto = new EstadoPersonaRolDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public EstadoPersonaRolDTO transformarConDependencias(EstadoPersonaRol obj) {
		EstadoPersonaRolDTO dto = new EstadoPersonaRolDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public EstadoPersonaRol transformarEntidadConDependencias(EstadoPersonaRol obj) {
		EstadoPersonaRol dto = new EstadoPersonaRol();
		dto = new EstadoPersonaRolDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public EstadoPersonaRol transformarEntidadSinDependencias(EstadoPersonaRol obj) {
		EstadoPersonaRol dto = new EstadoPersonaRol();
		dto = new EstadoPersonaRolDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	@Override
	public Boolean notificacionArbitroLitigante(Long idPersona, Long idRol, String idMotivo, Long idCaso) 
			throws SIMASCNegocioExcepcion{
		Boolean notifoLitigante = false;
		List<EstadoPersonaRol> estadosPersona = 
				manejadorEstadoPersonaRol.consultarEstadoPersonaFiltros(idPersona, idRol, idMotivo,true);
		List<RolPersonaCaso> personasEnvio = null;
		RolPersonaCaso rolPersonCasoAbogado = null;
		
		if(estadosPersona != null && !estadosPersona.isEmpty()){
			rolPersonCasoAbogado =	manejadorRolPersonaCaso.consultarPersonaPorRolCasoPrestador(idCaso, 
					UtilDominios.ROL_PERSONA_ABOGADO);
			personasEnvio = new ArrayList<RolPersonaCaso>();
			if(rolPersonCasoAbogado != null)
				personasEnvio.add(rolPersonCasoAbogado);

		}
		
		if(personasEnvio != null && !personasEnvio.isEmpty()){			
			List<String> textoCorreo = new ArrayList<String>();	
			Persona arbitroLitigado = manejadorPersona.buscar(idPersona);			
			String texto = String.format(CUERPO_CORREO,
					arbitroLitigado.getNombreCompleto(), rolPersonCasoAbogado.getCaso().getIdCaso(),
					rolPersonCasoAbogado.getCaso().getNombre() );
			textoCorreo.add(texto);							
			correoRolPersonaCasoFacade.envioDeCorreo(ASUNTO_CORREO, null, personasEnvio, null, textoCorreo,
					idCaso, null, null, false);
			notifoLitigante = true;
		}
		
		return notifoLitigante;
		
		
	}
	
	/**
	 * ADM-C-022
	 * Servicio que consulta el historial de estados del funcionario
	 * 
	 * @return
	 */
	/*public List<HistoricoEstadoPersonaRolDTO> consultarHistoricoEstados(Long idPersona){
		List<HistoricoEstadoPersonaRol> historico = manejadorHistoricoEstadoPersonaRol.consultarHistoricoPersona(idPersona);
		List<HistoricoEstadoPersonaRolDTO> historicoDTO = new ArrayList<>();
		if(historico!=null && !historico.isEmpty()){
			historicoDTO = HistoricoEstadoPersonaRolDTO.transformarListaSinDependencias(historico);
			for(HistoricoEstadoPersonaRolDTO dto : historicoDTO){
				dto = this.consultarNombresCamposPorRol(dto);
			}
		}
		return historicoDTO;
	}*/

	public List<HistoricoEstadoMotivoPersonaDTO> consultarHistoricoEstados(Long idPersona){
		List<HistoricoEstadoMotivoPersonaDTO> historicoDTO = manejadorHistoricoEstadoMotivoPersona.consultarHistoricoPersona(idPersona);
		for(HistoricoEstadoMotivoPersonaDTO dto : historicoDTO){
			dto.setRol(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ROL_PERSONA, dto.getRol()));
		}
		return historicoDTO;
	}

	/**
	 * consulta los nombres del Motivo, el Rol y el Estado en el Dominio
	 * @param dto
	 * @return
	 */
	private HistoricoEstadoMotivoPersonaDTO consultarNombresCamposPorRol(HistoricoEstadoMotivoPersonaDTO dto) {
		
		if (dto.getRol().equals(UtilDominios.ROL_PERSONA_CONCILIADOR)) {
			
			String motivoConciliador = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_MOTIVO_ESTADO_CONCILIADORES, dto.getMotivo());
			String estadoConciliadorActivo = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_MOTIVO_ESTADO_CONCILIADORES, UtilDominios.DOMINIO_ESTADO_OPERADOR_ACTIVO);
			if (motivoConciliador.equals(estadoConciliadorActivo)) {
				dto.setEstado(estadoConciliadorActivo);
			}else {
				dto.setEstado(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ESTADO_CONCILIADORES, UtilDominios.DOMINIO_ESTADO_OPERADOR_INACTIVO));
			}
			dto.setMotivo(motivoConciliador);
			
		}else {
			// Rol distinto a Conciliador
			if (dto.getMotivo().equals(UtilDominios.ESTADO_ARBITROS_HABILITADO)) {
				dto.setEstado(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ESTADO_ARBITROS, UtilDominios.DOMINIO_ESTADO_SORTEABLE));
			}else {
				dto.setEstado(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ESTADO_ARBITROS, UtilDominios.DOMINIO_ESTADO_NO_SORTEABLE));
			}
			dto.setMotivo(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_MOTIVO_ESTADO, dto.getMotivo()));
		}
		dto.setRol(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ROL_PERSONA, dto.getRol()));
			
		return dto;
	}
	/**
	 * ADM-C-022
	 * Servicio que cambia el estado del funcionario actualizando el historial de estados del mismo.
	 * @param estadoPersona
	 * @return
	 */
	public void cambiarEstadoFuncionario(EstadoPersonaRol estadoPersona){
		//consultar el estado actual para saber si se debe finalizar o asignar el rol de árbitro social
		String estadoAnterior = UtilConstantes.CADENA_VACIA;		
		Long idPersona = estadoPersona.getEstadoPersonaRolPK().getIdPersona();
		Long idRol = estadoPersona.getEstadoPersonaRolPK().getIdRol();
		Long idServicio = estadoPersona.getEstadoPersonaRolPK().getIdServicio();
		Map<String, Long> numMinimoCasos = new HashMap<>();				
		
		boolean esConciliador = (idRol.toString().equals(UtilConstantes.ID_ROL_CONCILIADOR));
		boolean esActivo = (estadoPersona.getEstadoRegistro().equals("ACT"));
		List<PersonaServicioMateria> serviciosMateriaPorPersona = manejadorPersonaServicioMateria.consultaPersonServicioMateriaByIdPersona(idPersona);		
		
		if(esConciliador && esActivo) {								
			numMinimoCasos = obtenerNumeroMinimoCasos(serviciosMateriaPorPersona);						
		}
		
		boolean esArbitro =  (idRol.equals(UtilConstantes.ID_ROL_ARBITRO));
		
		if(esArbitro){
			EstadoPersonaRolPK pId = new EstadoPersonaRolPK();
			pId.setIdRol(idRol);
			pId.setIdPersona(idPersona);
			pId.setIdServicio(idServicio);
			EstadoPersonaRol estadoPersonaRol = manejadorEstadoPersonaRol.buscar(pId);
			if(estadoPersonaRol != null){
				estadoAnterior = estadoPersonaRol.getIdMotivo();
			}		
		}
		
		manejadorEstadoPersonaRol.crearOActualizarEstadoPersonaEstadoRol(estadoPersona);
		
		HistoricoEstadoPersonaRol historico = generarHistoricoEstado(estadoPersona);		
		manejadorHistoricoEstadoPersonaRol.crear(historico);
		
		if((idRol.equals(UtilConstantes.ID_ROL_ARBITRO) || idRol.equals(UtilConstantes.ID_ROL_ARBITRO_SOCIAL) ||
				idRol.equals(UtilConstantes.ID_ROL_ARBITRO_RECUPERACION) || idRol.equals(UtilConstantes.ID_ROL_AMIGABLE_COMPONEDOR)
				|| idRol.equals(UtilConstantes.ID_ROL_PERITO)) && estadoAnterior != null && estadoAnterior.equals(UtilDominios.ESTADO_ARBITROS_INACTIVO_LITIGANDO)){
			//Si es árbitro internacional se debe cambiar el estado
			cambiarEstadoArbitroInternacional(idPersona, estadoPersona.getIdMotivo(), estadoPersona.getIdUsuarioModificacion());
		}
			
		if(esArbitro){			
			validarSuspencionArbitro(estadoPersona.getIdMotivo(), estadoAnterior, idPersona, idRol);
		}
		
		if(esConciliador &&esActivo) {				
			actualizarNumeroMinimoCasosPersona(serviciosMateriaPorPersona, numMinimoCasos, idPersona);
		}
	}
	
	public Map<String, Long> obtenerNumeroMinimoCasos(List<PersonaServicioMateria> serviciosMateriaPorPersona){
		
		HashMap<String, Long> numMinimoCasos = new HashMap<>();	
		for (PersonaServicioMateria servicioMateriaXPersona : serviciosMateriaPorPersona) {
			PersonaServicioMateria personaServicioMateria = new PersonaServicioMateria();
			Long idMateria = servicioMateriaXPersona.getIdMateria();
			Long idServicio = servicioMateriaXPersona.getIdServicio();
			String llave = "M"+idMateria+"S"+idServicio;
			personaServicioMateria.setIdMateria(idMateria);
			personaServicioMateria.setIdServicio(idServicio);
			Long minimoCasos = manejadorPersonaServicioMateria.obtenerCantidadCasosAsignados(personaServicioMateria);
			numMinimoCasos.put(llave, minimoCasos);
		}
		
		return numMinimoCasos;
	}
	
	public void cambiarEstadoArbitroInternacional(Long idPersona, String idMotivo, String idUsuarioModificacion) {
		
		List<RolPersona> rolAsignado = manejadorRolPersona.consultarRolesAsignados(idPersona, UtilDominios.ROL_PERSONA_ARBITRO_INTERNACIONAL);
		
		if(rolAsignado.size() > 0 && rolAsignado.get(0).getFechaFinVigencia() != null) {
			
			EstadoPersonaRolPK pIdInt = new EstadoPersonaRolPK();
			pIdInt.setIdRol(rolAsignado.get(0).getIdRol());
			pIdInt.setIdPersona(idPersona);						
			EstadoPersonaRol estadoPersonaRolInt = manejadorEstadoPersonaRol.buscar(pIdInt);
			Date date = new Date();
			estadoPersonaRolInt.setFechaAsignacion(new Timestamp(date.getTime()));	
			estadoPersonaRolInt.setIdMotivo(idMotivo);
			estadoPersonaRolInt.setIdUsuarioModificacion(idUsuarioModificacion);
			
			manejadorEstadoPersonaRol.crearOActualizarEstadoPersonaEstadoRol(estadoPersonaRolInt);
			
			HistoricoEstadoPersonaRol historicoInt = generarHistoricoEstado(estadoPersonaRolInt);
			
			manejadorHistoricoEstadoPersonaRol.crear(historicoInt);				
		}
	}
	
	public void validarSuspencionArbitro(String idMotivo, String estadoAnterior, Long idPersona, Long idRol) {
		
		String estadoNuevo = idMotivo;
		boolean noSuspension = (!estadoNuevo.equals(UtilDominios.ESTADO_ARBITROS_HABILITADO) || !estadoNuevo.equals(UtilDominios.MOTIVO_CONTESTACION_EXTEMPORANEA) || !estadoNuevo.equals(UtilDominios.MOTIVO_NO_CONTESTACION) || !estadoNuevo.equals(UtilDominios.MOTIVO_NO_ACEPTACION));
		if(estadoAnterior != null && estadoAnterior.equals(UtilDominios.ESTADO_ARBITROS_HABILITADO) && noSuspension){
			rolPersonaFacade.finalizarRolArbitroSocialCambioEstado(idPersona);
		}
		if(noSuspension && estadoNuevo.equals(UtilDominios.ESTADO_ARBITROS_HABILITADO)){
			personaFacade.asignarRolArbitroSocial(idPersona, estadoNuevo, idRol, false);
		}	
	}
	
	public void actualizarNumeroMinimoCasosPersona(List<PersonaServicioMateria> serviciosMateriaPorPersona, Map<String, Long> numMinimoCasos, Long idPersona) {
		
		for (PersonaServicioMateria servicioMateriaXPersona : serviciosMateriaPorPersona) {
			Long idMateria = servicioMateriaXPersona.getIdMateria();
			Long idServicio = servicioMateriaXPersona.getIdServicio();
			String llave = "M"+idMateria+"S"+idServicio;
			Long numMinCasos = numMinimoCasos.get(llave);
			PersonaServicioMateria personaServicioMateria = manejadorPersonaServicioMateria.consultarPersonaServicioMateriaPorServicioMateriaPersona(idServicio, idMateria, idPersona);			
			personaServicioMateria.setCantidadCasosAsignados(numMinCasos);
			manejadorPersonaServicioMateria.actualizar(personaServicioMateria);				
		}
	}	
	
	/**
	 * ADM-C-022
	 * Genera la entidad historico correspondiente al estado que se pasa como parámetro
	 * 
	 * @param estadoPersona
	 * @return
	 */
	public HistoricoEstadoPersonaRol generarHistoricoEstado(EstadoPersonaRol estadoPersona){
		
		HistoricoEstadoPersonaRol historicoEstado = new HistoricoEstadoPersonaRol();
		historicoEstado.setIdMotivo(estadoPersona.getIdMotivo());
		historicoEstado.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		historicoEstado.setFecha(estadoPersona.getFechaUltimaModificacion());
		historicoEstado.setIdPersona(estadoPersona.getEstadoPersonaRolPK().getIdPersona());
		historicoEstado.setIdRol(estadoPersona.getEstadoPersonaRolPK().getIdRol());
		historicoEstado.setFechaUltimaModificacion(estadoPersona.getFechaUltimaModificacion());
		historicoEstado.setIdServicio(estadoPersona.getEstadoPersonaRolPK().getIdServicio());
		return historicoEstado;
	}
	
	@Override
	public  List<EstadoPersonaRolDTO> consultarEstadoPersonaRol(Long idPersona, Long idRol,
    		String idMotivo, Boolean idMotivoIgual) {
		
		return (List<EstadoPersonaRolDTO>)transformarColeccionSinDependencias(manejadorEstadoPersonaRol.consultarEstadoPersonaFiltros(idPersona, idRol, idMotivo, idMotivoIgual),new ArrayList<EstadoPersonaRolDTO>());
	}

	@Override
	public List<DominioDTO> consultarMotivosEstadoPersonaRol(Long idRol) {
		
		Rol rol = manejadorRol.buscar(idRol);
		String nombreRol = rol.getNombre();
		List<ConsultaDominioDTO> parametrosBusqueda = new ArrayList<>();
		ConsultaDominioDTO parametrosDominio = new ConsultaDominioDTO();
		parametrosDominio.setCodigoClasificador(nombreRol);
		parametrosDominio.setDominioClasificador("ROL_PERSONA");
		parametrosDominio.setDominioAConsultar("MOTIVO_ESTADO");
		parametrosBusqueda.add(parametrosDominio);
		return dominioFacade.consultarDominiosPorClasificadores(parametrosBusqueda);
	}
	
	
}
