package at.fhv.itb13.sportify.communicaton.servants;

import at.fhv.itb13.sportify.application.controller.PersonController;
import at.fhv.itb13.sportify.communicaton.remote.PersonRemote;
import at.fhv.itb13.sportify.dataTransfer.dtoInterfaces.PersonDTO;
import at.fhv.itb13.sportify.model.entities.Person;

import java.rmi.RemoteException;

/**
 * Created by Patrick on 28.10.2015.
 */
public class PersonServant implements PersonRemote {
    private PersonController _personController;
    @Override
    public void create(PersonDTO personDto) throws RemoteException {
        //TODO
        _personController = PersonController.getInstance();
        _personController.create(personDto);
    }
}
