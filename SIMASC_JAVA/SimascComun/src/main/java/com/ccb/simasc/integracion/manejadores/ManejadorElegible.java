package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Elegible;
import com.ccb.simasc.transversal.entidades.ElegiblePK;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Elegible.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorElegible extends ManejadorCrud<Elegible,ElegiblePK>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
    
    public ManejadorElegible() {
        super(Elegible.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta sección sus modificaciones

    public List<String> consultarPersonasLiberadasPorSorteo(Long idSorteo) {	
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT CONCAT(p.primer_nombre_o_razon_social,' ',p.segundo_nombre,' ',p.primer_apellido,' ',p.segundo_apellido) as nombreCompleto ");
		nativeQuery.append("FROM ELEGIBLE e ");
		nativeQuery.append("INNER JOIN PERSONA p on e.id_persona =p.id_persona ");	
		nativeQuery.append("WHERE e.elegido_por_liberacion_lista = 1 ");
		nativeQuery.append("AND e.id_sorteo =  ?1 ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString());
		query.setParameter(1, idSorteo);		
    	return (List<String>) query.getResultList();
    }
    // protected region metodos adicionales end
        
}

