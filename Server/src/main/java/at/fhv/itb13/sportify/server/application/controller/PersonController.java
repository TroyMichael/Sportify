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
     *
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
        List <Person> personList = new LinkedList<>();
        _facade.beginTransaction();
        personList = _facade.getAll(Person.class);

        List <List<Person>> resultsSet = new ArrayList<List<Person>>();
        List <Person> lastNameResults;
        List <Person> firstNameResults;
        List <Person> streetResults;
        List <Person> houseNumberResults;
        List <Person> postalCodeResults;
        List <Person> cityResults;
        List <Person> emailResults;
        List <Person> birthdateResults;


        if (personDTO.getLName().length() > 0){
            lastNameResults = personList.stream().filter(person -> person.getLName().equals(personDTO.getLName())).collect(Collectors.toList());
            resultsSet.add(lastNameResults);
        }
        if (personDTO.getFName().length() > 0){
            firstNameResults = personList.stream().filter(person -> person.getFName().equals(personDTO.getFName())).collect(Collectors.toList());
            resultsSet.add(firstNameResults);
        }
        if (personDTO.getStreet().length() > 0){
            streetResults = personList.stream().filter(person -> person.getStreet().equals(personDTO.getStreet())).collect(Collectors.toList());
            resultsSet.add(streetResults);
        }
        if (personDTO.getHouseNumber().length() > 0){
            houseNumberResults = personList.stream().filter(person -> person.getHouseNumber().equals(personDTO.getHouseNumber())).collect(Collectors.toList());
            resultsSet.add(houseNumberResults);
        }
        if (personDTO.getPostalCode().length() > 0){
            postalCodeResults = personList.stream().filter(person -> person.getPostalCode().equals(personDTO.getPostalCode())).collect(Collectors.toList());
            resultsSet.add(postalCodeResults);
        }
        if (personDTO.getCity().length() > 0){
            cityResults = personList.stream().filter(person -> person.getCity().equals(personDTO.getCity())).collect(Collectors.toList());
            resultsSet.add(cityResults);
        }
        if (personDTO.getEmail().length() > 0){
            emailResults = personList.stream().filter(person -> person.getEmail().equals(personDTO.getEmail())).collect(Collectors.toList());
            resultsSet.add(emailResults);
        }
        if (personDTO.getBirthdate().length() > 0){
            birthdateResults = personList.stream().filter(person -> person.getBirthdate().equals(personDTO.getBirthdate())).collect(Collectors.toList());
            resultsSet.add(birthdateResults);
        }

        List <Person> personResults = new ArrayList<>();
        personResults.addAll(getCommonElements(resultsSet));

        List <PersonDTO> personDTOResults = null;
        try {
            personDTOResults = _personMapper.listToDTO(personResults);
        } catch (DomainObjectIsNullException e) {
            e.printStackTrace();
        }

        return personDTOResults;
    }

    private Set <Person> getCommonElements (Collection <? extends Collection <Person>> collections){
        Set <Person> common = new LinkedHashSet<Person>();
        if (!collections.isEmpty()){
            Iterator <? extends Collection<Person>> iterator = collections.iterator();
            common.addAll(iterator.next());
            while (iterator.hasNext()){
                common.retainAll(iterator.next());
            }
        }
        return common;
    }

}
