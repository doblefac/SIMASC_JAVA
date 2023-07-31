package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta seccion sus modificaciones

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorHonorariosFijados;
import com.ccb.simasc.integracion.manejadores.ManejadorPagoHonorarios;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorValorHonorariosActor;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDiaFestivoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDistribucionTarifaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IHonorariosFijadosFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrizacionTarifasFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrosGeneralesFacade;
import com.ccb.simasc.transversal.dto.DistribucionTarifaDTO;
import com.ccb.simasc.transversal.dto.HonorariosFijadosDTO;
import com.ccb.simasc.transversal.dto.ParametrizacionTarifasDTO;
import com.ccb.simasc.transversal.dto.formularios.HonorariosActorDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosTarifasDTO;
import com.ccb.simasc.transversal.entidades.HonorariosFijados;
import com.ccb.simasc.transversal.entidades.PagoHonorarios;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.ValorHonorariosActor;
import com.ccb.simasc.transversal.entidades.ValorHonorariosActorPK;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link HonorariosFijados}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class HonorariosFijadosFacade extends AccesoFacade<HonorariosFijados, Long, HonorariosFijadosDTO>
		implements IHonorariosFijadosFacade {

	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada

	/**
	 * Manejador utilizado para consultar funcionalidad relacionadas con la
	 * entidad RolPersonaCaso
	 */
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	/**
	 * Manejador utilizado para consultar funcionalidad relacionadas con la
	 * entidad HonorariosFijados
	 */
	@EJB
	private ManejadorHonorariosFijados manejadorHonorariosFijados;
	/**
	 * Manejador utilizado para consultar funcionalidad relacionadas con la
	 * entidad ParametrosGenerales
	 */
	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;
	/**
	 * Manejador utilizado para consultar funcionalidad relacionadas con la
	 * entidad PagoHonorarios
	 */
	@EJB
	private ManejadorPagoHonorarios manejadorPagoHonorarios;
	/**
	 * Manejador utilizado para consultar metodos de parametrizacion de tarifas
	 */
	@EJB
	private IParametrizacionTarifasFacade parametrizacionTarifasFacade;
	/**
	 * fachada utilizada para consultar metodos de distribucion de tarifas
	 */
	@EJB
	private IDistribucionTarifaFacade distribucionTarifaFacade;
	/**
	 * fachada utilizada para consultar metodos de parametros generales
	 */
	@EJB
	private IParametrosGeneralesFacade parametrosGeneralesFacade;
	/**
	 * Manejador utilizado para consultar funcionalidad relacionadas con la
	 * entidad ValorHonorariosActor
	 */
	@EJB
	private ManejadorValorHonorariosActor manejadorValorHonorariosActor;

	@EJB
	private IDiaFestivoFacade diaFestivoFacade;
	
	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorHonorariosFijados;
	}

	@Override
	public HonorariosFijadosDTO transformarSinDependencias(HonorariosFijados obj) {
		HonorariosFijadosDTO dto = new HonorariosFijadosDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public HonorariosFijadosDTO transformarConDependencias(HonorariosFijados obj) {
		HonorariosFijadosDTO dto = new HonorariosFijadosDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public HonorariosFijados transformarEntidadConDependencias(HonorariosFijados obj) {
		HonorariosFijados dto = new HonorariosFijados();
		dto = new HonorariosFijadosDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public HonorariosFijados transformarEntidadSinDependencias(HonorariosFijados obj) {
		HonorariosFijados dto = new HonorariosFijados();
		dto = new HonorariosFijadosDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Consulta los honorarios fijados por las partes
	 * 
	 * @param Long
	 *            idCaso
	 */
	@Override
	public List<HonorariosFijadosDTO> consultarHonorariosFijados(Long idCaso) {
		Double iva = manejadorParametrosGenerales.buscar(UtilConstantes.IVA).getValorNumerico().doubleValue();
		BigDecimal valorTotal = BigDecimal.ZERO;
		BigDecimal valorTotalIva = BigDecimal.ZERO;
		BigDecimal bigDecimal100 = new BigDecimal(100);
		List<Persona> lstArbitrosPrincipales = manejadorRolPersonaCaso.consultarArbitrosPrincipales(idCaso,
				UtilDominios.ROL_PERSONA_ARBITRO);
		RolPersonaCaso rolSecretario = manejadorRolPersonaCaso.consultarSecretarioDelCaso(idCaso);
		HonorariosFijados honorariosFijados = manejadorHonorariosFijados.consultarPorIdCaso(idCaso);
		List<HonorariosFijadosDTO> lstHonorariosFijadosDTO = new ArrayList<>();
		if (honorariosFijados != null) {
			HonorariosFijadosDTO honorariosFijadosDTO = new HonorariosFijadosDTO();
			List<HonorariosActorDTO> lstActorArbitro = new ArrayList<>();
			HonorariosActorDTO actorSecretario = new HonorariosActorDTO();
			HonorariosActorDTO actorCAC = new HonorariosActorDTO();
			for (ValorHonorariosActor valorHonorariosActor : honorariosFijados.getValorHonorariosActorList()) {
				if (valorHonorariosActor.getValorHonorariosActorPK().getTipoActor()
						.equals(UtilDominios.TIPO_ACTOR_CASO_ARBITRO)) {
					for (Persona persona : lstArbitrosPrincipales) {
						HonorariosActorDTO arbitro = new HonorariosActorDTO();
						arbitro.setActor(persona.getNombreCompleto());
						arbitro.setRol(UtilDominios.ROL_PERSONA_ARBITRO);
						arbitro.setValor(valorHonorariosActor.getValor());
						lstActorArbitro.add(arbitro);
					}
					honorariosFijadosDTO.setHonorariosArbitros(lstActorArbitro);
					honorariosFijadosDTO.setValorArbitros(
							valorHonorariosActor.getValor().multiply(new BigDecimal(lstArbitrosPrincipales.size())));
				}
				if (valorHonorariosActor.getValorHonorariosActorPK().getTipoActor()
						.equals(UtilDominios.TIPO_ACTOR_CASO_SECRETARIO)) {
					actorSecretario.setActor(rolSecretario.getPersona().getNombreCompleto());
					actorSecretario.setRol(UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
					actorSecretario.setValor(valorHonorariosActor.getValor());
					honorariosFijadosDTO.setHonorariosSecretario(actorSecretario);
				}

				if (valorHonorariosActor.getValorHonorariosActorPK().getTipoActor().equals(UtilDominios.TIPO_ACTOR_CASO_CAC)) {
					actorCAC.setActor(UtilDominios.TIPO_ACTOR_CASO_CAC);
					actorCAC.setRol(UtilDominios.TIPO_ACTOR_CASO_CAC);
					actorCAC.setValor(valorHonorariosActor.getValor());
					honorariosFijadosDTO.setHonorariosCAC(actorCAC);
				}
			}
			valorTotal = actorCAC.getValor().add(actorSecretario.getValor())
					.add(honorariosFijadosDTO.getValorArbitros());
			valorTotalIva = valorTotal.multiply(new BigDecimal(iva).divide(bigDecimal100, 9, RoundingMode.HALF_UP));
			valorTotal = valorTotal.add(valorTotalIva);
			honorariosFijadosDTO.setFechaFijacion(honorariosFijados.getFechaFijacion());
			honorariosFijadosDTO.setTotal(valorTotal);
			honorariosFijadosDTO.setTotalIva(valorTotalIva);
			honorariosFijadosDTO.setValorFijadoPretensiones(honorariosFijados.getValorFijadoPretensiones());
			HonorariosFijadosDTO honorariosDemandanteDTO = new HonorariosFijadosDTO();
			honorariosFijadosDTO.setRolHonorarios(UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
			honorariosDemandanteDTO.setHonorariosFijados(honorariosFijadosDTO);
			HonorariosFijadosDTO honorariosDemandadoDTO = new HonorariosFijadosDTO();
			honorariosFijadosDTO.setRolHonorarios(UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
			honorariosDemandadoDTO.setHonorariosFijados(honorariosFijadosDTO);
			PagoHonorarios pagoHonorariosDemandante = manejadorPagoHonorarios.consultarPorIdCasoYPartePaga(idCaso,
					UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE);
			PagoHonorarios pagoHonorariosDemandado = manejadorPagoHonorarios.consultarPorIdCasoYPartePaga(idCaso,
					UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);

			if (pagoHonorariosDemandante != null) {
				honorariosDemandanteDTO.setFechaPago(pagoHonorariosDemandante.getFechaPago());
			}
			lstHonorariosFijadosDTO.add(honorariosDemandanteDTO);

			if (pagoHonorariosDemandado != null) {
				honorariosDemandadoDTO.setFechaPago(pagoHonorariosDemandado.getFechaPago());
			}
			lstHonorariosFijadosDTO.add(honorariosDemandadoDTO);
		} else {

			String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR183.toString());
			throw new SimascException(mensaje);
		}
		return lstHonorariosFijadosDTO;
	}

	/**
	 * Realiza el calculo de las tarifas para el pago de los honorarios de los
	 * actores en el caso
	 * 
	 * @param ParametrosTarifasDTO
	 *            parametrosTarifasDTO
	 */
	@Override
	public List<HonorariosFijadosDTO> calcularTarifas(ParametrosTarifasDTO parametrosTarifasDTO) {
		Double iva = manejadorParametrosGenerales.buscar(UtilConstantes.IVA).getValorNumerico().doubleValue();
		BigDecimal valorTotal = BigDecimal.ZERO;
		BigDecimal valorTotalIva = BigDecimal.ZERO;
		BigDecimal parametroSMLMV;                                             
		BigDecimal valorTarifa = BigDecimal.ZERO;
		BigDecimal cuantia = parametrosTarifasDTO.getCuantia();
		BigDecimal porcentajeArbitro = BigDecimal.ZERO;
		BigDecimal porcentajeCAC = BigDecimal.ZERO;
		BigDecimal porcentajeSecretario = BigDecimal.ZERO;
		BigDecimal bigDecimal100 = new BigDecimal(100);
		BigDecimal bigDecimal30 = new BigDecimal(30); // utilizado para el calculo de SMDLV
		BigDecimal valorArbitro = BigDecimal.ZERO;
		BigDecimal valorSecretario = BigDecimal.ZERO;
		BigDecimal valorCAC = BigDecimal.ZERO;
		BigDecimal bigDecimal2 = new BigDecimal(2);
		
		List<Persona> lstArbitrosPrincipales = null;
		RolPersonaCaso rolSecretario = null;
		if (parametrosTarifasDTO.getCantidadArbitros() == null) {
			lstArbitrosPrincipales = manejadorRolPersonaCaso
					.consultarArbitrosPrincipales(parametrosTarifasDTO.getIdCaso(), UtilDominios.ROL_PERSONA_ARBITRO);
			if(lstArbitrosPrincipales.isEmpty()){
				String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR231.toString());
				throw new SimascException(mensaje);
			}
			if (lstArbitrosPrincipales.size() == 1) {
				parametrosTarifasDTO.setCantidadArbitros(UtilDominios.CANTIDAD_ARBITROS_UNO);
			}
			if (lstArbitrosPrincipales.size() == 3) {
				parametrosTarifasDTO.setCantidadArbitros(UtilDominios.CANTIDAD_ARBITROS_TRES);
			}
			
		}
		if (parametrosTarifasDTO.getIdCaso() != null) {
			rolSecretario = manejadorRolPersonaCaso
					.consultarSecretarioDelCaso(parametrosTarifasDTO.getIdCaso());
			if(rolSecretario == null){
				String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR227.toString());
				throw new SimascException(mensaje);
			}
		}

		ParametrizacionTarifasDTO parametrizacion = parametrizacionTarifasFacade
				.consultarParametrizacion(parametrosTarifasDTO);
		List<DistribucionTarifaDTO> lstPorcentajesActor = distribucionTarifaFacade
				.consultarDistribucion(parametrosTarifasDTO);
		
		// Consulta el valor del salario mínimo y de la UVT para realizar el cálculo de la tarifa
		if(parametrizacion.getTipoValorRango().equals(UtilConstantes.SMLMV)){
			parametroSMLMV = new BigDecimal(
				parametrosGeneralesFacade.consultarPorCodigo(UtilConstantes.SMLMV).getValorNumerico());
		}else{
			parametroSMLMV = new BigDecimal(
				parametrosGeneralesFacade.consultarPorCodigo(UtilConstantes.UVT).getValorNumerico());
		}

		if (parametrizacion.getTipoValorMinimo().equals(UtilDominios.TIPO_VALOR_TARIFA_SMD)) {
			BigDecimal valorSMD = parametroSMLMV.divide(bigDecimal30, 2, RoundingMode.HALF_EVEN);			
			valorTarifa = valorSMD.multiply(parametrizacion.getValorMinimo());
		} else if (parametrizacion.getTipoValorMinimo().equals(UtilDominios.TIPO_VALOR_TARIFA_PORCENTAJE)) {
			valorTarifa = cuantia.multiply(parametrizacion.getValorMinimo().divide(bigDecimal100));
		} else if (parametrizacion.getTipoValorMinimo().equals(UtilDominios.TIPO_VALOR_TARIFA_UVT) ||
				parametrizacion.getTipoValorMinimo().equals(UtilDominios.TIPO_VALOR_TARIFA_SMM)) {
			valorTarifa = parametroSMLMV.multiply(parametrizacion.getValorMinimo());
		}
		
		for (DistribucionTarifaDTO distribucionTarifaDTO : lstPorcentajesActor) {
			if (distribucionTarifaDTO.getTipoActor().equals(UtilDominios.TIPO_ACTOR_CASO_ARBITRO)) {
				
				if(parametrosTarifasDTO.getIdServicio().equals(UtilConstantes.ID_SERVICIO_AMIGABLE_COMPOSICION)) {					
					BigDecimal cantidadArbitros = new BigDecimal(parametrosTarifasDTO.getCantidadArbitros());
					porcentajeArbitro = (distribucionTarifaDTO.getValorPorcentual().divide(cantidadArbitros, 2, RoundingMode.FLOOR)).divide(bigDecimal100);
				}else {
					porcentajeArbitro = distribucionTarifaDTO.getValorPorcentual().divide(bigDecimal100);
				}
								
				valorArbitro = validarMaximoSalario(UtilDominios.TIPO_ACTOR_CASO_ARBITRO,
						valorTarifa.multiply(porcentajeArbitro), parametroSMLMV, parametrizacion.getValorMaximo());
			}
			if (distribucionTarifaDTO.getTipoActor().equals(UtilDominios.TIPO_ACTOR_CASO_SECRETARIO)) {
				
				porcentajeSecretario = distribucionTarifaDTO.getValorPorcentual().divide(bigDecimal100);
				valorSecretario = valorTarifa.multiply(porcentajeSecretario);
			}
			if (distribucionTarifaDTO.getTipoActor().equals(UtilDominios.TIPO_ACTOR_CASO_CAC)) {
				
				porcentajeCAC = distribucionTarifaDTO.getValorPorcentual().divide(bigDecimal100);
				valorCAC = valorTarifa.multiply(porcentajeCAC);
			}
		}

		HonorariosFijadosDTO honorariosFijadosDTO = new HonorariosFijadosDTO();
		List<HonorariosActorDTO> lstHonorariosArbitros = null;
		if (lstArbitrosPrincipales != null && !lstArbitrosPrincipales.isEmpty()) {
			lstHonorariosArbitros = obtenerValoresArbitro(lstArbitrosPrincipales, null, valorArbitro, porcentajeArbitro);
			honorariosFijadosDTO.setValorArbitros(valorArbitro.multiply(new BigDecimal(lstArbitrosPrincipales.size())));
		} else {
			lstHonorariosArbitros = obtenerValoresArbitro(null, parametrosTarifasDTO.getCantidadArbitros(), valorArbitro, porcentajeArbitro);
			
			if(parametrosTarifasDTO.getIdServicio().equals(UtilConstantes.ID_SERVICIO_AMIGABLE_COMPOSICION)) {	
				honorariosFijadosDTO.setValorArbitros(valorArbitro.multiply(new BigDecimal(parametrosTarifasDTO.getCantidadArbitros())));
			}else {
				honorariosFijadosDTO.setValorArbitros(valorArbitro.multiply(new BigDecimal(
						parametrosTarifasDTO.getCantidadArbitros().equals(UtilDominios.CANTIDAD_ARBITROS_UNO) ? 1 : 3)));
			}
			
		}
		honorariosFijadosDTO.setHonorariosArbitros(lstHonorariosArbitros);
		HonorariosActorDTO actorSecretario = new HonorariosActorDTO();
		HonorariosActorDTO actorCAC = new HonorariosActorDTO();
				
		if(parametrosTarifasDTO.getIdServicio().equals(UtilConstantes.ID_SERVICIO_AMIGABLE_COMPOSICION)) {
			actorSecretario.setValor(valorSecretario);
			actorCAC.setValor(valorCAC);
		}else {			
			actorSecretario.setValor(lstHonorariosArbitros.get(0).getValor().divide(bigDecimal2));
			actorCAC.setValor(lstHonorariosArbitros.get(0).getValor().divide(bigDecimal2));
		}
		actorSecretario.setRol(UtilDominios.TIPO_ACTOR_CASO_SECRETARIO);
		actorSecretario.setActor(rolSecretario != null ? rolSecretario.getPersona().getNombreCompleto() : UtilConstantes.ACTOR_SECRETARIO);
		actorSecretario.setPorcentaje(porcentajeSecretario.multiply(bigDecimal100).toString());
		actorCAC.setRol(UtilDominios.TIPO_ACTOR_CASO_CAC);
		actorCAC.setActor(UtilDominios.TIPO_ACTOR_CASO_CAC);		
		actorCAC.setPorcentaje(porcentajeCAC.multiply(bigDecimal100).toString());
		honorariosFijadosDTO.setHonorariosSecretario(actorSecretario);
		honorariosFijadosDTO.setHonorariosCAC(actorCAC);
		valorTotal = actorCAC.getValor().add(actorSecretario.getValor()).add(honorariosFijadosDTO.getValorArbitros());
		valorTotalIva = valorTotal.multiply(BigDecimal.valueOf(iva).divide(bigDecimal100, 2, RoundingMode.HALF_UP));
		valorTotal = valorTotal.add(valorTotalIva);
		List<HonorariosFijadosDTO> lstHonorariosFijadosDTO = new ArrayList<>();
		honorariosFijadosDTO.setTotal(valorTotal);
		honorariosFijadosDTO.setTotalIva(valorTotalIva);
		lstHonorariosFijadosDTO.add(honorariosFijadosDTO);
		return lstHonorariosFijadosDTO;
	}
	
	public List<HonorariosActorDTO> obtenerValoresArbitro(List<Persona> lstArbitrosPrincipales, String cantidad, BigDecimal valor, BigDecimal porcentaje){
		List<HonorariosActorDTO> lstHonorariosArbitros = new ArrayList<>();
		if (cantidad != null) {
			int arbitros;
			if(cantidad.equals(UtilDominios.CANTIDAD_ARBITROS_UNO)) {	
				arbitros = 1;
			}else if(cantidad.equals(UtilDominios.CANTIDAD_ARBITROS_TRES)){
				arbitros = 3;
			}else {
				arbitros = Integer.parseInt(cantidad);
			}
			
			for (int i = 0; i < arbitros; i++) {
				HonorariosActorDTO actorArbitro = new HonorariosActorDTO();
				actorArbitro.setRol(UtilDominios.TIPO_ACTOR_CASO_ARBITRO);
				if (arbitros > 1)
					actorArbitro.setActor(UtilConstantes.ACTOR_ARBITRO.concat(" ").concat(String.valueOf(i+1)));
				else
					actorArbitro.setActor(UtilConstantes.ACTOR_ARBITRO);
				actorArbitro.setValor(valor);
				actorArbitro.setPorcentaje(porcentaje.multiply(new BigDecimal(100)).toString());
				lstHonorariosArbitros.add(actorArbitro);
			}
		} else {
			for (Persona persona : lstArbitrosPrincipales) {
				HonorariosActorDTO actorArbitro = new HonorariosActorDTO();
				actorArbitro.setRol(UtilDominios.TIPO_ACTOR_CASO_ARBITRO);
				actorArbitro.setActor(persona.getNombreCompleto());
				actorArbitro.setValor(valor);
				actorArbitro.setPorcentaje(porcentaje.multiply(new BigDecimal(100)).toString());
				lstHonorariosArbitros.add(actorArbitro);
			}
		}
		return lstHonorariosArbitros;
	}
	
	/**
	 * Método para determinar si el valor de los honorarios del actor supera el tope maximo
	 * @param actor
	 * @param valor
	 * @param salarioMinimo
	 * @return
	 */
	public BigDecimal validarMaximoSalario(String actor, BigDecimal valor, BigDecimal salarioMinimo, BigDecimal topeMaximo) {
		BigDecimal valorReal = BigDecimal.ZERO;
		if(!actor.equals(UtilDominios.TIPO_ACTOR_CASO_ARBITRO)){
			topeMaximo = topeMaximo.divide(new BigDecimal(2), 2, RoundingMode.HALF_UP);
		}
		if (valor.compareTo(topeMaximo.multiply(salarioMinimo)) > 0) {
			valorReal = topeMaximo.multiply(salarioMinimo);
		} else {
			valorReal = valor;
		}
		return valorReal;
	}

	/**
	 * Guarda las tarifas pare el caso
	 * 
	 * @param honorariosFijadosDTO
	 */
	@Override
	public void guardarCalculoTarifas(HonorariosFijadosDTO honorariosFijadosDTO) {

		HonorariosFijados honorarios = new HonorariosFijados();
		try {
			honorarios.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			honorarios.setFechaFijacion(honorariosFijadosDTO.getFechaFijacion());
			honorarios.setFechaLimiteDePago(diaFestivoFacade.adicionarDiasHabilesFecha(honorariosFijadosDTO.getFechaFijacion(), 10));
			honorarios.setFechaUltimaModificacion(new Date());
			honorarios.setIdCaso(honorariosFijadosDTO.getIdCaso());
			honorarios.setIdUsuarioModificacion(honorariosFijadosDTO.getIdUsuarioModificacion() != null
					? honorariosFijadosDTO.getIdUsuarioModificacion() : UtilConstantes.USUARIO_DEFECTO_SIMASC);
			honorarios.setMoneda(UtilDominios.TIPO_MONEDA_COP);
			if (UtilDominios.TIPO_TARIFA_INTERNACIONAL.equals(honorariosFijadosDTO.getTipoTarifa())
					| UtilDominios.TIPO_TARIFA_OTRO.equals(honorariosFijadosDTO.getTipoTarifa())
					| honorariosFijadosDTO.getHonorariosArbitros().size() > 3) {
				honorarios.setMoneda(honorariosFijadosDTO.getMoneda());
			}
			if (honorariosFijadosDTO.getHonorariosSecretario()==null) 
			{
				String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR227.toString());
				throw new SimascException(mensaje);
			}
			honorarios.setValorFijadoPretensiones(honorariosFijadosDTO.getTotal());
			HonorariosFijados honorariosFijados = new HonorariosFijados();
			
			
			/* Registra las tarifas fijadas */			
			honorariosFijados = manejadorHonorariosFijados.registrarTarifas(honorarios);
			
			/* Registra los honorarios para el actor Secretario */
			ValorHonorariosActor honorariosActorSec = new ValorHonorariosActor();
			
			
			honorariosActorSec.setHonorariosFijados(honorariosFijados);
			
			honorariosActorSec.setIdUsuarioModificacion(honorariosFijadosDTO.getIdUsuarioModificacion());
			
			
			honorariosActorSec.setValor(honorariosFijadosDTO.getHonorariosSecretario().getValor());
			
			ValorHonorariosActorPK valorHonorariosActorSec = new ValorHonorariosActorPK(
					honorariosFijados.getIdHonorariosFijados(),
					honorariosFijadosDTO.getHonorariosSecretario().getRol());
			
			honorariosActorSec.setValorHonorariosActorPK(valorHonorariosActorSec);
			honorariosActorSec = manejadorValorHonorariosActor.registrarHonorariosActor(honorariosActorSec);
			/* Registra los honorarios para el actor CAC */
			ValorHonorariosActor honorariosActorCac = new ValorHonorariosActor();
			honorariosActorCac.setHonorariosFijados(honorariosFijados);
			honorariosActorCac.setIdUsuarioModificacion(honorariosFijadosDTO.getIdUsuarioModificacion());
			honorariosActorCac.setValor(honorariosFijadosDTO.getHonorariosCAC().getValor());
			ValorHonorariosActorPK valorHonorariosActorCac = new ValorHonorariosActorPK(
					honorariosFijados.getIdHonorariosFijados(), honorariosFijadosDTO.getHonorariosCAC().getRol());
			honorariosActorCac.setValorHonorariosActorPK(valorHonorariosActorCac);
			honorariosActorCac = manejadorValorHonorariosActor.registrarHonorariosActor(honorariosActorCac);
			/* Registra los honorarios para el actor Arbitro */
			ValorHonorariosActor honorariosActorArbitro = new ValorHonorariosActor();
			for (HonorariosActorDTO honorariosActorArbitroDTO : honorariosFijadosDTO.getHonorariosArbitros()) {
				honorariosActorArbitro.setHonorariosFijados(honorariosFijados);
				honorariosActorArbitro.setIdUsuarioModificacion(honorariosFijadosDTO.getIdUsuarioModificacion());
				honorariosActorArbitro.setValor(honorariosActorArbitroDTO.getValor());
				ValorHonorariosActorPK valorHonorariosActorArb = new ValorHonorariosActorPK(
						honorariosFijados.getIdHonorariosFijados(), honorariosActorArbitroDTO.getRol());
				honorariosActorArbitro.setValorHonorariosActorPK(valorHonorariosActorArb);
			}
			honorariosActorArbitro = manejadorValorHonorariosActor.registrarHonorariosActor(honorariosActorArbitro);
		} catch (EntityExistsException e) {
			String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR226.toString());
			throw new SimascException(mensaje);
		}
	}

	// protected region metodos adicionales end

}
