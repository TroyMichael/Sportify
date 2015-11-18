package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimplePersonDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Patrick on 28.10.2015.
 *
 */
public interface PersonRemote extends Remote {
    void create(PersonDTO personDto) throws RemoteException;

    List<PersonDTO> searchPerson(PersonDTO personDto) throws RemoteException;

    void editPerson(PersonDTO personDTO) throws RemoteException;

    List<PersonDTO> getAllPersons() throws RemoteException;

    List<SimplePersonDTO> getAllSimplePersons() throws RemoteException;
}
