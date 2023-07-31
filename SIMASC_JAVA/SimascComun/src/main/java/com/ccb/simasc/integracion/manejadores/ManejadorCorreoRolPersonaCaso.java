package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Acuse;
import com.ccb.simasc.transversal.entidades.CorreoRolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad CorreoRolPersonaCaso.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorCorreoRolPersonaCaso extends ManejadorCrud<CorreoRolPersonaCaso,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
	private transient EntityManager em;
	// protected region atributos end
    
    public ManejadorCorreoRolPersonaCaso() {
        super(CorreoRolPersonaCaso.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
	
	/**
	 * Metodo encargado de retornar el listado de correos rol persona caso por
	 * tipo de acuse. Con opcion de aplicar filtros por Caso, audiencia y
	 * persona
	 * 
	 * @param tipoAcuse
	 * @param idCaso
	 * @param idAudiencia
	 * @param idPersona
	 * @return List<CorreoRolPersonaCaso>
	 * @throws SimascException
	 */
    @SuppressWarnings("unchecked")
	public List<CorreoRolPersonaCaso> obtenerCorreos(String tipoAcuse, Long idCaso, Long idAudiencia, Long idPersona) throws SimascException{
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT crpc FROM CorreoRolPersonaCaso crpc ");
		jpqlQuery.append(" INNER JOIN crpc.acuseList ac  ");
		jpqlQuery.append(" LEFT JOIN crpc.llamadaList ll  ");
		
		jpqlQuery.append(" WHERE ac.acusePK.tipo =: ");
		jpqlQuery.append(Acuse.ENTIDAD_ACUSE_TIPO);
		jpqlQuery.append(" AND crpc.estadoRegistro =: ");
		jpqlQuery.append(CorreoRolPersonaCaso.ENTIDAD_CORREO_ROL_PERSONA_CASO_ESTADO_REGISTRO);
			
		if(tipoAcuse.equalsIgnoreCase(UtilDominios.TIPO_ACUSE_FALLA)){
			jpqlQuery.append(" AND crpc.gestionado =: ");
			jpqlQuery.append(CorreoRolPersonaCaso.ENTIDAD_CORREO_ROL_PERSONA_CASO_GESTIONADO);
		}
		
		if(idCaso != null){
			jpqlQuery.append(" AND crpc.idCasoReceptor =: ");
			jpqlQuery.append(CorreoRolPersonaCaso.ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_CASO_RECEPTOR);
		}
		
		if(idAudiencia != null){
			jpqlQuery.append(" AND crpc.idAudiencia =: ");
			jpqlQuery.append(CorreoRolPersonaCaso.ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_AUDIENCIA);
		}
		
		if(idPersona != null){
			jpqlQuery.append(" AND crpc.idPersonaReceptor =: ");
			jpqlQuery.append(CorreoRolPersonaCaso.ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_PERSONA_RECEPTOR);
		}

		Query query = em.createQuery(jpqlQuery.toString());
		query.setParameter(Acuse.ENTIDAD_ACUSE_TIPO, tipoAcuse);
		query.setParameter(CorreoRolPersonaCaso.ENTIDAD_CORREO_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if(tipoAcuse.equalsIgnoreCase(UtilDominios.TIPO_ACUSE_FALLA))
			query.setParameter(CorreoRolPersonaCaso.ENTIDAD_CORREO_ROL_PERSONA_CASO_GESTIONADO, false);
		if(idCaso != null)
			query.setParameter(CorreoRolPersonaCaso.ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_CASO_RECEPTOR, idCaso);
		if(idAudiencia != null)
			query.setParameter(CorreoRolPersonaCaso.ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_AUDIENCIA, idAudiencia);
		if(idPersona != null)
			query.setParameter(CorreoRolPersonaCaso.ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_PERSONA_RECEPTOR, idPersona);
		return query.getResultList();
	}
    
	/**
	 * Metodo que obtiene las personas que no han sido notificadas por medio de
	 * correo electronico. Solo se obtiene la primera incidencia que se
	 * encuentre.
	 * 
	 * @return
	 * @throws SimascException
	 */
    @SuppressWarnings("unchecked")
  	public List<CorreoRolPersonaCaso> obtenerCorreosFallaEnvio() throws SimascException{
    	List<CorreoRolPersonaCaso> correoRolPersonaCasos = new ArrayList<>();	
    	Query q = em.createNativeQuery("SELECT * FROM( "
    			+ "SELECT ROW_NUMBER() "
    			+ "OVER(PARTITION BY id_persona_receptor ORDER BY crpc.id_correo_rol_persona_caso) num, "
    			+ "crpc.* "
    			+ "FROM CORREO_ROL_PERSONA_CASO crpc "
    			+ "INNER JOIN acuse ac ON crpc.id_correo_rol_persona_caso = ac.id_correo_rol_persona_caso "
    			+ "WHERE ac.tipo = 'FER' "
    			+ "AND crpc.estado_registro = 'ACT' "
    			+ "AND crpc.id_audiencia IS NOT NULL "
    			+ "AND crpc.gestionado = 0 "
    			+ ") as ta  "
    			+ "WHERE ta.num = 1 ", CorreoRolPersonaCaso.class);
    	correoRolPersonaCasos =  q.getResultList();
    	return correoRolPersonaCasos;
  	}
    /**
     * Metodo que consulta los correos que se han enviado de un caso 
     * @param idCaso
     * @param certificado  
     * @return
     * @throws SimascException
     */
	public List<CorreoRolPersonaCaso> obtenerCorreosPorIdCaso(Long idCaso,Boolean certificado) throws SimascException{
		StringBuilder sql = new StringBuilder();
		sql.append(" select crpc.* from Correo_rol_persona_caso crpc");
		sql.append(" left join carta_persona cp on cp.id_carta_persona = crpc.id_carta_persona and cp.estado_registro = ?1 ");
		sql.append(" where crpc.estado_registro = ?1 ");
		sql.append(" and (crpc.id_caso_receptor = ?2 or cp.id_caso = ?2)");
		if(certificado != null && certificado){
			sql.append(" and crpc.asunto like ?3");
		}
		Query query = getEntityManager().createNativeQuery(sql.toString(), CorreoRolPersonaCaso.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idCaso);
		query.setParameter(3, UtilConstantes.INDICADOR_CORREO_CERTIFICADO);
		
		return query.getResultList();
	}
	
	public List<CorreoRolPersonaCaso> correosCitacionAudiencia(Long idCaso){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT cro.* FROM CORREO_ROL_PERSONA_CASO cro WHERE  id_rol_receptor IN ");
		nativeQuery.append(" (SELECT r.id_rol FROM ROL r WHERE r.nombre IN  ");
		nativeQuery.append(" (SELECT cd.codigo_clasificado FROM CLASIFICADOR_DOMINIO cd WHERE cd.codigo_clasificador = ?2 )) ");
		nativeQuery.append(" AND id_audiencia IN ");
		nativeQuery.append(" (SELECT a.id_audiencia FROM AUDIENCIA a WHERE a.id_caso = ?3 AND a.estado_registro = ?1 ) ");
		nativeQuery.append(" AND cro.estado_registro = ?1 ");
		Query query = em.createNativeQuery(nativeQuery.toString(), CorreoRolPersonaCaso.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.AGRUPADOR_ROL_PERSONA_PARTES_APLICACION);
		query.setParameter(3, idCaso);
		
		return query.getResultList();
	}
	
	public List<Long> consultaEnvioCorreoNotificacionAceptacionArbitrosAcuse(Long idCaso){
		
		List<String> rolesCaso = Arrays.asList(UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE, UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select distinct cp.id_persona from plantilla_carta pc ");
		nativeQuery.append(" inner join CARTA_PERSONA cp on pc.id_plantilla_carta= cp.id_plantilla_carta ");
		nativeQuery.append(" outer apply ( select crpc.id_correo_rol_persona_caso from CORREO_ROL_PERSONA_CASO crpc ");
		nativeQuery.append(" left join acuse a on crpc.id_correo_rol_persona_caso = a.id_correo_rol_persona_caso ");
		nativeQuery.append(" where cp.id_carta_persona=crpc.id_carta_persona ");
		nativeQuery.append(" and (a.id_correo_rol_persona_caso is null or a.tipo <> ?2 ) ");
		nativeQuery.append(" and cp.id_caso = crpc.id_caso_receptor ");
		nativeQuery.append(" and crpc.estado_registro=?1 group by crpc.id_correo_rol_persona_caso, fecha_envio  having max(fecha_envio) = fecha_envio ) as ultimoCorreoRol ");
		nativeQuery.append(" inner join ROL_PERSONA_CASO rpc on rpc.id_persona = cp.id_persona and rpc.id_caso=cp.id_caso ");
		nativeQuery.append(" inner join rol r on rpc.id_rol=r.id_rol and r.nombre ").append(UtilConsultasSQL.clausulaInSQLStrings(rolesCaso));
		nativeQuery.append(" where  cp.estado_registro=?1 ");
		nativeQuery.append(" and pc.nombre=?4 ");
		nativeQuery.append(" and cp.id_caso=?3 ");
		nativeQuery.append(" and rpc.estado_registro=?1 ");
		nativeQuery.append(" and r.estado_registro=?1 ");
		nativeQuery.append(" and (ultimoCorreoRol.id_correo_rol_persona_caso is not null or cp.estado_carta = ?5) ");
				
		Query query = em.createNativeQuery(nativeQuery.toString(), Long.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.TIPO_ACUSE_FALLA);
		query.setParameter(3, idCaso);
		query.setParameter(4, UtilDominios.NOMBRE_PLANTILLA_CARTA_NOTIFICACION_ACEPTACION_ARBITROS);
		query.setParameter(5, UtilDominios.ESTADO_CARTA_IMPRESA);
				
		return query.getResultList();
		
	}
    
    // protected region metodos adicionales end
        
}

