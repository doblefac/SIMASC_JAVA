package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

// Escriba en esta sección sus modificaciones

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.ConvenioDTO;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.entidades.Centro;
import com.ccb.simasc.transversal.entidades.Convenio;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.RelacionadoConvenio;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.Sede;
import com.ccb.simasc.transversal.entidades.SedeConvenio;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad Convenio.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorConvenio extends ManejadorCrud<Convenio, Long> {

	// protected region atributos on begin

	// Escriba en esta sección sus modificaciones

	// protected region atributos end

	public ManejadorConvenio() {
		super(Convenio.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	public List<Convenio> consultarConvenioPorRelacionado(Long idPersona, Long idRol, Date fechaVigencia,
			String tipoConvenio) {
		StringBuilder jpqlQuery = new StringBuilder();
		List<Convenio> convenio = null;
		Date fechaInicioDia = null;
		Date fechaFinDia = null;

		if (fechaVigencia != null) {
			fechaInicioDia = UtilOperaciones.obtenerFechaComienzoDelDia(fechaVigencia);
			fechaFinDia = UtilOperaciones.obtenerFechaFinDelDia(fechaVigencia);
		}

		jpqlQuery.append(" select c from RelacionadoConvenio rc , Convenio c ");
		jpqlQuery.append(" where c.estadoRegistro = :");
		jpqlQuery.append(Convenio.ENTIDAD_CONVENIO_ESTADO_REGISTRO_CONVENIO);
		jpqlQuery.append(" AND rc.estadoRegistro = :");
		jpqlQuery.append(RelacionadoConvenio.ENTIDAD_RELACIONADO_CONVENIO_ESTADO_REGISTRO_RELACIONADOCONVENIO);
		jpqlQuery.append(" AND c.idConvenio = rc.convenio.idConvenio ");
		if (idRol != null) {
			jpqlQuery.append(" AND rc.rol.idRol = :");
			jpqlQuery.append(Rol.ENTIDAD_ROL_PK);
		}
		if (tipoConvenio != null) {
			jpqlQuery.append(" AND c.tipoConvenio = :");
			jpqlQuery.append(Convenio.ENTIDAD_CONVENIO_TIPO_CONVENIO);
		}

		jpqlQuery.append(" AND rc.persona.idPersona = :");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);

		if (fechaVigencia != null) {
			jpqlQuery.append(" AND :inicioDia >= c.fechaInicioVigencia ");
			jpqlQuery.append(" AND :finDia <= c.fechaFinVigencia ");
		}

		jpqlQuery.append(" ORDER BY c.nombre ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Convenio.ENTIDAD_CONVENIO_ESTADO_REGISTRO_CONVENIO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(RelacionadoConvenio.ENTIDAD_RELACIONADO_CONVENIO_ESTADO_REGISTRO_RELACIONADOCONVENIO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if (idRol != null) {
			query.setParameter(Rol.ENTIDAD_ROL_PK, idRol);
		}
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);

		if (fechaVigencia != null) {
			query.setParameter("inicioDia", fechaInicioDia);
			query.setParameter("finDia", fechaFinDia);
		}

		if (tipoConvenio != null) {
			query.setParameter(Convenio.ENTIDAD_CONVENIO_TIPO_CONVENIO, tipoConvenio);
		}

		convenio = query.getResultList();

		return convenio;
	}

	/**
	 * Consulta los convenios vigentes por tipo de convenio
	 * 
	 * @param tipoConvenio
	 * @return
	 */
	public List<Convenio> consultarConveniosVigentes(String tipoConvenio, Long idServicio) {
		Date fechaActual = new Date();

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT c FROM Convenio c ");
		jpqlQuery.append(" WHERE c.tipoConvenio = :").append(Convenio.ENTIDAD_CONVENIO_TIPO_CONVENIO);
		jpqlQuery.append(" AND c.estadoRegistro = :").append(Convenio.ENTIDAD_CONVENIO_ESTADO_REGISTRO);
		jpqlQuery.append(" AND c.idServicio = :").append(Convenio.ENTIDAD_CONVENIO_ID_SERVICIO);
		if(UtilDominios.TIPO_CONVENIO_CONVENIO.equals(tipoConvenio))
			jpqlQuery.append(" AND :fechaActual BETWEEN c.fechaInicioVigencia AND c.fechaFinVigencia ");
		else
			jpqlQuery.append(" AND :fechaActual < c.fechaInicioVigencia ");
		jpqlQuery.append(" order by c.nombre ");
		Query query = mp.getEntityManager().createQuery(jpqlQuery.toString());
		query.setParameter(Convenio.ENTIDAD_CONVENIO_TIPO_CONVENIO, tipoConvenio);
		query.setParameter("fechaActual", fechaActual);
		query.setParameter(Convenio.ENTIDAD_CONVENIO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Convenio.ENTIDAD_CONVENIO_ID_SERVICIO, idServicio);

		return (List<Convenio>) query.getResultList();
	}
	
	/**
	 * Método que consulta las jornadas activas de los centros 
	 * @param centros
	 * @param ejecutadas indica si se buscan las jornadas ya ejecutadas o sin ejecutar
	 * @return
	 */
	public List<Convenio> consultarJornadas(List<Long> centros, boolean ejecutadas) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT c FROM Convenio c ");
		jpqlQuery.append(" where c.tipoConvenio = :").append(Convenio.ENTIDAD_CONVENIO_TIPO_CONVENIO);
		if(ejecutadas)
			jpqlQuery.append(" and c.fechaInicioVigencia <= :").append(Convenio.ENTIDAD_CONVENIO_FECHA_INICIO_VIGENCIA);
		else
			jpqlQuery.append(" and c.fechaInicioVigencia > :").append(Convenio.ENTIDAD_CONVENIO_FECHA_INICIO_VIGENCIA);
		jpqlQuery.append(" and c.idCentro ").append(UtilConsultasSQL.clausulaInSQLSNumeros(centros));
		jpqlQuery.append(" and c.estadoRegistro =:").append(Convenio.ENTIDAD_CONVENIO_ESTADO_REGISTRO);
		
		Query query = mp.getEntityManager().createQuery(jpqlQuery.toString());
		query.setParameter(Convenio.ENTIDAD_CONVENIO_TIPO_CONVENIO, UtilDominios.TIPO_CONVENIO_JORNADA);
		query.setParameter(Convenio.ENTIDAD_CONVENIO_FECHA_INICIO_VIGENCIA, new Date());
		query.setParameter(Convenio.ENTIDAD_CONVENIO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return (List<Convenio>) query.getResultList();
	}
	
	/**
	 * Metodo que consulta los centros que tenga el usuario en seccion y por una fecha que se compara con la fecha
	 * de vigencia del convenio 
	 * @param centros
	 * @param fechaConsulta
	 * @return
	 */
	public List<Convenio> consultarConveniosPorCentro(List<String> centros, Date fechaConsulta, Long idPersona, Long idRol) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT DISTINCT (sc.convenio) FROM  RelacionadoConvenio rc,  SedeConvenio sc");
		jpqlQuery.append(" JOIN sc.sede s ");
		jpqlQuery.append(" JOIN s.centro c ");
		
		jpqlQuery.append(" WHERE s.centro.idCentro ").append(UtilConsultasSQL.clausulaInSQLSNumeros(centros));
		jpqlQuery.append(" AND sc.convenio.tipoConvenio = : ").append(Convenio.ENTIDAD_CONVENIO_TIPO_CONVENIO);
		jpqlQuery.append(" AND sc.convenio.estadoRegistro = : ").append(Convenio.ENTIDAD_CONVENIO_ESTADO_REGISTRO);
		jpqlQuery.append(" AND sc.estadoRegistro = : ").append(SedeConvenio.ENTIDAD_SEDE_CONVENIO_ESTADO_REGISTRO);
		jpqlQuery.append(" AND s.estadoRegistro = : ").append(Sede.ENTIDAD_SEDE_ESTADO_REGISTRO);
		jpqlQuery.append(" AND c.estadoRegistro = : ").append(Centro.ENTIDAD_CENTRO_ESTADO_REGISTRO);

		if( fechaConsulta != null ){
			jpqlQuery.append(" AND sc.convenio.fechaInicioVigencia < :").append(Convenio.ENTIDAD_CONVENIO_FECHA_INICIO_VIGENCIA);
			jpqlQuery.append(" AND sc.convenio.fechaFinVigencia > :").append(Convenio.ENTIDAD_CONVENIO_FECHA_FIN_VIGENCIA);
		}
		
		if(idPersona != null && idRol != null){
			jpqlQuery.append(" AND rc.relacionadoConvenioPK.idConvenio = sc.convenio.idConvenio ");
			jpqlQuery.append(" AND rc.estadoRegistro = : ").append(RelacionadoConvenio.ENTIDAD_RELACIONADO_CONVENIO_ESTADO_REGISTRO);
			jpqlQuery.append(" AND rc.relacionadoConvenioPK.idPersona = : ").append(Persona.ENTIDAD_PERSONA_PK);
			jpqlQuery.append(" AND rc.relacionadoConvenioPK.idRol = : ").append(Rol.ENTIDAD_ROL_PK);
		}


		jpqlQuery.append(" ORDER BY c.nombre ");
		
		Query query = mp.getEntityManager().createQuery(jpqlQuery.toString(), Convenio.class);
		query.setParameter(Convenio.ENTIDAD_CONVENIO_TIPO_CONVENIO, UtilDominios.TIPO_CONVENIO_CONVENIO);
		if( fechaConsulta != null ){
			query.setParameter(Convenio.ENTIDAD_CONVENIO_FECHA_INICIO_VIGENCIA, fechaConsulta);
			query.setParameter(Convenio.ENTIDAD_CONVENIO_FECHA_FIN_VIGENCIA, fechaConsulta);
		}
		
		if(idPersona != null && idRol != null){
			query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
			query.setParameter(Rol.ENTIDAD_ROL_PK,idRol);
			query.setParameter(RelacionadoConvenio.ENTIDAD_RELACIONADO_CONVENIO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		}
		query.setParameter(Convenio.ENTIDAD_CONVENIO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(SedeConvenio.ENTIDAD_SEDE_CONVENIO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Sede.ENTIDAD_SEDE_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Centro.ENTIDAD_CENTRO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return (List<Convenio>) query.getResultList();
	}

	/**
	 * Metodo que consulta los convenios por nombre y/o id convenio sin importar el estado
	 * de vigencia del convenio 
	 * @param centros
	 * @param fechaConsulta
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ConvenioDTO> consultarConveniosPorNombreCodigo(String nombreConvenio, Long idConvenio, List<Long> idCentros){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT distinct(c.id_convenio) as idConvenio, c.nombre as nombre, c.fecha_inicio_vigencia as fechaInicioVigencia, c.fecha_fin_vigencia as fechaFinVigencia from convenio c " );
		nativeQuery.append(" inner join SEDE_CONVENIO sc on sc.id_convenio=c.id_convenio ");
		nativeQuery.append(" inner join sede s on s.id_sede=sc.id_sede ");
		nativeQuery.append(" where SC.estado_registro=?1 "); 
		nativeQuery.append(" AND C.tipo_convenio=?3 ");
		if( idCentros != null && !idCentros.isEmpty() )
			nativeQuery.append(" AND sc.id_sede in (select se.id_sede  from sede se where se.estado_registro=?1 and se.id_centro ").append( UtilConsultasSQL.clausulaInSQLSNumeros(idCentros)).append(" )");
	
		if(nombreConvenio != null){
			nativeQuery.append(" AND C.nombre LIKE ?4 ");	
		}
		if(idConvenio != null){
	     	nativeQuery.append(" AND sc.id_convenio= ?2 ");
		}
		
		nativeQuery.append("  order by c.nombre ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(),ConvenioDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idConvenio);
		query.setParameter(3, UtilDominios.TIPO_CONVENIO_CONVENIO);
		query.setParameter(4,"%"+ nombreConvenio+"%");

		return query.getResultList();
	}
	
	/**
	 * Método que obtiene la cantidad de jornadas programadas que finalizaron entre las dos fechas recibidas
	 * y si se recibe el identificador de la persona retorna la cantidad de jornadas a las que asistio la persona como conciliador
	 * @param periodoDesde
	 * @param periodoHasta
	 * @param idPersona
	 * @return
	 */
	public BigDecimal obtenerJornadasProgramadas(Date periodoDesde, Date periodoHasta, Long idPersona) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select count(distinct co.id_convenio) from convenio co ");
		if(idPersona != null) {
			nativeQuery.append("inner join caso c on co.id_convenio = c.id_convenio and c.estado_registro = ?1 ");
			nativeQuery.append("inner join rol_persona_caso rpc on rpc.estado = ?5 ");
			nativeQuery.append("and rpc.estado_registro = ?1 and rpc.tipo_nombramiento = ?6 ");
			nativeQuery.append("and rpc.id_persona = ?7 and rpc.id_caso = c.id_caso ");
			nativeQuery.append("inner join tipo_de_servicio_rol t on rpc.id_rol = t.id_rol and t.tipo_servicio = ?8 and t.estado_registro = ?1 ");
		}
		nativeQuery.append("where tipo_convenio = ?2 ");
		nativeQuery.append("and CAST(co.fecha_fin_vigencia as Date) between ?3 and ?4 ");
		nativeQuery.append("and co.estado_registro = ?1");
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(),BigDecimal.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.TIPO_CONVENIO_JORNADA);
		query.setParameter(3, periodoDesde);
		query.setParameter(4, periodoHasta);
		if(idPersona != null) {
			query.setParameter(5, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
			query.setParameter(6, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
			query.setParameter(7, idPersona);
			query.setParameter(8, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		}
		return (BigDecimal) query.getSingleResult();
	}

	/**
	 * Obtiene los convenios vigentes de los centros especificados
	 * 
	 * @param centros
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Convenio> consultarConveniosVigentesPorCentro(List<String> centros) {
		StringBuilder nativeQuery = new StringBuilder();

		nativeQuery.append("SELECT distinct c.* FROM CONVENIO c ");
		
		nativeQuery.append(" inner join SEDE_CONVENIO sc ");
		nativeQuery.append(" on sc.id_convenio = c.id_convenio ");
		nativeQuery.append(" inner join sede s ");
		nativeQuery.append(" on s.id_sede = sc.id_sede ");
		
		nativeQuery.append(" WHERE GETDATE() BETWEEN c.fecha_inicio_vigencia and c.fecha_fin_vigencia ");
		nativeQuery.append("   AND c.estado_registro = ?1 ");
		nativeQuery.append("   AND sc.estado_registro = ?1 ");
		nativeQuery.append("   AND s.estado_registro = ?1 ");
		nativeQuery.append("   AND c.tipo_convenio = ?2 ");
		if (centros != null && !centros.isEmpty())
			nativeQuery.append("AND s.id_centro ").append(UtilConsultasSQL.clausulaInSQLSNumeros(centros));

		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), Convenio.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.TIPO_CONVENIO_CONVENIO);

		return query.getResultList();
	}
	
	
	/**
	 * Metodo que permite listar los convenios que han finalizado el contrato de
	 * convenio.
	 * 
	 * @param valor:
	 *            dia parametrizado en que se ejecuta la alerta.
	 * @param tipoPeriodicidad:
	 *            habil o calendario.
	 * @return List<InfoBasicaAlertasDTO>.
	 */
	public List<InfoBasicaAlertasDTO> listarConveniosFinalizacionContrato(Long valor, String tipoPeriodicidad) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT DISTINCT c.id_convenio AS idConvenio, ");
		nativeQuery.append(" c.nombre AS nombreConvenio, ");
		nativeQuery.append(" p.id_persona AS idPersona, ");
		nativeQuery.append(
				" CONCAT(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) AS nombrePersona ");

		nativeQuery.append(" FROM RELACIONADO_CONVENIO rc ");

		nativeQuery.append(" INNER JOIN CONVENIO c ");
		nativeQuery.append(" ON rc.id_convenio = c.id_convenio ");
		nativeQuery.append(" AND c.estado_registro = ?1 ");

		nativeQuery.append(" INNER JOIN CONTRATO_CONVENIO cc ");
		nativeQuery.append(" ON cc.id_convenio = rc.id_convenio ");
		nativeQuery.append(" AND cc.fecha_fin >= GETDATE() ");

		if (UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad)) {
			nativeQuery.append(" AND dbo.diasHabilesEntreDosFechas( GETDATE(), cc.fecha_fin) = ?3 ");
		} else {
			nativeQuery.append(" AND DATEDIFF(D, GETDATE(), cc.fecha_fin) = ?3 ");
		}

		nativeQuery.append(" AND cc.estado_registro = ?1 ");

		nativeQuery.append(" INNER JOIN ROL_PERSONA rp ");
		nativeQuery.append(" ON  rp.id_persona = rc.id_persona ");
		nativeQuery.append(" AND rp.id_rol = rc.id_rol ");
		nativeQuery.append(" AND rp.estado_registro = ?1 ");

		nativeQuery.append(" INNER JOIN ROL r ");
		nativeQuery.append(" ON r.id_rol = rp.id_rol ");
		nativeQuery.append(" AND r.nombre = ?2 ");
		nativeQuery.append(" AND r.estado_registro = 'ACT' ");

		nativeQuery.append(" INNER JOIN PERSONA p ");
		nativeQuery.append(" ON rp.id_persona = p.id_persona ");
		nativeQuery.append(" AND p.estado_registro = ?1 ");

		nativeQuery.append(" WHERE rc.estado_registro = ?1 ");

		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);

		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION);
		query.setParameter(3, valor != null ? valor : 30);

		return query.getResultList();

	}

	/**
	 * Metodo que permite listar los convenios que han finalizado el contrato de
	 * convenio y no tienen un nuevo contrato.
	 * 
	 * @return List<InfoBasicaAlertasDTO>.
	 */
	public List<InfoBasicaAlertasDTO> listarConveniosSinNuevoContrato(Long valor, String tipoPeriodicidad ) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT DISTINCT c.id_convenio AS idConvenio, ");
		nativeQuery.append(" c.nombre AS nombreConvenio, ");
		nativeQuery.append(" p.id_persona AS idPersona, ");
		nativeQuery.append(
				" CONCAT(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) AS nombrePersona ");

		nativeQuery.append(" FROM RELACIONADO_CONVENIO rc ");

		nativeQuery.append(" CROSS APPLY (SELECT TOP 1 cconv.* FROM CONTRATO_CONVENIO cconv ");
		nativeQuery.append(" WHERE  cconv.id_convenio = rc.id_convenio ");
		nativeQuery.append(" AND cconv.fecha_fin < GETDATE() ");
		
		if (UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad)) {
			nativeQuery.append(" AND dbo.diasHabilesEntreDosFechas( cconv.fecha_fin, GETDATE()) = ?3 ");
		} else {
			nativeQuery.append(" AND DATEDIFF(D, cconv.fecha_fin , GETDATE()) = ?3 ");
		}
		nativeQuery.append(" AND cconv.estado_registro = ?1 ");
		nativeQuery.append(" order by cconv.fecha_fin DESC) ccon ");

		nativeQuery.append(" INNER JOIN CONVENIO c ");
		nativeQuery.append(" ON rc.id_convenio = c.id_convenio ");
		nativeQuery.append(" AND c.estado_registro = ?1 ");

		nativeQuery.append(" INNER JOIN ROL_PERSONA rp ");
		nativeQuery.append(" ON  rp.id_persona = rc.id_persona ");
		nativeQuery.append(" AND rp.id_rol = rc.id_rol ");
		nativeQuery.append(" AND rp.estado_registro = ?1 ");

		nativeQuery.append(" INNER JOIN ROL r ");
		nativeQuery.append(" ON r.id_rol = rp.id_rol ");
		nativeQuery.append(" AND r.nombre = ?2 ");
		nativeQuery.append(" AND r.estado_registro = 'ACT' ");

		nativeQuery.append(" INNER JOIN PERSONA p ");
		nativeQuery.append(" ON rp.id_persona = p.id_persona ");
		nativeQuery.append(" AND p.estado_registro = ?1 ");

		nativeQuery.append(" WHERE rc.estado_registro = ?1 ");
		nativeQuery
				.append(" AND NOT EXISTS (SELECT * FROM CONTRATO_CONVENIO cc WHERE cc.id_convenio = ccon.id_convenio ");
		nativeQuery.append(" AND cc.fecha_fin > GETDATE() AND cc.estado_registro = ?1 )  ");

		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION);
		query.setParameter(3, valor);
		return query.getResultList();

	}
	// protected region metodos adicionales end

}
