package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "match")
public class Match extends PersistentObjectImpl {

    private int _duration;
    private Date _start;
    private Roster _winner;
    private Tournament _tournament;
    private Set<Roster> _rosters = new HashSet<>();


    public Match() {
    }

    public Match(int duration, Date start, Tournament tournament) {
        _duration = duration;
        _start = start;
        _tournament = tournament;
    }

    @Column(name = "duration")
    public int getDuration() {
        return _duration;
    }

    public void setDuration(int duration) {
        _duration = duration;
    }

    @Column(name = "start")
    public Date getStart() {
        return _start;
    }

    public void setStart(Date start) {
        _start = start;
    }

    @ManyToOne
    @JoinColumn(name = "winner_id", referencedColumnName = "id")
    public Roster getWinner() {
        return _winner;
    }

    public void setWinner(Roster winner) {
        _winner = winner;
    }

    @ManyToOne
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    public Tournament getTournament() {
        return _tournament;
    }

    public void setTournament(Tournament tournament) {
        _tournament = tournament;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "match")
    public Set<Roster> getRosters() {
        return _rosters;
    }

    public void setRosters(Set<Roster> rosters) {
        _rosters = rosters;
    }

    public void addRoster(Roster roster) {
        _rosters.add(roster);
    }

    public void removeRoster(Roster roster) {
        _rosters.remove(roster);
    }
}
