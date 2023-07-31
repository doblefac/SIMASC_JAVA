

package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.Objects;
import java.io.Serializable;

/**

 * @author 
 */
@XmlRootElement
public class ReporteArbitrosDispSorteoDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String primerNombre;		
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String nombreArbitro;
	private String materia;
	private String lista;
	private int consecutivo;
	private String designadoPreviamente;
	private String preseleccion;
	private String tipoPreseleccion;
	private int idCaso;

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
    public ReporteArbitrosDispSorteoDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }

	public String getNombreArbitro() {		
		return this.primerNombre + ' ' + this.segundoNombre + ' ' + 
				this.primerApellido + ' ' + this.segundoApellido ;
	}

	public void setNombreArbitro(String nombreArbitro) {
		this.nombreArbitro = nombreArbitro;
	}

	public String getMateria() {
		return materia;
	}


	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getLista() {
		return lista;
	}

	public void setLista(String lista) {
		this.lista = lista;
	}




	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;

        hash = 37 * hash + Objects.hashCode(this.nombreArbitro);
        hash = 37 * hash + Objects.hashCode(this.materia);    
        hash = 37 * hash + Objects.hashCode(this.lista);
        hash = 37 * hash + Objects.hashCode(this.consecutivo);
        hash = 37 * hash + Objects.hashCode(this.designadoPreviamente);
        hash = 37 * hash + Objects.hashCode(this.preseleccion);
        hash = 37 * hash + Objects.hashCode(this.tipoPreseleccion);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        
        
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
        final ReporteArbitrosDispSorteoDTO other = (ReporteArbitrosDispSorteoDTO) obj;
                
        if (!Objects.equals(this.nombreArbitro, other.nombreArbitro)) {
        	return false;
        }
        
        if (!Objects.equals(this.materia,
        		other.materia)
        		) {
        	return false;
        }
        if (!Objects.equals(this.consecutivo,
        		other.consecutivo)
        		) {
        	return false;
        }
        if (!Objects.equals(this.designadoPreviamente,
        		other.designadoPreviamente)
        		) {
        	return false;
        }
        if (!Objects.equals(this.preseleccion,
        		other.preseleccion)
        		) {
        	return false;
        }
        if (!Objects.equals(this.tipoPreseleccion,
        		other.tipoPreseleccion)
        		) {
        	return false;
        }
        if (!Objects.equals(this.idCaso,
        		other.idCaso)
        		) {
        	return false;
        }
                
        return Objects.equals(this.lista, other.lista);
                
    }

	/**
	 * @return the primerNombre
	 */
	public String getPrimerNombre() {
		return primerNombre;
	}

	/**
	 * @param primerNombre the primerNombre to set
	 */
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	/**
	 * @return the segundoNombre
	 */
	public String getSegundoNombre() {
		return segundoNombre;
	}

	/**
	 * @param segundoNombre the segundoNombre to set
	 */
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	/**
	 * @return the primerApellido
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}

	/**
	 * @param primerApellido the primerApellido to set
	 */
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	/**
	 * @return the segundoApellido
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}

	/**
	 * @param segundoApellido the segundoApellido to set
	 */
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public int getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(int consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getDesignadoPreviamente() {
		return designadoPreviamente;
	}

	public void setDesignadoPreviamente(String designadoPreviamente) {
		this.designadoPreviamente = designadoPreviamente;
	}

	public String getPreseleccion() {
		return preseleccion;
	}

	public void setPreseleccion(String preseleccion) {
		this.preseleccion = preseleccion;
	}

	public String getTipoPreseleccion() {
		return tipoPreseleccion;
	}

	public void setTipoPreseleccion(String tipoPreseleccion) {
		this.tipoPreseleccion = tipoPreseleccion;
	}

	public int getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(int idCaso) {
		this.idCaso = idCaso;
	}

	
	
	
	
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 


