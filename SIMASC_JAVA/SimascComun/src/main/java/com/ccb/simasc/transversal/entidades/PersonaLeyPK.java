package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class PersonaLeyPK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@NotNull
	@Column(name = "id_persona")
	private Long idPersona;

	@Basic(optional = false)
	@NotNull
	@Column(name = "id_ley_aplicable")
	private String idLeyAplicable;

	public PersonaLeyPK(Long idPersona, String idLeyAplicable) {
		this.idPersona = idPersona;
		this.idLeyAplicable = idLeyAplicable;
	}

	public PersonaLeyPK() {
	}

	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idPersona);
		hash = 37 * hash + Objects.hashCode(this.idLeyAplicable);

		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final PersonaLeyPK other = (PersonaLeyPK) obj;

		if (!Objects.equals(this.idLeyAplicable, other.idLeyAplicable)) {
			return false;
		}

		return Objects.equals(this.idPersona, other.idPersona);

	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public String getIdLeyAplicable() {
		return idLeyAplicable;
	}

	public void setIdLeyAplicable(String idLeyAplicable) {
		this.idLeyAplicable = idLeyAplicable;
	}
}
