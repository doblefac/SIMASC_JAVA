package com.ccb.simasc.integracion.manejadores;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.PersonaSector;
import com.ccb.simasc.transversal.entidades.PersonaSectorPK;

@Stateless
public class ManejadorPersonaSector extends ManejadorCrud<PersonaSector, PersonaSectorPK> {

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	
	
	public ManejadorPersonaSector() {
		super(PersonaSector.class);
	}
	
	/**
	 * Método que obtiene los Sectores asosciados a un usuario dado su ID
	 * @param idUsuario 
	 */
	@SuppressWarnings("unchecked")
	public List<PersonaSector> obtenerSectoresByUsuario(Long idUsuario){
		List<PersonaSector> resultado = new ArrayList<PersonaSector> ();
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT p FROM PersonaSector p ");
		jpqlQuery.append(" WHERE p.personaSectorPk.idPersona =:idUsuario AND p.estadoRegistro =:estado ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter("idUsuario", idUsuario);
		query.setParameter("estado","ACT");
		resultado = query.getResultList();
		return resultado;
		
	}
	
	@SuppressWarnings("unchecked")
	public void agregarListaSectoresUsuario(List<PersonaSector> listaSectores, Long idPersona){
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" DELETE FROM PersonaSector p ");
		jpqlQuery.append(" WHERE p.personaSectorPk.idPersona =:idPersona AND p.estadoRegistro =:estado ");
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter("idPersona", idPersona);
		query.setParameter("estado","ACT");
		
		query.executeUpdate();
		
		for (PersonaSector obj : listaSectores){
			this.crear(obj);
		}
	}
	
}
