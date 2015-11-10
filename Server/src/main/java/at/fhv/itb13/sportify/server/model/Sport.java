package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "sport")
public class Sport extends PersistentObjectImpl {

    private String _name;
    private Department _department;
    private Collection<Team> _teams;

    public Sport() {
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    public Collection<Team> getTeams() {
        return _teams;
    }

    public void setTeams(Collection<Team> teams) {
        _teams = teams;
    }

    public void addTeam(Team team){
        _teams.add(team);
    }

    public void removeTeam(Team team){
        _teams.remove(team);
    }
}
