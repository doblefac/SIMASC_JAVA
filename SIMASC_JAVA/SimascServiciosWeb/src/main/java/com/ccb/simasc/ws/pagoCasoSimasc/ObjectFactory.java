
package com.ccb.simasc.ws.pagoCasoSimasc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccb.simasc.ws.pagoCasoSimasc package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CrearPagoCasoRequest_QNAME = new QName("http://services.simasc", "CrearPagoCasoRequest");
    private final static QName _CrearPagoCasoResponse_QNAME = new QName("http://services.simasc", "CrearPagoCasoResponse");
    private final static QName _CrearPagoCasoFault_QNAME = new QName("http://services.simasc", "CrearPagoCasoFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccb.simasc.ws.pagoCasoSimasc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CrearPagoCasoReqType }
     * 
     */
    public CrearPagoCasoReqType createCrearPagoCasoReqType() {
        return new CrearPagoCasoReqType();
    }

    /**
     * Create an instance of {@link CrearPagoCasoRespType }
     * 
     */
    public CrearPagoCasoRespType createCrearPagoCasoRespType() {
        return new CrearPagoCasoRespType();
    }

    /**
     * Create an instance of {@link CrearPagoCasoFaultType }
     * 
     */
    public CrearPagoCasoFaultType createCrearPagoCasoFaultType() {
        return new CrearPagoCasoFaultType();
    }

    /**
     * Create an instance of {@link DetallePagoCasoType }
     * 
     */
    public DetallePagoCasoType createDetallePagoCasoType() {
        return new DetallePagoCasoType();
    }

    /**
     * Create an instance of {@link PagoCasoType }
     * 
     */
    public PagoCasoType createPagoCasoType() {
        return new PagoCasoType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrearPagoCasoReqType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.simasc", name = "CrearPagoCasoRequest")
    public JAXBElement<CrearPagoCasoReqType> createCrearPagoCasoRequest(CrearPagoCasoReqType value) {
        return new JAXBElement<CrearPagoCasoReqType>(_CrearPagoCasoRequest_QNAME, CrearPagoCasoReqType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrearPagoCasoRespType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.simasc", name = "CrearPagoCasoResponse")
    public JAXBElement<CrearPagoCasoRespType> createCrearPagoCasoResponse(CrearPagoCasoRespType value) {
        return new JAXBElement<CrearPagoCasoRespType>(_CrearPagoCasoResponse_QNAME, CrearPagoCasoRespType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrearPagoCasoFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.simasc", name = "CrearPagoCasoFault")
    public JAXBElement<CrearPagoCasoFaultType> createCrearPagoCasoFault(CrearPagoCasoFaultType value) {
        return new JAXBElement<CrearPagoCasoFaultType>(_CrearPagoCasoFault_QNAME, CrearPagoCasoFaultType.class, null, value);
    }

}
