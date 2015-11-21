package at.fhv.itb13.sportify.database.dao;

import at.fhv.itb13.sportify.database.RosterMother;
import at.fhv.itb13.sportify.database.SessionFactoryRule;
import at.fhv.itb13.sportify.server.database.dao.RosterDAO;
import at.fhv.itb13.sportify.server.model.Roster;
import at.fhv.itb13.sportify.shared.util.IdGenerator;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RosterDAOTest {

    @Rule
    public SessionFactoryRule _sf = new SessionFactoryRule();

    @Test
    public void get() {
        // arrange
        _sf.beginTransaction();
        String rosterId = IdGenerator.createId();
        RosterMother rosterMother = new RosterMother(_sf.getSession(), rosterId);
        Roster roster1 = rosterMother.instance();
        // TODO: add objects to collections
        _sf.commitTransaction();

        // act
        _sf.beginTransaction();
        RosterDAO rosterDAO = new RosterDAO();
        rosterDAO.setSession(_sf.getSession());
        Roster roster2 = rosterDAO.get(rosterId);
        _sf.commitTransaction();

        // assert
        assertNotNull(roster1);
        assertNotNull(roster2);
        assertEquals(roster1.getId(), roster2.getId());
        assertEquals(roster1.getVersion(), roster2.getVersion());
        assertEquals(roster1.getName(), roster2.getName());
        assertEquals(roster1.getMatchTeams(), roster2.getMatchTeams());
        assertEquals(roster1.getPersons(), roster2.getPersons());
    }
}
