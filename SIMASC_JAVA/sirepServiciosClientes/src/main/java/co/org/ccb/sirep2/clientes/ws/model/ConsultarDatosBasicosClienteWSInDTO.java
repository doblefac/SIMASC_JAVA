
package co.org.ccb.sirep2.clientes.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsultarDatosBasicosClienteWSInDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultarDatosBasicosClienteWSInDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idClase" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numCliente" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numMatricula" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numInscripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numRut" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoConsulta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarDatosBasicosClienteWSInDTO", propOrder = {
    "idClase",
    "numId",
    "numCliente",
    "numMatricula",
    "numInscripcion",
    "numRut",
    "tipoConsulta"
})
public class ConsultarDatosBasicosClienteWSInDTO {

    @XmlElement(required = true, nillable = true)
    protected String idClase;
    @XmlElement(required = true, nillable = true)
    protected String numId;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer numCliente;
    @XmlElement(required = true, nillable = true)
    protected String numMatricula;
    @XmlElement(required = true, nillable = true)
    protected String numInscripcion;
    @XmlElement(required = true, nillable = true)
    protected String numRut;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer tipoConsulta;

    /**
     * Gets the value of the idClase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdClase() {
        return idClase;
    }

    /**
     * Sets the value of the idClase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdClase(String value) {
        this.idClase = value;
    }

    /**
     * Gets the value of the numId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumId() {
        return numId;
    }

    /**
     * Sets the value of the numId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumId(String value) {
        this.numId = value;
    }

    /**
     * Gets the value of the numCliente property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumCliente() {
        return numCliente;
    }

    /**
     * Sets the value of the numCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumCliente(Integer value) {
        this.numCliente = value;
    }

    /**
     * Gets the value of the numMatricula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumMatricula() {
        return numMatricula;
    }

    /**
     * Sets the value of the numMatricula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumMatricula(String value) {
        this.numMatricula = value;
    }

    /**
     * Gets the value of the numInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumInscripcion() {
        return numInscripcion;
    }

    /**
     * Sets the value of the numInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumInscripcion(String value) {
        this.numInscripcion = value;
    }

    /**
     * Gets the value of the numRut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumRut() {
        return numRut;
    }

    /**
     * Sets the value of the numRut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumRut(String value) {
        this.numRut = value;
    }

    /**
     * Gets the value of the tipoConsulta property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTipoConsulta() {
        return tipoConsulta;
    }

    /**
     * Sets the value of the tipoConsulta property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTipoConsulta(Integer value) {
        this.tipoConsulta = value;
    }

}
