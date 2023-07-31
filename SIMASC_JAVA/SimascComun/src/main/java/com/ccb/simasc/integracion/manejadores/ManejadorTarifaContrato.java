package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.TarifaContratoDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.ContratoConvenio;
import com.ccb.simasc.transversal.entidades.TarifaContrato;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad TarifaContrato.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorTarifaContrato extends ManejadorCrud<TarifaContrato,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorTarifaContrato() {
        super(TarifaContrato.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    @PersistenceContext
	private EntityManager em;
    
    
    @SuppressWarnings("unchecked")
	public List<TarifaContrato> consultarTarifasContrato(TarifaContratoDTO tarifa){
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT t ");
		jpqlQuery.append(" FROM TarifaContrato t ");
		
		jpqlQuery.append(" WHERE ");
		jpqlQuery.append("  t.estadoRegistro =:   ").append(TarifaContrato.ENTIDAD_TARIFA_CONTRATO_ESTADO_REGISTRO);
		if(tarifa.getCodigoContrato()!=null){
			jpqlQuery.append(" AND t.codigoContrato =:").append(TarifaContrato.ENTIDAD_TARIFA_CONTRATO_CODIGO_CONTRATO);
			
		}
		if(tarifa.getTipoTarifa()!=null){
			jpqlQuery.append(" AND  t.tipoTarifa =: ").append(TarifaContrato.ENTIDAD_TARIFA_CONTRATO_TIPO_TARIFA);
		}
		
		if(tarifa.getResultado()!=null){
			jpqlQuery.append(" AND  t.resultado =: ").append(TarifaContrato.ENTIDAD_TARIFA_CONTRATO_RESULTADO);
		}

		jpqlQuery.append(" order by t.idTarifaContrato desc ");
		
		Query query = em.createQuery(jpqlQuery.toString());
		
		query.setParameter(TarifaContrato.ENTIDAD_TARIFA_CONTRATO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if(tarifa.getCodigoContrato()!=null){
			query.setParameter(TarifaContrato.ENTIDAD_TARIFA_CONTRATO_CODIGO_CONTRATO, tarifa.getCodigoContrato());			
		}
		if(tarifa.getTipoTarifa()!=null){
			query.setParameter(TarifaContrato.ENTIDAD_TARIFA_CONTRATO_TIPO_TARIFA, tarifa.getTipoTarifa());	
		}
		if(tarifa.getResultado()!=null){
			query.setParameter(TarifaContrato.ENTIDAD_TARIFA_CONTRATO_RESULTADO, tarifa.getResultado());	
		}
		
		return query.getResultList();
		
	}
    
    /** CON-C-014, consulta las tarifas de un convenio
     * @param idConvenio
     * @return List<TarifaContrato>
     */
    public List<TarifaContrato> consultarTarifasContratoConvenio( Long idConvenio ){
    	StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append("SELECT t FROM TarifaContrato t ");
    	jpqlQuery.append(" JOIN t.contratoConvenio cc ");
    	jpqlQuery.append(" WHERE cc.idConvenio =: ").append(ContratoConvenio.ENTIDAD_CONTRATO_CONVENIO_ID_CONVENIO);
    	jpqlQuery.append(" AND t.estadoRegistro =: ").append(TarifaContrato.ENTIDAD_TARIFA_CONTRATO_ESTADO_REGISTRO);
    	jpqlQuery.append(" AND cc.estadoRegistro =: ").append(ContratoConvenio.ENTIDAD_CONTRATO_CONVENIO_ESTADO_REGISTRO);
    	
    	Query query = em.createQuery(jpqlQuery.toString());
		query.setParameter(ContratoConvenio.ENTIDAD_CONTRATO_CONVENIO_ID_CONVENIO, idConvenio);
		query.setParameter(TarifaContrato.ENTIDAD_TARIFA_CONTRATO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(ContratoConvenio.ENTIDAD_CONTRATO_CONVENIO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();
    }

    /** CON-C-014: Consulta los casos de convenio que se les va a hacer facturacion
	 * @param filtros
	 * @return
	 */
	public TarifaContrato consultarTarifaCaso( Caso caso, ContratoConvenio contrato ){
		TarifaContrato tarifaContrato = null;
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select tc.* from TARIFA_CONTRATO tc ");
		nativeQuery.append(" inner join CONTRATO_CONVENIO cc ");
		nativeQuery.append(" on cc.codigo_contrato = tc.codigo_contrato ");
		nativeQuery.append(" inner join CASO c ");
		nativeQuery.append(" on c.fecha_estado between cc.fecha_inicio and cc.fecha_fin ");	
		nativeQuery.append(" where cc.id_convenio = ?2 ");
		nativeQuery.append(" and tc.resultado = ?3 ");
		nativeQuery.append(" and c.id_caso = ?8 ");

		if( UtilDominios.CODIGO_TIPO_COMBINACION_COMPLETA.equals(contrato.getTipoCombinacion())
			|| UtilDominios.CODIGO_TIPO_COMBINACION_VOLUMEN.equals(contrato.getTipoCombinacion()) ){
			nativeQuery.append(" and ( select count( ca.id_caso ) ");
			nativeQuery.append(" from caso ca ");
			nativeQuery.append(" where ca.fecha_estado between ( SELECT DATEADD(month, DATEDIFF(month, 0, ?4 ), 0) ) ");
			nativeQuery.append(" and ( SELECT DATEADD(s,-1,DATEADD(mm, DATEDIFF(m,0, ?4 )+1,0)) ) ");
			nativeQuery.append(" and ca.estado_caso = ?5 ");
			nativeQuery.append(" and ca.id_servicio = ?7 ");
			nativeQuery.append(" and ca.estado_registro = ?1 ");
			nativeQuery.append(" and ca.id_convenio = ?2 ) between tc.minimo_casos and tc.maximo_casos ");
		}
		
		if( UtilDominios.CODIGO_TIPO_COMBINACION_COMPLETA.equals(contrato.getTipoCombinacion())
			|| UtilDominios.CODIGO_TIPO_COMBINACION_CUANTIA.equals(contrato.getTipoCombinacion()) ){
			nativeQuery.append(" and ?6 between tc.cuantia_minima and tc.cuantia_maxima ");
		}
		nativeQuery.append(" and tc.estado_registro = ?1 ");
		nativeQuery.append(" and cc.estado_registro = ?1 ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), TarifaContrato.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, contrato.getIdConvenio());
		query.setParameter(3, caso.getResultado());
		query.setParameter(4, caso.getFechaEstado());
		query.setParameter(5, UtilDominios.ESTADO_CASO_REGISTRADO);
		query.setParameter(6, caso.getValorPretensiones());
		query.setParameter(7, UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO);
		query.setParameter(8, caso.getIdCaso());
		
		try{
			tarifaContrato = (TarifaContrato) query.getSingleResult();
		} catch( NoResultException nre ){
			tarifaContrato = null;
		} catch( NonUniqueResultException nue ){
			throw new SIMASCNegocioExcepcion(String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO388.toString()),
					Arrays.asList(caso.getIdCaso()).toArray()));
		}
		return tarifaContrato;
	}
	
    // protected region metodos adicionales end
        
}

