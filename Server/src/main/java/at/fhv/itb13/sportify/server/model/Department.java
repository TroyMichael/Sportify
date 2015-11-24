package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "department")
public class Department extends PersistentObjectImpl {

    private String _name;
    private String _description;
    private Set<Sport> _sports = new HashSet<>();

    public Department() {
    }

    public Department(String name, String description) {
        _name = name;
        _description = description;
    }

    @Column(name = "name")
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    public Set<Sport> getSports() {
        return _sports;
    }

    public void setSports(Set<Sport> sports) {
        for (Sport sport : _sports) {
            sport.setDepartment(null);
        }
        _sports = sports;
        for (Sport sport : _sports) {
            sport.setDepartment(this);
        }
    }

    public void addSport(Sport sport) {
        _sports.add(sport);
        if (sport != null) {
            sport.setDepartment(this);
        }
    }

    public void removeSport(Sport sport) {
        _sports.remove(sport);
        if (sport != null) {
            sport.setDepartment(null);
        }
    }
}
