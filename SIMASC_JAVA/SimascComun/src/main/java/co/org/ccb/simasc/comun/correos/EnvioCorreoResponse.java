
package co.org.ccb.simasc.comun.correos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="envioCorreoResult" type="{http://correos.ccb.org.co/}EnvioCorreoOutDTO" minOccurs="0"/>
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
    "envioCorreoResult"
})
@XmlRootElement(name = "envioCorreoResponse")
public class EnvioCorreoResponse {

    protected EnvioCorreoOutDTO envioCorreoResult;

    /**
     * Obtiene el valor de la propiedad envioCorreoResult.
     * 
     * @return
     *     possible object is
     *     {@link EnvioCorreoOutDTO }
     *     
     */
    public EnvioCorreoOutDTO getEnvioCorreoResult() {
        return envioCorreoResult;
    }

    /**
     * Define el valor de la propiedad envioCorreoResult.
     * 
     * @param value
     *     allowed object is
     *     {@link EnvioCorreoOutDTO }
     *     
     */
    public void setEnvioCorreoResult(EnvioCorreoOutDTO value) {
        this.envioCorreoResult = value;
    }

}
