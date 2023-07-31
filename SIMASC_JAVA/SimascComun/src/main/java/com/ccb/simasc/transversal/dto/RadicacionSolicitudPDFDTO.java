package com.ccb.simasc.transversal.dto;


public class RadicacionSolicitudPDFDTO {
	//informacion pagina 1
	private long idCaso;
	private String hechos;
	private String pretensiones;
	private String tipoCuantia;
	private String valorPretensiones;
	private String inicioConflicto;
	private String asuntoConciliacion;
	private String sede;
	private String centro;
	private String conciliador;
	private String fechaAudiencia;
	private String fechaRadicacion;
	private String jornada; 
	private int dia;
	private int mes;
	private int ano;
	
	//informacion pagina 2
	private String rol;
	private String tipoIdentificacion;
	private String tipoPersonaParte;
	private String numeroIdentificacion;
	private String ciudadIdentificacion;
	private String nacionalidad;
	private String nombre;
	private String direccion;
	private String pais;
	private String ciudad;
	private String telefono;
	private String email;
	private String sexo;
	private String fechaNacimiento;
	private String estrato;
	private String profesion;
	private String escolaridad;
	private String institucionEducativa;
	private String fechaGrado;
	private String tarjetaProfesional;
	private String entidadTarjetaProfesional;
	private String celular;
	private String email2;
	private String email3;
	
	//persona juridica
	private String tipoEmpresa;
	private String tipoEntidadPublica;
	private String representanteLegal;
	private String sectorEmpresa;
	private String paginaWeb;
	
	//Documentos aportados
	private String nombreDocumento;
	
	//Condiciones generales
	private String valorMora;
	private String cantAcreedor;
	private String cantDeuda;
	private String domicilio;
	private String saldoCapital;
	private String tipoPersona;
	
	public RadicacionSolicitudPDFDTO(){
		
	}
	
	public RadicacionSolicitudPDFDTO(long idCaso, String hechos, String pretensiones, String tipoCuantia,
			String valorPretensiones, String inicioConflicto, String asuntoConciliacion, String sede,
			String conciliador, String fechaAudiencia, String jornada, int dia, int mes, int ano,
			String valorMora, String cantAcreedor, String cantDeuda, String domicilio, String saldoCapital, String tipoPersona, String fechaRadicacion, String centro) {
		this.idCaso = idCaso;
		this.hechos = hechos;
		this.pretensiones = pretensiones;
		this.tipoCuantia = tipoCuantia;
		this.valorPretensiones = valorPretensiones;
		this.inicioConflicto = inicioConflicto;
		this.asuntoConciliacion = asuntoConciliacion;
		this.sede = sede;
		this.conciliador = conciliador;
		this.fechaAudiencia = fechaAudiencia;
		this.jornada = jornada;
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.valorMora = valorMora;
		this.cantAcreedor = cantAcreedor;
		this.cantDeuda = cantDeuda;
		this.domicilio = domicilio;
		this.saldoCapital = saldoCapital;
		this.tipoPersona = tipoPersona;
		this.fechaRadicacion = fechaRadicacion;
		this.centro= centro;
	}
	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(long idCaso) {
		this.idCaso = idCaso;
	}
	public String getHechos() {
		return hechos;
	}
	public void setHechos(String hechos) {
		this.hechos = hechos;
	}
	public String getPretensiones() {
		return pretensiones;
	}
	public void setPretensiones(String pretensiones) {
		this.pretensiones = pretensiones;
	}
	public String getTipoCuantia() {
		return tipoCuantia;
	}
	public void setTipoCuantia(String tipoCuantia) {
		this.tipoCuantia = tipoCuantia;
	}
	public String getValorPretensiones() {
		return valorPretensiones;
	}
	public void setValorPretensiones(String valorPretensiones) {
		this.valorPretensiones = valorPretensiones;
	}
	public String getInicioConflicto() {
		return inicioConflicto;
	}
	public void setInicioConflicto(String inicioConflicto) {
		this.inicioConflicto = inicioConflicto;
	}
	public String getAsuntoConciliacion() {
		return asuntoConciliacion;
	}
	public void setAsuntoConciliacion(String asuntoConciliacion) {
		this.asuntoConciliacion = asuntoConciliacion;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public String getConciliador() {
		return conciliador;
	}
	public void setConciliador(String conciliador) {
		this.conciliador = conciliador;
	}
	public String getFechaAudiencia() {
		return fechaAudiencia;
	}
	public void setFechaAudiencia(String fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}
	public String getJornada() {
		return jornada;
	}
	public void setJornada(String jornada) {
		this.jornada = jornada;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	public String getCiudadIdentificacion() {
		return ciudadIdentificacion;
	}
	public void setCiudadIdentificacion(String ciudadIdentificacion) {
		this.ciudadIdentificacion = ciudadIdentificacion;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getEstrato() {
		return estrato;
	}
	public void setEstrato(String estrato) {
		this.estrato = estrato;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public String getEscolaridad() {
		return escolaridad;
	}
	public void setEscolaridad(String escolaridad) {
		this.escolaridad = escolaridad;
	}
	public String getInstitucionEducativa() {
		return institucionEducativa;
	}
	public void setInstitucionEducativa(String institucionEducativa) {
		this.institucionEducativa = institucionEducativa;
	}
	public String getFechaGrado() {
		return fechaGrado;
	}
	public void setFechaGrado(String fechaGrado) {
		this.fechaGrado = fechaGrado;
	}
	public String getTarjetaProfesional() {
		return tarjetaProfesional;
	}
	public void setTarjetaProfesional(String tarjetaProfesional) {
		this.tarjetaProfesional = tarjetaProfesional;
	}
	public String getEntidadTarjetaProfesional() {
		return entidadTarjetaProfesional;
	}
	public void setEntidadTarjetaProfesional(String entidadTarjetaProfesional) {
		this.entidadTarjetaProfesional = entidadTarjetaProfesional;
	}
	public String getTipoEmpresa() {
		return tipoEmpresa;
	}
	public void setTipoEmpresa(String tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}
	public String getTipoEntidadPublica() {
		return tipoEntidadPublica;
	}
	public void setTipoEntidadPublica(String tipoEntidadPublica) {
		this.tipoEntidadPublica = tipoEntidadPublica;
	}
	public String getRepresentanteLegal() {
		return representanteLegal;
	}
	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}
	public String getSectorEmpresa() {
		return sectorEmpresa;
	}
	public void setSectorEmpresa(String sectorEmpresa) {
		this.sectorEmpresa = sectorEmpresa;
	}
	public String getPaginaWeb() {
		return paginaWeb;
	}
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

	public String getValorMora() {
		return valorMora;
	}

	public void setValorMora(String valorMora) {
		this.valorMora = valorMora;
	}

	public String getCantAcreedor() {
		return cantAcreedor;
	}

	public void setCantAcreedor(String cantAcreedor) {
		this.cantAcreedor = cantAcreedor;
	}

	public String getCantDeuda() {
		return cantDeuda;
	}

	public void setCantDeuda(String cantDeuda) {
		this.cantDeuda = cantDeuda;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getSaldoCapital() {
		return saldoCapital;
	}

	public void setSaldoCapital(String saldoCapital) {
		this.saldoCapital = saldoCapital;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public String getFechaRadicacion() {
		return fechaRadicacion;
	}

	public void setFechaRadicacion(String fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getTipoPersonaParte() {
		return tipoPersonaParte;
	}

	public void setTipoPersonaParte(String tipoPersonaParte) {
		this.tipoPersonaParte = tipoPersonaParte;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getEmail3() {
		return email3;
	}

	public void setEmail3(String email3) {
		this.email3 = email3;
	}

	// constructor page 1

	

}
