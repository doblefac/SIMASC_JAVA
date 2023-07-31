
package co.org.ccb.sirep2.clientes.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsultarHomonimiaWSInDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultarHomonimiaWSInDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sigla" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="palabrasClave" type="{http://model.ws.clientes.sirep2.ccb.org.co}ArrayOf_3488661_1440052060_nillable_string"/>
 *         &lt;element name="ctrEsal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarHomonimiaWSInDTO", propOrder = {
    "nombre",
    "sigla",
    "palabrasClave",
    "ctrEsal"
})
public class ConsultarHomonimiaWSInDTO {

    @XmlElement(required = true, nillable = true)
    protected String nombre;
    @XmlElement(required = true, nillable = true)
    protected String sigla;
    @XmlElement(required = true, nillable = true)
    protected ArrayOf34886611440052060NillableString palabrasClave;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer ctrEsal;

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the sigla property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * Sets the value of the sigla property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigla(String value) {
        this.sigla = value;
    }

    /**
     * Gets the value of the palabrasClave property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOf34886611440052060NillableString }
     *     
     */
    public ArrayOf34886611440052060NillableString getPalabrasClave() {
        return palabrasClave;
    }

    /**
     * Sets the value of the palabrasClave property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOf34886611440052060NillableString }
     *     
     */
    public void setPalabrasClave(ArrayOf34886611440052060NillableString value) {
        this.palabrasClave = value;
    }

    /**
     * Gets the value of the ctrEsal property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCtrEsal() {
        return ctrEsal;
    }

    /**
     * Sets the value of the ctrEsal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCtrEsal(Integer value) {
        this.ctrEsal = value;
    }

}
