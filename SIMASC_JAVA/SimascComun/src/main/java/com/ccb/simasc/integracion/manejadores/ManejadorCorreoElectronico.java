package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.CorreoElectronicoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad CorreoElectronico.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorCorreoElectronico extends ManejadorCrud<CorreoElectronico, Long> {

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end

	public ManejadorCorreoElectronico() {
		super(CorreoElectronico.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
    
	/**
	 * Consulta los Correos Electronicos asociados a una Persona
	 * @param idPersona
	 * @param incluirInactivos
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<CorreoElectronico> obtenerCorreosPersona(Long idPersona, Boolean incluirInactivos, String tipo) {
		// Build Query
		StringBuilder jpqlQuery = new StringBuilder();		
		jpqlQuery.append("SELECT c FROM CorreoElectronico c ");
		jpqlQuery.append(" WHERE c.idPersona= :");
		jpqlQuery.append(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_ID_PERSONA);
		
		if(!incluirInactivos) {
			jpqlQuery.append(" AND c.estadoRegistro= :");
			jpqlQuery.append(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_ESTADO_REGISTRO);
		}
		
		if(tipo != null && !tipo.isEmpty()) {
			jpqlQuery.append(" AND c.tipo= :");
			jpqlQuery.append(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_TIPO);
		}
		
		jpqlQuery.append(" ORDER BY c.tipo ");
		
		// Execute Query		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_ID_PERSONA, idPersona);
		
		if(!incluirInactivos) {
			query.setParameter(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		}		
		
		if(tipo != null && !tipo.isEmpty()) {
			query.setParameter(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_TIPO, tipo);
		}
		
		return query.getResultList();		
	}
    
	/**
	 * Metodo para persistir el correo electronico
	 * 
	 * @param CorreoElectronico
	 * @return CorreoElectronico
	 */
	public CorreoElectronico crearCorreoElectronico(CorreoElectronico correo) {
		return (CorreoElectronico) mp.updateObject(correo);
	}

	/**
	 * Consulta la lista de Correos Electronicos ACTIVOS asociados a una Persona
	 * @param idPersona
	 * @return
	 */
	public List<CorreoElectronico> consultaCorreosPersona(Long idPersona) {
		return obtenerCorreosPersona(idPersona, false, null);
	}
	
	/**
	 * Consulta la lista de Correos Electronicos ACTIVOS o INACTIVOS asociados a una Persona
	 * @param idPersona
	 * @param incluirInactivos
	 * @return
	 */
	public List<CorreoElectronico> consultaCorreosPersona(Long idPersona, Boolean incluirInactivos) {
		return obtenerCorreosPersona(idPersona, incluirInactivos, null);
	}
	
	/**
	 * Consulta la lista de Correos Electronicos ACTIVOS o INACTIVOS asociados a una persona,
	 * y que pertenezcan a un tipo definido por parámetro
	 * @param idPersona
	 * @param incluirInactivos
	 * @param tipo
	 * @return
	 */
	public List<CorreoElectronico> consultaCorreosPersona(Long idPersona, Boolean incluirInactivos, String tipo) {
		return obtenerCorreosPersona(idPersona, incluirInactivos, tipo);
	}
	
	@SuppressWarnings("unchecked")
	public List<CorreoElectronico> consultarCorreosPersonaSinAsociacionCaso(Long idPersona, String tipo) {
		// Build Query
		StringBuilder jpqlQuery = new StringBuilder();		
		jpqlQuery.append("SELECT c FROM CorreoElectronico c ");
		
		jpqlQuery.append(" WHERE c.idCorreo NOT IN (");
		jpqlQuery.append(" SELECT DISTINCT crp.correoElectronicoRolPersonaCasoPK.idCorreo FROM CorreoElectronicoRolPersonaCaso crp ");
		jpqlQuery.append(" WHERE crp.correoElectronicoRolPersonaCasoPK.idPersona = :");
		jpqlQuery.append(UtilConsultasSQL.obtenerNombreParametro(CorreoElectronicoRolPersonaCaso.ENTIDAD_CORREO_ELECTRONICO_ROL_PERSONA_CASO_PK_ID_PERSONA));
		jpqlQuery.append(" )");
		
		jpqlQuery.append(" AND c.idCorreo NOT IN (");
		jpqlQuery.append(" SELECT DISTINCT cps.correoElectronicoPersonaSolicitudPK.idCorreo FROM CorreoElectronicoPersonaSolicitud cps ");
		jpqlQuery.append(" WHERE cps.correoElectronicoPersonaSolicitudPK.idPersona = :");
		jpqlQuery.append(UtilConsultasSQL.obtenerNombreParametro(CorreoElectronicoRolPersonaCaso.ENTIDAD_CORREO_ELECTRONICO_ROL_PERSONA_CASO_PK_ID_PERSONA));
		jpqlQuery.append(" )");
		
		jpqlQuery.append(" AND c.idPersona= :");
		jpqlQuery.append(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_ID_PERSONA);
		
		jpqlQuery.append(" AND c.estadoRegistro = :").append(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_ESTADO_REGISTRO_CORREOELECTRONICO);
		
		if(tipo != null && !tipo.isEmpty()) {
			jpqlQuery.append(" AND c.tipo= :");
			jpqlQuery.append(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_TIPO);
		}
		
		jpqlQuery.append(" ORDER BY c.idCorreo ");
		
		// Execute Query		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(UtilConsultasSQL.obtenerNombreParametro(CorreoElectronicoRolPersonaCaso.ENTIDAD_CORREO_ELECTRONICO_ROL_PERSONA_CASO_PK_ID_PERSONA), idPersona);
		query.setParameter(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_ID_PERSONA, idPersona);
		query.setParameter(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_TIPO, tipo);
		query.setParameter(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_ESTADO_REGISTRO_CORREOELECTRONICO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();
	}

	/**
	 * Consulta para obtener el correo electronico de una Persona por direccion
	 * y/o idPersona
	 * 
	 * @param direccion
	 * @param idPersona
	 * @return CorreoElectronico
	 */
	public CorreoElectronico buscarPorDireccionPersona(String direccion, Long idPersona) {
		StringBuilder jpqlQuery = new StringBuilder();
		CorreoElectronico correo = new CorreoElectronico();
		try {
			jpqlQuery.append("SELECT c FROM CorreoElectronico c ");
			jpqlQuery.append(" WHERE C.estadoRegistro=:");
			jpqlQuery.append(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_ESTADO_REGISTRO_CORREOELECTRONICO);
			jpqlQuery.append(" AND c.direccion LIKE:");
			jpqlQuery.append(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_DIRECCION);
			if (idPersona != null) {
				jpqlQuery.append(" AND c.idPersona=:");
				jpqlQuery.append(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_ID_PERSONA);
			}

			Query query = mp.createQuery(jpqlQuery.toString());

			query.setParameter(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_DIRECCION, "%" + direccion + "%");
			query.setParameter(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_ESTADO_REGISTRO_CORREOELECTRONICO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
			if (idPersona != null)
				query.setParameter(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_ID_PERSONA, idPersona);

			correo = (CorreoElectronico) query.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			correo = null;
		}
		return correo;
	}

	@SuppressWarnings("unchecked")
	public CorreoElectronico consultarPorTipoCorreoIdPersona(Long idPersona, String tipoCorreo) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT t FROM CorreoElectronico t ");
		jpqlQuery.append(" WHERE ");
		jpqlQuery.append(" t.tipo=:");
		jpqlQuery.append(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_TIPO);
		jpqlQuery.append(" AND t.persona.idPersona=:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_TIPO, tipoCorreo);
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);

		List<CorreoElectronico> listaCorreos = query.getResultList();

		return listaCorreos != null && !listaCorreos.isEmpty() ? (CorreoElectronico) listaCorreos.get(0) : null;

	}

	/**
	 * Inactiva todos los correos de la persona que no se pasan como parametro
	 * 
	 * @param identificadoresCorreos
	 */
	public void inactivarOtrosCorreosPersona(List<Long> identificadoresCorreos, Long idPersona){
		
		StringBuilder nativeQuery = new StringBuilder("UPDATE CORREO_ELECTRONICO SET estado_registro= ?3");
		nativeQuery.append(" WHERE ");
		nativeQuery.append("id_persona = ?1 AND ");
		
		if(identificadoresCorreos!=null && !identificadoresCorreos.isEmpty()){
			nativeQuery.append(" id_correo NOT ")
			.append(UtilConsultasSQL.clausulaInSQLSNumeros(identificadoresCorreos));
			nativeQuery.append(" AND ");
		}
		nativeQuery.append(" id_correo NOT IN (SELECT id_correo FROM CORREO_ELECTRONICO_ROL_PERSONA_CASO WHERE id_persona = ?1 and estado_registro = ?2)");
		nativeQuery.append(" AND id_correo NOT IN (SELECT id_correo FROM CORREO_ELECTRONICO_PERSONA_SOLICITUD WHERE id_persona = ?1 and estado_registro = ?2)");
		
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString());
		query.setParameter(1, idPersona);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_INACTIVO);
		
		query.executeUpdate();
		
	}

	/**
	 * ADM-C-021 Crea o actualiza los correos de la lista: Crea los correos d si
	 * no viene definido el identificador, de lo contrario lo actualiza.
	 * 
	 * @param lista
	 */
	public void crearOActualizarListaCorreos(List<CorreoElectronico> lista) {
		for (CorreoElectronico correo : lista) {
			this.creaOActualizaCorreo(correo);
		}
	}

	/**
	 * ADM-C-021 Crea los correos d si no viene definido el identificador, de lo
	 * contrario lo actualiza.
	 * 
	 * @param correo
	 */
	public void creaOActualizaCorreo(CorreoElectronico correo) {
		if (correo != null) {
			if (correo.getIdCorreo() != null) {
				this.actualizar(correo);
			} else {
				this.crear(correo);
			}
		}
	}

	/**
	 * ADM-C-021 Crea o actualiza los correos que se pasan como parámetro. El
	 * resto de correos que tenga la persona y que no vienen en la lista se
	 * inactivan.
	 * 
	 * @param correosDTO
	 * @param idPersona
	 */
	public void crearOActualizarCorreosElectronicosPersona(List<CorreoElectronicoDTO> correosDTO, Long idPersona) {

		List<CorreoElectronico> correos = new CorreoElectronicoDTO()
				.transformarColeccionDTOAColeccionEntidades(correosDTO);

		for (CorreoElectronico correo : correos) {
			correo.setIdPersona(idPersona);
		}

		this.crearOActualizarListaCorreos(correos);

		List<Long> idCorreos = new ArrayList<>();
		for (CorreoElectronico correo : correos) {
			idCorreos.add(correo.getIdCorreo());
		}

		if (!idCorreos.isEmpty()) {
			this.inactivarOtrosCorreosPersona(idCorreos, idPersona);
		}
	}

	public void pasarCorreosASecundarios(long idPersona, Long idCorreo) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" update correo_electronico ");
		nativeQuery.append(" set tipo = ?1 ");
		nativeQuery.append(" where id_persona = ?2");
		if(idCorreo !=null )
			nativeQuery.append(" and id_correo != ?3");
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString());
		query.setParameter(1, UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_SECUNDARIO);
		query.setParameter(2, idPersona);
		if(idCorreo !=null )
			query.setParameter(3, idCorreo);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public CorreoElectronico traerPrimerCorreoPrimario(long idPersona) {
		StringBuilder jpqlQuery = new StringBuilder();
		List<CorreoElectronico> correos = new ArrayList<>();
		CorreoElectronico correo = new CorreoElectronico();
		try {
			jpqlQuery.append(" select c from CorreoElectronico c ");
			jpqlQuery.append(" where c.tipo = : ").append(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_TIPO);
			jpqlQuery.append(" and c.estadoRegistro = :").append(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_ESTADO_REGISTRO);
			jpqlQuery.append(" and c.idPersona = : ").append(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_ID_PERSONA);
			jpqlQuery.append(" order by c.idCorreo ");
			
			Query query = getEntityManager().createQuery(jpqlQuery.toString(),CorreoElectronico.class);
			query.setParameter(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_TIPO, UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
			query.setParameter(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(CorreoElectronico.ENTIDAD_CORREO_ELECTRONICO_ID_PERSONA, idPersona);
			
			correos =  query.getResultList();
			if(!correos.isEmpty())
				correo = correos.get(0);
			else
				correo=null;
		} catch (NoResultException | NonUniqueResultException e) {
			correo=null;
		}

		return correo;

	}
	
	public Boolean tieneCorreosAsociados(Long idPersona) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select 1 from correo_electronico (nolock) where id_persona = ?1 and estado_registro = ?2");
		
		Query query = getEntityManager().createNativeQuery(sql.toString(), Long.class);
		query.setParameter(1, idPersona);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return !query.getResultList().isEmpty();
	}

	// protected region metodos adicionales end

}
