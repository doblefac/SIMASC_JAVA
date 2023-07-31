package com.ccb.simasc.negocio.arbitraje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorServicio;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.transversal.dto.ServicioDTO;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

@Stateless
@LocalBean
public class ServicioFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CasoFacade.class.getName());

	@EJB
	ManejadorServicio manejadorServicio;
	
	public List<ServicioDTO> getBusquedaServicios(){	
		InformacionFiltro filtro = new InformacionFiltro(TipoFiltro.EXACTO, Servicio.ENTIDAD_SERVICIO_TIPO, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		List<InformacionFiltro> filtrosServicio = new ArrayList<>();
		filtrosServicio.add(filtro);
		
		List<Servicio> servicio = manejadorServicio.consultar(filtrosServicio, null, null);	
		List<ServicioDTO> servicioDTOs = new ArrayList<ServicioDTO>();
		
		for(Servicio sev:servicio){
			ServicioDTO newServicio= new ServicioDTO();
			newServicio.setEstadoRegistro(sev.getEstadoRegistro());
			newServicio.setFechaUltimaModificacion(sev.getFechaUltimaModificacion());
			newServicio.setIdServicio(sev.getIdServicio());
			newServicio.setIdUsuarioModificacion(sev.getIdUsuarioModificacion());
			newServicio.setNombre(sev.getNombre());
			newServicio.setObservaciones(sev.getObservaciones());
			servicioDTOs.add(newServicio);
		}
		
		return servicioDTOs;
	}
	
	public List<ServicioDTO> getBusquedaServiciosParametroSorteo(){						
		return manejadorServicio.consultarServiciosPorParametroServicioSorteo(1L, 1L, 1L);						
	}
	
}
