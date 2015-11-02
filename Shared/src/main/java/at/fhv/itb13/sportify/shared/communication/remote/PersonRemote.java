package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Patrick on 28.10.2015.
 */
public interface PersonRemote extends Remote {
    void create(PersonDTO personDto) throws RemoteException;
}
