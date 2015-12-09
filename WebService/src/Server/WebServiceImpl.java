package Server;

import javax.jws.WebService;

/**
 * Created by Michael on 09.12.2015.
 *
 */
@WebService(endpointInterface = "Server.WebServiceInterface")
public class WebServiceImpl implements WebServiceInterface {

    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello webservice " + name;
    }
}
