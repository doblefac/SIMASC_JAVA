/**
 * 25/02/2019
 * @author jnmartinez
 */
package com.ccb.simasc.integracion.manejadores;
//protected region imports manejador on begin
//Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.cartas.LotesCartasDTO;
import com.ccb.simasc.transversal.entidades.PersonaLote;
import com.ccb.simasc.transversal.entidades.PersonaLotePK;

/**
 * @author jnmartinez
 *
 */
@Stateless
public class ManejadorPersonaLote extends ManejadorCrud<PersonaLote,PersonaLotePK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	/**
	 * 
	 */
	public ManejadorPersonaLote() {
		super(PersonaLote.class);
	}
	
	// protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
	
	
	public List<LotesCartasDTO> obtenerPersonasNoGeneracion(Long idLote) {
		StringBuilder sql = new StringBuilder();
		sql.append("select id_caso codigoCaso, id_persona idPersona, nombre_persona nombreParte, rol, correo, id_audiencia idAudiencia ");
		sql.append("from persona_lote where id_lote = ?1 ");
		
		Query query = getEntityManager().createNativeQuery(sql.toString(), LotesCartasDTO.class);
		query.setParameter(1, idLote);
		
		return query.getResultList();
	}
	
	// protected region metodos adicionales end
}
