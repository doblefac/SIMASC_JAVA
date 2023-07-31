package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.ProgramacionAlerta;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ProgramacionAlertaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ProgramacionAlertaDTO extends IDTO<ProgramacionAlerta> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idProgramacionAlerta;

	private Long idCaso;		
	private Long idPersona;		
	private Date fechaEjecucion;		
	private String estado;		
	private Long idAlerta;		
	private Long idDocumento;
	
    public ProgramacionAlertaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdProgramacionAlerta(){
		return this.idProgramacionAlerta;
	}
	
	public void setIdProgramacionAlerta(Long idProgramacionAlerta){
		this.idProgramacionAlerta = idProgramacionAlerta;
	}
	
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Date getFechaEjecucion(){
		return this.fechaEjecucion;
	}
	
	public void setFechaEjecucion(Date fechaEjecucion){
		this.fechaEjecucion = fechaEjecucion;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Long getIdAlerta(){
		return this.idAlerta;
	}
	
	public void setIdAlerta(Long idAlerta){
		this.idAlerta = idAlerta;
	}

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idProgramacionAlerta);        
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.fechaEjecucion);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.idAlerta);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ProgramacionAlertaDTO que se pasa
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
        final ProgramacionAlertaDTO other = (ProgramacionAlertaDTO) obj;
                
        if (!Objects.equals(this.idProgramacionAlerta, other.idProgramacionAlerta)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaEjecucion, other.fechaEjecucion)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        
        return Objects.equals(this.idAlerta, other.idAlerta);
                
    }
    
    @Override
	public ProgramacionAlertaDTO transformarSinDependencias(ProgramacionAlerta obj) {
		ProgramacionAlertaDTO programacionAlertaDTO = new ProgramacionAlertaDTO();
		
		programacionAlertaDTO.setIdProgramacionAlerta(obj.getIdProgramacionAlerta());
		programacionAlertaDTO.setIdCaso(obj.getIdCaso());
		programacionAlertaDTO.setIdPersona(obj.getIdPersona());
		programacionAlertaDTO.setFechaEjecucion(obj.getFechaEjecucion());
		programacionAlertaDTO.setEstado(obj.getEstado());
		programacionAlertaDTO.setIdAlerta(obj.getIdAlerta());
		programacionAlertaDTO.setIdDocumento(obj.getIdDocumento());
		
		return programacionAlertaDTO;
	}

	@Override
	public ProgramacionAlertaDTO transformarConDependencias(ProgramacionAlerta obj) {
		ProgramacionAlertaDTO programacionAlertaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return programacionAlertaDTO;
	}

	@Override
	public ProgramacionAlerta transformarEntidadSinDependencias(ProgramacionAlerta obj) {
		ProgramacionAlerta programacionAlerta = new ProgramacionAlerta();
		
		programacionAlerta.setIdProgramacionAlerta(obj.getIdProgramacionAlerta());
		
		programacionAlerta.setIdCaso(obj.getIdCaso());
		programacionAlerta.setIdPersona(obj.getIdPersona());
		programacionAlerta.setFechaEjecucion(obj.getFechaEjecucion());
		programacionAlerta.setEstado(obj.getEstado());
		programacionAlerta.setIdAlerta(obj.getIdAlerta());
		programacionAlerta.setIdDocumento(obj.getIdDocumento());
		
		return programacionAlerta;
	}


	@Override
	public ProgramacionAlerta transformarEntidadConDependencias(ProgramacionAlerta obj) {
		ProgramacionAlerta programacionAlerta = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return programacionAlerta;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ProgramacionAlerta> coleccion) {
		List<ProgramacionAlertaDTO> programacionAlertaDTOList = new ArrayList<>();
		for(ProgramacionAlerta c : coleccion)
			programacionAlertaDTOList.add(transformarConDependencias(c));
		return programacionAlertaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ProgramacionAlerta> coleccion) {
		List<ProgramacionAlertaDTO> programacionAlertaDTOList = new ArrayList<>();
		for(ProgramacionAlerta c : coleccion)
			programacionAlertaDTOList.add(transformarSinDependencias(c));
		return programacionAlertaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ProgramacionAlerta> coleccion) {
		List<ProgramacionAlerta> programacionAlertaList = new ArrayList<>();
		for(ProgramacionAlerta c : coleccion)
			programacionAlertaList.add(transformarEntidadConDependencias(c));
		return programacionAlertaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ProgramacionAlerta> coleccion) {
		List<ProgramacionAlerta> programacionAlertaList = new ArrayList<>();
		for(ProgramacionAlerta c : coleccion)
			programacionAlertaList.add(transformarEntidadSinDependencias(c));
		return programacionAlertaList;
	}




	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
