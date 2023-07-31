package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorEntregaDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorEvento;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEntregaDocumentoFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.implementacion.DominioFacade;
import com.ccb.simasc.transversal.dto.DocumentosEntregadosDTO;
import com.ccb.simasc.transversal.dto.EntregaDocumentoDTO;
import com.ccb.simasc.transversal.entidades.EntregaDocumento;
import com.ccb.simasc.transversal.entidades.EntregaDocumentoPK;
import com.ccb.simasc.transversal.entidades.Evento;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link EntregaDocumento}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class EntregaDocumentoFacade extends AccesoFacade<EntregaDocumento, EntregaDocumentoPK, EntregaDocumentoDTO> implements IEntregaDocumentoFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorEntregaDocumento manejadorEntregaDocumento;
	
	@EJB
	private DominioFacade dominioFacade;
	
	@EJB
	private ManejadorEvento manejadorEvento;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorEntregaDocumento;
	}

	@Override
	public EntregaDocumentoDTO transformarSinDependencias(EntregaDocumento obj) {
		EntregaDocumentoDTO dto = new EntregaDocumentoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public EntregaDocumentoDTO transformarConDependencias(EntregaDocumento obj) {
		EntregaDocumentoDTO dto = new EntregaDocumentoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public EntregaDocumento transformarEntidadConDependencias(EntregaDocumento obj) {
		EntregaDocumento dto = new EntregaDocumento();
		dto = new EntregaDocumentoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public EntregaDocumento transformarEntidadSinDependencias(EntregaDocumento obj) {
		EntregaDocumento dto = new EntregaDocumento();
		dto = new EntregaDocumentoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	@Override
	public List<DocumentosEntregadosDTO> consultarDocumentosEntregadosPartesCaso( Long idCaso ){
		return manejadorEntregaDocumento.consultarDocumentosEntregadosPartesCaso(idCaso);
	}
	
	@Override
	public void entregarDocumentos( List<DocumentosEntregadosDTO> documentosAEntregar, Long idCaso ){
		List<EntregaDocumento> entregasDocumento = new ArrayList<EntregaDocumento>();
		List<Evento> eventosEntregaDocumento = new ArrayList<Evento>();
		
		for (DocumentosEntregadosDTO documentoAEntregar: documentosAEntregar ){
			EntregaDocumentoPK entregaDocumentoPK = new EntregaDocumentoPK();
			EntregaDocumento entregaDocumento = new EntregaDocumento();
			
			entregaDocumentoPK.setIdDocumento(documentoAEntregar.getIdDocumento());
			entregaDocumentoPK.setIdPersona(documentoAEntregar.getIdPersona());
			entregaDocumento.setEntregaDocumentoPK(entregaDocumentoPK);
			entregaDocumento.setFechaEntrega(new Date());
			entregaDocumento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			entregasDocumento.add(entregaDocumento);
			
			Evento eventoEntregaDocumento = new Evento();
			String nombreTipoDocumento = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_DOCUMENTO_DIG, documentoAEntregar.getTipoDocumento());
			List<String> argsTexto = new ArrayList<String>();
			argsTexto.add( nombreTipoDocumento );
			argsTexto.add( documentoAEntregar.getNombreParte() );
			String detalleEvento = (String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO310.toString()), argsTexto.toArray()));
			
			eventoEntregaDocumento.setTipoEvento(UtilDominios.TIPO_EVENTO_ENTREGA_DOCUMENTO);
			eventoEntregaDocumento.setFechaEvento(new Date());
			eventoEntregaDocumento.setObservaciones(detalleEvento);
			eventoEntregaDocumento.setIdCaso(idCaso);
			eventoEntregaDocumento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			eventosEntregaDocumento.add(eventoEntregaDocumento);
		}
		manejadorEntregaDocumento.crearLista(entregasDocumento);
		manejadorEvento.crearLista(eventosEntregaDocumento);
	}
	
	// protected region metodos adicionales end
	
}
