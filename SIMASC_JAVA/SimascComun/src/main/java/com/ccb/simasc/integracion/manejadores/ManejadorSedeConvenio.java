package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Convenio;
import com.ccb.simasc.transversal.entidades.Sede;
import com.ccb.simasc.transversal.entidades.SedeConvenio;
import com.ccb.simasc.transversal.entidades.SedeConvenioPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad SedeConvenio.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorSedeConvenio extends ManejadorCrud<SedeConvenio,SedeConvenioPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
	private transient EntityManager em;

	// protected region atributos end
    
    public ManejadorSedeConvenio() {
        super(SedeConvenio.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    public List<SedeConvenio> consultarSedesConvenio(Long idConvenio) {
		StringBuilder jpqlQuery = new StringBuilder();

		jpqlQuery.append("SELECT s FROM SedeConvenio s ");
		jpqlQuery.append(" WHERE s.sedeConvenioPK.idConvenio = :");
		jpqlQuery.append(Convenio.ENTIDAD_CONVENIO_PK);
		jpqlQuery.append(" AND s.estadoRegistro = :");
		jpqlQuery.append(SedeConvenio.ENTIDAD_SEDE_CONVENIO_ESTADO_REGISTRO);			

		Query query = em.createQuery(jpqlQuery.toString(), SedeConvenio.class);

		query.setParameter(Convenio.ENTIDAD_CONVENIO_PK, idConvenio);
		query.setParameter(SedeConvenio.ENTIDAD_SEDE_CONVENIO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		

		return query.getResultList();
	}

	/**
	 * Consultar centros por convenio.
	 * 
	 * @param idConvenio:
	 *            Identificador del convenio.
	 * @return List<Long> : Lista de centros.
	 */
	public List<Long> consultarCentrosPorConvenio(Long idConvenio) {
		StringBuilder jpqlQuery = new StringBuilder();

		jpqlQuery.append(" SELECT DISTINCT(s.idCentro) FROM SedeConvenio sc JOIN sc.sede s ");
		jpqlQuery.append(" WHERE sc.sedeConvenioPK.idConvenio = :");
		jpqlQuery.append(Convenio.ENTIDAD_CONVENIO_PK);
		jpqlQuery.append(" AND sc.estadoRegistro = :");
		jpqlQuery.append(SedeConvenio.ENTIDAD_SEDE_CONVENIO_ESTADO_REGISTRO);
		jpqlQuery.append(" AND s.estadoRegistro = :");
		jpqlQuery.append(Sede.ENTIDAD_SEDE_ESTADO_REGISTRO);
		Query query = em.createQuery(jpqlQuery.toString());

		query.setParameter(Convenio.ENTIDAD_CONVENIO_PK, idConvenio);
		query.setParameter(SedeConvenio.ENTIDAD_SEDE_CONVENIO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Sede.ENTIDAD_SEDE_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return query.getResultList();
	}
    
    // protected region metodos adicionales end
        
}

