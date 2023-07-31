package com.ccb.simasc.negocio.arbitraje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.ManejadorDominio;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.entidades.Persona;

@Stateless
@LocalBean
public class FuncionarioExternoFacade
		/* extends AbstractFacade<FuncionarioExterno> */ implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	ManejadorDominio manejadorDominio;

	@EJB
	ManejadorRol manejadorRol;

	@EJB
	ManejadorRolPersona manejadorRolPersona;

	@PersistenceContext
	private transient EntityManager em;

	/*
	 * @Override protected EntityManager getEntityManager() { return em; }
	 */

	public FuncionarioExternoFacade() {
		// super(FuncionarioExterno.class);
	}

	public List<PersonaDTO> getBusquedaFuncionarios(String tipoFuncionario) {
		
		List<PersonaDTO> funcionarioExternoDTOs = new ArrayList<PersonaDTO>();
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT DISTINCT p.* FROM PERSONA p ");
		nativeQuery.append(" LEFT JOIN ROL_PERSONA rp ON p.id_persona = rp.id_persona ");
		nativeQuery.append(" LEFT JOIN ROL r ON rp.id_rol = r.id_rol ");
		nativeQuery.append(" WHERE r.nombre =?1 ORDER BY p.primer_nombre_o_razon_social, p.primer_apellido  ");
		Query query = em.createNativeQuery(nativeQuery.toString(), Persona.class);
		query.setParameter(1, tipoFuncionario);
		funcionarioExternoDTOs = (List<PersonaDTO>) new PersonaDTO().transformarColeccionSinDependencias(query.getResultList());
		return funcionarioExternoDTOs;
	}
	
	

	/*
	 * public List<FuncionarioExternoDTO> getSecretarios(){ // pendiente por
	 * modificaciones de la base de datos List<FuncionarioExterno>
	 * listFuncionarios= null; List<FuncionarioExternoDTO>
	 * FuncionarioExternoDTOs = new ArrayList<>();
	 * 
	 * try{
	 * 
	 * listFuncionarios = manejadorFuncionarioExterno.consultar(null, null,
	 * null); for(FuncionarioExterno funcionario:listFuncionarios){
	 * FuncionarioExternoDTO FuncionarioExternoDTO = new
	 * FuncionarioExternoDTO(); FuncionarioExternoDTO = new
	 * FuncionarioExternoDTO();
	 * FuncionarioExternoDTO.setNombres(funcionario.getNombres());
	 * FuncionarioExternoDTO.setApellidos(funcionario.getApellidos());
	 * FuncionarioExternoDTOs.add(FuncionarioExternoDTO);
	 * 
	 * } }catch(Exception e){ FacesContext.getCurrentInstance().addMessage(null,
	 * new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!",
	 * "No existen funcionarios")); }
	 * 
	 * 
	 * 
	 * return FuncionarioExternoDTOs; }
	 * 
	 * 
	 * public List<FuncionarioExternoDTO> getArbitros(){ // pendiente por
	 * modificaciones de la base de datos List<FuncionarioExterno>
	 * listFuncionarios= null; List<FuncionarioExternoDTO>
	 * FuncionarioExternoDTOs = new ArrayList<>();
	 * 
	 * try{
	 * 
	 * listFuncionarios = manejadorFuncionarioExterno.consultar(null, null,
	 * null); for(FuncionarioExterno funcionario:listFuncionarios){
	 * FuncionarioExternoDTO FuncionarioExternoDTO = new
	 * FuncionarioExternoDTO(); FuncionarioExternoDTO = new
	 * FuncionarioExternoDTO();
	 * FuncionarioExternoDTO.setNombres(funcionario.getNombres());
	 * FuncionarioExternoDTO.setApellidos(funcionario.getApellidos());
	 * FuncionarioExternoDTOs.add(FuncionarioExternoDTO);
	 * 
	 * } }catch(Exception e){ FacesContext.getCurrentInstance().addMessage(null,
	 * new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!",
	 * "No existen funcionarios")); }
	 * 
	 * 
	 * 
	 * return FuncionarioExternoDTOs; }
	 */

}
