package at.fhv.itb13.sportify.database;

import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.server.model.Roster;
import at.fhv.itb13.sportify.server.model.Team;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

public class PersonMother extends PersistentObjectMother<Person> {

    private String _fname = "fname";
    private String _lname = "lname";
    private String _street = "street";
    private String _houseNumber = "houseNumber";
    private String _postalCode = "postalCode";
    private String _city = "city";
    private String _email = "email";
    private String _birthdate = "01.01.1900";
    private Boolean _paid = true;
    private Set<Roster> _rosters = new HashSet<Roster>();
    private Set<Team> _teams = new HashSet<Team>();
    private Set<Team> _trainedTeams = new HashSet<>();

    public PersonMother(Session session, String defaultId) {
        super(session, Person.class, defaultId);
    }

    @Override
    protected void configureInstance(Person person) {
        person.setFName(_fname);
        person.setLName(_lname);
        person.setStreet(_street);
        person.setHouseNumber(_houseNumber);
        person.setPostalCode(_postalCode);
        person.setCity(_city);
        person.setEmail(_email);
        person.setBirthdate(_birthdate);
        person.setPaid(_paid);
        person.setRosters(_rosters);
        person.setTeams(_teams);
        person.setTrainedTeams(_trainedTeams);
    }

    @Override
    protected Person createInstance() {
        Person person = new Person();
        person.setId(getId());
        return person;
    }

    public PersonMother fname(String fname) {
        _fname = fname;
        return this;
    }

    public PersonMother lname(String lname) {
        _lname = lname;
        return this;
    }

    public PersonMother street(String street) {
        _street = street;
        return this;
    }

    public PersonMother houseNumber(String houseNumber) {
        _houseNumber = houseNumber;
        return this;
    }

    public PersonMother postalCode(String postalCode) {
        _postalCode = postalCode;
        return this;
    }

    public PersonMother city(String city) {
        _city = city;
        return this;
    }

    public PersonMother email(String email) {
        _email = email;
        return this;
    }

    public PersonMother birthdate(String birthdate) {
        _birthdate = birthdate;
        return this;
    }

    public PersonMother paid(Boolean paid) {
        _paid = paid;
        return this;
    }

    public PersonMother roster(Roster roster) {
        _rosters.add(roster);
        return this;
    }

    public PersonMother team(Team team) {
        _teams.add(team);
        return this;
    }

    public PersonMother trainedTeam(Team trainedTeam) {
        _trainedTeams.add(trainedTeam);
        return this;
    }
}
