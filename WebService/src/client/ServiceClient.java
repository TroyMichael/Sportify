package client;

import Server.WebServiceImpl;
import Server.WebServiceInterface;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceRef;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Michael on 09.12.2015.
 *
 */
public class ServiceClient {


    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:4711/ws/hello?wsdl");
            QName qName = new QName("http://Server/", "WebServiceImplService");
            Service service = Service.create(url, qName);
            WebServiceInterface impl = service.getPort(WebServiceInterface.class);
            System.out.println(impl.getAllClosedMatches());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
