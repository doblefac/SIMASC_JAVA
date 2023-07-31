package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

// Escriba en esta sección sus modificaciones

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.formularios.TipoDocumentalDTO;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.DominioPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad Dominio.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorDominio extends ManejadorCrud<Dominio, DominioPK> {

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	private static final String JPQL_CARGUE_DOMINIOS = "SELECT DISTINCT(d) FROM Dominio d LEFT JOIN FETCH d.clasificadorDominioListAgrupador LEFT JOIN FETCH d.clasificadorDominioListAgrupado ORDER BY d.dominio, d.nombre";
	// protected region atributos end

	public ManejadorDominio() {
		super(Dominio.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	@SuppressWarnings("unchecked")
	public List<Dominio> consultarDominiosParaCargue() {

		Query query = mp.createQuery(JPQL_CARGUE_DOMINIOS);

		return query.getResultList();
	}

	/**
	 * Método para verificar si el nombre de la plantilla ya existe y sea diferetene
	 * a la plantilla que se este modificando
	 * 
	 * @param nombre
	 * @param codigo
	 * @return
	 */
	public boolean existeNombrePlantilla(String nombre, String codigo) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select * from dominio where dominio = ?1 ");
		nativeQuery.append("and upper(replace(nombre, ' ', '' )) = upper(replace(?2, ' ', '')) ");
		if (codigo != null)
			nativeQuery.append("and codigo <> ?3");

		Query query = getEntityManager().createNativeQuery(nativeQuery.toString());
		query.setParameter(1, UtilDominios.DOMINIO_NOMBRE_PLANTILLA_CARTA);
		query.setParameter(2, nombre);
		if (codigo != null)
			query.setParameter(3, codigo);

		return !query.getResultList().isEmpty();
	}

	@SuppressWarnings("unchecked")
	public List<String> consultarCodigosDominio(String dominio) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select codigo from dominio where dominio = ?1 ");

		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), String.class);
		query.setParameter(1, dominio);

		return query.getResultList();
	}

	public String consultarNombreRol(String nombreRol) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select nombre from dominio ");
		nativeQuery.append("where codigo = ?1 ");
		nativeQuery.append("and dominio = ?2 ");

		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), String.class);
		query.setParameter(1, nombreRol);
		query.setParameter(2, UtilDominios.DOMINIO_ROL_PERSONA);

		return query.getSingleResult().toString();
	}
	// protected region metodos adicionales end

	@SuppressWarnings("unchecked")
	public List<Dominio> obtenerDominioRol(Long idRol) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select d.* ");
		nativeQuery.append(" from DOMINIO d , rol r ");
		nativeQuery.append(" where d.codigo  = r.nombre ");
		nativeQuery.append(" and r.id_rol = ?1 ");
		nativeQuery.append(" and d.dominio = ?2 ");

		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), Dominio.class);
		query.setParameter(1, idRol);
		query.setParameter(2, UtilDominios.DOMINIO_ROL_PERSONA);

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Dominio> obtenerDominioMotivosInactivacionArbitro() {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select d.* ");
		nativeQuery.append(" from DOMINIO d ");
		nativeQuery.append(" where d.dominio = ?1 ");

		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), Dominio.class);
		query.setParameter(1, UtilDominios.DOMINIO_MOTIVOS_INACTIVACION_ARBITRO);

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Dominio> obtenerDominioPorClasificador(String clasificador) {

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select d.* from DOMINIO d ");
		nativeQuery.append(
				" inner join CLASIFICADOR_DOMINIO cd on cd.codigo_clasificado = d.codigo and cd.dominio_clasificado = d.dominio ");
		nativeQuery.append("  where dominio_clasificador = ?1 ");
		nativeQuery.append("  and cd.estado_registro = ?2 ");

		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), Dominio.class);
		query.setParameter(1, clasificador);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Dominio> obtenerDominioPorServicio(Long idServicio, String dominio) {

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select d.* from DOMINIO d ");
		nativeQuery.append(" INNER JOIN TIPO_DOCUMENTAL td ON td.codigo = d.codigo  ");
		nativeQuery.append("  where td.id_servicio  = ?1 ");
		nativeQuery.append("  and td.es_virtual = 1 ");
		nativeQuery.append("  and d.dominio  = ?2 ");
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), Dominio.class);
		query.setParameter(1, idServicio);
		query.setParameter(2, dominio);

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<TipoDocumentalDTO> tipoDocumentalCaso(Long idCaso) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT d.codigo, d.dominio, d.nombre, d.descripcion,td.es_virtual FROM CASO c, TIPO_DOCUMENTAL td, DOMINIO d  ");
		jpqlQuery.append("WHERE c.id_servicio = td.id_servicio AND d.codigo = td.codigo ");
		jpqlQuery.append("AND c.id_caso =?1 ");
		jpqlQuery.append(" AND d.dominio = ?2");
		Query query = getEntityManager().createNativeQuery(jpqlQuery.toString(), TipoDocumentalDTO.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, UtilDominios.DOMINIO_TIPO_DOCUMENTO_DIG);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Dominio> tipoSorteoPorServicio(Long idServicio) {
		
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT d.* ");
		jpqlQuery.append(" FROM parametro_servicio_sorteo pss ");
		jpqlQuery.append(" INNER JOIN dominio d ON pss.id_servicio_sorteo = d.codigo ");
		jpqlQuery.append(" WHERE d.dominio = ?1 ");
		jpqlQuery.append(" AND pss.estado_registro = ?2 ");
		jpqlQuery.append(" AND id_servicio = ?3 ");
		
		Query query = getEntityManager().createNativeQuery(jpqlQuery.toString(), Dominio.class);
		query.setParameter(1, UtilDominios.DOMINIO_TIPO_DE_SORTEO);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(3, idServicio);
		return query.getResultList();
	}
	
	public String consultarTipoSorteo(String codigo) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select nombre from dominio ");
		nativeQuery.append("where codigo = ?1 ");
		nativeQuery.append("and dominio = ?2 ");

		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), String.class);
		query.setParameter(1, codigo);
		query.setParameter(2, UtilDominios.DOMINIO_TIPO_DE_SORTEO);

		return query.getSingleResult().toString();
	}

}
