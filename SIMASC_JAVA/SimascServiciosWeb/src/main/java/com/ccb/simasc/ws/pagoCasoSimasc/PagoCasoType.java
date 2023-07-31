
package com.ccb.simasc.ws.pagoCasoSimasc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PagoCasoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PagoCasoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroRecibo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroOrdenPago" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombreQuienPaga" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valorPago" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="idSede" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="detallePago" type="{http://services.simasc}DetallePagoCasoType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PagoCasoType", propOrder = {
    "numeroRecibo",
    "numeroOrdenPago",
    "nombreQuienPaga",
    "tipoIdentificacion",
    "numeroIdentificacion",
    "valorPago",
    "idSede",
    "detallePago"
})
public class PagoCasoType {

    @XmlElement(required = true)
    protected String numeroRecibo;
    @XmlElement(required = true)
    protected String numeroOrdenPago;
    @XmlElement(required = true)
    protected String nombreQuienPaga;
    @XmlElement(required = true)
    protected String tipoIdentificacion;
    @XmlElement(required = true)
    protected String numeroIdentificacion;
    @XmlElement(required = true)
    protected BigDecimal valorPago;
    @XmlElement(required = true)
    protected String idSede;
    @XmlElement(required = true)
    protected List<DetallePagoCasoType> detallePago;

    /**
     * Gets the value of the numeroRecibo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroRecibo() {
        return numeroRecibo;
    }

    /**
     * Sets the value of the numeroRecibo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroRecibo(String value) {
        this.numeroRecibo = value;
    }

    /**
     * Gets the value of the numeroOrdenPago property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroOrdenPago() {
        return numeroOrdenPago;
    }

    /**
     * Sets the value of the numeroOrdenPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroOrdenPago(String value) {
        this.numeroOrdenPago = value;
    }

    /**
     * Gets the value of the nombreQuienPaga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreQuienPaga() {
        return nombreQuienPaga;
    }

    /**
     * Sets the value of the nombreQuienPaga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreQuienPaga(String value) {
        this.nombreQuienPaga = value;
    }

    /**
     * Gets the value of the tipoIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    /**
     * Sets the value of the tipoIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoIdentificacion(String value) {
        this.tipoIdentificacion = value;
    }

    /**
     * Gets the value of the numeroIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    /**
     * Sets the value of the numeroIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroIdentificacion(String value) {
        this.numeroIdentificacion = value;
    }

    /**
     * Gets the value of the valorPago property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorPago() {
        return valorPago;
    }

    /**
     * Sets the value of the valorPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorPago(BigDecimal value) {
        this.valorPago = value;
    }

    /**
     * Gets the value of the idSede property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdSede() {
        return idSede;
    }

    /**
     * Sets the value of the idSede property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdSede(String value) {
        this.idSede = value;
    }

    /**
     * Gets the value of the detallePago property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detallePago property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetallePago().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetallePagoCasoType }
     * 
     * 
     */
    public List<DetallePagoCasoType> getDetallePago() {
        if (detallePago == null) {
            detallePago = new ArrayList<DetallePagoCasoType>();
        }
        return this.detallePago;
    }

}
