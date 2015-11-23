package at.fhv.itb13.sportify.database;

import at.fhv.itb13.sportify.server.model.*;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

public class TeamMother extends PersistentObjectMother<InternalTeam, TeamMother> {

    private String _name = "name";
    private Person _trainer;
    private Sport _sport;
    private Set<Person> _persons = new HashSet<Person>();
    private Set<Tournament> _tournaments = new HashSet<>();
    private Set<MatchTeam> _matchTeams = new HashSet<MatchTeam>();

    public TeamMother() {
        super(InternalTeam.class);
    }

    public TeamMother(Session session) {
        super(session, InternalTeam.class);
    }

    public TeamMother(Session session, String defaultId) {
        super(session, InternalTeam.class, defaultId);
    }

    @Override
    protected void configureInstance(InternalTeam team) {
        team.setName(_name);
        team.setTrainer(_trainer);
        team.setSport(_sport);
        team.setPersons(_persons);
        team.setTournaments(_tournaments);
        team.setMatchTeams(_matchTeams);
    }

    @Override
    protected InternalTeam createInstance() {
        InternalTeam team = new InternalTeam();
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

    public TeamMother tournament(Tournament tournament) {
        _tournaments.add(tournament);
        return this;
    }

    public TeamMother matchTeam(MatchTeam matchTeam) {
        _matchTeams.add(matchTeam);
        return this;
    }
}