package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;

// Escriba en esta seccion sus modificaciones

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.ValorHonorariosActor;
import com.ccb.simasc.transversal.entidades.ValorHonorariosActorPK;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad ValorHonorariosActor.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorValorHonorariosActor extends ManejadorCrud<ValorHonorariosActor, ValorHonorariosActorPK> {

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end

	public ManejadorValorHonorariosActor() {
		super(ValorHonorariosActor.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Registra un actor con sus respectivos valores
	 * 
	 * @param honorariosActor
	 * @return
	 */
	public ValorHonorariosActor registrarHonorariosActor(ValorHonorariosActor honorariosActor)
			throws EntityExistsException {
		try {
			honorariosActor.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			honorariosActor.setFechaUltimaModificacion(new Date());
			honorariosActor.setIdUsuarioModificacion(honorariosActor.getIdUsuarioModificacion() != null
					? honorariosActor.getIdUsuarioModificacion() : UtilConstantes.USUARIO_DEFECTO_SIMASC);
		} catch (EntityExistsException e) {
			String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR226.toString());
			throw new SimascException(mensaje);
		}
		return (ValorHonorariosActor) mp.updateObject(honorariosActor);

	}

	// protected region metodos adicionales end

}
