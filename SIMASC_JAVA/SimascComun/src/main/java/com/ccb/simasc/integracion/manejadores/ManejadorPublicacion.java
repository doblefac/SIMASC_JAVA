package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.enumeraciones.TipoOrdenamiento;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionOrdenamiento;
import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Publicacion;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Publicacion.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorPublicacion extends ManejadorCrud<Publicacion,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorPublicacion() {
        super(Publicacion.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    /***
     * consulta las publicaciones por idPersona
     * @param idPersona
     * @return
     */
    public List<Publicacion> consultarPublicacionesPersona(Long idPersona){
    	List<InformacionFiltro> filtros = new ArrayList<>();
    	filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, Publicacion.ENTIDAD_PUBLICACION_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO));
    	filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, Publicacion.ENTIDAD_PUBLICACION_ID_PERSONA, idPersona));
    	
    	List<InformacionOrdenamiento> ordenamiento = new ArrayList<>();
    	ordenamiento.add(new InformacionOrdenamiento(TipoOrdenamiento.ASCENDENTE, Publicacion.ENTIDAD_PUBLICACION_TIPO_PUBLICACION));
    	
    	return this.consultar(filtros, ordenamiento, null);
    }
    // protected region metodos adicionales end
        
}

