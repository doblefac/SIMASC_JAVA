package com.ccb.simasc.negocio.arbitraje;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.transversal.entidades.Caso;

/**
*
* @author dpachon
*/
@Stateless
@LocalBean
public class CasoFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	ManejadorCaso manejadorCaso;
	
	public Caso getBusquedaCaso(long idCaso){		
		Caso caso = manejadorCaso.buscar(idCaso);	
		return caso;
	}
	
	public List<Caso> getCasos(){		
		List<Caso> casos = manejadorCaso.consultar(null, null, null);	
		return casos;
	}
	
	public void actualizarCasos(Caso caso) {
		manejadorCaso.actualizarCaso(caso);
	}
}
