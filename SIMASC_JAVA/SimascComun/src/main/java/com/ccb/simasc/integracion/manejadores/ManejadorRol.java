package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.RolDTO;
import com.ccb.simasc.transversal.entidades.ParametroServicioSorteo;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Rol.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorRol extends ManejadorCrud<Rol,Long>{

	// protected region atributos on begin
 	// Escriba en esta sección sus modificaciones
	// Atributos

    @PersistenceContext
    private transient EntityManager em; 
    // protected region atributos end
    
    public ManejadorRol() {
        super(Rol.class);
    }    
    
    // protected region metodos adicionales on begin
  	// Escriba en esta sección sus modificaciones
    /**
     * Consulta un rol por nombre
     * @param nombre
     * @return
     */
    public List<Rol> consultarRolesPorNombre(String nombre){
    	Query q = em.createNativeQuery("SELECT rol.* FROM ROL rol "
    			+ "WHERE rol.nombre = ?1 ", Rol.class);
    	q.setParameter(1, nombre);
    	try {
    		return q.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }
    
    public Rol consultarRolPorNombre(String nombre){
    	StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append("SELECT r FROM Rol r");
    	jpqlQuery.append(" WHERE r.nombre=?1");
    	Query query = mp.createQuery(jpqlQuery.toString());
    	query.setParameter(1, nombre);

    	try {
    		return (Rol) query.getSingleResult();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }
    
    public Rol consultarRolPorId(Long idRol){
    	Rol rol = this.buscar(Long.valueOf(idRol));
		if(rol==null){
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR201.toString());
			throw new SimascException(mensajeError);
		}
		 return rol;
    }
    
    public RolDTO consultarRolBasicoPorId(Long idRol) {
    	StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append("SELECT r.id_rol , r.nombre , r.fecha_creacion, r.preseleccion, r.tipo_servicio, ");
    	jpqlQuery.append("r.id_usuario_modificacion , r.fecha_ultima_modificacion, r.estado_registro, ");
    	jpqlQuery.append("r.aplica_mauc FROM Rol r");
    	jpqlQuery.append(" WHERE r.id_rol=?1");
    	Query query = mp.createNativeQuery(jpqlQuery.toString());
    	query.setParameter(1, idRol);

    	try {
    		return (RolDTO) query.getSingleResult();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }
    
    public String consultarNombreRolPorId(Long idRol){
    	return consultarRolPorId(idRol).getNombre();
    }
	
	/**
	 * Valida que el nombre de rol corresponda a cualquiera de los roles clasificados por el agrupador PRESTADO del dominio 
	 * AGRUPADOR_ROL_PERSONA
	 * @param nombre
	 * @return
	 */
	public boolean validarRolEsPrestadorDeServicio(String nombreRol){
		//Valida que el nombre de rol corresponda a cualquiera de los roles clasificados por el agrupador PRESTADO del dominio
		String nativeQuery= "SELECT count(*) from Dominio d INNER JOIN  Clasificador_dominio cd " + 
		" ON cd.codigo_clasificado=d.codigo AND cd.dominio_clasificado=d.dominio" + 
		" AND cd.dominio_clasificador= ?2 "+
		" AND cd.codigo_clasificador= ?3 "+
		" WHERE d.dominio= ?4 "+
		"   AND d.codigo = ?1";
		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, nombreRol);		
		query.setParameter(2, UtilDominios.DOMINIO_AGRUPADOR_ROL_PERSONA);		
		query.setParameter(3, UtilDominios.AGRUPADOR_ROL_PERSONA_PRESTADORES_SERVICIO);		
		query.setParameter(4, UtilDominios.DOMINIO_ROL_PERSONA);		
		int resultado = ((Integer)query.getSingleResult()).intValue();
		
		return  resultado>0;
	}
	
	
	/**
	 * Valida que el nombre de rol corresponda al apoderado demandante o demandado
	 * @param nombre
	 * @return
	 */
	public boolean validarRolEsApoderado(String nombreRol){
		return (nombreRol.equals(UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO)
				|| nombreRol.equals(UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE));
	}
	
	/**
	 * Valida si el rol está habilitado para realizar pronunciamiento, es decir 
	 * que es cualquiera de los siguientes roles:
	 * Arbitro
	 * Secretario
	 * 
	 * @param nombre
	 * @return
	 */
	public boolean validarRolRealizaPronunciamiento(String nombreRol){
		return nombreRol.equals(UtilDominios.ROL_PERSONA_ARBITRO)
				|| nombreRol.equals(UtilDominios.ROL_PERSONA_ARBITRO_INTERNACIONAL)
				|| nombreRol.equals(UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
	}
	
	/**
	 * Retorna los nombres de los roles que definen un arbitro
	 * @return
	 */
	public List<String> obtenerRolesArbitros() {
		List<String> roles = new ArrayList<String>();
		roles.add(UtilDominios.ROL_PERSONA_ARBITRO_SOCIAL);
		roles.add(UtilDominios.ROL_PERSONA_AMIGABLE_COMPONEDOR);
		roles.add(UtilDominios.ROL_PERSONA_ARBITRO_INTERNACIONAL);
		roles.add(UtilDominios.ROL_PERSONA_ARBITRO);		
		roles.add(UtilDominios.ROL_PERSONA_PERITO);	
		roles.add(UtilDominios.ROL_PERSONA_ARBITRO_RECUPERACION);
		roles.add(UtilDominios.ROL_PERSONA_ARBITRO_ADHOC);
		roles.add(UtilDominios.ROL_PERSONA_MEDIADOR);
		roles.add(UtilDominios.ROL_PERSONA_ARBITRO_RECUSACION);
		return roles;
	}

	public List<String> obtenerRolesOperadores() {
		List<String> roles = new ArrayList<String>();
		roles.add(UtilDominios.ROL_PERSONA_ARBITRO_SOCIAL);
		roles.add(UtilDominios.ROL_PERSONA_AMIGABLE_COMPONEDOR);
		roles.add(UtilDominios.ROL_PERSONA_ARBITRO_INTERNACIONAL);
		roles.add(UtilDominios.ROL_PERSONA_ARBITRO);		
		roles.add(UtilDominios.ROL_PERSONA_PERITO);	
		roles.add(UtilDominios.ROL_PERSONA_ARBITRO_RECUPERACION);
		roles.add(UtilDominios.ROL_PERSONA_ARBITRO_ADHOC);
		roles.add(UtilDominios.ROL_PERSONA_MEDIADOR);
		roles.add(UtilDominios.ROL_PERSONA_ARBITRO_RECUSACION);
		roles.add(UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
		return roles;
	}
	
	public List<Rol> consultarRolPorServicio(String tipoServicio) {
		StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append("SELECT r FROM Rol r");
    	jpqlQuery.append(" WHERE r.tipoServicio = ?1");
    	jpqlQuery.append(" AND r.estadoRegistro = ?2");
    	Query query = mp.createQuery(jpqlQuery.toString());
    	query.setParameter(1, tipoServicio);
    	query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);

    	try {
    		return query.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
	}
	
	/**
	 * Devuelve el codigo generado si no se encuentra el nombre del rol ingresado
	 * @param nombreRol
	 * @return
	 */
	public String validarRolNoExistente(String nombreRol) {
		StringBuilder jpqlQuery = new StringBuilder();
		String codigoDominio;
    	jpqlQuery.append("select left((select cast(max(id_rol) + 1 as varchar) as id from Rol )");
    	jpqlQuery.append(" + upper(replace(?1, ' ', '')), 8)");
    	jpqlQuery.append(" WHERE replace(?1, ' ', '') NOT IN ");
    	jpqlQuery.append(" (select replace(d.nombre,' ','') from dominio d where d.dominio = ?2 )");
    	Query query = em.createNativeQuery(jpqlQuery.toString());
    	query.setParameter(1, nombreRol);
    	query.setParameter(2, UtilDominios.DOMINIO_ROL_PERSONA);

		try {
			codigoDominio = (String) query.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			codigoDominio = null;
		}
    	
		return codigoDominio;
	}
	
	/**
	 * ADM-C-021
	 * ADM-C-003
	 * Devuelve los roles que manejan lista que son aquellos que se encuentran definidos
	 * en la tabla de parametro servicio sorteo que manejan lista.
	 * @return
	 */
	public List<Rol> consultarRolesQueManejanLista(){
		StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append("SELECT DISTINCT(p.rol) FROM ParametroServicioSorteo p ");
    	jpqlQuery.append(" WHERE p.sorteoConLista = :").append(ParametroServicioSorteo.ENTIDAD_PARAMETRO_SERVICIO_SORTEO_SORTEO_CON_LISTA);
    	jpqlQuery.append(" AND p.estadoRegistro = :").append(ParametroServicioSorteo.ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ESTADO_REGISTRO_PARAMETROSERVICIOSORTEO);
    	Query query = mp.createQuery(jpqlQuery.toString());
    	query.setParameter(ParametroServicioSorteo.ENTIDAD_PARAMETRO_SERVICIO_SORTEO_SORTEO_CON_LISTA, true);
    	query.setParameter(ParametroServicioSorteo.ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ESTADO_REGISTRO_PARAMETROSERVICIOSORTEO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

    	try {
    		return query.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
	}
	
	/**
	 * ADM-C-003
	 * Devuelve los roles que manejan materia que son aquellos que se encuentran definidos
	 * en la tabla de parametro servicio sorteo que manejan materia.
	 * @return
	 */
	public List<Rol> consultarRolesQueManejanMateria(){
		StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append("SELECT DISTINCT(p.rol) FROM ParametroServicioSorteo p ");
    	jpqlQuery.append(" WHERE p.sorteoConMateria = :").append(ParametroServicioSorteo.ENTIDAD_PARAMETRO_SERVICIO_SORTEO_SORTEO_CON_MATERIA);
    	jpqlQuery.append(" AND p.estadoRegistro = :").append(ParametroServicioSorteo.ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ESTADO_REGISTRO_PARAMETROSERVICIOSORTEO);
    	Query query = mp.createQuery(jpqlQuery.toString());
    	query.setParameter(ParametroServicioSorteo.ENTIDAD_PARAMETRO_SERVICIO_SORTEO_SORTEO_CON_MATERIA, true);
    	query.setParameter(ParametroServicioSorteo.ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ESTADO_REGISTRO_PARAMETROSERVICIOSORTEO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

    	try {
    		return query.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
	}
	
	
	public List<Rol> consutarRolesPorListaTipoServicio(List<String> tipoServicio, boolean transversales){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT r.* FROM ROL r ");
		nativeQuery.append(" WHERE r.estado_registro = ?1 ");
		nativeQuery.append(" AND r.tipo_servicio ").append(UtilConsultasSQL.clausulaInSQLStrings(tipoServicio));
		if(transversales){
			nativeQuery.append(" OR tipo_servicio IS NULL ");		
		}
		Query query = em.createNativeQuery(nativeQuery.toString(),Rol.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();
	}
		
	/**
	 * ADM-C-022
	 * método que obtiene los servicios asociados a un rol dado en la tabla Parametro_servicio_sorteo
	 *  @param codigoRol
	 */

	public List<Long> obtenerServiciosPorRolSorteo( Long codigoRol){
		StringBuilder query = new StringBuilder();
		query.append(" select pss.id_servicio from PARAMETRO_SERVICIO_SORTEO pss  ");
		query.append(" inner join rol r on r.id_rol = pss.id_rol  ");
		query.append(" where r.id_rol = ?1 ");
		
		Query q = em.createNativeQuery(query.toString());
		q.setParameter(1, codigoRol);
		
		@SuppressWarnings("unchecked")
		List<Long> listaServicios = q.getResultList();
		return listaServicios;
		
	}
	/**
	 *  Retorna la lista de roles en parametro_servicio_sorteo
	 * 
	 * @param tipoServicio
	 * @param transversales
	 * @return
	 */
	public List<Rol> consultarRolesSorteo(){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT DISTINCT  r.* FROM ROL r ");
		nativeQuery.append(" INNER JOIN PARAMETRO_SERVICIO_SORTEO pss ");
		nativeQuery.append(" on r.id_rol = pss.id_rol  ");
		nativeQuery.append(" where r.estado_registro =?1 and pss.estado_registro =?1 ");
		Query query = em.createNativeQuery(nativeQuery.toString(),Rol.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return query.getResultList();
	}

    public String consultarTipoServicioRolPorNombre(String nombre){
    	Rol rol = consultarRolPorNombre(nombre);
    	return rol.getTipoServicio();
    }
    // protected region metodos adicionales end
        
}

