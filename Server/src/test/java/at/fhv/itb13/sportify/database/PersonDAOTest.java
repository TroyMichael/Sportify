package at.fhv.itb13.sportify.database;

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
    private String _id = IdGenerator.createId();

    @Test
    public void save() {
        // arrange
        PersonObjectMother personObjectMother = new PersonObjectMother(_sf.getSession(), _id);
        Person person1 = personObjectMother.instance();
        // TODO: add objects to collections

        // act
        PersonDAO personDAO = new PersonDAO();
        personDAO.setSession(_sf.getSession());
        Person person2 = personDAO.get(_id);

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
        // TODO: add assertions for collections
    }
}
