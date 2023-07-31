package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

// Escriba en esta seccion sus modificaciones

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.HonorariosFijados;
import com.ccb.simasc.transversal.entidades.PagoHonorarios;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad HonorariosFijados.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorHonorariosFijados extends ManejadorCrud<HonorariosFijados, Long> {

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	@PersistenceContext
	private transient EntityManager em;

	// protected region atributos end

	public ManejadorHonorariosFijados() {
		super(HonorariosFijados.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Consulta los honorarios fijados por el id del caso
	 * 
	 * @param idCaso
	 * @return
	 */
	public HonorariosFijados consultarPorIdCaso(Long idCaso) {

		StringBuilder jpqlQuery = new StringBuilder();
		HonorariosFijados resultado;
		try {
			jpqlQuery.append("SELECT hf FROM HonorariosFijados hf ");
			jpqlQuery.append(" WHERE hf.idCaso=:");
			jpqlQuery.append(PagoHonorarios.ENTIDAD_PAGO_HONORARIOS_ID_CASO);
			jpqlQuery.append(" AND hf.estadoRegistro=:");
			jpqlQuery.append(PagoHonorarios.ENTIDAD_PAGO_HONORARIOS_ESTADO_REGISTRO);
			Query query = mp.createQuery(jpqlQuery.toString());
			query.setParameter(PagoHonorarios.ENTIDAD_PAGO_HONORARIOS_ESTADO_REGISTRO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(PagoHonorarios.ENTIDAD_PAGO_HONORARIOS_ID_CASO, idCaso);
			resultado = (HonorariosFijados) query.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			resultado = null;
		}

		return resultado;

	}

	/**
	 * Registra las tarifas fijas
	 * 
	 * @param honorariosFijados
	 * @return
	 */
	public HonorariosFijados registrarTarifas(HonorariosFijados honorariosFijados) throws EntityExistsException {

		HonorariosFijados honorariosNuevo = new HonorariosFijados();
		try {
			honorariosFijados.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			honorariosFijados.setFechaUltimaModificacion(new Date());
			honorariosFijados.setIdUsuarioModificacion(honorariosFijados.getIdUsuarioModificacion() != null
					? honorariosFijados.getIdUsuarioModificacion() : UtilConstantes.USUARIO_DEFECTO_SIMASC);
			honorariosNuevo = (HonorariosFijados) mp.updateObject(honorariosFijados);
			em.flush();
		} catch (EntityExistsException e) {
			String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR226.toString());
			throw new SimascException(mensaje);
		}
		return honorariosNuevo;

	}

	// protected region metodos adicionales end

}
