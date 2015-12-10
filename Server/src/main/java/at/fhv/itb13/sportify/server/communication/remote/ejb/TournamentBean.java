package at.fhv.itb13.sportify.server.communication.remote.ejb;

import at.fhv.itb13.sportify.server.application.controller.TournamentController;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTournamentDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import at.fhv.itb13.sportify.shared.communication.enums.RightName;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SessionRemote;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.TournamentRemote;

import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class TournamentBean implements TournamentRemote {

    private SessionRemote _session;
    private TournamentController _tournamentController;

    public TournamentBean() {
        _tournamentController = new TournamentController();
    }

    public void setSession(SessionRemote session) {
        _session = session;
    }

    @Override
    public void createTournament(TournamentDTO tournamentDTO) throws NotAuthorizedException {
        _session.authorize(RightName.TOURNAMENT_MODIFY);
        _tournamentController.create(tournamentDTO);
    }

    @Override
    public List<TournamentDTO> getAllTournaments() {
        return _tournamentController.getAllTournaments();
    }

    @Override
    public List<SimpleTournamentDTO> getAllSimpleTournaments() {
        return _tournamentController.getAllSimpleTournaments();
    }

    @Override
    public TournamentDTO getTournamentDTOByID(String id) {
        return _tournamentController.getTournamentDTOByID(id);
    }

    @Override
    public SimpleTournamentDTO getSimpleTournamentDTOByID(String id) {
        return _tournamentController.getSimpleTournamentDTOByID(id);
    }

    @Override
    public void updateTournament(TournamentDTO tournamentDTO) throws NotAuthorizedException {
        _session.authorize(RightName.TOURNAMENT_MODIFY);
        _tournamentController.update(tournamentDTO);
    }
}
