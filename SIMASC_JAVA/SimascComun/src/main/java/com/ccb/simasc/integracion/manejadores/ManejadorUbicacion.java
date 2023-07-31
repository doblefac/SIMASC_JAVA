package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.UbicacionDTO;
import com.ccb.simasc.transversal.dto.UbicacionDetalladaDTO;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Ubicacion;
import com.ccb.simasc.transversal.entidades.UbicacionRolPersonaCaso;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Ubicacion.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorUbicacion extends ManejadorCrud<Ubicacion,Long>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@PersistenceContext
	private EntityManager em;

	// protected region atributos end
    
    public ManejadorUbicacion() {
        super(Ubicacion.class);
    }    
    
    // protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
    
    @SuppressWarnings("unchecked")
	private List<Ubicacion> consultarUbicacionesPorPersona(Long idPersona, Boolean incluirInactivos) {
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT t FROM Ubicacion t ");
		jpqlQuery.append(" WHERE ");
		jpqlQuery.append(" t.persona.idPersona=:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);
		
		if(!incluirInactivos) {
			jpqlQuery.append(" AND t.estadoRegistro=:");
			jpqlQuery.append("estadoRegistro");
		}
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		
		if(!incluirInactivos) {
			query.setParameter("estadoRegistro", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		}
		
		return (List<Ubicacion>) query.getResultList();
    }
    
    /**
     * Consulta las Ubicaciones de una Persona sin asociación a un Caso,
     * teniendo en cuenta como criterio el tipo de Ubicación
     * 
     * @param idPersona
     * @param tipo
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Ubicacion> consultarUbicacionesPersonaSinAsociacionCaso(Long idPersona, String tipo) {
		// Build Query
		StringBuilder jpqlQuery = new StringBuilder();		
		jpqlQuery.append("SELECT u FROM Ubicacion u ");
		
		jpqlQuery.append(" WHERE u.idUbicacion NOT IN (");
		jpqlQuery.append(" SELECT DISTINCT urp.ubicacionRolPersonaCasoPK.idUbicacion FROM UbicacionRolPersonaCaso urp ");
		jpqlQuery.append(" WHERE urp.ubicacionRolPersonaCasoPK.idPersona = :");
		jpqlQuery.append(UtilConsultasSQL.obtenerNombreParametro(UbicacionRolPersonaCaso.ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_PERSONA));
		jpqlQuery.append(" )");
		
		jpqlQuery.append(" AND u.idUbicacion NOT IN (");
		jpqlQuery.append(" SELECT DISTINCT ups.ubicacionPersonaSolicitudPK.idUbicacion FROM UbicacionPersonaSolicitud ups ");
		jpqlQuery.append(" WHERE ups.ubicacionPersonaSolicitudPK.idPersona = :");
		jpqlQuery.append(UtilConsultasSQL.obtenerNombreParametro(UbicacionRolPersonaCaso.ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_PERSONA));
		jpqlQuery.append(" )");
		
		jpqlQuery.append(" AND u.idPersona= :");
		jpqlQuery.append(Ubicacion.ENTIDAD_UBICACION_ID_PERSONA);
		
		if(tipo != null && !tipo.isEmpty()) {
			jpqlQuery.append(" AND u.tipo= :");
			jpqlQuery.append(Ubicacion.ENTIDAD_UBICACION_TIPO);
		}
		
		jpqlQuery.append(" ORDER BY u.idUbicacion ");
		
		// Execute Query		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(UtilConsultasSQL.obtenerNombreParametro(UbicacionRolPersonaCaso.ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_PERSONA), idPersona);
		query.setParameter(Ubicacion.ENTIDAD_UBICACION_ID_PERSONA, idPersona);
		query.setParameter(Ubicacion.ENTIDAD_UBICACION_TIPO, tipo);
		
		return query.getResultList();
	}
    
	/**
	 * Metodo para persistir la ubicacion
	 * 
	 * @param Ubicacion
	 * @return
	 */
	public Ubicacion crearUbicacion(Ubicacion ubicacion) {
		return (Ubicacion) mp.updateObject(ubicacion);
	}

	/**
	 * Busca las ubicaciones de una persona
	 * 
	 * @param idPersona
	 * @return
	 */
	public List<Ubicacion> consultarPorIdPersona(Long idPersona) {
		return consultarUbicacionesPorPersona(idPersona, false);
	}
	
	/**
	 * Busca las ubicaciones de una persona teniendo en cuenta su estado
	 * 
	 * @param idPersona
	 * @return
	 */
	public List<Ubicacion> consultarPorIdPersona(Long idPersona, Boolean incluirInactivos) {
		return consultarUbicacionesPorPersona(idPersona, incluirInactivos);
	}
	
	 /**
     * ADM-C-021
     * Crea los ubicaciones  si no viene definido el identificador, de 
	 * lo contrario lo actualiza.
     * @param ubicacion
     */
    public void creaOActualizaUbicacion(Ubicacion ubicacion){
    	if(ubicacion!=null){
    		if(ubicacion.getIdUbicacion()!=null){
    			this.actualizar(ubicacion);
    		}else{
    			this.crear(ubicacion);
    		}
    	}    	
    }
    
    /**
	 * ADM-C-021
	 * Crea o actualiza los ubicacions de la lista:
	 * Crea los ubicacions si no viene definido el identificador, de 
	 * lo contrario lo actualiza.
	 * @param lista
	 */
    public void crearOActualizarListaUbicaciones(List<Ubicacion> lista) {
    	for(Ubicacion ubicacion : lista){
    		this.creaOActualizaUbicacion(ubicacion);
        }    	
    }
    
	/**
	 * ADM-C-021 Crea o actualiza los ubicaciones que se pasan como parámetro. El
	 * resto de ubicaciones que tenga la persona y que no vienen en la lista se
	 * inactivan.
	 * 
	 * @param ubicacionsDTO
	 * @param idPersona
	 */
	public void crearOActualizarUbicacionesPersona(List<UbicacionDTO> ubicacionesDTO, Long idPersona) {
		List<Ubicacion> ubicaciones = new UbicacionDTO().transformarColeccionDTOAColeccionEntidades(ubicacionesDTO);
		for(Ubicacion ubicacion: ubicaciones){
			if(ubicacion.getIdPersona()==null || ubicacion.getIdUbicacion()==null){
				ubicacion.setIdPersona(idPersona);
			}
		}
		
		this.crearOActualizarListaUbicaciones(ubicaciones);		
		
		List<Long> idUbicacions = new ArrayList<>();
		for (Ubicacion ubicacion : ubicaciones) {			
			idUbicacions.add(ubicacion.getIdUbicacion());
		}

		if (!idUbicacions.isEmpty()) {
			this.inactivarOtrasUbicaciones(idUbicacions, idPersona);
		}
	}
	

	/**
	 * Inactiva todos los telefonos de la persona que no se pasan como parametro
	 * 
	 * @param identificadoresTelefonos
	 */
	public void inactivarOtrasUbicaciones(List<Long> idsUbicaciones, Long idPersona) {

		StringBuilder nativeQuery = new StringBuilder("UPDATE UBICACION SET estado_registro= ?2 ");
		nativeQuery.append(" WHERE ");
		if(idsUbicaciones!=null && !idsUbicaciones.isEmpty()){
			nativeQuery.append(" id_ubicacion NOT ")
			.append(UtilConsultasSQL.clausulaInSQLSNumeros(idsUbicaciones));
			nativeQuery.append(" AND ");
		}
		
		nativeQuery.append(" id_ubicacion NOT IN (SELECT id_ubicacion FROM UBICACION_ROL_PERSONA_CASO WHERE id_persona = ?1) ");
		nativeQuery.append(" AND ");
		nativeQuery.append(" id_ubicacion NOT IN (SELECT id_ubicacion FROM UBICACION_PERSONA_SOLICITUD WHERE id_persona = ?1) ");
		nativeQuery.append(" AND ");
		
		nativeQuery.append("id_persona = ?1");
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString());
		query.setParameter(1, idPersona);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_INACTIVO);

		query.executeUpdate();

	}
	
	@SuppressWarnings("unchecked")
	public List<UbicacionDetalladaDTO> ubicacionDetallaByIdpersona(Long idPersona){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select	u.id_persona as idPersona, ");
		nativeQuery.append(" u.direccion as direccion, "); 
		nativeQuery.append(" zona.pais as pais, "); 
		nativeQuery.append(" zona.ciudad as ciudad, ");
		nativeQuery.append(" zona.idPais as idPais, ");
		nativeQuery.append(" zona.id_zona_geografica as idCiudad, ");
		nativeQuery.append(" u.id_ubicacion as idUbicacion ");
		nativeQuery.append(" from ubicacion u ");  
		nativeQuery.append(" left join (select zgc.id_zona_geografica,zgp.nombre as pais , zgd.nombre as departamento, ");
		nativeQuery.append(" zgc.nombre as ciudad,zgp.id_zona_geografica as idPais ");
		nativeQuery.append(" from  zona_geografica zgp ");  
		nativeQuery.append(" inner join ZONA_GEOGRAFICA zgd on zgd.id_zona_geografica_padre=zgp.id_zona_geografica "); 
		nativeQuery.append(" inner join ZONA_GEOGRAFICA zgc on zgc.id_zona_geografica_padre=zgd.id_zona_geografica) "); 
		nativeQuery.append(" zona on zona.id_zona_geografica=u.id_zona_geografica "); 
		nativeQuery.append(" where u.id_persona = ").append(idPersona.toString());
		nativeQuery.append(" and u.estado_registro = ?1 ");
		nativeQuery.append(" and u.tipo = ?2 ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(),UbicacionDetalladaDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.TIPO_UBICACION_PRINCIPAL);
		
		return query.getResultList();
	}
	
	
	public UbicacionDTO consultarPrimeraUbicacionPersona(Long idPersona){
		
		UbicacionDTO ubicacionPersona;
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append(" select top 1 ");
		nativeQuery.append(" id_ubicacion as idUbicacion, ");
		nativeQuery.append(" direccion, latitud, longitud, tipo, ");
		nativeQuery.append(" id_zona_geografica AS idZonaGeografica, ");
		nativeQuery.append(" id_persona AS idPersona ");
		nativeQuery.append(" from ubicacion ");
		nativeQuery.append("  where id_persona=?1 ");
		nativeQuery.append(" AND estado_registro = ?2 ");
		nativeQuery.append(" AND tipo = ?3 ");
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), UbicacionDTO.class);
		query.setParameter(1, idPersona);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(3, UtilDominios.TIPO_UBICACION_PRINCIPAL);
		
		try{
			ubicacionPersona =  (UbicacionDTO) query.getSingleResult();
		} catch (NoResultException e) {
			ubicacionPersona = new UbicacionDTO();
		}
		return ubicacionPersona;
		
	}
	
	/**
	 * Método que obtiene las direcciones de la persona en un caso incluyendo la principal
	 * @param idCaso
	 * @param idPersona
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Ubicacion> consultarDireccionesCaso(Long idCaso, Long idPersona) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select distinct u.* from ubicacion u ");
		nativeQuery.append("left join UBICACION_ROL_PERSONA_CASO ur on u.id_ubicacion = ur.id_ubicacion ");
		nativeQuery.append("and ur.id_caso = ?1 and ur.estado_registro = ?2 ");
		nativeQuery.append("where (u.tipo = ?3 or ur.id_ubicacion is not null) ");
		nativeQuery.append("and u.id_persona = ?4 ");
		nativeQuery.append("and u.estado_registro = ?2 ");
		nativeQuery.append("order by u.tipo ");
		
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), Ubicacion.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(3, UtilDominios.TIPO_UBICACION_PRINCIPAL);
		query.setParameter(4, idPersona);
		
		return query.getResultList();
	}
	
	public Boolean tieneDirecciones(Long idPersona) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select 1 from ubicacion where estado_registro = ?1 and id_persona = ?2");
		
		Query query = getEntityManager().createNativeQuery(sql.toString(), Long.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idPersona);
		
		return !query.getResultList().isEmpty();
	}
	// protected region metodos adicionales end
        
}

