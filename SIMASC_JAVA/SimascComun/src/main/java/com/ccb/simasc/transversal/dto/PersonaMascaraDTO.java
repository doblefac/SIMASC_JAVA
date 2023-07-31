package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta sección sus modificaciones


import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad PersonaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class PersonaMascaraDTO{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	private String nombreCompleto;
	private Long idPersonaServicioMateria;
	private List<TelefonoDTO> telefonos;
	private List<CorreoElectronicoDTO> correos;
	private List<UbicacionDTO> direcciones;
	// protected region atributos end
	private Long idPersona;

	private String tipoPersona;		
	private String tipoDocumento;		
	private String numeroDocumento;		
	private String primerNombreORazonSocial;		
	private String segundoNombre;		
	private String primerApellido;		
	private String segundoApellido;		
	private String tipoFuncionario;		
	private String numeroTarjetaProfesional;		
	private String ciudadDelDocumento;		
	private String fechaDeNacimiento;		
	private String escolaridad;		
	private String estrato;		
	private String sexo;		
	private String institucionEducativa;		
	private Date fechaDeGrado;		
	private String entidadExpideTarjetaProfesional;		
	private String tipoDeEmpresa;		
	private String tipoDeEntidadPublica;		
	private String representanteLegal;		
	private String sectorDeLaEmpresa;		
	private String paginaWeb;		
	private String preferenciasDeRefrigerio;		
	private String resumenHojaVida;		
	private boolean autorizaPublicacionDatos;		
	private String estadoPersona;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idProfesion;		
	private String idPaisOrigen;		
	private Long idPersonaJuridica;		
	private String registroConciliador;		
	private String idPolitica;
	private List<CorreoElectronico> correoElectronicoList;	
	private List<RolPersonaCaso> rolPersonaCasoList;
	
    public PersonaMascaraDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
	
	public String getTipoPersona(){
		return this.tipoPersona;
	}
	
	public void setTipoPersona(String tipoPersona){
		this.tipoPersona = tipoPersona;
	}
		
	public String getTipoDocumento(){
		return this.tipoDocumento;
	}
	
	public void setTipoDocumento(String tipoDocumento){
		this.tipoDocumento = tipoDocumento;
	}
		
	public String getNumeroDocumento(){
		return this.numeroDocumento;
	}
	
	public void setNumeroDocumento(String numeroDocumento){
		this.numeroDocumento = numeroDocumento;
	}
		
	public String getPrimerNombreORazonSocial(){
		return this.primerNombreORazonSocial;
	}
	
	public void setPrimerNombreORazonSocial(String primerNombreORazonSocial){
		this.primerNombreORazonSocial = primerNombreORazonSocial;
	}
		
	public String getSegundoNombre(){
		return this.segundoNombre;
	}
	
	public void setSegundoNombre(String segundoNombre){
		this.segundoNombre = segundoNombre;
	}
		
	public String getPrimerApellido(){
		return this.primerApellido;
	}
	
	public void setPrimerApellido(String primerApellido){
		this.primerApellido = primerApellido;
	}
		
	public String getSegundoApellido(){
		return this.segundoApellido;
	}
	
	public void setSegundoApellido(String segundoApellido){
		this.segundoApellido = segundoApellido;
	}
		
	public String getTipoFuncionario(){
		return this.tipoFuncionario;
	}
	
	public void setTipoFuncionario(String tipoFuncionario){
		this.tipoFuncionario = tipoFuncionario;
	}
		
	public String getNumeroTarjetaProfesional(){
		return this.numeroTarjetaProfesional;
	}
	
	public void setNumeroTarjetaProfesional(String numeroTarjetaProfesional){
		this.numeroTarjetaProfesional = numeroTarjetaProfesional;
	}
		
	public String getCiudadDelDocumento(){
		return this.ciudadDelDocumento;
	}
	
	public void setCiudadDelDocumento(String ciudadDelDocumento){
		this.ciudadDelDocumento = ciudadDelDocumento;
	}
		
	public String getFechaDeNacimiento(){
		return this.fechaDeNacimiento;
	}
	
	public void setFechaDeNacimiento(String fechaDeNacimiento){
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
		
	public String getEscolaridad(){
		return this.escolaridad;
	}
	
	public void setEscolaridad(String escolaridad){
		this.escolaridad = escolaridad;
	}
		
	public String getEstrato(){
		return this.estrato;
	}
	
	public void setEstrato(String estrato){
		this.estrato = estrato;
	}
		
	public String getSexo(){
		return this.sexo;
	}
	
	public void setSexo(String sexo){
		this.sexo = sexo;
	}
		
	public String getInstitucionEducativa(){
		return this.institucionEducativa;
	}
	
	public void setInstitucionEducativa(String institucionEducativa){
		this.institucionEducativa = institucionEducativa;
	}
		
	public Date getFechaDeGrado(){
		return this.fechaDeGrado;
	}
	
	public void setFechaDeGrado(Date fechaDeGrado){
		this.fechaDeGrado = fechaDeGrado;
	}
		
	public String getEntidadExpideTarjetaProfesional(){
		return this.entidadExpideTarjetaProfesional;
	}
	
	public void setEntidadExpideTarjetaProfesional(String entidadExpideTarjetaProfesional){
		this.entidadExpideTarjetaProfesional = entidadExpideTarjetaProfesional;
	}
		
	public String getTipoDeEmpresa(){
		return this.tipoDeEmpresa;
	}
	
	public void setTipoDeEmpresa(String tipoDeEmpresa){
		this.tipoDeEmpresa = tipoDeEmpresa;
	}
		
	public String getTipoDeEntidadPublica(){
		return this.tipoDeEntidadPublica;
	}
	
	public void setTipoDeEntidadPublica(String tipoDeEntidadPublica){
		this.tipoDeEntidadPublica = tipoDeEntidadPublica;
	}
		
	public String getRepresentanteLegal(){
		return this.representanteLegal;
	}
	
	public void setRepresentanteLegal(String representanteLegal){
		this.representanteLegal = representanteLegal;
	}
		
	public String getSectorDeLaEmpresa(){
		return this.sectorDeLaEmpresa;
	}
	
	public void setSectorDeLaEmpresa(String sectorDeLaEmpresa){
		this.sectorDeLaEmpresa = sectorDeLaEmpresa;
	}
		
	public String getPaginaWeb(){
		return this.paginaWeb;
	}
	
	public void setPaginaWeb(String paginaWeb){
		this.paginaWeb = paginaWeb;
	}
		
	public String getPreferenciasDeRefrigerio(){
		return this.preferenciasDeRefrigerio;
	}
	
	public void setPreferenciasDeRefrigerio(String preferenciasDeRefrigerio){
		this.preferenciasDeRefrigerio = preferenciasDeRefrigerio;
	}
		
	public String getResumenHojaVida(){
		return this.resumenHojaVida;
	}
	
	public void setResumenHojaVida(String resumenHojaVida){
		this.resumenHojaVida = resumenHojaVida;
	}
		
	public boolean getAutorizaPublicacionDatos(){
		return this.autorizaPublicacionDatos;
	}
	
	public void setAutorizaPublicacionDatos(boolean autorizaPublicacionDatos){
		this.autorizaPublicacionDatos = autorizaPublicacionDatos;
	}
		
	public String getEstadoPersona(){
		return this.estadoPersona;
	}
	
	public void setEstadoPersona(String estadoPersona){
		this.estadoPersona = estadoPersona;
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
		
	public Long getIdProfesion(){
		return this.idProfesion;
	}
	
	public void setIdProfesion(Long idProfesion){
		this.idProfesion = idProfesion;
	}
		
	public String getIdPaisOrigen(){
		return this.idPaisOrigen;
	}
	
	public void setIdPaisOrigen(String idPaisOrigen){
		this.idPaisOrigen = idPaisOrigen;
	}
		
	public Long getIdPersonaJuridica(){
		return this.idPersonaJuridica;
	}
	
	public void setIdPersonaJuridica(Long idPersonaJuridica){
		this.idPersonaJuridica = idPersonaJuridica;
	}
		
	public String getRegistroConciliador(){
		return this.registroConciliador;
	}
	
	public void setRegistroConciliador(String registroConciliador){
		this.registroConciliador = registroConciliador;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idPersona);        
        hash = 37 * hash + Objects.hashCode(this.tipoPersona);
        hash = 37 * hash + Objects.hashCode(this.tipoDocumento);
        hash = 37 * hash + Objects.hashCode(this.numeroDocumento);
        hash = 37 * hash + Objects.hashCode(this.primerNombreORazonSocial);
        hash = 37 * hash + Objects.hashCode(this.segundoNombre);
        hash = 37 * hash + Objects.hashCode(this.primerApellido);
        hash = 37 * hash + Objects.hashCode(this.segundoApellido);
        hash = 37 * hash + Objects.hashCode(this.tipoFuncionario);
        hash = 37 * hash + Objects.hashCode(this.numeroTarjetaProfesional);
        hash = 37 * hash + Objects.hashCode(this.ciudadDelDocumento);
        hash = 37 * hash + Objects.hashCode(this.fechaDeNacimiento);
        hash = 37 * hash + Objects.hashCode(this.escolaridad);
        hash = 37 * hash + Objects.hashCode(this.estrato);
        hash = 37 * hash + Objects.hashCode(this.sexo);
        hash = 37 * hash + Objects.hashCode(this.institucionEducativa);
        hash = 37 * hash + Objects.hashCode(this.fechaDeGrado);
        hash = 37 * hash + Objects.hashCode(this.entidadExpideTarjetaProfesional);
        hash = 37 * hash + Objects.hashCode(this.tipoDeEmpresa);
        hash = 37 * hash + Objects.hashCode(this.tipoDeEntidadPublica);
        hash = 37 * hash + Objects.hashCode(this.representanteLegal);
        hash = 37 * hash + Objects.hashCode(this.sectorDeLaEmpresa);
        hash = 37 * hash + Objects.hashCode(this.paginaWeb);
        hash = 37 * hash + Objects.hashCode(this.preferenciasDeRefrigerio);
        hash = 37 * hash + Objects.hashCode(this.resumenHojaVida);
        hash = 37 * hash + (this.autorizaPublicacionDatos ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.estadoPersona);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idProfesion);
        hash = 37 * hash + Objects.hashCode(this.idPaisOrigen);
        hash = 37 * hash + Objects.hashCode(this.idPersonaJuridica);
        hash = 37 * hash + Objects.hashCode(this.registroConciliador);
        hash = 37 * hash + Objects.hashCode(this.idPolitica);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PersonaDTO que se pasa
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
        final PersonaMascaraDTO other = (PersonaMascaraDTO) obj;
                
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoPersona, other.tipoPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDocumento, other.tipoDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroDocumento, other.numeroDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.primerNombreORazonSocial, other.primerNombreORazonSocial)) {
            return false;
        }
        
        if (!Objects.equals(this.segundoNombre, other.segundoNombre)) {
            return false;
        }
        
        if (!Objects.equals(this.primerApellido, other.primerApellido)) {
            return false;
        }
        
        if (!Objects.equals(this.segundoApellido, other.segundoApellido)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoFuncionario, other.tipoFuncionario)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroTarjetaProfesional, other.numeroTarjetaProfesional)) {
            return false;
        }
        
        if (!Objects.equals(this.ciudadDelDocumento, other.ciudadDelDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeNacimiento, other.fechaDeNacimiento)) {
            return false;
        }
        
        if (!Objects.equals(this.escolaridad, other.escolaridad)) {
            return false;
        }
        
        if (!Objects.equals(this.estrato, other.estrato)) {
            return false;
        }
        
        if (!Objects.equals(this.sexo, other.sexo)) {
            return false;
        }
        
        if (!Objects.equals(this.institucionEducativa, other.institucionEducativa)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeGrado, other.fechaDeGrado)) {
            return false;
        }
        
        if (!Objects.equals(this.entidadExpideTarjetaProfesional, other.entidadExpideTarjetaProfesional)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDeEmpresa, other.tipoDeEmpresa)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDeEntidadPublica, other.tipoDeEntidadPublica)) {
            return false;
        }
        
        if (!Objects.equals(this.representanteLegal, other.representanteLegal)) {
            return false;
        }
        
        if (!Objects.equals(this.sectorDeLaEmpresa, other.sectorDeLaEmpresa)) {
            return false;
        }
        
        if (!Objects.equals(this.paginaWeb, other.paginaWeb)) {
            return false;
        }
        
        if (!Objects.equals(this.preferenciasDeRefrigerio, other.preferenciasDeRefrigerio)) {
            return false;
        }
        
        if (!Objects.equals(this.resumenHojaVida, other.resumenHojaVida)) {
            return false;
        }
        
        if (!Objects.equals(this.autorizaPublicacionDatos, other.autorizaPublicacionDatos)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoPersona, other.estadoPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoRegistro, other.estadoRegistro)) {
            return false;
        }
        
        if (!Objects.equals(this.idProfesion, other.idProfesion)) {
            return false;
        }
        
        if (!Objects.equals(this.idPaisOrigen, other.idPaisOrigen)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaJuridica, other.idPersonaJuridica)) {
        	return false;
        }
        if (!Objects.equals(this.idPolitica, other.idPolitica)) {
            return false;
        }
        
        return Objects.equals(this.registroConciliador, other.registroConciliador);
                
    }
        
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	private boolean esMediador;
	private String tipoNombramiento; 
	/**
	 * @return the esMediador
	 */
	public boolean isEsMediador() {
		return esMediador;
	}

	/**
	 * @param esMediador
	 *            the esMediador to set
	 */
	public void setEsMediador(boolean esMediador) {
		this.esMediador = esMediador;
	}

	public String getTipoNombramiento() {
		return tipoNombramiento;
	}

	public void setTipoNombramiento(String tipoNombramiento) {
		this.tipoNombramiento = tipoNombramiento;
	} 

	public String getNombreCompleto() {
		StringBuilder sb = new StringBuilder();
    	if(this.getPrimerNombreORazonSocial()!=null){
    		sb.append(this.getPrimerNombreORazonSocial());
        	sb.append(UtilConstantes.CARACTER_ESPACIO);
    	}
    	
    	if(this.getSegundoNombre()!=null){
    		sb.append(this.getSegundoNombre());
        	sb.append(UtilConstantes.CARACTER_ESPACIO);
    	}
    	
    	if(this.getPrimerApellido()!=null){
    		sb.append(this.getPrimerApellido());
        	sb.append(UtilConstantes.CARACTER_ESPACIO);
    	}
    	
    	if(this.getSegundoApellido()!=null){
    		sb.append(this.getSegundoApellido());
    	}
    	return sb.toString();		
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * @return the idPersonaServicioMateria
	 */
	public Long getIdPersonaServicioMateria() {
		return idPersonaServicioMateria;
	}



	/**
	 * @param idPersonaServicioMateria the idPersonaServicioMateria to set
	 */
	public void setIdPersonaServicioMateria(Long idPersonaServicioMateria) {
		this.idPersonaServicioMateria = idPersonaServicioMateria;
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
	 * @return the correos
	 */
	public List<CorreoElectronicoDTO> getCorreos() {
		return correos;
	}



	/**
	 * @param correos the correos to set
	 */
	public void setCorreos(List<CorreoElectronicoDTO> correos) {
		this.correos = correos;
	}



	/**
	 * @return the direcciones
	 */
	public List<UbicacionDTO> getDirecciones() {
		return direcciones;
	}



	/**
	 * @param direcciones the direcciones to set
	 */
	public void setDirecciones(List<UbicacionDTO> direcciones) {
		this.direcciones = direcciones;
	}



	public String getIdPolitica() {
		return idPolitica;
	}



	public void setIdPolitica(String idPolitica) {
		this.idPolitica = idPolitica;
	}



	public List<CorreoElectronico> getCorreoElectronicoList() {
		return correoElectronicoList;
	}



	public void setCorreoElectronicoList(List<CorreoElectronico> correoElectronicoList) {
		this.correoElectronicoList = correoElectronicoList;
	}



	public List<RolPersonaCaso> getRolPersonaCasoList() {
		return rolPersonaCasoList;
	}



	public void setRolPersonaCasoList(List<RolPersonaCaso> rolPersonaCasoList) {
		this.rolPersonaCasoList = rolPersonaCasoList;
	}
	
	// protected region metodos adicionales end
	
	
}
