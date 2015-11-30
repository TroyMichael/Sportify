package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Session extends Remote {

    UserDTO getUserDTO();

    PersonRemote getPersonRemote() throws RemoteException;

    TeamRemote getTeamRemote() throws RemoteException;

    SportRemote getSportRemote() throws RemoteException;

    TournamentRemote getTournamentRemote() throws RemoteException;

    MatchRemote getMatchRemote() throws RemoteException;

    MessageRemote getMessageRemote() throws RemoteException;
}
