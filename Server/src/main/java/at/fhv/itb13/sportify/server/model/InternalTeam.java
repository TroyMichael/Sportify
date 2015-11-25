package at.fhv.itb13.sportify.server.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "internalteam")
@PrimaryKeyJoinColumn(name = "team_id")
public class InternalTeam extends Team {

    private Person _trainer;
    private Set<Person> _persons = new HashSet<>();

    public InternalTeam() {
    }

    public InternalTeam(String name, Sport sport, Person trainer) {
        super(name, sport);
        _trainer = trainer;
    }

    @ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    public Person getTrainer() {
        return _trainer;
    }

    public void setTrainer(Person trainer) {
        _trainer = trainer;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "team_person", joinColumns = {@JoinColumn(name = "team_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "person_id", nullable = false, updatable = false)})
    public Set<Person> getPersons() {
        return _persons;
    }

    public void setPersons(Set<Person> persons) {
        _persons = persons;
    }

    public void addPerson(Person person) {
        if (person != null) {
            _persons.add(person);
        }
    }

    public void removePerson(Person person) {
        if (person != null) {
            _persons.remove(person);
        }
    }
}
