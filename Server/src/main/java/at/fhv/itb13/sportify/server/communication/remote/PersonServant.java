package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.application.controller.PersonController;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimplePersonDTO;
import at.fhv.itb13.sportify.shared.communication.remote.PersonRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Patrick on 28.10.2015.
 *
 */
public class PersonServant extends UnicastRemoteObject implements PersonRemote {
    private PersonController _personController;

    public PersonServant() throws RemoteException {
        super();
        _personController = new PersonController();
    }

    @Override
    public void create(PersonDTO personDto) throws RemoteException {
        _personController.create(personDto);
    }

    @Override
    public List<PersonDTO> searchPerson(PersonDTO personDto) throws RemoteException {
        return _personController.searchPerson(personDto);
    }

    @Override
    public void editPerson(PersonDTO personDTO) throws RemoteException {
        _personController.saveOrUpdate(personDTO);
    }

    @Override
    public List<PersonDTO> getAllPersons() throws RemoteException{
        return _personController.getAllPersons();
    }

    @Override
    public List<SimplePersonDTO> getAllSimplePersons() throws RemoteException {
        return _personController.getAllSimplePersons();
    }
}
