package at.fhv.itb13.sportify.server.database;

import at.fhv.itb13.sportify.server.model.Match;
import at.fhv.itb13.sportify.server.model.MatchTeam;
import at.fhv.itb13.sportify.server.model.Roster;
import at.fhv.itb13.sportify.server.model.Team;
import org.hibernate.Session;

public class MatchTeamMother extends PersistentObjectMother<MatchTeam, MatchTeamMother> {

    private Match _match;
    private Team _team;
    private Roster _roster;
    private String _points;

    public MatchTeamMother() {
        super(MatchTeam.class);
    }

    public MatchTeamMother(Session session) {
        super(session, MatchTeam.class);
    }

    public MatchTeamMother(Session session, String defaultId) {
        super(session, MatchTeam.class, defaultId);
    }

    @Override
    protected void configureInstance(MatchTeam matchTeam) {
        matchTeam.setMatch(_match);
        matchTeam.setTeam(_team);
        matchTeam.setRoster(_roster);
        matchTeam.setPoints(_points);
    }

    @Override
    protected MatchTeam createInstance() {
        MatchTeam matchTeam = new MatchTeam();
        matchTeam.setId(getId());
        return matchTeam;
    }

    public MatchTeamMother match(Match match) {
        _match = match;
        return this;
    }

    public MatchTeamMother team(Team team) {
        _team = team;
        return this;
    }

    public MatchTeamMother roster(Roster roster) {
        _roster = roster;
        return this;
    }

    public MatchTeamMother points(String points) {
        _points = points;
        return this;
    }
}