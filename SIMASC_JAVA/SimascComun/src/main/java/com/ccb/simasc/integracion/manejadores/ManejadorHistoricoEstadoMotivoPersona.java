package com.ccb.simasc.integracion.manejadores;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.HistoricoEstadoMotivoPersonaDTO;
import com.ccb.simasc.transversal.entidades.HistoricoEstadoMotivoPersona;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

//protected region imports manejador end

/**
* Manejador que define las operaciones CRUD y de negocio a realizar sobre
* la tabla correspondiente a la entidad HistoricoEstadoMotivoPersona.
* 
* @author daristizabal
*/
@Stateless
public class ManejadorHistoricoEstadoMotivoPersona extends ManejadorCrud<HistoricoEstadoMotivoPersona,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorHistoricoEstadoMotivoPersona() {
        super(HistoricoEstadoMotivoPersona.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    @PersistenceContext
	private EntityManager em;
	
    /**
     * crear el historico estado persona servicio 
     * @param idPersona  
     * @param idRol
     * @param idMotivo
     * @param fecha  si esta fecha es nula se coloca la fecha actual
     */
    public void crearHistoricoEstadoMotivoPersona(Long idPersona, Long idRol, String idMotivo, Date fecha, String estadoParaSorteo, Long idMateria, String motivoEstadoSorteo){
    	HistoricoEstadoMotivoPersona historicoPersona = new HistoricoEstadoMotivoPersona();
    	historicoPersona.setIdPersona(idPersona);
    	historicoPersona.setIdRol(idRol);
    	historicoPersona.setIdMotivo(idMotivo);   
    	historicoPersona.setEstadoParaSorteo(estadoParaSorteo);
    	historicoPersona.setIdMateria(idMateria);   
    	historicoPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	historicoPersona.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
    	historicoPersona.setFechaUltimaModificacion(new Date());
    	historicoPersona.setMotivoEstadoSorteo(motivoEstadoSorteo);
    	if(fecha != null){
    		historicoPersona.setFecha(fecha);
    	}else{
    		historicoPersona.setFecha(new Date());
    	}
    	crear(historicoPersona);
    }
    
    /**
     * ADM-C-022
     * Consulta el historial de estados de la persona que se pasa como parámetro

     * @param idPersona
     * @return
     */
    public List<HistoricoEstadoMotivoPersonaDTO> consultarHistoricoPersona(Long idPersona){
    	StringBuilder nativeQuery = new StringBuilder();
    	List<HistoricoEstadoMotivoPersonaDTO> historicos = null ;
    	nativeQuery.append("SELECT     id_persona as idPersona, ");
    	nativeQuery.append("r.nombre as rol, ");
    	nativeQuery.append("CASE emd.estado ");
    	nativeQuery.append(" WHEN '" + UtilDominios.DOMINIO_ESTADO_NO_SORTEABLE + "' ");
    	nativeQuery.append(" THEN ");
    	nativeQuery.append(" CASE hem.id_motivo ");
    	nativeQuery.append(" WHEN '"+ UtilDominios.ESTADO_ARBITROS_HABILITADO +"' ");
    	nativeQuery.append(" THEN dme.nombre ");
    	nativeQuery.append(" ELSE do.nombre ");
    	nativeQuery.append(" END ");
    	nativeQuery.append(" ELSE '' ");
    	nativeQuery.append(" END AS idMotivo, ");
    	nativeQuery.append(" de.nombre AS estado, ");
    	nativeQuery.append(" m.nombre  AS materia, ");
    	nativeQuery.append(" ser.nombre  AS servicio, ");
    	nativeQuery.append(" hem.id_usuario_modificacion as idUsuarioModificacion , ");
    	nativeQuery.append(" hem.fecha_ultima_modificacion as fechaUltimaModificacion, ");
    	nativeQuery.append(" hem.estado_registro as estadoRegistro ");
    	nativeQuery.append("FROM HISTORICO_ESTADO_MOTIVO_PERSONA hem ");
    	nativeQuery.append("INNER JOIN ROL r ");
    	nativeQuery.append("ON   r.id_rol= hem.id_rol ");
    	nativeQuery.append(" AND (r.tipo_servicio in (?6, ?10) or r.id_rol =?2) ");
    	nativeQuery.append("INNER JOIN MATERIA m ");
    	nativeQuery.append("ON hem.id_materia = m.id_materia ");
    	nativeQuery.append("INNER JOIN ESTADO_MOTIVO_DISPONIBILIDAD emd ");
    	nativeQuery.append("ON hem.estado_para_sorteo = emd.estado_para_sorteo ");
    	nativeQuery.append("AND  hem.id_motivo = emd.id_motivo_estado ");
    	nativeQuery.append("INNER JOIN dominio de ");
    	nativeQuery.append("ON emd.estado = codigo ");
    	nativeQuery.append("AND dominio  in (?3,?4) ");
    	nativeQuery.append("LEFT JOIN dominio do ");
    	nativeQuery.append("ON hem.id_motivo = do.codigo ");
    	nativeQuery.append("AND do.dominio= ?8 ");
    	nativeQuery.append("LEFT JOIN dominio dme ");
    	nativeQuery.append("ON hem.motivo_estado_sorteo = dme.codigo ");
    	nativeQuery.append("AND dme.dominio= ?8 ");
    	nativeQuery.append("INNER JOIN servicio ser ");
    	nativeQuery.append("ON hem.id_servicio = ser.id_servicio ");
    	nativeQuery.append("WHERE id_persona= ?1 ");
    	nativeQuery.append(" ");
    	nativeQuery.append("UNION ");
    	nativeQuery.append(" ");
    	nativeQuery.append("SELECT id_persona AS idPersona, ");
    	nativeQuery.append("r.nombre AS rol, ");
    	nativeQuery.append("do.nombre AS idMotivo, ");
    	nativeQuery.append("de.nombre AS estado, ");
    	nativeQuery.append("''   AS materia, ");
    	nativeQuery.append("''   AS servicio, ");
    	nativeQuery.append("hem.id_usuario_modificacion  AS idUsuarioModificacion, ");
    	nativeQuery.append("hem.fecha_ultima_modificacion AS fechaUltimaModificacion, ");
    	nativeQuery.append("hem.estado_registro           AS estadoRegistro ");
    	nativeQuery.append("FROM HISTORICO_ESTADO_PERSONA_ROL hem ");
    	nativeQuery.append("INNER JOIN ROL r ");
    	nativeQuery.append(" ON  r.id_rol= hem.id_rol ");
    	nativeQuery.append("AND r.tipo_servicio IN (?7, ?11) ");
    	nativeQuery.append("INNER JOIN ESTADO_MOTIVO_DISPONIBILIDAD emd ");
    	nativeQuery.append("ON hem.id_motivo = emd.id_motivo_estado ");
    	nativeQuery.append("INNER JOIN dominio de ");
    	nativeQuery.append("ON emd.estado = codigo ");
    	nativeQuery.append("AND de.dominio= ?5 ");
    	nativeQuery.append("INNER JOIN dominio do ");
    	nativeQuery.append("ON hem.id_motivo = do.codigo ");
    	nativeQuery.append("AND do.dominio= ?9 ");
    	nativeQuery.append("WHERE id_persona= ?1 ");
    	nativeQuery.append("order by   rol, materia, fechaUltimaModificacion ");
    	
		Query query = em.createNativeQuery(nativeQuery.toString(),HistoricoEstadoMotivoPersonaDTO.class);
		
		query.setParameter(1, idPersona);
		query.setParameter(2, UtilDominios.ROL_MEDIADOR);
		query.setParameter(3, UtilDominios.DOMINIO_ESTADO_ARBITROS);
		query.setParameter(4, UtilDominios.DOMINIO_ESTADO_MEDIADORES);
		query.setParameter(5, UtilDominios.DOMINIO_ESTADO_CONCILIADORES);
		query.setParameter(6, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		query.setParameter(7, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(8, UtilDominios.DOMINIO_MOTIVO_ESTADO);
		query.setParameter(9, UtilDominios.DOMINIO_MOTIVO_ESTADO_CONCILIADORES);
		query.setParameter(10, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);
		query.setParameter(11, UtilDominios.TIPO_SERVICIO_CONVIVENCIA);
		
		historicos = query.getResultList();
		return historicos;
    }

}
