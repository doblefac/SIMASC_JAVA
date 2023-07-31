package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.ModificarActasConstanciasDevueltasDTO;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.entidades.DevolucionDocumentoResultado;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad DevolucionDocumentoResultado.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorDevolucionDocumentoResultado extends ManejadorCrud<DevolucionDocumentoResultado,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
	private EntityManager em;
	// protected region atributos end
    
    public ManejadorDevolucionDocumentoResultado() {
        super(DevolucionDocumentoResultado.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    /** CON-F-084
     * Consulta las actas o constancias de un caso que han devuelto en control de legalidad para se modificadas 
     * @param idCaso
     * @return List<ModificarActasConstanciasDevueltasDTO>
     */
    public List<ModificarActasConstanciasDevueltasDTO> consultarActasConstanciasDevueltasCaso( Long idCaso ){
    	
    	
    	StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT ra.id_resultado_audiencia AS idResultadoAudiencia, ");
		nativeQuery.append(" ultimaAudiencia.hora_inicio AS fechaAudiencia, ");
		nativeQuery.append(" d.tipo_documento AS tipoDocumento, d.descripcion AS descripcionDocumento, ");
		nativeQuery.append(" d.numero_folios AS numeroFolios, d.id_audiencia AS idAudienciaDocumento, ");
		nativeQuery.append(" d.id_documento AS idDocumento, ddr.id_devolucion_documento_resultado AS idDevolucionDocumentoResultado, ");
		nativeQuery.append(" (select nombre from dominio where codigo = ra.tipo_resultado_audiencia and dominio = ?2 ) AS resultado, ");
		nativeQuery.append(" (concat( '<p style=''margin-bottom:0px''>', stuff((select '.,', (select nombre from dominio where codigo = md.motivo and dominio = ?3 ) ");
				nativeQuery.append(" from MOTIVO_DEVOLUCION md ");
				nativeQuery.append(" where md.id_devolucion = ddr.id_devolucion_documento_resultado ");
		nativeQuery.append(" for xml path('')), 1, 2, ''), '.</p>' )) AS motivosDevolucion, ");
		nativeQuery.append(" ddr.observaciones AS observaciones ");

		nativeQuery.append(" FROM DEVOLUCION_DOCUMENTO_RESULTADO ddr ");
		nativeQuery.append(" INNER JOIN DOCUMENTO d ");
		nativeQuery.append(" ON d.id_documento = ddr.id_documento ");
		nativeQuery.append(" INNER JOIN CASO c ");
		nativeQuery.append(" ON d.id_caso = c.id_caso ");

		nativeQuery.append(" INNER JOIN AUDIENCIA a ");
		nativeQuery.append(" ON a.id_caso = c.id_caso ");
		nativeQuery.append(" AND d.id_audiencia = a.id_audiencia ");
		nativeQuery.append(" INNER JOIN RESULTADO_AUDIENCIA ra ");
		nativeQuery.append(" ON ra.id_documento = d.id_documento ");
		nativeQuery.append(" AND ra.id_documento = ddr.id_documento ");
		
		nativeQuery.append(" CROSS APPLY( ");
		nativeQuery.append(" SELECT TOP 1 * FROM AUDIENCIA au ");
		nativeQuery.append(" WHERE au.estado = ?4 ");
		nativeQuery.append(" AND au.id_caso = c.id_caso ");
		nativeQuery.append(" AND au.estado_registro = ?1 ");
		nativeQuery.append(" ORDER BY au.consecutivo DESC) ultimaAudiencia ");
		
		nativeQuery.append(" WHERE c.id_caso = ?5 ");
		nativeQuery.append(" AND ra.estado = ?6 ");
		nativeQuery.append(" AND ddr.estado_registro = ?1 ");
		nativeQuery.append(" AND d.estado_registro = ?1 ");
		nativeQuery.append(" AND c.estado_registro = ?1 ");
		nativeQuery.append(" AND a.estado_registro = ?1 ");
		nativeQuery.append(" AND ra.estado_registro = ?1 ");
		nativeQuery.append(" ORDER BY ddr.fecha ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), ModificarActasConstanciasDevueltasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.DOMINIO_RESULTADOS_AUDIENCIA);
		query.setParameter(3, UtilDominios.DOMINIO_MOTIVO_DEVOLUCION_CASO);
		query.setParameter(4, UtilDominios.ESTADO_AUDIENCIA_REALIZADA);
		query.setParameter(5, idCaso);
		query.setParameter(6, UtilDominios.ESTADO_RESULTADO_DEVUELTO);
		
		return query.getResultList();
    }
    
    
    /**
     * Consulta caso que estan en estado en devolucion con resultado audiencia en devolucion (acta constancia)
     * @return
     */
    public List<InfoBasicaAlertasDTO> alertaCorreccionActaConstancia(Long idCaso, Long idPersona){
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT  c.id_caso as idCaso, c.nombre as nombreCaso, S.id_centro AS idCentro FROM CASO C ");
		nativeQuery.append(" INNER JOIN AUDIENCIA AU ON AU.ID_CASO=C.ID_CASO  ");
		nativeQuery.append(" INNER JOIN RESULTADO_AUDIENCIA RA ON AU.ID_AUDIENCIA=RA.ID_AUDIENCIA ");
		nativeQuery.append(" INNER JOIN ROL_PERSONA_CASO RPC ON RPC.ID_CASO=C.ID_CASO  ");
		nativeQuery.append(" INNER JOIN SEDE S ON S.id_sede = C.id_sede  ");
		nativeQuery.append(" WHERE C.ID_CASO=?1 ");
		nativeQuery.append(" AND RA.ESTADO=?2 ");
		nativeQuery.append(" AND RPC.ID_PERSONA=?3 ");
		nativeQuery.append(" AND RPC.ID_ROL= (SELECT R.ID_ROL FROM ROL R WHERE R.nombre=?4 and r.estado_registro=?5)");
		nativeQuery.append(" AND RPC.tipo_nombramiento=?6 ");
		nativeQuery.append(" AND RPC.estado=?7 ");
		nativeQuery.append(" and c.estado_caso=?8");
		nativeQuery.append(" and c.estado_registro=?5 ");
		nativeQuery.append(" AND AU.ESTADO_REGISTRO=?5 ");
		nativeQuery.append(" AND RA.estado_registro=?5 ");
		nativeQuery.append(" AND S.estado_registro=?5 ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, UtilDominios.ESTADO_RESULTADO_DEVUELTO);
		query.setParameter(3, idPersona);
		query.setParameter(4, UtilDominios.ROL_PERSONA_CONCILIADOR);
		query.setParameter(5, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(6, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(7, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(8, UtilDominios.ESTADO_CASO_DEVUELTO_EN_CONTROL_DE_LEGALIDAD);

		return query.getResultList();
	}

    // protected region metodos adicionales end
        
}

