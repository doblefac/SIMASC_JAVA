package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.LockModeType;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Carpeta;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Carpeta.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorCarpeta extends ManejadorCrud<Carpeta,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorCarpeta() {
        super(Carpeta.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
	public Carpeta actualizarCarpeta(Carpeta carpeta) {
		return (Carpeta) mp.updateObject(carpeta);
	}
	
	/**
	 * Obtiene carpeta por idCuaderno y idCaso
	 */
	@SuppressWarnings("unchecked")
	public Carpeta obtenerCarpetaCuadernoCaso (Long idCaso, Long idCuaderno){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("Select c from Carpeta c ");
		nativeQuery.append("where c.idCaso = :idCaso and c.idCuaderno = :idCuaderno");
		Query query = mp.getEntityManager().createQuery(nativeQuery.toString()).setLockMode(LockModeType.NONE);
		query.setParameter("idCaso", idCaso);
		query.setParameter("idCuaderno", idCuaderno);
		
		List<Carpeta> carpeta = query.getResultList();
		if(carpeta != null && !carpeta.isEmpty()){
			return carpeta.get(0);
		}else{
			return null;
		}
	}
    // protected region metodos adicionales end
       
	
	@SuppressWarnings("unchecked")
	public List<Carpeta> obtenerCudernosPorCaso (Long idCaso){
		
		Query query = mp.getEntityManager().createQuery("select c from Carpeta c , Cuaderno cu "
				+ " WHERE c.idCaso = :idCaso and c.idCuaderno = cu.idCuaderno and c.esCuaderno = :esCuaderno ");
		
		query.setParameter("idCaso", idCaso);
		query.setParameter("esCuaderno", true);
		
		return (List<Carpeta>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Carpeta> obtenerCarpetasySubcarpetas (Long idCarpetaContenedora){
		
		Query query = mp.getEntityManager().createQuery("select c from Carpeta c  "
				+ " WHERE c.idCarpetaContenedora = :idCarpetaContenedora and c.estadoRegistro = :estadoRegistro");
		
		query.setParameter("idCarpetaContenedora", idCarpetaContenedora);
		query.setParameter("estadoRegistro", UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return (List<Carpeta>) query.getResultList();
	}
}