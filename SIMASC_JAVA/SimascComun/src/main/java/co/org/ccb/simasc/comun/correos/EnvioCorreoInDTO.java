
package co.org.ccb.simasc.comun.correos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EnvioCorreoInDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EnvioCorreoInDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="llave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="de" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="para" type="{http://correos.ccb.org.co/}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="asunto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoContenido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contenido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adjuntos" type="{http://correos.ccb.org.co/}ArrayOfAdjuntoDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnvioCorreoInDTO", propOrder = {
    "llave",
    "de",
    "para",
    "asunto",
    "tipoContenido",
    "contenido",
    "adjuntos"
})
public class EnvioCorreoInDTO {

    protected String llave;
    protected String de;
    protected ArrayOfString para;
    protected String asunto;
    protected String tipoContenido;
    protected String contenido;
    protected ArrayOfAdjuntoDTO adjuntos;

    /**
     * Obtiene el valor de la propiedad llave.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLlave() {
        return llave;
    }

    /**
     * Define el valor de la propiedad llave.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLlave(String value) {
        this.llave = value;
    }

    /**
     * Obtiene el valor de la propiedad de.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDe() {
        return de;
    }

    /**
     * Define el valor de la propiedad de.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDe(String value) {
        this.de = value;
    }

    /**
     * Obtiene el valor de la propiedad para.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getPara() {
        return para;
    }

    /**
     * Define el valor de la propiedad para.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setPara(ArrayOfString value) {
        this.para = value;
    }

    /**
     * Obtiene el valor de la propiedad asunto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * Define el valor de la propiedad asunto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsunto(String value) {
        this.asunto = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoContenido.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoContenido() {
        return tipoContenido;
    }

    /**
     * Define el valor de la propiedad tipoContenido.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoContenido(String value) {
        this.tipoContenido = value;
    }

    /**
     * Obtiene el valor de la propiedad contenido.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Define el valor de la propiedad contenido.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContenido(String value) {
        this.contenido = value;
    }

    /**
     * Obtiene el valor de la propiedad adjuntos.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAdjuntoDTO }
     *     
     */
    public ArrayOfAdjuntoDTO getAdjuntos() {
        return adjuntos;
    }

    /**
     * Define el valor de la propiedad adjuntos.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAdjuntoDTO }
     *     
     */
    public void setAdjuntos(ArrayOfAdjuntoDTO value) {
        this.adjuntos = value;
    }

}
