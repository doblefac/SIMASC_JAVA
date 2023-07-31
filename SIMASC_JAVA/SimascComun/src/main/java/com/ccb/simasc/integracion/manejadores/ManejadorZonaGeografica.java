package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.ZonaGeograficaDTO;
import com.ccb.simasc.transversal.entidades.ZonaGeografica;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad ZonaGeografica.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorZonaGeografica extends ManejadorCrud<ZonaGeografica,String>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
    
    public ManejadorZonaGeografica() {
        super(ZonaGeografica.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta sección sus modificaciones
    public List<ZonaGeografica> obtenerCiudadesPorPais(String idPais) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT ciudad FROM ZonaGeografica ciudad JOIN ciudad.tipoZonaGeografica tipoZona  ");
		jpqlQuery.append("WHERE tipoZona.nombre = :tipoZona AND NOT ciudad.zonaGeografica IS NULL AND ciudad.zonaGeografica.idZonaGeograficaPadre =:pais ");
		jpqlQuery.append("ORDER BY ciudad.nombre ");
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter("pais", idPais);
		query.setParameter("tipoZona", "CIUDAD");
		return query.getResultList();
    }
    
    public List<ZonaGeografica> obtenerCiudadesPorNacionalidad(String idZona) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT hijo FROM ZonaGeografica hijo ");
		jpqlQuery.append("WHERE hijo.idZonaGeograficaPadre in ");
		jpqlQuery.append("(select zg.idZonaGeografica from ZonaGeografica zg ");
		jpqlQuery.append("where zg.nombreNacionalidad=: idZona) ");
		jpqlQuery.append("ORDER BY hijo.nombre ");
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter("idZona", idZona);
		return query.getResultList();
		
    }
    
    /**
     * Consulta la zona geografica por nombre en los registros activos. Devuelve
     * nulo si no hay ninguna coincidencia.
     * 
     * @param nombre
     * @return
     */
    public ZonaGeografica obtenerZonaGeograficaPorNombre(String nombre){
    	List<InformacionFiltro> filtros = new ArrayList<>();
    	filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, ZonaGeografica.ENTIDAD_ZONA_GEOGRAFICA_NOMBRE, nombre));
    	filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, ZonaGeografica.ENTIDAD_ZONA_GEOGRAFICA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO));
    	
    	List<ZonaGeografica> zonasGeograficas = consultar(filtros, null, null);
    	ZonaGeografica zonaGeografica = null;
    	if(zonasGeograficas!=null && !zonasGeograficas.isEmpty()){
    		zonaGeografica = zonasGeograficas.get(0);
    	}
    	
    	return zonaGeografica;
    }
    
    /**
   	 * Método que consulta el nombre del pais de idZonaGeografica (Departamento)
   	 * @param List<String> idZonaGeograficas
   	 * @return List<ZonaGeograficaDTO>
   	 */
   	public List<ZonaGeograficaDTO> consultarNombrePaisPorDepartamento( List<String> idZonasGeograficas){
   	
   		StringBuilder nativeQuery = new StringBuilder();
   		nativeQuery.append(" SELECT pais.NOMBRE as nombre, ciu.id_zona_geografica as idZonaGeografica ");
   		nativeQuery.append(" FROM ZONA_GEOGRAFICA pais  ");
   		nativeQuery.append(" inner join ZONA_GEOGRAFICA dep on dep.id_zona_geografica_padre=pais.id_zona_geografica ");
   		nativeQuery.append(" inner join ZONA_GEOGRAFICA ciu on ciu.id_zona_geografica_padre=dep.id_zona_geografica ");
   		nativeQuery.append(" WHERE ciu.id_zona_geografica ").append(UtilConsultasSQL.clausulaInSQLStrings(idZonasGeograficas));
   		nativeQuery.append(" and pais.estado_registro=?1 ");
   		nativeQuery.append(" and dep.estado_registro=?1 ");
   		nativeQuery.append(" and ciu.estado_registro=?1 ");
   		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), ZonaGeograficaDTO.class);
   			
   		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
   	
   		
   	
   		return query.getResultList();
   	}

    // protected region metodos adicionales end
        
}

