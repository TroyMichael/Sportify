package Server;

import at.fhv.itb13.sportify.server.application.controller.TournamentController;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import dataContainers.WSTournament;

import javax.ejb.EJB;
import javax.jws.WebService;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michael on 09.12.2015.
 *
 */
@WebService(endpointInterface = "Server.WebServiceInterface")

public class WebServiceImpl implements WebServiceInterface {

    TournamentController tournamentController = new TournamentController();

    @Override
    public List<WSTournament> getAllClosedMatches() {

        List<WSTournament> wsTournaments = new LinkedList<>();
        List<TournamentDTO> tournamentDTOs = tournamentController.getAllTournaments();

        for (TournamentDTO tournamentDTO : tournamentDTOs) {
            wsTournaments.add(new WSTournament(tournamentDTO));
        }

        return wsTournaments;
    }
}
