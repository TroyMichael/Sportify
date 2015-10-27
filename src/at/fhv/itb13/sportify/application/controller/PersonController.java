package at.fhv.itb13.sportify.application.controller;

import at.fhv.itb13.sportify.database.DBFacade;
import at.fhv.itb13.sportify.database.DBFacadeImpl;
import at.fhv.itb13.sportify.database.PersonDAO;
import at.fhv.itb13.sportify.model.entities.Person;
import at.fhv.itb13.sportify.model.interfaces.PersonRestricted;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mod on 10/27/15.
 */
public class PersonController {
    private static PersonController ourInstance = new PersonController();
    private DBFacade _facade;
    public static PersonController getInstance() {
        return ourInstance;
    }

    private PersonController() {
        _facade = new DBFacadeImpl(new PersonDAO());
    }
    /**
     * Create a new Entry in the table person
     *
     * @param person save this object
     */
    public void create(Person person) {
        try {
            _facade.beginTransaction();
            _facade.create(person);
            _facade.commitTransaction();
            _facade.rollbackTransaction();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Update the content of a person
     *
     * @param person person object with changed values
     */
    public void saveOrupdate(Person person) {
        try{
            _facade.beginTransaction();
            _facade.createOrUpdate(person);
            _facade.commitTransaction();
            _facade.rollbackTransaction();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * TODO: Suche nicht sehr performant
     * Search for a specific Person in the Database
     *
     * @param input search for this parameter
     * @return the searched person or throws an exception
     */
    public List<PersonRestricted> getPerson(String input){
        List<PersonRestricted> foundpersons = new LinkedList<>();
        try {
            _facade.beginTransaction();
            List<Person> personList = _facade.getAll(Person.class);
            for (Person person : personList) {
                System.out.println(person.getFName());
                if (input == person.getFName()) {
                    foundpersons.add(person);
                }
                if (input == person.getLName()) {
                    foundpersons.add(person);
                }
            }
            return foundpersons;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
