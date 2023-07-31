
package co.org.ccb.sirep2.clientes.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValidarProfesionLiberalOutDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidarProfesionLiberalOutDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cumpleCondicionProfesionLiberal" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="mensajeError" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoError" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidarProfesionLiberalOutDTO", propOrder = {
    "cumpleCondicionProfesionLiberal",
    "mensajeError",
    "codigoError"
})
public class ValidarProfesionLiberalOutDTO {

    protected boolean cumpleCondicionProfesionLiberal;
    @XmlElement(required = true, nillable = true)
    protected String mensajeError;
    @XmlElement(required = true, nillable = true)
    protected String codigoError;

    /**
     * Gets the value of the cumpleCondicionProfesionLiberal property.
     * 
     */
    public boolean isCumpleCondicionProfesionLiberal() {
        return cumpleCondicionProfesionLiberal;
    }

    /**
     * Sets the value of the cumpleCondicionProfesionLiberal property.
     * 
     */
    public void setCumpleCondicionProfesionLiberal(boolean value) {
        this.cumpleCondicionProfesionLiberal = value;
    }

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

}
