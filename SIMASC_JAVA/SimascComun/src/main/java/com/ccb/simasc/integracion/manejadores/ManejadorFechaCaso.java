package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.FechaCaso;
import com.ccb.simasc.transversal.entidades.FechaCasoPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad FechaCaso.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorFechaCaso extends ManejadorCrud<FechaCaso,FechaCasoPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorFechaCaso() {
        super(FechaCaso.class);
    }    
    
    // protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	
	/**
	 * Actualiza o crea un registro en fechaCaso
	 * 
	 * @param fechaCaso
	 * @return
	 */
	public FechaCaso actualizarFechaCaso(FechaCaso fechaCaso) {
		return (FechaCaso) mp.updateObject(fechaCaso);

	}
	
	
	/**
	 * Consulta las fechas de un caso por tipo y por caso
	 * @param idCaso
	 * @param tipoFecha
	 * @return
	 */
	public Date consultaFechaPorCasoTipo(Long idCaso, String tipoFecha) {
		Date resultado = null;
		try {
			StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append(" SELECT fc.fecha FROM FechaCaso fc ");
			jpqlQuery.append(" WHERE fc.fechaCasoPK.tipoFecha =: ");
			jpqlQuery.append("tipoFecha");
			jpqlQuery.append(" AND fc.caso.idCaso =: ");
			jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
			jpqlQuery.append(" AND fc.estadoRegistro =: ");
			jpqlQuery.append(FechaCaso.ENTIDAD_FECHA_CASO_ESTADO_REGISTRO);

			Query query = mp.createQuery(jpqlQuery.toString());
			query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
			query.setParameter("tipoFecha", tipoFecha);
			query.setParameter(FechaCaso.ENTIDAD_FECHA_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

			resultado = (Date) query.getSingleResult();
		} catch (NoResultException e) {
			resultado = null;
		}
		return resultado;

	}
	
	
	/**
	 * Consulta las fechas de un caso por tipo y por caso
	 * @param idCaso
	 * @param tipoFecha
	 * @return
	 */
	public FechaCaso consultaFechaCaso(Long idCaso, String tipoFecha) {
		FechaCaso resultado = null;
		FechaCasoPK pk = new FechaCasoPK(tipoFecha, idCaso);	
		try {
			resultado = buscar(pk);
		} catch (NoResultException e) {
			resultado = null;
		}
		return resultado;
	}
	
	// protected region metodos adicionales end
        
}

