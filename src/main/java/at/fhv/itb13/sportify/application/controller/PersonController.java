package at.fhv.itb13.sportify.application.controller;

import at.fhv.itb13.sportify.dataTransfer.DTOObject;
import at.fhv.itb13.sportify.dataTransfer.dtoInterfaces.PersonDTO;
import at.fhv.itb13.sportify.dataTransfer.mapper.PersonMapper;
import at.fhv.itb13.sportify.database.DBFacade;
import at.fhv.itb13.sportify.database.DBFacadeImpl;
import at.fhv.itb13.sportify.database.PersonDAO;
import at.fhv.itb13.sportify.model.entities.Person;
import at.fhv.itb13.sportify.model.interfaces.PersonRestricted;
import at.fhv.itb13.sportify.util.IdGenerator;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mod on 10/27/15.
 *
 */
public class PersonController {
    private static PersonController ourInstance = new PersonController(new PersonMapper());
    private DBFacade _facade;

    public static PersonController getInstance() {
        return ourInstance;
    }

    private PersonMapper _personMapper;

    public PersonController(PersonMapper personMapper) {
        _personMapper = personMapper;
        _facade = new DBFacadeImpl(new PersonDAO());
    }

    private PersonController() {
        _facade = new DBFacadeImpl(new PersonDAO());
    }


    /**
     * Create a new Entry in the table person
     *
     * @param person save this object
     */
    public void create(PersonDTO person) {

        try {
            Person personDomain = _personMapper.toDomainObject(person);
            _facade.beginTransaction();
            _facade.create(personDomain);
            _facade.commitTransaction();
        } catch (Exception e) {
            _facade.rollbackTransaction();
            e.printStackTrace();
        }
    }

    /**
     * Update the content of a person
     *
     * @param person person object with changed values
     */
    public void saveOrupdate(PersonDTO person) {


        try {
            Person personDomain = _personMapper.toDomainObject(person);
            _facade.beginTransaction();
            _facade.createOrUpdate(personDomain);
            _facade.commitTransaction();
        } catch (Exception e) {
            _facade.rollbackTransaction();
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
    public List<PersonRestricted> getPerson(String input) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
