package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import java.util.logging.Logger;

// Escriba en esta seccion sus modificaciones

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.FalloAlerta;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad FalloAlerta.
 * 
 * @author jsoto
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class ManejadorFalloAlerta extends ManejadorCrud<FalloAlerta, Long> {

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@Resource
	private UserTransaction userTransaction;

	private static final Logger LOGGER = Logger.getLogger(ManejadorFalloAlerta.class.getName());
	
	// protected region atributos end

	public ManejadorFalloAlerta() {
		super(FalloAlerta.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Metodo encargado de actualiza los fallos de las alertas y programaciones
	 */
	public void actualizarFallosAlerta() {
		LOGGER.info("actualizacion fallos 1.4");

		StringBuilder nativeQuery = new StringBuilder();

		nativeQuery.append(" UPDATE pa set estado = fa.estado ");
		nativeQuery.append(" FROM PROGRAMACION_ALERTA pa ");
		nativeQuery.append(" INNER JOIN FALLO_ALERTA fa ");
		nativeQuery.append(" ON pa.id_programacion_alerta = fa.id_programacion_alerta; ");

		nativeQuery.append("UPDATE a SET estado_ejecucion = fa.estado ");
		nativeQuery.append(" FROM ALERTA a ");
		nativeQuery.append(" INNER JOIN FALLO_ALERTA fa ");
		nativeQuery.append(" ON a.id_alerta = fa.id_alerta;");

		nativeQuery.append(" DELETE FROM FALLO_ALERTA ");

		try {
			userTransaction.begin();
			Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString());
			query.executeUpdate();
			userTransaction.commit();
		} catch (Exception e) {
			try {
				if (userTransaction.getStatus() == 0) {
					userTransaction.rollback();
				}
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				LOGGER.info(e1.getMessage());
			}
			LOGGER.info(e.getMessage());
		}
	

	}

	// protected region metodos adicionales end

}
