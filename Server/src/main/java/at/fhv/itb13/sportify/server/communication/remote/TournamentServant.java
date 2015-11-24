package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.application.controller.TournamentController;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import at.fhv.itb13.sportify.shared.communication.remote.TournamentRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by mod on 11/19/15.
 *
 */
public class TournamentServant extends UnicastRemoteObject implements TournamentRemote {

    TournamentController _controller = null;

    public TournamentServant() throws RemoteException {
        super();
        _controller = new TournamentController();
    }

    @Override
    public void createTournament(TournamentDTO tournamentDTO) throws RemoteException {
        _controller.create(tournamentDTO);
    }

    @Override
    public List<TournamentDTO> getAllTournaments() throws RemoteException {
        return _controller.getAllTournaments();
    }
}
