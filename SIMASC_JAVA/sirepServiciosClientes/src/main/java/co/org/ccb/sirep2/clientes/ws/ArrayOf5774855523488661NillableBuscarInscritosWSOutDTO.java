
package co.org.ccb.sirep2.clientes.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import co.org.ccb.sirep2.clientes.ws.model.BuscarInscritosWSOutDTO;


/**
 * <p>Java class for ArrayOf_577485552_3488661_nillable_BuscarInscritosWSOutDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOf_577485552_3488661_nillable_BuscarInscritosWSOutDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BuscarInscritosWSOutDTO" type="{http://model.ws.clientes.sirep2.ccb.org.co}BuscarInscritosWSOutDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOf_577485552_3488661_nillable_BuscarInscritosWSOutDTO", propOrder = {
    "buscarInscritosWSOutDTO"
})
public class ArrayOf5774855523488661NillableBuscarInscritosWSOutDTO {

    @XmlElement(name = "BuscarInscritosWSOutDTO", nillable = true)
    protected List<BuscarInscritosWSOutDTO> buscarInscritosWSOutDTO;

    /**
     * Gets the value of the buscarInscritosWSOutDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the buscarInscritosWSOutDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBuscarInscritosWSOutDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BuscarInscritosWSOutDTO }
     * 
     * 
     */
    public List<BuscarInscritosWSOutDTO> getBuscarInscritosWSOutDTO() {
        if (buscarInscritosWSOutDTO == null) {
            buscarInscritosWSOutDTO = new ArrayList<BuscarInscritosWSOutDTO>();
        }
        return this.buscarInscritosWSOutDTO;
    }

}
