package com.ccb.simasc.integracion.manejadores;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Trm;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

@Stateless
public class ManejadorTrm extends ManejadorCrud<Trm,Long>{

    @PersistenceContext
    private transient EntityManager em; 
    
    public ManejadorTrm() {
        super(Trm.class);
    }    
    
    @SuppressWarnings("unchecked")
    public List<Trm> consultarTodos(){
    	
    	StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT trm.* ");
		nativeQuery.append(" FROM TRM trm ");
		nativeQuery.append(" WHERE trm.estado_registro = ?1 ");
		
    	Query q = em.createNativeQuery(nativeQuery.toString(), Trm.class);
    	q.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	try {
    		return q.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }
    
    public Trm obtenerTrmActual(){
    	
    	StringBuilder nativeQuery = new StringBuilder();    	
		nativeQuery.append(" SELECT top 1 * ");
		nativeQuery.append(" FROM TRM ");
		nativeQuery.append(" WHERE CAST(fecha AS Date) <= CAST(GETDATE() AS Date) ");
		nativeQuery.append(" ORDER BY fecha DESC ");
		
    	Query q = em.createNativeQuery(nativeQuery.toString(), Trm.class);
    	q.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	try {
    		return (Trm) q.getSingleResult();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }
}

