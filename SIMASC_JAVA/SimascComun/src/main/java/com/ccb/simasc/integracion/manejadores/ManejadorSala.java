package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.SalaDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltrosSalasDTO;
import com.ccb.simasc.transversal.entidades.Agendamiento;
import com.ccb.simasc.transversal.entidades.Sala;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Sala.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorSala extends ManejadorCrud<Sala,Long>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@PersistenceContext
	private transient EntityManager em;
	// protected region atributos end
    
    public ManejadorSala() {
        super(Sala.class);
    }    
    
    // protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/**
	 * Devuelve los casos que tienen un sorteo cuya fecha programada es la fecha
	 * que se pasa como parámetro
	 * 
	 * @param fecha
	 *            Fecha programada para el sorteo de los casos
	 * @return Lista de casos
	 */
	@SuppressWarnings("unchecked")
	public List<Object> consultarSalasOcupadas(Date fecha, String caso, String nombreCaso, String nombreSede) {

		StringBuilder jpqlQuery = new StringBuilder();

		jpqlQuery.append(" SELECT c ");
		jpqlQuery.append(" FROM Caso c");
		jpqlQuery.append(" JOIN c.audienciaList a");
		jpqlQuery.append(" JOIN a.sala s");
		jpqlQuery.append(" JOIN s.sede d");
		jpqlQuery.append(" LEFT JOIN c.rolPersonaCasoList rpc");
		jpqlQuery.append(" JOIN rpc.persona p");

		if (fecha != null || !caso.equals("") || caso != null || nombreCaso != null || !nombreCaso.equals("")
				|| nombreSede != null || !nombreSede.equals("")) {
			jpqlQuery.append(" WHERE ");
		}
		if (fecha != null)
			jpqlQuery.append(" a.horaInicio=:horaInicio AND");

		if (nombreCaso != null && !nombreCaso.equals(""))
			jpqlQuery.append(" c.nombre=:nombre AND");

		if (caso != null && !caso.equals(""))
			jpqlQuery.append(" c.idCaso=:idCaso AND");

		if (nombreSede != null && !nombreSede.equals(""))
			jpqlQuery.append(" d.nombre=:nombreSede AND");

		jpqlQuery.delete(jpqlQuery.length() - 4, jpqlQuery.length() - 1);
		jpqlQuery.append(" ORDER BY d.nombre ASC, s.numeroSala,a.horaInicio");

		Query query = mp.createQuery(jpqlQuery.toString());
		// query.setParameter(Audiencia.ENTIDAD_AUDIENCIA_HORA_INICIO, new
		// Date(fecha.getTime()) );
		// query.setParameter(Caso.ENTIDAD_CASO_NOMBRE, nombreCaso);
		// query.setParameter(Audiencia.ENTIDAD_AUDIENCIA_ID_CASO, caso);
		// query.setParameter("nombreSede", nombreSede);

		return query.getResultList();
	}

	/**
	 * Metodo que consulta las salas por filtros
	 * 
	 * @param filtrosSalasDTO
	 * @return List<SalaDTO>
	 */
	@SuppressWarnings("unchecked")
	public List<Sala> buscarSalaFiltros(FiltrosSalasDTO filtrosSalasDTO) {
		Query q = em.createNativeQuery(
				"SELECT sa.* FROM sede se " + "INNER JOIN sala sa ON se.id_sede = sa.id_sede "
						+ "LEFT JOIN AGENDAMIENTO ag ON sa.id_sala = ag.id_sala "
						+ "WHERE CAST(ag.hora_inicio AS DATE) = ?1 " + "AND se.id_sede = ?2 ",
				Sala.class);
		q.setParameter(1, new Date(filtrosSalasDTO.getFechaSolicitud().getTime()), TemporalType.DATE);
		q.setParameter(2, filtrosSalasDTO.getIdSala());
		return q.getResultList();
	}
	/**
	 * 
	 * @param filtrosSalasDTO
	 * @return
	 */
	public List<SalaDTO> buscarSalaDisponibles(FiltrosSalasDTO filtrosSalasDTO) throws SimascException {
		List<String> lstRecursos = new ArrayList<>();
		if(filtrosSalasDTO.getInfraestructuras() != null && !filtrosSalasDTO.getInfraestructuras().isEmpty())			
			lstRecursos = filtrosSalasDTO.getInfraestructuras();
		else
			lstRecursos.add(" ");
		
		String lstInRecusos = "";
		for (String string : lstRecursos) {
			if (lstInRecusos.equals(""))
				lstInRecusos += "'" + string + "'";
			else
				lstInRecusos += ",'" + string + "'";
		}

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT s.id_sala");
		nativeQuery.append(",('Sede: '+sed.nombre + ' - Sala: '+ s.numero_sala)");
		nativeQuery.append(" ,s.id_sede ");
		nativeQuery.append(" FROM sala as s ");
		nativeQuery.append(" INNER JOIN sede as sed ");
		nativeQuery.append(" on sed.id_sede = s.id_sede ");
		nativeQuery.append(" WHERE S.estado_registro = ?1");
		nativeQuery.append(" AND s.tipo_servicio = ?2 ");
		if(filtrosSalasDTO.getInfraestructuras() != null && !filtrosSalasDTO.getInfraestructuras().isEmpty())
		{
			nativeQuery.append(" and (select count(*) from INFRAESTRUCTURA_SALA i WHERE codigo_recurso in ("
							+ lstInRecusos + ") and i.id_sala = s.id_sala and i.estado_registro = ?1) = ");
			nativeQuery.append("(select count(codigo_recurso) from INFRAESTRUCTURA WHERE codigo_recurso in ("
							+ lstInRecusos + ")) ");
		}
 		
		Query query = em.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		List<Object[]> resultados = query.getResultList();
		List<SalaDTO> lsSalas = convertirSalasDTO(resultados);
		List<SalaDTO> lstSalasValidas = new ArrayList<>();

		for (SalaDTO salaDTO : lsSalas) {
			try {
				StringBuilder nativeQuery2 = new StringBuilder();
				nativeQuery2.append("SELECT S.id_sala,s.numero_sala ");
				nativeQuery2.append(" FROM AGENDAMIENTO AG");
				nativeQuery2.append(" LEFT JOIN SALA S");
				nativeQuery2.append(" ON AG.id_sala = S.id_sala ");
				nativeQuery2.append(" WHERE AG.id_sala = " + salaDTO.getIdSala()); 
				nativeQuery2.append(" AND AG.hora_inicio >= ?1 ");
				nativeQuery2.append(" AND AG.hora_fin <= ?2 ");
				//INCON26 acotar salas que estan ocupadas				
				nativeQuery2.append(" AND ( (?1 > AG.hora_inicio AND ?1 < AG.hora_fin) ");
				nativeQuery2.append(" OR (?2 > AG.hora_inicio AND ?2 < AG.hora_fin) ");
				nativeQuery2.append(" OR (?1 <= AG.hora_inicio AND ?2 >= AG.hora_fin)) ");
				nativeQuery2.append(" AND AG.estado_registro = '");
				nativeQuery2.append(UtilDominios.ESTADO_REGISTRO_ACTIVO+"'");
				Query query2 = em.createNativeQuery(nativeQuery2.toString());
				query2.setParameter(1,filtrosSalasDTO.getHoraInicio());
				query2.setParameter(2,filtrosSalasDTO.getHoraFin());
				Object resultados2 = query2.getSingleResult();
			} catch (NoResultException e) {
				lstSalasValidas.add(salaDTO);
			} catch (NonUniqueResultException e) {
				throw new SimascException(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO187.toString()));
			}

		}
		return lstSalasValidas;
	}
	/**
	 * Convierte una lista objetos en una lista de salasDTO
	 * @param registros
	 * @return
	 */
	private List<SalaDTO> convertirSalasDTO(List<Object[]> registros) {
		List<SalaDTO> salas = new ArrayList<>();
		for (Object[] registro : registros) {
			salas.add(convertirRegistroSalasDTO(registro));
		}

		return salas;
	}
	/**
	 * Convierte objetos en salasDTO
	 * @param registro
	 * @return
	 */
	private SalaDTO convertirRegistroSalasDTO(Object[] registro) {
		SalaDTO salaDTO = new SalaDTO();
		Object reg = new Object();

		salaDTO.setIdSala((reg = registro[0]) == null ? null : ((BigDecimal) reg).longValue());
		salaDTO.setNumeroSala((reg = registro[1]) == null ? null : (String) reg);
		salaDTO.setIdSede((reg = registro[2]) == null ? null : ((BigDecimal) reg).longValue());

		return salaDTO;
	}
	
	/**
	 * Metodo que consulta si la sala se encuentra ocupada en una hora
	 * especifica. Retorna true si se encuentra ocupada y false si no lo esta.
	 * 
	 * @param idSala
	 * @param honaInicial
	 * @param horaFinal
	 * @return Boolean
	 */
	public Boolean validarSalaOcupada(Long idSala, Date honaInicial, Date horaFinal){
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT a FROM Agendamiento a ");
		jpqlQuery.append("WHERE a.idSala =: ");
		jpqlQuery.append(Agendamiento.ENTIDAD_AGENDAMIENTO_ID_SALA);
		jpqlQuery.append(" AND ( (:horaInicial > a.horaInicio AND :horaInicial < a.horaFin) ");
		jpqlQuery.append(" OR (:horaFinal > a.horaInicio AND :horaFinal < a.horaFin) ");
		jpqlQuery.append(" OR (:horaInicial <= a.horaInicio AND :horaFinal >= a.horaFin)) ");
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Agendamiento.ENTIDAD_AGENDAMIENTO_ID_SALA, idSala);
		query.setParameter("horaInicial", honaInicial, TemporalType.TIMESTAMP);
		query.setParameter("horaFinal", horaFinal, TemporalType.TIMESTAMP);
		
		Boolean salaOcupada = query.getResultList().isEmpty() ? false : true;
		
		return salaOcupada;
	}
	
	/**
	 * Consulta para verificar si en la sede se encuentran salas disponibles en el rango de horas indicado 
	 * @param idSede
	 * @param horaInicial
	 * @param horaFinal
	 * @return
	 */
	public List<Sala> buscarSalasDisponiblesSede(Long idSede, Date horaInicio, Date horaFin) {
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append(" SELECT s.* ");
		nativeQuery.append(" FROM SALA s ");
		nativeQuery.append(" LEFT JOIN (SELECT DISTINCT a.id_sala FROM AGENDAMIENTO a ");
		nativeQuery.append(" INNER JOIN sala s on s.id_sala = a.id_sala and s.id_sede = ?1 and s.estado_registro = ?4 ");
		nativeQuery.append(" WHERE (cast(hora_inicio as date) = cast(?2 as date) OR cast(hora_fin as date) = cast(?3 as date) )   ");
		nativeQuery.append(" AND  ((DATEADD(s,1, ?2 ) BETWEEN a.hora_inicio AND a.hora_fin) ");
		nativeQuery.append(" OR (DATEADD(s,-1, ?3 ) BETWEEN a.hora_inicio AND a.hora_fin) ) ");
		nativeQuery.append(" AND a.estado_registro = ?4 ) a ");
		nativeQuery.append(" ON s.id_sala =a.id_sala ");
		nativeQuery.append(" WHERE a.id_sala is null ");
		nativeQuery.append(" AND s.id_sede = ?1 ");
		nativeQuery.append(" AND s.estado_registro = ?4 ");

		Query query = em.createNativeQuery(nativeQuery.toString(), Sala.class);
		query.setParameter(1, idSede);
		query.setParameter(2, horaInicio);
		query.setParameter(3, horaFin);
		query.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return query.getResultList();
	}
	
	public boolean existenSalasDisponibles(Long idSede, Date horaInicio, Date horaFin) {
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append(" SELECT 1 ");
		nativeQuery.append(" FROM SALA s ");
		nativeQuery.append(" LEFT JOIN (SELECT DISTINCT a.id_sala FROM AGENDAMIENTO a ");
		nativeQuery.append(" INNER JOIN sala s on s.id_sala = a.id_sala and s.id_sede = ?1 and s.estado_registro = ?4 ");
		nativeQuery.append(" WHERE (cast(hora_inicio as date) = cast(?2 as date) OR cast(hora_fin as date) = cast(?3 as date) )   ");
		nativeQuery.append(" AND  ((DATEADD(s,1, ?2 ) BETWEEN a.hora_inicio AND a.hora_fin) ");
		nativeQuery.append(" OR (DATEADD(s,-1, ?3 ) BETWEEN a.hora_inicio AND a.hora_fin) ) ");
		nativeQuery.append(" AND a.estado_registro = ?4 ) a ");
		nativeQuery.append(" ON s.id_sala =a.id_sala ");
		nativeQuery.append(" WHERE a.id_sala is null ");
		nativeQuery.append(" AND s.id_sede = ?1 ");
		nativeQuery.append(" AND s.estado_registro = ?4 ");

		Query query = em.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, idSede);
		query.setParameter(2, horaInicio);
		query.setParameter(3, horaFin);
		query.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return !query.getResultList().isEmpty();
	}
	
	
	
	/**
	 * Consulta la sede de la audiencia
	 * @param idAudiencia 
	 * @return Long 
	 */
	public Long consultarSedeAudiencia(Long idAudiencia, Long idCaso) {
		StringBuilder nativeQuery = new StringBuilder();
		Long sedeAudiencia;
		nativeQuery.append(" SELECT s.id_sede  ");
		nativeQuery.append(" from SALA s");
		nativeQuery.append(" INNER JOIN AGENDAMIENTO ag ON  s.id_sala=ag.id_sala ");
		nativeQuery.append(" inner join audiencia a on a.id_audiencia=ag.id_audiencia "); 
		nativeQuery.append(" where a.id_caso=?1 ");
		nativeQuery.append(" and a.id_audiencia=?2 ");
		nativeQuery.append(" and a.estado_registro=?3 ");
		nativeQuery.append(" and ag.estado_registro=?3 ");
		nativeQuery.append(" and s.estado_registro=?3 ");
		Query query = em.createNativeQuery(nativeQuery.toString(), Long.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, idAudiencia);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
       
		try{
			sedeAudiencia =  (Long) query.getSingleResult();
		} catch (NoResultException e) {
			sedeAudiencia = null;
		}
		return sedeAudiencia != null ? sedeAudiencia : null;
		
	}

	// protected region metodos adicionales end
        
}

