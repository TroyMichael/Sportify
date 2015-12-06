package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchStatus;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "match_")
public class Match extends PersistentObjectImpl {

    private Integer _duration;
    private Date _start;
    private Tournament _tournament;
    private MatchStatus _matchStatus;
    private Set<MatchTeam> _matchTeams = new HashSet<>();

    public Match() {
    }

    public Match(Integer duration, Date start, Tournament tournament, MatchStatus matchStatus) {
        _duration = duration;
        _start = start;
        _tournament = tournament;
        _matchStatus = matchStatus;
    }

    @Column(name = "duration")
    public Integer getDuration() {
        return _duration;
    }

    public void setDuration(Integer duration) {
        _duration = duration;
    }

    @Column(name = "start")
    @Type(type = "date")
    public Date getStart() {
        return _start;
    }

    public void setStart(Date start) {
        _start = start;
    }

    @ManyToOne
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    public Tournament getTournament() {
        return _tournament;
    }

    public void setTournament(Tournament tournament) {
        _tournament = tournament;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public MatchStatus getMatchStatus() {
        return _matchStatus;
    }

    public void setMatchStatus(MatchStatus matchStatus) {
        _matchStatus = matchStatus;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "match")
    public Set<MatchTeam> getMatchTeams() {
        return _matchTeams;
    }

    public void setMatchTeams(Set<MatchTeam> matchTeams) {
        _matchTeams = matchTeams;
    }

    public void addMatchTeam(MatchTeam matchTeam) {
        if (matchTeam != null) {
            _matchTeams.add(matchTeam);
        }
    }

    public void removeMatchTeam(MatchTeam matchTeam) {
        if (matchTeam != null) {
            _matchTeams.remove(matchTeam);
        }
    }
}
