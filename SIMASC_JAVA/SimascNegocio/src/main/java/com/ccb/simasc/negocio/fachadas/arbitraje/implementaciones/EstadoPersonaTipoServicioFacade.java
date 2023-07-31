package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronico;
import com.ccb.simasc.integracion.manejadores.ManejadorEstadoPersonaTipoServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorHistoricoEstadoPersonaTipoServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEstadoPersonaTipoServicioFacade;
import com.ccb.simasc.transversal.dto.EstadoPersonaTipoServicioDTO;
import com.ccb.simasc.transversal.dto.HistoricoEstadoPersonaTipoServicioDTO;
import com.ccb.simasc.transversal.entidades.EstadoPersonaTipoServicio;
import com.ccb.simasc.transversal.entidades.EstadoPersonaTipoServicioPK;
import com.ccb.simasc.transversal.entidades.HistoricoEstadoPersonaTipoServicio;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link EstadoPersonaTipoServicio}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class EstadoPersonaTipoServicioFacade extends AccesoFacade<EstadoPersonaTipoServicio, EstadoPersonaTipoServicioPK, EstadoPersonaTipoServicioDTO> implements IEstadoPersonaTipoServicioFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorHistoricoEstadoPersonaTipoServicio manejadorHistoricoEstadoPersonaTipoServicio;
	
	@EJB
	private ManejadorEstadoPersonaTipoServicio manejadorEstadoPersonaTipoServicio;
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	
	@EJB
	private ManejadorCorreoElectronico  manejadorCorreoElectronico;
	
	@EJB
	private ManejadorPersona manejadorPersona;
	
	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;
	
	@EJB
	private IDominioFacade dominioFacade;
	
	private  String ASUNTO_CORREO = "Notificación arbitro litigando";
	private  String CUERPO_CORREO = "El Dr. %1s, que actuaba como apoderado del caso No. %2s – %3s, se encuentra en estado "
			+ "Inactivo Litigando. Por favor verifique si se debe cambiar su estado.";
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorEstadoPersonaTipoServicio;
	}

	@Override
	public EstadoPersonaTipoServicioDTO transformarSinDependencias(EstadoPersonaTipoServicio obj) {
		EstadoPersonaTipoServicioDTO dto = new EstadoPersonaTipoServicioDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public EstadoPersonaTipoServicioDTO transformarConDependencias(EstadoPersonaTipoServicio obj) {
		EstadoPersonaTipoServicioDTO dto = new EstadoPersonaTipoServicioDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public EstadoPersonaTipoServicio transformarEntidadConDependencias(EstadoPersonaTipoServicio obj) {
		EstadoPersonaTipoServicio dto = new EstadoPersonaTipoServicio();
		dto = new EstadoPersonaTipoServicioDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public EstadoPersonaTipoServicio transformarEntidadSinDependencias(EstadoPersonaTipoServicio obj) {
		EstadoPersonaTipoServicio dto = new EstadoPersonaTipoServicio();
		dto = new EstadoPersonaTipoServicioDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	@Override
	public Boolean notificacionArbitroLitigante(Long idPersona, String tipoServicio, 
			String estado, Long idCaso) throws SIMASCNegocioExcepcion{
		Boolean notifoLitigante = false;
		List<EstadoPersonaTipoServicio> estadosPersona = 
				manejadorEstadoPersonaTipoServicio.consultarEstadoPersonaFiltros(idPersona, tipoServicio, estado,true);
		List<RolPersonaCaso> personasEnvio = null;
		RolPersonaCaso rolPersonCasoAbogado = null;
		String nombreCaso = UtilConstantes.CADENA_VACIA;
		
		if(estadosPersona != null && !estadosPersona.isEmpty()){
			rolPersonCasoAbogado =	manejadorRolPersonaCaso.consultarPersonaPorRolCasoPrestador(idCaso, 
					UtilDominios.ROL_PERSONA_ABOGADO);
			personasEnvio = new ArrayList<RolPersonaCaso>();
			if(rolPersonCasoAbogado != null){
				personasEnvio.add(rolPersonCasoAbogado);
				nombreCaso = rolPersonCasoAbogado.getCaso().getNombre();
			}

		}
		
		if(personasEnvio != null && !personasEnvio.isEmpty()){			
			List<String> textoCorreo = new ArrayList<String>();	
			Persona arbitroLitigado = manejadorPersona.buscar(idPersona);			
			String texto = String.format(CUERPO_CORREO, arbitroLitigado.getNombreCompleto(), idCaso, nombreCaso);
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
	public List<HistoricoEstadoPersonaTipoServicioDTO> consultarHistoricoEstados(Long idPersona){
		List<HistoricoEstadoPersonaTipoServicio> historico = manejadorHistoricoEstadoPersonaTipoServicio.consultarHistoricoPersona(idPersona);
		List<HistoricoEstadoPersonaTipoServicioDTO> historicoDTO = new ArrayList<>();
		if(historico!=null && !historico.isEmpty()){
			historicoDTO = HistoricoEstadoPersonaTipoServicioDTO.transformarListaSinDependencias(historico);
			for(HistoricoEstadoPersonaTipoServicioDTO dto : historicoDTO){
				dto.setTipoServicio(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_SERVICIO, dto.getTipoServicio()));
				dto.setEstado(consultarNombreEstadoHistorico(dto.getEstado(), dto.getTipoServicio()));
			}
		}
		
		return historicoDTO;
	}
	
	
	/**
	 * ADM-C-022
	 * Consulta el nombre de código de dominio que representa el estado en el histórico
	 * dependiendo del tipo de servicio.
	 * @param codigoDominio
	 * @param tipoServicio Nombre del dominio correspondiente al tipo de servicio
	 * @return
	 */
	private String consultarNombreEstadoHistorico(String codigoDominio, String tipoServicio){
		String nombre = null;
		String nombrePlanJusticia = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_SERVICIO, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);

		if(nombrePlanJusticia!=null){
			if(nombrePlanJusticia.equals(tipoServicio)){
				nombre = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ESTADO_ARBITROS, codigoDominio);
			}else{
				//Plan dialogos
				nombre = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ESTADO_CONCILIADORES, codigoDominio);
			}
		}		
		
		return nombre;
	}
	
	/**
	 * ADM-C-022
	 * Servicio que cambia el estado del funcionario actualizando el historial de estados del mismo.
	 * @param estadoPersona
	 * @return
	 */
	public void cambiarEstadoFuncionario(EstadoPersonaTipoServicio estadoPersona){
		manejadorEstadoPersonaTipoServicio.crearOActualizarEstadoPersonaEstadoTipoServicio(estadoPersona);
		
		HistoricoEstadoPersonaTipoServicio historico = generarHistoricoEstado(estadoPersona);
		
		manejadorHistoricoEstadoPersonaTipoServicio.crear(historico);
	}
	
	/**
	 * ADM-C-022
	 * Genera la entidad historico correspondiente al estado que se pasa como parámetro
	 * 
	 * @param estadoPersona
	 * @return
	 */
	private HistoricoEstadoPersonaTipoServicio generarHistoricoEstado(EstadoPersonaTipoServicio estadoPersona){
		
		HistoricoEstadoPersonaTipoServicio historicoEstado = new HistoricoEstadoPersonaTipoServicio();
		historicoEstado.setEstado(estadoPersona.getEstado());
		historicoEstado.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		historicoEstado.setFecha(estadoPersona.getFechaAsignacion());
		historicoEstado.setIdPersona(estadoPersona.getEstadoPersonaTipoServicioPK().getIdPersona());
		historicoEstado.setTipoServicio(estadoPersona.getEstadoPersonaTipoServicioPK().getTipoServicio());
		
		return historicoEstado;
	}
	
	@Override
	public  List<EstadoPersonaTipoServicioDTO> consultarEstadoPersonaTipoServicio(Long idPersona, String tipoServicio,
    		String estado, Boolean estadoIgual) {
		
		return (List<EstadoPersonaTipoServicioDTO>)transformarColeccionSinDependencias(manejadorEstadoPersonaTipoServicio.consultarEstadoPersonaFiltros(idPersona, tipoServicio, estado, estadoIgual),new ArrayList<EstadoPersonaTipoServicioDTO>());
	}

	// protected region metodos adicionales end
	
}
