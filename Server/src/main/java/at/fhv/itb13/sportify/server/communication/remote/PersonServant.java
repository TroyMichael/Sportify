package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.application.controller.PersonController;
import at.fhv.itb13.sportify.server.model.UserRight;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimplePersonDTO;
import at.fhv.itb13.sportify.shared.communication.enums.RightName;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import at.fhv.itb13.sportify.shared.communication.remote.PersonRemote;
import at.fhv.itb13.sportify.shared.communication.remote.Session;

import java.rmi.RemoteException;
import java.util.List;

public class PersonServant extends SessionServant implements PersonRemote {

    private PersonController _personController;

    public PersonServant(Session session) throws RemoteException {
        super(session);
        _personController = new PersonController();
    }

    @Override
    public void create(PersonDTO personDto) throws RemoteException, NotAuthorizedException {
        authorize(RightName.PERSON_MODIFY);
        _personController.create(personDto);
    }

    @Override
    public List<PersonDTO> searchPerson(PersonDTO personDto) throws RemoteException {
        return _personController.searchPerson(personDto);
    }

    @Override
    public void editPerson(PersonDTO personDTO) throws RemoteException, NotAuthorizedException {
        authorize(RightName.PERSON_MODIFY);
        _personController.saveOrUpdate(personDTO);
    }

    @Override
    public List<PersonDTO> getAllPersons() throws RemoteException {
        return _personController.getAllPersons();
    }

    @Override
    public List<SimplePersonDTO> getAllSimplePersons() throws RemoteException {
        return _personController.getAllSimplePersons();
    }
}
