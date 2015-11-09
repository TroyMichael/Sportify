package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "team")
public class Team extends PersistentObjectImpl {

    private String name;
    private Department _department;
    private Collection<Person> _persons;
    private Collection<Roster> _rosters;

    public Team() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    public Department getDepartment() {
        return _department;
    }

    public void setDepartment(Department department) {
        _department = department;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "team_person", joinColumns = {@JoinColumn(name = "team_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "person_id", nullable = false, updatable = false)})
    public Collection<Person> getPersons() {
        return _persons;
    }

    public void setPersons(Collection<Person> persons) {
        _persons = persons;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    public Collection<Roster> getRosters() {
        return _rosters;
    }

    public void setRosters(Collection<Roster> rosters) {
        _rosters = rosters;
    }
}
