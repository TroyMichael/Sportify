package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roster")
public class Roster extends PersistentObjectImpl {

    private String _name;
    private Team _team;
    private Collection<Person> _persons;

    public Roster() {
    }

    @Column(name = "name")
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    public Team getTeam() {
        return _team;
    }

    public void setTeam(Team team) {
        _team = team;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roster_person", joinColumns = {@JoinColumn(name = "roster_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "person_id", nullable = false, updatable = false)})
    public Collection<Person> getPersons() {
        return _persons;
    }

    public void setPersons(Collection<Person> persons) {
        _persons = persons;
    }
}
