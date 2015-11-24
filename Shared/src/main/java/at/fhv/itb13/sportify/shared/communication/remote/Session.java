package at.fhv.itb13.sportify.shared.communication.remote;



import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Session extends Remote {

    PersonRemote getPersonRemote() throws RemoteException;

    UserRemote getUserRemote() throws RemoteException;

    TeamRemote getTeamRemote() throws RemoteException;

    TeamDetailRemote getTeamDetailRemote() throws RemoteException;

    SportRemote getSportRemote() throws RemoteException;

    TournamentRemote getTournamentRemote() throws RemoteException;

    MatchRemote getMatchRemote() throws RemoteException;
}
