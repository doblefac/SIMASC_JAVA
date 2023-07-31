package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.FiltroBusquedaUsuarioSistemaDTO;
import com.ccb.simasc.transversal.dto.UsuarioClaveDTO;
import com.ccb.simasc.transversal.dto.UsuarioSistemaConsultaDTO;
import com.ccb.simasc.transversal.entidades.Centro;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Usuario;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Usuario.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorUsuario extends ManejadorCrud<Usuario,String>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones																			
																				
	
	@PersistenceContext
	private transient EntityManager em;
	// protected region atributos end
    
    public ManejadorUsuario() {
        super(Usuario.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta sección sus modificaciones

    /**
     * Consulta la información a mostrar en el caso de uso ADM-C-020
     * @param rolPersona
     * @return
     */
    public List<UsuarioSistemaConsultaDTO> consultarUsuariosSistemaConsultaDTO(FiltroBusquedaUsuarioSistemaDTO filtro){
    	
    	
    	List<Long> identificadoresPersonas = consultarPersonasBusqueda(filtro);
    	List<UsuarioSistemaConsultaDTO> usuarios = new ArrayList<>();
    	
    	if(!identificadoresPersonas.isEmpty()){
    		Query query = em.createNativeQuery(construirConsultaUsuariosSistema(identificadoresPersonas), UsuarioSistemaConsultaDTO.class );
    	  	
        	query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
        	query.setParameter(2, UtilDominios.DOMINIO_ROL_PERSONA);
        	query.setParameter(3, UtilDominios.DOMINIO_ESTADO_USUARIO);        	
        	usuarios = query.getResultList();
    	}
    	
    	return usuarios;
    }
    
    /**
     * Consulta las personas que cumplen los criterios de búsqueda
     * ADM-C-020
     * @param filtro
     * @return
     */
    public List<Long> consultarPersonasBusqueda(FiltroBusquedaUsuarioSistemaDTO filtro){
    	
    	Query query = null;
    	
    	query = em.createNativeQuery(construirConsultaPreliminarUsuariosSistema(filtro));
    	  	
    	if(filtro.definenRol())
    		query.setParameter(1, filtro.getRol());
    	else {
    		query.setParameter(4, UtilDominios.DOMINIO_AGRUPADOR_ROL_PERSONA_CONSULTA_USUARIO);
    		query.setParameter(5, filtro.getGrupoUsuarioConsulta());
    		query.setParameter(6, UtilDominios.DOMINIO_ROL_PERSONA);
    		
    	}
    	query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	if(filtro.definenApellidos())
    		query.setParameter(7, '%'+filtro.getApellidos()+'%' );
    	if(filtro.definenNombres())
    		query.setParameter(8, '%'+filtro.getNombres()+'%' );

    	List<BigDecimal> resultados = query.getResultList();
    	
    	return convertirResultadoAListaIdentificadoresPersonas(resultados);
    }
	
    private List<Long> convertirResultadoAListaIdentificadoresPersonas(List<BigDecimal> registros){
    	List<Long> identificadoresPersonas = new ArrayList<>();
    	for(BigDecimal registro : registros){
    		identificadoresPersonas.add(registro.longValue());
    	}    	
    	return identificadoresPersonas;
    }
    
    /**
     * ADM-C-020
     * Genera la lista de UsuarioSistemaConsultaDTO 
     * @param registros
     * @return
     */
	private List<UsuarioSistemaConsultaDTO> convertirResultadoAListaUsuarioSistemaConsultaDTO(List<Object[]> registros) {
		
		List<UsuarioSistemaConsultaDTO> usuariosDTO = new ArrayList<>();	
		
		int registrosSize = registros.size();
		if(registrosSize>1){
			usuariosDTO = generarListaUsuarioSistemaConsultaDTO(registros);
		}else if(registrosSize==1){
			usuariosDTO.add(convertirRegistroAUsuarioSistemaConsultaDTO(registros.get(0)));
		}
	
		return usuariosDTO;
	}
	
	/**
	 * ADM-C-020
	 * Genera la lista de UsuarioSistemaConsultaDTO cuando el número de registro es mayor a 1
	 * @param registros
	 * @return
	 */
	private List<UsuarioSistemaConsultaDTO> generarListaUsuarioSistemaConsultaDTO(List<Object[]> registros){
		
		List<UsuarioSistemaConsultaDTO> usuariosDTO = new ArrayList<>();
		int registrosSize = registros.size();
		UsuarioSistemaConsultaDTO anteriorDTO =convertirRegistroAUsuarioSistemaConsultaDTO(registros.get(0));
		UsuarioSistemaConsultaDTO actualDTO = null;
		StringBuilder roles = new StringBuilder(anteriorDTO.getRoles());

		for (int i = 1; i<registros.size(); i++) {
			actualDTO = convertirRegistroAUsuarioSistemaConsultaDTO(registros.get(i));
			if(anteriorDTO.getIdPersona().equals(actualDTO.getIdPersona())){
				if(i==registrosSize-1){
					roles.append(", ").append(actualDTO.getRoles());
					anteriorDTO.setRoles(roles.toString());
					usuariosDTO.add(anteriorDTO);
				}else{
					roles.append(", ").append(actualDTO.getRoles());
				}					
			}else{
				if(i==registrosSize-1){
					anteriorDTO.setRoles(roles.toString());
					usuariosDTO.add(anteriorDTO);
					usuariosDTO.add(actualDTO);
				}else{
					anteriorDTO.setRoles(roles.toString());
					usuariosDTO.add(anteriorDTO);
					anteriorDTO = actualDTO;
					roles = new StringBuilder(actualDTO.getRoles());
				}
				
			}
		}
		
		return usuariosDTO;
	}
	
	private UsuarioSistemaConsultaDTO convertirRegistroAUsuarioSistemaConsultaDTO(Object[] registro) {
		UsuarioSistemaConsultaDTO usuarioDTO = new UsuarioSistemaConsultaDTO();
		Object reg;
		usuarioDTO.setIdPersona(((reg = registro[0]) == null ? null : ((BigDecimal) reg).longValue()));
		usuarioDTO.setRoles(((reg = registro[1]) == null ? null : (String) reg));
		usuarioDTO.setNombres(((reg = registro[2]) == null ? null : (String) reg));
		usuarioDTO.setApellidos(((reg = registro[3]) == null ? null : (String) reg));
		usuarioDTO.setEstado(((reg = registro[4]) == null ? null : (String) reg));
	
		return usuarioDTO;
	}
	
	/**
	 * Consulta del CU ADM-C-020
	 * @param filtro
	 * @param identificadoresPersonas
	 * @return
	 */
	private String construirConsultaUsuariosSistema(List<Long> identificadoresPersonas){		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT 	p.id_persona AS idPersona, 	");
		query.append(" ISNULL(p.primer_nombre_o_razon_social,'')+' '+ISNULL(p.segundo_nombre,'') AS nombres, ");
		query.append(" ISNULL(p.primer_apellido,'')+' '+ISNULL(p.segundo_apellido,'') AS apellidos,	");
		query.append(" du.nombre AS estado,	");
		query.append(" (select stuff(( select  distinct( ', ' + dr.nombre)	");
		query.append(" FROM ROL_PERSONA rp 	");
		query.append(" INNER JOIN ROL r ON rp.id_rol = r.id_rol AND r.estado_registro =?1	");	
		query.append(" INNER JOIN DOMINIO dr ON dr.codigo = r.nombre AND dr.dominio =?2	");
		query.append(" WHERE id_persona = p.id_persona	");
		query.append(" AND rp.estado_registro =?1	");
		query.append(" AND rp.fecha_fin_vigencia is null	");
		query.append(" order by ', ' + dr.nombre for xml path('')), 1, 1, ''))  AS roles	");
		query.append(" FROM PERSONA p	"); 	
		query.append(" INNER JOIN Usuario u ON u.id_persona=p.id_persona	"); 	
		query.append(" INNER JOIN Dominio du ON du.codigo=u.estado AND du.dominio=?3	"); 
		query.append(" WHERE p.estado_registro=?1	");
		query.append(" AND p.id_persona ").append(UtilConsultasSQL.clausulaInSQLSNumeros(identificadoresPersonas));  
		query.append(" ORDER BY p.id_persona ");

		return query.toString();
	}
	
	/**
	 * Identifican las personas que cumplen con los criterios de busqueda
	 * @param filtro
	 * @return
	 */
	private String construirConsultaPreliminarUsuariosSistema(FiltroBusquedaUsuarioSistemaDTO filtro){		
		StringBuilder query = new StringBuilder();
		query.append("SELECT ");
		query.append("	DISTINCT(p.id_persona) ");
		query.append("FROM ROL_PERSONA rp "); 
		query.append("	INNER JOIN Rol r ON r.id_rol=rp.id_rol "); 
		query.append("	INNER JOIN Persona p ON p.id_persona=rp.id_persona ");
		query.append("	INNER JOIN Usuario u ON u.id_persona=p.id_persona ");
		query.append("WHERE ");
		//query.append("	rp.estado_registro=?1 	AND");
		query.append(" r.estado_registro=?2 ");
		query.append("	AND p.estado_registro=?3 ");
		
		if(filtro.definenRol()){
			query.append("	AND r.nombre= ?1");
		}else{
			//Se consultan todos los roles permitidos para el grupo al que pertenece el usuario
			query.append("	AND r.nombre in ( ");
			query.append(" SELECT d.codigo from Dominio d "); 
			query.append("	INNER JOIN  Clasificador_dominio cd "); 
			query.append("		ON cd.codigo_clasificado=d.codigo "); 
			query.append("			AND cd.dominio_clasificado=d.dominio ");
			query.append("			AND cd.dominio_clasificador= ?4");
			query.append("			AND cd.codigo_clasificador= ?5");
			query.append("	WHERE ");
			query.append("		d.dominio= ?6 ");
		}
		
		if(filtro.definenApellidos()){
			query.append("	AND CONCAT(p.primer_apellido, p.segundo_apellido) like replace( ?7 ");
			query.append(", ' ', '')");
		}
		
		if(filtro.definenNombres()){
			query.append("	AND CONCAT(p.primer_nombre_o_razon_social, p.segundo_nombre) like replace( ?8 ");
			query.append(", ' ', '')");
		}
		if(!filtro.definenRol()){
			query.append(")");
		}
		
		return query.toString();
	}
	
	public Usuario consultarUsuarioPorPersonaEstado(Long idPersona, String estado){
		Usuario usuario = null;
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT u FROM Usuario u ");
		jpqlQuery.append(" WHERE u.idPersona = :").append(Usuario.ENTIDAD_USUARIO_ID_PERSONA);
		if(estado != null){
			jpqlQuery.append(" AND u.estado =:").append(Usuario.ENTIDAD_USUARIO_ESTADO);			
		}
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Usuario.ENTIDAD_USUARIO_ID_PERSONA, idPersona);
		if(estado != null){
			query.setParameter(Usuario.ENTIDAD_USUARIO_ESTADO,estado);			
		}
		try {
			usuario = (Usuario)query.getSingleResult();
		} catch (NoResultException nr) {
			usuario = null;
			}catch (NonUniqueResultException e) {
				String mensajeError = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR525.toString()));
				throw new SimascException(mensajeError); 
			}
		
		return usuario;
		
	}
	
	/**
     * Método para obtener el listado de los centros a los cuales se encuentra asociado un usuario
     * ADM-C-015
     * @param idPersona
     * @return
     */
    public List<Centro> obtenerCentrosActor(Long idPersona) {
    	StringBuilder string = new StringBuilder();     	
    	string.append("SELECT DISTINCT rp.centro FROM RolPersona rp ")
    	.append(" WHERE rp.persona.idPersona = :").append(Persona.ENTIDAD_PERSONA_PK)
    	.append(" AND rp.estadoRegistro = ").append("'ACT'")
    	.append(" AND rp.persona.estadoRegistro = ").append("'ACT'")
    	.append(" AND rp.centro.estadoRegistro = ").append("'ACT'");
    	
    	Query query = mp.createQuery(string.toString());
    	query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
    	
    	
    	try {
    		return query.getResultList();			
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }

    
    @SuppressWarnings("unchecked")
	public List<UsuarioClaveDTO> consultarEstadoUsuario(Long idPersona) {

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select u.usuario_login as usuarioLogin, u.id_persona as idPersona, u.estado as estadoUsuario, u.observaciones as observaciones, ");
		nativeQuery.append(" u.ultimo_acceso as ultimoAcceso, c.clave_bloqueada as claveBloqueada, c.fecha_vencimiento as fechaVencimientoClave, ");
		nativeQuery.append(" c.estado_registro as estadoRegistroClave, ");
		nativeQuery.append(" (case when (select count(*) from rol_persona rp where rp.estado_registro=?2  ");
		nativeQuery.append(" AND (rp.fecha_fin_vigencia is null or Cast(rp.fecha_fin_vigencia as Date)>=Cast(GETDATE() as Date)) ");
		nativeQuery.append(" AND (Cast(rp.fecha_inicio_vigencia as Date))<=(Cast(GETDATE() as Date)) ");
		nativeQuery.append(" AND rp.id_rol in (select id_rol from rol where nombre in (?3, ?4, ?5)) ");
		nativeQuery.append(" AND rp.id_persona=?1)>0  then 'true'  ");
		nativeQuery.append(" else 'false'  ");
		nativeQuery.append(" end) as esFuncionarioCCB ");
		nativeQuery.append(" from usuario u ");
		nativeQuery.append(" left join clave c on u.usuario_login=c.usuario_login ");
		nativeQuery.append(" and c.estado_registro=?2 ");
		nativeQuery.append(" where u.id_persona=?1 ");
		nativeQuery.append(" ORDER BY c.fecha_vencimiento desc ");	
	    
		Query q = em.createNativeQuery(nativeQuery.toString(), UsuarioClaveDTO.class);
		
		q.setParameter(1, idPersona);
		q.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(3, UtilDominios.ROL_PERSONA_ADMINISTRADOR_USUARIOS);
		q.setParameter(4, UtilDominios.ROL_PERSONA_DIRECTOR_CAC);
		q.setParameter(5, UtilDominios.ROL_PERSONA_SUBDIRECTOR_CAC);
		
		return q.getResultList();
	}

    // protected region metodos adicionales end
        
}

