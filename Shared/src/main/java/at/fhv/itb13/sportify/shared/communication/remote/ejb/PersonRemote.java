package at.fhv.itb13.sportify.shared.communication.remote.ejb;

import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimplePersonDTO;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface PersonRemote {

    void setSession(SessionRemote session);

    void create(PersonDTO personDto) throws NotAuthorizedException;

    List<PersonDTO> searchPerson(PersonDTO personDto);

    void editPerson(PersonDTO personDTO) throws NotAuthorizedException;

    List<PersonDTO> getAllPersons();

    List<SimplePersonDTO> getAllSimplePersons();
}
