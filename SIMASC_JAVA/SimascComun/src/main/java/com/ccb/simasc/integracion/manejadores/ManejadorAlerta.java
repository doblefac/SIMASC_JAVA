package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.AlertaDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Alerta;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Alerta.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorAlerta extends ManejadorCrud<Alerta,Long> {

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	
	@PersistenceContext
	private EntityManager em;

	// protected region atributos end
    
    public ManejadorAlerta() {
        super(Alerta.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    public List<Alerta> consultarAlertasPorEstadoTipoServicio(AlertaDTO filtros){
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT a FROM Alerta a ");
		jpqlQuery.append(" WHERE a.estadoRegistro = :");
		jpqlQuery.append(Alerta.ENTIDAD_ALERTA_ESTADO_REGISTRO);
		if(filtros.getTipoServicio() != null){
			jpqlQuery.append(" AND a.tipoServicio = :");
			jpqlQuery.append(Alerta.ENTIDAD_ALERTA_TIPO_SERVICIO);			
		}
		
		if(filtros.getEstado() != null){
			jpqlQuery.append(" AND a.estado =:");
			jpqlQuery.append(Alerta.ENTIDAD_ALERTA_ESTADO);
		}
		
		if(filtros.getTipoAlerta() != null){
			jpqlQuery.append(" AND a.tipoAlerta =:");
			jpqlQuery.append(Alerta.ENTIDAD_ALERTA_TIPO_ALERTA);
		}
		
		if( filtros.getEstadoEjecucion() != null ){
			jpqlQuery.append(" AND a.estadoEjecucion =:");	
			jpqlQuery.append(Alerta.ENTIDAD_ALERTA_ESTADO_EJECUCION);			
		}
		
		if( filtros.getCodigo() != null ){
			jpqlQuery.append(" AND a.codigo =:");
			jpqlQuery.append(Alerta.ENTIDAD_ALERTA_CODIGO);			
		}
		
		if(filtros.getHoraEjecucion() != null){
			jpqlQuery.append(" AND a.horaEjecucion <= :");
			jpqlQuery.append(Alerta.ENTIDAD_ALERTA_HORA_EJECUCION);
		}
		jpqlQuery.append(" ORDER BY a.tipoServicio , a.nombre ");		

		
		
		Query query = em.createQuery(jpqlQuery.toString());
		query.setParameter(Alerta.ENTIDAD_ALERTA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if(filtros.getTipoServicio()  != null){
			query.setParameter(Alerta.ENTIDAD_ALERTA_TIPO_SERVICIO,filtros.getTipoServicio() );
		}
		if(filtros.getEstado() != null){
			query.setParameter(Alerta.ENTIDAD_ALERTA_ESTADO,filtros.getEstado());
		}		
		if(filtros.getTipoAlerta() != null){
			query.setParameter(Alerta.ENTIDAD_ALERTA_TIPO_ALERTA,filtros.getTipoAlerta());
		}		
		if( filtros.getEstadoEjecucion() != null ){
			query.setParameter(Alerta.ENTIDAD_ALERTA_ESTADO_EJECUCION, filtros.getEstadoEjecucion());
			
		}
		if(filtros.getHoraEjecucion() != null){
			query.setParameter(Alerta.ENTIDAD_ALERTA_HORA_EJECUCION, filtros.getHoraEjecucion());

		}
		
		if( filtros.getCodigo() != null ){
			query.setParameter(Alerta.ENTIDAD_ALERTA_CODIGO, filtros.getCodigo());			
		}

		return query.getResultList();

    }
    
    /**
     * Consulta las persona notificadas por peticion
     * @param centros
     * @param roles
     * @return
     */
    public List<Long> consultarNotificadosPeticion(Long centro, String roles){
    	StringBuilder nativeQuery = new StringBuilder();		

    	nativeQuery.append(" SELECT DISTINCT rp.id_persona FROM ROL_PERSONA rp "); 
    	nativeQuery.append(" WHERE rp.estado_registro = ?1  ");
    	nativeQuery.append(" AND  rp.fecha_inicio_vigencia <= CONVERT (DATE, GETDATE()) ");
    	nativeQuery.append(" AND (rp.fecha_fin_vigencia IS NULL OR CAST(rp.fecha_fin_vigencia AS DATE) >= CONVERT (DATE, GETDATE())  ) ");
    	nativeQuery.append(" AND rp.id_rol IN  ");
    	nativeQuery.append(" ( SELECT r.id_rol FROM ROL r  ");
    	nativeQuery.append(" LEFT JOIN CLASIFICADOR_DOMINIO cd ON cd.codigo_clasificado = r.nombre  ");
    	nativeQuery.append(" AND cd.dominio_clasificador = ?2 AND cd.estado_registro = ?1  ");
    	nativeQuery.append(" WHERE r.nombre IN ( ").append(roles).append(" ) AND ");
    	nativeQuery.append(" cd.codigo_clasificador IS NULL AND r.estado_registro = ?1  )  ");	

    	if(centro != null){
    		nativeQuery.append(" AND rp.id_centro = ?3 ");    		
    	}
    	
		Query query = em.createNativeQuery(nativeQuery.toString(),Long.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.DOMINIO_AGRUPADOR_ROL_ENVIO_ALERTAS);
		query.setParameter(3, centro);
		return query.getResultList();
    	
    }
    
    /**
     * Metodo encargado de consultar las personas que se notificaran al momento de enviar una nueva alerta
     * @param idCaso
     * @param idAlerta
     * @param centros
     * @param idConvenio
     * @param idPersonas
     * @return
     */
    public List<PersonaBasicaDTO> consultarPersonasNotificar(Long idCaso, Long idAlerta, List<Long> centros, Long idConvenio, List<Long> idPersonas){
        String listaEstados[] = new String[]{UtilDominios.ESTADO_ROL_PERSONA_CASO_INACTIVO, UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO};    	
        
    	StringBuilder nativeQuery = new StringBuilder();		
    	nativeQuery.append(" SELECT DISTINCT pe.id_persona AS idPersona, ce.direccion AS correoElectronico FROM PERSONA pe ");
    	nativeQuery.append(" INNER JOIN CORREO_ELECTRONICO  ce ON pe.id_persona = ce.id_persona ");
    	nativeQuery.append(" WHERE ce.estado_registro = ?1  ");
    	nativeQuery.append(" AND ce.tipo = ?2 ");
    	nativeQuery.append(" AND pe.id_persona IN (SELECT pe.id_persona FROM PERSONA pe ");
    	nativeQuery.append(" INNER JOIN PERSONA_ROL_ALERTA pra ON  pra.id_persona = pe.id_persona ");
    	nativeQuery.append(" WHERE pra.id_alerta = ?3 and pra.estado_registro = ?1 ");
    	nativeQuery.append(" UNION ");
    	nativeQuery.append(" SELECT DISTINCT rp.id_persona FROM ROL_PERSONA rp "); 
    	nativeQuery.append(" WHERE rp.estado_registro = ?1  ");
    	nativeQuery.append(" AND rp.fecha_fin_vigencia IS NULL ");
    	nativeQuery.append(" AND rp.id_rol IN  ( SELECT r.id_rol ");
    	nativeQuery.append(" FROM PERSONA_ROL_ALERTA rpa ");
    	nativeQuery.append(" INNER JOIN ROL r ON r.id_rol = rpa.id_rol AND r.estado_registro = ?1 ");
    	nativeQuery.append(" LEFT JOIN CLASIFICADOR_DOMINIO cd ON cd.codigo_clasificado = r.nombre ");
    	nativeQuery.append(" AND cd.dominio_clasificador = ?4 AND cd.estado_registro = ?1 ");
    	nativeQuery.append(" WHERE id_alerta = ?3 AND rpa.estado_registro = ?1 ");    	
    	if((idCaso != null) || (idPersonas != null && !idPersonas.isEmpty())){
    	nativeQuery.append(" AND ( ( cd.codigo_clasificador IS NULL  OR ( cd.codigo_clasificador <> ?5  AND cd.codigo_clasificador <> ?8 )  " );
    	if(idConvenio == null){
    		nativeQuery.append(" OR r.nombre = ?10 ");
    	}
    	nativeQuery.append("  ) ) " );
    	}
    	
    	nativeQuery.append("  ) " );
    	
    	if(centros != null && !centros.isEmpty()){
    		nativeQuery.append(" AND rp.id_centro ").append(UtilConsultasSQL.clausulaInSQLSNumeros(centros));
    		
    	}
    	if(idCaso != null){
    		
    		nativeQuery.append(" UNION ");
    		nativeQuery.append(" SELECT DISTINCT rpc.id_persona FROM ROL_PERSONA_CASO rpc "); 
    		nativeQuery.append(" WHERE rpc.estado_registro = ?1 ");
    		nativeQuery.append(" AND rpc.id_rol IN (SELECT r.id_rol ");
    		nativeQuery.append(" FROM PERSONA_ROL_ALERTA rpa ");
    		nativeQuery.append(" INNER JOIN ROL r ON r.id_rol = rpa.id_rol AND r.estado_registro = ?1 ");
    		nativeQuery.append(" INNER JOIN CLASIFICADOR_DOMINIO cd ON cd.codigo_clasificado = r.nombre ");
    		nativeQuery.append(" AND cd.dominio_clasificador = ?4 AND cd.estado_registro = ?1 ");
    		nativeQuery.append(" WHERE id_alerta = ?3 AND rpa.estado_registro = ?1 ");
    		nativeQuery.append(" AND  cd.codigo_clasificador = ?5 ) ");
    		nativeQuery.append(" AND rpc.estado NOT ").append(UtilConsultasSQL.clausulaInSQLStrings(Arrays.asList(listaEstados)));
    		nativeQuery.append(" AND (rpc.tipo_nombramiento IS NULL OR  rpc.tipo_nombramiento = ?6 ) ");
    		nativeQuery.append(" AND rpc.id_caso = ?7  ");
    		
    	}
    	
    	if(idPersonas != null && !idPersonas.isEmpty()){
    		nativeQuery.append(" UNION ");
    		nativeQuery.append(" SELECT id_persona FROM PERSONA ");
    		nativeQuery.append(" WHERE id_persona ").append(UtilConsultasSQL.clausulaInSQLSNumeros(idPersonas));
    		
    	}
    	
    	if(idConvenio != null){
    		nativeQuery.append(" UNION ");
    		nativeQuery.append(" SELECT id_persona FROM RELACIONADO_CONVENIO rc ");
    		nativeQuery.append(" WHERE id_convenio = ?9 AND rc.estado_registro = ?1 ");
    		nativeQuery.append(" AND rc.id_rol IN (SELECT rol.id_rol ");
    		nativeQuery.append(" FROM PERSONA_ROL_ALERTA pral ");
    		nativeQuery.append(" INNER JOIN ROL rol ON rol.id_rol = pral.id_rol AND rol.estado_registro = ?1 ");
    		nativeQuery.append(" INNER JOIN CLASIFICADOR_DOMINIO cdo ON cdo.codigo_clasificado = rol.nombre ");
    		nativeQuery.append(" AND cdo.dominio_clasificador = ?4 AND cdo.estado_registro = ?1 ");
    		nativeQuery.append(" WHERE id_alerta = ?3 AND pral.estado_registro = ?1 ");
    		nativeQuery.append(" AND  cdo.codigo_clasificador = ?8 ) ");
    	}
    	
    	nativeQuery.append(" ) ORDER BY pe.id_persona ");

		
		Query query = em.createNativeQuery(nativeQuery.toString(),PersonaBasicaDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
		query.setParameter(3, idAlerta);
		query.setParameter(4, UtilDominios.DOMINIO_AGRUPADOR_ROL_ENVIO_ALERTAS);
		query.setParameter(5, UtilDominios.CODIGO_AGRUPADOR_ROLES_DEL_CASO);
		query.setParameter(8, UtilDominios.CODIGO_AGRUPADOR_ROLES_DEL_CONVENIO);
		
		if(idCaso != null){
			query.setParameter(6, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
			query.setParameter(7, idCaso);
		}
		
		if(idConvenio != null){
			query.setParameter(9, idConvenio);
		}else{
			query.setParameter(10, UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION);
		}
		

		return query.getResultList();
    }
    
    public List<PersonaBasicaDTO> consultarPersonasNotificar(Long idAlerta){
        
    	StringBuilder nativeQuery = new StringBuilder();		
    	nativeQuery.append(" SELECT DISTINCT pe.id_persona AS idPersona, ce.direccion AS correoElectronico FROM PERSONA pe ");
    	nativeQuery.append(" INNER JOIN CORREO_ELECTRONICO  ce ON pe.id_persona = ce.id_persona ");
    	nativeQuery.append(" WHERE ce.estado_registro = ?1  ");
    	nativeQuery.append(" AND ce.tipo = ?2 ");
    	nativeQuery.append(" AND pe.id_persona IN (SELECT pe.id_persona FROM PERSONA pe ");
    	nativeQuery.append(" INNER JOIN PERSONA_ROL_ALERTA pra ON  pra.id_persona = pe.id_persona ");
    	nativeQuery.append(" WHERE pra.id_alerta = ?3 and pra.estado_registro = ?1 ");
    	nativeQuery.append(" ) ORDER BY pe.id_persona ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(),PersonaBasicaDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
		query.setParameter(3, idAlerta);		

		return query.getResultList();
    }
    
    /**
     * Actualiza las alertas para su ejecucion diaria
     * @param idCaso
     * @param idAlerta
     * @param centros
     */
    public void actualizarEjecucionDiaria(){

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" UPDATE ALERTA SET estado_ejecucion = ?5 , fecha_ultima_modificacion = GETDATE(), ");
		nativeQuery.append(" hora_ejecucion =  SMALLDATETIMEFROMPARTS ( DATEPART(yy,GETDATE()), DATEPART(mm,GETDATE()), DATEPART(dd,GETDATE()), ");
		nativeQuery.append(" DATEPART(HH, hora_ejecucion) , DATEPART(mi, hora_ejecucion )) ");
		nativeQuery.append(" WHERE id_alerta IN ( ");
		nativeQuery.append(" SELECT a.id_alerta FROM ALERTA a  ");
		nativeQuery.append(" INNER JOIN DIA_EJECUCION dej ON dej.estado_registro = ?1 AND dej.id_alerta = a.id_alerta ");
		nativeQuery.append(" WHERE a.estado_registro = ?1 AND a.estado = ?2 ");
		nativeQuery.append(" AND a.tipo_alerta = ?4 ");
		nativeQuery.append(" AND a.hora_ejecucion IS NOT NULL ");
		nativeQuery.append(" AND dej.dia = DATEPART(dw, GETDATE()) ");
		nativeQuery.append(" AND ( a.tipo_periodicidad = ?3 ");
		nativeQuery.append(" OR NOT EXISTS  (  SELECT id_festivo FROM DIA_FESTIVO df WHERE df.estado_registro = ?1 ");
		nativeQuery.append(" AND  CAST (df.fecha AS DATE) = CONVERT (date, GETDATE()) )  ) ) ");
		
		Query query = em.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_ALERTA_ACTIVA);
		query.setParameter(3, UtilDominios.TIPO_PERIODICIDAD_CALENDARIO);
		query.setParameter(4, UtilDominios.TIPO_ALERTA_PARAMETRIZADA);
		query.setParameter(5, UtilDominios.ESTADO_EJECUCION_ALERTA_PENDIENTE);
		
		query.executeUpdate();
    }
    
    public List<Alerta> obtenerAlertaPorCodigo(String codigoAlerta) {
    	StringBuilder nativeQuery = new StringBuilder();
    	nativeQuery.append("select * from alerta");
    	nativeQuery.append(" where estado_registro = ?1");
    	nativeQuery.append(" and codigo = ?2");
    	Query query = em.createNativeQuery(nativeQuery.toString());
    	query = em.createNativeQuery(nativeQuery.toString(), Alerta.class);
    	query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	query.setParameter(2, codigoAlerta);
    	return query.getResultList();
    }
	
    public List<Alerta> consultarAlertasPendientes(AlertaDTO filtros) {
    	
    	StringBuilder nativeQuery = new StringBuilder();
    	nativeQuery.append("UPDATE alerta set estado_ejecucion = ?7");
    	nativeQuery.append(" where estado_registro = ?8");
    	if(filtros.getTipoServicio() != null)
    		nativeQuery.append(" AND tipo_servicio = ?1");
		
		if(filtros.getEstado() != null)
			nativeQuery.append(" AND estado = ?2");
		
		if(filtros.getTipoAlerta() != null)
			nativeQuery.append(" AND tipo_alerta = ?3");
		
		if( filtros.getEstadoEjecucion() != null )
			nativeQuery.append(" AND estado_ejecucion = ?4");
		
		if( filtros.getCodigo() != null )
			nativeQuery.append(" AND codigo = ?5");
		
		if(filtros.getHoraEjecucion() != null)
			nativeQuery.append(" AND hora_ejecucion <= ?6");
		
		    	Query query = em.createNativeQuery(nativeQuery.toString());
		    	if(filtros.getTipoServicio() != null)
		    		query.setParameter(1, filtros.getTipoServicio());
		    	if(filtros.getEstado() != null)
		    		query.setParameter(2, filtros.getEstado());
		    	if(filtros.getTipoAlerta() != null)
		    		query.setParameter(3, filtros.getTipoAlerta());
		    	if( filtros.getEstadoEjecucion() != null )
		    		query.setParameter(4, filtros.getEstadoEjecucion());
		    	if( filtros.getCodigo() != null )
		    		query.setParameter(5, filtros.getCodigo());
		    	if(filtros.getHoraEjecucion() != null)
		    		query.setParameter(6, filtros.getHoraEjecucion());
				query.setParameter(7, UtilDominios.PROGRAMACION_ALERTA_EN_EJECUCION);
				query.setParameter(8, UtilDominios.ESTADO_REGISTRO_ACTIVO);
				query.executeUpdate();
    	
    	nativeQuery = new StringBuilder();
    	nativeQuery.append("select * from alerta");
    	nativeQuery.append(" where estado_ejecucion = ?1");
    	nativeQuery.append(" and estado_registro = ?2");
    	nativeQuery.append(" order by tipo_servicio, nombre");
    	
    	query = em.createNativeQuery(nativeQuery.toString(), Alerta.class);
    	query.setParameter(1, UtilDominios.PROGRAMACION_ALERTA_EN_EJECUCION);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();
    }
    
    
    
  
    // protected region metodos adicionales end
        
}

