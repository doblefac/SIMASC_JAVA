package com.ccb.simasc.integracion.manejadores;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.MotivoHabilitacionArbitro;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

@Stateless
public class ManejadorMotivoHabilitacionArbitro extends ManejadorCrud<MotivoHabilitacionArbitro, Long>{
	
	@PersistenceContext
	private transient EntityManager em;

    public ManejadorMotivoHabilitacionArbitro() {
        super(MotivoHabilitacionArbitro.class);
    }  
	
    @SuppressWarnings("unchecked")
	public List<MotivoHabilitacionArbitro> findAll(){
		
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT DISTINCT m FROM MotivoHabilitacionArbitro m , Dominio d  ");
		jpqlQuery.append(" where m.codigoInactivacion = d.dominioPK.codigo and  m.estadoRegistro = ?1  and d.dominioPK.dominio = ?2 ") ;

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(1, "ACT");
		query.setParameter(2, UtilDominios.DOMINIO_MOTIVOS_INACTIVACION_ARBITRO);
		
		return query.getResultList();
	}
	
	public MotivoHabilitacionArbitro findbyCodigoInactivacion(Dominio codigoInactivacion){

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select * from MOTIVOS_HABILITACION_ARBITRO m where codigo_inactivacion = ?1 ");

		
		Query query = em.createNativeQuery(nativeQuery.toString(), MotivoHabilitacionArbitro.class);
		query.setParameter(1, codigoInactivacion.getDominioPK().getCodigo());
				
		return (MotivoHabilitacionArbitro) query.getSingleResult();
	}
	
	public boolean validarSiExiste(Dominio codigoInactivacion){

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select count(mha_codigo) from MOTIVOS_HABILITACION_ARBITRO m where codigo_inactivacion = ?1 ");

		
		Query query = em.createNativeQuery(nativeQuery.toString(), Integer.class);
		query.setParameter(1, codigoInactivacion.getDominioPK().getCodigo());
		
		int valor =  (int) query.getSingleResult();
		return valor > 0;
	}
	
	public void crearMotivoHabilitacionArbitro(MotivoHabilitacionArbitro motivo) {
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append("INSERT INTO MOTIVOS_HABILITACION_ARBITRO ( codigo_inactivacion, "
				+ "id_usuario_modificacion , fecha_ultima_modificacion , estado_registro) values ( "
				+ " ?1 , ?2 ,?3 , ?4 ) ");
		Query query = em.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, motivo.getCodigoInactivacion().getDominioPK().getCodigo());
		query.setParameter(2, motivo.getIdUsuarioModificacion());
		query.setParameter(3, motivo.getFechaUltimaModificacion());
		query.setParameter(4, motivo.getEstadoRegistro());
		query.executeUpdate();
	}
	
}
