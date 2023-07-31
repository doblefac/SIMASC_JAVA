package com.ccb.simasc.integracion.manejadores;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.CorrerTrasladoDocumentoDTO;
import com.ccb.simasc.transversal.entidades.CorrerTraslado;
import com.ccb.simasc.transversal.entidades.CorrerTrasladoPK;
@Stateless
public class ManejadorCorrerTraslado extends ManejadorCrud<CorrerTraslado,CorrerTrasladoPK>{

	@PersistenceContext
	private EntityManager em;
	
	public ManejadorCorrerTraslado() {
		super(CorrerTraslado.class);
	}
	
	public List<CorrerTrasladoDocumentoDTO> consultarTrasladosPorCaso(Long idCaso){
		StringBuilder nativeQuery = new StringBuilder();		

    	nativeQuery.append(" select distinct fecha_traslado as fechaTraslado, d.nombre as nombreDocumento, "); 
		nativeQuery.append(" (SELECT stuff((SELECT ', ' + CONCAT (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) ");
		nativeQuery.append(" FROM correr_traslado ctp WITH (nolock) INNER JOIN PERSONA pe WITH (nolock) ON ctp.id_persona=pe.id_persona  ");
		nativeQuery.append(" WHERE ctp.id_caso =ct.id_caso AND ctp.id_documento = ctp.id_documento FOR XML path('')), 1, 1, '')) AS partes  ");
		nativeQuery.append(" from correr_traslado ct ");
		nativeQuery.append(" inner join DOCUMENTO d on ct.id_documento = d.id_documento  ");
		nativeQuery.append(" where ct.id_caso = ?1 ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), CorrerTrasladoDocumentoDTO.class);
		query.setParameter(1, idCaso);
		return query.getResultList();
	}
}
