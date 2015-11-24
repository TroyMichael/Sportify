package at.fhv.itb13.sportify.server.database;

import at.fhv.itb13.sportify.server.model.ExternalTeam;
import at.fhv.itb13.sportify.server.model.MatchTeam;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.server.model.Tournament;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

public class ExternalTeamMother extends PersistentObjectMother<ExternalTeam, ExternalTeamMother> {

    private String _name = "name";
    private Sport _sport;
    private Set<Tournament> _tournaments = new HashSet<>();
    private Set<MatchTeam> _matchTeams = new HashSet<MatchTeam>();

    public ExternalTeamMother() {
        super(ExternalTeam.class);
    }

    public ExternalTeamMother(Session session) {
        super(session, ExternalTeam.class);
    }

    public ExternalTeamMother(Session session, String defaultId) {
        super(session, ExternalTeam.class, defaultId);
    }

    @Override
    protected void configureInstance(ExternalTeam team) {
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

    public ExternalTeamMother name(String name) {
        _name = name;
        return this;
    }

    public ExternalTeamMother sport(Sport sport) {
        _sport = sport;
        return this;
    }

    public ExternalTeamMother tournament(Tournament tournament) {
        _tournaments.add(tournament);
        return this;
    }

    public ExternalTeamMother matchTeam(MatchTeam matchTeam) {
        _matchTeams.add(matchTeam);
        return this;
    }
}