package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrizacionTarifas;
import com.ccb.simasc.integracion.manejadores.ManejadorTrm;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrizacionTarifasInternacionalFacade;
import com.ccb.simasc.transversal.dto.ParametrizacionTarifasDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosTarifasInternacionalesDTO;
import com.ccb.simasc.transversal.dto.formularios.ResultadosTarifaInternacionalDTO;
import com.ccb.simasc.transversal.entidades.ParametrizacionTarifas;
import com.ccb.simasc.transversal.entidades.Trm;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

@Stateless
@LocalBean
public class ParametrizacionTarifasInternacionalFacade extends AccesoFacade<ParametrizacionTarifas, Long, ParametrizacionTarifasDTO>
		implements IParametrizacionTarifasInternacionalFacade {

	@EJB
	private ManejadorParametrizacionTarifas manejadorParametrizacionTarifas;
	
	@EJB
	private ManejadorTrm manejadorTrm;	

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorParametrizacionTarifas;
	}

	@Override
	public ParametrizacionTarifasDTO transformarSinDependencias(ParametrizacionTarifas obj) {
		return new ParametrizacionTarifasDTO().transformarSinDependencias(obj);
	}

	@Override
	public ParametrizacionTarifasDTO transformarConDependencias(ParametrizacionTarifas obj) {
		return new ParametrizacionTarifasDTO().transformarConDependencias(obj);
	}

	@Override
	public ParametrizacionTarifas transformarEntidadConDependencias(ParametrizacionTarifas obj) {
		return new ParametrizacionTarifasDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public ParametrizacionTarifas transformarEntidadSinDependencias(ParametrizacionTarifas obj) {
		return new ParametrizacionTarifasDTO().transformarEntidadSinDependencias(obj);
	}
	
	@Override
	public ResultadosTarifaInternacionalDTO calcularTarifaPublico(ParametrosTarifasInternacionalesDTO parametrosTarifasDTO) {
			
		/*
		 * basado en las tarifas para ‘Reglamento CAC vigente hasta el 09-10-2022’
		 * */
		if(parametrosTarifasDTO.getModeloTarifa().equals(UtilDominios.CODIGO_DOMINIO_TARIFA_REGLAMENTO_CAC_09)
			|| parametrosTarifasDTO.getModeloTarifa().equals(UtilDominios.CODIGO_DOMINIO_TARIFA_REGLAMENTO_UNCITRAL_09)) {
			
			parametrosTarifasDTO.setModeloTarifa(UtilDominios.CODIGO_DOMINIO_TARIFA_REGLAMENTO_CAC_09);			
		}	
		
		/*
		 * basado en las tarifas para ‘Reglamento CAC vigente desde el 10-10-2022’
		 * */		
		if(parametrosTarifasDTO.getModeloTarifa().equals(UtilDominios.CODIGO_DOMINIO_TARIFA_REGLAMENTO_CAC_10)
				|| parametrosTarifasDTO.getModeloTarifa().equals(UtilDominios.CODIGO_DOMINIO_TARIFA_REGLAMENTO_UNCITRAL_10)) {
				
			parametrosTarifasDTO.setModeloTarifa(UtilDominios.CODIGO_DOMINIO_TARIFA_REGLAMENTO_CAC_10);	
		}
				
		return calculoTarifasInternacionales(parametrosTarifasDTO);
	}
	
	
	public ResultadosTarifaInternacionalDTO calculoTarifasInternacionales(ParametrosTarifasInternacionalesDTO parametrosTarifasDTO) {
		
		ResultadosTarifaInternacionalDTO resultado = new ResultadosTarifaInternacionalDTO();
		Date fechaTRM = null;
		String valorTRM = null;
		
		if(parametrosTarifasDTO.getMoneda().equals(UtilDominios.TIPO_MONEDA_COP)) {
			Trm trmActual = obtenerTrmActual();
			fechaTRM = trmActual.getFecha();
			valorTRM = trmActual.getValor();
			parametrosTarifasDTO.setMontoDisputa(this.convertirTarifaActorDolares(
					parametrosTarifasDTO.getMontoDisputa()));
		}
		
		String tarifaArbitroMax = tarifaPorActor(parametrosTarifasDTO, UtilDominios.CODIGO_DOMINIO_ACTOR_ARBITRO_MAXIMO);		
		String tarifaArbitroMin = tarifaPorActor(parametrosTarifasDTO, UtilDominios.CODIGO_DOMINIO_ACTOR_ARBITRO_MINIMO);		
		String tarifaAdministrativa = tarifaPorActor(parametrosTarifasDTO, UtilDominios.CODIGO_DOMINIO_ACTOR_TARIFA_ADMINISTRATIVA);		
		
		resultado.setHonorariosMaximo(tarifaArbitroMax);
		resultado.setHonorariosMinimo(tarifaArbitroMin);
		resultado.setGastosAdministrativos(tarifaAdministrativa);
		resultado.setFechaTRM(fechaTRM);
		resultado.setValorTRM(valorTRM);
		
		return resultado;
	}
	
	public String tarifaPorActor(ParametrosTarifasInternacionalesDTO parametrosTarifasDTO, String actor) {
		
		BigDecimal tarifaActor = new BigDecimal(0);
		BigDecimal cantidadTresArbitros = new BigDecimal(3);
		
		List<ParametrizacionTarifas> listadoTarifasParaHonorarioMaximo = 
				manejadorParametrizacionTarifas.obtenerListadoTarifasPorMonto(parametrosTarifasDTO, actor);			
		if(listadoTarifasParaHonorarioMaximo.size() > 1) {
			tarifaActor = calculoAcumulativo(listadoTarifasParaHonorarioMaximo, parametrosTarifasDTO.getMontoDisputa());
		}else {
			tarifaActor = calculoPorcentual(listadoTarifasParaHonorarioMaximo, parametrosTarifasDTO.getMontoDisputa());
		}		
				
		if(parametrosTarifasDTO.getCantidadArbitros().equals(UtilDominios.CANTIDAD_ARBITROS_TRES) && actor.contains("ARBIN")) {
			tarifaActor = tarifaActor.multiply(cantidadTresArbitros);
		}
		if(parametrosTarifasDTO.getMoneda().equals(UtilDominios.TIPO_MONEDA_COP)) {			
			tarifaActor = convertirTarifaActorPesos(tarifaActor);
		}
		
		return tarifaActor.setScale(0, RoundingMode.HALF_UP).toString();
	}
	
	public BigDecimal calculoAcumulativo(List<ParametrizacionTarifas> listadoTarifas, BigDecimal monto) {
		
		BigDecimal bigDecimal100 = new BigDecimal(100);
		BigDecimal tarifaAcumulada  = new BigDecimal(0);
		BigDecimal rangoInicial  = new BigDecimal(0);
		int ultimaPosicion = listadoTarifas.size() - 1;
				
		for (int i = 0; i < listadoTarifas.size(); i++) {
			
			if(listadoTarifas.get(i).getRangoInferior().compareTo(rangoInicial) == 0) {
				
				BigDecimal tarifa = new BigDecimal(0);
				
				if(listadoTarifas.get(i).getTipoValorMinimo().equals(UtilDominios.TIPO_VALOR_TARIFA_PORCENTAJE)) {
					tarifa = listadoTarifas.get(i).getRangoSuperior().multiply(listadoTarifas.get(i).getValorMinimo().divide(bigDecimal100));
				}else if(listadoTarifas.get(i).getTipoValorMinimo().equals(UtilDominios.TIPO_VALOR_TARIFA_USD)){
					tarifa = listadoTarifas.get(i).getValorMinimo();
				}				
				tarifaAcumulada = tarifaAcumulada.add(tarifa);
				continue;
			}
			BigDecimal diferencial = new BigDecimal(0);
			if(listadoTarifas.get(i).getTipoValorMinimo().equals(UtilDominios.TIPO_VALOR_TARIFA_PORCENTAJE) && i < ultimaPosicion) {
				diferencial = listadoTarifas.get(i).getRangoSuperior().subtract(listadoTarifas.get(i).getRangoInferior());
				
			}if(i == ultimaPosicion) {
				diferencial = monto.subtract(listadoTarifas.get(i).getRangoInferior());
			}
			BigDecimal tarifa = diferencial.multiply(listadoTarifas.get(i).getValorMinimo().divide(bigDecimal100));			
			tarifaAcumulada = tarifaAcumulada.add(tarifa);
		}
		return tarifaAcumulada;
	}
	
	public BigDecimal calculoPorcentual(List<ParametrizacionTarifas> listadoTarifas, BigDecimal monto) {
		
		BigDecimal bigDecimal100 = new BigDecimal(100);
		
		for (ParametrizacionTarifas parametrizacionTarifa : listadoTarifas) {			
								
			if(parametrizacionTarifa.getTipoValorMinimo().equals(UtilDominios.TIPO_VALOR_TARIFA_PORCENTAJE)) {
				return monto.multiply(parametrizacionTarifa.getValorMinimo().divide(bigDecimal100));
			}else if(parametrizacionTarifa.getTipoValorMinimo().equals(UtilDominios.TIPO_VALOR_TARIFA_USD)){
				return parametrizacionTarifa.getValorMinimo();
			}									
		}
		return new BigDecimal(0);
	}
	
	public BigDecimal convertirTarifaActorPesos(BigDecimal tarifaActor) {
		
		Trm trmActual = obtenerTrmActual();
		String valorFormateado = trmActual.getValor().replace(".", "").replace(",", ".");
		BigDecimal valorTRM = new BigDecimal(valorFormateado);
		return tarifaActor.multiply(valorTRM);
	}
	
	public BigDecimal convertirTarifaActorDolares(BigDecimal tarifaActor) {
		
		Trm trmActual = obtenerTrmActual();
		String valorFormateado = trmActual.getValor().replace(".", "").replace(",", ".");
		BigDecimal valorTRM = new BigDecimal(valorFormateado);		
		return tarifaActor.divide(valorTRM, RoundingMode.HALF_UP);
	}
	
	public Trm obtenerTrmActual() {		
		return manejadorTrm.obtenerTrmActual();
	}
}
