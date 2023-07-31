/**
 * 29/10/2018
 * @author jnmartinez
 */
package com.ccb.simasc.integracion.manejadores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.transversal.dto.AlertaDTO;
import com.ccb.simasc.transversal.entidades.Alerta;
import com.ccb.simasc.transversal.entidades.ProgramacionAlerta;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

/**
 * @author jnmartinez
 *
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class ManejadorAlertasPendientes {
	private static final Logger logger = LogManager.getLogger(ManejadorAlertasPendientes.class.getName());
	@Resource
	private UserTransaction userTransaction;

	@PersistenceContext
	private EntityManager em;

	@EJB
	private ManejadorAlerta manejadorAlerta;
	
	public List<ProgramacionAlerta> consultarProgramacionesEnEjecucion()
	{
		List<ProgramacionAlerta> programaciones = new ArrayList<>();
		if(consultarProgramacionesPendientes()) {
			StringBuilder nativeQuery = new StringBuilder();
			nativeQuery.append("update programacion_alerta set estado = ?3 ");
			nativeQuery.append(" where estado = ?1");
			nativeQuery.append(" and fecha_ejecucion <= ?2");
			
			try {
				userTransaction.begin();				
				Query query = em.createNativeQuery(nativeQuery.toString());
				query.setParameter(1, UtilDominios.PROGRAMACION_ALERTA_PENDIENTE);
				query.setParameter(2, new Date());
				query.setParameter(3, UtilDominios.PROGRAMACION_ALERTA_EN_EJECUCION);
				query.executeUpdate();
				userTransaction.commit();
			} catch (Exception e) {
				try {
					if (userTransaction.getStatus() == 0) {
						userTransaction.rollback();
					}
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					logger.error("Error: ", e1);
				}
			}
			nativeQuery = new StringBuilder();
			nativeQuery.append("select p from ProgramacionAlerta p ");
			nativeQuery.append(" where p.estado = :").append(ProgramacionAlerta.ENTIDAD_PROGRAMACION_ALERTA_ESTADO);
			
			Query query = em.createQuery(nativeQuery.toString());
			query.setParameter(ProgramacionAlerta.ENTIDAD_PROGRAMACION_ALERTA_ESTADO, UtilDominios.PROGRAMACION_ALERTA_EN_EJECUCION);
			
			programaciones = query.getResultList();
			
		}
		return programaciones;
	}
	public List<Alerta> consultarAlertasEnEjecucion(AlertaDTO filtros) {
		List<Alerta> alertas = new ArrayList<>();
		if (consultarAlertasPendientes(filtros)) {
			StringBuilder nativeQuery = new StringBuilder();
			nativeQuery.append("UPDATE alerta set estado_ejecucion = ?7");
			nativeQuery.append(" where estado_registro = ?8");
			construirConsulta(filtros, nativeQuery);

			try {
				userTransaction.begin();
				Query query = em.createNativeQuery(nativeQuery.toString());
				construirParametros(filtros, query);
				query.setParameter(7, UtilDominios.PROGRAMACION_ALERTA_EN_EJECUCION);
				query.setParameter(8, UtilDominios.ESTADO_REGISTRO_ACTIVO);
				query.executeUpdate();
				userTransaction.commit();
			} catch (Exception e) {
				try {
					if (userTransaction.getStatus() == 0) {
						userTransaction.rollback();
					}
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					logger.error("Error: ", e1);
				}

			}

			nativeQuery = new StringBuilder();
			nativeQuery.append("select * from alerta");
			nativeQuery.append(" where estado_ejecucion = ?1");
			nativeQuery.append(" and estado_registro = ?2");
			nativeQuery.append(" order by tipo_servicio, nombre");

			Query query = em.createNativeQuery(nativeQuery.toString(), Alerta.class);
			query.setParameter(1, UtilDominios.PROGRAMACION_ALERTA_EN_EJECUCION);
			query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);

			alertas = query.getResultList();
		}

		return alertas;
	}
	
	public boolean consultarAlertasPendientes(AlertaDTO filtros) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select 1 from alerta ");
		nativeQuery.append(" where estado_registro = ?8");
		construirConsulta(filtros, nativeQuery);
		
		Query query = em.createNativeQuery(nativeQuery.toString());
		construirParametros(filtros, query);
		query.setParameter(8, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return !query.getResultList().isEmpty();
	}
	
	public boolean consultarProgramacionesPendientes() {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select 1 from programacion_alerta ");
		nativeQuery.append(" where estado = ?1");
		nativeQuery.append(" and fecha_ejecucion <= ?2");
		
		Query query = em.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, UtilDominios.PROGRAMACION_ALERTA_PENDIENTE);
		query.setParameter(2, new Date());
		
		return !query.getResultList().isEmpty();
	}
	
	private void construirConsulta(AlertaDTO filtros, StringBuilder nativeQuery) {
		if (filtros.getTipoServicio() != null)
			nativeQuery.append(" AND tipo_servicio = ?1");

		if (filtros.getEstado() != null)
			nativeQuery.append(" AND estado = ?2");

		if (filtros.getTipoAlerta() != null)
			nativeQuery.append(" AND tipo_alerta = ?3");

		if (filtros.getEstadoEjecucion() != null)
			nativeQuery.append(" AND estado_ejecucion = ?4");

		if (filtros.getCodigo() != null)
			nativeQuery.append(" AND codigo = ?5");

		if (filtros.getHoraEjecucion() != null)
			nativeQuery.append(" AND hora_ejecucion <= ?6");
	}
	
	private void construirParametros(AlertaDTO filtros, Query query) {
		if (filtros.getTipoServicio() != null)
			query.setParameter(1, filtros.getTipoServicio());
		if (filtros.getEstado() != null)
			query.setParameter(2, filtros.getEstado());
		if (filtros.getTipoAlerta() != null)
			query.setParameter(3, filtros.getTipoAlerta());
		if (filtros.getEstadoEjecucion() != null)
			query.setParameter(4, filtros.getEstadoEjecucion());
		if (filtros.getCodigo() != null)
			query.setParameter(5, filtros.getCodigo());
		if (filtros.getHoraEjecucion() != null)
			query.setParameter(6, filtros.getHoraEjecucion());
	}
	
	public boolean consultarFalloAlerta(Long idAlertaProgramacion, boolean isAlerta) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select 1 from fallo_alerta");
		if(isAlerta)
			nativeQuery.append(" where id_alerta = ?1");
		else
			nativeQuery.append(" where id_programacion_alerta = ?1");

		Query query = em.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, idAlertaProgramacion);
		
		return !query.getResultList().isEmpty();
	}

}
