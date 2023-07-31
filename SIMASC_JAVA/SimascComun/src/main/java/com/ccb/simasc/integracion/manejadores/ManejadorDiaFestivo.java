package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.DiaFestivoDTO;
import com.ccb.simasc.transversal.entidades.DiaFestivo;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad DiaFestivo.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorDiaFestivo extends ManejadorCrud<DiaFestivo,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	
	// protected region atributos end
    
    public ManejadorDiaFestivo() {
        super(DiaFestivo.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    public List<Date> obtenerFestivosAnio( int anio ){
    	
    	StringBuilder nativeQuery = new StringBuilder();

		nativeQuery.append(" SELECT df.fecha FROM Dia_Festivo df ");
		nativeQuery.append(" WHERE df.estado_registro = ?1 ");
		nativeQuery.append(" AND YEAR(df.fecha) = ?2 ");
		nativeQuery.append(" ORDER BY df.fecha ");
	
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), Date.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, anio);

		return query.getResultList();
    }
    
    /** 
     * @author prendon
     * Metodo que busca una fecha en la tabla dia_festivo 
     * @param fecha
     * @return DiaFestivo, si es null es porque no encontró el dia que se estaba buscando.
     */
    public DiaFestivo consultarFecha( Date fecha ){
    	DiaFestivo diaFestivo;
    	StringBuilder nativeQuery = new StringBuilder();
    	nativeQuery.append("SELECT * FROM Dia_Festivo df ");
    	nativeQuery.append(" WHERE df.estado_registro = ?1 ");
    	nativeQuery.append(" AND CAST( df.fecha as date ) = CAST( ?2 as date ) ");

		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), DiaFestivo.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, fecha);
		try{
			diaFestivo = (DiaFestivo) query.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			diaFestivo = null;
		}
		return diaFestivo;
    }
    
    /**
     * Método que realiza los calculos para obtener los dias habiles entre dos fechas dadas
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    public int obtenerDiasEntreDosFechas(Date fechaInicial, Date fechaFinal) {
    	int cantidadDias = 0;
    	StringBuilder nativeQuery = new StringBuilder();
    	nativeQuery.append("select dbo.diasHabilesEntreDosFechas(?1, ?2) ");
    	
    	Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), Integer.class);
		query.setParameter(1, fechaInicial);
		query.setParameter(2, fechaFinal);
		try{
			cantidadDias = (Integer) query.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {}
		
		return Math.abs(cantidadDias);
    }
    
    /**
     * Método que realiza los calculos para restar dias habiles a una fecha
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    public Date restarDiasHabilesAFecha(Date fechaInicial, Long dias) {
    	Date fechaHabil = null;
    	StringBuilder nativeQuery = new StringBuilder();
    	nativeQuery.append("select dbo.RestarDiasHabiles(?1, ?2) ");
    	
    	Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), Date.class);
    	query.setParameter(1, fechaInicial);
    	query.setParameter(2, dias);
    	try{
    		fechaHabil = (Date) query.getSingleResult();
    	} catch (NoResultException | NonUniqueResultException e) {}
    	
    	return fechaHabil;
    }
    
    /**
     * Método que realiza los calculos para obtener los dias habiles entre dos fechas dadas
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    public Date sumarDiasHabilesAFecha(Date fechaInicial, Long dias) {
    	Date fechaHabil = null;
    	StringBuilder nativeQuery = new StringBuilder();
    	nativeQuery.append("select dbo.SumarDiasHabiles(?1, ?2) ");
    	
    	Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), Date.class);
		query.setParameter(1, fechaInicial);
		query.setParameter(2, dias);
		try{
			fechaHabil = (Date) query.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {}
		
		return fechaHabil;
    }
    
    /**
     * Método que realiza los calculos para obtener los dias entre dos fechas
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    public int calcularDiasEntreDosFechas(Date fechaInicial, Date fechaFinal) {
    	int cantidadDias = 0;
    	StringBuilder nativeQuery = new StringBuilder();
    	nativeQuery.append("select dbo.calcularDiasEntreDosFechas(?1, ?2) ");
    	
    	Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), Integer.class);
		query.setParameter(1, fechaInicial);
		query.setParameter(2, fechaFinal);
		try{
			cantidadDias = (Integer) query.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {}
		
		return Math.abs(cantidadDias);
    }
    
    
    /**
     * Método que consulta todos los festivos 
     * @return
     */
    public List<DiaFestivoDTO> consultarDiasFestivos() {
    
    	StringBuilder nativeQuery = new StringBuilder();
    	nativeQuery.append("select * from dia_festivo ");
    	nativeQuery.append(" where estado_registro=?1 ");
    	
    	Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), DiaFestivo.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();
    }
    
    /**
     * Método que obtiene el listado de fechas que se encuentran marcados 
     * como festivos en la tabla DIA_FESTIVO entre las fechas especificadas
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    public List<Date> consultarFestivosEntreFecha(Date fechaInicial, Date fechaFinal) {
    	StringBuilder builder = new StringBuilder();
    	builder.append("select fecha from DIA_FESTIVO ");
    	builder.append("where cast(fecha as date) between cast(?1 as date) and cast(?2 as date) ");
    	builder.append("and estado_registro = ?3");
    	
    	Query query = getEntityManager().createNativeQuery(builder.toString());
    	query.setParameter(1, fechaInicial);
    	query.setParameter(2, fechaFinal);
    	query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	
    	return query.getResultList();
    }

	public int validarSiFechaEsHabil(Date fecha) {
    	int cantidadDias = 0;
    	StringBuilder nativeQuery = new StringBuilder();
    	nativeQuery.append("select dbo.diasHabilesEntreDosFechas(?1, ?2) ");
    	
    	Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), Integer.class);
		query.setParameter(1, fecha);
		query.setParameter(2, fecha);
		try{
			cantidadDias = (Integer) query.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {}
		
		return cantidadDias;
	}
    
    
    // protected region metodos adicionales end
        
}

