package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaRolAlerta;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad PersonaRolAlerta.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorPersonaRolAlerta extends ManejadorCrud<PersonaRolAlerta,BigDecimal>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorPersonaRolAlerta() {
        super(PersonaRolAlerta.class);
    }    
    
    
	@PersistenceContext
	private transient EntityManager em;
	
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    // protected region metodos adicionales end
    
	@SuppressWarnings("unchecked")
	public PersonaRolAlerta obtenerPersonaRolAlertaId50(Long idPersona,Long idRol) {
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append(" select * ");
		sbQuery.append(" from PERSONA_ROL_ALERTA p ");
		sbQuery.append(" where p.id_persona = ?1 and p.id_rol = ?2 and p.id_alerta = ?3 ");

		Query query = em.createNativeQuery(sbQuery.toString(), Persona.class);

		query.setParameter(UtilConstantes.UNO, idPersona);
		query.setParameter(UtilConstantes.DOS, idRol);
		query.setParameter(UtilConstantes.TRES, 50);

		List<Object> lstPersona = query.getResultList();

		if (lstPersona == null || lstPersona.isEmpty()) {
			return null;
		}

		return (PersonaRolAlerta) lstPersona.get(UtilConstantes.CERO);
	}
	
	@SuppressWarnings("unchecked")
	public List<PersonaRolAlerta> obtenerListaPersonaRolAlertaByIdAlerta(Long idAlerta) {
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append(" select * ");
		sbQuery.append(" from PERSONA_ROL_ALERTA p ");
		sbQuery.append(" where p.id_alerta = ?1");

		Query query = em.createNativeQuery(sbQuery.toString(), PersonaRolAlerta.class);

		query.setParameter(UtilConstantes.UNO, idAlerta);

		return  query.getResultList();
	}
	
	
        
}

