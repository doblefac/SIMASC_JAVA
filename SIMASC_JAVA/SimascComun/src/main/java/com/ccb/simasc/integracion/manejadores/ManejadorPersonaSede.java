package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.ParametrosSedesRepartoDTO;
import com.ccb.simasc.transversal.dto.SedeDTO;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaSede;
import com.ccb.simasc.transversal.entidades.PersonaSedePK;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.entidades.Sede;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad PersonaSede.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorPersonaSede extends ManejadorCrud<PersonaSede,PersonaSedePK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	private static final String NO_SEDES_DISPONIBLES_PERSONA = MensajesSimasc.getInstancia()
			.getMensajePorKey(MensajesEnum.ERROR478.toString());
	private static final String NO_SEDES_DISPONIBLES_FECHA = MensajesSimasc.getInstancia()
			.getMensajePorKey(MensajesEnum.ERROR479.toString());
	private static final String NO_SEDES_DISPONIBLES_HORA = MensajesSimasc.getInstancia()
			.getMensajePorKey(MensajesEnum.ERROR480.toString());
	private static final String NO_SEDES_DISPONIBLES = MensajesSimasc.getInstancia()
			.getMensajePorKey(MensajesEnum.ERROR481.toString());


	// protected region atributos end
    
    public ManejadorPersonaSede() {
        super(PersonaSede.class);
    }    
    
    // protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Obtiene todas las PersonaSede de una persona
	 * 
	 * @param idPersona
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PersonaSede> obtenerPersonaSedesPorPersona(Long idPersona) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT ps FROM PersonaSede ps ");
		jpqlQuery.append(" WHERE ps.personaSedePK.idPersona = :");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);

		return query.getResultList();
	}

	/**
	 * Metodo que retorna una lista de sedes correspondeintes a una persona y un
	 * tipo de sede
	 * 
	 * @param idPersona
	 * @param tipoSede
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Sede> obtenerSedesPorPersonaYTipoSedeActivas(Long idPersona, String tipoSede, Long idServicio) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT s.* From SEDE s  ");
		nativeQuery.append(" INNER JOIN PERSONA_SEDE ps on ps.id_sede = s.id_sede  ");
		nativeQuery.append(" INNER JOIN SERVICIO_SEDE ss on ss.id_sede = ps.id_sede ");
		nativeQuery.append(" WHERE s.estado_registro = ?1 AND ps.estado_registro = ?1 AND ss.estado_registro = ?1"); 
		if(idPersona != null){
			nativeQuery.append(" AND ps.id_persona= ?2");		
		}
		if(tipoSede != null){
			nativeQuery.append(" AND s.tipo_sede= ?3");		
		}
		if(idServicio != null){
			nativeQuery.append(" AND ss.id_servicio= ?4");		
		}
		
		Query q = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), Sede.class);
		q.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if(idPersona != null){
			q.setParameter(2,idPersona);	
		}
		if(tipoSede != null){
			q.setParameter(3,tipoSede);		
		}
		if(idServicio != null){
			q.setParameter(4,idServicio);	
		}
		
		
		return q.getResultList();
		
	}
	
	/**
	 * Metodo que retorna una lista de sedes correspondeintes a una persona y un
	 * tipo de sede
	 * 
	 * @param idPersona
	 * @param tipoSede
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Sede> obtenerSedesPorPersonaYTipoSede(Long idPersona, String tipoSede) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT ps.sede FROM PersonaSede ps");
		jpqlQuery.append(" WHERE ps.persona.idPersona=:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);
		if(tipoSede != null && !tipoSede.equals(UtilConstantes.VALOR_UNDEFINED)) {
			jpqlQuery.append(" AND ps.sede.tipoSede =:");
			jpqlQuery.append(Sede.ENTIDAD_SEDE_TIPO_SEDE);			
		}
		jpqlQuery.append(" AND ps.estadoRegistro =:");
		jpqlQuery.append(PersonaSede.ENTIDAD_PERSONA_SEDE_ESTADO_REGISTRO_PERSONASEDE);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		if(tipoSede != null && !tipoSede.equals(UtilConstantes.VALOR_UNDEFINED))
			query.setParameter(Sede.ENTIDAD_SEDE_TIPO_SEDE, tipoSede);
		query.setParameter(PersonaSede.ENTIDAD_PERSONA_SEDE_ESTADO_REGISTRO_PERSONASEDE,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();
	}


	/**
	 * Metodo que retorna una lista de sedes de acuerdo a los parametros
	 * enviados en la modificacion de parametros para reparto CONF103.
	 * 
	 * @param parametrosSedesRepartoDTO:
	 *            DTO de filtros para consultar las sedes.
	 * @return List<PersonaSede>.
	 */
	@SuppressWarnings("unchecked")
	public List<SedeDTO> obtenerSedesPorParametros(ParametrosSedesRepartoDTO parametrosSedesReparto) {

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT DISTINCT(s.id_sede) AS idSede, ");
		nativeQuery.append("s.nombre AS  nombre ");
		nativeQuery.append("FROM SEDE s ");

		nativeQuery.append("INNER JOIN SERVICIO_SEDE ss ON ss.id_sede = s.id_sede AND ss.id_servicio =?3 ");
		nativeQuery.append("AND ss.estado_registro =?2 ");

		if (parametrosSedesReparto.getIdConciliador() != null) {
			nativeQuery.append("INNER JOIN PERSONA_SEDE ps ON ps.id_sede = s.id_sede AND ps.id_persona =?4 ");
			nativeQuery.append("AND ps.estado_registro =?2 ");
			nativeQuery.append("INNER JOIN DISPONIBILIDAD_PERSONA dp on dp.id_persona = ps.id_persona ");
			
		}

		if (parametrosSedesReparto.getFormatoHora() != null
				&& parametrosSedesReparto.getFormatoHora().getFechaInicio() != null
				&& parametrosSedesReparto.getFormatoHora().getFechaFin() != null) {
			nativeQuery.append("INNER JOIN SALA sa ON sa.id_sede = s.id_sede ");
			nativeQuery.append("AND sa.estado_registro =?2 AND sa.id_sala NOT IN ( ");
			nativeQuery.append("SELECT id_sala FROM AGENDAMIENTO a ");
			nativeQuery.append("WHERE a.estado_registro = ?2 ");
			nativeQuery.append("AND  ((DATEADD(s,1, ?5 ) BETWEEN a.hora_inicio AND a.hora_fin) ");
			nativeQuery.append("OR (DATEADD(s,-1, ?6 ) BETWEEN a.hora_inicio AND a.hora_fin) ) ) ");
		}
		
		nativeQuery.append("WHERE s.estado_registro =?2 AND s.id_centro =?1  ");
		if (parametrosSedesReparto.getFormatoHora() != null
				&& parametrosSedesReparto.getFormatoHora().getFechaInicio() != null
				&& parametrosSedesReparto.getFormatoHora().getFechaFin() != null
				&& parametrosSedesReparto.getIdConciliador() != null){			
			nativeQuery.append("AND dp.codigo = datepart(dw, ?5) ");
			nativeQuery.append(" AND convert (time, ?5, 108 ) between dp.hora_inicio and dp.hora_fin ");
			nativeQuery.append(" AND convert (time, ?6, 108 ) between dp.hora_inicio and dp.hora_fin ");
		}
		nativeQuery.append("ORDER BY s.nombre");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), SedeDTO.class);

		query.setParameter(1, parametrosSedesReparto.getIdCentro());
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(3, parametrosSedesReparto.getIdServicio());

		if (parametrosSedesReparto.getIdConciliador() != null) {
			query.setParameter(4, parametrosSedesReparto.getIdConciliador());
		}

		if (parametrosSedesReparto.getFormatoHora() != null
				&& parametrosSedesReparto.getFormatoHora().getFechaInicio() != null
				&& parametrosSedesReparto.getFormatoHora().getFechaFin() != null) {
			query.setParameter(5, parametrosSedesReparto.getFormatoHora().getFechaInicio());
			query.setParameter(6, parametrosSedesReparto.getFormatoHora().getFechaFin());
		}

		List<SedeDTO> listaSedes = query.getResultList();

		if (listaSedes.isEmpty() && parametrosSedesReparto.getIdConciliador() != null
				&& (parametrosSedesReparto.getFormatoHora() == null
						|| parametrosSedesReparto.getFormatoHora().getFechaInicio() == null)) {
			throw new SimascException(NO_SEDES_DISPONIBLES_PERSONA);
		} else if (listaSedes.isEmpty() && parametrosSedesReparto.getIdConciliador() == null
				&& (parametrosSedesReparto.getListaHoras() != null
						&& !parametrosSedesReparto.getListaHoras().isEmpty())) {
			throw new SimascException(NO_SEDES_DISPONIBLES_FECHA);
		} else if (listaSedes.isEmpty() && parametrosSedesReparto.getIdConciliador() == null
				&& parametrosSedesReparto.getListaHoras() == null && parametrosSedesReparto.getFormatoHora() != null
				&& parametrosSedesReparto.getFormatoHora().getFechaInicio() != null
				&& parametrosSedesReparto.getFormatoHora().getFechaFin() != null) {
			throw new SimascException(NO_SEDES_DISPONIBLES_HORA);
		} else if (listaSedes.isEmpty() && parametrosSedesReparto.getIdConciliador() != null
				&& parametrosSedesReparto.getListaHoras() != null) {
			throw new SimascException(NO_SEDES_DISPONIBLES);
		} else {
			return listaSedes;
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<Sede> obtenerSedesPorCentros(List<Long> idCentros){		
		String centrosIn = UtilConsultasSQL.clausulaInSQLSNumeros(idCentros);
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT s FROM Sede s ");
		jpqlQuery.append(" WHERE s.centro.idCentro ");		 		
		jpqlQuery.append(centrosIn);
		jpqlQuery.append(" and s.estadoRegistro =: " );
		jpqlQuery.append(Sede.ENTIDAD_SEDE_ESTADO_REGISTRO);   
		jpqlQuery.append(" order by s.nombre " );
		Query query = mp.getEntityManager().createQuery(jpqlQuery.toString(), Sede.class);
		query.setParameter(Sede.ENTIDAD_SEDE_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();
	}

	/** 12-02-2018 
	 * query que consulta una lista de personas de una sede por una lista de roles
	 * @author prendon
	 * @param idSede
	 * @param nombreRol
	 * @return List<Persona>
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> consultarPersonaPorSedeRol( Long idSede, List<String> nombreRoles ){
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT distinct p FROM PersonaSede ps ");
		jpqlQuery.append(" JOIN ps.persona p ");
		jpqlQuery.append(" JOIN p.rolPersonaList rp ");
		jpqlQuery.append(" WHERE ps.estadoRegistro = : ").append(PersonaSede.ENTIDAD_PERSONA_SEDE_ESTADO_REGISTRO);
		jpqlQuery.append(" AND ps.sede.idSede = : ").append(Sede.ENTIDAD_SEDE_PK);
		jpqlQuery.append(" AND rp.rol.nombre in : ").append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND p.estadoRegistro = : ").append(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO);
		jpqlQuery.append(" AND rp.estadoRegistro = : ").append(RolPersona.ENTIDAD_ROL_PERSONA_ESTADO_REGISTRO);
		jpqlQuery.append(" AND rp.rol.estadoRegistro = : ").append(Rol.ENTIDAD_ROL_ESTADO_REGISTRO);
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Sede.ENTIDAD_SEDE_PK, idSede);
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombreRoles);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(PersonaSede.ENTIDAD_PERSONA_SEDE_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(RolPersona.ENTIDAD_ROL_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Rol.ENTIDAD_ROL_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return query.getResultList();	
	}
	
	// protected region metodos adicionales end
        
}

