package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.CasosAsignadosDTO;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.EventoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad EventoRolPersonaCaso.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorEventoRolPersonaCaso extends ManejadorCrud<EventoRolPersonaCaso,Long>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
    
    public ManejadorEventoRolPersonaCaso() {
        super(EventoRolPersonaCaso.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    /**
	 * Registra un evento rol persona caso
	 * 
	 * @param caso
	 * @return
	 */
	public EventoRolPersonaCaso crearEventoRolPersonaCaso(EventoRolPersonaCaso eventoRolPersonaCaso) {
		return (EventoRolPersonaCaso) mp.updateObject(eventoRolPersonaCaso);

	}

	public List<EventoRolPersonaCaso> consultarEventosPorRPC(Long idRol, Long idPersona, Long idCaso,
			String estadoAsignado) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT erpc FROM EventoRolPersonaCaso erpc ");
		jpqlQuery.append(" WHERE erpc.idRol = :").append(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ID_ROL);
		jpqlQuery.append(" AND erpc.idPersona = :")
				.append(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ID_PERSONA);
		jpqlQuery.append(" AND erpc.idCaso = :").append(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ID_CASO);
		jpqlQuery.append(" AND erpc.estadoRegistro = :")
				.append(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_REGISTRO);

		if (estadoAsignado != null && !estadoAsignado.isEmpty()) {
			jpqlQuery.append(" AND erpc.estadoAsignado = :")
					.append(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_ASIGNADO);
		}

		jpqlQuery.append(" ORDER BY erpc.fechaDeAsignacion ");

		// Query query = mp.getEntityManager().createQuery("SELECT erpc FROM
		// EventoRolPersonaCaso erpc "
		// + " WHERE erpc.idRol =:idRol "
		// + " AND erpc.idPersona =:idPersona "
		// + " AND erpc.idCaso =: idCaso"
		// + " AND erpc.estadoRegistro =: estadoRegistro "
		// + " ORDER BY erpc.fechaDeAsignacion", EventoRolPersonaCaso.class);
		// query.setParameter("idRol", idRol);
		// query.setParameter("idPersona", idPersona);
		// query.setParameter("idCaso", idCaso);
		// query.setParameter("estadoRegistro",
		// UtilDominios.ESTADO_REGISTRO_ACTIVO);

		Query query = mp.getEntityManager().createQuery(jpqlQuery.toString(), EventoRolPersonaCaso.class);
		query.setParameter(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ID_ROL, idRol);
		query.setParameter(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ID_PERSONA, idPersona);
		query.setParameter(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ID_CASO, idCaso);
		query.setParameter(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);

		if (estadoAsignado != null && !estadoAsignado.isEmpty()) {
			query.setParameter(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_ASIGNADO, estadoAsignado);
		}

		return query.getResultList();
	}
    

    public List<EventoRolPersonaCaso> consultarEvento(Long idRol, Long idPersona, Long idCaso, String estado){
    	Query query = mp.getEntityManager().createQuery("SELECT erpc FROM EventoRolPersonaCaso erpc "
    			+ "WHERE erpc.idRol =:idRol "
    			+ "AND erpc.idPersona =:idPersona "
    			+ "AND erpc.idCaso =:idCaso "
    			+ "AND erpc.estadoAsignado = :estado ", EventoRolPersonaCaso.class);
    	query.setParameter("idRol", idRol);
    	query.setParameter("idPersona", idPersona);
    	query.setParameter("idCaso", idCaso);
    	query.setParameter("estado", estado);
    	
    	return query.getResultList();
    }
    
    /**
     * metodo encargado de traer el evento del arbitro con estado por confirmar.
     * @param idPersona
     * @param idCaso
     * @return
     */
    public List<EventoRolPersonaCaso> consultarEventoJuezAsignado(Long idPersona, Long idCaso){
    	Query query = mp.getEntityManager().createQuery("SELECT erpc FROM EventoRolPersonaCaso erpc "
    			+ "WHERE erpc.idRol = 1 " //el id corresponde al juez
    			+ "AND erpc.idPersona =:idPersona "
    			+ "AND erpc.idCaso =: idCaso "
    			+ "AND erpc.estadoAsignado = 'POR'" , EventoRolPersonaCaso.class);
    	query.setParameter("idPersona", idPersona);
    	query.setParameter("idCaso", idCaso);
    	
    	return query.getResultList();
    }
    
    /**
     * metodo que consulta los EventoRolPersona caso activos de un caso seleccionado, filtrado por etapa del caso y estado de la 
	 * persona dentro del caso.
     * @param casosAsignadosDTO
     * @return
     */
    public List<EventoRolPersonaCaso> eventoRpcAsignado(CasosAsignadosDTO casosAsignadosDTO){
		List<EventoRolPersonaCaso> listaCasos = new ArrayList<EventoRolPersonaCaso>();
    	StringBuilder jpqlQuery = new StringBuilder();

    	try {
	    	jpqlQuery.append(" SELECT rpc FROM EventoRolPersonaCaso rpc WHERE");
	    	jpqlQuery.append(" rpc.rolPersonaCaso.persona.idPersona =: ").append(Persona.ENTIDAD_PERSONA_PK);
	    	jpqlQuery.append(" AND rpc.rolPersonaCaso.rol.nombre =: ").append(Rol.ENTIDAD_ROL_NOMBRE);
	    	jpqlQuery.append(" AND rpc.rolPersonaCaso.estado =:").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
	    	jpqlQuery.append(" AND rpc.rolPersonaCaso.estadoRegistro =: ").append(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_REGISTRO);
	    	jpqlQuery.append(" AND rpc.estadoRegistro =:").append(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_REGISTRO);
	    	jpqlQuery.append(" AND rpc.rolPersonaCaso.caso.estadoRegistro =:").append(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_REGISTRO);
	    	jpqlQuery.append(" AND rpc.rolPersonaCaso.caso.etapa =: ").append(Caso.ENTIDAD_CASO_ETAPA);
	    	jpqlQuery.append(" AND rpc.estadoAsignado =: ").append(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_ASIGNADO);
	    	jpqlQuery.append(" ORDER BY rpc.fechaDeAsignacion ");
	    	Query query = mp.createQuery(jpqlQuery.toString());
	    	query.setParameter(Persona.ENTIDAD_PERSONA_PK, casosAsignadosDTO.getIdPersona());
	    	query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, casosAsignadosDTO.getNombreRol());
	    	query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, casosAsignadosDTO.getEstadoRpc());
	    	query.setParameter(Caso.ENTIDAD_CASO_ETAPA, casosAsignadosDTO.getEtapaDelCaso());
	    	query.setParameter(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_ASIGNADO, casosAsignadosDTO.getEstadoRpc());
	    	query.setParameter(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
	    	listaCasos = query.getResultList();
    	}catch(Exception e){
    		String mensajeError = "no hay datos para la busqueda";
			throw new SimascException(mensajeError);
    	}
    	
		return listaCasos;
	}
    
    /** Alerta A-10-1 Comunicar designación COMDES
     * @return las personas que se les debe notificar con su respectiva tabla html
     */
    public List<InfoBasicaAlertasDTO> alertaComunicarDesignacion(){
    	StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" DECLARE @TablaEncabezado varchar(max); ");
		nativeQuery.append(" Set @TablaEncabezado = CONCAT( ISNULL ( (SELECT descripcion FROM DOMINIO WHERE dominio = 'TABLA_ALERTA_ESTILOS' AND codigo = 'TEA'), ");
												nativeQuery.append(" '<html><body><table> '), ");
												nativeQuery.append(" '<tr><td  width= 100px> <b>Fecha de designación</b></td>'+ ");
												nativeQuery.append(" '<td> <b>Código del caso</b></td>' + ");
												nativeQuery.append(" '<td><b>Nombre del árbitro</b></td></tr>' ); ");
		
		nativeQuery.append(" (select * from ( select abogado.id_persona as idPersona, @TablaEncabezado + ");
		nativeQuery.append(" ( select format( erpc_d.fecha_de_asignacion, 'dd/MM/yyyy') as td, ca.id_caso as td, ");
		nativeQuery.append(" concat(rtrim(pa.primer_nombre_o_razon_social), rtrim(' '+pa.segundo_nombre), rtrim(' '+pa.primer_apellido), rtrim(' '+pa.segundo_apellido)) as td ");
		nativeQuery.append(" from ROL_PERSONA_CASO rpc ");
		nativeQuery.append(" inner join caso ca ");
		nativeQuery.append(" on ca.id_caso = rpc.id_caso ");
		nativeQuery.append(" inner join persona pa ");
		nativeQuery.append(" on rpc.id_persona = pa.id_persona ");
		nativeQuery.append(" inner join evento_rol_persona_caso erpc_d ");
		nativeQuery.append(" on erpc_d.id_persona = rpc.id_persona ");
		nativeQuery.append(" and erpc_d.id_rol = rpc.id_rol ");
		nativeQuery.append(" and erpc_d.id_caso = rpc.id_caso ");
		nativeQuery.append(" inner join rol_persona_caso rpc_a ");
		nativeQuery.append(" on ca.id_caso = rpc_a.id_caso ");
		nativeQuery.append(" where rpc.estado in ( ?2 ) ");
		nativeQuery.append(" and rpc.metodo_nombramiento = ?3 ");
		nativeQuery.append(" and rpc.tipo_nombramiento = ?4 ");
		nativeQuery.append(" and rpc.id_rol in (select id_rol from parametro_servicio_sorteo where estado_registro = ?1 ) ");
		nativeQuery.append(" and erpc_d.estado_asignado = ?2 ");
		nativeQuery.append(" and rpc_a.id_rol = ( select id_rol from rol where nombre = ?5 ) ");
		nativeQuery.append(" and rpc_a.estado <> ?6 ");
		nativeQuery.append(" and rpc.id_persona not in (select distinct erpc.id_persona ");
		nativeQuery.append(" from EVENTO_ROL_PERSONA_CASO erpc ");
		nativeQuery.append(" inner join CARTA_PERSONA cp ");
		nativeQuery.append(" on cp.id_caso = erpc.id_caso ");
		nativeQuery.append(" and cp.id_persona = erpc.id_persona ");
		nativeQuery.append(" inner join PLANTILLA_CARTA pc ");
		nativeQuery.append(" on pc.id_plantilla_carta = cp.id_plantilla_carta ");
		nativeQuery.append(" where erpc.estado_asignado = ?7 ");
		nativeQuery.append(" and pc.nombre in ( ?8 ,'PCNARI' ) ");
		nativeQuery.append(" and cp.estado_carta = ?9 ");
		nativeQuery.append(" and ca.id_caso = erpc.id_caso ");
		nativeQuery.append(" and erpc.id_rol in (select id_rol from parametro_servicio_sorteo where estado_registro = ?1 ) ");
		nativeQuery.append(" and erpc.estado_registro = ?1 ");
		nativeQuery.append(" and cp.estado_registro = ?1 ");
		nativeQuery.append(" and pc.estado_registro = ?1 ");
		nativeQuery.append(" ) ");
		nativeQuery.append(" and ca.estado_registro = ?1 ");
		nativeQuery.append(" and ca.estado_caso not in (?10, ?11) ");
		nativeQuery.append(" and rpc.estado_registro = ?1 ");
		nativeQuery.append(" and erpc_d.estado_registro = ?1 ");
		nativeQuery.append(" and rpc_a.estado_registro = ?1 ");
		nativeQuery.append(" and rpc_a.id_caso = ca.id_caso ");
		nativeQuery.append(" and abogado.id_persona = rpc_a.id_persona ");
		nativeQuery.append(" for xml raw ('tr'), ELEMENTS) + '</table></body></html>' as tabla ");
		nativeQuery.append(" from (select distinct id_persona from rol_persona_caso rpc_a ");
		nativeQuery.append(" where rpc_a.estado_registro = ?1 ");
		nativeQuery.append(" and rpc_a.estado <> ?6 ");
		nativeQuery.append(" and rpc_a.id_rol = ( select id_rol from rol where nombre = ?5 )) abogado) a where a.tabla is not null) ");
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		query.setParameter(3, UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
		query.setParameter(4, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(5, UtilDominios.ROL_PERSONA_ABOGADO);
		query.setParameter(6, UtilDominios.ESTADO_ROL_PERSONA_CASO_INACTIVO);
		query.setParameter(7, UtilDominios.ESTADO_ROL_PERSONA_CASO_COMUNICADO);
		query.setParameter(8, UtilDominios.NOMBRE_PLANTILLA_CARTA_NOTIFICACION_ARBITRO);
		query.setParameter(9, UtilDominios.ESTADO_CARTA_ENVIADA);
		query.setParameter(10, UtilDominios.ESTADO_CASO_REGISTRADO);
		query.setParameter(11, UtilDominios.ESTADO_CASO_CERRADO);
		return query.getResultList();
	}
    
    // protected region metodos adicionales end
        
}

