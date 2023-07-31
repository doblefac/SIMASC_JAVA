
package co.org.ccb.sirep2.clientes.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MatriculadoWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MatriculadoWSDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="afiliado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="categoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoCamara" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoClaseIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desCategoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descripcionCamara" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descripcionClaseIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desEstadoMat" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desIdClase" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desOrganizacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desPropiedad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desSociedad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="digitoVerificacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estadoMatricula" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estadoProponente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idPropiedad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="matricula" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numCliente" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numeroIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroInscripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="organizacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rue_codigo_camara_i" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rue_desc_camara" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rue_desc_camara_i" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rue_desc_municipio_comercial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rue_direccion_comercial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rue_municipio_comercial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rue_telefono_comercial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rue_valor_activos_sin_ajuste" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="sigla" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoSociedad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ultAnoRenova" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MatriculadoWSDTO", propOrder = {
    "afiliado",
    "categoria",
    "codigoCamara",
    "codigoClaseIdentificacion",
    "desCategoria",
    "descripcionCamara",
    "descripcionClaseIdentificacion",
    "desEstadoMat",
    "desIdClase",
    "desOrganizacion",
    "desPropiedad",
    "desSociedad",
    "digitoVerificacion",
    "estadoMatricula",
    "estadoProponente",
    "idPropiedad",
    "matricula",
    "nombre",
    "numCliente",
    "numeroIdentificacion",
    "numeroInscripcion",
    "organizacion",
    "rueCodigoCamaraI",
    "rueDescCamara",
    "rueDescCamaraI",
    "rueDescMunicipioComercial",
    "rueDireccionComercial",
    "rueMunicipioComercial",
    "rueTelefonoComercial",
    "rueValorActivosSinAjuste",
    "sigla",
    "tipoSociedad",
    "ultAnoRenova"
})
public class MatriculadoWSDTO {

    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean afiliado;
    @XmlElement(required = true, nillable = true)
    protected String categoria;
    @XmlElement(required = true, nillable = true)
    protected String codigoCamara;
    @XmlElement(required = true, nillable = true)
    protected String codigoClaseIdentificacion;
    @XmlElement(required = true, nillable = true)
    protected String desCategoria;
    @XmlElement(required = true, nillable = true)
    protected String descripcionCamara;
    @XmlElement(required = true, nillable = true)
    protected String descripcionClaseIdentificacion;
    @XmlElement(required = true, nillable = true)
    protected String desEstadoMat;
    @XmlElement(required = true, nillable = true)
    protected String desIdClase;
    @XmlElement(required = true, nillable = true)
    protected String desOrganizacion;
    @XmlElement(required = true, nillable = true)
    protected String desPropiedad;
    @XmlElement(required = true, nillable = true)
    protected String desSociedad;
    @XmlElement(required = true, nillable = true)
    protected String digitoVerificacion;
    @XmlElement(required = true, nillable = true)
    protected String estadoMatricula;
    @XmlElement(required = true, nillable = true)
    protected String estadoProponente;
    @XmlElement(required = true, nillable = true)
    protected String idPropiedad;
    @XmlElement(required = true, nillable = true)
    protected String matricula;
    @XmlElement(required = true, nillable = true)
    protected String nombre;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer numCliente;
    @XmlElement(required = true, nillable = true)
    protected String numeroIdentificacion;
    @XmlElement(required = true, nillable = true)
    protected String numeroInscripcion;
    @XmlElement(required = true, nillable = true)
    protected String organizacion;
    @XmlElement(name = "rue_codigo_camara_i", required = true, nillable = true)
    protected String rueCodigoCamaraI;
    @XmlElement(name = "rue_desc_camara", required = true, nillable = true)
    protected String rueDescCamara;
    @XmlElement(name = "rue_desc_camara_i", required = true, nillable = true)
    protected String rueDescCamaraI;
    @XmlElement(name = "rue_desc_municipio_comercial", required = true, nillable = true)
    protected String rueDescMunicipioComercial;
    @XmlElement(name = "rue_direccion_comercial", required = true, nillable = true)
    protected String rueDireccionComercial;
    @XmlElement(name = "rue_municipio_comercial", required = true, nillable = true)
    protected String rueMunicipioComercial;
    @XmlElement(name = "rue_telefono_comercial", required = true, nillable = true)
    protected String rueTelefonoComercial;
    @XmlElement(name = "rue_valor_activos_sin_ajuste", required = true, type = Double.class, nillable = true)
    protected Double rueValorActivosSinAjuste;
    @XmlElement(required = true, nillable = true)
    protected String sigla;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer tipoSociedad;
    @XmlElement(required = true, nillable = true)
    protected String ultAnoRenova;

    /**
     * Gets the value of the afiliado property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAfiliado() {
        return afiliado;
    }

    /**
     * Sets the value of the afiliado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAfiliado(Boolean value) {
        this.afiliado = value;
    }

    /**
     * Gets the value of the categoria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Sets the value of the categoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoria(String value) {
        this.categoria = value;
    }

    /**
     * Gets the value of the codigoCamara property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCamara() {
        return codigoCamara;
    }

    /**
     * Sets the value of the codigoCamara property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCamara(String value) {
        this.codigoCamara = value;
    }

    /**
     * Gets the value of the codigoClaseIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoClaseIdentificacion() {
        return codigoClaseIdentificacion;
    }

    /**
     * Sets the value of the codigoClaseIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoClaseIdentificacion(String value) {
        this.codigoClaseIdentificacion = value;
    }

    /**
     * Gets the value of the desCategoria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesCategoria() {
        return desCategoria;
    }

    /**
     * Sets the value of the desCategoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesCategoria(String value) {
        this.desCategoria = value;
    }

    /**
     * Gets the value of the descripcionCamara property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionCamara() {
        return descripcionCamara;
    }

    /**
     * Sets the value of the descripcionCamara property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionCamara(String value) {
        this.descripcionCamara = value;
    }

    /**
     * Gets the value of the descripcionClaseIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionClaseIdentificacion() {
        return descripcionClaseIdentificacion;
    }

    /**
     * Sets the value of the descripcionClaseIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionClaseIdentificacion(String value) {
        this.descripcionClaseIdentificacion = value;
    }

    /**
     * Gets the value of the desEstadoMat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesEstadoMat() {
        return desEstadoMat;
    }

    /**
     * Sets the value of the desEstadoMat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesEstadoMat(String value) {
        this.desEstadoMat = value;
    }

    /**
     * Gets the value of the desIdClase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesIdClase() {
        return desIdClase;
    }

    /**
     * Sets the value of the desIdClase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesIdClase(String value) {
        this.desIdClase = value;
    }

    /**
     * Gets the value of the desOrganizacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesOrganizacion() {
        return desOrganizacion;
    }

    /**
     * Sets the value of the desOrganizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesOrganizacion(String value) {
        this.desOrganizacion = value;
    }

    /**
     * Gets the value of the desPropiedad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesPropiedad() {
        return desPropiedad;
    }

    /**
     * Sets the value of the desPropiedad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesPropiedad(String value) {
        this.desPropiedad = value;
    }

    /**
     * Gets the value of the desSociedad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesSociedad() {
        return desSociedad;
    }

    /**
     * Sets the value of the desSociedad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesSociedad(String value) {
        this.desSociedad = value;
    }

    /**
     * Gets the value of the digitoVerificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDigitoVerificacion() {
        return digitoVerificacion;
    }

    /**
     * Sets the value of the digitoVerificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDigitoVerificacion(String value) {
        this.digitoVerificacion = value;
    }

    /**
     * Gets the value of the estadoMatricula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoMatricula() {
        return estadoMatricula;
    }

    /**
     * Sets the value of the estadoMatricula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoMatricula(String value) {
        this.estadoMatricula = value;
    }

    /**
     * Gets the value of the estadoProponente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoProponente() {
        return estadoProponente;
    }

    /**
     * Sets the value of the estadoProponente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoProponente(String value) {
        this.estadoProponente = value;
    }

    /**
     * Gets the value of the idPropiedad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPropiedad() {
        return idPropiedad;
    }

    /**
     * Sets the value of the idPropiedad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPropiedad(String value) {
        this.idPropiedad = value;
    }

    /**
     * Gets the value of the matricula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Sets the value of the matricula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatricula(String value) {
        this.matricula = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the numCliente property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumCliente() {
        return numCliente;
    }

    /**
     * Sets the value of the numCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumCliente(Integer value) {
        this.numCliente = value;
    }

    /**
     * Gets the value of the numeroIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    /**
     * Sets the value of the numeroIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroIdentificacion(String value) {
        this.numeroIdentificacion = value;
    }

    /**
     * Gets the value of the numeroInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroInscripcion() {
        return numeroInscripcion;
    }

    /**
     * Sets the value of the numeroInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroInscripcion(String value) {
        this.numeroInscripcion = value;
    }

    /**
     * Gets the value of the organizacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizacion() {
        return organizacion;
    }

    /**
     * Sets the value of the organizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizacion(String value) {
        this.organizacion = value;
    }

    /**
     * Gets the value of the rueCodigoCamaraI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRueCodigoCamaraI() {
        return rueCodigoCamaraI;
    }

    /**
     * Sets the value of the rueCodigoCamaraI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRueCodigoCamaraI(String value) {
        this.rueCodigoCamaraI = value;
    }

    /**
     * Gets the value of the rueDescCamara property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRueDescCamara() {
        return rueDescCamara;
    }

    /**
     * Sets the value of the rueDescCamara property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRueDescCamara(String value) {
        this.rueDescCamara = value;
    }

    /**
     * Gets the value of the rueDescCamaraI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRueDescCamaraI() {
        return rueDescCamaraI;
    }

    /**
     * Sets the value of the rueDescCamaraI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRueDescCamaraI(String value) {
        this.rueDescCamaraI = value;
    }

    /**
     * Gets the value of the rueDescMunicipioComercial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRueDescMunicipioComercial() {
        return rueDescMunicipioComercial;
    }

    /**
     * Sets the value of the rueDescMunicipioComercial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRueDescMunicipioComercial(String value) {
        this.rueDescMunicipioComercial = value;
    }

    /**
     * Gets the value of the rueDireccionComercial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRueDireccionComercial() {
        return rueDireccionComercial;
    }

    /**
     * Sets the value of the rueDireccionComercial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRueDireccionComercial(String value) {
        this.rueDireccionComercial = value;
    }

    /**
     * Gets the value of the rueMunicipioComercial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRueMunicipioComercial() {
        return rueMunicipioComercial;
    }

    /**
     * Sets the value of the rueMunicipioComercial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRueMunicipioComercial(String value) {
        this.rueMunicipioComercial = value;
    }

    /**
     * Gets the value of the rueTelefonoComercial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRueTelefonoComercial() {
        return rueTelefonoComercial;
    }

    /**
     * Sets the value of the rueTelefonoComercial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRueTelefonoComercial(String value) {
        this.rueTelefonoComercial = value;
    }

    /**
     * Gets the value of the rueValorActivosSinAjuste property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRueValorActivosSinAjuste() {
        return rueValorActivosSinAjuste;
    }

    /**
     * Sets the value of the rueValorActivosSinAjuste property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRueValorActivosSinAjuste(Double value) {
        this.rueValorActivosSinAjuste = value;
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
     * Gets the value of the tipoSociedad property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTipoSociedad() {
        return tipoSociedad;
    }

    /**
     * Sets the value of the tipoSociedad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTipoSociedad(Integer value) {
        this.tipoSociedad = value;
    }

    /**
     * Gets the value of the ultAnoRenova property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUltAnoRenova() {
        return ultAnoRenova;
    }

    /**
     * Sets the value of the ultAnoRenova property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUltAnoRenova(String value) {
        this.ultAnoRenova = value;
    }

}
