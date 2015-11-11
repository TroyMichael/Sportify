package at.fhv.itb13.sportify.client.communication;

import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTOImpl;
import at.fhv.itb13.sportify.shared.communication.remote.PersonRemote;
import junit.framework.TestCase;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by KYUSS on 02.11.2015.
 */
public class ServiceLocatorTest extends TestCase {

    public void testGetInstance() throws Exception {
    }

    public void testGetPersonRemote() throws Exception {
    }

    public void testSearchPerson() throws RemoteException {
        PersonDTO personDTO = new PersonDTOImpl();
        //personDTO.setFName("caro");
        //personDTO.setLName("Harrison");
        //personDTO.setStreet("Kennedy");
        personDTO.setCity("dorn");

        List<PersonDTO> persons = ServiceLocator.getInstance().getRemote(PersonRemote.class).searchPerson(personDTO);
        for (PersonDTO personDTO1 : persons) {
            System.out.println(personDTO1.getFName() + personDTO1.getLName());
        }
    }
}