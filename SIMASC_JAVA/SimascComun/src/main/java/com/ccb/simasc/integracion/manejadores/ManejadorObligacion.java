package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.SeguimientoObligacionesDTO;
import com.ccb.simasc.transversal.entidades.Obligacion;
import com.ccb.simasc.transversal.entidades.ObligacionPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Obligacion.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorObligacion extends ManejadorCrud<Obligacion,ObligacionPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	
	@PersistenceContext
	private EntityManager em;

	// protected region atributos end
    
    public ManejadorObligacion() {
        super(Obligacion.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    @SuppressWarnings("unchecked")
	public List<SeguimientoObligacionesDTO> consultarObligacionesCaso(Long idCaso){
    	StringBuilder nativeQuery = new StringBuilder();
    	nativeQuery.append(" select	distinct o.tipo_obligacion as tipoObligacion, ");
    	nativeQuery.append(" isnull(o.valor_total_acuerdo,0) as valorTotalAcuerdo, ");
    	nativeQuery.append(" CONVERT(VARCHAR(MAX),o.compromiso) as compromiso, ");
    	nativeQuery.append(" o.fecha_compromiso as fecha, ");
    	nativeQuery.append(" isnull(o.direccion,'') as direccion, ");
    	nativeQuery.append(" o.observaciones, ");
    	nativeQuery.append(" o.id_resultado_audiencia idResultadoAudiencia, ");
    	nativeQuery.append(" isnull((select nombre from DOMINIO where codigo=o.forma_de_pago and dominio = ?1),'') as formaPago ");
    	nativeQuery.append(" from obligacion o ");
    	nativeQuery.append(" inner join	obligacion_parte op on op.id_resultado_audiencia=o.id_resultado_audiencia "); 
    	nativeQuery.append(" and o.estado_registro=op.estado_registro and o.tipo_obligacion=op.tipo_obligacion ");
    	nativeQuery.append(" where o.estado_registro=?2 ");
    	nativeQuery.append(" and op.id_caso=?3 ");
    	
    	Query query = em.createNativeQuery(nativeQuery.toString(),SeguimientoObligacionesDTO.class);
    	query.setParameter(1, UtilDominios.DOMINIO_FORMA_PAGO_OBLIGACION);
    	query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	query.setParameter(3, idCaso);
    	
    	return query.getResultList();
    }
    // protected region metodos adicionales end
        
}

