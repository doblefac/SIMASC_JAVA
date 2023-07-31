
package com.ccb.simasc.ws.pagoCasoSimasc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrearPagoCasoFaultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrearPagoCasoFaultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="enviarMensajeFault" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoFault" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearPagoCasoFaultType", propOrder = {
    "enviarMensajeFault",
    "codigoFault"
})
public class CrearPagoCasoFaultType {

    @XmlElement(required = true)
    protected String enviarMensajeFault;
    protected int codigoFault;

    /**
     * Gets the value of the enviarMensajeFault property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnviarMensajeFault() {
        return enviarMensajeFault;
    }

    /**
     * Sets the value of the enviarMensajeFault property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnviarMensajeFault(String value) {
        this.enviarMensajeFault = value;
    }

    /**
     * Gets the value of the codigoFault property.
     * 
     */
    public int getCodigoFault() {
        return codigoFault;
    }

    /**
     * Sets the value of the codigoFault property.
     * 
     */
    public void setCodigoFault(int value) {
        this.codigoFault = value;
    }

}
