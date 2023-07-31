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
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad ParametrosGenerales.
 * 
 * @author jsoto
 */
@Stateless(name="ManejadorParametrosGeneralesBean", mappedName="ManejadorParametrosGeneralesBean")
public class ManejadorParametrosGenerales extends ManejadorCrud<ParametrosGenerales,String>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	@PersistenceContext
	private transient EntityManager em;

	// protected region atributos end
    
    public ManejadorParametrosGenerales() {
        super(ParametrosGenerales.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    /**
     * Consulta los parametros por tipo
     * @param tipo
     * @return
     */
	public List<ParametrosGenerales> obtenerParametrosPorTipo(String tipo){
    	StringBuilder jpqlQuery = new StringBuilder();

    	jpqlQuery.append("SELECT p from ParametrosGenerales p ");
    	jpqlQuery.append(" WHERE p.estadoRegistro = :");
    	jpqlQuery.append(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_ESTADO_REGISTRO);
    	if(tipo!=null){
    		jpqlQuery.append(" AND  p.tipo= :");
    		jpqlQuery.append(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_TIPO);
    	}
    	Query query = mp.createQuery(jpqlQuery.toString()); 
    	if(tipo!=null){
    		query.setParameter(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_TIPO, tipo);
		}
		query.setParameter(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	return query.getResultList();
    	
    }
	
	/**
	 * Bloquea el registro de parametros generales cuyo código es MCON_REP. Esto se utiliza para
	 * manejar la concurrencia en el proceso de reparto. El registro se desbloquea cuando finalice la 
	 * transacción, es decir cuando el proceso de reparto finalice 
	 */
	public void iniciarProcesoReparto(){
		
		StringBuilder sbIniciar = new StringBuilder();
		
		sbIniciar.append(" select valor_numerico ");
		sbIniciar.append(" from  parametros_generales WITH (ROWLOCK, UPDLOCK)");
		sbIniciar.append(" where codigo = ?1 ");
		
		Query query = em.createNativeQuery(sbIniciar.toString());
		
		query.setParameter(UtilConstantes.UNO, UtilDominios.PARAMETRO_GENERAL_MANEJO_CONCURRENCIA_EN_REPARTO);
		
		query.getSingleResult();
		
		
	}
    
    /**
     * Obtiene el valor especificado de un parámetro general por código y tipo
     * 
     * @param codigo
     * @param tipo
     * @return
     */
	public Object obtenerValorParametroPorCodigoTipo(String codigo, String tipo, String valorRetorno) {
		StringBuilder jpqlQuery = new StringBuilder();

		jpqlQuery.append("SELECT p." + valorRetorno + " from ParametrosGenerales p");
		jpqlQuery.append(" WHERE p.codigo = :");
		jpqlQuery.append(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_PK);
		jpqlQuery.append(" AND p.tipo = :");
		jpqlQuery.append(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_TIPO);
		jpqlQuery.append(" AND p.estadoRegistro = :");
		jpqlQuery.append(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_PK, codigo);
		query.setParameter(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_TIPO, tipo);
		query.setParameter(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);

		Object valor;
		try{
			valor = query.getSingleResult();
		} catch (NonUniqueResultException e) {
			String mensajeError = String
					.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR200.toString()));
			throw new SimascException(mensajeError);
		} catch (NoResultException e) {
			valor = null;
		}
		return valor;
	}
	
	/**
	 * Obtiene un parámetro general por código y tipo
	 * 
	 * @param codigo
	 * @param tipo
	 * @return
	 */
	public ParametrosGenerales obtenerParametrosPorCodigoYTipo(String codigo, String tipo) {
		ParametrosGenerales parametrosGenerales = null;
		StringBuilder jpqlQuery = new StringBuilder();

		jpqlQuery.append("SELECT p from ParametrosGenerales p");
		jpqlQuery.append(" WHERE p.codigo = :");
		jpqlQuery.append(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_PK);
		jpqlQuery.append(" AND p.tipo = :");
		jpqlQuery.append(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_TIPO);
		jpqlQuery.append(" AND p.estadoRegistro = :");
		jpqlQuery.append(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_PK, codigo);
		query.setParameter(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_TIPO, tipo);
		query.setParameter(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);

		try {
			parametrosGenerales = (ParametrosGenerales) query.getSingleResult();
		} catch (Exception e) {
			parametrosGenerales = null;
		}

		return parametrosGenerales;
	}
    
	public ParametrosGenerales obtenerParametroPorNombre(String nombre) {
		ParametrosGenerales parametrosGenerales = null;
		StringBuilder jpqlQuery = new StringBuilder();

		jpqlQuery.append("SELECT p from ParametrosGenerales p");
		jpqlQuery.append(" WHERE p.nombre = :");
		jpqlQuery.append(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_NOMBRE);
		jpqlQuery.append(" AND p.estadoRegistro = :");
		jpqlQuery.append(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_NOMBRE, nombre);
		query.setParameter(ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);

		try {
			parametrosGenerales = (ParametrosGenerales) query.getSingleResult();
		} catch (Exception e) {
			parametrosGenerales = null;
		}

		return parametrosGenerales;
	}	

	public ParametrosGenerales actualizarParametrosGenerales(ParametrosGenerales parametroGeneral) {
		return (ParametrosGenerales) mp.updateObject(parametroGeneral);

	}

    // protected region metodos adicionales end
        
}

