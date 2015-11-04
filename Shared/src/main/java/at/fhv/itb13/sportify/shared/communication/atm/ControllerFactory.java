package at.fhv.itb13.sportify.shared.communication.atm;


import at.fhv.itb13.sportify.shared.communication.remote.PersonRemote;

import java.rmi.RemoteException;

/**
 * Created by Niklas Fessler on 11/4/15.
 */
public interface ControllerFactory {
    public PersonRemote getPersonRemote() throws RemoteException;
}
