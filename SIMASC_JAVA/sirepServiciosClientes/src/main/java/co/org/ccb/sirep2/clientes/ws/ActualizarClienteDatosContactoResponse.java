
package co.org.ccb.sirep2.clientes.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import co.org.ccb.sirep2.clientes.ws.model.ActualizarClienteDatosContactoOutDTO;


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
 *         &lt;element name="actualizarClienteDatosContactoReturn" type="{http://model.ws.clientes.sirep2.ccb.org.co}ActualizarClienteDatosContactoOutDTO"/>
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
    "actualizarClienteDatosContactoReturn"
})
@XmlRootElement(name = "actualizarClienteDatosContactoResponse")
public class ActualizarClienteDatosContactoResponse {

    @XmlElement(required = true, nillable = true)
    protected ActualizarClienteDatosContactoOutDTO actualizarClienteDatosContactoReturn;

    /**
     * Gets the value of the actualizarClienteDatosContactoReturn property.
     * 
     * @return
     *     possible object is
     *     {@link ActualizarClienteDatosContactoOutDTO }
     *     
     */
    public ActualizarClienteDatosContactoOutDTO getActualizarClienteDatosContactoReturn() {
        return actualizarClienteDatosContactoReturn;
    }

    /**
     * Sets the value of the actualizarClienteDatosContactoReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActualizarClienteDatosContactoOutDTO }
     *     
     */
    public void setActualizarClienteDatosContactoReturn(ActualizarClienteDatosContactoOutDTO value) {
        this.actualizarClienteDatosContactoReturn = value;
    }

}
