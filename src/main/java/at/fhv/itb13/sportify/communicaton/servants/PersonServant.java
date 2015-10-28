package at.fhv.itb13.sportify.communicaton.servants;

import at.fhv.itb13.sportify.communicaton.remote.PersonRemote;
import at.fhv.itb13.sportify.dataTransfer.dtoInterfaces.PersonDTO;

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
