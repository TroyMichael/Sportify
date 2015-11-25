package at.fhv.itb13.sportify.server.database;

import at.fhv.itb13.sportify.server.model.*;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

public class TeamMother extends PersistentObjectMother<Team, TeamMother> {

    private String _name = "name";
    private Sport _sport;
    private Set<Tournament> _tournaments = new HashSet<>();
    private Set<MatchTeam> _matchTeams = new HashSet<MatchTeam>();

    public TeamMother() {
        super(Team.class);
    }

    public TeamMother(Session session) {
        super(session, Team.class);
    }

    public TeamMother(Session session, String defaultId) {
        super(session, Team.class, defaultId);
    }

    @Override
    protected void configureInstance(Team team) {
        team.setName(_name);
        team.setSport(_sport);
        team.setTournaments(_tournaments);
        team.setMatchTeams(_matchTeams);
    }

    @Override
    protected ExternalTeam createInstance() {
        ExternalTeam team = new ExternalTeam();
        team.setId(getId());
        return team;
    }

    public TeamMother name(String name) {
        _name = name;
        return this;
    }

    public TeamMother sport(Sport sport) {
        _sport = sport;
        return this;
    }

    public TeamMother tournament(Tournament tournament) {
        _tournaments.add(tournament);
        return this;
    }

    public TeamMother matchTeam(MatchTeam matchTeam) {
        _matchTeams.add(matchTeam);
        return this;
    }
}