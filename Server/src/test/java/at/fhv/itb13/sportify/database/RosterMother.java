package at.fhv.itb13.sportify.database;

import at.fhv.itb13.sportify.server.model.*;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

public class RosterMother extends PersistentObjectMother<Roster, RosterMother> {

    private String _name = "name";
    private Team _team;
    private Match _match;
    private Set<Person> _persons = new HashSet<Person>();

    public RosterMother() {
        super(Roster.class);
    }

    public RosterMother(Session session) {
        super(session, Roster.class);
    }

    public RosterMother(Session session, String defaultId) {
        super(session, Roster.class, defaultId);
    }

    @Override
    protected void configureInstance(Roster roster) {
        roster.setName(_name);
        roster.setTeam(_team);
        roster.setMatch(_match);
        roster.setPersons(_persons);
    }

    @Override
    protected Roster createInstance() {
        Roster roster = new Roster();
        roster.setId(getId());
        return roster;
    }

    public RosterMother name(String name) {
        _name = name;
        return this;
    }

    public RosterMother team(Team team) {
        _team = team;
        return this;
    }

    public RosterMother match(Match match) {
        _match = match;
        return this;
    }

    public RosterMother person(Person person) {
        _persons.add(person);
        return this;
    }
}