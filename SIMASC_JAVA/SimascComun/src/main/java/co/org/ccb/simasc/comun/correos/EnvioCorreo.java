
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
 *         &lt;element name="inDTO" type="{http://correos.ccb.org.co/}EnvioCorreoInDTO" minOccurs="0"/>
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
    "inDTO"
})
@XmlRootElement(name = "envioCorreo")
public class EnvioCorreo {

    protected EnvioCorreoInDTO inDTO;

    /**
     * Obtiene el valor de la propiedad inDTO.
     * 
     * @return
     *     possible object is
     *     {@link EnvioCorreoInDTO }
     *     
     */
    public EnvioCorreoInDTO getInDTO() {
        return inDTO;
    }

    /**
     * Define el valor de la propiedad inDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link EnvioCorreoInDTO }
     *     
     */
    public void setInDTO(EnvioCorreoInDTO value) {
        this.inDTO = value;
    }

}
