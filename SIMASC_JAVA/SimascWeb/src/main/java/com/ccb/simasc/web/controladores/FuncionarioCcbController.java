package com.ccb.simasc.web.controladores;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ccb.simasc.negocio.arbitraje.FuncionarioCcbFacade;

@ManagedBean(name = "funcionarioCcbController")
@ViewScoped
public class FuncionarioCcbController implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @EJB
    private FuncionarioCcbFacade ejbFacade;
    
    public FuncionarioCcbController() {
    }
    
    public void imprimirConsola(){
    	System.out.println("-------------------------------  IMPRIMIENDO CONSOLA ----------------------------------");
    	if(ejbFacade!=null){
    		System.out.println("NOT NULL -------------------------------------------------------------------");
    	}else{
    		System.out.println("NULL -------------------------------------------------------------------");
    	}
    	System.out.println("-------------------------------  IMPRIMIENDO CONSOLA fin ----------------------------------");
    }

}
