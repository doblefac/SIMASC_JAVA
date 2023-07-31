package com.ccb.simasc.negocio.arbitraje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorDominio;
import com.ccb.simasc.integracion.manejadores.ManejadorMateria;
import com.ccb.simasc.integracion.manejadores.ManejadorServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorServicioMateria;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.transversal.dto.MateriaDTO;
import com.ccb.simasc.transversal.entidades.Materia;
import com.ccb.simasc.transversal.entidades.ServicioMateria;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

@Stateless
@LocalBean
public class MateriaFacade extends AbstractFacade<Materia> implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	ManejadorDominio manejadorDominio;

	@EJB
	ManejadorMateria manejadorMateria;

	@EJB
	ManejadorServicioMateria manejadorServicioMateria;

	@EJB
	ManejadorServicio manejadorServicio;

	@PersistenceContext(unitName = "SimascPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public MateriaFacade() {
		super(Materia.class);
	}

	public List<MateriaDTO> getBusquedaMaterias() {
		List<Materia> listMaterias = null;
		List<MateriaDTO> materiaDTOs = new ArrayList<>();
		try {

			listMaterias = manejadorMateria.consultar(null, null, null);
			for (Materia materia : listMaterias) {
				MateriaDTO materiaDTO = new MateriaDTO();
				materiaDTO.setNombre(materia.getNombre());
				materiaDTO.setIdMateria(materia.getIdMateria());
				materiaDTO.setObservaciones(materia.getObservaciones());
				materiaDTOs.add(materiaDTO);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO155.toString()),
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO156.toString())));
		}
		return materiaDTOs;
	}

	public List<MateriaDTO> getBusquedaMateriasXCriterio(String campo, String criterio) {
		List<Materia> listMaterias = null;
		List<MateriaDTO> materiaDTOs = new ArrayList<>();
		InformacionFiltro filtro = new InformacionFiltro(TipoFiltro.EXACTO, campo, criterio);
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(filtro);

		try {

			listMaterias = manejadorMateria.consultar(filtros, null, null);
			for (Materia materia : listMaterias) {
				MateriaDTO materiaDTO = new MateriaDTO();
				materiaDTO.setNombre(materia.getNombre());
				// materiaDTO.setId(materia.getId());
				materiaDTO.setObservaciones(materia.getObservaciones());
				materiaDTOs.add(materiaDTO);

			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO155.toString()),
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO156.toString())));
		}

		return materiaDTOs;
	}

	public List<MateriaDTO> getBusquedaMateriasXServicio(String campo, String criterio) {
		List<MateriaDTO> materiaDTOs = new ArrayList<>();
		List<ServicioMateria> listServicioMaterias = null;
		InformacionFiltro filtro = new InformacionFiltro(TipoFiltro.EXACTO, campo, criterio);
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(filtro);

		try {

			listServicioMaterias = manejadorServicioMateria.consultar(filtros, null, null);
			for (ServicioMateria servicioMateria : listServicioMaterias) {
				MateriaDTO materiaDTO = new MateriaDTO();
				materiaDTO.setNombre(servicioMateria.getMateria().getNombre());
				materiaDTO.setIdMateria(servicioMateria.getMateria().getIdMateria());
				materiaDTO.setObservaciones(servicioMateria.getMateria().getObservaciones());
				materiaDTOs.add(materiaDTO);

			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO155.toString()),
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO156.toString())));
		}

		return materiaDTOs;
	}
	
	public List<MateriaDTO> getBusquedaMateriasXServicioActivo(String campo, String criterio) {
		List<MateriaDTO> materiaDTOs = new ArrayList<>();
		List<ServicioMateria> listServicioMaterias = null;
		InformacionFiltro filtro = new InformacionFiltro(TipoFiltro.EXACTO, campo, criterio);
		InformacionFiltro filtroActivo = new InformacionFiltro(TipoFiltro.EXACTO, ServicioMateria.ENTIDAD_SERVICIO_MATERIA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(filtro);
		filtros.add(filtroActivo);
		try {

			listServicioMaterias = manejadorServicioMateria.consultar(filtros, null, null);
			for (ServicioMateria servicioMateria : listServicioMaterias) {
				MateriaDTO materiaDTO = new MateriaDTO();
				materiaDTO.setNombre(servicioMateria.getMateria().getNombre());
				materiaDTO.setIdMateria(servicioMateria.getMateria().getIdMateria());
				materiaDTO.setObservaciones(servicioMateria.getMateria().getObservaciones());
				materiaDTOs.add(materiaDTO);

			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO155.toString()),
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO156.toString())));
		}

		return materiaDTOs;
	}

}
