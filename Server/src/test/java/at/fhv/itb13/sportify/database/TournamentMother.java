package at.fhv.itb13.sportify.database;

import at.fhv.itb13.sportify.server.model.Match;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.server.model.Tournament;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

public class TournamentMother extends PersistentObjectMother<Tournament, TournamentMother> {

    private String _description = "description";
    private Sport _sport;
    private Set<Team> _teams = new HashSet<>();
    private Set<Match> _matches = new HashSet<>();

    public TournamentMother() {
        super(Tournament.class);
    }

    public TournamentMother(Session session) {
        super(session, Tournament.class);
    }

    public TournamentMother(Session session, String defaultId) {
        super(session, Tournament.class, defaultId);
    }

    @Override
    protected void configureInstance(Tournament tournament) {
        tournament.setDescription(_description);
        tournament.setSport(_sport);
        tournament.setTeams(_teams);
        tournament.setMatches(_matches);
    }

    @Override
    protected Tournament createInstance() {
        Tournament tournament = new Tournament();
        tournament.setId(getId());
        return tournament;
    }

    public TournamentMother description(String description) {
        _description = description;
        return this;
    }

    public TournamentMother sport(Sport sport) {
        _sport = sport;
        return this;
    }

    public TournamentMother team(Team team) {
        _teams.add(team);
        return this;
    }

    public TournamentMother match(Match match) {
        _matches.add(match);
        return this;
    }
}