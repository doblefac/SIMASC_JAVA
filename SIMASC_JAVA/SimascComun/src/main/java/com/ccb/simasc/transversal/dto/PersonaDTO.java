package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta sección sus modificaciones


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilMascaraTexto;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad PersonaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class PersonaDTO extends IDTO<Persona> implements Serializable{	

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
	private Date fechaDeNacimiento;		
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
	private boolean designadoPreviamente;
	
    public PersonaDTO(){
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
		
	public Date getFechaDeNacimiento(){
		return this.fechaDeNacimiento;
	}
	
	public void setFechaDeNacimiento(Date fechaDeNacimiento){
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
	
    public boolean isDesignadoPreviamente() {
		return designadoPreviamente;
	}

	public void setDesignadoPreviamente(boolean designadoPreviamente) {
		this.designadoPreviamente = designadoPreviamente;
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
        final PersonaDTO other = (PersonaDTO) obj;
                
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
    
    @Override
	public PersonaDTO transformarSinDependencias(Persona obj) {
		PersonaDTO personaDTO = new PersonaDTO();
		
		personaDTO.setIdPersona(obj.getIdPersona());
		personaDTO.setTipoPersona(obj.getTipoPersona());
		personaDTO.setTipoDocumento(obj.getTipoDocumento());
		personaDTO.setNumeroDocumento(obj.getNumeroDocumento());
		personaDTO.setPrimerNombreORazonSocial(obj.getPrimerNombreORazonSocial());
		personaDTO.setSegundoNombre(obj.getSegundoNombre());
		personaDTO.setPrimerApellido(obj.getPrimerApellido());
		personaDTO.setSegundoApellido(obj.getSegundoApellido());
		personaDTO.setTipoFuncionario(obj.getTipoFuncionario());
		personaDTO.setNumeroTarjetaProfesional(obj.getNumeroTarjetaProfesional());
		personaDTO.setCiudadDelDocumento(obj.getCiudadDelDocumento());
		personaDTO.setFechaDeNacimiento(obj.getFechaDeNacimiento());
		personaDTO.setEscolaridad(obj.getEscolaridad());
		personaDTO.setEstrato(obj.getEstrato());
		personaDTO.setSexo(obj.getSexo());
		personaDTO.setInstitucionEducativa(obj.getInstitucionEducativa());
		personaDTO.setFechaDeGrado(obj.getFechaDeGrado());
		personaDTO.setEntidadExpideTarjetaProfesional(obj.getEntidadExpideTarjetaProfesional());
		personaDTO.setTipoDeEmpresa(obj.getTipoDeEmpresa());
		personaDTO.setTipoDeEntidadPublica(obj.getTipoDeEntidadPublica());
		personaDTO.setRepresentanteLegal(obj.getRepresentanteLegal());
		personaDTO.setSectorDeLaEmpresa(obj.getSectorDeLaEmpresa());
		personaDTO.setPaginaWeb(obj.getPaginaWeb());
		personaDTO.setPreferenciasDeRefrigerio(obj.getPreferenciasDeRefrigerio());
		personaDTO.setResumenHojaVida(obj.getResumenHojaVida());
		personaDTO.setAutorizaPublicacionDatos(obj.getAutorizaPublicacionDatos());
		personaDTO.setEstadoPersona(obj.getEstadoPersona());
		personaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		personaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		personaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		personaDTO.setIdProfesion(obj.getIdProfesion());
		personaDTO.setIdPaisOrigen(obj.getIdPaisOrigen());
		personaDTO.setIdPersonaJuridica(obj.getIdPersonaJuridica());
		personaDTO.setRegistroConciliador(obj.getRegistroConciliador());
		personaDTO.setIdPolitica(obj.getIdPolitica());
		
		return personaDTO;
	}

	@Override
	public PersonaDTO transformarConDependencias(Persona obj) {
		PersonaDTO personaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return personaDTO;
	}

	@Override
	public Persona transformarEntidadSinDependencias(Persona obj) {
		Persona persona = new Persona();
		
		persona.setIdPersona(obj.getIdPersona());
		
		persona.setTipoPersona(obj.getTipoPersona());
		persona.setTipoDocumento(obj.getTipoDocumento());
		persona.setNumeroDocumento(obj.getNumeroDocumento());
		persona.setPrimerNombreORazonSocial(obj.getPrimerNombreORazonSocial());
		persona.setSegundoNombre(obj.getSegundoNombre());
		persona.setPrimerApellido(obj.getPrimerApellido());
		persona.setSegundoApellido(obj.getSegundoApellido());
		persona.setTipoFuncionario(obj.getTipoFuncionario());
		persona.setNumeroTarjetaProfesional(obj.getNumeroTarjetaProfesional());
		persona.setCiudadDelDocumento(obj.getCiudadDelDocumento());
		persona.setFechaDeNacimiento(obj.getFechaDeNacimiento());
		persona.setEscolaridad(obj.getEscolaridad());
		persona.setEstrato(obj.getEstrato());
		persona.setSexo(obj.getSexo());
		persona.setInstitucionEducativa(obj.getInstitucionEducativa());
		persona.setFechaDeGrado(obj.getFechaDeGrado());
		persona.setEntidadExpideTarjetaProfesional(obj.getEntidadExpideTarjetaProfesional());
		persona.setTipoDeEmpresa(obj.getTipoDeEmpresa());
		persona.setTipoDeEntidadPublica(obj.getTipoDeEntidadPublica());
		persona.setRepresentanteLegal(obj.getRepresentanteLegal());
		persona.setSectorDeLaEmpresa(obj.getSectorDeLaEmpresa());
		persona.setPaginaWeb(obj.getPaginaWeb());
		persona.setPreferenciasDeRefrigerio(obj.getPreferenciasDeRefrigerio());
		persona.setResumenHojaVida(obj.getResumenHojaVida());
		persona.setAutorizaPublicacionDatos(obj.getAutorizaPublicacionDatos());
		persona.setEstadoPersona(obj.getEstadoPersona());
		persona.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		persona.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		persona.setEstadoRegistro(obj.getEstadoRegistro());
		persona.setIdProfesion(obj.getIdProfesion());
		persona.setIdPaisOrigen(obj.getIdPaisOrigen());
		persona.setIdPersonaJuridica(obj.getIdPersonaJuridica());
		persona.setRegistroConciliador(obj.getRegistroConciliador());
		persona.setIdPolitica(obj.getIdPolitica());
		
		return persona;
	}


	@Override
	public Persona transformarEntidadConDependencias(Persona obj) {
		Persona persona = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		List<CorreoElectronico> listcorreo=new ArrayList<>();
		for(CorreoElectronico correo:obj.getCorreoElectronicoList()){
			listcorreo.add(new CorreoElectronicoDTO().transformarEntidadConDependencias(correo));
		}
		persona.setCorreoElectronicoList(listcorreo);
		
		List<RolPersonaCaso> rolPersonaCasoList = new ArrayList<>();
		for(RolPersonaCaso rolPersonaCaso:obj.getRolPersonaCasoList()){
			rolPersonaCasoList.add(new RolPersonaCasoDTO().transformarEntidadConDependencias(rolPersonaCaso));
		}
		persona.setRolPersonaCasoList(rolPersonaCasoList);
		// protected region modificaciones transformarEntidadConDependencias end
		
		return persona;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Persona> coleccion) {
		List<PersonaDTO> personaDTOList = new ArrayList<>();
		for(Persona c : coleccion)
			personaDTOList.add(transformarConDependencias(c));
		return personaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Persona> coleccion) {
		List<PersonaDTO> personaDTOList = new ArrayList<>();
		for(Persona c : coleccion)
			personaDTOList.add(transformarSinDependencias(c));
		return personaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Persona> coleccion) {
		List<Persona> personaList = new ArrayList<>();
		for(Persona c : coleccion)
			personaList.add(transformarEntidadConDependencias(c));
		return personaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Persona> coleccion) {
		List<Persona> personaList = new ArrayList<>();
		for(Persona c : coleccion)
			personaList.add(transformarEntidadSinDependencias(c));
		return personaList;
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
	
	
	// protected region metodos adicionales end
	
	public PersonaMascaraDTO transformarEntidadEnmascarandoConDependencias(Persona obj) {
		PersonaMascaraDTO personaMascara = transformarEntidadEnmascarandoSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		List<CorreoElectronico> listcorreo=new ArrayList<>();
		for(CorreoElectronico correo:obj.getCorreoElectronicoList()){
			listcorreo.add(new CorreoElectronicoDTO().transformarEntidadConDependencias(correo));
		}
		personaMascara.setCorreoElectronicoList(listcorreo);
		
		List<RolPersonaCaso> rolPersonaCasoList = new ArrayList<>();
		for(RolPersonaCaso rolPersonaCaso:obj.getRolPersonaCasoList()){
			rolPersonaCasoList.add(new RolPersonaCasoDTO().transformarEntidadConDependencias(rolPersonaCaso));
		}
		personaMascara.setRolPersonaCasoList(rolPersonaCasoList);
		// protected region modificaciones transformarEntidadConDependencias end
		
		return personaMascara;
	}
	
	public PersonaMascaraDTO transformarEntidadEnmascarandoSinDependencias(Persona obj) {
		PersonaMascaraDTO personaMascara = new PersonaMascaraDTO();
		
		personaMascara.setIdPersona(obj.getIdPersona());
		
		personaMascara.setTipoPersona(obj.getTipoPersona());
		personaMascara.setTipoDocumento(obj.getTipoDocumento());
		personaMascara.setNumeroDocumento(obj.getNumeroDocumento());
		personaMascara.setPrimerNombreORazonSocial(obj.getPrimerNombreORazonSocial());
		personaMascara.setSegundoNombre(obj.getSegundoNombre());
		personaMascara.setPrimerApellido(obj.getPrimerApellido());
		personaMascara.setSegundoApellido(obj.getSegundoApellido());
		personaMascara.setTipoFuncionario(obj.getTipoFuncionario());
		personaMascara.setNumeroTarjetaProfesional(obj.getNumeroTarjetaProfesional());
		personaMascara.setCiudadDelDocumento(obj.getCiudadDelDocumento());
		personaMascara.setFechaDeNacimiento(UtilMascaraTexto.replaceCharacterByDot(obj.getFechaDeNacimiento().toString()));
		personaMascara.setEscolaridad(UtilMascaraTexto.replaceCharacterByDot(obj.getEscolaridad()));
		personaMascara.setEstrato(UtilMascaraTexto.replaceCharacterByDot(obj.getEstrato()));
		personaMascara.setSexo(obj.getSexo());
		personaMascara.setInstitucionEducativa(UtilMascaraTexto.replaceCharacterByDot(obj.getInstitucionEducativa()));
		personaMascara.setFechaDeGrado(obj.getFechaDeGrado());
		personaMascara.setEntidadExpideTarjetaProfesional(obj.getEntidadExpideTarjetaProfesional());
		personaMascara.setTipoDeEmpresa(obj.getTipoDeEmpresa());
		personaMascara.setTipoDeEntidadPublica(obj.getTipoDeEntidadPublica());
		personaMascara.setRepresentanteLegal(obj.getRepresentanteLegal());
		personaMascara.setSectorDeLaEmpresa(obj.getSectorDeLaEmpresa());
		personaMascara.setPaginaWeb(obj.getPaginaWeb());
		personaMascara.setPreferenciasDeRefrigerio(obj.getPreferenciasDeRefrigerio());
		personaMascara.setResumenHojaVida(obj.getResumenHojaVida());
		personaMascara.setAutorizaPublicacionDatos(obj.getAutorizaPublicacionDatos());
		personaMascara.setEstadoPersona(obj.getEstadoPersona());
		personaMascara.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		personaMascara.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		personaMascara.setEstadoRegistro(obj.getEstadoRegistro());
		personaMascara.setIdProfesion(obj.getIdProfesion());
		personaMascara.setIdPaisOrigen(obj.getIdPaisOrigen());
		personaMascara.setIdPersonaJuridica(obj.getIdPersonaJuridica());
		personaMascara.setRegistroConciliador(obj.getRegistroConciliador());
		personaMascara.setIdPolitica(obj.getIdPolitica());
		
		return personaMascara;
	}

}
