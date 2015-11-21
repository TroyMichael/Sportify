package at.fhv.itb13.sportify.database;

import at.fhv.itb13.sportify.server.model.Match;
import at.fhv.itb13.sportify.server.model.MatchStatus;
import at.fhv.itb13.sportify.server.model.MatchTeam;
import at.fhv.itb13.sportify.server.model.Tournament;
import org.hibernate.Session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MatchMother extends PersistentObjectMother<Match, MatchMother> {

    private Integer _duration = 60;
    private Date _start;
    private Tournament _tournament;
    private MatchStatus _matchStatus = MatchStatus.PLANNED;
    private Set<MatchTeam> _matchTeams = new HashSet<>();

    public MatchMother() {
        super(Match.class);
        try {
            _start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2000-03-15 14:10:05");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public MatchMother(Session session) {
        super(session, Match.class);
        try {
            _start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2000-03-15 14:10:05");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public MatchMother(Session session, String defaultId) {
        super(session, Match.class, defaultId);
        try {
            _start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2000-03-15 14:10:05");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void configureInstance(Match match) {
        match.setDuration(_duration);
        match.setStart(_start);
        match.setTournament(_tournament);
        match.setMatchStatus(_matchStatus);
        match.setMatchTeams(_matchTeams);
    }

    @Override
    protected Match createInstance() {
        Match match = new Match();
        match.setId(getId());
        return match;
    }

    public MatchMother duration(Integer duration) {
        _duration = duration;
        return this;
    }

    public MatchMother start(Date start) {
        _start = start;
        return this;
    }

    public MatchMother tournament(Tournament tournament) {
        _tournament = tournament;
        return this;
    }

    public MatchMother matchStatus(MatchStatus matchStatus) {
        _matchStatus = matchStatus;
        return this;
    }

    public MatchMother matchTeam(MatchTeam matchTeam) {
        _matchTeams.add(matchTeam);
        return this;
    }
}