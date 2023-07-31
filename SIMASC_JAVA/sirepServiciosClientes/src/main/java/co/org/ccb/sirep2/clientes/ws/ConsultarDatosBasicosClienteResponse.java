
package co.org.ccb.sirep2.clientes.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import co.org.ccb.sirep2.clientes.ws.model.ConsultarDatosBasicosClienteWSOutDTO;


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
 *         &lt;element name="consultarDatosBasicosClienteReturn" type="{http://model.ws.clientes.sirep2.ccb.org.co}ConsultarDatosBasicosClienteWSOutDTO"/>
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
    "consultarDatosBasicosClienteReturn"
})
@XmlRootElement(name = "consultarDatosBasicosClienteResponse")
public class ConsultarDatosBasicosClienteResponse {

    @XmlElement(required = true, nillable = true)
    protected ConsultarDatosBasicosClienteWSOutDTO consultarDatosBasicosClienteReturn;

    /**
     * Gets the value of the consultarDatosBasicosClienteReturn property.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarDatosBasicosClienteWSOutDTO }
     *     
     */
    public ConsultarDatosBasicosClienteWSOutDTO getConsultarDatosBasicosClienteReturn() {
        return consultarDatosBasicosClienteReturn;
    }

    /**
     * Sets the value of the consultarDatosBasicosClienteReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarDatosBasicosClienteWSOutDTO }
     *     
     */
    public void setConsultarDatosBasicosClienteReturn(ConsultarDatosBasicosClienteWSOutDTO value) {
        this.consultarDatosBasicosClienteReturn = value;
    }

}
