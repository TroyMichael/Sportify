package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.communication.datatransfer.exceptions.DomainObjectIsNullException;
import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTOImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KYUSS on 28.10.2015.
 */
public class PersonMapper extends Mapper<PersonDTO, Person> {

    @Override
    public Person toDomainObject(PersonDTO personDTO) {
        if (personDTO != null) {
            Person person = new Person(
                    personDTO.getFName(),
                    personDTO.getLName(),
                    personDTO.getStreet(),
                    personDTO.getHouseNumber(),
                    personDTO.getPostalCode(),
                    personDTO.getCity(),
                    personDTO.getEmail(),
                    personDTO.getBirthdate(),
                    personDTO.getPayed()
            );
            person.setId(personDTO.getId());
            person.setVersion(personDTO.getVersion());
            return person;
        }
        return null;
    }

    @Override
    public PersonDTO toDTOObject(Person person) {
        if (person != null) {
            PersonDTO personDTO = new PersonDTOImpl();
            personDTO.setFName(person.getFName());
            personDTO.setLName(person.getLName());
            personDTO.setStreet(person.getStreet());
            personDTO.setHouseNumber(person.getHouseNumber());
            personDTO.setPostalCode(person.getPostalCode());
            personDTO.setCity(person.getCity());
            personDTO.setEmail(person.getEmail());
            personDTO.setBirthdate(person.getBirthdate());
            personDTO.setVersion(person.getVersion());
            personDTO.setId(person.getId());
            personDTO.setPayed(person.isPayed());
            return personDTO;
        }
        return null;
    }

    public List<PersonDTO> listToDTO(List<Person> persons) {
        List<PersonDTO> personDTOs = new ArrayList<>();
        for (Person person : persons) {
            personDTOs.add(toDTOObject(person));
        }
        return personDTOs;
    }
}
