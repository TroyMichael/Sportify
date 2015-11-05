package at.fhv.itb13.sportify.shared.communication.remote;


import at.fhv.itb13.sportify.server.communication.remote.UserServant;
import at.fhv.itb13.sportify.shared.communication.remote.PersonRemote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Niklas Fessler on 11/4/15.
 */
public interface ControllerFactory extends Remote{
    public PersonRemote getPersonRemote() throws RemoteException;
    public UserRemote getUserRemote() throws RemoteException;
}
