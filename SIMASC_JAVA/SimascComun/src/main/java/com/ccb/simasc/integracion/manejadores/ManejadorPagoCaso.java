package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.InfoReliquidacionPagoCaso;
import com.ccb.simasc.transversal.dto.ReliquidarPagoCasoDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.PagoCaso;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad PagoCaso.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorPagoCaso extends ManejadorCrud<PagoCaso, String> {

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	@PersistenceContext
	private transient EntityManager em;

	// protected region atributos end

	public ManejadorPagoCaso() {
		super(PagoCaso.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	@SuppressWarnings("unchecked")
	public List<PagoCaso> obtenerPagosCasosPorEstado(String estadoPago) {
		List<PagoCaso> pagoCaso = new ArrayList<>();
		String estadoPagoRegistro = PagoCaso.ENTIDAD_PAGO_CASO_ESTADO;

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT pc FROM PagoCaso pc");
		jpqlQuery.append(" WHERE pc.estado=:");
		jpqlQuery.append(estadoPagoRegistro);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(estadoPagoRegistro, estadoPago);

		pagoCaso = query.getResultList();
		return pagoCaso;
	}

	@SuppressWarnings("unchecked")
	public List<ReliquidarPagoCasoDTO> listarPagoCaso(long idCaso) {
		List<ReliquidarPagoCasoDTO> detallePago = new ArrayList<>();
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select  pc.id_caso AS idCaso, ");
		nativeQuery.append(" dpc.codigo_item AS codigoItem, ");
		nativeQuery.append(" dpc.numero_recibo AS numeroRecibo, ");
		nativeQuery.append(" dpc.servicio_caja as codigoServicio, ");
		nativeQuery.append(
				" (select nombre from dominio where codigo=dpc.servicio_caja and dominio= ?1 ) as detallePago, ");
		nativeQuery.append(" dpc.valor AS valor");
		nativeQuery.append(" from	(select top(1)c.id_caso, ");
		nativeQuery.append(" p.numero_recibo ");
		nativeQuery.append(" from caso c ");
		nativeQuery.append(" inner join PAGO_CASO p on p.id_caso=c.id_caso ");
		nativeQuery.append(" where  c.id_caso = ?2");
		nativeQuery.append(" order by p.fecha_pago) pc ");
		nativeQuery.append(" inner join DETALLE_PAGO_CASO dpc on pc.numero_recibo=dpc.numero_recibo ");

		Query query = em.createNativeQuery(nativeQuery.toString(), ReliquidarPagoCasoDTO.class);
		query.setParameter(1, UtilDominios.DOMINIO_TIPO_SERVICIO_CAJA);
		query.setParameter(2, idCaso);

		detallePago = query.getResultList();
		return detallePago;
	}

	@SuppressWarnings("unchecked")
	public List<InfoReliquidacionPagoCaso> listarReliquidacionPagoCaso(long idCaso) {
		List<InfoReliquidacionPagoCaso> reliquidacion = new ArrayList<>();
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select	r.id_reliquidacion as idReliquidacion, ");
		nativeQuery.append(" r.id_caso as idCaso, ");
		nativeQuery.append(" r.fecha as fechaReliquidacion, ");
		nativeQuery.append(" r.orden_de_pago as numeroOrden, ");
		nativeQuery.append(" isnull(ho.valor,0) as valorHonorario, ");
		nativeQuery.append(" isnull(iho.valor,0) as ivaHonorario, ");
		nativeQuery.append(" isnull(ga.valor,0) as valorGastoAdministrativo, ");
		nativeQuery.append(" isnull(iga.valor,0) as ivaGastoAdministrativo, ");
		nativeQuery.append(" r.valor as totalPagar, ");
		nativeQuery.append(" case r.pagada when 0 then 'No' when 1 then 'Si' end as ordenPagada, ");
		nativeQuery.append(" r.fecha_pago as fechaPago ");
		nativeQuery.append(" from RELIQUIDACION r ");
		nativeQuery.append(" cross apply ( select	id_reliquidacion, valor ");
		nativeQuery.append(" from DETALLE_RELIQUIDACION dr ");
		nativeQuery.append(" where dr.id_reliquidacion=r.id_reliquidacion ");
		nativeQuery.append(" and dr.codigo_item = 2) ho ");
		nativeQuery.append(" cross apply ( select	id_reliquidacion, valor ");
		nativeQuery.append(" from DETALLE_RELIQUIDACION dr ");
		nativeQuery.append(" where dr.id_reliquidacion=r.id_reliquidacion ");
		nativeQuery.append(" and dr.codigo_item = 3) iho ");
		nativeQuery.append(" cross apply ( select	id_reliquidacion, valor ");
		nativeQuery.append(" from DETALLE_RELIQUIDACION dr ");
		nativeQuery.append(" where dr.id_reliquidacion=r.id_reliquidacion ");
		nativeQuery.append(" and dr.codigo_item = 4) ga ");
		nativeQuery.append(" cross apply ( select	id_reliquidacion, valor ");
		nativeQuery.append(" from DETALLE_RELIQUIDACION dr ");
		nativeQuery.append(" where dr.id_reliquidacion=r.id_reliquidacion ");
		nativeQuery.append(" and dr.codigo_item = 5) iga ");
		nativeQuery.append(" where r.id_caso= ?6 ");
		Query query = em.createNativeQuery(nativeQuery.toString(), InfoReliquidacionPagoCaso.class);
		query.setParameter(6, idCaso);
		reliquidacion = query.getResultList();
		return reliquidacion;
	}

	@SuppressWarnings("unchecked")
	public List<PagoCaso> obtenerPagosCasosPorfiltros(Date fecha, String tipoDocumento, String numeroDocumento) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT pc FROM PagoCaso pc");
		jpqlQuery.append(" WHERE pc.fechaPago > : ").append(PagoCaso.ENTIDAD_PAGO_CASO_FECHA_PAGO);
		jpqlQuery.append(" and pc.numeroDeDocumento = : ").append(PagoCaso.ENTIDAD_PAGO_CASO_NUMERO_DE_DOCUMENTO);
		jpqlQuery.append(" and pc.estadoRegistro = : ").append(PagoCaso.ENTIDAD_PAGO_CASO_ESTADO_REGISTRO);
		if (tipoDocumento !=null && !tipoDocumento.isEmpty() )
			jpqlQuery.append(" and pc.tipoDeDocumento = : ").append(PagoCaso.ENTIDAD_PAGO_CASO_TIPO_DE_DOCUMENTO);
        
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(PagoCaso.ENTIDAD_PAGO_CASO_FECHA_PAGO, fecha);

		query.setParameter(PagoCaso.ENTIDAD_PAGO_CASO_NUMERO_DE_DOCUMENTO, numeroDocumento);
		query.setParameter(PagoCaso.ENTIDAD_PAGO_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if (tipoDocumento !=null &&  !tipoDocumento.isEmpty())
			query.setParameter(PagoCaso.ENTIDAD_PAGO_CASO_TIPO_DE_DOCUMENTO, tipoDocumento);
		return query.getResultList();
	}
	
	/**
	 * Consulta los pago caso de un caso por idCaso
	 * @param idCaso
	 * @return List<PagoCaso> 
	 */
	public List<PagoCaso> obtenerUltimoPagoCaso( Long idCaso ){
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT pc FROM PagoCaso pc ");
		jpqlQuery.append(" WHERE pc.idCaso = : ").append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND pc.estadoRegistro = : ").append(PagoCaso.ENTIDAD_PAGO_CASO_ESTADO_REGISTRO);
		jpqlQuery.append(" ORDER BY pc.fechaPago DESC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(PagoCaso.ENTIDAD_PAGO_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return query.getResultList();
	}
	
	// protected region metodos adicionales end

}
