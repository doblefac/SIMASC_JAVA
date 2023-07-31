package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin

// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

// protected region imports entidad end

/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name = "PRESELECCIONADO")
@NamedQuery(name = "Preseleccionado.findAll", query = "SELECT p FROM Preseleccionado p")
public class Preseleccionado implements Serializable {

	private static final long serialVersionUID = 1L;

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end

	// Definicion de atributos de la entidad (Exceptuando relaciones)

	public static final String ENTIDAD_PRESELECCIONADO_PK_ID_CASO = "preseleccionadoPK.idCaso";

	public static final String ENTIDAD_PRESELECCIONADO_PK_ID_PERSONA = "preseleccionadoPK.idPersona";
	public static final String ENTIDAD_PRESELECCIONADO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PRESELECCIONADO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PRESELECCIONADO_ESTADO_REGISTRO_PRESELECCIONADO = "estadoRegistroPreseleccionado";
	public static final String ENTIDAD_PRESELECCIONADO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_PRESELECCIONADO_TIPO_PRESELECCION = "tipoPreseleccion";
	public static final String ENTIDAD_PRESELECCIONADO_MATERIAS_PRESELECCION = "materiasPreseleccion";
	public static final String ENTIDAD_PRESELECCIONADO_ID_MATERIA = "idMateria";
	private static final String[] ATRIBUTOS_ENTIDAD_PRESELECCIONADO = {
			ENTIDAD_PRESELECCIONADO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_PRESELECCIONADO_ESTADO_REGISTRO,
			ENTIDAD_PRESELECCIONADO_PK_ID_CASO, ENTIDAD_PRESELECCIONADO_ID_USUARIO_MODIFICACION,
			ENTIDAD_PRESELECCIONADO_PK_ID_PERSONA, ENTIDAD_PRESELECCIONADO_TIPO_PRESELECCION,
			ENTIDAD_PRESELECCIONADO_MATERIAS_PRESELECCION, ENTIDAD_PRESELECCIONADO_ID_MATERIA };

	@EmbeddedId
	private PreseleccionadoPK preseleccionadoPK;

	@Column(name = "id_usuario_modificacion")
	private String idUsuarioModificacion;

	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name = "fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;

	@Column(name = "estado_registro")
	private String estadoRegistro;

	@ManyToOne
	@JoinColumn(name = "id_caso", referencedColumnName = "id_caso", insertable = false, updatable = false)
	private Caso caso;

	@ManyToOne
	@JoinColumn(name = "id_persona", referencedColumnName = "id_persona", insertable = false, updatable = false)
	private Persona persona;

	@Column(name = "tipo_preseleccion")
	private String tipoPreseleccion;

	@Column(name = "materias_preseleccion")
	private String materiasPreseleccion;

	@Column(name = "id_materia")
	private Long idMateria;

	public Preseleccionado() {
		preseleccionadoPK = new PreseleccionadoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
	}

	public PreseleccionadoPK getPreseleccionadoPK() {
		return this.preseleccionadoPK;
	}

	public void setPreseleccionadoPK(PreseleccionadoPK preseleccionadoPK) {
		this.preseleccionadoPK = preseleccionadoPK;
	}

	public String getIdUsuarioModificacion() {
		return this.idUsuarioModificacion;
	}

	public void setIdUsuarioModificacion(String idUsuarioModificacion) {
		this.idUsuarioModificacion = idUsuarioModificacion;
	}

	public Date getFechaUltimaModificacion() {
		return this.fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public String getEstadoRegistro() {
		return this.estadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public Caso getCaso() {
		return this.caso;
	}

	public void setCaso(Caso caso) {
		this.caso = caso;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getTipoPreseleccion() {
		return tipoPreseleccion;
	}

	public void setTipoPreseleccion(String tipoPreseleccion) {
		this.tipoPreseleccion = tipoPreseleccion;
	}

	public String getMateriasPreseleccion() {
		return materiasPreseleccion;
	}

	public void setMateriasPreseleccion(String materiasPreseleccion) {
		this.materiasPreseleccion = materiasPreseleccion;
	}
	
	

	public Long getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}

	/**
	 * Verifica si la entidad contiene el atributo que se pasa como parametro
	 *
	 * @param atributo Nombre del atributo a validar
	 * @return Verdadero si la entidad contiene al atributo.
	 */
	public static boolean contieneAtributo(String atributo) {

		boolean contiene = false;
		for (final String atr : ATRIBUTOS_ENTIDAD_PRESELECCIONADO) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.preseleccionadoPK);
		hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
		hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
		hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
		hash = 37 * hash + Objects.hashCode(this.tipoPreseleccion);
		hash = 37 * hash + Objects.hashCode(this.materiasPreseleccion);
		hash = 37 * hash + Objects.hashCode(this.idMateria);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad Preseleccionado que se pasa
	 * como parametro comprobando que comparten los mismos valores en cada uno de
	 * sus atributos. Solo se tienen en cuenta los atributos simples, es decir, se
	 * omiten aquellos que definen una relacion con otra tabla.
	 *
	 * @param obj Instancia de la categoría a comprobar iguales.
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
		final Preseleccionado other = (Preseleccionado) obj;

		if (!Objects.equals(this.preseleccionadoPK, other.preseleccionadoPK)) {
			return false;
		}

		if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
			return false;
		}

		if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
			return false;
		}
		if (!Objects.equals(this.tipoPreseleccion, other.tipoPreseleccion)) {
			return false;
		}
		if (!Objects.equals(this.materiasPreseleccion, other.materiasPreseleccion)) {
			return false;
		}
		if (!Objects.equals(this.idMateria, other.idMateria)) {
			return false;
		}

		return Objects.equals(this.estadoRegistro, other.estadoRegistro);

	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
