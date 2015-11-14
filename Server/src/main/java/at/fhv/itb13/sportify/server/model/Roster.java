package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roster")
public class Roster extends PersistentObjectImpl {

    private String _name;
    private Team _team;
    private Match _match;
    private Set<Person> _persons = new HashSet<Person>();

    public Roster() {
    }

    public Roster(String name, Team team) {
        _name = name;
        _team = team;
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

//    @ManyToOne
//    @JoinColumn(name = "match_id", referencedColumnName = "id")
//    public Match getMatch(){
//        return _match;
//    }

    public void setMatch(Match match){
        _match = match;
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
