package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumentoRadicado;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoRadicadoFacade;
import com.ccb.simasc.transversal.dto.DocumentoRadicadoDTO;
import com.ccb.simasc.transversal.entidades.DocumentoRadicado;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

@Stateless
@LocalBean
public class DocumentoRadicadoFacade extends AccesoFacade<DocumentoRadicado, Long, DocumentoRadicadoDTO> implements IDocumentoRadicadoFacade {	

	@EJB
	private ManejadorDocumentoRadicado manejadorDocumentoRadicado;
	
	@Inject
	private ApplicationRequestContext appContext;

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorDocumentoRadicado;
	}

	@Override
	public void guardarDocumentoRadicado(Long idDocumento) {
		DocumentoRadicado documentoRadicado = new DocumentoRadicado();
		String nombreUsuario  = (appContext != null && appContext.getContextoSesion() != null) ? 
				appContext.getContextoSesion().getNombreUsuario() : UtilConstantes.USUARIO_DEFECTO_SIMASC;
		
		documentoRadicado.setIdDocumento(idDocumento);
		documentoRadicado.setFechaRadicacion(new Date());
		documentoRadicado.setIdUsuarioModificacion(nombreUsuario);
		documentoRadicado.setFechaUltimaModificacion(new Date());
		documentoRadicado.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);	
		
		manejadorDocumentoRadicado.crear(documentoRadicado);
	}

	@Override
	public DocumentoRadicadoDTO transformarSinDependencias(DocumentoRadicado obj) {
		return null;
	}

	@Override
	public DocumentoRadicado transformarEntidadConDependencias(DocumentoRadicado obj) {
		return null;
	}

	@Override
	public DocumentoRadicado transformarEntidadSinDependencias(DocumentoRadicado obj) {
		return null;
	}

	@Override
	public DocumentoRadicadoDTO transformarConDependencias(DocumentoRadicado obj) {
		return null;
	}

}
 