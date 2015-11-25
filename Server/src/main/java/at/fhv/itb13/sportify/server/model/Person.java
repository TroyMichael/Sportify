package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person extends PersistentObjectImpl {

    private String _fname;
    private String _lname;
    private String _street;
    private String _houseNumber;
    private String _postalCode;
    private String _city;
    private String _email;
    private String _birthdate;
    private Boolean _paid;
    private Set<Roster> _rosters = new HashSet<>();
    private Set<InternalTeam> _teams = new HashSet<>();
    private Set<InternalTeam> _trainedTeams = new HashSet<>();

    public Person() {
    }

    public Person(String fname, String lname, String street, String houseNumber, String postalCode, String city, String email, String birthdate, Boolean paid) {
        _fname = fname;
        _lname = lname;
        _street = street;
        _houseNumber = houseNumber;
        _postalCode = postalCode;
        _city = city;
        _email = email;
        _birthdate = birthdate;
        _paid = paid;
    }

    @Column(name = "firstname")
    public String getFName() {
        return _fname;
    }

    public void setFName(String fname) {
        _fname = fname;
    }

    @Column(name = "lastname")
    public String getLName() {
        return _lname;
    }

    public void setLName(String lname) {
        _lname = lname;
    }

    @Column(name = "street")
    public String getStreet() {
        return _street;
    }

    public void setStreet(String street) {
        _street = street;
    }

    @Column(name = "housenumber")
    public String getHouseNumber() {
        return _houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        _houseNumber = houseNumber;
    }

    @Column(name = "postalcode")
    public String getPostalCode() {
        return _postalCode;
    }

    public void setPostalCode(String postalCode) {
        _postalCode = postalCode;
    }

    @Column(name = "city")
    public String getCity() {
        return _city;
    }

    public void setCity(String city) {
        _city = city;
    }

    @Column(name = "email")
    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    @Column(name = "birthdate")
    public String getBirthdate() {
        return _birthdate;
    }

    public void setBirthdate(String birthdate) {
        _birthdate = birthdate;
    }

    @Column(name = "paid")
    public Boolean isPaid() {
        return _paid;
    }

    public void setPaid(Boolean paid) {
        _paid = paid;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roster_person", joinColumns = {@JoinColumn(name = "person_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "roster_id", nullable = false, updatable = false)})
    public Set<Roster> getRosters() {
        return _rosters;
    }

    public void setRosters(Set<Roster> rosters) {
        _rosters = rosters;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "team_person", joinColumns = {@JoinColumn(name = "person_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "team_id", nullable = false, updatable = false)})
    public Set<InternalTeam> getTeams() {
        return _teams;
    }

    public void setTeams(Set<InternalTeam> teams) {
        _teams = teams;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trainer")
    public Set<InternalTeam> getTrainedTeams() {
        return _trainedTeams;
    }

    public void setTrainedTeams(Set<InternalTeam> trainedTeams) {
        _trainedTeams = trainedTeams;
    }

    public void addRoster(Roster roster) {
        if (roster != null) {
            _rosters.add(roster);
        }
    }

    public void removeRoster(Roster roster) {
        if (roster != null) {
            _rosters.remove(roster);
        }
    }

    public void addTeam(InternalTeam team) {
        if (team != null) {
            _teams.add(team);
        }
    }

    public void removeTeam(InternalTeam team) {
        if (team != null) {
            _teams.remove(team);
        }
    }

    public void addTrainedTeam(InternalTeam trainedTeam) {
        if (trainedTeam != null) {
            _trainedTeams.add(trainedTeam);
        }
    }

    public void removeTrainedTeam(InternalTeam trainedTeam) {
        if (trainedTeam != null) {
            _trainedTeams.add(trainedTeam);
        }
    }
}
