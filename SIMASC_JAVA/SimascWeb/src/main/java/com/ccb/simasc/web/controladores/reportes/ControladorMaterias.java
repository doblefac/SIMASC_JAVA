package com.ccb.simasc.web.controladores.reportes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.arbitraje.MateriaFacade;
import com.ccb.simasc.transversal.dto.MateriaDTO;
import com.ccb.simasc.transversal.entidades.ServicioMateria;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

public abstract class ControladorMaterias extends ControladorReporte {
	
	private static final Logger logger = LogManager.getLogger(ControladorMaterias.class.getName());
	protected List<MateriaDTO> materiaDTOs;
	
	@EJB
	MateriaFacade materiaFacade;
	
	@PostConstruct
	public void postConstruct() {		
		materiaDTOs = new ArrayList<>();
	}

	@PreDestroy
	public void preDestroy() {	
		materiaDTOs = new ArrayList<>();
	}
	
	public void cargarMaterias(ValueChangeEvent ev) {
		materiaDTOs = new ArrayList<>();
		try {
			if (ev.getNewValue() != null) {
				String tipoCasoSeleccionado = (String) ev.getNewValue();
				if (tipoCasoSeleccionado != null) {
					materiaDTOs = materiaFacade.getBusquedaMateriasXServicioActivo(
							ServicioMateria.ENTIDAD_SERVICIO_MATERIA_PK_ID_SERVICIO, tipoCasoSeleccionado);
					
					List<MateriaDTO> l3 = new ArrayList<>();
					for(MateriaDTO m :materiaDTOs) {
						if(m.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
							l3.add(m);
						}								
					}
					materiaDTOs = l3;
					this.ordenarMaterias();
				}
			}

		} catch (Exception e) {
			logger.error("Funcion cargarMaterias ----> ", e.getMessage());
		}
	}
	
	private void ordenarMaterias() {
		Collections.sort(materiaDTOs, new Comparator<MateriaDTO>() {
	        @Override
	        public int compare(MateriaDTO m1, MateriaDTO m2) {
	            return  m1.getNombre().compareTo(m2.getNombre());
	        }
	    });		
	}
	
	public List<MateriaDTO> getMateriaDTOs() {
		return materiaDTOs;
	}
	public void setMateriaDTOs(List<MateriaDTO> materiaDTOs) {
		this.materiaDTOs = materiaDTOs;
	}
}
