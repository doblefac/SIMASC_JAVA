
package co.org.ccb.sirep2.clientes.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import co.org.ccb.sirep2.clientes.ws.model.CrearClienteDatosContactoOutDTO;


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
 *         &lt;element name="crearClienteDatosContactoReturn" type="{http://model.ws.clientes.sirep2.ccb.org.co}CrearClienteDatosContactoOutDTO"/>
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
    "crearClienteDatosContactoReturn"
})
@XmlRootElement(name = "crearClienteDatosContactoResponse")
public class CrearClienteDatosContactoResponse {

    @XmlElement(required = true, nillable = true)
    protected CrearClienteDatosContactoOutDTO crearClienteDatosContactoReturn;

    /**
     * Gets the value of the crearClienteDatosContactoReturn property.
     * 
     * @return
     *     possible object is
     *     {@link CrearClienteDatosContactoOutDTO }
     *     
     */
    public CrearClienteDatosContactoOutDTO getCrearClienteDatosContactoReturn() {
        return crearClienteDatosContactoReturn;
    }

    /**
     * Sets the value of the crearClienteDatosContactoReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrearClienteDatosContactoOutDTO }
     *     
     */
    public void setCrearClienteDatosContactoReturn(CrearClienteDatosContactoOutDTO value) {
        this.crearClienteDatosContactoReturn = value;
    }

}
