package at.fhv.itb13.sportify.database;

import at.fhv.itb13.sportify.server.model.Department;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.server.model.Tournament;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

public class SportMother extends PersistentObjectMother<Sport, SportMother> {

    private String _name = "name";
    private Department _department;
    private Set<Tournament> _tournaments = new HashSet<Tournament>();
    private Set<Team> _teams = new HashSet<Team>();

    public SportMother() {
        super(Sport.class);
    }

    public SportMother(Session session) {
        super(session, Sport.class);
    }

    public SportMother(Session session, String defaultId) {
        super(session, Sport.class, defaultId);
    }

    @Override
    protected void configureInstance(Sport sport) {
        sport.setName(_name);
        sport.setDepartment(_department);
        sport.setTournaments(_tournaments);
        sport.setTeams(_teams);
    }

    @Override
    protected Sport createInstance() {
        Sport sport = new Sport();
        sport.setId(getId());
        return sport;
    }

    public SportMother name(String name) {
        _name = name;
        return this;
    }

    public SportMother department(Department department) {
        _department = department;
        return this;
    }

    public SportMother tournament(Tournament tournament) {
        _tournaments.add(tournament);
        return this;
    }

    public SportMother team(Team team) {
        _teams.add(team);
        return this;
    }
}