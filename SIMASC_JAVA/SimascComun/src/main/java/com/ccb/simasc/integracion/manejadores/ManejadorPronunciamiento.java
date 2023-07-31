package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.entidades.CartaPersona;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.EventoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PlantillaCarta;
import com.ccb.simasc.transversal.entidades.Pronunciamiento;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Pronunciamiento.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorPronunciamiento extends ManejadorCrud<Pronunciamiento,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorPronunciamiento() {
        super(Pronunciamiento.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    /**
	 * ARB-F-052 Pronunciamiento del árbitro a la designación
	 * 
	 * @author jsoto
	 * 
	 * Consulta el caso al cual ha sido asignado el operador y que está pendiente de pronunciamiento. 
	 *  
	 * En caso que haya más de un caso asignado se consultan los casos en los que ha sido asignado 
	 * el operador que se pasa como parámetro y en caso de 
	 * que sea más de uno se devuelve el caso en el que se asigno de primeras (la fecha más vieja).
	 * 
	 * @param idRol el id del rol que se obtiene desde el front
	 * @param idUsuario el id del operador (idPersona) para el cual se va a consultar su asignación
	 * @return El código del caso pendiente de pronunciamiento. Si no se encuentra ningún caso se devuelve el número -1.
	 */	
	public Long consultarAsignacionPendienteDePronunciamiento(Long idPersona, List<String> nombresRol) {
		StringBuilder jpqlQuery = new StringBuilder();
		String estadoAsignado1 = "estadoAsignado1";
		String nombresRoles = "nombreRoles";
		List<String> estadosExcluidos = new ArrayList<String>();
		estadosExcluidos.add(UtilDominios.ESTADO_CASO_CERRADO);
		estadosExcluidos.add(UtilDominios.ESTADO_CASO_CANCELADO);
		Long idCasoAsignado = null;
		try {
			jpqlQuery.append("SELECT rpc.rolPersonaCasoPK.idCaso ");
			jpqlQuery.append(" FROM RolPersonaCaso rpc, ");
				jpqlQuery.append(" CartaPersona cp, PlantillaCarta pc ");
			jpqlQuery.append(" INNER JOIN rpc.eventoRolPersonaCasoList erpc ");
			jpqlQuery.append(" INNER JOIN rpc.eventoRolPersonaCasoList erpc_c ");
			jpqlQuery.append(" INNER JOIN rpc.caso c ");	
			jpqlQuery.append(" WHERE ");
			jpqlQuery.append("  	rpc.rolPersonaCasoPK.idPersona =: ").append(Persona.ENTIDAD_PERSONA_PK);
				jpqlQuery.append(" AND cp.idPersona = rpc.rolPersonaCasoPK.idPersona ");
				jpqlQuery.append(" AND cp.idCaso = rpc.rolPersonaCasoPK.idCaso ");
				jpqlQuery.append(" AND pc.idPlantillaCarta = cp.idPlantillaCarta ");
			jpqlQuery.append(" 		AND rpc.tipoNombramiento =: ").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO);
			jpqlQuery.append(" 		AND rpc.rol.nombre IN :").append(nombresRoles);
			jpqlQuery.append(" 		AND rpc.estado =: ").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
			jpqlQuery.append(" 		AND rpc.estadoRegistro =:").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
			jpqlQuery.append(" 		AND erpc.estadoAsignado =:").append(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_ASIGNADO);
			jpqlQuery.append(" 		AND erpc.estadoRegistro =:").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
			jpqlQuery.append(" 		AND c.estadoRegistro =:").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
			jpqlQuery.append(" 		AND c.estadoCaso NOT IN :").append(Caso.ENTIDAD_CASO_ESTADO_CASO);
				jpqlQuery.append(" AND erpc_c.estadoAsignado =: ").append(estadoAsignado1);
				jpqlQuery.append(" AND cp.estadoCarta =: ").append(CartaPersona.ENTIDAD_CARTA_PERSONA_ESTADO_CARTA);
				jpqlQuery.append(" AND pc.nombre IN : ").append(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_NOMBRE);
				jpqlQuery.append(" AND erpc_c.estadoRegistro =:").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
				jpqlQuery.append(" AND cp.estadoRegistro =:").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
				jpqlQuery.append(" AND pc.estadoRegistro =:").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
			jpqlQuery.append(" ORDER BY ");
			jpqlQuery.append(" 		erpc.fechaDeAsignacion ");
			
			Query query = mp.createQuery(jpqlQuery.toString());
			query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
			query.setParameter(nombresRoles, nombresRol);
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
			query.setParameter(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_ASIGNADO, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
			query.setParameter(Caso.ENTIDAD_CASO_ESTADO_CASO,estadosExcluidos);
			query.setParameter(estadoAsignado1,UtilDominios.ESTADO_ROL_PERSONA_CASO_COMUNICADO);
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_NOMBRE,Arrays.asList(UtilDominios.NOMBRE_PLANTILLA_CARTA_NOTIFICACION_ARBITRO,
																							UtilDominios.NOMBRE_PLANTILLA_CARTA_NOTIFICACION_ARBITRO_INTERNACIONAL,
																							UtilDominios.NOMBRE_PLANTILLA_CARTA_NOTIFICACION_ARBITRO_RECUPERACIONL));
			query.setParameter(CartaPersona.ENTIDAD_CARTA_PERSONA_ESTADO_CARTA,UtilDominios.ESTADO_CARTA_ENVIADA);
			List resultados = (List<Object[]>) query.getResultList();
			if(resultados.isEmpty()){
				idCasoAsignado = new Long(-1);
			}else{
				idCasoAsignado = (Long) resultados.get(0) ;
			}
			

		} catch (NoResultException e) {
			return new Long(-1);
		}
		return idCasoAsignado;
	}
	
	/** Alerta A-10-2 Actualizar pronunciamiento COMDESP
     * @return las personas que se les debe notificar con su respectiva tabla html
     */
    public List<InfoBasicaAlertasDTO> alertaActualizarPronunciamiento(){
    	List<InfoBasicaAlertasDTO> resultado = new ArrayList<InfoBasicaAlertasDTO>(); 
    	
		// 1. Borra todos los registros de la tabla ARBITROS_SIN_PRONUNCIAR para
		// evitar que se notifiquen árbitros ya pronunciados
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" DELETE FROM ARBITROS_SIN_PRONUNCIAR ");

		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString());
		query.executeUpdate();
    	
		// 2. Consulta los datos de los arbitros que no se han pronunciado e
		// inserta los resultados en la tabla ARBITROS_SIN_PRONUNCIAR
    	nativeQuery = new StringBuilder();
    	nativeQuery.append(" INSERT INTO ARBITROS_SIN_PRONUNCIAR ");
    	nativeQuery.append(" SELECT DISTINCT ");
    	nativeQuery.append("     ca.id_caso AS id_caso, ");
    	nativeQuery.append("     concat(RTRIM(pa.primer_nombre_o_razon_social), RTRIM(' ' + pa.segundo_nombre), RTRIM(' ' + pa.primer_apellido), RTRIM(' ' + pa.segundo_apellido)) AS nombre_arbitro, ");
    	nativeQuery.append("     format(erpc_d.fecha_de_asignacion, 'dd/MM/yyyy') AS fecha_designacion, ");
    	nativeQuery.append("     format(MAX(erpc.fecha_de_asignacion), 'dd/MM/yyyy') AS fecha_comunicacion, ");
    	nativeQuery.append("     rpc_a.id_persona as persona_abogado_caso ");
    	nativeQuery.append(" FROM CASO ca ");
    	nativeQuery.append(" INNER JOIN ROL_PERSONA_CASO rpc ");
    	nativeQuery.append("     ON rpc.id_caso = ca.id_caso ");
    	nativeQuery.append(" INNER JOIN EVENTO_ROL_PERSONA_CASO erpc ");
    	nativeQuery.append("     ON rpc.id_caso = erpc.id_caso ");
    	nativeQuery.append("     AND rpc.id_persona = erpc.id_persona ");
    	nativeQuery.append("     AND rpc.id_rol = erpc.id_rol ");
    	nativeQuery.append(" INNER JOIN CARTA_PERSONA cp ");
    	nativeQuery.append("     ON cp.id_caso = erpc.id_caso ");
    	nativeQuery.append("     AND cp.id_persona = erpc.id_persona ");
    	nativeQuery.append(" INNER JOIN PLANTILLA_CARTA pc ");
    	nativeQuery.append("     ON pc.id_plantilla_carta = cp.id_plantilla_carta ");
    	nativeQuery.append(" INNER JOIN persona pa ");
    	nativeQuery.append("     ON pa.id_persona = rpc.id_persona ");
    	nativeQuery.append(" INNER JOIN EVENTO_ROL_PERSONA_CASO erpc_d ");
    	nativeQuery.append("     ON rpc.id_caso = erpc_d.id_caso ");
    	nativeQuery.append("     AND rpc.id_persona = erpc_d.id_persona  ");
    	nativeQuery.append("     AND rpc.id_rol = erpc_d.id_rol ");
    	nativeQuery.append(" INNER JOIN servicio s ");
    	nativeQuery.append("     ON ca.id_servicio = s.id_servicio ");
    	nativeQuery.append("     AND s.tipo = 'PJT' ");
    	nativeQuery.append(" INNER JOIN ROL_PERSONA_CASO rpc_a ");
    	nativeQuery.append("     ON rpc_a.id_caso = ca.id_caso ");
    	nativeQuery.append("     AND rpc_a.id_rol = (SELECT r.id_rol FROM ROL r WHERE r.nombre = 'ABO') ");
    	nativeQuery.append("     AND rpc_a.estado IN ('ASG', 'ACE') ");
    	nativeQuery.append("     AND rpc_a.estado_registro = 'ACT' ");
    	nativeQuery.append(" WHERE erpc.estado_asignado = 'COM' ");
    	nativeQuery.append(" AND erpc_d.estado_asignado = 'POR' ");
    	nativeQuery.append(" AND pc.nombre IN ('PCNAR', 'PCNARI') ");
    	nativeQuery.append(" AND cp.estado_carta = 'ENV' ");
    	nativeQuery.append(" AND rpc.estado = 'POR' ");
    	nativeQuery.append(" AND rpc.motivo_inactivacion is null ");
    	nativeQuery.append(" AND rpc.tipo_nombramiento = 'PRI' ");
    	nativeQuery.append(" AND ca.estado_registro = 'ACT' ");
    	nativeQuery.append(" AND ca.estado_caso NOT IN ('REGISTRA', 'CER') ");
    	nativeQuery.append(" AND rpc.estado_registro = 'ACT' ");
    	nativeQuery.append(" AND erpc.estado_registro = 'ACT' ");
    	nativeQuery.append(" AND cp.estado_registro = 'ACT' ");
    	nativeQuery.append(" AND pc.estado_registro = 'ACT' ");
    	nativeQuery.append(" AND pa.estado_registro = 'ACT' ");
    	nativeQuery.append(" AND erpc_d.estado_registro = 'ACT' ");
    	nativeQuery.append(" GROUP BY ca.id_caso, ");
    	nativeQuery.append("          pa.primer_nombre_o_razon_social, ");
    	nativeQuery.append("          pa.segundo_nombre, ");
    	nativeQuery.append("          pa.primer_apellido, ");
    	nativeQuery.append("          pa.segundo_apellido, ");
    	nativeQuery.append("          erpc_d.fecha_de_asignacion, ");
    	nativeQuery.append("          rpc_a.id_persona ");
		
		query = mp.getEntityManager().createNativeQuery(nativeQuery.toString());
		query.executeUpdate();
		
		// 3. Construye la tabla HTML para la notificación de los árbitros sin
		// pronunciar a partir de los resultados almacenados en la tabla
		// ARBITROS_SIN_PRONUNCIAR
		nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT idPersona, tabla FROM AlertaActualizacionPronunciamiento() ");

		query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		resultado = query.getResultList();
		
		return resultado;
	}

    // protected region metodos adicionales end
        
}

