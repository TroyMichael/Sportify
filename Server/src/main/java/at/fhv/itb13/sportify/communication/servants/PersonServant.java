package at.fhv.itb13.sportify.communication.servants;

import at.fhv.itb13.sportify.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.communication.remote.PersonRemote;

import java.rmi.RemoteException;

/**
 * Created by Patrick on 28.10.2015.
 */
public class PersonServant implements PersonRemote {
    @Override
    public void create(PersonDTO personDto) throws RemoteException {
        //TODO
    }
}
