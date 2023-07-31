package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.FalloAlerta;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad FalloAlertaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class FalloAlertaDTO extends IDTO<FalloAlerta> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idFalloAlerta;

	private String estado;		
	private Date fechaFallo;		
	private Long idProgramacionAlerta;		
	private Long idAlerta;		
	
    public FalloAlertaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdFalloAlerta(){
		return this.idFalloAlerta;
	}
	
	public void setIdFalloAlerta(Long idFalloAlerta){
		this.idFalloAlerta = idFalloAlerta;
	}
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Date getFechaFallo(){
		return this.fechaFallo;
	}
	
	public void setFechaFallo(Date fechaFallo){
		this.fechaFallo = fechaFallo;
	}
		
	public Long getIdProgramacionAlerta(){
		return this.idProgramacionAlerta;
	}
	
	public void setIdProgramacionAlerta(Long idProgramacionAlerta){
		this.idProgramacionAlerta = idProgramacionAlerta;
	}
		
	public Long getIdAlerta(){
		return this.idAlerta;
	}
	
	public void setIdAlerta(Long idAlerta){
		this.idAlerta = idAlerta;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idFalloAlerta);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.fechaFallo);
        hash = 37 * hash + Objects.hashCode(this.idProgramacionAlerta);
        hash = 37 * hash + Objects.hashCode(this.idAlerta);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad FalloAlertaDTO que se pasa
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
        final FalloAlertaDTO other = (FalloAlertaDTO) obj;
                
        if (!Objects.equals(this.idFalloAlerta, other.idFalloAlerta)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFallo, other.fechaFallo)) {
            return false;
        }
        
        if (!Objects.equals(this.idProgramacionAlerta, other.idProgramacionAlerta)) {
            return false;
        }
        
        return Objects.equals(this.idAlerta, other.idAlerta);
                
    }
    
    @Override
	public FalloAlertaDTO transformarSinDependencias(FalloAlerta obj) {
		FalloAlertaDTO falloAlertaDTO = new FalloAlertaDTO();
		
		falloAlertaDTO.setIdFalloAlerta(obj.getIdFalloAlerta());
		falloAlertaDTO.setEstado(obj.getEstado());
		falloAlertaDTO.setFechaFallo(obj.getFechaFallo());
		falloAlertaDTO.setIdProgramacionAlerta(obj.getIdProgramacionAlerta());
		falloAlertaDTO.setIdAlerta(obj.getIdAlerta());
		
		return falloAlertaDTO;
	}

	@Override
	public FalloAlertaDTO transformarConDependencias(FalloAlerta obj) {
		FalloAlertaDTO falloAlertaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return falloAlertaDTO;
	}

	@Override
	public FalloAlerta transformarEntidadSinDependencias(FalloAlerta obj) {
		FalloAlerta falloAlerta = new FalloAlerta();
		
		falloAlerta.setIdFalloAlerta(obj.getIdFalloAlerta());
		
		falloAlerta.setEstado(obj.getEstado());
		falloAlerta.setFechaFallo(obj.getFechaFallo());
		falloAlerta.setIdProgramacionAlerta(obj.getIdProgramacionAlerta());
		falloAlerta.setIdAlerta(obj.getIdAlerta());
		
		return falloAlerta;
	}


	@Override
	public FalloAlerta transformarEntidadConDependencias(FalloAlerta obj) {
		FalloAlerta falloAlerta = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return falloAlerta;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<FalloAlerta> coleccion) {
		List<FalloAlertaDTO> falloAlertaDTOList = new ArrayList<>();
		for(FalloAlerta c : coleccion)
			falloAlertaDTOList.add(transformarConDependencias(c));
		return falloAlertaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<FalloAlerta> coleccion) {
		List<FalloAlertaDTO> falloAlertaDTOList = new ArrayList<>();
		for(FalloAlerta c : coleccion)
			falloAlertaDTOList.add(transformarSinDependencias(c));
		return falloAlertaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<FalloAlerta> coleccion) {
		List<FalloAlerta> falloAlertaList = new ArrayList<>();
		for(FalloAlerta c : coleccion)
			falloAlertaList.add(transformarEntidadConDependencias(c));
		return falloAlertaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<FalloAlerta> coleccion) {
		List<FalloAlerta> falloAlertaList = new ArrayList<>();
		for(FalloAlerta c : coleccion)
			falloAlertaList.add(transformarEntidadSinDependencias(c));
		return falloAlertaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
