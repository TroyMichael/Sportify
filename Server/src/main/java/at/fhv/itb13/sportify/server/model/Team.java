package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team extends PersistentObjectImpl {

    private String _name;
    private Person _trainer;
    private Sport _sport;
    private Set<Person> _persons = new HashSet<Person>();
    private Set<MatchTeam> _matchTeams = new HashSet<MatchTeam>();

    public Team() {
    }

    public Team(String name, Person trainer, Sport sport) {
        _name = name;
        _sport = sport;
        _trainer = trainer;
    }

    @Column(name = "name")
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    @ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    public Person getTrainer() {
        return _trainer;
    }

    public void setTrainer(Person trainer) {
        _trainer = trainer;
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
    public Set<MatchTeam> getMatchTeams() {
        return _matchTeams;
    }

    public void setMatchTeams(Set<MatchTeam> matchTeams) {
        _matchTeams = matchTeams;
    }

    public void addPerson(Person person) {
        _persons.add(person);
    }

    public void removePerson(Person person) {
        _persons.remove(person);
    }

    public void addMatchTeam(MatchTeam matchTeam) {
        _matchTeams.add(matchTeam);
    }

    public void removeMatchTeam(MatchTeam matchTeam) {
        _matchTeams.remove(matchTeam);
    }
}
