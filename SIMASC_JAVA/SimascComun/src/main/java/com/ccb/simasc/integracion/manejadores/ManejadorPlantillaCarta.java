package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

import java.util.List;

// Escriba en esta seccion sus modificaciones

import javax.ejb.Stateless;
import javax.persistence.LockModeType;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.PlantillaCartaDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.PlantillaCarta;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad PlantillaCarta.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorPlantillaCarta extends ManejadorCrud<PlantillaCarta, Long> {

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end

	public ManejadorPlantillaCarta() {
		super(PlantillaCarta.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	@SuppressWarnings("unchecked")
	public PlantillaCarta consultarPlantillaPorNombre(long idCaso, String nombrePlantilla) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" select pc ");
		jpqlQuery.append(" from   PlantillaCarta pc, Caso c, Servicio s ");
		jpqlQuery.append(" where  c.idServicio = s.idServicio ");
		jpqlQuery.append(" and    (c.idServicio = pc.idServicio ");
		jpqlQuery.append(" OR 	    s.tipo = pc.tipoServicio) ");
		jpqlQuery.append(" and	   pc.nombre =:").append(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_NOMBRE);
		jpqlQuery.append(" and    c.idCaso =:").append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" and    pc.estadoRegistro =:").append(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_ESTADO_REGISTRO);
		jpqlQuery.append(" and    c.estadoRegistro =:").append(Caso.ENTIDAD_CASO_ESTADO_REGISTRO);
		jpqlQuery.append(" and    s.estadoRegistro =:").append(Servicio.ENTIDAD_SERVICIO_ESTADO_REGISTRO);
		jpqlQuery.append(" and    pc.servicio.tipo = :").append(Servicio.ENTIDAD_SERVICIO_TIPO);
		Query query = getEntityManager().createQuery(jpqlQuery.toString());
		query.setParameter(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_NOMBRE, nombrePlantilla);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Caso.ENTIDAD_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Servicio.ENTIDAD_SERVICIO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Servicio.ENTIDAD_SERVICIO_TIPO, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		List<PlantillaCarta> lista = query.getResultList();
		if (!lista.isEmpty())
			return lista.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<PlantillaCarta> consultarPlantillaCartaFiltros(PlantillaCartaDTO filtro, LockModeType lockMode) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" select pc ");
		jpqlQuery.append(" from   PlantillaCarta pc ");

		if (filtro.getIdConvenio() != null) {
			jpqlQuery.append(" where  pc.idConvenio =: ").append(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_ID_CONVENIO);
		} else {
			jpqlQuery.append(" where  pc.idPlantillaCarta =: ").append(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_PK);
		}

		if (filtro.getTipoServicio() != null)
			jpqlQuery.append(" and pc.tipoServicio =:").append(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_TIPO_SERVICIO);

		if (filtro.getPlantillaHtml() != null)
			jpqlQuery.append(" and pc.plantillaHtml =:").append(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_PLANTILLA_HTML);

		if (filtro.getIdServicio() != null)
			jpqlQuery.append(" and pc.idServicio =:").append(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_ID_SERVICIO);

		jpqlQuery.append(" and pc.estadoRegistro =:").append(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_ESTADO_REGISTRO);

		Query query = getEntityManager().createQuery(jpqlQuery.toString(), PlantillaCarta.class);
		if (lockMode != null)
			query.setLockMode(lockMode);

		if (filtro.getIdConvenio() != null) {
			query.setParameter(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_ID_CONVENIO, filtro.getIdConvenio());
		} else {
			query.setParameter(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_PK, filtro.getIdPlantillaCarta());
		}
		if (filtro.getTipoServicio() != null)
			query.setParameter(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_TIPO_SERVICIO, filtro.getTipoServicio());

		if (filtro.getPlantillaHtml() != null)
			query.setParameter(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_PLANTILLA_HTML, filtro.getPlantillaHtml());
		if (filtro.getIdServicio() != null)
			query.setParameter(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_ID_SERVICIO, filtro.getIdServicio());

		query.setParameter(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_ESTADO_REGISTRO, filtro.getEstadoRegistro());

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<PlantillaCarta> consultaPlantillaPorTipoServicio(String nombre){
		
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" select pc ");
		jpqlQuery.append(" from   PlantillaCarta pc ");
		jpqlQuery.append(" where pc.tipoServicio =:").append(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_TIPO_SERVICIO);
		
		Query query = getEntityManager().createQuery(jpqlQuery.toString(), PlantillaCarta.class);
		
		query.setParameter(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_TIPO_SERVICIO, nombre);
	
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<PlantillaCarta> consultaPlantillaPorNombre(String nombre){
		
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" select pc ");
		jpqlQuery.append(" from   PlantillaCarta pc ");
		jpqlQuery.append(" where pc.nombre =:").append(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_NOMBRE);
		
		Query query = getEntityManager().createQuery(jpqlQuery.toString(), PlantillaCarta.class);
		
		query.setParameter(PlantillaCarta.ENTIDAD_PLANTILLA_CARTA_NOMBRE, nombre);
	
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<PlantillaCarta> consultarPlantillasEquidad() {
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append(" SELECT p.* ");
		nativeQuery.append(" FROM PLANTILLA_CARTA p ");
		nativeQuery.append(" INNER JOIN DOMINIO d on p.nombre = d.codigo and d.dominio = ?1 ");
		nativeQuery.append(" WHERE tipo_servicio IN(?2, ?3) ");
		nativeQuery.append(" AND p.nombre not in (?4) ");
		nativeQuery.append(" AND p.estado_registro = ?5 ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), PlantillaCarta.class);
		query.setParameter(1, UtilDominios.DOMINIO_NOMBRE_PLANTILLA_CARTA);
		query.setParameter(2, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(3, UtilDominios.TIPO_SERVICIO_CONVIVENCIA);
		query.setParameter(4, UtilDominios.NOMBRE_PLANTILLA_CARTA_PRIMERA_AUDIENCIA);
		query.setParameter(5, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
    	return query.getResultList();
	}

	// protected region metodos adicionales end

}
