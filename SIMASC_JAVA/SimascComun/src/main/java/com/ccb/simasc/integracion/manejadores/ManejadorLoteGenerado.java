package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.LoteGenerado;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Agendamiento.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorLoteGenerado extends ManejadorCrud<LoteGenerado,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
	private transient EntityManager em;
	// protected region atributos end
    
    public ManejadorLoteGenerado() {
        super(LoteGenerado.class);
    }    
    
    // protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
    /**
     * Método que obtiene el primer lote generado por una persona
     * @param idPersona
     * @return
     */
    public LoteGenerado consularPrimerLoteGenerado(Long idPersona) {
    	StringBuilder sql = new StringBuilder();
    	sql.append("select top 1 * from lote_generado (nolock) where id_persona = ?1 order by fecha_generacion ");
    	
    	Query query = getEntityManager().createNativeQuery(sql.toString(), LoteGenerado.class);
    	query.setParameter(1, idPersona);
    	
    	LoteGenerado lote;
    	try {
    		lote = (LoteGenerado) query.getSingleResult();
    	} catch (NonUniqueResultException | NoResultException e) {
    		lote = null;
    	}
    	return lote;
    }
    
    public void borradoLoteGenerado(Long idLote) {
    	StringBuilder sql = new StringBuilder();
    	sql.append(" delete lote_generado where id_lote = ?1 ");
    	Query query = getEntityManager().createNativeQuery(sql.toString());
    	query.setParameter(1, idLote);
    	
    	query.executeUpdate();
    }
	
	// protected region metodos adicionales end
        
}

