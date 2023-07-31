package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Notificacion;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Notificacion.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorNotificacion extends ManejadorCrud<Notificacion,Long>{

    @PersistenceContext
	private transient EntityManager em;
    
    public ManejadorNotificacion() {
        super(Notificacion.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    public void crearNotificacionAlerta(Notificacion notificacion){
        StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append("INSERT INTO NOTIFICACION ");
        nativeQuery.append("( estado, estado_registro, fecha_notificacion, ");
        nativeQuery.append("fecha_ultima_modificacion, id_alerta, id_persona, ");
        nativeQuery.append("id_usuario_modificacion, texto_alerta) values ( ");
		nativeQuery.append(" ?1 , ?2 ,?3 , ?4, ?5, ?6, ?7, ?8 ) ");

		Query query = em.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, notificacion.getEstado());
		query.setParameter(2, notificacion.getEstadoRegistro());
		query.setParameter(3, notificacion.getFechaNotificacion());
		query.setParameter(4, notificacion.getFechaUltimaModificacion());
		query.setParameter(5, notificacion.getIdAlerta());
        query.setParameter(6, notificacion.getIdPersona());
        query.setParameter(7, notificacion.getIdUsuarioModificacion());
        query.setParameter(8, notificacion.getTextoAlerta());
		query.executeUpdate();
    }
    // protected region metodos adicionales end
        
}

