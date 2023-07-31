
package co.org.ccb.sirep2.clientes.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DireccionDetalleDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DireccionDetalleDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numCliente" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idTipoDir" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="direccion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idMunip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idPais" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idZonaPostal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idZonaGeogr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numTel1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numTel2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numFax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numAa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dirUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numMovil" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ctrMensajes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="barrioTextual" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idBarrio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ctrMensajesMail" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DireccionDetalleDTO", propOrder = {
    "numCliente",
    "idTipoDir",
    "direccion",
    "idMunip",
    "idPais",
    "idZonaPostal",
    "idZonaGeogr",
    "numTel1",
    "numTel2",
    "numFax",
    "numAa",
    "email",
    "dirUrl",
    "numMovil",
    "ctrMensajes",
    "barrioTextual",
    "idBarrio",
    "ctrMensajesMail"
})
public class DireccionDetalleDTO {

    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer numCliente;
    @XmlElement(required = true, nillable = true)
    protected String idTipoDir;
    @XmlElement(required = true, nillable = true)
    protected String direccion;
    @XmlElement(required = true, nillable = true)
    protected String idMunip;
    @XmlElement(required = true, nillable = true)
    protected String idPais;
    @XmlElement(required = true, nillable = true)
    protected String idZonaPostal;
    @XmlElement(required = true, nillable = true)
    protected String idZonaGeogr;
    @XmlElement(required = true, nillable = true)
    protected String numTel1;
    @XmlElement(required = true, nillable = true)
    protected String numTel2;
    @XmlElement(required = true, nillable = true)
    protected String numFax;
    @XmlElement(required = true, nillable = true)
    protected String numAa;
    @XmlElement(required = true, nillable = true)
    protected String email;
    @XmlElement(required = true, nillable = true)
    protected String dirUrl;
    @XmlElement(required = true, nillable = true)
    protected String numMovil;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer ctrMensajes;
    @XmlElement(required = true, nillable = true)
    protected String barrioTextual;
    @XmlElement(required = true, nillable = true)
    protected String idBarrio;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer ctrMensajesMail;

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
     * Gets the value of the idTipoDir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdTipoDir() {
        return idTipoDir;
    }

    /**
     * Sets the value of the idTipoDir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdTipoDir(String value) {
        this.idTipoDir = value;
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
     * Gets the value of the idMunip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMunip() {
        return idMunip;
    }

    /**
     * Sets the value of the idMunip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMunip(String value) {
        this.idMunip = value;
    }

    /**
     * Gets the value of the idPais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPais() {
        return idPais;
    }

    /**
     * Sets the value of the idPais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPais(String value) {
        this.idPais = value;
    }

    /**
     * Gets the value of the idZonaPostal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdZonaPostal() {
        return idZonaPostal;
    }

    /**
     * Sets the value of the idZonaPostal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdZonaPostal(String value) {
        this.idZonaPostal = value;
    }

    /**
     * Gets the value of the idZonaGeogr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdZonaGeogr() {
        return idZonaGeogr;
    }

    /**
     * Sets the value of the idZonaGeogr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdZonaGeogr(String value) {
        this.idZonaGeogr = value;
    }

    /**
     * Gets the value of the numTel1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumTel1() {
        return numTel1;
    }

    /**
     * Sets the value of the numTel1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumTel1(String value) {
        this.numTel1 = value;
    }

    /**
     * Gets the value of the numTel2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumTel2() {
        return numTel2;
    }

    /**
     * Sets the value of the numTel2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumTel2(String value) {
        this.numTel2 = value;
    }

    /**
     * Gets the value of the numFax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumFax() {
        return numFax;
    }

    /**
     * Sets the value of the numFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumFax(String value) {
        this.numFax = value;
    }

    /**
     * Gets the value of the numAa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumAa() {
        return numAa;
    }

    /**
     * Sets the value of the numAa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumAa(String value) {
        this.numAa = value;
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
     * Gets the value of the dirUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirUrl() {
        return dirUrl;
    }

    /**
     * Sets the value of the dirUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirUrl(String value) {
        this.dirUrl = value;
    }

    /**
     * Gets the value of the numMovil property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumMovil() {
        return numMovil;
    }

    /**
     * Sets the value of the numMovil property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumMovil(String value) {
        this.numMovil = value;
    }

    /**
     * Gets the value of the ctrMensajes property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCtrMensajes() {
        return ctrMensajes;
    }

    /**
     * Sets the value of the ctrMensajes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCtrMensajes(Integer value) {
        this.ctrMensajes = value;
    }

    /**
     * Gets the value of the barrioTextual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarrioTextual() {
        return barrioTextual;
    }

    /**
     * Sets the value of the barrioTextual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarrioTextual(String value) {
        this.barrioTextual = value;
    }

    /**
     * Gets the value of the idBarrio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdBarrio() {
        return idBarrio;
    }

    /**
     * Sets the value of the idBarrio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdBarrio(String value) {
        this.idBarrio = value;
    }

    /**
     * Gets the value of the ctrMensajesMail property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCtrMensajesMail() {
        return ctrMensajesMail;
    }

    /**
     * Sets the value of the ctrMensajesMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCtrMensajesMail(Integer value) {
        this.ctrMensajesMail = value;
    }

}
