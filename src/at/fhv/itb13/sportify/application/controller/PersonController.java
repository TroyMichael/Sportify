package at.fhv.itb13.sportify.application.controller;

import at.fhv.itb13.sportify.application.exception.PersonNotFoundException;
import at.fhv.itb13.sportify.database.DBFacade;
import at.fhv.itb13.sportify.database.DBFacadeImpl;
import at.fhv.itb13.sportify.database.PersonDAO;
import at.fhv.itb13.sportify.model.entities.Person;

import java.util.List;

/**
 * Created by Niklas Fessler on 10/27/15.
 * Search, edit or delete a Person from the Database
 * Controller is Singelton
 */
public class PersonController {
    private DBFacade _facade;
    private static PersonController _personController;

    private void PersonController() {
        _facade = new DBFacadeImpl(new PersonDAO());
    }

    public static PersonController getInstance() {
        if (_personController == null) {
            _personController = new PersonController();
        }
        return _personController;
    }

    /**
     * Create a new Entry in the table person
     *
     * @param person save this object
     */
    public void create(Person person) {
        _facade.beginTransaction();
        _facade.create(person);
        _facade.commitTransaction();
        //     _facade.rollbackTransaction();
    }

    /**
     * Update the content of a person
     *
     * @param person person object with changed values
     */
    public void saveOrupdate(Person person) {
        _facade.beginTransaction();
        _facade.createOrUpdate(person);
        _facade.commitTransaction();

    }

    /**
     * TODO: Suche nicht sehr performant
     * Search for a specific Person in the Database
     *
     * @param input search for this parameter
     * @return the searched person or throws an exception
     */
    /*
    * TODO: exception statt null
     */
    public Person getPerson(String input) throws PersonNotFoundException {
        _facade.beginTransaction();
        List<Person> personList = _facade.getAll(Person.class);
        for (Person person : personList) {
            if (input == person.getFName()) {
                return person;
            }
            if (input == person.getLName()) {
                return person;
            } else {
                throw new PersonNotFoundException();
            }
        }  return null; //never reached
    }
}
