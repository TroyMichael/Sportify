package at.fhv.itb13.sportify.communication.servants;

import at.fhv.itb13.sportify.application.controller.PersonController;
import at.fhv.itb13.sportify.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.communication.remote.PersonRemote;

import java.rmi.RemoteException;

/**
 * Created by Patrick on 28.10.2015.
 */
public class PersonServant implements PersonRemote {
    private PersonController _personController;

    @Override
    public void create(PersonDTO personDto) throws RemoteException {
        _personController = PersonController.getInstance();
        _personController.create(personDto);
    }
}
