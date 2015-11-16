package at.fhv.itb13.sportify.database;

import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.server.model.Roster;
import at.fhv.itb13.sportify.server.model.Team;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

public class PersonObjectMother extends PersistentObjectMother<Person> {

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

    public PersonObjectMother(Session session, String defaultId) {
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

    public PersonObjectMother fname(String fname) {
        _fname = fname;
        return this;
    }

    public PersonObjectMother lname(String lname) {
        _lname = lname;
        return this;
    }

    public PersonObjectMother street(String street) {
        _street = street;
        return this;
    }

    public PersonObjectMother houseNumber(String houseNumber) {
        _houseNumber = houseNumber;
        return this;
    }

    public PersonObjectMother postalCode(String postalCode) {
        _postalCode = postalCode;
        return this;
    }

    public PersonObjectMother city(String city) {
        _city = city;
        return this;
    }

    public PersonObjectMother email(String email) {
        _email = email;
        return this;
    }

    public PersonObjectMother birthdate(String birthdate) {
        _birthdate = birthdate;
        return this;
    }

    public PersonObjectMother paid(Boolean paid) {
        _paid = paid;
        return this;
    }

    public PersonObjectMother roster(Roster roster) {
        _rosters.add(roster);
        return this;
    }

    public PersonObjectMother team(Team team) {
        _teams.add(team);
        return this;
    }

    public PersonObjectMother trainedTeam(Team trainedTeam) {
        _trainedTeams.add(trainedTeam);
        return this;
    }
}
