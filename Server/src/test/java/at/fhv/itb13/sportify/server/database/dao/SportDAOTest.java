package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.SessionFactoryRule;
import at.fhv.itb13.sportify.server.database.SportMother;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.shared.util.IdGenerator;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SportDAOTest {

    @Rule
    public SessionFactoryRule _sf = new SessionFactoryRule();

    @Test
    public void get() {
        // arrange
        _sf.beginTransaction();
        String sportId = IdGenerator.createId();
        SportMother sportMother = new SportMother(_sf.getSession(), sportId);
        Sport sport1 = sportMother.instance();
        // TODO: add objects to collections
        _sf.commitTransaction();

        // act
        _sf.beginTransaction();
        SportDAO sportDAO = new SportDAO();
        sportDAO.setSession(_sf.getSession());
        Sport sport2 = sportDAO.get(sportId);
        _sf.commitTransaction();

        // assert
        assertNotNull(sport1);
        assertNotNull(sport2);
        assertEquals(sport1.getId(), sport2.getId());
        assertEquals(sport1.getVersion(), sport2.getVersion());
        assertEquals(sport1.getName(), sport2.getName());
        assertEquals(sport1.getDepartment(), sport2.getDepartment());
        assertEquals(sport1.getTournaments(), sport2.getTournaments());
        assertEquals(sport1.getTeams(), sport2.getTeams());
    }
}
