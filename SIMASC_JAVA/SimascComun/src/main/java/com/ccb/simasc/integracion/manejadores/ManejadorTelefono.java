package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

import java.util.ArrayList;


// Escriba en esta sección sus modificaciones

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.TelefonoDTO;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Telefono;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad Telefono.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorTelefono extends ManejadorCrud<Telefono, Long> {

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	
	// protected region atributos end

	public ManejadorTelefono() {
		super(Telefono.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/**
	 * Metodo para persistir el telefono
	 * 
	 * @param Telefono
	 * @return Telefono
	 */
	public Telefono crearTelefono(Telefono telefono) {
		return (Telefono) mp.updateObject(telefono);
	}

	/**
	 * Método que obtiene los telefonos de una persona de acuerdo al tipo
	 * @param tipoTelefono
	 * @param idPersona
	 * @return
	 */
	public List<Telefono> consultarPorTipoYPersona(List<String> tipoTelefono, Long idPersona) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT t FROM Telefono t ");
		jpqlQuery.append(" WHERE ");
		jpqlQuery.append(" t.tipoTelefono in :");
		jpqlQuery.append(Telefono.ENTIDAD_TELEFONO_TIPO_TELEFONO);
		jpqlQuery.append(" AND t.persona.idPersona=:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);
		jpqlQuery.append(" AND t.estadoRegistro=:");
		jpqlQuery.append("estadoRegistro");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Telefono.ENTIDAD_TELEFONO_TIPO_TELEFONO, tipoTelefono);
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		query.setParameter("estadoRegistro", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return query.getResultList();
	}

	/**
	 * Se obtienen todos los telefonos asociados a una persona. Pueden ser
	 * activos e inactivos o solo activos.
	 * 
	 * @param idPersona
	 * @param estadoRegistro
	 * @return List<Telefono>
	 */
	public List<Telefono> consultarTelefonosPersona(Long idPersona, Boolean estadoRegistro) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT t FROM Telefono t ");
		jpqlQuery.append(" WHERE t.persona.idPersona=: ");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);

		if (estadoRegistro) {
			jpqlQuery.append(" AND t.estadoRegistro=: ");
			jpqlQuery.append(Telefono.ENTIDAD_TELEFONO_ESTADO_REGISTRO);
		}

		jpqlQuery.append(" ORDER BY t.idTelefono ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);

		if (estadoRegistro)
			query.setParameter(Telefono.ENTIDAD_TELEFONO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return query.getResultList();
	}

	/**
	 * Inactiva todos los telefonos de la persona que no se pasan como parametro
	 * 
	 * @param identificadoresTelefonos
	 */
	public void inactivarOtrosTelefonos(List<Long> identificadoresTelefonos, Long idPersona) {

		StringBuilder nativeQuery = new StringBuilder("UPDATE TELEFONO SET estado_registro= ?2 ");
		nativeQuery.append(" WHERE ");
		if(identificadoresTelefonos!=null && !identificadoresTelefonos.isEmpty()){
			nativeQuery.append(" id_telefono NOT ")
			.append(UtilConsultasSQL.clausulaInSQLSNumeros(identificadoresTelefonos));
			nativeQuery.append(" AND ");
		}
		nativeQuery.append("id_persona = ?1");
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString());
		query.setParameter(1, idPersona);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_INACTIVO);

		query.executeUpdate();

	}
	
	/**
	 * ADM-C-021
	 * Crea o actualiza los telefonos de la lista:
	 * Crea los telefonos si no viene definido el identificador, de 
	 * lo contrario lo actualiza.
	 * @param lista
	 */
    public void crearOActualizarListaTelefonos(List<Telefono> lista) {
    	for(Telefono telefono : lista){
    		this.creaOActualizaTelefono(telefono);
        }    	
    }
    
    /**
     * ADM-C-021
     * Crea los telefonos d si no viene definido el identificador, de 
	 * lo contrario lo actualiza.
     * @param telefono
     */
    public void creaOActualizaTelefono(Telefono telefono){
    	if(telefono!=null){
    		if(telefono.getIdTelefono()!=null){
    			this.actualizar(telefono);
    		}else{
    			this.crear(telefono);
    		}
    	}    	
    }
    
	/**
	 * ADM-C-021 Crea o actualiza los telefonos que se pasan como parámetro. El
	 * resto de telefonos que tenga la persona y que no vienen en la lista se
	 * inactivan.
	 * 
	 * @param telefonosDTO
	 * @param idPersona
	 */
	public void crearOActualizarTelefonosPersona(List<TelefonoDTO> telefonosDTO, Long idPersona) {
		List<Telefono> telefonos = new TelefonoDTO().transformarColeccionDTOAColeccionEntidades(telefonosDTO);
		for(Telefono telefono: telefonos){
			if (telefono.getIdPersona() == null) {
				telefono.setIdPersona(idPersona);
			}
		}
		
		this.crearOActualizarListaTelefonos(telefonos);		
		List<Long> idTelefonos = new ArrayList<>();
		for (Telefono telefono : telefonos) {			
			idTelefonos.add(telefono.getIdTelefono());
		}

		if (!idTelefonos.isEmpty()) {
			this.inactivarOtrosTelefonos(idTelefonos, idPersona);
		}
	}
    

	// protected region metodos adicionales end

}
