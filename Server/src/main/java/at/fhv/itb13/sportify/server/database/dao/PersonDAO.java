package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.GenericDAOImpl;
import at.fhv.itb13.sportify.server.model.Person;

public class PersonDAO extends GenericDAOImpl<Person, String> {

    public PersonDAO() {
        super(Person.class);
    }
}
