package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.CasosPendientesFirmaDTO;
import com.ccb.simasc.transversal.dto.PartesEstadoFirmaDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.FirmaDocumento;
import com.ccb.simasc.transversal.entidades.FirmaDocumentoPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad FirmaDocumento.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorFirmaDocumento extends ManejadorCrud<FirmaDocumento,FirmaDocumentoPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorFirmaDocumento() {
        super(FirmaDocumento.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    /**
     * Metodo que consutla las firmas de documentos por filtros
     * @param idDocumento
     * @param idCaso
     * @param estado
     * @return
     */
    public List<FirmaDocumento>  consultarFirmasFiltros(Long idDocumento,  Long idCaso, String estado){      	
          
      	StringBuilder nativeQuery = new StringBuilder();      	
      	nativeQuery.append(" SELECT fd.* FROM FIRMA_DOCUMENTO fd ");
      	nativeQuery.append(" WHERE fd.estado_registro = ?1 ");
      	if(idDocumento != null){
      		nativeQuery.append(" AND fd.id_documento = ?2 ");      		
      	}
      	
      	if(idCaso != null){
      		nativeQuery.append(" AND fd.id_caso = ?3 ");      		
      	}
      	
      	if(estado!= null){
      		nativeQuery.append(" AND fd.estado = ?4 ");      		
      	}      	

  		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(),PersonaBasicaDTO.class);
  		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
  		
      	if(idDocumento != null){
      		query.setParameter(2, idDocumento);
      	}
      	
      	if(idCaso != null){
      		query.setParameter(3, idCaso);
      	}
      	
      	if(estado!= null){
      		query.setParameter(4, estado);
      	}  		

  		return query.getResultList();    
    	
    }
    
    
    /**
     * Consulta la informacion de las parte para su firma
     * @param idCaso
     * @return
     */
    public List<PartesEstadoFirmaDTO> consultarFirmaPartes(Long idCaso, Long idDocumento){
    	StringBuilder nativeQuery = new StringBuilder();      	

    	nativeQuery.append(" SELECT rpc.id_persona as idPersona, rpc.id_caso as idCaso, rpc.id_rol idRol, ");
    	nativeQuery.append(" concat(rtrim(pe.primer_nombre_o_razon_social), rtrim(' '+pe.segundo_nombre), ");
      	nativeQuery.append(" rtrim(' '+pe.primer_apellido), rtrim(' '+pe.segundo_apellido)) AS nombreParte, ");
      	nativeQuery.append(" ro.nombre AS codigoRol , ");
      	nativeQuery.append(" dr.nombre AS nombreRol, ");
      	nativeQuery.append(" (SELECT CASE WHEN fd.estado IS NULL THEN ?2 ELSE fd.estado END)  AS codigoEstado, ");
      	nativeQuery.append(" (SELECT nombre FROM DOMINIO WHERE dominio = ?6 AND" );
      	nativeQuery.append("  codigo = (SELECT CASE WHEN fd.estado IS NULL THEN ?2 ELSE fd.estado END) ) AS nombreEstado, ");
      	nativeQuery.append(" fd.fecha_emision AS fechaHabilitacion, ");
      	nativeQuery.append(" fd.fecha_firma AS fechaFirma ");
      	nativeQuery.append(" FROM ROL_PERSONA_CASO rpc ");
      	nativeQuery.append(" INNER JOIN PERSONA pe ON pe.id_persona = rpc.id_persona ");
      	nativeQuery.append(" INNER JOIN ROL ro ON ro.id_rol = rpc.id_rol AND ro.estado_registro = ?1 ");
      	nativeQuery.append(" INNER JOIN CLASIFICADOR_DOMINIO cd ON cd.dominio_clasificador = ?8 ");
      	nativeQuery.append("  AND cd.codigo_clasificador = 'PARTAPP' AND cd.codigo_clasificado = ro.nombre AND cd.estado_registro = ?1 ");
      	
      	nativeQuery.append(" INNER JOIN DOMINIO dr ON dr.codigo = ro.nombre AND dr.dominio = ?3 ");
      	nativeQuery.append(" LEFT JOIN FIRMA_DOCUMENTO fd ON fd.id_rol = rpc.id_rol AND fd.id_persona = rpc.id_persona AND fd.id_caso = rpc.id_caso ");
      	if(idDocumento != null){
      		nativeQuery.append(" AND fd.id_documento = ?5 ");      		
      	}
      	nativeQuery.append(" AND fd.estado_registro = ?1 ");
      	nativeQuery.append(" WHERE rpc.id_caso = ?4 ");
      	nativeQuery.append(" AND rpc.estado_registro = ?1 ");
      	nativeQuery.append(" ORDER BY ro.nombre,  1 ");
      	
  		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(),PartesEstadoFirmaDTO.class);
  		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
  		query.setParameter(2, UtilDominios.ESTADO_DOCUMENTO_SIN_SOLICITUD);
  		query.setParameter(3, UtilDominios.DOMINIO_ROL_PERSONA);
  		query.setParameter(4, idCaso); 
  		query.setParameter(6, UtilDominios.DOMINIO_ESTADO_FIRMA_DOCUMENTO); 
  		query.setParameter(7, UtilDominios.AGRUPADOR_ROL_PERSONA_PARTES_APLICACION); 
  		query.setParameter(8, UtilDominios.DOMINIO_AGRUPADOR_ROL_PERSONA); 
  		
  		if(idDocumento != null){
  			query.setParameter(5, idDocumento); 
  		}  		
      	
    	return query.getResultList();
    }
    
    /**
     * Consulta la lista de casos pendientes de firma por el conciliador
     * @param idPersona
     * @return
     */
    public List<CasosPendientesFirmaDTO> casosPendientesFirmaConciliador(Long idPersona){
    	StringBuilder nativeQuery = new StringBuilder();  
    	
    	nativeQuery.append(" SELECT DISTINCT  ca.id_caso AS idCaso,  ");
    	nativeQuery.append(" ca.nombre AS nombreCaso,  ");
    	nativeQuery.append(" act.id_documento AS idDocumento, ");  
    	nativeQuery.append(" act.nombre AS nombreDocumento,  ");
    	nativeQuery.append(" act.url AS urlDocumento, ");
    	nativeQuery.append(" act.fecha_radicacion AS fechaRadicacion, ");
    	nativeQuery.append(" SUM (CASE  WHEN fd.estado = ?2 THEN 1 ELSE 0 END)  AS personasFaltantes "); 
    	nativeQuery.append(" FROM CASO ca INNER JOIN ROL_PERSONA_CASO rpc ON ca.id_caso = rpc.id_caso ");
    	nativeQuery.append(" INNER JOIN TIPO_DE_SERVICIO_ROL ts ON ts.tipo_servicio = ?3  AND ts.id_rol = rpc.id_rol ");  
    	nativeQuery.append(" CROSS APPLY ( SELECT TOP 1 * FROM DOCUMENTO do WHERE ca.id_caso = do.id_caso  ");
    	nativeQuery.append(" AND do.estado_registro = ?1 AND do.tipo_documento = ?4 ORDER BY do.fecha_ultima_modificacion DESC ) act "); 
    	nativeQuery.append(" INNER JOIN FIRMA_DOCUMENTO fd ON fd.id_documento = act.id_documento AND fd.estado <> ?5 ");
    	nativeQuery.append(" LEFT JOIN FIRMA_DOCUMENTO fdcon ON fdcon.id_documento = act.id_documento AND fdcon.estado_registro = ?1 ");
    	nativeQuery.append(" AND fdcon.id_persona = ?6 AND fdcon.estado = ?7 ");
    	nativeQuery.append(" WHERE ca.estado_registro = ?1 ");
    	nativeQuery.append(" AND ca.estado_caso <> ?8 ");
    	nativeQuery.append(" AND rpc.estado_registro = ?1 ");
    	nativeQuery.append(" AND ts.estado_registro = ?1 ");
    	nativeQuery.append(" AND rpc.id_persona = ?6 ");
    	nativeQuery.append(" AND rpc.tipo_nombramiento = ?9 ");
    	nativeQuery.append(" AND rpc.estado = ?10 ");
    	nativeQuery.append(" AND fd.estado_registro = ?1 ");
    	nativeQuery.append(" AND fdcon.id_persona IS NULL ");
    	nativeQuery.append(" GROUP BY ca.id_caso, ca.nombre, act.id_documento, act.nombre, act.url, act.fecha_radicacion ");
    	nativeQuery.append(" ORDER BY act.fecha_radicacion ASC ");
    	
  		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(),CasosPendientesFirmaDTO.class);

  		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
  		query.setParameter(2, UtilDominios.ESTADO_DOCUMENTO_SOLICITADO);
  		query.setParameter(3, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
  		query.setParameter(4, UtilDominios.TIPO_DOCUMENTO_DIG_ACTA_CONCILIACION);
  		query.setParameter(5, UtilDominios.ESTADO_DOCUMENTO_SIN_SOLICITUD);
  		query.setParameter(6, idPersona);
  		query.setParameter(7, UtilDominios.ESTADO_DOCUMENTO_FIRMADO);
  		query.setParameter(8, UtilDominios.ESTADO_CASO_REGISTRADO);
  		query.setParameter(9, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
  		query.setParameter(10, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
    	
    	return query.getResultList();
    }
    
    /**
     * Consulta que verifica que la persona tenga la solicitud de firma en el caso indicado
     * en caso de que exista mas de un documento se toma el ultimo que se haya emitido
     * @param idPersona
     * @param idCaso
     * @return
     */
    public List<FirmaDocumento> obtenerParteFirmante(Long idPersona, Long idCaso) {
    	StringBuilder nativeQuery = new StringBuilder();  
    	
    	nativeQuery.append("select top 1 * from FIRMA_DOCUMENTO where id_persona = ?1 and id_caso = ?2 ");
    	nativeQuery.append("and estado_registro = ?3 and estado = ?4 order by fecha_emision desc");
    	Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(),FirmaDocumento.class);
    	query.setParameter(1, idPersona);
    	query.setParameter(2, idCaso);
    	query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	query.setParameter(4, UtilDominios.ESTADO_DOCUMENTO_SOLICITADO);

    	return query.getResultList();
    }

    // protected region metodos adicionales end
        
}

