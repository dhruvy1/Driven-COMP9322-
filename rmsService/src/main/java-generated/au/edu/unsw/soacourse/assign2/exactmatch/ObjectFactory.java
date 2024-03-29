
package au.edu.unsw.soacourse.assign2.exactmatch;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the au.edu.unsw.soacourse.assign2.exactmatch package. 
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

    private final static QName _NullEntryFault_QNAME = new QName("http://exactmatch.assign2.soacourse.unsw.edu.au", "nullEntryFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: au.edu.unsw.soacourse.assign2.exactmatch
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ValidateExactMatchRequest }
     * 
     */
    public ValidateExactMatchRequest createValidateExactMatchRequest() {
        return new ValidateExactMatchRequest();
    }

    /**
     * Create an instance of {@link ValidateExactMatchResponse }
     * 
     */
    public ValidateExactMatchResponse createValidateExactMatchResponse() {
        return new ValidateExactMatchResponse();
    }

    /**
     * Create an instance of {@link ServiceFaultType }
     * 
     */
    public ServiceFaultType createServiceFaultType() {
        return new ServiceFaultType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exactmatch.assign2.soacourse.unsw.edu.au", name = "nullEntryFault")
    public JAXBElement<ServiceFaultType> createNullEntryFault(ServiceFaultType value) {
        return new JAXBElement<ServiceFaultType>(_NullEntryFault_QNAME, ServiceFaultType.class, null, value);
    }

}
