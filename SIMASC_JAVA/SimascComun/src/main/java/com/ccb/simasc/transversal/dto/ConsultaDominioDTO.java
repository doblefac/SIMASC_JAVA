package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

// protected region imports dto end
/**
 * ADM-C-021
 * DAO que contiene información para consultar un dominio ya sea por su nombre o por el clasificador.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ConsultaDominioDTO implements Serializable{	
           
	private static final long serialVersionUID = 931416882994931636L;
	
	//Nombre del dominio a consultar (Si se define dominio y codigo clasificador este campo se omite en la busqueda).
	private String dominioAConsultar;       
    private String codigoClasificador;       
    private String dominioClasificador;
    
    public ConsultaDominioDTO(){
    	
    }
    
	public ConsultaDominioDTO(String dominioAConsultar, String codigoClasificador, String dominioClasificador) {
		this.dominioAConsultar = dominioAConsultar;
		this.codigoClasificador = codigoClasificador;
		this.dominioClasificador = dominioClasificador;
	}
	
	public String getDominioAConsultar() {
		return dominioAConsultar;
	}
	public void setDominioAConsultar(String dominioAConsultar) {
		this.dominioAConsultar = dominioAConsultar;
	}
	public String getCodigoClasificador() {
		return codigoClasificador;
	}
	public void setCodigoClasificador(String codigoClasificador) {
		this.codigoClasificador = codigoClasificador;
	}
	public String getDominioClasificador() {
		return dominioClasificador;
	}
	public void setDominioClasificador(String dominioClasificador) {
		this.dominioClasificador = dominioClasificador;
	}  
    
    

}
