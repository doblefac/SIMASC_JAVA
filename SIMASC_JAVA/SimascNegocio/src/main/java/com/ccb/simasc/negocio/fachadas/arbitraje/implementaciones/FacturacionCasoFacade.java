package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorDiaFestivo;
import com.ccb.simasc.integracion.manejadores.ManejadorFacturacionCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorPagoCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRelacionadoConvenio;
import com.ccb.simasc.integracion.manejadores.ManejadorReliquidacion;
import com.ccb.simasc.integracion.manejadores.ManejadorTarifaContrato;
import com.ccb.simasc.integracion.manejadores.ManejadorUsuario;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAudienciaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFacturacionCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISolicitudServicioFacade;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.FacturacionCasoDTO;
import com.ccb.simasc.transversal.dto.LiquidacionCasosConvenioDTO;
import com.ccb.simasc.transversal.dto.FiltrosTramiteOrdinarioDTO;
import com.ccb.simasc.transversal.dto.TramiteOrdinarioDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.dto.formularios.ProcesoReliquidacionDTO;
import com.ccb.simasc.transversal.dto.reportes.FiltroReportesDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.ContratoConvenio;
import com.ccb.simasc.transversal.entidades.FacturacionCaso;
import com.ccb.simasc.transversal.entidades.PagoCaso;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.TarifaContrato;
import com.ccb.simasc.transversal.entidades.Usuario;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link FacturacionCaso}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class FacturacionCasoFacade extends AccesoFacade<FacturacionCaso, Long, FacturacionCasoDTO>
		implements IFacturacionCasoFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	@EJB
	private ManejadorFacturacionCaso manejadorFacturacionCaso;

	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorUsuario manejadorUsuario;
	
	@EJB
	private ManejadorPagoCaso manejadorPagoCaso;
	
	@EJB
	private ManejadorReliquidacion manejadorReliquidacion;
	
	@EJB
	private ManejadorTarifaContrato manejadorTarifaContrato;
	
	@EJB
	private IDominioFacade dominioFacade;
	
	@EJB
	private ManejadorDiaFestivo manejadorDiaFestivo;
	
	@EJB
	private ISolicitudServicioFacade solicitudServicioFacade;
	
	@EJB
	private IAudienciaFacade audienciaFacade;
	
	@EJB
	private ManejadorRelacionadoConvenio manejadorRelacionadoConvenio;
	
    @Inject
    private ApplicationRequestContext appContext;

	// protected region atributos end

	@SuppressWarnings("rawtypes")
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorFacturacionCaso;
	}

	@Override
	public FacturacionCasoDTO transformarSinDependencias(FacturacionCaso obj) {
		FacturacionCasoDTO dto = new FacturacionCasoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public FacturacionCasoDTO transformarConDependencias(FacturacionCaso obj) {
		FacturacionCasoDTO dto = new FacturacionCasoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public FacturacionCaso transformarEntidadConDependencias(FacturacionCaso obj) {
		return new FacturacionCasoDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public FacturacionCaso transformarEntidadSinDependencias(FacturacionCaso obj) {
		return new FacturacionCasoDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	@Override
	public List<TramiteOrdinarioDTO> casosPendienteCobroByConciliador(FiltrosTramiteOrdinarioDTO filtros) {
		List<Long> idCentros = new ArrayList<>();
		for (CentroDTO centro : filtros.getCentros()) {
			idCentros.add(centro.getIdCentro());
		}
		String centrosIn = UtilConsultasSQL.clausulaInSQLSNumeros(idCentros);
		List<TramiteOrdinarioDTO> datosCaso = manejadorFacturacionCaso.casosPendienteCobroByConciliador(
				filtros.getFechaDesde(), filtros.getFechaHasta(), filtros.getIdConciliador(), centrosIn);
		return calcularTotalByConciliador(datosCaso, filtros.getIdConciliador());

	}

	@Override
	public List<TramiteOrdinarioDTO> calcularTotalByConciliador(List<TramiteOrdinarioDTO> datosCasos,
			long idConciliador) {
		boolean bandera = false;
		double valorTotal = 0;
		long conciliador = 0;
		List<TramiteOrdinarioDTO> aux = new ArrayList<>();
		TramiteOrdinarioDTO caso;
		if (!datosCasos.isEmpty()) {
			for (TramiteOrdinarioDTO dato : datosCasos) {
				if (dato.getIdConciliador() != conciliador && bandera) {
					conciliador = dato.getIdConciliador();
					caso = new TramiteOrdinarioDTO();
					caso.setIdConciliador(Long.valueOf(UtilConstantes.CERO));
					caso.setValorCobrar(valorTotal);
					if (idConciliador == 0)
						caso.setNombreCaso(UtilConstantes.TEXTO_SUBTOTAL);
					else
						caso.setNombreCaso(UtilConstantes.TEXTO_VALOR_TOTAL);
					valorTotal = dato.getValorCobrar();
					aux.add(caso);
					aux.add(dato);
				} else {
					valorTotal += dato.getValorCobrar();
					conciliador = dato.getIdConciliador();
					aux.add(dato);
					bandera = true;
				}
			}
			caso = new TramiteOrdinarioDTO();
			caso.setIdConciliador(Long.valueOf(UtilConstantes.CERO));
			caso.setValorCobrar(valorTotal);
			if (idConciliador == 0)
				caso.setNombreCaso(UtilConstantes.TEXTO_SUBTOTAL);
			else
				caso.setNombreCaso(UtilConstantes.TEXTO_VALOR_TOTAL);
			aux.add(caso);
		}

		return aux;
	}

	@Override
	public List<TramiteOrdinarioDTO> aprobarFacturaByConciliador(List<TramiteOrdinarioDTO> tramites, long idConciliador,
			long idUsuario) {
		List<TramiteOrdinarioDTO> tramitesAprobados = new ArrayList<>();
		List<FacturacionCaso> facturacionesCrear = new ArrayList<FacturacionCaso>();
		List<FacturacionCaso> facturacionesActualizar = new ArrayList<FacturacionCaso>();
		for (TramiteOrdinarioDTO tramite : tramites) {
			if( tramite.getIdCaso() != null ){
				FacturacionCaso fc = manejadorFacturacionCaso.buscar(tramite.getIdFacturacionCaso());
				if ( tramite.getIdFacturacionCaso() == null ) {
					fc = crearFacturacionCaso(tramite.getIdCaso());
				}
				fc.setAprobado(tramite.getAprobado());
				fc.setFechaDeAprobacion(new Date());
				fc.setIdUsuarioModificacion(Long.toString(idUsuario));
				if ( fc.getIdFacturacionCaso() != null ) {
					facturacionesActualizar.add(fc);
				} else{
					facturacionesCrear.add(fc);
				}
				tramitesAprobados.add(tramite);
			}
		}
		manejadorFacturacionCaso.actualizarLista(facturacionesActualizar);
		manejadorFacturacionCaso.crearLista(facturacionesCrear);
		List<TramiteOrdinarioDTO> totalByConciliador = calcularTotalByConciliador(tramitesAprobados, idConciliador);
		enviarCorreoAuxiliar(totalByConciliador, idUsuario, idConciliador);
		enviarCorreoConciliador(totalByConciliador, idConciliador);
		return totalByConciliador;
	}

	public void enviarCorreoAuxiliar(List<TramiteOrdinarioDTO> datosConciliadores, Long idUsuario, Long idConciliador) {
		Usuario usuario = manejadorUsuario.buscar(Long.toString(idUsuario));
		Persona persona = manejadorPersona.buscar(usuario.getPersona().getIdPersona());
		List<Persona> listPersona = new ArrayList<>();
		List<String> listTexto = new ArrayList<>();
		double valorTotal = 0;
		if (persona != null) {
			listPersona.add(persona);
			StringBuilder bld = new StringBuilder();
			bld.append("<table>");
			for (TramiteOrdinarioDTO tramite : datosConciliadores) {
				bld.append(cuerpoCorreo(tramite, false, idConciliador, false));
				if (tramite.getIdCaso() != null)
					valorTotal += tramite.getValorCobrar();
			}
			if (idConciliador != null)
				bld.append("<tr><td>").append(UtilConstantes.CUERPO_TOTAL_COBRAR_CONCILIADORES).append("</td><td>")
						.append(UtilOperaciones.formatoPesos(valorTotal)).append("</td></tr>");
			bld.append("</table>");
			listTexto.add(bld.toString());
			correoRolPersonaCasoFacade.envioDeCorreo(UtilConstantes.ASUNTO_CASOS_CONCILIACION_POR_COBRAR, listPersona,
					null, null, listTexto, null, null, null, false);
		}

	}

	public void enviarCorreoConciliador(List<TramiteOrdinarioDTO> datosConciliadores, Long idConciliador) {
		boolean bandera = false;
		List<Persona> listPersona = new ArrayList<>();
		StringBuilder textoCorreo = new StringBuilder();
		textoCorreo.append("<table>");
		for (TramiteOrdinarioDTO conciliador : datosConciliadores) {
			if (!bandera) {
				Persona persona = manejadorPersona.buscar(conciliador.getIdConciliador());
				if ( persona != null )
					listPersona.add(persona);
				textoCorreo.append(cuerpoCorreo(conciliador, true, idConciliador, false));
				bandera = true;
			} else if (conciliador.getIdCaso() != null ) {
				bandera = false;
				textoCorreo.append("</table>");
				List<String> listTexto = new ArrayList<>();
				listTexto.add(textoCorreo.toString());
				textoCorreo = new StringBuilder();
				textoCorreo.append("<table>");
				correoRolPersonaCasoFacade.envioDeCorreo(UtilConstantes.ASUNTO_CASOS_CONCILIACION_POR_COBRAR,
						listPersona, null, null, listTexto, null, null, null, false);
			} else {
				textoCorreo.append(cuerpoCorreo(conciliador, true, idConciliador, true));
			}

		}
	}

	public String cuerpoCorreo(TramiteOrdinarioDTO tramite, boolean conciliador, Long idConciliador, boolean repetido) {
		String textoCorreo = "";
		if (tramite.getIdCaso() != null ) {
			if (conciliador && !repetido) {
				textoCorreo += "<tr><td colspan=\"2\">";
				textoCorreo += "Señor(a)" + tramite.getConciliador()
						+ " La siguiente es la lista de casos de trámite ordinario que puede cobrar este mes:";
				textoCorreo += "</td></tr>";
			} else if (!repetido)
				textoCorreo += "<tr><td>Conciliador:</td><td>" + tramite.getConciliador() + "</td></tr>";

			textoCorreo += "<tr><td>Código del caso: </td><td>" + tramite.getIdCaso() + "</td></tr>";
			textoCorreo += "<tr><td>Fecha de cierre: </td><td>"
					+ UtilOperaciones.formatearFechaFormato(tramite.getFechaCierre(), "dd/MM/yyyy") + "</td></tr>";
			textoCorreo += "<tr><td>Resultado: </td><td>" + tramite.getResultado() + "</td></tr>";
			textoCorreo += "<tr><td>Valor a cobrar: </td><td>" + UtilOperaciones.formatoPesos(tramite.getValorCobrar()) + "</td></tr>";
		} else if (tramite.getIdCaso() != null && idConciliador != null ) {
			textoCorreo += "<tr><td>Subtotal conciliador: </td><td>" + UtilOperaciones.formatoPesos(tramite.getValorCobrar()) + "</td></tr>";
		}
		return textoCorreo;
	}
	
	
	@Override
	public void actualizarFacturacionRutaCaso(FacturacionCaso facturacionCaso) {
		String idUsuario = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if(appContext!= null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() != null  ){
			idUsuario = appContext.getContextoSesion().getNombreUsuario();
		}	
		FacturacionCaso facturacion = manejadorFacturacionCaso
				.obtenerFacturacionCasoPorCaso(facturacionCaso.getIdCaso());

		if (facturacion == null) {
			facturacion = new FacturacionCaso();
			facturacion.setValor(new BigDecimal(0) );
			facturacion.setAprobado(false);
			facturacion.setFechaDeFacturacion(facturacionCaso.getFechaDeCobro());
			facturacion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);		
			facturacion.setIdCaso(facturacionCaso.getIdCaso());
		}
		
		facturacion.setCobrado(facturacionCaso.getCobrado());
		facturacion.setFechaDeCobro(facturacionCaso.getFechaDeCobro());
		facturacion.setFechaUltimaModificacion(new Date());
		facturacion.setIdUsuarioModificacion(idUsuario);
		if (facturacion.getIdFacturacionCaso() != null) {
			manejadorFacturacionCaso.actualizar(facturacion);
		} else {
			manejadorFacturacionCaso.crear(facturacion);
		}

	}
	
	private FacturacionCaso crearFacturacionCaso ( Long idCaso ){
		FacturacionCaso fc = new FacturacionCaso();
		fc.setIdCaso(idCaso);
		fc.setFechaDeFacturacion(new Date());
		fc.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		//Corrección ICON22 
		List<PagoCaso> valoresPagosCaso = manejadorPagoCaso.obtenerUltimoPagoCaso(idCaso);
		Long valorPagoCaso = (valoresPagosCaso != null && !valoresPagosCaso.isEmpty())? manejadorFacturacionCaso.obtenerValorConciliador(idCaso) : 0;
		fc.setValor(BigDecimal.valueOf(valorPagoCaso));
		Double valorDevoluciones = manejadorReliquidacion.obtenerReliquidacionCasoTipo(idCaso, UtilDominios.TIPO_RELIQUIDACION_DEVOLUCION);
		Double valorCobroAdicional = manejadorReliquidacion.obtenerReliquidacionCasoTipo(idCaso, UtilDominios.TIPO_RELIQUIDACION_RELIQUIDACION);
		Double valorCobrosAdicionales = ((valorCobroAdicional !=null)? valorCobroAdicional : 0) - ((valorDevoluciones != null)? valorDevoluciones : 0);
		fc.setValorCobrosAdicionales(BigDecimal.valueOf(valorCobrosAdicionales));
		fc.setValorTotal(BigDecimal.valueOf(valorPagoCaso + valorCobrosAdicionales));
		return fc;
	}
	
	@Override
	public List<LiquidacionCasosConvenioDTO> consultarLiquidacionCasosConvenio( FiltroReportesDTO filtros ){
		List<Caso> casosPorLiquidar = manejadorFacturacionCaso.consultarCasosConvenioPorFacturar(filtros);
		ContratoConvenio contratoConvenio = null;
		if( casosPorLiquidar != null && !casosPorLiquidar.isEmpty() ){
			List<TarifaContrato> tarifasContrato = manejadorTarifaContrato.consultarTarifasContratoConvenio(filtros.getIdConvenio());
			if( tarifasContrato != null && tarifasContrato.isEmpty() )
				throw new SIMASCNegocioExcepcion(
						String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO387.toString())));
			else
				contratoConvenio = tarifasContrato.get(0).getContratoConvenio();
		}
		List<LiquidacionCasosConvenioDTO> facturacionesCasosConvenio = new ArrayList<LiquidacionCasosConvenioDTO>();
		
		for( Caso caso : casosPorLiquidar ){
			LiquidacionCasosConvenioDTO facturacionCasoConvenioDTO = new LiquidacionCasosConvenioDTO();
			Double valorCaso = null;
			Double valorCobroAdicionalCaso = null;
			Long numeroAudienciasCelebradas;
			Double porcentajeRecobro;
			
			facturacionCasoConvenioDTO.setIdCaso(caso.getIdCaso());
			facturacionCasoConvenioDTO.setNombreCaso(caso.getNombre());
			facturacionCasoConvenioDTO.setFechaCierre(caso.getFechaEstado());
			String resultado = dominioFacade.getNombreDominio( UtilDominios.DOMINIO_RESULTADO_CASO_CONCILIACION, caso.getResultado() );
			facturacionCasoConvenioDTO.setResultado(resultado);
			
			if( UtilDominios.RESULTADO_CASO_CONCILIACION_CANCELACION.equals(caso.getResultado()) 
				&& manejadorDiaFestivo.obtenerDiasEntreDosFechas(caso.getFechaRadicacion(), caso.getFechaEstado()) <= contratoConvenio.getDiasCancelacion() ){
				facturacionCasoConvenioDTO.setValorCaso( 0D );
				facturacionCasoConvenioDTO.setValorCobrosAdicionales( null );
			} else{
				TarifaContrato tarifaCaso = manejadorTarifaContrato.consultarTarifaCaso( caso, contratoConvenio );
				if( tarifaCaso != null && tarifaCaso.getPorcentaje() != null ){
					ProcesoReliquidacionDTO reliquidacion = new ProcesoReliquidacionDTO();
					reliquidacion.setCuantia( UtilDominios.TIPO_CUANTIA_CONCILIACION_DETERMINADO.equals(caso.getTipoCuantia())? caso.getValorPretensiones() : null );
					reliquidacion.setIdCentro(caso.getSede().getIdCentro());
					reliquidacion.setIsSolicitud(true);
					reliquidacion.setIdServicio(UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO);
					valorCaso = solicitudServicioFacade.liquidar(reliquidacion).getValor();
					valorCaso = valorCaso*( 1 - (tarifaCaso.getPorcentaje())/100 );
				} else if( tarifaCaso != null && tarifaCaso.getValor() != null ){
					valorCaso = tarifaCaso.getValor();
				}
				//recobro de audiencias adicionales
				if( valorCaso != null ){					
					numeroAudienciasCelebradas = audienciaFacade.obtenerNumeroAudienciasCaso(caso.getIdCaso(),
							Arrays.asList(UtilDominios.ESTADO_AUDIENCIA_REALIZADA));
					if( numeroAudienciasCelebradas > contratoConvenio.getMaximoAudiencias() ){
						porcentajeRecobro = ( numeroAudienciasCelebradas - contratoConvenio.getMaximoAudiencias() )*
											contratoConvenio.getIncrementoAudienciaAdicional().doubleValue();
						valorCobroAdicionalCaso = (porcentajeRecobro/100)*valorCaso;
						valorCaso = valorCaso + valorCobroAdicionalCaso;
					}
				}
				facturacionCasoConvenioDTO.setValorCaso( valorCaso );
				facturacionCasoConvenioDTO.setValorCobrosAdicionales( valorCobroAdicionalCaso );
			}
			facturacionesCasosConvenio.add(facturacionCasoConvenioDTO);
		}
		return facturacionesCasosConvenio;
	}
	
	@Override
	public void guardarFacturacionCasosConvenio( Long idConvenio , List<LiquidacionCasosConvenioDTO> listaFacturacion, String idUsuarioModificacion){
		//Se inserta la informacion de la facturacion del caso convenio
		for (LiquidacionCasosConvenioDTO facturacionCasosConvenioDTO : listaFacturacion) {
			//Campos comunes
			FacturacionCaso facturacionCaso = new FacturacionCaso();
			facturacionCaso.setAprobado(true);
			facturacionCaso.setCobrado(false);
			facturacionCaso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			facturacionCaso.setFechaDeAprobacion(new Date());
			facturacionCaso.setFechaUltimaModificacion(new Date());
			facturacionCaso.setIdUsuarioModificacion(idUsuarioModificacion);
			facturacionCaso.setFechaDeFacturacion(new Date());
			if(facturacionCasosConvenioDTO.getValorCaso()!=null){
				facturacionCaso.setIdCaso(facturacionCasosConvenioDTO.getIdCaso());
				facturacionCaso.setValorTotal(BigDecimal.valueOf(facturacionCasosConvenioDTO.getValorCaso()));
				if(facturacionCasosConvenioDTO.getValorCobrosAdicionales()!=null){
					facturacionCaso.setValorCobrosAdicionales(BigDecimal.valueOf(facturacionCasosConvenioDTO.getValorCobrosAdicionales()));
					facturacionCaso.setValor(BigDecimal.valueOf(facturacionCasosConvenioDTO.getValorCaso()-facturacionCasosConvenioDTO.getValorCobrosAdicionales()));	
				}else{
					facturacionCaso.setValor(BigDecimal.valueOf(facturacionCasosConvenioDTO.getValorCaso()));
				}
				try {
					manejadorFacturacionCaso.crear(facturacionCaso);	
				} catch (Exception e) {
					String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO392.toString(),facturacionCasosConvenioDTO.getIdCaso().toString());
					throw new SIMASCNegocioExcepcion(
							String.format(MensajesSimasc.getInstancia().getMensajePorKey(mensaje)));
				}
			}
		}
		enviarNotificacionAnalistaConvenio( idConvenio, listaFacturacion );
	}
	
	/** CON-C-014 envia la notificacion al analista de conciliacion con la liquidacion de los casos de un convenio
	 * @param idConvenio
	 * @param listaFacturacion
	 */
	private void enviarNotificacionAnalistaConvenio( Long idConvenio , List<LiquidacionCasosConvenioDTO> listaFacturacion ){
		if( !listaFacturacion.isEmpty() ){
			Boolean liquidacionValida = false;
			//Se envia el correo al analista de conciliación
			List<String> roles= new ArrayList<String>();
			roles.add(UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION);
			List<PersonaBasicaDTO> analistas = manejadorRelacionadoConvenio.consultarPersonasRelacionadoConvenio(roles, idConvenio);
			List<Persona> personasNotificacion = new ArrayList<Persona>();
			for (PersonaBasicaDTO persona : analistas) {
				Persona personaNotificacion = new Persona();
				personaNotificacion.setIdPersona(persona.getIdPersona());
				personasNotificacion.add(personaNotificacion);
			}
			//Correo
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			List<String> listTexto = new ArrayList<>();
			StringBuilder bld = new StringBuilder();
			bld.append("La siguiente es la lista de casos de convenio cerrados durante el mes con los valores a facturar:");
			bld.append("<br/><br/>");
			bld.append("<table border=1>");
			bld.append("<tr><td>Código del Caso</td>");
			bld.append("<td>Fecha del Cierre</td>");
			bld.append("<td>Resultado</td>");
			bld.append("<td>Valor a Cobrar</td></tr>");
				
			for (LiquidacionCasosConvenioDTO listaFactura : listaFacturacion) {

				if(listaFactura.getValorCaso()!=null){
					bld.append("<tr><td>").append(listaFactura.getIdCaso()).append("</td>");
					bld.append("<td>").append(df.format(listaFactura.getFechaCierre())).append("</td>");
					bld.append("<td>").append(listaFactura.getResultado()).append("</td>");
					bld.append("<td>").append(listaFactura.getValorCaso()).append("</td></tr>");
					liquidacionValida = true;
				}	
			}
			bld.append("</table>");
			listTexto.add(bld.toString());
			if( !personasNotificacion.isEmpty() && liquidacionValida )
				correoRolPersonaCasoFacade.envioDeCorreo(UtilConstantes.ASUNTO_FACTURACION_CASOS_CONVENIO,
															personasNotificacion, null, null, listTexto, null, null, null, false);			
		}
	}
	// protected region metodos adicionales end

}
