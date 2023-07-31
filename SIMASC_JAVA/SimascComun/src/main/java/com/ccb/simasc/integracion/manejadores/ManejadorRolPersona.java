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

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.ConsultaPrestadoresDTO;
import com.ccb.simasc.transversal.dto.PersonaAleatoriaDTO;
import com.ccb.simasc.transversal.dto.RolPersonaDTO;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.dto.formularios.BusquedaRolesActivosDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltroConsultaPrestadoresDTO;
import com.ccb.simasc.transversal.dto.formularios.InfoPrestadorDTO;
import com.ccb.simasc.transversal.entidades.Centro;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad RolPersona.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorRolPersona extends ManejadorCrud<RolPersona, Long> {

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	private static final String CONDICION_PERSONA_REGISTRO_ACTIVO = " AND rp.persona.estadoRegistro = ";
	private static final String CONDICION_ROL_REGISTRO_ACTIVO = " AND rp.rol.estadoRegistro = ";
	private static final String CONDICION_ROL_PERSONA_REGISTRO_ACTIVO = " AND rp.estadoRegistro = ";
	private static final String ORDER_BY_IDROLPERSONA = " ORDER BY rp.idRolPersona";

	@PersistenceContext
	private transient EntityManager em;
	// protected region atributos end

	public ManejadorRolPersona() {
		super(RolPersona.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/**
	 * Guarda el rol de una persona
	 * 
	 * @param rolPersona
	 * @return
	 */
	public RolPersona crearRolPersona(RolPersona rolPersona) {

		return (RolPersona) mp.updateObject(rolPersona);

	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/**
	 * Obtiene todas las personas que tengan un rol de la lista
	 * 
	 * @param rolPersona
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RolPersona> consultarRolPersonasPorRoles(List<String> roles) {
		StringBuilder string = new StringBuilder();
		string.append("SELECT rp FROM RolPersona rp WHERE rp.rol.nombre in :").append(Rol.ENTIDAD_ROL_NOMBRE)
				.append(" AND rp.persona.estadoRegistro = ").append("'ACT'")
				.append(" AND rp.rol.estadoRegistro = ").append("'ACT'")
				.append(CONDICION_ROL_PERSONA_REGISTRO_ACTIVO).append("'ACT'")
				.append(ORDER_BY_IDROLPERSONA);
		Query query = mp.createQuery(string.toString());
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, roles);
		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()),
					e);
		}
	}

	/**
	 * ADM-C-020 Obtiene todas las personas que tengan un rol de la lista
	 * precargando el rol, la persona asociada, su usuario
	 * 
	 * @param rolPersona
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RolPersona> consultarUsuariosSistemaConsultaDTO(List<String> roles) {
		StringBuilder string = new StringBuilder();
		string.append(
				"SELECT rp FROM RolPersona rp JOIN rp.persona JOIN rp.persona.usuarioList JOIN rp.rol WHERE rp.rol.nombre in :")
				.append(Rol.ENTIDAD_ROL_NOMBRE).append(CONDICION_PERSONA_REGISTRO_ACTIVO)
				.append("'ACT'").append(CONDICION_ROL_REGISTRO_ACTIVO)
				.append("'ACT'").append(CONDICION_ROL_PERSONA_REGISTRO_ACTIVO)
				.append("'ACT'").append(ORDER_BY_IDROLPERSONA);
		Query query = mp.createQuery(string.toString());
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, roles);
		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()),
					e);
		}
	}

	/**
	 * Devuelve los roles persona que están activos asociados a la persona
	 * 
	 * @param idPersona
	 * @return Lista de RolPersona que están activos
	 */
	@SuppressWarnings("unchecked")
	public List<Rol> consultarRolesPersonaActivosPorPersona(Long idPersona) {
		StringBuilder string = new StringBuilder();
		string.append("SELECT rp.rol FROM RolPersona rp ").append("	WHERE ").append("       rp.persona.idPersona = :")
				.append(Persona.ENTIDAD_PERSONA_PK).append(" 		AND rp.persona.estadoRegistro = ")
				.append("'ACT'").append(" 		AND rp.rol.estadoRegistro = ")
				.append("'ACT'").append(" 		AND rp.fechaInicioVigencia <= :")
				.append(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA).append(" 		AND  (")
				.append("				rp.fechaFinVigencia IS NULL").append(" 				OR ")
				.append("				rp.fechaFinVigencia >= :")
				.append(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA).append(" 			 )")
				.append(" 		AND rp.estadoRegistro = ").append("'ACT'")
				.append(" 	ORDER BY rp.rol.nombre");
		Query query = mp.createQuery(string.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		query.setParameter(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA, new Date());

		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()),
					e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Rol> consultarRolesInactivosPorPersona(Long idPersona) {
		StringBuilder string = new StringBuilder();
		string.append("SELECT rp.rol FROM RolPersona rp ").append(" 	WHERE rp.persona.idPersona = :")
				.append(Persona.ENTIDAD_PERSONA_PK).append(" 		AND rp.fechaFinVigencia IS NOT NULL ")
				.append("		AND	rp.fechaFinVigencia < :").append(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_FIN_VIGENCIA)

				.append("		AND (rp.estadoRegistro = ").append("'INA'")
				.append("		OR rp.estadoRegistro = ").append("'ACT'").append(")")

				.append(CONDICION_ROL_REGISTRO_ACTIVO).append("'ACT'")
				.append(" 	ORDER BY rp.rol.nombre");
		Query query = mp.createQuery(string.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		query.setParameter(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_FIN_VIGENCIA, new Date());

		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()),
					e);
		}
	}

	/**
	 * Consulta si la persona tiene asignado el rol en la fecha actual
	 * 
	 * @param idPersona
	 * @param nombreRol
	 *            Nombre de dominio del rol
	 * @return
	 */
	public boolean consultarSiPersonaTieneRol(Long idPersona, String nombreRol) {
		List<Rol> rolesPersona = consultarRolesPersonaActivosPorPersona(idPersona);
		boolean tieneRol = false;

		for (Rol rol : rolesPersona) {
			if (rol.getNombre().equals(nombreRol)) {
				tieneRol = true;
			}
		}

		return tieneRol;
	}

	@SuppressWarnings("unchecked")
	public List<Number> consultarRolPersonasPorRolesIds(List<String> roles) {
		StringBuilder string = new StringBuilder();
		string.append("SELECT rp.idRolPersona FROM RolPersona rp WHERE rp.rol.nombre in :")
				.append(Rol.ENTIDAD_ROL_NOMBRE).append(CONDICION_PERSONA_REGISTRO_ACTIVO)
				.append("'ACT'").append(CONDICION_ROL_REGISTRO_ACTIVO)
				.append("'ACT'").append(CONDICION_ROL_PERSONA_REGISTRO_ACTIVO)
				.append("'ACT'").append(ORDER_BY_IDROLPERSONA);
		Query query = mp.createQuery(string.toString());
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, roles);
		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()),
					e);
		}
	}

	/**
	 * Retorna los roles de una persona
	 * 
	 * @param idPersona
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Rol> consultarRolesPersona(Long idPersona) {
		StringBuilder stringQuery = new StringBuilder();
		stringQuery.append("SELECT rp.rol FROM RolPersona rp ").append(" WHERE ").append(" rp.persona.idPersona = ")
				.append(idPersona).append(CONDICION_ROL_REGISTRO_ACTIVO)
				.append("'ACT'").append(" AND rp.fechaInicioVigencia <= :")
				.append(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA).append(" AND  (")
				.append(" rp.fechaFinVigencia IS NULL").append(" OR ").append(" rp.fechaFinVigencia >= :")
				.append(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA).append(" )")
				.append(CONDICION_ROL_PERSONA_REGISTRO_ACTIVO).append("'ACT'");
		Query query = mp.createQuery(stringQuery.toString());
		query.setParameter(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA, new Date());

		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()),
					e);
		}
	}

	/**
	 * consulta si la persona es un arbitro en una fecha particular
	 * 
	 * @param idPersona
	 * @param fecha
	 *            en la cual se revisa la vigencia del rol
	 * @return
	 */
	public Boolean consultarPersonaEsArbitro(Long idPersona, Date fecha) {

		if (fecha == null)
			fecha = new Date();

		StringBuilder stringQuery = new StringBuilder();
		stringQuery.append(" SELECT COUNT(rp.id_rol) FROM PERSONA p ");
		stringQuery.append(" LEFT JOIN  ROL_PERSONA rp  ON rp.id_persona = p.id_persona ");
		stringQuery.append(" WHERE p.estado_registro = ?1 ");
		stringQuery.append(" AND rp.estado_registro = ?1 ");
		stringQuery.append(
				" AND rp.id_rol IN (SELECT distinct (id_rol) FROM PARAMETRO_SERVICIO_SORTEO WHERE estado_registro = ?1 ) ");
		stringQuery.append(" AND p.id_persona = ?3 ");
		stringQuery.append(" AND (rp.fecha_fin_vigencia IS NULL OR cast(rp.fecha_fin_vigencia as DATE) > ?2 ) ");
		stringQuery.append(" AND cast(rp.fecha_inicio_vigencia as DATE) < ?2 ");

		Query query = mp.getEntityManager().createNativeQuery(stringQuery.toString());
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, fecha);
		query.setParameter(3, idPersona);

		Integer resultados = (Integer) query.getSingleResult();

		return (resultados != null && resultados > 0);

	}

	/**
	 * Método para obtener los rol persona relacionados al codigo del rol y que
	 * se encuentren activos
	 * 
	 * @param idPersona
	 * @param nombreRoles
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RolPersona> consultarRolesAsignados(Long idPersona, List<String> nombreRoles) {
    	StringBuilder string = new StringBuilder();     	
    	string.append("SELECT rp FROM RolPersona rp ")
	    .append("	WHERE ")
	    .append(" 		rp.persona.idPersona = :").append(Persona.ENTIDAD_PERSONA_PK)
	    .append(" 		AND rp.rol.idRol IN (SELECT r.idRol FROM Rol r WHERE r.nombre IN :").append(Rol.ENTIDAD_ROL_PK)
		.append(" 	)	AND rp.persona.estadoRegistro = ").append("'ACT'")
		.append(CONDICION_ROL_REGISTRO_ACTIVO).append("'ACT'")
		.append(" 		AND rp.fechaInicioVigencia <= :").append(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA)
		.append(" 		AND  rp.fechaFinVigencia IS NULL ")
		.append(CONDICION_ROL_PERSONA_REGISTRO_ACTIVO).append("'ACT'")
		.append(ORDER_BY_IDROLPERSONA);
    	Query query = mp.createQuery(string.toString());
    	query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
    	query.setParameter(Rol.ENTIDAD_ROL_PK, nombreRoles);
    	query.setParameter(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA, new Date());

    	try {
    		return query.getResultList();			
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}

    }
    
    public List<RolPersona> consultarRolesAsignados(Long idPersona, String nombreRol) {
    	List<String> nombreRoles = new ArrayList<String>();
    	nombreRoles.add(nombreRol);
    	return this.consultarRolesAsignados(idPersona, nombreRoles);    	
    }

	/**
	 * Retorna el listado de objetos RolPersona que represente los las personas
	 * asociadas al rol cuyo id se recibe por parámetro que estén vigentes,
	 * activos y que pertenezcan al centro cuyo id se recibe por parámetro
	 * 
	 * @param idRol
	 *            identificador del rol a consultar
	 * @param idCentro
	 *            identificador del centro a consultar
	 * @author Javier Estévez
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public List<Number> consultarRolesPersonaPorRolYCentro(Long idRol, Long idCentro) {

		StringBuilder sbQuery = new StringBuilder();

		sbQuery.append("SELECT rp.idRolPersona FROM RolPersona rp ").append(" WHERE ").append(" 	rp.idRol = :")
				.append(Rol.ENTIDAD_ROL_PK).append(" AND rp.persona.estadoRegistro = ")
				.append("'ACT'").append(" AND rp.rol.estadoRegistro = ")
				.append("'ACT'").append(" AND rp.fechaInicioVigencia <= :")
				.append(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA).append(" AND  (")
				.append("	rp.fechaFinVigencia IS NULL").append("	OR ").append("	rp.fechaFinVigencia >= :")
				.append(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_FIN_VIGENCIA).append(" )").append(" AND rp.idCentro = :")
				.append(Centro.ENTIDAD_CENTRO_PK).append(" AND rp.estadoRegistro = ")
				.append("'ACT'").append(" ORDER BY rp.idRolPersona");

		Query query = mp.createQuery(sbQuery.toString());

		Date fechaActual = new Date();
		// se establece parametros
		query.setParameter(Rol.ENTIDAD_ROL_PK, idRol);
		query.setParameter(Centro.ENTIDAD_CENTRO_PK, idCentro);
		query.setParameter(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA, fechaActual);
		query.setParameter(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_FIN_VIGENCIA, fechaActual);

		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()),
					e);
		}

	}

	/**
	 * Método para obtener el listado de los centros a los cuales se encuentra
	 * asociado un usuario
	 * 
	 * @param idPersona
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Centro> obtenerCentrosActor(Long idPersona) {
		StringBuilder string = new StringBuilder();
		string.append("SELECT DISTINCT rp.centro FROM RolPersona rp ").append(" WHERE rp.persona.idPersona = :")
				.append(Persona.ENTIDAD_PERSONA_PK).append(CONDICION_ROL_PERSONA_REGISTRO_ACTIVO)
				.append("'ACT'").append(CONDICION_PERSONA_REGISTRO_ACTIVO)
				.append("'ACT'").append(" AND rp.centro.estadoRegistro = ")
				.append("'ACT'");

		Query query = mp.createQuery(string.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);

		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()),
					e);
		}
	}

	/**
	 * ADM-C-015 Obtiene los rolPersona asociados a la persona sin tener en
	 * cuenta el estado del registro
	 * 
	 * @param idPersona
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RolPersona> obtenerRolPesonaPorPersona(Long idPersona) {
		StringBuilder string = new StringBuilder();
		string.append("SELECT rp FROM RolPersona rp WHERE rp.persona.idPersona = :").append(Persona.ENTIDAD_PERSONA_PK)
				.append(CONDICION_PERSONA_REGISTRO_ACTIVO).append("'ACT'")
				.append(CONDICION_ROL_REGISTRO_ACTIVO).append("'ACT'")
				.append(" ORDER BY rp.idCentro");
		Query query = mp.createQuery(string.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()),
					e);
		}
	}

	/**
	 * ADM-C-021 Devuelve verdadero si la persona no tiene ninguna asignación
	 * para el rol y centro que se pasa como parámetro. Si el centro llega nulo
	 * se ignora en la validación.
	 * 
	 * @param idPersona
	 * @param nombreRol
	 * @param idCentro
	 * @return
	 */
	public boolean validarUsuarioNoTieneRol(Long idPersona, Long idRol, Long idCentro) {

		StringBuilder nativeQuery = new StringBuilder("SELECT count(*) FROM ROL_PERSONA rp");
		nativeQuery.append(" WHERE rp.id_persona=?1 ");
		nativeQuery.append(" 	AND rp.id_rol=?2 ");
		if (idCentro != null) {
			nativeQuery.append(" 	AND rp.id_centro=?3 ");
		}
		nativeQuery.append(" AND rp.estado_registro = ").append("'ACT'");
		Query query = em.createNativeQuery(nativeQuery.toString());

		query.setParameter(1, idPersona);
		query.setParameter(2, idRol);
		if (idCentro != null) {
			query.setParameter(3, idCentro);
		}

		int resultado = ((Integer) query.getSingleResult()).intValue();

		return resultado == 0;
	}

	/**
	 * ADM-C-003 COnsulta lista de prestadores Consulta los prestadores que
	 * cumplan los criterios de filtrado que se pasan como parámetro
	 * 
	 * @param filtro
	 * @param rolesPrestador
	 *            Lista con los códigos de dominio de los roles que son
	 *            prestadores de servicio
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<InfoPrestadorDTO> consultarListaPrestadores(FiltroConsultaPrestadoresDTO filtro) {

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT ");
		nativeQuery.append("	ISNULL(p.primer_apellido,'')+' '+ISNULL(p.segundo_apellido,'') AS APELLIDOS, ");
		nativeQuery
				.append("	ISNULL(p.primer_nombre_o_razon_social,'')+' '+ISNULL(p.segundo_nombre,'') AS NOMBRES, ");
		nativeQuery.append("	p.tipo_documento AS TIPO_DOCUMENTO, ");
		nativeQuery.append("	p.numero_documento AS NUMERO_DOCUMENTO, ");
		nativeQuery.append("	p.numero_tarjeta_profesional AS TARJETA_PROFESIONAL, ");
		nativeQuery.append("	p.fecha_de_nacimiento AS FECHA_NACIMIENTO, ");
		nativeQuery.append("	ISNULL(l.nombre,'') AS LISTA, ");
		nativeQuery.append("	(SELECT m.nombre + '\n' FROM PERSONA_SERVICIO_MATERIA psm ");
		nativeQuery.append(
				"		INNER JOIN SERVICIO_MATERIA sm ON sm.id_servicio = psm.id_servicio AND sm.id_materia=psm.id_materia ");
		nativeQuery.append("		INNER JOIN MATERIA m ON m.id_materia = sm.id_materia ");
		nativeQuery.append("		INNER JOIN SERVICIO s ON s.id_servicio = sm.id_servicio ");
		nativeQuery.append("		INNER JOIN PARAMETRO_SERVICIO_SORTEO pss ON pss.id_servicio=s.id_servicio ");
		nativeQuery.append("		INNER JOIN ROL rol ON rol.id_rol = pss.id_rol ");
		nativeQuery.append("		WHERE ");
		nativeQuery.append("			rol.id_rol=r.id_rol  ");
		nativeQuery.append("			AND psm.id_persona=p.id_persona ");
		nativeQuery.append("			AND psm.estado_registro='ACT' ");
		nativeQuery.append("			AND pss.estado_registro='ACT' ");
		nativeQuery.append("			AND sm.estado_registro='ACT' FOR XML PATH('')) AS MATERIAS, ");
		nativeQuery.append(
				"	(SELECT u.direccion + '\n' FROM UBICACION u WHERE u.id_persona = p.id_persona AND u.estado_registro='ACT' FOR XML PATH('')) AS DIRECCIONES, ");
		nativeQuery.append(
				"	(SELECT t.numero + '\n' FROM TELEFONO t WHERE t.id_persona = p.id_persona AND t.estado_registro='ACT' AND t.tipo_telefono='FIJ' FOR XML PATH('')) AS TELEFONOS, ");
		nativeQuery.append(
				"	(SELECT TOP(1) t.numero + '\n' FROM TELEFONO t WHERE t.id_persona = p.id_persona AND t.estado_registro='ACT' AND t.tipo_telefono='CEL' FOR XML PATH('')) AS CELULAR, ");
		nativeQuery.append(
				"	(SELECT  TOP(1) c.direccion FROM CORREO_ELECTRONICO c WHERE c.id_persona=p.id_persona AND c.estado_registro='ACT' AND c.tipo='PRI' ORDER BY ID_CORREO DESC) AS CORREO_ELECTRONICO, ");
		nativeQuery.append("	rp.acta_aprobacion AS ACTA_APROBACION, ");
		nativeQuery.append("	rp.fecha_acta_aprobacion AS FECHA_ACTA_APROBACION, ");
		nativeQuery.append("	rp.fecha_ultima_modificacion AS FECHA_ULTIMA_ACTUALIZACION, ");
		nativeQuery.append(
				"	CASE WHEN rp.reportado_sicaac IS NULL THEN '' WHEN rp.reportado_sicaac=1 THEN 'Si' WHEN rp.reportado_sicaac=0 THEN 'No' END AS REPORTADO_SICAAC, ");
		nativeQuery.append(
				"	ISNULL(pj.primer_nombre_o_razon_social,'')+' '+ISNULL(pj.segundo_nombre,'')+' '+ISNULL(pj.primer_apellido,'')+' '+ISNULL(pj.segundo_apellido,'') AS PERSONA_JURIDICA ");
		nativeQuery.append(" FROM ");
		nativeQuery.append("	ROL_PERSONA rp ");
		nativeQuery.append("	INNER JOIN ROL r ON r.id_rol = rp.id_rol ");
		nativeQuery.append("	INNER JOIN PERSONA p ON p.id_persona = rp.id_persona ");
		nativeQuery.append("	LEFT JOIN LISTA l ON l.id_lista = rp.id_lista ");
		nativeQuery.append("	LEFT JOIN PERSONA pj ON pj.id_persona = p.id_persona_juridica ");
		if (filtro.tieneMateria()) {
			nativeQuery.append("	INNER JOIN PERSONA_SERVICIO_MATERIA psm ON psm.id_persona = p.id_persona ");
			nativeQuery.append(
					"	INNER JOIN SERVICIO_MATERIA sm ON sm.id_servicio = psm.id_servicio AND sm.id_materia = psm.id_materia ");
			nativeQuery.append("	INNER JOIN MATERIA m ON m.id_materia = sm.id_materia ");
			nativeQuery.append(
					"	INNER JOIN PARAMETRO_SERVICIO_SORTEO pss ON pss.id_rol = r.id_rol AND pss.id_servicio=sm.id_servicio");
		}
		nativeQuery.append(" WHERE ");
		nativeQuery.append("	rp.estado_registro = 'ACT' ");
		nativeQuery.append("	AND r.nombre =").append("'"+filtro.getRol()+"'");
		if (filtro.tieneApellidos()) {
			nativeQuery.append("	AND ISNULL(p.primer_apellido,'')+ISNULL(p.segundo_apellido,'') LIKE ")
					.append("'%"+filtro.getApellidos()+"%'");
		}
		if (filtro.tieneNombres()) {
			nativeQuery.append("	AND ISNULL(p.primer_nombre_o_razon_social,'')+ISNULL(p.segundo_nombre,'') LIKE ")
					.append("'%"+filtro.getNombres()+"%'");
		}
		if (filtro.tieneTipoDocumento()) {
			nativeQuery.append("	AND p.tipo_documento = ")
					.append("'"+filtro.getTipoDocumento()+"'");
		}
		if (filtro.tieneNumeroDocumento()) {
			nativeQuery.append("	AND p.numero_documento LIKE ")
					.append("'%"+filtro.getNumeroDocumento()+"%'");
		}
		if (filtro.tieneMateria()) {
			nativeQuery.append("	AND m.id_materia = ").append(filtro.getIdMateria());
			nativeQuery.append("	AND psm.estado_registro='ACT' ");
			nativeQuery.append("	AND sm.estado_registro='ACT' ");
			nativeQuery.append("	AND pss.estado_registro='ACT' ");
		}
		if (filtro.tieneLista()) {
			nativeQuery.append("	AND l.id_lista = ").append(filtro.getIdLista());
		}
		if (filtro.tienePersonaJuridica()) {
			nativeQuery
					.append("	AND ISNULL(pj.primer_nombre_o_razon_social,'')+ISNULL(pj.segundo_nombre,'')+ISNULL(pj.primer_apellido,'')+ISNULL(pj.segundo_apellido,'') LIKE ")
					.append("'%"+filtro.getPersonaJuridica()+"%'");
		}
		nativeQuery.append(" ORDER BY ");
		nativeQuery.append("	APELLIDOS, NOMBRES ");


		Query query = em.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, filtro.getRol());
		query.setParameter(2, filtro.getApellidos());
		query.setParameter(3, filtro.getNombres());
		query.setParameter(4, filtro.getTipoDocumento());
		query.setParameter(5, filtro.getNumeroDocumento());
		query.setParameter(6, filtro.getPersonaJuridica());

		List<Object[]> resultados = query.getResultList();

		return convertirResultadoAInfoPrestadorDTO(resultados);
	}

	/**
	 * ADM-C-003
	 * 
	 * @param registros
	 * @return
	 */
	private List<InfoPrestadorDTO> convertirResultadoAInfoPrestadorDTO(List<Object[]> registros) {
		List<InfoPrestadorDTO> prestadores = new ArrayList<>();
		for (Object[] registro : registros) {
			prestadores.add(convertirRegistroAInfoPrestadorDTO(registro));
		}

		return prestadores;
	}

	/**
	 * ADM-C-003
	 * 
	 * @param registro
	 * @return
	 */
	private InfoPrestadorDTO convertirRegistroAInfoPrestadorDTO(Object[] registro) {
		InfoPrestadorDTO prestador = new InfoPrestadorDTO();

		prestador.setApellidos(UtilConsultasSQL.procesarRegistroString(registro[0]));
		prestador.setNombres(UtilConsultasSQL.procesarRegistroString(registro[1]));
		prestador.setTipoDocumento(UtilConsultasSQL.procesarRegistroString(registro[2]));
		prestador.setNumeroDocumento(UtilConsultasSQL.procesarRegistroString(registro[3]));
		prestador.setTarjetaProfesional(UtilConsultasSQL.procesarRegistroString(registro[4]));
		prestador.setFechaNacimiento(UtilConsultasSQL.procesarRegistroDate(registro[5]));
		prestador.setLista(UtilConsultasSQL.procesarRegistroString(registro[6]));
		prestador.setMaterias(UtilConsultasSQL.procesarRegistroString(registro[7]));
		prestador.setDirecciones(UtilConsultasSQL.procesarRegistroString(registro[8]));
		prestador.setTelefonos(UtilConsultasSQL.procesarRegistroString(registro[9]));
		prestador.setCelular(UtilConsultasSQL.procesarRegistroString(registro[10]));
		prestador.setCorreoElectronico(UtilConsultasSQL.procesarRegistroString(registro[11]));
		prestador.setActaAprobacion(UtilConsultasSQL.procesarRegistroString(registro[12]));
		prestador.setFechaAprobacion(UtilConsultasSQL.procesarRegistroDate(registro[13]));
		prestador.setFechaUltimaActualizacion(UtilConsultasSQL.procesarRegistroDate(registro[14]));
		prestador.setReportadoSicaac(UtilConsultasSQL.procesarRegistroString(registro[15]));
		prestador.setPersonaJuridica(UtilConsultasSQL.procesarRegistroString(registro[16]));

		return prestador;
	}

	/**
	 * Retorna el registro de RolPersona basado en el id del rol y el id de la
	 * persona
	 * 
	 * @param idRol
	 * @param idPersona
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RolPersona> obtenerRolPersona(Long idRol, Long idPersona) {
		StringBuilder jpqlQuery = new StringBuilder("SELECT r FROM RolPersona r WHERE r.estadoRegistro = :estado AND r.idRol = :idRol AND r.idPersona =:idPersona");
		jpqlQuery.append(" AND r.fechaInicioVigencia <= :")
		.append(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA).append(" AND  (")
		.append(" r.fechaFinVigencia IS NULL").append(" OR ").append(" r.fechaFinVigencia >= :")
		.append(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA).append(" )");
		Query query = em
					.createQuery(jpqlQuery.toString(),RolPersona.class)
					.setParameter("idRol", idRol)
					.setParameter("estado", UtilDominios.ESTADO_REGISTRO_ACTIVO)
					.setParameter("idPersona", idPersona)
					.setParameter(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA, new Date());
		return query.getResultList();
	}

	/**
	 * ADM-F-030 Método que obtiene las personas naturales con rol perito que
	 * con o sin asociación a una persona juridica
	 * 
	 * @param idPersonaJuridica
	 *            si el dato viene nulo se retornar todas las personas sin
	 *            asociacion o en caso contrario aquellas asociadas a la persona
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> consultarPeritoSinAsociar(Long idPersonaJuridica) {
		StringBuilder string = new StringBuilder();
		string.append("SELECT DISTINCT rp.persona FROM RolPersona rp WHERE rp.rol.nombre = :")
				.append(Rol.ENTIDAD_ROL_NOMBRE).append(" AND rp.persona.estadoRegistro = :")
				.append(Rol.ENTIDAD_ROL_ESTADO_REGISTRO)
				.append(" AND rp.persona.tipoPersona = :").append(Persona.ENTIDAD_PERSONA_TIPO_PERSONA);
		if (idPersonaJuridica == null)
			string.append(" AND rp.persona.idPersonaJuridica IS NULL");
		else
			string.append(" AND rp.persona.idPersonaJuridica = :").append(Persona.ENTIDAD_PERSONA_ID_PERSONA_JURIDICA);
		string.append(" AND rp.rol.estadoRegistro =: ")
				.append(Rol.ENTIDAD_ROL_ESTADO_REGISTRO)
				.append(" 		AND rp.fechaInicioVigencia <= :")
				.append(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA).append(" 		AND  (")
				.append("				rp.fechaFinVigencia IS NULL").append(" 				OR ")
				.append("				rp.fechaFinVigencia >= :")
				.append(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA).append(" 			 )")
				.append(" AND rp.estadoRegistro =:")
				.append(Rol.ENTIDAD_ROL_ESTADO_REGISTRO)
				.append(" ORDER BY rp.persona.primerNombreORazonSocial");
		Query query = mp.createQuery(string.toString());
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, UtilDominios.ROL_PERSONA_PERITO);
		query.setParameter(Rol.ENTIDAD_ROL_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_TIPO_PERSONA, UtilDominios.TIPO_PERSONA_NATURAL);
		if (idPersonaJuridica != null)
			query.setParameter(Persona.ENTIDAD_PERSONA_ID_PERSONA_JURIDICA, idPersonaJuridica);
		query.setParameter(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA, new Date());
		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()),
					e);
		}
	}

	/**
	 * Consulta las personas por centro y roles
	 * 
	 * @param roles
	 * @param centro
	 *            al cual se asocia la persona, si es null retorna todos los
	 *            centros
	 * @return lista de persona sin repetir, que cumplan las condiciones
	 *         enviadas
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> consultarPesonasPorRolPersonaCentro(List<String> roles, List<Long> centro,
			Date fechaVigencia) {
		Date fechaFinDia = null;
		Date fechaInicioDia = null;
		if (fechaVigencia != null) {
			fechaFinDia = UtilOperaciones.obtenerFechaFinDelDia(fechaVigencia);
			fechaInicioDia = UtilOperaciones.obtenerFechaComienzoDelDia(fechaVigencia);
		}
		StringBuilder string = new StringBuilder();
		string.append(" SELECT  DISTINCT(rp.persona) FROM RolPersona rp WHERE rp.rol.nombre IN :");
		string.append(RolPersona.ENTIDAD_ROL_PERSONA_ID_ROL);
		if (fechaVigencia != null) {
			string.append(" AND (rp.fechaFinVigencia IS NULL OR rp.fechaFinVigencia >= :");
			string.append(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_FIN_VIGENCIA).append(" )");
			string.append(" AND rp.fechaInicioVigencia <= :");
			string.append(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA);
		}
		if (centro != null && !centro.isEmpty()) {
			string.append(" AND rp.idCentro IN :");
			string.append(RolPersona.ENTIDAD_ROL_PERSONA_ID_CENTRO);
		}
		string.append(" AND rp.persona.estadoRegistro =: ");
		string.append(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO);
		string.append(" AND rp.estadoRegistro =: ");
		string.append(RolPersona.ENTIDAD_ROL_PERSONA_ESTADO_REGISTRO);
		string.append(
				" ORDER BY rp.persona.primerNombreORazonSocial, rp.persona.segundoNombre ,rp.persona.primerApellido ,rp.persona.segundoApellido ");

		Query query = mp.createQuery(string.toString());
		query.setParameter(RolPersona.ENTIDAD_ROL_PERSONA_ID_ROL, roles);
		if (fechaVigencia != null) {
			query.setParameter(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA, fechaInicioDia);
			query.setParameter(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_FIN_VIGENCIA, fechaFinDia);
		}
		if (centro != null && !centro.isEmpty()) {
			query.setParameter(RolPersona.ENTIDAD_ROL_PERSONA_ID_CENTRO, centro);
		}
		query.setParameter(RolPersona.ENTIDAD_ROL_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();

	}

	/**
	 * consutla los conciliadores por los filtros seleccionados, teniendo en
	 * encuenta el estado de rol_persona_caso.
	 * 
	 * @param estadoRpc
	 * @param estadoTipoServicio
	 * @param centros
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> conciliadoresActivosPorEstadoRpc(BusquedaRolesActivosDTO datosConsulta) {

		Date fechaFinDia = null;
		Date fechaInicioDia = null;
		if (datosConsulta.getFechaVigenciaRol() != null) {
			fechaFinDia = UtilOperaciones.obtenerFechaFinDelDia(datosConsulta.getFechaVigenciaRol());
			fechaInicioDia = UtilOperaciones.obtenerFechaComienzoDelDia(datosConsulta.getFechaVigenciaRol());
		}
		StringBuilder nativeQuery = new StringBuilder();

		nativeQuery.append(" SELECT DISTINCT p.* FROM PERSONA p ");
		nativeQuery.append(" INNER JOIN ROL_PERSONA_CASO rpc ON P.id_persona = rpc.id_persona ");
		nativeQuery.append(" INNER JOIN ESTADO_PERSONA_TIPO_SERVICIO eps ON eps.id_persona = p.id_persona ");
		nativeQuery.append(" INNER JOIN ROL_PERSONA rp ON rp.id_rol = rpc.id_rol AND rp.id_persona = rpc.id_persona ");
		nativeQuery.append(" WHERE rpc.id_rol IN (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = ?2 ) ");
		nativeQuery.append(" AND rpc.estado = ?3 ");
		nativeQuery.append(" AND p.estado_registro = ?1 ");
		nativeQuery.append(" AND rpc.estado_registro = ?1 ");
		nativeQuery.append(" AND eps.estado_registro = ?1 ");
		nativeQuery.append(" AND rp.estado_registro = ?1 ");
		nativeQuery.append(" AND eps.tipo_servicio = ?2 ");
		nativeQuery.append(" AND eps.estado = ?4 ");
		if (datosConsulta.getCentro() != null && !datosConsulta.getCentro().isEmpty()) {
			nativeQuery.append(" AND rp.id_centro ");
			nativeQuery.append(UtilConsultasSQL.clausulaInSQLSNumeros(datosConsulta.getCentro()));
		}
		if (datosConsulta.getFechaVigenciaRol() != null) {
			nativeQuery.append(" AND (rp.fecha_fin_vigencia IS NULL OR rp.fecha_fin_vigencia >= ?5 ) ");
			nativeQuery.append(" AND rp.fecha_inicio_vigencia <= ?6 ");
		}
		nativeQuery.append(
				" ORDER BY p.primer_nombre_o_razon_social,p.segundo_nombre,p.primer_apellido,p.segundo_apellido ");
		Query query = em.createNativeQuery(nativeQuery.toString(), Persona.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(3, datosConsulta.getEstadoRolPersonaCaso());
		query.setParameter(4, datosConsulta.getEstadoTipoServicio());
		if (datosConsulta.getFechaVigenciaRol() != null) {
			query.setParameter(5, fechaFinDia);
			query.setParameter(6, fechaInicioDia);
		}
		return query.getResultList();
	}

	/**
	 * consutla los Roles Persona de tipo prestador vigentes de una persona, por
	 * rol, por lista, por centros y estado de la tabla
	 * estado_persona_tipo_servicio
	 * 
	 * @param idPersona
	 * @param centros
	 * @return List<RolPersona>
	 */
	@SuppressWarnings("unchecked")
	public List<RolPersona> consultarRolPersonaPrestadorPersonaRolLista(Long idPersona, String nombreRol,
			String nombreLista, List<String> estadoEPTS, List<String> idCentros) {
		StringBuilder nativeQuery = new StringBuilder();

		nativeQuery.append(" SELECT rp.* FROM ROL_PERSONA rp ");
		nativeQuery.append(" INNER JOIN PERSONA p ");
		nativeQuery.append(" ON p.id_persona = rp.id_persona ");
		nativeQuery.append(" INNER JOIN ROL rol ");
		nativeQuery.append(" ON rol.id_rol = rp.id_rol ");
		nativeQuery.append(" INNER JOIN ESTADO_PERSONA_ROL epr ");
		nativeQuery.append(" ON epr.id_persona = p.id_persona ");
		nativeQuery.append(" AND epr.id_rol = rol.id_rol ");
		nativeQuery.append(" LEFT JOIN lista l ");
		nativeQuery.append(" ON l.id_lista = rp.id_lista ");
		nativeQuery.append(" AND l.estado_registro = ?1 ");
		nativeQuery.append(" WHERE rp.estado_registro = ?1 ");
		nativeQuery.append(" AND ( rp.fecha_fin_vigencia is null  ");
		nativeQuery.append(" OR ");
		nativeQuery.append(" rp.fecha_fin_vigencia >= ?3 )  ");
		nativeQuery.append(
				" AND (rol.id_rol in ( SELECT distinct(id_rol) FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = ?2 and estado_registro = ?1 ) ");
		nativeQuery.append(
				" OR rol.id_rol in ( select distinct(id_rol) from PARAMETRO_SERVICIO_SORTEO where estado_registro = ?1 )) ");
		if (nombreRol != null)
			nativeQuery.append(" AND rol.nombre = ?5 ");
		if (idPersona != null)
			nativeQuery.append(" AND p.id_persona = ?4 ");
		if (nombreLista != null)
			nativeQuery.append(" AND l.nombre = ?6 ");
		if (estadoEPTS != null && !estadoEPTS.isEmpty())
			nativeQuery.append(" AND epr.id_motivo ").append(UtilConsultasSQL.clausulaInSQLStrings(estadoEPTS));
		if (idCentros != null && !idCentros.isEmpty())
			nativeQuery.append(" AND rp.id_centro ").append(UtilConsultasSQL.clausulaInSQLSNumeros(idCentros));
		nativeQuery.append(" AND epr.estado_registro = ?1 ");
		nativeQuery.append(" AND p.estado_registro = ?1 ");
		nativeQuery.append(" AND rol.estado_registro = ?1 ");
		nativeQuery.append(" ORDER BY rol.nombre ");

		Query query = em.createNativeQuery(nativeQuery.toString(), RolPersona.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(3, new Date());
		query.setParameter(4, idPersona);
		query.setParameter(5, nombreRol);
		query.setParameter(6, nombreLista);

		return query.getResultList();
	}

	/**
	 * CON-C-004 Importante: no cambiar el ORDER BY de la consulta Calcula la
	 * duracion de un rol-persona por persona, rol y lista, agrupado por centro
	 * 
	 * @param nombreRol
	 * @param idPersona
	 * @param idLista
	 * @return List<BigDecimal> no se hizo el cast de BigDecimal a Long
	 */
	@SuppressWarnings("unchecked")
	public List<BigDecimal> duracionRolPersonaPorRolPersonaLista(String nombreRol, Long idPersona,
			List<String> nombreLista) {

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(
				" SELECT SUM (DATEDIFF ( dd, rp.fecha_inicio_vigencia , ISNULL( rp.fecha_fin_vigencia, GETDATE() ) ) ) AS duracion");
		nativeQuery.append(" FROM ROL_PERSONA rp ");
		nativeQuery.append(" INNER JOIN rol r ");
		nativeQuery.append(" ON rp.id_rol = r.id_rol ");
		nativeQuery.append(" INNER JOIN persona p ");
		nativeQuery.append(" ON rp.id_persona = p.id_persona ");
		nativeQuery.append(" INNER JOIN LISTA l ");
		nativeQuery.append(" ON l.id_lista = rp.id_lista ");
		nativeQuery.append(" INNER JOIN centro c ");
		nativeQuery.append(" ON c.id_centro = rp.id_centro ");
		nativeQuery.append(" WHERE rp.estado_registro = ?1 ");
		nativeQuery.append(" AND r.nombre = ?2 ");
		nativeQuery.append(" AND p.id_persona = ?3 ");
		if (nombreLista != null && !nombreLista.isEmpty())
			nativeQuery.append(" AND l.nombre ").append(UtilConsultasSQL.clausulaInSQLStrings(nombreLista));
		nativeQuery.append(" AND r.estado_registro = ?1 ");
		nativeQuery.append(" AND p.estado_registro = ?1 ");
		nativeQuery.append(" AND l.estado_registro = ?1 ");
		nativeQuery.append(" AND c.estado_registro = ?1 ");
		nativeQuery.append(" GROUP BY rp.id_centro, c.nombre ");
		nativeQuery.append(" ORDER by duracion DESC");

		Query query = em.createNativeQuery(nativeQuery.toString(), BigDecimal.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, nombreRol);
		query.setParameter(3, idPersona);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ConsultaPrestadoresDTO> consultarPersonasPorRolMateria(ConsultaPrestadoresDTO filtros) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(
				" SELECT DISTINCT concat(rtrim(pres.primer_nombre_o_razon_social), rtrim(' '+pres.segundo_nombre), rtrim(' '+pres.primer_apellido), rtrim(' '+pres.segundo_apellido)) AS nombrePrestador, ");
		nativeQuery.append(" pres.id_persona AS idPersona, ");
		nativeQuery.append(" rol.id_rol AS idRol, ");
		nativeQuery.append(" l.nombre AS nombreLista, ");

		nativeQuery.append(" stuff(( select distinct ( ', '+m.nombre ) ");
		nativeQuery.append(" from materia m ");
		nativeQuery.append(" inner join persona_servicio_materia psms on psms.id_materia = m.id_materia ");
		nativeQuery.append(" inner join servicio se on se.id_servicio = psms.id_servicio ");
		nativeQuery.append(" where rol.tipo_servicio = se.tipo ");
		nativeQuery.append(" and psms.id_persona = pres.id_persona ");
		nativeQuery.append(" and m.estado_registro = ?1 ");
		nativeQuery.append(" and psms.estado_registro = ?1 ");
		nativeQuery.append(" and se.estado_registro = ?1 ");
		nativeQuery.append(" order by ', '+m.nombre   ");
		nativeQuery.append(" FOR xml PATH ('')), 1, 2, '') AS materias, ");

		nativeQuery.append(
				" concat(rtrim(pjur.primer_nombre_o_razon_social), rtrim(' '+pjur.segundo_nombre), rtrim(' '+pjur.primer_apellido), rtrim(' '+pjur.segundo_apellido)) AS nombrePersonaJuridica ");
		nativeQuery.append(" FROM PERSONA pres ");
		nativeQuery.append(" CROSS APPLY( ");
		nativeQuery.append(" select top 1 rp_ca.* from ROL_PERSONA rp_ca ");
		nativeQuery.append(" inner join rol rol_ca ");
		nativeQuery.append(" on rol_ca.id_rol = rp_ca.id_rol ");
		nativeQuery.append(" and rol_ca.nombre = ?2 ");
		nativeQuery.append(" where pres.id_persona = rp_ca.id_persona ");
		nativeQuery.append(" and ( rp_ca.fecha_fin_vigencia is null or ");
		nativeQuery.append(" rp_ca.fecha_fin_vigencia >= GETDATE() ) ");
		nativeQuery.append(" and rp_ca.estado_registro = ?1 ");
		nativeQuery.append(" and rol_ca.estado_registro = ?1 ");
		nativeQuery.append(" order by rp_ca.fecha_inicio_vigencia desc ) rp ");
		nativeQuery.append(" INNER JOIN ROL rol ");
		nativeQuery.append(" ON rol.id_rol = rp.id_rol ");
		if (filtros.getIdMateria() != null) {
			nativeQuery.append(" INNER JOIN PERSONA_SERVICIO_MATERIA psm ");
			nativeQuery.append(" ON psm.id_persona = rp.id_persona ");
			nativeQuery.append(" AND psm.estado_registro = ?1 ");
			nativeQuery.append(" AND psm.id_materia = ?3 ");
			nativeQuery.append(" INNER JOIN SERVICIO ser ");
			nativeQuery.append(" ON ser.tipo = rol.tipo_servicio ");
			nativeQuery.append(" AND ser.id_servicio = psm.id_servicio ");
			nativeQuery.append(" AND ser.estado_registro = ?1 ");
		}
		nativeQuery.append(" LEFT JOIN LISTA l ");
		nativeQuery.append(" ON l.id_lista = rp.id_lista ");
		nativeQuery.append(" AND l.estado_registro = ?1 ");
		nativeQuery.append(" LEFT JOIN PERSONA pjur ");
		nativeQuery.append(" ON pjur.id_persona = pres.id_persona_juridica ");
		nativeQuery.append(" AND pjur.estado_registro = ?1 ");
		nativeQuery.append(" WHERE pres.estado_registro = ?1 ");
		if (filtros.getNombrePrestador() != null)
			nativeQuery.append(" AND ")
					.append(UtilConsultasSQL.consultarPorNombresPersonaAliasTabla("pres", filtros.getNombrePrestador()));
		if (filtros.getApellidoPrestador() != null)
			nativeQuery.append(" AND ")
					.append(UtilConsultasSQL.consultarPorApellidosPersonaAliasTabla( "pres", filtros.getApellidoPrestador()));
		if (filtros.getNumeroDocumento() != null)
			nativeQuery.append(" AND pres.numero_documento LIKE ?5 ");
		if (filtros.getNombreLista() != null)
			nativeQuery.append(" AND l.nombre = ?4 ");
		if (filtros.getNombrePersonaJuridica() != null)
			nativeQuery.append(" AND ").append(
					UtilConsultasSQL.consultarNombreCompletoPersonaAliasTabla("pjur", filtros.getNombrePersonaJuridica()));
		nativeQuery.append(" AND rol.estado_registro = ?1 ");
		nativeQuery.append(" ORDER by nombrePrestador");

		Query query = em.createNativeQuery(nativeQuery.toString(), ConsultaPrestadoresDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, filtros.getNombreRol());
		query.setParameter(3, filtros.getIdMateria());
		query.setParameter(4, filtros.getNombreLista());
		query.setParameter(5, "%"+ filtros.getNumeroDocumento()+"%");

		return query.getResultList();
	}

	
	/**
	 * consulta los Roles de prestadores de servicio por id persona y centro sin tener en cuenta el estado en la tabla estado persona tipo servicio
	 * 
	 * @param idPersona
	 * @return List<RolPersona>
	 */
	@SuppressWarnings("unchecked")
	public List<RolPersonaDTO> consultarRolPersona(Long idPersona) {
		StringBuilder nativeQuery = new StringBuilder();

		nativeQuery.append(" SELECT d.nombre as nombreRol, rp.id_rol_persona as idRolPersona, rol.tipo_servicio as tipoServicio, rp.id_centro as idCentro, rp.id_rol as idRol FROM ROL_PERSONA rp  ");
		nativeQuery.append(" INNER JOIN PERSONA p ");
		nativeQuery.append(" ON p.id_persona = rp.id_persona ");
		nativeQuery.append(" INNER JOIN ROL rol ");
		nativeQuery.append(" ON rol.id_rol = rp.id_rol ");
		nativeQuery.append(" INNER JOIN dominio d on d.codigo=rol.nombre  ");
		nativeQuery.append(" WHERE rp.estado_registro = ?1 ");
		nativeQuery.append(" and rol.id_rol in (  SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = ?2 and estado_registro = ?1 ");
		nativeQuery.append(" OR rol.id_rol in ( select distinct(id_rol) from PARAMETRO_SERVICIO_SORTEO where estado_registro = ?1 ) or rol.nombre=?6)   ");
		nativeQuery.append(" and d.dominio= ?5  ");
		nativeQuery.append(" AND ( rp.fecha_fin_vigencia is null   ");
		nativeQuery.append(" OR rp.fecha_fin_vigencia >= ?3) ");
		nativeQuery.append(" AND p.id_persona = ?4 ");
		nativeQuery.append(" AND p.estado_registro = ?1 ");
		nativeQuery.append(" AND rol.estado_registro = ?1 ");
		

		Query query = em.createNativeQuery(nativeQuery.toString(), RolPersonaDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(3, new Date());
		query.setParameter(4, idPersona);
		query.setParameter(5, UtilDominios.DOMINIO_ROL_PERSONA);
		query.setParameter(6, UtilDominios.ROL_PERSONA_PERITO);
		
		return query.getResultList();
	}
	
	/**
	 * Consultar personas aleatoriamente por centro (Control de cambios
	 * seguimiento a casos).
	 * 
	 * @param roles
	 * @param centro
	 *            al cual se asocia la persona, si es null retorna todos los
	 *            centros
	 * @return lista de persona sin repetir, que cumplan las condiciones
	 *         enviadas
	 */
	@SuppressWarnings("unchecked")
	public List<PersonaAleatoriaDTO> consultarPersonasAleatorioPorCentro(List<String> roles, List<Long> centros,
			Date fechaVigencia) {
		Date fechaFinDia = null;
		Date fechaInicioDia = null;
		if (fechaVigencia != null) {
			fechaFinDia = UtilOperaciones.obtenerFechaFinDelDia(fechaVigencia);
			fechaInicioDia = UtilOperaciones.obtenerFechaComienzoDelDia(fechaVigencia);
		}
		StringBuilder string = new StringBuilder();

		string.append(" SELECT  DISTINCT(rp.id_persona) AS idPersona, ");
		string.append(" r.id_rol AS idRol, ");
		string.append(
				" CONCAT(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) AS nombrePersona, ");
		string.append(" CHECKSUM(NEWID()) AS aleatorio ");

		string.append(" FROM ROL_PERSONA rp ");

		string.append(" INNER JOIN PERSONA p ");
		string.append(" ON p.id_persona = rp.id_persona ");
		string.append(" AND p.estado_registro = ?1 ");

		string.append(" INNER JOIN ROL r  ");
		string.append(" ON r.id_rol = rp.id_rol ");
		string.append(" AND r.estado_registro = ?1 ");
		string.append(" AND r.nombre ").append(UtilConsultasSQL.clausulaInSQLStrings(roles));

		if (fechaVigencia != null) {
			string.append(" WHERE (rp.fecha_fin_vigencia IS NULL OR rp.fecha_fin_vigencia >= ?2 ) ");
			string.append(" AND rp.fecha_inicio_vigencia <= ?3 ");
		}
		if (centros != null && !centros.isEmpty()) {
			string.append(" AND rp.id_centro ").append(UtilConsultasSQL.clausulaInSQLSNumeros(centros));
		}
		string.append(" AND rp.estado_registro = ?1 ");
		string.append(" ORDER BY aleatorio ");

		Query query = em.createNativeQuery(string.toString(), PersonaAleatoriaDTO.class);

		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		if (fechaVigencia != null) {
			query.setParameter(2, fechaInicioDia);
			query.setParameter(3, fechaFinDia);
		}

		return query.getResultList();

	}

	/**
	 * Metodo que permite consultar los conciliadores del centro cuya membresia
	 * se encuentra proxima a vencer.
	 * 
	 * @param tablaEncabezado:
	 *            encabezado de la tabla.
	 * @param tablaCierre:
	 *            cierre elementos tabla html.
	 * @param tablaTextoEncabezado:
	 *            texto titulo encabezado de la tabla.
	 * @param tipoPeriodicidad:
	 *            calendario o habil.
	 * @param diaMinimo:
	 *            dia minimo de ejecucion alerta.
	 * @param diaMaximo:
	 *            dia maximo de ejecucion alerta.
	 * @return List<InfoBasicaAlertasDTO>.
	 */
	@SuppressWarnings("unchecked")
	public List<InfoBasicaAlertasDTO> consultarConciliadoresVencimientoMembresia(String tablaEncabezado,
			String tablaCierre, String tablaTextoEncabezado, String tipoPeriodicidad, Long diaMinimo, Long diaMaximo) {

		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append(" DECLARE @TablaEncabezado varchar(max), ");
		sqlQuery.append(" @TablaCierre varchar(max), ");
		sqlQuery.append(" @TablaTextoEncabezado varchar(max) ");
		sqlQuery.append(" Set @TablaCierre = ?1 ");
		sqlQuery.append(" Set @TablaEncabezado = ?2 ");
		sqlQuery.append(" Set @TablaTextoEncabezado = ?3 ");
		sqlQuery.append(" (SELECT * FROM (SELECT DISTINCT rp.id_centro AS idCentro, c.nombre AS nombreCentro,  @TablaEncabezado + @TablaTextoEncabezado + ");
		sqlQuery.append(" (SELECT CONCAT(per.primer_nombre_o_razon_social, ' ', per.segundo_nombre, ' ', per.primer_apellido, ' ', per.segundo_apellido) AS td, ");
		if (UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad)) {
			sqlQuery.append(" dbo.diasHabilesEntreDosFechas( GETDATE(), mem.fecha_fin) AS td  ");
		} else {
			sqlQuery.append(" DATEDIFF(D, GETDATE(), mem.fecha_fin) AS td  ");
		}

		sqlQuery.append(" FROM MEMBRESIA mem ");
		sqlQuery.append(" INNER JOIN PERSONA per ");
		sqlQuery.append(" ON per.id_persona = mem.id_persona ");
		sqlQuery.append(" AND per.estado_registro = ?4 ");
		sqlQuery.append(" INNER JOIN ROL re ");
		sqlQuery.append(" ON  re.id_rol = rp.id_rol ");
		sqlQuery.append(" AND re.nombre = ?5 ");
		sqlQuery.append(" AND re.estado_registro = ?4 ");
		sqlQuery.append(" WHERE mem.estado_registro = ?4 ");
		sqlQuery.append(" AND rp.id_persona = per.id_persona ");
		sqlQuery.append(" AND rp.estado_registro = ?4 ");

		if (UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad)) {
			sqlQuery.append(" dbo.diasHabilesEntreDosFechas( GETDATE(), mem.fecha_fin) BETWEEN ?6 AND ?7  ");
		} else {
			sqlQuery.append(" AND DATEDIFF(D, GETDATE(), mem.fecha_fin) BETWEEN ?6 AND ?7 ");
		}

		sqlQuery.append(" FOR xml RAW ('tr'), ELEMENTS) + @TablaCierre AS tabla  ");
		sqlQuery.append(" FROM ROL_PERSONA rp, CENTRO c WHERE rp.id_centro = c.id_centro) AS t  ");
		sqlQuery.append(" WHERE t.tabla IS NOT NULL)  ");

		Query query = em.createNativeQuery(sqlQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, tablaCierre);
		query.setParameter(2, tablaEncabezado);
		query.setParameter(3, tablaTextoEncabezado);

		query.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(5, UtilDominios.ROL_PERSONA_CONCILIADOR);

		query.setParameter(6, diaMinimo);
		query.setParameter(7, diaMaximo);

		return query.getResultList();
	}

	/**
	 * Consulta los funcionarios del CAC
	 * 
	 * @param idCaso
	 * @param roles
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> consultarFuncionariosCAC(List<String> roles) {
		
		StringBuilder nativeQuery = new StringBuilder();

		nativeQuery.append(" SELECT DISTINCT p.* FROM PERSONA p ");
		nativeQuery.append(" INNER JOIN ROL_PERSONA rp ON rp.id_persona = p.id_persona ");
		nativeQuery.append(" WHERE rp.id_rol in (select id_rol from rol where nombre ").append(UtilConsultasSQL.clausulaInSQLStrings(roles));
		nativeQuery.append(" ) AND p.estado_registro = ?1 ");
		nativeQuery.append(" AND rp.estado_registro = ?1 ");
		nativeQuery.append(" AND (rp.fecha_fin_vigencia IS NULL OR rp.fecha_fin_vigencia >= ?2 ) ");
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), Persona.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);	
		query.setParameter(2, new Date());
		return query.getResultList();
	}
	
	/**
	 * Obtiene los estados que omiten el cambio de estado con la alerta ARBEME
	 */
	public List<String> obtenerEstadosArbitrosNoEmeritos() {
		List<String> estados = new ArrayList<String>();
		estados.add(UtilDominios.ESTADO_ARBITROS_EMERITO);
		estados.add(UtilDominios.ESTADO_ARBITROS_RETIRADO);
		estados.add(UtilDominios.ESTADO_ARBITROS_RETIRADO_EXCLUSION);
		estados.add(UtilDominios.ESTADO_ARBITROS_FALLECIDO);		
		return estados;
	}
	
	/**
	 * Devuelve los roles persona que están activos asociados a la persona
	 * 
	 * @param idPersona
	 * @return Lista de RolPersona que están activos
	 */
	@SuppressWarnings("unchecked")
	public List<Rol> consultarRolesPersonaActivosPorPersonaEnParametroServicios(Long idPersona) {
		StringBuilder string = new StringBuilder();
		string.append("SELECT rp.rol FROM RolPersona rp ").append("	WHERE ").append("       rp.persona.idPersona = :")
				.append(Persona.ENTIDAD_PERSONA_PK).append(" 		AND rp.persona.estadoRegistro = ")
				.append("'ACT'").append(" 		AND rp.rol.estadoRegistro = ")
				.append("'ACT'").append(" 		AND rp.fechaInicioVigencia <= :")
				.append(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA).append(" 		AND  (")
				.append("				rp.fechaFinVigencia IS NULL").append(" 				OR ")
				.append("				rp.fechaFinVigencia >= :")
				.append(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA).append(" 			 )")
				.append(" 		AND rp.estadoRegistro = ").append("'ACT'")
				.append(" 		AND rp.rol.tipoServicio = :").append(Rol.ENTIDAD_ROL_TIPO_SERVICIO)
				.append("    	AND rp.idRol in (SELECT DISTINCT pss.idRol FROM ParametroServicioSorteo pss)	")
				.append(" 	ORDER BY rp.rol.nombre");
		Query query = mp.createQuery(string.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		query.setParameter(RolPersona.ENTIDAD_ROL_PERSONA_FECHA_INICIO_VIGENCIA, new Date());
		query.setParameter(Rol.ENTIDAD_ROL_TIPO_SERVICIO, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);

		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()),
					e);
		}
	}
	// protected region metodos adicionales end

	/**
	 * consulta si la persona es un arbitro en una fecha particular
	 * 
	 * @param idPersona
	 * @param fecha
	 *            en la cual se revisa la vigencia del rol
	 * @return
	 */
	public Long consultarRolOperadorPorServicio(Long idPersona, Long idServicio) {
		StringBuilder stringQuery = new StringBuilder();
		Long resultados = null;
		stringQuery.append("SELECT     rp.id_rol ");
		stringQuery.append("FROM       PERSONA p ");
		stringQuery.append("           INNER JOIN ROL_PERSONA rp ");
		stringQuery.append("           ON         rp.id_persona = p.id_persona ");
		stringQuery.append("           INNER JOIN PARAMETRO_SERVICIO_SORTEO pss ");
		stringQuery.append("           ON         rp.id_rol        = pss.id_rol ");
		stringQuery.append("WHERE      p.estado_registro           = ?1 ");
		stringQuery.append("AND        rp.estado_registro          = ?1 ");
		stringQuery.append("AND        p.id_persona                = ?2 ");
		stringQuery.append("AND        rp.fecha_fin_vigencia IS NULL ");
		stringQuery.append("AND        pss.id_servicio             = ?3");

		Query query = mp.getEntityManager().createNativeQuery(stringQuery.toString());
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idPersona);
		query.setParameter(3, idServicio);

		try{
			Object resultado = query.getSingleResult();
			
			if(resultado != null){
				resultados = new Long(resultado.toString());
			}		
		} catch (NoResultException | NonUniqueResultException e) {
			resultados = null;
		}
		return resultados;
	}
	
	@SuppressWarnings("unchecked")
	public List<RolPersona> consultarRolPersonasPorRolesByIdRol(Long idRol) {
		StringBuilder string = new StringBuilder();
		string.append("SELECT * FROM ROL_PERSONA  ")
				.append(" WHERE .id_rol = ?1 ")
				.append(" AND estado_registro = ?2")
				.append(" AND fecha_fin_vigencia IS NULL OR fecha_fin_vigencia > GETDATE() ");

		Query query = mp.getEntityManager().createNativeQuery(string.toString(), RolPersona.class);
		query.setParameter(1, idRol);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()),
					e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<RolPersonaDTO> consultarRolPersonaPorServicio(Long idPersona, Long idServicio) {
		
		StringBuilder nativeSql = new StringBuilder();

		nativeSql.append(" SELECT d.nombre as nombreRol, rp.id_rol_persona as idRolPersona, rol.tipo_servicio as tipoServicio, rp.id_centro as idCentro, rp.id_rol as idRol FROM ROL_PERSONA rp  ");
		nativeSql.append(" INNER JOIN PERSONA p ");
		nativeSql.append(" ON p.id_persona = rp.id_persona ");
		nativeSql.append(" INNER JOIN ROL rol ");
		nativeSql.append(" ON rol.id_rol = rp.id_rol ");
		nativeSql.append(" INNER JOIN dominio d on d.codigo=rol.nombre  ");
		nativeSql.append(" INNER JOIN PARAMETRO_SERVICIO_SORTEO pss on pss.id_rol = rol.id_rol ");
		nativeSql.append(" INNER JOIN SERVICIO s on s.id_servicio = pss.id_servicio ");
		nativeSql.append(" WHERE rp.estado_registro = ?1 ");
		nativeSql.append(" and rol.id_rol in (  SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = ?2 and estado_registro = ?1 ");
		nativeSql.append(" OR rol.id_rol in ( select distinct(id_rol) from PARAMETRO_SERVICIO_SORTEO where estado_registro = ?1 ) or rol.nombre=?6)   ");
		nativeSql.append(" and d.dominio= ?5  ");
		nativeSql.append(" AND ( rp.fecha_fin_vigencia is null   ");
		nativeSql.append(" OR rp.fecha_fin_vigencia >= ?3) ");
		nativeSql.append(" AND p.id_persona = ?4 ");
		nativeSql.append(" AND p.estado_registro = ?1 ");
		nativeSql.append(" AND rol.estado_registro = ?1 ");
		nativeSql.append(" AND s.id_servicio = ?7 ");
		

		Query query = em.createNativeQuery(nativeSql.toString(), RolPersonaDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(3, new Date());
		query.setParameter(4, idPersona);
		query.setParameter(5, UtilDominios.DOMINIO_ROL_PERSONA);
		query.setParameter(6, UtilDominios.ROL_PERSONA_PERITO);
		query.setParameter(7, idServicio);
		
		return query.getResultList();
	}

}
