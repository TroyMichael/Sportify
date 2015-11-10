package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "team")
public class Team extends PersistentObjectImpl {

    private String _name;
    private Sport _sport;
    private Collection<Person> _persons;
    private Collection<Roster> _rosters;

    public Team() {
    }

    @Column(name = "name")
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }


   public Sport getSport(){
       return _sport;
   }

    public void setSport(Sport sport){
        _sport = sport;
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
