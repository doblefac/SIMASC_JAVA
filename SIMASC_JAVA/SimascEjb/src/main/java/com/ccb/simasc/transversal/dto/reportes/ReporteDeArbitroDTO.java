

package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.Objects;
import java.io.Serializable;

/**
 
 */
@XmlRootElement
public class ReporteDeArbitroDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String nombre;		
	private String estado;		
	private String motivo;		
	private String tipoCaso;
	private String tipoLista;
	private String materia;
	private String disponibilidad;
	private String rol;

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
    public ReporteDeArbitroDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }



	

	public String getNombre() {
		return nombre;
	}





	public void setNombre(String nombre) {
		this.nombre = nombre;
	}





	public String getEstado() {
		return estado;
	}





	public void setEstado(String estado) {
		this.estado = estado;
	}


	
	public String getMotivo() {
		return motivo;
	}


	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}



	public String getTipoCaso() {
		return tipoCaso;
	}





	public void setTipoCaso(String tipoCaso) {
		this.tipoCaso = tipoCaso;
	}





	public String getTipoLista() {
		return tipoLista;
	}





	public void setTipoLista(String tipoLista) {
		this.tipoLista = tipoLista;
	}





	public String getMateria() {
		return materia;
	}





	public void setMateria(String materia) {
		this.materia = materia;
	}





	public String getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}



	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;            
        hash = 37 * hash + Objects.hashCode(this.nombre); 
        hash = 37 * hash + Objects.hashCode(this.tipoCaso);
        hash = 37 * hash + Objects.hashCode(this.estado);   
        hash = 37 * hash + Objects.hashCode(this.motivo);   
        hash = 37 * hash + Objects.hashCode(this.materia);
        hash = 37 * hash + Objects.hashCode(this.tipoLista);    
        hash = 37 * hash + Objects.hashCode(this.disponibilidad);    
        hash = 37 * hash + Objects.hashCode(this.rol);    
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CasoDTO que se pasa
     * como parámetro comprobando que comparten los mismos valores en cada uno
     * de sus atributos. Solo se tienen en cuenta los atributos simples, es
     * decir, se omiten aquellos que definen una relación con otra tabla.
     *
     * @param obj Instancia de la categoría a comprobar
     * iguales.
     * @return Verdadero si esta instancia y la que se pasan como parámetros son
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReporteDeArbitroDTO other = (ReporteDeArbitroDTO) obj;
        
       
        if (!Objects.equals(this.nombre, other.nombre)) {
        	return false;
        }
        
    
        if (!Objects.equals(this.tipoCaso, other.tipoCaso)) {
        	return false;
        }
        
        
        if (!Objects.equals(this.estado, other.estado)) {
        	return false;
        }
        
        if (!Objects.equals(this.motivo, other.motivo)) {
        	return false;
        }
      
        if (!Objects.equals(this.materia,
        		other.materia)
        		) {
        	return false;
        }
        if (!Objects.equals(this.tipoLista, other.tipoLista)) {
        	return false;
        }
  
		if (!Objects.equals(this.rol,
			other.rol)
			) {
		return false;
		}
		
        return Objects.equals(this.disponibilidad, other.disponibilidad);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 


