package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.RolPersonaCasoPK;
import com.ccb.simasc.transversal.entidades.Ubicacion;
import com.ccb.simasc.transversal.entidades.UbicacionRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.UbicacionRolPersonaCasoPK;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad UbicacionRolPersonaCaso.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorUbicacionRolPersonaCaso extends ManejadorCrud<UbicacionRolPersonaCaso,UbicacionRolPersonaCasoPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorUbicacionRolPersonaCaso() {
        super(UbicacionRolPersonaCaso.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    /**
	 * Consulta la lista de Ubicaciones asociadas a un Rol Persona Caso
	 * @param rolPersonaCaso
	 * @return
	 */	
	@SuppressWarnings("unchecked")
	public List<Ubicacion> consultarUbicacionesPorRolPersonaCaso(RolPersonaCasoPK rolPersonaCaso) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT urpc.ubicacion FROM UbicacionRolPersonaCaso urpc");
		jpqlQuery.append(" WHERE urpc.rolPersonaCaso.rolPersonaCasoPK.idPersona = :");
		jpqlQuery.append(UtilConsultasSQL.obtenerNombreParametro(UbicacionRolPersonaCaso.ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_PERSONA));
		jpqlQuery.append(" AND urpc.rolPersonaCaso.rolPersonaCasoPK.idCaso = :");
		jpqlQuery.append(UtilConsultasSQL.obtenerNombreParametro(UbicacionRolPersonaCaso.ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_CASO));
		jpqlQuery.append(" AND urpc.rolPersonaCaso.rolPersonaCasoPK.idRol = :");
		jpqlQuery.append(UtilConsultasSQL.obtenerNombreParametro(UbicacionRolPersonaCaso.ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_ROL));
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(UtilConsultasSQL.obtenerNombreParametro(UbicacionRolPersonaCaso.ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_PERSONA), rolPersonaCaso.getIdPersona());
		query.setParameter(UtilConsultasSQL.obtenerNombreParametro(UbicacionRolPersonaCaso.ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_CASO), rolPersonaCaso.getIdCaso());
		query.setParameter(UtilConsultasSQL.obtenerNombreParametro(UbicacionRolPersonaCaso.ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_ROL), rolPersonaCaso.getIdRol());

		return query.getResultList();
	}
	
	/**
	 * Consulta la lista de Roles Persona Caso asociados a una Ubicacion
	 * @param idUbicacion
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RolPersonaCaso> consultarRolesPersonaCasoPorUbicacion(Long idUbicacion) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT urpc.rolPersonaCaso FROM UbicacionRolPersonaCaso urpc");
		jpqlQuery.append(" WHERE urpc.ubicacion.idUbicacion = :");
		jpqlQuery.append(UtilConsultasSQL.obtenerNombreParametro(UbicacionRolPersonaCaso.ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_UBICACION));
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(UtilConsultasSQL.obtenerNombreParametro(UbicacionRolPersonaCaso.ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_UBICACION), idUbicacion);
		
		return query.getResultList();
	}	
	
	@SuppressWarnings("unchecked")
	public List<UbicacionRolPersonaCaso> validarUbicacionRolPersonaCaso(Long idUbicacion,long idPersona, long idRol,long idCaso) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT * FROM UBICACION_ROL_PERSONA_CASO urpc ");
		jpqlQuery.append("WHERE urpc.id_ubicacion =?1 ");
		jpqlQuery.append(" AND urpc.id_rol=?2 ");
		jpqlQuery.append(" AND urpc.id_persona=?3 ");
		jpqlQuery.append(" AND urpc.id_caso=?4 ");
		Query query = mp.createNativeQuery(jpqlQuery.toString());
		query.setParameter(1, idUbicacion);
		query.setParameter(2, idRol);
		query.setParameter(3, idPersona);
		query.setParameter(4, idCaso);
		return query.getResultList();
	}	
	
    // protected region metodos adicionales end
        
}

