package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.ReporteCorreosDevueltosDTO;
import com.ccb.simasc.transversal.entidades.Llamada;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Llamada.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorLlamada extends ManejadorCrud<Llamada,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
	private transient EntityManager em;
	// protected region atributos end
    
    public ManejadorLlamada() {
        super(Llamada.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
	/**
	 * Persiste el registro de una llamada
	 * 
	 * @param llamada
	 * @return Llamada
	 */
	public Llamada crearLlamada(Llamada llamada) {
		return (Llamada) mp.updateObject(llamada);
	}
	
	/**
	 * Metodo que consulta los registros de llamadas activas con la opcion de
	 * realizar filtro por idCaso, fechaInicial y fechaFinal.
	 * 
	 * @param idCaso
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return List<Llamada>
	 */
	@SuppressWarnings("unchecked")
	public List<Llamada> consultarLlamadas(Long idCaso, Date fechaInicial, Date fechaFinal) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT ll FROM Llamada ll ");
		jpqlQuery.append(" WHERE ll.estadoRegistro =: ");
		jpqlQuery.append(Llamada.ENTIDAD_LLAMADA_ESTADO_REGISTRO);

		if (idCaso != null) {
			jpqlQuery.append(" AND ll.idCaso =: ");
			jpqlQuery.append(Llamada.ENTIDAD_LLAMADA_ID_CASO);
		}

		if (fechaInicial != null && fechaFinal != null)
			jpqlQuery.append(" AND ll.fecha BETWEEN :fechaInicial AND :fechaFinal ");
		
		jpqlQuery.append(" ORDER BY ll.fecha DESC ");

		Query query = em.createQuery(jpqlQuery.toString());
		query.setParameter(Llamada.ENTIDAD_LLAMADA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		if (idCaso != null)
			query.setParameter(Llamada.ENTIDAD_LLAMADA_ID_CASO, idCaso);

		if (fechaInicial != null && fechaFinal != null) {
			query.setParameter("fechaInicial", fechaInicial);
			query.setParameter("fechaFinal", fechaFinal);
		}
		return query.getResultList();
	}
	
	public List<ReporteCorreosDevueltosDTO> reporteCorreosDevueltos(Long idCaso, Date fechaInicial, Date FechaFinal){
		int contador = 0;
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select crp.id_caso_receptor as casoReceptor, c.nombre as  nombreCaso, s.nombre as tipoCaso,"); 
		nativeQuery.append(" concat(p.primer_nombre_o_razon_social, ' ', isnull(p.segundo_nombre, ''), ' ', p.primer_apellido, ' ', isnull(p.segundo_apellido, '') ) as parte, ");
		nativeQuery.append(" l.fecha, t.numero as telefono, l.contactado, l.observaciones, l.confirmacion_asistencia as confirmacionAsistencia ");
		nativeQuery.append(" from acuse a ");
		nativeQuery.append(" inner join CORREO_ROL_PERSONA_CASO crp ");
		nativeQuery.append(" on a.id_correo_rol_persona_caso = crp.id_correo_rol_persona_caso ");
		nativeQuery.append(" inner join caso c ");
		nativeQuery.append(" on crp.id_caso_receptor = c.id_caso ");
		nativeQuery.append(" inner join servicio s ");
		nativeQuery.append(" on s.id_servicio = c.id_servicio ");
		nativeQuery.append(" inner join PERSONA p  ");
		nativeQuery.append(" on crp.id_persona_receptor = p.id_persona  ");
		nativeQuery.append(" left join LLAMADA l ");
		nativeQuery.append(" on l.id_correo_rol_persona_caso = crp.id_correo_rol_persona_caso   ");
		nativeQuery.append(" left join telefono t ");
		nativeQuery.append(" on l.id_telefono = t.id_telefono ");
		nativeQuery.append(" where a.tipo = 'FER'  ");      
		
		if(idCaso != null){
			contador++;
			nativeQuery.append(" and c.id_caso= ?" +contador);   
		}
		if(fechaInicial != null){
			contador++;
			nativeQuery.append(" and crp.fecha_envio >= ?"+contador);
		}
		if(FechaFinal != null){
			contador++;
			nativeQuery.append(" and crp.fecha_envio <= ?"+contador);
		}   
		int contadorParametros = 0;
		Query query = em.createNativeQuery(nativeQuery.toString());
		if(idCaso != null){
			contadorParametros++;
			query.setParameter(contadorParametros, idCaso);
		}
		if(fechaInicial != null){
			contadorParametros++;
			query.setParameter(contadorParametros, fechaInicial);
		}
		if(FechaFinal != null){
			contadorParametros++;
			query.setParameter(contadorParametros, FechaFinal);
		}
		List<ReporteCorreosDevueltosDTO> reporte = query.getResultList();
		return reporte;                                                                          
	}
    // protected region metodos adicionales end
        
}

