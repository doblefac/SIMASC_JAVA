package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.LockModeType;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumentoObligatorio;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionOrdenamiento;
import com.ccb.simasc.integracion.manejadores.utilidades.RangoConsulta;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoObligatorioFacade;
import com.ccb.simasc.transversal.dto.DocumentoObligatorioDTO;
import com.ccb.simasc.transversal.entidades.DocumentoObligatorio;

@Stateless
@LocalBean
public class DocumentoObligatorioFacade extends AccesoFacade<DocumentoObligatorio, Long, DocumentoObligatorioDTO> implements IDocumentoObligatorioFacade{

	
	@EJB
	private ManejadorDocumentoObligatorio manejadorDocumentoObligatorio;
	
	
	@Override
	public DocumentoObligatorio buscar(Long id) {
		
		return null;
	}

	@Override
	public DocumentoObligatorio buscar(Long id, LockModeType lockMode) {
		
		return null;
	}

	@Override
	public DocumentoObligatorio crear() {
		
		return null;
	}

	@Override
	public void crear(DocumentoObligatorio obj) {
		
		
	}

	@Override
	public void actualizar(DocumentoObligatorio obj) {
		
		
	}

	@Override
	public void actualizarSinAtributosDeAuditoria(DocumentoObligatorio obj) {
		
		
	}

	@Override
	public void crearSinAtributosDeAuditoria(DocumentoObligatorio obj) {
		
		
	}

	@Override
	public void eliminar(DocumentoObligatorio obj) {
		
		
	}

	@Override
	public void eliminarPorId(Long id) {
		
		
	}

	@Override
	public Collection<DocumentoObligatorioDTO> obtenerTodos(Collection<DocumentoObligatorioDTO> empty,
			Boolean modoDependencias) {
		
		return null;
	}

	@Override
	public Collection<DocumentoObligatorio> obtenerEntidadesTodos(Collection<DocumentoObligatorio> empty,
			Boolean modoDependencias) {
		
		return null;
	}

	@Override
	public Collection<DocumentoObligatorioDTO> obtenerPorFiltro(Collection<InformacionFiltro> filtros,
			Collection<InformacionOrdenamiento> informacionOrdenamiento, RangoConsulta rangoConsulta,
			Collection<DocumentoObligatorioDTO> empty, Boolean modoDependencias) {
		
		return null;
	}

	@Override
	public Collection<DocumentoObligatorio> obtenerEntidadesPorFiltro(Collection<InformacionFiltro> filtros,
			Collection<InformacionOrdenamiento> informacionOrdenamiento, RangoConsulta rangoConsulta,
			Collection<DocumentoObligatorio> empty, Boolean modoDependencias) {
		
		return null;
	}

	@Override
	public IManejadorCrud getManejadorCrud() {
		
		return null;
	}

	@Override
	public DocumentoObligatorioDTO transformarSinDependencias(DocumentoObligatorio obj) {
		
		return null;
	}

	@Override
	public DocumentoObligatorio transformarEntidadConDependencias(DocumentoObligatorio obj) {
		
		return null;
	}

	@Override
	public DocumentoObligatorio transformarEntidadSinDependencias(DocumentoObligatorio obj) {
		
		return null;
	}

	@Override
	public DocumentoObligatorioDTO transformarConDependencias(DocumentoObligatorio obj) {
		
		return null;
	}

	@Override
	public Collection<DocumentoObligatorioDTO> transformarColeccionConDependencias(
			Collection<DocumentoObligatorio> coleccion, Collection<DocumentoObligatorioDTO> empty) {
		
		return null;
	}

	@Override
	public Collection<DocumentoObligatorioDTO> transformarColeccionSinDependencias(
			Collection<DocumentoObligatorio> coleccion, Collection<DocumentoObligatorioDTO> empty) {
		
		return null;
	}

	@Override
	public Collection<DocumentoObligatorio> transformarEntidadesColeccionConDependencias(
			Collection<DocumentoObligatorio> coleccion, Collection<DocumentoObligatorio> empty) {
		
		return null;
	}

	@Override
	public Collection<DocumentoObligatorio> transformarEntidadesColeccionSinDependencias(
			Collection<DocumentoObligatorio> coleccion, Collection<DocumentoObligatorio> empty) {
		
		return null;
	}

	@Override
	public List<DocumentoObligatorioDTO> consultarDocumentoObligatorioPorServicio(Long idServicio) {
		
		List<DocumentoObligatorio> list = manejadorDocumentoObligatorio.consultarDocumentoPorServicio(idServicio);
		if(list.isEmpty()) {
			return new ArrayList<DocumentoObligatorioDTO>();
		}
		
		return convertirLista(list);
	}

	private List<DocumentoObligatorioDTO> convertirLista(List<DocumentoObligatorio> list) {
		List<DocumentoObligatorioDTO> lista = new ArrayList<DocumentoObligatorioDTO>();
		for(DocumentoObligatorio enti : list) {
			lista.add(new DocumentoObligatorioDTO().transformarSinDependencias(enti));
		}
		
		return lista;
	}

}
