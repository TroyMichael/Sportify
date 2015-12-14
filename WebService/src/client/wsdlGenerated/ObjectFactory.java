
package client.wsdlGenerated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the client.wsdlGenerated package. 
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

    private final static QName _GetAllClosedMatches_QNAME = new QName("http://Server/", "getAllClosedMatches");
    private final static QName _GetAllClosedMatchesResponse_QNAME = new QName("http://Server/", "getAllClosedMatchesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client.wsdlGenerated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllClosedMatchesResponse }
     * 
     */
    public GetAllClosedMatchesResponse createGetAllClosedMatchesResponse() {
        return new GetAllClosedMatchesResponse();
    }

    /**
     * Create an instance of {@link GetAllClosedMatches }
     * 
     */
    public GetAllClosedMatches createGetAllClosedMatches() {
        return new GetAllClosedMatches();
    }

    /**
     * Create an instance of {@link WsTournament }
     * 
     */
    public WsTournament createWsTournament() {
        return new WsTournament();
    }

    /**
     * Create an instance of {@link WsMatch }
     * 
     */
    public WsMatch createWsMatch() {
        return new WsMatch();
    }

    /**
     * Create an instance of {@link WsTeam }
     * 
     */
    public WsTeam createWsTeam() {
        return new WsTeam();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllClosedMatches }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "getAllClosedMatches")
    public JAXBElement<GetAllClosedMatches> createGetAllClosedMatches(GetAllClosedMatches value) {
        return new JAXBElement<GetAllClosedMatches>(_GetAllClosedMatches_QNAME, GetAllClosedMatches.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllClosedMatchesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "getAllClosedMatchesResponse")
    public JAXBElement<GetAllClosedMatchesResponse> createGetAllClosedMatchesResponse(GetAllClosedMatchesResponse value) {
        return new JAXBElement<GetAllClosedMatchesResponse>(_GetAllClosedMatchesResponse_QNAME, GetAllClosedMatchesResponse.class, null, value);
    }

}
