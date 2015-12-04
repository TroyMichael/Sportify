package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.application.controller.TournamentController;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTournamentDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import at.fhv.itb13.sportify.shared.communication.enums.RightName;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import at.fhv.itb13.sportify.shared.communication.remote.Session;
import at.fhv.itb13.sportify.shared.communication.remote.TournamentRemote;

import java.rmi.RemoteException;
import java.util.List;

public class TournamentServant extends SessionServant implements TournamentRemote {

    private TournamentController _tournamentController = null;

    public TournamentServant(Session session) throws RemoteException {
        super(session);
        _tournamentController = new TournamentController();
    }

    @Override
    public void createTournament(TournamentDTO tournamentDTO) throws RemoteException, NotAuthorizedException {
        authorize(RightName.TOURNAMENT_MODIFY);
        _tournamentController.create(tournamentDTO);
    }

    @Override
    public List<TournamentDTO> getAllTournaments() throws RemoteException {
        return _tournamentController.getAllTournaments();
    }

    @Override
    public List<SimpleTournamentDTO> getAllSimpleTournaments() throws RemoteException {
        return _tournamentController.getAllSimpleTournaments();
    }

    @Override
    public TournamentDTO getByID(String id) throws RemoteException {
        return _tournamentController.getByID(id);
    }

    @Override
    public void updateTournament(TournamentDTO tournamentDTO) throws RemoteException, NotAuthorizedException {
        authorize(RightName.TOURNAMENT_MODIFY);
        _tournamentController.update(tournamentDTO);
    }
}
