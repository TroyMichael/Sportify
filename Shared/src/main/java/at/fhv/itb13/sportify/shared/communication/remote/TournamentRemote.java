package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTournamentDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TournamentRemote extends Remote {
    void createTournament(TournamentDTO tournamentDTO) throws RemoteException, NotAuthorizedException;

    List<TournamentDTO> getAllTournaments() throws RemoteException;

    List<SimpleTournamentDTO> getAllSimpleTournaments() throws RemoteException;
}
