package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.ValorPlantillaCarta;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad ValorPlantillaCarta.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorValorPlantillaCarta extends ManejadorCrud<ValorPlantillaCarta,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorValorPlantillaCarta() {
        super(ValorPlantillaCarta.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    public void actualizarValorPlantilla(String usuarioModificacion, String estado, Long idPlantillaCarta) {
    	StringBuilder nativeQuery = new StringBuilder();
    	nativeQuery.append("update VALOR_PLANTILLA_CARTA set estado_registro = ?1 ");
    	nativeQuery.append(", id_usuario_modificacion = ?2, fecha_ultima_modificacion = GETDATE() ");
    	nativeQuery.append("where id_plantilla_carta = ?3");
    	
    	Query query = getEntityManager().createNativeQuery(nativeQuery.toString());
    	query.setParameter(1, estado);
    	query.setParameter(2, usuarioModificacion);
    	query.setParameter(3, idPlantillaCarta);
    	query.executeUpdate();
    }
    
    public List<ValorPlantillaCarta> consultarValoresPlantilla(Long idPlantillaCarta) {
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select * from valor_plantilla_carta (nolock) ");
    	sql.append(" where id_plantilla_carta = ?1 ");
    	sql.append(" and estado_registro = ?2 ");
    	
    	Query query = getEntityManager().createNativeQuery(sql.toString(), ValorPlantillaCarta.class);
    	query.setParameter(1, idPlantillaCarta);
    	query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	
    	return query.getResultList();
    }
    // protected region metodos adicionales end
        
}

