package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.List;

public class CrearConvenioDTO implements Serializable{

	private Long idPersona;
	private String codTipoPersona;
	private String codTipoDocumento;
	private String nombreRepresentanteLegal;
	private String numeroIdentificacion;
	private String primerNombre;
	private String idPais;
	private Long idTelefono;
	private String tipoTelefono;
	private String telefono;
	private String idCiudad;
	private String direccion;
	private Long idDireccion;
	
	private String nombreConvenio;
	private int maximoDiasProgramacionAudiencias;
	private int numeroDiasHabilesInicioReparto;
	private boolean requiereSuplente;
	private Long idServicio;
	private Long idMateria;
	private List<Long> centros;
	private String duracionAudiencia;
	private List<Long> idSedes;
    private Long idPlantilla;
	private Long idFuncionarioResponsable;
	private Long idCorreo;
	private String correoElectronico;
	
	private String nombreCiudad;
	private String nombreMateria;
	private String nombreFuncionarioResponsable;
	private List<String> nombreSedes;
	private String nombrePlantilla;
	private List<TelefonoDTO> telefonos;
	private Long idConvenio;
	
	
	
	
	
	
	/**
	 * @return the idPersona
	 */
	public Long getIdPersona() {
		return idPersona;
	}
	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	/**
	 * @return the codTipoPersona
	 */
	public String getCodTipoPersona() {
		return codTipoPersona;
	}
	/**
	 * @param codTipoPersona the codTipoPersona to set
	 */
	public void setCodTipoPersona(String codTipoPersona) {
		this.codTipoPersona = codTipoPersona;
	}
	/**
	 * @return the codTipoDocumento
	 */
	public String getCodTipoDocumento() {
		return codTipoDocumento;
	}
	/**
	 * @param codTipoDocumento the codTipoDocumento to set
	 */
	public void setCodTipoDocumento(String codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
	}
	
	/**
	 * @return the numeroIdentificacion
	 */
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	/**
	 * @param numeroIdentificacion the numeroIdentificacion to set
	 */
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
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
	 * @return the tipoTelefono
	 */
	public String getTipoTelefono() {
		return tipoTelefono;
	}
	/**
	 * @param tipoTelefono the tipoTelefono to set
	 */
	public void setTipoTelefono(String tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the nombreConvenio
	 */
	public String getNombreConvenio() {
		return nombreConvenio;
	}
	/**
	 * @param nombreConvenio the nombreConvenio to set
	 */
	public void setNombreConvenio(String nombreConvenio) {
		this.nombreConvenio = nombreConvenio;
	}
	
	/**
	 * @return the requiereSuplente
	 */
	public boolean isRequiereSuplente() {
		return requiereSuplente;
	}
	/**
	 * @param requiereSuplente the requiereSuplente to set
	 */
	public void setRequiereSuplente(boolean requiereSuplente) {
		this.requiereSuplente = requiereSuplente;
	}
	/**
	 * @return the idServicio
	 */
	public Long getIdServicio() {
		return idServicio;
	}
	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	/**
	 * @return the idMateria
	 */
	public Long getIdMateria() {
		return idMateria;
	}
	/**
	 * @param idMateria the idMateria to set
	 */
	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}
	/**
	 * @return the centros
	 */
	public List<Long> getCentros() {
		return centros;
	}
	/**
	 * @param centros the centros to set
	 */
	public void setCentros(List<Long> centros) {
		this.centros = centros;
	}
	/**
	 * @return the duracionAudiencia
	 */
	public String getDuracionAudiencia() {
		return duracionAudiencia;
	}
	/**
	 * @param duracionAudiencia the duracionAudiencia to set
	 */
	public void setDuracionAudiencia(String duracionAudiencia) {
		this.duracionAudiencia = duracionAudiencia;
	}
	/**
	 * @return the idSedes
	 */
	public List<Long> getIdSedes() {
		return idSedes;
	}
	/**
	 * @param idSedes the idSedes to set
	 */
	public void setIdSedes(List<Long> idSedes) {
		this.idSedes = idSedes;
	}

	/**
	 * @return the nombreRepresentanteLegal
	 */
	public String getNombreRepresentanteLegal() {
		return nombreRepresentanteLegal;
	}
	/**
	 * @param nombreRepresentanteLegal the nombreRepresentanteLegal to set
	 */
	public void setNombreRepresentanteLegal(String nombreRepresentanteLegal) {
		this.nombreRepresentanteLegal = nombreRepresentanteLegal;
	}
	/**
	 * @return the idFuncionarioResponsable
	 */
	public Long getIdFuncionarioResponsable() {
		return idFuncionarioResponsable;
	}
	/**
	 * @param idFuncionarioResponsable the idFuncionarioResponsable to set
	 */
	public void setIdFuncionarioResponsable(Long idFuncionarioResponsable) {
		this.idFuncionarioResponsable = idFuncionarioResponsable;
	}
	/**
	 * @return the idPais
	 */
	public String getIdPais() {
		return idPais;
	}
	/**
	 * @param idPais the idPais to set
	 */
	public void setIdPais(String idPais) {
		this.idPais = idPais;
	}
	/**
	 * @return the idTelefono
	 */
	public Long getIdTelefono() {
		return idTelefono;
	}
	/**
	 * @param idTelefono the idTelefono to set
	 */
	public void setIdTelefono(Long idTelefono) {
		this.idTelefono = idTelefono;
	}
	/**
	 * @return the idCiudad
	 */
	public String getIdCiudad() {
		return idCiudad;
	}
	/**
	 * @param idCiudad the idCiudad to set
	 */
	public void setIdCiudad(String idCiudad) {
		this.idCiudad = idCiudad;
	}
	/**
	 * @return the idDireccion
	 */
	public Long getIdDireccion() {
		return idDireccion;
	}
	/**
	 * @param idDireccion the idDireccion to set
	 */
	public void setIdDireccion(Long idDireccion) {
		this.idDireccion = idDireccion;
	}
	/**
	 * @return the idCorreo
	 */
	public Long getIdCorreo() {
		return idCorreo;
	}
	/**
	 * @param idCorreo the idCorreo to set
	 */
	public void setIdCorreo(Long idCorreo) {
		this.idCorreo = idCorreo;
	}
	/**
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	/**
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	/**
	 * @return the maximoDiasProgramacionAudiencias
	 */
	public int getMaximoDiasProgramacionAudiencias() {
		return maximoDiasProgramacionAudiencias;
	}
	/**
	 * @param maximoDiasProgramacionAudiencias the maximoDiasProgramacionAudiencias to set
	 */
	public void setMaximoDiasProgramacionAudiencias(int maximoDiasProgramacionAudiencias) {
		this.maximoDiasProgramacionAudiencias = maximoDiasProgramacionAudiencias;
	}
	/**
	 * @return the numeroDiasHabilesInicioReparto
	 */
	public int getNumeroDiasHabilesInicioReparto() {
		return numeroDiasHabilesInicioReparto;
	}
	/**
	 * @param numeroDiasHabilesInicioReparto the numeroDiasHabilesInicioReparto to set
	 */
	public void setNumeroDiasHabilesInicioReparto(int numeroDiasHabilesInicioReparto) {
		this.numeroDiasHabilesInicioReparto = numeroDiasHabilesInicioReparto;
	}
	/**
	 * @return the idPlantilla
	 */
	public Long getIdPlantilla() {
		return idPlantilla;
	}
	/**
	 * @param idPlantilla the idPlantilla to set
	 */
	public void setIdPlantilla(Long idPlantilla) {
		this.idPlantilla = idPlantilla;
	}
	/**
	 * @return the nombreCiudad
	 */
	public String getNombreCiudad() {
		return nombreCiudad;
	}
	/**
	 * @param nombreCiudad the nombreCiudad to set
	 */
	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}
	/**
	 * @return the nombreMateria
	 */
	public String getNombreMateria() {
		return nombreMateria;
	}
	/**
	 * @param nombreMateria the nombreMateria to set
	 */
	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}
	/**
	 * @return the nombreFuncionarioResponsable
	 */
	public String getNombreFuncionarioResponsable() {
		return nombreFuncionarioResponsable;
	}
	/**
	 * @param nombreFuncionarioResponsable the nombreFuncionarioResponsable to set
	 */
	public void setNombreFuncionarioResponsable(String nombreFuncionarioResponsable) {
		this.nombreFuncionarioResponsable = nombreFuncionarioResponsable;
	}
	/**
	 * @return the nombreSedes
	 */
	public List<String> getNombreSedes() {
		return nombreSedes;
	}
	/**
	 * @param nombreSedes the nombreSedes to set
	 */
	public void setNombreSedes(List<String> nombreSedes) {
		this.nombreSedes = nombreSedes;
	}
	/**
	 * @return the nombrePlantilla
	 */
	public String getNombrePlantilla() {
		return nombrePlantilla;
	}
	/**
	 * @param nombrePlantilla the nombrePlantilla to set
	 */
	public void setNombrePlantilla(String nombrePlantilla) {
		this.nombrePlantilla = nombrePlantilla;
	}
	/**
	 * @return the telefonos
	 */
	public List<TelefonoDTO> getTelefonos() {
		return telefonos;
	}
	/**
	 * @param telefonos the telefonos to set
	 */
	public void setTelefonos(List<TelefonoDTO> telefonos) {
		this.telefonos = telefonos;
	}
	/**
	 * @return the idConvenio
	 */
	public Long getIdConvenio() {
		return idConvenio;
	}
	/**
	 * @param idConvenio the idConvenio to set
	 */
	public void setIdConvenio(Long idConvenio) {
		this.idConvenio = idConvenio;
	}
	
	

	
	
	
	
	
	
	
	
	
}
