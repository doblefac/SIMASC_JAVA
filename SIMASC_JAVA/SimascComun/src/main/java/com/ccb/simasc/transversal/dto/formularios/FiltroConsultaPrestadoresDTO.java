package com.ccb.simasc.transversal.dto.formularios;

/**
 * DTO utilizado en la consulta del requerimietno ADM-C-003 Consulta de lista de prestadores
 * Representa los filtros seleccionados por el usuario 
 * @author jsoto
 *
 */
public class FiltroConsultaPrestadoresDTO {
	
	private String rol;
	private String nombres;
	private String apellidos;
	private String tipoDocumento;
	private String numeroDocumento;
	private Long idMateria;
	private Long idLista;
	//Nombres y apellidos de la persona jurídica asociada al perito
	private String personaJuridica;	
	
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public Long getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}
	public Long getIdLista() {
		return idLista;
	}
	public void setIdLista(Long idLista) {
		this.idLista = idLista;
	}
	public String getPersonaJuridica() {
		return personaJuridica;
	}
	public void setPersonaJuridica(String personaJuridica) {
		this.personaJuridica = personaJuridica;
	}
	
	public boolean tieneRol(){
		return this.rol!=null && !this.rol.isEmpty();
	}
	
	public boolean tieneNombres(){
		return this.nombres!=null && !this.nombres.isEmpty();
	}
	public boolean tieneApellidos(){
		return this.apellidos!=null && !this.apellidos.isEmpty();
	}
	public boolean tieneTipoDocumento(){
		return this.tipoDocumento!=null && !this.tipoDocumento.isEmpty();
	}
	public boolean tieneNumeroDocumento(){
		return this.numeroDocumento!=null && !this.numeroDocumento.isEmpty();
	}
	public boolean tieneMateria(){
		return this.idMateria!=null;
	}
	public boolean tieneLista(){
		return this.idLista!=null;
	}
	public boolean tienePersonaJuridica(){
		return this.personaJuridica!=null && !this.personaJuridica.isEmpty();
	}

}



