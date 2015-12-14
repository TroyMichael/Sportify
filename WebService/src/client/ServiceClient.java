package client;

import client.wsdlGenerated.WebServiceImplService;
import client.wsdlGenerated.WebServiceInterface;
import client.wsdlGenerated.WsMatch;
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

            if (wsTournament.getMatches().size() > 0) {
                for (WsMatch match : wsTournament.getMatches()) {
                    if (match.getTeam1() != null && match.getTeam2() != null) {
                        System.out.println("\t" + match.getTeam1().getTeamName() + " vs " + match.getTeam2().getTeamName() + "\t" + match.getTeam1().getScore() + " : " + match.getTeam2().getScore());
                    } else {
                        System.out.println("\told Testdata, match or matchTeams not correctly defined");
                    }
                }
            } else {
                System.out.println("\tNo Matches!");
            }
            System.out.println("\n");
        }
    }
}
