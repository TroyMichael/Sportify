package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tournament")
public class Tournament extends PersistentObjectImpl {

    private String _description;
    private Sport _sport;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tournament")
    public Set<Match> getMatches() {
        return _matches;
    }

    public void setMatches(Set<Match> matches) {
        _matches = matches;
    }

    public void addMatch(Match match) {
        _matches.add(match);
    }

    public void removeMatch(Match match) {
        _matches.remove(match);
    }
}
