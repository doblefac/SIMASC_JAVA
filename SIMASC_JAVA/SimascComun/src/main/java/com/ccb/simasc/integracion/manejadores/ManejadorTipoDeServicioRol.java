package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.entidades.TipoDeServicioRol;
import com.ccb.simasc.transversal.entidades.TipoDeServicioRolPK;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad TipoDeServicioRol.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorTipoDeServicioRol extends ManejadorCrud<TipoDeServicioRol,TipoDeServicioRolPK>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
    
    public ManejadorTipoDeServicioRol() {
        super(TipoDeServicioRol.class);
    }
    
    /**
     * Retorna verdadero si el rol cuyo identificador se recibe por parámetro es un prestador
     * de servicios, retorna falso si no lo es
     * 
     * @author Javier Estévez
     * 
     */
    public boolean esPrestadorServicio(Long idRol){
    	
    	StringBuilder sbQuery = new StringBuilder();
    	sbQuery.append("SELECT count(t) "); 
    	sbQuery.append(" FROM TipoDeServicioRol t ");
    	sbQuery.append(" WHERE t.tipoDeServicioRolPK.tipoServicio =:").append(UtilConstantes.PARAM_TIPO_SERVICIO);
    	sbQuery.append(" AND t.tipoDeServicioRolPK.idRol =:").append(UtilConstantes.PARAM_ID_ROL);
    	sbQuery.append(" AND t.estadoRegistro =:").append(UtilConstantes.PARAM_ESTADO_REGISTRO);
    	
    	Query query = mp.createQuery(sbQuery.toString());
    	
    	query.setParameter(UtilConstantes.PARAM_TIPO_SERVICIO, 
    					UtilDominios.TIPO_SERVICIO_PRESTADOR_SERVICIO);
    	query.setParameter(UtilConstantes.PARAM_ID_ROL,
    					idRol);
    	query.setParameter(UtilConstantes.PARAM_ESTADO_REGISTRO,
    			UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	
    	Long resultado = (Long)query.getSingleResult();
    	
    	if(resultado != null && resultado.longValue() >= UtilConstantes.UNO){
    		return true;
    	}
    	
    	return false;
    	
    	
    }
    
    // protected region metodos adicionales on begin
    // Escriba en esta sección sus modificaciones

    /**
     * Consulta los tipos de servicio asociados al rol identificado por nombreRol
     * @param valorCampo corresponde al valor por el que se quiere filtrar. El tipo debe corresponder con el campo
     * Si es el id del rol debe ser númerico, si es el nombre del rol debe ser una cadena.
     * @param campo Atributo por el que se quiere filtrar, puede ser el nombre del rol (Rol.ENTIDAD_ROL_NOMBRE) o 
     * el id del rol (TipoDeServicioRol.ENTIDAD_TIPO_DE_SERVICIO_ROL_PK_ID_ROL)
     * @return
     */
    public List<TipoDeServicioRol> consultarTiposDeServicioRol(String valorCampo, String campo){
    	
    	StringBuilder jpqlQuery = new StringBuilder("SELECT t FROM TipoDeServicioRol t JOIN t.rol r ");
    	if(Rol.ENTIDAD_ROL_NOMBRE.equals(campo)){
    		jpqlQuery.append(" WHERE r.nombre=:").append(Rol.ENTIDAD_ROL_NOMBRE);
    	}else if(TipoDeServicioRol.ENTIDAD_TIPO_DE_SERVICIO_ROL_PK_ID_ROL.equals(campo)){
    		jpqlQuery.append(" WHERE t.tipoDeServicioRolPK.idRol=:").append(Rol.ENTIDAD_ROL_NOMBRE);
    	}    	
    	jpqlQuery.append("	AND r.estadoRegistro=:").append(Rol.ENTIDAD_ROL_ESTADO_REGISTRO_ROL);
    	jpqlQuery.append("	AND t.estadoRegistro=:").append(TipoDeServicioRol.ENTIDAD_TIPO_DE_SERVICIO_ROL_ESTADO_REGISTRO_TIPODESERVICIOROL);
    	
    	Query q = mp.createQuery(jpqlQuery.toString());
    	
    	if(TipoDeServicioRol.ENTIDAD_TIPO_DE_SERVICIO_ROL_PK_ID_ROL.equals(campo)){
    		q.setParameter(Rol.ENTIDAD_ROL_NOMBRE, Integer.valueOf(valorCampo));
    	}else if(Rol.ENTIDAD_ROL_NOMBRE.equals(campo)){
    		q.setParameter(Rol.ENTIDAD_ROL_NOMBRE, valorCampo);
    	} 
    	
    	q.setParameter(Rol.ENTIDAD_ROL_ESTADO_REGISTRO_ROL, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	q.setParameter(TipoDeServicioRol.ENTIDAD_TIPO_DE_SERVICIO_ROL_ESTADO_REGISTRO_TIPODESERVICIOROL, 
    						UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	
    	try {
    		return q.getResultList();
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }
    
    /** ADM-C-004 consulta los roles de prestadores
     * @param tipoServicio
     * @return List<Rol>
     */
    public List<Rol> consultarRolesPrestador( String tipoServicio ){
    	StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append(" SELECT DISTINCT(rol) FROM TipoDeServicioRol tsr ");
    	jpqlQuery.append(" JOIN tsr.rol rol ");
    	jpqlQuery.append(" WHERE tsr.estadoRegistro = : ").append(TipoDeServicioRol.ENTIDAD_TIPO_DE_SERVICIO_ROL_ESTADO_REGISTRO);
    	jpqlQuery.append(" AND rol.estadoRegistro = : ").append(Rol.ENTIDAD_ROL_ESTADO_REGISTRO);
    	if( tipoServicio != null )
    		jpqlQuery.append(" AND tsr.tipoDeServicioRolPK.tipoServicio =: ").append(Servicio.ENTIDAD_SERVICIO_TIPO);
    	jpqlQuery.append(" ORDER BY rol.nombre");
    	
    	Query query = mp.createQuery(jpqlQuery.toString());
    	query.setParameter( TipoDeServicioRol.ENTIDAD_TIPO_DE_SERVICIO_ROL_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO );
    	query.setParameter( Rol.ENTIDAD_ROL_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO );
    	if( tipoServicio != null )
    		query.setParameter( Servicio.ENTIDAD_SERVICIO_TIPO, tipoServicio );
    	return query.getResultList();
    }
    
    // protected region metodos adicionales end
    
}

