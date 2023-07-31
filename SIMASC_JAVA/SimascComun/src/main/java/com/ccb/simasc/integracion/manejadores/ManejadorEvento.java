package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.EventoDTO;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.dto.formularios.RutaDelCasoDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Evento;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Evento.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorEvento extends ManejadorCrud<Evento,Long>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@PersistenceContext
	private transient EntityManager em;
	// protected region atributos end
    
    public ManejadorEvento() {
        super(Evento.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta sección sus modificaciones
	public List<Evento> consultarEventosCaso(Long idCaso){
		

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT e");
		jpqlQuery.append(" FROM Evento e");
		jpqlQuery.append(" WHERE e.idCaso =:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND e.estadoRegistro=:");
		jpqlQuery.append(Evento.ENTIDAD_EVENTO_ESTADO_REGISTRO);
		jpqlQuery.append(" ORDER BY e.fechaEvento ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK,idCaso);
		query.setParameter(Evento.ENTIDAD_EVENTO_ESTADO_REGISTRO,UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return query.getResultList();
	}
	
	/**
	 * Consulta tipos de eventos asociados a un caso
	 * @param idCaso
	 * @param tipoEvento
	 * @param idEventos
	 * @return
	 */
	public List<Evento> consultarEventosTipoCaso(Long idCaso, List<String> tipoEvento, List<Long> idEventos){
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT e FROM Evento e ");
		jpqlQuery.append("WHERE e.idCaso = :idCaso ");
		if(!idEventos.isEmpty()){
			jpqlQuery.append("AND e.idEvento IN( ");
			for (Integer i=0; i < idEventos.size(); i++){
				jpqlQuery.append(idEventos.get(i));
				if(i < idEventos.size()-1)
					jpqlQuery.append(",");
			}
			jpqlQuery.append(") ");
		}		
		Integer eventosMax=tipoEvento.size();
		if(eventosMax > 1)
			jpqlQuery.append(" AND (");
		else
			jpqlQuery.append(" AND e.tipoEvento = '"+tipoEvento.get(0)+"'");
			  
		for (Integer i=0; i < eventosMax; i++){
			if(eventosMax > 1){
				jpqlQuery.append(" e.tipoEvento = '"+tipoEvento.get(i)+"'");
				if(i < eventosMax-1)
					jpqlQuery.append(" OR");
			}	
		}
				
		if(eventosMax > 1)
			jpqlQuery.append(") ");
		
		jpqlQuery.append(" ORDER BY e.idEvento ASC ");
		Query query = em.createQuery(jpqlQuery.toString(), Evento.class);
		query.setParameter("idCaso", idCaso);
		return query.getResultList();    		
	}
	
	/**
	 * Actualiza un evento en base de datos
	 * @param Evento
	 * @return Evento
	 */
	public Evento actualizarEvento(Evento evento){
		return (Evento) mp.updateObject(evento);
		
	}
	
	
	public List<Evento> consultarEventoCaso(Long idCaso, List<String> tiposEvento){
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT e");
		jpqlQuery.append(" FROM Evento e");
		jpqlQuery.append(" WHERE e.idCaso =:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND e.tipoEvento IN :");
		jpqlQuery.append(Evento.ENTIDAD_EVENTO_TIPO_EVENTO);
		jpqlQuery.append(" AND e.estadoRegistro=:");
		jpqlQuery.append(Evento.ENTIDAD_EVENTO_ESTADO_REGISTRO);
		jpqlQuery.append(" ORDER BY e.fechaEvento ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK,idCaso);
		query.setParameter(Evento.ENTIDAD_EVENTO_TIPO_EVENTO,tiposEvento);
		query.setParameter(Evento.ENTIDAD_EVENTO_ESTADO_REGISTRO,UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();
	}
	
	/**
	 * Consulta los eventos asociados a un caso 
	 * @param Evento
	 * @return Evento
	 */
	public List<RutaDelCasoDTO> consultarEventosCasoTipo(Long idCaso){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select e.fecha_evento as fechaEvento, e.observaciones as descripcion, D.nombre as tipo   ");
		nativeQuery.append(" from evento e  ");
		nativeQuery.append(" inner join dominio d on e.tipo_evento=d.codigo ");
		nativeQuery.append(" where e.id_caso=?1 ");
		nativeQuery.append(" and d.dominio=?2 ");
		nativeQuery.append(" and e.estado_registro=?3 ");
		nativeQuery.append(" order by e.fecha_evento desc ");
		
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), RutaDelCasoDTO.class);
		
		query.setParameter(1, idCaso);
		query.setParameter(2, UtilDominios.DOMINIO_TIPO_EVENTO);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();
	}
	
	
	/**
	 * retorna el estado del caso 
	 * @param idCaso
	 * @return
	 */
	public List <Evento> consultarUltimoEventoPorEstados(Long idCaso, List<String> domniosActivo){
		
		StringBuilder jpqlQuery = new StringBuilder();
		List<Evento> eventos = new ArrayList<Evento>(); 
		
		try{		
		jpqlQuery.append("SELECT e");
		jpqlQuery.append(" FROM Evento e");
		jpqlQuery.append(" WHERE e.idCaso =:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND e.tipoEvento IN :");
		jpqlQuery.append(Evento.ENTIDAD_EVENTO_TIPO_EVENTO);
		jpqlQuery.append(" AND e.estadoRegistro=:");
		jpqlQuery.append(Evento.ENTIDAD_EVENTO_ESTADO_REGISTRO_EVENTO);
		jpqlQuery.append(" ORDER BY e.fechaEvento DESC ");
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(Evento.ENTIDAD_EVENTO_TIPO_EVENTO, domniosActivo);
		query.setParameter(Evento.ENTIDAD_EVENTO_ESTADO_REGISTRO_EVENTO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		eventos = query.getResultList();

		
		
		} catch (Exception e) {
			throw new SimascException("mensaje quemado por ahora", e);
		}
		
		return eventos;
	}
	
	/**
	 * Método que realiza el borrado logico de los eventos que no se encuentren en los tipos de evento
	 * enviados por parametro para el caso
	 * @param idCaso
	 * @param tipos lista con los eventos que no se borraran
	 */
	public void limpiarEvento(Long idCaso, List<String> tipos) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("update evento set estado_registro = ?1 ");
		nativeQuery.append("where id_caso = ?2 ");
		nativeQuery.append("and tipo_evento not ").append(UtilConsultasSQL.clausulaInSQLStrings(tipos));
		nativeQuery.append(" and estado_registro = ?3");
		
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString());
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_INACTIVO);
		query.setParameter(2, idCaso);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		query.executeUpdate();
	}
	
	/** alerta A-11, consulta de la alerta Trámite sin movimiento, TSINMOV
	 * @param dias: numero de dias que debe validar el metodo
	 * @return List<InfoBasicaAlertasDTO>: id de los centros, junto con su respectiva tabla
	 */
	public List<InfoBasicaAlertasDTO> alertaTramiteSinMovimiento( String tipoPeriodicidad, Long dias ){
		 StringBuilder nativeQuery = new StringBuilder();
		 nativeQuery.append(" DECLARE @TablaEncabezado varchar(max); ");
		 nativeQuery.append(" Set @TablaEncabezado = CONCAT( ISNULL ( (SELECT descripcion FROM DOMINIO WHERE dominio = 'TABLA_ALERTA_ESTILOS' AND codigo = 'TEA'), ");
		 nativeQuery.append(" '<html><body><table> '), ");
		 nativeQuery.append(" '<tr><td width= 100px> <b>Código del caso</b></td>'+ ");
		 nativeQuery.append(" '<td><b>Nombre del caso</b></td>' + ");
		 nativeQuery.append(" '<td><b>Nombre del secretario de tribunal</b></td></tr>' ); ");
		 
		nativeQuery.append(" (select * from (select ce.id_centro as idCentro, @TablaEncabezado + ");
		nativeQuery.append(" (select ca.id_caso as td, ca.nombre as td, ");
		nativeQuery.append(" concat(rtrim(pst.primer_nombre_o_razon_social), rtrim(' '+pst.segundo_nombre), rtrim(' '+pst.primer_apellido), rtrim(' '+pst.segundo_apellido)) as td ");
		nativeQuery.append(" from caso ca ");
		nativeQuery.append(" inner join servicio ser ");
		nativeQuery.append(" on ser.id_servicio = ca.id_servicio ");
		nativeQuery.append(" inner join sede se ");
		nativeQuery.append(" on se.id_sede = ca.id_sede ");
		
		nativeQuery.append(" cross apply (select TOP 1 id_evento, ");
		if (UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad))
			nativeQuery.append(" dbo.SumarDiasHabiles( fecha_evento, ?3 ) ");
		else
			nativeQuery.append(" DATEDIFF ( dd, e.fecha_evento, GETDATE() ) ");
		nativeQuery.append(" as diasTranscurridos ");
		nativeQuery.append(" from evento e where ca.id_caso = id_caso and estado_registro = ?1 ");
		nativeQuery.append(" order by fecha_evento desc) e ");
		
		nativeQuery.append(" left join rol_persona_caso rpc_st ");
		nativeQuery.append(" on rpc_st.id_caso = ca.id_caso ");
		nativeQuery.append(" and rpc_st.id_rol = ( select id_rol from rol where nombre = ?7 ) ");
		nativeQuery.append(" and rpc_st.estado <> ?8 ");
		nativeQuery.append(" and rpc_st.estado_registro = ?1 ");
		nativeQuery.append(" left join persona pst ");
		nativeQuery.append(" on pst.id_persona = rpc_st.id_persona ");
		nativeQuery.append(" and pst.estado_registro = ?1 ");
		nativeQuery.append(" left join suspension sus ");
		nativeQuery.append(" on sus.id_caso = ca.id_caso ");
		nativeQuery.append(" and ( sus.fecha_final is null ");
				nativeQuery.append(" or ");
				nativeQuery.append(" sus.fecha_final >= getdate() ) ");
		nativeQuery.append(" and sus.tipo <> ?2 ");
		nativeQuery.append(" and sus.estado_registro = ?1 ");
		nativeQuery.append(" where ca.estado_caso not in ( ?4 , ?5 ) ");
		if (UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad))
			nativeQuery.append(" and e.diasTranscurridos <= GETDATE() ");
		else
			nativeQuery.append(" and e.diasTranscurridos >= ?3 ");
		nativeQuery.append(" and sus.id_caso is null ");
		nativeQuery.append(" and ser.tipo in (?6, ?9) ");
		nativeQuery.append(" and ca.estado_registro = ?1 ");
		nativeQuery.append(" and ser.estado_registro = ?1 ");
		nativeQuery.append(" and se.estado_registro = ?1 ");
		nativeQuery.append(" and se.id_centro = ce.id_centro ");
		nativeQuery.append(" FOR XML RAW('tr'), ELEMENTS) + '</table></body></html>' as tabla ");
		nativeQuery.append(" from centro ce ");
		nativeQuery.append(" where ce.estado_registro = ?1 ) a where a.tabla is not null ) ");

		 Query query = em.createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		 query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		 query.setParameter(2, UtilDominios.TIPO_SUSPENSION_PRORROGA);
		 query.setParameter(3, dias);
		 query.setParameter(4, UtilDominios.ESTADO_CASO_CERRADO);
		 query.setParameter(5, UtilDominios.ESTADO_CASO_CANCELADO);
		 query.setParameter(6, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		 query.setParameter(7, UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
		 query.setParameter(8, UtilDominios.ESTADO_ROL_PERSONA_CASO_INACTIVO);
		 query.setParameter(9, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);

		 return query.getResultList();
		}	
	
	
	/**
	 * Consulta el ultimo evento de cada tipo de evento enviado del caso
	 * @param idCaso, tipoEventos
	 * @return EventoDTO
	 */
	public List<EventoDTO> consultarUltimoEventoPorTipos(List<String> tipoEventos, Long idCaso){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT tipo_evento as tipoEvento, max(fecha_evento) AS fechaEvento ");
		nativeQuery.append(" FROM evento  ");
		nativeQuery.append(" where id_caso=?1  ");
		nativeQuery.append(" and tipo_evento ").append(UtilConsultasSQL.clausulaInSQLStrings(tipoEventos));
		nativeQuery.append(" and estado_registro=?2  ");
		nativeQuery.append(" GROUP BY tipo_evento, observaciones ");
		nativeQuery.append(" ORDER BY fechaEvento  ");
		
		return ejecutarConsulta(nativeQuery.toString(), idCaso);
	}
	
	public List<EventoDTO> consultarEventosPorTipoOrdernadoFechaDesc (List<String> tipoEventos, Long idCaso){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT id_evento as idEvento, tipo_evento as tipoEvento, fecha_evento as fechaEvento,");
		nativeQuery.append(" observaciones as observaciones, id_caso as idCaso, id_usuario_modificacion as idUsuarioModificacion,");
		nativeQuery.append(" fecha_ultima_modificacion as fechaUltimaModificacion ,estado_registro as estadoRegistro");
		nativeQuery.append(" FROM evento  ");
		nativeQuery.append(" where id_caso=?1  ");
		nativeQuery.append(" and tipo_evento ").append(UtilConsultasSQL.clausulaInSQLStrings(tipoEventos));
		nativeQuery.append(" and estado_registro=?2  ");		
		nativeQuery.append(" ORDER BY fechaEvento DESC");
		
		return ejecutarConsulta(nativeQuery.toString(), idCaso);
	}
    
    private List<EventoDTO> ejecutarConsulta(String consulta, Long idCaso){
		Query query = em.createNativeQuery(consulta, EventoDTO.class);
		
		query.setParameter(1, idCaso);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();
	}  
	// protected region metodos adicionales end
}

