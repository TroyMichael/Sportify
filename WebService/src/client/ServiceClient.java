package client;

import client.wsdlGenerated.WebServiceImplService;
import client.wsdlGenerated.WebServiceInterface;
import client.wsdlGenerated.WsTournament;

import java.util.List;

/**
 * Created by Michael on 09.12.2015.
 *
 */
public class ServiceClient {


    public static void main(String[] args) {

        WebServiceImplService service = new WebServiceImplService();
        WebServiceInterface serviceInterface = service.getWebServiceImplPort();

        List<WsTournament> tournamentList = serviceInterface.getAllClosedMatches();

        for (WsTournament wsTournament : tournamentList) {
            System.out.println(wsTournament.getTournamentName());
        }

    }
}
