package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.AutorizacionTratamientoDatos;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad AutorizacionTratamientoDatosDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class AutorizacionTratamientoDatosDTO extends IDTO<AutorizacionTratamientoDatos> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idAutorizacionTratamientoDatos;

	private String numeroIdentificacion;		
	private String tipoIdentificacion;		
	private boolean indicadorAceptacion;		
	private Date fechaAceptacion;		
	
    public AutorizacionTratamientoDatosDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdAutorizacionTratamientoDatos(){
		return this.idAutorizacionTratamientoDatos;
	}
	
	public void setIdAutorizacionTratamientoDatos(Long idAutorizacionTratamientoDatos){
		this.idAutorizacionTratamientoDatos = idAutorizacionTratamientoDatos;
	}
	
	public String getNumeroIdentificacion(){
		return this.numeroIdentificacion;
	}
	
	public void setNumeroIdentificacion(String numeroIdentificacion){
		this.numeroIdentificacion = numeroIdentificacion;
	}
		
	public String getTipoIdentificacion(){
		return this.tipoIdentificacion;
	}
	
	public void setTipoIdentificacion(String tipoIdentificacion){
		this.tipoIdentificacion = tipoIdentificacion;
	}
		
	public boolean getIndicadorAceptacion(){
		return this.indicadorAceptacion;
	}
	
	public void setIndicadorAceptacion(boolean indicadorAceptacion){
		this.indicadorAceptacion = indicadorAceptacion;
	}
		
	public Date getFechaAceptacion(){
		return this.fechaAceptacion;
	}
	
	public void setFechaAceptacion(Date fechaAceptacion){
		this.fechaAceptacion = fechaAceptacion;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idAutorizacionTratamientoDatos);        
        hash = 37 * hash + Objects.hashCode(this.numeroIdentificacion);
        hash = 37 * hash + Objects.hashCode(this.tipoIdentificacion);
        hash = 37 * hash + (this.indicadorAceptacion ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.fechaAceptacion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AutorizacionTratamientoDatosDTO que se pasa
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
        final AutorizacionTratamientoDatosDTO other = (AutorizacionTratamientoDatosDTO) obj;
                
        if (!Objects.equals(this.idAutorizacionTratamientoDatos, other.idAutorizacionTratamientoDatos)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroIdentificacion, other.numeroIdentificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoIdentificacion, other.tipoIdentificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.indicadorAceptacion, other.indicadorAceptacion)) {
            return false;
        }
        
        return Objects.equals(this.fechaAceptacion, other.fechaAceptacion);
                
    }
    
    @Override
	public AutorizacionTratamientoDatosDTO transformarSinDependencias(AutorizacionTratamientoDatos obj) {
		AutorizacionTratamientoDatosDTO autorizacionTratamientoDatosDTO = new AutorizacionTratamientoDatosDTO();
		
		autorizacionTratamientoDatosDTO.setIdAutorizacionTratamientoDatos(obj.getIdAutorizacionTratamientoDatos());
		autorizacionTratamientoDatosDTO.setNumeroIdentificacion(obj.getNumeroIdentificacion());
		autorizacionTratamientoDatosDTO.setTipoIdentificacion(obj.getTipoIdentificacion());
		autorizacionTratamientoDatosDTO.setIndicadorAceptacion(obj.getIndicadorAceptacion());
		autorizacionTratamientoDatosDTO.setFechaAceptacion(obj.getFechaAceptacion());
		
		return autorizacionTratamientoDatosDTO;
	}

	@Override
	public AutorizacionTratamientoDatosDTO transformarConDependencias(AutorizacionTratamientoDatos obj) {
		AutorizacionTratamientoDatosDTO autorizacionTratamientoDatosDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return autorizacionTratamientoDatosDTO;
	}

	@Override
	public AutorizacionTratamientoDatos transformarEntidadSinDependencias(AutorizacionTratamientoDatos obj) {
		AutorizacionTratamientoDatos autorizacionTratamientoDatos = new AutorizacionTratamientoDatos();
		
		autorizacionTratamientoDatos.setIdAutorizacionTratamientoDatos(obj.getIdAutorizacionTratamientoDatos());
		
		autorizacionTratamientoDatos.setNumeroIdentificacion(obj.getNumeroIdentificacion());
		autorizacionTratamientoDatos.setTipoIdentificacion(obj.getTipoIdentificacion());
		autorizacionTratamientoDatos.setIndicadorAceptacion(obj.getIndicadorAceptacion());
		autorizacionTratamientoDatos.setFechaAceptacion(obj.getFechaAceptacion());
		
		return autorizacionTratamientoDatos;
	}


	@Override
	public AutorizacionTratamientoDatos transformarEntidadConDependencias(AutorizacionTratamientoDatos obj) {
		AutorizacionTratamientoDatos autorizacionTratamientoDatos = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return autorizacionTratamientoDatos;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<AutorizacionTratamientoDatos> coleccion) {
		List<AutorizacionTratamientoDatosDTO> autorizacionTratamientoDatosDTOList = new ArrayList<>();
		for(AutorizacionTratamientoDatos c : coleccion)
			autorizacionTratamientoDatosDTOList.add(transformarConDependencias(c));
		return autorizacionTratamientoDatosDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<AutorizacionTratamientoDatos> coleccion) {
		List<AutorizacionTratamientoDatosDTO> autorizacionTratamientoDatosDTOList = new ArrayList<>();
		for(AutorizacionTratamientoDatos c : coleccion)
			autorizacionTratamientoDatosDTOList.add(transformarSinDependencias(c));
		return autorizacionTratamientoDatosDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<AutorizacionTratamientoDatos> coleccion) {
		List<AutorizacionTratamientoDatos> autorizacionTratamientoDatosList = new ArrayList<>();
		for(AutorizacionTratamientoDatos c : coleccion)
			autorizacionTratamientoDatosList.add(transformarEntidadConDependencias(c));
		return autorizacionTratamientoDatosList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<AutorizacionTratamientoDatos> coleccion) {
		List<AutorizacionTratamientoDatos> autorizacionTratamientoDatosList = new ArrayList<>();
		for(AutorizacionTratamientoDatos c : coleccion)
			autorizacionTratamientoDatosList.add(transformarEntidadSinDependencias(c));
		return autorizacionTratamientoDatosList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
