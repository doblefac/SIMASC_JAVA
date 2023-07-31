package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IIntegracionSWFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorDetallePagoCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDominio;
import com.ccb.simasc.integracion.manejadores.ManejadorPagoCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorServicio;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDetallePagoCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPagoCasoFacade;
import com.ccb.simasc.transversal.dto.DetallePagoCasoDTO;
import com.ccb.simasc.transversal.dto.InfoReliquidacionPagoCaso;
import com.ccb.simasc.transversal.dto.NotificacionPagoDTO;
import com.ccb.simasc.transversal.dto.PagoCasoDTO;
import com.ccb.simasc.transversal.dto.ReliquidarPagoCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioGenerarPagoPup;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.DetallePagoCaso;
import com.ccb.simasc.transversal.entidades.DetallePagoCasoPK;
import com.ccb.simasc.transversal.entidades.PagoCaso;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link PagoCaso}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class PagoCasoFacade extends AccesoFacade<PagoCaso, String, PagoCasoDTO> implements IPagoCasoFacade {

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	@EJB
	private ManejadorDominio manejadorDominio;

	@EJB
	private ManejadorPagoCaso manejadorPagoCaso;

	@EJB
	private ManejadorServicio manejadorServicio;

	@EJB
	private ICasoFacade casoFacade;
	
	@EJB
    private IIntegracionSWFacade integracionSWFacade;
	
	@EJB
	private IDetallePagoCasoFacade detallePagoCasoFacade;
	
	@EJB
	private ManejadorDetallePagoCaso manejadorDetallePagoCaso;

	// protected region atributos end

	@SuppressWarnings("rawtypes")
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorPagoCaso;
	}

	@Override
	public PagoCasoDTO transformarSinDependencias(PagoCaso obj) {
		PagoCasoDTO dto = new PagoCasoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public PagoCasoDTO transformarConDependencias(PagoCaso obj) {
		PagoCasoDTO dto = new PagoCasoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public PagoCaso transformarEntidadConDependencias(PagoCaso obj) {
		PagoCaso dto = new PagoCaso();
		dto = new PagoCasoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public PagoCaso transformarEntidadSinDependencias(PagoCaso obj) {
		PagoCaso dto = new PagoCaso();
		dto = new PagoCasoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	public PagoCaso obtenerPagoSolicitud(String numeroRecibo) {
		// List<DetallePagoCaso> detallesPagoCaso =
		// manejadorDetallePagoCaso.consultar(null, null, null);
		PagoCaso pagoCaso = manejadorPagoCaso.buscar(numeroRecibo);
		// List<DetallePagoCaso> listaDetallesPagoCaso = new ArrayList<>();
		// for(DetallePagoCaso detallePago : detallesPagoCaso){
		// if(detallePago.getDetallePagoCasoPK().getNumeroRecibo().equals(numeroRecibo)){
		// Dominio dominio = new Dominio();
		// DominioPK dominioPk = new DominioPK();
		// dominioPk.setCodigo(detallePago.getServicioCaja());
		// dominioPk.setDominio(UtilDominios.DOMINIO_TIPO_SERVICIO_CAJA);
		// detallePago.setServicioCaja(manejadorDominio.buscar(dominioPk).getNombre());
		// listaDetallesPagoCaso.add(detallePago);
		// }
		// }
		// pagoCaso.setDetallePagoCasoList(listaDetallesPagoCaso);

		return pagoCaso;
	}

	@SuppressWarnings("unused")
	@Override
	public Collection<PagoCaso> obtenerPagosCasosPorEstado(String estadoPago) {

		PagoCasoDTO dto = new PagoCasoDTO();
		List<PagoCaso> pagos = new ArrayList<>();
		return transformarEntidadesColeccionConDependencias(manejadorPagoCaso.obtenerPagosCasosPorEstado(estadoPago),
				pagos);
	}

	@Override
	public PagoCaso obtenerPagosPorNumeroRecibo(String numRecibo) {
		PagoCaso pago = manejadorPagoCaso.buscar(numRecibo);
		if (pago != null) {
			pago.setEstado(UtilDominios.ESTADOS_PAGO_CASO_CASO_ASOCIADO);
		}
		return pago;
	}

	@Override
	public NotificacionPagoDTO crearPagoCaso(PagoCasoDTO pagoCasoDTO, String realPath) {
		Caso caso = casoFacade.creacionCasoTramiteOrdinario(Long.valueOf(pagoCasoDTO.getIdOrdenDePago()),
				pagoCasoDTO.getIdUsuarioModificacion(), pagoCasoDTO, realPath);

		PagoCaso pagoCaso = new PagoCaso();
		pagoCaso.setNumeroRecibo(pagoCasoDTO.getNumeroRecibo());
		pagoCaso.setValor(pagoCasoDTO.getValor());
		pagoCaso.setFechaPago(new Date());
		pagoCaso.setNombrePersona(pagoCasoDTO.getNombrePersona());
		pagoCaso.setTipoDeDocumento(pagoCasoDTO.getTipoDeDocumento());
		pagoCaso.setNumeroDeDocumento(pagoCasoDTO.getNumeroDeDocumento());
		pagoCaso.setIdCaso(caso.getIdCaso());
		pagoCaso.setIdServicio(caso.getIdServicio());
		pagoCaso.setIdSede(pagoCasoDTO.getIdSede());
		pagoCaso.setIdUsuarioModificacion(pagoCasoDTO.getIdUsuarioModificacion());
		pagoCaso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		pagoCaso.setFechaUltimaModificacion(new Date());
		pagoCaso.setEstado(pagoCasoDTO.getEstado());
		pagoCaso.setDescripcion(pagoCasoDTO.getDescripcion());
		crear(pagoCaso);

		creaDetallePagoCaso(pagoCaso, pagoCasoDTO);
		
		NotificacionPagoDTO pago = new NotificacionPagoDTO();
		pago.setCaso(caso);
		pago.setPagoCaso(pagoCaso);
		
		return pago;
	}

	@Override
	public List<ReliquidarPagoCasoDTO> listarPagoCaso(long idCaso) {
		if (Long.valueOf(idCaso) != null)
			return manejadorPagoCaso.listarPagoCaso(idCaso);
		else
			return null;
	}

	@Override
	public List<InfoReliquidacionPagoCaso> listarReliquidacionPagoCaso(long idCaso) {
		if (Long.valueOf(idCaso) != null)
			return manejadorPagoCaso.listarReliquidacionPagoCaso(idCaso);
		else
			return null;
	}
	
	@Override
	public String generarOrdenPago(FormularioGenerarPagoPup datosOrden) {
		return integracionSWFacade.generarOrdenDePagoPup(datosOrden);
	}
	
	@Override
	public PagoCaso obtenerPagoCasoPorCaso(long idCaso){
		List<PagoCaso> pagos = manejadorPagoCaso.obtenerUltimoPagoCaso(idCaso);
		if(pagos != null && !pagos.isEmpty()){
			return pagos.get(0);
		}
		return null;
	}
	
	@Override
	public void creaDetallePagoCaso(PagoCaso pagoCaso, PagoCasoDTO pagoCasoDTO) {
		for (DetallePagoCasoDTO detallePago : pagoCasoDTO.getDetallePagoCasoList()) {
			DetallePagoCaso detallePagoCaso = new DetallePagoCaso();
			DetallePagoCasoPK detallePagoCasoPK = new DetallePagoCasoPK();
			detallePagoCaso.setPagoCaso(pagoCaso);
			detallePagoCaso.setIdUsuarioModificacion(pagoCasoDTO.getIdUsuarioModificacion());
			detallePagoCaso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			detallePagoCaso.setFechaUltimaModificacion(new Date());
			detallePagoCaso.setServicioCaja(detallePago.getServicioCaja());
			detallePagoCaso.setValor(detallePago.getValor());
			detallePagoCasoPK.setCodigoItem(detallePago.getDetallePagoCasoPK().getCodigoItem());
			detallePagoCasoPK.setNumeroRecibo(pagoCasoDTO.getNumeroRecibo());
			detallePagoCaso.setDetallePagoCasoPK(detallePagoCasoPK);
			manejadorDetallePagoCaso.crear(detallePagoCaso);
		}
	}

	// protected region metodos adicionales end

}
