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
import com.ccb.simasc.integracion.manejadores.ManejadorRequisitoLista;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.RequisitoLista;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;

@Stateless
@LocalBean
public class RequisitoFacade extends AbstractFacade<Dominio> implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	ManejadorRequisitoLista manejadorRequisitoLista;

	@PersistenceContext(unitName = "SimascPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public RequisitoFacade() {
		super(Dominio.class);
	}

	public int getRequisitos(String idLista) {
		InformacionFiltro filtro = new InformacionFiltro(TipoFiltro.EXACTO, "lista.idLista", idLista);
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(filtro);
		int requisitos = 0;
		try {
			List<RequisitoLista> listRequisitosLista = manejadorRequisitoLista.consultar(filtros, null, null);
			requisitos = listRequisitosLista.size();

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO155.toString()),
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO150.toString())));
		}
		return requisitos;
	}

}
