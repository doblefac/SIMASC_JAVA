package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones

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
import com.ccb.simasc.transversal.dto.formularios.FiltrosSalasDTO;
import com.ccb.simasc.transversal.entidades.Agendamiento;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Agendamiento.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorAgendamiento extends ManejadorCrud<Agendamiento,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
	private transient EntityManager em;
	// protected region atributos end
    
    public ManejadorAgendamiento() {
        super(Agendamiento.class);
    }    
    
    // protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Metodo que consulta las salas por filtros y los organiza por hora y sala
	 * 
	 * @param filtrosSalasDTO
	 * @return List<SalaDTO>
	 */
	@SuppressWarnings("unchecked")
	public List<Agendamiento> buscarAgendamientoSalaSede(FiltrosSalasDTO filtrosSalasDTO) {

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT ag.* FROM sede se ");
		nativeQuery.append("INNER JOIN sala sa ON se.id_sede = sa.id_sede ");
		nativeQuery.append("LEFT JOIN AGENDAMIENTO ag ON sa.id_sala = ag.id_sala ");
		nativeQuery.append(" WHERE ");
		nativeQuery.append(" ag.estado_registro = '");
		nativeQuery.append(UtilDominios.ESTADO_REGISTRO_ACTIVO + "'");
		if (filtrosSalasDTO.getFechaSolicitud() != null)
			nativeQuery.append(" AND CAST(ag.hora_inicio AS DATE) = ?1 ");
		if (filtrosSalasDTO.getIdSede() != null)
			nativeQuery.append(" AND se.id_sede = ?2 ");
		
		if (filtrosSalasDTO.getIdSala() != null){
			nativeQuery.append(" AND sa.id_sala = ?3 ");
		}

		nativeQuery.append(" ORDER BY ag.hora_inicio,ag.id_sala ");

		Query q = em.createNativeQuery(nativeQuery.toString(), Agendamiento.class);
		if (filtrosSalasDTO.getFechaSolicitud() != null)
			q.setParameter(1, new Date(filtrosSalasDTO.getFechaSolicitud().getTime()), TemporalType.DATE);
		if (filtrosSalasDTO.getIdSede() != null)
			q.setParameter(2, filtrosSalasDTO.getIdSede());
		
		if (filtrosSalasDTO.getIdSala() != null){
			q.setParameter(3, filtrosSalasDTO.getIdSala());
		}
		return q.getResultList();
	}

	public void crearAgendamiento(Agendamiento agendamiento) {
		crear(agendamiento);
	}
	
	public Agendamiento buscarAgendamientoAudiencia(Long idAudiencia) {
		StringBuilder jpqlQuery = new StringBuilder();
		Agendamiento resultado;
		try {
			jpqlQuery.append("SELECT DISTINCT(ag) FROM Agendamiento ag JOIN ag.sala s ");
			jpqlQuery.append(" WHERE ag.idAudiencia=:");
			jpqlQuery.append(Agendamiento.ENTIDAD_AGENDAMIENTO_ID_AUDIENCIA);
			jpqlQuery.append(" AND ag.estadoRegistro=:");
			jpqlQuery.append(Agendamiento.ENTIDAD_AGENDAMIENTO_ESTADO_REGISTRO);
			Query query = mp.createQuery(jpqlQuery.toString());
			query.setParameter(Agendamiento.ENTIDAD_AGENDAMIENTO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(Agendamiento.ENTIDAD_AGENDAMIENTO_ID_AUDIENCIA, idAudiencia);
			resultado = (Agendamiento) query.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			resultado = null;
		}

		return resultado;
	}
	

	public Agendamiento agendamientoExiste(Audiencia audiencia) {
		StringBuilder jpqlQuery = new StringBuilder();
		Agendamiento resultado;
		try {
			jpqlQuery.append("SELECT DISTINCT(ag) FROM Agendamiento ag JOIN ag.sala s ");
			jpqlQuery.append(" WHERE ag.idAudiencia=:");
			jpqlQuery.append(Agendamiento.ENTIDAD_AGENDAMIENTO_ID_AUDIENCIA);
			jpqlQuery.append(" AND ag.estadoRegistro=:");
			jpqlQuery.append(Agendamiento.ENTIDAD_AGENDAMIENTO_ESTADO_REGISTRO);
			jpqlQuery.append(" AND ag.horaInicio=:");
			jpqlQuery.append(Agendamiento.ENTIDAD_AGENDAMIENTO_HORA_INICIO);
			jpqlQuery.append(" AND ag.horaFin=:");
			jpqlQuery.append(Agendamiento.ENTIDAD_AGENDAMIENTO_HORA_FIN);
			Query query = mp.createQuery(jpqlQuery.toString());
			query.setParameter(Agendamiento.ENTIDAD_AGENDAMIENTO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(Agendamiento.ENTIDAD_AGENDAMIENTO_ID_AUDIENCIA, audiencia.getIdAudiencia());
			query.setParameter(Agendamiento.ENTIDAD_AGENDAMIENTO_HORA_INICIO, audiencia.getHoraInicio());
			query.setParameter(Agendamiento.ENTIDAD_AGENDAMIENTO_HORA_FIN, audiencia.getHoraFin());
			resultado = (Agendamiento) query.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			resultado = null;
		}

		return resultado;
	}
	
	/**
	 * 
	 * @param idAudiencia
	 * @return
	 */
	public List<Agendamiento> buscarAgendamientosAudiencia(Long idAudiencia) {
		StringBuilder jpqlQuery = new StringBuilder();
		List<Agendamiento> resultado = new ArrayList<>();
		
			jpqlQuery.append("SELECT DISTINCT(ag) FROM Agendamiento ag JOIN ag.sala s ");
			jpqlQuery.append(" WHERE ag.idAudiencia=:");
			jpqlQuery.append(Agendamiento.ENTIDAD_AGENDAMIENTO_ID_AUDIENCIA);
			jpqlQuery.append(" AND ag.estadoRegistro=:");
			jpqlQuery.append(Agendamiento.ENTIDAD_AGENDAMIENTO_ESTADO_REGISTRO);
			Query query = mp.createQuery(jpqlQuery.toString());
			query.setParameter(Agendamiento.ENTIDAD_AGENDAMIENTO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(Agendamiento.ENTIDAD_AGENDAMIENTO_ID_AUDIENCIA, idAudiencia);
			resultado = (List<Agendamiento>) query.getResultList();

		return resultado;
	}

	
	// protected region metodos adicionales end
        
}

