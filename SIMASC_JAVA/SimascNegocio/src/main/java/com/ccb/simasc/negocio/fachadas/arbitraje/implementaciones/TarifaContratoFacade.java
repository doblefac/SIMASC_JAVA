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
import com.ccb.simasc.integracion.manejadores.ManejadorContratoConvenio;
import com.ccb.simasc.integracion.manejadores.ManejadorTarifaContrato;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITarifaContratoFacade;
import com.ccb.simasc.transversal.dto.ContratoConvenioDTO;
import com.ccb.simasc.transversal.dto.TarifaContratoDTO;
import com.ccb.simasc.transversal.entidades.ContratoConvenio;
import com.ccb.simasc.transversal.entidades.TarifaContrato;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link TarifaContrato}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class TarifaContratoFacade extends AccesoFacade<TarifaContrato, Long, TarifaContratoDTO> implements ITarifaContratoFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorTarifaContrato manejadorTarifaContrato;
	
	@EJB
	private ManejadorContratoConvenio manejadorContratoConvenio;
	
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorTarifaContrato;
	}

	@Override
	public TarifaContratoDTO transformarSinDependencias(TarifaContrato obj) {	 
		return new TarifaContratoDTO().transformarSinDependencias(obj);
	}

	@Override
	public TarifaContratoDTO transformarConDependencias(TarifaContrato obj) {
		return new TarifaContratoDTO().transformarConDependencias(obj);
		
	}

	@Override
	public TarifaContrato transformarEntidadConDependencias(TarifaContrato obj) {
		return new TarifaContratoDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public TarifaContrato transformarEntidadSinDependencias(TarifaContrato obj) {
		return new TarifaContratoDTO().transformarEntidadSinDependencias(obj);
	}
	
	
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public List<TarifaContratoDTO> consultarTarifasContrato(TarifaContratoDTO tarifaContrato){
		return (List<TarifaContratoDTO>) transformarColeccionSinDependencias(manejadorTarifaContrato.consultarTarifasContrato(tarifaContrato), new ArrayList<TarifaContratoDTO>());
	}
	
	@Override
	public void actualizarTarifasContrato(List<TarifaContratoDTO> tarifas, String idUsuario){
		if(!tarifas.isEmpty()){
			//Actualizacion
			if(tarifas.get(0).getValor()!=null || tarifas.get(0).getPorcentaje()!=null){
				for (TarifaContratoDTO tarifaContratoDTO : tarifas) {
					TarifaContrato tarifaActualizar = manejadorTarifaContrato.buscar(tarifaContratoDTO.getIdTarifaContrato());
					tarifaActualizar.setValor(tarifaContratoDTO.getValor());
					tarifaActualizar.setPorcentaje(tarifaContratoDTO.getPorcentaje());
					tarifaActualizar.setIdUsuarioModificacion(idUsuario);
					tarifaActualizar.setFechaUltimaModificacion(new Date());
					manejadorTarifaContrato.actualizar(tarifaActualizar);
				}
			}
			//Eliminar
			else{
				
				TarifaContratoDTO numeroTarifas = new TarifaContratoDTO();
				numeroTarifas.setCodigoContrato(tarifas.get(0).getCodigoContrato());
				numeroTarifas.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				List<TarifaContrato> cambiarTipoCombinacion = manejadorTarifaContrato.consultarTarifasContrato(numeroTarifas);
				if(cambiarTipoCombinacion.isEmpty()){
					ContratoConvenioDTO contrato = new ContratoConvenioDTO();
					contrato.setCodigoContrato(tarifas.get(0).getCodigoContrato());
					List<ContratoConvenio> contratoActualizar = manejadorContratoConvenio.consultarContratoConvenios(contrato);
					contratoActualizar.get(0).setTipoCombinacion(null);
					manejadorContratoConvenio.actualizar(contratoActualizar.get(0));
				}
				TarifaContrato tarifa= manejadorTarifaContrato.buscar(tarifas.get(0).getIdTarifaContrato());
				tarifa.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
				tarifa.setFechaUltimaModificacion(new Date());
				tarifa.setIdUsuarioModificacion(idUsuario);
				manejadorTarifaContrato.actualizar(tarifa);
			}
		}
	}
	
	@Override
	public void crearTarifasContrato(List<TarifaContrato> tarifaContrato){
		
		if(tarifaContrato.isEmpty() || tarifaContrato.get(0) == null || tarifaContrato.get(0).getCodigoContrato() == null){
			throw new SIMASCNegocioExcepcion("Error de datos validos para guardar");
		}
		
		
		//Validaciones de tipo de tarifa y cruce
		String tipoCombinacion =	
				this.validarTarifasExistentes(tarifaContrato,tarifaContrato.get(0).getCodigoContrato());
		
		// Almancenamiento
		String idUsuario = UtilConstantes.USUARIO_DEFECTO_SIMASC;		
		if(appContext!= null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() != null  ){
			idUsuario = appContext.getContextoSesion().getNombreUsuario();
		}	

		for (TarifaContrato tarifaContratoFor : tarifaContrato) {
			tarifaContratoFor.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			tarifaContratoFor.setFechaUltimaModificacion(new Date());
			tarifaContratoFor.setIdUsuarioModificacion(idUsuario);
			manejadorTarifaContrato.crear(tarifaContratoFor);
		}
		//Actualizacion de la tarifa
		ContratoConvenio contrato = manejadorContratoConvenio.buscar(tarifaContrato.get(0).getCodigoContrato());
		contrato.setTipoCombinacion(tipoCombinacion);
		manejadorContratoConvenio.actualizar(contrato);
		
		
	}
	
	private String validarTarifasExistentes(List<TarifaContrato> tarifasNuevas, String codigoContrato){
		String tipoCombinacionNueva  = validaTipoTarifa(tarifasNuevas);
		TarifaContratoDTO filtroTarifa = new TarifaContratoDTO();
		filtroTarifa.setCodigoContrato(codigoContrato);
		List<TarifaContrato> tarifasActuales = 	manejadorTarifaContrato.consultarTarifasContrato(filtroTarifa);	
		if(!tarifasActuales.isEmpty()){
			
			String tipoTarifaActual = tarifasActuales.get(0).getContratoConvenio().getTipoCombinacion();
			if(tipoTarifaActual == null){
				throw new SIMASCNegocioExcepcion("Las tarifas almacenadas no tienen tipo estructura");
			}
			
			if(! tipoTarifaActual.equals( tipoCombinacionNueva ) ){
				throw new SIMASCNegocioExcepcion("Las tarifas almacenadas no tienen la estructura de las tarifas seleccionadas");			
			}
			for (TarifaContrato almacenadaFor : tarifasActuales) {
				for (TarifaContrato nuevaFor : tarifasNuevas) {
					validacionCrucesTarifas(almacenadaFor, nuevaFor, tipoCombinacionNueva);
				}
			
			}
		}

		return tipoCombinacionNueva;
		
	}
	
	private  String validaTipoTarifa(List<TarifaContrato> tarifasNuevas)  {
		boolean[] tipoTarifa = new boolean[4];
		String tipoTarifaCalculo = null;

		for (TarifaContrato tarifaFor : tarifasNuevas) {
			validarIntegridadData(tarifaFor);
			if (tarifaFor.getCuantiaMinima() != null && tarifaFor.getMinimoCasos() != null) {
				// tarifa completa 0
				tipoTarifaCalculo = UtilDominios.CODIGO_TIPO_COMBINACION_COMPLETA ;
				tipoTarifa[0] = true; 
			} else if (tarifaFor.getCuantiaMinima() != null) {
				// solo cuantia 1
				tipoTarifa[1] = true; 
				tipoTarifaCalculo =  UtilDominios.CODIGO_TIPO_COMBINACION_CUANTIA;
			} else if (tarifaFor.getMinimoCasos() != null) {
				// solo volumen  3
				tipoTarifa[2] = true; 
				tipoTarifaCalculo = UtilDominios.CODIGO_TIPO_COMBINACION_VOLUMEN;
			} else {
				// solo resultado  4
				tipoTarifa[3] = true; 
				tipoTarifaCalculo = UtilDominios.CODIGO_TIPO_COMBINACION_RESULTADO;
			}
		}

		validacionGeneralData(tipoTarifa, tarifasNuevas.get(0).getIdTarifaContrato());

		return tipoTarifaCalculo;
	}

	private void validarIntegridadData(TarifaContrato tarifa) {
		if (tarifa.getResultado() == null || (tarifa.getCuantiaMaxima() == null ^ tarifa.getCuantiaMinima() == null)
				|| (tarifa.getMaximoCasos() == null ^ tarifa.getMinimoCasos() == null)) {
			String mensaje = tarifa.getIdTarifaContrato() == null ? "con uno de los datos seleccionados(Nuevos)"
					: "en los datos almacenados registro: idTarifa = " + tarifa.getIdTarifaContrato();
			throw new  SIMASCNegocioExcepcion("Error de estructura individual  " + mensaje);
		}
	}

	private void validacionGeneralData(boolean[] tipoTarifa, Long idTarifa) {
		int valorValidacion = 0;
		for (boolean tipoFor : tipoTarifa) {
			if (tipoFor) {
				valorValidacion++;
			}
		}
		if (valorValidacion > 1) {
			String mensaje = idTarifa == null ? "en las tarifas seleccionadas" : "en las tarifas almmacenadas";
			throw new SIMASCNegocioExcepcion("Existe mas de un tipo de tarifa " + mensaje);
		}
	}



	private void validacionCrucesTarifas(TarifaContrato taAlmacenada, TarifaContrato taNueva, String tipo)  {
		if(!taAlmacenada.getTipoTarifa().equals(taNueva.getTipoTarifa())){
			throw new SIMASCNegocioExcepcion("El contrato no puede contener tarifas dinamicas y fijas al mimo tiempo");
		}
		
		if ( validacionResultados(taAlmacenada, taNueva) && 
			 ( (tipo.equals(UtilDominios.CODIGO_TIPO_COMBINACION_CUANTIA) || tipo.equals(UtilDominios.CODIGO_TIPO_COMBINACION_RESULTADO) ) 
					 || validacionCruceVolumen(taAlmacenada, taNueva) )&& 
			 ( (tipo.equals(UtilDominios.CODIGO_TIPO_COMBINACION_VOLUMEN)  || tipo.equals(UtilDominios.CODIGO_TIPO_COMBINACION_RESULTADO) ) 
					 || validacionCruceCuantias(taAlmacenada, taNueva)) ){
			throw new SIMASCNegocioExcepcion("Una de las tarifas seleccionadas presentan cruce con las tarifas previamente almacenadas");
		}
	}
	
	
	
	private boolean validacionResultados(TarifaContrato taAlmacenada, TarifaContrato taNueva){
		return taAlmacenada.getResultado().equals(taNueva.getResultado());
	}

	private boolean validacionCruceCuantias(TarifaContrato taAlmacenada, TarifaContrato taNueva) {

		return !((taNueva.getCuantiaMaxima() < taAlmacenada.getCuantiaMinima())
				|| (taNueva.getCuantiaMinima() > taAlmacenada.getCuantiaMaxima()));

	}

	private boolean validacionCruceVolumen(TarifaContrato taAlmacenada, TarifaContrato taNueva) {

		return !((taNueva.getMaximoCasos() < taAlmacenada.getMinimoCasos())
				|| (taNueva.getMinimoCasos() > taAlmacenada.getMaximoCasos()));

	}
	
	


	
	
	
	
	
	// protected region metodos adicionales end
	
}
