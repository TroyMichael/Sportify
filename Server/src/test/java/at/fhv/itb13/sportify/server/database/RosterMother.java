package at.fhv.itb13.sportify.server.database;

import at.fhv.itb13.sportify.server.model.MatchTeam;
import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.server.model.Roster;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

public class RosterMother extends PersistentObjectMother<Roster, RosterMother> {

    private String _name = "name";
    private Set<MatchTeam> _matchTeams = new HashSet<MatchTeam>();
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
        roster.setMatchTeams(_matchTeams);
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

    public RosterMother matchTeam(MatchTeam matchTeam) {
        _matchTeams.add(matchTeam);
        return this;
    }

    public RosterMother person(Person person) {
        _persons.add(person);
        return this;
    }
}