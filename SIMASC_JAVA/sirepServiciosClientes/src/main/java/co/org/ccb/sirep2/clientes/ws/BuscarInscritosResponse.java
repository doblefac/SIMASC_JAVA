
package co.org.ccb.sirep2.clientes.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="buscarInscritosReturn" type="{http://ws.clientes.sirep2.ccb.org.co}ArrayOf_577485552_3488661_nillable_BuscarInscritosWSOutDTO"/>
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
    "buscarInscritosReturn"
})
@XmlRootElement(name = "buscarInscritosResponse")
public class BuscarInscritosResponse {

    @XmlElement(required = true, nillable = true)
    protected ArrayOf5774855523488661NillableBuscarInscritosWSOutDTO buscarInscritosReturn;

    /**
     * Gets the value of the buscarInscritosReturn property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOf5774855523488661NillableBuscarInscritosWSOutDTO }
     *     
     */
    public ArrayOf5774855523488661NillableBuscarInscritosWSOutDTO getBuscarInscritosReturn() {
        return buscarInscritosReturn;
    }

    /**
     * Sets the value of the buscarInscritosReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOf5774855523488661NillableBuscarInscritosWSOutDTO }
     *     
     */
    public void setBuscarInscritosReturn(ArrayOf5774855523488661NillableBuscarInscritosWSOutDTO value) {
        this.buscarInscritosReturn = value;
    }

}
