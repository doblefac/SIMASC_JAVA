
package co.org.ccb.simasc.comun.correos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfAdjuntoDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfAdjuntoDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AdjuntoDTO" type="{http://correos.ccb.org.co/}AdjuntoDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfAdjuntoDTO", propOrder = {
    "adjuntoDTO"
})
public class ArrayOfAdjuntoDTO {

    @XmlElement(name = "AdjuntoDTO", nillable = true)
    protected List<AdjuntoDTO> adjuntoDTO;

    /**
     * Gets the value of the adjuntoDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the adjuntoDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdjuntoDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdjuntoDTO }
     * 
     * 
     */
    public List<AdjuntoDTO> getAdjuntoDTO() {
        if (adjuntoDTO == null) {
            adjuntoDTO = new ArrayList<AdjuntoDTO>();
        }
        return this.adjuntoDTO;
    }

    public void setAdjuntoDTO(List<AdjuntoDTO> adjuntoDTO) {
    	this.adjuntoDTO = adjuntoDTO;
    }
}
