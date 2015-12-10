package Server;

import javax.xml.ws.Endpoint;

/**
 * Created by Michael on 09.12.2015.
 *
 */
public class RunService {

    public static void main(String[] args) {

        Endpoint.publish("http://localhost:4711/ws/hello", new WebServiceImpl());
    }
}
