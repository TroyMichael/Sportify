package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team")
@Inheritance(strategy = InheritanceType.JOINED)
public class Team extends PersistentObjectImpl {

    private String _name;
    private Sport _sport;
    private Set<Tournament> _tournaments = new HashSet<>();
    private Set<MatchTeam> _matchTeams = new HashSet<>();

    public Team() {
    }

    public Team(String name, Sport sport) {
        _name = name;
        _sport = sport;
    }

    @Column(name = "name")
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    @ManyToOne
    @JoinColumn(name = "sport_id", referencedColumnName = "id")
    public Sport getSport() {
        return _sport;
    }

    public void setSport(Sport sport) {
        _sport = sport;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tournament_team", joinColumns = {@JoinColumn(name = "team_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "tournament_id", nullable = false, updatable = false)})
    public Set<Tournament> getTournaments() {
        return _tournaments;
    }

    public void setTournaments(Set<Tournament> tournaments) {
        _tournaments = tournaments;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    public Set<MatchTeam> getMatchTeams() {
        return _matchTeams;
    }

    public void setMatchTeams(Set<MatchTeam> matchTeams) {
        _matchTeams = matchTeams;
    }

    public void addTournament(Tournament tournament) {
        _tournaments.add(tournament);
    }

    public void removeTournament(Tournament tournament) {
        _tournaments.remove(tournament);
    }

    public void addMatchTeam(MatchTeam matchTeam) {
        _matchTeams.add(matchTeam);
    }

    public void removeMatchTeam(MatchTeam matchTeam) {
        _matchTeams.remove(matchTeam);
    }
}
