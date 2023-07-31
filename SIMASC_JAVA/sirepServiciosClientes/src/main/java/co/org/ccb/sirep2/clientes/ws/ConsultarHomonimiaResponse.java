
package co.org.ccb.sirep2.clientes.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import co.org.ccb.sirep2.clientes.ws.model.ConsultarHomonimiaWSOutDTO;


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
 *         &lt;element name="consultarHomonimiaReturn" type="{http://model.ws.clientes.sirep2.ccb.org.co}ConsultarHomonimiaWSOutDTO"/>
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
    "consultarHomonimiaReturn"
})
@XmlRootElement(name = "consultarHomonimiaResponse")
public class ConsultarHomonimiaResponse {

    @XmlElement(required = true, nillable = true)
    protected ConsultarHomonimiaWSOutDTO consultarHomonimiaReturn;

    /**
     * Gets the value of the consultarHomonimiaReturn property.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarHomonimiaWSOutDTO }
     *     
     */
    public ConsultarHomonimiaWSOutDTO getConsultarHomonimiaReturn() {
        return consultarHomonimiaReturn;
    }

    /**
     * Sets the value of the consultarHomonimiaReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarHomonimiaWSOutDTO }
     *     
     */
    public void setConsultarHomonimiaReturn(ConsultarHomonimiaWSOutDTO value) {
        this.consultarHomonimiaReturn = value;
    }

}
