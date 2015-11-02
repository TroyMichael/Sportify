package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.application.controller.PersonController;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.remote.PersonRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Patrick on 28.10.2015.
 */
public class PersonServant extends UnicastRemoteObject implements PersonRemote {
    private PersonController _personController;

    public PersonServant() throws RemoteException {
        super();
        _personController = PersonController.getInstance();
    }

    @Override
    public void create(PersonDTO personDto) throws RemoteException {
        _personController = PersonController.getInstance();
        _personController.create(personDto);
    }

    @Override
    public List<PersonDTO> searchPerson(PersonDTO personDto) throws RemoteException{
        return null;
    }
}
