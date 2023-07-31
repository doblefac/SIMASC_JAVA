package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.HomologacionSistemaExterno;
import com.ccb.simasc.transversal.entidades.HomologacionSistemaExternoPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad HomologacionSistemaExterno.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorHomologacionSistemaExterno extends ManejadorCrud<HomologacionSistemaExterno,HomologacionSistemaExternoPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	@PersistenceContext
	private transient EntityManager em;
    
    public ManejadorHomologacionSistemaExterno() {
        super(HomologacionSistemaExterno.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    public String consultarCodigosSistemaExterno(String sistemaExterno ,String codigo, String Dominio) {
    	
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT hse FROM HomologacionSistemaExterno hse ");
		jpqlQuery.append(" WHERE hse.homologacionSistemaExternoPK.codigoSimasc=:").append("codigoSimasc");
		jpqlQuery.append(" AND hse.homologacionSistemaExternoPK.dominioSimasc=:").append("dominioSimasc");
		jpqlQuery.append(" AND hse.homologacionSistemaExternoPK.sistemaExterno=:").append("sistemaExterno");
		jpqlQuery.append(" AND hse.estadoRegistro=:").append("estadoRegistro");
		Query query = mp.createQuery(jpqlQuery.toString());		
		query.setParameter("codigoSimasc", codigo);
		query.setParameter("dominioSimasc", Dominio);
		query.setParameter("sistemaExterno", sistemaExterno);
		query.setParameter("estadoRegistro", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		List<HomologacionSistemaExterno> homologacionSistemaExterno = query.getResultList();
		
		if(homologacionSistemaExterno != null && !homologacionSistemaExterno.isEmpty()) {
			return homologacionSistemaExterno.get(0).getHomologacionSistemaExternoPK().getCodigoSistemaExterno();
		}
		
		return null;
		
	}
    
    public String consultarCodigosSimasc(String sistemaExterno ,String codigo, String Dominio) {
    	
    	String homologacion;   	
    		
    	try{
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT hse FROM HomologacionSistemaExterno hse ");
		jpqlQuery.append(" WHERE hse.homologacionSistemaExternoPK.codigoSistemaExterno=:").append("codigoSistemaExterno");
		jpqlQuery.append(" AND hse.homologacionSistemaExternoPK.dominioSimasc=:").append("dominioSimasc");
		jpqlQuery.append(" AND hse.homologacionSistemaExternoPK.sistemaExterno=:").append("sistemaExterno");
		jpqlQuery.append(" AND hse.estadoRegistro=:").append("estadoRegistro");
		Query query = mp.createQuery(jpqlQuery.toString());		
		query.setParameter("codigoSistemaExterno", codigo);
		query.setParameter("dominioSimasc", Dominio);
		query.setParameter("sistemaExterno", sistemaExterno);
		query.setParameter("estadoRegistro", UtilDominios.ESTADO_REGISTRO_ACTIVO);		

		homologacion = ((HomologacionSistemaExterno) query.getSingleResult()).getHomologacionSistemaExternoPK().getCodigoSimasc();
    	}catch(NonUniqueResultException | NoResultException e){
    		homologacion = null;
    	}
		
		return homologacion;

	}
    
    @SuppressWarnings("unchecked")
	public List<HomologacionSistemaExterno> consultarHomologacionesPorSistemaExterno(String sistemaExterno) {
    	
    	List<HomologacionSistemaExterno>  homologaciones;   	
    		
    	try{
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT hse FROM HomologacionSistemaExterno hse ");
		jpqlQuery.append(" WHERE hse.homologacionSistemaExternoPK.sistemaExterno=:").append("sistemaExterno");
		jpqlQuery.append(" AND hse.estadoRegistro=:").append("estadoRegistro");
		Query query = mp.createQuery(jpqlQuery.toString());		
		query.setParameter("sistemaExterno", sistemaExterno);
		query.setParameter("estadoRegistro", UtilDominios.ESTADO_REGISTRO_ACTIVO);		

		homologaciones = query.getResultList();
    	}catch(NonUniqueResultException | NoResultException e){
    		homologaciones = null;
    	}
		
		return homologaciones;

	}
    
    public HomologacionSistemaExterno obtenerHomologacionPorSistemaExterno(HomologacionSistemaExterno homologacionSisExterno) {
    	
    	HomologacionSistemaExterno homologacion;   	
    		
    	try{
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT hse FROM HomologacionSistemaExterno hse ");
		jpqlQuery.append(" WHERE hse.homologacionSistemaExternoPK.dominioSimasc=:").append("dominioSimasc");
		jpqlQuery.append(" AND hse.homologacionSistemaExternoPK.codigoSimasc=:").append("codigoSimasc");
		jpqlQuery.append(" AND hse.homologacionSistemaExternoPK.sistemaExterno=:").append("sistemaExterno");
		Query query = mp.createQuery(jpqlQuery.toString());		
		query.setParameter("dominioSimasc", homologacionSisExterno.getHomologacionSistemaExternoPK().getDominioSimasc());
		query.setParameter("codigoSimasc", homologacionSisExterno.getHomologacionSistemaExternoPK().getCodigoSimasc());
		query.setParameter("sistemaExterno", homologacionSisExterno.getHomologacionSistemaExternoPK().getSistemaExterno());

		homologacion = (HomologacionSistemaExterno) query.getSingleResult();
    	}catch(NonUniqueResultException | NoResultException e){
    		homologacion = null;
    	}
		
		return homologacion;

	}
    
	public void actulizarHomologacionSistemaExterno(HomologacionSistemaExterno homologacionSisExterno) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("UPDATE HOMOLOGACION_SISTEMA_EXTERNO ");
		nativeQuery.append("SET codigo_sistema_externo = ?1 ,");
		nativeQuery.append(" id_usuario_modificacion = ?2 ,");
		nativeQuery.append(" fecha_ultima_modificacion = ?3 , ");
		nativeQuery.append(" estado_registro = ?4 ");
		nativeQuery.append("WHERE sistema_externo = ?5 AND codigo_simasc = ?6 ");
		Query query = em.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, homologacionSisExterno.getHomologacionSistemaExternoPK().getCodigoSistemaExterno());
		query.setParameter(2, homologacionSisExterno.getIdUsuarioModificacion());
		query.setParameter(3, homologacionSisExterno.getFechaUltimaModificacion());
		query.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(5, homologacionSisExterno.getHomologacionSistemaExternoPK().getSistemaExterno());
		query.setParameter(6, homologacionSisExterno.getHomologacionSistemaExternoPK().getCodigoSimasc());
		query.executeUpdate();
	}

	public void desactivarHomologacionSistemaExterno(HomologacionSistemaExterno homologacionSisExterno) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("UPDATE HOMOLOGACION_SISTEMA_EXTERNO ");
		nativeQuery.append("SET estado_registro = ?1 ,");
		nativeQuery.append(" id_usuario_modificacion = ?2 ,");
		nativeQuery.append(" fecha_ultima_modificacion = ?3 ");
		nativeQuery.append("WHERE sistema_externo = ?4 AND codigo_simasc = ?5 ");
		Query query = em.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_INACTIVO);
		query.setParameter(2, homologacionSisExterno.getIdUsuarioModificacion());
		query.setParameter(3, homologacionSisExterno.getFechaUltimaModificacion());
		query.setParameter(4, homologacionSisExterno.getHomologacionSistemaExternoPK().getSistemaExterno());
		query.setParameter(5, homologacionSisExterno.getHomologacionSistemaExternoPK().getCodigoSimasc());
		query.executeUpdate();
	}
    
    // protected region metodos adicionales end
        
}

