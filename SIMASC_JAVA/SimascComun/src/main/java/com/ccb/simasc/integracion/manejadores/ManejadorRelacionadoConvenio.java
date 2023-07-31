package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.RelacionadoConvenio;
import com.ccb.simasc.transversal.entidades.RelacionadoConvenioPK;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad RelacionadoConvenio.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorRelacionadoConvenio extends ManejadorCrud<RelacionadoConvenio, RelacionadoConvenioPK> {

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	// protected region atributos end

	public ManejadorRelacionadoConvenio() {
		super(RelacionadoConvenio.class);
	}

	// protected region metodos adicionales on begin

	/**
	 * Metodo que consulta los conciliadores relacionados por Convenio.
	 * 
	 * @author aperez.
	 * 
	 * @param idConvenio:
	 *            Identificador del convenio.
	 * @return List<PersonaBasicaDTO>: Lista de conciliadores.
	 */
	@SuppressWarnings("unchecked")
	public List<PersonaBasicaDTO> consultarConciliadoresRelacionadoConvenio(Long idConvenio, Long idSede, String estadoPersonaTipoServicio) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT DISTINCT p.id_persona AS idPersona, ");
		nativeQuery.append(
				"CONCAT(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ' , p.segundo_apellido ) AS nombreCompleto, ");
		nativeQuery.append("p.tipo_documento AS tipoDocumento, ");
		nativeQuery.append("p.numero_documento AS numeroDocumento ");
		nativeQuery.append("FROM  RELACIONADO_CONVENIO rc ");
		nativeQuery.append("INNER JOIN TIPO_DE_SERVICIO_ROL tdsr ");
		nativeQuery.append("ON tdsr.id_rol = rc.id_rol AND tdsr.tipo_servicio = ?1 ");
		nativeQuery.append("AND tdsr.estado_registro = ?2 ");
		
		nativeQuery.append("INNER JOIN PERSONA p ");
		nativeQuery.append("ON p.id_persona = rc.id_persona ");
		nativeQuery.append("AND p.estado_registro = ?2 ");
		
		nativeQuery.append("INNER JOIN ESTADO_PERSONA_ROL epr ");
		nativeQuery.append("ON p.id_persona = epr.id_persona AND epr.id_rol = ?6 ");
		nativeQuery.append("AND epr.id_motivo = ?4 AND epr.estado_registro = ?2 ");
		
		nativeQuery.append(" INNER JOIN ROL_PERSONA as rp on p.id_persona = rp.id_persona  and rp.id_rol = tdsr.id_rol ");
		nativeQuery.append(" AND (rp.fecha_fin_vigencia IS NULL OR rp.fecha_fin_vigencia >= CAST(GETDATE() as Date)) ");
		nativeQuery.append(" AND rp.estado_registro = ?2 ");
		
		if(idSede!=null){
			nativeQuery.append(" INNER JOIN PERSONA_SEDE ps ");
			nativeQuery.append(" on ps.id_persona = p.id_persona ");
			nativeQuery.append(" AND ps.estado_registro = ?2 ");
			
			nativeQuery.append(" INNER JOIN SEDE s ");
			nativeQuery.append(" ON s.id_centro = rp.id_centro ");
			nativeQuery.append(" AND s.id_sede = ps.id_sede ");
			nativeQuery.append(" AND s.estado_registro = ?2 ");
		}
		
		nativeQuery.append(" WHERE rc.id_convenio = ?3 ");
		nativeQuery.append(" AND rc.estado_registro = ?2 ");
		if(idSede!=null)
			nativeQuery.append(" and ps.id_sede=?5  ");
		nativeQuery.append(" ORDER BY nombreCompleto ");
		
		Query q = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), PersonaBasicaDTO.class);

		q.setParameter(1, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		q.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(3, idConvenio);
		q.setParameter(4, estadoPersonaTipoServicio);
		q.setParameter(6, UtilConstantes.ID_ROL_CONCILIADOR);
		if(idSede!=null)
			q.setParameter(5, idSede);
		
		return q.getResultList();

	}
	
	/**
	 * Método para inactivar todas las personas que se encuentran asociadas a un convenio con determinado rol
	 * @param idConvenio
	 * @param idRol
	 */
	public void inactivarRelacionadosPorRol(Long idConvenio, Long idRol) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("UPDATE RELACIONADO_CONVENIO SET ESTADO_REGISTRO = 'INA' WHERE ID_CONVENIO = ?1 AND ID_ROL = ?2 AND ESTADO_REGISTRO = ?3");
		
		Query q = mp.getEntityManager().createNativeQuery(nativeQuery.toString());
		q.setParameter(1, idConvenio);
		q.setParameter(2, idRol);
		q.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.executeUpdate();
	}

	// Escriba en esta seccion sus modificaciones

	/**
	 * Metodo para consultar las persona(s) asociadas a un convenio}
	 * @param idConvenio
	 * @param List<idRol>
	 * @return List<Personas> 
	 */
	
	public List<PersonaBasicaDTO> consultarPersonasRelacionadoConvenio(List<String> roles, Long idConvenio){
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select p.id_persona as idPersona ");
		nativeQuery.append(" , concat(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) as nombreCompleto ");
		nativeQuery.append(" FROM RELACIONADO_CONVENIO rc INNER JOIN PERSONA p ");
		nativeQuery.append(" ON p.id_persona=rc.id_persona ");
		nativeQuery.append(" inner join  rol ro on ro.id_rol = rc.id_rol ");
		nativeQuery.append(" and ro.nombre ").append(UtilConsultasSQL.clausulaInSQLStrings(roles));
		nativeQuery.append(" INNER JOIN rol_persona rp on rc.id_persona=rp.id_persona and rp.id_rol = ro.id_rol ");
		nativeQuery.append(" where p.estado_registro = ?1 ");
		nativeQuery.append(" and rc.estado_registro = ?1 ");
		nativeQuery.append(" and rp.estado_registro=?1 ");
		nativeQuery.append(" and ro.estado_registro=?1 ");						
		nativeQuery.append(" and rc.id_convenio = ?2 ");
				
	
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), PersonaBasicaDTO.class);

		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idConvenio);

		return query.getResultList();
	}
	
	// protected region metodos adicionales end

}
