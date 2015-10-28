package at.fhv.itb13.sportify.communicaton.remote;

import at.fhv.itb13.sportify.dataTransfer.dtoInterfaces.PersonDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Patrick on 28.10.2015.
 */
public interface PersonRemote extends Remote {
    void create(PersonDTO personDto) throws RemoteException;
}
