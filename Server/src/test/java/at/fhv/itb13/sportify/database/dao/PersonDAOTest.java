package at.fhv.itb13.sportify.database.dao;

import at.fhv.itb13.sportify.database.PersonMother;
import at.fhv.itb13.sportify.database.SessionFactoryRule;
import at.fhv.itb13.sportify.server.database.dao.PersonDAO;
import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.shared.util.IdGenerator;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PersonDAOTest {

    @Rule
    public SessionFactoryRule _sf = new SessionFactoryRule();

    @Test
    public void get() {
        // arrange
        _sf.beginTransaction();
        String personId = IdGenerator.createId();
        PersonMother personMother = new PersonMother(_sf.getSession(), personId);
        Person person1 = personMother.instance();
        // TODO: add objects to collections
        _sf.commitTransaction();

        // act
        _sf.beginTransaction();
        PersonDAO personDAO = new PersonDAO();
        personDAO.setSession(_sf.getSession());
        Person person2 = personDAO.get(personId);
        _sf.commitTransaction();

        // assert
        assertNotNull(person1);
        assertNotNull(person2);
        assertEquals(person1.getId(), person2.getId());
        assertEquals(person1.getVersion(), person2.getVersion());
        assertEquals(person1.getFName(), person2.getFName());
        assertEquals(person1.getLName(), person2.getLName());
        assertEquals(person1.getStreet(), person2.getStreet());
        assertEquals(person1.getHouseNumber(), person2.getHouseNumber());
        assertEquals(person1.getPostalCode(), person2.getPostalCode());
        assertEquals(person1.getCity(), person2.getCity());
        assertEquals(person1.getEmail(), person2.getEmail());
        assertEquals(person1.getBirthdate(), person2.getBirthdate());
        assertEquals(person1.isPaid(), person2.isPaid());
        assertEquals(person1.getRosters(), person2.getRosters());
        assertEquals(person1.getTeams(), person2.getTeams());
        assertEquals(person1.getTrainedTeams(), person2.getTrainedTeams());
    }
}
