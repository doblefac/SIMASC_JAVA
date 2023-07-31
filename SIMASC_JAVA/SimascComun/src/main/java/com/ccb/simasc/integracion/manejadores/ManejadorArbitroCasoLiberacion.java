package com.ccb.simasc.integracion.manejadores;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.ArbitroCasoLiberacion;
import com.ccb.simasc.transversal.entidades.ArbitroCasoLiberacionPK;
import com.ccb.simasc.transversal.entidades.Persona;

@Stateless
public class ManejadorArbitroCasoLiberacion extends ManejadorCrud<ArbitroCasoLiberacion, ArbitroCasoLiberacionPK>{

	 public ManejadorArbitroCasoLiberacion() {
	        super(ArbitroCasoLiberacion.class);
	    }    
	
	public ArbitroCasoLiberacion crearCasoLiberacion(ArbitroCasoLiberacion arbitroCasoLiberacion) {
		return (ArbitroCasoLiberacion) mp.updateObject(arbitroCasoLiberacion);

	}
	
	@SuppressWarnings("unchecked")
	public ArbitroCasoLiberacion buscarArbitroPorId(Long idPersona) {
		
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT a FROM ArbitroCasoLiberacion a ");
		jpqlQuery.append(" WHERE a.arbitroCasoLiberacionPK.idPersona =:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
	
		List<ArbitroCasoLiberacion> arbitroCasoLiberacionList = query.getResultList();
		
		if(arbitroCasoLiberacionList != null && !arbitroCasoLiberacionList.isEmpty()) {
			return arbitroCasoLiberacionList.get(0);
		}
		return null;
		
	}
	
	

} 
