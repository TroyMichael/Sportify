package at.fhv.itb13.sportify.application.controller;

import at.fhv.itb13.sportify.communication.datatransfer.mapper.PersonMapper;
import at.fhv.itb13.sportify.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.database.DBFacade;
import at.fhv.itb13.sportify.database.DBFacadeImpl;
import at.fhv.itb13.sportify.database.PersonDAO;
import at.fhv.itb13.sportify.model.Person;

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

    public List <PersonDTO> searchPerson (PersonDTO personDTO){
        List <PersonDTO> foundPersons = new LinkedList<>();
        List <Person> personList = new LinkedList<>();

        _facade.beginTransaction();
        personList = _facade.getAll(Person.class);
        List <Person> temporaryResults = new LinkedList<>();

        if (personDTO.getLName().length() > 0){
            for (Person person : personList){
                if (personDTO.getLName() == person.getLName()){
                    temporaryResults.add(person);
                }
            }
        } else if (personDTO.getFName().length() > 0){
            for (Person person : personList){
                if (personDTO.getFName() == person.getFName()){
                    temporaryResults.add(person);
                }
            }
        } else if (personDTO.getBirthdate().length() > 0){
            for (Person person : personList){
                if (personDTO.getBirthdate() == person.getBirthdate()){
                    temporaryResults.add(person);
                }
            }
        }
        return null;
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
