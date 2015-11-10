package at.fhv.itb13.sportify.server.database;

import at.fhv.itb13.sportify.server.model.*;

/**
 * Created by mod on 11/4/15.
 */
public class GenericDAOGenerator<T> {
    private GenericDAO _personDAO = null;
    private GenericDAO _sportDAO = null;
    private GenericDAO _teamDAO = null;
    private GenericDAO _departmentDAO = null;
    private GenericDAO _rosterDAO = null;
    private static GenericDAOGenerator _instance = null;

    protected GenericDAOGenerator() {

    }

    public static GenericDAOGenerator getInstance() {
        if (_instance == null) {
            _instance = new GenericDAOGenerator();
        }
        return _instance;
    }

    public GenericDAO getDAO(T object) {
        GenericDAO returndao = null;
        if (object.equals(Sport.class)) {
            if (_sportDAO == null) {
                _sportDAO = new SportDAO();
            }
            returndao = _sportDAO;
        }
        if (object.equals(Person.class)) {
            if (_personDAO == null) {
                _personDAO = new PersonDAO();
            }
            returndao = _personDAO;
        }
        if (object.equals(Team.class)) {
            if (_teamDAO == null) {
                _teamDAO = new TeamDAO();
            }
            returndao = _teamDAO;
        }
        if (object.equals(Department.class)) {
            if (_departmentDAO == null) {
                _departmentDAO = new DepartmentDAO();
            }
            returndao = _departmentDAO;
        }
        if (object.equals(Roster.class)) {
            if (_rosterDAO == null) {
                _rosterDAO = new RosterDAO();
            }
            returndao = _rosterDAO;
        }
        return returndao;
    }
}
