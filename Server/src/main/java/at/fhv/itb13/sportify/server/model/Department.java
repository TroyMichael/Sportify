package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "department")
public class Department extends PersistentObjectImpl {

    private String _name;
    private String _description;
    private Collection<Sport> _sports;


    public Department() {
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
    public Collection<Sport> getSports() {
        return _sports;
    }

    public void setSports(Collection<Sport> sports) {
        _sports = sports;
    }



    public void addSport(Sport sport) {
        _sports.add(sport);
    }

    public void removeSport(Sport sport) {
        _sports.remove(sport);
    }
}
