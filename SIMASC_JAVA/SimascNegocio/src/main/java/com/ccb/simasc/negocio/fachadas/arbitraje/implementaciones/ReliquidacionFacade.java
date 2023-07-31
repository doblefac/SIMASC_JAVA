package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IIntegracionSWFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.enumeraciones.TipoOrdenamiento;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorPagoCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorReliquidacion;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionOrdenamiento;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAudienciaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICartaPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDetalleReliquidacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPlantillaCartaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IReliquidacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.transversal.dto.DetalleLiquidacionDTO;
import com.ccb.simasc.transversal.dto.DtoGenericoDTO;
import com.ccb.simasc.transversal.dto.PlantillaCartaDTO;
import com.ccb.simasc.transversal.dto.ReliquidacionDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioDatosClienteDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioGenerarLiquidacionDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioGenerarPagoPup;
import com.ccb.simasc.transversal.dto.formularios.ParametrosGenerarCartaDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.PagoCaso;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Reliquidacion;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Reliquidacion}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ReliquidacionFacade extends AccesoFacade<Reliquidacion, Long, ReliquidacionDTO> implements IReliquidacionFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorReliquidacion manejadorReliquidacion;
	
	@EJB
	private ManejadorCaso manejadorCaso;
	
	@EJB
	private ManejadorPagoCaso manejadorPagoCaso;
	
	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	
	@EJB
	private IEventoFacade eventoFacade;
	
	@EJB
	private IAudienciaFacade audienciaFacade;
	
	@EJB
	private IDetalleReliquidacionFacade detalleReliquidacionFacade;
	
	@EJB
	private ICartaPersonaFacade cartaPersonaFacade;
	
	@EJB
	private IPersonaFacade personaFacade;
	
	@EJB
	private IPlantillaCartaFacade plantillaCartaFacade;
	
	@EJB
	private IRolPersonaCasoFacade rolPersonaCasoFacade;
	
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;
    
    @EJB
    private IIntegracionSWFacade integracionSWFacade;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorReliquidacion;
	}

	@Override
	public ReliquidacionDTO transformarSinDependencias(Reliquidacion obj) {
		return new ReliquidacionDTO().transformarSinDependencias(obj);
	}

	@Override
	public ReliquidacionDTO transformarConDependencias(Reliquidacion obj) {
		return new ReliquidacionDTO().transformarConDependencias(obj);
	}

	@Override
	public Reliquidacion transformarEntidadConDependencias(Reliquidacion obj) {
		return new ReliquidacionDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Reliquidacion transformarEntidadSinDependencias(Reliquidacion obj) {
		return new ReliquidacionDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	@Override
	public DtoGenericoDTO calcularReliquidacionPorTipo(Long idCaso , String motivoReliquidacion){
		Long reliquidacion = 0L;	
		Long porcentajeRetorno = 0L;
		String tipoReliquidacion = null;
		DtoGenericoDTO reliquidacionRetorno = new DtoGenericoDTO();
		if(UtilDominios.RESULTADO_CASO_CONCILIACION_FALTA_COMPETENCIA.equals(motivoReliquidacion)){
			tipoReliquidacion = UtilDominios.CODIGO_PARAMETRO_GRAL_DEVOLUCION_NO_COMPETENCIA;
			
		}else if(UtilDominios.RESULTADO_CASO_CONCILIACION_CANCELACION.equals(motivoReliquidacion) || 
				 UtilDominios.RESULTADO_CASO_CONCILIACION_ARREGLO_DIRECTO.equals(motivoReliquidacion)){
			if(!audienciaFacade.existeRealiazacionAudiencia(idCaso)){				
				if(audienciaFacade.existeCitacionAudiencia(idCaso)){
					tipoReliquidacion = UtilDominios.CODIGO_PARAMETRO_GRAL_DEVOLUCION_HUBO_CITACION;
				}else{
					tipoReliquidacion = UtilDominios.CODIGO_PARAMETRO_GRAL_DEVOLUCION_NO_CITACION;							
				}
			}
		}

		if(tipoReliquidacion != null){
			PagoCaso pagoActual = null;
			
			//Consulta del pago del caso
			List<InformacionFiltro> filtrosPago = new ArrayList<InformacionFiltro>();
			InformacionFiltro filtro1 = new InformacionFiltro(TipoFiltro.EXACTO, PagoCaso.ENTIDAD_PAGO_CASO_ESTADO_REGISTRO,UtilDominios.ESTADO_REGISTRO_ACTIVO);
			InformacionFiltro filtro2 = new InformacionFiltro(TipoFiltro.EXACTO, PagoCaso.ENTIDAD_PAGO_CASO_ID_CASO,idCaso);

			filtrosPago.add(filtro1);
			filtrosPago.add(filtro2);	
			List<InformacionOrdenamiento> Ordenamiento = new ArrayList<InformacionOrdenamiento>();
			Ordenamiento.add(new InformacionOrdenamiento(TipoOrdenamiento.DESCENDENTE, PagoCaso.ENTIDAD_PAGO_CASO_FECHA_PAGO));
			List<PagoCaso> pagosCaso = manejadorPagoCaso.consultar(filtrosPago, Ordenamiento ,null);
			
			//Consulta paramtro de reliquidacion
			List<InformacionFiltro> filtrosParametros = new ArrayList<InformacionFiltro>();
			InformacionFiltro filtro3 = new InformacionFiltro(TipoFiltro.EXACTO, ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			InformacionFiltro filtro4 = new InformacionFiltro(TipoFiltro.EXACTO, ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_TIPO, "PORCENTAJE DEVOLUCION");
			InformacionFiltro filtro5 = new InformacionFiltro(TipoFiltro.EXACTO, ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_PK, tipoReliquidacion);

			filtrosParametros.add(filtro3);
			filtrosParametros.add(filtro4);
			filtrosParametros.add(filtro5);
			List<ParametrosGenerales> parametrosGenerales = manejadorParametrosGenerales.consultar(filtrosParametros, null ,null);
			
			
			if(!pagosCaso.isEmpty() && !parametrosGenerales.isEmpty() && pagosCaso.get(0).getValor() != null 
					&& parametrosGenerales.get(0).getValorNumerico() != null){
				pagoActual = pagosCaso.get(0);
				porcentajeRetorno = parametrosGenerales.get(0).getValorNumerico();
				reliquidacion = pagoActual.getValor() * porcentajeRetorno /100;
				reliquidacionRetorno.setValorGenerico1(reliquidacion);
				reliquidacionRetorno.setValorGenerico2(porcentajeRetorno);
			}				
		}		
		
		return reliquidacionRetorno;
	}
	

	
	@Override
	public void  generarReliquidacionTipoDevolucion(Long idCaso, String motivo){
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if(appContext!= null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() != null  ){
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		}	
		DtoGenericoDTO calculoReliquidacion = this.calcularReliquidacionPorTipo(idCaso, motivo);
		if(calculoReliquidacion.getValorGenerico1() != null && calculoReliquidacion.getValorGenerico2() != null){
				Reliquidacion reliquidacion = new Reliquidacion();
				reliquidacion.setIdCaso(idCaso);
				reliquidacion.setTipo(UtilDominios.TIPO_RELIQUIDACION_DEVOLUCION);
				reliquidacion.setMotivo(motivo);	
				reliquidacion.setFecha(new Date());						
				reliquidacion.setPorcentajeDevolucion(calculoReliquidacion.getValorGenerico2());
				reliquidacion.setValor(new BigDecimal(calculoReliquidacion.getValorGenerico1()));		
				reliquidacion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				reliquidacion.setIdUsuarioModificacion(usuarioModificacion);
				reliquidacion.setFechaUltimaModificacion(new Date());	
				manejadorReliquidacion.crear(reliquidacion);
				//Genera evento reliquidacion
				List<String> args = new ArrayList<>();
				args.add(calculoReliquidacion.getValorGenerico2().toString());
				String  observaciones =(String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO273.toString()), args.toArray()));		
	
				eventoFacade.registrarEvento(idCaso, UtilDominios.TIPO_EVENTO_DEVOLUCION_DINERO, observaciones, usuarioModificacion);
		}

		
	}
	
	@Override
	public void registroProcesoReliquidacion(FormularioGenerarLiquidacionDTO datos, Long idCaso, String motivo, String nuevaCuantia) {
		FormularioGenerarPagoPup pup = new FormularioGenerarPagoPup();
		datos.setIsSolicitud(false);
		datos.setIdSolicitudAplicativo(idCaso);
		pup.setDatosLiquidacion(datos);
		List<PagoCaso> pago = manejadorPagoCaso.obtenerUltimoPagoCaso(idCaso);
		PersonaBasicaDTO persona = new PersonaBasicaDTO();
		persona.setNumeroDocumento(pago.get(0).getNumeroDeDocumento());
		persona.setTipoDocumento(pago.get(0).getTipoDeDocumento());
		FormularioDatosClienteDTO datosCliente = personaFacade.validarIdentificacionPagadorSolicitud(persona);
		pup.setNumeroCliente(datosCliente.getNumCliente());
		String numeroOrden = integracionSWFacade.generarOrdenDePagoPup(pup);
		Reliquidacion reliquidacion = new Reliquidacion();
		reliquidacion.setOrdenDePago(numeroOrden);
		reliquidacion.setTipo(UtilDominios.TIPO_RELIQUIDACION_RELIQUIDACION);
		reliquidacion.setMotivo(motivo);
		reliquidacion.setFecha(new Date());
		reliquidacion.setValor(new BigDecimal(datos.getValor()));
		reliquidacion.setPagada(false);
		reliquidacion.setIdCaso(idCaso);
		reliquidacion.setNuevaCuantia(nuevaCuantia != null ? Long.valueOf(nuevaCuantia) : null);
		reliquidacion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		crear(reliquidacion);
		
		for (DetalleLiquidacionDTO detalleLiquidacion : datos.getDetalle()) {
			detalleReliquidacionFacade.crearDetalleReliquidacion(reliquidacion.getIdReliquidacion(),
					Long.valueOf(detalleLiquidacion.getItem()),
					Long.valueOf(detalleLiquidacion.getValorTotal()),
					detalleLiquidacion.getServicioId());
		}
		
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if(appContext!= null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() != null  ){
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		}
		
		if(nuevaCuantia != null) {
			eventoFacade.registrarEvento(idCaso, UtilDominios.TIPO_EVENTO_RELIQUIDACION,
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO381.toString(), nuevaCuantia),
					usuarioModificacion);
		} else {
			eventoFacade.registrarEvento(idCaso, UtilDominios.TIPO_EVENTO_RELIQUIDACION,
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO382.toString(), datos.getMensaje(),
							reliquidacion.getValor().toString()),
					usuarioModificacion);
		}
		notificacionReliquidacion(idCaso);
	}
	
	/**
	 * Método para realizar la generacion de la carta de reliquidacion
	 * @param idCaso
	 */
	private void notificacionReliquidacion(Long idCaso) {
		PlantillaCartaDTO plantilla = plantillaCartaFacade.consultarPlantillaServicioCaso(idCaso,
				UtilDominios.NOMBRE_PLANTILLA_CARTA_RELIQUIDACION);
		List<Long> destinatarios = new ArrayList<Long>();
		List<RolPersonaCaso> personas = manejadorRolPersonaCaso.consultarPersonasAsignadasCasoPorRol(idCaso,
				Arrays.asList(UtilDominios.ROL_PERSONA_CONVOCANTE), false);
		for (RolPersonaCaso rolPersonaCaso : personas) {
			destinatarios.add(rolPersonaCaso.getRolPersonaCasoPK().getIdPersona());
		}
		ParametrosGenerarCartaDTO parametrosCarta = new ParametrosGenerarCartaDTO();
		parametrosCarta.setIdCaso(idCaso);
		parametrosCarta.setIdPlantilla(String.valueOf(plantilla.getIdPlantillaCarta()));
		parametrosCarta.setIndicadorNotificacion(UtilConstantes.NO);
		parametrosCarta.setIndicadorEnvio(UtilConstantes.NO);
		parametrosCarta.setListaIdNotificados(destinatarios);
		cartaPersonaFacade.generarCarta(parametrosCarta,null);
	}


	// protected region metodos adicionales end
	
}
