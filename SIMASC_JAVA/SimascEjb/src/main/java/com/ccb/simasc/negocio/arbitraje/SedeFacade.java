package com.ccb.simasc.negocio.arbitraje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ccb.simasc.integracion.manejadores.ManejadorSede;
import com.ccb.simasc.transversal.dto.SedeDTO;
import com.ccb.simasc.transversal.entidades.Sede;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

@Stateless
@LocalBean
public class SedeFacade extends AbstractFacade<Sede> implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	ManejadorSede manejadorSede;

	@PersistenceContext(unitName = "SimascPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public SedeFacade() {
		super(Sede.class);
	}

	public List<SedeDTO> getBusquedaSedes() {
		List<Sede> listSede = null;
		List<SedeDTO> sedeDTOs = new ArrayList<>();

		try {
			listSede = manejadorSede.consultar(null, null, null);
			for (Sede sede : listSede) {				
				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equalsIgnoreCase(sede.getEstadoRegistro())) {
					SedeDTO sedeDTO = new SedeDTO();
					sedeDTO.setNombre(sede.getNombre());
					sedeDTO.setIdSede(sede.getIdSede());
					sedeDTO.setDireccion(sede.getDireccion());
					// sedeDTO.setTelefono(sede.getTelefono());
					sedeDTOs.add(sedeDTO);
				}
			}			
			if (!sedeDTOs.isEmpty()) {
				Collections.sort(sedeDTOs, new Comparator<SedeDTO>() {
			        @Override
			        public int compare(SedeDTO s1, SedeDTO s2) {
			            return  s1.getNombre().compareTo(s2.getNombre());
			        }
			    });
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO155.toString()),
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO150.toString())));
		}

		return sedeDTOs;
	}

}
