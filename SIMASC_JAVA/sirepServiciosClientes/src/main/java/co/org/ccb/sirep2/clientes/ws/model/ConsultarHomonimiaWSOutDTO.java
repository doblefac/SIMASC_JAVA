
package co.org.ccb.sirep2.clientes.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsultarHomonimiaWSOutDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultarHomonimiaWSOutDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mensajeError" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="matriculas" type="{http://model.ws.clientes.sirep2.ccb.org.co}ArrayOfMatriculadoWSDTO"/>
 *         &lt;element name="indicadorSirep" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="indicadorRue" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="codigoError" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarHomonimiaWSOutDTO", propOrder = {
    "mensajeError",
    "matriculas",
    "indicadorSirep",
    "indicadorRue",
    "codigoError"
})
public class ConsultarHomonimiaWSOutDTO {

    @XmlElement(required = true, nillable = true)
    protected String mensajeError;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfMatriculadoWSDTO matriculas;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer indicadorSirep;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer indicadorRue;
    @XmlElement(required = true, nillable = true)
    protected String codigoError;

    /**
     * Gets the value of the mensajeError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensajeError() {
        return mensajeError;
    }

    /**
     * Sets the value of the mensajeError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensajeError(String value) {
        this.mensajeError = value;
    }

    /**
     * Gets the value of the matriculas property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMatriculadoWSDTO }
     *     
     */
    public ArrayOfMatriculadoWSDTO getMatriculas() {
        return matriculas;
    }

    /**
     * Sets the value of the matriculas property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMatriculadoWSDTO }
     *     
     */
    public void setMatriculas(ArrayOfMatriculadoWSDTO value) {
        this.matriculas = value;
    }

    /**
     * Gets the value of the indicadorSirep property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIndicadorSirep() {
        return indicadorSirep;
    }

    /**
     * Sets the value of the indicadorSirep property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIndicadorSirep(Integer value) {
        this.indicadorSirep = value;
    }

    /**
     * Gets the value of the indicadorRue property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIndicadorRue() {
        return indicadorRue;
    }

    /**
     * Sets the value of the indicadorRue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIndicadorRue(Integer value) {
        this.indicadorRue = value;
    }

    /**
     * Gets the value of the codigoError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoError() {
        return codigoError;
    }

    /**
     * Sets the value of the codigoError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoError(String value) {
        this.codigoError = value;
    }

}
