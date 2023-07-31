package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Lista;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Lista.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorLista extends ManejadorCrud<Lista,Long>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@PersistenceContext
	private EntityManager em;
	// protected region atributos end
    
    public ManejadorLista() {
        super(Lista.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta sección sus modificaciones
    
    /**
     * Consulta una lista por nombre
     * @param nombre
     * @return Lista
     */
    public Lista consultarListaNombre( String nombre ){
    	Lista lista;
    	
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT l ");
		jpqlQuery.append("FROM Lista l ");
		jpqlQuery.append("WHERE l.estadoRegistro = : ").append(Lista.ENTIDAD_LISTA_ESTADO_REGISTRO);
		jpqlQuery.append(" AND l.nombre = : ").append(Lista.ENTIDAD_LISTA_NOMBRE);

		Query query = em.createQuery(jpqlQuery.toString());
		query.setParameter(Lista.ENTIDAD_LISTA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Lista.ENTIDAD_LISTA_NOMBRE, nombre );
		try{
			lista = (Lista) query.getSingleResult();
		} catch (NoResultException e) {
			lista = null;
		} catch (NonUniqueResultException e) {
			throw new SIMASCNegocioExcepcion(String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR565.toString())));
		} 
		
		return lista;
	}
    
    /** ADM-C-004
     * @param nombre: nombre de la lista
     * @return List<Lista>
     */
    public List<Lista> consultarListaParametros( String nombre ){
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT l ");
		jpqlQuery.append("FROM Lista l ");
		jpqlQuery.append("WHERE l.estadoRegistro = : ").append(Lista.ENTIDAD_LISTA_ESTADO_REGISTRO);
		if( nombre != null )
			jpqlQuery.append(" AND l.nombre = : ").append(Lista.ENTIDAD_LISTA_NOMBRE);
		jpqlQuery.append(" ORDER BY l.nombre");
		
		Query query = em.createQuery(jpqlQuery.toString());
		query.setParameter(Lista.ENTIDAD_LISTA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if( nombre != null )
			query.setParameter(Lista.ENTIDAD_LISTA_NOMBRE, nombre );
		return query.getResultList();
    }
    
    // protected region metodos adicionales end
        
}

