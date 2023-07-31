package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.entidades.PagoHonorarios;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad PagoHonorarios.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorPagoHonorarios extends ManejadorCrud<PagoHonorarios,Long>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
    
    public ManejadorPagoHonorarios() {
        super(PagoHonorarios.class);
    }    
    
    // protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/**
	 * Consulta la parte en pago de honorarios
	 * 
	 * @param idCaso
	 * @param partePaga
	 * @return
	 */
	public PagoHonorarios consultarPorIdCasoYPartePaga(Long idCaso, String partePaga) {

		StringBuilder jpqlQuery = new StringBuilder();
		PagoHonorarios resultado;
		try {
			jpqlQuery.append("SELECT ph FROM PagoHonorarios ph ");
			jpqlQuery.append(" WHERE ph.idCaso=:");
			jpqlQuery.append(PagoHonorarios.ENTIDAD_PAGO_HONORARIOS_ID_CASO);
			jpqlQuery.append(" AND ph.estadoRegistro=:");
			jpqlQuery.append(PagoHonorarios.ENTIDAD_PAGO_HONORARIOS_ESTADO_REGISTRO);
			jpqlQuery.append(" AND ph.parteQuePaga=:");
			jpqlQuery.append(PagoHonorarios.ENTIDAD_PAGO_HONORARIOS_PARTE_QUE_PAGA);
			Query query = mp.createQuery(jpqlQuery.toString());
			query.setParameter(PagoHonorarios.ENTIDAD_PAGO_HONORARIOS_ESTADO_REGISTRO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(PagoHonorarios.ENTIDAD_PAGO_HONORARIOS_ID_CASO, idCaso);
			query.setParameter(PagoHonorarios.ENTIDAD_PAGO_HONORARIOS_PARTE_QUE_PAGA, partePaga);

			resultado = (PagoHonorarios) query.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			resultado = null;
		}

		return resultado;

	}
	/**
	 * 
	 * @param idCaso
	 * @return
	 */
	public List<PagoHonorarios> consultarPorIdCaso(Long idCaso) {

		StringBuilder jpqlQuery = new StringBuilder();
		List<PagoHonorarios> resultado;
		try {
			jpqlQuery.append("SELECT ph FROM PagoHonorarios ph ");
			jpqlQuery.append(" WHERE ph.idCaso=:");
			jpqlQuery.append(PagoHonorarios.ENTIDAD_PAGO_HONORARIOS_ID_CASO);
			jpqlQuery.append(" AND ph.estadoRegistro=:");
			jpqlQuery.append(PagoHonorarios.ENTIDAD_PAGO_HONORARIOS_ESTADO_REGISTRO);
			Query query = mp.createQuery(jpqlQuery.toString());
			query.setParameter(PagoHonorarios.ENTIDAD_PAGO_HONORARIOS_ESTADO_REGISTRO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(PagoHonorarios.ENTIDAD_PAGO_HONORARIOS_ID_CASO, idCaso);

			resultado = (List<PagoHonorarios>) query.getResultList();
		} catch (NoResultException | NonUniqueResultException e) {
			resultado = null;
		}

		return resultado;

	}
	
	/** alerta A-20, A-21 , Aviso de N días sobre fecha de vencimiento de pago, codigos: VENPAGO, VENPAGF
	 * @param tipoPeriodicidad
	 * @param dias
	 * @return casos con su fecha limite de pago de honorarios
	 */
	public List<InfoBasicaAlertasDTO> alertaAvisoVencimientoPagoHonorarios( String tipoPeriodicidad, Long dias ){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select distinct ca.id_caso as idCaso, ");
		nativeQuery.append(" hf.fecha_limite_de_pago as fecha, ");
		nativeQuery.append(" se.id_centro as idCentro ");
		nativeQuery.append(" from caso ca ");
		nativeQuery.append(" inner join sede se ");
		nativeQuery.append(" on se.id_sede = ca.id_sede ");
		nativeQuery.append(" inner join servicio ser ");
		nativeQuery.append(" on ser.id_servicio = ca.id_servicio ");
		nativeQuery.append(" inner join honorarios_fijados hf ");
		nativeQuery.append(" on hf.id_caso = ca.id_caso ");
		nativeQuery.append(" left join pago_honorarios ph_dte ");
		nativeQuery.append(" on ph_dte.id_caso = ca.id_caso ");
		nativeQuery.append(" and ph_dte.parte_que_paga in ( ?2 , ?3 ) ");
		nativeQuery.append(" and ph_dte.estado_registro = ?1 ");
		nativeQuery.append(" left join pago_honorarios ph_dda ");
		nativeQuery.append(" on ph_dda.id_caso = ca.id_caso ");
		nativeQuery.append(" and ph_dda.parte_que_paga in ( ?4 , ?5 ) ");
		nativeQuery.append(" and ph_dda.estado_registro = ?1 ");
		nativeQuery.append(" where ser.tipo = ?6 ");
		nativeQuery.append(" and (ph_dte.id_caso is null ");
		nativeQuery.append(" or ph_dda.id_caso is null) ");
		if (UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad)){
			nativeQuery.append(" and dbo.diasHabilesEntreDosFechas( getdate(), hf.fecha_limite_de_pago ) = ?7 ");	
		}else{
			nativeQuery.append(" and DATEDIFF( dd, GETDATE(), hf.fecha_limite_de_pago ) = ?7 ");
		}
		nativeQuery.append(" and ca.estado_registro = ?1 ");
		nativeQuery.append(" and ser.estado_registro = ?1 ");
		nativeQuery.append(" and hf.estado_registro = ?1 ");
		nativeQuery.append(" and se.estado_registro = ?1 ");
						
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE);
		query.setParameter(3, UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
		query.setParameter(4, UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);
		query.setParameter(5, UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
		query.setParameter(6, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		query.setParameter(7, dias);

		return query.getResultList();
	}
	
	/** Colsulta que determina a quien va dirigida la alerta VENPAGO (A-20), VENPAGF (A-21) y PAGCONT (A-22)
	 * el case del select solo se tiene en cuenta en la alerta PAGCONT (A-22)
	 * @param idCaso
	 * @param codigoAlerta
	 * @return
	 */
	public List<InfoBasicaAlertasDTO> consultaPartesAlertaPagoHonorarios( Long idCaso, String codigoAlerta ){
		String rolesDemandante = UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE + "," + UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE;
		String rolesDemandado = UtilDominios.ROL_PERSONA_PARTE_DEMANDADA + "," + UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO;
		String rolesParteArbitraje = rolesDemandante + "," + rolesDemandado;
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select ");
		nativeQuery.append(" rpc.id_persona as idPersona ");
		nativeQuery.append(" from rol_persona_caso rpc ");
		nativeQuery.append(" inner join rol r ");
		nativeQuery.append(" on r.id_rol = rpc.id_rol ");
		nativeQuery.append(" where r.nombre in ( select codigo_clasificado from CLASIFICADOR_DOMINIO where codigo_clasificador = ?2 and estado_registro = ?1 ) ");
		nativeQuery.append(" and r.nombre in ( ");
		nativeQuery.append(" select value from string_split( ");
		nativeQuery.append(" coalesce( ");
		nativeQuery.append(" (select case when parte_que_paga in ( ?3 , ?4 ) then ?7 ");
		nativeQuery.append(" when parte_que_paga in ( ?5 , ?6 ) then ?8 ");
		nativeQuery.append(" end ");
		nativeQuery.append(" from pago_honorarios ");
		nativeQuery.append(" where id_caso = rpc.id_caso ");
		nativeQuery.append(" and estado_registro = ?1 ) ");
		nativeQuery.append(" , ?11 ) ");
		nativeQuery.append(" , ',')) ");
		nativeQuery.append(" and rpc.id_caso = ?9 ");
		nativeQuery.append(" and rpc.estado <> ?10 ");
		nativeQuery.append(" and rpc.estado_registro = ?1 ");
		nativeQuery.append(" and r.estado_registro = ?1 ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.AGRUPADOR_ROL_PERSONA_PARTES_APLICACION);
		query.setParameter(3, UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE);
		query.setParameter(4, UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
		query.setParameter(5, UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);
		query.setParameter(6, UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
		if( UtilDominios.CODIGO_ALERTA_NO_PAGO_CONTRAPARTE_HONORARIOS.equals(codigoAlerta) ){
			query.setParameter(7, rolesDemandante);
			query.setParameter(8, rolesDemandado);
		} else{
			query.setParameter(8, rolesDemandante);
			query.setParameter(7, rolesDemandado);
		}
		query.setParameter(9, idCaso);
		query.setParameter(10, UtilDominios.ESTADO_ROL_PERSONA_CASO_INACTIVO);
		query.setParameter(11, rolesParteArbitraje);
		
		return query.getResultList();
	}
	
	public List<InfoBasicaAlertasDTO> alertaNoPagoHonorariosContraparte( String tipoPeriodicidad, Long dias ){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" WITH consulta AS ( ");
		nativeQuery.append(" select distinct se.id_centro AS idCentro, ");
		if (UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad)){
			nativeQuery.append(" dbo.diasHabilesEntreDosFechas( hf.fecha_limite_de_pago, getdate() ) AS habil, ");	
		}else{
			nativeQuery.append(" DATEDIFF( dd, hf.fecha_limite_de_pago, GETDATE() ) AS calendario, ");
		}
		nativeQuery.append(" case when ph_dda.parte_que_paga is not null ");
			nativeQuery.append(" then ( select nombre from dominio where codigo = ?2 and dominio = ?8 ) ");
			nativeQuery.append(" when ph_dte.parte_que_paga is not null ");
			nativeQuery.append(" then ( select nombre from dominio where codigo = ?4 and dominio = ?8 ) ");
		nativeQuery.append(" end as nombreRol, ");
		nativeQuery.append(" ca.id_caso AS idCaso ");
		nativeQuery.append(" from caso ca ");
		nativeQuery.append(" inner join sede se ");
		nativeQuery.append(" on se.id_sede = ca.id_sede ");
		nativeQuery.append(" inner join servicio ser ");
		nativeQuery.append(" on ser.id_servicio = ca.id_servicio ");
		nativeQuery.append(" inner join honorarios_fijados hf ");
		nativeQuery.append(" on hf.id_caso = ca.id_caso ");
		nativeQuery.append(" left join pago_honorarios ph_dte ");
		nativeQuery.append(" on ph_dte.id_caso = ca.id_caso ");
		nativeQuery.append(" and ph_dte.parte_que_paga in ( ?2 , ?3 ) ");
		nativeQuery.append(" and ph_dte.estado_registro = ?1 ");
		nativeQuery.append(" left join pago_honorarios ph_dda ");
		nativeQuery.append(" on ph_dda.id_caso = ca.id_caso ");
		nativeQuery.append(" and ph_dda.parte_que_paga in ( ?4 , ?5 ) ");
		nativeQuery.append(" and ph_dda.estado_registro = ?1 ");
		nativeQuery.append(" where ser.tipo = ?6 ");
		nativeQuery.append(" and ((ph_dte.id_caso is not null and ph_dda.id_caso is null) or ((ph_dte.id_caso is null and ph_dda.id_caso is not null))) ");
		nativeQuery.append(" and ca.estado_registro = ?1 ");
		nativeQuery.append(" and ser.estado_registro = ?1 ");
		nativeQuery.append(" and hf.estado_registro = ?1 ");
		nativeQuery.append(" and se.estado_registro = ?1 ) ");
		
		nativeQuery.append(" SELECT co.idCentro, co.idCaso, ");
		nativeQuery.append(" co.nombreRol ");
		nativeQuery.append(" FROM consulta co ");
		if (UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad)){
			nativeQuery.append(" WHERE co.habil = ?7 ");	
		}else{
			nativeQuery.append(" WHERE co.calendario = ?7 ");
		}

		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE);
		query.setParameter(3, UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
		query.setParameter(4, UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);
		query.setParameter(5, UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
		query.setParameter(6, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		query.setParameter(7, dias);
		query.setParameter(8, UtilDominios.DOMINIO_ROL_PERSONA_PARTE);
		
		return query.getResultList();
	}
	
	// protected region metodos adicionales end
        
}

