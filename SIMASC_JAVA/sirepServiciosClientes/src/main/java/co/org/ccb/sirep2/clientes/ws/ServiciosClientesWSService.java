
package co.org.ccb.sirep2.clientes.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ServiciosClientesWSService", targetNamespace = "http://ws.clientes.sirep2.ccb.org.co", wsdlLocation = "http://ihsd:80/WSClientes/services/ServiciosClientesWS?wsdl")
public class ServiciosClientesWSService
    extends Service
{

    private final static URL SERVICIOSCLIENTESWSSERVICE_WSDL_LOCATION;
    private final static WebServiceException SERVICIOSCLIENTESWSSERVICE_EXCEPTION;
    private final static QName SERVICIOSCLIENTESWSSERVICE_QNAME = new QName("http://ws.clientes.sirep2.ccb.org.co", "ServiciosClientesWSService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://ihsd:80/WSClientes/services/ServiciosClientesWS?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SERVICIOSCLIENTESWSSERVICE_WSDL_LOCATION = url;
        SERVICIOSCLIENTESWSSERVICE_EXCEPTION = e;
    }

    public ServiciosClientesWSService() {
        super(__getWsdlLocation(), SERVICIOSCLIENTESWSSERVICE_QNAME);
    }

    public ServiciosClientesWSService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SERVICIOSCLIENTESWSSERVICE_QNAME, features);
    }

    public ServiciosClientesWSService(URL wsdlLocation) {
        super(wsdlLocation, SERVICIOSCLIENTESWSSERVICE_QNAME);
    }

    public ServiciosClientesWSService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICIOSCLIENTESWSSERVICE_QNAME, features);
    }

    public ServiciosClientesWSService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServiciosClientesWSService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ServiciosClientesWS
     */
    @WebEndpoint(name = "ServiciosClientesWS")
    public ServiciosClientesWS getServiciosClientesWS() {
        return super.getPort(new QName("http://ws.clientes.sirep2.ccb.org.co", "ServiciosClientesWS"), ServiciosClientesWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ServiciosClientesWS
     */
    @WebEndpoint(name = "ServiciosClientesWS")
    public ServiciosClientesWS getServiciosClientesWS(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.clientes.sirep2.ccb.org.co", "ServiciosClientesWS"), ServiciosClientesWS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SERVICIOSCLIENTESWSSERVICE_EXCEPTION!= null) {
            throw SERVICIOSCLIENTESWSSERVICE_EXCEPTION;
        }
        return SERVICIOSCLIENTESWSSERVICE_WSDL_LOCATION;
    }

}
