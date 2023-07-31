package com.ccb.simasc.integracion.manejadores;
//protected region imports manejador on begin
//Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.CorreoElectronicoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.CorreoElectronicoRolPersonaCasoPK;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.RolPersonaCasoPK;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

//protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad CorreoElectronicoRolPersonaCaso.
 * 
 * @author ftovar
 *
 */
@Stateless
public class ManejadorCorreoElectronicoRolPersonaCaso extends ManejadorCrud<CorreoElectronicoRolPersonaCaso,CorreoElectronicoRolPersonaCasoPK>  {
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	/**
	 * Constructor
	 * @param claseEntidad
	 */
	public ManejadorCorreoElectronicoRolPersonaCaso() {
        super(CorreoElectronicoRolPersonaCaso.class);
    }
	
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
	
	/**
	 * Consulta la lista de Correos asociados a un Rol Persona Caso
	 * @param rolPersonaCasoPK
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CorreoElectronico> consultarCorreosPorRolPersonaCaso(RolPersonaCasoPK rolPersonaCasoPK) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select c.* from correo_electronico_rol_persona_caso crpc" );
		sql.append(" inner join correo_electronico c on c.id_correo = crpc.id_correo and c.estado_registro = ?1 ");
		sql.append(" where crpc.estado_registro = ?1 ");
		sql.append(" and crpc.id_caso = ?2 and crpc.id_persona = ?3 ");
		if (rolPersonaCasoPK.getIdRol() != null) 
			sql.append(" and crpc.id_rol = ?4");

		Query query = getEntityManager().createNativeQuery(sql.toString(), CorreoElectronico.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, rolPersonaCasoPK.getIdCaso());
		query.setParameter(3, rolPersonaCasoPK.getIdPersona());

		if (rolPersonaCasoPK.getIdRol() != null)
			query.setParameter(4, rolPersonaCasoPK.getIdRol());

		return query.getResultList();
	}
	
	/**
	 * Consulta la lista de Roles Persona Caso asociados a un Correo
	 * @param idCorreo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RolPersonaCaso> consultarRolesPersonaCasoPorCorreo(Long idCorreo) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT crpc.rolPersonaCaso FROM CorreoElectronicoRolPersonaCaso crpc");
		jpqlQuery.append(" WHERE crpc.correoElectronico.idCorreo = :");
		jpqlQuery.append(UtilConsultasSQL.obtenerNombreParametro(CorreoElectronicoRolPersonaCaso.ENTIDAD_CORREO_ELECTRONICO_ROL_PERSONA_CASO_PK_ID_CORREO));
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(UtilConsultasSQL.obtenerNombreParametro(CorreoElectronicoRolPersonaCaso.ENTIDAD_CORREO_ELECTRONICO_ROL_PERSONA_CASO_PK_ID_CORREO), idCorreo);
		
		return query.getResultList();
	}
	
	/**
	 * Método que obtiene los correos principales de la persona junto con los correos asociados al caso
	 * @param pk
	 * @return
	 */
	public List<String> obtenerCorreosPersona(RolPersonaCasoPK pk) {
		StringBuilder sql = new StringBuilder();
		sql.append("select direccion from CORREO_ELECTRONICO where id_persona = ?1 ");
		sql.append(" and tipo = ?2 and estado_registro = ?3 ");
		sql.append(" union ");
		sql.append(" select c.direccion from CORREO_ELECTRONICO_ROL_PERSONA_CASO crpc ");
		sql.append(" inner join correo_electronico c on c.id_correo = crpc.id_correo and c.estado_registro = ?3 ");
		sql.append(" where crpc.estado_registro = ?3 and crpc.id_persona = ?1 ");
		sql.append(" and crpc.id_caso = ?4 ");
		if (pk.getIdRol() != null)
			sql.append(" and crpc.id_rol = ?5 ");
		
		Query query = getEntityManager().createNativeQuery(sql.toString(), String.class);
		query.setParameter(1, pk.getIdPersona());
		query.setParameter(2, UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(4, pk.getIdCaso());
		if (pk.getIdRol() != null)
			query.setParameter(5, pk.getIdRol());
		
		return query.getResultList();
	}
	
	// protected region metodos adicionales end
}
