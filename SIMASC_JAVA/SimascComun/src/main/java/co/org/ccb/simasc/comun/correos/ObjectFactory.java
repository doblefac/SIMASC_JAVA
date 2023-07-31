
package co.org.ccb.simasc.comun.correos;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.org.ccb.correos package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: co.org.ccb.correos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EnvioCorreoResponse }
     * 
     */
    public EnvioCorreoResponse createEnvioCorreoResponse() {
        return new EnvioCorreoResponse();
    }

    /**
     * Create an instance of {@link EnvioCorreoOutDTO }
     * 
     */
    public EnvioCorreoOutDTO createEnvioCorreoOutDTO() {
        return new EnvioCorreoOutDTO();
    }

    /**
     * Create an instance of {@link EnvioCorreo }
     * 
     */
    public EnvioCorreo createEnvioCorreo() {
        return new EnvioCorreo();
    }

    /**
     * Create an instance of {@link EnvioCorreoInDTO }
     * 
     */
    public EnvioCorreoInDTO createEnvioCorreoInDTO() {
        return new EnvioCorreoInDTO();
    }

    /**
     * Create an instance of {@link ArrayOfAdjuntoDTO }
     * 
     */
    public ArrayOfAdjuntoDTO createArrayOfAdjuntoDTO() {
        return new ArrayOfAdjuntoDTO();
    }

    /**
     * Create an instance of {@link AdjuntoDTO }
     * 
     */
    public AdjuntoDTO createAdjuntoDTO() {
        return new AdjuntoDTO();
    }

    /**
     * Create an instance of {@link ArrayOfString }
     * 
     */
    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

}
