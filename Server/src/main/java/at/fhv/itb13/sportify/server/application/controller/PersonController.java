package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.exceptions.DomainObjectIsNullException;
import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.PersonMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.database.PersonDAO;
import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;

import java.util.*;
import java.util.stream.Collectors;

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
        _facade = new DBFacadeImpl();
    }

    /**
     * Create a new entry in the table person
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
    public void saveOrUpdate(PersonDTO person) {
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
     * search for a person by a given set of various strings
     *
     * @param personDTO the person you are searching for with the given attributes from the search view
     */
    public List<PersonDTO> searchPerson(PersonDTO personDTO) {
        //fetch all the persons from the database
        List<Person> personList = new LinkedList<>();
        try {
            _facade.beginTransaction();
            personList = _facade.getAll(Person.class);
            _facade.commitTransaction();
        } catch (Exception e) {
            _facade.rollbackTransaction();
        }

        /*
        create the lists necessary for keeping the results for each search query and the result set which contains
        all the other lists with results
        */
        List<List<Person>> resultsSet = new ArrayList<List<Person>>();
        List<Person> lastNameResults;
        List<Person> firstNameResults;
        List<Person> streetResults;
        List<Person> houseNumberResults;
        List<Person> postalCodeResults;
        List<Person> cityResults;
        List<Person> emailResults;
        List<Person> birthdateResults;

        //search for all persons whose last names fits the search query
        if (personDTO.getLName().length() > 0) {
            lastNameResults = personList.stream().filter(person -> person.getLName().toLowerCase().contains(personDTO.getLName().toLowerCase())).collect(Collectors.toList());
            resultsSet.add(lastNameResults);
        }

        //search for all persons whose first names fits the search query
        if (personDTO.getFName().length() > 0) {
            firstNameResults = personList.stream().filter(person -> person.getFName().toLowerCase().contains(personDTO.getFName().toLowerCase())).collect(Collectors.toList());
            resultsSet.add(firstNameResults);
        }

        //search for all persons whose street names fits the search query
        if (personDTO.getStreet().length() > 0) {
            streetResults = personList.stream().filter(person -> person.getStreet().toLowerCase().contains(personDTO.getStreet().toLowerCase())).collect(Collectors.toList());
            resultsSet.add(streetResults);
        }

        //search for all persons whose house number fits the search query
        if (personDTO.getHouseNumber().length() > 0) {
            houseNumberResults = personList.stream().filter(person -> person.getHouseNumber().toLowerCase().contains(personDTO.getHouseNumber().toLowerCase())).collect(Collectors.toList());
            resultsSet.add(houseNumberResults);
        }

        //search for all persons whose psotal code fits the search query
        if (personDTO.getPostalCode().length() > 0) {
            postalCodeResults = personList.stream().filter(person -> person.getPostalCode().toLowerCase().contains(personDTO.getPostalCode().toLowerCase())).collect(Collectors.toList());
            resultsSet.add(postalCodeResults);
        }

        //search for all persons whose city fits the search query
        if (personDTO.getCity().length() > 0) {
            cityResults = personList.stream().filter(person -> person.getCity().toLowerCase().contains(personDTO.getCity().toLowerCase())).collect(Collectors.toList());
            resultsSet.add(cityResults);
        }

        //search for all persons whose email address fits the search query
        if (personDTO.getEmail().length() > 0) {
            emailResults = personList.stream().filter(person -> person.getEmail().toLowerCase().contains(personDTO.getEmail().toLowerCase())).collect(Collectors.toList());
            resultsSet.add(emailResults);
        }

        //search for all persons whose birthdate fits the search query
        if (personDTO.getBirthdate().length() > 0) {
            birthdateResults = personList.stream().filter(person -> person.getBirthdate().toLowerCase().contains(personDTO.getBirthdate().toLowerCase())).collect(Collectors.toList());
            resultsSet.add(birthdateResults);
        }

        //get common elements fetches the objects which are contained in all the filled result lists
        List<Person> personResults = new ArrayList<>();
        personResults.addAll(getCommonElements(resultsSet));

        //remap the persons to the personDTO
        List<PersonDTO> personDTOResults = null;
        try {
            personDTOResults = _personMapper.listToDTO(personResults);
        } catch (DomainObjectIsNullException e) {
            e.printStackTrace();
        }

        return personDTOResults;
    }

    /**
     * will search common persons in all given collections and returns them
     *
     * @param collections a collection of collection containing persons
     */
    private Set<Person> getCommonElements(Collection<? extends Collection<Person>> collections) {
        Set<Person> common = new LinkedHashSet<Person>();
        if (!collections.isEmpty()) {
            Iterator<? extends Collection<Person>> iterator = collections.iterator();
            common.addAll(iterator.next());
            while (iterator.hasNext()) {
                common.retainAll(iterator.next());
            }
        }
        return common;
    }

}
