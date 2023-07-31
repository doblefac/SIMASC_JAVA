package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.Cuota;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.CuotaPK;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad CuotaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class CuotaDTO extends IDTO<Cuota> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private CuotaPK cuotaPK;

	private Date fechaPrevista;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public CuotaDTO(){
		cuotaPK = new CuotaPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public CuotaPK getCuotaPK(){
		return this.cuotaPK;
	}
	
	public void setCuotaPK(CuotaPK cuotaPK){
		this.cuotaPK   = cuotaPK ;
	}  
	
	public Date getFechaPrevista(){
		return this.fechaPrevista;
	}
	
	public void setFechaPrevista(Date fechaPrevista){
		this.fechaPrevista = fechaPrevista;
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
        
        hash = 37 * hash + Objects.hashCode(this.cuotaPK);        
        hash = 37 * hash + Objects.hashCode(this.fechaPrevista);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CuotaDTO que se pasa
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
        final CuotaDTO other = (CuotaDTO) obj;
                
        if (!Objects.equals(this.cuotaPK, other.cuotaPK)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaPrevista, other.fechaPrevista)) {
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
	public CuotaDTO transformarSinDependencias(Cuota obj) {
		CuotaDTO cuotaDTO = new CuotaDTO();
		
		cuotaDTO.setCuotaPK(obj.getCuotaPK());
		cuotaDTO.setFechaPrevista(obj.getFechaPrevista());
		cuotaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		cuotaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		cuotaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return cuotaDTO;
	}

	@Override
	public CuotaDTO transformarConDependencias(Cuota obj) {
		CuotaDTO cuotaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return cuotaDTO;
	}

	@Override
	public Cuota transformarEntidadSinDependencias(Cuota obj) {
		Cuota cuota = new Cuota();
		
		cuota.setCuotaPK(obj.getCuotaPK());
		
		cuota.setFechaPrevista(obj.getFechaPrevista());
		cuota.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		cuota.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		cuota.setEstadoRegistro(obj.getEstadoRegistro());
		
		return cuota;
	}


	@Override
	public Cuota transformarEntidadConDependencias(Cuota obj) {
		Cuota cuota = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return cuota;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Cuota> coleccion) {
		List<CuotaDTO> cuotaDTOList = new ArrayList<>();
		for(Cuota c : coleccion)
			cuotaDTOList.add(transformarConDependencias(c));
		return cuotaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Cuota> coleccion) {
		List<CuotaDTO> cuotaDTOList = new ArrayList<>();
		for(Cuota c : coleccion)
			cuotaDTOList.add(transformarSinDependencias(c));
		return cuotaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Cuota> coleccion) {
		List<Cuota> cuotaList = new ArrayList<>();
		for(Cuota c : coleccion)
			cuotaList.add(transformarEntidadConDependencias(c));
		return cuotaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Cuota> coleccion) {
		List<Cuota> cuotaList = new ArrayList<>();
		for(Cuota c : coleccion)
			cuotaList.add(transformarEntidadSinDependencias(c));
		return cuotaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
