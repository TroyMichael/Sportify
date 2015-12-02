package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimplePersonDTO;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PersonRemote extends Remote {
    void create(PersonDTO personDto) throws RemoteException, NotAuthorizedException;

    List<PersonDTO> searchPerson(PersonDTO personDto) throws RemoteException;

    void editPerson(PersonDTO personDTO) throws RemoteException, NotAuthorizedException;

    List<PersonDTO> getAllPersons() throws RemoteException;

    List<SimplePersonDTO> getAllSimplePersons() throws RemoteException;
}
