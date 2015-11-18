package at.fhv.itb13.sportify.database;

import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.server.model.Roster;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.server.model.Team;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

public class TeamMother extends PersistentObjectMother<Team, TeamMother> {

    private String _name = "name";
    private Person _trainer;
    private Sport _sport;
    private Set<Person> _persons = new HashSet<Person>();
    private Set<Roster> _rosters = new HashSet<Roster>();

    public TeamMother() {
        super(Team.class);
    }

    public TeamMother(Session session) {
        super(session, Team.class);
    }

    public TeamMother(Session session, String defaultId) {
        super(session, Team.class, defaultId);
    }

    @Override
    protected void configureInstance(Team team) {
        team.setName(_name);
        team.setTrainer(_trainer);
        team.setSport(_sport);
        team.setPersons(_persons);
        team.setRosters(_rosters);
    }

    @Override
    protected Team createInstance() {
        Team team = new Team();
        team.setId(getId());
        return team;
    }

    public TeamMother name(String name) {
        _name = name;
        return this;
    }

    public TeamMother trainer(Person trainer) {
        _trainer = trainer;
        return this;
    }

    public TeamMother sport(Sport sport) {
        _sport = sport;
        return this;
    }

    public TeamMother person(Person person) {
        _persons.add(person);
        return this;
    }

    public TeamMother roster(Roster roster) {
        _rosters.add(roster);
        return this;
    }
}