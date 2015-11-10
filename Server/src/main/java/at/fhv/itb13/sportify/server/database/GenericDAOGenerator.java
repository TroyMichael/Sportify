package at.fhv.itb13.sportify.server.database;

import at.fhv.itb13.sportify.server.database.dao.PersonDAO;
import at.fhv.itb13.sportify.server.database.dao.SportDAO;
import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.server.model.Sport;

/**
 * Created by mod on 11/4/15.
 */
public class GenericDAOGenerator<T> {
    private GenericDAO _personDAO = null;
    private GenericDAO _sportDAO = null;
    private static GenericDAOGenerator _instance = null;
    protected GenericDAOGenerator() {

    }
    public static GenericDAOGenerator getInstance(){
        if(_instance == null){
            _instance = new GenericDAOGenerator();
        }
        return _instance;
    }
    public GenericDAO getDAO(T object){
        GenericDAO returndao = null;
        if(object.equals(Sport.class)){
            if(_sportDAO == null){
                _sportDAO = new SportDAO();
            }
            returndao = _sportDAO;
        }
        if(object.equals(Person.class)){
            if(_personDAO == null){
                _personDAO = new PersonDAO();
            }
            returndao = _personDAO;
        }
        return returndao;
    }
}
