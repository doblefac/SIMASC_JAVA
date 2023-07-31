package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.MotivoDevolucion;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.MotivoDevolucionPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad MotivoDevolucionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class MotivoDevolucionDTO extends IDTO<MotivoDevolucion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private MotivoDevolucionPK motivoDevolucionPK;

	
    public MotivoDevolucionDTO(){
		motivoDevolucionPK = new MotivoDevolucionPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public MotivoDevolucionPK getMotivoDevolucionPK(){
		return this.motivoDevolucionPK;
	}
	
	public void setMotivoDevolucionPK(MotivoDevolucionPK motivoDevolucionPK){
		this.motivoDevolucionPK   = motivoDevolucionPK ;
	}  
	
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.motivoDevolucionPK);        
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad MotivoDevolucionDTO que se pasa
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
        final MotivoDevolucionDTO other = (MotivoDevolucionDTO) obj;
                
        
        return Objects.equals(this.motivoDevolucionPK, other.motivoDevolucionPK);
                
    }
    
    @Override
	public MotivoDevolucionDTO transformarSinDependencias(MotivoDevolucion obj) {
		MotivoDevolucionDTO motivoDevolucionDTO = new MotivoDevolucionDTO();
		
		motivoDevolucionDTO.setMotivoDevolucionPK(obj.getMotivoDevolucionPK());
		
		return motivoDevolucionDTO;
	}

	@Override
	public MotivoDevolucionDTO transformarConDependencias(MotivoDevolucion obj) {
		MotivoDevolucionDTO motivoDevolucionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return motivoDevolucionDTO;
	}

	@Override
	public MotivoDevolucion transformarEntidadSinDependencias(MotivoDevolucion obj) {
		MotivoDevolucion motivoDevolucion = new MotivoDevolucion();
		
		motivoDevolucion.setMotivoDevolucionPK(obj.getMotivoDevolucionPK());
		
		
		return motivoDevolucion;
	}


	@Override
	public MotivoDevolucion transformarEntidadConDependencias(MotivoDevolucion obj) {
		MotivoDevolucion motivoDevolucion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return motivoDevolucion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<MotivoDevolucion> coleccion) {
		List<MotivoDevolucionDTO> motivoDevolucionDTOList = new ArrayList<>();
		for(MotivoDevolucion c : coleccion)
			motivoDevolucionDTOList.add(transformarConDependencias(c));
		return motivoDevolucionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<MotivoDevolucion> coleccion) {
		List<MotivoDevolucionDTO> motivoDevolucionDTOList = new ArrayList<>();
		for(MotivoDevolucion c : coleccion)
			motivoDevolucionDTOList.add(transformarSinDependencias(c));
		return motivoDevolucionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<MotivoDevolucion> coleccion) {
		List<MotivoDevolucion> motivoDevolucionList = new ArrayList<>();
		for(MotivoDevolucion c : coleccion)
			motivoDevolucionList.add(transformarEntidadConDependencias(c));
		return motivoDevolucionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<MotivoDevolucion> coleccion) {
		List<MotivoDevolucion> motivoDevolucionList = new ArrayList<>();
		for(MotivoDevolucion c : coleccion)
			motivoDevolucionList.add(transformarEntidadSinDependencias(c));
		return motivoDevolucionList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
