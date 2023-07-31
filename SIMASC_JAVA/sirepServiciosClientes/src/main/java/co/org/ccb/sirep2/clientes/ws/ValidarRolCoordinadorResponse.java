
package co.org.ccb.sirep2.clientes.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import co.org.ccb.sirep2.clientes.ws.model.ValidarRolCoordinadorOutDTO;


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
 *         &lt;element name="validarRolCoordinadorReturn" type="{http://model.ws.clientes.sirep2.ccb.org.co}ValidarRolCoordinadorOutDTO"/>
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
    "validarRolCoordinadorReturn"
})
@XmlRootElement(name = "validarRolCoordinadorResponse")
public class ValidarRolCoordinadorResponse {

    @XmlElement(required = true, nillable = true)
    protected ValidarRolCoordinadorOutDTO validarRolCoordinadorReturn;

    /**
     * Gets the value of the validarRolCoordinadorReturn property.
     * 
     * @return
     *     possible object is
     *     {@link ValidarRolCoordinadorOutDTO }
     *     
     */
    public ValidarRolCoordinadorOutDTO getValidarRolCoordinadorReturn() {
        return validarRolCoordinadorReturn;
    }

    /**
     * Sets the value of the validarRolCoordinadorReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidarRolCoordinadorOutDTO }
     *     
     */
    public void setValidarRolCoordinadorReturn(ValidarRolCoordinadorOutDTO value) {
        this.validarRolCoordinadorReturn = value;
    }

}
