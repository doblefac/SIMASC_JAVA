package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.entidades.Suspension;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Suspension.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorSuspension extends ManejadorCrud<Suspension,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorSuspension() {
        super(Suspension.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
	/**
	 * Registra una persona en la bd
	 * 
	 * @param persona
	 * @return
	 */
	public Suspension actualizarSuspension(Suspension suspension) {
		return (Suspension) mp.updateObject(suspension);

	}
	
    /**
	 * Consulta las suspensiones de un caso por tipo y por caso
	 * @param idCaso
	 * @param tipo: Dominio TIPO_SUSPENSION
	 * @return Lista de suspensiones por tipo
	 */
	public List<Suspension> consultaSuspensionesPorCasoTipo(Long idCaso, String tipo) {
		List<Suspension> resultado = null;
		
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT s FROM Suspension s ");
		jpqlQuery.append(" WHERE s.tipo = :");
		jpqlQuery.append("tipo");
		jpqlQuery.append(" AND s.idCaso = :");
		jpqlQuery.append("idCaso");
		jpqlQuery.append(" AND s.estadoRegistro =: ");
		jpqlQuery.append(Suspension.ENTIDAD_SUSPENSION_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter("tipo", tipo);
		query.setParameter("idCaso", idCaso);
		query.setParameter(Suspension.ENTIDAD_SUSPENSION_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		resultado = (List<Suspension>) query.getResultList();			
		return resultado;

	}

	/**
	 * consulta todas las suspensiones de un caso.
	 * @param idCaso
	 * @return
	 */
	public List<Suspension> consultarSuspensionIdCaso(Long idCaso){
		List<Suspension> resultado = new ArrayList<Suspension>();
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT s FROM Suspension s ");		
		jpqlQuery.append(" WHERE s.idCaso = :").append(Suspension.ENTIDAD_SUSPENSION_ID_CASO);
		jpqlQuery.append(" AND s.estadoRegistro =: ").append(Suspension.ENTIDAD_SUSPENSION_ESTADO_REGISTRO);
		jpqlQuery.append(" ORDER BY s.fechaInicial ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Suspension.ENTIDAD_SUSPENSION_ID_CASO, idCaso);
		query.setParameter(Suspension.ENTIDAD_SUSPENSION_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		resultado = (List<Suspension>) query.getResultList();			
		return resultado;
		
	}
	
	/** Alerta de "tramites inactivos" codigo de alerta: TRAINA
	 * @return InfoBasicaAlertasDTO
	 */
	public List<InfoBasicaAlertasDTO> alertaTramiteInactivo(){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select distinct ca.id_caso as idCaso, ");
		nativeQuery.append(" ca.nombre as nombreCaso, ");
		nativeQuery.append(" se.id_centro as idCentro ");
		nativeQuery.append(" from caso ca ");
		nativeQuery.append(" inner join sede se ");
		nativeQuery.append(" on se.id_sede = ca.id_sede ");
		nativeQuery.append(" inner join SUSPENSION sus ");
		nativeQuery.append(" on sus.id_caso = ca.id_caso ");
		nativeQuery.append(" where sus.fecha_final is null ");
		nativeQuery.append(" and GETDATE() >= DATEADD( MONTH, (select isnull( (select valor_numerico ");
															nativeQuery.append(" from PARAMETROS_GENERALES ");
															nativeQuery.append(" where codigo = ?2 ");
															nativeQuery.append(" and tipo = ?3 ");
															nativeQuery.append(" and estado_registro = ?1), 1 )), sus.fecha_inicial ) ");
		nativeQuery.append(" and ca.estado_registro = ?1 ");
		nativeQuery.append(" and ca.estado_caso not in (?4, ?5) ");
		nativeQuery.append(" and sus.estado_registro = ?1 ");
		nativeQuery.append(" and se.estado_registro = ?1 ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.CODIGO_PARAMETRO_GENERAL_TIEMPO_INACTIVIDAD);
		query.setParameter(3, UtilDominios.TIPO_PARAMETRO_GENERAL_ALERTA_TRAMITE_INACTIVO);
		query.setParameter(4, UtilDominios.ESTADO_CASO_REGISTRADO);
		query.setParameter(5, UtilDominios.ESTADO_CASO_CERRADO);
		
		return query.getResultList();
	}
	
	/** Alerta de "Tramite próximo a activarse", codigo de alerta: TRAACT
	 * @param diasARestar 
	 * @return List<InfoBasicaAlertasDTO> 
	 */
	public List<InfoBasicaAlertasDTO> alertaTramiteProximoActivarse( Long diasARestar ){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select distinct ca.id_caso as idCaso, ");
		nativeQuery.append(" ca.nombre as nombreCaso, ");
		nativeQuery.append(" se.id_centro as idCentro, ");
		nativeQuery.append(" sus.fecha_final as fecha ");
		nativeQuery.append(" from caso ca ");
		nativeQuery.append(" inner join sede se ");
		nativeQuery.append(" on se.id_sede = ca.id_sede ");
		nativeQuery.append(" inner join SUSPENSION sus ");
		nativeQuery.append(" on sus.id_caso = ca.id_caso ");
		nativeQuery.append(" where sus.fecha_final is not null ");
		nativeQuery.append(" and Cast( GETDATE() as date ) = Cast( DATEADD( DAY, -?2, sus.fecha_final ) as date ) ");
		nativeQuery.append(" and sus.tipo <> ?3 ");
		nativeQuery.append(" and ca.estado_registro = ?1 ");
		nativeQuery.append(" and sus.estado_registro = ?1 ");
		nativeQuery.append(" and se.estado_registro = ?1 ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, diasARestar);
		query.setParameter(3, UtilDominios.TIPO_SUSPENSION_PRORROGA);
		
		return query.getResultList();
	}
	
    /**
	 * Consulta las suspensiones por las partes de un caso
	 * @param idCaso
	 * @param tipo: Dominio TIPO_SUSPENSION
	 * @return Lista de suspensiones por tipo
	 */
	public List<Suspension> consultaSuspensionesPartesPorCasoTipo(Long idCaso) {
		List<Suspension> resultado = null;
		
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT s FROM Suspension s ");
		jpqlQuery.append(" WHERE s.quienSuspende = :");
		jpqlQuery.append("quienSuspende");
		jpqlQuery.append(" AND s.idCaso = :");
		jpqlQuery.append("idCaso");
		jpqlQuery.append(" AND s.estadoRegistro =: ");
		jpqlQuery.append(Suspension.ENTIDAD_SUSPENSION_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter("quienSuspende", UtilDominios.SUSPENDIDO_POR_LAS_PARTES);
		query.setParameter("idCaso", idCaso);
		query.setParameter(Suspension.ENTIDAD_SUSPENSION_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		resultado = (List<Suspension>) query.getResultList();			
		return resultado;

	}
	
    // protected region metodos adicionales end
        
}

