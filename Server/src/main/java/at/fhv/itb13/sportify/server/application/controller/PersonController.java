package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.PersonMapper;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.database.PersonDAO;
import at.fhv.itb13.sportify.server.model.Person;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mod on 10/27/15.
 */
public class PersonController {
    private static PersonController ourInstance = new PersonController(new PersonMapper());
    private DBFacade _facade;

    public static PersonController getInstance() {
        return ourInstance;
    }

    private PersonMapper _personMapper;

    private PersonController(PersonMapper personMapper) {
        _personMapper = personMapper;
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
     * @return the searched person or throws an exception
     */
    public List<PersonDTO> getPerson(String fname, String lname, String street, String houseNumber, String postalCode, String city, String email, String birthdate) {
        List<PersonDTO> foundpersons = new LinkedList<>();
        List<Person> temp = new LinkedList<>();
        try {
            _facade.beginTransaction();
            List<Person> personList = _facade.getAll(Person.class);
            for (Person person : personList) {
                if (fname == person.getFName()) {
                    temp.add(person);
                }
            }
            for (Person p : temp) {
                if (lname != p.getLName()) {
                    temp.remove(p);
                } else if (street != p.getStreet()) {
                    temp.remove(p);
                } else if (houseNumber != p.getHouseNumber()) {
                    temp.remove(p);
                } else if (postalCode != p.getPostalCode()) {
                    temp.remove(p);
                } else if (city != p.getCity()) {
                    temp.remove(p);
                } else if (email != p.getEmail()) {
                    temp.remove(p);
                } else if (birthdate != p.getBirthdate()) {
                    temp.remove(p);
                }
            }
            for (Person p : temp) {
                foundpersons.add(_personMapper.toDTOObject(p));
            }
            return foundpersons;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
