
package com.ccb.simasc.ws.pagoCasoSimasc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrearPagoCasoReqType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrearPagoCasoReqType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PagoCaso" type="{http://services.simasc}PagoCasoType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearPagoCasoReqType", propOrder = {
    "pagoCaso"
})
public class CrearPagoCasoReqType {

    @XmlElement(name = "PagoCaso", required = true)
    protected PagoCasoType pagoCaso;

    /**
     * Gets the value of the pagoCaso property.
     * 
     * @return
     *     possible object is
     *     {@link PagoCasoType }
     *     
     */
    public PagoCasoType getPagoCaso() {
        return pagoCaso;
    }

    /**
     * Sets the value of the pagoCaso property.
     * 
     * @param value
     *     allowed object is
     *     {@link PagoCasoType }
     *     
     */
    public void setPagoCaso(PagoCasoType value) {
        this.pagoCaso = value;
    }

}
