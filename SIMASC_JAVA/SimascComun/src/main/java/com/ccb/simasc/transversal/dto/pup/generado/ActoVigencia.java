//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.08 at 01:52:41 PM COT 
//


package com.ccb.simasc.transversal.dto.pup.generado;

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
 *         &lt;element ref="{http://softwaresci.com/ceet/contabilizador/servicios}FechaDocumentobroId"/>
 *         &lt;element ref="{http://softwaresci.com/ceet/contabilizador/servicios}IndicadorEspecial"/>
 *         &lt;element ref="{http://softwaresci.com/ceet/contabilizador/servicios}OrigenIdDocumento"/>
 *         &lt;element ref="{http://softwaresci.com/ceet/contabilizador/servicios}Actos"/>
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
    "fechaDocumentobroId",
    "indicadorEspecial",
    "origenIdDocumento",
    "actos"
})
@XmlRootElement(name = "ActoVigencia")
public class ActoVigencia {

    @XmlElement(name = "FechaDocumentobroId", required = true)
    protected String fechaDocumentobroId;
    @XmlElement(name = "IndicadorEspecial", required = true)
    protected String indicadorEspecial;
    @XmlElement(name = "OrigenIdDocumento", required = true)
    protected String origenIdDocumento;
    @XmlElement(name = "Actos", required = true)
    protected Actos actos;

    /**
     * Gets the value of the fechaDocumentobroId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaDocumentobroId() {
        return fechaDocumentobroId;
    }

    /**
     * Sets the value of the fechaDocumentobroId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaDocumentobroId(String value) {
        this.fechaDocumentobroId = value;
    }

    /**
     * Gets the value of the indicadorEspecial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndicadorEspecial() {
        return indicadorEspecial;
    }

    /**
     * Sets the value of the indicadorEspecial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndicadorEspecial(String value) {
        this.indicadorEspecial = value;
    }

    /**
     * Gets the value of the origenIdDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigenIdDocumento() {
        return origenIdDocumento;
    }

    /**
     * Sets the value of the origenIdDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigenIdDocumento(String value) {
        this.origenIdDocumento = value;
    }

    /**
     * Gets the value of the actos property.
     * 
     * @return
     *     possible object is
     *     {@link Actos }
     *     
     */
    public Actos getActos() {
        return actos;
    }

    /**
     * Sets the value of the actos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Actos }
     *     
     */
    public void setActos(Actos value) {
        this.actos = value;
    }

}
