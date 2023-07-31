/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccb.simasc.negocio.arbitraje;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ccb.simasc.integracion.manejadores.ManejadorDominio;

/**
*
* @author jsoto
*/
@Stateless
@LocalBean
public class FuncionarioCcbFacade /*extends AbstractFacade<FuncionarioCcb>*/ implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB 
	ManejadorDominio manejadorDominio;


	//@EJB
	//ManejadorFuncionarioCcb manejadorFuncionarioCcb;

	
@PersistenceContext(unitName = "SimascPU")
   private EntityManager em;
/*
   @Override
   protected EntityManager getEntityManager() {
       return em;
   }
*/
   public FuncionarioCcbFacade() {
       //super(FuncionarioCcb.class);
   }	
   
   /*
	public List<FuncionarioCcbDTO> getBusquedaFuncionariosCCB(){	
		List<FuncionarioCcb> listFuncionarios= null;
		List<FuncionarioCcbDTO> funcionarioCcbDTOs = new ArrayList<>();

		try{

			listFuncionarios = manejadorFuncionarioCcb.consultar(null, null, null);
			for(FuncionarioCcb funcionario:listFuncionarios){
				FuncionarioCcbDTO funcionarioCcbDTO = new FuncionarioCcbDTO();
				funcionarioCcbDTO = new FuncionarioCcbDTO();
				funcionarioCcbDTO.setNombres(funcionario.getNombres());
				funcionarioCcbDTO.setApellidos(funcionario.getApellidos());
				funcionarioCcbDTO.setNumeroTarjetaProfesional(funcionario.getNumeroTarjetaProfesional());
				funcionarioCcbDTO.setFuncionarioCcbPK(funcionario.getFuncionarioCcbPK());
				funcionarioCcbDTOs.add(funcionarioCcbDTO);

			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "No existen funcionarios"));
		}
		
		
		
		return funcionarioCcbDTOs;
	}
*/

}