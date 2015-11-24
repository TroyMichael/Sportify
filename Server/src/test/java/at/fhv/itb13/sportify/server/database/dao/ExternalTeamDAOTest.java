package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.ExternalTeamMother;
import at.fhv.itb13.sportify.server.database.SessionFactoryRule;
import at.fhv.itb13.sportify.server.model.ExternalTeam;
import at.fhv.itb13.sportify.shared.util.IdGenerator;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExternalTeamDAOTest {

    @Rule
    public SessionFactoryRule _sf = new SessionFactoryRule();

    @Test
    public void get() {
        // arrange
        _sf.beginTransaction();
        String teamId = IdGenerator.createId();
        ExternalTeamMother teamMother = new ExternalTeamMother(_sf.getSession(), teamId);
        ExternalTeam team1 = teamMother.instance();
        // TODO: add objects to collections
        _sf.commitTransaction();

        // act
        _sf.beginTransaction();
        ExternalTeamDAO teamDAO = new ExternalTeamDAO();
        teamDAO.setSession(_sf.getSession());
        ExternalTeam team2 = teamDAO.get(teamId);
        _sf.commitTransaction();

        // assert
        assertNotNull(team1);
        assertNotNull(team2);
        assertEquals(team1.getId(), team2.getId());
        assertEquals(team1.getVersion(), team2.getVersion());
        assertEquals(team1.getName(), team2.getName());
        assertEquals(team1.getSport(), team2.getSport());
        assertEquals(team1.getMatchTeams(), team2.getMatchTeams());
    }
}
