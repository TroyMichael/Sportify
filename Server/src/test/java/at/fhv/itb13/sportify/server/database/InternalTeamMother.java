package at.fhv.itb13.sportify.server.database;

import at.fhv.itb13.sportify.server.model.*;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

public class InternalTeamMother extends PersistentObjectMother<InternalTeam, InternalTeamMother> {

    private String _name = "name";
    private Person _trainer;
    private Sport _sport;
    private Set<Person> _persons = new HashSet<Person>();
    private Set<Tournament> _tournaments = new HashSet<>();
    private Set<MatchTeam> _matchTeams = new HashSet<MatchTeam>();

    public InternalTeamMother() {
        super(InternalTeam.class);
    }

    public InternalTeamMother(Session session) {
        super(session, InternalTeam.class);
    }

    public InternalTeamMother(Session session, String defaultId) {
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

    public InternalTeamMother name(String name) {
        _name = name;
        return this;
    }

    public InternalTeamMother trainer(Person trainer) {
        _trainer = trainer;
        return this;
    }

    public InternalTeamMother sport(Sport sport) {
        _sport = sport;
        return this;
    }

    public InternalTeamMother person(Person person) {
        _persons.add(person);
        return this;
    }

    public InternalTeamMother tournament(Tournament tournament) {
        _tournaments.add(tournament);
        return this;
    }

    public InternalTeamMother matchTeam(MatchTeam matchTeam) {
        _matchTeams.add(matchTeam);
        return this;
    }
}