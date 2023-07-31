
package co.org.ccb.sirep2.clientes.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import co.org.ccb.sirep2.clientes.ws.model.VerificacionNitOutDTO;


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
 *         &lt;element name="verificacionNitReturn" type="{http://model.ws.clientes.sirep2.ccb.org.co}VerificacionNitOutDTO"/>
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
    "verificacionNitReturn"
})
@XmlRootElement(name = "verificacionNitResponse")
public class VerificacionNitResponse {

    @XmlElement(required = true, nillable = true)
    protected VerificacionNitOutDTO verificacionNitReturn;

    /**
     * Gets the value of the verificacionNitReturn property.
     * 
     * @return
     *     possible object is
     *     {@link VerificacionNitOutDTO }
     *     
     */
    public VerificacionNitOutDTO getVerificacionNitReturn() {
        return verificacionNitReturn;
    }

    /**
     * Sets the value of the verificacionNitReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificacionNitOutDTO }
     *     
     */
    public void setVerificacionNitReturn(VerificacionNitOutDTO value) {
        this.verificacionNitReturn = value;
    }

}
