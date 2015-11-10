package at.fhv.itb13.sportify.shared.communication.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Niklas Fessler on 11/4/15.
 */
public interface ControllerFactory extends Remote {
    PersonRemote getPersonRemote() throws RemoteException;

    UserRemote getUserRemote() throws RemoteException;

    TeamRemote getTeamRemote() throws RemoteException;
}
