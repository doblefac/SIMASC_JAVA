
package co.org.ccb.sirep2.clientes.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VerificacionPreRutOutDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VerificacionPreRutOutDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mensajeError" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoError" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="concepto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numFormulario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dv" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="administracion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoContribuyente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fecExpedicion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paisExpedicion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="departamentoIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="municipioIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primerApellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="segundoApellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primerNombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="otrosNombres" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="razonSocial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombreComercial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sigla" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paisDireccion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="departamentoDireccion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="municipioDireccion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="direccion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="apartadoAereo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telefono1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telefono2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="clasificacionPrincipal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaInicioPrincipal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="clsificacionSecundaria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaInicioSecundaria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="otrasClasificaciones" type="{http://model.ws.clientes.sirep2.ccb.org.co}ArrayOf_3488661_1440052060_nillable_string"/>
 *         &lt;element name="ocupacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cantidadEstablecientos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VerificacionPreRutOutDTO", propOrder = {
    "mensajeError",
    "codigoError",
    "concepto",
    "numFormulario",
    "nit",
    "dv",
    "administracion",
    "tipoContribuyente",
    "tipoIdentificacion",
    "numIdentificacion",
    "fecExpedicion",
    "paisExpedicion",
    "departamentoIdentificacion",
    "municipioIdentificacion",
    "primerApellido",
    "segundoApellido",
    "primerNombre",
    "otrosNombres",
    "razonSocial",
    "nombreComercial",
    "sigla",
    "paisDireccion",
    "departamentoDireccion",
    "municipioDireccion",
    "direccion",
    "email",
    "apartadoAereo",
    "telefono1",
    "telefono2",
    "clasificacionPrincipal",
    "fechaInicioPrincipal",
    "clsificacionSecundaria",
    "fechaInicioSecundaria",
    "otrasClasificaciones",
    "ocupacion",
    "cantidadEstablecientos"
})
public class VerificacionPreRutOutDTO {

    @XmlElement(required = true, nillable = true)
    protected String mensajeError;
    @XmlElement(required = true, nillable = true)
    protected String codigoError;
    @XmlElement(required = true, nillable = true)
    protected String concepto;
    @XmlElement(required = true, nillable = true)
    protected String numFormulario;
    @XmlElement(required = true, nillable = true)
    protected String nit;
    @XmlElement(required = true, nillable = true)
    protected String dv;
    @XmlElement(required = true, nillable = true)
    protected String administracion;
    @XmlElement(required = true, nillable = true)
    protected String tipoContribuyente;
    @XmlElement(required = true, nillable = true)
    protected String tipoIdentificacion;
    @XmlElement(required = true, nillable = true)
    protected String numIdentificacion;
    @XmlElement(required = true, nillable = true)
    protected String fecExpedicion;
    @XmlElement(required = true, nillable = true)
    protected String paisExpedicion;
    @XmlElement(required = true, nillable = true)
    protected String departamentoIdentificacion;
    @XmlElement(required = true, nillable = true)
    protected String municipioIdentificacion;
    @XmlElement(required = true, nillable = true)
    protected String primerApellido;
    @XmlElement(required = true, nillable = true)
    protected String segundoApellido;
    @XmlElement(required = true, nillable = true)
    protected String primerNombre;
    @XmlElement(required = true, nillable = true)
    protected String otrosNombres;
    @XmlElement(required = true, nillable = true)
    protected String razonSocial;
    @XmlElement(required = true, nillable = true)
    protected String nombreComercial;
    @XmlElement(required = true, nillable = true)
    protected String sigla;
    @XmlElement(required = true, nillable = true)
    protected String paisDireccion;
    @XmlElement(required = true, nillable = true)
    protected String departamentoDireccion;
    @XmlElement(required = true, nillable = true)
    protected String municipioDireccion;
    @XmlElement(required = true, nillable = true)
    protected String direccion;
    @XmlElement(required = true, nillable = true)
    protected String email;
    @XmlElement(required = true, nillable = true)
    protected String apartadoAereo;
    @XmlElement(required = true, nillable = true)
    protected String telefono1;
    @XmlElement(required = true, nillable = true)
    protected String telefono2;
    @XmlElement(required = true, nillable = true)
    protected String clasificacionPrincipal;
    @XmlElement(required = true, nillable = true)
    protected String fechaInicioPrincipal;
    @XmlElement(required = true, nillable = true)
    protected String clsificacionSecundaria;
    @XmlElement(required = true, nillable = true)
    protected String fechaInicioSecundaria;
    @XmlElement(required = true, nillable = true)
    protected ArrayOf34886611440052060NillableString otrasClasificaciones;
    @XmlElement(required = true, nillable = true)
    protected String ocupacion;
    protected int cantidadEstablecientos;

    /**
     * Gets the value of the mensajeError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensajeError() {
        return mensajeError;
    }

    /**
     * Sets the value of the mensajeError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensajeError(String value) {
        this.mensajeError = value;
    }

    /**
     * Gets the value of the codigoError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoError() {
        return codigoError;
    }

    /**
     * Sets the value of the codigoError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoError(String value) {
        this.codigoError = value;
    }

    /**
     * Gets the value of the concepto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * Sets the value of the concepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConcepto(String value) {
        this.concepto = value;
    }

    /**
     * Gets the value of the numFormulario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumFormulario() {
        return numFormulario;
    }

    /**
     * Sets the value of the numFormulario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumFormulario(String value) {
        this.numFormulario = value;
    }

    /**
     * Gets the value of the nit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNit() {
        return nit;
    }

    /**
     * Sets the value of the nit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNit(String value) {
        this.nit = value;
    }

    /**
     * Gets the value of the dv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDv() {
        return dv;
    }

    /**
     * Sets the value of the dv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDv(String value) {
        this.dv = value;
    }

    /**
     * Gets the value of the administracion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdministracion() {
        return administracion;
    }

    /**
     * Sets the value of the administracion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdministracion(String value) {
        this.administracion = value;
    }

    /**
     * Gets the value of the tipoContribuyente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoContribuyente() {
        return tipoContribuyente;
    }

    /**
     * Sets the value of the tipoContribuyente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoContribuyente(String value) {
        this.tipoContribuyente = value;
    }

    /**
     * Gets the value of the tipoIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    /**
     * Sets the value of the tipoIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoIdentificacion(String value) {
        this.tipoIdentificacion = value;
    }

    /**
     * Gets the value of the numIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    /**
     * Sets the value of the numIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumIdentificacion(String value) {
        this.numIdentificacion = value;
    }

    /**
     * Gets the value of the fecExpedicion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecExpedicion() {
        return fecExpedicion;
    }

    /**
     * Sets the value of the fecExpedicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecExpedicion(String value) {
        this.fecExpedicion = value;
    }

    /**
     * Gets the value of the paisExpedicion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaisExpedicion() {
        return paisExpedicion;
    }

    /**
     * Sets the value of the paisExpedicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaisExpedicion(String value) {
        this.paisExpedicion = value;
    }

    /**
     * Gets the value of the departamentoIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartamentoIdentificacion() {
        return departamentoIdentificacion;
    }

    /**
     * Sets the value of the departamentoIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartamentoIdentificacion(String value) {
        this.departamentoIdentificacion = value;
    }

    /**
     * Gets the value of the municipioIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipioIdentificacion() {
        return municipioIdentificacion;
    }

    /**
     * Sets the value of the municipioIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipioIdentificacion(String value) {
        this.municipioIdentificacion = value;
    }

    /**
     * Gets the value of the primerApellido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     * Sets the value of the primerApellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimerApellido(String value) {
        this.primerApellido = value;
    }

    /**
     * Gets the value of the segundoApellido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * Sets the value of the segundoApellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegundoApellido(String value) {
        this.segundoApellido = value;
    }

    /**
     * Gets the value of the primerNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimerNombre() {
        return primerNombre;
    }

    /**
     * Sets the value of the primerNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimerNombre(String value) {
        this.primerNombre = value;
    }

    /**
     * Gets the value of the otrosNombres property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtrosNombres() {
        return otrosNombres;
    }

    /**
     * Sets the value of the otrosNombres property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtrosNombres(String value) {
        this.otrosNombres = value;
    }

    /**
     * Gets the value of the razonSocial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Sets the value of the razonSocial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonSocial(String value) {
        this.razonSocial = value;
    }

    /**
     * Gets the value of the nombreComercial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreComercial() {
        return nombreComercial;
    }

    /**
     * Sets the value of the nombreComercial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreComercial(String value) {
        this.nombreComercial = value;
    }

    /**
     * Gets the value of the sigla property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * Sets the value of the sigla property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigla(String value) {
        this.sigla = value;
    }

    /**
     * Gets the value of the paisDireccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaisDireccion() {
        return paisDireccion;
    }

    /**
     * Sets the value of the paisDireccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaisDireccion(String value) {
        this.paisDireccion = value;
    }

    /**
     * Gets the value of the departamentoDireccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartamentoDireccion() {
        return departamentoDireccion;
    }

    /**
     * Sets the value of the departamentoDireccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartamentoDireccion(String value) {
        this.departamentoDireccion = value;
    }

    /**
     * Gets the value of the municipioDireccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipioDireccion() {
        return municipioDireccion;
    }

    /**
     * Sets the value of the municipioDireccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipioDireccion(String value) {
        this.municipioDireccion = value;
    }

    /**
     * Gets the value of the direccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Sets the value of the direccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccion(String value) {
        this.direccion = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the apartadoAereo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApartadoAereo() {
        return apartadoAereo;
    }

    /**
     * Sets the value of the apartadoAereo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApartadoAereo(String value) {
        this.apartadoAereo = value;
    }

    /**
     * Gets the value of the telefono1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono1() {
        return telefono1;
    }

    /**
     * Sets the value of the telefono1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono1(String value) {
        this.telefono1 = value;
    }

    /**
     * Gets the value of the telefono2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono2() {
        return telefono2;
    }

    /**
     * Sets the value of the telefono2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono2(String value) {
        this.telefono2 = value;
    }

    /**
     * Gets the value of the clasificacionPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClasificacionPrincipal() {
        return clasificacionPrincipal;
    }

    /**
     * Sets the value of the clasificacionPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClasificacionPrincipal(String value) {
        this.clasificacionPrincipal = value;
    }

    /**
     * Gets the value of the fechaInicioPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInicioPrincipal() {
        return fechaInicioPrincipal;
    }

    /**
     * Sets the value of the fechaInicioPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInicioPrincipal(String value) {
        this.fechaInicioPrincipal = value;
    }

    /**
     * Gets the value of the clsificacionSecundaria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClsificacionSecundaria() {
        return clsificacionSecundaria;
    }

    /**
     * Sets the value of the clsificacionSecundaria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClsificacionSecundaria(String value) {
        this.clsificacionSecundaria = value;
    }

    /**
     * Gets the value of the fechaInicioSecundaria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInicioSecundaria() {
        return fechaInicioSecundaria;
    }

    /**
     * Sets the value of the fechaInicioSecundaria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInicioSecundaria(String value) {
        this.fechaInicioSecundaria = value;
    }

    /**
     * Gets the value of the otrasClasificaciones property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOf34886611440052060NillableString }
     *     
     */
    public ArrayOf34886611440052060NillableString getOtrasClasificaciones() {
        return otrasClasificaciones;
    }

    /**
     * Sets the value of the otrasClasificaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOf34886611440052060NillableString }
     *     
     */
    public void setOtrasClasificaciones(ArrayOf34886611440052060NillableString value) {
        this.otrasClasificaciones = value;
    }

    /**
     * Gets the value of the ocupacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOcupacion() {
        return ocupacion;
    }

    /**
     * Sets the value of the ocupacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOcupacion(String value) {
        this.ocupacion = value;
    }

    /**
     * Gets the value of the cantidadEstablecientos property.
     * 
     */
    public int getCantidadEstablecientos() {
        return cantidadEstablecientos;
    }

    /**
     * Sets the value of the cantidadEstablecientos property.
     * 
     */
    public void setCantidadEstablecientos(int value) {
        this.cantidadEstablecientos = value;
    }

}
