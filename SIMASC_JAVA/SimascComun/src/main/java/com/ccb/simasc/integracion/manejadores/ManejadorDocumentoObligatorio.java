package com.ccb.simasc.integracion.manejadores;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.DocumentoObligatorio;

@Stateless
public class ManejadorDocumentoObligatorio extends ManejadorCrud<DocumentoObligatorio, Long> {

	@PersistenceContext
	private transient EntityManager em;
	
	
	public ManejadorDocumentoObligatorio() {
		super(DocumentoObligatorio.class);
		
	}
	
	public ManejadorDocumentoObligatorio(Class<DocumentoObligatorio> claseEntidad) {
		super(claseEntidad);
	}
	
	
	public List<DocumentoObligatorio> consultarDocumentoPorServicio(Long idServicio) {

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append(" SELECT do.* ");
		sbQuery.append(" FROM DOCUMENTO_OBLIGATORIO do ");
		sbQuery.append(" WHERE do.id_servicio  = ?1 ");
		sbQuery.append(" AND do.estado = 1 ");

		Query query = em.createNativeQuery(sbQuery.toString(), DocumentoObligatorio.class);
		query.setParameter(1, idServicio);
	
		 return query.getResultList();

	}

}
