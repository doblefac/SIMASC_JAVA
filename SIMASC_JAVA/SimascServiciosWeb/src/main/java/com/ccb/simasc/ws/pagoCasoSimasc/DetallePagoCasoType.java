
package com.ccb.simasc.ws.pagoCasoSimasc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DetallePagoCasoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DetallePagoCasoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="servicioCaja" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoItem" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetallePagoCasoType", propOrder = {
    "valor",
    "servicioCaja",
    "codigoItem"
})
public class DetallePagoCasoType {

    @XmlElement(required = true)
    protected BigDecimal valor;
    @XmlElement(required = true)
    protected String servicioCaja;
    protected int codigoItem;

    /**
     * Gets the value of the valor property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * Sets the value of the valor property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValor(BigDecimal value) {
        this.valor = value;
    }

    /**
     * Gets the value of the servicioCaja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicioCaja() {
        return servicioCaja;
    }

    /**
     * Sets the value of the servicioCaja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicioCaja(String value) {
        this.servicioCaja = value;
    }

    /**
     * Gets the value of the codigoItem property.
     * 
     */
    public int getCodigoItem() {
        return codigoItem;
    }

    /**
     * Sets the value of the codigoItem property.
     * 
     */
    public void setCodigoItem(int value) {
        this.codigoItem = value;
    }

}
