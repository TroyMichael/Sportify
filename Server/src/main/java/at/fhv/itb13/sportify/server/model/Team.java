package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team extends PersistentObjectImpl {

    private String _name;
    private Sport _sport;
    private Set<Person> _persons = new HashSet<Person>();
    private Set<Roster> _rosters = new HashSet<Roster>();

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
    @JoinTable(name = "team_person", joinColumns = {@JoinColumn(name = "team_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "person_id", nullable = false, updatable = false)})
    public Set<Person> getPersons() {
        return _persons;
    }

    public void setPersons(Set<Person> persons) {
        _persons = persons;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    public Set<Roster> getRosters() {
        return _rosters;
    }

    public void setRosters(Set<Roster> rosters) {
        _rosters = rosters;
    }

    public void addPerson(Person person) {
        _persons.add(person);
    }

    public void removePerson(Person person) {
        _persons.remove(person);
    }

    public void addRoster(Roster roster) {
        _rosters.add(roster);
    }

    public void removeRoster(Roster roster) {
        _rosters.remove(roster);
    }
}
