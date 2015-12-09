package Server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.*;

/**
 * Created by Michael on 09.12.2015.
 *
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface WebServiceInterface {

    @WebMethod String getHelloWorldAsString(String name);

}
