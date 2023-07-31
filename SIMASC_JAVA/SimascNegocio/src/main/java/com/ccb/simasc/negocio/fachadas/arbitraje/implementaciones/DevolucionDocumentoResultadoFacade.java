package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDevolucionDocumentoResultado;
import com.ccb.simasc.integracion.manejadores.ManejadorMotivoDevolucion;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroDeServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorProgramacionAlerta;
import com.ccb.simasc.integracion.manejadores.ManejadorResultadoAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlmacenamientoDocumentosFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDevolucionDocumentoResultadoFacade;
import com.ccb.simasc.negocio.fachadas.reparto.interfaces.IRepartoSvc;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.CasoDTO;
import com.ccb.simasc.transversal.dto.DevolucionDocumentoDTO;
import com.ccb.simasc.transversal.dto.DevolucionDocumentoResultadoDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.ModificarActasConstanciasDevueltasDTO;
import com.ccb.simasc.transversal.dto.MotivoDevolucionDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.DevolucionDocumentoResultado;
import com.ccb.simasc.transversal.entidades.MotivoDevolucion;
import com.ccb.simasc.transversal.entidades.MotivoDevolucionPK;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.ResultadoAudiencia;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.EstadosCasoException;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilParamServicio;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link DevolucionDocumentoResultado}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class DevolucionDocumentoResultadoFacade extends AccesoFacade<DevolucionDocumentoResultado, Long, DevolucionDocumentoResultadoDTO> implements IDevolucionDocumentoResultadoFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorDevolucionDocumentoResultado manejadorDevolucionDocumentoResultado;
	
	@EJB
	private ManejadorResultadoAudiencia manejadorResultadoAudiencia;
	
	@EJB
	private ICasoFacade casoFacade;
	
	@EJB
	private IRepartoSvc repartoFacade;
	
	@EJB
	private IAlmacenamientoDocumentosFacade almacenamientoDocumentosFacade;
	
	@EJB
	private ResultadoAudienciaFacade resultadoAudienciaFacade;

	@EJB
	private ManejadorMotivoDevolucion manejadorMotivoDevolucion;
	
	@EJB
	private ManejadorCaso manejadorCaso;
	
	@EJB
	private ManejadorParametroDeServicio manejadorParametroDeServicio;
	
	@EJB
	private ManejadorPersona manejadorPersona;
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	
	@EJB
	private CorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;
	
	@EJB 
	private ManejadorProgramacionAlerta manejadorProgramacionAlerta;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorDevolucionDocumentoResultado;
	}

	@Override
	public DevolucionDocumentoResultadoDTO transformarSinDependencias(DevolucionDocumentoResultado obj) {
		DevolucionDocumentoResultadoDTO dto = new DevolucionDocumentoResultadoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public DevolucionDocumentoResultadoDTO transformarConDependencias(DevolucionDocumentoResultado obj) {
		DevolucionDocumentoResultadoDTO dto = new DevolucionDocumentoResultadoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public DevolucionDocumentoResultado transformarEntidadConDependencias(DevolucionDocumentoResultado obj) {
		DevolucionDocumentoResultado dto = new DevolucionDocumentoResultado();
		dto = new DevolucionDocumentoResultadoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public DevolucionDocumentoResultado transformarEntidadSinDependencias(DevolucionDocumentoResultado obj) {
		DevolucionDocumentoResultado dto = new DevolucionDocumentoResultado();
		dto = new DevolucionDocumentoResultadoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/*
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IDevolucionDocumentoResultadoFacade#consultarActasConstanciasDevueltasCaso(
	 * com.ccb.simasc.transversal.dto.ModificarActasConstanciasDevueltasDTO)
	 */
	@Override
	public List<ModificarActasConstanciasDevueltasDTO> consultarActasConstanciasDevueltasCaso( Long idCaso ){
		return manejadorDevolucionDocumentoResultado.consultarActasConstanciasDevueltasCaso(idCaso);
	}
	
	/*
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IDevolucionDocumentoResultadoFacade#modificarActasConstanciasDevueltas(
	 * com.ccb.simasc.transversal.dto.ModificarActasConstanciasDevueltasDTO)
	 */
	@Override
	public void modificarActasConstanciasDevueltas( ModificarActasConstanciasDevueltasDTO modificarActasConstanciasDevueltasDTO, String idUsuarioModificacion ) throws EstadosCasoException{
		DocumentoDTO documentoAntiguoDTO = new DocumentoDTO();
		DevolucionDocumentoResultado devolucionDocumentoResultado = manejadorDevolucionDocumentoResultado.buscar(modificarActasConstanciasDevueltasDTO.getIdDevolucionDocumentoResultado());
		devolucionDocumentoResultado.setCorrige(modificarActasConstanciasDevueltasDTO.getCorrige());
		devolucionDocumentoResultado.setFechaCorreccion(modificarActasConstanciasDevueltasDTO.getFechaCorrecion());
		devolucionDocumentoResultado.setObservacionesRespuesta(modificarActasConstanciasDevueltasDTO.getObservacionesRespuesta());
		devolucionDocumentoResultado.setIdUsuarioModificacion(idUsuarioModificacion);
		devolucionDocumentoResultado.setFechaUltimaModificacion( new Date() );
		
		if( !devolucionDocumentoResultado.getIdDocumento().equals( modificarActasConstanciasDevueltasDTO.getIdDocumento() ) ){
			documentoAntiguoDTO.setIdDocumento( devolucionDocumentoResultado.getIdDocumento() );
			devolucionDocumentoResultado.setIdDocumento(modificarActasConstanciasDevueltasDTO.getIdDocumento());
		}
		manejadorDevolucionDocumentoResultado.actualizar(devolucionDocumentoResultado);
		
		ResultadoAudiencia resultadoAudiencia = manejadorResultadoAudiencia.buscar(modificarActasConstanciasDevueltasDTO.getIdResultadoAudiencia());
		resultadoAudiencia.setEstado(modificarActasConstanciasDevueltasDTO.getEstadoResultadoAudiencia());
		resultadoAudiencia.setIdDocumento(modificarActasConstanciasDevueltasDTO.getIdDocumento());
		resultadoAudiencia.setIdUsuarioModificacion(idUsuarioModificacion);
		resultadoAudiencia.setFechaUltimaModificacion( new Date() );
		manejadorResultadoAudiencia.actualizar(resultadoAudiencia);
		
		CasoDTO casoDTO = casoFacade.consultarCasoActivo(modificarActasConstanciasDevueltasDTO.getIdCaso());
		
		if( !UtilDominios.ESTADO_CASO_EN_CONCILIACION.equals(casoDTO.getEstadoCaso()) ){
			
			if( !UtilDominios.ESTADO_CASO_ASIGNADO_CONTROL_DE_LEGALIDAD.equals(casoDTO.getEstadoCaso()) &&
				(UtilDominios.ESTADO_RESULTADO_CORRIGE.equals(resultadoAudiencia.getEstado()) || UtilDominios.ESTADO_RESULTADO_INSISTE.equals(resultadoAudiencia.getEstado())) ) 
			{
				
				if(UtilDominios.ESTADO_CASO_DEVUELTO_EN_CONTROL_DE_CALIDAD.equals(casoDTO.getEstadoCaso() )|| 
						UtilConstantes.ID_SERVICIO_EQUIDAD.equals(casoDTO.getIdServicio())) {
					casoFacade.cambiarEstadoCaso(modificarActasConstanciasDevueltasDTO.getIdCaso(),
							UtilDominios.ESTADO_CASO_ASIGNADO_CONTROL_DE_CALIDAD, null,
							UtilDominios.TIPO_EVENTO_CASO_CONTROL_CALIDAD,
							UtilConstantes.CAMBIO_ESTADO_CASO_POR_MODIFICACION_ACTA_O_CONSTANCIA);
				}
				else 
				{
					casoFacade.cambiarEstadoCaso(modificarActasConstanciasDevueltasDTO.getIdCaso(),
							
							UtilDominios.ESTADO_CASO_ASIGNADO_CONTROL_DE_LEGALIDAD, null,
							UtilDominios.TIPO_EVENTO_CASO_CONTROL_LEGALIDAD,
							UtilConstantes.CAMBIO_ESTADO_CASO_POR_MODIFICACION_ACTA_O_CONSTANCIA);
				}
				
			}
			
			else if( UtilDominios.ESTADO_RESULTADO_REPROGRAMAR_AUDIENCIA.equals(resultadoAudiencia.getEstado()) ) 
			{
				if(UtilDominios.ESTADO_CASO_DEVUELTO_EN_CONTROL_DE_CALIDAD.equals(casoDTO.getEstadoCaso() )|| 
						UtilConstantes.ID_SERVICIO_EQUIDAD.equals(casoDTO.getIdServicio())) {
					casoFacade.cambiarEstadoCaso(modificarActasConstanciasDevueltasDTO.getIdCaso(),
							UtilDominios.ESTADO_CASO_EN_CONCILIACION, null,
							UtilDominios.TIPO_EVENTO_CASO_CONTROL_CALIDAD,
							UtilConstantes.CAMBIO_ESTADO_CASO_POR_MODIFICACION_ACTA_O_CONSTANCIA);
				}
				else 
				{		
									
					casoFacade.cambiarEstadoCaso(modificarActasConstanciasDevueltasDTO.getIdCaso(),
							UtilDominios.ESTADO_CASO_EN_CONCILIACION, null,
							UtilDominios.TIPO_EVENTO_CASO_CONTROL_LEGALIDAD,
							UtilConstantes.CAMBIO_ESTADO_CASO_POR_MODIFICACION_ACTA_O_CONSTANCIA);	
				}
			}
		}
		if( documentoAntiguoDTO.getIdDocumento() != null ) {
			almacenamientoDocumentosFacade.eliminarDocumento(documentoAntiguoDTO.getIdDocumento(), idUsuarioModificacion);
		}
	}
	
	@Override
	public void guardarDevolucionDocumento(DevolucionDocumentoDTO devolucionDocumentoDTO,
			String idUsuarioModificacion) throws EstadosCasoException {
		
		DevolucionDocumentoResultado devolucionDocumento = new DevolucionDocumentoResultado();
		devolucionDocumento.setFecha(new Date());
		devolucionDocumento.setCorrige(Boolean.FALSE);
		devolucionDocumento.setObservaciones(devolucionDocumentoDTO.getObservacionDevolucion());
		devolucionDocumento.setIdUsuarioModificacion(idUsuarioModificacion);
		// devolucionDocumento.setFechaUltimaModificacion(new Date());
		devolucionDocumento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		devolucionDocumento.setIdDocumento(devolucionDocumentoDTO.getIdDocumento());
		devolucionDocumento.setIdPersona(Long.valueOf(idUsuarioModificacion));
		manejadorDevolucionDocumentoResultado.crear(devolucionDocumento);

		// guardar los motivos de devolucion
		List<MotivoDevolucionDTO> motivos = devolucionDocumentoDTO.getMotivosDevolucion();
		if (motivos != null)
			guardarMotivosDevolucion(motivos, devolucionDocumento.getIdDevolucionDocumentoResultado());

		// actualiza estado del resultado a devuelto
			resultadoAudienciaFacade.actualizarEstadoResultado(UtilDominios.ESTADO_RESULTADO_DEVUELTO,
					devolucionDocumentoDTO.getIdResultadoAudiencia(), idUsuarioModificacion);
		if (devolucionDocumentoDTO.getIdServicio() != null && devolucionDocumentoDTO.getIdServicio().equals(UtilConstantes.ID_SERVICIO_EQUIDAD))
		{
		
			casoFacade.cambiarEstadoCaso(devolucionDocumentoDTO.getIdCaso(),
					UtilDominios.ESTADO_CASO_DEVUELTO_EN_CONTROL_DE_CALIDAD, new Date(),
					UtilDominios.TIPO_EVENTO_CASO_DEVUELTO_CONTROL_CALIDAD,
					UtilConstantes.CAMBIO_ESTADO_CASO_POR_DEVOLUCION_DOCUMENTO);
			List<String> estados = Arrays.asList(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
			List<RolPersonaCaso> conciliador = manejadorRolPersonaCaso.consultaConciliadoresCasoEstadoNombramiento(
					devolucionDocumentoDTO.getIdCaso(), estados, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
	
	
			if(conciliador.isEmpty()){
				
				throw new SIMASCNegocioExcepcion(
						String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR343.toString())));
				
			}else{
				
				// Correo devolucion documento
				repartoFacade.envioCorreDevolucionConciliadroEquidad(devolucionDocumentoDTO.getIdCaso(), conciliador.get(0).getRolPersonaCasoPK().getIdPersona() );
				 
			}
			

			
			
		}
		else {
			// actualiza estado del caso a Devuelto en control de legalidad
			
			casoFacade.cambiarEstadoCaso(devolucionDocumentoDTO.getIdCaso(),
					UtilDominios.ESTADO_CASO_DEVUELTO_EN_CONTROL_DE_LEGALIDAD, new Date(),
					UtilDominios.TIPO_EVENTO_CASO_DEVUELTO_CONTROL_LEGALIDAD,
					UtilConstantes.CAMBIO_ESTADO_CASO_POR_DEVOLUCION_DOCUMENTO);
		}
		if (devolucionDocumentoDTO.getIdServicio() != null && devolucionDocumentoDTO.getIdServicio() != UtilConstantes.ID_SERVICIO_EQUIDAD)
		{
			
			// Estados del conciliador principal
			List<String> estados = Arrays.asList(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
			
			// Consulta conciliador principal
			List<RolPersonaCaso> conciliador = manejadorRolPersonaCaso.consultaConciliadoresCasoEstadoNombramiento(
							devolucionDocumentoDTO.getIdCaso(), estados, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
			
			
			if(conciliador.isEmpty()){
				
				throw new SIMASCNegocioExcepcion(
						String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR343.toString())));
				
			}else{
				
				// notificar devolucion documento
				notificarDevolucionDocumento(devolucionDocumentoDTO.getIdCaso(),
						devolucionDocumentoDTO.getIdResultadoAudiencia(),conciliador);
				manejadorProgramacionAlerta.crearProgramacionAlerta(devolucionDocumentoDTO.getIdCaso(), conciliador.get(0).getRolPersonaCasoPK().getIdPersona(), UtilDominios.CODIGO_ALERTA_CORRECCION_ACTA_CONSTANCIA,null);
			}
			
		}
		
			
		
		
		
	}

	/**
	 * Metodo que permite guardar los motivos de devolucion de documento.
	 * 
	 * @param motivos
	 * @param idDevolucion
	 */
	private void guardarMotivosDevolucion(List<MotivoDevolucionDTO> motivos, Long idDevolucion) {
		for (MotivoDevolucionDTO motivoDTO : motivos) {
			MotivoDevolucion motivoDevolucion = new MotivoDevolucion();
			MotivoDevolucionPK motivoDevolucionPK = new MotivoDevolucionPK();
			motivoDevolucionPK.setIdDevolucion(idDevolucion);
			motivoDevolucionPK.setMotivo(motivoDTO.getMotivoDevolucionPK().getMotivo());
			motivoDevolucion.setMotivoDevolucionPK(motivoDevolucionPK);
			manejadorMotivoDevolucion.crear(motivoDevolucion);
		}
	}

	/**
	 * Metodo que permite notificar la devolucion de documento al conciliador.
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 * @param idPersona:
	 *            Identificador de la persona a notificar.
	 * @param idResultadoAudiencia:
	 *            Identificador del resultado de audiencia.
	 */
	private void notificarDevolucionDocumento(Long idCaso, Long idResultadoAudiencia, List<RolPersonaCaso> conciliador) {
		Caso caso = manejadorCaso.buscar(idCaso);
		
		Long idPersona = conciliador.get(0).getRolPersonaCasoPK().getIdPersona();
		String asunto = UtilConstantes.ASUNTO_DEVOLUCION_CONTROL_LEGALIDAD;
		String tipoDocumento = obtenerTipoDocumento(idResultadoAudiencia);
		StringBuilder textoCorreo = new StringBuilder();
		List<String> args = new ArrayList<>();
		args.add(tipoDocumento);
		args.add(String.valueOf(caso.getIdCaso()));
		args.add(caso.getNombre());
		Long tiempoCorreccionActaConstancia = Long.valueOf((manejadorParametroDeServicio.consultarParametrosDeServicio(
				Arrays.asList(UtilParamServicio.TIEMPO_MAXIMO_PARA_CORRECCION_DOCUMENTO), caso.getIdServicio(),
				UtilParamServicio.TIPO_PARAMETRO_CORRECCION_DOCUMENTO)).get(0).getValor());
		args.add(String.valueOf(tiempoCorreccionActaConstancia));
		textoCorreo.append(String.format(
				MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO309.toString()), args.toArray()));
		List<String> lstCuerpo = new ArrayList<>();
		lstCuerpo.add(textoCorreo.toString());
		List<Persona> lstPersonas = new ArrayList<Persona>();
		lstPersonas.add(manejadorPersona.buscar(idPersona));
		// Envia correo
		correoRolPersonaCasoFacade.envioDeCorreo(asunto, lstPersonas, null, null, lstCuerpo, caso.getIdCaso(), null,
				null, Boolean.FALSE);
	}

	private String obtenerTipoDocumento(Long idResultadoAudiencia) {
		ResultadoAudiencia resultadoAudiencia = manejadorResultadoAudiencia.buscar(idResultadoAudiencia);
		String tipoResultado = resultadoAudiencia.getTipoResultadoAudiencia();
		String tipoDocumento = UtilConstantes.TIPO_DOCUMENTO_DE_LA_CONSTANCIA;
		if (tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_PARCIAL)
				|| tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_TOTAL)) {
			tipoDocumento = UtilConstantes.TIPO_DOCUMENTO_DEL_ACTA;
		}
		return tipoDocumento;
	}

	
	// protected region metodos adicionales end
	
}
