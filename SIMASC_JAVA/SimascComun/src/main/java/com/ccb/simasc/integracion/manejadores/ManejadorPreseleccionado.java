package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.PreseleccionadoDesignadoDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Preseleccionado;
import com.ccb.simasc.transversal.entidades.PreseleccionadoPK;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


@Stateless
public class ManejadorPreseleccionado extends ManejadorCrud<Preseleccionado,PreseleccionadoPK>{

	@PersistenceContext
	private transient EntityManager em;
    
    public ManejadorPreseleccionado() {
        super(Preseleccionado.class);
    }    
    
    
	/**
	 * Metodo encargado de consultar un Arbitro Preseleccionado activo a un caso
	 * 
	 * @param idPersona
	 * @param idCaso
	 * @return Preseleccionado
	 * @throws SimascException
	 */
	public Preseleccionado buscarPreseleccionado(Long idPersona, Long idCaso) throws SimascException {
		Preseleccionado preseleccionado = new Preseleccionado();
		try {
			StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append(" SELECT pr FROM Preseleccionado pr ");
			jpqlQuery.append(" WHERE pr.persona.idPersona =: ");
			jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);
			jpqlQuery.append(" AND pr.caso.idCaso =: ");
			jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
			jpqlQuery.append(" AND pr.estadoRegistro =: ");
			jpqlQuery.append(Preseleccionado.ENTIDAD_PRESELECCIONADO_ESTADO_REGISTRO);

			Query query = mp.createQuery(jpqlQuery.toString());
			query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
			query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
			query.setParameter(Preseleccionado.ENTIDAD_PRESELECCIONADO_ESTADO_REGISTRO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
			preseleccionado = (Preseleccionado) query.getSingleResult();

		} catch (NoResultException e) {
			return preseleccionado;
		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString());
			throw new SimascException(mensajeError);
		}

		return preseleccionado;
	}
    
	/**
	 * Metodo encargado de consultar los arbitros que se encuentran preseleccionados y activos a un caso
	 * @param idCaso
	 * @return List<Preseleccionado>
	 * @throws SimascException
	 */
	public List<Preseleccionado> consultarPreseleccionadosCaso(Long idCaso) throws SimascException {
		List<Preseleccionado> preseleccionados = new ArrayList<Preseleccionado>();

		try {
			StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append(" SELECT pr FROM Preseleccionado pr ");
			jpqlQuery.append(" WHERE pr.caso.idCaso =: ");
			jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
			jpqlQuery.append(" AND pr.estadoRegistro =: ");
			jpqlQuery.append(Preseleccionado.ENTIDAD_PRESELECCIONADO_ESTADO_REGISTRO);

			Query query = mp.createQuery(jpqlQuery.toString());
			query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
			query.setParameter(Preseleccionado.ENTIDAD_PRESELECCIONADO_ESTADO_REGISTRO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
			preseleccionados = query.getResultList();
		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString());
			throw new SimascException(mensajeError);
		}
		return preseleccionados;
	}
    
    /**
	 * Registra un Preseleccionado en la bd
	 * 
	 * @param persona
	 * @return
	 */
	public Preseleccionado crearPreseleccionado(Preseleccionado preseleccionado) {
		return (Preseleccionado) mp.updateObject(preseleccionado);

	}
    
	public List<PreseleccionadoDesignadoDTO> consultarPreseleccionadosCasoDesignado(Long idCaso) throws SimascException {
		
		List<PreseleccionadoDesignadoDTO> preseleccionadosDto = new ArrayList<>();

		try {
			StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append(" SELECT pr.id_persona as idPersona, pr.id_caso as idCaso, ");
			jpqlQuery.append(" CONCAT(p.primer_nombre_o_razon_social,' ',p.segundo_nombre,' ', p.primer_apellido,' ',p.segundo_apellido) as nombreCompleto, ");
			jpqlQuery.append(" pr.tipo_preseleccion as tipoPreseleccion,  ");
			jpqlQuery.append("   (SELECT CASE WHEN COUNT(*) > 0 THEN 'true' ELSE 'false' END ");
			jpqlQuery.append("   FROM ROL_PERSONA_CASO rpc ");
			jpqlQuery.append("   WHERE rpc.id_persona = pr.id_persona AND rpc.id_caso = pr.id_caso ");
			jpqlQuery.append("   AND rpc.estado_registro = ?1) as designadoPreviamente ");
			jpqlQuery.append(" FROM PRESELECCIONADO pr ");
			jpqlQuery.append(" INNER JOIN PERSONA p on p.id_persona = pr.id_persona ");
			jpqlQuery.append(" WHERE pr.id_caso = ?2 ");
			jpqlQuery.append(" AND pr.estado_registro = ?1 ");				
			
			Query query = em.createNativeQuery(jpqlQuery.toString(), PreseleccionadoDesignadoDTO.class);	
			query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(2, idCaso);

			preseleccionadosDto = query.getResultList();
		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString());
			throw new SimascException(mensajeError);
		}
		return preseleccionadosDto;
	}
        
}

