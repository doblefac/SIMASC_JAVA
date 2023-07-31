package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.ProgramacionAlerta;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad ProgramacionAlerta.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorProgramacionAlerta extends ManejadorCrud<ProgramacionAlerta,Long>{

	private static final Logger logger = LogManager.getLogger(ManejadorProgramacionAlerta.class.getName());
	private static final String TEXTO_BEGIN = " BEGIN ";
	private static final String TEXTO_END = " END ";
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorProgramacionAlerta() {
        super(ProgramacionAlerta.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
	public List<ProgramacionAlerta> consultaProgramacionesPorEjecutar(){
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT pa FROM ProgramacionAlerta pa ");
		jpqlQuery.append(" WHERE pa.estado = :").append(ProgramacionAlerta.ENTIDAD_PROGRAMACION_ALERTA_ESTADO);
		jpqlQuery.append(" AND pa.fechaEjecucion <= :").append(ProgramacionAlerta.ENTIDAD_PROGRAMACION_ALERTA_FECHA_EJECUCION);
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(ProgramacionAlerta.ENTIDAD_PROGRAMACION_ALERTA_ESTADO, UtilDominios.PROGRAMACION_ALERTA_PENDIENTE);
		query.setParameter(ProgramacionAlerta.ENTIDAD_PROGRAMACION_ALERTA_FECHA_EJECUCION, new Date());
		return query.getResultList();
	}
	
	public void crearProgramacionesAceptacionConciliacionEquidad(Long idCaso, Long idPersona, String metodoNombramiento){
		
		logger.info("crearProgramacionesAceptacionConciliacionEquidad");
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append(" DECLARE @TiempoAceptacion numeric(20); ");
		nativeQuery.append(" DECLARE @IdservicioEquidad numeric(18); ");
		nativeQuery.append(TEXTO_BEGIN);	
		nativeQuery.append(" SELECT @TiempoAceptacion = valor FROM PARAMETRO_DE_SERVICIO ps inner join CASO c ");
		nativeQuery.append(" on ps.id_servicio = c.id_servicio WHERE c.id_caso = ?2 ");
		nativeQuery.append(" AND ps.nombre =  (CASE WHEN ?4 = ?7 THEN ?9 WHEN ?4 = ?8 THEN ?10 END); ");
		nativeQuery.append(" SELECT @IdservicioEquidad = id_servicio FROM servicio WHERE nombre = 'CONCILIACION EN EQUIDAD' ");
		nativeQuery.append(" if(@TiempoAceptacion IS NOT NULL) ");
		nativeQuery.append(TEXTO_BEGIN);		
		nativeQuery.append("   INSERT INTO PROGRAMACION_ALERTA ( id_caso, id_persona, fecha_ejecucion, estado, id_alerta ) SELECT ?2,?3,dbo.fechaHoraLaborable(GETDATE(), @TiempoAceptacion), ?6 , a.id_alerta  ");
		nativeQuery.append("   FROM ALERTA a WHERE a.codigo  = 'RECACE1E' AND a.estado = ?1 AND a.estado_registro = ?1 ; ");
		nativeQuery.append("   INSERT INTO PROGRAMACION_ALERTA ( id_caso, id_persona, fecha_ejecucion, estado, id_alerta ) SELECT ?2,?3,dbo.fechaHoraLaborable(GETDATE(), @TiempoAceptacion - a.valor), ?6 , a.id_alerta ");
		nativeQuery.append("   FROM ALERTA a WHERE a.codigo  = 'RECACEUE' AND a.estado = ?1 AND a.estado_registro = ?1 ");
		nativeQuery.append(TEXTO_END);
		nativeQuery.append(TEXTO_END);
		Query query = mp.createNativeQuery(nativeQuery.toString());		

		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idCaso);
		query.setParameter(3, idPersona);
		query.setParameter(4, metodoNombramiento);				
		query.setParameter(5, UtilDominios.PARAMETRO_GENERAL_PLAZO_ACEPTACION_CONCILIADOR);
		query.setParameter(6, UtilDominios.PROGRAMACION_ALERTA_PENDIENTE);		
		query.setParameter(7, UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
		query.setParameter(8, UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES);		
		query.setParameter(9, UtilDominios.CODIGO_PARAMETRO_GRAL_PLAZO_ACEPTACION_CONCILIADOR_CCB);
		query.setParameter(10, UtilDominios.CODIGO_PARAMETRO_GRAL_PLAZO_ACEPTACION_CONCILIADOR_PARTES);
		query.executeUpdate();
		
	}
	
	
	public void crearProgramacionesAceptacionConciliacion(Long idCaso, Long idPersona, String metodoNombramiento){
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append(" DECLARE @TiempoAceptacion numeric(20); ");
		nativeQuery.append(" DECLARE @Idservicio numeric(18); ");
		nativeQuery.append(TEXTO_BEGIN);
	
		nativeQuery.append(" SELECT @TiempoAceptacion = valor FROM PARAMETRO_DE_SERVICIO ps inner join CASO c ");
		nativeQuery.append(" on ps.id_servicio = c.id_servicio WHERE c.id_caso = ?2 ");
		nativeQuery.append(" AND ps.nombre =  (CASE WHEN ?4 = ?7 THEN ?9 WHEN ?4 = ?8 THEN ?10 END); 	 ");
		nativeQuery.append(" SELECT @Idservicio = id_servicio FROM servicio WHERE nombre = 'INSOLVENCIA' ");
		nativeQuery.append(" if(@TiempoAceptacion IS NOT  NULL)	 ");
		nativeQuery.append(TEXTO_BEGIN);
		nativeQuery.append(" if ( SELECT id_servicio FROM CASO c WHERE id_caso = ?2 ) <> @Idservicio ");
		nativeQuery.append(TEXTO_BEGIN);
		nativeQuery.append(" INSERT INTO PROGRAMACION_ALERTA ( id_caso, id_persona, fecha_ejecucion, estado, id_alerta ) SELECT ?2,?3,dbo.fechaHoraLaborable(getdate(), a.valor), ?6 , a.id_alerta  ");
		nativeQuery.append(" FROM ALERTA a WHERE a.codigo  = 'RECACE1' AND a.estado = ?1 AND a.estado_registro = ?1 ; ");
		nativeQuery.append(TEXTO_END);
		nativeQuery.append(" INSERT INTO PROGRAMACION_ALERTA ( id_caso, id_persona, fecha_ejecucion, estado, id_alerta ) SELECT ?2,?3,dbo.fechaHoraLaborable(GETDATE(), @TiempoAceptacion - a.valor), ?6 , a.id_alerta ");
		nativeQuery.append(" FROM ALERTA a WHERE a.codigo  = 'RECACEU' AND a.estado = ?1 AND a.estado_registro = ?1 ");
		nativeQuery.append(" INSERT INTO PROGRAMACION_ALERTA ( id_caso, id_persona, fecha_ejecucion, estado, id_alerta ) SELECT ?2,?3,dbo.fechaHoraLaborable(GETDATE(), @TiempoAceptacion), ?6 ,");
		nativeQuery.append(" a.id_alerta FROM ALERTA a WHERE a.codigo = 'CSUPLEN' AND a.estado = ?1 AND a.estado_registro = ?1 ");
		nativeQuery.append(TEXTO_END);
		nativeQuery.append(TEXTO_END);
		Query query = mp.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idCaso);
		query.setParameter(3, idPersona);
		query.setParameter(4, metodoNombramiento);				
		query.setParameter(5, UtilDominios.PARAMETRO_GENERAL_PLAZO_ACEPTACION_CONCILIADOR);
		query.setParameter(6, UtilDominios.PROGRAMACION_ALERTA_PENDIENTE);
		
		query.setParameter(7, UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
		query.setParameter(8, UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES);		
		query.setParameter(9, UtilDominios.CODIGO_PARAMETRO_GRAL_PLAZO_ACEPTACION_CONCILIADOR_CCB);
		query.setParameter(10, UtilDominios.CODIGO_PARAMETRO_GRAL_PLAZO_ACEPTACION_CONCILIADOR_PARTES);
		query.executeUpdate();
	}
	
	/**
	 * Método que genera la programacion de una alerta de tipo programacion audiencia
	 * @param idCaso
	 * @param idPersona
	 */
	public void crearProgramacionAlerta( Long idCaso, Long idPersona, String codigoAlerta, Long idDocumento ) {
		StringBuilder nativeQuery = new StringBuilder();
		logger.info("documento id");
		if(idDocumento!=null){
			nativeQuery.append(" insert into PROGRAMACION_ALERTA ( id_caso, id_persona, fecha_ejecucion, estado, id_alerta, id_documento )");	
		}else{
			nativeQuery.append(" insert into PROGRAMACION_ALERTA ( id_caso, id_persona, fecha_ejecucion, estado, id_alerta )");	
		}		
		nativeQuery.append(" select ?2, ?3, case ");
		nativeQuery.append(" when a.tipo_periodicidad = ?5 then ");
		nativeQuery.append(" case when a.periodicidad = ?6 then DATEADD(day, a.valor, GETDATE()) ");
		nativeQuery.append(" else DATEADD(HOUR, a.valor, GETDATE()) end ");
		nativeQuery.append(" else ");
		nativeQuery.append(" case when a.periodicidad = ?6 then dbo.SumarDiasHabiles(GETDATE(), a.valor) ");
		nativeQuery.append(" else dbo.fechaHoraLaborable(GETDATE(), a.valor) end ");
		nativeQuery.append(" end, ?4, a.id_alerta");
		if(idDocumento!=null){
			nativeQuery.append(" , ?8 ");	
		}
		nativeQuery.append(" from alerta a ");
		nativeQuery.append(" where codigo = ?7 ");
		nativeQuery.append(" and estado = ?1 ");
		nativeQuery.append(" and estado_registro = ?1 ");
		
		Query query = mp.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idCaso);
		query.setParameter(3, idPersona);
		query.setParameter(4, UtilDominios.PROGRAMACION_ALERTA_PENDIENTE);
		query.setParameter(5, UtilDominios.TIPO_PERIODICIDAD_CALENDARIO);
		query.setParameter(6, UtilDominios.PERIODICIDAD_DIA);
		query.setParameter(7, codigoAlerta);
		if(idDocumento!=null){
			query.setParameter(8, idDocumento);
		}
		query.executeUpdate();
	}
		

	public void modificarAlertaReactivacionCaso(Long idCaso, Date fechaInicial, Date nuevaFecha, Long idAlerta)
	{
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append("update PROGRAMACION_ALERTA ");
		nativeQuery.append("set fecha_ejecucion = ?1 ");
		nativeQuery.append("where id_alerta = ?2 ");
		nativeQuery.append("and id_caso = ?3 ");
		nativeQuery.append("and fecha_ejecucion = ?4 ");
		Query query = mp.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, nuevaFecha);
		query.setParameter(2, idAlerta);
		query.setParameter(3, idCaso);
		query.setParameter(4, fechaInicial);		
		query.executeUpdate();
	}
	
	public void crearProgramacionAlertaDiasHabiles( Long idCaso, Long idPersona, String codigoAlerta, Long cantidadDias ) {
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append(" insert into PROGRAMACION_ALERTA ( id_caso, id_persona, fecha_ejecucion, estado, id_alerta )");			
		nativeQuery.append(" select ?2, ?3, dbo.SumarDiasHabiles(GETDATE(), ?6), ?4, a.id_alerta");
		nativeQuery.append(" from alerta a ");
		nativeQuery.append(" where codigo = ?5 ");
		nativeQuery.append(" and estado = ?1 ");
		nativeQuery.append(" and estado_registro = ?1 ");
		
		Query query = mp.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idCaso);
		query.setParameter(3, idPersona);
		query.setParameter(4, UtilDominios.PROGRAMACION_ALERTA_PENDIENTE);				
		query.setParameter(5, codigoAlerta);
		query.setParameter(6, cantidadDias);
		
		query.executeUpdate();
	}
		

    // protected region metodos adicionales end
        
}

