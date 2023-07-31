
package co.org.ccb.simasc.comun.correos;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "EnvioCorreoServiceSoap", targetNamespace = "http://correos.ccb.org.co/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EnvioCorreoServiceSoap {


    /**
     * 
     * @param inDTO
     * @return
     *     returns co.org.ccb.correos.EnvioCorreoOutDTO
     */
    @WebMethod(action = "http://correos.ccb.org.co/envioCorreo")
    @WebResult(name = "envioCorreoResult", targetNamespace = "http://correos.ccb.org.co/")
    @RequestWrapper(localName = "envioCorreo", targetNamespace = "http://correos.ccb.org.co/", className = "co.org.ccb.correos.EnvioCorreo")
    @ResponseWrapper(localName = "envioCorreoResponse", targetNamespace = "http://correos.ccb.org.co/", className = "co.org.ccb.correos.EnvioCorreoResponse")
    public EnvioCorreoOutDTO envioCorreo(
        @WebParam(name = "inDTO", targetNamespace = "http://correos.ccb.org.co/")
        EnvioCorreoInDTO inDTO);

}
