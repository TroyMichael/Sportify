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
    private Set<Tournament> _tournaments = new HashSet<>();
    private Set<Team> _teams = new HashSet<>();

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
        Department oldDepartment = _department;
        _department = department;
        if ((oldDepartment != null) && (!oldDepartment.equals(_department))) {
            Set<Sport> oldDepartmentSports = oldDepartment.getSports();
            if ((oldDepartmentSports != null) && (oldDepartmentSports.contains(this))) {
                oldDepartment.removeSport(this);
            }
        }
        if ((_department != null) && (!_department.equals(oldDepartment))) {
            Set<Sport> newDepartmentSports = _department.getSports();
            if ((newDepartmentSports != null) && (!newDepartmentSports.contains(this))) {
                _department.addSport(this);
            }
        }
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sport")
    public Set<Team> getTeams() {
        return _teams;
    }

    public void setTeams(Set<Team> teams) {
        _teams = teams;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sport")
    public Set<Tournament> getTournaments() {
        return _tournaments;
    }

    public void setTournaments(Set<Tournament> tournaments) {
        _tournaments = tournaments;
    }

    public void addTeam(Team team) {
        _teams.add(team);
    }

    public void removeTeam(Team team) {
        _teams.remove(team);
    }

    public void addTournament(Tournament tournament) {
        _tournaments.add(tournament);
    }

    public void removeTournament(Tournament tournamet) {
        _tournaments.remove(tournamet);
    }
}
