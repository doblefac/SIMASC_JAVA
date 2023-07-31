
package co.org.ccb.sirep2.clientes.ws.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMatriculadoWSDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMatriculadoWSDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MatriculadoWSDTO" type="{http://model.ws.clientes.sirep2.ccb.org.co}MatriculadoWSDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMatriculadoWSDTO", propOrder = {
    "matriculadoWSDTO"
})
public class ArrayOfMatriculadoWSDTO {

    @XmlElement(name = "MatriculadoWSDTO", nillable = true)
    protected List<MatriculadoWSDTO> matriculadoWSDTO;

    /**
     * Gets the value of the matriculadoWSDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the matriculadoWSDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMatriculadoWSDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MatriculadoWSDTO }
     * 
     * 
     */
    public List<MatriculadoWSDTO> getMatriculadoWSDTO() {
        if (matriculadoWSDTO == null) {
            matriculadoWSDTO = new ArrayList<MatriculadoWSDTO>();
        }
        return this.matriculadoWSDTO;
    }

}
