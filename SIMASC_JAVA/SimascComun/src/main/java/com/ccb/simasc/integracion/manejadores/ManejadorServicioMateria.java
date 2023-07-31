package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Materia;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.entidades.ServicioMateria;
import com.ccb.simasc.transversal.entidades.ServicioMateriaPK;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad ServicioMateria.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorServicioMateria extends ManejadorCrud<ServicioMateria,ServicioMateriaPK>{

	// protected region atributos on begin
  	// Escriba en esta sección sus modificaciones
    @PersistenceContext
    private transient EntityManager em;
    // protected region atributos end
    
    public ManejadorServicioMateria() {
        super(ServicioMateria.class);
    }    
    
    // protected region metodos adicionales on begin
   	// Escriba en esta sección sus modificaciones
    /**
     * Consulta que trae todas las materias de un servicio determinado
     * @param servicio
     * @return
     */
    public List<ServicioMateria> consultarMateriasPorServicio(Servicio servicio){
    	List<Materia> materias = new ArrayList<>(); 
    	List<ServicioMateria> serviciosMateria = em.createQuery("SELECT sm FROM ServicioMateria sm WHERE sm.servicio.idServicio = :idServicio", ServicioMateria.class)
    			.setParameter("idServicio", servicio.getIdServicio())
    			.getResultList();
    	
    	for (ServicioMateria servicioMateria : serviciosMateria) {
			materias.add(servicioMateria.getMateria());
		}
    	return serviciosMateria;
    }
    // protected region metodos adicionales end
        
}

