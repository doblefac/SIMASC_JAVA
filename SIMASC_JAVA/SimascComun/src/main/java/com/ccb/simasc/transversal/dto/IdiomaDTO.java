package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.Idioma;
import com.ccb.simasc.transversal.entidades.IdiomaPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad IdiomaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class IdiomaDTO extends IDTO<Idioma> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private IdiomaPK idiomaPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public IdiomaDTO(){
		idiomaPK = new IdiomaPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public IdiomaPK getIdiomaPK(){
		return this.idiomaPK;
	}
	
	public void setIdiomaPK(IdiomaPK idiomaPK){
		this.idiomaPK   = idiomaPK ;
	}  
	
	public String getIdUsuarioModificacion(){
		return this.idUsuarioModificacion;
	}
	
	public void setIdUsuarioModificacion(String idUsuarioModificacion){
		this.idUsuarioModificacion = idUsuarioModificacion;
	}
		
	public Date getFechaUltimaModificacion(){
		return this.fechaUltimaModificacion;
	}
	
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion){
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
		
	public String getEstadoRegistro(){
		return this.estadoRegistro;
	}
	
	public void setEstadoRegistro(String estadoRegistro){
		this.estadoRegistro = estadoRegistro;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idiomaPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad IdiomaDTO que se pasa
     * como parametro comprobando que comparten los mismos valores en cada uno
     * de sus atributos. Solo se tienen en cuenta los atributos simples, es
     * decir, se omiten aquellos que definen una relacion con otra tabla.
     *
     * @param obj Instancia de la categoría a comprobar
     * iguales.
     * @return Verdadero si esta instancia y la que se pasan como parametros son
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IdiomaDTO other = (IdiomaDTO) obj;
                
        if (!Objects.equals(this.idiomaPK, other.idiomaPK)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
    
    @Override
	public IdiomaDTO transformarSinDependencias(Idioma obj) {
		IdiomaDTO idiomaDTO = new IdiomaDTO();
		
		idiomaDTO.setIdiomaPK(obj.getIdiomaPK());
		idiomaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		idiomaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		idiomaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return idiomaDTO;
	}

	@Override
	public IdiomaDTO transformarConDependencias(Idioma obj) {
		IdiomaDTO idiomaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return idiomaDTO;
	}

	@Override
	public Idioma transformarEntidadSinDependencias(Idioma obj) {
		Idioma idioma = new Idioma();
		
		idioma.setIdiomaPK(obj.getIdiomaPK());
		
		idioma.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		idioma.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		idioma.setEstadoRegistro(obj.getEstadoRegistro());
		
		return idioma;
	}


	@Override
	public Idioma transformarEntidadConDependencias(Idioma obj) {
		Idioma idioma = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return idioma;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Idioma> coleccion) {
		List<IdiomaDTO> idiomaDTOList = new ArrayList<>();
		for(Idioma c : coleccion)
			idiomaDTOList.add(transformarConDependencias(c));
		return idiomaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Idioma> coleccion) {
		List<IdiomaDTO> idiomaDTOList = new ArrayList<>();
		for(Idioma c : coleccion)
			idiomaDTOList.add(transformarSinDependencias(c));
		return idiomaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Idioma> coleccion) {
		List<Idioma> idiomaList = new ArrayList<>();
		for(Idioma c : coleccion)
			idiomaList.add(transformarEntidadConDependencias(c));
		return idiomaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Idioma> coleccion) {
		List<Idioma> idiomaList = new ArrayList<>();
		for(Idioma c : coleccion)
			idiomaList.add(transformarEntidadSinDependencias(c));
		return idiomaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Transforma un dominio del idioma a una entidad Idioma.
	 * @param dominio Idioma a asignar a la persona
	 * @param idPersona
	 * @return
	 */
	public static Idioma transformarDominioAIdioma(Dominio dominio, Long idPersona){
		Idioma idioma = null; 
		if(dominio!=null && dominio.getDominioPK()!=null){
			idioma = new Idioma();			
			IdiomaPK idiomaPK = new IdiomaPK();
			idiomaPK.setIdPersona(idPersona);
			idiomaPK.setNombre(dominio.getDominioPK().getCodigo());
			idioma.setIdiomaPK(idiomaPK);			
			idioma.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		}		
		
		return idioma;
	}
	
	/**
	 * Transforma una lista de dominios de idiomas en una lista de entidades de Idioma
	 * @param dominios Idiomas a asignar a la persona
	 * @param idPersona
	 * @return
	 */
	public static List<Idioma> transformarListaDominioAListaEntidades(List<Dominio> dominios, Long idPersona) {
		List<Idioma> idiomas = new ArrayList<>();
		if(dominios!=null && !dominios.isEmpty()){
			for(Dominio dominio : dominios){
				idiomas.add(transformarDominioAIdioma(dominio, idPersona));
			}				
		}			
		return idiomas;
	}
	
	/**
	 * Devuelve los codigo de dominio de la lista de idiomas "idiomas" que se pasa como parámetro.
	 * Devuelve una lista vacía si la lista de idiomas viene nula o vacía.
	 * @param idiomas
	 * @return
	 */
	public static List<String> obtenerCodigosDominioIdiomas(List<Idioma> idiomas){
		List<String> codigos = new ArrayList<>();
		if(idiomas!=null && !idiomas.isEmpty()){
			for(Idioma idioma : idiomas){
				codigos.add(idioma.getIdiomaPK().getNombre());
			}
		}
		
		return codigos;
	}
	// protected region metodos adicionales end

}
