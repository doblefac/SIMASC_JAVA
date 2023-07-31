package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.DocumentosEntregadosDTO;
import com.ccb.simasc.transversal.entidades.EntregaDocumento;
import com.ccb.simasc.transversal.entidades.EntregaDocumentoPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad EntregaDocumento.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorEntregaDocumento extends ManejadorCrud<EntregaDocumento,EntregaDocumentoPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	
	@PersistenceContext
	private EntityManager em;
	
	// protected region atributos end
    
    public ManejadorEntregaDocumento() {
        super(EntregaDocumento.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    /** CON-F-085
     * @author prendon
     * query que consulta las entregas de documentos de un caso junto con todas las partes del caso
     * @param idRolPersona
     * @param estadoSolicitud
     * @return SolicitudCambioLista
     */
    public List<DocumentosEntregadosDTO> consultarDocumentosEntregadosPartesCaso( Long idCaso ){
    	StringBuilder nativeQuery = new StringBuilder();
    	
		String nombreParte = " (select stuff((select ', ' + concat (p.primer_nombre_o_razon_social, ' '+p.segundo_nombre, "
				+ " ' '+p.primer_apellido, ' '+p.segundo_apellido)  for xml path('')), 1, 1, '')) as nombreParte, ";
		
		nativeQuery.append("SELECT p.id_persona as idPersona, ");
		nativeQuery.append(nombreParte);
		nativeQuery.append(" r.nombre as nombreRolParte, ");
		nativeQuery.append(" a.hora_inicio as fechaAudiencia, ");
		nativeQuery.append(" a.resultado as resultadoAudiencia, ");
		nativeQuery.append(" d.id_documento as idDocumento, ");
		nativeQuery.append(" d.tipo_documento as tipoDocumento, ");
		nativeQuery.append(" ed.fecha_entrega as fechaEntrega ");
		nativeQuery.append(" FROM PERSONA P ");
		nativeQuery.append(" INNER JOIN ROL_PERSONA_CASO rpc ");
		nativeQuery.append(" ON p.id_persona = rpc.id_persona ");
		nativeQuery.append(" INNER JOIN ROL r ");
		nativeQuery.append(" ON r.id_rol = rpc.id_rol ");
		nativeQuery.append(" INNER JOIN AUDIENCIA a ");
		nativeQuery.append(" ON a.id_caso = rpc.id_caso ");
		nativeQuery.append(" INNER JOIN RESULTADO_AUDIENCIA ra ");
		nativeQuery.append(" ON ra.id_audiencia = a.id_audiencia ");
		nativeQuery.append(" INNER JOIN documento d ");
		nativeQuery.append(" ON d.id_audiencia = a.id_audiencia ");
		nativeQuery.append(" LEFT JOIN ENTREGA_DOCUMENTO ed ");
		nativeQuery.append(" ON p.id_persona = ed.id_persona ");
		nativeQuery.append(" AND d.id_documento = ed.id_documento ");
		nativeQuery.append(" AND ed.estado_registro = ?1 ");
		nativeQuery.append(" WHERE rpc.id_caso = ?2 ");
		nativeQuery.append(" AND r.nombre in ( select codigo_clasificado from CLASIFICADOR_DOMINIO ");
						nativeQuery.append("  where codigo_clasificador = ?3 and estado_registro = ?1 ) ");
		nativeQuery.append(" AND ((d.tipo_documento = ?4 AND ra.tipo_resultado_audiencia in ( ?5, ?6 )) ");
				nativeQuery.append(" OR (d.tipo_documento = ?7 AND ra.tipo_resultado_audiencia in ( ?8, ?9 ))) ");
		nativeQuery.append(" AND ra.estado = ?10 ");
		nativeQuery.append(" AND p.estado_registro = ?1 ");
		nativeQuery.append(" AND rpc.estado_registro = ?1 ");
		nativeQuery.append(" AND d.estado_registro = ?1 ");
		nativeQuery.append(" AND r.estado_registro = ?1 ");
		nativeQuery.append(" AND ra.estado_registro = ?1 ");
		nativeQuery.append(" AND a.estado_registro = ?1 ");
		nativeQuery.append(" ORDER BY r.nombre DESC");

		Query query = em.createNativeQuery(nativeQuery.toString(), DocumentosEntregadosDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idCaso);
		query.setParameter(3, UtilDominios.AGRUPADOR_ROL_PERSONA_PARTES_APLICACION);
		query.setParameter(4, UtilDominios.TIPO_DOCUMENTO_DIG_ACTA_CONCILIACION);
		query.setParameter(5, UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_TOTAL);
		query.setParameter(6, UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_PARCIAL);
		query.setParameter(7, UtilDominios.TIPO_DOCUMENTO_DIG_CONSTANCIA_RESULTADO_AUDIENCIA);
		query.setParameter(8, UtilDominios.RESULTADO_AUDIENCIA_IMPOSIBILIDAD_DE_ACUERDO);
		query.setParameter(9, UtilDominios.RESULTADO_AUDIENCIA_INASISTENCIA);
		query.setParameter(10, UtilDominios.ESTADO_RESULTADO_VALIDADO);

		return query.getResultList();
    }
    
    // protected region metodos adicionales end
        
}

