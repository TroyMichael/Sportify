package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roster")
public class Roster extends PersistentObjectImpl {

    private String _name;
    private Set<MatchTeam> _matchTeams = new HashSet<MatchTeam>();
    private Set<Person> _persons = new HashSet<Person>();

    public Roster() {
    }

    public Roster(String name) {
        _name = name;
    }

    @Column(name = "name")
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roster")
    public Set<MatchTeam> getMatchTeams() {
        return _matchTeams;
    }

    public void setMatchTeams(Set<MatchTeam> matchTeams) {
        _matchTeams = matchTeams;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roster_person", joinColumns = {@JoinColumn(name = "roster_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "person_id", nullable = false, updatable = false)})
    public Set<Person> getPersons() {
        return _persons;
    }

    public void setPersons(Set<Person> persons) {
        _persons = persons;
    }

    public void addPerson(Person person) {
        _persons.add(person);
    }

    public void removePerson(Person person) {
        _persons.remove(person);
    }
}
