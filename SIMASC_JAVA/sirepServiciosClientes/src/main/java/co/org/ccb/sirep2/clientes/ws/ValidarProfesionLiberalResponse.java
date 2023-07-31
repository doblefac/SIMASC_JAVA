
package co.org.ccb.sirep2.clientes.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import co.org.ccb.sirep2.clientes.ws.model.ValidarProfesionLiberalOutDTO;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="validarProfesionLiberalReturn" type="{http://model.ws.clientes.sirep2.ccb.org.co}ValidarProfesionLiberalOutDTO"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "validarProfesionLiberalReturn"
})
@XmlRootElement(name = "validarProfesionLiberalResponse")
public class ValidarProfesionLiberalResponse {

    @XmlElement(required = true, nillable = true)
    protected ValidarProfesionLiberalOutDTO validarProfesionLiberalReturn;

    /**
     * Gets the value of the validarProfesionLiberalReturn property.
     * 
     * @return
     *     possible object is
     *     {@link ValidarProfesionLiberalOutDTO }
     *     
     */
    public ValidarProfesionLiberalOutDTO getValidarProfesionLiberalReturn() {
        return validarProfesionLiberalReturn;
    }

    /**
     * Sets the value of the validarProfesionLiberalReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidarProfesionLiberalOutDTO }
     *     
     */
    public void setValidarProfesionLiberalReturn(ValidarProfesionLiberalOutDTO value) {
        this.validarProfesionLiberalReturn = value;
    }

}
