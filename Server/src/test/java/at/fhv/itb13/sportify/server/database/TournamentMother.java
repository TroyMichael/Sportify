package at.fhv.itb13.sportify.server.database;

import at.fhv.itb13.sportify.server.model.*;
import org.hibernate.Session;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class TournamentMother extends PersistentObjectMother<Tournament, TournamentMother> {

    private String _description = "description";
    private Sport _sport;
    private Date _start;
    private String _location;
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
        tournament.setStart(_start);
        tournament.setLocation(_location);
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

    public TournamentMother start(Date start) {
        _start = start;
        return this;
    }

    public TournamentMother location(String location) {
        _location = location;
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