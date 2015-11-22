package at.fhv.itb13.sportify.shared.communication.remote;


import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by mod on 11/19/15.
 */
public interface TournamentRemote  extends Remote {
    void createTournament(TournamentDTO tournamentDTO)  throws RemoteException;


}