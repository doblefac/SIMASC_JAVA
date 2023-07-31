package com.ccb.simasc.transversal.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.Evento;


/**
 * DTO que contiene los datos necearios para iniciar el proceso de reparto.
 *  
 * @author Asesoftware - Javier Estévez
 */
@XmlRootElement
public class DatosEntradaRepartoDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2809206066091355328L;

	/**
	 * Identificador del caso al que se le asignarán funcionarios
	 */
	private Long idCaso; 
	
	/**
	 * Identificador del rol que debe tener el funcionario o funcionarios que
	 * se asingarán al caso
	 */
	private Long idRol;
		
	/**
	 * Identificador de la sede de la audiencia
	 */
	private Long idSede;
	
	/**
	 * Fecha en que está programada la audiencia
	 */
	private Date fechaAudiencia;
	
	/**
	 * Hora en que está programada la audiencia
	 */
	private Date horaAudiencia;
	
	/**
	 * Código del conciliador (funcionario o persona) que se asignará al caso
	 */
	private Long idConciliador;
	
	/**
	 * Identificador del documento, obligatorio si el rol es digitalizador
	 */
	private Long idDocumento; 
	
	/**
	 * Usuario que ejecuta el proceso de reparto
	 */
	private String usuario;
	
	/**
	 *  Verdadero: se invoca el procedimiento de reparto desde el caso de uso de cambio de conciliador
	 *  null o Falso: no se invoca el procedimiento de reparto desde el caso de uso de cambio de conciliador
	 */
	private Boolean invocadoDesdeCambioConciliador;
	
	/**
	 * evento de modificacion de paramentros
	 */
	private Evento evento;
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idCaso);        
        hash = 37 * hash + Objects.hashCode(this.idRol);
        hash = 37 * hash + Objects.hashCode(this.idSede);
        hash = 37 * hash + Objects.hashCode(this.fechaAudiencia);
        hash = 37 * hash + Objects.hashCode(this.horaAudiencia);
        hash = 37 * hash + Objects.hashCode(this.idConciliador);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AcuseDTO que se pasa
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
        final DatosEntradaRepartoDTO other = (DatosEntradaRepartoDTO) obj;
                
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.idRol, other.idRol)) {
            return false;
        }
        
        if (!Objects.equals(this.idSede, other.idSede)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaAudiencia, other.fechaAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.horaAudiencia, other.horaAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.idConciliador, other.idConciliador)) {
            return false;
        }
        
        return Objects.equals(this.idDocumento, other.idDocumento);
                
    }

	public Long getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public Long getIdSede() {
		return idSede;
	}

	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}

	public Date getFechaAudiencia() {
		return fechaAudiencia;
	}

	public void setFechaAudiencia(Date fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}

	public Date getHoraAudiencia() {
		return horaAudiencia;
	}

	public void setHoraAudiencia(Date horaAudiencia) {
		this.horaAudiencia = horaAudiencia;
	}

	public Long getIdConciliador() {
		return idConciliador;
	}

	public void setIdConciliador(Long idConciliador) {
		this.idConciliador = idConciliador;
	}

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}
	
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Boolean getInvocadoDesdeCambioConciliador() {
		return invocadoDesdeCambioConciliador;
	}

	public void setInvocadoDesdeCambioConciliador(Boolean invocadoDesdeCambioConciliador) {
		this.invocadoDesdeCambioConciliador = invocadoDesdeCambioConciliador;
	}

	/**
	 * @return the evento
	 */
	public Evento getEvento() {
		return evento;
	}

	/**
	 * @param evento the evento to set
	 */
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	  
}
