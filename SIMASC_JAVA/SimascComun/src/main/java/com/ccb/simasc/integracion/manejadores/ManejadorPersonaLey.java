package com.ccb.simasc.integracion.manejadores;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.PersonaLey;
import com.ccb.simasc.transversal.entidades.PersonaLeyPK;

@Stateless
public class ManejadorPersonaLey extends ManejadorCrud<PersonaLey, PersonaLeyPK> {

	public ManejadorPersonaLey(){
		super(PersonaLey.class);
	}
	
	/**
	 * Método que obtiene los Sectores asosciados a un usuario dado su ID
	 * @param idUsuario 
	 */
	@SuppressWarnings("unchecked")
	public List<PersonaLey> obtenerLeyAplicableByUsuario(Long idUsuario){
		List<PersonaLey> resultado = new ArrayList<PersonaLey> ();
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT p FROM PersonaLey p ");
		jpqlQuery.append(" WHERE p.personaLeyPk.idPersona =:idUsuario AND p.estadoRegistro =:estado ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter("idUsuario", idUsuario);
		query.setParameter("estado","ACT");
		resultado = query.getResultList();
		return resultado;
		
	}
	
	public void agregarListaLeyAplicableUsuario(List<PersonaLey> listaLeyAplicable, Long idPersona){
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" DELETE FROM PersonaLey p ");
		jpqlQuery.append(" WHERE p.personaLeyPk.idPersona =:idPersona AND p.estadoRegistro =:estado ");
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter("idPersona", idPersona);
		query.setParameter("estado","ACT");
		
		query.executeUpdate();
		
		for (PersonaLey obj : listaLeyAplicable){
			this.crear(obj);
		}
	}
}
