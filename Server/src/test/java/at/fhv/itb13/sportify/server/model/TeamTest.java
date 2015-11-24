package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.InternalTeamMother;
import at.fhv.itb13.sportify.server.database.TournamentMother;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class TeamTest {

    private Team _team;

    @Before
    public void setUp() {
        _team = new InternalTeamMother().tournament(new TournamentMother().instance()).instance();
    }

    @Test
    public void setTournamentsWithSet() {
        // arrange
        Set<Tournament> oldTournaments = _team.getTournaments();
        Set<Tournament> newTournaments = new HashSet<Tournament>();
        newTournaments.add(new TournamentMother().instance());

        // assert before
        assertEquals(_team.getTournaments(), oldTournaments);
        for (Tournament tournament : oldTournaments) {
            assertTrue(tournament.getTeams().contains(_team));
        }
        for (Tournament tournament : newTournaments) {
            assertTrue(!tournament.getTeams().contains(_team));
        }

        // act
        _team.setTournaments(newTournaments);

        // assert after
        assertEquals(_team.getTournaments(), newTournaments);
        for (Tournament tournament : oldTournaments) {
            assertTrue(!tournament.getTeams().contains(_team));
        }
        for (Tournament tournament : newTournaments) {
            assertTrue(tournament.getTeams().contains(_team));
        }
    }

    @Test
    public void setTournamentsWithSameSet() {
        // arrange
        Set<Tournament> oldTournaments = _team.getTournaments();

        // assert before
        assertEquals(_team.getTournaments(), oldTournaments);
        for (Tournament tournament : oldTournaments) {
            assertTrue(tournament.getTeams().contains(_team));
        }

        // act
        _team.setTournaments(oldTournaments);

        // assert after
        assertEquals(_team.getTournaments(), oldTournaments);
        for (Tournament tournament : oldTournaments) {
            assertTrue(tournament.getTeams().contains(_team));
        }
    }

    @Test
    public void setTournamentsWithNull() {
        // arrange
        Set<Tournament> oldTournaments = _team.getTournaments();

        // assert before
        assertEquals(_team.getTournaments(), oldTournaments);
        for (Tournament tournament : oldTournaments) {
            assertTrue(tournament.getTeams().contains(_team));
        }

        // act
        _team.setTournaments(null);

        // assert after
        assertNull(_team.getTournaments());
        for (Tournament tournament : oldTournaments) {
            assertTrue(!tournament.getTeams().contains(_team));
        }
    }

    @Test
    public void addTournamentWithTournament() {
        // arrange
        Set<Tournament> oldTournaments = _team.getTournaments();
        Tournament oldTournament = oldTournaments.iterator().next();
        Tournament newTournament = new TournamentMother().instance();

        // assert before
        assertTrue(_team.getTournaments().contains(oldTournament));
        assertTrue(!_team.getTournaments().contains(newTournament));
        assertTrue(_team.getTournaments().size() == 1);
        for (Tournament tournament : oldTournaments) {
            assertTrue(tournament.getTeams().contains(_team));
        }
        assertTrue(!newTournament.getTeams().contains(_team));

        // act
        _team.addTournament(newTournament);

        // assert after
        assertTrue(_team.getTournaments().contains(oldTournament));
        assertTrue(_team.getTournaments().contains(newTournament));
        assertTrue(_team.getTournaments().size() == 2);
        for (Tournament tournament : _team.getTournaments()) {
            assertTrue(tournament.getTeams().contains(_team));
        }
    }

    @Test
    public void addTournamentWithSameTournament() {
        // arrange
        Set<Tournament> oldTournaments = _team.getTournaments();
        Tournament oldTeam = oldTournaments.iterator().next();

        // assert before
        assertTrue(_team.getTournaments().contains(oldTeam));
        assertTrue(_team.getTournaments().size() == 1);
        for (Tournament tournament : oldTournaments) {
            assertTrue(tournament.getTeams().contains(_team));
        }

        // act
        _team.addTournament(oldTeam);

        // assert after
        assertTrue(_team.getTournaments().contains(oldTeam));
        assertTrue(_team.getTournaments().size() == 1);
        for (Tournament tournament : oldTournaments) {
            assertTrue(tournament.getTeams().contains(_team));
        }
    }

    @Test
    public void addTournamentWithNull() {
        // arrange
        Set<Tournament> oldTournaments = _team.getTournaments();

        // assert before
        assertTrue(_team.getTournaments().size() == 1);
        for (Tournament tournament : oldTournaments) {
            assertTrue(tournament.getTeams().contains(_team));
        }

        // act
        _team.addTournament(null);

        // assert after
        assertTrue(_team.getTournaments().size() == 1);
        for (Tournament tournament : oldTournaments) {
            assertTrue(tournament.getTeams().contains(_team));
        }
    }

    @Test
    public void removeTournamentWithTournament() {
        // arrange
        Set<Tournament> oldTournaments = _team.getTournaments();
        Tournament oldTournament = oldTournaments.iterator().next();

        // assert before
        assertTrue(_team.getTournaments().contains(oldTournament));
        assertTrue(_team.getTournaments().size() == 1);
        assertTrue(oldTournament.getTeams().contains(_team));

        // act
        _team.removeTournament(oldTournament);

        // assert after
        assertTrue(!_team.getTournaments().contains(oldTournament));
        assertTrue(_team.getTournaments().size() == 0);
        assertTrue(!oldTournament.getTeams().contains(_team));
    }

    @Test
    public void removeTournamentWithNewTournament() {
        // arrange
        Set<Tournament> oldTournaments = _team.getTournaments();
        Tournament oldTournament = oldTournaments.iterator().next();
        Tournament newTournament = new TournamentMother().instance();

        // assert before
        assertTrue(_team.getTournaments().contains(oldTournament));
        assertTrue(_team.getTournaments().size() == 1);
        assertTrue(oldTournament.getTeams().contains(_team));

        // act
        _team.removeTournament(newTournament);

        // assert after
        assertTrue(_team.getTournaments().contains(oldTournament));
        assertTrue(_team.getTournaments().size() == 1);
        assertTrue(oldTournament.getTeams().contains(_team));
    }

    @Test
    public void removeTournamentWithNull() {
        // arrange
        Set<Tournament> oldTournaments = _team.getTournaments();
        Tournament oldTournament = oldTournaments.iterator().next();

        // assert before
        assertTrue(_team.getTournaments().contains(oldTournament));
        assertTrue(_team.getTournaments().size() == 1);
        assertTrue(oldTournament.getTeams().contains(_team));

        // act
        _team.removeTournament(null);

        // assert after
        assertTrue(_team.getTournaments().contains(oldTournament));
        assertTrue(_team.getTournaments().size() == 1);
        assertTrue(oldTournament.getTeams().contains(_team));
    }
}
