package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones



import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.entidades.Peticion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Peticion.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorPeticion extends ManejadorCrud<Peticion,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorPeticion() {
        super(Peticion.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones 
    
    /**
     * consulta las peticiones por caso y tipo peticion
     * @param idCaso
     * @param tipoPeticion
     * @return
     */
    public List<Peticion> peticionesPorCaso(Long idCaso, String tipoPeticion){

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT p FROM Peticion p ");
		jpqlQuery.append(" WHERE p.idCaso = :");		
		jpqlQuery.append(Peticion.ENTIDAD_PETICION_ID_CASO);
		jpqlQuery.append(" AND p.estadoRegistro = :");
		jpqlQuery.append(Peticion.ENTIDAD_PETICION_ESTADO_REGISTRO);
		if(tipoPeticion != null){
			jpqlQuery.append(" AND p.tipo = :");
			jpqlQuery.append(Peticion.ENTIDAD_PETICION_TIPO);
		}
		jpqlQuery.append(" ORDER BY p.idPeticion DESC");
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Peticion.ENTIDAD_PETICION_ID_CASO, idCaso);
		query.setParameter(Peticion.ENTIDAD_PETICION_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if(tipoPeticion != null){
			query.setParameter(Peticion.ENTIDAD_PETICION_TIPO, tipoPeticion);		
		}
		return query.getResultList();
    	
    }
    
    public BigDecimal consultarPeticiones(Long idPersona,
			Date periodoDesde, Date periodoHasta, boolean respuestaOportuna, List<String> peticiones) {
    	StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select count(distinct p.id_peticion) from PETICION p ");
		nativeQuery.append(" inner join rol_persona_caso rpc on rpc.id_caso = p.id_caso and rpc.id_persona = ?4 and rpc.estado = ?2 ");
		nativeQuery.append(" inner join TIPO_DE_SERVICIO_ROL t on rpc.id_rol = t.id_rol and t.tipo_servicio = ?3 and t.estado_registro = ?1 ");
		nativeQuery.append(" where p.tipo ").append(UtilConsultasSQL.clausulaInSQLStrings(peticiones));
		nativeQuery.append(" and p.estado_registro = ?1 ");
		nativeQuery.append(" and cast(p.fecha_respuesta as date) between ?5 and ?6 ");
		if(respuestaOportuna) {
			nativeQuery.append(" and dbo.diasHabilesEntreDosFechas(p.fecha_radicacion, p.fecha_respuesta) <= (select valor_numerico ");
			nativeQuery.append(" from PARAMETROS_GENERALES where codigo = p.tipo AND TIPO = ?7) ");			
		}
		
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), BigDecimal.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(3, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(4, idPersona);
		query.setParameter(5, periodoDesde);
		query.setParameter(6, periodoHasta);
		if(respuestaOportuna)
			query.setParameter(7, UtilConstantes.TIPO_PARAMETRO_PLAZO_RESPUESTA);
		
		return (BigDecimal) query.getSingleResult();
    }
    
    /**
     * Consulta de la alerta de peticiones especiales C20
     * @return
     */
    public List<InfoBasicaAlertasDTO>  alertaPeticionesEspeciales() {
    	StringBuilder nativeQuery = new StringBuilder();
    	nativeQuery.append(" SELECT pe.id_peticion AS idPeticion, ");
    	nativeQuery.append(" ca.id_caso AS idCaso, ");
    	nativeQuery.append(" ca.nombre AS nombreCaso, ");
    	nativeQuery.append(" ce.id_centro AS idCentro, ");
    	nativeQuery.append(" (SELECT descripcion FROM DOMINIO WHERE codigo = pe.tipo AND dominio = ?2 ) AS tipoPeticion, ");
    	nativeQuery.append(" CONCAT('''',REPLACE(prol.valor_texto, ',', ''','''),'''') AS valorParametro ");
    	nativeQuery.append(" FROM PETICION pe ");
    	nativeQuery.append(" INNER JOIN CASO ca ON ca.estado_registro = ?1 AND ca.id_caso = pe.id_caso ");
    	nativeQuery.append(" INNER JOIN SEDE se ON ca.id_sede = se.id_sede AND se.estado_registro = ?1 ");
    	nativeQuery.append(" INNER JOIN SERVICIO ser ON ser.id_servicio = ca.id_servicio AND ser.estado_registro = ?1 ");
    	nativeQuery.append(" LEFT JOIN PARAMETROS_GENERALES pla ON pla.tipo = ?3 AND pla.codigo = pe.tipo ");
    	nativeQuery.append(" LEFT JOIN PARAMETROS_GENERALES prol ON prol.tipo = ?4 AND  prol.codigo =  ");
    	nativeQuery.append(" (SELECT CASE pe.tipo WHEN ?5 THEN ?6 ");
    	nativeQuery.append(" WHEN ?7 THEN ?8  ");
    	nativeQuery.append(" WHEN ?9 THEN ?10  ");
    	nativeQuery.append(" WHEN ?11 THEN ?12 ");
    	nativeQuery.append(" WHEN ?13 THEN ?14 ");
    	nativeQuery.append(" WHEN ?15 THEN ?16 ");
    	nativeQuery.append(" WHEN ?17 THEN ?18 ELSE  '' END) ");
    	nativeQuery.append(" , CENTRO ce ");
    	nativeQuery.append(" WHERE pe.estado_registro = ?1 ");
    	nativeQuery.append(" AND pe.respuesta IS NULL ");
    	nativeQuery.append(" AND pe.fecha_respuesta IS NULL ");
    	nativeQuery.append(" AND ser.tipo = ?19 ");
		nativeQuery.append(" AND ce.id_centro =  ( CASE WHEN ca.id_servicio = ?20 THEN  (SELECT cv.id_centro FROM CASO c INNER JOIN CONVENIO cv "); 
		nativeQuery.append(" ON ca.id_convenio = cv.id_convenio AND ca.id_caso = c.id_caso  AND  cv.estado_registro = ?1 )  ");
		nativeQuery.append(" ELSE  (SELECT se.id_centro FROM CASO cas INNER JOIN SEDE se ON se.id_sede = cas.id_sede WHERE ca.id_caso = cas.id_caso AND se.estado_registro = ?1 ) END ) ");
    	nativeQuery.append(" AND  DATEDIFF(day,pe.fecha_radicacion, GETDATE()) >= pla.valor_numerico " );


		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.DOMINIO_TIPO_PETICION);
		query.setParameter(3, UtilConstantes.TIPO_PARAMETRO_PLAZO_RESPUESTA);
		query.setParameter(4, UtilConstantes.TIPO_PARAMETRO_ROL_TIPO_PETICION);
		
		query.setParameter(5, UtilDominios.TIPO_PETICION_CAMBIO_CONCILIADOR);
		query.setParameter(6, UtilDominios.ROL_TIPO_PETICION_CAMBIO_CONCILIADOR);
		
		query.setParameter(7, UtilDominios.TIPO_PETICION_CAMBIO_FECHA);
		query.setParameter(8, UtilDominios.ROL_TIPO_PETICION_CAMBIO_FECHA);
		
		query.setParameter(9, UtilDominios.TIPO_PETICION_DERECHO_PETICION);
		query.setParameter(10, UtilDominios.ROL_TIPO_PETICION_DERECHO_PETICION);
		
		query.setParameter(11, UtilDominios.TIPO_PETICION_CAMBIO_DEVOLUCION_DINERO);
		query.setParameter(12, UtilDominios.ROL_TIPO_PETICION_DEVOLUCION_DINERO);
		
		query.setParameter(13, UtilDominios.TIPO_PETICION_FOTOCOPIAS);
		query.setParameter(14, UtilDominios.ROL_TIPO_PETICION_FOTOCOPIAS);
		
		query.setParameter(15, UtilDominios.TIPO_PETICION_CAMBIO_INCUMPLIMIENTO);
		query.setParameter(16, UtilDominios.ROL_TIPO_PETICION_INCUMPLIMIENTO);
		
		query.setParameter(17, UtilDominios.TIPO_PETICION_OTROS);
		query.setParameter(18, UtilDominios.ROL_TIPO_PETICION_OTROS);
		
		query.setParameter(19, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(20, UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA);

		
		
		return  query.getResultList();
    }

    // protected region metodos adicionales end
        
}

