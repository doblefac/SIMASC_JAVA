
package co.org.ccb.sirep2.clientes.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VerificacionPreRutInDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VerificacionPreRutInDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numFormulario" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VerificacionPreRutInDTO", propOrder = {
    "numFormulario",
    "telefono"
})
public class VerificacionPreRutInDTO {

    protected long numFormulario;
    protected long telefono;

    /**
     * Gets the value of the numFormulario property.
     * 
     */
    public long getNumFormulario() {
        return numFormulario;
    }

    /**
     * Sets the value of the numFormulario property.
     * 
     */
    public void setNumFormulario(long value) {
        this.numFormulario = value;
    }

    /**
     * Gets the value of the telefono property.
     * 
     */
    public long getTelefono() {
        return telefono;
    }

    /**
     * Sets the value of the telefono property.
     * 
     */
    public void setTelefono(long value) {
        this.telefono = value;
    }

}
