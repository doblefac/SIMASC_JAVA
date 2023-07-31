package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

// Escriba en esta seccion sus modificaciones

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.DesarrolloProfesional;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad DesarrolloProfesional.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorDesarrolloProfesional extends ManejadorCrud<DesarrolloProfesional, Long> {

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end

	public ManejadorDesarrolloProfesional() {
		super(DesarrolloProfesional.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/** CON-C-004
	 * Método que realiza la consulta de desarrollo profesional de acuerdo a la persona, el tipo de desarrollo y 
	 * fecha final mayor a la que se mande por parametro 
	 * @param idPersona
	 * @param tipoDesarrollo
	 * @return List<DesarrolloProfesional>
	 */
	@SuppressWarnings("unchecked")
	public List<DesarrolloProfesional> consultarDesarrolloProfesional(Long idPersona, String tipoDesarrollo, Date periodoDesde, Date periodoHasta, List<String> materiasCurso, String tipoCurso) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT dp FROM DesarrolloProfesional dp");
		jpqlQuery.append(" WHERE  ");
		jpqlQuery.append(" dp.estadoRegistro = :")
		.append(DesarrolloProfesional.ENTIDAD_DESARROLLO_PROFESIONAL_ESTADO_REGISTRO);
		if(materiasCurso!=null){
			jpqlQuery.append(" and dp.materiaCurso IN : ").append(DesarrolloProfesional.ENTIDAD_DESARROLLO_PROFESIONAL_MATERIA_CURSO);
		}
		if(tipoCurso!=null){
			jpqlQuery.append(" and dp.tipoCurso = :").append(DesarrolloProfesional.ENTIDAD_DESARROLLO_PROFESIONAL_TIPO_CURSO);
		}
		if(idPersona!=null){
		jpqlQuery.append(" and dp.persona.estadoRegistro = :")
				.append(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO);
		jpqlQuery.append(" AND dp.persona.idPersona = :").append(Persona.ENTIDAD_PERSONA_PK);
		}
		if( tipoDesarrollo !=null )
			jpqlQuery.append(" AND dp.tipoDesarrolloProfesional = :")
				.append(DesarrolloProfesional.ENTIDAD_DESARROLLO_PROFESIONAL_TIPO_DESARROLLO_PROFESIONAL);
		if( periodoHasta != null && periodoDesde != null )
			jpqlQuery.append(" AND dp.fechaFinal BETWEEN :").append(DesarrolloProfesional.ENTIDAD_DESARROLLO_PROFESIONAL_FECHA_INICIAL)
					 .append(" AND :").append(DesarrolloProfesional.ENTIDAD_DESARROLLO_PROFESIONAL_FECHA_FINAL);
		else if( periodoHasta != null)
			jpqlQuery.append(" AND dp.fechaFinal >= :").append(DesarrolloProfesional.ENTIDAD_DESARROLLO_PROFESIONAL_FECHA_FINAL);
		
		jpqlQuery.append(" ORDER BY dp.fechaFinal DESC");
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(DesarrolloProfesional.ENTIDAD_DESARROLLO_PROFESIONAL_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if(materiasCurso!=null){
			query.setParameter(DesarrolloProfesional.ENTIDAD_DESARROLLO_PROFESIONAL_MATERIA_CURSO, materiasCurso);
		}
		if(tipoCurso!=null){
			query.setParameter(DesarrolloProfesional.ENTIDAD_DESARROLLO_PROFESIONAL_TIPO_CURSO, tipoCurso);			
		}
		if(idPersona!=null){
			query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		}
		if( tipoDesarrollo !=null )
			query.setParameter(DesarrolloProfesional.ENTIDAD_DESARROLLO_PROFESIONAL_TIPO_DESARROLLO_PROFESIONAL,
				tipoDesarrollo);
		if( periodoHasta != null && periodoDesde != null ) {
			query.setParameter(DesarrolloProfesional.ENTIDAD_DESARROLLO_PROFESIONAL_FECHA_INICIAL, UtilOperaciones.obtenerFechaComienzoDelDia(periodoDesde));
			query.setParameter(DesarrolloProfesional.ENTIDAD_DESARROLLO_PROFESIONAL_FECHA_FINAL, UtilOperaciones.obtenerFechaComienzoDelDia(periodoHasta));
		} else if( periodoHasta != null )
			query.setParameter(DesarrolloProfesional.ENTIDAD_DESARROLLO_PROFESIONAL_FECHA_FINAL, UtilOperaciones.obtenerFechaComienzoDelDia(periodoHasta));
		
		try {
			return (List<DesarrolloProfesional>) query.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()),
					e);
		}
	}
	// protected region metodos adicionales end

}
