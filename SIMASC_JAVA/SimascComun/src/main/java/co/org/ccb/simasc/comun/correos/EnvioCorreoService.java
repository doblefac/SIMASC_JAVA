
package co.org.ccb.simasc.comun.correos;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

import com.ccb.simasc.transversal.utilidades.UtilConstantes;

@WebServiceClient(name = "EnvioCorreoService", targetNamespace = UtilConstantes.URL_ENVIO_CORREOS, wsdlLocation = "META-INF/wsdl/EnvioCorreoService.asmx.wsdl")
public class EnvioCorreoService
    extends Service
{

    private final static URL ENVIOCORREOSERVICE_WSDL_LOCATION;
    private final static WebServiceException ENVIOCORREOSERVICE_EXCEPTION;
    private final static QName ENVIOCORREOSERVICE_QNAME = new QName(UtilConstantes.URL_ENVIO_CORREOS, "EnvioCorreoService");

    static {
        ENVIOCORREOSERVICE_WSDL_LOCATION = co.org.ccb.simasc.comun.correos.EnvioCorreoService.class.getClassLoader().getResource("META-INF/wsdl/EnvioCorreoService.asmx.wsdl");
        WebServiceException e = null;
        if (ENVIOCORREOSERVICE_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find 'META-INF/wsdl/EnvioCorreoService.asmx.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        ENVIOCORREOSERVICE_EXCEPTION = e;
    }

    public EnvioCorreoService() {
        super(__getWsdlLocation(), ENVIOCORREOSERVICE_QNAME);
    }

    public EnvioCorreoService(WebServiceFeature... features) {
        super(__getWsdlLocation(), ENVIOCORREOSERVICE_QNAME, features);
    }

    public EnvioCorreoService(URL wsdlLocation) {
        super(wsdlLocation, ENVIOCORREOSERVICE_QNAME);
    }

    public EnvioCorreoService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ENVIOCORREOSERVICE_QNAME, features);
    }

    public EnvioCorreoService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EnvioCorreoService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns EnvioCorreoServiceSoap
     */
    @WebEndpoint(name = "EnvioCorreoServiceSoap")
    public EnvioCorreoServiceSoap getEnvioCorreoServiceSoap() {
        return super.getPort(new QName(UtilConstantes.URL_ENVIO_CORREOS, "EnvioCorreoServiceSoap"), EnvioCorreoServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EnvioCorreoServiceSoap
     */
    @WebEndpoint(name = "EnvioCorreoServiceSoap")
    public EnvioCorreoServiceSoap getEnvioCorreoServiceSoap(WebServiceFeature... features) {
        return super.getPort(new QName(UtilConstantes.URL_ENVIO_CORREOS, "EnvioCorreoServiceSoap"), EnvioCorreoServiceSoap.class, features);
    }

    /**
     * 
     * @return
     *     returns EnvioCorreoServiceSoap
     */
    @WebEndpoint(name = "EnvioCorreoServiceSoap12")
    public EnvioCorreoServiceSoap getEnvioCorreoServiceSoap12() {
        return super.getPort(new QName(UtilConstantes.URL_ENVIO_CORREOS, "EnvioCorreoServiceSoap12"), EnvioCorreoServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EnvioCorreoServiceSoap
     */
    @WebEndpoint(name = "EnvioCorreoServiceSoap12")
    public EnvioCorreoServiceSoap getEnvioCorreoServiceSoap12(WebServiceFeature... features) {
        return super.getPort(new QName(UtilConstantes.URL_ENVIO_CORREOS, "EnvioCorreoServiceSoap12"), EnvioCorreoServiceSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ENVIOCORREOSERVICE_EXCEPTION!= null) {
            throw ENVIOCORREOSERVICE_EXCEPTION;
        }
        return ENVIOCORREOSERVICE_WSDL_LOCATION;
    }

}
