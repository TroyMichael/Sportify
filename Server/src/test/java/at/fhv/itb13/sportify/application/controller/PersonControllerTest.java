package at.fhv.itb13.sportify.application.controller;

import at.fhv.itb13.sportify.server.application.controller.PersonController;
import at.fhv.itb13.sportify.server.database.dao.SportDAO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTOImpl;
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
        PersonController personController = new PersonController();
        PersonDTO personDTO = new PersonDTOImpl();
        //personDTO.setFName("Diane");
        //personDTO.setLName("Harrison");
        personDTO.setStreet("black");
        personDTO.setCity("death");
        SportDAO sportDAO = new SportDAO();
        List<PersonDTO> persons = personController.searchPerson(personDTO);
        for (PersonDTO personDTO1 : persons) {
            System.out.println(personDTO1.getFName() + " " + personDTO1.getLName());
        }
        assertTrue(persons.size() > 0);
    }

    public void testGetCommonElements() throws Exception {

    }

    public void testGetAllPersons() throws Exception {
        PersonController personController = new PersonController();
        List<PersonDTO> personDTOList = personController.getAllPersons();
        for (PersonDTO personDTO : personDTOList) {
            System.out.println(personDTO.getFName() + " " + personDTO.getLName());
        }
        assertTrue(personDTOList.size() > 0);
    }
}