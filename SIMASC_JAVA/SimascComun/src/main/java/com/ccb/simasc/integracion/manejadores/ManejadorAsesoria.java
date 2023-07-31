package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.AsesoriaDTO;
import com.ccb.simasc.transversal.entidades.Asesoria;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad Asesoria.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorAsesoria extends ManejadorCrud<Asesoria, Long> {

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
	private EntityManager em;
	// protected region atributos end

	public ManejadorAsesoria() {
		super(Asesoria.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	@SuppressWarnings("unchecked")
	public List<AsesoriaDTO> consultaAsesorias(AsesoriaDTO filtros, String inIdCentros) {
		String personaAsesorada = " concat(rtrim(p.primer_nombre_o_razon_social),' ',rtrim(p.segundo_nombre), "
				+ " ' ',rtrim(p.primer_apellido),' ',rtrim(p.segundo_apellido)) as personaAsesorada, ";
		String funcionario = " concat(rtrim(f.primer_nombre_o_razon_social),' ',rtrim(f.segundo_nombre), "
				+ "' ',rtrim(f.primer_apellido),' ',rtrim(f.segundo_apellido)) as funcionario ";

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select	a.id_asesoria as idAsesoria, ");
		nativeQuery.append(" p.id_persona as idPersona, ");
		nativeQuery.append(personaAsesorada);
		nativeQuery.append(" a.fecha_asesoria as fechaAsesoria, ");
		nativeQuery.append(" s.nombre as servicio, ");
		nativeQuery.append(" m.nombre as tipoConsulta, ");
		nativeQuery.append(" a.observaciones, ");
		nativeQuery.append(funcionario);
		nativeQuery.append(" from asesoria a ");
		nativeQuery.append(" inner join persona p on p.id_persona=a.id_persona ");
		nativeQuery.append(" and p.estado_registro=a.estado_registro ");
		nativeQuery.append(" inner join materia m on m.id_materia=a.id_materia ");
		nativeQuery.append(" and m.estado_registro=a.estado_registro ");
		nativeQuery.append(" inner join servicio s on s.id_servicio=a.id_servicio ");
		nativeQuery.append(" and s.estado_registro=a.estado_registro ");
		nativeQuery.append(" inner join persona f on f.id_persona=a.id_persona_asesora ");
		nativeQuery.append(" and f.estado_registro=a.estado_registro ");
		nativeQuery.append(" where a.estado_registro=?1 ");
		nativeQuery.append(" and s.tipo= ?5 ");
		nativeQuery.append(" and exists (select 1 from rol_persona rp where f.id_persona=rp.id_persona ");
		nativeQuery.append(" and rp.id_centro ").append(inIdCentros).append(") ");
		nativeQuery.append(" and cast(a.fecha_asesoria as date) between cast(?2 as date) and cast(?3 as date) ");

		if (Long.valueOf(filtros.getIdPersonaAsesora()) > 0 )
			nativeQuery.append(" and a.id_persona_asesora= ?4 ");
		Query query = em.createNativeQuery(nativeQuery.toString(), AsesoriaDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, filtros.getFechaInicio());
		query.setParameter(3, filtros.getFechaHasta());
		if (Long.valueOf(filtros.getIdPersonaAsesora()) > 0)
			query.setParameter(4, filtros.getIdPersonaAsesora());
		query.setParameter(5, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Persona> listadoIdAsesores(String inIdCentros) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" select distinct p from Persona p, Asesoria a ,RolPersona rp ");
		jpqlQuery.append(" where p.idPersona = a.idPersonaAsesora ");
		jpqlQuery.append(" and p.estadoRegistro = a.estadoRegistro ");
		jpqlQuery.append(" and p.idPersona=rp.idPersona ");
		jpqlQuery.append(" and a.estadoRegistro =: ");
		jpqlQuery.append(Asesoria.ENTIDAD_ASESORIA_ESTADO_REGISTRO);
		jpqlQuery.append(" and rp.idCentro ").append(inIdCentros);

		Query query = em.createQuery(jpqlQuery.toString(), Persona.class);
		query.setParameter(Asesoria.ENTIDAD_ASESORIA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();
	}
	// protected region metodos adicionales end

}
