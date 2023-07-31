package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.DominioDTO;
import com.ccb.simasc.transversal.dto.IdiomaDTO;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.Idioma;
import com.ccb.simasc.transversal.entidades.IdiomaPK;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Idioma.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorIdioma extends ManejadorCrud<Idioma,IdiomaPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorIdioma() {
        super(Idioma.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    /**
     * Inactiva todos los idiomas de la persona que no se pasan como parametro
     * @param codigosIdiomas Codigos de dominio de los idiomas que tiene la persona
     * @param idPersona
     */
	public void inactivarOtrosIdiomas(List<String> codigosIdiomas, Long idPersona){
		
		StringBuilder nativeQuery = new StringBuilder("UPDATE IDIOMA SET estado_registro= ?2");
		nativeQuery.append(" WHERE ");
		if(codigosIdiomas!=null && !codigosIdiomas.isEmpty()){
			nativeQuery.append(" nombre NOT ").append(UtilConsultasSQL.clausulaInSQLSNumeros(codigosIdiomas));
			nativeQuery.append(" AND ");
		}
		nativeQuery.append("id_persona = ?1");
						
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString());
		query.setParameter(1, idPersona);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_INACTIVO);
		
		query.executeUpdate();
		
	}
	

	public List<Idioma> consultarIdiomasPersona(Long idPersona, Boolean estadoRegistro) {			
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT i FROM Idioma i ");
		jpqlQuery.append(" WHERE i.idiomaPK.idPersona=:");
		jpqlQuery.append(obtenerNombreParametro(Idioma.ENTIDAD_IDIOMA_PK_ID_PERSONA));
		
		if (estadoRegistro) {
			jpqlQuery.append(" AND i.estadoRegistro=:");
			jpqlQuery.append(obtenerNombreParametro(Idioma.ENTIDAD_IDIOMA_ESTADO_REGISTRO));
		}
		
		jpqlQuery.append(" ORDER BY i.idiomaPK.nombre ");
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(obtenerNombreParametro(Idioma.ENTIDAD_IDIOMA_PK_ID_PERSONA), idPersona);
		
		if(estadoRegistro)
			query.setParameter(obtenerNombreParametro(Idioma.ENTIDAD_IDIOMA_ESTADO_REGISTRO), UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return query.getResultList();
	}
	
	/**
	 * ADM-C-021
	 * Crea o actualiza los idiomas de la lista:
	 * Crea los idiomas si no viene definido el identificador, de 
	 * lo contrario lo actualiza.
	 * @param lista
	 */
    public void crearOActualizarListaIdiomas(List<Idioma> lista) {
    	for(Idioma idioma : lista){
    		this.creaOActualizaIdioma(idioma);
        }    	
    }
    
    /**
     * ADM-C-021
     * Crea los idiomas d si no viene definido el identificador, de 
	 * lo contrario lo actualiza.
     * @param idioma
     */
    public void creaOActualizaIdioma(Idioma idioma){
    	if(idioma!=null){
    		Idioma idiomaBD = this.buscar(idioma.getIdiomaPK());
    		if(idiomaBD!=null){
    			this.actualizar(idioma);
    		}else{
    			this.crear(idioma);
    		}
    	}    	
    }
    
	/**
	 * ADM-C-021 Crea o actualiza los idiomas que se pasan como parámetro. El
	 * resto de idiomas que tenga la persona y que no vienen en la lista se
	 * inactivan.
	 * 
	 * @param dominios
	 *            Dominios de idioma a asignar a la persona
	 * @param idPersona
	 */
	public void crearOActualizarIdiomasPersona(List<Dominio> dominios, Long idPersona) {

		List<Idioma> idiomas = IdiomaDTO.transformarListaDominioAListaEntidades(dominios, idPersona);
		this.crearOActualizarListaIdiomas(idiomas);

		List<String> codigosIdiomas = DominioDTO.obtenerCodigosDominios(dominios);

		this.inactivarOtrosIdiomas(codigosIdiomas, idPersona);
	}

    // protected region metodos adicionales end
        
}

