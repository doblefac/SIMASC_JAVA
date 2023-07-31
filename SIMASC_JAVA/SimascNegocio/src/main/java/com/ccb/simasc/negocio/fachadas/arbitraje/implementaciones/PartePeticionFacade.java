package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorPartePeticion;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPartePeticionFacade;
import com.ccb.simasc.transversal.dto.PartePeticionDTO;
import com.ccb.simasc.transversal.dto.RolPersonaCasoDTO;
import com.ccb.simasc.transversal.entidades.PartePeticion;
import com.ccb.simasc.transversal.entidades.PartePeticionPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link PartePeticion}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class PartePeticionFacade extends AccesoFacade<PartePeticion, PartePeticionPK, PartePeticionDTO> implements IPartePeticionFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorPartePeticion manejadorPartePeticion;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorPartePeticion;
	}

	@Override
	public PartePeticionDTO transformarSinDependencias(PartePeticion obj) {
		return new PartePeticionDTO().transformarSinDependencias(obj);
	}

	@Override
	public PartePeticionDTO transformarConDependencias(PartePeticion obj) {	
		return new PartePeticionDTO().transformarConDependencias(obj);
	}

	@Override
	public PartePeticion transformarEntidadConDependencias(PartePeticion obj) {
		return new PartePeticionDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public PartePeticion transformarEntidadSinDependencias(PartePeticion obj) {
		return  new PartePeticionDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	@Override
	public void crearPartePeticionPorListaPartes(List<RolPersonaCasoDTO> partes, Long idPeticion, String tipo){
		for (RolPersonaCasoDTO parteFor : partes) {
			PartePeticion partePeticion = new PartePeticion();
			partePeticion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			partePeticion.setFechaUltimaModificacion(new Date());
			PartePeticionPK partePeticionPK = new PartePeticionPK();
			partePeticionPK.setIdCaso(parteFor.getRolPersonaCasoPK().getIdCaso());
			partePeticionPK.setIdPersona(parteFor.getRolPersonaCasoPK().getIdPersona());
			partePeticionPK.setIdRol(parteFor.getRolPersonaCasoPK().getIdRol());
			partePeticionPK.setIdPeticion(idPeticion);
			partePeticionPK.setTipo(tipo);
			partePeticion.setPartePeticionPK(partePeticionPK);
			manejadorPartePeticion.crear(partePeticion);
		}		
		
	}

	// protected region metodos adicionales end
	
}
