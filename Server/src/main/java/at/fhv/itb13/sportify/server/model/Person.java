package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person extends PersistentObjectImpl {

    private String _fname = "";
    private String _lname = "";
    private String _street = "";
    private String _houseNumber = "";
    private String _postalCode = "";
    private String _city = "";
    private String _email = "";
    private String _birthdate = "";
    private Boolean _payed = false;
    private Set<Roster> _rosters = new HashSet<Roster>();
    private Set<Team> _teams = new HashSet<Team>();

    public Person() {
    }

    public Person(String fname, String lname, String street, String houseNumber, String postalCode, String city, String email, String birthdate, Boolean payed) {
        _fname = fname;
        _lname = lname;
        _street = street;
        _houseNumber = houseNumber;
        _postalCode = postalCode;
        _city = city;
        _email = email;
        _birthdate = birthdate;
        _payed = payed;
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

    @Column(name = "payed")
    public Boolean isPayed() {
        return _payed;
    }

    public void setPayed(Boolean payed) {
        _payed = payed;
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
    public Set<Team> getTeams() {
        return _teams;
    }

    public void setTeams(Set<Team> teams) {
        _teams = teams;
    }

    public void addRoster(Roster roster) {
        _rosters.add(roster);
    }

    public void removeRoster(Roster roster) {
        _rosters.remove(roster);
    }

    public void addTeam(Team team) {
        _teams.add(team);
    }

    public void removeTeam(Team team) {
        _teams.remove(team);
    }
}
