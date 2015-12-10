package Server;

import at.fhv.itb13.sportify.server.application.controller.TournamentController;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;

import javax.ejb.EJB;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by Michael on 09.12.2015.
 *
 */
@WebService(endpointInterface = "Server.WebServiceInterface")
public class WebServiceImpl implements WebServiceInterface {
    TournamentController tournamentController = new TournamentController();
    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello webservice " + name;
    }

    @Override
    public String getAllClosedMatches() {
        boolean complete = true;

        List<TournamentDTO> tournamentDTOs =  tournamentController.getAllTournaments();
        StringBuilder builder = new StringBuilder();
            for(TournamentDTO simpleTournamentDTO : tournamentDTOs){
            int counter = 0;
            for(MatchDTO matchDTO:simpleTournamentDTO.getMatches()){
                if(matchDTO.getMatchStatus() == "FINISHED"){
                    builder.append(matchDTO.getTeam1().getName() + " vs. " + matchDTO.getTeam2().getName() + " " + matchDTO.getTeam1().getPoints() + ":" + matchDTO.getTeam2().getPoints());
                    builder.append("\n");
                    counter++;
                }

            }
            if(counter < simpleTournamentDTO.getMatches().size()){
                complete = false;
            }
        }
        builder.append("Alle Wettkämpfe wurden ausgeführt: "+ complete + "\n");
        return builder.toString();
    }
}
