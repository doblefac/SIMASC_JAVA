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
import com.ccb.simasc.integracion.manejadores.ManejadorNotificacion;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlertaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDiaFestivoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.INotificacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametroDeServicioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPlantillaCartaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IProgramacionAlertaFacade;
import com.ccb.simasc.transversal.dto.NotificacionDTO;
import com.ccb.simasc.transversal.dto.ParametroDeServicioDTO;
import com.ccb.simasc.transversal.dto.PlantillaCartaDTO;
import com.ccb.simasc.transversal.entidades.Alerta;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Notificacion;
import com.ccb.simasc.transversal.entidades.ProgramacionAlerta;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Notificacion}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class NotificacionFacade extends AccesoFacade<Notificacion, Long, NotificacionDTO> implements INotificacionFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorNotificacion manejadorNotificacion;
	
	@EJB
	private ICasoFacade casoFacade;
	
	@EJB
	private IProgramacionAlertaFacade programacionAlertaFacade; 
	
	@EJB
	private IDiaFestivoFacade diaFestivoFacade;
	
	@EJB
	private IAlertaFacade alertaFacade;
	
	@EJB
	private IParametroDeServicioFacade parametroDeServicioFacade;
	
	@EJB
	private IPlantillaCartaFacade plantillaCartaFacade;
	
	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;
	
	@Inject
	private ApplicationRequestContext appContext;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorNotificacion;
	}

	@Override
	public NotificacionDTO transformarSinDependencias(Notificacion obj) {
		NotificacionDTO dto = new NotificacionDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public NotificacionDTO transformarConDependencias(Notificacion obj) {
		NotificacionDTO dto = new NotificacionDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Notificacion transformarEntidadConDependencias(Notificacion obj) {
		Notificacion dto = new Notificacion();
		dto = new NotificacionDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Notificacion transformarEntidadSinDependencias(Notificacion obj) {
		Notificacion dto = new Notificacion();
		dto = new NotificacionDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	
	
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	@Override
	public void generarLogAlerta(String texto, String estado, Long idAlerta, Long idPersona, Date fechaNotificacion){
		String idUsuario = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		
		Notificacion notificacion = new Notificacion();
		notificacion.setIdAlerta(idAlerta);
		notificacion.setIdPersona(idPersona);
		notificacion.setEstado(estado);
	
		notificacion.setTextoAlerta(texto);
		if(fechaNotificacion != null){
			notificacion.setFechaNotificacion(fechaNotificacion);
		}else{
			notificacion.setFechaNotificacion(new Date());	
		}
		notificacion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		notificacion.setFechaUltimaModificacion(new Date());
		notificacion.setIdUsuarioModificacion(idUsuario);		
		manejadorNotificacion.crearNotificacionAlerta(notificacion);
	}
	
	@Override
	public void notificaVencimientoPronunciamientoarbitro(Long idCaso, Long idPersona) {
		
		Caso caso = casoFacade.buscar(idCaso);

		List<String> nombreParametros = new ArrayList<String>();
		nombreParametros.add(UtilConstantes.APLICA_NOTIFICACION_ARBITRO);

		List<ParametroDeServicioDTO> parametroDeServicioList = parametroDeServicioFacade
				.consultarParametroDeServicio(nombreParametros, caso.getIdServicio(),
						UtilConstantes.TIPO_PARAMETRO_APLICA_NOTIFICACION_ARBITRO);

		if (parametroDeServicioList != null && !parametroDeServicioList.isEmpty()
				&& parametroDeServicioList.get(0).getValor().equals(UtilConstantes.SI)) {
			
			nombreParametros = new ArrayList<String>();
			nombreParametros.add(UtilConstantes.TIEMPO_MAX_PRONUNCIAMIENTO_CASO);

			parametroDeServicioList = new ArrayList<ParametroDeServicioDTO>();
			parametroDeServicioList = parametroDeServicioFacade.consultarParametroDeServicio(
					nombreParametros, caso.getIdServicio(),
					UtilConstantes.TIPO_PARAMETRO_TIEMPO_PRONUNCIAMIENTO_CASO);
			
			List<Alerta> alertaList =alertaFacade.consultaAlertaPorCodigo(UtilConstantes.CODIGO_ALERTA_NOTIFICACION_ARBITRO);
			
			if(alertaList != null && !alertaList.isEmpty()) {
				int diasMaximo = UtilConstantes.DIAS_MAXIMOS_PARA_PRONUNCIAMIENTO;

				if (parametroDeServicioList != null && !parametroDeServicioList.isEmpty()) {
					diasMaximo = Integer.parseInt(parametroDeServicioList.get(0).getValor());
				}
				
				diasMaximo = diasMaximo - alertaList.get(0).getValor().intValue();

				Date fechaComunicacionLimite = diaFestivoFacade.sumarDiasHabilesAFecha(new Date(),
						Long.valueOf(diasMaximo));

				ProgramacionAlerta programacionAlerta = new ProgramacionAlerta();
				programacionAlerta.setIdCaso(idCaso);
				programacionAlerta.setIdPersona(idPersona);
				programacionAlerta.setEstado(UtilDominios.PROGRAMACION_ALERTA_PENDIENTE);
				programacionAlerta.setFechaEjecucion(fechaComunicacionLimite);
				programacionAlerta.setIdAlerta(alertaList.get(0).getIdAlerta());

				programacionAlertaFacade.crear(programacionAlerta);
			}
			
		}
		
	}
	
	@Override
	public void notificaUsuarioPlantilla(String nombrePlantilla, RolPersonaCaso rolPersonaCaso, String asunto) {
					
		PlantillaCartaDTO plantillaCarta =  plantillaCartaFacade.consultarPlantillaServicioNombre(  rolPersonaCaso.getCaso().getIdServicio(),nombrePlantilla,  rolPersonaCaso.getCaso().getIdCaso());
		
		List<String> cuerpoCorreo = new ArrayList<>();
		cuerpoCorreo.add(plantillaCarta.getPlantillaHtml());
		List<RolPersonaCaso> rolesPersonasCaso = new ArrayList<>();
		rolesPersonasCaso.add(rolPersonaCaso);
		// Envia correo
		correoRolPersonaCasoFacade.envioDeCorreo(asunto, null, rolesPersonasCaso, null, cuerpoCorreo, rolPersonaCaso.getCaso().getIdCaso(),
				null, null, false);				
				
	}
	
	
	// protected region metodos adicionales end
	
}
