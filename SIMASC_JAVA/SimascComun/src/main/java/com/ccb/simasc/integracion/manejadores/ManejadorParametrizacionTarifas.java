package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones

import java.util.List;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.ParametrizacionTarifas;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.dto.ParametrizacionTarifasDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosTarifasDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosTarifasInternacionalesDTO;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad ParametrizacionTarifas.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorParametrizacionTarifas extends ManejadorCrud<ParametrizacionTarifas,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
    private EntityManager em;
	// protected region atributos end
    
    public ManejadorParametrizacionTarifas() {
        super(ParametrizacionTarifas.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    /**
     * Permite obtener el rango maximo para la tarifa y la cantidad de arbitros, teniendo en cuenta el servicio
     * @param tipoTarifa
     * @param tipoServicio
     * @param cantidadArbitros
     * @return
     */
    public BigDecimal obtenerRangoMaximo(String tipoTarifa, String tipoServicio, String cantidadArbitros) {
		Query q = em.createNativeQuery("SELECT isnull(max(rango_superior),0) "
				+ "FROM Parametrizacion_tarifas "
				+ "WHERE tipo_tarifa = ?1 "
				+ "and id_servicio in (select id_servicio from SERVICIO where tipo = ?2"
				+ " and estado_registro = ?3) "
				+ " and cantidad_arbitros = ?4 "
				+ " and estado_registro = ?3 ");
		q.setParameter(1, tipoTarifa);
		q.setParameter(2, tipoServicio);
		q.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(4, cantidadArbitros);
		return (BigDecimal) q.getSingleResult();
	}

    @SuppressWarnings("unchecked")
	public List<ParametrizacionTarifasDTO> consultarRangoTarifa(ParametrosTarifasDTO parametrosTarifasDTO, boolean consultarRango){
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append("select ");
		if(consultarRango){
			nativeQuery.append("top 1 ");
		}		 
		nativeQuery.append("cantidad_arbitros as cantidadArbitros, tipo_tarifa as tipoTarifa, moneda, tipo_valor_rango as tipoValorRango, ");
		nativeQuery.append("rango_inferior as rangoInferior, rango_superior as rangoSuperior, tipo_valor_minimo as tipoValorMinimo, ");
		nativeQuery.append("valor_minimo as valorMinimo, tipo_valor_maximo as tipoValorMaximo, valor_maximo as valorMaximo, ");
		nativeQuery.append("tipo_actor as tipoActor, pt.id_usuario_modificacion as idUsuarioModificacion, pt.fecha_ultima_modificacion as fechaUltimaModificacion, ");
		nativeQuery.append("pt.estado_registro as estadoRegistro, id_servicio as idServicio ");
		nativeQuery.append("from PARAMETRIZACION_TARIFAS pt ");
		nativeQuery.append("inner join parametros_generales pg on pt.tipo_valor_rango = pg.codigo ");
		nativeQuery.append("where cantidad_arbitros = ?1 ");
		nativeQuery.append("and tipo_tarifa = ?2 ");
		nativeQuery.append("and id_servicio = ?3 ");
		nativeQuery.append("and materia_consumo = ?4 ");
		if(consultarRango){
			if(parametrosTarifasDTO.isIndeterminado()){
				nativeQuery.append("and rango_inferior = rango_superior ");
			}else{
				if(parametrosTarifasDTO.getIdServicio().equals(UtilConstantes.ID_SERVICIO_AMIGABLE_COMPOSICION)) {
					 
					nativeQuery.append(" and ("+parametrosTarifasDTO.getCuantia()+"  between ");										
					nativeQuery.append(" (valor_numerico * rango_inferior) and (valor_numerico * rango_superior)) ");
					
				}else {
					nativeQuery.append("and CONVERT(DECIMAL(10,2),( ");
					nativeQuery.append(parametrosTarifasDTO.getCuantia());
					nativeQuery.append("/ valor_numerico)) >= rango_inferior ");
					nativeQuery.append("and CONVERT(DECIMAL(10,2),( ");
					nativeQuery.append(parametrosTarifasDTO.getCuantia());
					nativeQuery.append("/ valor_numerico)) <= rango_superior ");
				}				
			}			
		}else{
			nativeQuery.append("and rango_inferior <> rango_superior ");
		}		
		nativeQuery.append("and pt.estado_registro = ?5 ");
		nativeQuery.append("order by  rangoInferior");

		Query query = em.createNativeQuery(nativeQuery.toString(), ParametrizacionTarifasDTO.class);
		query.setParameter(1, parametrosTarifasDTO.getCantidadArbitros());
		query.setParameter(2, parametrosTarifasDTO.getTipoTarifa());
		query.setParameter(3, parametrosTarifasDTO.getIdServicio());
		query.setParameter(4, parametrosTarifasDTO.isArbitrajeConsumo());
		query.setParameter(5, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return query.getResultList();		
	}
    
    @SuppressWarnings("unchecked")
	public List<ParametrizacionTarifas> obtenerListadoTarifasPorMonto(ParametrosTarifasInternacionalesDTO parametrosTarifasInternacionalDTO, String tipoActor){
		
		StringBuilder nativeQuery = new StringBuilder();
				
		nativeQuery.append("SELECT * ");
		nativeQuery.append("FROM PARAMETRIZACION_TARIFAS pt ");
		nativeQuery.append("WHERE tipo_tarifa = ?1 ");
		nativeQuery.append("and tipo_actor = ?2 ");
		nativeQuery.append("and estado_registro = ?3 ");
		nativeQuery.append("and ?4 >= rango_inferior  ");
		nativeQuery.append("order by  rango_inferior");

		Query query = em.createNativeQuery(nativeQuery.toString(), ParametrizacionTarifas.class);
		query.setParameter(1, parametrosTarifasInternacionalDTO.getModeloTarifa());
		query.setParameter(2, tipoActor);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(4, parametrosTarifasInternacionalDTO.getMontoDisputa());
		
		return query.getResultList();	
	}
        
}

