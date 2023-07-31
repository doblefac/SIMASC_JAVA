package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta sección sus modificaciones


import java.io.Serializable;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.DominioPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad DominioDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class DominioPKDTO extends IDTO<DominioPK> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	private static final long serialVersionUID = 8357035024479665750L;

	private String dominio;		
	private String codigo;			
	
    public DominioPKDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }
		
	public DominioPKDTO(String dominio, String codigo) {
		super();
		this.dominio = dominio;
		this.codigo = codigo;
	}

	public String getCodigo(){
		return this.codigo;
	}
	
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
	
	
    public String getDominio() {
		return dominio;
	}


	public void setDominio(String dominio) {
		this.dominio = dominio;
	}


	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
   
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((dominio == null) ? 0 : dominio.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DominioPKDTO other = (DominioPKDTO) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (dominio == null) {
			if (other.dominio != null)
				return false;
		} else if (!dominio.equals(other.dominio))
			return false;
		return true;
	}


	@Override
	public DominioPKDTO transformarSinDependencias(DominioPK obj) {
		DominioPKDTO dominioPKDTO = new DominioPKDTO();
		
		dominioPKDTO.setDominio(obj.getDominio());
		dominioPKDTO.setCodigo(obj.getCodigo());		
		return dominioPKDTO;
	}

	@Override
	public IDTO transformarConDependencias(DominioPK obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DominioPK transformarEntidadSinDependencias(DominioPK obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DominioPK transformarEntidadConDependencias(DominioPK obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection transformarColeccionConDependencias(Collection<DominioPK> coleccion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection transformarColeccionSinDependencias(Collection<DominioPK> coleccion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<DominioPK> coleccion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<DominioPK> coleccion) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// protected region metodos adicionales end

}
