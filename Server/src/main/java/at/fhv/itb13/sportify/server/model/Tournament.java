package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tournament")
public class Tournament extends PersistentObjectImpl {

    private String _description;
    private Sport _sport;
    private Date _start;
    private String _location;
    private Set<Team> _teams = new HashSet<>();
    private Set<Match> _matches = new HashSet<>();

    public Tournament() {
    }

    public Tournament(String description, Sport sport) {
        _description = description;
        _sport = sport;
    }

    @Column(name = "description")
    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    @ManyToOne
    @JoinColumn(name = "sport_id", referencedColumnName = "id")
    public Sport getSport() {
        return _sport;
    }

    public void setSport(Sport sport) {
        _sport = sport;
    }

    @Column(name = "location")
    public String getLocation() {
        return _location;
    }

    public void setLocation(String location) {
        _location = location;
    }

    @Column(name = "start")
    public Date getStart() {
        return _start;
    }

    public void setStart(Date start) {
        _start = start;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tournament_team", joinColumns = {@JoinColumn(name = "tournament_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "team_id", nullable = false, updatable = false)})
    public Set<Team> getTeams() {
        return _teams;
    }

    public void setTeams(Set<Team> teams) {
        _teams = teams;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tournament")
    public Set<Match> getMatches() {
        return _matches;
    }

    public void setMatches(Set<Match> matches) {
        _matches = matches;
    }

    public void addTeam(InternalTeam team) {
        _teams.add(team);
    }

    public void removeTeam(InternalTeam team) {
        _teams.remove(team);
    }

    public void addMatch(Match match) {
        _matches.add(match);
    }

    public void removeMatch(Match match) {
        _matches.remove(match);
    }
}
