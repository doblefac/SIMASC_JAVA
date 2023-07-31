package com.ccb.simasc.integracion.manejadores;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Cuaderno;

@Stateless
public class ManejadorCuaderno extends ManejadorCrud<Cuaderno,Long>{

	public ManejadorCuaderno() {
		super(Cuaderno.class);
	}

	public List<Cuaderno> obtenerCuadernos(){
		Query query = this.mp.createNamedQuery("Cuaderno.findAll");
		return query.getResultList();
	}
}
