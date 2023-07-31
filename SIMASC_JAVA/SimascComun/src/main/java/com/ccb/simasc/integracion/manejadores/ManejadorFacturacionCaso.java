package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.TramiteOrdinarioDTO;
import com.ccb.simasc.transversal.dto.reportes.FiltroReportesDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.FacturacionCaso;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad FacturacionCaso.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorFacturacionCaso extends ManejadorCrud<FacturacionCaso,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
	private EntityManager em;
	// protected region atributos end
    
    public ManejadorFacturacionCaso() {
        super(FacturacionCaso.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

	public List<TramiteOrdinarioDTO> casosPendienteCobroByConciliador(Date fechaDesde, Date fechaHasta,
			long idConciliador,String centrosIn) {
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append(" SELECT DISTINCT C.ID_CASO AS idCaso, ");
		nativeQuery.append(" p.id_persona AS idConciliador,");
		nativeQuery.append(" facturacion.id_facturacion_caso AS idFacturacionCaso,");
		nativeQuery.append(" C.NOMBRE AS nombreCaso ,e.fecha_evento AS fechaCierre, c.resultado,"); 
		nativeQuery.append(" ISNULL((SELECT nombre FROM    DOMINIO WHERE   codigo =c.resultado AND dominio='RESULTADO_CASO_CONCILIACION'),'') AS resultado  ,");
		nativeQuery.append(" concat(RTRIM(p.primer_nombre_o_razon_social), ' '+RTRIM(p.segundo_nombre),' '+RTRIM(p.primer_apellido), ' '+RTRIM(p.segundo_apellido)) AS conciliador,HON.VALOR                                                                                                                                              AS valorCobrar,");
		nativeQuery.append(" 'true' AS aprobado ");
		nativeQuery.append(" FROM  PERSONA P ");
		nativeQuery.append(" INNER JOIN ROL_PERSONA_CASO RPC ON RPC.ID_PERSONA = P.ID_PERSONA ");
		nativeQuery.append(" INNER JOIN CASO C ON C.ID_CASO = RPC.id_caso ");
		nativeQuery.append(" INNER JOIN SEDE S ON S.id_sede=C.id_sede ");
		nativeQuery.append(" LEFT JOIN FACTURACION_CASO facturacion on facturacion.id_caso = c.id_caso ");
		nativeQuery.append(" inner join evento e WITH (NOLOCK) on c.id_caso = e.id_caso ");
		nativeQuery.append(" Inner JOIN (SELECT  id_caso, SUM(valor) AS valor ");
		nativeQuery.append(" FROM (SELECT  r.id_caso,SUM(dr.valor) AS valor ");
		nativeQuery.append(" FROM RELIQUIDACION r INNER JOIN DETALLE_RELIQUIDACION dr ");
		nativeQuery.append(" ON r.id_reliquidacion=dr.id_reliquidacion ");
		nativeQuery.append(" WHERE servicio_caja IN (SELECT dom.codigo ");
		nativeQuery.append(" FROM  dominio dom ");
		nativeQuery.append(" WHERE dom.nombre =?3 AND dom.dominio=?1 ) ");
		nativeQuery.append(" AND  r.estado_registro =?2 AND dr.estado_registro =?2 GROUP BY  r.id_caso ");
		nativeQuery.append(" UNION ");
		nativeQuery.append(" SELECT pc.id_caso, dpc.valor ");
		nativeQuery.append(" FROM  PAGO_CASO pc ");
		nativeQuery.append(" INNER JOIN detalle_pago_caso dpc ON  dpc.numero_recibo=pc.numero_recibo ");
		nativeQuery.append(" WHERE servicio_caja IN (SELECT dom.codigo FROM dominio dom ");
		nativeQuery.append(" WHERE dom.nombre =?3 AND dom.dominio=?1) ");
		nativeQuery.append(" AND  pc.estado_registro  =?2 AND dpc.estado_registro =?2) AS t1 ");
		nativeQuery.append(" GROUP BY id_caso ) HON ON C.ID_CASO=HON.ID_CASO ");
		nativeQuery.append(" WHERE rpc.id_rol IN (SELECT id_rol  FROM    TIPO_DE_SERVICIO_ROL WHERE   tipo_servicio =?4) ");
		nativeQuery.append(" AND C.estado_caso IN (?5, ?6, ?7) AND RPC.ESTADO =?8 ");
		nativeQuery.append(" AND S.id_centro IN ( 1 ) AND p.estado_registro   =?2 ");
		nativeQuery.append(" AND RPC.estado_registro =?2 AND c.estado_registro   =?2 ");
		nativeQuery.append(" AND s.estado_registro =?2 AND c.id_servicio = 1 ");
		nativeQuery.append(" and tipo_evento in  ('CAN', 'REGISTRO') and estado_caso in  (?7, ?5) and fecha_evento between ?9 and ?10 ");
		nativeQuery.append(" and exists(select 1 from AUDIENCIA a where a.id_caso = c.id_caso and a.estado = ?12) ");
		if (Long.valueOf(idConciliador) != null && Long.valueOf(idConciliador) > 0)
			nativeQuery.append(" and p.id_persona = ?11 ");
		nativeQuery.append(" order by conciliador ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), TramiteOrdinarioDTO.class);
		query.setParameter(1, UtilDominios.DOMINIO_TIPO_SERVICIO_CAJA);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(3, UtilDominios.NOMBRE_DOMINIO_HONORARIOS_CONCILIADOR);
		query.setParameter(4, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(5, UtilDominios.ESTADO_CASO_REGISTRADO);
		query.setParameter(6, UtilDominios.ESTADO_CASO_ASIGNADO_CONTROL_DE_LEGALIDAD);
		query.setParameter(7, UtilDominios.ESTADO_CASO_CANCELADO);
		query.setParameter(8, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(12, UtilDominios.ESTADO_SORTEO_REALIZADO);
		Calendar inicio = Calendar.getInstance();
		inicio.setTime(fechaDesde);
		Calendar fin = Calendar.getInstance();
		fin.setTime(fechaHasta);
		query.setParameter(9, UtilOperaciones.setearInicioDelDia(inicio));
		query.setParameter(10, UtilOperaciones.setearFinDelDia(fin));
		if (Long.valueOf(idConciliador) != null && Long.valueOf(idConciliador) > 0)
			query.setParameter(11, idConciliador);
		return query.getResultList();
	}
    
	/**
	 * Obtiene la facturación de un caso dado el id del caso
	 * 
	 * @param idCaso
	 * @return
	 */
	public FacturacionCaso obtenerFacturacionCasoPorCaso(Long idCaso) {
		FacturacionCaso facturacion;
		StringBuilder jpqlQuery = new StringBuilder();			
		jpqlQuery.append("SELECT fc FROM FacturacionCaso fc");
		jpqlQuery.append(" WHERE fc.idCaso = :").append(FacturacionCaso.ENTIDAD_FACTURACION_CASO_ID_CASO);
		jpqlQuery.append(" AND fc.estadoRegistro = :").append(FacturacionCaso.ENTIDAD_FACTURACION_CASO_ESTADO_REGISTRO);
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(FacturacionCaso.ENTIDAD_FACTURACION_CASO_ID_CASO, idCaso);
		query.setParameter(FacturacionCaso.ENTIDAD_FACTURACION_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		try{
			facturacion = (FacturacionCaso) query.getSingleResult();
		}catch(NoResultException e){
			facturacion = null;
		}catch (NonUniqueResultException  e) {
			throw new SimascException("Error con los datos de facturación");
		}

		return facturacion;
	}
	
	/** CON-C-014: Consulta los casos de convenio que se les va a hacer facturacion
	 * @param filtros
	 * @return List<Caso> casos que se van a liquidar
	 */
	public List<Caso> consultarCasosConvenioPorFacturar( FiltroReportesDTO filtros ){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select ca.* from caso ca ");
		nativeQuery.append(" inner join convenio co ");
		nativeQuery.append(" on co.id_convenio = ca.id_convenio ");
		nativeQuery.append(" left join FACTURACION_CASO fc ");
		nativeQuery.append(" on fc.id_caso = ca.id_caso ");
		nativeQuery.append(" and fc.estado_registro = ?1 ");
		nativeQuery.append(" where fc.id_caso is null ");
		nativeQuery.append(" and ca.estado_caso = ?2 ");
		nativeQuery.append(" and ca.resultado in ( select codigo from DOMINIO where DOMINIO = ?7 ) ");
		nativeQuery.append(" and ca.fecha_estado between ?3 and ?4 ");
		nativeQuery.append(" and co.id_convenio = ?5 ");
		nativeQuery.append(" and ca.id_servicio = ?6 ");
		nativeQuery.append(" and ca.estado_registro = ?1 ");
		nativeQuery.append(" and co.estado_registro = ?1 ");
		nativeQuery.append(" order by ca.fecha_estado");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), Caso.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_CASO_REGISTRADO);
		query.setParameter(3, filtros.getFechaCierreCasoInicial());
		query.setParameter(4, filtros.getFechaCierreCasoFinal());
		query.setParameter(5, filtros.getIdConvenio());
		query.setParameter(6, UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO);
		query.setParameter(7, UtilDominios.DOMINIO_RESULTADO_CASO_CONCILIACION);
		
		return query.getResultList();
	}
    
	/**
	 * Obtiene el valor a pagar para honorarios conciliador dado un idCaso
	 * @param idCaso id del caso a consultar
	 * @return valor valor a pagar al conciliador
	 */
	public Long obtenerValorConciliador(Long idCaso){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT dpc.valor ");
		nativeQuery.append(" FROM  PAGO_CASO pc  ");
		nativeQuery.append(" INNER JOIN detalle_pago_caso dpc ON  dpc.numero_recibo=pc.numero_recibo ");
		nativeQuery.append(" WHERE servicio_caja IN (SELECT dom.codigo FROM dominio dom ");
		nativeQuery.append(" WHERE dom.nombre ='HONORARIOS CONCILIADOR' AND dom.dominio='TIPO_SERVICIO_CAJA') ");
		nativeQuery.append(" AND  pc.estado_registro  ='ACT' AND dpc.estado_registro ='ACT' ");
		nativeQuery.append(" and id_caso  = ?1 ");
		Query query = this.em.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, idCaso);
		return ((BigDecimal) query.getSingleResult()).longValue();
	}
    // protected region metodos adicionales end
        
}

