package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;

@Entity
@Table(name = "match_team")
public class MatchTeam extends PersistentObjectImpl {

    private Match _match;
    private Team _team;
    private Roster _roster;
    private String _points;

    public MatchTeam() {
    }

    public MatchTeam(Match match, InternalTeam team, Roster roster, String points) {
        _match = match;
        _team = team;
        _roster = roster;
        _points = points;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", referencedColumnName = "id", nullable = false)
    public Match getMatch() {
        return _match;
    }

    public void setMatch(Match match) {
        _match = match;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
    public Team getTeam() {
        return _team;
    }

    public void setTeam(Team team) {
        _team = team;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roster_id", referencedColumnName = "id")
    public Roster getRoster() {
        return _roster;
    }

    public void setRoster(Roster roster) {
        _roster = roster;
    }

    @Column(name = "points")
    public String getPoints() {
        return _points;
    }

    public void setPoints(String points) {
        _points = points;
    }
}


