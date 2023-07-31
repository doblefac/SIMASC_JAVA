
package co.org.ccb.sirep2.clientes.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DireccionDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DireccionDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="direccion" type="{http://model.ws.clientes.sirep2.ccb.org.co}DireccionDetalleDTO"/>
 *         &lt;element name="descTipoDir" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descIdMunip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descIdPais" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DireccionDTO", propOrder = {
    "direccion",
    "descTipoDir",
    "descIdMunip",
    "descIdPais"
})
public class DireccionDTO {

    @XmlElement(required = true, nillable = true)
    protected DireccionDetalleDTO direccion;
    @XmlElement(required = true, nillable = true)
    protected String descTipoDir;
    @XmlElement(required = true, nillable = true)
    protected String descIdMunip;
    @XmlElement(required = true, nillable = true)
    protected String descIdPais;

    /**
     * Gets the value of the direccion property.
     * 
     * @return
     *     possible object is
     *     {@link DireccionDetalleDTO }
     *     
     */
    public DireccionDetalleDTO getDireccion() {
        return direccion;
    }

    /**
     * Sets the value of the direccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link DireccionDetalleDTO }
     *     
     */
    public void setDireccion(DireccionDetalleDTO value) {
        this.direccion = value;
    }

    /**
     * Gets the value of the descTipoDir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescTipoDir() {
        return descTipoDir;
    }

    /**
     * Sets the value of the descTipoDir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescTipoDir(String value) {
        this.descTipoDir = value;
    }

    /**
     * Gets the value of the descIdMunip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescIdMunip() {
        return descIdMunip;
    }

    /**
     * Sets the value of the descIdMunip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescIdMunip(String value) {
        this.descIdMunip = value;
    }

    /**
     * Gets the value of the descIdPais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescIdPais() {
        return descIdPais;
    }

    /**
     * Sets the value of the descIdPais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescIdPais(String value) {
        this.descIdPais = value;
    }

}
