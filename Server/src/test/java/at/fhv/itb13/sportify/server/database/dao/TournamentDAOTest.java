package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.SessionFactoryRule;
import at.fhv.itb13.sportify.server.database.TournamentMother;
import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.util.IdGenerator;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TournamentDAOTest {

    @Rule
    public SessionFactoryRule _sf = new SessionFactoryRule();

    @Test
    public void get() {
        // arrange
        _sf.beginTransaction();
        String tournamentId = IdGenerator.createId();
        TournamentMother tournamentMother = new TournamentMother(_sf.getSession(), tournamentId);
        Tournament tournament1 = tournamentMother.instance();
        // TODO: add objects to collections
        _sf.commitTransaction();

        // act
        _sf.beginTransaction();
        TournamentDAO tournamentDAO = new TournamentDAO();
        tournamentDAO.setSession(_sf.getSession());
        Tournament tournament2 = tournamentDAO.get(tournamentId);
        _sf.commitTransaction();

        // assert
        assertNotNull(tournament1);
        assertNotNull(tournament2);
        assertEquals(tournament1.getId(), tournament2.getId());
        assertEquals(tournament1.getVersion(), tournament2.getVersion());
        assertEquals(tournament1.getDescription(), tournament2.getDescription());
        assertEquals(tournament1.getSport(), tournament2.getSport());
        assertEquals(tournament1.getMatches(), tournament2.getMatches());
    }
}
