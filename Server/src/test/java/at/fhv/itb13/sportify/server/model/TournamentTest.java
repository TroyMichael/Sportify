package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.InternalTeamMother;
import at.fhv.itb13.sportify.server.database.TournamentMother;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class TournamentTest {

    private Tournament _tournament;

    @Before
    public void setUp() {
        _tournament = new TournamentMother().team(new InternalTeamMother().instance()).instance();
    }

    @Test
    public void setTeamsWithSet() {
        // arrange
        Set<Team> oldTeams = _tournament.getTeams();
        Set<Team> newTeams = new HashSet<Team>();
        newTeams.add(new InternalTeamMother().instance());

        // assert before
        assertEquals(_tournament.getTeams(), oldTeams);
        for (Team team : oldTeams) {
            assertTrue(team.getTournaments().contains(_tournament));
        }
        for (Team team : newTeams) {
            assertTrue(!team.getTournaments().contains(_tournament));
        }

        // act
        _tournament.setTeams(newTeams);

        // assert after
        assertEquals(_tournament.getTeams(), newTeams);
        for (Team team : oldTeams) {
            assertTrue(!team.getTournaments().contains(_tournament));
        }
        for (Team team : newTeams) {
            assertTrue(team.getTournaments().contains(_tournament));
        }
    }

    @Test
    public void setTeamsWithSameSet() {
        // arrange
        Set<Team> oldTeams = _tournament.getTeams();

        // assert before
        assertEquals(_tournament.getTeams(), oldTeams);
        for (Team team : oldTeams) {
            assertTrue(team.getTournaments().contains(_tournament));
        }

        // act
        _tournament.setTeams(oldTeams);

        // assert after
        assertEquals(_tournament.getTeams(), oldTeams);
        for (Team team : oldTeams) {
            assertTrue(team.getTournaments().contains(_tournament));
        }
    }

    @Test
    public void setTeamsWithNull() {
        // arrange
        Set<Team> oldTeams = _tournament.getTeams();

        // assert before
        assertEquals(_tournament.getTeams(), oldTeams);
        for (Team team : oldTeams) {
            assertTrue(team.getTournaments().contains(_tournament));
        }

        // act
        _tournament.setTeams(null);

        // assert after
        assertNull(_tournament.getTeams());
        for (Team team : oldTeams) {
            assertTrue(!team.getTournaments().contains(_tournament));
        }
    }

    @Test
    public void addTeamWithTeam() {
        // arrange
        Set<Team> oldTeams = _tournament.getTeams();
        Team oldTeam = oldTeams.iterator().next();
        Team newTeam = new InternalTeamMother().instance();

        // assert before
        assertTrue(_tournament.getTeams().contains(oldTeam));
        assertTrue(!_tournament.getTeams().contains(newTeam));
        assertTrue(_tournament.getTeams().size() == 1);
        for (Team team : oldTeams) {
            assertTrue(team.getTournaments().contains(_tournament));
        }
        assertTrue(!newTeam.getTournaments().contains(_tournament));

        // act
        _tournament.addTeam(newTeam);

        // assert after
        assertTrue(_tournament.getTeams().contains(oldTeam));
        assertTrue(_tournament.getTeams().contains(newTeam));
        assertTrue(_tournament.getTeams().size() == 2);
        for (Team team : _tournament.getTeams()) {
            assertTrue(team.getTournaments().contains(_tournament));
        }
    }

    @Test
    public void addTeamWithSameTeam() {
        // arrange
        Set<Team> oldTeams = _tournament.getTeams();
        Team oldTeam = oldTeams.iterator().next();

        // assert before
        assertTrue(_tournament.getTeams().contains(oldTeam));
        assertTrue(_tournament.getTeams().size() == 1);
        for (Team team : oldTeams) {
            assertTrue(team.getTournaments().contains(_tournament));
        }

        // act
        _tournament.addTeam(oldTeam);

        // assert after
        assertTrue(_tournament.getTeams().contains(oldTeam));
        assertTrue(_tournament.getTeams().size() == 1);
        for (Team team : oldTeams) {
            assertTrue(team.getTournaments().contains(_tournament));
        }
    }

    @Test
    public void addTeamWithNull() {
        // arrange
        Set<Team> oldTeams = _tournament.getTeams();

        // assert before
        assertTrue(_tournament.getTeams().size() == 1);
        for (Team team : oldTeams) {
            assertTrue(team.getTournaments().contains(_tournament));
        }

        // act
        _tournament.addTeam(null);

        // assert after
        assertTrue(_tournament.getTeams().size() == 1);
        for (Team team : oldTeams) {
            assertTrue(team.getTournaments().contains(_tournament));
        }
    }

    @Test
    public void removeTeamWithTeam() {
        // arrange
        Set<Team> oldTeams = _tournament.getTeams();
        Team oldTeam = oldTeams.iterator().next();

        // assert before
        assertTrue(_tournament.getTeams().contains(oldTeam));
        assertTrue(_tournament.getTeams().size() == 1);
        assertTrue(oldTeam.getTournaments().contains(_tournament));

        // act
        _tournament.removeTeam(oldTeam);

        // assert after
        assertTrue(!_tournament.getTeams().contains(oldTeam));
        assertTrue(_tournament.getTeams().size() == 0);
        assertTrue(!oldTeam.getTournaments().contains(_tournament));
    }

    @Test
    public void removeTeamWithNewTeam() {
        // arrange
        Set<Team> oldTeams = _tournament.getTeams();
        Team oldTeam = oldTeams.iterator().next();
        Team newTeam = new InternalTeamMother().instance();

        // assert before
        assertTrue(_tournament.getTeams().contains(oldTeam));
        assertTrue(_tournament.getTeams().size() == 1);
        assertTrue(oldTeam.getTournaments().contains(_tournament));

        // act
        _tournament.removeTeam(newTeam);

        // assert after
        assertTrue(_tournament.getTeams().contains(oldTeam));
        assertTrue(_tournament.getTeams().size() == 1);
        assertTrue(oldTeam.getTournaments().contains(_tournament));
    }

    @Test
    public void removeTeamWithNull() {
        // arrange
        Set<Team> oldTeams = _tournament.getTeams();
        Team oldTeam = oldTeams.iterator().next();

        // assert before
        assertTrue(_tournament.getTeams().contains(oldTeam));
        assertTrue(_tournament.getTeams().size() == 1);
        assertTrue(oldTeam.getTournaments().contains(_tournament));

        // act
        _tournament.removeTeam(null);

        // assert after
        assertTrue(_tournament.getTeams().contains(oldTeam));
        assertTrue(_tournament.getTeams().size() == 1);
        assertTrue(oldTeam.getTournaments().contains(_tournament));
    }
}
