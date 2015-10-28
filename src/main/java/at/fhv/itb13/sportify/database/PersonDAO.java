package at.fhv.itb13.sportify.database;

import at.fhv.itb13.sportify.model.entities.Person;

public class PersonDAO extends GenericDAOImpl<Person, String> {

    public PersonDAO() {
        super(Person.class);
    }
}
