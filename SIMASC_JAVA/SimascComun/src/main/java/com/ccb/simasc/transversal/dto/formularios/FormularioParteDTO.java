package com.ccb.simasc.transversal.dto.formularios;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.RolPersonaDTO;
import com.ccb.simasc.transversal.dto.TelefonoDTO;
import com.ccb.simasc.transversal.dto.UbicacionDTO;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Profesion;
import com.ccb.simasc.transversal.entidades.ZonaGeografica;

/**
 * ADM-C-021
 * ARB-F-050
 * DAO que contiene la información del formulario para la creacion de parte representante sea
 * abogado demandante o abogado demandado.
 * Este formulario esta construido para los servicios REST
 * 
 * @author dpachon
 */
@XmlRootElement
public class FormularioParteDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idCaso;
	private Long idRepresentado;
	private Dominio tipoPersona;
	private Dominio rol;
	private Dominio tipoIdentificacion;
	private String numeroIdentificacion;
	private ZonaGeografica ciudadIdentificacion;
	//Utilizado en ADM-C-021 en vez del campo ciudadIdentificacion
	private String ciudadIdentificacionTexto;
	private String nacionalidad;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellidoORazonSocial;
	private String segundoApellido;
	private String direccion;
	private ZonaGeografica pais;
	private ZonaGeografica ciudad;
	private String numeroTelefonoUno;
	private String numeroTelefonoDos;
	private String numeroCelular;
	private String emailUno;
	private String emailUnoAnterior;
	private String emailDos;
	private String emailDosAnterior;
	private String emailTres;
	private String emailTresAnterior;
	private Dominio tipoSexo;
	private Date fechaNacimiento;
	private Dominio estrato;
	private Profesion profesion;
	private Dominio escolaridad;
	private Dominio institucionEducativa;
	private Date fechaGrado;
	private String numeroTarjetaProfesional;
	private Dominio entidadTarjetaProfesional;
	//Publica o privada
	private Dominio tipoEmpresa;
	private Dominio tipoEntidadPublica;
	private String representanteLegal;
	private Dominio sectorEmpresa;
	private String paginaWeb;
	private List<Persona> representada;
	private String nombreCompleto;
	private Long idPersona;
	private List<UbicacionDTO> lstUbicacion;
	
	//Las siguientes listas de DTO se añaden para el caso de uso ADM-F-021
	private List<TelefonoDTO> telefonos;
	private List<CorreoElectronicoDTO> correosElectronicos;
	private List<Dominio> idiomas;	
	private RolPersonaDTO rolPersona;
	private String tipoFuncionario;
	
	// ARB-F-109
	private Long idSolicitudServicio;
	
	private Long idCentro;
	
	private String idPolitica;
	
	private Dominio tieneEmpleo;
	private Dominio tipoEmpleo;	
	private Dominio sectorEconomico;	
	private Dominio estadoCivil;	
	private int numeroPersonasAcargo;	
	private Dominio tieneSociedadConyugal;	
	private Dominio sociedadConyugalVigente;	
	
	// EQUI-F-004
	private String numeroCelular1;
	private String numeroCelular2;
	private Dominio ocupacion;	
	private Dominio actividadEconomica;	
	private Dominio nombreNegocio;	
	private String nitEmpresa;	
	private String nombreEmpresa;
	private String otraActividadEconomica;	
	private String otraNombreNegocio;	
	
	/**
	 * @return the idCaso
	 */
	public Long getIdCaso() {
		return idCaso;
	}
	/**
	 * @param idCaso the idCaso to set
	 */
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	/**
	 * @return the tipoPersona
	 */
	public Dominio getTipoPersona() {
		return tipoPersona;
	}
	/**
	 * @param tipoPersona the tipoPersona to set
	 */
	public void setTipoPersona(Dominio tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	/**
	 * @return the rol
	 */
	public Dominio getRol() {
		return rol;
	}
	/**
	 * @param rol the rol to set
	 */
	public void setRol(Dominio rol) {
		this.rol = rol;
	}
	/**
	 * @return the tipoIdentificacion
	 */
	public Dominio getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	/**
	 * @param tipoIdentificacion the tipoIdentificacion to set
	 */
	public void setTipoIdentificacion(Dominio tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
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
	 * @return the ciudadIdentificacion
	 */
	public ZonaGeografica getCiudadIdentificacion() {
		return ciudadIdentificacion;
	}
	/**
	 * @param ciudadIdentificacion the ciudadIdentificacion to set
	 */
	public void setCiudadIdentificacion(ZonaGeografica ciudadIdentificacion) {
		this.ciudadIdentificacion = ciudadIdentificacion;
	}
	
	public String getCiudadIdentificacionTexto() {
		return ciudadIdentificacionTexto;
	}
	public void setCiudadIdentificacionTexto(String ciudadIdentificacionTexto) {
		this.ciudadIdentificacionTexto = ciudadIdentificacionTexto;
	}
	/**
	 * @return the nacionalidad
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}
	/**
	 * @param nacionalidad the nacionalidad to set
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
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
	 * @return the primerApellidoORazonSocial
	 */
	public String getPrimerApellidoORazonSocial() {
		return primerApellidoORazonSocial;
	}
	/**
	 * @param primerApellidoORazonSocial the primerApellidoORazonSocial to set
	 */
	public void setPrimerApellidoORazonSocial(String primerApellidoORazonSocial) {
		this.primerApellidoORazonSocial = primerApellidoORazonSocial;
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
	 * @return the pais
	 */
	public ZonaGeografica getPais() {
		return pais;
	}
	/**
	 * @param pais the pais to set
	 */
	public void setPais(ZonaGeografica pais) {
		this.pais = pais;
	}
	/**
	 * @return the ciudad
	 */
	public ZonaGeografica getCiudad() {
		return ciudad;
	}
	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(ZonaGeografica ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * @return the numeroTelefonoUno
	 */
	public String getNumeroTelefonoUno() {
		return numeroTelefonoUno;
	}
	/**
	 * @param numeroTelefonoUno the numeroTelefonoUno to set
	 */
	public void setNumeroTelefonoUno(String numeroTelefonoUno) {
		this.numeroTelefonoUno = numeroTelefonoUno;
	}
	/**
	 * @return the numeroTelefonoDos
	 */
	public String getNumeroTelefonoDos() {
		return numeroTelefonoDos;
	}
	/**
	 * @param numeroTelefonoDos the numeroTelefonoDos to set
	 */
	public void setNumeroTelefonoDos(String numeroTelefonoDos) {
		this.numeroTelefonoDos = numeroTelefonoDos;
	}
	/**
	 * @return the numeroCelular
	 */
	public String getNumeroCelular() {
		return numeroCelular;
	}
	/**
	 * @param numeroCelular the numeroCelular to set
	 */
	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}
	/**
	 * @return the emailUno
	 */
	public String getEmailUno() {
		return emailUno;
	}
	/**
	 * @param emailUno the emailUno to set
	 */
	public void setEmailUno(String emailUno) {
		this.emailUno = emailUno;
	}
	/**
	 * @return the emailDos
	 */
	public String getEmailDos() {
		return emailDos;
	}
	/**
	 * @param emailDos the emailDos to set
	 */
	public void setEmailDos(String emailDos) {
		this.emailDos = emailDos;
	}
	/**
	 * @return the emailTres
	 */
	public String getEmailTres() {
		return emailTres;
	}
	/**
	 * @param emailTres the emailTres to set
	 */
	public void setEmailTres(String emailTres) {
		this.emailTres = emailTres;
	}
	/**
	 * @return the tipoSexo
	 */
	public Dominio getTipoSexo() {
		return tipoSexo;
	}
	/**
	 * @param tipoSexo the tipoSexo to set
	 */
	public void setTipoSexo(Dominio tipoSexo) {
		this.tipoSexo = tipoSexo;
	}
	/**
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * @return the estrato
	 */
	public Dominio getEstrato() {
		return estrato;
	}
	/**
	 * @param estrato the estrato to set
	 */
	public void setEstrato(Dominio estrato) {
		this.estrato = estrato;
	}
	/**
	 * @return the profesion
	 */
	public Profesion getProfesion() {
		return profesion;
	}
	/**
	 * @param profesion the profesion to set
	 */
	public void setProfesion(Profesion profesion) {
		this.profesion = profesion;
	}
	/**
	 * @return the escolaridad
	 */
	public Dominio getEscolaridad() {
		return escolaridad;
	}
	/**
	 * @param escolaridad the escolaridad to set
	 */
	public void setEscolaridad(Dominio escolaridad) {
		this.escolaridad = escolaridad;
	}
	/**
	 * @return the institucionEducativa
	 */
	public Dominio getInstitucionEducativa() {
		return institucionEducativa;
	}
	/**
	 * @param institucionEducativa the institucionEducativa to set
	 */
	public void setInstitucionEducativa(Dominio institucionEducativa) {
		this.institucionEducativa = institucionEducativa;
	}
	/**
	 * @return the fechaGrado
	 */
	public Date getFechaGrado() {
		return fechaGrado;
	}
	/**
	 * @param fechaGrado the fechaGrado to set
	 */
	public void setFechaGrado(Date fechaGrado) {
		this.fechaGrado = fechaGrado;
	}
	/**
	 * @return the numeroTarjetaProfesional
	 */
	public String getNumeroTarjetaProfesional() {
		return numeroTarjetaProfesional;
	}
	/**
	 * @param numeroTarjetaProfesional the numeroTarjetaProfesional to set
	 */
	public void setNumeroTarjetaProfesional(String numeroTarjetaProfesional) {
		this.numeroTarjetaProfesional = numeroTarjetaProfesional;
	}
	/**
	 * @return the entidadTarjetaProfesional
	 */
	public Dominio getEntidadTarjetaProfesional() {
		return entidadTarjetaProfesional;
	}
	/**
	 * @param entidadTarjetaProfesional the entidadTarjetaProfesional to set
	 */
	public void setEntidadTarjetaProfesional(Dominio entidadTarjetaProfesional) {
		this.entidadTarjetaProfesional = entidadTarjetaProfesional;
	}
	/**
	 * @return the tipoEmpresa
	 */
	public Dominio getTipoEmpresa() {
		return tipoEmpresa;
	}
	/**
	 * @param tipoEmpresa the tipoEmpresa to set
	 */
	public void setTipoEmpresa(Dominio tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}
	/**
	 * @return the tipoEntidadPublica
	 */
	public Dominio getTipoEntidadPublica() {
		return tipoEntidadPublica;
	}
	/**
	 * @param tipoEntidadPublica the tipoEntidadPublica to set
	 */
	public void setTipoEntidadPublica(Dominio tipoEntidadPublica) {
		this.tipoEntidadPublica = tipoEntidadPublica;
	}
	/**
	 * @return the representanteLegal
	 */
	public String getRepresentanteLegal() {
		return representanteLegal;
	}
	/**
	 * @param representanteLegal the representanteLegal to set
	 */
	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}
	/**
	 * @return the sectorEmpresa
	 */
	public Dominio getSectorEmpresa() {
		return sectorEmpresa;
	}
	/**
	 * @param sectorEmpresa the sectorEmpresa to set
	 */
	public void setSectorEmpresa(Dominio sectorEmpresa) {
		this.sectorEmpresa = sectorEmpresa;
	}
	/**
	 * @return the paginaWeb
	 */
	public String getPaginaWeb() {
		return paginaWeb;
	}
	/**
	 * @param paginaWeb the paginaWeb to set
	 */
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}
	/**
	 * @return the representada
	 */
	public List<Persona> getRepresentada() {
		return representada;
	}
	/**
	 * @param representada the representada to set
	 */
	public void setRepresentada(List<Persona> representada) {
		this.representada = representada;
	}
	/**
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
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
	public List<UbicacionDTO> getLstUbicacion() {
		return lstUbicacion;
	}
	public void setLstUbicacion(List<UbicacionDTO> lstUbicacion) {
		this.lstUbicacion = lstUbicacion;
	}
	public List<TelefonoDTO> getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(List<TelefonoDTO> telefonos) {
		this.telefonos = telefonos;
	}
	public List<CorreoElectronicoDTO> getCorreosElectronicos() {
		return correosElectronicos;
	}
	public void setCorreosElectronicos(List<CorreoElectronicoDTO> correosElectronicos) {
		this.correosElectronicos = correosElectronicos;
	}
	public List<Dominio> getIdiomas() {
		return idiomas;
	}
	public void setIdiomas(List<Dominio> idiomas) {
		this.idiomas = idiomas;
	}
	public RolPersonaDTO getRolPersona() {
		return rolPersona;
	}
	public void setRolPersona(RolPersonaDTO rolPersona) {
		this.rolPersona = rolPersona;
	}
	public String getTipoFuncionario() {
		return tipoFuncionario;
	}
	public void setTipoFuncionario(String tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}
	public Long getIdSolicitudServicio() {
		return idSolicitudServicio;
	}
	public void setIdSolicitudServicio(Long idSolicitudServicio) {
		this.idSolicitudServicio = idSolicitudServicio;
	}

	public Long getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(Long idCentro) {
		this.idCentro = idCentro;
	}

	public Long getIdRepresentado() {
		return idRepresentado;
	}

	public void setIdRepresentado(Long idRepresentado) {
		this.idRepresentado = idRepresentado;
	}

	public String getEmailUnoAnterior() {
		return emailUnoAnterior;
	}
	public void setEmailUnoAnterior(String emailUnoAnterior) {
		this.emailUnoAnterior = emailUnoAnterior;
	}
	public String getEmailDosAnterior() {
		return emailDosAnterior;
	}
	public void setEmailDosAnterior(String emailDosAnterior) {
		this.emailDosAnterior = emailDosAnterior;
	}
	public String getEmailTresAnterior() {
		return emailTresAnterior;
	}
	public void setEmailTresAnterior(String emailTresAnterior) {
		this.emailTresAnterior = emailTresAnterior;
	}
	public String getIdPolitica() {
		return idPolitica;
	}
	public void setIdPolitica(String idPolitica) {
		this.idPolitica = idPolitica;
	}
	public Dominio getTieneEmpleo() {
		return tieneEmpleo;
	}
	public void setTieneEmpleo(Dominio tieneEmpleo) {
		this.tieneEmpleo = tieneEmpleo;
	}
	public Dominio getTipoEmpleo() {
		return tipoEmpleo;
	}
	public void setTipoEmpleo(Dominio tipoEmpleo) {
		this.tipoEmpleo = tipoEmpleo;
	}
	public Dominio getSectorEconomico() {
		return sectorEconomico;
	}
	public void setSectorEconomico(Dominio sectorEconomico) {
		this.sectorEconomico = sectorEconomico;
	}
	public Dominio getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(Dominio estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public int getNumeroPersonasAcargo() {
		return numeroPersonasAcargo;
	}
	public void setNumeroPersonasAcargo(int numeroPersonasAcargo) {
		this.numeroPersonasAcargo = numeroPersonasAcargo;
	}
	public Dominio getTieneSociedadConyugal() {
		return tieneSociedadConyugal;
	}
	public void setTieneSociedadConyugal(Dominio tieneSociedadConyugal) {
		this.tieneSociedadConyugal = tieneSociedadConyugal;
	}
	public Dominio getSociedadConyugalVigente() {
		return sociedadConyugalVigente;
	}
	public void setSociedadConyugalVigente(Dominio sociedadConyugalVigente) {
		this.sociedadConyugalVigente = sociedadConyugalVigente;
	}
	public Dominio getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(Dominio ocupacion) {
		this.ocupacion = ocupacion;
	}
	public Dominio getActividadEconomica() {
		return actividadEconomica;
	}
	public void setActividadEconomica(Dominio actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}
	public Dominio getNombreNegocio() {
		return nombreNegocio;
	}
	public void setNombreNegocio(Dominio nombreNegocio) {
		this.nombreNegocio = nombreNegocio;
	}
	public String getNitEmpresa() {
		return nitEmpresa;
	}
	public void setNitEmpresa(String nitEmpresa) {
		this.nitEmpresa = nitEmpresa;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getNumeroCelular1() {
		return numeroCelular1;
	}
	public void setNumeroCelular1(String numeroCelular1) {
		this.numeroCelular1 = numeroCelular1;
	}
	public String getNumeroCelular2() {
		return numeroCelular2;
	}
	public void setNumeroCelular2(String numeroCelular2) {
		this.numeroCelular2 = numeroCelular2;
	}
	public String getOtraActividadEconomica() {
		return otraActividadEconomica;
	}
	public void setOtraActividadEconomica(String otraActividadEconomica) {
		this.otraActividadEconomica = otraActividadEconomica;
	}
	public String getOtraNombreNegocio() {
		return otraNombreNegocio;
	}
	public void setOtraNombreNegocio(String otraNombreNegocio) {
		this.otraNombreNegocio = otraNombreNegocio;
	}
}
