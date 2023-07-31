package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

// Escriba en esta secciÃ³n sus modificaciones


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.ConciliadorSeleccionadoDTO;
import com.ccb.simasc.transversal.dto.FiltosPreseleccionArbitros;
import com.ccb.simasc.transversal.dto.FiltrosPreseleccionArbitrosCCB;
import com.ccb.simasc.transversal.dto.InformacionPersonaDTO;
import com.ccb.simasc.transversal.dto.InformacionPrestadorDTO;
import com.ccb.simasc.transversal.dto.PersonaMateriaAsignadaDTO;
import com.ccb.simasc.transversal.dto.alertas.AlertaEmeritoDTO;
import com.ccb.simasc.transversal.dto.formularios.BusquedaRolesActivosDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltrosSeleccionConciliadorDTO;
import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.ParamEstadoArbitroPreseleccion;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad Persona.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorPersona extends ManejadorCrud<Persona, Long> {

	// protected region atributos on begin
	// Escriba en esta secciÃ³n sus modificaciones
	private static final Logger logger = LogManager.getLogger(ManejadorCrud.class.getName());

	@PersistenceContext
	private transient EntityManager em;

	@EJB
	private ManejadorRol manejadorRol;

	@EJB
	private ManejadorRolPersona manejadorRolPersona;
	// protected region atributos end

	public ManejadorPersona() {
		super(Persona.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta secciÃ³n sus modificaciones

	/**
	 * Consulta la cantidad de funcionarios activos con el rol necesario para
	 * asignar a un caso, si esta cantidad es cero se lanza una excepcion
	 * (SIMASCNegocioExcepcion) indicando esta situación.
	 * 
	 * @author Javier Estévez
	 * 
	 */
	public void validarRolParaAsignarCaso(List<Long> lstRoles) {

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append(" select count(1) ");
		sbQuery.append(" from persona p ");
		sbQuery.append(" inner join rol_persona rp on rp.id_persona = p.id_persona ");
		sbQuery.append(" where p.estado_persona = ?1 ");
		sbQuery.append(" and rp.id_rol ").append(UtilConsultasSQL.clausulaInSQLSNumeros(lstRoles));
		sbQuery.append(" and p.estado_registro = ?2 ");
		sbQuery.append(" and rp.estado_registro = ?2 ");
		
		Query query = em.createNativeQuery(sbQuery.toString());
		query.setParameter(1, UtilDominios.ESTADO_PERSONA_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		Integer resultado = (Integer) query.getSingleResult();

		if (resultado == null || resultado.intValue() == UtilConstantes.CERO) {
			throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR454.toString());
		}

	}

	/**
	 * Consulta la cantidad de funcionarios activos con el rol y sede necesarios
	 * para asignar a un caso, si esta cantidad es cero se lanza una excepcion
	 * (SIMASCNegocioExcepcion) indicando esta que no hay prestadores de
	 * servicio para la sede, se asume que la validación del método
	 * validarRolParaAsignarCaso fue realizada previamente.
	 * 
	 * @author Javier Estévez
	 * 
	 */
	public void validarRolSedeParaAsignarCaso(List<Long> lstRoles, Long idSede) {

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append(" select count(1) ");
		sbQuery.append(" from persona p ");
		sbQuery.append(" inner join rol_persona rp on rp.id_persona = p.id_persona ");
		sbQuery.append(" inner join persona_sede ps on ps.id_persona = p.id_persona ");
		sbQuery.append(" where p.estado_persona = ?1 ");
		sbQuery.append(" and rp.id_rol ").append(UtilConsultasSQL.clausulaInSQLSNumeros(lstRoles));
		sbQuery.append(" and ps.id_sede = ?2 ");
		sbQuery.append(" and p.estado_registro = ?3 ");
		sbQuery.append(" and rp.estado_registro = ?3 ");
		sbQuery.append(" and ps.estado_registro = ?3 ");

		Query query = em.createNativeQuery(sbQuery.toString());

		// se establecen los parametros
		query.setParameter(1, UtilDominios.ESTADO_PERSONA_ACTIVO);
		query.setParameter(2, idSede);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		Integer resultado = (Integer) query.getSingleResult();

		if (resultado == null || resultado.intValue() == UtilConstantes.CERO) {
			throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR455.toString());
		}

	}

	/**
	 * Consulta la cantidad de funcionarios activos con el rol, sede y materia
	 * necesarios para asignar a un caso, si esta cantidad es cero se lanza una
	 * excepcion (SIMASCNegocioExcepcion) indicando esta que no hay prestadores
	 * de servicio para la materia, se asume que la validación del método
	 * validarRolSedeParaAsignarCaso fue realizada previamente.
	 * 
	 * @author Javier Estévez
	 * 
	 */
	public void validarRolSedeMateriaParaAsignarCaso(List<Long> lstRoles, Long idSede, Caso caso) {

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append(" select count(1) ");
		sbQuery.append(" from persona p ");
		sbQuery.append(" inner join rol_persona rp on rp.id_persona = p.id_persona ");
		sbQuery.append(" inner join persona_sede ps on ps.id_persona = p.id_persona ");
		sbQuery.append(" inner join persona_servicio_materia psm on psm.id_persona = p.id_persona ");
		sbQuery.append(" where p.estado_persona = ?1 ");
		sbQuery.append(" and rp.id_rol ").append(UtilConsultasSQL.clausulaInSQLSNumeros(lstRoles));
		sbQuery.append(" and ps.id_sede = ?2 ");
		sbQuery.append(" and psm.id_materia = ?3 ");
		sbQuery.append(" and psm.id_servicio = ?4 ");
		sbQuery.append(" and p.estado_registro = ?5 ");
		sbQuery.append(" and rp.estado_registro = ?5 ");
		sbQuery.append(" and psm.estado_registro = ?5 ");
		sbQuery.append(" and ps.estado_registro = ?5 ");

		Query query = em.createNativeQuery(sbQuery.toString());

		// se establecen los parametros
		query.setParameter(1, UtilDominios.ESTADO_PERSONA_ACTIVO);
		query.setParameter(2, idSede);
		query.setParameter(3, caso.getIdMateria());
		query.setParameter(4, caso.getIdServicio());
		query.setParameter(5, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		Integer resultado = (Integer) query.getSingleResult();

		if (resultado == null || resultado.intValue() == UtilConstantes.CERO) {
			throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR456.toString());
		}

	}

	/**
	 * Consulta la cantidad de funcionarios activos con el rol, sede, materia y
	 * lista necesarios para asignar a un caso, si esta cantidad es cero se
	 * lanza una excepcion (SIMASCNegocioExcepcion) indicando esta que no hay
	 * prestadores de servicio para la lista, se asume que la validación del
	 * método validarRolSedeMateriaParaAsignarCaso fue realizada previamente.
	 * 
	 * @author Javier Estévez
	 * 
	 */
	public void validarRolSedeMateriaListaParaAsignarCaso(List<Long> lstRoles, Long idSede, Caso caso,
			Long valorPretencionesReferencia, Long valorPretencionesCaso) {

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append(" select count(1) ");
		sbQuery.append(" from persona p ");
		sbQuery.append(" inner join rol_persona rp on rp.id_persona = p.id_persona ");
		sbQuery.append(" inner join persona_sede ps on ps.id_persona = p.id_persona ");
		sbQuery.append(" inner join persona_servicio_materia psm on psm.id_persona = p.id_persona ");

		if (!caso.getTipoCuantia().equals(UtilDominios.TIPO_CUANTIA_CONCILIACION_INDETERMINADO)) {

			sbQuery.append(" inner join lista l on l.id_lista = rp.id_lista ");
		}

		sbQuery.append(" where p.estado_persona = ?1 ");
		sbQuery.append(" and rp.id_rol ").append(UtilConsultasSQL.clausulaInSQLSNumeros(lstRoles));

		sbQuery.append(" and ps.id_sede = ?2 ");
		sbQuery.append(" and psm.id_materia = ?3 ");
		sbQuery.append(" and psm.id_servicio = ?4");

		if (!caso.getTipoCuantia().equals(UtilDominios.TIPO_CUANTIA_CONCILIACION_INDETERMINADO)) {

			sbQuery.append(" and l.nombre = ?5");
		}

		// validación de estado de registros
		sbQuery.append(" and p.estado_registro = ?1");
		sbQuery.append(" and rp.estado_registro = ?1");
		sbQuery.append(" and psm.estado_registro = ?1");
		sbQuery.append(" and ps.estado_registro = ?1");
		if (!caso.getTipoCuantia().equals(UtilDominios.TIPO_CUANTIA_CONCILIACION_INDETERMINADO)) {

			sbQuery.append(" and l.estado_registro = ?1");
		}

		Query query = em.createNativeQuery(sbQuery.toString());

		// se establecen los parametros
		query.setParameter(1, UtilDominios.ESTADO_PERSONA_ACTIVO);

		query.setParameter(2, idSede);
		query.setParameter(3, caso.getIdMateria());
		query.setParameter(4, caso.getIdServicio());

		if (!caso.getTipoCuantia().equals(UtilDominios.TIPO_CUANTIA_CONCILIACION_INDETERMINADO)) {

			if (valorPretencionesCaso.longValue() >= valorPretencionesReferencia.longValue()) {

				query.setParameter(UtilConstantes.CINCO, UtilDominios.TIPO_LISTA_A);

			} else {

				query.setParameter(UtilConstantes.CINCO, UtilDominios.TIPO_LISTA_B);

			}
		}

		Integer resultado = (Integer) query.getSingleResult();

		if (resultado == null || resultado.intValue() == UtilConstantes.CERO) {

			throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR457.toString());

		}

	}

	/**
	 * Consulta una persona sin sus relaciones
	 * 
	 * @author Javier Estévez
	 * 
	 * @return retorna un objeto tipo Persona con los datos encontrados
	 */
	public Persona consultaBasicaPersona(Long idPersona) {

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append(" select * ");
		sbQuery.append(" from persona p ");
		sbQuery.append(" where p.id_persona = ?1 ");

		Query query = em.createNativeQuery(sbQuery.toString(), Persona.class);

		query.setParameter(UtilConstantes.UNO, idPersona);

		List<Object> lstPersona = query.getResultList();

		if (lstPersona == null || lstPersona.isEmpty()) {
			return null;
		}

		return (Persona) lstPersona.get(UtilConstantes.CERO);

	}
	/**
	 * Consulta las personas que cumplen los requisitos para ser asignadas a un
	 * caso
	 * 
	 * @author Javier Estévez
	 * 
	 * @return retorna un listado de vectores de objetos, en la primera posición
	 *         del vector viaja el identificador de la persona, en la segunda
	 *         posición del vector viaja la cantidad de casos que esa persona
	 *         tiene asignados.
	 */
	public List<Object[]> consultarPrestadorParaAsignarCasoEquidad(List<Long> lstRoles, Long idSede, Caso caso) {

		logger.info(" Entra a  consultarPrestadorParaAsignarCasoEquidad ");
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append(" select distinct p.id_persona, psm.cantidad_casos_asignados ");
		sbQuery.append(" from persona p ");
		sbQuery.append(" inner join rol_persona rp on rp.id_persona = p.id_persona ");
		
		
		sbQuery.append(" inner join persona_servicio_materia psm on psm.id_persona = p.id_persona ");
		
		sbQuery.append(" inner join persona_sede ps on ps.id_persona = p.id_persona ");
		sbQuery.append(" inner join estado_persona_rol epr on epr.id_persona = p.id_persona and epr.id_rol = rp.id_rol ");

		
		int indice = UtilConstantes.UNO;
		sbQuery.append(" where p.estado_persona = ?").append(indice++);
		

		sbQuery.append(" and ps.id_sede = ?").append(indice++);
		sbQuery.append(" and psm.id_materia = ?").append(indice++);
		sbQuery.append(" and psm.id_servicio = ?").append(indice++);
		
		// validación de estado de registros
		sbQuery.append(" and p.estado_registro = ").append("'ACT'");
		sbQuery.append(" and rp.estado_registro = ").append("'ACT'");
		sbQuery.append(" and psm.estado_registro = ").append("'ACT'");
		sbQuery.append(" and ps.estado_registro = ").append("'ACT'");
		sbQuery.append(" and epr.estado_registro = ").append("'ACT'");
		sbQuery.append(" and epr.id_motivo = ?").append(indice++);
		
		//conciliadores que no esten en el caso
		sbQuery.append(" AND p.id_persona NOT IN ( SELECT id_persona FROM ROL_PERSONA_CASO WHERE id_caso = ").append(caso.getIdCaso());
		sbQuery.append(" AND id_rol IN (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = 'COM' AND estado_registro= 'ACT'))");

		Query query = em.createNativeQuery(sbQuery.toString());

		indice = UtilConstantes.UNO;
		query.setParameter(indice++, UtilDominios.ESTADO_PERSONA_ACTIVO);
		query.setParameter(indice++, idSede);
		query.setParameter(indice++, caso.getIdMateria());
		query.setParameter(indice++, caso.getIdServicio());
		query.setParameter(indice++, UtilDominios.ESTADO_CONCILIADOR_ACTIVO);

		logger.info(" ConsultarPrestadorParaAsignarCasoEquidad: "+ query.getResultList());
		
		return query.getResultList();

	}


	/**
	 * Consulta las personas que cumplen los requisitos para ser asignadas a un
	 * caso
	 * 
	 * @author Javier Estévez
	 * 
	 * @return retorna un listado de vectores de objetos, en la primera posición
	 *         del vector viaja el identificador de la persona, en la segunda
	 *         posición del vector viaja la cantidad de casos que esa persona
	 *         tiene asignados.
	 */
	public List<Object[]> consultarPrestadorParaAsignarCaso(List<Long> lstRoles, Long idSede, Caso caso,
			Long valorPretencionesReferencia, Long valorPretencionesCaso) {

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append(" select distinct p.id_persona, psm.cantidad_casos_asignados ");
		sbQuery.append(" from persona p ");
		sbQuery.append(" inner join rol_persona rp on rp.id_persona = p.id_persona ");
		//if(cosa para validar esto) {
			sbQuery.append(" inner join persona_servicio_materia psm on psm.id_persona = p.id_persona ");
		//}
		
		sbQuery.append(" inner join persona_sede ps on ps.id_persona = p.id_persona ");
		sbQuery.append(" inner join estado_persona_rol epr on epr.id_persona = p.id_persona and epr.id_rol = rp.id_rol ");

		// si el tipo de cuantía no es indeterminado
		if ( !(UtilDominios.TIPO_CUANTIA_CONCILIACION_INDETERMINADO.equals(caso.getTipoCuantia())) ) {
			sbQuery.append(" inner join lista l on l.id_lista = rp.id_lista ");
		}
		int indice = UtilConstantes.UNO;
		sbQuery.append(" where p.estado_persona = ?").append(indice++);
		sbQuery.append(" and rp.id_rol in (");

		for (int i = 0; i < lstRoles.size(); i++) {
			if (i != UtilConstantes.CERO) {
				sbQuery.append(UtilConstantes.COMA).append(UtilConstantes.ESPACIO);
			}
			sbQuery.append("?").append(indice++);
		}
		sbQuery.append(") ");

		sbQuery.append(" and ps.id_sede = ?").append(indice++);
		sbQuery.append(" and psm.id_materia = ?").append(indice++);
		sbQuery.append(" and psm.id_servicio = ?").append(indice++);
		
		// validación de estado de registros
		sbQuery.append(" and p.estado_registro = ").append("'ACT'");
		sbQuery.append(" and rp.estado_registro = ").append("'ACT'");
		sbQuery.append(" and psm.estado_registro = ").append("'ACT'");
		sbQuery.append(" and ps.estado_registro = ").append("'ACT'");
		sbQuery.append(" and epr.estado_registro = ").append("'ACT'");
		sbQuery.append(" and epr.id_motivo = ?").append(indice++);
		
		//conciliadores que no esten en el caso
		sbQuery.append(" AND p.id_persona NOT IN ( SELECT id_persona FROM ROL_PERSONA_CASO WHERE id_caso = ").append(caso.getIdCaso());
		sbQuery.append(" AND id_rol IN (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = 'PDL' AND estado_registro='ACT')");
		sbQuery.append(" AND estado_registro ='ACT' )");
		
		if ( !(UtilDominios.TIPO_CUANTIA_CONCILIACION_INDETERMINADO.equals(caso.getTipoCuantia())) ) {

			sbQuery.append(" and l.estado_registro = ").append("'ACT'");
			sbQuery.append(" and l.nombre = ?").append(indice++);
		}

		sbQuery.append(" order by psm.cantidad_casos_asignados");

		Query query = em.createNativeQuery(sbQuery.toString());

		indice = UtilConstantes.UNO;
		query.setParameter(indice++, UtilDominios.ESTADO_PERSONA_ACTIVO);

		for (Long idRol : lstRoles) {
			query.setParameter(indice++, idRol);
		}

		query.setParameter(indice++, idSede);
		query.setParameter(indice++, caso.getIdMateria());
		query.setParameter(indice++, caso.getIdServicio());
		//query.setParameter(indice++, UtilDominios.TIPO_SERVICIO_PRESTADOR_SERVICIO);
		query.setParameter(indice++, UtilDominios.ESTADO_CONCILIADOR_ACTIVO);

		if ( !(UtilDominios.TIPO_CUANTIA_CONCILIACION_INDETERMINADO.equals(caso.getTipoCuantia())) ) {

			if (valorPretencionesCaso.longValue() >= valorPretencionesReferencia.longValue()) {

				query.setParameter(indice++, UtilDominios.TIPO_LISTA_A);

			} else {

				query.setParameter(indice++, UtilDominios.TIPO_LISTA_B);

			}

		}

		return query.getResultList();

	}

	/**
	 * Consulta las personas que se encuentran asignadas a un convenio y que se
	 * encuentran activas
	 * 
	 * @author Javier Estévez
	 * 
	 * @return retorna un listado de vectores de objetos, en la primera posición
	 *         del vector viaja el identificador de la persona, en la segunda
	 *         posición del vector viaja la cantidad de casos que esa persona
	 *         tiene asignados.
	 */
	public List<Object[]> consultarPrestadoresActivosAsignadosConvenio(Long idRol, Caso caso) {

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append(" select distinct p.id_persona, psm.cantidad_casos_asignados ");
		sbQuery.append(" from persona p ");
		sbQuery.append(" inner join persona_servicio_materia psm on psm.id_persona = p.id_persona ");
		sbQuery.append(" inner join estado_persona_rol epr on epr.id_persona = p.id_persona ");
		sbQuery.append(" inner join relacionado_convenio rc on rc.id_persona = p.id_persona and rc.id_rol = eps.id_rol ");

		int indice = UtilConstantes.UNO;
		sbQuery.append(" where p.estado_persona = ?").append(indice++);
		sbQuery.append(" and psm.id_materia = ?").append(indice++);
		sbQuery.append(" and psm.id_servicio = ?").append(indice++);
		sbQuery.append(" and epts.tipo_servicio = ?").append(indice++);
		sbQuery.append(" and epr.id_motivo = ?").append(indice++);
		sbQuery.append(" and rc.id_convenio = ?").append(indice++);
		sbQuery.append(" and rc.id_rol = ?").append(indice++);

		// validación de estado de registros
		sbQuery.append(" and p.estado_registro = ").append("'ACT'");
		sbQuery.append(" and psm.estado_registro = ").append("'ACT'");
		sbQuery.append(" and epr.estado_registro = ").append("'ACT'");
		sbQuery.append(" and rc.estado_registro = ").append("'ACT'");
		
		//conciliadores que no esten en el caso
		sbQuery.append(" AND p.id_persona NOT IN ( SELECT id_persona FROM ROL_PERSONA_CASO WHERE id_caso = ").append(caso.getIdCaso());
		sbQuery.append(" AND id_rol IN (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = 'PDL' AND estado_registro='ACT')");
		sbQuery.append(" AND estado_registro ='ACT' )");

		sbQuery.append(" order by psm.cantidad_casos_asignados");

		Query query = em.createNativeQuery(sbQuery.toString());

		indice = UtilConstantes.UNO;

		query.setParameter(indice++, UtilDominios.ESTADO_PERSONA_ACTIVO);

		query.setParameter(indice++, caso.getIdMateria());
		query.setParameter(indice++, caso.getIdServicio());
		query.setParameter(indice++, UtilDominios.TIPO_SERVICIO_PRESTADOR_SERVICIO);
		query.setParameter(indice++, UtilDominios.ESTADO_CONCILIADOR_ACTIVO);
		query.setParameter(indice++, caso.getIdConvenio());
		query.setParameter(indice++, idRol);

		return query.getResultList();

	}

	/**
	 * Devuelve las personas con el rol de arbitro en estado activo ordenados
	 * por el estado
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> consultarArbitrosOrdenadosFechaEstado() {
		String estadoRegistroRolPersona = RolPersona.ENTIDAD_ROL_PERSONA_ESTADO_REGISTRO;
		String estadoRegistroPersona = Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO + "1";

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rp.persona FROM RolPersona rp");
		jpqlQuery.append(" WHERE rp.rol.nombre=:");
		jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rp.estado_registro=:");
		jpqlQuery.append(estadoRegistroRolPersona);
		jpqlQuery.append(" AND rp.persona.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroPersona);
		jpqlQuery.append(" AND rp.persona.estadoPersona=:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA);
		jpqlQuery.append(" ORDER BY rp.persona.fechaEst ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, UtilDominios.ROL_PERSONA_ARBITRO);
		query.setParameter(estadoRegistroRolPersona, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA, UtilDominios.ESTADO_PERSONA_ACTIVO);
		query.setParameter(estadoRegistroPersona, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();
	}

	/**
	 * Devuelve las personas con el rol de arbitro en estado activo ordenadas
	 * alfabeticamente
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> consultarArbitrosOrdenadosAlfabeticamente() {
		String estadoRegistroRolPersona = RolPersona.ENTIDAD_ROL_PERSONA_ESTADO_REGISTRO;
		String estadoRegistroPersona = Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO + "1";

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT DISTINCT rp.persona FROM RolPersona rp");
		jpqlQuery.append(" WHERE rp.rol.nombre in:");
		jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rp.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroRolPersona);
		jpqlQuery.append(" AND rp.persona.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroPersona);
		jpqlQuery.append(" AND rp.persona.estadoPersona=:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA);
		jpqlQuery.append(" ORDER BY rp.persona.primerNombreORazonSocial, rp.persona.segundoNombre, rp.persona.primerApellido, rp.persona.segundoApellido ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());

		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, manejadorRol.obtenerRolesArbitros());
		query.setParameter(estadoRegistroRolPersona, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA, UtilDominios.ESTADO_PERSONA_ACTIVO);
		query.setParameter(estadoRegistroPersona, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Persona> consultarOperadoresOrdenadosAlfabeticamente() {
		String estadoRegistroRolPersona = RolPersona.ENTIDAD_ROL_PERSONA_ESTADO_REGISTRO;
		String estadoRegistroPersona = Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO + "1";

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT DISTINCT rp.persona FROM RolPersona rp");
		jpqlQuery.append(" WHERE rp.rol.nombre in:");
		jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rp.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroRolPersona);
		jpqlQuery.append(" AND rp.persona.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroPersona);
		jpqlQuery.append(" AND rp.persona.estadoPersona=:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA);
		jpqlQuery.append(" ORDER BY rp.persona.primerNombreORazonSocial, rp.persona.segundoNombre, rp.persona.primerApellido, rp.persona.segundoApellido ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());

		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, manejadorRol.obtenerRolesOperadores());
		query.setParameter(estadoRegistroRolPersona, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA, UtilDominios.ESTADO_PERSONA_ACTIVO);
		query.setParameter(estadoRegistroPersona, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();
	}

	/**
	 * Metodo que pone en NULL el numero_documento de una persona
	 * 
	 * @param numerodocumento
	 */
	public void actulizarNumeroIdentificacionEnNull(Long numerodocumento) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("UPDATE PERSONA ");
		nativeQuery.append("SET numero_documento = NULL ");
		nativeQuery.append("WHERE id_persona =?1 ");
		Query query = em.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, numerodocumento);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Persona> consultarPersonasPorRol(String nombreRol) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT distinct rp.persona FROM RolPersona rp");
		jpqlQuery.append(" WHERE rp.rol.nombre=:").append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rp.estadoRegistro=:")
				.append(Rol.ENTIDAD_ROL_ESTADO_REGISTRO);
		jpqlQuery.append(" AND rp.persona.estadoRegistro=:")
				.append(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO);
		jpqlQuery.append(" AND rp.persona.estadoPersona=:")
				.append(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO);
		jpqlQuery.append(" ORDER BY rp.persona.primerNombreORazonSocial ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombreRol);
		query.setParameter(Rol.ENTIDAD_ROL_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return query.getResultList();

	}
	
	/**
	 * Obtiene la lista de personas parametrizadas para reparto de abogado por
	 * tipo de servicio
	 * 
	 * @param idServicio
	 * @return
	 8*/
	public List<Persona> consultarAbogadosPorServicio(Long idServicio) {
		StringBuilder nativeQuery = new StringBuilder();

		nativeQuery.append("(SELECT DISTINCT p.* FROM ROL_PERSONA rp ") ; 
		nativeQuery.append(" INNER JOIN PERSONA p ON rp.id_persona = p.id_persona "); 
		nativeQuery.append(" INNER JOIN PARAMETRIZAR_SERVICIO_ROL psr ON rp.id_rol = psr.id_rol ");
		nativeQuery.append(" WHERE rp.estado_registro = 'ACT' AND p.estado_registro = 'ACT' AND psr.estado_registro = 'ACT' "); 
		nativeQuery.append(" AND (rp.fecha_fin_vigencia IS NULL OR rp.fecha_fin_vigencia > GETDATE()) ");
		nativeQuery.append(" AND psr.id_servicio = ?1) ");
		nativeQuery.append(" UNION ");
		nativeQuery.append(" (SELECT DISTINCT p.* FROM ROL_PERSONA rp "); 
		nativeQuery.append(" INNER JOIN PERSONA p ON rp.id_persona = p.id_persona ");
		nativeQuery.append(" INNER JOIN PARAMETRIZAR_SERVICIO_ROL psr ON rp.id_rol = psr.id_rol_sin_reparto ");
		nativeQuery.append(" WHERE rp.estado_registro = 'ACT' AND p.estado_registro = 'ACT' AND psr.estado_registro = 'ACT' ");
		nativeQuery.append(" AND (rp.fecha_fin_vigencia IS NULL OR rp.fecha_fin_vigencia > GETDATE()) ");
		nativeQuery.append(" AND psr.id_servicio = ?1) ");
		nativeQuery.append(" ORDER BY p.primer_nombre_o_razon_social, p.segundo_nombre, p.primer_apellido, p.segundo_apellido;");

		Query query = em.createNativeQuery(nativeQuery.toString(), Persona.class);
		query.setParameter(1, idServicio);
		return query.getResultList();
	}

	/**
	 * Consulta los arbitros posibles para preseleccionar dependiendo del tipo
	 * de servicio sea de conciliacion o sea de arbitraje
	 * 
	 * @param tipoServicio
	 * @return List<Persona>
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> consultarArbitrosPreseleccion(String tipoServicio) {
		List<Persona> persona;
		StringBuilder jpqlQuery = new StringBuilder();

		jpqlQuery.append(" SELECT p FROM Persona p ");
		jpqlQuery.append(" INNER JOIN p.estadoPersonaTipoServicioList epts ");
		jpqlQuery.append(" WHERE epts.estadoPersonaTipoServicioPK.tipoServicio = ");
		jpqlQuery.append(":tipoServicio");
		jpqlQuery.append(" AND epts.estado IN (:estadosExcluidos)  ");

		StringBuilder estadosQuery = new StringBuilder();
		estadosQuery
				.append("SELECT peap.codigo FROM ParamEstadoArbitroPreseleccion peap WHERE peap.estadoRegistro = :");
		estadosQuery.append(ParamEstadoArbitroPreseleccion.ENTIDAD_PARAM_ESTADO_ARBITRO_PRESELECCION_ESTADO_REGISTRO);
		Query estados = mp.createQuery(estadosQuery.toString());
		estados.setParameter(ParamEstadoArbitroPreseleccion.ENTIDAD_PARAM_ESTADO_ARBITRO_PRESELECCION_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_INACTIVO);
		List<String> sList = estados.getResultList();

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter("tipoServicio", tipoServicio);
		query.setParameter("estadosExcluidos", sList);
		persona = query.getResultList();
		return persona;
	}

	/**
	 * Metodo que consulta una persona por numero de cedula, rol y caso
	 * 
	 * @param numeroCedula
	 * @param nombreRol
	 * @return
	 */
	public Persona getConsultarPersonaPorCedulaPorRolPorCaso(String numeroCedula, String nombreRol, Long idCaso) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT p FROM Persona p ");
		jpqlQuery.append("INNER JOIN p.rolPersonaList rp ");
		jpqlQuery.append("INNER JOIN p.rolPersonaCasoList rpc ");
		jpqlQuery.append("INNER JOIN rpc.rol r ");
		jpqlQuery.append("WHERE p.numeroDocumento = ?1 ");
		jpqlQuery.append("AND r.nombre = ?2 ");
		jpqlQuery.append("AND rpc.caso.idCaso = ?3 ");
		jpqlQuery.append("AND rpc.estadoRegistro = ?4 ");
		Query query = em.createQuery(jpqlQuery.toString(), Persona.class);
		query.setParameter(1, numeroCedula);
		query.setParameter(2, nombreRol);
		query.setParameter(3, idCaso);
		query.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		List<Persona> personas;
		personas = query.getResultList();

		if (personas == null || personas.isEmpty())
			return null;
		else
			return personas.get(0);

	}

	/**
	 * Registra una persona en la bd
	 * 
	 * @param persona
	 * @return
	 */
	public Persona crearPersona(Persona persona) {
		Persona personaCreada = (Persona) mp.updateObject(persona);
		this.getEntityManager().flush();

		return personaCreada;

	}

	/**
	 * Metodo que consulta las personas relacionas a un Caso por rol
	 * 
	 * @param idCaso
	 * @param idRol
	 * @return List<Persona>
	 */
	// Se modifica el metodo filtrando el estado de rol persona caso por No
	// Aplica y el estado registro como Activo
	@SuppressWarnings("unchecked")
	public List<Persona> getConsultarPartesCasoPorRol(Long idCaso, String nombreRol) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT p FROM RolPersonaCaso rpc ");
		jpqlQuery.append("INNER JOIN rpc.persona p ");
		jpqlQuery.append("INNER JOIN rpc.rol r ");
		jpqlQuery.append("WHERE rpc.caso.idCaso = ?1 ");
		jpqlQuery.append("AND r.nombre = ?2 ");
		jpqlQuery.append("AND rpc.estado = ?3 ");
		jpqlQuery.append("AND rpc.estadoRegistro = ?4 ");
		Query query = em.createQuery(jpqlQuery.toString(), Persona.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, nombreRol);
		query.setParameter(3, UtilDominios.ESTADO_ROL_PERSONA_CASO_NO_APLICA);
		query.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Persona> getConsultarPartesCasoPorRolApoderado(Long idCaso, String nombreRol, Long idPersona) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT p FROM RolPersonaCaso rpc ");
		jpqlQuery.append("INNER JOIN rpc.persona p ");
		jpqlQuery.append("INNER JOIN rpc.rol r ");
		jpqlQuery.append("WHERE rpc.caso.idCaso = ?1 ");
		jpqlQuery.append("AND r.nombre = ?2 ");
		jpqlQuery.append("AND rpc.estado = ?3 ");
		jpqlQuery.append("AND rpc.estadoRegistro = ?4 ");
		jpqlQuery.append("AND rpc.idPersonaApoderado = ?5 ");

		Query query = em.createQuery(jpqlQuery.toString(), Persona.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, nombreRol);
		query.setParameter(3, UtilDominios.ESTADO_ROL_PERSONA_CASO_NO_APLICA);
		query.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(5, idPersona);
		return query.getResultList();
	}

	/**
	 * Metodo que consulta las personas con roles Demandante, Demandando,
	 * Apoderado Demandante y Apoderado Demandado relacionadas a un Caso
	 * 
	 * @param idCaso
	 * @return List<Persona>
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> getConsultarPartesCaso(Long idCaso) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc.persona FROM RolPersonaCaso rpc ");
		jpqlQuery.append("WHERE (rpc.rol.nombre =:apoDemandado ");
		jpqlQuery.append(" OR rpc.rol.nombre = :apoDemandante");
		jpqlQuery.append(" OR rpc.rol.nombre =:parteDemandante ");
		jpqlQuery.append(" OR rpc.rol.nombre =:parteDemandada ");
		jpqlQuery.append(" ) ");
		jpqlQuery.append("AND rpc.rolPersonaCasoPK.idCaso = : ").append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro =:").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		jpqlQuery.append(" AND rpc.estado <>:")
				.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
		jpqlQuery.append(" ORDER BY rpc.persona.primerNombreORazonSocial,rpc.persona.numeroDocumento");
		Query query = em.createQuery(jpqlQuery.toString(), Persona.class);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter("apoDemandado", UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
		query.setParameter("apoDemandante", UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
		query.setParameter("parteDemandante", UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE);
		query.setParameter("parteDemandada", UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, UtilDominios.ESTADO_ROL_PERSONA_CASO_INACTIVO);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return query.getResultList();
	}

	public void persistir(Persona persona) {
		em.persist(persona);
	}

	/**
	 * Consulta los arbitros seleccionados al caso
	 * 
	 * @param idCaso
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> consultarArbitrosPorRolCaso(Long idCaso, String nombreRol) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc.persona FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rol.nombre=:");
		jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		jpqlQuery.append(" AND rpc.estado=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);

		jpqlQuery.append(" ORDER BY rpc.persona.primerNombreORazonSocial ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombreRol);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO,
				UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);

		return query.getResultList();

	}

	/**
	 * Devuelve los nombres de las personas con los roles Conciliador,
	 * Arbitro, y Secretario de tribunal para el servicio que se pasa como
	 * parametro
	 * 
	 * @param tipoServicio
	 * @return List<Persona>
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> consultarPrestadoresDelServicio(String tipoServicio) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT DISTINCT p FROM Persona p ");
		jpqlQuery.append("INNER JOIN p.rolPersonaList rp ");
		jpqlQuery.append("INNER JOIN rp.rol r ");
		jpqlQuery.append("INNER JOIN r.tipoDeServicioRolList tsr ");
		jpqlQuery.append("WHERE tsr.tipoDeServicioRolPK.tipoServicio = ?1 ");
		Query query = em.createQuery(jpqlQuery.toString(), Persona.class);
		query.setParameter(1, tipoServicio);
		return query.getResultList();
	}

	/**
	 * Valida que la persona tenga alguno de los siguientes roles: secretario,
	 * abogado, Analistas de conciliaciÃ³n, responsables de convenios, auxiliar
	 * administrativo, coordinador del servicio, coordinador CAC, direcciÃ³n CAC
	 * 
	 * @param idPersona
	 * @return
	 */
	public boolean validarNoEsPrestadorDeServicio(Long idPersona) {

		Persona usuario = this.buscar(idPersona);

		boolean noEsPrestadorDeServicio = false;
		for (RolPersona rolPersona : usuario.getRolPersonaList()) {
			if (!manejadorRol.validarRolEsPrestadorDeServicio(rolPersona.getRol().getNombre())) {
				noEsPrestadorDeServicio = true;
				break;
			}
		}

		return noEsPrestadorDeServicio;
	}

	/**
	 * Metodo que consulta una persona por numero de cedula, rol y caso
	 * 
	 * @param numeroCedula
	 * @param nombreRol
	 * @return
	 */
	public Persona getConsultarPersonaPorDocumento(String tipoDocumento, String numeroDcumento) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT p FROM Persona p ");
		jpqlQuery.append("WHERE p.numeroDocumento = ?1 ");
		jpqlQuery.append("AND p.tipoDocumento = ?2 ");
		Query query = em.createQuery(jpqlQuery.toString(), Persona.class);
		query.setParameter(1, numeroDcumento);
		query.setParameter(2, tipoDocumento);
		List<Persona> personas;
		personas = query.getResultList();

		if (personas == null || personas.isEmpty())
			return null;
		else
			return personas.get(0);

	}

	/**
	 * Metodo que consulta una persona por numero de cedula, roles de partes y
	 * caso
	 * 
	 * @param numeroCedula
	 * @param nombreRol
	 * @return
	 */
	public Persona getConsultarPartePorCedulaPorCaso(String numeroCedula, Long idCaso) {
		List<Persona> personas = new ArrayList<Persona>();
		Persona persona = new Persona();

		try {
			StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append("SELECT p FROM Persona p ");
			jpqlQuery.append("LEFT JOIN p.rolPersonaCasoList rpc ");
			jpqlQuery.append("LEFT JOIN rpc.rol r ");
			jpqlQuery.append("WHERE (r.nombre =:apoDemandado ");
			jpqlQuery.append(" OR r.nombre =:apoDemandante ");
			jpqlQuery.append(" OR r.nombre =:parteDemandante ");
			jpqlQuery.append(" OR r.nombre =:parteDemandada ");
			jpqlQuery.append(" ) ");
			jpqlQuery.append("AND p.numeroDocumento =: ");
			jpqlQuery.append(Persona.ENTIDAD_PERSONA_NUMERO_DOCUMENTO);
			jpqlQuery.append(" AND rpc.caso.idCaso =: ");
			jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
			jpqlQuery.append(" AND rpc.estado =:");
			jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
			jpqlQuery.append(" AND p.estadoPersona =:");
			jpqlQuery.append(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA);
			Query query = em.createQuery(jpqlQuery.toString(), Persona.class);
			query.setParameter(Persona.ENTIDAD_PERSONA_NUMERO_DOCUMENTO, numeroCedula);
			query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO,
					UtilDominios.ESTADO_ROL_PERSONA_CASO_NO_APLICA);
			query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA, UtilDominios.ESTADO_PERSONA_ACTIVO);
			query.setParameter("apoDemandado", UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
			query.setParameter("apoDemandante", UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
			query.setParameter("parteDemandante", UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE);
			query.setParameter("parteDemandada", UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);
			personas = (List<Persona>) query.getResultList();
			if (!personas.isEmpty())
				persona = personas.get(0);
			else
				throw new NoResultException();

		} catch (NoResultException e) {
			try {
				StringBuilder jpqlQuery = new StringBuilder();
				jpqlQuery.append("SELECT p FROM Persona p ");
				jpqlQuery.append("WHERE ");
				jpqlQuery.append("p.numeroDocumento =: ");
				jpqlQuery.append(Persona.ENTIDAD_PERSONA_NUMERO_DOCUMENTO);
				jpqlQuery.append(" AND p.estadoPersona =:");
				jpqlQuery.append(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA);
				Query query = em.createQuery(jpqlQuery.toString(), Persona.class);
				query.setParameter(Persona.ENTIDAD_PERSONA_NUMERO_DOCUMENTO, numeroCedula);
				query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA, UtilDominios.ESTADO_PERSONA_ACTIVO);
				personas = (List<Persona>) query.getResultList();
				if (!personas.isEmpty())
					persona = personas.get(0);
				else
					throw new NoResultException();
			} catch (NoResultException e2) {
				String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString());
				throw new SimascException(mensajeError);
			} catch (NonUniqueResultException e2) {
				String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR200.toString());
				throw new SimascException(mensajeError);
			}
		} catch (NonUniqueResultException e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR200.toString());
			throw new SimascException(mensajeError);
		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString());
			throw new SimascException(mensajeError);
		}
		return persona;
	}

	/**
	 * Metodo que consulta una persona por numero de cedula, roles de partes y
	 * caso
	 * 
	 * @param numeroCedula
	 * @param nombreRol
	 * @return
	 */
	public Persona getConsultarPartePoridPersonaPorCaso(Long idPersona, Long idCaso) {
		Persona persona = new Persona();

		try {
			StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append("SELECT p FROM Persona p ");
			jpqlQuery.append("LEFT JOIN p.rolPersonaCasoList rpc ");
			jpqlQuery.append("INNER JOIN rpc.rol r ");
			jpqlQuery.append("WHERE (r.nombre =:apoDemandado ");
			jpqlQuery.append(" OR r.nombre =:apoDemandante ");
			jpqlQuery.append(" OR r.nombre =:parteDemandante ");
			jpqlQuery.append(" OR r.nombre =:parteDemandada ");
			jpqlQuery.append(" OR r.nombre =:parteProcurador ");
			jpqlQuery.append(" OR r.nombre =:parteCurador ");
			jpqlQuery.append(" OR r.nombre =:parteAgencia ");
			jpqlQuery.append(" ) ");
			jpqlQuery.append("AND p.idPersona =: ");
			jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);
			jpqlQuery.append(" AND rpc.caso.idCaso =: ");
			jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
			jpqlQuery.append(" AND rpc.estado =:");
			jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
			jpqlQuery.append(" AND p.estadoPersona =:");
			jpqlQuery.append(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA);

			Query query = em.createQuery(jpqlQuery.toString(), Persona.class);
			query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
			query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO,
					UtilDominios.ESTADO_ROL_PERSONA_CASO_NO_APLICA);
			query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA, UtilDominios.ESTADO_PERSONA_ACTIVO);
			query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA, UtilDominios.ESTADO_PERSONA_ACTIVO);
			query.setParameter("apoDemandado", UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
			query.setParameter("apoDemandante", UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
			query.setParameter("parteDemandante", UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE);
			query.setParameter("parteDemandada", UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);
			query.setParameter("parteProcurador", UtilDominios.ROL_PERSONA_PARTE_PROCURADOR_JUDICIAL);
			query.setParameter("parteCurador", UtilDominios.ROL_PERSONA_PARTE_CURADOR_AD_LITERM);
			query.setParameter("parteAgencia", UtilDominios.ROL_PERSONA_PARTE_AGENCIA);
			persona = (Persona) query.getSingleResult();
		} catch (NoResultException e) {
			try {
				StringBuilder jpqlQuery = new StringBuilder();
				jpqlQuery.append("SELECT p FROM Persona p ");
				jpqlQuery.append("WHERE ");
				jpqlQuery.append("p.idPersona =: ");
				jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);
				Query query = em.createQuery(jpqlQuery.toString(), Persona.class);
				query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
				persona = (Persona) query.getSingleResult();
			} catch (NoResultException e2) {
				String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString());
				throw new SimascException(mensajeError);
			} catch (NonUniqueResultException e2) {
				String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR200.toString());
				throw new SimascException(mensajeError);
			}
		} catch (NonUniqueResultException e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR200.toString());
			throw new SimascException(mensajeError);
		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString());
			throw new SimascException(mensajeError);
		}
		return persona;
	}

	/**
	 * Metodo que consulta una persona por numero de cedula, roles de partes y
	 * caso
	 * 
	 * @param numeroCedula
	 * @param nombreRol
	 * @return
	 */
	public Persona getConsultarPartePorCedula(String numeroCedula) {
		Persona persona = new Persona();

		try {
			StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append("SELECT p FROM Persona p ");
			jpqlQuery.append("LEFT JOIN p.rolPersonaList rp ");
			jpqlQuery.append("INNER JOIN p.rolPersonaCasoList rpc ");
			jpqlQuery.append("INNER JOIN rpc.rol r ");
			jpqlQuery.append("WHERE (r.nombre =:apoDemandado ");
			jpqlQuery.append(" OR r.nombre =:apoDemandante ");
			jpqlQuery.append(" OR r.nombre =:parteDemandante ");
			jpqlQuery.append(" OR r.nombre =:parteDemandada ");
			jpqlQuery.append(" OR r.nombre =:parteProcurador ");
			jpqlQuery.append(" OR r.nombre =:parteCurador ");
			jpqlQuery.append(" OR r.nombre =:parteAgencia ");
			jpqlQuery.append(" ) ");
			jpqlQuery.append("AND p.numeroDocumento =: ");
			jpqlQuery.append(Persona.ENTIDAD_PERSONA_NUMERO_DOCUMENTO);

			Query query = em.createQuery(jpqlQuery.toString(), Persona.class);
			query.setParameter(Persona.ENTIDAD_PERSONA_NUMERO_DOCUMENTO, numeroCedula);
			query.setParameter("apoDemandado", UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
			query.setParameter("apoDemandante", UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
			query.setParameter("parteDemandante", UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE);
			query.setParameter("parteDemandada", UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);
			query.setParameter("parteProcurador", UtilDominios.ROL_PERSONA_PARTE_PROCURADOR_JUDICIAL);
			query.setParameter("parteCurador", UtilDominios.ROL_PERSONA_PARTE_CURADOR_AD_LITERM);
			query.setParameter("parteAgencia", UtilDominios.ROL_PERSONA_PARTE_AGENCIA);
			persona = (Persona) query.getSingleResult();
		} catch (NoResultException e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString());
			throw new SimascException(mensajeError);
		} catch (NonUniqueResultException e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR200.toString());
			throw new SimascException(mensajeError);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return persona;
	}

	/**
	 * consulta las personas asignadas a un caso con determinado estado.
	 * 
	 * @param idCaso
	 * @param nombreRol
	 * @param estado
	 * @return
	 */
	public List<Persona> consultarPersonasPorRolCasoEstado(Long idCaso, String nombreRol, String estado) {

		String estadoRegistroRolPersona = RolPersona.ENTIDAD_ROL_PERSONA_ESTADO_REGISTRO;
		String estadoRegistroPersona = Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO + "1";

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc.persona FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rol.nombre=:");
		jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroRolPersona);
		jpqlQuery.append(" AND rpc.persona.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroPersona);
		jpqlQuery.append(" AND rpc.persona.estadoPersona=:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA);

		jpqlQuery.append(" AND rpc.estado=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
		jpqlQuery.append(" ORDER BY rpc.persona.primerNombreORazonSocial ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombreRol);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(estadoRegistroRolPersona, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA, UtilDominios.ESTADO_PERSONA_ACTIVO);
		query.setParameter(estadoRegistroPersona, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, estado);

		return query.getResultList();

	}

	/**
	 * trae las personas por tipo de persona.
	 * 
	 * @param tipoPersona
	 * @param estado
	 * @return
	 */
	public Persona consultarPersonasPorTipoPersona(String tipoPersona, String estado) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT p FROM Persona p");
		jpqlQuery.append(" WHERE p.tipoPersona =:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_TIPO_PERSONA);
		jpqlQuery.append(" AND p.estadoRegistro =:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO);
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_TIPO_PERSONA, tipoPersona);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO, estado);
		query.setFirstResult(0);
		query.setMaxResults(1);

		return (Persona) query.getSingleResult();

	}

	/**
	 * Obtiene una persna por número y tipo de documento
	 * 
	 * @param tipoDocumento
	 * @param numeroDocumento
	 * @return
	 */
	public Persona consultarPersonaPorIdentificacion(String tipoDocumento, String numeroDocumento) {
		List<Persona> personas;
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT p FROM Persona p WHERE ");
		jpqlQuery.append("p.tipoDocumento = :").append(Persona.ENTIDAD_PERSONA_TIPO_DOCUMENTO);
		jpqlQuery.append(" AND p.numeroDocumento = :").append(Persona.ENTIDAD_PERSONA_NUMERO_DOCUMENTO);
		jpqlQuery.append(" AND p.estadoRegistro = :").append(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_TIPO_DOCUMENTO, tipoDocumento);
		query.setParameter(Persona.ENTIDAD_PERSONA_NUMERO_DOCUMENTO, numeroDocumento);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		personas = query.getResultList();

		return personas.isEmpty() ? null : personas.get(0);
	}

	/**
	 * Obtiene materias asignadas a una persona
	 * 
	 * @param idPersona
	 * @return
	 */
	public List<PersonaMateriaAsignadaDTO> consultarMateriasAsignadas(Long idPersona) {
		List<PersonaMateriaAsignadaDTO> materias;

		StringBuilder nativeQuery = new StringBuilder();

		nativeQuery.append("  SELECT Distinct psm.id_persona_servicio_materia as idPersonaServicioMateria,");
		nativeQuery.append(" s.nombre as nombreServicio, ");
		nativeQuery.append(" m.id_materia as idMateria, ");
		nativeQuery.append(" m.nombre as nombreMateria, ");
		nativeQuery.append(" psm.fecha_inicio_vigencia as fechaInicioVigencia, ");
		nativeQuery.append(" psm.fecha_fin_de_vigencia as fechaFinDeVigencia, ");
		nativeQuery.append(" pss.cantidad_de_materias as cantidadDeMaterias, ");
		nativeQuery.append(" s.tipo as tipoServicio, ");
		nativeQuery.append(" s.id_servicio as idServicio ");
		nativeQuery.append(" FROM PERSONA_SERVICIO_MATERIA psm ");
		nativeQuery.append(" INNER JOIN SERVICIO AS s ON psm.id_servicio = s.id_servicio ");
		nativeQuery.append(" INNER JOIN PARAMETRO_SERVICIO_SORTEO AS pss on psm.id_servicio = pss.id_servicio ");
		nativeQuery.append(" INNER JOIN MATERIA AS m on psm.id_materia = m.id_materia and m.estado_registro = ?2 ");
		nativeQuery.append(" WHERE psm.id_persona = ?1 AND psm.estado_registro = ?2 ");
		nativeQuery.append(" order by s.nombre, m.id_materia");

		Query query = em.createNativeQuery(nativeQuery.toString(), PersonaMateriaAsignadaDTO.class);
		query.setParameter(1, idPersona);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		materias = query.getResultList();
		return materias;
	}

	/**
	 * Para rol conciliador del servicio Trámite Ordinario, el campo cantidad de
	 * casos asignados debe grabarse con el menor valor de casos asignados a un
	 * conciliador con el mismo servicio y materia.
	 * 
	 * @param idPersona
	 * @return
	 */
	public Long validaNumeroCasosAsignados(String idRol, Long idMateria) {
		BigDecimal numCasos;

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" Select ISNULL(Min(casosAsig.numCasos),0) AS numMinCasos ");
		nativeQuery.append(
				" FROM (Select COUNT(id_persona) as numCasos, id_persona, mater.id_servicio, id_materia from PERSONA_SERVICIO_MATERIA as mater ");
		nativeQuery.append(
				" INNER JOIN AGRUPAMIENTO_ROL as agr ON agr.id_servicio =  mater.id_servicio and agr.tipo_agrupamiento = 'MATEROL'");
		nativeQuery.append(" INNER JOIN ROL as rol on rol.id_rol = agr.id_rol ");
		nativeQuery.append(" where rol.nombre = ?1 and id_materia = ?2 and mater.estado_registro = ?3 ");
		nativeQuery.append(" GROUP BY id_persona, mater.id_servicio, id_materia ");
		nativeQuery.append(" ) as casosAsig ");

		Query query = em.createNativeQuery(nativeQuery.toString(), BigDecimal.class);
		query.setParameter(1, idRol);
		query.setParameter(2, idMateria);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		numCasos = (BigDecimal) query.getSingleResult();
		return numCasos.longValue();
	}

	public Collection<Persona> consultaSecretarioActivoCaso(Long idCaso) {
		String estadoRegistroRolPersona = RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO;
		String estadoRegistroPersona = Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO + "1";
		List<String> estados = new ArrayList<>();
		estados.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		estados.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		List<Persona> persona;
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT distinct rpc.persona FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rol.nombre=:");
		jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroRolPersona);
		jpqlQuery.append(" AND rpc.persona.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroPersona);
		jpqlQuery.append(" AND rpc.persona.estadoPersona=:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA);
		jpqlQuery.append(" AND rpc.estado IN :estados");
		jpqlQuery.append(" AND rpc.caso.idCaso =:idCaso");
		jpqlQuery.append(" AND rpc.persona.estadoPersona=:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA);
		jpqlQuery.append(" ORDER BY rpc.persona.primerNombreORazonSocial ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter("estados", estados);
		query.setParameter("idCaso", idCaso);
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, UtilDominios.TIPO_ACTOR_CASO_SECRETARIO);
		query.setParameter(estadoRegistroRolPersona, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA, UtilDominios.ESTADO_PERSONA_ACTIVO);
		query.setParameter(estadoRegistroPersona, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		persona = query.getResultList();
		return persona;
	}

	/**
	 * Consulta una persona en el sistema por número de docuemnto y correo
	 * electrónico
	 * 
	 * @param numeroDocumento
	 * @param correoElectronico
	 * @return
	 */
	public Persona consultarPersonaPorDocumentoYCorreoElectronico(String numeroDocumento, String correoElectronico) {
		Persona persona;
		try {
			StringBuilder nativeQuery = new StringBuilder();
			nativeQuery.append("SELECT TOP 1 p.* ");
			nativeQuery.append("  FROM PERSONA p, ");
			nativeQuery.append("       CORREO_ELECTRONICO ce ");
			nativeQuery.append(" WHERE p.id_persona = ce.id_persona ");
			nativeQuery.append("   AND p.numero_documento = ?1 ");
			nativeQuery.append("   AND SUBSTRING(ce.direccion, 0, CHARINDEX('@', ce.direccion)) = ?2 ");
			nativeQuery.append("   AND ce.tipo = '").append(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL)
					.append("' ");
			nativeQuery.append("   AND p.estado_persona = '").append(UtilDominios.ESTADO_PERSONA_ACTIVO).append("' ");
			nativeQuery.append("   AND p.estado_registro = '").append(UtilDominios.ESTADO_REGISTRO_ACTIVO).append("' ");
			nativeQuery.append("   AND ce.estado_registro = '").append(UtilDominios.ESTADO_REGISTRO_ACTIVO)
					.append("' ");

			Query query = em.createNativeQuery(nativeQuery.toString(), Persona.class);
			query.setParameter(1, numeroDocumento);
			query.setParameter(2, correoElectronico);

			persona = (Persona) query.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			persona = null;
		}

		return persona;
	}

	/**
	 * Consulta conciliadores activos por centro
	 * 
	 * @param centro
	 * @return List<Persona>
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> consultarConciliadoresPorCentros(BusquedaRolesActivosDTO filtros) {
		List<Persona> conciliadoresPorCentro;

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT DISTINCT p.* ");
		nativeQuery.append(" FROM ");
		nativeQuery.append(" PERSONA as p ");
		nativeQuery.append(" INNER JOIN ROL_PERSONA as rp on p.id_persona = rp.id_persona  ");
		nativeQuery.append(
				" INNER JOIN TIPO_DE_SERVICIO_ROL as tdsr ON tdsr.tipo_servicio = ?1 and rp.id_rol = tdsr.id_rol ");
		nativeQuery.append(
				" INNER JOIN ESTADO_PERSONA_ROL as epr on p.id_persona = epr.id_persona and epr.id_rol = rp.id_rol ");

		if (filtros.getIdMateria() != null) {
			nativeQuery.append(" INNER JOIN SERVICIO s ");
			nativeQuery.append(" ON s.tipo = tdsr.tipo_servicio ");
			nativeQuery.append(" AND s.estado_registro = ?4 ");
			nativeQuery.append(" INNER JOIN PERSONA_SERVICIO_MATERIA psm ");
			nativeQuery.append(" ON psm.id_persona = p.id_persona ");
			nativeQuery.append(" AND ( psm.fecha_fin_de_vigencia is null ");
			nativeQuery.append(" OR psm.fecha_fin_de_vigencia >= GETDATE() ) ");
			nativeQuery.append(" AND s.id_servicio = psm.id_servicio ");
			nativeQuery.append(" AND psm.id_materia = ?5 ");
			nativeQuery.append(" AND psm.estado_registro = ?4 ");
		}

		nativeQuery.append(" WHERE ");
		nativeQuery.append(" epr.id_motivo = ?2 ");
		nativeQuery.append(" AND (rp.fecha_fin_vigencia IS NULL OR rp.fecha_fin_vigencia >= CAST(GETDATE() as Date)) ");
		if (filtros.getCentro() != null && !filtros.getCentro().isEmpty())
			nativeQuery.append(" AND rp.id_centro ")
					.append(UtilConsultasSQL.clausulaInSQLSNumeros(filtros.getCentro()));
		nativeQuery.append(" AND p.estado_registro = ?4 ");
		nativeQuery.append(" AND tdsr.estado_registro = ?4 ");
		nativeQuery.append(" AND epr.estado_registro = ?4 ");
		nativeQuery.append(" and exists (select 1 from PERSONA_SEDE ps inner join sede s on ps.id_sede = s.id_sede where id_persona = p.id_persona ");
		if (filtros.getCentro() != null && !filtros.getCentro().isEmpty())
			nativeQuery.append(" AND s.id_centro ")
					.append(UtilConsultasSQL.clausulaInSQLSNumeros(filtros.getCentro()));
		nativeQuery.append(" ) ORDER BY p.primer_nombre_o_razon_social ");

		try {
			Query query = em.createNativeQuery(nativeQuery.toString(), Persona.class);
			query.setParameter(1, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
			query.setParameter(2, filtros.getEstadoTipoServicio());
			query.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			if (filtros.getIdMateria() != null)
				query.setParameter(5, filtros.getIdMateria());

			conciliadoresPorCentro = query.getResultList();
		} catch (Exception e) {
			conciliadoresPorCentro = new ArrayList<>();
		}

		return conciliadoresPorCentro;
	}
	
	@SuppressWarnings("unchecked")
	public List<Persona> consultarMediadoresPorCentros(BusquedaRolesActivosDTO filtros) {
		List<Persona> conciliadoresPorCentro;

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT DISTINCT p.* ");
		nativeQuery.append(" FROM ");
		nativeQuery.append(" PERSONA as p ");
		nativeQuery.append(" INNER JOIN ROL_PERSONA as rp on p.id_persona = rp.id_persona  ");
		nativeQuery.append(
				" INNER JOIN TIPO_DE_SERVICIO_ROL as tdsr ON tdsr.tipo_servicio = ?1 and rp.id_rol = tdsr.id_rol ");
		nativeQuery.append(
				" INNER JOIN ESTADO_PERSONA_ROL as epr on p.id_persona = epr.id_persona and epr.id_rol = tdsr.id_rol ");

		nativeQuery.append(" INNER JOIN SERVICIO s  ON s.tipo = tdsr.tipo_servicio  AND s.estado_registro = ?4 ");
		if (filtros.getIdMateria() != null) {
			nativeQuery.append(" INNER JOIN PERSONA_SERVICIO_MATERIA psm ");
			nativeQuery.append(" ON psm.id_persona = p.id_persona ");
			nativeQuery.append(" AND ( psm.fecha_fin_de_vigencia is null ");
			nativeQuery.append(" OR psm.fecha_fin_de_vigencia >= GETDATE() ) ");
			nativeQuery.append(" AND s.id_servicio = psm.id_servicio ");
			nativeQuery.append(" AND psm.id_materia = ?5 ");
			nativeQuery.append(" AND psm.estado_registro = ?4 ");
		}

		nativeQuery.append(" WHERE ");
		nativeQuery.append(" epr.id_motivo  = ?2 ");
		nativeQuery.append(" AND (rp.fecha_fin_vigencia IS NULL OR CAST(rp.fecha_fin_vigencia as Date) >= CAST(GETDATE() as Date)) ");
		nativeQuery.append(" AND rp.id_centro IN ( 1 ) ");
		nativeQuery.append(" AND p.estado_registro = ?4 ");
		nativeQuery.append(" AND tdsr.estado_registro = ?4 ");
		nativeQuery.append(" AND epr.estado_registro = ?4 ");
		nativeQuery.append(" ORDER BY p.primer_nombre_o_razon_social ");

		try {
			Query query = em.createNativeQuery(nativeQuery.toString(), Persona.class);
			query.setParameter(1, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
			query.setParameter(2, filtros.getEstadoTipoServicio());
			query.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			if (filtros.getIdMateria() != null)
				query.setParameter(5, filtros.getIdMateria());

			conciliadoresPorCentro = query.getResultList();
		} catch (Exception e) {
			conciliadoresPorCentro = new ArrayList<>();
		}

		return conciliadoresPorCentro;
	}
	

	/**
	 * Consulta que retorna los Conciliadores activos. Opcional aplicar filtros
	 * con FiltrosSeleccionConciliadorDTO.
	 * 
	 * ADVERTENCIA: Este metodo puede crecer cuantos filtros nuevos sea
	 * necesario aplicar.
	 * 
	 * @param filtrosDTO
	 * @return List<Persona>
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> consultarConciliadores(FiltrosSeleccionConciliadorDTO filtrosDTO) {

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT p.* FROM PERSONA p ");
		nativeQuery.append(" INNER JOIN ROL_PERSONA rp ON p.id_persona = rp.id_persona ");
		nativeQuery.append(" INNER JOIN ROL r ON rp.id_rol = r.id_rol ");
		nativeQuery.append(" INNER JOIN PERSONA_SERVICIO_MATERIA psm ON p.id_persona = psm.id_persona ");
		nativeQuery.append(" WHERE r.nombre = ?1 ");
		nativeQuery.append(" AND psm.estado_registro = ?2 ");

		if (filtrosDTO.getIdServicio() != null) {
			nativeQuery.append(" AND psm.id_servicio = ?3 ");
		}

		if (filtrosDTO.getIdMateria() != null) {
			nativeQuery.append(" AND psm.id_materia = ?4 ");
		}

		Query query = em.createNativeQuery(nativeQuery.toString(), Persona.class);
		query.setParameter(1, filtrosDTO.getNombreRol());
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		if (filtrosDTO.getIdServicio() != null) {
			query.setParameter(3, filtrosDTO.getIdServicio());

		}

		if (filtrosDTO.getIdMateria() != null) {
			query.setParameter(4, filtrosDTO.getIdMateria());
		}

		return query.getResultList();
	}

	/**
	 * Metodo que consulta una persona por numero y tipo de identificacion
	 * 
	 * @param numeroCedula
	 * @param nombreRol
	 * @return Persona
	 */
	public Persona getConsultarPartePorCedula(String numeroCedula, String tipoId) {
		Persona persona = new Persona();
		try {
			StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append("SELECT p FROM Persona p ");
			jpqlQuery.append("WHERE ");
			jpqlQuery.append("p.numeroDocumento = :");
			jpqlQuery.append(Persona.ENTIDAD_PERSONA_NUMERO_DOCUMENTO);
			jpqlQuery.append(" AND p.tipoDocumento = :");
			jpqlQuery.append(Persona.ENTIDAD_PERSONA_TIPO_DOCUMENTO);
			jpqlQuery.append(" AND p.estadoPersona = :");
			jpqlQuery.append(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA);
			jpqlQuery.append(" AND p.estadoRegistro = :");
			jpqlQuery.append(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO);
			Query query = em.createQuery(jpqlQuery.toString(), Persona.class);
			query.setParameter(Persona.ENTIDAD_PERSONA_NUMERO_DOCUMENTO, numeroCedula);
			query.setParameter(Persona.ENTIDAD_PERSONA_TIPO_DOCUMENTO, tipoId);
			query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA, UtilDominios.ESTADO_PERSONA_ACTIVO);
			query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			persona = (Persona) query.getSingleResult();
		} catch (NoResultException e2) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString());
			throw new SimascException(mensajeError);
		} catch (NonUniqueResultException e2) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR200.toString());
			throw new SimascException(mensajeError);
		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString());
			throw new SimascException(mensajeError);
		}
		return persona;
	}

	@SuppressWarnings("unchecked")
	public List<InformacionPersonaDTO> partesInterventoras(long idCaso) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select  rpc.id_caso as idCaso, ");
		nativeQuery.append(" p.id_persona as idPersona, ");
		nativeQuery.append(" rol.nombre as rol, ");
		nativeQuery
				.append(" (select nombre from DOMINIO where codigo=p.tipo_de_empresa and dominio=?1) as tipoEmpresa, ");
		nativeQuery.append(
				" (select nombre from DOMINIO where codigo=p.tipo_de_entidad_publica and dominio=?2) as tipoEntidadPublica, ");
		nativeQuery.append(
				" (select nombre from DOMINIO where codigo=p.tipo_documento and dominio=?3) as tipoDocumentoPersona, ");
		nativeQuery.append(" p.numero_documento as numeroDocumento, ");
		nativeQuery.append(
				" (select zg.nombre from  zona_geografica zg where zg.id_zona_geografica=p.ciudad_del_documento) as ciudadDocumento, ");
		nativeQuery.append(
				" (select zg.nombre from  zona_geografica zg where zg.id_zona_geografica=p.id_pais_origen) as nacionalidad, ");
		nativeQuery.append(" p.primer_nombre_o_razon_social as nombreORazonSocial, ");
		nativeQuery.append(" p.segundo_nombre as segundoNombre, ");
		nativeQuery.append(" p.primer_apellido as primerApellido, ");
		nativeQuery.append(" p.segundo_apellido as segundoApellido, ");
		nativeQuery.append(" (select nombre from DOMINIO where codigo=p.sexo and dominio=?4) as sexo, ");
		nativeQuery.append(" p.fecha_de_nacimiento as fechaNacimiento, ");
		nativeQuery.append(" (select nombre from DOMINIO where codigo=p.estrato and dominio=?5) as  estrato, ");
		nativeQuery.append(" (select nombre from PROFESION where id_profesion=p.id_profesion) as profesion, ");
		nativeQuery.append(" (select nombre from DOMINIO where codigo=p.ESCOLARIDAD and dominio=?6) as escolaridad, ");
		nativeQuery.append(
				" (select nombre from DOMINIO where codigo=p.escolaridad and dominio=?7) as institucionesEducativas, ");
		nativeQuery.append(" p.fecha_de_grado as fechaGrado, ");
		nativeQuery.append(" p.numero_tarjeta_profesional as numeroTarjetaProfesional, ");
		nativeQuery.append(
				" (select nombre from DOMINIO where codigo=p.entidad_expide_tarjeta_profesional and dominio=?8) as entidadTarjetaProfesional, ");
		nativeQuery.append(" p.representante_legal as representanteLegal, ");
		nativeQuery.append(
				" (select nombre from DOMINIO where codigo=p.sector_de_la_empresa and dominio=?9) as sectorEmpresa, ");
		nativeQuery.append(" p.pagina_web as paginaWeb ");
		nativeQuery.append(" from rol_persona_caso rpc ");
		nativeQuery.append(" inner join PERSONA p on p.id_persona=rpc.id_persona ");
		nativeQuery.append(" inner join (select id_rol,d.nombre from rol r ");
		nativeQuery.append(
				" inner join dominio d on r.nombre=codigo and dominio='rol_persona' ) rol on rol.id_rol=rpc.id_rol ");
		nativeQuery.append(" where rpc.id_caso=?10 ");

		Query query = em.createNativeQuery(nativeQuery.toString(), InformacionPersonaDTO.class);
		query.setParameter(1, UtilDominios.DOMINIO_TIPO_EMPRESA);
		query.setParameter(2, UtilDominios.DOMINIO_TIPO_ENTIDAD_PUBLICA);
		query.setParameter(3, UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA);
		query.setParameter(4, UtilDominios.DOMINIO_SEXOS);
		query.setParameter(5, UtilDominios.DOMINIO_ESTRATOS);
		query.setParameter(6, UtilDominios.DOMINIO_ESCOLARIDADES);
		query.setParameter(7, UtilDominios.DOMINIO_INSTITUCIONES_EDUCATIVAS);
		query.setParameter(8, UtilDominios.DOMINIO_ENTIDAD_TARJETA_PROFESIONAL);
		query.setParameter(9, UtilDominios.DOMINIO_SECTOR_EMPRESA);
		query.setParameter(10, idCaso);
		return query.getResultList();
	}

	public ConciliadorSeleccionadoDTO conciliadorSeleccionado(Long idPersona) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select	c.id_caso as idCaso, ");
		nativeQuery.append(" p.id_persona as idPersona, ");
		nativeQuery.append(" p.primer_nombre_o_razon_social as nombreORazonSocial, ");
		nativeQuery.append(" p.segundo_nombre as segundoNombre, ");
		nativeQuery.append(" p.primer_apellido as primerApellido, ");
		nativeQuery.append(" p.segundo_apellido as segundoApellido, ");
		nativeQuery.append(" ss.fecha_inicio_audiencia as fechaInicioAudiencia ");
		nativeQuery.append(" from caso c ");
		nativeQuery.append(" inner join SOLICITUD_SERVICIO ss on ss.id_solicitud_servicio=c.id_solicitud_servicio ");
		nativeQuery.append(" inner join PERSONA_SOLICITUD ps on ps.id_solicitud_servicio=ss.id_solicitud_servicio ");
		nativeQuery.append(" inner join PERSONA p on p.id_persona=ps.id_persona ");
		nativeQuery.append(
				" inner join rol r on r.id_rol=ps.id_rol and exists (select 1 from DOMINIO where codigo=r.nombre and dominio=?1) ");
		nativeQuery.append(" inner join SERVICIO s on s.id_servicio=c.id_servicio and s.nombre=?2 ");
		nativeQuery.append(" where p.id_persona= ?3 ");

		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), ConciliadorSeleccionadoDTO.class);
		query.setParameter(1, UtilDominios.DOMINIO_ROL_PERSONA);
		query.setParameter(2, UtilDominios.SERVICIO_CONVENIO_CONCILIACION);
		query.setParameter(3, idPersona);

		return (ConciliadorSeleccionadoDTO) query.getResultList();
	}

	/**
	 * consulta las persona activas por tipo de funcioinario
	 * 
	 * @param tiposFuncionario
	 *            tipos de funcionario para filtrar
	 * @return
	 */
	public List<Persona> cosultarPersonaPorTipoFuncionario(List<String> tiposFuncionario) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT p FROM Persona p ");
		jpqlQuery.append(" WHERE p.tipoFuncionario IN :");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_TIPO_FUNCIONARIO);
		jpqlQuery.append(" AND p.estadoRegistro = :");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO);
		jpqlQuery.append(
				" ORDER BY p.primerNombreORazonSocial , p.segundoNombre , p.primerApellido , p.segundoApellido ");

		Query query = em.createQuery(jpqlQuery.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_TIPO_FUNCIONARIO, tiposFuncionario);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Persona> conciliadoresCasosPendienteCobro() {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT DISTINCT P.* ");
		nativeQuery.append(" FROM PERSONA P ");
		nativeQuery.append(" INNER JOIN ROL_PERSONA_CASO RPC ON RPC.ID_PERSONA = P.ID_PERSONA ");
		nativeQuery.append(" INNER JOIN ROL R ON R.ID_ROL = RPC.ID_ROL AND r.id_rol IN (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio =  ?1 ) ");
		nativeQuery.append(" INNER JOIN CASO C ON C.ID_CASO = RPC.ID_CASO AND  c.estado_registro = ?7 ");
		nativeQuery.append(" LEFT JOIN FACTURACION_CASO FC ON FC.ID_CASO = C.ID_CASO AND FC.COBRADO = ?5 AND FC.FECHA_DE_COBRO IS NULL  ");
		nativeQuery.append(" WHERE C.estado_caso in (?4,?8) ");
		nativeQuery.append(" AND RPC.ESTADO = ?6 ");
		nativeQuery.append(" AND rpc.estado_registro = ?7 ");
		nativeQuery.append(" AND r.estado_registro = ?7 order by p.primer_nombre_o_razon_social ASC ");		

		Query query = em.createNativeQuery(nativeQuery.toString(), Persona.class);
		query.setParameter(1, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(4, UtilDominios.ESTADO_CASO_CERRADO);
		query.setParameter(5, UtilConstantes.CERO);
		query.setParameter(6, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(7, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(8, UtilDominios.ESTADO_CASO_REGISTRADO);

		return query.getResultList();
	}

	/**
	 * consulta de nombre completo de las personas (partes de un caso de
	 * conciliacion) con su(s) correo electronico
	 * 
	 * @param idCaso
	 * @return
	 */
	public List<PersonaBasicaDTO> consultarCorreosElectronicosPorPersonaCaso(Long idCaso) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(
				" select p.primer_nombre_o_razon_social as primerNombreORazonSocial, p.id_persona as idPersona, ");
		nativeQuery.append(" p.segundo_nombre as segundoNombre, ");
		nativeQuery.append(" p.primer_apellido as primerApellido, ");
		nativeQuery.append(" p.segundo_apellido as segundoApellido, ");
		nativeQuery.append(" concat(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) as nombreCompleto, ");
		nativeQuery.append(" c.direccion as correoElectronico ");
		nativeQuery.append(" from PERSONA  p ");
		nativeQuery.append(" inner join ROL_PERSONA_CASO RPC on P.id_persona= RPC.id_persona  ");
		nativeQuery.append(" inner join CORREO_ELECTRONICO C ON P.ID_PERSONA =C.id_persona ");
		nativeQuery.append(" and (c.tipo = ?4 or (c.tipo <> ?4 and exists ");
		nativeQuery.append(" (select 1 from CORREO_ELECTRONICO_ROL_PERSONA_CASO cep ");
		nativeQuery.append(" where cep.id_caso = rpc.id_caso and cep.id_correo = c.id_correo)))");
		nativeQuery.append(" inner join rol r on rpc.id_rol = r.id_rol ");
		nativeQuery.append(" where RPC.id_caso=?1 ");
		nativeQuery.append(" AND RPC.ID_ROL IN (SELECT id_rol FROM ROL WHERE NOMBRE IN  ");
		nativeQuery.append(
				" (select CD.codigo_clasificado from CLASIFICADOR_DOMINIO CD WHERE CD.codigo_clasificador=?2)) ");
		nativeQuery.append("  AND p.estado_registro = ?3 ");
		nativeQuery.append(" AND c.estado_registro = ?3 ");
		nativeQuery.append(" AND RPC.estado_registro = ?3 ");
		nativeQuery.append(" AND R.estado_registro =  ?3 ");
		nativeQuery.append(
				" ORDER BY  p.primer_nombre_o_razon_social, p.segundo_nombre, p.primer_apellido, p.segundo_apellido ");

		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), PersonaBasicaDTO.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, UtilDominios.AGRUPADOR_ROL_PERSONA_PARTES_APLICACION);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(4, UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
		
		return (List<PersonaBasicaDTO>) query.getResultList();
	}

	/**
	 * Consulta los conciliadores que esten asignados a la sede de la audiencia,
	 * con asignación en la materia y cuantía del caso
	 * 
	 * @param idCaso
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PersonaBasicaDTO> consultarConciliadoresAsignadosASedeAudiencia(Long idMateria, Long idRol,
			String tipoCuantia, Long idServicio, Long sedeAudiencia, Long valorPretencionesCaso,
			Long valorPretencionesReferencia) {
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append(" select p.id_persona as idPersona, ");
		nativeQuery.append(
				"CONCAT(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ' , p.segundo_apellido ) AS nombreCompleto ");
		nativeQuery.append(" from PERSONA  p ");
		nativeQuery.append(" inner join ROL_PERSONA rp on rp.id_persona=p.id_persona ");
		nativeQuery.append(" inner join PERSONA_SEDE ps on ps.id_persona = p.id_persona ");
		nativeQuery.append(" inner join PERSONA_SERVICIO_MATERIA psm on psm.id_persona =  p.id_persona ");
		nativeQuery.append(" inner join LISTA L ON l.id_lista = rp.id_lista  ");
		nativeQuery.append(" where p.estado_persona=?1 ");
		nativeQuery.append(" AND rp.id_rol=?2  ");
		nativeQuery.append(" and ps.id_sede=?3 ");
		nativeQuery.append(" and psm.id_materia=?4 ");
		nativeQuery.append(" AND (	rp.fecha_fin_vigencia > getdate() or rp.fecha_fin_vigencia is null) ");
		nativeQuery.append(" and psm.id_servicio=?5 ");
		nativeQuery.append(" AND p.estado_registro=?1 ");
		nativeQuery.append(" AND rp.estado_registro=?1 ");
		nativeQuery.append(" AND psm.estado_registro=?1 ");
		nativeQuery.append(" AND ps.estado_registro=?1 ");
		nativeQuery.append(" AND l.estado_registro=?1 ");
		nativeQuery.append("  and exists (SELECT 1 FROM SEDE WHERE ID_SEDE  = PS.ID_SEDE ");
		nativeQuery.append(" AND ID_CENTRO = RP.ID_CENTRO  AND ESTADO_REGISTRO = ?1 ) ");

		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), PersonaBasicaDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idRol);
		query.setParameter(3, sedeAudiencia);
		query.setParameter(4, idMateria);
		query.setParameter(5, idServicio);

		return (List<PersonaBasicaDTO>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Persona> conciliadoresSeguimientoCasos(String inCentros) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT DISTINCT P.* ");
		nativeQuery.append(" FROM PERSONA P ");
		nativeQuery.append(
				" INNER JOIN ROL_PERSONA_CASO RPC ON RPC.ID_PERSONA = P.ID_PERSONA and p.estado_registro=rpc.estado_registro ");
		nativeQuery
				.append(" inner join LLAMADA ll on ll.id_caso=rpc.id_caso and ll.estado_registro=rpc.estado_registro ");
		nativeQuery.append(" INNER JOIN ROL R ON R.ID_ROL = RPC.ID_ROL ");
		nativeQuery.append(" inner join CASO c on c.id_caso=rpc.id_caso and c.estado_registro=rpc.estado_registro ");
		nativeQuery.append(" inner join sede s on s.id_sede=c.id_sede and s.estado_registro=c.estado_registro ");
		nativeQuery.append(" WHERE R.NOMBRE IN (?1,?2,?3) ");
		nativeQuery.append(" AND RPC.ESTADO = ?4 ");
		nativeQuery.append(" AND P.ESTADO_REGISTRO = ?5 ");
		nativeQuery.append(" and ll.tipo_llamada= ?6 ");
		nativeQuery.append(" and s.id_centro ").append(inCentros);
		
		Query query = em.createNativeQuery(nativeQuery.toString(), Persona.class);
		query.setParameter(1, UtilDominios.ROL_PERSONA_CONCILIADOR);
		query.setParameter(2, UtilDominios.ROL_PERSONA_CONCILIADOR_EN_INSOLVENCIA);
		query.setParameter(3, UtilDominios.ROL_PERSONA_CONCILIADOR_COMUNITARIO);
		query.setParameter(4, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(5, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(6, UtilDominios.LLAMADA_SEGUIMIENTO_CASO);
		
		return query.getResultList();
	}
	
	/**
	 * Método para obtener los conciliadores que tienen audiencias programadas para la fecha 
	 * @param fechaAudiencia
	 * @return
	 */
	public List<PersonaBasicaDTO> consultarConciliadoresConAudienciasProgramada(Date fechaAudiencia) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select distinct p.id_persona as idPersona ");
		nativeQuery.append(" , concat(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) as nombreCompleto ");
		nativeQuery.append(" from PERSONA p ");
		nativeQuery.append(" inner join AGENDA_PERSONA a on p.id_persona = a.id_persona and a.estado_registro = ?2 ");
		nativeQuery.append(" inner join ROL_PERSONA_CASO rpc on rpc.id_persona = a.id_persona ");
		nativeQuery.append(" and id_rol in (select id_rol from TIPO_DE_SERVICIO_ROL where tipo_servicio = ?3)");
		nativeQuery.append(" and rpc.estado <> ?4 ");
		nativeQuery.append(" and tipo_nombramiento = ?5");
		nativeQuery.append(" and rpc.estado_registro = ?2 ");
		nativeQuery.append(" where tipo_actividad_agenda = ?6 ");
		nativeQuery.append(" and a.estado <> ?7 ");
		nativeQuery.append(" and CAST(fecha_inicio as Date) = CAST(?1 as Date) ");
		nativeQuery.append(" and id_audiencia is not null ");
		nativeQuery.append(" and p.estado_registro = ?2 ");
		Query query = em.createNativeQuery(nativeQuery.toString(), PersonaBasicaDTO.class);
		query.setParameter(1, fechaAudiencia);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(3, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(4, UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO);
		query.setParameter(5, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(6, UtilDominios.TIPO_ACTIVIDAD_AGENDA_AUDIENCIA_CONCILIACION);
		query.setParameter(7, UtilDominios.ESTADO_AGENDA_PERSONA_CANCELADA);

		return query.getResultList();
	}
	
	public Persona consultarPersonaTipoYNumeroDocumento(Persona filtros) {
		StringBuilder jpqlQuery = new StringBuilder();
		Persona persona = new Persona();
		jpqlQuery.append(" select p from Persona p ");
		jpqlQuery.append(" where ");
		jpqlQuery.append(" p.estadoRegistro =: ").append(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO);
		if (filtros.getTipoPersona() != null) {
			jpqlQuery.append(" and p.tipoPersona =: ").append(Persona.ENTIDAD_PERSONA_TIPO_PERSONA);
		}
		if (filtros.getTipoDocumento() != null) {
			jpqlQuery.append(" and p.tipoDocumento =: ").append(Persona.ENTIDAD_PERSONA_TIPO_DOCUMENTO);
		}
		if (filtros.getNumeroDocumento() != null) {
			jpqlQuery.append(" and p.numeroDocumento =: ").append(Persona.ENTIDAD_PERSONA_NUMERO_DOCUMENTO);
		}
		

		Query query = em.createQuery(jpqlQuery.toString(), Persona.class);
		if (filtros.getTipoPersona() != null) {
			query.setParameter(Persona.ENTIDAD_PERSONA_TIPO_PERSONA, filtros.getTipoPersona());
		}
		if (filtros.getTipoDocumento() != null) {
			query.setParameter(Persona.ENTIDAD_PERSONA_TIPO_DOCUMENTO, filtros.getTipoDocumento());
		}
		if (filtros.getNumeroDocumento() != null) {
			query.setParameter(Persona.ENTIDAD_PERSONA_NUMERO_DOCUMENTO, filtros.getNumeroDocumento());
		}
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		try {
			persona = (Persona) query.getSingleResult();
		} catch (NoResultException nr) {
			persona = null;
		}		
		return persona;
	}
	
	/** TRANS-F-020 consulta que trae un resumen de la hoja de vida de un prestador.
	 * @param idPersona
	 * @param idRol
	 * @return InformacionPrestadorDTO
	 */
	public InformacionPrestadorDTO consultarInformacionPrestador( Long idPersona, Long idRol ){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT rol.nombre AS nombreRol, ");
		nativeQuery.append(" concat( rtrim(pres.primer_nombre_o_razon_social), rtrim(' '+pres.segundo_nombre) ) AS nombresPrestador, ");
		nativeQuery.append(" concat( rtrim(pres.primer_apellido), rtrim(' '+pres.segundo_apellido) ) AS apellidosPrestador, ");
		nativeQuery.append(" (select nombre from zona_geografica where id_zona_geografica = pres.id_pais_origen and estado_registro = ?1 ) AS nacionalidad, ");
		nativeQuery.append(" pres.resumen_hoja_vida AS hojaDeVida, ");
		nativeQuery.append(" stuff(( select distinct ( ', '+m.nombre ) ");
				nativeQuery.append(" from materia m ");
				nativeQuery.append(" inner join persona_servicio_materia psms on psms.id_materia = m.id_materia ");
				nativeQuery.append(" inner join servicio se on se.id_servicio = psms.id_servicio ");
				nativeQuery.append(" where rol.tipo_servicio = se.tipo ");
				nativeQuery.append(" and psms.id_persona = pres.id_persona ");
				nativeQuery.append(" and m.estado_registro = ?1 ");
				nativeQuery.append(" and psms.estado_registro = ?1 ");
				nativeQuery.append(" and se.estado_registro = ?1 ");
				nativeQuery.append(" order by ', '+m.nombre ");
		nativeQuery.append(" FOR xml PATH ('')), 1, 2, '') AS materias, ");
		nativeQuery.append(" stuff(( select distinct ( ', '+d.nombre ) ");
				nativeQuery.append(" from idioma i ");
				nativeQuery.append(" inner join DOMINIO d on d.codigo = i.nombre and dominio = ?3 ");
				nativeQuery.append(" where i.id_persona = pres.id_persona ");
				nativeQuery.append(" and i.estado_registro = ?1 ");
				nativeQuery.append(" order by ', '+d.nombre ");
		nativeQuery.append(" FOR xml PATH ('')), 1, 2, '') AS idiomas, ");
		nativeQuery.append(" case when rol.nombre = ?7 then CONCAT( ubicacion.direccion, ', ', ");
				nativeQuery.append(" (select nombre from zona_geografica where id_zona_geografica = ubicacion.id_zona_geografica and estado_registro = ?1 ) ) end AS direccion, ");
		nativeQuery.append(" case when rol.nombre = ?7 then CONCAT( telefono.numero, ' Extensión '+telefono.extension) end AS numeroTelefono, ");
		nativeQuery.append(" case when rol.nombre = ?7 then celular.numero end as numeroCelular ");
		nativeQuery.append(" FROM PERSONA pres ");
		nativeQuery.append(" CROSS APPLY ( ");
		nativeQuery.append(" select top 1 rp_ca.* from ROL_PERSONA rp_ca ");
		nativeQuery.append(" where rp_ca.id_persona = pres.id_persona ");
		nativeQuery.append(" and rp_ca.id_rol = ?2 ");
		nativeQuery.append(" and ( rp_ca.fecha_fin_vigencia is null or ");
				nativeQuery.append(" rp_ca.fecha_fin_vigencia >= GETDATE() ) ");
		nativeQuery.append(" and rp_ca.estado_registro = 'ACT' ");
		nativeQuery.append(" order by rp_ca.fecha_inicio_vigencia desc ) rp ");
		nativeQuery.append(" INNER JOIN ROL rol ");
		nativeQuery.append(" ON rol.id_rol = rp.id_rol ");
		nativeQuery.append(" OUTER APPLY( ");
		nativeQuery.append(" select top 1 u.* from UBICACION u ");
		nativeQuery.append(" where u.id_persona = pres.id_persona ");
		nativeQuery.append(" and u.estado_registro = ?1 ");
		nativeQuery.append(" order by u.fecha_ultima_modificacion desc ) ubicacion ");
		nativeQuery.append(" OUTER APPLY ( ");
		nativeQuery.append(" select top 1 t_fij.* from TELEFONO t_fij ");
		nativeQuery.append(" where t_fij.id_persona = pres.id_persona ");
		nativeQuery.append(" and t_fij.tipo_telefono = ?4 ");
		nativeQuery.append(" and t_fij.estado_registro = ?1 ");
		nativeQuery.append(" order by t_fij.fecha_ultima_modificacion desc ) telefono ");
		nativeQuery.append(" OUTER APPLY ( ");
		nativeQuery.append(" select top 1 t_cel.* from TELEFONO t_cel ");
		nativeQuery.append(" where t_cel.id_persona = pres.id_persona ");
		nativeQuery.append(" and t_cel.tipo_telefono = ?5 ");
		nativeQuery.append(" and t_cel.estado_registro = ?1 ");
		nativeQuery.append(" order by t_cel.fecha_ultima_modificacion desc ) celular ");
		nativeQuery.append(" WHERE pres.id_persona = ?6 ");
		nativeQuery.append(" AND pres.estado_registro = ?1 ");
		nativeQuery.append(" AND rol.estado_registro = ?1 ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), InformacionPrestadorDTO.class);
		query.setParameter( 1, UtilDominios.ESTADO_REGISTRO_ACTIVO );
		query.setParameter( 2, idRol );
		query.setParameter( 3, UtilDominios.DOMINIO_IDIOMAS );
		query.setParameter( 4, UtilDominios.TIPO_TELEFONO_FIJO );
		query.setParameter( 5, UtilDominios.TIPO_TELEFONO_CELULAR );
		query.setParameter( 6, idPersona );
		query.setParameter( 7, UtilDominios.ROL_PERSONA_PERITO );
		
		List<InformacionPrestadorDTO> informacionPrestadorDTO = (List<InformacionPrestadorDTO>) query.getResultList();
		return !informacionPrestadorDTO.isEmpty()? informacionPrestadorDTO.get(0) : null; 
	}
	public PersonaBasicaDTO consultarPersonaPorRolCaso(Long idCaso, String nombreRol) {
		PersonaBasicaDTO datos;
		StringBuilder sql = new StringBuilder();
		sql.append("select p.numero_documento as numeroDocumento from persona p ");
		sql.append(" inner join rol_persona_caso rpc on p.id_persona = rpc.id_persona ");
		sql.append(" and rpc.estado_registro = ?1 and estado = ?2 and rpc.id_caso = ?3  ");
		sql.append(" inner join rol r on r.id_rol = rpc.id_rol and r.estado_registro = ?1 and r.nombre = ?4 ");
		sql.append(" where p.estado_registro = ?1 ");
		
		Query query = getEntityManager().createNativeQuery(sql.toString(), PersonaBasicaDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_USUARIO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(3, idCaso);
		query.setParameter(4, nombreRol);
		
		try {
			datos = (PersonaBasicaDTO) query.getSingleResult();
		} catch(NoResultException | NonUniqueResultException e) {
			datos = null;
		}
		return datos;
	}
	/**
	 * Devuelve arbitros con edad mayor a 75 años
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AlertaEmeritoDTO> consultarArbitrosEmeritos() {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT p.id_persona as idPersona, ");
		nativeQuery.append("CONCAT(p.primer_nombre_o_razon_social, ' ' , p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) as nombrePersona, ");
		nativeQuery.append("rp.id_rol as idRol, pss.id_servicio as idServicio ");
		nativeQuery.append("FROM persona p  with (nolock) ");
		nativeQuery.append("inner join ESTADO_PERSONA_ROL epr  with (nolock) ");
		nativeQuery.append("on p.id_persona = epr.id_persona ");
		nativeQuery.append("inner join ROL_PERSONA rp  with (nolock) ");
		nativeQuery.append("on p.id_persona = rp.id_persona "); 
		nativeQuery.append("and rp.id_rol = epr.id_rol ");
		nativeQuery.append("inner join PERSONA_ROL_ALERTA pra  with (nolock) ");
		nativeQuery.append("on rp.id_rol = pra.id_rol ");
		nativeQuery.append("inner join parametro_servicio_sorteo pss ");
		nativeQuery.append("on pss.id_rol = rp.id_rol ");
		nativeQuery.append("and pss.id_parametros_servicios_sorteo = pss.id_servicio_sorteo ");
		nativeQuery.append("inner join ALERTA a  with (nolock) ");
		nativeQuery.append("on pra.id_alerta = a.id_alerta ");
		nativeQuery.append("WHERE p.estado_registro = ?1 ");
		nativeQuery.append("AND epr.id_motivo = ?2 ");
		nativeQuery.append("AND epr.estado_registro = ?1 ");
		nativeQuery.append("AND pra.estado_registro = ?1 ");
		nativeQuery.append("and rp.estado_registro = ?1 and rp.fecha_fin_vigencia is null ");
		nativeQuery.append("AND convert(varchar(10), p.fecha_de_nacimiento, 111) <= CONVERT(varchar(10), dateadd(yy, -75, GETDATE()), 111) ");
		nativeQuery.append("and a.codigo = ?3 ");
		

		Query query = em.createNativeQuery(nativeQuery.toString(), AlertaEmeritoDTO.class);

		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_ARBITROS_HABILITADO);
		query.setParameter(3, "ARBEME");

		return query.getResultList();
	}

	/**
	 * Consulta los arbitros posibles para preseleccionar dependiendo del tipo
	 * de servicio sea de conciliacion o sea de arbitraje
	 * 
	 * @param tipoServicio
	 * @return List<Persona>
	 */
	@SuppressWarnings("unchecked")
	public List<GenericoDTO> consultarArbitrosInternacionalPreseleccion(String tipoServicio, FiltosPreseleccionArbitros filtros) {
		// CONSULTA DE ARBITROS
		List<Object[]> persona;
		List<GenericoDTO> salida = new ArrayList<GenericoDTO>();
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select distinct p.id_persona as idPersona ");
		nativeQuery.append(" , concat(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) as nombreCompleto ");
		nativeQuery.append(" ,p.id_pais_origen  ");
		
		if(filtros.getIdioma() != null && !filtros.getIdioma().isEmpty()){
			nativeQuery.append(" ,idioma.nombre  ");
		}		
		if(filtros.getLeyAplicable() != null && !filtros.getLeyAplicable().isEmpty()){
			nativeQuery.append(" ,pl.id_ley_aplicable  ");
		}		
		if(filtros.getIdMateria() != null && !filtros.getIdMateria().isEmpty()){
			nativeQuery.append(" ,psm.id_materia  ");
		}		
			nativeQuery.append(" ,CASE when exists(select epr.id_persona from ESTADO_PERSONA_ROL epr, PERSONA_SERVICIO_MATERIA psm where epr.id_persona = psm.id_persona and epr.id_motivo = ?6 and psm.estado_para_sorteo = ?5 and epr.id_persona= p.id_persona  and epr.estado_registro = ?5 and psm.estado_registro=?5 ) then 'Sorteable' else 'No sorteable' END as estado_sorteo  ");
		
		nativeQuery.append(" from PERSONA p ");
		nativeQuery.append(" inner join ESTADO_PERSONA_ROL epr on epr.id_persona = p.id_persona ");
		
		if(filtros.getIdioma() != null && !filtros.getIdioma().isEmpty()){
			nativeQuery.append(" LEFT JOIN IDIOMA idioma on idioma.id_persona = p.id_persona and idioma.nombre =1");
		}		
		if(filtros.getLeyAplicable() != null && !filtros.getLeyAplicable().isEmpty()){
			nativeQuery.append(" LEFT JOIN PERSONA_LEY pl on p.id_persona = pl.id_persona and pl.id_ley_aplicable =2");
		}		
		nativeQuery.append(" INNER JOIN  PERSONA_SERVICIO_MATERIA ps on  p.id_persona =  ps.id_persona  ");
		if(filtros.getIdMateria() != null && !filtros.getIdMateria().isEmpty()){
			nativeQuery.append(" LEFT JOIN  PERSONA_SERVICIO_MATERIA psm on  p.id_persona =  psm.id_persona and psm.id_materia =3");
		}	
		nativeQuery.append(" INNER JOIN  PARAMETRO_SERVICIO_SORTEO pss on ps.id_servicio = pss.id_servicio and epr.id_rol = pss.id_rol and pss.id_servicio =4");
		nativeQuery.append(" INNER JOIN ROL_PERSONA rp on pss.id_rol = rp.id_rol and rp.id_persona = p.id_persona and rp.estado_registro = ?5 and rp.fecha_fin_vigencia is null ");
		nativeQuery.append(" WHERE epr.id_motivo NOT IN (select codigo from PARAM_ESTADO_ARBITRO_PRESELECCION where estado_registro = ?5)");		
		
		Query query = em.createNativeQuery(nativeQuery.toString());
		
		if(filtros.getIdioma() != null && !filtros.getIdioma().isEmpty()){
			query.setParameter(1, filtros.getIdioma());
		}
		if(filtros.getLeyAplicable() != null && !filtros.getLeyAplicable().isEmpty()){
			query.setParameter(2, filtros.getLeyAplicable());
		}					
		
		if(filtros.getIdMateria() != null && !filtros.getIdMateria().isEmpty()){
			query.setParameter(3, Long.valueOf(filtros.getIdMateria()));
		}	
		else{
			// sin materia
			query.setParameter(3, UtilConstantes.ID_SIN_MATERIA);
		}
		query.setParameter(4, UtilConstantes.ID_SERVICIO_ARBITRAJE_INTERNACIONAL);
		query.setParameter(5, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(6, UtilDominios.ESTADO_ARBITROS_HABILITADO);
		
		persona = query.getResultList();
		for (Object[] obj : persona){
			//INDICE DE ARREGLO
			int indiceArreglo = UtilConstantes.CERO;
			GenericoDTO aux = new GenericoDTO();
			aux.setId("" + obj[indiceArreglo++]);
			aux.setNombre((String) obj[indiceArreglo++]);
			aux.setIdPais(""+ obj[indiceArreglo++]);
			if(filtros.getIdioma() != null && !filtros.getIdioma().isEmpty()){
				aux.setIdioma(""+ obj[indiceArreglo++]);
			}		
			if(filtros.getLeyAplicable() != null && !filtros.getLeyAplicable().isEmpty()){
				aux.setIdLeyAplicable(""+ obj[indiceArreglo++]);
			}		
			if(filtros.getIdMateria() != null && !filtros.getIdMateria().isEmpty()){
				aux.setIdMateria(""+ obj[indiceArreglo++]);
			}		
				aux.setEstadoSorteo(""+ obj[indiceArreglo++]);
			salida.add(aux);
		}
		
		return salida;
	}
	
	/**
	 * Consulta los arbitros posibles para preseleccionar dependiendo del tipo
	 * de servicio sea de conciliacion o sea de arbitraje
	 * 
	 * @param tipoServicio
	 * @return List<Persona>
	 */
	@SuppressWarnings("unchecked")
	public List<GenericoDTO> consultarArbitrosPreseleccionFiltros(String tipoServicio, FiltosPreseleccionArbitros filtros) {
		
		// CONSULTA DE ESTADOS
		StringBuilder estadosQuery = new StringBuilder();
		estadosQuery
				.append("SELECT peap.codigo FROM ParamEstadoArbitroPreseleccion peap WHERE peap.estadoRegistro = :");
		estadosQuery.append(ParamEstadoArbitroPreseleccion.ENTIDAD_PARAM_ESTADO_ARBITRO_PRESELECCION_ESTADO_REGISTRO);
		Query estados = mp.createQuery(estadosQuery.toString());
		estados.setParameter(ParamEstadoArbitroPreseleccion.ENTIDAD_PARAM_ESTADO_ARBITRO_PRESELECCION_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_INACTIVO);
		List<String> sList = estados.getResultList();
		
		//INDICE DE CONSULTA
		int indiceConsulta = UtilConstantes.UNO;
		
		//INDICE DE PARAMETROS
		int indiceParametros = UtilConstantes.UNO;
		
		// CONSULTA DE ARBITROS
		List<Object[]> persona;
		List<GenericoDTO> salida = new ArrayList<GenericoDTO>();
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select distinct p.id_persona as idPersona ");
		nativeQuery.append(" , concat(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) as nombreCompleto ");
		nativeQuery.append(" ,CASE when exists(select epr.id_persona from ESTADO_PERSONA_ROL epr, PERSONA_SERVICIO_MATERIA psm where epr.id_persona = psm.id_persona and epr.id_motivo = 'EAH' and psm.estado_para_sorteo = 'ACT' and epr.id_persona= p.id_persona  and epr.estado_registro = 'ACT' and psm.estado_registro='ACT' ) then 'Sorteable' else 'No sorteable' END as estado_sorteo  ");
		nativeQuery.append(" from PERSONA p ");
		nativeQuery.append(" inner join ESTADO_PERSONA_ROL epr on epr.id_persona = p.id_persona ");		
		nativeQuery.append(" inner JOIN ROL_PERSONA rp on p.id_persona = rp.id_persona and rp.estado_registro = 'ACT' ");
		nativeQuery.append(" inner JOIN ROL r on r.id_rol = rp.id_rol and r.estado_registro = 'ACT' ");
		
		// filtro Rol
		if(filtros.getRol() != null && !filtros.getRol().isEmpty()){
			nativeQuery.append(" and  rp.id_rol  =?").append(indiceConsulta++);
		}
		// filto Lista
		if(filtros.getLista() != null && !filtros.getLista().isEmpty()){
			nativeQuery.append(" and  rp.id_lista =?").append(indiceConsulta++);
		}			
		if(filtros.getQuienPreselecciona().equals(UtilDominios.PRESELECCIONADO_POR_LA_CAMARA_DE_COMERCIO_BOGOTA) || (filtros.getIdMateria() != null && !filtros.getIdMateria().isEmpty()) ){
			nativeQuery.append(" INNER JOIN  PERSONA_SERVICIO_MATERIA psm on  p.id_persona =  psm.id_persona and psm.estado_registro ='ACT' ");
		}
		if(filtros.getQuienPreselecciona().equals(UtilDominios.PRESELECCIONADO_POR_LA_CAMARA_DE_COMERCIO_BOGOTA)){
			nativeQuery.append(" INNER JOIN  PARAMETRO_SERVICIO_SORTEO pss on psm.id_servicio = pss.id_servicio  and pss.id_rol = rp.id_rol ");
		}
		if(filtros.getIdMateria() != null && !filtros.getIdMateria().isEmpty()){
			nativeQuery.append(" and psm.id_materia =?").append(indiceConsulta++);
		}	
		if(filtros.getRol().equals(UtilConstantes.ID_ROL_CONCILIADOR)){
			nativeQuery.append(" WHERE r.tipo_servicio = ");
			nativeQuery.append("?").append(indiceConsulta++);
			nativeQuery.append(" AND epr.id_motivo IN (");
			for (int i = 0; i < sList.size(); i++) {
				if (i != UtilConstantes.CERO) {
					nativeQuery.append(UtilConstantes.COMA).append(UtilConstantes.ESPACIO);
				}
				nativeQuery.append("?").append(indiceConsulta++);
			}
			nativeQuery.append(") ");
		}
		
		
		Query query = em.createNativeQuery(nativeQuery.toString());
		
		if(filtros.getRol() != null && !filtros.getRol().isEmpty()){
			query.setParameter(indiceParametros++, Long.valueOf(filtros.getRol()));
		}	
		
		if(filtros.getLista() != null && !filtros.getLista().isEmpty()){
			query.setParameter(indiceParametros++, Long.valueOf(filtros.getLista()));
		}
		
		if(filtros.getIdMateria() != null && !filtros.getIdMateria().isEmpty()){
			query.setParameter(indiceParametros++, filtros.getIdMateria());
		}	
		if(filtros.getRol().equals(UtilConstantes.ID_ROL_CONCILIADOR)){
			query.setParameter(indiceParametros++, tipoServicio);
			for (String idEstado : sList) {
				query.setParameter(indiceParametros++, idEstado);
			}
		}
			
		persona = query.getResultList();
		
		for (Object[] obj : persona){
			//INDICE DE ARREGLO
			int indiceArreglo = UtilConstantes.CERO;
			GenericoDTO aux = new GenericoDTO();
			aux.setId("" + obj[indiceArreglo++]);
			aux.setNombre((String) obj[indiceArreglo++]);
			aux.setEstadoSorteo(""+ obj[indiceArreglo++]);
			salida.add(aux);
		}
		
		return salida;
	}
	// protected region metodos adicionales end
	
	@SuppressWarnings("unchecked")
	public List<GenericoDTO> consultarArbitrosPreseleccionCCBFiltros(FiltrosPreseleccionArbitrosCCB filtros) {
		
		String idsMaterias = UtilSimasc.listaToString(filtros.getIdMaterias());
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT p.id_persona, CONCAT(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) as arbitro ");
		nativeQuery.append("FROM PERSONA p ");
		nativeQuery.append("INNER JOIN PERSONA_SERVICIO_MATERIA psm ON p.id_persona = psm.id_persona ");		
		nativeQuery.append("INNER JOIN PARAMETRO_SERVICIO_SORTEO pss ON pss.id_servicio = psm.id_servicio ");
		nativeQuery.append("INNER JOIN ROL_PERSONA rp ON rp.id_rol = pss.id_rol and rp.id_persona = p.id_persona ");
		if(filtros.getNombreLista() != null){
			nativeQuery.append("INNER JOIN LISTA l ON l.id_lista = rp.id_lista ");
		}		
		nativeQuery.append("INNER JOIN ESTADO_PERSONA_ROL epr ON p.id_persona = epr.id_persona and rp.id_rol = epr.id_rol ");
		nativeQuery.append("WHERE psm.id_materia IN ( " + idsMaterias + " ) ");
		nativeQuery.append("AND psm.estado_para_sorteo = ?1 ");
		nativeQuery.append("AND psm.estado_registro = ?1 ");
		nativeQuery.append("AND psm.fecha_fin_de_vigencia is null ");
		nativeQuery.append("AND rp.estado_registro = ?2 AND rp.fecha_fin_vigencia IS NULL ");
		nativeQuery.append("AND psm.id_servicio = ?3 ");
		if(filtros.getNombreLista() != null){
			nativeQuery.append("AND l.nombre = ?4 ");
		}
		nativeQuery.append("AND epr.id_motivo = ?7 ");
		nativeQuery.append("AND epr.id_servicio = ?3 ");
		nativeQuery.append("AND epr.estado_registro = ?1 ");
		nativeQuery.append("AND p.id_persona in (select id_persona ");
		nativeQuery.append("FROM PERSONA_SERVICIO_MATERIA ");
		nativeQuery.append("WHERE id_materia IN ( " + idsMaterias + " ) ");
		nativeQuery.append("AND estado_registro = ?1 ");
		nativeQuery.append("AND id_servicio = ?3 ");
		nativeQuery.append("AND fecha_fin_de_vigencia is null ");
		nativeQuery.append("GROUP by id_persona ");
		nativeQuery.append("HAVING COUNT(*) > ?6 ");
		nativeQuery.append(") ");
		nativeQuery.append("GROUP BY p.id_persona, p.primer_nombre_o_razon_social ,p.segundo_nombre, p.primer_apellido, p.segundo_apellido ");
		nativeQuery.append("HAVING COUNT(*) > ?5 ");
				
		Query query = em.createNativeQuery(nativeQuery.toString());		
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(3, filtros.getIdServicio());
		if(filtros.getNombreLista() != null){
			query.setParameter(4, filtros.getNombreLista());
		}
		query.setParameter(5, filtros.getCantidadMateriasSorteable());
		query.setParameter(6, filtros.getCantidadMateriasAsignadas());
		query.setParameter(7, UtilDominios.ESTADO_ARBITROS_HABILITADO);
		
		List<Object[]> arbitros = query.getResultList();
		List<GenericoDTO> listadoArbitros = new ArrayList<>();
				
		for (Object[] obj : arbitros){
			//INDICE DE ARREGLO
			int indiceArreglo = UtilConstantes.CERO;
			GenericoDTO aux = new GenericoDTO();
			aux.setId("" + obj[indiceArreglo++]);
			aux.setNombre((String) obj[indiceArreglo++]);
			aux.setEstadoSorteo("Sorteable");
			listadoArbitros.add(aux);
		}
		
		return listadoArbitros;
	}

	public List<PersonaBasicaDTO> consultarAcreedorerPorCaso(Long idCaso) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT     p.primer_nombre_o_razon_social +' '+ p.segundo_nombre +' '+ p.primer_apellido +' '+p.segundo_apellido AS nombreCompleto , ");
		nativeQuery.append("           p.tipo_documento                                                                                      AS tipoDocumento  , ");
		nativeQuery.append("           p.numero_documento                                                                                    AS numeroDocumento, ");
		nativeQuery.append("           ( SELECT top(1)                                                                                          ce.direccion ");
		nativeQuery.append("           FROM    CORREO_ELECTRONICO ce ");
		nativeQuery.append("           WHERE   ce.id_persona = p.id_persona ");
		nativeQuery.append("           ) ");
		nativeQuery.append("           AS              correoElectronico, ");
		nativeQuery.append("           ( SELECT top(1) t.numero ");
		nativeQuery.append("           FROM    TELEFONO t ");
		nativeQuery.append("           WHERE   t.id_persona = p.id_persona ");
		nativeQuery.append("           ) ");
		nativeQuery.append("                       AS telefono, ");
		nativeQuery.append("           u.direccion AS direccion ");
		nativeQuery.append("FROM       ROL_PERSONA_CASO rpc ");
		nativeQuery.append("           INNER JOIN CASO c ");
		nativeQuery.append("           ON         c.id_caso = rpc.id_caso ");
		nativeQuery.append("           INNER JOIN PERSONA p ");
		nativeQuery.append("           ON         p.id_persona = rpc.id_persona ");
		nativeQuery.append("           INNER JOIN Rol r ");
		nativeQuery.append("           ON         r.id_rol = rpc.id_rol ");
		nativeQuery.append("           LEFT JOIN UBICACION u ");
		nativeQuery.append("           ON         u.id_persona      = p.id_persona ");
		nativeQuery.append("           AND        u.estado_registro = ?3 ");
		nativeQuery.append("WHERE      c.id_caso                    = ?1 ");
		nativeQuery.append("AND        r.nombre                     = ?2 ");
		nativeQuery.append("AND        rpc.estado_registro          = ?3 ");

		Query query = em.createNativeQuery(nativeQuery.toString(), PersonaBasicaDTO.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, UtilDominios.ROL_PERSONA_ACREEDOR);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();
	}

	public String consultarConciliadorPorCaso(Long idCaso) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT     p.primer_nombre_o_razon_social +' '+ p.segundo_nombre +' '+ p.primer_apellido +' '+p.segundo_apellido AS nombreCompleto ");
		nativeQuery.append("FROM       ROL_PERSONA_CASO rpc ");
		nativeQuery.append("           INNER JOIN CASO c ");
		nativeQuery.append("           ON         c.id_caso = rpc.id_caso ");
		nativeQuery.append("           INNER JOIN PERSONA p ");
		nativeQuery.append("           ON         p.id_persona = rpc.id_persona ");
		nativeQuery.append("           INNER JOIN Rol r ");
		nativeQuery.append("           ON         r.id_rol = rpc.id_rol ");
		nativeQuery.append("WHERE      c.id_caso           = ?1 ");
		nativeQuery.append("AND        r.nombre            = ?2 ");
		nativeQuery.append("AND        rpc.estado_registro = ?3 ");

		Query query = em.createNativeQuery(nativeQuery.toString(), String.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, UtilDominios.ROL_PERSONA_CONCILIADOR);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		if(query.getResultList().isEmpty()) {
			return null;
		}

		return query.getResultList().get(0).toString();
	}

}
