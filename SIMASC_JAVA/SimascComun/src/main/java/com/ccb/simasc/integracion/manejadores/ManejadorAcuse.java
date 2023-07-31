package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Acuse;
import com.ccb.simasc.transversal.entidades.AcusePK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Acuse.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorAcuse extends ManejadorCrud<Acuse,AcusePK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorAcuse() {
        super(Acuse.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
	/**
	 * Método encargado de obtener el acuse de un CorreoRolPersonaCaso por tipo
	 * 
	 * @param idCorreoRolPersonaCaso
	 * @param tipo
	 * @return
	 */
	public Acuse obtenerAcuseCorreoRolPersonaCaso(Long idCorreoRolPersonaCaso, String tipo) {
		StringBuilder jpqlQuery = new StringBuilder();

		jpqlQuery.append("SELECT a FROM Acuse a ");
		jpqlQuery.append(" WHERE a.acusePK.idCorreoRolPersonaCaso = :idCorreoRolPersonaCaso ");
		jpqlQuery.append("   AND a.acusePK.tipo = :tipo ");
		jpqlQuery.append("   AND a.estadoRegistro = :");
		jpqlQuery.append(Acuse.ENTIDAD_ACUSE_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter("idCorreoRolPersonaCaso", idCorreoRolPersonaCaso);
		query.setParameter("tipo", tipo);
		query.setParameter(Acuse.ENTIDAD_ACUSE_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return (Acuse) query.getSingleResult();
	}
	
	public Object[] obtenerAcusesCorreo(Long idCorreoRolPersonaCaso) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select (select 1 from acuse where id_correo_rol_persona_caso = ?1 and tipo = ?3 and estado_registro = ?2)");
		sql.append(" , (select 1 from acuse where id_correo_rol_persona_caso = ?1 and tipo = ?4 and estado_registro = ?2)");
		sql.append(" , (select 1 from acuse where id_correo_rol_persona_caso = ?1 and tipo = ?5 and estado_registro = ?2)");
		sql.append(" , (select 1 from acuse where id_correo_rol_persona_caso = ?1 and tipo = ?6 and estado_registro = ?2)");
		
		Query query = getEntityManager().createNativeQuery(sql.toString());
		query.setParameter(1, idCorreoRolPersonaCaso);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(3, UtilDominios.TIPO_ACUSE_ENVIO);
		query.setParameter(4, UtilDominios.TIPO_ACUSE_RECIBIDO);
		query.setParameter(5, UtilDominios.TIPO_ACUSE_LECTURA);
		query.setParameter(6, UtilDominios.TIPO_ACUSE_FALLA);
		
		return (Object[]) query.getSingleResult();
	}															   

    // protected region metodos adicionales end
        
}

