package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.InternalTeamMother;
import at.fhv.itb13.sportify.server.database.SessionFactoryRule;
import at.fhv.itb13.sportify.server.model.InternalTeam;
import at.fhv.itb13.sportify.shared.util.IdGenerator;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InternalTeamDAOTest {

    @Rule
    public SessionFactoryRule _sf = new SessionFactoryRule();

    @Test
    public void get() {
        // arrange
        _sf.beginTransaction();
        String teamId = IdGenerator.createId();
        InternalTeamMother teamMother = new InternalTeamMother(_sf.getSession(), teamId);
        InternalTeam team1 = teamMother.instance();
        // TODO: add objects to collections
        _sf.commitTransaction();

        // act
        _sf.beginTransaction();
        InternalTeamDAO teamDAO = new InternalTeamDAO();
        teamDAO.setSession(_sf.getSession());
        InternalTeam team2 = teamDAO.get(teamId);
        _sf.commitTransaction();

        // assert
        assertNotNull(team1);
        assertNotNull(team2);
        assertEquals(team1.getId(), team2.getId());
        assertEquals(team1.getVersion(), team2.getVersion());
        assertEquals(team1.getName(), team2.getName());
        assertEquals(team1.getSport(), team2.getSport());
        assertEquals(team1.getTournaments(), team2.getTournaments());
        assertEquals(team1.getMatchTeams(), team2.getMatchTeams());
        assertEquals(team1.getTrainer(), team2.getTrainer());
        assertEquals(team1.getPersons(), team2.getPersons());
    }
}
