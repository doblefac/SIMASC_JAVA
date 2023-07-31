package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.ContratoConvenioDTO;
import com.ccb.simasc.transversal.entidades.ContratoConvenio;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad ContratoConvenio.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorContratoConvenio extends ManejadorCrud<ContratoConvenio,String>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorContratoConvenio() {
        super(ContratoConvenio.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    @PersistenceContext
	private EntityManager em;
    
    /**
	 * Método que consulta el numero de contratos por convenio
	 * @param idConvenio
	 * @return numeroAudiencias
	 */
	public Long consultarNumeroContratosPorConvenio( Long idConvenio){
		BigDecimal numeroContratos;
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT COUNT(*) ");
		nativeQuery.append(" FROM CONTRATO_CONVENIO c ");
	
		nativeQuery.append(" WHERE c.id_convenio = ?1");
		nativeQuery.append(" AND c.estado_registro = ?2 ");
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), BigDecimal.class);
		query.setParameter(1, idConvenio);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		try{
			numeroContratos =  (BigDecimal) query.getSingleResult();
		} catch (NoResultException e) {
			numeroContratos = null;
		}
		return numeroContratos != null ? numeroContratos.longValue() : null;
	}

	
	@SuppressWarnings("unchecked")
	public List<ContratoConvenio> consultarContratoConvenios(ContratoConvenioDTO convenio){
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT c FROM ContratoConvenio c ");
	
		jpqlQuery.append(" WHERE ");
		jpqlQuery.append("  c.estadoRegistro =:   ").append(ContratoConvenio.ENTIDAD_CONTRATO_CONVENIO_ESTADO_REGISTRO);
		if(convenio.getIdConvenio()!=null){
			jpqlQuery.append(" AND c.idConvenio =:").append(ContratoConvenio.ENTIDAD_CONTRATO_CONVENIO_ID_CONVENIO);
			
		}
		if(convenio.getCodigoContrato()!=null){
			jpqlQuery.append(" AND  c.codigoContrato =: ").append(ContratoConvenio.ENTIDAD_CONTRATO_CONVENIO_PK);
		}

		jpqlQuery.append(" order by c.fechaInicio desc ");
		
		Query query = em.createQuery(jpqlQuery.toString(), ContratoConvenio.class);
		
		query.setParameter(ContratoConvenio.ENTIDAD_CONTRATO_CONVENIO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if(convenio.getIdConvenio()!=null){
			query.setParameter(ContratoConvenio.ENTIDAD_CONTRATO_CONVENIO_ID_CONVENIO, convenio.getIdConvenio());			
		}
		if(convenio.getCodigoContrato()!=null){
			query.setParameter(ContratoConvenio.ENTIDAD_CONTRATO_CONVENIO_PK, convenio.getCodigoContrato());
			}
		

		return query.getResultList();
		
	}
    // protected region metodos adicionales end
        
}

