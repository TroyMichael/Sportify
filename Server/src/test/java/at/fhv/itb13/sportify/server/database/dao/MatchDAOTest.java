package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.MatchMother;
import at.fhv.itb13.sportify.server.database.SessionFactoryRule;
import at.fhv.itb13.sportify.server.model.Match;
import at.fhv.itb13.sportify.shared.util.IdGenerator;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MatchDAOTest {

    @Rule
    public SessionFactoryRule _sf = new SessionFactoryRule();

    @Test
    public void get() {
        // arrange
        _sf.beginTransaction();
        String matchId = IdGenerator.createId();
        MatchMother matchMother = new MatchMother(_sf.getSession(), matchId);
        Match match1 = matchMother.instance();
        // TODO: add objects to collections
        _sf.commitTransaction();

        // act
        _sf.beginTransaction();
        MatchDAO matchDAO = new MatchDAO();
        matchDAO.setSession(_sf.getSession());
        Match match2 = matchDAO.get(matchId);
        _sf.commitTransaction();

        // assert
        assertNotNull(match1);
        assertNotNull(match2);
        assertEquals(match1.getId(), match2.getId());
        assertEquals(match1.getVersion(), match2.getVersion());
        assertEquals(match1.getDuration(), match2.getDuration());
        assertEquals(match1.getStart(), match2.getStart());
        assertEquals(match1.getTournament(), match2.getTournament());
        assertEquals(match1.getMatchStatus(), match2.getMatchStatus());
        assertEquals(match1.getMatchTeams(), match2.getMatchTeams());
    }
}
