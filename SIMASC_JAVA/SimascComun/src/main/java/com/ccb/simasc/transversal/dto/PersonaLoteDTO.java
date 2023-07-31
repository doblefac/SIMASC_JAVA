/**
 * 25/02/2019
 * @author jnmartinez
 */
package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.PersonaLote;
import com.ccb.simasc.transversal.entidades.PersonaLotePK;

/**
 * @author jnmartinez
 *
 */
@XmlRootElement
public class PersonaLoteDTO extends IDTO<PersonaLote> implements Serializable {

private PersonaLotePK personaLotePK;
	private String nombrePersona;
	private String rol;
	private String correo;
	private Long idAudiencia;
	
	public PersonaLoteDTO() {
		personaLotePK = new PersonaLotePK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
	}
	
	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.personaLotePK);
        hash = 37 * hash + Objects.hashCode(this.nombrePersona);
        hash = 37 * hash + Objects.hashCode(this.rol);
        hash = 37 * hash + Objects.hashCode(this.correo);
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParteDeLaRecusacion que se pasa
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
        final PersonaLoteDTO other = (PersonaLoteDTO) obj;
        
        if (!Objects.equals(this.personaLotePK, other.personaLotePK)) {
            return false;
        }
        if (!Objects.equals(this.nombrePersona, other.nombrePersona)) {
            return false;
        }
        if (!Objects.equals(this.rol, other.rol)) {
            return false;
        }
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        
        return Objects.equals(this.idAudiencia, other.idAudiencia);
                
    }

	public PersonaLotePK getPersonaLotePK() {
		return personaLotePK;
	}

	public void setPersonaLotePK(PersonaLotePK personaLotePK) {
		this.personaLotePK = personaLotePK;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Long getIdAudiencia() {
		return idAudiencia;
	}

	public void setIdAudiencia(Long idAudiencia) {
		this.idAudiencia = idAudiencia;
	}

	/* (non-Javadoc)
	 * @see com.ccb.simasc.transversal.dto.IDTO#transformarSinDependencias(java.lang.Object)
	 */
	@Override
	public PersonaLoteDTO transformarSinDependencias(PersonaLote obj) {
		PersonaLoteDTO personaLoteDTO = new PersonaLoteDTO();
		personaLoteDTO.setCorreo(obj.getCorreo());
		personaLoteDTO.setIdAudiencia(obj.getIdAudiencia());
		personaLoteDTO.setNombrePersona(obj.getNombrePersona());
		personaLoteDTO.setRol(obj.getRol());
		personaLoteDTO.setPersonaLotePK(obj.getPersonaLotePK());
		return personaLoteDTO;
	}

	/* (non-Javadoc)
	 * @see com.ccb.simasc.transversal.dto.IDTO#transformarConDependencias(java.lang.Object)
	 */
	@Override
	public PersonaLoteDTO transformarConDependencias(PersonaLote obj) {
		PersonaLoteDTO personaLoteDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		return personaLoteDTO;
	}

	/* (non-Javadoc)
	 * @see com.ccb.simasc.transversal.dto.IDTO#transformarEntidadSinDependencias(java.lang.Object)
	 */
	@Override
	public PersonaLote transformarEntidadSinDependencias(PersonaLote obj) {
		PersonaLote personaLote = new PersonaLote();
		personaLote.setCorreo(obj.getCorreo());
		personaLote.setIdAudiencia(obj.getIdAudiencia());
		personaLote.setNombrePersona(obj.getNombrePersona());
		personaLote.setRol(obj.getRol());
		personaLote.setPersonaLotePK(obj.getPersonaLotePK());
		return personaLote;
	}

	/* (non-Javadoc)
	 * @see com.ccb.simasc.transversal.dto.IDTO#transformarEntidadConDependencias(java.lang.Object)
	 */
	@Override
	public PersonaLote transformarEntidadConDependencias(PersonaLote obj) {
		PersonaLote personaLote = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		return personaLote;
	}

	/* (non-Javadoc)
	 * @see com.ccb.simasc.transversal.dto.IDTO#transformarColeccionConDependencias(java.util.Collection)
	 */
	@Override
	public Collection transformarColeccionConDependencias(Collection<PersonaLote> coleccion) {
		List<PersonaLoteDTO> personaLoteDTOList = new ArrayList<>();
		for (PersonaLote personaLote : coleccion) {
			personaLoteDTOList.add(transformarConDependencias(personaLote));
		}
		return personaLoteDTOList;
	}

	/* (non-Javadoc)
	 * @see com.ccb.simasc.transversal.dto.IDTO#transformarColeccionSinDependencias(java.util.Collection)
	 */
	@Override
	public Collection transformarColeccionSinDependencias(Collection<PersonaLote> coleccion) {
		List<PersonaLoteDTO> personaLoteDTOList = new ArrayList<>();
		for (PersonaLote personaLote : coleccion) {
			personaLoteDTOList.add(transformarSinDependencias(personaLote));
		}
		return personaLoteDTOList;
	}

	/* (non-Javadoc)
	 * @see com.ccb.simasc.transversal.dto.IDTO#transformarColeccionEntidadesConDependencias(java.util.Collection)
	 */
	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<PersonaLote> coleccion) {
		List<PersonaLote> personaLoteList = new ArrayList<>();
		for (PersonaLote personaLote : coleccion) {
			personaLoteList.add(transformarEntidadConDependencias(personaLote));
		}
		return personaLoteList;
	}

	/* (non-Javadoc)
	 * @see com.ccb.simasc.transversal.dto.IDTO#transformarColeccionEntidadesSinDependencias(java.util.Collection)
	 */
	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<PersonaLote> coleccion) {
		List<PersonaLote> personaLoteList = new ArrayList<>();
		for (PersonaLote personaLote : coleccion) {
			personaLoteList.add(transformarEntidadSinDependencias(personaLote));
		}
		return personaLoteList;
	}
    
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
}
