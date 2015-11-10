package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.server.communication.remote.SessionImpl;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Caroline on 10.11.2015.
 */
public interface Session extends Remote {

    PersonRemote getPersonRemote() throws RemoteException;

    UserRemote getUserRemote() throws RemoteException;

    TeamRemote getTeamRemote() throws RemoteException;

    SportRemote getSportRemote() throws RemoteException;
}
