package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sport")
public class Sport extends PersistentObjectImpl {

    private String _name;
    private Department _department;
    private Set<Team> _teams = new HashSet<Team>();

    public Sport() {
    }

    public Sport(String name, Department department) {
        _name = name;
        _department = department;
    }

    @Column(name = "name")
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    public Department getDepartment() {
        return _department;
    }

    public void setDepartment(Department department) {
        _department = department;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sport")
    public Set<Team> getTeams() {
        return _teams;
    }

    public void setTeams(Set<Team> teams) {
        _teams = teams;
    }

    public void addTeam(Team team) {
        _teams.add(team);
    }

    public void removeTeam(Team team) {
        _teams.remove(team);
    }
}
