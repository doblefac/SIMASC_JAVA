package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.HistoricoExpedienteDTO;
import com.ccb.simasc.transversal.entidades.HistoricoExpediente;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import javax.ejb.Stateless;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad HistoricoExpediente.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorHistoricoExpediente extends ManejadorCrud<HistoricoExpediente,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorHistoricoExpediente() {
        super(HistoricoExpediente.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    @PersistenceContext
	private EntityManager em;
    
    public List<HistoricoExpedienteDTO> consultarHistoricoExpedientePorCaso(Long idCaso){
    	StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT h.id_caso AS idCaso, h.fecha as fecha, h.observaciones as observaciones,  ");
		nativeQuery.append(" case when h.id_persona is not null then  ");
		nativeQuery.append(" concat(rtrim(p.primer_nombre_o_razon_social), rtrim(' '+p.segundo_nombre), rtrim(' '+p.primer_apellido), rtrim(' '+p.segundo_apellido))   ");
		nativeQuery.append(" else 'Otros' end as nombreCompleto  ");
		nativeQuery.append(" FROM HISTORICO_EXPEDIENTE h ");
		nativeQuery.append(" INNER JOIN CASO c ON c.id_caso = h.id_caso ");
		nativeQuery.append(" LEFT JOIN PERSONA p ON p.id_persona = h.id_persona ");
		nativeQuery.append(" WHERE  h.estado_registro= ?1 ");
		nativeQuery.append(" AND h.id_caso= ?2 ");
		Query query = em.createNativeQuery(nativeQuery.toString(), HistoricoExpedienteDTO.class);
		
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idCaso);
		
		return query.getResultList();

    }
    // protected region metodos adicionales end
        
}

