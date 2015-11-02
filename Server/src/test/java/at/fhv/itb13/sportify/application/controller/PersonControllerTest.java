package at.fhv.itb13.sportify.application.controller;

import at.fhv.itb13.sportify.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.communication.dtos.PersonDTOImpl;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by KYUSS on 02.11.2015.
 */
public class PersonControllerTest extends TestCase {

    public void testGetInstance() throws Exception {

    }

    public void testCreate() throws Exception {

    }

    public void testSaveOrupdate() throws Exception {

    }

    public void testSearchPerson() throws Exception {
        PersonController personController = PersonController.getInstance();
        PersonDTO personDTO = new PersonDTOImpl();
        //personDTO.setFName("Karl");
        //personDTO.setLName("Harrison");
        //personDTO.setStreet("Kennedy");
        personDTO.setCity("Dornbirn");

        List<PersonDTO> persons = personController.searchPerson(personDTO);
        for (PersonDTO personDTO1 : persons){
            System.out.println(personDTO1.getFName() + personDTO1.getLName());
        }
    }

    public void testGetCommonElements() throws Exception {

    }
}