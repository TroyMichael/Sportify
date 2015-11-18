package at.fhv.itb13.sportify.application.controller;

import at.fhv.itb13.sportify.database.SessionFactoryRule;
import at.fhv.itb13.sportify.server.application.controller.PersonController;
import at.fhv.itb13.sportify.server.database.dao.SportDAO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTOImpl;
import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

/**
 * Created by KYUSS on 02.11.2015.
 */
public class PersonControllerTest extends TestCase {
    /*
    *public void create(PersonDTO person)
     *public void saveOrUpdate(PersonDTO person)
      *  public List<PersonDTO> searchPerson(PersonDTO personDTO)
       *  public List<PersonDTO> getAllPersons ()
       *   public List<SimplePersonDTO> getAllSimplePersons()
       *  public List <PersonDTO> getPayedPersons ()
     */

    @Test
    public void testCreatePerson(){
        PersonController controller = new PersonController();
        controller.create(new PersonDTOImpl());

    }
    @Test
    public void testSaveOrupdatePerson() throws Exception {

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
        /*
        for (PersonDTO personDTO1 : persons) {
            System.out.println(personDTO1.getFName() + " " + personDTO1.getLName());
        }*/
        assertTrue(persons.size() > 0);
    }

    public void testGetCommonElements() throws Exception {

    }

    public void testGetAllPersons() throws Exception {
        PersonController personController = new PersonController();
        List<PersonDTO> personDTOList = personController.getAllPersons();
        /*
        for (PersonDTO personDTO : personDTOList) {
            System.out.println(personDTO.getFName() + " " + personDTO.getLName());
        }*/
        assertTrue(personDTOList.size() > 0);
    }

    public void testGetPayedPersons() throws Exception {
        PersonController personController = new PersonController();
        List <PersonDTO> payedPersons = personController.getPayedPersons();
        for (PersonDTO personDTO : payedPersons){
            System.out.println(personDTO.getFName() + " " + personDTO.getLName());
        }
        assertTrue(payedPersons.size() > 0);
    }
}