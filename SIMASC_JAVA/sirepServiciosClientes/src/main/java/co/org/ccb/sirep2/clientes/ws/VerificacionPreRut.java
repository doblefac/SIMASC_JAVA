
package co.org.ccb.sirep2.clientes.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import co.org.ccb.sirep2.clientes.ws.model.VerificacionPreRutInDTO;


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
 *         &lt;element name="inDto" type="{http://model.ws.clientes.sirep2.ccb.org.co}VerificacionPreRutInDTO"/>
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
    "inDto"
})
@XmlRootElement(name = "verificacionPreRut")
public class VerificacionPreRut {

    @XmlElement(required = true, nillable = true)
    protected VerificacionPreRutInDTO inDto;

    /**
     * Gets the value of the inDto property.
     * 
     * @return
     *     possible object is
     *     {@link VerificacionPreRutInDTO }
     *     
     */
    public VerificacionPreRutInDTO getInDto() {
        return inDto;
    }

    /**
     * Sets the value of the inDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificacionPreRutInDTO }
     *     
     */
    public void setInDto(VerificacionPreRutInDTO value) {
        this.inDto = value;
    }

}
